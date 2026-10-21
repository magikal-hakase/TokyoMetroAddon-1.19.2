package com.magikal_hakase;

import net.minecraft.util.StringRepresentable;

public enum BenchPart implements StringRepresentable {
    SINGLE,
    LEFT,
    MIDDLE,
    RIGHT;

    @Override
    public String getSerializedName() {
        return this.toString().toLowerCase();
    }
}
