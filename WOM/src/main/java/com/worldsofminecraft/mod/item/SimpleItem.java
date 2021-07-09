package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.ITexture;
import com.worldsofminecraft.resource.texture.ItemTexture;

public class SimpleItem implements IItem {

	private final String name;
	private final ItemTexture texture;
	private String parent;
	private String registryName;
	private String simpleRegistryName;
	
	public SimpleItem(@Nonnull String name, @Nonnull IPNGResource texture) {
		this(name, ItemTexture.get(texture));
	}
	
	public SimpleItem(@Nonnull String name, @Nonnull ItemTexture texture) {
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(texture != null, "texture must not be null");
		this.name = Utils.getInstance().validateName(name);
		this.texture = texture;
		//TODO(jcollard 7/9/2021): Implement ability to have different parent
		this.parent = "item/generated";
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ITexture getTexture() {
		return this.texture;
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
	public String getParent() {
		return this.parent;
	}

}
