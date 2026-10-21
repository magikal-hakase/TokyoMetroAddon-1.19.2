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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.core.BlockPos;

public class AluminumSpindleSlopeBlock extends HorizontalDirectionalBlock {
    private static final VoxelShape NORTH_1 = Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0);
    private static final VoxelShape NORTH_2 = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0);
    private static final VoxelShape SHAPE_NORTH = Shapes.or(NORTH_1, NORTH_2);
    private static final VoxelShape EAST_1 = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0);
    private static final VoxelShape EAST_2 = Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_EAST = Shapes.or(EAST_1, EAST_2);
    private static final VoxelShape SOUTH_1 = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0);
    private static final VoxelShape SOUTH_2 = Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(SOUTH_1, SOUTH_2);
    private static final VoxelShape WEST_1 = Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    private static final VoxelShape WEST_2 = Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_WEST = Shapes.or(WEST_1, WEST_2);

    public AluminumSpindleSlopeBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }
}
