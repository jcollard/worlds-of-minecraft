package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;

public class QuickSwordItem extends QuickTieredItem {
	
	private int damage = 3;
	private float speed = -2.4F;
	
	public QuickSwordItem(String name, String texture) {
		super(name, texture);
		setTier(Tier.IRON);
	}

	public QuickSwordItem(String name, IItemModel model) {
		super(name, model);
		setTier(Tier.IRON);
	}

	public QuickSwordItem(String name, IPNGResource texture) {
		super(name, texture);
		setTier(Tier.IRON);
	}

	public QuickSwordItem(String name, ItemTexture texture) {
		super(name, texture);
		setTier(Tier.IRON);
	}
	
	public QuickSwordItem(String name, String texture, Tier tier) {
		super(name, texture, tier);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public QuickSwordItem setDamage(int damage) {
		Preconditions.checkArgument(damage >= 0, "Damage must be greater than or equal to 0.");
		this.damage = damage;
		return this;
	}
	
	public QuickSwordItem setSpeed(float speed) {
		this.speed = speed;
		return this;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return () -> new SwordItemAdapter(this);
	}
	
	
	

}
