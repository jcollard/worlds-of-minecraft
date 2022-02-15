package com.worldsofminecraft.mod.item.recipe;

import com.worldsofminecraft.mod.IMinecraftMod;

public interface IRecipe {

    String generateResource(IMinecraftMod mod);

    String getName();

    int getIngredientCount();

}
