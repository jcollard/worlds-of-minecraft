package com.example.examplemod;

import java.util.Map.Entry;
import java.util.Random;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.entity.item.IItemEntity;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.item.ItemExtender;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.util.DelayedExecution;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.mod.util.math.Vector3d;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(TestMod.MODID)
public class TestMod extends QuickMod {

	public static final String MODID = "mymod";

	public Builder getBuilder() {
		String authors = "Joseph Collard";
		String modName = "Example Mod";
		MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
		builder.logoFile(PNGResource.get("assets/common/banana.png"));
		builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");

		ItemTab bananaTab = builder.createCustomTab("Bananas", PNGResource.get("assets/common/bananas.png"));
		
		QuickItem peeledBanana = new QuickItem("Peeled Banana", "assets/common/banana_peeled.png");
		peeledBanana.getProperties().tab(bananaTab);
		builder.addItem(peeledBanana);	
		
		QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
		banana.getProperties().tab(bananaTab);
		builder.addItem(banana);
		
		QuickItem bananaPeel = new QuickItem("Banana Peel", "assets/common/banana_peel.png");
		bananaPeel.getProperties().tab(bananaTab);
		builder.addItem(bananaPeel);
		
		QuickItem bananas = new QuickItem("Bananas", "assets/common/bananas.png");
		bananas.getProperties().tab(bananaTab);
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
		bananas.setUseAction(IItem.Action.EAT);
		builder.addItem(bananas);
		
		QuickItem sword = new QuickItem("My Sword", ItemModel.get(VanillaItem.IRON_SWORD));
		sword.setUseAction(IItem.Action.BLOCK);
		sword.setOnUse((context) -> {
			if(!context.world.getModel().isClientSide()) {
				return;
			}
			IForgeRegistry<Item> items = ForgeRegistries.ITEMS;
			JsonObject json = new JsonObject();
			for(Entry<RegistryKey<Item>, Item> e : items.getEntries()) {
				json.add(e.getValue().getRegistryName().toString(), new JsonPrimitive(e.getValue().getName(new ItemStack(e.getValue())).getString()));
			}
			System.out.println(Utils.getInstance().getGson().toJson(json));
		});
		builder.addItem(sword);
		
		ItemExtender customCompass = new ItemExtender("My Compass", VanillaItem.COMPASS, () -> new CompassItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));
		builder.addItem(customCompass);
		
		ItemExtender customClock = new ItemExtender("My Clock", VanillaItem.CLOCK, () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_TOOLS)));
		builder.addItem(customClock);
		
		return builder;
	}
	
	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.BUILDER.build();
	}

}
