package com.worldsofminecraft.mod.item.stack;

import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.UsesForge;

import net.minecraft.item.ItemStack;

public class ItemStackAdapter implements IItemStack {
	
	private final ItemStack stack;

	public ItemStackAdapter(IItem item, int amount) {
		this.stack = new ItemStack(item.construct(), amount);
	}

	@UsesForge
	public ItemStackAdapter(ItemStack itemStack) {
		this.stack = itemStack;
	}

	@Override
	@UsesForge
	public ItemStack getModel() {
		return stack;
	}

	@Override
	public int getCount() {
		return stack.getCount();
	}

	@Override
	public void setCount(int amount) {
		stack.setCount(amount);
	}

}
