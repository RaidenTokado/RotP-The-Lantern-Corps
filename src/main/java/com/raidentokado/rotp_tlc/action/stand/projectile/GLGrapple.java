package com.raidentokado.rotp_tlc.action.stand.projectile;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import com.github.standobyte.jojo.init.power.non_stand.hamon.ModHamonSkills;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.impl.nonstand.type.hamon.skill.BaseHamonSkill;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLGrapplingVineEntity;
import com.raidentokado.rotp_tlc.init.InitSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.Optional;

public class GLGrapple extends StandEntityAction {
    private boolean hasEnt;

    public GLGrapple(StandEntityAction.Builder builder) {
        super(builder);
    }



    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            LivingEntity user = userPower.getUser();
            GLGrapplingVineEntity vine = new GLGrapplingVineEntity(world, standEntity, userPower,false,user);
            vine.isSpread(false);
            vine.setHamonDamageOnHit(0,0);
            vine.isScarlet(false,0);

            if (isShiftVariation()) {
                INonStandPower.getNonStandPowerOptional(user).ifPresent(ipower -> {
                    Optional<HamonData> hamonOp = ipower.getTypeSpecificData(ModPowers.HAMON.get());
                    if(hamonOp.isPresent()){
                        HamonData hamon = hamonOp.get();
                        if (hamon.isSkillLearned(ModHamonSkills.THROWABLES_INFUSION.get()) && ipower.getEnergy()>0){
                            float cost = (float) hamon.getHamonStrengthLevel()/4;
                            hamon.hamonPointsFromAction(BaseHamonSkill.HamonStat.STRENGTH,cost);
                            hamon.hamonPointsFromAction(BaseHamonSkill.HamonStat.CONTROL,cost);
                            float hamonEfficiency = hamon.getActionEfficiency(cost, true);
                            user.playSound(ModSounds.HAMON_CONCENTRATION.get(),1,1);
                            standEntity.playSound(InitSounds.USER_GREEN_LANTERN.get(),1F,1);
                            vine.isCharged(true);
                            vine.setHamonDamageOnHit(hamonEfficiency,cost);
                        }
                        if(hamon.isSkillLearned(ModHamonSkills.SCARLET_OVERDRIVE.get())&& ipower.getEnergy()>0){
                            user.playSound(ModSounds.HAMON_CONCENTRATION.get(),1,1);
                            vine.isScarlet(true,hamon.getHamonStrengthLevel());
                        }
                        if(hamon.isSkillLearned(ModHamonSkills.HAMON_SPREAD.get())&& ipower.getEnergy()>0){
                            vine.isSpread(true);
                        }
                    }
                });

                vine.setBindEntities(true);
            }
            world.addFreshEntity(vine);
        }
    }


    @Override
    public boolean standRetractsAfterTask(IStandPower standPower, StandEntity standEntity) {
        return isShiftVariation();
    }

    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return this.isShiftVariation() && target.getType() == ActionTarget.TargetType.ENTITY;
    }


}