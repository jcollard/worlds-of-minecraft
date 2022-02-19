package org.chadwickschool.examples.recipes;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.QuickFood;
import com.worldsofminecraft.mod.item.QuickItem;
import com.worldsofminecraft.mod.item.recipe.CampfireRecipe;
import com.worldsofminecraft.mod.item.recipe.SmeltingRecipe;

// TODO: Don't forget to add it to a ModPack if you make a new Mod
// TODO: Don't forget to uncomment the @Mod to make sure it load.
//@Mod(ShapelessRecipeExample.MODID)
public class CookingRecipeExamples extends QuickMod {

    public static final String MODID = "shapeless_recipes_example";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Recipe Examples";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);

        QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
        builder.addItem(banana);

        QuickFood cookedBanana = new QuickFood("Cooked Banana", "assets/common/banana.png");
        builder.addItem(banana);

        // You can create a campfire recipe
        CampfireRecipe cookedBananaRecipe = new CampfireRecipe("CampfireBananaRecipe", cookedBanana);
        cookedBananaRecipe.setIngredient(banana);
        cookedBananaRecipe.setExperience(0.3);
        cookedBananaRecipe.setCookingTime(200);
        builder.addRecipe(cookedBananaRecipe);

        // If you want to also be able to use a smoker, you must also add a
        // SmokingRecipe
        SmeltingRecipe smokedBananaRecipe = new SmeltingRecipe("SmokerBananaRecipe", cookedBanana);
        smokedBananaRecipe.setIngredient(banana);
        smokedBananaRecipe.setExperience(0.3);
        smokedBananaRecipe.setCookingTime(100); // You can specify a different speed when smoking
        builder.addRecipe(smokedBananaRecipe);

        return builder;
    }

}
