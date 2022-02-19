package org.chadwickschool.examples.recipes;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.recipe.SmithingRecipe;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

// TODO: Don't forget to add it to a ModPack if you make a new Mod
// TODO: Don't forget to uncomment the @Mod to make sure it load.
//@Mod(ShapelessRecipeExample.MODID)
public class SmithingRecipeExample extends QuickMod {

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

        // You can create a smithing recipe by specifying the resulting item and count
        SmithingRecipe smithFieryAxe = new SmithingRecipe("SmithFieryAxe", fieryAxe, 1);
        // Smithing requires 1 base item
        smithFieryAxe.setBaseItem(VanillaItem.IRON_AXE);
        // Smithing requires 1 ingredient
        smithFieryAxe.setIngredient(VanillaItem.CHARCOAL);
        builder.addRecipe(smithFieryAxe);

        return builder;
    }

}
