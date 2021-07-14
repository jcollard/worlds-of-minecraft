package com.worldsofminecraft.mod.item;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.world.IWorld;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;

public class QuickItem extends AbstractItem {

	public Function<ItemUseContext, IItemStack> onUse;

	public QuickItem(@Nonnull String name, @Nonnull String texture) {
		this(name, PNGResource.get(texture));
	}

	public QuickItem(@Nonnull String name, @Nonnull IPNGResource texture) {
		this(name, ItemTexture.get(texture));
	}

	public QuickItem(@Nonnull String name, @Nonnull ItemTexture texture) {
		this(name, ItemModel.getBuilder(texture).build());
	}

	public QuickItem(@Nonnull String name, @Nonnull IItemModel model) {
		super(name, model);
	}

	@Override
	public IItemStack onUse(IItemStack stack, IWorld world, ILivingEntity livingEntity,
			Supplier<IItemStack> defaultAction) {
		if (onUse != null) {
			return onUse.apply(new ItemUseContext(stack, world, livingEntity, defaultAction));
		}
		return super.onUse(stack, world, livingEntity, defaultAction);
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return () -> new IItem.Adapter(this);
	}

}
