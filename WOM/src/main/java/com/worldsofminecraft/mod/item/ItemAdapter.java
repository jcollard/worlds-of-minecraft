package com.worldsofminecraft.mod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemAdapter extends Item {
	
	private final QuickItem item;

	public ItemAdapter(QuickItem item) {
		super(ItemAdapter.getProperties(item));
		this.item = item;
	}

	private static Properties getProperties(QuickItem item) {
		Properties p = new Item.Properties();
		item.initProperties(p);
		return p;
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
