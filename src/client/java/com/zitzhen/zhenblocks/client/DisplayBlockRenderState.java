package com.zitzhen.zhenblocks.client;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;

public class DisplayBlockRenderState extends BlockEntityRenderState {
	public Component text = Component.empty();
	public Direction facing = Direction.NORTH;
}
