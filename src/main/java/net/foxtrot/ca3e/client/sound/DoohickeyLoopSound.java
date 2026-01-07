package net.foxtrot.ca3e.client.sound;

import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class DoohickeyLoopSound extends AbstractTickableSoundInstance {

    private final MiningDoohickeyBlockEntity be;
    private boolean ending = false;

    public DoohickeyLoopSound(MiningDoohickeyBlockEntity be, SoundEvent sound, SoundSource source, float volume, float pitch) {
        super(sound, source, RandomSource.create());
        this.be = be;

        this.looping = true;
        this.delay = 0;
        this.attenuation = SoundInstance.Attenuation.LINEAR;

        this.volume = volume;
        this.pitch = pitch;

        this.x = be.getBlockPos().getX() + 0.5;
        this.y = be.getBlockPos().getY() + 0.5;
        this.z = be.getBlockPos().getZ() + 0.5;
    }

    public void end() {
        this.ending = true;
    }

    public boolean isEnding() {
        return this.ending;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public void tick() {
        if (ending || be.isRemoved() || be.getLevel() == null) {
            this.stop();
            return;
        }

        this.x = be.getBlockPos().getX() + 0.5;
        this.y = be.getBlockPos().getY() + 0.5;
        this.z = be.getBlockPos().getZ() + 0.5;

        if (!be.isRunning()) {
            this.stop();
        }
    }
}
