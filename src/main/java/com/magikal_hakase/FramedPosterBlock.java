package com.magikal_hakase;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;

public class FramedPosterBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 6);
    private static final VoxelShape NORTH_SHAPE = Block.box(0.5, -3.0, 15.5, 15.5, 17.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.5, -3.0, 0.0, 15.5, 17.0, 0.5);
    private static final VoxelShape EAST_SHAPE = Block.box(0.0, -3.0, 0.5, 0.5, 17.0, 15.5);
    private static final VoxelShape WEST_SHAPE = Block.box(15.5, -3.0, 0.5, 16.0, 17.0, 15.5);

    public FramedPosterBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .setValue(VARIANT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, VARIANT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        int randomVariant = ctx.getLevel().getRandom().nextInt(7);
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(VARIANT, randomVariant);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }
}
