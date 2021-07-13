package com.worldsofminecraft.mod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class QuickItemAdapter extends Item {
	
	private final QuickItem item;

	public QuickItemAdapter(QuickItem item) {
		super(QuickItemAdapter.getProperties(item));
		this.item = item;
	}

	private static Properties getProperties(QuickItem item) {
		Properties p = new Item.Properties();
		item.initProperties(p);
		return p;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
		if(item.onUse() == null) {
			return super.finishUsingItem(stack, world, livingEntity);
		}
		ItemUseContext context = new ItemUseContext(stack, world, livingEntity);
		return item.onUse().apply(context).getModel();
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {	
		if (item.onUse() == null) {
			return super.getUseAnimation(stack);
		}
		return item.getUseAnimation().getUseAction();
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		if(item.onUse() == null) {
			return super.getUseDuration(stack);
		}
		return this.item.getUseDuration();
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(item.onUse() == null) {
			return super.use(world, player, hand);
		}
		player.startUsingItem(hand);
		return ActionResult.pass(player.getItemInHand(hand));
		
	}

}
