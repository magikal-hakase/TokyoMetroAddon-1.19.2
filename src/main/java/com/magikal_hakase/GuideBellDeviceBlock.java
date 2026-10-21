package com.magikal_hakase;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class GuideBellDeviceBlock extends BaseEntityBlock {
    // Property that stores the sound type
    public static final EnumProperty<GuideBellSound> SOUND_TYPE =
            EnumProperty.create("sound_type", GuideBellSound.class);

    public GuideBellDeviceBlock(Properties properties) {
        super(properties);
        // Default state: SINE
        this.registerDefaultState(this.stateDefinition.any().setValue(SOUND_TYPE, GuideBellSound.SINE));
    }

    protected static final VoxelShape SHAPE = Block.box(3.5, 13.0, 5.5, 12.5, 16.0, 10.5);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SOUND_TYPE);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);

        // Only works when the player holds the "Toolbox"
        if (heldItem.is(TokyoMetroAddonItems.TOOLBOX.get())) {
            if (!world.isClientSide) {
                // Cycle to the next sound type
                GuideBellSound currentSound = state.getValue(SOUND_TYPE);
                GuideBellSound nextSound = currentSound.next();
                world.setBlock(pos, state.setValue(SOUND_TYPE, nextSound), 3);

                // Play a feedback click sound
                world.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.BLOCKS, 0.5f, 1.0f);
            }
            return InteractionResult.SUCCESS;
        }

        // Without the toolbox, do nothing
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GuideBellDeviceBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
                                                                  BlockEntityType<T> type) {
        if (!world.isClientSide) { // Server side only
            return createTickerHelper(type, TokyoMetroAddonBlockEntities.GUIDE_BELL_DEVICE_ENTITY.get(),
                    GuideBellDeviceBlockEntity::tick);
        }
        return null;
    }
}
