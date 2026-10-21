package com.magikal_hakase;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TokyoMetroAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TokyoMetroAddonGroup {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TokyoMetroAddon.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOKYO_METRO_ADDON_GROUP = TABS.register(
            "tokyo_metro_addon_group",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tokyometroaddon.tokyo_metro_addon_group"))
                    .icon(() -> new ItemStack(TokyoMetroAddonBlocks.WHITE_SMALL_TILE_BLOCK.get()))
                    .build());

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() != TOKYO_METRO_ADDON_GROUP.get()) {
            return;
        }
        event.accept(new ItemStack(TokyoMetroAddonItems.TOOLBOX.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.WHITE_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.RED_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.YELLOW_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.BLUE_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.CYAN_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.GREEN_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.BROWN_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.BLACK_MINI_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.WHITE_SMALL_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.LIGHT_GRAY_SMALL_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.BLACK_SMALL_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.WHITE_TILE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.WHITE_PLATFORM_PLATE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.LIGHT_GRAY_PLATFORM_PLATE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.WHITE_SLATTED_WALL_PANEL_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.GUIDANCE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK_WITH_GUIDANCE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK_ROW.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK_ENCLOSEDLINE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.ROUTE_MARKER_BLUE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.ROUTE_MARKER_WHITE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.ALUMINUM_SPINDLE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.ALUMINUM_SPINDLE_BLOCK.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.ALUMINUM_SPINDLE_SLOPE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.STAINLESS_STEEL_FENCE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.METRO_BENCH.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.METRO_STAIRS.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.LIGHT_GRAY_METRO_STAIRS.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.FLUORESCENT_LIGHT.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.GUIDE_BELL_DEVICE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.EMERGENCY_EXIT_SIGN.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.AISLE_GUIDE_SIGN.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.AISLE_GUIDE_SIGN_BOTH.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.POSTER_NOTICE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.POSTER_REQUEST.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.POSTER_KEEP_LEFT_WHITE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.POSTER_KEEP_LEFT_BLUE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.POSTER_NO_SMOKING.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.FRAMED_POSTER.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.INDOOR_FIRE_HYDRANT.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.METAL_DOOR.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.METAL_DOOR_LOCKING.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.TOKYO_TICKET_BARRIER_ENTRANCE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.TOKYO_TICKET_BARRIER_EXIT.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.TOKYO_TICKET_MACHINE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.TOKYO_FARE_ADJUSTMENT_MACHINE.get()));
        event.accept(new ItemStack(TokyoMetroAddonBlocks.TOKYO_PLATFORM.get()));
    }

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }
}
