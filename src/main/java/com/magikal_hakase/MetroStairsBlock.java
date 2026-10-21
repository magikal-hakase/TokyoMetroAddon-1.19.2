package com.magikal_hakase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

import java.util.Objects;

public class MetroStairsBlock extends StairBlock {
    public static final EnumProperty<StairEnd> STAIR_END = EnumProperty.create("stair_end", StairEnd.class);

    public MetroStairsBlock(BlockState baseBlockState, Properties properties) {
        super(baseBlockState, properties);
        // Default state
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(STAIR_END, StairEnd.SINGLE)
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, Half.BOTTOM)
                .setValue(SHAPE, StairsShape.STRAIGHT)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAIR_END);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        // Compute the state at placement
        BlockState state = super.getStateForPlacement(ctx);
        return Objects.requireNonNull(state)
                .setValue(STAIR_END, getEndState(state, ctx.getLevel(), ctx.getClickedPos()));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        // Recompute the state when neighboring blocks update
        BlockState updatedState = super.updateShape(state, direction, neighborState, world, pos, neighborPos);
        return updatedState.setValue(STAIR_END, getEndState(updatedState, world, pos));
    }

    // Core logic that determines the end state
    private StairEnd getEndState(BlockState state, LevelAccessor world, BlockPos pos) {
        Direction facing = state.getValue(FACING);

        // "Right" and "left" directions relative to the facing
        Direction rightDir = facing.getClockWise();
        Direction leftDir = facing.getCounterClockWise();

        // Get left and right neighbor blocks
        BlockState rightNeighbor = world.getBlockState(pos.relative(rightDir));
        BlockState leftNeighbor = world.getBlockState(pos.relative(leftDir));

        // Check whether both neighbors are the same stairs type with the same facing
        boolean hasRight = rightNeighbor.is(this) && rightNeighbor.getValue(FACING) == facing;
        boolean hasLeft = leftNeighbor.is(this) && leftNeighbor.getValue(FACING) == facing;

        if (hasLeft && hasRight) {
            return StairEnd.MIDDLE;
        } else if (hasLeft) {
            return StairEnd.RIGHT;
        } else if (hasRight) {
            return StairEnd.LEFT;
        } else {
            return StairEnd.SINGLE;
        }
    }
}
