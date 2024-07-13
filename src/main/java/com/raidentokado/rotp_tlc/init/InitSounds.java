package com.raidentokado.rotp_tlc.init;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.github.standobyte.jojo.util.mc.OstSoundList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RotpTLCAddon.MOD_ID);

    public static final Supplier<SoundEvent> GREEN_LANTERN_PUNCH = SOUNDS.register("gl_punch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_punch")));
    public static final Supplier<SoundEvent> UGl_PUNCH = SOUNDS.register("ugl_punch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"ugl_punch")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_PUNCH_HEAVY = SOUNDS.register("gl_heavy_punch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_heavy_punch")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_RECEIVING = SOUNDS.register("gl_receiving",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_receiving")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_UNRECEIVING = SOUNDS.register("gl_unreceiving",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_unreceiving")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_FLIGHT = SOUNDS.register("gl_flight",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_flight")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_GRAPPLE = SOUNDS.register("gl_grapple",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_grapple")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_GRAPPLE_CATCH = SOUNDS.register("gl_grapple_catch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"gl_grapple_catch")));
    public static final RegistryObject<SoundEvent> GREEN_LANTERN_SUMMON = SOUNDS.register("gl_summon",
            () -> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_summon")));

    public static final RegistryObject<SoundEvent> GREEN_LANTERN_UNSUMMON = SOUNDS.register("gl_unsummon",
            () -> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_unsummon")));

    public static final RegistryObject<SoundEvent> GREEN_LANTERN_BLOCK = SOUNDS.register("gl_block",
            () -> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_block")));

    public static final RegistryObject<SoundEvent> USER_GREEN_LANTERN = SOUNDS.register("gl_user",
            ()->new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_user")));

    static final OstSoundList GREEN_LANTERN_OST = new OstSoundList(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_ost"), SOUNDS);
}