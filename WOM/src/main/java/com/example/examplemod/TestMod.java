package com.example.examplemod;

import java.nio.file.Paths;

import com.worldsofminecraft.mod.BaseMod;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.item.ItemTab;
import com.worldsofminecraft.mod.item.ItemUseContext;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemDisplay;
import com.worldsofminecraft.resource.model.item.IItemDisplay.Position;
import com.worldsofminecraft.resource.model.item.IItemTransform;
import com.worldsofminecraft.resource.model.item.ItemDisplay;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.model.item.ItemTransform;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;
import com.worldsofminecraft.resource.texture.item.MinecraftItemTexture;

import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod extends BaseMod {

	public static final String MODID = "mymod";

	public MinecraftMod.Builder getBuilder() {
		String authors = "Joseph Collard";
		String modName = "Example Mod";
		MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
		builder.logoFile(PNGResource.get("assets/common/banana.png"));
		builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");

		ItemTab bananaTab = builder.createCustomTab("Bananas", PNGResource.get("assets/common/bananas.png"));
		
		QuickItem peeledBanana = new QuickItem("Peeled Banana", "assets/common/banana_peeled.png");
		peeledBanana.setTab(bananaTab);
		builder.addItem(peeledBanana);	
		
		QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
		banana.setTab(bananaTab);
		builder.addItem(banana);
		
		ItemModel model2 = ItemModel.getBuilder(new MinecraftItemTexture("item/iron_sword")).parent("item/iron_sword").build();
		QuickItem sword = new QuickItem("My Sword", model2);
		sword.setOnUse(TestMod::onUse);
		builder.addItem(sword);
		
		builder.addResource(Paths.get("banana.png"), Paths.get("banana2.png"));
		
		return builder;
	}
	
	public static void onUse(ItemUseContext context) {
		Utils.getInstance().getLogger().debug("Testing!");
	}

	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.BUILDER.build();
	}

}
