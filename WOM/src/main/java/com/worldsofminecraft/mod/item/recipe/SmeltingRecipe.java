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
    private double experience = 0.1;
    private int cookingTime = 200;

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
                "This recipe may only have 1 ingredient.");
        this.ingredient = item;
        ingredientCount++;
        return this;
    }

    public SmeltingRecipe setIngredient(@Nonnull VanillaItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "This recipe may only have 1 ingredient.");
        this.vanillaIngredient = item;
        ingredientCount++;
        return this;
    }

    /**
     * Sets the amount of experience this recipe provides. The default value is 0.1.
     * 
     * @param experience a non-negative amount of experience.
     * @return
     */
    public SmeltingRecipe setExperience(double experience) {
        Preconditions.checkArgument(experience >= 0, "Recipe experience must be greater than or equal to 0.");
        this.experience = experience;
        return this;
    }

    /**
     * Sets the amount of time this recipe takes in ticks. The default value is 200.
     * 
     * @param ticks A positive number of ticks
     * @return For convenience returns this SmeltingRecipe
     */
    public SmeltingRecipe setCookingTime(int ticks) {
        Preconditions.checkArgument(ticks > 0, "Cooking time must be at least 1 tick.");
        this.cookingTime = ticks;
        return this;
    }

    @Override
    public String generateResource() {
        JsonObject model = new JsonObject();
        model.add("type", getType());

        model.add("ingredient", getIngredient());
        model.add("experience", new JsonPrimitive(experience));
        model.add("cookingtime", new JsonPrimitive(cookingTime));
        model.add("result", getResult());
        return Utils.getInstance()
                    .getGson()
                    .toJson(model);
    }

    protected JsonElement getType() {
        return new JsonPrimitive("minecraft:smelting");
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
