package org.chadwickschool.examples;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.tab.ItemTab;

//@Mod(BananaMod.MODID)
public class BananaMod extends QuickMod {

	public static final String MODID = "bananas";

	@Override
	public Builder getBuilder() {
		// TODO: Update the author name to be you
		String authors = "Author Name";

		// TODO: Update the mod name to be Banana Mod
		String modName = "Mod Name";
		MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);

		// Sets the logo that appears on the Mods page in Minecraft
		builder.logoFile("assets/common/banana.png");
		// Sets the description that appears on this mod in the Mods page
		builder.description("This mod adds Bananas to Minecraft!");

		// TODO: Set the name of the tab to be "Bananas"
		// TODO: Set the image on the Bananas tab to be "assets/common/bananas.png"
		ItemTab bananaTab = builder.createCustomTab("Name of Tab", "Tab Image");

		// TODO: Name this item "Banana"
		// TODO: Set the image of this item to "assets/common/banana.png"
		QuickItem banana = new QuickItem("Name of Item", "Item Image");

		// This sets the properties for the banana. Specifically, this sets the banana
		// to be on the bananaTab we created above.
		banana	.getProperties()
				.tab(bananaTab);

		// This line adds the item to the game.
		builder.addItem(banana);

		// TODO: Create a "Banana Bunch" Item (assets/common/bananas.png)
		// TODO: Create a "Peeled Banana" Item (assets/common/banana_peeled.png)
		// TODO: Create a "Banana Peel" Item (assets/common/banana_peel.png)

		return builder;
	}

	public static void main(String[] args) {
		BananaMod mod = new BananaMod();
		mod.BUILDER.build();
	}

}
