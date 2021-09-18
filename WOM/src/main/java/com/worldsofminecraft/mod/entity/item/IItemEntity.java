package com.worldsofminecraft.mod.entity.item;

import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.world.IWorld;

import net.minecraft.entity.item.ItemEntity;

public interface IItemEntity {

    static IItemEntity construct(IWorld world, double x, double y, double z) {
        return new ItemEntityAdapter(world, x, y, z);
    }

    void setPickUpDelay(int delay);

    void setItem(IItemStack itemStack);

    void setVelocity(double d, double e, double f);

    ItemEntity getModel();

}
