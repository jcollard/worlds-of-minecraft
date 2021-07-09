package com.worldsofminecraft.resource.model.item;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.resource.model.item.IItemDisplay.Position;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

public class ItemModel implements IItemModel {

	public static class ItemModelBuilder {

		private final Map<String, ItemTexture> layers = new TreeMap<>();
		private String parent = "item/generated";
		private IItemDisplay display = null;

		private ItemModelBuilder(ItemTexture texture) {
			this.layers.put("layer0", texture);
		}

		public ItemModelBuilder parent(@Nonnull String parent) {
			Preconditions.checkArgument(parent != null);
			// TODO(jcollard 7/9/20210): parent should be `item/generated` or another
			// `item`. Ideally, we should do a check against all registered items /
			// minecraft defaults
			this.parent = parent;
			return this;
		}

		public ItemModelBuilder display(@Nonnull IItemDisplay display) {
			Preconditions.checkArgument(display != null);
			this.display = display;
			return this;
		}

		public ItemModel build() {
			return new ItemModel(this);
		}

	}

	public static ItemModelBuilder getBuilder(@Nonnull ItemTexture texture) {
		Preconditions.checkArgument(texture != null);
		return new ItemModelBuilder(texture);
	}

	private final Map<String, ItemTexture> layers;
	private final String parent;
	private final IItemDisplay display;

	private ItemModel(ItemModelBuilder b) {
		this.layers = new TreeMap<>(b.layers);
		this.parent = b.parent;
		this.display = b.display;
	}

	@Override
	public String getParent() {
		return this.parent;
	}

	@Override
	public IItemDisplay getDisplay() {
		return this.display;
	}

	@Override
	public Map<String, ItemTexture> getLayers() {
		return Collections.unmodifiableMap(this.layers);
	}

	@Override
	public String generateResouce(IMinecraftMod mod) throws IOException {

		List<String> textures = new LinkedList<String>();

		// Generate all textures
		for (Entry<String, ItemTexture> texture : layers.entrySet()) {
			String textureRegistryName = texture.getValue().generateResource(mod);
			textures.add("    \"" + texture.getKey() + "\": \"" + textureRegistryName + "\"");
		}

		// TODO(jcollard 7/9/2021): Write JSON type for items.
		StringBuilder b = new StringBuilder();
		b.append("{\n");
		b.append("  \"parent\": \"" + parent + "\",\n");

		b.append("  \"textures\": {\n");
		b.append(String.join(",\n", textures));
		b.append("\n");
		b.append("  }\n");

		if(this.display != null) {
			b.append(",  \"display\":{\n");
			for(Entry<Position, IItemTransform> transform : display.getTransforms().entrySet()) {
				Vector3D r = transform.getValue().getRotation();
				Vector3D t = transform.getValue().getTranslation();
				Vector3D s = transform.getValue().getScale();
				b.append("    \"" + transform.getKey().KEY + "\": {\n");
				b.append("      \"rotation\": [ " + r.X + ", " + r.Y + ", " + r.Z + " ],\n");
				b.append("      \"translation\": [ " + t.X + ", " + t.Y + ", " + t.Z + " ],\n");
				b.append("      \"scale\": [ " + s.X + ", " + s.Y + ", " + s.Z + " ]\n");
				b.append("    }\n");
			}
			b.append("  }\n");
		}
		
		
		b.append("}");

		return b.toString();
	}

}
