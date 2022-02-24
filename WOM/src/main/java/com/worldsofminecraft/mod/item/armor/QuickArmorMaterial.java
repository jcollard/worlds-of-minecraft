package com.worldsofminecraft.mod.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class QuickArmorMaterial {
	
	private static class AdapterArmorMaterial implements IArmorMaterial {

		@Override
		public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getEnchantmentValue() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public SoundEvent getEquipSound() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Ingredient getRepairIngredient() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public float getToughness() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getKnockbackResistance() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

}
