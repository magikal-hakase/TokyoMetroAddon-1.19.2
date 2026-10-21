package com.magikal_hakase.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SeatEntity extends Entity {
    public SeatEntity(EntityType<?> type, Level world) {
        super(type, world);
        this.noPhysics = true;
    }

    public SeatEntity(Level world, double x, double y, double z) {
        this(TokyoMetroAddonEntities.SEAT_ENTITY.get(), world);
        this.setPos(x, y, z);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
    }

    @Override
    public double getPassengersRidingOffset() {
        return -0.1;
    }
}
