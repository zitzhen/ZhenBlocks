package com.zitzhen.zhenblocks.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import com.zitzhen.zhenblocks.blockentity.DisplayBlockEntity;

public final class ZhenBlocksNetworking {
	private ZhenBlocksNetworking() {
	}

	public static void register() {
		PayloadTypeRegistry.clientboundPlay().register(OpenEditScreenPayload.TYPE, OpenEditScreenPayload.CODEC);
		PayloadTypeRegistry.serverboundPlay().register(SetDisplayTextPayload.TYPE, SetDisplayTextPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(SetDisplayTextPayload.TYPE, (payload, context) -> {
			var player = context.player();
			context.server().execute(() -> {
				var level = player.level();
				if (!(level.getBlockEntity(payload.pos()) instanceof DisplayBlockEntity display)) {
					return;
				}
				// Authority checks: within reach and valid block entity.
				if (player.distanceToSqr(payload.pos().getX() + 0.5, payload.pos().getY() + 0.5,
						payload.pos().getZ() + 0.5) > 64.0) {
					return;
				}
				String safe = payload.text()
						.replace('\n', ' ')
						.replace('\r', ' ');
				if (safe.length() > DisplayBlockEntity.MAX_TEXT_LENGTH) {
					safe = safe.substring(0, DisplayBlockEntity.MAX_TEXT_LENGTH);
				}
				display.setText(net.minecraft.network.chat.Component.literal(safe));
			});
		});
	}
}
