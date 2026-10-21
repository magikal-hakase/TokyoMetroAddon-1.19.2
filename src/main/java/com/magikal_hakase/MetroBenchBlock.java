package com.magikal_hakase;

import com.magikal_hakase.entity.SeatEntity;
import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MetroBenchBlock extends HorizontalDirectionalBlock {
    public static final EnumProperty<BenchPart> PART = EnumProperty.create("part", BenchPart.class);
    public static final BooleanProperty HAS_POLE = BooleanProperty.create("has_pole");

    public MetroBenchBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .setValue(PART, BenchPart.SINGLE)
                .setValue(HAS_POLE, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, PART, HAS_POLE);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).is(TokyoMetroAddonItems.TOOLBOX.get())) {
            if (!world.isClientSide) {
                world.setBlock(pos, state.cycle(HAS_POLE), 3);
            }
            return InteractionResult.SUCCESS;
        }
        if (!world.isClientSide) {
            SeatEntity seat = new SeatEntity(world, pos.getX() + 0.5, pos.getY() + 0.4, pos.getZ() + 0.5);
            world.addFreshEntity(seat);
            player.startRiding(seat);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = super.getStateForPlacement(ctx)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
        return state.setValue(PART, getPartState(state, ctx.getLevel(), ctx.getClickedPos()));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return state.setValue(PART, getPartState(state, world, pos));
    }

    private BenchPart getPartState(BlockState state, LevelAccessor world, BlockPos pos) {
        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();
        BlockState leftNeighbor = world.getBlockState(pos.relative(leftDir));
        BlockState rightNeighbor = world.getBlockState(pos.relative(rightDir));
        boolean hasLeft = leftNeighbor.is(this) && leftNeighbor.getValue(BlockStateProperties.HORIZONTAL_FACING) == facing;
        boolean hasRight = rightNeighbor.is(this) && rightNeighbor.getValue(BlockStateProperties.HORIZONTAL_FACING) == facing;
        if (hasLeft && hasRight) {
            return BenchPart.MIDDLE;
        }
        if (hasLeft) {
            return BenchPart.RIGHT;
        }
        if (hasRight) {
            return BenchPart.LEFT;
        }
        return BenchPart.SINGLE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, BlockStateProperties.HORIZONTAL_FACING);
        return IBlock.getVoxelShapeByDirection(0.1, 0.0, 0.1, 15.9, 16.0, 15.9, facing);
    }
}
