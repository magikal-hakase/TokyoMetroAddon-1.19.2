package com.magikal_hakase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class DirectionalSlabBlock extends SlabBlock {

    // 向きを保存するためのプロパティ
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public DirectionalSlabBlock(Settings settings) {
        super(settings);
        // デフォルトの状態を設定（下付き、北向き、水没なし）
        setDefaultState(this.stateManager.getDefaultState()
                .with(TYPE, SlabType.BOTTOM)
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // --- ここからスラブの性質を決める処理 (SlabBlockから引用) ---
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            // 既に同じスラブがある場合はダブルスラブにする
            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);
        }
        boolean waterlogged = ctx.getWorld().getFluidState(blockPos).getFluid() == Fluids.WATER;
        SlabType slabType = ctx.getHitPos().y - (double)blockPos.getY() > 0.5 ? SlabType.TOP : SlabType.BOTTOM;

        // --- 向きの性質とスラブの性質を組み合わせて返す ---
        return this.getDefaultState()
                .with(TYPE, slabType)
                .with(FACING, ctx.getPlayerFacing().getOpposite()) // プレイヤーの向きから設置方向を決める
                .with(WATERLOGGED, waterlogged);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // このブロックが持つ状態をすべて登録する
        super.appendProperties(builder); // SlabBlockのTYPEとWATERLOGGEDを継承
        builder.add(FACING); // 新たに向きのプロパティを追加
    }
}