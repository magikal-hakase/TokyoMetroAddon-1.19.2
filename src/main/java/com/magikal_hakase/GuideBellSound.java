package com.magikal_hakase;

import net.minecraft.util.StringRepresentable;

public enum GuideBellSound implements StringRepresentable {
    SINE("sine"),
    SAWTOOTH("sawtooth"),
    SQUARE("square");

    private final String name;

    GuideBellSound(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    // Convenient method to get the next sound
    public GuideBellSound next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
