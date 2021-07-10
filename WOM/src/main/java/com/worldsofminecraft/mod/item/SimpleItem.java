package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

public class SimpleItem implements IItem {

	private final String name;
	private final IItemModel model;
	private String registryName;
	private String simpleRegistryName;
	
	public SimpleItem(@Nonnull String name, @Nonnull IPNGResource texture) {
		this(name, ItemTexture.get(texture));
	}
	
	public SimpleItem(@Nonnull String name, @Nonnull ItemTexture texture) {
		this(name, ItemModel.getBuilder(texture).build());
	}
	
	public SimpleItem(@Nonnull String name, @Nonnull IItemModel model) {
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

}
