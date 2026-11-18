package com.zitzhen.zhenblocks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

import com.zitzhen.zhenblocks.registry.ZhenBlocksBlockEntities;

public class ZhenBlocksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.register(
				ZhenBlocksBlockEntities.DISPLAY_BLOCK_ENTITY,
				DisplayBlockEntityRenderer::new);
	}
}
