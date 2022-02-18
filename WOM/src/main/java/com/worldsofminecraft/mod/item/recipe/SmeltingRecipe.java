package com.worldsofminecraft.mod.item.recipe;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

/**
 * A SmeltingRecipe is one for which there is a single ingredient and a single
 * output within a furnace.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class SmeltingRecipe implements IRecipe {

    private IItem ingredient;
    private VanillaItem vanillaIngredient;
    private final String recipeName;
    private final IItem resultItem;
    private final VanillaItem resultVanillaItem;
    private int ingredientCount = 0;

    /**
     * Given a name and a resulting item, creates a SmeltingRecipe
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public SmeltingRecipe(@Nonnull String recipeName, @Nonnull IItem result) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        this.recipeName = recipeName;
        this.resultItem = result;
        this.resultVanillaItem = null;
    }

    /**
     * Given a name and a resulting item, creates a SmeltingRecipe
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public SmeltingRecipe(@Nonnull String recipeName, @Nonnull VanillaItem result) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        this.recipeName = recipeName;
        this.resultItem = null;
        this.resultVanillaItem = result;
    }

    public SmeltingRecipe setIngredient(@Nonnull IItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "A smelting recipe may only have 1 ingredient.");
        this.ingredient = item;
        ingredientCount++;
        return this;
    }

    public SmeltingRecipe setIngredient(@Nonnull VanillaItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "A smelting recipe may only have 1 ingredient.");
        this.vanillaIngredient = item;
        ingredientCount++;
        return this;
    }

    @Override
    public String generateResource() {
        JsonObject model = new JsonObject();
        model.add("type", new JsonPrimitive("minecraft:smelting"));

        model.add("ingredient", getIngredient());
        // TODO
//        model.add("experience", JsonPrimitive(experience));
//        model.add("cookingtime", JsonPrimitive(cookingTime));
        model.add("result", getResult());
        return Utils.getInstance()
                    .getGson()
                    .toJson(model);
    }

    private JsonElement getResult() {
        JsonObject result = new JsonObject();
        if (this.resultItem != null) {
            return new JsonPrimitive(this.resultItem.getRegistryName());
        } else {
            return new JsonPrimitive(this.resultVanillaItem.RECIPE_NAME);
        }
    }

    private JsonElement getIngredient() {
        return null; // TODO
    }

    @Override
    public String getName() {
        return this.recipeName;
    }

    @Override
    public int getIngredientCount() {
        return this.ingredientCount;
    }

    private int getIndex(int row, int column) {
        return row * 3 + column;
    }

}
