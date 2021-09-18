package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.exception.BuildFailedException;
import com.worldsofminecraft.mod.util.Volatile;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.item.Item;

@Volatile
public class SimpleItemExtender extends QuickItem {

    private final Supplier<Item> itemSupplier;
    private final VanillaItem vanillaItem;

    public SimpleItemExtender(@Nonnull String name, @Nonnull VanillaItem vanillaItem) {
        this(name, vanillaItem, ItemModel.get(vanillaItem));

    }

    public SimpleItemExtender(@Nonnull String name, @Nonnull VanillaItem vanillaItem, @Nonnull IItemModel model) {
        super(name, model);
        Preconditions.checkArgument(name != null, "item name must not be null.");
        Preconditions.checkArgument(model != null, "model must not be null");
        Preconditions.checkArgument(vanillaItem != null, "The itemToModel must be non-null");
        try {
            // Checks to see if the item is simple enough to use basic Item.Properties.
            vanillaItem.SUPPLIER.get()
                                .getClass()
                                .getConstructor(Item.Properties.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new BuildFailedException(
                    "Could not extend \"" + vanillaItem.toString() + "\". The item is too complex.", e);
        }
        this.itemSupplier = () -> new IItem.Adapter.Builder<Item>(vanillaItem.SUPPLIER.get()
                                                                                      .getClass()).constructor(
                                                                                              Item.Properties.class)
                                                                                                  .args(IItem.Adapter.getProperties(
                                                                                                          this))
                                                                                                  .build(this).MODEL;
        this.vanillaItem = vanillaItem;
    }

    @Override
    protected Supplier<Item> getItemSupplier() {
        return itemSupplier;
    }

    public VanillaItem getVanillaItem() {
        return this.vanillaItem;
    }

}
