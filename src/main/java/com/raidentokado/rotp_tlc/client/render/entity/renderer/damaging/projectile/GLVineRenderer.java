package com.raidentokado.rotp_tlc.client.render.entity.renderer.damaging.projectile;

import com.raidentokado.rotp_tlc.client.render.entity.model.projectile.GLVineModel;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLGrapplingVineEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GLVineRenderer extends GLVineAbstractRenderer<GLGrapplingVineEntity> {

    public GLVineRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GLVineModel<GLGrapplingVineEntity>());
    }
}