package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemUseContext {
	
	public final Item item;
	public final World world;
	public final PlayerEntity player;
	public final Hand hand;
	public final Supplier<ActionResult<ItemStack>> defaultBehavior;
	
	public ItemUseContext(Item item, World world, PlayerEntity player, Hand hand, Supplier<ActionResult<ItemStack>> defaultBehavior) {
		this.item = item;
		this.world = world;
		this.player = player;
		this.hand = hand;
		this.defaultBehavior = defaultBehavior;
	}

}
