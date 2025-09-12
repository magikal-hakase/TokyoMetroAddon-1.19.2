package com.magikal_hakase;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuideBellDeviceBlockEntity extends BlockEntity {
    private int tickCounter = 0;

    public GuideBellDeviceBlockEntity(BlockPos pos, BlockState state) {
        super(TokyoMetroAddonBlockEntities.GUIDE_BELL_DEVICE_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, GuideBellDeviceBlockEntity be) {
        be.tickCounter++;
        if (be.tickCounter >= 200) {
            be.tickCounter = 0;
            if (world != null && !world.isClient) {
                // ▼▼▼ ブロックの状態(state)からサウンドの種類を取得 ▼▼▼
                GuideBellSound sound = state.get(GuideBellDeviceBlock.SOUND_TYPE);

                SoundEvent soundToPlay = switch (sound) {
                    case SAWTOOTH -> TokyoMetroAddonSounds.GUIDE_BELL_SAWTOOTH;
                    case SQUARE -> TokyoMetroAddonSounds.GUIDE_BELL_SQUARE;
                    default -> TokyoMetroAddonSounds.GUIDE_BELL_SINE; // SINE
                };
                world.playSound(null, pos, soundToPlay, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
    }
}