package com.magikal_hakase;

import mtr.block.BlockPlatform;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TokyoMetroAddonBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TokyoMetroAddon.MOD_ID);

    private static final Supplier<BlockBehaviour.Properties> STONE_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.0f, 6.0f);
    private static final Supplier<BlockBehaviour.Properties> STONE_WEAK_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5f, 6.0f);
    private static final Supplier<BlockBehaviour.Properties> METAL_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0f, 6.0f);
    private static final Supplier<BlockBehaviour.Properties> METAL_STRONG_PROPERTIES =
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.5f, 6.0f);

    public static final RegistryObject<Block> WHITE_MINI_TILE_BLOCK = registerBlock("white_mini_tile_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5f, 6.0f)));

    public static final RegistryObject<Block> RED_MINI_TILE_BLOCK = registerBlock("red_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> YELLOW_MINI_TILE_BLOCK = registerBlock("yellow_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> BLUE_MINI_TILE_BLOCK = registerBlock("blue_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> CYAN_MINI_TILE_BLOCK = registerBlock("cyan_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> GREEN_MINI_TILE_BLOCK = registerBlock("green_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> BROWN_MINI_TILE_BLOCK = registerBlock("brown_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> BLACK_MINI_TILE_BLOCK = registerBlock("black_mini_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> WHITE_TILE_BLOCK = registerBlock("white_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> WHITE_SMALL_TILE_BLOCK = registerBlock("white_small_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> LIGHT_GRAY_SMALL_TILE_BLOCK = registerBlock("light_gray_small_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> BLACK_SMALL_TILE_BLOCK = registerBlock("black_small_tile_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> WHITE_PLATFORM_PLATE = registerBlock("white_platform_plate",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> LIGHT_GRAY_PLATFORM_PLATE = registerBlock("light_gray_platform_plate",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> WHITE_SLATTED_WALL_PANEL_BLOCK = registerBlock("white_slatted_wall_panel_block",
            () -> new Block(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> METRO_STAIRS = registerBlock("metro_stairs",
            () -> new MetroStairsBlock(Blocks.STONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS)));

    public static final RegistryObject<Block> LIGHT_GRAY_METRO_STAIRS = registerBlock("light_gray_metro_stairs",
            () -> new CustomStairsBlock(LIGHT_GRAY_SMALL_TILE_BLOCK.get().defaultBlockState(),
                    STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> ALUMINUM_SPINDLE = registerBlock("aluminum_spindle",
            () -> new DirectionalSlabBlock(STONE_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> ALUMINUM_SPINDLE_BLOCK = registerBlock("aluminum_spindle_block",
            () -> new DirectionBlock(STONE_PROPERTIES.get()));

    public static final RegistryObject<Block> ALUMINUM_SPINDLE_SLOPE = registerBlock("aluminum_spindle_slope",
            () -> new AluminumSpindleSlopeBlock(STONE_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> FLUORESCENT_LIGHT = registerBlock("fluorescent_light",
            () -> new FluorescentLightBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(1.0f).lightLevel(state -> 15).noOcclusion()));

    public static final RegistryObject<Block> STAINLESS_STEEL_FENCE = registerBlock("stainless_steel_fence",
            () -> new StainlessSteelFenceBlock(METAL_STRONG_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> METRO_BENCH = registerBlock("metro_bench",
            () -> new MetroBenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0f).noOcclusion()));

    public static final RegistryObject<Block> GUIDANCE_BLOCK = registerBlock("guidance_block",
            () -> new GuidanceBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> CAUTION_BLOCK = registerBlock("caution_block",
            () -> new CautionBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> CAUTION_BLOCK_WITH_GUIDANCE_BLOCK = registerBlock("caution_block_with_guidance_block",
            () -> new GuidanceBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> CAUTION_BLOCK_ROW = registerBlock("caution_block_row",
            () -> new GuidanceBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> CAUTION_BLOCK_ENCLOSEDLINE = registerBlock("caution_block_enclosedline",
            () -> new GuidanceBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> ROUTE_MARKER_BLUE = registerBlock("route_marker_blue",
            () -> new GuidanceBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> ROUTE_MARKER_WHITE = registerBlock("route_marker_white",
            () -> new GuidanceBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> GUIDE_BELL_DEVICE = registerBlock("guide_bell_device",
            () -> new GuideBellDeviceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(1.5f, 6.0f).noOcclusion()));

    public static final RegistryObject<Block> EMERGENCY_EXIT_SIGN = registerBlock("emergency_exit_sign",
            () -> new EmergencyExitSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(1.5f, 6.0f).lightLevel(state -> 3).noOcclusion()));

    public static final RegistryObject<Block> AISLE_GUIDE_SIGN = registerBlock("aisle_guide_sign",
            () -> new EmergencyExitSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(1.5f, 6.0f).lightLevel(state -> 3).noOcclusion()));

    public static final RegistryObject<Block> AISLE_GUIDE_SIGN_BOTH = registerBlock("aisle_guide_sign_both",
            () -> new EmergencyExitSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(1.5f, 6.0f).lightLevel(state -> 3).noOcclusion()));

    public static final RegistryObject<Block> POSTER_NOTICE = registerBlock("poster_notice",
            () -> new MetroPosterBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> POSTER_REQUEST = registerBlock("poster_request",
            () -> new MetroPosterBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> POSTER_KEEP_LEFT_WHITE = registerBlock("poster_keep_left_white",
            () -> new MetroPosterBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> POSTER_KEEP_LEFT_BLUE = registerBlock("poster_keep_left_blue",
            () -> new MetroPosterBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> POSTER_NO_SMOKING = registerBlock("poster_no_smoking",
            () -> new MetroPosterBlock(STONE_WEAK_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> FRAMED_POSTER = registerBlock("framed_poster",
            () -> new FramedPosterBlock(STONE_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> INDOOR_FIRE_HYDRANT = registerBlock("indoor_fire_hydrant",
            () -> new IndoorFireHydrantBlock(METAL_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> METAL_DOOR = registerBlock("metal_door",
            () -> new CustomDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(3.0f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> METAL_DOOR_LOCKING = registerBlock("metal_door_locking",
            () -> new CustomDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(5.0f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> TOKYO_TICKET_BARRIER_ENTRANCE = registerBlock("tokyo_ticket_barrier_entrance",
            () -> new TokyoTicketBarrierBlock(true));

    public static final RegistryObject<Block> TOKYO_TICKET_BARRIER_EXIT = registerBlock("tokyo_ticket_barrier_exit",
            () -> new TokyoTicketBarrierBlock(false));

    public static final RegistryObject<Block> TOKYO_TICKET_MACHINE = registerBlock("tokyo_ticket_machine",
            () -> new TokyoTicketMachineBlock(METAL_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> TOKYO_FARE_ADJUSTMENT_MACHINE = registerBlock("tokyo_fare_adjustment_machine",
            () -> new TokyoTicketMachineBlock(METAL_PROPERTIES.get().noOcclusion()));

    public static final RegistryObject<Block> TOKYO_PLATFORM = registerBlock("tokyo_platform",
            () -> new BlockPlatform(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .strength(2.0f, 6.0f), true));

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier) {
        RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);
        // The BlockItem supplier runs when the ITEMS registry fires, after BLOCKS is
        // already populated, so block.get() is safe here.
        TokyoMetroAddonItems.ITEMS.register(name, () -> new net.minecraft.world.item.BlockItem(block.get(),
                new net.minecraft.world.item.Item.Properties()));
        return block;
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
