package com.worldsofminecraft.mod.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.worldsofminecraft.mod.IMinecraftMod;

public class Utils {
	
	private static final Utils INSTANCE = new Utils();
	
	private String rootDir = ".";
	private boolean live = false;
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private final Logger logger = LogManager.getLogger();
	
	
	public static Utils getInstance() {
		return INSTANCE;
	}
	
	private Utils() {}

	/**
	 * Generates the contents of a mods.toml file for the specified mod
	 * @param mod The mod to be generated
	 * @return a string containing the contents of the mods.toml
	 */
	public String getModsToML(@Nonnull IMinecraftMod mod) {
		Preconditions.checkArgument(mod != null);
		Map<String, String> mapping = new HashMap<>();
		mapping.put("modLoader", mod.getModLoader());
		mapping.put("loaderVersion", mod.getLoaderVersion());
		mapping.put("license", mod.getLicense());
		mapping.put("issueTrackerURL", mod.getIssueTrackerURL());
		mapping.put("modId", mod.getModId());
		mapping.put("version", mod.getVersion());
		mapping.put("displayName", mod.getDisplayName());
		mapping.put("updateJSONURL", mod.getUpdateJSONURL());
		mapping.put("displayURL", mod.getDisplayURL());
		if(mod.getLogo() != null) {
			mapping.put("logoFile", mod.getLogo().getFileName());
		}
		mapping.put("credits", mod.getCredits());
		mapping.put("authors", mod.getAuthors());
		mapping.put("description", mod.getDescription());
		
		StringBuilder b = new StringBuilder();
		b.append("# This file was generated using the Worlds of Minecraft Mod Builder Library\n");
		b.append("# This file should not be edited manually as it will likely be overwritten\n");
		b.append("# during a build process.\n");
		this.appendKeys(b, mapping, "modLoader", "loaderVersion", "license", "issueTrackerURL");
		b.append("[[mods]]\n");
		this.appendKeys(b, mapping, "modId", "version", "displayName", "updateJSONURL", "displayURL", "logoFile", "credits", "authors");
		b.append("description='''\n");
		b.append(mod.getDescription());
		b.append("\n'''");
		
		//TODO(2021-07-08 jcollard): Write dependencies
		
		return b.toString();
	}
	
	private final void appendKeys(StringBuilder b, Map<String, String> mapping, String ... keys) {
		for(String key : keys) {
			String value = mapping.get(key);
			if(value == null) {
				continue;
			}
			b.append(key + "=\"" + value + "\"\n");
		}
	}
	
	public Path getRootDir() {
		return Paths.get(rootDir);
	}
	
	public void setRootDir(@Nonnull String path) {
		Preconditions.checkArgument(path != null);
		this.rootDir = path;
	}
	
	public Path getMetaDir() {
		return Paths.get(rootDir).resolve("src/main/resources/META-INF");
	}
	
	public Path getAssetsDir(@Nonnull IMinecraftMod mod) {
		Preconditions.checkArgument(mod != null);
		return Paths.get(rootDir).resolve("src/main/resources/assets/" + mod.getModId());
	}

	public Path getResourcesDir() {
		return Paths.get(rootDir).resolve("src/main/resources/");
	}
	
	public Path getItemModelsDir(@Nonnull IMinecraftMod mod) {
		Preconditions.checkArgument(mod != null);
		return getAssetsDir(mod).resolve("models/item/");
	}

	public Path getItemsTextureDir(@Nonnull IMinecraftMod mod) {
		Preconditions.checkArgument(mod != null);
		return getAssetsDir(mod).resolve("textures/items/");
	}

	public Path getLangFileDir(@Nonnull IMinecraftMod mod) {
		Preconditions.checkArgument(mod != null);
		return getAssetsDir(mod).resolve("lang/");
	}

	/**
	 * Converts a string to a registry safe name by replacing all non-safe characters with an underscore.
	 * @param string The string to convert
	 * @return The registry safe version of the string
	 */
	public String safeRegistryName(@Nonnull String name) {
		Preconditions.checkArgument(name != null);
		return name.toLowerCase().replaceAll("\\W", "_");
	}

	public String validateName(String name) {
		if(!name.matches("^[A-Za-z0-9]*[A-Za-z0-9 ][A-Za-z0-9 ]*$")) {
			throw new InvalidNameException("The name \"" + name + "\" is invalid. A name may only contain letters, numbers, and spaces.");
		}
		return name;
	}

	public String validateRegistryName(String name) {
		if(!name.matches("^[a-z][a-z0-9_]*$")) {
			throw new InvalidNameException("The registry name \"" + name + "\" is invalid. A registry name must begin with a letter and contain only lowercase letters, numbers, and underscores.");
		}
		return name;
	}

	public String validateModId(String modId) {
		if(!modId.matches("^[a-z][a-z_]*$")) {
			throw new InvalidNameException("The modId \"" + modId + "\" is invalid. A modId must begin with a letter and contain only lowercase letters and underscores.");
		}
		return modId;
	}

	public void setLive(boolean b) {
		this.live = b;
	}
	
	public boolean isLive() {
		return this.live;
	}
	
	public Gson getGson() {
		return this.gson;
	}
	
	public Logger getLogger() {
		return logger;
	}


	
	
}
