package com.zitzhen.zhenblocks.registry;

import java.util.Set;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

import com.zitzhen.zhenblocks.blockentity.DisplayBlockEntity;

public final class ZhenBlocksBlockEntities {
	public static final BlockEntityType<DisplayBlockEntity> DISPLAY_BLOCK_ENTITY = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Identifier.fromNamespaceAndPath("zhenblocks", "display_block"),
			new BlockEntityType<>(DisplayBlockEntity::new, Set.of(ZhenBlocksBlocks.DISPLAY_BLOCK)));

	private ZhenBlocksBlockEntities() {
	}

	public static void initialize() {
		// Static registration is enough; this method exists for a consistent call site.
	}
}
