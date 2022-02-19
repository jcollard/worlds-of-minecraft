package com.worldsofminecraft.mod.item.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

public class BlastingRecipe extends SmeltingRecipe {

    public BlastingRecipe(String recipeName, IItem result) {
        super(recipeName, result);
    }

    public BlastingRecipe(String recipeName, VanillaItem result) {
        super(recipeName, result);
    }

    @Override
    protected JsonElement getType() {
        return new JsonPrimitive("minecraft:blasting");
    }

}
