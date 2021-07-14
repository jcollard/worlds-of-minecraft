package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public interface IIngredient {
	
	Ingredient getModel();
	
	//TODO: Add ItemTags to allow ItemGroups
	public static IIngredient EMPTY = new Adapter(() -> Ingredient.EMPTY);

	public static IIngredient simple(VanillaItem item) {
		return new Adapter(() -> Ingredient.of(item.SUPPLIER.get()));
	}

	public static IIngredient simple(IItem item) {
		return new Adapter(() -> Ingredient.of(IItemStack.construct(item, 1).getModel()));
	}
	

	public static IIngredient simple(IItem ... items) {
		return new Adapter(() -> {
			ItemStack[] stack = new ItemStack[items.length];
			for(int i = 0; i < items.length; i++) {
				stack[i] = new ItemStack(items[i].getRegistryObject().get(), 1);
			}
			return Ingredient.of(stack);
		});
	}
	
	public static IIngredient simple(VanillaItem ... items) {
		return new Adapter(() -> {
			ItemStack[] stack = new ItemStack[items.length];
			for(int i = 0; i < items.length; i++) {
				stack[i] = new ItemStack(items[i].SUPPLIER.get(), 1);
			}
			return Ingredient.of(stack);
		});
	}

	public static IIngredient create(IItemStack... items) {
		return new Adapter(() -> {
			ItemStack[] stack = new ItemStack[items.length];
			for (int i = 0; i < items.length; i++) {
				stack[i] = items[i].getModel();
			}
			return Ingredient.of(stack);
		});
	}

	public class Adapter implements IIngredient {

		private final Supplier<Ingredient> supplier;
		private Ingredient model;

		public Adapter(@Nonnull Supplier<Ingredient> supplier) {
			Preconditions.checkNotNull(supplier, "Cannot model a null value.");
			this.supplier = supplier;
		}

		@Override
		public Ingredient getModel() {
			if (model == null) {
				model = supplier.get();
			}
			return model;
		}

	}

}
