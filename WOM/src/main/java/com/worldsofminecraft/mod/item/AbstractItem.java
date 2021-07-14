package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public abstract class AbstractItem implements IItem {
	
	private final String name;
	private final IItemModel model;
	private String simpleRegistryName;
	private RegistryObject<Item> registryObject;
	
	public AbstractItem(@Nonnull String name, @Nonnull String texture) {
		this(name, PNGResource.get(texture));
	}
	
	public AbstractItem(@Nonnull String name, @Nonnull IPNGResource texture) {
		this(name, ItemTexture.get(texture));
	}
	
	public AbstractItem(@Nonnull String name, @Nonnull ItemTexture texture) {
		this(name, ItemModel.getBuilder(texture).build());
	}

	public AbstractItem(@Nonnull String name, @Nonnull IItemModel model) {
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(model != null, "model must not be null");
		this.name = Utils.getInstance().validateName(name);
		this.simpleRegistryName = Utils.getInstance().validateRegistryName(Utils.getInstance().safeRegistryName(name));
		this.model = model;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IItemModel getItemModel() {
		return this.model;
	}


	@Override
	public String getSimpleRegistryName() {
		return this.simpleRegistryName;
	}

	@Override
	public RegistryObject<Item> register(DeferredRegister<Item> register) {
		Preconditions.checkState(this.registryObject == null, "This item was previously registered. Cannot register an item multiple times.");
		this.registryObject = register.register(simpleRegistryName, this.getItemSupplier());
		return this.registryObject;
	}

	@Override
	public RegistryObject<Item> getRegistryObject() {
		Preconditions.checkState(this.registryObject != null, "This item has not yet been registered.");
		return this.registryObject;
	}

	@Override
	public Item construct() {
		Preconditions.checkState(this.registryObject != null, "Cannot construct an item that has not yet been registered.");
		return this.registryObject.get();
	}
	
	protected abstract Supplier<Item> getItemSupplier();

}
