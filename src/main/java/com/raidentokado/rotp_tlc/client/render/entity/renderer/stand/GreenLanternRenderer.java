package com.raidentokado.rotp_tlc.client.render.entity.renderer.stand;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.client.render.entity.model.stand.GreenLanternModel;
import com.raidentokado.rotp_tlc.entity.stand.stands.GreenLanternEntity;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class GreenLanternRenderer extends StandEntityRenderer<GreenLanternEntity, GreenLanternModel> {

    public GreenLanternRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GreenLanternModel(), new ResourceLocation(RotpTLCAddon.MOD_ID, "textures/entity/stand/void.png"), 0);
    }
}
