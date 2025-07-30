package com.linnett.alpha_test.client.particle;

import com.linnett.alpha_test.Alpha_test;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ParticlesRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Alpha_test.MODID);

    public static final RegistryObject<SimpleParticleType> BUG_DARK = PARTICLE_TYPES.register("bug_dark", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BUG_LIGHT = PARTICLE_TYPES.register("bug_light", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BUG_WITHE = PARTICLE_TYPES.register("bug_withe", () -> new SimpleParticleType(true));





    public static final RegistryObject<ParticleType<BlockParticleOption>> SAND_PARTICLES = PARTICLE_TYPES.register("sand_particles", () -> new ParticleType<BlockParticleOption>(true, BlockParticleOption.DESERIALIZER) {
        @Override
        public Codec<BlockParticleOption> codec() {
            Function<ParticleType<BlockParticleOption>, Codec<BlockParticleOption>> codec = BlockParticleOption::codec;
            return (Codec)codec.apply(this);
        }
    });



    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}