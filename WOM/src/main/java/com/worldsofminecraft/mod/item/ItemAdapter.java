package com.worldsofminecraft.mod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemAdapter extends Item {
	
	private final IItem item;

	public ItemAdapter(IItem item) {
		super(ItemAdapter.getProperties(item));
		this.item = item;
	}

	private static Properties getProperties(IItem item) {
		return new Item.Properties().tab(item.getTab().getItemGroup());
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(item.onUse() == null) {
			return super.use(world, player, hand);
		}
		
		ItemUseContext context = new ItemUseContext(this, world, player, hand, () -> super.use(world, player, hand));
		return item.onUse().apply(context);
		
	}

}
