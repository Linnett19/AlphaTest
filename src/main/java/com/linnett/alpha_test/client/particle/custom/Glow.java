package com.linnett.alpha_test.client.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class Glow extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected Glow(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.sprites = spriteSet;

        this.lifetime = 50;
        this.gravity = 0f;

        this.xd = 0;
        this.yd = 0;
        this.zd = 0;

        this.setSize(0.25F, 0.25F);
        this.setAlpha(1f);
        this.pickSprite(spriteSet);
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return 0.25F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new Glow(level, x, y, z, spriteSet);
        }
    }
}

