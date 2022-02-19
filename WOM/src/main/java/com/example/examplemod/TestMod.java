package com.example.examplemod;

import com.google.common.base.Strings;
import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IIngredient;
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
import com.worldsofminecraft.mod.item.recipe.BlastingRecipe;
import com.worldsofminecraft.mod.item.recipe.CampfireRecipe;
import com.worldsofminecraft.mod.item.recipe.ShapedRecipe;
import com.worldsofminecraft.mod.item.recipe.ShapelessRecipe;
import com.worldsofminecraft.mod.item.recipe.SmallShapedRecipe;
import com.worldsofminecraft.mod.item.recipe.SmeltingRecipe;
import com.worldsofminecraft.mod.item.recipe.SmithingRecipe;
import com.worldsofminecraft.mod.item.recipe.SmokingRecipe;
import com.worldsofminecraft.mod.item.recipe.StoneCuttingRecipe;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.potion.Effect.Type;
import com.worldsofminecraft.mod.potion.QuickPotion;
import com.worldsofminecraft.mod.util.DelayedExecution;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.vanilla.VanillaItem;
import com.worldsofminecraft.util.SimpleItemGenerator;

//@Mod(TestMod.MODID)
public class TestMod extends QuickMod {

    public static final String MODID = "mymod";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Example Mod";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
        builder.logoFile("assets/common/banana.png");
        builder.description("This is an example mod. Modify this line of code to change the description in Minecraft!");

        ItemTab bananaTab = builder.createCustomTab("Bananas", "assets/common/bananas.png");

        QuickItem bananaPeel = new QuickItem("Banana Peel", "assets/common/banana_peel.png");
        bananaPeel.getProperties()
                  .tab(bananaTab);
        builder.addItem(bananaPeel);

        QuickFood peeledBanana = new QuickFood("Peeled Banana", "assets/common/banana_peeled.png");
        peeledBanana.getProperties()
                    .tab(bananaTab);
        peeledBanana.addEffect(new Effect(Type.JUMP_BOOST).level(5)
                                                          .seconds(20.0F));
        peeledBanana.foodPoints(3);
        peeledBanana.onConsumed = (context) -> {
            context.entity.showMessage("Mmm! Tasty!");
            context.entity.dropItemStack(context.world, IItemStack.construct(bananaPeel, 1));
            return context.itemStack;
        };
        builder.addItem(peeledBanana);

        QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
        banana.getProperties()
              .tab(bananaTab);
        banana.onUse = (context) -> {
            Utils.getInstance()
                 .getLogger()
                 .info("Hello world!");
            context.entity.showMessage("Hello world!");
            return context.itemStack;
        };
        builder.addItem(banana);

        ShapelessRecipe bananaRecipe = new ShapelessRecipe("BananaRecipe", banana, 1);
        bananaRecipe.addIngredient(VanillaItem.APPLE, 1);
        bananaRecipe.addIngredient(VanillaItem.YELLOW_DYE, 1);
        builder.addRecipe(bananaRecipe);

        ShapelessRecipe appleRecipe = new ShapelessRecipe("AppleRecipe", VanillaItem.APPLE, 1);
        appleRecipe.addIngredient(banana, 1);
        appleRecipe.addIngredient(VanillaItem.RED_DYE, 1);
        builder.addRecipe(appleRecipe);

        QuickItem bananas = new QuickItem("Bananas", "assets/common/bananas.png");
        bananas.getProperties()
               .tab(bananaTab);
        bananas.onUse = (context) -> {
            DelayedExecution execution = new DelayedExecution(
                    () -> context.entity.dropItemStack(context.world, IItemStack.construct(banana, 1)));
            for (int i = 0; i < 8; i++) {
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

        ShapedRecipe mySwordRecipe = new ShapedRecipe("MySwordRecipe", sword, 1);
        mySwordRecipe.addIngredient(VanillaItem.IRON_INGOT, 0, 1);
        mySwordRecipe.addIngredient(VanillaItem.IRON_INGOT, 1, 1);
        mySwordRecipe.addIngredient(VanillaItem.IRON_INGOT, 1, 0);
        mySwordRecipe.addIngredient(VanillaItem.IRON_INGOT, 1, 2);
        mySwordRecipe.addIngredient(VanillaItem.STICK, 2, 1);
        builder.addRecipe(mySwordRecipe);

        SimpleItemExtender customCompass = new SimpleItemExtender("My Compass", VanillaItem.COMPASS);
        builder.addItem(customCompass);

        SimpleItemExtender talkingClock = new SimpleItemExtender("Talking Clock", VanillaItem.CLOCK);
        talkingClock.onUse = (context) -> {
            float time = context.world.getTimeOfDay();
            int minutes = ((int) ((24.0 * 60.0) * time) + 12 * 60) % (24 * 60);
            int hour = minutes / 60;
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

        QuickItem simpleItemFinder = new QuickItem("Simple Item Finder", ItemModel.get(VanillaItem.END_ROD));
        simpleItemFinder.onUse = (context) -> {
            SimpleItemGenerator.generate();
            return context.itemStack;
        };
        builder.addItem(simpleItemFinder);

        QuickPotion potatoWater = new QuickPotion("Potato Water",
                IIngredient.simple(VanillaItem.POTATO, VanillaItem.BAKED_POTATO));
        potatoWater.addEffect(new Effect(Type.MOVEMENT_SPEED).seconds(20)
                                                             .level(3))
                   .addEffect(new Effect(Type.JUMP_BOOST).seconds(20)
                                                         .level(3));
        builder.addPotion(potatoWater);

        SmeltingRecipe testRecipe = new SmeltingRecipe("SmeltingRecipe", boatCapsul);
        testRecipe.setIngredient(VanillaItem.ACACIA_BOAT);
        builder.addRecipe(testRecipe);

        BlastingRecipe blastingRecipe = new BlastingRecipe("BlastingRecipe", boatCapsul);
        blastingRecipe.setIngredient(VanillaItem.ACACIA_BOAT);
        builder.addRecipe(blastingRecipe);

        CampfireRecipe campfireRecipe = new CampfireRecipe("CampfireRecipe", boatCapsul);
        campfireRecipe.setIngredient(VanillaItem.ACACIA_BOAT);
        builder.addRecipe(campfireRecipe);

        SmokingRecipe smokingRecipe = new SmokingRecipe("SmokingRecipe", boatCapsul);
        smokingRecipe.setIngredient(VanillaItem.ACACIA_BOAT);
        builder.addRecipe(smokingRecipe);

        StoneCuttingRecipe stoneCuttingRecipe = new StoneCuttingRecipe("StoneCuttingRecipe", boatCapsul, 1);
        stoneCuttingRecipe.setIngredient(VanillaItem.ACACIA_BOAT);
        builder.addRecipe(stoneCuttingRecipe);

        SmithingRecipe smithingRecipe = new SmithingRecipe("SmithingRecipe", boatCapsul, 1);
        smithingRecipe.setBaseItem(VanillaItem.ACACIA_BOAT);
        smithingRecipe.setIngredient(VanillaItem.ACACIA_BUTTON);
        builder.addRecipe(smithingRecipe);

        SmallShapedRecipe dagger = new SmallShapedRecipe("DaggerRecipe", sword, 1);
        dagger.addIngredient(VanillaItem.IRON_INGOT, 0, 1);
        dagger.addIngredient(VanillaItem.IRON_INGOT, 1, 1);
        builder.addRecipe(dagger);

        return builder;
    }

    public static void main(String[] args) {
        TestMod mod = new TestMod();
        mod.BUILDER.build();
    }

}
