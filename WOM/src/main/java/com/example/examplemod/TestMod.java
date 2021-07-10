package com.example.examplemod;

import com.worldsofminecraft.mod.BaseMod;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.item.SimpleItem;
import com.worldsofminecraft.resource.model.item.IItemDisplay;
import com.worldsofminecraft.resource.model.item.IItemDisplay.Position;
import com.worldsofminecraft.resource.model.item.IItemTransform;
import com.worldsofminecraft.resource.model.item.ItemDisplay;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.model.item.ItemTransform;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

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

		SimpleItem banana = new SimpleItem("Banana of Greatness", PNGResource.get("assets/common/banana.png"));
		IItemTransform transform = ItemTransform.getBuilder().scale(1.5f, 1.5f, 1.5f).build();
		
		IItemDisplay display = ItemDisplay.getBuilder().transform(Position.FIRSTPERSON_LEFTHAND, transform).build();
		ItemModel model = ItemModel.getBuilder(ItemTexture.get(PNGResource.get("assets/common/banana.png")))
				.display(display).build();
		SimpleItem banana2 = new SimpleItem("Banana", model);

		builder.addItem(banana);
		builder.addItem(banana2);
		
		return builder;
	}

	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.getBuilder().build();
	}

}
