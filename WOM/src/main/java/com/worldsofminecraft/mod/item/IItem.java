package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.worldsofminecraft.resource.model.item.IItemModel;

import net.minecraft.item.Item;

public interface IItem {
	
	String getName();
	IItemModel getModel();
	String getRegistryName();
	String getSimpleRegistryName();
	void setRegistryName(@Nonnull String modID, @Nonnull String name);
	Supplier<Item> toItem();

}
