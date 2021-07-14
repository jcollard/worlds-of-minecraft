package com.worldsofminecraft.mod.item;

import com.worldsofminecraft.resource.model.item.IItemModel;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public interface IItem {
	
	String getName();
	IItemModel getItemModel();
	String getSimpleRegistryName();
	RegistryObject<Item> getRegistryObject();
	RegistryObject<Item> register(DeferredRegister<Item> register);

}
