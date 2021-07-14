package com.worldsofminecraft.mod.entity;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.util.math.Vector3d;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.StringTextComponent;

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

	@Override
	public LivingEntity getModel() {
		return entity;
	}

	@Override
	public void showMessage(String message) {
		if(entity instanceof ClientPlayerEntity) {
			entity.sendMessage(new StringTextComponent(message), entity.getUUID());
		}
	}

}
