package com.zitzhen.zhenblocks;

import net.fabricmc.api.ModInitializer;

import com.zitzhen.zhenblocks.network.ZhenBlocksNetworking;
import com.zitzhen.zhenblocks.registry.ZhenBlocksBlockEntities;
import com.zitzhen.zhenblocks.registry.ZhenBlocksBlocks;

public class ZhenBlocks implements ModInitializer {
	@Override
	public void onInitialize() {
		ZhenBlocksBlocks.initialize();
		ZhenBlocksBlockEntities.initialize();
		ZhenBlocksNetworking.register();
	}
}
