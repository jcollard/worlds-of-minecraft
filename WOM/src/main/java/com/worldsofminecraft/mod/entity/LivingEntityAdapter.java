package com.worldsofminecraft.mod.entity;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.util.math.Vector3d;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
        StringTextComponent msg = new StringTextComponent(message);
        if (entity instanceof ServerPlayerEntity) {
            entity.sendMessage(msg, entity.getUUID());
        }
    }

    @Override
    public void addEffect(Effect regeneration) {
        entity.addEffect(regeneration.toInstance());
    }

}
