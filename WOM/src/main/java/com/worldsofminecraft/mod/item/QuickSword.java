package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

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
		return () -> new Adapter(this);
	}

	private static class Adapter extends SwordItem {

		private final IItem.Adapter.Delegate delegate;

		public Adapter(@Nonnull QuickSword item) {
			super(QuickTieredItem.getTier(item.getTier()), item.getDamage(), item.getSpeed(),
					IItem.Adapter.getProperties(item));
			this.delegate = new IItem.Adapter.Delegate(item, super::finishUsingItem);
		}
		
		@Override
		public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
			return delegate.finishUsingItem(stack, world, livingEntity);
		}

		@Override
		public UseAction getUseAnimation(ItemStack stack) {
			return this.delegate.getUseAnimation(stack);
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return this.delegate.getUseDuration(stack);
		}

		@Override
		public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
			return this.delegate.use(world, player, hand);

		}

	}

}
