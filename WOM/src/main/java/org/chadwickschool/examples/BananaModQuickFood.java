package org.chadwickschool.examples;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.item.QuickFood;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.potion.Effect;

/**
 * <h1>Banana Mod - Quick Food</h1>
 * 
 * <h2>Prerequisite</h2>
 * <p>
 * Before starting this activity, be sure you complete the {@link BananaMod}
 * activity.
 * </p>
 * 
 * <h2>Overview</h2>
 * <p>
 * In this activity, you will update your BananaMod to make your banana items
 * edible using the {@link QuickFood} class.
 * </p>
 * 
 * <h2>QuickFood</h2>
 * <p>
 * The {@link QuickFood} class allows you to quickly create an edible item by
 * simply specifying its name and texture. Additionally, the {@link QuickFood}
 * class provides methods for specifying how much hunger it restores, its
 * saturation ratio, if it is considered meat, if it can only be eaten if the
 * player is hungry, as well as if it provides any special effects when
 * consumed.
 * </p>
 * 
 * <h2>Getting Started - Copy the Starter Code</h2>
 * <p>
 * In the {@link BananaModQuickFood#challenge()} method, is a series of TODOs
 * you should copy and paste into your BananaMod before beginning the tutorial
 * below.
 * </p>
 * 
 * <p>
 * In your BananaMod, you previously used the {@link QuickItem} class to
 * construct a banana object.
 * </p>
 * 
 * <pre>
 * QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
 * </pre>
 * 
 * <p>
 * To make an edible item, you will use {@link QuickFood} instead of
 * {@link QuickItem}. Start by updating your code to match the following:
 * </p>
 * 
 * <pre>
 * QuickFood banana = new QuickFood("Banana", "assets/common/banana.png");
 * </pre>
 * 
 * <p>
 * You will likely see a red line under {@link QuickFood} which means you must
 * import it. From the top menu select "Source" and then "Organize Imports":
 * </p>
 * 
 * <p>
 * <img src="./docs/images/organize-imports.png"/>
 * </p>
 * 
 * <p>
 * Congratulations! You've made your very first food.
 * </p>
 * 
 * <p>
 * To build your mod, first save your BananaMod.java file. Then, run it as a
 * Java Application. If all goes well, you won't receive any red output in the
 * console.
 * </p>
 * 
 * <p>
 * Now, if you run the game, you will be able to eat the banana. By default,
 * {@link QuickFood} items act almost identically to apples. The only difference
 * is that they are always edible.
 * </p>
 * 
 * <h2>Customizing QuickFood</h2>
 * 
 * <p>
 * {@link QuickFood} provides the following methods that allow you to change
 * properties:
 * 
 * <ul>
 * <li>{@link QuickFood#foodPoints(int)} - Specifies the amount of hunger that
 * is restored</li>
 * <li>{@link QuickFood#isAlwaysEdible(boolean)} - Specifies if this food can be
 * eaten if the player is already full.</li>
 * <li>{@link QuickFood#isMeat(boolean)} - Specifies if this food is considered
 * meat.</li>
 * <li>{@link QuickFood#saturationRatio(float)} - Specifies how much saturation
 * this food provides.</li>
 * <li>{@link QuickFood#addEffect(Effect)} - Adds an effect that is applied when
 * eaten</li>
 * <li>{@link QuickFood#addEffect(Effect, float)} - Adds an effect that has a
 * probability of being applied when eaten</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Let's start by setting the foodPoints to {@code 2} and the saturationRatio to
 * {@code 0.3F}
 * </p>
 * 
 * <pre>
 * QuickFood banana = new QuickFood("Banana", "assets/common/banana.png");
 * banana.getProperties()
 *       .tab(bananaTab);
 * banana.foodPoints(2);
 * banana.saturationRatio(0.3F);
 * </pre>
 * 
 * <h2>Adding a Jumping Effect</h2>
 * 
 * <p>
 * Now comes the fun part! We are going to make it so the Banana provides a
 * jumping effect when consumed.
 * </p>
 * 
 * <p>
 * First, we need to declare an {@link Effect} that we will add. We'll name it
 * {@code jumpingEffect}.
 * </p>
 * 
 * <pre>
 * Effect jumpingEffect;
 * </pre>
 * 
 * <p>
 * You will likely have a red line under the keyword Effect. You will need to
 * import {@code com.worldsofminecraft.mod.potion.Effect}. <b>Note</b>: If you
 * import the wrong effect, the red line will not go away. To fix this you can
 * undo by pressing Command + Z (or Control + Z on Windows).
 * </p>
 * 
 * <img src="docs/images/import-effect.png"/>
 * 
 * <p>
 * Now that we have {@link Effect} imported we can construct a new instance and
 * specify that we want to apply a jump boost.
 * </p>
 * 
 * <pre>
 * jumpEffect = new Effect(Effect.Type.JUMP_BOOST);
 * </pre>
 * 
 * <p>
 * Next, we will specify how powerful the jump effect should be by using the
 * {@link Effect#level(int)} method. Additionally, we will specify that the
 * effect should last 20 seconds using the {@link Effect#seconds(int)} method.
 * </p>
 * 
 * <pre>
 * jumpEffect.level(4)
 *           .seconds(20);
 * </pre>
 * 
 * <p>
 * Finally, we will add the effect to our {@code banana}.
 * </p>
 * 
 * <pre>
 * banana.addEffect(jumpEffect);
 * </pre>
 * 
 * Here is the entire code segment:
 * 
 * <pre>
 * QuickFood banana = new QuickFood("Banana", "assets/common/banana.png");
 * banana.getProperties()
 *       .tab(bananaTab);
 * banana.foodPoints(2);
 * banana.saturationRatio(0.3F);
 * Effect jumpEffect;
 * jumpEffect = new Effect(Effect.Type.JUMP_BOOST);
 * jumpEffect.level(4)
 *           .seconds(20);
 * banana.addEffect(jumpEffect);
 * </pre>
 * 
 * <p>
 * Save your mod. Then run it as a Java Application. Finally, launch your mod in
 * Minecraft. If all went well, when you eat your banana, a Jump effect will be
 * applied to you for 20 seconds.
 * </p>
 * 
 * <img src="docs/images/jump-effect.png"/>
 * 
 * <h2>Challenge - Custom Food</h2>
 * 
 * <p>
 * Use the pixel editor at Piskel (https://www.piskelapp.com/p/create/sprite) to
 * create your own custom food item and add it to your BananaMod. Each food item
 * can have as many effects as you want! Try to make a food that applies at
 * least 2 different effects.
 * </p>
 * 
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class BananaModQuickFood {

    public void challenge() {
        MinecraftMod.Builder builder = MinecraftMod.getBuilder("Challenge Mod", "Joseph Colalrd", "challenge_mod");
        ItemTab bananaTab = builder.createCustomTab("Bananas", "assets/common/bananas.png");

        // You should already have this code!
        QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
        banana.getProperties()
              .tab(bananaTab);

        builder.addItem(banana);

        /** Copy below this line */

        // TODO: Change the banana item to be a QuickFood so it is edible

        // TODO: Set the banana.foodPoints property to 2

        // TODO: Create a new Effect called bananaEffect.
        // Make sure to import com.worldsofminecraft.mod.potion.Effect

        // TODO: Construct a new Effect and specify the Effect.Type to be JUMP_BOOS

        // TODO: Specify the bananaEffect to be level 4

        // TODO: Specify the bananaEffect to last 20 seconds.

        // TODO: Add the effect to the banana using the addEffect(bananaEffect) method

        /** Copy above this line */
    }

}
