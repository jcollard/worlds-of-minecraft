package com.worldsofminecraft.mod.entity;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.util.math.Vector3d;

import net.minecraft.entity.LivingEntity;

public class LivingEntityAdapter implements ILivingEntity {
	
	private final LivingEntity entity;
	
	public LivingEntityAdapter(@Nonnull LivingEntity entity) {
		this.entity = entity;
	}

	@Override
	public double getEyeY() {
		return entity.getEyeY();
	}

	@Override
	public Vector3d getPosition() {
		return Vector3d.convert(entity.getPosition(0.0f));
	}

	@Override
	public Vector3d getForward() {
		return Vector3d.convert(entity.getForward());
	}

}
