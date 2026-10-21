package com.magikal_hakase.entity;

import com.magikal_hakase.TokyoMetroAddon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TokyoMetroAddonEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TokyoMetroAddon.MOD_ID);

    public static final RegistryObject<EntityType<SeatEntity>> SEAT_ENTITY =
            ENTITY_TYPES.register("seat", () -> EntityType.Builder.of(
                    (EntityType<SeatEntity> type, Level level) -> new SeatEntity(type, level),
                    MobCategory.MISC)
                    .sized(0.0f, 0.0f)
                    .build(TokyoMetroAddon.MOD_ID + ":seat"));

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}
