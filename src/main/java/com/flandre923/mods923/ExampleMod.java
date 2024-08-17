package com.flandre923.mods923;

import com.flandre923.mods923.setup.MathQuestionRegistries;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mods923";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public ExampleMod(IEventBus modEventBus, ModContainer modContainer)
    {
        MathQuestionRegistries.init();
    }

}
