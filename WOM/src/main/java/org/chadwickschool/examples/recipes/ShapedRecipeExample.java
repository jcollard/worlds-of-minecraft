package org.chadwickschool.examples.recipes;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.recipe.ShapedRecipe;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

// TODO: Don't forget to add it to a ModPack if you make a new Mod
// TODO: Don't forget to uncomment the @Mod to make sure it load.
//@Mod(ShapelessRecipeExample.MODID)
public class ShapedRecipeExample extends QuickMod {

    public static final String MODID = "shapeless_recipes_example";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Shapeless Recipe Examples";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);

        String name = "Fiery Axe";
        String texture = "assets/common/fiery_axe.png";
        Tier tier = Tier.DIAMOND;
        QuickSword fieryAxe = new QuickSword(name, texture, tier);
        builder.addItem(fieryAxe);

        // 1. Create a ShapedRecipe by specifying the name of the recipe, the result
        // of the recipe, and the count of the recipe
        //
        // * The first argument is the name of the recipe (this must contain only
        // letters)
        // * The second argument is the item that will be created by this recipe
        // * The third argument is the number of items that will be created by this
        // recipe
        ShapedRecipe fieryAxeRecipe = new ShapedRecipe("FieryAxeRecipe", fieryAxe, 1);

        // 2. Next, specify the ingredients for this recipe and their location
        // within a crafting table. row 0 and column 0 are the top left spot in
        // the crafting table.
        fieryAxeRecipe.addIngredient(VanillaItem.IRON_INGOT, 0, 0); // Top Left
        fieryAxeRecipe.addIngredient(VanillaItem.IRON_INGOT, 0, 1); // Top Middle
        fieryAxeRecipe.addIngredient(VanillaItem.IRON_INGOT, 1, 0); // Middle Left
        fieryAxeRecipe.addIngredient(VanillaItem.STICK, 1, 1); // Center
        fieryAxeRecipe.addIngredient(VanillaItem.STICK, 2, 1); // Bottom Middle
        fieryAxeRecipe.addIngredient(VanillaItem.CHARCOAL, 2, 0); // Bottom Left
        fieryAxeRecipe.addIngredient(VanillaItem.CHARCOAL, 0, 2); // Top Right

        // 3. Finally, add the recipe to the builder
        builder.addRecipe(fieryAxeRecipe);

        return builder;
    }

}
