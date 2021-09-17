package com.worldsofminecraft.mod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.worldsofminecraft.mod.exception.BuildFailedException;
import com.worldsofminecraft.mod.item.AbstractItem;
import com.worldsofminecraft.mod.potion.QuickPotion;
import com.worldsofminecraft.mod.util.Utils;

public class ModPack {

	private final Set<MinecraftMod.Builder> builders = new HashSet<>();

	private final String modLoader = "javafml";
	private final String loaderVersion = "[36,)";
	private String license = "All rights reserved";

	private Set<IMinecraftMod> mods = new HashSet<>();

	public String getModLoader() {
		return this.modLoader;
	}

	public String getLoaderVersion() {
		return this.loaderVersion;
	}

	public String getLicense() {
		return this.license;
	}

	public Set<IMinecraftMod> getMods() {
		return this.mods;
	}

	public ModPack addMod(MinecraftMod.Builder mod) {
		mod.setPackagedMode(true);
		this.builders.add(mod);
		return this;
	}

	public void build() {
		Utils utils = Utils.getInstance();

		boolean errors = !(AbstractItem.checkRegistration() && QuickPotion.checkRegistration());
		if (errors) {
			System.err.println("Warnings occurred while generating your mod. Press Enter to Continue.");
			Scanner s = new Scanner(System.in);
			s.nextLine();
			s.close();
		}

		utils	.getLogger()
				.info("Building Mod Pack...");

		for (MinecraftMod.Builder builder : builders) {
			mods.add(builder.build());
		}

		generateModTOML();

		utils	.getLogger()
				.info("Mod Pack Build complete!");

	}

	private void generateModTOML() {
		Utils utils = Utils.getInstance();

		String modsToMLContents = utils.getModToML(this);
		Path modsToML = utils	.getMetaDir()
								.resolve("mods.toml");
		try {
			Files.createDirectories(utils.getMetaDir());
			utils	.getLogger()
					.info("Creating " + modsToML);
			Files.deleteIfExists(modsToML);
			Files.write(modsToML, modsToMLContents.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new BuildFailedException("Could not create mods.toml.", e);
		}
	}

}
