package com.worldsofminecraft.resource.texture.item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.ITexture;

public class ItemTexture implements ITexture {
	
	private static final Map<String, ItemTexture> cache = new HashMap<>();

	public static ItemTexture get(@Nonnull IPNGResource png) {
		Preconditions.checkArgument(png != null);
		if(!cache.containsKey(png.getFileName())) {
			cache.put(png.getFileName(), new ItemTexture(png));
		}
		return cache.get(png.getFileName());
	}
	
	private final IPNGResource png;
	private String generated = null;
	
	private ItemTexture(@Nonnull IPNGResource png) {
		this.png = png;
	}
	

	@Override
	public String generateResource(IMinecraftMod mod) throws IOException {
		if(generated != null) {
			return generated;
		}
		
		Path textureDir = Utils.getInstance().getItemsTextureDir(mod);
		Files.createDirectories(textureDir);
		Path outfile = textureDir.resolve(png.getFileName());
		Utils.getInstance().getLogger().info("Creating item texture: " + outfile);
		Files.copy(png.getPath(), outfile, StandardCopyOption.REPLACE_EXISTING);
		this.generated = mod.getModId() + ":items/" + png.getSimpleName(); 
		return this.generated;
	}

}
