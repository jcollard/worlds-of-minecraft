package com.worldsofminecraft.resource.texture;

import java.io.IOException;

import com.worldsofminecraft.mod.IMinecraftMod;

public interface ITexture {

	String generateResource(IMinecraftMod mod) throws IOException;

}
