package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.util.Volatile;
import com.worldsofminecraft.mod.world.IWorld;
import com.worldsofminecraft.resource.model.item.IItemModel;

import net.minecraft.item.Item;
import net.minecraft.item.UseAction;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public interface IItem {

	String getName();

	IItemModel getItemModel();

	String getSimpleRegistryName();

	IItem.Properties getProperties();

	Action getUseAction();

	int getUseDuration();

	RegistryObject<Item> getRegistryObject();

	RegistryObject<Item> register(DeferredRegister<Item> register);

	default IItemStack onUse(IItemStack stack, IWorld world, ILivingEntity livingEntity,
			@Nonnull Supplier<IItemStack> defaultAction) {
		return defaultAction.get();
	}

	public static class Properties {

		private ItemTab tab = ItemTab.MISC;

		public Properties tab(@Nullable ItemTab tab) {
			this.tab = tab;
			return this;
		}

		public ItemTab getTab() {
			return this.tab;
		}

	}

	public enum Action {
		NONE(UseAction.NONE), EAT(UseAction.EAT), DRINK(UseAction.DRINK), BLOCK(UseAction.BLOCK), BOW(UseAction.BOW),
		SPEAR(UseAction.SPEAR), CROSSBOW(UseAction.CROSSBOW);

		public final UseAction action;

		private Action(UseAction action) {
			this.action = action;
		}

		@Volatile
		public UseAction getUseAction() {
			return this.action;
		}

	}

}
