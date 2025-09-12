package com.magikal_hakase;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TokyoMetroAddonItems {
    public static final Item TOOLBOX = registerItem("toolbox",
            new Item(new FabricItemSettings()));

    public static void register() {
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(TokyoMetroAddon.MOD_ID, name), item);
    }
}
