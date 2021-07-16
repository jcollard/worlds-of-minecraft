package com.worldsofminecraft.mod.entity;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.entity.item.IItemEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.util.Volatile;
import com.worldsofminecraft.mod.util.math.Vector3d;
import com.worldsofminecraft.mod.world.IWorld;

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
	
	default void dropItemStack(@Nonnull IWorld world, @Nonnull IItemStack itemStack) {
		Preconditions.checkNotNull(world, "Cannot drop an ItemStack in a null world.");
		Preconditions.checkNotNull(itemStack, "Cannot drop a null ItemStack.");
		Vector3d vec3d = this.getPosition();
		Vector3d front = this.getForward();
		double x = vec3d.x + front.x;
		double y = this.getEyeY();
		double z = vec3d.z + front.z;
		IItemEntity entity = IItemEntity.construct(world, x, y, z);
		entity.setVelocity(front.x/4, front.y/4, front.z/4);
		entity.setPickUpDelay(32);
		entity.setItem(itemStack);
		world.addItemEntity(entity);
	}
	

}
