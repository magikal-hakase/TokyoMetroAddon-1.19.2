package com.magikal_hakase;

import net.minecraft.util.StringIdentifiable;

public enum GuideBellSound implements StringIdentifiable {
    SINE("sine"),
    SAWTOOTH("sawtooth"),
    SQUARE("square");

    private final String name;

    GuideBellSound(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    // 次のサウンドを取得する便利なメソッド
    public GuideBellSound next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}