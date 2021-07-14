package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.item.stack.IItemStack;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemAdapter extends Item {

	private final IItem item;

	public ItemAdapter(@Nonnull IItem item) {
		super(ItemAdapter.getProperties(item));
		this.item = item;
	}

	private static Properties getProperties(@Nonnull IItem item) {
		Preconditions.checkNotNull(item, "Cannot create an ItemAdapter from a null IItem.");
		Properties p = new Item.Properties().tab(item.getProperties().getTab().getItemGroup());
		return p;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
		Supplier<IItemStack> defaultAction = () -> IItemStack
				.convert(super.finishUsingItem(stack, world, livingEntity));
		ItemUseContext context = new ItemUseContext(stack, world, livingEntity, defaultAction);
		IItemStack s = item.onUse(context.itemStack, context.world, context.entity, defaultAction);
		return s.getModel();
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return this.item.getUseAction().getUseAction();
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return this.item.getUseDuration();
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		player.startUsingItem(hand);
		return ActionResult.pass(player.getItemInHand(hand));

	}

}
