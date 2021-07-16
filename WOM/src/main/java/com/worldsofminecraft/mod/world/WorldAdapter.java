package com.worldsofminecraft.mod.world;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.entity.item.IItemEntity;

import net.minecraft.world.World;

public class WorldAdapter implements IWorld {

	private final World world;

	public WorldAdapter(@Nonnull World world) {
		this.world = world;
	}

	@Override
	public World getModel() {
		return world;
	}

	@Override
	public void addItemEntity(IItemEntity entity) {
		world.addFreshEntity(entity.getModel());

	}

	@Override
	public long getDayTime() {
		return world.getDayTime();
	}

	@Override
	public float getTimeOfDay() {
		return world.getTimeOfDay(0);
	}
}
