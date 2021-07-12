package com.example.examplemod;

import com.worldsofminecraft.mod.BaseMod;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.item.ItemTab;
import com.worldsofminecraft.mod.item.ItemUseAnimation;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.MinecraftItemTexture;

import net.minecraft.item.ItemStack;
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
		
		QuickItem bananaPeel = new QuickItem("Banana Peel", "assets/common/banana_peel.png");
		bananaPeel.setTab(bananaTab);
		builder.addItem(bananaPeel);
		
		QuickItem bananas = new QuickItem("Bananas", "assets/common/bananas.png");
		bananas.setTab(bananaTab);
		bananas.setOnUse((context) -> {
			return new ItemStack(banana.construct(), 8 * context.itemStack.getCount());
		});
		
		bananas.setUseDuration(32);
		bananas.setUseAnimation(ItemUseAnimation.EAT);
		builder.addItem(bananas);
		
		ItemModel model2 = ItemModel.getBuilder(new MinecraftItemTexture("item/iron_sword")).parent("item/iron_sword").build();
		QuickItem sword = new QuickItem("My Sword", model2);
		builder.addItem(sword);
		
		return builder;
	}
	
	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.BUILDER.build();
	}

}
