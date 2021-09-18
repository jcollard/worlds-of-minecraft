package com.worldsofminecraft.mod.item.tab;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import net.minecraft.item.ItemGroup;

public class ItemTab {

    public static final ItemTab BREWING = new ItemTab(ItemGroup.TAB_BREWING);
    public static final ItemTab BUILDING_BLOCKS = new ItemTab(ItemGroup.TAB_BUILDING_BLOCKS);
    public static final ItemTab COMBAT = new ItemTab(ItemGroup.TAB_COMBAT);
    public static final ItemTab DECORATIONS = new ItemTab(ItemGroup.TAB_DECORATIONS);
    public static final ItemTab FOOD = new ItemTab(ItemGroup.TAB_FOOD);
    public static final ItemTab HOTBAR = new ItemTab(ItemGroup.TAB_HOTBAR);
    public static final ItemTab INVENTORY = new ItemTab(ItemGroup.TAB_INVENTORY);
    public static final ItemTab MATERIALS = new ItemTab(ItemGroup.TAB_MATERIALS);
    public static final ItemTab MISC = new ItemTab(ItemGroup.TAB_MISC);
    public static final ItemTab REDSTONE = new ItemTab(ItemGroup.TAB_REDSTONE);
    public static final ItemTab SEARCH = new ItemTab(ItemGroup.TAB_SEARCH);
    public static final ItemTab TOOLS = new ItemTab(ItemGroup.TAB_TOOLS);
    public static final ItemTab TRANSPORTATION = new ItemTab(ItemGroup.TAB_TRANSPORTATION);

    private final ItemGroup itemGroup;

    public ItemTab(@Nonnull ItemGroup tab) {
        Preconditions.checkArgument(tab != null, "ItemGroup must be non-null.");
        this.itemGroup = tab;
    }

    public ItemGroup getItemGroup() {
        return this.itemGroup;
    }

}
