package com.worldsofminecraft.mod.item;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class QuickItem implements IItem {

	private final String name;
	private final IItemModel model;
	private ItemTab tab = ItemTab.MISC;
	private String registryName;
	private String simpleRegistryName;
	private Function<ItemUseContext, IItemStack> onUse;
	private RegistryObject<Item> registryObject;
	private int useDuration = 16;
	private ItemUseAnimation animation = ItemUseAnimation.EAT;
	

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
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(model != null, "model must not be null");
		this.name = Utils.getInstance().validateName(name);
		this.model = model;
	}
	

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getRegistryName() {
		return this.registryName;
	}
	

	@Override
	public void setRegistryName(@Nonnull String modId, @Nonnull String name) {
		if(this.registryName != null) {
			throw new IllegalStateException("Registry name may only be set once.");
		}
		Preconditions.checkArgument(name != null);
		Preconditions.checkArgument(modId != null);
		name = Utils.getInstance().validateRegistryName(name);
		modId = Utils.getInstance().validateModId(modId);
		this.registryName = "item." + modId + "." + name;
		this.simpleRegistryName = name;
	}

	@Override
	public String getSimpleRegistryName() {
		return this.simpleRegistryName;
	}

	@Override
	public IItemModel getModel() {
		return this.model;
	}
	
	public void setTab(@Nonnull ItemTab tab) {
		Preconditions.checkArgument(tab != null);
		this.tab = tab;
	}
	
	public void clearTab() {
		this.tab = null;
	}
	
	public void initProperties(Item.Properties p) {
		if(this.tab != null) {
			p.tab(tab.getItemGroup());
		}
	}

//	public Function<IItem, Item> getItemAdapter() {
//		return this.supplier;
//	}
//	
//	public void setItemBuilder(Function<IItem, Item> supplier) {
//		this.supplier = supplier;
//	}
	
	public QuickItem setOnUse(@Nonnull Function<ItemUseContext, IItemStack> onUse) {
		Preconditions.checkArgument(onUse != null, "onUse must not be null.");
		this.onUse = onUse;
		return this;
	}
	

	public QuickItem setOnUse(@Nonnull Consumer<ItemUseContext> onUse) {
		Preconditions.checkArgument(onUse != null, "onUse must not be null.");
		this.setOnUse((context) -> {
			onUse.accept(context);
			return context.itemStack;
		});
		return this;
	}
	
	public QuickItem setUseDuration(int duration) {
		Preconditions.checkArgument(duration >= 0, "Delay must be greater than or equal to 0.");
		this.useDuration = duration;
		return this;
	}
	
	public int getUseDuration() {
		return useDuration;
	}
	
	public ItemUseAnimation getUseAnimation() {
		return this.animation;
	}
	
	public QuickItem setUseAnimation(@Nonnull ItemUseAnimation animation) {
		Preconditions.checkArgument(animation != null, "Cannot set to null animaiton.");
		this.animation = animation;
		return this;
	}
	
	public Function<ItemUseContext, IItemStack> onUse(){
		return this.onUse;
	}

	@Override
	public RegistryObject<Item> getRegistryObject() {
		return this.registryObject;
	}

	@Override
	public RegistryObject<Item> register(DeferredRegister<Item> register) {
		Preconditions.checkState(this.registryObject == null, "This item was previously registered. Cannot register an item multiple times.");
		this.registryObject = register.register(simpleRegistryName, () -> new ItemAdapter(this));
		return this.registryObject;
	}

	@Override
	public Item construct() {
		Preconditions.checkState(this.registryObject != null, "Cannot construct an item that has not yet been registered.");
		return this.registryObject.get();
	}

}
