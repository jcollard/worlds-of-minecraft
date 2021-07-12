package com.example.examplemod;

import java.util.Random;

import com.worldsofminecraft.mod.BaseMod;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.item.ItemTab;
import com.worldsofminecraft.mod.item.ItemUseAnimation;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.MinecraftItemTexture;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
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
			Vector3d vec3d = context.player.getPosition(0.0f);
			Vector3d front = context.player.getForward();
			Random r = new Random();
			for(int i = 0; i < 8; i++) {
				final long delay = 100 * i;
				Thread t = new Thread( new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
						}
						double x = vec3d.x + front.x*2 + (2 * r.nextDouble() - 1);
						double y = context.player.getEyeY() + (r.nextDouble()/2);
						double z = vec3d.z + front.z*2 + (2 * r.nextDouble() - 1);
						ItemEntity entity = new ItemEntity(context.world, x, y, z);
						entity.setPickUpDelay(32);
						
						entity.setItem(new ItemStack(banana.construct(), 1));
						context.world.addFreshEntity(entity);
					}
				});
				t.start();
			}
			context.itemStack.setCount(context.itemStack.getCount() - 1);
			return context.itemStack;
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
