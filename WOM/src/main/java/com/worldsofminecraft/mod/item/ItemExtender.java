package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Volatile;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.VanillaItemModel;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.item.Item;

@Volatile
public class ItemExtender extends AbstractItem {

	private final Supplier<Item> itemSupplier;
	private final VanillaItem vanillaItem;

	public ItemExtender(@Nonnull String name, @Nonnull VanillaItem vanillaItem, @Nonnull Supplier<Item> supplier) {
		this(name, VanillaItemModel.get(vanillaItem), vanillaItem, supplier);
	}
	
	public ItemExtender(@Nonnull String name, @Nonnull IItemModel model, @Nonnull VanillaItem vanillaItem, Supplier<Item> itemSupplier) {
		super(name, model);
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(model != null, "model must not be null");
		Preconditions.checkArgument(vanillaItem != null, "The itemToModel must be non-null");
		this.itemSupplier = itemSupplier;
		this.vanillaItem = vanillaItem;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return itemSupplier;
	}
	
	public VanillaItem getVanillaItem() {
		return this.vanillaItem;
	}

}
