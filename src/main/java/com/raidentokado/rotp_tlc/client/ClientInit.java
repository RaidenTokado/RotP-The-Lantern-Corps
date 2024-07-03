package com.raidentokado.rotp_tlc.client;

import com.github.standobyte.jojo.client.render.entity.layerrenderer.HamonBurnLayer;
import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.client.render.entity.model.stand.GreenLanternUserModel;
import com.raidentokado.rotp_tlc.client.render.entity.renderer.GreenLanternLayer;
import com.raidentokado.rotp_tlc.client.render.entity.renderer.damaging.projectile.GLGrapplingVineRenderer;
import com.raidentokado.rotp_tlc.client.render.entity.renderer.damaging.projectile.GLShieldRenderer;
import com.raidentokado.rotp_tlc.client.render.entity.renderer.stand.GreenLanternRenderer;
import com.raidentokado.rotp_tlc.init.AddonStands;
import com.raidentokado.rotp_tlc.init.InitEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@EventBusSubscriber(modid = RotpTLCAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        Minecraft mc = event.getMinecraftSupplier().get();;

        RenderingRegistry.registerEntityRenderingHandler(AddonStands.GREEN_LANTERN.getEntityType(), GreenLanternRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.GL_GRAPPLING_VINE.get(), GLGrapplingVineRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.GL_SHIELD.get(), GLShieldRenderer::new);

        event.enqueueWork(() -> {
            Map<String, PlayerRenderer> skinMap = mc.getEntityRenderDispatcher().getSkinMap();
            addLayers(skinMap.get("default"), false);
            addLayers(skinMap.get("slim"), true);
            mc.getEntityRenderDispatcher().renderers.values().forEach(ClientInit::addLayersToEntities);
        });


    }
    @SubscribeEvent
    public static void onMcConstructor(ParticleFactoryRegisterEvent event){
        Minecraft mc = Minecraft.getInstance();

    }
    private static void addLayers(PlayerRenderer renderer, boolean slim){
        renderer.addLayer(new GreenLanternLayer<>(renderer,new GreenLanternUserModel<>(0.55F,slim),slim));
        addLivingLayers(renderer);
        addBipedLayers(renderer);
    }

    private static <T extends LivingEntity, M extends BipedModel<T>> void addLayersToEntities(EntityRenderer<?> renderer) {
        if (renderer instanceof LivingRenderer<?, ?>) {
            addLivingLayers((LivingRenderer<T, ?>) renderer);
            if (((LivingRenderer<?, ?>) renderer).getModel() instanceof BipedModel<?>) {
                addBipedLayers((LivingRenderer<T, M>) renderer);
            }
        }
    }

    private static <T extends LivingEntity, M extends EntityModel<T>> void addLivingLayers(@NotNull LivingRenderer<T, M> renderer) {

        renderer.addLayer(new HamonBurnLayer<>(renderer));
    }

    private static <T extends LivingEntity, M extends BipedModel<T>> void addBipedLayers(LivingRenderer<T, M> renderer) {


    }

}