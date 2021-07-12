package com.worldsofminecraft.mod.item;

import com.worldsofminecraft.mod.util.UsesForge;

import net.minecraft.item.UseAction;

public enum ItemUseAnimation{
	   NONE(UseAction.NONE),
	   EAT(UseAction.EAT),
	   DRINK(UseAction.DRINK),
	   BLOCK(UseAction.BLOCK),
	   BOW(UseAction.BOW),
	   SPEAR(UseAction.SPEAR),
	   CROSSBOW(UseAction.CROSSBOW);

	private final UseAction action;
	
	private ItemUseAnimation(UseAction action) {
		this.action = action;
	}
	
	@UsesForge
	public UseAction getUseAction() {
		return this.action;
	}
	
}