package com.worldsofminecraft.mod.item.stack;

import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.item.ItemStack;

public interface IItemStack {

	ItemStack getModel();

	int getCount();

	void setCount(int amount);

	static IItemStack convert(ItemStack itemStack) {
		return new ItemStackAdapter(itemStack);
	}

	static IItemStack construct(IItem item, int amount) {
		return new ItemStackAdapter(item, amount);
	}

	static IItemStack construct(VanillaItem vanillaItem, int amount) {
		return new ItemStackAdapter(new ItemStack(vanillaItem.SUPPLIER.get(), amount));
	}

}
