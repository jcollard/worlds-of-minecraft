package com.worldsofminecraft.mod.item;

import javax.annotation.Nonnull;

import com.worldsofminecraft.resource.model.item.IItemModel;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public interface IItem {
	
	String getName();
	IItemModel getModel();
	String getRegistryName();
	String getSimpleRegistryName();
	void setRegistryName(@Nonnull String modID, @Nonnull String name);
	
	RegistryObject<Item> getRegistryObject();
	RegistryObject<Item> register(DeferredRegister<Item> register);
	Item construct();

}
