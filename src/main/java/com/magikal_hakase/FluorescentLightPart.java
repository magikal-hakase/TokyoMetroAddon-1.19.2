package com.magikal_hakase;

import net.minecraft.util.StringRepresentable;

public enum FluorescentLightPart implements StringRepresentable {
    SINGLE,
    START,
    END;

    @Override
    public String getSerializedName() {
        return this.toString().toLowerCase();
    }
}
