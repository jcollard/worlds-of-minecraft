package com.worldsofminecraft.mod.item.util.functional;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.world.IWorld;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUseContext {

	public final IItemStack itemStack;
	public final IWorld world;
	public final ILivingEntity entity;
	public final Supplier<IItemStack> defaultAction;

	public ItemUseContext(IItemStack itemStack, IWorld world, ILivingEntity entity, @Nonnull Supplier<IItemStack> defaultAction) {
		this.itemStack = itemStack;
		this.world = world;
		this.entity = entity;
		this.defaultAction = defaultAction;
	}

	public ItemUseContext(ItemStack itemStack, World world, LivingEntity entity, @Nonnull Supplier<IItemStack> defaultAction) {
		this.itemStack = IItemStack.convert(itemStack);
		this.world = IWorld.convert(world);
		this.entity = ILivingEntity.convert(entity);
		this.defaultAction = defaultAction;
	}

}
