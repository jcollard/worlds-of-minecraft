package com.worldsofminecraft.mod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUseContext {
	
	public final ItemStack itemStack;
	public final World world;
	public final LivingEntity player;
	
	public ItemUseContext(ItemStack itemStack, World world, LivingEntity player) {
		this.itemStack = itemStack;
		this.world = world;
		this.player = player;
	}

}
