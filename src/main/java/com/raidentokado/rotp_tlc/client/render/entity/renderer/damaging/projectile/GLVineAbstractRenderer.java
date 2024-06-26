package com.raidentokado.rotp_tlc.client.render.entity.renderer.damaging.projectile;

import com.github.standobyte.jojo.client.render.entity.renderer.damaging.extending.ExtendingEntityRenderer;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.client.render.entity.model.projectile.GLVineModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public abstract class GLVineAbstractRenderer<T extends OwnerBoundProjectileEntity> extends ExtendingEntityRenderer<T, GLVineModel<T>> {

    public GLVineAbstractRenderer(EntityRendererManager renderManager, GLVineModel<T> model) {
        super(renderManager, model, new ResourceLocation(RotpTLCAddon.MOD_ID, "textures/entity/projectiles/gl_vine.png"));
    }
}