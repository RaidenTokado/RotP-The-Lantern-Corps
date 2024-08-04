package com.raidentokado.rotp_tlc.block;

import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.google.common.collect.ImmutableList;
import com.raidentokado.rotp_tlc.init.AddonStands;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class LanternBox extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    protected static final VoxelShape NORTH_SHAPE = Block.box(5, 0, 5, 11, 5, 11);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(5, 0, 5, 11, 5, 11);
    protected static final VoxelShape WEST_SHAPE = Block.box(5, 0, 5, 11, 5, 11);
    protected static final VoxelShape EAST_SHAPE = Block.box(5, 0, 5, 11, 5, 11);

    public LanternBox(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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


    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirror) {
        return mirror == Mirror.NONE ? state : state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch(state.getValue(FACING)) {
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case NORTH:
            default:
                return NORTH_SHAPE;
        }
    }
}