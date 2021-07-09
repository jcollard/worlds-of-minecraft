package com.worldsofminecraft.mod.block;

import com.worldsofminecraft.mod.item.IBlockItem;

public interface IBlock {
	
	public boolean canBeItem();
	public IBlockItem asBlockItem();

}
