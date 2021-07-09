package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import com.worldsofminecraft.resource.texture.ITexture;

public interface IItem {
	
	String getName();
	ITexture getTexture();
	String getRegistryName();
	String getSimpleRegistryName();
	void setRegistryName(@Nonnull String modID, @Nonnull String name);
	String getParent();
	

}
