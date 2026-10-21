package com.magikal_hakase;

import com.magikal_hakase.entity.TokyoMetroAddonEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(TokyoMetroAddon.MOD_ID)
public class TokyoMetroAddon {
    public static final String MOD_ID = "tokyometroaddon";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public TokyoMetroAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TokyoMetroAddonGroup.register(modEventBus);
        TokyoMetroAddonSounds.register(modEventBus);
        TokyoMetroAddonItems.register(modEventBus);
        TokyoMetroAddonBlocks.register(modEventBus);
        TokyoMetroAddonBlockEntities.register(modEventBus);
        TokyoMetroAddonEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Hello Forge world!");
    }
}
