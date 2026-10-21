package com.magikal_hakase;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GuideBellDeviceBlockEntity extends BlockEntity {
    private int tickCounter = 0;

    public GuideBellDeviceBlockEntity(BlockPos pos, BlockState state) {
        super(TokyoMetroAddonBlockEntities.GUIDE_BELL_DEVICE_ENTITY.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, GuideBellDeviceBlockEntity be) {
        be.tickCounter++;
        if (be.tickCounter >= 200) {
            be.tickCounter = 0;
            if (world != null && !world.isClientSide) {
                // Get the sound type from the block state
                GuideBellSound sound = state.getValue(GuideBellDeviceBlock.SOUND_TYPE);

                SoundEvent soundToPlay = switch (sound) {
                    case SAWTOOTH -> TokyoMetroAddonSounds.GUIDE_BELL_SAWTOOTH.get();
                    case SQUARE -> TokyoMetroAddonSounds.GUIDE_BELL_SQUARE.get();
                    default -> TokyoMetroAddonSounds.GUIDE_BELL_SINE.get(); // SINE
                };
                world.playSound(null, pos, soundToPlay, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
    }
}
