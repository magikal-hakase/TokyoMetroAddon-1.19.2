package com.magikal_hakase;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TokyoMetroAddonBlocks {
    public static final Block WHITE_MINI_TILE_BLOCK = registerBlock("white_mini_tile_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f)));

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier(TokyoMetroAddon.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registry.BLOCK, new Identifier(TokyoMetroAddon.MOD_ID, name), block);
    }

    public static final Block RED_MINI_TILE_BLOCK = registerBlock("red_mini_tile_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f)));

    public static final Block WHITE_TILE_BLOCK = registerBlock("white_tile_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f)));

    public static final Block WHITE_SMALL_TILE_BLOCK = registerBlock("white_small_tile_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f)));

    public static final Block METRO_STAIRS = registerBlock("metro_stairs",
            new MetroStairsBlock(Blocks.STONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.STONE_BRICK_STAIRS)));

    public static final Block ALUMINUM_SPINDLE = registerBlock("aluminum_spindle",
            new DirectionalSlabBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f)));

    public static final Block FLUORESCENT_LIGHT = registerBlock("fluorescent_light",
            new FluorescentLightBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f).luminance(state -> 15)));

    public static final Block GUIDANCE_BLOCK = registerBlock("guidance_block",
            new GuidanceBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).nonOpaque()));

    public static final Block CAUTION_BLOCK = registerBlock("caution_block",
            new CautionBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).nonOpaque()));

    public static final Block CAUTION_BLOCK_WITH_GUIDANCE_BLOCK = registerBlock("caution_block_with_guidance_block",
            new GuidanceBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).nonOpaque()));

    public static final Block CAUTION_BLOCK_ROW = registerBlock("caution_block_row",
            new GuidanceBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).nonOpaque()));

    public static final Block CAUTION_BLOCK_ENCLOSEDLINE = registerBlock("caution_block_enclosedline",
            new GuidanceBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).nonOpaque()));

    public static final Block GUIDE_BELL_DEVICE = registerBlock("guide_bell_device",
            new GuideBellDeviceBlock(FabricBlockSettings.of(Material.METAL).strength(1.5f).nonOpaque()));

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(TokyoMetroAddon.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void register() {
    }
}
