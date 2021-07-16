package com.example.examplemod;

import com.google.common.base.Strings;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickAxe;
import com.worldsofminecraft.mod.item.QuickFood;
import com.worldsofminecraft.mod.item.QuickHoe;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.QuickPickaxe;
import com.worldsofminecraft.mod.item.QuickShovel;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.SimpleItemExtender;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.potion.Effect.Type;
import com.worldsofminecraft.mod.util.DelayedExecution;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestMod extends QuickMod {

	public static final String MODID = "mymod";

	public Builder getBuilder() {
		String authors = "Joseph Collard";
		String modName = "Example Mod";
		MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
		builder.logoFile("assets/common/banana.png");
		builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");

		ItemTab bananaTab = builder.createCustomTab("Bananas", "assets/common/bananas.png");
		

		QuickItem bananaPeel = new QuickItem("Banana Peel", "assets/common/banana_peel.png");
		bananaPeel.getProperties().tab(bananaTab);
		builder.addItem(bananaPeel);
		
		QuickFood peeledBanana = new QuickFood("Peeled Banana", "assets/common/banana_peeled.png");
		peeledBanana.getProperties().tab(bananaTab);
		peeledBanana.addEffect(new Effect(Type.JUMP_BOOST).level(5).duration(20.0F));
		peeledBanana.foodPoints(3);
		peeledBanana.onConsumed = (context) -> {
			context.entity.showMessage("Mmm! Tasty!");
			context.entity.dropItemStack(context.world, IItemStack.construct(bananaPeel, 1));
			return context.itemStack;
		};
		builder.addItem(peeledBanana);	
		
		QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
		banana.getProperties().tab(bananaTab);
		banana.onUse = (context) -> {
			Utils.getInstance().getLogger().info("Hello world!");
			context.entity.showMessage("Hello world!");
			return context.itemStack;
		};
		builder.addItem(banana);
		
		
		QuickItem bananas = new QuickItem("Bananas", "assets/common/bananas.png");
		bananas.getProperties().tab(bananaTab);
		bananas.onUse = (context) -> {
			DelayedExecution execution = new DelayedExecution(() -> context.entity.dropItemStack(context.world, IItemStack.construct(banana, 1)));
			for(int i = 0; i < 8; i++) {
				execution.executeAfter(0.1 * i);
			}
			context.itemStack.setCount(context.itemStack.getCount() - 1);
			return context.itemStack;
		};
		
		bananas.setUseDuration(32);
		bananas.setUseAction(IItem.Action.EAT);
		builder.addItem(bananas);
		
		QuickSword sword = new QuickSword("My Sword", ItemModel.get(VanillaItem.IRON_SWORD), Tier.IRON);
		sword.setDamage(3);
		sword.setSpeed(-2.4F);
		builder.addItem(sword);
		
		SimpleItemExtender customCompass = new SimpleItemExtender("My Compass", VanillaItem.COMPASS);
		builder.addItem(customCompass);
		
		SimpleItemExtender talkingClock = new SimpleItemExtender("Talking Clock", VanillaItem.CLOCK);
		talkingClock.onUse = (context) -> {
			float time = context.world.getTimeOfDay();
			int minutes = ((int)((24.0 * 60.0) * time) + 12 * 60) % (24 * 60);
			int hour = minutes/60;
			String ampm = hour > 11 ? "PM" : "AM";
			if (hour > 12) {
				hour -= 12;
			} else if (hour == 0) {
				hour = 12;
			}
			String timeMessage = hour + ":" + Strings.padStart("" + minutes % 60, 2, '0') + " " + ampm;
			context.entity.showMessage("The time is " + timeMessage);
			return context.itemStack;
		};
		builder.addItem(talkingClock);
		
		QuickAxe myAxe = new QuickAxe("My Axe", ItemModel.get(VanillaItem.IRON_AXE), Tier.IRON);
		myAxe.onUse = (context) -> {
			context.entity.showMessage("Using my axe!");
			return context.itemStack;
		};
		builder.addItem(myAxe);
		QuickHoe myHoe = new QuickHoe("My Hoe", ItemModel.get(VanillaItem.IRON_HOE), Tier.IRON);
		builder.addItem(myHoe);
		QuickPickaxe myPickaxe = new QuickPickaxe("My Pickaxe", ItemModel.get(VanillaItem.IRON_PICKAXE), Tier.IRON);
		builder.addItem(myPickaxe);
		QuickShovel myShovel = new QuickShovel("My Shovel", ItemModel.get(VanillaItem.IRON_SHOVEL), Tier.IRON);
		builder.addItem(myShovel);
		
		QuickItem boatCapsul = new QuickItem("Boat Capsul", ItemModel.get(VanillaItem.ACACIA_BUTTON));
		boatCapsul.onUse = (context) -> {
			context.entity.dropItemStack(context.world, IItemStack.construct(VanillaItem.ACACIA_BOAT, 1));
			return context.itemStack;
		};
		builder.addItem(boatCapsul);
		
		return builder;
	}
	
	public static void main(String[] args) {
		TestMod mod = new TestMod();
		mod.BUILDER.build();
	}

}
