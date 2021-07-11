package com.worldsofminecraft.mod.item;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nonnull;

import com.worldsofminecraft.resource.model.item.IItemModel;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface IItem {
	
	String getName();
	IItemModel getModel();
	String getRegistryName();
	String getSimpleRegistryName();
	void setRegistryName(@Nonnull String modID, @Nonnull String name);
	ItemTab getTab();
	Function<IItem, Item> getItemBuilder();
	void setOnUse(Function<ItemUseContext, ActionResult<ItemStack>> onUse);
	void setOnUse(Consumer<ItemUseContext> onUse);
	public Function<ItemUseContext, ActionResult<ItemStack>> onUse();

}
