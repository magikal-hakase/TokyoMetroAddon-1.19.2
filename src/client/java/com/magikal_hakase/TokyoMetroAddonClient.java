package com.magikal_hakase;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TokyoMetroAddonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(TokyoMetroAddonBlocks.GUIDANCE_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TokyoMetroAddonBlocks.CAUTION_BLOCK_ROW, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TokyoMetroAddonBlocks.CAUTION_BLOCK_WITH_GUIDANCE_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TokyoMetroAddonBlocks.CAUTION_BLOCK_ENCLOSEDLINE, RenderLayer.getCutout());
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}