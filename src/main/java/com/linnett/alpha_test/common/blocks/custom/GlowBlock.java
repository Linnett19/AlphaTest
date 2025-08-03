package com.linnett.alpha_test.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GlowBlock extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 15);

    public GlowBlock() {
        super(BlockBehaviour.Properties.of()
                .noCollission()
                .noOcclusion()
                .replaceable()
                .strength(0.0F)
                .lightLevel(state -> state.getValue(LEVEL)));
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }

    // Метод для генерации частиц - вызывается автоматически Minecraft
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (level.isClientSide) {
            // Простая проверка - если игрок в радиусе 10 блоков держит этот блок
            for (Player player : level.players()) {
                if (player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()) <= 100) { // 10^2 = 100
                    ItemStack mainHand = player.getMainHandItem();
                    ItemStack offHand = player.getOffhandItem();

                    if (mainHand.getItem() == this.asItem() || offHand.getItem() == this.asItem()) {
                        // Генерация частиц в центре блока
                        double x = pos.getX() + 0.5;
                        double y = pos.getY() + 0.7;
                        double z = pos.getZ() + 0.5;

                        // Основные частицы (100% шанс)
                        level.addParticle(ParticleTypes.GLOW, x, y, z, 0, 0, 0);

                        // Дополнительные частицы (30% шанс)
                        if (random.nextFloat() < 0.3) {
                            level.addParticle(ParticleTypes.FLAME,
                                    x, y, z,
                                    (random.nextDouble() - 0.5) * 0.1,
                                    (random.nextDouble() - 0.5) * 0.1,
                                    (random.nextDouble() - 0.5) * 0.1
                            );
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LEVEL, 1);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (!player.isShiftKeyDown() && heldItem.getItem() == this.asItem()) {
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel < 15) {
                level.setBlock(pos, state.setValue(LEVEL, currentLevel + 1), 3);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}