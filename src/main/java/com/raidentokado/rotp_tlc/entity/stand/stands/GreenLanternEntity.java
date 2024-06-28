package com.raidentokado.rotp_tlc.entity.stand.stands;


import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.entity.stand.StandRelativeOffset;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mod.JojoModUtil;
import com.raidentokado.rotp_tlc.init.InitSounds;
import com.raidentokado.rotp_tlc.init.InitTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class GreenLanternEntity extends StandEntity {
    private static final DataParameter<Boolean> REFLECT = EntityDataManager.defineId(GreenLanternEntity.class, DataSerializers.BOOLEAN);

    public GreenLanternEntity(StandEntityType<GreenLanternEntity> type, World world){
        super(type, world);
        unsummonOffset = getDefaultOffsetFromUser().copy();
    }
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        LivingEntity user = getUser();
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            player.abilities.mayfly = player.abilities.instabuild;
        }
    }

    private final StandRelativeOffset offsetDefault = StandRelativeOffset.withYOffset(0, 0, 0);

    @Override
    public void tick() {
        super.tick();
        LivingEntity user = getUser();
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            player.abilities.mayfly = true;

            if (level.isClientSide()) {
                float speed = 0.30f;
                player.abilities.setFlyingSpeed(speed);
            }
        }


        IStandPower.getStandPowerOptional(this.getUser()).ifPresent(power -> {

            if(getReflect()){
                if(!level.isClientSide){
                    power.consumeStamina(7);
                    List<ProjectileEntity> list =  level.getEntitiesOfClass(ProjectileEntity.class, Objects.requireNonNull(this.getUser()).getBoundingBox().inflate(2),EntityPredicates.ENTITY_STILL_ALIVE);
                    list.forEach(projectileEntity -> {

                        if(!InitTags.NO_REFLECT.contains(projectileEntity.getType())){
                            RayTraceResult result = level.clip(new RayTraceContext(
                                    projectileEntity.position(),
                                    projectileEntity.position().add(projectileEntity.getDeltaMovement()),
                                    RayTraceContext.BlockMode.COLLIDER,
                                    RayTraceContext.FluidMode.NONE,
                                    projectileEntity));

                            if(result.getType() == RayTraceResult.Type.MISS){
                                JojoModUtil.deflectProjectile(projectileEntity,projectileEntity.getDeltaMovement().reverse());
                                power.consumeStamina(14);
                                level.playSound(null,projectileEntity.blockPosition(), InitSounds.GREEN_LANTERN_SUMMON.get(), SoundCategory.PLAYERS,0.7F,1);

                            }

                        }


                    });
                    if(power.getStamina()==0){
                        if(getReflect()){
                            setReflect(false);
                            level.playSound(null,this.blockPosition(), InitSounds.GREEN_LANTERN_UNSUMMON.get(), SoundCategory.PLAYERS,1,1);

                        }
                    }
                }
            }
        });
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(REFLECT, false);

    }


    @Override
    public boolean isPickable(){ return false;}

    public StandRelativeOffset getDefaultOffsetFromUser() {return offsetDefault;}

    public void setReflect(boolean active){
        entityData.set(REFLECT,active);
    }

    public boolean getReflect(){
        return entityData.get(REFLECT);
    }
}