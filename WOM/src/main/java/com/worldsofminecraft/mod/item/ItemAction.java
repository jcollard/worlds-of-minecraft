package com.worldsofminecraft.mod.item;

import com.worldsofminecraft.mod.util.Volatile;

import net.minecraft.item.UseAction;

public enum ItemAction{
	   NONE(UseAction.NONE),
	   EAT(UseAction.EAT),
	   DRINK(UseAction.DRINK),
	   BLOCK(UseAction.BLOCK),
	   BOW(UseAction.BOW),
	   SPEAR(UseAction.SPEAR),
	   CROSSBOW(UseAction.CROSSBOW);

	private final UseAction action;
	
	private ItemAction(UseAction action) {
		this.action = action;
	}
	
	@Volatile
	public UseAction getUseAction() {
		return this.action;
	}
	
}