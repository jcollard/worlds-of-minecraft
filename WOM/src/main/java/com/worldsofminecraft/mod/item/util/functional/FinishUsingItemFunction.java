package com.worldsofminecraft.mod.item.util.functional;

import com.worldsofminecraft.util.functional.TriFunction;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@FunctionalInterface
public interface FinishUsingItemFunction extends TriFunction<ItemStack, World, LivingEntity, ItemStack>{

}
