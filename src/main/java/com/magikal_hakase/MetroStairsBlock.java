package com.magikal_hakase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

import java.util.Objects;

public class MetroStairsBlock extends StairsBlock {
    public static final EnumProperty<StairEnd> STAIR_END = EnumProperty.of("stair_end", StairEnd.class);

    public MetroStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
        // デフォルトの状態を設定
        setDefaultState(this.stateManager.getDefaultState()
                .with(STAIR_END, StairEnd.SINGLE)
                .with(FACING, Direction.NORTH)
                .with(HALF, net.minecraft.block.enums.BlockHalf.BOTTOM)
                .with(SHAPE, net.minecraft.block.enums.StairShape.STRAIGHT)
                .with(WATERLOGGED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STAIR_END);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // 設置時の状態を計算
        BlockState state = super.getPlacementState(ctx);
        return Objects.requireNonNull(state).with(STAIR_END, getEndState(state, ctx.getWorld(), ctx.getBlockPos()));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        // 周囲のブロックが更新された時の状態を再計算
        BlockState updatedState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        return updatedState.with(STAIR_END, getEndState(updatedState, world, pos));
    }

    // 端の状態を判断する核心的なロジック
    private StairEnd getEndState(BlockState state, WorldAccess world, BlockPos pos) {
        Direction facing = state.get(FACING);

        // 自分の向きに対する「右」と「左」の方向を取得
        Direction rightDir = facing.rotateYClockwise();
        Direction leftDir = facing.rotateYCounterclockwise();

        // 左右のブロックを取得
        BlockState rightNeighbor = world.getBlockState(pos.offset(rightDir));
        BlockState leftNeighbor = world.getBlockState(pos.offset(leftDir));

        // 左右のブロックが同じ種類の階段か、かつ同じ向きかをチェック
        boolean hasRight = rightNeighbor.isOf(this) && rightNeighbor.get(FACING) == facing;
        boolean hasLeft = leftNeighbor.isOf(this) && leftNeighbor.get(FACING) == facing;

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