package com.worldsofminecraft.mod.item.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

public class CampfireRecipe extends SmeltingRecipe {

    public CampfireRecipe(String recipeName, IItem result) {
        super(recipeName, result);
    }

    public CampfireRecipe(String recipeName, VanillaItem result) {
        super(recipeName, result);
    }

    @Override
    protected JsonElement getType() {
        return new JsonPrimitive("minecraft:campfire_cooking");
    }

}
