package com.worldsofminecraft.mod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;

/**
 * A MinecraftModBuilder is used to specify the components which should be provided
 * with a MinecraftMod.
 *  
 * @author Joseph Collard <jcollard@chadwickschool.org>
 *
 */
public class MinecraftModBuilder {
	
	private final String modId;
	private final String modName;
	private final String authors;
	
	// TODO(jcollard 7/8/2021): Consider if these should be part of the
	// builder.
	private final String modLoader = "javafml";
	private final String loaderVersion = "[36,)";
	private final List<IModDependency> dependencies = new LinkedList<IModDependency>();
	private final String updateJSONURL = null;
	private final String issueTrackerURL = null;
	private final String displayURL = null;
	
	private String license = "All rights reserved";
	private String version = "0.0.0";
	private IPNGResource logoFile = null;
	private String credits = null;
	private String description = "TODO: Set Description in Builder";
	
	
	/**
	 * Creates a MinecraftModBuilder by specifying the authors, modName, and modId.
	 * 
	 * @param authors The authors of this mod
	 * @param modName The Name of this mod
	 * @param modId The ID of this mod
	 */
	public MinecraftModBuilder(@Nonnull String authors, @Nonnull String modName, @Nonnull String modId) {
		Preconditions.checkArgument(authors != null, "author must be non-null");
		Preconditions.checkArgument(modName != null, "modName must be non-null");
		Preconditions.checkArgument(modId != null, "modId must be non-null");
		this.authors = authors.trim();
		this.modId = modId.trim();
		this.modName = modName.trim();
	}
	
	public MinecraftModBuilder license(@Nonnull String license) {
		Preconditions.checkArgument(license != null, "license must be non-null.");
		return this;
	}
	
	public MinecraftModBuilder version(@Nonnull String version) {
		Preconditions.checkArgument(version != null, "version must be non-null.");
		this.version = version;
		return this;
	}
	
	public MinecraftModBuilder logoFile(@Nonnull IPNGResource logoFile) {
		Preconditions.checkArgument(logoFile != null, "logoFile must be non-null.");
		this.logoFile = logoFile;
		return this;
	}
	
	public MinecraftModBuilder credits(@Nonnull String credits) {
		Preconditions.checkArgument(credits != null, "Credits must be non-null.");
		this.credits = credits;
		return this;
	}
	
	public MinecraftModBuilder description(@Nonnull String description) {
		Preconditions.checkArgument(description != null);
		this.description = description;
		return this;
	}
	
	public IMinecraftMod build() {
		IMinecraftMod mod = new MinecraftMod(this);
		String modsToMLContents = Utils.getInstance().getModsToML(mod);
		Path modsToML = Utils.getInstance().getMetaDir().resolve("mods.toml");
		try {
			System.out.println("Writing " + modsToML);
			Files.deleteIfExists(modsToML);
			Files.write(modsToML, modsToMLContents.getBytes(), StandardOpenOption.CREATE);
			System.out.println("Done.");
		} catch (IOException e) {
			System.err.println("Could not create mods.toml.");
			e.printStackTrace();
			System.exit(-1);
		}
		return mod;
	}
	
	private static class MinecraftMod implements IMinecraftMod {
		
		private final String modId;
		private final String modName;
		private final String authors;
		private final String modLoader;
		private final String loaderVersion;
		private final List<IModDependency> dependencies;
		private final String updateJSONURL;
		private final String issueTrackerURL;
		private final String displayURL;
		
		private final String license;
		private final String version;
		private final IPNGResource logoFile;
		private final String credits;
		private final String description;
		
		private MinecraftMod(MinecraftModBuilder b) {
			authors = b.authors;
			modId = b.modId;
			modName = b.modName;
			modLoader = b.modLoader;
			loaderVersion = b.loaderVersion;
			dependencies = new LinkedList<>(b.dependencies);
			updateJSONURL = b.updateJSONURL;
			issueTrackerURL = b.issueTrackerURL;
			displayURL = b.displayURL;
			license = b.license;
			version = b.version;
			logoFile = b.logoFile;
			credits = b.credits;
			description = b.description;
		}

		@Override
		public String getModLoader() {
			return modLoader;
		}

		@Override
		public String getLoaderVersion() {
			return loaderVersion;
		}

		@Override
		public String getLicense() {
			return license;
		}

		@Override
		public String getModId() {
			return modId;
		}

		@Override
		public String getVersion() {
			return version;
		}

		@Override
		public String getDisplayName() {
			return modName;
		}

		@Override
		public String getUpdateJSONURL() {
			return updateJSONURL;
		}

		@Override
		public String getDisplayURL() {
			return displayURL;
		}

		@Override
		public String getLogoFile() {
			if(logoFile == null) {
				return null;
			}
			return logoFile.getFileName();
		}

		@Override
		public String getCredits() {
			return credits;
		}

		@Override
		public String getAuthors() {
			return authors;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		public List<IModDependency> getDependencies() {
			return dependencies;
		}

		@Override
		public String getIssueTrackerURL() {
			return issueTrackerURL;
		}
		
	}

}
