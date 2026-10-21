package com.magikal_hakase;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class CustomDoorBlock extends DoorBlock {
    public CustomDoorBlock(BlockBehaviour.Properties settings) {
        super(settings, BlockSetType.IRON);
    }
}
