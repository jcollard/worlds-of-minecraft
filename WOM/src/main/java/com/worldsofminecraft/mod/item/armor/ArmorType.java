package com.worldsofminecraft.mod.item.armor;

import net.minecraft.inventory.EquipmentSlotType;

public enum ArmorType {
	
	FEET(EquipmentSlotType.FEET),
	LEGS(EquipmentSlotType.LEGS),
	CHEST(EquipmentSlotType.CHEST),
	HEAD(EquipmentSlotType.HEAD);
	
	
	public final EquipmentSlotType MODEL;
	
	private ArmorType(EquipmentSlotType model) {
		this.MODEL = model;
	}
}
