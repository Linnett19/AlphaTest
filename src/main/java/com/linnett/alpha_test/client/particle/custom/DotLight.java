package com.linnett.alpha_test.client.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

import javax.annotation.Nullable;

public class DotLight extends TextureSheetParticle {

    private final float baseSize = 0.5F;
    private float stretchCycle = 0.0F;
    private final float red, green, blue;

    private final int fadeStartTick = 15;

    protected DotLight(ClientLevel level, double x, double y, double z, float red, float green, float blue) {
        super(level, x, y, z);

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.setColor(red, green, blue);

        this.setSize(baseSize, baseSize);
        this.quadSize = baseSize;
        this.lifetime = 40;
        this.gravity = 0.0F;
        this.yd = 0.05D;

        this.setAlpha(1.0F);
    }

    @Override
    public void tick() {
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.y += this.yd;

        stretchCycle += 0.2F;
        float stretch = (float) Math.sin(stretchCycle) * 0.2F + 1.0F;
        this.quadSize = baseSize * stretch;

        if (this.age >= fadeStartTick) {
            float progress = (float)(this.age - fadeStartTick) / (this.lifetime - fadeStartTick);
            this.setAlpha(1.0F - progress);
        }

        this.setColor(red, green, blue);
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

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            float red = 0.90F;
            float green = 0.30F;
            float blue = 1.00F;

            DotLight particle = new DotLight(level, x, y, z, red, green, blue);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }

    @Override
    public int getLightColor(float partialTicks) {
        return 240;
    }
}
