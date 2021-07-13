package com.worldsofminecraft.resource.texture.item;

import java.io.IOException;

import com.worldsofminecraft.mod.IMinecraftMod;

public class MinecraftItemTexture extends ItemTexture {
	
	private final String registryName;
	
	public MinecraftItemTexture(String registryName) {
		this.registryName = registryName;
	}

	@Override
	protected String getGenerated(IMinecraftMod mod) throws IOException {
		return this.registryName;
	}

	public String getRegistryName() {
		return this.registryName;
	}

}
