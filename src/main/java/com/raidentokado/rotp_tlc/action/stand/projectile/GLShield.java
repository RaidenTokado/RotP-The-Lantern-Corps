package com.raidentokado.rotp_tlc.action.stand.projectile;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.raidentokado.rotp_tlc.entity.damaging.projectile.GLShieldEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class GLShield extends StandEntityAction {

    public GLShield(StandEntityAction.Builder builder) {
        super(builder.holdType());
    }

    @Override
    public void startedHolding(World world, LivingEntity user, IStandPower power, ActionTarget target, boolean requirementsFulfilled) {
        if (!world.isClientSide() && requirementsFulfilled) {
            float width = 8;
            float height = 4;
            GLShieldEntity shield = new GLShieldEntity(world, user, width, height);
            shield.yRot = user.yRot;
            shield.xRot = user.xRot;
            shield.updateShieldPos();
            world.addFreshEntity(shield);
        }
    }
}
