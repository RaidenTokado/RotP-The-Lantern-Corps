package com.raidentokado.rotp_tlc.init;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLGrapplingVineEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RotpTLCAddon.MOD_ID);
    public static final RegistryObject<EntityType<GLGrapplingVineEntity>> GL_VINE = ENTITIES.register("gl_vine",
            () -> EntityType.Builder.<GLGrapplingVineEntity>of(GLGrapplingVineEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
                    .build(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_vine").toString()));

}