package com.worldsofminecraft.mod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.exception.BuildFailedException;
import com.worldsofminecraft.mod.item.AbstractItem;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.tab.CustomItemTab;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Joseph Collard <jcollard@worldsofminecraft.ocm>
 *
 */

public class MinecraftMod implements IMinecraftMod {

	public static Builder getBuilder(@Nonnull String authors, @Nonnull String modName, @Nonnull String modId) {
		Preconditions.checkArgument(authors != null, "author must be non-null");
		Preconditions.checkArgument(modName != null, "modName must be non-null");
		Preconditions.checkArgument(modId != null, "MOD ID must be non-null");
		return new Builder(authors, modName, modId);
	}

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

	private MinecraftMod(Builder b) {
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

	public static class Builder {

		public final String MOD_ID;
		public final String MOD_NAME;
		public final String AUTHORS;

		private Logger LOGGER = Utils.getInstance().getLogger();

		private final Map<String, IItem> items = new TreeMap<>();
		private final Map<Path, Path> resources = new HashMap<>();

		// TODO(2021-07-08 jcollard): Consider if these should be part of the
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
		private boolean clearPreviousMod = true;
		public boolean stopOnWarnings = true;

		/**
		 * Creates a MinecraftModBuilder by specifying the authors, modName, and modId.
		 * 
		 * @param authors The authors of this mod
		 * @param modName The Name of this mod
		 * @param modId   The ID of this mod
		 */
		private Builder(@Nonnull String authors, @Nonnull String modName, @Nonnull String modId) {
			this.AUTHORS = authors.trim();
			this.MOD_ID = Utils.getInstance().validateModId(modId.trim());
			this.MOD_NAME = Utils.getInstance().validateName(modName.trim());
		}

		public Builder license(@Nonnull String license) {
			Preconditions.checkArgument(license != null, "license must be non-null.");
			return this;
		}

		public Builder version(@Nonnull String version) {
			Preconditions.checkArgument(version != null, "version must be non-null.");
			this.version = version;
			return this;
		}

		public Builder logoFile(@Nonnull String logoFile) {
			Preconditions.checkNotNull(logoFile, "logoFile must be non-null.");
			this.logoFile = PNGResource.get(logoFile);
			return this;
		}
		
		public Builder logoFile(@Nonnull IPNGResource logoFile) {
			Preconditions.checkArgument(logoFile != null, "logoFile must be non-null.");
			this.logoFile = logoFile;
			return this;
		}

		public Builder credits(@Nonnull String credits) {
			Preconditions.checkArgument(credits != null, "Credits must be non-null.");
			this.credits = credits;
			return this;
		}

		public Builder description(@Nonnull String description) {
			Preconditions.checkArgument(description != null);
			this.description = description;
			return this;
		}

		public Builder addItem(@Nonnull IItem item) {
			Preconditions.checkArgument(item != null, "Cannot add a null item to a mod. Did you initialize it?");
			String registryName = "item." + MOD_ID + "." + item.getSimpleRegistryName();
			if (items.containsKey(registryName)) {
				throw new IllegalStateException(
						"Unable to add the item \"" + item.getName() + "\". The registry name, \""
								+ registryName + "\", matched another item that was previously registered.");
			}
			items.put(registryName, item);
			return this;
		}

		public Builder clearPreviousMod(boolean clear) {
			this.clearPreviousMod = clear;
			return this;
		}

		public Builder addResource(@Nonnull Path fromPath, @Nonnull Path toPath) {
			Preconditions.checkArgument(fromPath != null);
			Preconditions.checkArgument(toPath != null);
			if (!Utils.getInstance().isLive() && !Files.exists(fromPath)) {
				throw new IllegalArgumentException("The file \"" + fromPath + "\" could not be found.");
			}
			resources.put(fromPath, toPath);
			return this;
		}

		public Map<String, IItem> getItems() {
			return Collections.unmodifiableMap(this.items);
		}

		/**
		 * A Map of custom ItemTabs for this mod. TreeMap keeps the keys sorted
		 * alphabetically for convenience when generating the lang file
		 */
		private final Map<String, CustomItemTab> tabs = new TreeMap<>();
		private final Map<String, RegistryObject<Item>> itemRegistryObject = new HashMap<>();

		/**
		 * Register all items from this builder on the specified EventBus.
		 * 
		 * @param bus
		 */
		public void registerItems(@Nonnull IEventBus bus) {
			DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
			Map<String, IItem> items = getItems();
			for (IItem item : items.values()) {
				itemRegistryObject.put(item.getSimpleRegistryName(), item.register(ITEMS));
			}
			ITEMS.register(bus);
		}

		/**
		 * A helper method for initializing a new ItemGroup.
		 * 
		 * @param registryName the registryNamefor the new ItemGroup
		 * @param suppllier    A supplier for the icon associated with the new ItemGroup
		 * @return a new ItemGroup
		 */
		private ItemGroup initCustomItemGroup(String registryName, IItem item) {
			return new ItemGroup(registryName) {

				@Override
				public ItemStack makeIcon() {
					return new ItemStack(itemRegistryObject.get(item.getSimpleRegistryName()).get());
				}
			};
		}


		public ItemTab createCustomTab(@Nonnull String label, @Nonnull String icon) {
			return createCustomTab(label, PNGResource.get(icon));
		}
		
		/**
		 * Creates a Custom Tab with the specified label and icon.
		 * 
		 * @param label the label for the new tab
		 * @param icon  the icon for the new tab
		 * @return a new ItemTab that can be used for setting the creative tab.
		 */
		public ItemTab createCustomTab(@Nonnull String label, @Nonnull IPNGResource icon) {
			QuickItem item = new QuickItem("Custom Tab Icon " + label, icon);
			item.getProperties().tab(null);
			this.addItem(item);
			return createCustomTab(label, item);
		}

		public ItemTab createCustomTab(@Nonnull String label, @Nonnull IItem item) {
			Preconditions.checkArgument(item != null, "Cannot create a Custom Tab with a null item.");
			Preconditions.checkArgument(label != null, "Cannot create a Custom Tab with a null label.");
			Preconditions.checkArgument(!this.tabs.containsKey(label),
					"The Custom Tab \"" + label + "\" was previously defined. Did you mean to use getCustomTab?");
			Utils u = Utils.getInstance();
			String registryName = MOD_ID + "_" + u.safeRegistryName(u.validateName(label));
			tabs.put(label, new CustomItemTab(label, registryName, initCustomItemGroup(registryName, item)));
			return tabs.get(label);
		}

		public ItemTab getCustomTab(@Nonnull String label) {
			Preconditions.checkArgument(this.tabs.containsKey(label),
					"The Custom Tab \"" + label + "\" is not defined. Did you mean to use createCustomTab?");
			return tabs.get(label);
		}

		public IMinecraftMod build() {
			if (Utils.getInstance().isLive()) {
				throw new BuildFailedException("Cannot build mod during live mode.", new IllegalStateException());
			}
			if(!AbstractItem.checkRegistration(this) && stopOnWarnings) {
				System.err.println("Warnings occurred while generating your mod. Press Enter to Continue.");
				Scanner s = new Scanner(System.in);
				s.nextLine();
				s.close();
			}
			MinecraftMod mod = new MinecraftMod(this);
			clearPreviousMod(mod);
			generateModTOML(mod);
			generateItems(mod);
			generateLangFile(mod);
			generateResources(mod);
			return mod;
		}

		private void generateResources(IMinecraftMod mod) {
			if (resources.isEmpty()) {
				return;
			}

			for (Entry<Path, Path> resource : resources.entrySet()) {
				try {
					LOGGER.info("Creating resource: " + resource.getValue());
					Files.copy(resource.getKey(), Utils.getInstance().getResourcesDir().resolve(resource.getValue()),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					throw new BuildFailedException("Could not copy resource from \"" + resource.getKey() + "\" to \""
							+ resource.getValue() + "\".", e);
				}
			}
		}

		private void clearPreviousMod(MinecraftMod mod) {
			if (!clearPreviousMod) {
				return;
			}
			Path modDir = Utils.getInstance().getAssetsDir(mod);
			if (!Files.exists(modDir)) {
				return;
			}

			LOGGER.info("Clearing out previous mod files: " + modDir);
			try {
				Files.walk(modDir).map(Path::toFile).forEach(File::delete);
			} catch (IOException e) {
				throw new BuildFailedException("Could not remove previous mod files.", e);
			}
		}

		private void generateItems(MinecraftMod mod) {
			Utils utils = Utils.getInstance();
			for (IItem item : this.items.values()) {
				Path outfile = utils.getItemModelsDir(mod).resolve(item.getSimpleRegistryName() + ".json");
				LOGGER.info("Creating model file: " + outfile);
				// TODO(2021-07-09 jcollard): CREATE_NEW and blow up if file alredy exists?
				try {
					Files.write(outfile, item.getItemModel().generateResource(mod).getBytes(),
							StandardOpenOption.CREATE);
				} catch (IOException e) {
					throw new BuildFailedException("Could not write file \"" + outfile + "\". ", e);
				}
			}
		}

		private void generateLangFile(MinecraftMod mod) {
			JsonObject lang = new JsonObject();

			for (CustomItemTab tab : this.tabs.values()) {
				lang.add("itemGroup." + tab.REGISTRY_NAME, new JsonPrimitive(tab.NAME));
			}

			for (String registryName : this.items.keySet()) {
				lang.add(registryName, new JsonPrimitive(items.get(registryName).getName()));
			}

			Path outfile = Utils.getInstance().getLangFileDir(mod).resolve("en_us.json");
			LOGGER.info("Creating language file: " + outfile);
			try {
				Files.createDirectories(Utils.getInstance().getLangFileDir(mod));
				Files.write(outfile, Utils.getInstance().getGson().toJson(lang).getBytes(), StandardOpenOption.CREATE);
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
				LOGGER.info("Creating " + modsToML);
				Files.deleteIfExists(modsToML);
				Files.write(modsToML, modsToMLContents.getBytes(), StandardOpenOption.CREATE);

				if (mod.getLogo() != null) {
					LOGGER.info("Creating logo file: " + utils.getResourcesDir().resolve(mod.getLogo().getFileName()));
					Files.copy(mod.getLogo().getPath(), utils.getResourcesDir().resolve(mod.getLogo().getFileName()),
							StandardCopyOption.REPLACE_EXISTING);
				}
			} catch (IOException e) {
				throw new BuildFailedException("Could not create mods.toml.", e);
			}
		}

	}

}