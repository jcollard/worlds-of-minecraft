package com.worldsofminecraft.mod.item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class QuickFood extends AbstractItem {
	
	private boolean isAlwaysEdible = true;
	private boolean isMeat = false;
	private int foodPoints = 4;
	private float saturationRatio = 0.6F;
	private Supplier<Item> supplier;
	private final Map<Effect, Float> effects = new HashMap<>();
	
	public QuickFood(String name, IItemModel model) {
		super(name, model);
	}

	public QuickFood(String name, IPNGResource texture) {
		super(name, texture);
	}

	public QuickFood(String name, ItemTexture texture) {
		super(name, texture);
	}

	public QuickFood(String name, String texture) {
		super(name, texture);
	}
	
	public QuickFood isAlwaysEdible(boolean isAlwaysEdible) {
		this.isAlwaysEdible = isAlwaysEdible;
		return this;
	}
	
	public boolean isAlwaysEdible() {
		return this.isAlwaysEdible;
	}
	
	public QuickFood isMeat(boolean isMeat) {
		this.isMeat = isMeat;
		return this;
	}
	
	public boolean isMeat() {
		return this.isMeat;
	}
	
	public QuickFood foodPoints(int foodPoints) {
		Preconditions.checkArgument(foodPoints >= 1, "Food points must be at least 1.");
		this.foodPoints = foodPoints;
		return this;
	}
	
	public int getFoodPoints() {
		return this.foodPoints;
	}
	
	public QuickFood saturationRatio(float saturationRatio) {
		Preconditions.checkArgument(saturationRatio > 0, "Food saturation ration must be greater than 0.");
		this.saturationRatio = saturationRatio;
		return this;
	}
	
	public float getSaturationRatio() {
		return this.saturationRatio;
	}
	
	public QuickFood addEffect(@Nonnull Effect effect) {
		return addEffect(effect, 1.0F);
	}
	
	public QuickFood addEffect(@Nonnull Effect effect, float probability) {
		Preconditions.checkNotNull(effect, "Cannot add a null effect to food.");
		Preconditions.checkArgument(probability > 0 && probability <= 1, "Probability should be a value between 0 and 1. For example, 0.5 would be a 50% chance.");
		this.effects.put(effect, probability);
		return this;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		if(supplier == null) {
			Item.Properties p = IItem.Adapter.getProperties(this);
			Food.Builder fb = new Food.Builder();
			for(Effect e : this.effects.keySet()) {
				fb.effect(() -> e.toInstance(), this.effects.get(e));	
			}
			fb.nutrition(this.foodPoints);
			fb.saturationMod(saturationRatio/2);
			if(this.isAlwaysEdible) {
				fb.alwaysEat();
			}
			if(this.isMeat) {
				fb.meat();
			}
			p.food(fb.build());
			this.supplier = () -> new Item(p);
		}
		return this.supplier;
	}
	
	

}
