package com.worldsofminecraft.resource.texture.item;

import java.io.IOException;

import com.worldsofminecraft.mod.IMinecraftMod;

public class VanillaItemTexture extends ItemTexture {

    private final String registryName;

    public VanillaItemTexture(String registryName) {
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
