package com.worldsofminecraft.mod.item.recipe;

public interface IRecipe {

    /**
     * Given a mod to add this recipe to, generates the JSON string for this recipe.
     * 
     * @return A JSON string containing this recipe
     */
    String generateResource();

    /**
     * Retrieves the name of this recipe. This is a convenience method for referring
     * to the recipe
     * 
     * @return The recipe's name
     */
    String getName();

    /**
     * Returns the number of slots this item requires to create.
     * 
     * @return
     */
    int getIngredientCount();

}
