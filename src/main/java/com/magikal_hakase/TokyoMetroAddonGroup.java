package com.magikal_hakase;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TokyoMetroAddonGroup {
    public static ItemGroup TOKYO_METRO_ADDON_GROUP;

    public static void register() {
        TOKYO_METRO_ADDON_GROUP = FabricItemGroupBuilder.create( // createに変更
                        new Identifier(TokyoMetroAddon.MOD_ID, "tokyo_metro_addon_group"))
                .icon(() -> new ItemStack(TokyoMetroAddonBlocks.WHITE_SMALL_TILE_BLOCK)) // アイコン設定を .icon() に変更
                .appendItems(stacks -> {
                    stacks.add(new ItemStack(TokyoMetroAddonItems.TOOLBOX));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.WHITE_MINI_TILE_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.RED_MINI_TILE_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.WHITE_SMALL_TILE_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.WHITE_TILE_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.METRO_STAIRS));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.GUIDANCE_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK_WITH_GUIDANCE_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK_ROW));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.CAUTION_BLOCK_ENCLOSEDLINE));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.ALUMINUM_SPINDLE));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.FLUORESCENT_LIGHT));
                    stacks.add(new ItemStack(TokyoMetroAddonBlocks.GUIDE_BELL_DEVICE));
                })
                .build();
    }
}