package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import com.worldsofminecraft.resource.model.item.IItemModel;

public interface IItem {
	
	String getName();
	IItemModel getModel();
	String getRegistryName();
	String getSimpleRegistryName();
	void setRegistryName(@Nonnull String modID, @Nonnull String name);

}
