package com.worldsofminecraft.mod.world;

import com.worldsofminecraft.mod.entity.item.IItemEntity;

import net.minecraft.world.World;

public interface IWorld {

    World getModel();

    void addItemEntity(IItemEntity entity);

    long getDayTime();

    float getTimeOfDay();

    static IWorld convert(World world) {
        return new WorldAdapter(world);
    }

}
