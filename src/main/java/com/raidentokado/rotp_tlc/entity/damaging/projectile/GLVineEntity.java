package com.raidentokado.rotp_tlc.entity.damaging.projectile;

import com.github.standobyte.jojo.JojoModConfig;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.util.mc.damage.DamageUtil;
import com.raidentokado.rotp_tlc.init.InitEntities;
import com.raidentokado.rotp_tlc.init.InitSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class GLVineEntity extends OwnerBoundProjectileEntity {
    private float yRotOffset;
    private float xRotOffset;
    private boolean isBinding;
    private boolean dealtDamage;
    private float knockback = 0;
    private boolean spreed;
    private float baseHitPoints;


    public GLVineEntity(World world, LivingEntity entity, float angleXZ, float angleYZ, boolean isBinding) {
        super(InitEntities.GL_VINE.get(), entity, world);
        this.yRotOffset = angleXZ;
        this.xRotOffset = angleYZ;
        this.isBinding = isBinding;
    }

    public GLVineEntity(EntityType<? extends GLVineEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean standDamage() {
        return true;
    }

    public boolean isBinding() {
        return isBinding;
    }

    @Override
    public float getBaseDamage() {
        double mult = JojoModConfig.getCommonConfigInstance(false).standDamageMultiplier.get();
        return isBinding ? 1.5F * (float) mult  : 2F * (float) mult;
    }

    @Override
    public void tick() {
        super.tick();
        if(getOwner() != null){

        }
    }

    public void addKnockback(float knockback) {
        this.knockback = knockback;
    }

    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        return !dealtDamage ? super.hurtTarget(target, owner) : false;
    }

    @Override
    protected boolean shouldHurtThroughInvulTicks() {
        return true;
    }

    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            dealtDamage = true;
            Entity target = entityRayTraceResult.getEntity();
            playSound(InitSounds.GREEN_LANTERN_GRAPPLE_CATCH.get(), .25F, 1.0F);

            if (isBinding) {
                if (target instanceof LivingEntity) {
                    LivingEntity livingTarget = (LivingEntity) target;
                }
            }
            else {
                if (knockback > 0 && target instanceof LivingEntity) {
                    DamageUtil.knockback((LivingEntity) target, knockback, yRot);
                }
                setIsRetracting(true);
            }
        }
    }


    private static final BlockState LAMP = Blocks.REDSTONE_LAMP.defaultBlockState().setValue(RedstoneLampBlock.LIT,true);
    @Override
    protected void afterBlockHit(BlockRayTraceResult blockRayTraceResult, boolean brokenBlock) {
        if(level.getBlockState(blockRayTraceResult.getBlockPos()).getBlock()==Blocks.REDSTONE_LAMP &&
                !level.getBlockState(blockRayTraceResult.getBlockPos()).getBlockState().getValue(RedstoneLampBlock.LIT)
        ){
            level.setBlockAndUpdate(blockRayTraceResult.getBlockPos(),LAMP);
        }
        if(level.getBlockState(blockRayTraceResult.getBlockPos()).getBlock()==Blocks.IRON_BLOCK){
            if(this.getOwner() != null){
                BlockPos blockPos = blockRayTraceResult.getBlockPos();
                LivingEntity user = this.getOwner();
                if(this.getOwner() instanceof StandEntity){
                    user = ((StandEntity) this.getOwner()).getUser();
                }
                LivingEntity finalUser = user;
            }

        }

    }


    @Override
    protected float knockbackMultiplier() {
        return 0F;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0.0F;
    }

    @Override
    public int ticksLifespan() {
        int ticks = super.ticksLifespan();
        if (isBinding && isAttachedToAnEntity()) {
            ticks += 10;
        }
        return ticks;
    }



    @Override
    protected float movementSpeed() {
        return 16 / (float) ticksLifespan();
    }

    @Override
    public boolean isBodyPart() {
        return true;
    }

    private static final Vector3d OFFSET = new Vector3d(-0.3, -0.2, 0.75);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }

    @Override
    protected Vector3d originOffset(float yRot, float xRot, double distance) {
        return super.originOffset(yRot + yRotOffset, xRot + xRotOffset, distance);
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putFloat("Points", baseHitPoints);
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        baseHitPoints = nbt.getFloat("Points");
    }


    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        super.writeSpawnData(buffer);
        buffer.writeFloat(yRotOffset);
        buffer.writeFloat(xRotOffset);
        buffer.writeBoolean(isBinding);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        this.yRotOffset = additionalData.readFloat();
        this.xRotOffset = additionalData.readFloat();
        this.isBinding = additionalData.readBoolean();

    }


    public GLVineEntity setBaseUsageStatPoints (float points){
        this.baseHitPoints = points;
        return this;
    }

}