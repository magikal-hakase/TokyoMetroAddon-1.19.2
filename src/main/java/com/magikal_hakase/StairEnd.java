package com.magikal_hakase;

import net.minecraft.util.StringIdentifiable;

public enum StairEnd implements StringIdentifiable {
    SINGLE("single"), // 単体
    LEFT("left"),     // 左端
    RIGHT("right"),   // 右端
    MIDDLE("middle"); // 中間

    private final String name;

    StairEnd(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}