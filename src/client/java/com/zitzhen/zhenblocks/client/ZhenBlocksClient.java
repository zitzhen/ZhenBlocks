package com.zitzhen.zhenblocks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

import com.zitzhen.zhenblocks.network.OpenEditScreenPayload;
import com.zitzhen.zhenblocks.registry.ZhenBlocksBlockEntities;

public class ZhenBlocksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.register(
				ZhenBlocksBlockEntities.DISPLAY_BLOCK_ENTITY,
				DisplayBlockEntityRenderer::new);

		ClientPlayNetworking.registerGlobalReceiver(OpenEditScreenPayload.TYPE, (payload, context) -> {
			context.client().execute(() ->
					context.client().setScreenAndShow(new EditScreen(payload.pos())));
		});
	}
}
