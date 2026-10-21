package com.magikal_hakase;

import net.minecraft.util.StringRepresentable;

public enum StairEnd implements StringRepresentable {
    SINGLE("single"), // Single
    LEFT("left"),     // Left end
    RIGHT("right"),   // Right end
    MIDDLE("middle"); // Middle

    private final String name;

    StairEnd(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
