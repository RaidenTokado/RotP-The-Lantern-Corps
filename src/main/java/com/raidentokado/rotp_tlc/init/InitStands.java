package com.raidentokado.rotp_tlc.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.action.GreenLanternReceivingSword;
import com.raidentokado.rotp_tlc.action.GreenLanternUnreceivingSword;
import com.raidentokado.rotp_tlc.action.stand.projectile.GLGrapple;
import com.raidentokado.rotp_tlc.action.stand.projectile.GLShield;
import com.raidentokado.rotp_tlc.entity.stand.stands.GreenLanternEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @Deprecated public static final ITextComponent PART_DC_NAME = new TranslationTextComponent("multiverse.story_part.dc").withStyle(TextFormatting.DARK_BLUE);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpTLCAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpTLCAddon.MOD_ID);
    
 // ======================================== Green Lantern ========================================

    public static final RegistryObject<StandEntityAction> GREEN_LANTERN_PUNCH = ACTIONS.register("green_lantern_punch",
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.GREEN_LANTERN_PUNCH).swingHand().standOffsetFromUser(0,0,.5)
                    .standSound(StandEntityAction.Phase.WINDUP, InitSounds.UGl_PUNCH)));

    public static final RegistryObject<StandEntityAction> GREEN_LANTERN_BLOCK = ACTIONS.register("green_lantern_block",
            () -> new GLShield(new StandEntityAction.Builder().holdType().standSound(InitSounds.GREEN_LANTERN_BLOCK)
                    .staminaCostTick(4)
            ));

    public static final RegistryObject<StandEntityAction> GREEN_LANTERN_RECEIVING_SWORD = ACTIONS.register("green_lantern_receive_sword",
            () -> new GreenLanternReceivingSword(new StandEntityAction.Builder().staminaCost(1).autoSummonStand()
                    .resolveLevelToUnlock(1)
                    .standSound(StandEntityAction.Phase.PERFORM, InitSounds.GREEN_LANTERN_RECEIVING)));

    public static final RegistryObject<StandEntityAction> GREEN_LANTERN_UNRECEIVING = ACTIONS.register("green_lantern_unreceive",
            () -> new GreenLanternUnreceivingSword(new StandEntityAction.Builder().staminaCost(1).autoSummonStand()
//                    .noResolveUnlock() // если его вызвать, абилка вообще не откроется, если вручную ее не разблокировать, это для абилок по типу раскрафта предметов на КД, которые открываются не от уровня резолва
                    .standSound(StandEntityAction.Phase.PERFORM, InitSounds.GREEN_LANTERN_UNRECEIVING)
                    .shiftVariationOf(GREEN_LANTERN_RECEIVING_SWORD)));

    public static final RegistryObject<StandEntityAction> GREEN_LANTERN_GRAPPLE = ACTIONS.register("green_lantern_grapple",
            () -> new GLGrapple(new StandEntityAction.Builder().holdType()
                    .staminaCostTick(1).autoSummonStand().standUserWalkSpeed(1.0F)
                    .resolveLevelToUnlock(2)
                    .standSound(StandEntityAction.Phase.WINDUP, InitSounds.GREEN_LANTERN_GRAPPLE)));
    public static final RegistryObject<StandEntityAction> GREEN_LANTERN_GRAPPLE_ENTITY = ACTIONS.register("green_lantern_grapple_entity",
            () -> new GLGrapple(new StandEntityAction.Builder().holdType()
                    .staminaCostTick(1).autoSummonStand().standUserWalkSpeed(1.0F)
                    .resolveLevelToUnlock(2)
                    .standSound(StandEntityAction.Phase.WINDUP, InitSounds.GREEN_LANTERN_GRAPPLE)
                    .shiftVariationOf(GREEN_LANTERN_GRAPPLE)));
    
    

    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<GreenLanternEntity>> STAND_GREEN_LANTERN =
            new EntityStandRegistryObject<>("green_lantern",
                    STANDS, 
                    () -> new EntityStandType.Builder<StandStats>()
                    .color(0x00813a)
                    .storyPartName(PART_DC_NAME)
                    .leftClickHotbar(
                            GREEN_LANTERN_PUNCH.get()
                            )
                    .rightClickHotbar(
                            GREEN_LANTERN_BLOCK.get(),
                            GREEN_LANTERN_RECEIVING_SWORD.get(),
                            GREEN_LANTERN_GRAPPLE.get()
                            )
                    .defaultStats(StandStats.class, new StandStats.Builder()
                            .tier(6)
                            .power(20)
                            .speed(15)
                            .range(10)
                            .durability(20)
                            .precision(5)
                            .build())
                    .addOst(InitSounds.GREEN_LANTERN_OST)
                    .build(),
                    
                    InitEntities.ENTITIES,
                    () -> new StandEntityType<GreenLanternEntity>(GreenLanternEntity::new, 0.7F, 2.1F)
                    .summonSound(InitSounds.GREEN_LANTERN_SUMMON)
                    .unsummonSound(InitSounds.GREEN_LANTERN_UNSUMMON))
            .withDefaultStandAttributes();
}
