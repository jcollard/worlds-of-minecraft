package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;

public class ItemAdapter  {

	private final Item model;

	public ItemAdapter(@Nonnull Item model) {
		this.model = model;
	}

	public Item getModel() {
		return this.model;
	}

}
