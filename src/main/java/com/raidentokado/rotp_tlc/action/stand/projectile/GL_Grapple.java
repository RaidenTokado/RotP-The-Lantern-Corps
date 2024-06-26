package com.raidentokado.rotp_tlc.action.stand.projectile;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLGrapplingVineEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class GL_Grapple extends StandEntityAction {
    private boolean hasEnt;

    public GL_Grapple(StandEntityAction.Builder builder) {
        super(builder);
    }



    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            LivingEntity user = userPower.getUser();
            GLGrapplingVineEntity vine = new GLGrapplingVineEntity(world, standEntity, userPower,false,user);
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