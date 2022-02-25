package com.worldsofminecraft.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.worldsofminecraft.mod.util.Utils;

public class VanillaItemGenerator {

    public static void main(String[] args) throws IOException {
        Gson gson = Utils.getInstance()
                         .getGson();
        String json = String.join("\n", Files.readAllLines(Paths.get("assets/utils/vanilla_items.json")));

        @SuppressWarnings("unchecked")
        Map<String, String> mapping = (Map<String, String>) gson.fromJson(json, Map.class);
        List<String> entries = new LinkedList<String>();

        Map<String, Consumer<String>> exceptions = new HashMap<>();
        exceptions.put("GLOBE_BANNER_PATTERN", (registryName) -> entries.add(
                "GLOBE_BANNER_PATTERN(\"" + registryName + "\", () -> Items.GLOBE_BANNER_PATTER)"));
        // Missing!
        exceptions.put("CUT_SANDSTONE_SLAB", (registryName) -> {
        });

        for (Entry<String, String> e : mapping.entrySet()) {
            String enumName = cleanName(e.getKey());
            String registryName = cleanRegistryName(e.getKey());
            if (exceptions.containsKey(enumName)) {
                exceptions.get(enumName)
                          .accept(registryName);
                continue;
            }
            entries.add(enumName + "(\"" + registryName + "\", () -> Items." + enumName + ")");
        }
        System.out.println(String.join(",\n", entries));

    }

    private static String cleanRegistryName(String key) {
        return key.replace(":", "/")
                  .replace("minecraft", "item")
                  .trim();
    }

    private static String cleanName(String value) {
        return value.replace("minecraft:", "")
                    .trim()
                    .toUpperCase();
    }

}
