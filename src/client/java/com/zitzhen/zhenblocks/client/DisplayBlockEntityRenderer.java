package com.zitzhen.zhenblocks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import com.zitzhen.zhenblocks.block.DisplayBlock;
import com.zitzhen.zhenblocks.blockentity.DisplayBlockEntity;

public class DisplayBlockEntityRenderer
		implements BlockEntityRenderer<DisplayBlockEntity, DisplayBlockRenderState> {

	private static final float TEXT_SCALE = 1.0F / 18.0F;
	private static final float FACE_OFFSET = 0.502F;

	private final Font font;

	public DisplayBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
		this.font = context.font();
	}

	@Override
	public DisplayBlockRenderState createRenderState() {
		return new DisplayBlockRenderState();
	}

	@Override
	public void extractRenderState(DisplayBlockEntity blockEntity, DisplayBlockRenderState state,
			float partialTick, Vec3 cameraPosition,
			net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay overlay) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTick, cameraPosition, overlay);
		state.text = blockEntity.getText();
		state.facing = blockEntity.getBlockState().hasProperty(DisplayBlock.FACING)
				? blockEntity.getBlockState().getValue(DisplayBlock.FACING)
				: Direction.NORTH;
	}

	@Override
	public void submit(DisplayBlockRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
			CameraRenderState cameraState) {
		if (state.text.getString().isEmpty()) {
			return;
		}

		poseStack.pushPose();

		// BER origin is the block's center. Rotate so local +Z points toward the front face.
		// Axis.YP rotation applied to local +Z: 0=SOUTH(+Z), 90=EAST(+X),
		// 180=NORTH(-Z), 270=WEST(-X).
		float yRot = switch (state.facing) {
			case SOUTH -> 0.0F;
			case EAST -> 90.0F;
			case NORTH -> 180.0F;
			case WEST -> 270.0F;
			default -> 0.0F;
		};
		poseStack.rotateDegrees(Axis.YP, yRot);

		// Push slightly beyond the face to avoid z-fighting with the plank texture.
		poseStack.translate(0.0F, 0.0F, FACE_OFFSET);

		// Text is drawn in pixel units; scale so 18px spans one block.
		poseStack.scale(TEXT_SCALE, TEXT_SCALE, TEXT_SCALE);

		int width = font.width(state.text);
		collector.submitText(
				poseStack, -width / 2.0F, -4.0F,
				state.text.getVisualOrderText(),
				false, Font.DisplayMode.POLYGON_OFFSET,
				0, 0xFFFFFFFF, state.lightCoords, 0);

		poseStack.popPose();
	}
}
