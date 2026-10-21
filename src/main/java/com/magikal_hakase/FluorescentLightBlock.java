package com.magikal_hakase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FluorescentLightBlock extends HorizontalDirectionalBlock {

    // New state property (single, start, end)
    public static final EnumProperty<FluorescentLightPart> PART =
            EnumProperty.create("part", FluorescentLightPart.class);

    protected static final VoxelShape SHAPE = Block.box(0.0, 4.0, 0.0, 16.0, 12.0, 16.0);

    // North-south facing (Z axis) collision shape
    protected static final VoxelShape SHAPE_NS = Block.box(5.5, 14.5, 0.0, 10.5, 16.0, 16.0);
    // East-west facing (X axis) collision shape
    protected static final VoxelShape SHAPE_EW = Block.box(0.0, 14.5, 5.5, 16.0, 16.0, 10.5);

    public FluorescentLightBlock(Properties properties) {
        super(properties);
        // Default state: north facing single part
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, FluorescentLightPart.SINGLE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case EAST:
            case WEST:
                return SHAPE_EW;
            case NORTH:
            case SOUTH:
            default:
                return SHAPE_NS;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction playerFacing = ctx.getHorizontalDirection().getOpposite();
        BlockPos posToPlace = ctx.getClickedPos();
        Level world = ctx.getLevel();

        // Check the block behind the placement position (the connection source)
        BlockPos neighborPos = posToPlace.relative(playerFacing.getOpposite());
        BlockState neighborState = world.getBlockState(neighborPos);

        // Check whether the neighbor is a connectable "single" fluorescent light
        if (neighborState.is(this) && neighborState.getValue(PART) == FluorescentLightPart.SINGLE) {
            Direction neighborFacing = neighborState.getValue(FACING);
            // Check whether the neighbor faces towards this position
            if (neighborFacing == playerFacing) {
                // Update the neighbor to "START"
                world.setBlock(neighborPos, neighborState.setValue(PART, FluorescentLightPart.START), 3);
                // This block is placed as "END"
                return this.defaultBlockState().setValue(FACING, playerFacing)
                        .setValue(PART, FluorescentLightPart.END);
            }
        }

        // Otherwise place as "SINGLE"
        return this.defaultBlockState().setValue(FACING, playerFacing)
                .setValue(PART, FluorescentLightPart.SINGLE);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        // When this block is destroyed
        if (!state.is(newState.getBlock())) {
            // If this was part of a pair, reset the other block back to "SINGLE"
            Direction facing = state.getValue(FACING);
            FluorescentLightPart part = state.getValue(PART);

            // If this was START, find the END block and reset it
            if (part == FluorescentLightPart.START) {
                BlockPos endPos = pos.relative(facing);
                BlockState endState = world.getBlockState(endPos);
                if (endState.is(this) && endState.getValue(PART) == FluorescentLightPart.END) {
                    world.setBlock(endPos, endState.setValue(PART, FluorescentLightPart.SINGLE), 3);
                }
            }
            // If this was END, find the START block and reset it
            else if (part == FluorescentLightPart.END) {
                BlockPos startPos = pos.relative(facing.getOpposite());
                BlockState startState = world.getBlockState(startPos);
                if (startState.is(this) && startState.getValue(PART) == FluorescentLightPart.START) {
                    world.setBlock(startPos, startState.setValue(PART, FluorescentLightPart.SINGLE), 3);
                }
            }
        }
        super.onRemove(state, world, pos, newState, moved);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART);
    }
}
