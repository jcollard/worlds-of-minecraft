package com.worldsofminecraft.resource.model.item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.texture.item.MinecraftItemTexture;

public class MinecraftItemModel extends ItemModel {

	private JsonObject model;
	private final MinecraftItemTexture texture;

	public MinecraftItemModel(MinecraftItemTexture texture) {
		super(MinecraftItemModel.getBuilder(texture));
		this.texture = texture;
	}

	public static ItemModel.Builder getBuilder(MinecraftItemTexture texture) {
		try {
			Builder b = new Builder(texture);
			b.parent(texture.generateResource(null));
			return new Builder(texture).parent(texture.generateResource(null));
		} catch (IOException e) {
			throw new IllegalStateException(
					"Unable to create model. " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public String generateResource(IMinecraftMod mod) throws IOException {
		if (model == null) {	
			try {
				//TODO(2021-07-13 jcollard): We need to be able to override the internals of the model.
				Path p = Utils.getInstance().get_1_16_AssetsDir().resolve("models/" + texture.getRegistryName() + ".json");
				Gson g = Utils.getInstance().getGson();
				String json;
				json = String.join("\n", Files.readAllLines(p));
				JsonObject data = g.fromJson(json, JsonObject.class);
				this.model = data;
			} catch (IOException e) {
				throw new IllegalStateException(
						"Unable to create model. " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
			}
		}
		
		return Utils.getInstance().getGson().toJson(model);
	}

}
