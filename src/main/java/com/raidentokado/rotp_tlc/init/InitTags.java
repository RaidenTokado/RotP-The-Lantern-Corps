package com.raidentokado.rotp_tlc.init;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class InitTags {
    public static final Tags.IOptionalNamedTag<EntityType<?>> NO_REFLECT = EntityTypeTags.createOptional(new ResourceLocation(RotpTLCAddon.MOD_ID,"irreflectable"));

    public static void iniTags(){}
}