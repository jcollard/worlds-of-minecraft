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
	private final IItem.Properties properties = new IItem.Properties();
	private final String simpleRegistryName;
	private RegistryObject<Item> registryObject;
	private Action action = Action.EAT;
	private int useDuration = 20;
	
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
	public IItem.Properties getProperties(){
		return this.properties;
	}
	
	@Override
	public int getUseDuration() {
		return useDuration;
	}

	public void setUseDuration(int duration) {
		Preconditions.checkArgument(duration >= 0, "Delay must be greater than or equal to 0.");
		this.useDuration = duration;
	}

	@Override
	public Action getUseAction() {
		return this.action;
	}

	public void setUseAction(@Nonnull Action action) {
		Preconditions.checkArgument(action != null, "Cannot set a null action.");
		this.action = action;
	}

	
	protected abstract Supplier<Item> getItemSupplier();

}
