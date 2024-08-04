package com.raidentokado.rotp_tlc;

import com.raidentokado.rotp_tlc.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RotpTLCAddon.MOD_ID)
public class RotpTLCAddon {
    // The value here should match an entry in the META-INF/mods.toml file
    public static final String MOD_ID = "rotp_tlc";
    public static final Logger LOGGER = LogManager.getLogger();

    public RotpTLCAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
        InitParticles.PARTICLES.register(modEventBus);
        InitItems.ITEMS.register(modEventBus);
        InitBlocks.BLOCKS.register(modEventBus);


        InitTags.iniTags();

    }

    public static Logger getLogger() {
        return LOGGER;
    }
}