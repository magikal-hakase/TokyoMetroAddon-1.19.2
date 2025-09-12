package com.magikal_hakase;

import net.minecraft.util.StringIdentifiable;

public enum FluorescentLightPart implements StringIdentifiable {
    SINGLE,
    START,
    END;

    @Override
    public String asString() {
        return this.toString().toLowerCase();
    }
}
