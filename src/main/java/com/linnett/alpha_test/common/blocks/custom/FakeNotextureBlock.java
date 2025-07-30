package com.linnett.alpha_test.common.blocks.custom;

import com.linnett.alpha_test.client.particle.ParticlesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class FakeNotextureBlock extends Block {

    public FakeNotextureBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) return;

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

            level.addParticle(ParticlesRegistry.BUG_DARK.get(), x, y, z, 0, ySpeed, 0);
        }
    }

    
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity) {
            level.destroyBlock(pos, true);
        }
        super.stepOn(level, pos, state, entity);
    }
}

