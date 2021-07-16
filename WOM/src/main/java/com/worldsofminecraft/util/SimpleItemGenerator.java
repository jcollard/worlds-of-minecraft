package com.worldsofminecraft.util;

import java.util.LinkedList;
import java.util.List;

import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.item.Item;

public class SimpleItemGenerator {

	public static void generate() {
		List<String> simpleItems = new LinkedList<>();
		for (VanillaItem item : VanillaItem.values()) {
			try {
				item.SUPPLIER	.get()
								.getClass()
								.getConstructor(Item.Properties.class);
				simpleItems.add("public static VanillaItem " + item.toString() + " = VanillaItem." + item + ";");
			} catch (Error | Exception e) {
				System.err.println(
						"Could not get constructor for " + item + ". " + e.getClass() + ": " + e.getLocalizedMessage());
			}
		}

		System.out.println(String.join("\n", simpleItems));
	}
}
