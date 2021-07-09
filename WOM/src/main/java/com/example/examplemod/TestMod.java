package com.example.examplemod;

import com.worldsofminecraft.mod.MinecraftModBuilder;
import com.worldsofminecraft.resource.png.PNGResource;

import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod extends BaseMod {

	public static final String MODID = "mymod";
	
	public MinecraftModBuilder getBuilder() {
		MinecraftModBuilder builder = new MinecraftModBuilder("Joseph Collard", "Example Mod", MODID);
		builder.logoFile(PNGResource.get("assets/banana.png"));
		builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");
		return builder;
	}
	
	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.getBuilder().build();
	}
	
}
