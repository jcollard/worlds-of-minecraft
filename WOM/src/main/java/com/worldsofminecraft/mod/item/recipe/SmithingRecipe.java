package com.worldsofminecraft.mod.item.recipe;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

/**
 * A SmithingRecipe is one for which there is a base item to be upgraded and an
 * upgrade ingredient that result in an upgraded item.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class SmithingRecipe implements IRecipe {

    private IItem baseItem;
    private VanillaItem baseVanillaItem;
    private IItem ingredient;
    private VanillaItem vanillaIngredient;
    private final String recipeName;
    private final IItem resultItem;
    private final VanillaItem resultVanillaItem;
    private final int count;
    private int ingredientCount = 0;

    /**
     * Given a name and a resulting item, creates a SmithingRecipe
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The number of resulting items that are created by this
     *                   recipe
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public SmithingRecipe(@Nonnull String recipeName, @Nonnull IItem result, int count) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        Preconditions.checkArgument(count > 0, "Recipe must have a count greater than 0.");
        this.recipeName = recipeName;
        this.resultItem = result;
        this.resultVanillaItem = null;
        this.count = count;
    }

    /**
     * Given a name and a resulting item, creates a SmithingRecipe
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The number of resulting items that are created by this
     *                   recipe
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public SmithingRecipe(@Nonnull String recipeName, @Nonnull VanillaItem result, int count) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        Preconditions.checkArgument(count > 0, "Recipe must have a count greater than 0.");
        this.recipeName = recipeName;
        this.resultItem = null;
        this.count = count;
        this.resultVanillaItem = result;
    }

    public SmithingRecipe setIngredient(@Nonnull IItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "This recipe may only have 1 ingredient.");
        this.ingredient = item;
        ingredientCount++;
        return this;
    }

    public SmithingRecipe setIngredient(@Nonnull VanillaItem item) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkState(this.ingredient == null && this.vanillaIngredient == null,
                "This recipe may only have 1 ingredient.");
        this.vanillaIngredient = item;
        ingredientCount++;
        return this;
    }

    public SmithingRecipe setBaseItem(@Nonnull IItem item) {
        Preconditions.checkNotNull(item, "Cannot set a null base item.");
        Preconditions.checkState(this.baseItem == null && this.vanillaIngredient == null,
                "This recipe may only have 1 base item.");
        this.baseItem = item;
        return this;
    }

    public SmithingRecipe setBaseItem(@Nonnull VanillaItem item) {
        Preconditions.checkNotNull(item, "Cannot set a null base item.");
        Preconditions.checkState(this.baseItem == null && this.vanillaIngredient == null,
                "This recipe may only have 1 base item.");
        this.baseVanillaItem = item;
        return this;
    }

    @Override
    public String generateResource() {
        JsonObject model = new JsonObject();
        model.add("type", new JsonPrimitive("minecraft:smithing"));
        model.add("base", getBase());
        model.add("addition", getAddition());
        model.add("result", getResult());
        model.add("count", new JsonPrimitive(count));
        return Utils.getInstance()
                    .getGson()
                    .toJson(model);
    }

    private JsonElement getResult() {
        JsonObject result = new JsonObject();
        if (this.resultItem != null) {
            result.add("item", new JsonPrimitive(this.resultItem.getRegistryName()));
        } else {
            result.add("item", new JsonPrimitive(this.resultVanillaItem.RECIPE_NAME));
        }
        return result;
    }

    private JsonElement getBase() {
        JsonObject base = new JsonObject();

        if (this.baseItem != null) {
            base.add("item", new JsonPrimitive(this.baseItem.getRegistryName()));
        } else {
            base.add("item", new JsonPrimitive(this.baseVanillaItem.RECIPE_NAME));
        }
        return base;
    }

    private JsonElement getAddition() {
        JsonObject addition = new JsonObject();
        if (this.ingredient != null) {
            addition.add("item", new JsonPrimitive(this.ingredient.getRegistryName()));
        } else {
            addition.add("item", new JsonPrimitive(this.vanillaIngredient.RECIPE_NAME));
        }
        return addition;
    }

    @Override
    public String getName() {
        return this.recipeName;
    }

    @Override
    public int getIngredientCount() {
        return this.ingredientCount;
    }

    @Override
    public Optional<String> getErrorMessage() {
        if (this.baseItem == null && this.baseVanillaItem == null) {
            return Optional.of(String.format("The recipe %s requires a base item before it can be added to a mod.",
                    this.recipeName));
        }
        if (this.ingredientCount > 0)
            return Optional.empty();
        return Optional.of(String.format(
                "The recipe %s requires at least 1 ingredient before it can be added to a mod.", this.recipeName));
    }

}
