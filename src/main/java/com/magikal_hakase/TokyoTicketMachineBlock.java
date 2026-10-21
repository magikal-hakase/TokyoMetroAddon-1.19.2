package com.magikal_hakase;

import mtr.block.BlockTicketMachine;
import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TokyoTicketMachineBlock extends BlockTicketMachine {
    public TokyoTicketMachineBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    // NOTE: The MTR jar ships with SRG method names (getCollisionShape -> m_5939_).
    // This override is intentionally written without @Override; at runtime the Forge modlauncher
    // remaps both this mod and MTR to official names, so the override is applied correctly.
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, BlockStateProperties.HORIZONTAL_FACING);
        double height = 16.0;
        return IBlock.getVoxelShapeByDirection(0.0, 0.0, 0.0, 16.0, height, 12.5, facing);
    }
}
