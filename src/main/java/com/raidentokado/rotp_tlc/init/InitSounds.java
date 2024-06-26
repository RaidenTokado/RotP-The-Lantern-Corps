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

    public static final Supplier<SoundEvent> GREEN_LANTERN_PUNCH = SOUNDS.register("GL_Punch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Punch")));
    public static final Supplier<SoundEvent> UGl_PUNCH = SOUNDS.register("UGL_Punch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"UGL_Punch")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_PUNCH_HEAVY = SOUNDS.register("GL_Heavy_Punch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Heavy_Punch")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_RECEIVING = SOUNDS.register("GL_Receiving",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Receiving")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_UNRECEIVING = SOUNDS.register("GL_Unreceiving",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Unreceiving")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_FLIGHT = SOUNDS.register("GL_Flight",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Flight")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_GRAPPLE = SOUNDS.register("GL_Grapple",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Grapple")));
    public static final Supplier<SoundEvent> GREEN_LANTERN_GRAPPLE_CATCH = SOUNDS.register("GL_Grapple_Catch",
            ()-> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID,"GL_Grapple_Catch")));
    public static final RegistryObject<SoundEvent> GREEN_LANTERN_SUMMON = SOUNDS.register("GL_Summon",
            () -> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "GL_Summon")));

    public static final RegistryObject<SoundEvent> GREEN_LANTERN_UNSUMMON = SOUNDS.register("GL_Unsummon",
            () -> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "GL_Unsummon")));

    public static final RegistryObject<SoundEvent> GREEN_LANTERN_BLOCK = SOUNDS.register("GL_Block",
            () -> new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "GL_Block")));

    public static final RegistryObject<SoundEvent> USER_GREEN_LANTERN = SOUNDS.register("GL_User",
            ()->new SoundEvent(new ResourceLocation(RotpTLCAddon.MOD_ID, "GL_User")));

    static final OstSoundList GREEN_LANTERN_OST = new OstSoundList(new ResourceLocation(RotpTLCAddon.MOD_ID, "GL_OST"), SOUNDS);
}