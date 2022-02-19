package com.worldsofminecraft.mod.item.recipe;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

/**
 * A SmallShapedRecipe is one for which the crafting materials must occupy a
 * specific cell within a 2x2 crafting area.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class SmallShapedRecipe extends ShapedRecipe {

    /**
     * Given a name, item to craft, and the number of items this recipe will
     * produce, constructs a ShapedRecipe.
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The amount of items this recipe produces
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public SmallShapedRecipe(@Nonnull String recipeName, @Nonnull IItem result, int count) {
        super(recipeName, result, count);
    }

    /**
     * Given a name, item to craft, and the number of items this recipe will
     * produce, constructs a ShapedRecipe.
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The amount of items this recipe produces
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public SmallShapedRecipe(@Nonnull String recipeName, @Nonnull VanillaItem result, int count) {
        super(recipeName, result, count);
    }

    @Override
    protected int getMax() {
        return 2;
    }

    @Override
    protected int getIndex(int row, int column) {
        return row * 3 + column;
    }

}
