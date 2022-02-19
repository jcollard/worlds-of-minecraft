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
 * A StoneCuttingRecipe is one for which there is a single ingredient and a
 * single output within a stone cutter.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class StoneCuttingRecipe implements IRecipe {

    private IItem ingredient;
    private VanillaItem vanillaIngredient;
    private final String recipeName;
    private final IItem resultItem;
    private final VanillaItem resultVanillaItem;
    private final int count;
    private int ingredientCount = 0;

    /**
     * Given a name and a resulting item, creates a StoneCuttingRecipe
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The number of resulting items that are created by this
     *                   recipe
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public StoneCuttingRecipe(@Nonnull String recipeName, @Nonnull IItem result, int count) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        Preconditions.checkArgument(count > 0, "Recipe must have a count greater than 0.");
        this.recipeName = recipeName;
        this.resultItem = result;
        this.resultVanillaItem = null;
        this.count = count;
    }

    /**
     * Given a name and a resulting item, creates a StoneCuttingRecipe
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The number of resulting items that are created by this
     *                   recipe
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public StoneCuttingRecipe(@Nonnull String recipeName, @Nonnull VanillaItem result, int count) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        Preconditions.checkArgument(count > 0, "Recipe must have a count greater than 0.");
        this.recipeName = recipeName;
        this.resultItem = null;
        this.count = count;
        this.resultVanillaItem = result;
    }

    public StoneCuttingRecipe setIngredient(@Nonnull IItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "This recipe may only have 1 ingredient.");
        this.ingredient = item;
        ingredientCount++;
        return this;
    }

    public StoneCuttingRecipe setIngredient(@Nonnull VanillaItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "This recipe may only have 1 ingredient.");
        this.vanillaIngredient = item;
        ingredientCount++;
        return this;
    }

    @Override
    public String generateResource() {
        JsonObject model = new JsonObject();
        model.add("type", new JsonPrimitive("minecraft:stonecutting"));

        model.add("ingredient", getIngredient());
        model.add("result", getResult());
        model.add("count", new JsonPrimitive(count));
        return Utils.getInstance()
                    .getGson()
                    .toJson(model);
    }

    private JsonElement getResult() {
        if (this.resultItem != null) {
            return new JsonPrimitive(this.resultItem.getRegistryName());
        } else {
            return new JsonPrimitive(this.resultVanillaItem.RECIPE_NAME);
        }
    }

    private JsonElement getIngredient() {
        JsonObject ingredient = new JsonObject();
        if (this.ingredient != null) {
            ingredient.add("item", new JsonPrimitive(this.ingredient.getRegistryName()));
        } else {
            ingredient.add("item", new JsonPrimitive(this.vanillaIngredient.RECIPE_NAME));
        }
        return ingredient;
    }

    @Override
    public String getName() {
        return this.recipeName;
    }

    @Override
    public int getIngredientCount() {
        return this.ingredientCount;
    }

}
