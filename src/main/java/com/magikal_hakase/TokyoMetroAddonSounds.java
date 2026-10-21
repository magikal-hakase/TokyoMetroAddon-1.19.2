package com.magikal_hakase;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TokyoMetroAddonSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TokyoMetroAddon.MOD_ID);

    public static final RegistryObject<SoundEvent> GUIDE_BELL_SINE = registerSoundEvent("block.guide_bell_sine");
    public static final RegistryObject<SoundEvent> GUIDE_BELL_SAWTOOTH = registerSoundEvent("block.guide_bell_sawtooth");
    public static final RegistryObject<SoundEvent> GUIDE_BELL_SQUARE = registerSoundEvent("block.guide_bell_square");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TokyoMetroAddon.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
        TokyoMetroAddon.LOGGER.info("Registering Sounds for " + TokyoMetroAddon.MOD_ID);
    }
}
