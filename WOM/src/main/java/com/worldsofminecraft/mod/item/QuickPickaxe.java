package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class QuickPickaxe extends QuickTool {

	public QuickPickaxe(String name, IItemModel model, Tier tier) {
		super(name, model, tier);
	}

	public QuickPickaxe(String name, IPNGResource texture, Tier tier) {
		super(name, texture, tier);
	}

	public QuickPickaxe(String name, ItemTexture texture, Tier tier) {
		super(name, texture, tier);
	}

	public QuickPickaxe(String name, String texture, Tier tier) {
		super(name, texture, tier);
	}
	
	@Override
	protected Supplier<Item> getItemSupplier() {
		return () -> new IItem.Adapter.Builder<PickaxeItem>(PickaxeItem.class)
				.constructor(IItemTier.class, int.class, float.class, Item.Properties.class)
				.args(QuickTieredItem.getTier(this.getTier()), (int) this.getAttack(), this.getSpeed(),
						IItem.Adapter.getProperties(this))
				.build(this).MODEL;
	}

}
