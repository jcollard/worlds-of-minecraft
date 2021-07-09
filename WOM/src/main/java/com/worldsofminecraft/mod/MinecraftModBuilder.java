package com.worldsofminecraft.mod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

/**
 * A MinecraftModBuilder is used to specify the components which should be
 * provided with a {@link IMinecraftMod}.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.ocm>
 *
 */
public class MinecraftModBuilder {

	public final String MOD_ID;
	public final String MOD_NAME;
	public final String AUTHORS;

	private final Map<String, IItem> items = new TreeMap<>();

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
	 * @param modId   The ID of this mod
	 */
	public MinecraftModBuilder(@Nonnull String authors, @Nonnull String modName, @Nonnull String modId) {
		Preconditions.checkArgument(authors != null, "author must be non-null");
		Preconditions.checkArgument(modName != null, "modName must be non-null");
		Preconditions.checkArgument(modId != null, "MOD ID must be non-null");
		this.AUTHORS = authors.trim();
		this.MOD_ID = Utils.getInstance().validateModId(modId.trim());
		this.MOD_NAME = Utils.getInstance().validateName(modName.trim());
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

	public MinecraftModBuilder addItem(IItem item) {
		if (item.getRegistryName() == null) {
			item.setRegistryName(MOD_ID, Utils.getInstance().safeRegistryName(item.getName()));
		}
		if (items.containsKey(item.getRegistryName())) {
			throw new IllegalStateException("Unable to add the item \"" + item.getName() + "\". The registry name, \""
					+ item.getRegistryName() + "\", matched another item that was previously registered.");
		}
		items.put(item.getRegistryName(), item);
		return this;
	}

	public Map<String, IItem> getItems() {
		return Collections.unmodifiableMap(this.items);
	}

	public IMinecraftMod build() {
		if (Utils.getInstance().isLive()) {
			throw new BuildFailedException("Cannot build mod during live mode.", new IllegalStateException());
		}
		MinecraftMod mod = new MinecraftMod(this);
		generateModTOML(mod);
		generateItems(mod);
		generateLangFile(mod);
		return mod;
	}

	private void generateItems(MinecraftMod mod) {
		Utils utils = Utils.getInstance();

		for (IItem item : this.items.values()) {
			Path outfile = utils.getItemModelsDir(mod).resolve(item.getSimpleRegistryName() + ".json");
			System.out.println("Creating model file: " + outfile);
			// TODO(jcollard 7/9/2021): CREATE_NEW and blow up if file alredy exists?
			try {
				Files.write(outfile, item.getModel().generateResouce(mod).toString().getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e) {
				throw new BuildFailedException("Could not write file \"" + outfile + "\". ", e);
			}
		}
	}

	private void generateLangFile(MinecraftMod mod) {
		StringBuilder b = new StringBuilder();
		// TODO(jcollard 7/8/2021): Use JSON writier
		b.append("{\n");
		List<String> entries = new LinkedList<String>();
		for (String registryName : this.items.keySet()) {
			entries.add("  \"" + registryName + "\": \"" + items.get(registryName).getName() + "\"");
		}
		b.append(String.join(",\n", entries));
		b.append('\n');
		b.append("}\n");
		Path outfile = Utils.getInstance().getLangFileDir(mod).resolve("en_us.json");
		System.out.println("Creating language file: " + outfile);
		try {
			Files.createDirectories(Utils.getInstance().getLangFileDir(mod));
			Files.write(outfile, b.toString().getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new BuildFailedException("Could not create language file \"" + outfile + "\".", e);
		}
	}

	private void generateModTOML(MinecraftMod mod) {
		Utils utils = Utils.getInstance();
		String modsToMLContents = Utils.getInstance().getModsToML(mod);
		Path modsToML = utils.getMetaDir().resolve("mods.toml");
		try {
			Files.createDirectories(utils.getMetaDir());
			System.out.println("Creating " + modsToML);
			Files.deleteIfExists(modsToML);
			Files.write(modsToML, modsToMLContents.getBytes(), StandardOpenOption.CREATE);

			if (mod.getLogo() != null) {
				System.out
						.println("Creating logo file: " + utils.getResourcesDir().resolve(mod.getLogo().getFileName()));
				Files.copy(mod.getLogo().getPath(), utils.getResourcesDir().resolve(mod.getLogo().getFileName()),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new BuildFailedException("Could not create mods.toml.", e);
		}
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
			authors = b.AUTHORS;
			modId = b.MOD_ID;
			modName = b.MOD_NAME;
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
		public IPNGResource getLogo() {
			if (logoFile == null) {
				return null;
			}
			return logoFile;
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
