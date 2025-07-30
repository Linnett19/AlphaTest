package com.linnett.alpha_test.common.blocks.custom;

import com.linnett.alpha_test.client.particle.ParticlesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class NotextureLampBlock extends Block {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public NotextureLampBlock(BlockBehaviour.Properties properties) {
        super(properties.lightLevel(state -> state.getValue(LIT) ? 15 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            if (powered != state.getValue(LIT)) {
                level.setBlock(pos, state.setValue(LIT, powered), 3);
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide || !state.getValue(LIT)) return;

        if (random.nextFloat() < 0.2F) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + 1.05;
            double z = pos.getZ() + random.nextDouble();

            double ySpeed = 0.02 + random.nextDouble() * 0.02;

            level.addParticle(ParticlesRegistry.BUG_LIGHT.get(), x, y, z, 0, ySpeed, 0);
        }

        if (random.nextFloat() < 0.2F) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + 1.05;
            double z = pos.getZ() + random.nextDouble();

            double ySpeed = 0.02 + random.nextDouble() * 0.02;

            level.addParticle(ParticlesRegistry.BUG_WITHE.get(), x, y, z, 0, ySpeed, 0);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}

