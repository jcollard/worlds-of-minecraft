package com.example.examplemod;

import com.worldsofminecraft.mod.BaseMod;
import com.worldsofminecraft.mod.MinecraftModBuilder;
import com.worldsofminecraft.mod.item.SimpleItem;
import com.worldsofminecraft.resource.png.PNGResource;

import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod extends BaseMod {

	public static final String MODID = "mymod";
	
	public MinecraftModBuilder getBuilder() {
		String authors = "Joseph Collard";
		String modName = "Example Mod";
		MinecraftModBuilder builder = new MinecraftModBuilder(authors, modName, MODID);
		builder.logoFile(PNGResource.get("assets/common/banana.png"));
		builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");
		
		SimpleItem banana = new SimpleItem("Banana of Greatness", PNGResource.get("assets/common/banana.png"));
		SimpleItem banana2 = new SimpleItem("Banana", PNGResource.get("assets/common/banana.png"));
		
		builder.addItem(banana);
		builder.addItem(banana2);
		
		return builder;
	}
	
	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.getBuilder().build();
	}
	
}
