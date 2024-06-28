package com.raidentokado.rotp_tlc.init;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLGrapplingVineEntity;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLShieldEntity;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLVineEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RotpTLCAddon.MOD_ID);
    public static final RegistryObject<EntityType<GLVineEntity>> GL_VINE = ENTITIES.register("gl_vine",
            () -> EntityType.Builder.<GLVineEntity>of(GLVineEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
                    .build(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_vine").toString()));

    public static final RegistryObject<EntityType<GLGrapplingVineEntity>> GL_GRAPPLING_VINE = ENTITIES.register("gl_grappling_vine",
            () -> EntityType.Builder.<GLGrapplingVineEntity>of(GLGrapplingVineEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
                    .build(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_grappling_vine").toString()));
    public static final RegistryObject<EntityType<GLShieldEntity>> GL_SHIELD = ENTITIES.register("gl_shield",
            () -> EntityType.Builder.<GLShieldEntity>of(GLShieldEntity::new, EntityClassification.MISC).sized(3.0F, 3.0F).setUpdateInterval(Integer.MAX_VALUE).setShouldReceiveVelocityUpdates(false).noSummon().noSave().fireImmune()
                    .build(new ResourceLocation(RotpTLCAddon.MOD_ID, "gl_shield").toString()));

}