package com.magikal_hakase;

import net.minecraft.util.StringRepresentable;

public enum FencePostPart implements StringRepresentable {
    SINGLE,
    LEFT,
    RIGHT;

    @Override
    public String getSerializedName() {
        return this.toString().toLowerCase();
    }
}
