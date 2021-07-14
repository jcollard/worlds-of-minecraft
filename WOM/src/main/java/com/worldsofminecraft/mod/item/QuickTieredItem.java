package com.worldsofminecraft.mod.item;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class QuickTieredItem extends QuickItem {

	private Tier tier = new Tier();
	
	public QuickTieredItem(@Nonnull String name, @Nonnull String texture) {
		super(name, texture);
	}
	
	public QuickTieredItem(String name, IItemModel model) {
		super(name, model);
	}

	public QuickTieredItem(String name, IPNGResource texture) {
		super(name, texture);
	}

	public QuickTieredItem(String name, ItemTexture texture) {
		super(name, texture);
	}
	
	public QuickTieredItem(@Nonnull String name, @Nonnull String texture, @Nonnull Tier tier) {
		super(name, texture);
		Preconditions.checkNotNull(tier, "QuickTieredItem requires a non-null Tier.");
		this.tier = tier;
	}
	
	public Tier getTier() {
		return this.tier;
	}
	
	public void setTier(@Nonnull Tier tier) {
		Preconditions.checkNotNull(tier, "The specified tier must be non-null.");
		this.tier = tier;
	}

	protected static IItemTier getTier(Tier tier) {
		return ItemTierAdapter.get(tier);
	}
	
	private static class ItemTierAdapter implements IItemTier {

		private final Tier tier;
		private static final Map<Tier, IItemTier> TIERS = new HashMap<>();
		
		private static IItemTier get(Tier tier) {
			if(!TIERS.containsKey(tier)) {
				TIERS.put(tier, new ItemTierAdapter(tier));
			}
			return TIERS.get(tier);
		}
		
		private ItemTierAdapter(Tier t) {
			this.tier = t;
		}
		
		@Override
		public int getUses() {
			return tier.getUses();
		}

		@Override
		public float getSpeed() {
			return tier.getSpeed();
		}

		@Override
		public float getAttackDamageBonus() {
			return tier.getDamage();
		}

		@Override
		public int getLevel() {
			return tier.getLevel();
		}

		@Override
		public int getEnchantmentValue() {
			return tier.getEnchantability();
		}

		@Override
		public Ingredient getRepairIngredient() {
			return tier.getRepairIngredient().getModel();
		}
		
	}

}
