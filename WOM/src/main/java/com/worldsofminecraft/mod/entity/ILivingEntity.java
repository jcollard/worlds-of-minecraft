package com.worldsofminecraft.mod.entity;

import com.worldsofminecraft.mod.util.Volatile;
import com.worldsofminecraft.mod.util.math.Vector3d;

import net.minecraft.entity.LivingEntity;

public interface ILivingEntity {

	double getEyeY();
	Vector3d getPosition();
	Vector3d getForward();
	
	
	void showMessage(String message);
	
	@Volatile
	LivingEntity getModel();
	
	
	
	static ILivingEntity convert(LivingEntity entity) {
		return new LivingEntityAdapter(entity);
	}

}
