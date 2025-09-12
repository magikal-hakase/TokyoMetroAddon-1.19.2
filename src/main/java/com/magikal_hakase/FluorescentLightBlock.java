package com.magikal_hakase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FluorescentLightBlock extends HorizontalFacingBlock {

    // 新しい状態プロパティ (単体、始点、終点)
    public static final EnumProperty<FluorescentLightPart> PART = EnumProperty.of("part", FluorescentLightPart.class);

    // 当たり判定 (必要に応じて調整)
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 4.0, 0.0, 16.0, 12.0, 16.0);

    public FluorescentLightBlock(Settings settings) {
        super(settings);
        // デフォルトの状態を設定（北向きの単体）
        setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(PART, FluorescentLightPart.SINGLE));
    }

    protected static final VoxelShape SHAPE_NS = Block.createCuboidShape(5.5, 14.5, 0.0, 10.5, 16.0, 16.0);
    // 東西向き(X軸方向)の当たり判定
    protected static final VoxelShape SHAPE_EW = Block.createCuboidShape(0.0, 14.5, 5.5, 16.0, 16.0, 10.5);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // ブロックの向き(FACING)に応じて当たり判定を切り替える
        switch (state.get(FACING)) {
            case EAST:
            case WEST:
                return SHAPE_EW; // 東西向きの場合はSHAPE_EWを返す
            case NORTH:
            case SOUTH:
            default: // デフォルトも設定しておく
                return SHAPE_NS; // 南北向きの場合はSHAPE_NSを返す
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction playerFacing = ctx.getPlayerFacing().getOpposite();
        BlockPos posToPlace = ctx.getBlockPos();
        World world = ctx.getWorld();

        // 自分の後ろ側（接続元）になるブロックの位置を確認
        BlockPos neighborPos = posToPlace.offset(playerFacing.getOpposite());
        BlockState neighborState = world.getBlockState(neighborPos);

        // 隣のブロックが接続可能な「単体」の蛍光灯かチェック
        if (neighborState.isOf(this) && neighborState.get(PART) == FluorescentLightPart.SINGLE) {
            Direction neighborFacing = neighborState.get(FACING);
            // 隣のブロックが自分の方を向いているかチェック
            if (neighborFacing == playerFacing) {
                // 隣のブロックの状態を「始点(START)」に更新
                world.setBlockState(neighborPos, neighborState.with(PART, FluorescentLightPart.START), 3);
                // 自分は「終点(END)」として設置される
                return this.getDefaultState().with(FACING, playerFacing).with(PART, FluorescentLightPart.END);
            }
        }

        // どの条件にも当てはまらない場合は「単体(SINGLE)」として設置
        return this.getDefaultState().with(FACING, playerFacing).with(PART, FluorescentLightPart.SINGLE);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // このブロックが破壊された場合
        if (!state.isOf(newState.getBlock())) {
            // 自分がペアの一部だった場合、相方のブロックを「単体」に戻す
            Direction facing = state.get(FACING);
            FluorescentLightPart part = state.get(PART);

            // 自分が始点(START)なら、終点(END)だったブロックを探して単体に戻す
            if (part == FluorescentLightPart.START) {
                BlockPos endPos = pos.offset(facing);
                BlockState endState = world.getBlockState(endPos);
                if (endState.isOf(this) && endState.get(PART) == FluorescentLightPart.END) {
                    world.setBlockState(endPos, endState.with(PART, FluorescentLightPart.SINGLE), 3);
                }
            }
            // 自分が終点(END)なら、始点(START)だったブロックを探して単体に戻す
            else if (part == FluorescentLightPart.END) {
                BlockPos startPos = pos.offset(facing.getOpposite());
                BlockState startState = world.getBlockState(startPos);
                if (startState.isOf(this) && startState.get(PART) == FluorescentLightPart.START) {
                    world.setBlockState(startPos, startState.with(PART, FluorescentLightPart.SINGLE), 3);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // FACING と PART プロパティを登録
        builder.add(FACING, PART);
    }
}