package com.worldsofminecraft.mod.entity.item;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.world.IWorld;

import net.minecraft.entity.item.ItemEntity;

public class ItemEntityAdapter implements IItemEntity {

	private final ItemEntity model;
	
	public ItemEntityAdapter(@Nonnull IWorld world, double x, double y, double z) {
		this.model = new ItemEntity(world.getModel(), x, y, z);
	}

	@Override
	public void setPickUpDelay(int delay) {
		this.model.setPickUpDelay(delay);
	}

	@Override
	public void setItem(IItemStack itemStack) {
		this.model.setItem(itemStack.getModel());
		
	}

	@Override
	public ItemEntity getModel() {
		return model;
	}

	@Override
	public void setVelocity(double x, double y, double z) {
		this.model.setDeltaMovement(x, y, z);
	}

}
