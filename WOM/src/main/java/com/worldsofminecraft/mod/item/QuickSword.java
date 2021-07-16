package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class QuickSword extends QuickTieredItem {

	private int damage = 3;
	private float speed = -2.4F;

	public QuickSword(String name, IItemModel model, Tier tier) {
		super(name, model, tier);
	}

	public QuickSword(String name, IPNGResource texture, Tier tier) {
		super(name, texture, tier);
	}

	public QuickSword(String name, ItemTexture texture, Tier tier) {
		super(name, texture, tier);
	}

	public QuickSword(String name, String texture, Tier tier) {
		super(name, texture, tier);
	}

	public int getDamage() {
		return damage;
	}

	public float getSpeed() {
		return speed;
	}

	public QuickSword setDamage(int damage) {
		Preconditions.checkArgument(damage >= 0, "Damage must be greater than or equal to 0.");
		this.damage = damage;
		return this;
	}

	public QuickSword setSpeed(float speed) {
		this.speed = speed;
		return this;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return () -> new IItem.Adapter.Builder<SwordItem>(SwordItem.class)
																			.constructor(IItemTier.class, int.class,
																					float.class, Item.Properties.class)
																			.args(QuickTieredItem.getTier(
																					this.getTier()), this.getDamage(),
																					this.getSpeed(),
																					IItem.Adapter.getProperties(this))
																			.build(this).MODEL;
	}

}
