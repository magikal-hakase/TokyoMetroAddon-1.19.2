package com.magikal_hakase;

import mtr.block.BlockTicketBarrier;
import mtr.block.IBlock;
import mtr.data.TicketSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

public class TokyoTicketBarrierBlock extends BlockTicketBarrier {
    public TokyoTicketBarrierBlock(boolean isEntrance) {
        super(isEntrance);
    }

    // NOTE: The MTR jar ships with SRG method names (getCollisionShape -> m_5939_, getShape -> m_5940_).
    // These overrides are intentionally written without @Override; at runtime the Forge modlauncher
    // remaps both this mod and MTR to official names, so the overrides are applied correctly.
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, BlockStateProperties.HORIZONTAL_FACING);
        TicketSystem.EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(state, BlockTicketBarrier.OPEN);
        VoxelShape base = IBlock.getVoxelShapeByDirection(13.0, 0.0, -2.5, 16.0, 18.0, 18.5, facing);
        return open.isOpen() ? base : Shapes.or(
                IBlock.getVoxelShapeByDirection(0.0, 0.0, 7.0, 16.0, 24.0, 9.0, facing), base);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, BlockStateProperties.HORIZONTAL_FACING);
        TicketSystem.EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(state, BlockTicketBarrier.OPEN);
        VoxelShape base = IBlock.getVoxelShapeByDirection(13.0, 0.0, -2.5, 16.0, 24.0, 18.5, facing);
        return open.isOpen() ? base : Shapes.or(
                IBlock.getVoxelShapeByDirection(0.0, 0.0, 7.0, 16.0, 24.0, 9.0, facing), base);
    }
}
