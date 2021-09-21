package org.chadwickschool.examples;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.ModPack;
import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.item.QuickFood;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.item.util.functional.ItemUseContext;
import com.worldsofminecraft.mod.world.IWorld;

/**
 * 
 * <h1>Banana Mod - On Consumed Method</h1>
 * 
 * <h2>Prerequisite</h2>
 * <p>
 * Before starting this activity, be sure you complete the
 * {@link BananaModQuickFood} activity and optionally the challenge from the
 * {@link BananaMod} activity. If you did not complete the challenge, you will
 * be given the code to add a banana peel to your mod.
 * </p>
 * 
 * <h2>Overview</h2>
 * <p>
 * In this activity, you will update your Banana {@link QuickFood} item to run a
 * method after it has been consumed. The method will show a message to the
 * player and create a banana peel item in the world.
 * </p>
 * 
 * <h2>Static Method</h2>
 * <p>
 * First, you must add a static method to your BananaMod class. A static method
 * is a piece of code that you can reuse anywhere in your program. For our
 * purposes, we will be using it as a way to tell Minecraft to run extra code
 * after a Banana {@link QuickFood} has been eaten.
 * </p>
 * 
 * <p>
 * An empty definition of a static method called <@code eatBanana> is provided
 * below in the body of the BananaModOnConsumed class. Copy and paste it into
 * your BananaMod. Note, it should not go inside your {@code getBuilder} method.
 * See the picture below to get an idea of where you can place your method.
 * </p>
 * 
 * <img src="docs/images/eatbanana-method.png"/>
 * 
 * <p>
 * Make sure you have no errors in your code. If your code is looking a little
 * messy, you can ask Eclipse to format your code. By default, the hot key is
 * Command + Shift + F. You can also find it in the top menu Source > Format.
 * </p>
 * 
 * <img src="docs/images/source-format.png"/>
 * 
 * <h2>QuickFood onConsumed</h2>
 * 
 * <p>
 * To tell Minecraft to use your method when a {@link QuickFood} is eaten, you
 * must specify it using the {@link QuickFood#onConsumed} field.
 * </p>
 * 
 * <p>
 * In your {@code getBuilder} method, find your Banana code and add the
 * following:
 * </p>
 * 
 * <pre>
 * banana.onConsumed = BananaMod::eatBanana;
 * </pre>
 * 
 * <p>
 * The code above says, when a banana item is consumed run the {@code eatBanana}
 * method with that banana item.
 * </p>
 * 
 * <h2>ItemUseContext</h2>
 * 
 * <p>
 * At this point, your {@code eatBanana} method doesn't do anything special. It
 * is essentially the default action when a {@link QuickFood} is eaten. Let's
 * start by making it show a message to the player.
 * </p>
 * 
 * <p>
 * You may have noticed that there is a class called {@link ItemUseContext} as
 * part of your method. In the code you copied, this is associated with a
 * variable {@code context}. This allows us to know about the context of the
 * item being used (in this case consumed).
 * </p>
 * 
 * <p>
 * For our pursposes, we will be using {@link ItemUseContext#entity} which
 * happens to be whatever entity ate the item. This could be an animal, a mob, a
 * player, or any other number of living entities in Minecraft (did you know
 * Armor Stands are considered a living entity?).
 * </p>
 * 
 * <p>
 * You can show a message to the entity (in this case the player) who eats the
 * item by using the following code:
 * </p>
 * 
 * <pre>
 * context.entity.showMessage("Mmmm!");
 * </pre>
 * 
 * <p>
 * Copy the code above and put it inside your {@code eatBanana} method.
 * </p>
 * 
 * <p>
 * Be sure to save your code. Then, build your mod by running it as a Java
 * Application. If you made a {@link ModPack} in a previous challenge, you
 * should run your {@link ModPack} to build. Finally, if your build went well,
 * run Minecraft and eat one of your bananas.
 * </p>
 * 
 * <img src="docs/images/mmm-screenshot.png" width="600px"/>
 * 
 * <h2>IItemStack</h2>
 * 
 * <p>
 * Next, we want to drop a banana peel after a banana has been eaten. To do
 * this, we need to create an {@link IItemStack}.
 * </p>
 * 
 * <p>
 * An Item Stack is a core idea for items in Minecraft. The Item and QuickItem
 * is actually a definition of an Item and how it works in the game. An
 * ItemStack is one or more of those items in the world or inventory. You never
 * actually have an Item or QuickItem, you have an ItemStack.
 * </p>
 * 
 * <p>
 * The easiest way to make an {@link IItemStack} is to use the
 * {@link IItemStack#construct(IItem, int) method. This method can use
 * {@link QuickItem}s, {@link QuickFood}s and pretty much all World of Minecraft
 * items.
 * </p>
 * 
 * <p>
 * Let's try to create an item stack with 1 banana peel. Add the following code
 * to your {@code eatBanana} method.
 * </p>
 * 
 * <pre>
 * IItemStack bananaPeelStack = IItemStack.construct(bananaPeel, 1);
 * </pre>
 * 
 * <p>
 * You will probably need to import {@link IItemStack}.
 * </p>
 * 
 * <p>
 * You will have an error now:
 * </p>
 * 
 * <img src="docs/images/bananaPeel-error.png"/>
 * 
 * <p>
 * This error is saying that it does not have access to your {@code bananaPeel}
 * variable. Anytime you see an error that says "cannot be resolved to a
 * variable" it means that Java can't find where you defined your variable. When
 * this happens, we say that the variable is "out of scope". In this case, the
 * {@code bananaPeel} variable is only available in the {@code getBuilder}
 * method. This is because it was defined within the {@code getBuilder} method.
 * </p>
 * 
 * <p>
 * To allow the bananaPeel variable to be available in both the
 * {@code getBuilder} and {@code eatBanana} method, we must make it a
 * {@code static} variable. To do this, add the following line at the top of
 * your BananaMod class:
 * </p>
 * 
 * <pre>
 * public static QuickItem bananaPeel;
 * </pre>
 * 
 * <img src="docs/images/bananaPeel-static.png"/>
 * 
 * <p>
 * This line creates a {@code static} variable that is available anywhere within
 * the {@code BananaMod} class.
 * </p>
 * 
 * <p>
 * Next, we need to update our {@code getBuilder} method to use our static
 * bananaPeel variable. We will remove the declaration line and replace it with
 * a simple assignment:
 * </p>
 * 
 * <img src="docs/images/bananaPeel-static-assignment.png"/>
 * 
 * <p>
 * Notice the {@code bananaPeel} variable will change to be the color blue. When
 * a variable is blue, it tells us it is using a variable that is available in
 * any method. Make sure you save. If all went well, the error in your
 * {@code eatBanana} method should be gone.
 * </p>
 * 
 * <img src="docs/images/bananaPeel-stack-warning.png"/>
 * 
 * <h2>dropItemStack Method</h2>
 * 
 * <p>
 * You probably noticed there is a warning on your {@code bananaPeelStack}
 * variable. This warning is saying you created an item stack but you never did
 * anything with it. To get it into the world, we are going to have the entity
 * drop the item using the
 * {@link ILivingEntity#dropItemStack(IWorld, IItemStack) method. Add the
 * following line to your {@code eatBanana} method.
 * </p>
 * 
 * <pre>
 * context.entity.dropItemStack(context.world, bananaPeelStack);
 * </pre>
 * 
 * <p>
 * The code above instructs Minecraft to drop the ItemStack into the World at
 * the entities current location.
 * </p>
 * 
 * <p>
 * Your method should now look like this:
 * </p>
 * 
 * <img src="docs/images/eatbanana-method-finished.png"/>
 * 
 * <p>
 * Save your code. Build your mod. Then, run Minecraft. If all went well, when
 * you eat a banana, you will drop a banana peel!
 * </p>
 * 
 * <img src="docs/images/eatbanana-screenshot.png" width="600px"/>
 * 
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class BananaModOnConsumed {

    public void challenge() {
        MinecraftMod.Builder builder = MinecraftMod.getBuilder("Challenge Mod", "Joseph Colalrd", "challenge_mod");
        ItemTab bananaTab = builder.createCustomTab("Bananas", "assets/common/bananas.png");

        // You should already have this code!
        QuickFood banana = new QuickFood("Banana", "assets/common/banana.png");
        banana.getProperties()
              .tab(bananaTab);

        builder.addItem(banana);

        // If you did not complete the Banana Mod Challenge, you will need the code
        // below this line
        QuickItem bananaPeel = new QuickItem("Banana Peel", "assets/common/banana_peel.png");
        banana.getProperties()
              .tab(bananaTab);
        builder.addItem(bananaPeel);
        // If you did not complete the Banana Mod Challenge, you will need the code
        // above this line.

        /** Copy below this line */

        // TODO: Assign banana.onConsumed to BananaMod::eatBanana
        // TODO: Add the static method eatBanana to your BananaMod file

        /** Copy above this line */
    }

    /** Copy below this line */

    /**
     * This method should be called after a banana is consumed.
     * 
     * @param context the game context
     * @return the ItemStack after the banana was eaten.
     */
    public static IItemStack eatBanana(ItemUseContext context) {
        // TODO: Show the player message "Mmmmm!"
        // TODO: Update your bananaPeel to be defined in the static context
        // TODO: Create a new IItemStack that contains a single BananaPeel
        // TODO: Drop the item stack at the players feet.
        return context.itemStack;
    }

    /** Copy above this line */

}
