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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.zitzhen.zhenblocks.block.DisplayBlock;

public final class ZhenBlocksBlocks {
	public static final Block DISPLAY_BLOCK = register(
			"display_block",
			new DisplayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS)));

	public static final Item DISPLAY_BLOCK_ITEM = registerItem(
			"display_block",
			new BlockItem(DISPLAY_BLOCK, new Item.Properties()));

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
