package com.worldsofminecraft.mod.item;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;

public class QuickItem extends AbstractItem {

	private Function<ItemUseContext, IItemStack> onUse;

	public QuickItem(@Nonnull String name, @Nonnull String texture) {
		this(name, PNGResource.get(texture));
	}

	public QuickItem(@Nonnull String name, @Nonnull IPNGResource texture) {
		this(name, ItemTexture.get(texture));
	}

	public QuickItem(@Nonnull String name, @Nonnull ItemTexture texture) {
		this(name, ItemModel.getBuilder(texture).build());
	}

	public QuickItem(@Nonnull String name, @Nonnull IItemModel model) {
		super(name, model);
	}

	public QuickItem setOnUse(@Nonnull Function<ItemUseContext, IItemStack> onUse) {
		Preconditions.checkArgument(onUse != null, "onUse must not be null.");
		this.onUse = onUse;
		return this;
	}

	public QuickItem setOnUse(@Nonnull Consumer<ItemUseContext> onUse) {
		Preconditions.checkArgument(onUse != null, "onUse must not be null.");
		this.setOnUse((context) -> {
			onUse.accept(context);
			return context.itemStack;
		});
		return this;
	}

	public Function<ItemUseContext, IItemStack> onUse() {
		return this.onUse;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return () -> new ItemAdapter(this);
	}

}
