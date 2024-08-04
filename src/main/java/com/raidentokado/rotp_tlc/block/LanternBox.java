package com.raidentokado.rotp_tlc.block;

import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.google.common.collect.ImmutableList;
import com.raidentokado.rotp_tlc.init.AddonStands;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;


public class    LanternBox extends Block {

    public LanternBox(Properties properties) {
        super(properties);
    }
    public static final List<EntityStandRegistryObject.EntityStandSupplier<? extends StandType<?>, ?>> LANTERN_STANDS = ImmutableList.of(
            AddonStands.GREEN_LANTERN
            // и тут через запятую можно остальные прописать
    );

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        IStandPower playerStandData = IStandPower.getPlayerStandPower(player);
        if (!playerStandData.hasPower()) {
            if (!world.isClientSide() && !LANTERN_STANDS.isEmpty()) {
                Random random = player.getRandom();
                StandType<?> stand = LANTERN_STANDS.get(random.nextInt(LANTERN_STANDS.size())).getStandType();
                playerStandData.givePower(stand);
            }
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, (new AxisAlignedBB(x, y, z, x, y, z)).inflate(2.0D, 2.0D, 2.0D))) {
        }
        world.getBlockTicks().scheduleTick(pos, this, 10);
    }

    @Deprecated
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        world.getBlockTicks().scheduleTick(currentPos, this, 10);
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        world.getBlockTicks().scheduleTick(pos, this, 10);
    }
}