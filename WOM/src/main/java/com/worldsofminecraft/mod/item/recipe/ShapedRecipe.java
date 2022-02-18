package com.worldsofminecraft.mod.item.recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

/**
 * A ShapedRecipe is one for which the crafting materials must occupy a specific
 * cell within a crafting table (or crafting mechanism).
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class ShapedRecipe implements IRecipe {

    private final Map<IItem, Character> ingredientKeys = new HashMap<>();
    private final Map<VanillaItem, Character> vanillaIngredientKeys = new HashMap<>();
    private final char[] pattern = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
    private char NEXT_CHAR = 'A';
    private final String recipeName;
    private final IItem resultItem;
    private final VanillaItem resultVanillaItem;
    private final int resultCount;
    private int ingredientCount = 0;

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
    public ShapedRecipe(@Nonnull String recipeName, @Nonnull IItem result, int count) {
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
     * produce, constructs a ShapedRecipe.
     * 
     * @param recipeName The name of the recipe, this must be unique within a mod
     * @param result     The item that is crafted
     * @param count      The amount of items this recipe produces
     * 
     * @throws IllegalArgumentException If the provided recipe name contains
     *                                  anything other than letters
     */
    public ShapedRecipe(@Nonnull String recipeName, @Nonnull VanillaItem result, int count) {
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
     * Given an item ingredient, a row, and a column, adds this item to the recipe.
     * 
     * @param item The item to be used as an ingredient
     * @param row  The row (0, 1, or 2)
     * @param col  The column (0, 1, or 2)
     * 
     * @return For convenience returns this recipe
     */
    public ShapedRecipe addIngredient(@Nonnull IItem item, int row, int col) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkArgument(row >= 0 && row < 3, "Row must be 0, 1, or 2.");
        Preconditions.checkArgument(col >= 0 && col < 3, "Column must be 0, 1, or 2.");
        int index = this.getIndex(row, col);
        if (pattern[index] != ' ') {
            throw new IllegalStateException(
                    String.format("The specified position (%d, %d) has already been set.", row, col));
        }

        if (!ingredientKeys.containsKey(item)) {
            ingredientKeys.put(item, NEXT_CHAR);
            NEXT_CHAR++;
        }

        char el = ingredientKeys.get(item);
        pattern[index] = el;
        ingredientCount++;
        return this;
    }

    /**
     * Given an item ingredient, a row, and a column, adds this item to the recipe.
     * 
     * @param item The item to be used as an ingredient
     * @param row  The row (0, 1, or 2)
     * @param col  The column (0, 1, or 2)
     * 
     * @return For convenience returns this recipe
     */
    public ShapedRecipe addIngredient(@Nonnull VanillaItem item, int row, int col) {
        Preconditions.checkNotNull(item, "Cannot add a null ingredient");
        Preconditions.checkArgument(row >= 0 && row < 3, "Row must be 0, 1, or 2.");
        Preconditions.checkArgument(col >= 0 && col < 3, "Column must be 0, 1, or 2.");
        int index = this.getIndex(row, col);
        if (pattern[index] != ' ') {
            throw new IllegalStateException(
                    String.format("The specified position (%d, %d) has already been set.", row, col));
        }

        if (!vanillaIngredientKeys.containsKey(item)) {
            vanillaIngredientKeys.put(item, NEXT_CHAR);
            NEXT_CHAR++;
        }

        char el = vanillaIngredientKeys.get(item);
        pattern[index] = el;
        ingredientCount++;
        return this;
    }

    @Override
    public String generateResource() {
        JsonObject model = new JsonObject();
        model.add("type", new JsonPrimitive("minecraft:crafting_shaped"));

        model.add("pattern", getPattern());
        model.add("key", getKeys());
        model.add("result", getResult());
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
        result.add("count", new JsonPrimitive(this.resultCount));
        return result;
    }

    private JsonElement getPattern() {
        JsonArray ingredients = new JsonArray();
        ingredients.add(pattern[0] + "" + pattern[1] + "" + pattern[2]);
        ingredients.add(pattern[3] + "" + pattern[4] + "" + pattern[5]);
        ingredients.add(pattern[6] + "" + pattern[7] + "" + pattern[8]);
        return ingredients;
    }

    private JsonElement getKeys() {
        JsonObject keys = new JsonObject();
        for (Entry<IItem, Character> entry : this.ingredientKeys.entrySet()) {
            JsonObject item = new JsonObject();
            item.add("item", new JsonPrimitive(entry.getKey()
                                                    .getRegistryName()));
            keys.add(entry.getValue()
                          .toString(),
                    item);
        }

        for (Entry<VanillaItem, Character> entry : this.vanillaIngredientKeys.entrySet()) {
            JsonObject item = new JsonObject();
            item.add("item", new JsonPrimitive(entry.getKey().RECIPE_NAME));
            keys.add(entry.getValue()
                          .toString(),
                    item);
        }
        return keys;
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
