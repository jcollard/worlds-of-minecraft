package com.worldsofminecraft.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.worldsofminecraft.mod.util.Utils;

public class MinecraftItemTextureGenerator {

	public static void main(String[] args) throws IOException {
		Gson gson = Utils.getInstance().getGson();
		String json = String.join("\n", Files.readAllLines(Paths.get("assets/utils/vanilla_items.json")));
		
		@SuppressWarnings("unchecked")
		Map<String, String> mapping = (Map<String,String>)gson.fromJson(json, Map.class);
		List<String> entries = new LinkedList<String>();
		for(Entry<String, String> e : mapping.entrySet()) {
			System.out.println("public static final ItemTexture " + cleanValue(e.getKey()) + " = new MinecraftItemTexture(\"" + cleanKey(e.getKey()) + "\");");
		}
		System.out.println(String.join("\n", entries));

	}

	private static String cleanKey(String key) {
		return key.replace(":", "/").replace("minecraft",  "item").trim();
	}

	private static String cleanValue(String value) {
		return value.replace("minecraft:", "").trim().toUpperCase();
	}

}
