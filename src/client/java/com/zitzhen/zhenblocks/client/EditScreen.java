package com.zitzhen.zhenblocks.client;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import com.zitzhen.zhenblocks.blockentity.DisplayBlockEntity;
import com.zitzhen.zhenblocks.network.SetDisplayTextPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class EditScreen extends Screen {
	private static final int MAX_LENGTH = DisplayBlockEntity.MAX_TEXT_LENGTH;

	private final BlockPos blockPos;
	private EditBox editBox;

	public EditScreen(BlockPos blockPos) {
		super(Component.translatable("zhenblocks.screen.edit.title"));
		this.blockPos = blockPos;
	}

	@Override
	protected void init() {
		int boxWidth = 200;
		int boxHeight = 20;
		int x = this.width / 2 - boxWidth / 2;
		int y = this.height / 2 - boxHeight / 2;

		this.editBox = new EditBox(this.font, x, y, boxWidth, boxHeight,
				Component.translatable("zhenblocks.screen.edit.hint"));
		this.editBox.setMaxLength(MAX_LENGTH);
		this.editBox.setHint(Component.translatable("zhenblocks.screen.edit.hint"));
		this.editBox.setFocused(true);

		// Pre-fill with the current text from the client-side block entity.
		if (this.minecraft != null && this.minecraft.level != null
				&& this.minecraft.level.getBlockEntity(blockPos) instanceof DisplayBlockEntity display) {
			this.editBox.setValue(display.getText().getString());
		}

		addRenderableWidget(this.editBox);
		setInitialFocus(this.editBox);

		addRenderableWidget(Button.builder(
				Component.translatable("zhenblocks.screen.edit.confirm"),
				button -> confirm())
				.pos(this.width / 2 - 50, y + boxHeight + 8)
				.width(100)
				.build());
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (event.key() == InputConstants.KEY_RETURN && this.editBox != null && this.editBox.isFocused()) {
			confirm();
			return true;
		}
		return super.keyPressed(event);
	}

	private void confirm() {
		ClientPlayNetworking.send(new SetDisplayTextPayload(blockPos, editBox.getValue()));
		this.onClose();
	}
}
