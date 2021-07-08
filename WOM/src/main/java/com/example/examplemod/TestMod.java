package com.example.examplemod;

import com.worldsofminecraft.mod.MinecraftModBuilder;

import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod extends ExampleMod {

	public static final String MODID = "examplemod";
	
	public static void main(String[] args) {
		MinecraftModBuilder builder = new MinecraftModBuilder("Joseph Collard", "Example Mod", MODID);
		builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");
		builder.build();
	}
	
}
