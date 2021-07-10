package com.worldsofminecraft.resource.model.item;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.mod.util.Utils;
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
			// TODO(2021-07-09 jcollard): parent should be `item/generated` or another
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
	public String generateResource(IMinecraftMod mod) throws IOException {

		JsonObject model = new JsonObject();
		model.add("parent", new JsonPrimitive(parent));
		
		JsonObject textures = new JsonObject();
		for (Entry<String, ItemTexture> texture : layers.entrySet()) {
			String textureRegistryName = texture.getValue().generateResource(mod);
			textures.add(texture.getKey(), new JsonPrimitive(textureRegistryName));
		}
		model.add("textures", textures);

		if(this.display != null) {
			JsonObject display = new JsonObject();
			
			for(Entry<Position, IItemTransform> transform : this.display.getTransforms().entrySet()) {
				JsonObject transforms = new JsonObject();
				
				JsonArray rotation = new JsonArray();
				rotation.add(new JsonPrimitive(transform.getValue().getRotation().X));
				rotation.add(new JsonPrimitive(transform.getValue().getRotation().Y));
				rotation.add(new JsonPrimitive(transform.getValue().getRotation().Z));
				transforms.add("rotation", rotation);
				
				JsonArray translation = new JsonArray();
				translation.add(new JsonPrimitive(transform.getValue().getTranslation().X));
				translation.add(new JsonPrimitive(transform.getValue().getTranslation().Y));
				translation.add(new JsonPrimitive(transform.getValue().getTranslation().Z));
				transforms.add("translation", translation);
				
				JsonArray scale = new JsonArray();
				scale.add(new JsonPrimitive(transform.getValue().getScale().X));
				scale.add(new JsonPrimitive(transform.getValue().getScale().Y));
				scale.add(new JsonPrimitive(transform.getValue().getScale().Z));
				transforms.add("scale", scale);
				
				display.add(transform.getKey().KEY, transforms);
			}
			model.add("display",  display);
		}
		
		return Utils.getInstance().getGson().toJson(model);
	}

}
