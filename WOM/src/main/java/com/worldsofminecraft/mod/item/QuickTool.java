package com.worldsofminecraft.mod.item;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.block.IBlock;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

public class QuickTool extends QuickTieredItem {
	
	private float attack = 6.0F;
	private float speed = -3.2F;
	//TODO(2021-07-14 jcollard): Implement using blocks / Adapter
	private Set<IBlock> blocks;

	
	public QuickTool(String name, IItemModel model, Tier tier) {
		super(name, model, tier);
	}

	public QuickTool(String name, IPNGResource texture, Tier tier) {
		super(name, texture, tier);
	}

	public QuickTool(String name, ItemTexture texture, Tier tier) {
		super(name, texture, tier);
	}

	public QuickTool(String name, String texture, Tier tier) {
		super(name, texture, tier);
	}

	public Set<IBlock> getBlocks(){
		return Collections.unmodifiableSet(blocks);
	}
	
	public void setBlocks(@Nonnull Set<IBlock> blocks) {
		Preconditions.checkNotNull(blocks, "The set of blocks that a ToolItem can break must not be null.");
		this.blocks = blocks;
	}
	
	public float getAttack() {
		return this.attack;
	}
	
	public void setAttack(float attack) {
		Preconditions.checkArgument(attack >= 0, "Attack must be greater than or equal to 0.");
		this.attack = attack;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
