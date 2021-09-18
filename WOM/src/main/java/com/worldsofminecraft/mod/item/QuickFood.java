package com.worldsofminecraft.mod.item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.util.functional.ItemUseContext;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.world.IWorld;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * <h1>QuickFood</h1>
 * <p>
 * QuickFood provides a quick way to create edible items. It functions similarly
 * to {@link QuickItem} however it has an {@link QuickFood#onConsumed} helper
 * field rather than {@link QuickItem#onUse} helper field. Additionally, several
 * methods are provided to quickly specify the foods properties and any special
 * effects.
 * </p>
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class QuickFood extends AbstractItem {

	private boolean isAlwaysEdible = true;
	private boolean isMeat = false;
	private int foodPoints = 4;
	private float saturationRatio = 0.6F;
	private final Map<Effect, Float> effects = new HashMap<>();
	public Function<ItemUseContext, IItemStack> onConsumed;

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

	/**
	 * Specifies if this food can be eaten if the player is already full.
	 * 
	 * @param isAlwaysEdible
	 * @return
	 */
	public QuickFood isAlwaysEdible(boolean isAlwaysEdible) {
		this.isAlwaysEdible = isAlwaysEdible;
		return this;
	}

	public boolean isAlwaysEdible() {
		return this.isAlwaysEdible;
	}

	/**
	 * Specifies if this food is considered meat.
	 * 
	 * @param isMeat
	 * @return
	 */
	public QuickFood isMeat(boolean isMeat) {
		this.isMeat = isMeat;
		return this;
	}

	public boolean isMeat() {
		return this.isMeat;
	}

	/**
	 * Specifies the amount of hunger that is restored.
	 * 
	 * @param foodPoints
	 * @return
	 */
	public QuickFood foodPoints(int foodPoints) {
		Preconditions.checkArgument(foodPoints >= 1, "Food points must be at least 1.");
		this.foodPoints = foodPoints;
		return this;
	}

	public int getFoodPoints() {
		return this.foodPoints;
	}

	/**
	 * Specifies how much saturation this food provides.
	 * 
	 * @param saturationRatio
	 * @return
	 */
	public QuickFood saturationRatio(float saturationRatio) {
		Preconditions.checkArgument(saturationRatio > 0, "Food saturation ration must be greater than 0.");
		this.saturationRatio = saturationRatio;
		return this;
	}

	public float getSaturationRatio() {
		return this.saturationRatio;
	}

	/**
	 * Adds an effect that is applied when eaten.
	 * 
	 * @param effect
	 * @return
	 */
	public QuickFood addEffect(@Nonnull Effect effect) {
		return addEffect(effect, 1.0F);
	}

	/**
	 * Adds an effect that has a probability of being applied when eaten.
	 * 
	 * @param effect
	 * @param probability A number between 0 and 1 where 0 is a 0% chance and 1 is a
	 *                    100% chance.
	 * @return
	 */
	public QuickFood addEffect(@Nonnull Effect effect, float probability) {
		Preconditions.checkNotNull(effect, "Cannot add a null effect to food.");
		Preconditions.checkArgument(probability > 0 && probability <= 1,
				"Probability should be a value between 0 and 1. For example, 0.5 would be a 50% chance.");
		this.effects.put(effect, probability);
		return this;
	}

	public IItemStack onConsumed(IItemStack stack, IWorld world, ILivingEntity entity) {
		if (onConsumed != null) {
			ItemUseContext context = new ItemUseContext(stack, world, entity, () -> stack);
			return onConsumed.apply(context);
		}
		return stack;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Supplier<Item> getItemSupplier() {
		Item.Properties p = IItem.Adapter.getProperties(this);
		Food.Builder fb = new Food.Builder();
		for (Effect e : this.effects.keySet()) {
			fb.effect(() -> e.toInstance(), this.effects.get(e));
		}
		fb.nutrition(this.foodPoints);
		fb.saturationMod(saturationRatio / 2);
		if (this.isAlwaysEdible) {
			fb.alwaysEat();
		}
		if (this.isMeat) {
			fb.meat();
		}
		p.food(fb.build());

		IItem.Adapter.Builder<Item> b = new IItem.Adapter.Builder<>(Item.class)	.constructor(Item.Properties.class)
																				.args(p);
		return () -> new Adapter(this, b).MODEL;
	}

	public static class Adapter extends IItem.Adapter<Item> {

		private QuickFood food;

		public Adapter(QuickFood food, Builder<Item> builder) {
			super(food, builder);
			this.food = food;
		}

		@Override
		public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
			ItemStack defaultStack = livingEntity.eat(world, stack);
			return food	.onConsumed(IItemStack.convert(defaultStack), IWorld.convert(world),
								ILivingEntity.convert(livingEntity))
						.getModel();
		}

		@Override
		public UseAction getUseAnimation(ItemStack stack) {
			return UseAction.EAT;
		}

		@Override
		public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
			player.startUsingItem(hand);
			return ActionResult.consume(player.getItemInHand(hand));
		}

	}

}
