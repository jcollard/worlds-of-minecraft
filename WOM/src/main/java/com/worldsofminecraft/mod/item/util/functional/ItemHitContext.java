package com.worldsofminecraft.mod.item.util.functional;

import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.item.stack.IItemStack;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class ItemHitContext {

    public final IItemStack itemStack;
    public final ILivingEntity attacker;
    public final ILivingEntity defender;

    public ItemHitContext(IItemStack itemStack, ILivingEntity defender, ILivingEntity attacker) {
        this.itemStack = itemStack;
        this.attacker = attacker;
        this.defender = defender;
    }

    public ItemHitContext(ItemStack itemStack, LivingEntity defender, LivingEntity attacker) {
        this.itemStack = IItemStack.convert(itemStack);
        this.attacker = ILivingEntity.convert(attacker);
        this.defender = ILivingEntity.convert(defender);
    }

}
