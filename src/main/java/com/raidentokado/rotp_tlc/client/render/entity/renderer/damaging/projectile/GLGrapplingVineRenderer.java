package com.raidentokado.rotp_tlc.client.render.entity.renderer.damaging.projectile;

import com.raidentokado.rotp_tlc.client.render.entity.model.projectile.GLVineModel;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLGrapplingVineEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GLGrapplingVineRenderer extends GLVineAbstractRenderer<GLGrapplingVineEntity> {

    public GLGrapplingVineRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GLVineModel<GLGrapplingVineEntity>());
    }
}