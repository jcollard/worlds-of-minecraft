package com.worldsofminecraft.mod.item.recipe;

import java.util.Optional;

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

    /**
     * Returns an optional error message or Empty if there are no errors.
     * 
     * @return
     */
    default Optional<String> getErrorMessage() {
        if (this.getIngredientCount() > 0) {
            return Optional.empty();
        }
        return Optional.of(String.format(
                "The recipe %s requires at least 1 ingredient before it can be added to a mod.", this.getName()));

    }

}
