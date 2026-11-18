package com.zitzhen.zhenblocks.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import com.zitzhen.zhenblocks.blockentity.DisplayBlockEntity;

/** C2S: the text the player typed into the editor. */
public record SetDisplayTextPayload(BlockPos pos, String text) implements CustomPacketPayload {
	public static final Type<SetDisplayTextPayload> TYPE = new Type<>(
			Identifier.fromNamespaceAndPath("zhenblocks", "set_display_text"));

	public static final StreamCodec<RegistryFriendlyByteBuf, SetDisplayTextPayload> CODEC =
			StreamCodec.composite(
					BlockPos.STREAM_CODEC, SetDisplayTextPayload::pos,
					ByteBufCodecs.stringUtf8(DisplayBlockEntity.MAX_TEXT_LENGTH * 4), SetDisplayTextPayload::text,
					SetDisplayTextPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
