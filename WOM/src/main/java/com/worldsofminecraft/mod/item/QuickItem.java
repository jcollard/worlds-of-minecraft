package com.worldsofminecraft.mod.item;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class QuickItem implements IItem {

	private final String name;
	private final IItemModel model;
	private ItemTab tab = ItemTab.MISC;
	private String registryName;
	private String simpleRegistryName;
	private Supplier<Item> supplier = () -> new ItemAdapter(this);
	private Function<ItemUseContext, ActionResult<ItemStack>> onUse;
	

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
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(model != null, "model must not be null");
		this.name = Utils.getInstance().validateName(name);
		this.model = model;
	}
	

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}
	

	@Override
	public void setRegistryName(@Nonnull String modId, @Nonnull String name) {
		if(this.registryName != null) {
			throw new IllegalStateException("Registry name may only be set once.");
		}
		Preconditions.checkArgument(name != null);
		Preconditions.checkArgument(modId != null);
		name = Utils.getInstance().validateRegistryName(name);
		modId = Utils.getInstance().validateModId(modId);
		this.registryName = "item." + modId + "." + name;
		this.simpleRegistryName = name;
	}

	@Override
	public String getSimpleRegistryName() {
		return this.simpleRegistryName;
	}

	@Override
	public IItemModel getModel() {
		return this.model;
	}
	
	public void setTab(@Nonnull ItemTab tab) {
		Preconditions.checkArgument(tab != null);
		this.tab = tab;
	}
	
	public void initProperties(Item.Properties p) {
		p.tab(tab.getItemGroup());
	}

//	public Function<IItem, Item> getItemAdapter() {
//		return this.supplier;
//	}
//	
//	public void setItemBuilder(Function<IItem, Item> supplier) {
//		this.supplier = supplier;
//	}
	
	public void setOnUse(@Nonnull Function<ItemUseContext, ActionResult<ItemStack>> onUse) {
		Preconditions.checkArgument(onUse != null);
		this.onUse = onUse;
	}

	public void setOnUse(Consumer<ItemUseContext> onUse) {
		this.onUse = (context) -> {
			onUse.accept(context);
			return context.defaultBehavior.get();
		};
	}
	
	public Function<ItemUseContext, ActionResult<ItemStack>> onUse(){
		return this.onUse;
	}

	@Override
	public Supplier<Item> toItem() {
		return this.supplier;
	}

}
