package com.magikal_hakase;

import com.magikal_hakase.entity.TokyoMetroAddonEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TokyoMetroAddon.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TokyoMetroAddonClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.GUIDANCE_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.CAUTION_BLOCK_ROW.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.CAUTION_BLOCK_WITH_GUIDANCE_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.CAUTION_BLOCK_ENCLOSEDLINE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.ROUTE_MARKER_BLUE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.ROUTE_MARKER_WHITE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.POSTER_NOTICE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.POSTER_REQUEST.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.POSTER_KEEP_LEFT_WHITE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.POSTER_KEEP_LEFT_BLUE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.POSTER_NO_SMOKING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.METRO_BENCH.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TokyoMetroAddonBlocks.ALUMINUM_SPINDLE_SLOPE.get(), RenderType.cutout());
        });
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TokyoMetroAddonEntities.SEAT_ENTITY.get(), NoopRenderer::new);
    }
}
