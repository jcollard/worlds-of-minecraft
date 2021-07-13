package com.worldsofminecraft.mod.item;

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
	
	public ItemUseContext(IItemStack itemStack, IWorld world, ILivingEntity player) {
		this.itemStack = itemStack;
		this.world = world;
		this.entity = player;
	}
	
	public ItemUseContext(ItemStack itemStack, World world, LivingEntity entity) {
		this.itemStack = IItemStack.convert(itemStack);
		this.world = IWorld.convert(world);
		this.entity = ILivingEntity.convert(entity);
	}

}
