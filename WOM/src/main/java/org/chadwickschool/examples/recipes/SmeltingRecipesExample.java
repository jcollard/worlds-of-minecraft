package org.chadwickschool.examples.recipes;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.recipe.SmeltingRecipe;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

// TODO: Don't forget to add it to a ModPack if you make a new Mod
// TODO: Don't forget to uncomment the @Mod to make sure it load.
//@Mod(ShapelessRecipeExample.MODID)
public class SmeltingRecipesExample extends QuickMod {

    public static final String MODID = "shapeless_recipes_example";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Recipe Examples";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);

        String name = "Fiery Axe";
        String texture = "assets/common/fiery_axe.png";
        Tier tier = Tier.DIAMOND;
        QuickSword fieryAxe = new QuickSword(name, texture, tier);
        builder.addItem(fieryAxe);

        // You can create a smelting recipe
        SmeltingRecipe smeltFieryAxeRecipe = new SmeltingRecipe("SmeltFieryAxe", VanillaItem.IRON_INGOT);
        smeltFieryAxeRecipe.setIngredient(fieryAxe);
        smeltFieryAxeRecipe.setExperience(0.3);
        smeltFieryAxeRecipe.setCookingTime(200);
        builder.addRecipe(smeltFieryAxeRecipe);

        // If you want to also be able to use a blast furnace, you must also add a
        // BlastingRecipe
        SmeltingRecipe blastFieryAxeRecipe = new SmeltingRecipe("BlastFieryAxe", VanillaItem.IRON_INGOT);
        blastFieryAxeRecipe.setIngredient(fieryAxe);
        blastFieryAxeRecipe.setExperience(0.3);
        blastFieryAxeRecipe.setCookingTime(100); // You can specify a different speed when blasting
        builder.addRecipe(blastFieryAxeRecipe);

        return builder;
    }

}
