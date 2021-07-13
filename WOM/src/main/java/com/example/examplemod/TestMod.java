package com.example.examplemod;

import java.util.Random;

import com.worldsofminecraft.mod.BaseMod;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.entity.item.IItemEntity;
import com.worldsofminecraft.mod.item.ItemUseAnimation;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.util.DelayedExecution;
import com.worldsofminecraft.mod.util.math.Vector3d;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.MinecraftItemTexture;

import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod extends BaseMod {

	public static final String MODID = "mymod";

	public Builder getBuilder() {
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
			Vector3d vec3d = context.entity.getPosition();
			Vector3d front = context.entity.getForward();
			Random r = new Random();
			DelayedExecution execution = new DelayedExecution(() -> {
				double x = vec3d.x + front.x;
				double y = context.entity.getEyeY();
				double z = vec3d.z + front.z;
				IItemEntity entity = IItemEntity.construct(context.world, x, y, z);
				entity.setVelocity(front.x/4, r.nextDouble()/4, front.z/4);
				entity.setPickUpDelay(32);
				entity.setItem(IItemStack.construct(banana, 1));
				context.world.addItemEntity(entity);
			});
			for(int i = 0; i < 8; i++) {
				execution.executeAfter(0.1 * i);
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
