package com.linnett.alpha_test.client.event;

import com.linnett.alpha_test.Alpha_test;
import com.linnett.alpha_test.client.particle.ParticlesRegistry;
import com.linnett.alpha_test.client.particle.custom.Dot;
import com.linnett.alpha_test.client.particle.custom.DotLight;
import com.linnett.alpha_test.client.particle.custom.DotWithe;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Alpha_test.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBus {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {

        event.registerSpriteSet(ParticlesRegistry.BUG_DARK.get(), Dot.Provider::new);
        event.registerSpriteSet(ParticlesRegistry.BUG_LIGHT.get(), DotLight.Provider::new);
        event.registerSpriteSet(ParticlesRegistry.BUG_WITHE.get(), DotWithe.Provider::new);


    }
}