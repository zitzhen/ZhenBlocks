package com.zitzhen.zhenblocks.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import com.zitzhen.zhenblocks.block.DisplayBlock;

public final class ZhenBlocksBlocks {
	private static final Identifier DISPLAY_BLOCK_ID =
			Identifier.fromNamespaceAndPath("zhenblocks", "display_block");

	public static final Block DISPLAY_BLOCK = register(
			"display_block",
			new DisplayBlock(BlockBehaviour.Properties.of()
					.setId(ResourceKey.create(Registries.BLOCK, DISPLAY_BLOCK_ID))
					.mapColor(MapColor.WOOD)
					.strength(2.0F)
					.sound(SoundType.WOOD)));

	public static final Item DISPLAY_BLOCK_ITEM = registerItem(
			"display_block",
			new BlockItem(DISPLAY_BLOCK, new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM, DISPLAY_BLOCK_ID))));

	private ZhenBlocksBlocks() {
	}

	public static void initialize() {
		ResourceKey<CreativeModeTab> buildingBlocks = ResourceKey.create(
				Registries.CREATIVE_MODE_TAB,
				Identifier.fromNamespaceAndPath("minecraft", "building_blocks"));
		CreativeModeTabEvents.modifyOutputEvent(buildingBlocks)
				.register(output -> output.accept(DISPLAY_BLOCK_ITEM));
	}

	private static Block register(String name, Block block) {
		return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath("zhenblocks", name), block);
	}

	private static Item registerItem(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath("zhenblocks", name), item);
	}
}
