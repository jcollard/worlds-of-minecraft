package org.chadwickschool.examples.recipes;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.recipe.ShapelessRecipe;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

// TODO: Don't forget to add it to a ModPack if you make a new Mod
// TODO: Don't forget to uncomment the @Mod to make sure it load.
//@Mod(ShapelessRecipeExample.MODID)
public class ShapelessRecipeExample extends QuickMod {

    public static final String MODID = "shapeless_recipes_example";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Shapeless Recipe Examples";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);

        QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
        builder.addItem(banana);

        // 1. Create a ShapelessRecipe by specifying the name of the recipe, the result
        // of the recipe, and the count of the recipe
        //
        // * The first argument is the name of the recipe (this must contain only
        // letters)
        // * The second argument is the item that will be created by this recipe
        // * The third argument is the number of items that will be created by this
        // recipe
        ShapelessRecipe appleToBanana = new ShapelessRecipe("AppleToBananaRecipe", banana, 1);

        // 2. Next, specify the ingredients for this recipe:
        // * These can be QuickItems or VanillaItems
        appleToBanana.addIngredient(VanillaItem.APPLE, 1);
        appleToBanana.addIngredient(VanillaItem.YELLOW_DYE, 1);

        // 3. Finally, add the recipe to the builder
        builder.addRecipe(appleToBanana);

        // You can also create recipes that produce Vanilla Items:
        ShapelessRecipe bananaToApple = new ShapelessRecipe("BananaToAppleRecipe", VanillaItem.APPLE, 1);
        bananaToApple.addIngredient(banana, 1);
        bananaToApple.addIngredient(VanillaItem.RED_DYE, 1);
        builder.addRecipe(bananaToApple);

        return builder;
    }

}
