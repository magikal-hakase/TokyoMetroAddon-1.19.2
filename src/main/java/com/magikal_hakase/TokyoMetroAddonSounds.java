package com.magikal_hakase;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TokyoMetroAddonSounds {
    public static final SoundEvent GUIDE_BELL_SINE = registerSoundEvent("block.guide_bell_sine");
    public static final SoundEvent GUIDE_BELL_SAWTOOTH = registerSoundEvent("block.guide_bell_sawtooth");
    public static final SoundEvent GUIDE_BELL_SQUARE = registerSoundEvent("block.guide_bell_square");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(TokyoMetroAddon.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void register() {
        TokyoMetroAddon.LOGGER.info("Registering Sounds for " + TokyoMetroAddon.MOD_ID);
    }
}