package com.worldsofminecraft.mod.item;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.worldsofminecraft.mod.item.stack.IItemStack;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SwordItemAdapter extends SwordItem {
	
	private final QuickSwordItem item;

	public SwordItemAdapter(@Nonnull QuickSwordItem item) {
		super(QuickTieredItem.getTier(item.getTier()), item.getDamage(), item.getSpeed(), ItemAdapter.getProperties(item));
		this.item = item;
	}

	//TODO(2021-07-14 jcollard): This is copy / pasted from ItemAdapter. This should be organized somewhere specific
	
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
