package com.magikal_hakase;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TokyoMetroAddonBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TokyoMetroAddon.MOD_ID);

    @SuppressWarnings("unchecked")
    public static final RegistryObject<BlockEntityType<GuideBellDeviceBlockEntity>> GUIDE_BELL_DEVICE_ENTITY =
            (RegistryObject<BlockEntityType<GuideBellDeviceBlockEntity>>) BLOCK_ENTITY_TYPES.register("guide_bell_device",
                    () -> BlockEntityType.Builder.of(GuideBellDeviceBlockEntity::new,
                            TokyoMetroAddonBlocks.GUIDE_BELL_DEVICE.get()).build(null));

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITY_TYPES.register(modEventBus);
    }
}
