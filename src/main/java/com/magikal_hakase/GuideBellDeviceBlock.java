package com.magikal_hakase;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GuideBellDeviceBlock extends BlockWithEntity {
    // ▼▼▼ サウンドの種類を保存するプロパティを追加 ▼▼▼
    public static final EnumProperty<GuideBellSound> SOUND_TYPE = EnumProperty.of("sound_type", GuideBellSound.class);

    public GuideBellDeviceBlock(Settings settings) {
        super(settings);
        // デフォルトの状態を「正弦波(SINE)」に設定
        setDefaultState(this.stateManager.getDefaultState().with(SOUND_TYPE, GuideBellSound.SINE));
    }

    protected static final VoxelShape SHAPE = Block.createCuboidShape(3.5, 13.0, 5.5, 12.5, 16.0, 10.5);

    // プロパティをブロックの状態として登録
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SOUND_TYPE);
    }

    // ▼▼▼ 右クリック処理を大幅に変更 ▼▼▼
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);

        // プレイヤーが「工具箱(TOOLBOX)」を持っているかチェック
        if (heldItem.isOf(TokyoMetroAddonItems.TOOLBOX)) {
            if (!world.isClient) {
                // 現在のサウンド状態を取得し、次のサウンドに切り替える
                GuideBellSound currentSound = state.get(SOUND_TYPE);
                GuideBellSound nextSound = currentSound.next();
                world.setBlockState(pos, state.with(SOUND_TYPE, nextSound));

                // 設定変更のフィードバック音を鳴らす
                world.playSound(null, pos, SoundEvents.UI_BUTTON_CLICK, SoundCategory.BLOCKS, 0.5f, 1.0f);
            }
            return ActionResult.SUCCESS; // 処理成功
        }

        // 工具箱を持っていない場合は何もしない
        return ActionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GuideBellDeviceBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!world.isClient) {  // サーバーサイドでのみ実行
            return checkType(type, TokyoMetroAddonBlockEntities.GUIDE_BELL_DEVICE_ENTITY,
                    GuideBellDeviceBlockEntity::tick);
        }
        return null;
    }
}