package com.magikal_hakase;

import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StainlessSteelFenceBlock extends HorizontalDirectionalBlock {
    public static final EnumProperty<FencePostPart> PART = EnumProperty.create("part", FencePostPart.class);

    public StainlessSteelFenceBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .setValue(PART, FencePostPart.SINGLE));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, BlockStateProperties.HORIZONTAL_FACING);
        return IBlock.getVoxelShapeByDirection(0.0, 0.0, 14.0, 16.0, 22.0, 15.95, facing);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction blockFacing = ctx.getHorizontalDirection().getOpposite();
        BlockPos posToPlace = ctx.getClickedPos();
        Level world = ctx.getLevel();
        Direction leftDir = blockFacing.getCounterClockWise();
        Direction rightDir = blockFacing.getClockWise();
        BlockPos leftNeighborPos = posToPlace.relative(leftDir);
        BlockState leftNeighborState = world.getBlockState(leftNeighborPos);
        BlockPos rightNeighborPos = posToPlace.relative(rightDir);
        BlockState rightNeighborState = world.getBlockState(rightNeighborPos);
        if (leftNeighborState.is(this) && leftNeighborState.getValue(PART) == FencePostPart.SINGLE
                && leftNeighborState.getValue(BlockStateProperties.HORIZONTAL_FACING) == blockFacing) {
            world.setBlock(leftNeighborPos, leftNeighborState.setValue(PART, FencePostPart.LEFT), 3);
            return this.defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, blockFacing)
                    .setValue(PART, FencePostPart.RIGHT);
        }
        if (rightNeighborState.is(this) && rightNeighborState.getValue(PART) == FencePostPart.SINGLE
                && rightNeighborState.getValue(BlockStateProperties.HORIZONTAL_FACING) == blockFacing) {
            world.setBlock(rightNeighborPos, rightNeighborState.setValue(PART, FencePostPart.RIGHT), 3);
            return this.defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, blockFacing)
                    .setValue(PART, FencePostPart.LEFT);
        }
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, blockFacing)
                .setValue(PART, FencePostPart.SINGLE);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockPos leftNeighborPos;
            BlockState leftNeighborState;
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            FencePostPart part = state.getValue(PART);
            Direction leftDir = facing.getCounterClockWise();
            Direction rightDir = facing.getClockWise();
            if (part == FencePostPart.LEFT) {
                BlockPos rightNeighborPos = pos.relative(rightDir);
                BlockState rightNeighborState = world.getBlockState(rightNeighborPos);
                if (rightNeighborState.is(this) && rightNeighborState.getValue(PART) == FencePostPart.RIGHT) {
                    world.setBlock(rightNeighborPos, rightNeighborState.setValue(PART, FencePostPart.SINGLE), 3);
                }
            } else if (part == FencePostPart.RIGHT
                    && (leftNeighborState = world.getBlockState(leftNeighborPos = pos.relative(leftDir))).is(this)
                    && leftNeighborState.getValue(PART) == FencePostPart.LEFT) {
                world.setBlock(leftNeighborPos, leftNeighborState.setValue(PART, FencePostPart.SINGLE), 3);
            }
        }
        super.onRemove(state, world, pos, newState, moved);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, PART);
    }
}
