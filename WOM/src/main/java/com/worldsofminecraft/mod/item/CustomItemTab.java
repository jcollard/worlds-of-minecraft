package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemGroup;

public class CustomItemTab extends ItemTab {

	public final String NAME;
	public final String REGISTRY_NAME;
	
	public CustomItemTab(@Nonnull String name, @Nonnull String registryName, ItemGroup group) {
		super(group);

		this.NAME = name;
		this.REGISTRY_NAME = registryName;
	}
	
}


