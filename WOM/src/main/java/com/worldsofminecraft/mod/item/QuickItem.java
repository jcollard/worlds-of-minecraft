package com.worldsofminecraft.mod.item;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;

public class QuickItem extends AbstractItem {

	private ItemTab tab = ItemTab.MISC;
	private Function<ItemUseContext, IItemStack> onUse;
	private int useDuration = 16;
	private ItemUseAnimation animation = ItemUseAnimation.EAT;

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

	public void setTab(@Nonnull ItemTab tab) {
		Preconditions.checkArgument(tab != null);
		this.tab = tab;
	}

	public void clearTab() {
		this.tab = null;
	}

	public void initProperties(Item.Properties p) {
		if (this.tab != null) {
			p.tab(tab.getItemGroup());
		}
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

	public QuickItem setUseDuration(int duration) {
		Preconditions.checkArgument(duration >= 0, "Delay must be greater than or equal to 0.");
		this.useDuration = duration;
		return this;
	}

	public int getUseDuration() {
		return useDuration;
	}

	public ItemUseAnimation getUseAnimation() {
		return this.animation;
	}

	public QuickItem setUseAnimation(@Nonnull ItemUseAnimation animation) {
		Preconditions.checkArgument(animation != null, "Cannot set to null animaiton.");
		this.animation = animation;
		return this;
	}

	public Function<ItemUseContext, IItemStack> onUse() {
		return this.onUse;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return () -> new QuickItemAdapter(this);
	}

}
