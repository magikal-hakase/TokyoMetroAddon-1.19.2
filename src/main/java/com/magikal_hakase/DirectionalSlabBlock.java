package com.magikal_hakase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;

public class DirectionalSlabBlock extends SlabBlock {

    // Property that stores the facing direction
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DirectionalSlabBlock(Properties properties) {
        super(properties);
        // Default state: bottom slab, facing north, not waterlogged
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TYPE, SlabType.BOTTOM)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        // --- Slab placement logic (borrowed from SlabBlock) ---
        BlockPos blockPos = ctx.getClickedPos();
        BlockState blockState = ctx.getLevel().getBlockState(blockPos);
        if (blockState.is(this)) {
            // Placing on an existing slab of the same type -> double slab
            return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false);
        }
        boolean waterlogged = ctx.getLevel().getFluidState(blockPos).getType() == Fluids.WATER;
        SlabType slabType = ctx.getClickLocation().y - (double) blockPos.getY() > 0.5
                ? SlabType.TOP : SlabType.BOTTOM;

        // --- Combine slab properties with the facing direction ---
        return this.defaultBlockState()
                .setValue(TYPE, slabType)
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // Inherit TYPE and WATERLOGGED from SlabBlock
        super.createBlockStateDefinition(builder);
        // Add the new facing property
        builder.add(FACING);
    }
}
