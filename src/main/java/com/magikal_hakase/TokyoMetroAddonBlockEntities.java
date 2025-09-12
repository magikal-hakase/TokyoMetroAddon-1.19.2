package com.magikal_hakase;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class TokyoMetroAddonBlockEntities {

    public static BlockEntityType<GuideBellDeviceBlockEntity> GUIDE_BELL_DEVICE_ENTITY;

    public static void register() {
        GUIDE_BELL_DEVICE_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(TokyoMetroAddon.MOD_ID, "guide_bell_device_sine"),
                FabricBlockEntityTypeBuilder.create(GuideBellDeviceBlockEntity::new, TokyoMetroAddonBlocks.GUIDE_BELL_DEVICE).build(null)
        );
    }
}
