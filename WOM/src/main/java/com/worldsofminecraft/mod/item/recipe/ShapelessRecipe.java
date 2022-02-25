package com.worldsofminecraft.mod.item.recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

/**
 * A ShaplessRecipe is one for which the crafting materials may occupy any space
 * within the crafting table (or crafting mechanism).
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class ShapelessRecipe implements IRecipe {

    private final Map<IItem, Integer> ingredients = new HashMap<>();
    private final Map<VanillaItem, Integer> vanillaIngredients = new HashMap<>();
    private final String recipeName;
    private final IItem resultItem;
    private final VanillaItem resultVanillaItem;
    private final int resultCount;
    private int ingredientCount = 0;

    /**
     * Given a name, item to craft, and the number of items this recipe will
     * produce, constructs a ShapelessRecipe.
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The amount of items this recipe produces
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public ShapelessRecipe(@Nonnull String recipeName, @Nonnull IItem result, int count) {
        Preconditions.checkArgument(recipeName != null, "Recipe name must not be null.");
        Preconditions.checkArgument(result != null, "Cannot create a recipe for a null item.");
        for (char ch : recipeName.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                throw new IllegalArgumentException(
                        String.format("Illegal recipe name detected '%s'. A recipe name may only contain letters."));
            }
        }
        this.recipeName = recipeName;
        this.resultCount = count;
        this.resultItem = result;
        this.resultVanillaItem = null;
    }

    /**
     * Given a name, item to craft, and the number of items this recipe will
     * produce, constructs a ShapelessRecipe.
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The amount of items this recipe produces
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public ShapelessRecipe(@Nonnull String recipeName, @Nonnull VanillaItem result, int count) {
        for (char ch : recipeName.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                throw new IllegalArgumentException(
                        String.format("Illegal recipe name detected '%s'. A recipe name may only contain letters."));
            }
        }
        this.recipeName = recipeName;
        this.resultCount = count;
        this.resultItem = null;
        this.resultVanillaItem = result;
    }

    /**
     * Given an ingredient and a count specifying how many of that ingredient are
     * required, adds the ingredient to this recipe.
     * 
     * @param item  The item to be used as an ingredient
     * @param count The number of that item that is required
     * 
     * @throws IllegalStateException    if the item is already an ingredient or if
     *                                  the total count of this recipe is greater
     *                                  than 9
     * @throws IllegalArgumentException if the count is not a positive number
     * 
     * @return For convenience, returns this Builder
     */
    public ShapelessRecipe addIngredient(@Nonnull IItem item, int count) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        if (ingredients.containsKey(item)) {
            throw new IllegalStateException(
                    String.format("The ingredient '%s' was added multiple times to the recipe '%s'",
                            item.getSimpleRegistryName(), this.recipeName));
        }

        if (count < 0) {
            throw new IllegalArgumentException(String.format(
                    "The recipe '%s' could not be created because the ingredient '%s' must have a count of at least one but was %d",
                    this.recipeName, item.getSimpleRegistryName(), count));
        }

        if (this.ingredientCount >= 9) {
            throw new IllegalStateException(String.format(
                    "The recipe '%s' could not be created because it has too many ingredients types. The maximum number of ingredients is 9.",
                    this.recipeName));
        }
        ingredients.put(item, count);
        ingredientCount++;
        return this;
    }

    /**
     * Given an ingredient and a count specifying how many of that ingredient are
     * required, adds the ingredient to this recipe.
     * 
     * @param item  The item to be used as an ingredient
     * @param count The number of that item that is required
     * 
     * @throws IllegalStateException    if the item is already an ingredient or if
     *                                  the total count of this recipe is greater
     *                                  than 9
     * @throws IllegalArgumentException if the count is not a positive number
     * 
     * @return For convenience, returns this Builder
     */
    public ShapelessRecipe addIngredient(@Nonnull VanillaItem item, int count) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        if (vanillaIngredients.containsKey(item)) {
            throw new IllegalStateException(
                    String.format("The ingredient '%s' was added multiple times to the recipe '%s'", item.REGISTRY_NAME,
                            this.recipeName));
        }

        if (count < 0) {
            throw new IllegalArgumentException(String.format(
                    "The recipe '%s' could not be created because the ingredient '%s' must have a count of at least one but was %d",
                    this.recipeName, item.REGISTRY_NAME, count));
        }

        if (this.ingredientCount >= 9) {
            throw new IllegalStateException(String.format(
                    "The recipe '%s' could not be created because it has too many ingredients types. The maximum number of ingredients is 9.",
                    this.recipeName));
        }
        vanillaIngredients.put(item, count);
        ingredientCount++;
        return this;
    }

    @Override
    public String generateResource() {
        JsonObject model = new JsonObject();
        model.add("type", new JsonPrimitive("minecraft:crafting_shapeless"));
        JsonArray ingredients = new JsonArray();
        for (Entry<IItem, Integer> entry : this.ingredients.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                JsonObject elem = new JsonObject();
                elem.add("item", new JsonPrimitive(entry.getKey()
                                                        .getRegistryName()));
                ingredients.add(elem);
            }
        }

        for (Entry<VanillaItem, Integer> entry : this.vanillaIngredients.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                JsonObject elem = new JsonObject();
                elem.add("item", new JsonPrimitive(entry.getKey().RECIPE_NAME));
                ingredients.add(elem);
            }
        }

        JsonObject result = new JsonObject();
        if (this.resultItem != null) {
            result.add("item", new JsonPrimitive(this.resultItem.getRegistryName()));
        } else {
            result.add("item", new JsonPrimitive(this.resultVanillaItem.RECIPE_NAME));
        }
        result.add("count", new JsonPrimitive(this.resultCount));
        model.add("ingredients", ingredients);
        model.add("result", result);
        return Utils.getInstance()
                    .getGson()
                    .toJson(model);
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
