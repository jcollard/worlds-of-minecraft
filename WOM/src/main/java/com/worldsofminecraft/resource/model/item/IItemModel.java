package com.worldsofminecraft.resource.model.item;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.resource.model.IModel;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

public interface IItemModel extends IModel {
	
	String getParent();
	IItemDisplay getDisplay();
	Map<String, ItemTexture> getLayers();
	String generateResouce(@Nonnull IMinecraftMod mod) throws IOException;

}
