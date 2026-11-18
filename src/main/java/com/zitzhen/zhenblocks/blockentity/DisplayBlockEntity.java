package com.zitzhen.zhenblocks.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.zitzhen.zhenblocks.registry.ZhenBlocksBlockEntities;

public class DisplayBlockEntity extends BlockEntity {
	public static final String TEXT_KEY = "text";
	public static final int MAX_TEXT_LENGTH = 128;

	private Component text = Component.empty();

	public DisplayBlockEntity(BlockPos pos, BlockState state) {
		super(ZhenBlocksBlockEntities.DISPLAY_BLOCK_ENTITY, pos, state);
	}

	public Component getText() {
		return text;
	}

	/** Sets the displayed text and syncs it to nearby clients. Call on the logical server. */
	public void setText(Component text) {
		this.text = text;
		this.setChanged();
		if (level != null && !level.isClientSide()) {
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putString(TEXT_KEY, text.getString());
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.text = Component.literal(input.getStringOr(TEXT_KEY, ""));
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = super.getUpdateTag(registries);
		tag.putString(TEXT_KEY, text.getString());
		return tag;
	}
}
