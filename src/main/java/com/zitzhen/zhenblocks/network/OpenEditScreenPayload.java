package com.zitzhen.zhenblocks.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/** S2C: tells the client to open the text editor for a display block. */
public record OpenEditScreenPayload(BlockPos pos) implements CustomPacketPayload {
	public static final Type<OpenEditScreenPayload> TYPE = new Type<>(
			Identifier.fromNamespaceAndPath("zhenblocks", "open_edit_screen"));

	public static final StreamCodec<FriendlyByteBuf, OpenEditScreenPayload> CODEC =
			StreamCodec.composite(
					BlockPos.STREAM_CODEC, OpenEditScreenPayload::pos,
					OpenEditScreenPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
