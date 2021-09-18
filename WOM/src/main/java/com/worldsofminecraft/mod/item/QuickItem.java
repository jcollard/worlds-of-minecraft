package com.worldsofminecraft.mod.item;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.util.functional.ItemUseContext;
import com.worldsofminecraft.mod.world.IWorld;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;
import com.worldsofminecraft.resource.texture.item.VanillaItemTexture;

import net.minecraft.item.Item;

/**
 * 
 * A {@link QuickItem} is used to create simple items.
 * 
 * Example adding a simple item to a builder:
 * 
 * <pre>
 * QuickItem banana = new QuickItem("Banana", "assets/common/banana.png");
 * builder.addItem(banana);
 * </pre>
 * 
 * @author jcollard
 *
 */
public class QuickItem extends AbstractItem {

    /**
     * A helper function that specifies a function to run when the player right
     * clicks this item while held.
     */
    public Function<ItemUseContext, IItemStack> onUse;

    /**
     * Constructs a QuickItem specifying its name and a path to a texture to use.
     * 
     * @param name
     * @param texture
     */
    public QuickItem(@Nonnull String name, @Nonnull String texture) {
        this(name, PNGResource.get(texture));
    }

    /**
     * Constructs a QuickItem specifying its name and a {@link IPNGResource} to use
     * as a texture
     * 
     * @param name
     * @param texture
     */
    public QuickItem(@Nonnull String name, @Nonnull IPNGResource texture) {
        this(name, ItemTexture.get(texture));
    }

    /**
     * Constructs a QuickItem specifying its name and a {@link ItemTexture} to use
     * as a texture. This constructor is particularly useful for using
     * {@link VanillaItemTexture}s.
     * 
     * For example:
     * 
     * <pre>
     * QuickItem magicString = new QuickItem("Magic String", new VanillaItemTexture("item/string"));
     * </pre>
     * 
     * @param name
     * @param texture
     */
    public QuickItem(@Nonnull String name, @Nonnull ItemTexture texture) {
        this(name, ItemModel.getBuilder(texture)
                            .build());
    }

    /**
     * 
     * Constructs a QuickItem specifying its name and a {@link IItemModel} to use as
     * a texture. This is particularly useful if you want to applying scaling and
     * rotations.
     * 
     * @param name
     * @param model
     */
    public QuickItem(@Nonnull String name, @Nonnull IItemModel model) {
        super(name, model);
    }

    /**
     * This method is called when a player uses this item (default: right clicks
     * while holding). If the {@link QuickItem#onUse} helper field is defined, this
     * method will use it. Otherwise, this method should be implemented by a
     * subclass.
     */
    @Override
    public IItemStack onUse(IItemStack stack, IWorld world, ILivingEntity livingEntity,
            Supplier<IItemStack> defaultAction) {
        if (onUse != null) {
            return onUse.apply(new ItemUseContext(stack, world, livingEntity, defaultAction));
        }
        return super.onUse(stack, world, livingEntity, defaultAction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Supplier<Item> getItemSupplier() {
        return () -> new IItem.Adapter.Builder<Item>(Item.class).constructor(Item.Properties.class)
                                                                .args(IItem.Adapter.getProperties(this))
                                                                .build(this).MODEL;
    }

}
