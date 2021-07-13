package com.worldsofminecraft.mod.block;

import com.worldsofminecraft.mod.item.blockitem.IBlockItem;

public interface IBlock {
	
	public boolean canBeItem();
	public IBlockItem asBlockItem();

}
