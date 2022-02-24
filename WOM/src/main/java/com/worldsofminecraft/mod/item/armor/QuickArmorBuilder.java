package com.worldsofminecraft.mod.item.armor;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.mod.item.IIngredient;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class QuickArmorBuilder {
	
	private String name;
	private String feetName;
	private IPNGResource feetItemTexture;
	private String legsName;
	private IPNGResource legsItemTexture;
	private String chestName;
	private IPNGResource chestItemTexture;
	private String helmName;
	private IPNGResource helmItemTexture;
	private static final int[] DURABILITY_PER_SLOT = new int[]{13, 15, 16, 11};
	private Map<ArmorType, Integer> armor = new TreeMap<>();
	private int durability = 10;
	private int enchantability = 0;
	private float toughness = 1.0f;
	private float knockbackResistance = 1.0f;
	private IItem repairIngredient = null;
	private VanillaItem vanillaRepairIngredient = null;
	private IPNGResource texture;
	
	public QuickArmorBuilder(@Nonnull String name, @Nonnull String texture) {
		Preconditions.checkArgument(name != null, "QuickArmor name cannot be null.");
		this.texture = PNGResource.get(texture);
		this.name = name;
		this.feetName = String.format("%s Boots", name);
		this.legsName = String.format("%s Leggings", name);
		this.chestName = String.format("%s Chest", name);
		this.helmName = String.format("%s Helm", name);
		armor.put(ArmorType.FEET, 1);
		armor.put(ArmorType.LEGS, 4);
		armor.put(ArmorType.CHEST, 5);
		armor.put(ArmorType.HEAD, 2);
	}	
	
	public QuickArmorBuilder setProtection(@Nonnull ArmorType type, int protection) {
		Preconditions.checkArgument(type != null, "Cannot set protection for a null armor type.");
		Preconditions.checkArgument(protection > 0, "Armor must have a protection of at least 1.");
		armor.put(type, protection);
		return this;
	}
	
	public QuickArmorBuilder setFeetName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.feetName = name;
		return this;
	}
	
	public QuickArmorBuilder setLegsName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.legsName = name;
		return this;
	}
	
	public QuickArmorBuilder setChestName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.chestName = name;
		return this;
	}
	
	public QuickArmorBuilder setHelmName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.helmName = name;
		return this;
	}
	
	public QuickArmorBuilder setDurability(int durability) {
		Preconditions.checkArgument(durability > 0, "Durability must be a positive number.");
		this.durability = durability;
		return this;
	}
	
	public QuickArmorBuilder setEnchantability(int enchantability) {
		Preconditions.checkArgument(durability >= 0, "Enchantability must be a non-negative number.");
		this.enchantability = enchantability;
		return this;
	}
	
	public QuickArmorBuilder setToughness(float toughness) {
		Preconditions.checkArgument(toughness > 0, "Toughness must be a positive number.");
		this.toughness = toughness;
		return this;
	}
	
	public QuickArmorBuilder setKnockbackResistance(float knockbackResistance) {
		Preconditions.checkArgument(knockbackResistance >= 0, "Knockback resistance must be a positive number.");
		this.knockbackResistance = knockbackResistance;
		return this;
	}
	
	public QuickArmorBuilder setFeetItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.feetItemTexture = PNGResource.get(texture);
		return this;
	}

	public QuickArmorBuilder setLegsItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.legsItemTexture = PNGResource.get(texture);
		return this;
	}

	public QuickArmorBuilder setChestItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.chestItemTexture = PNGResource.get(texture);
		return this;
	}

	public QuickArmorBuilder setHelmItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.helmItemTexture = PNGResource.get(texture);
		return this;
	}
	
	public QuickArmorBuilder setRepairIngredient(@Nonnull IItem item) {
		Preconditions.checkArgument(item != null, "The repair item cannot be null.");
		Preconditions.checkState(this.repairIngredient == null && this.vanillaRepairIngredient == null, "Cannot specify more than one repair ingredient.");
		repairIngredient = item;
		return this;
	}
	
	public QuickArmorBuilder setRepairIngredient(@Nonnull VanillaItem item) {
		Preconditions.checkArgument(item != null, "The repair item cannot be null.");
		Preconditions.checkState(this.repairIngredient == null && this.vanillaRepairIngredient == null, "Cannot specify more than one repair ingredient.");
		vanillaRepairIngredient = item;
		return this;
	}
	
	public String generateResource(@Nonnull IMinecraftMod mod) throws IOException {
		return null;
	}
	
	public Optional<String> getErrorMessage() {
		if (this.chestItemTexture == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setChestItemTexture?", this.name));
		}
		
		if (this.feetItemTexture == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setFeetItemTexture?", this.name));
		}
		
		if (this.helmItemTexture == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setHelmItemTexture?", this.name));
		}
		
		if (this.legsItemTexture == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setLegsItemTexture?", this.name));
		}
		
		if (this.repairIngredient == null && this.vanillaRepairIngredient == null) {
			return Optional.of(String.format("No repair ingredient set for %s, did you remember to call setRepairIngredient?", this.name));
		}
		return Optional.empty();
	}
	
	private static class ArmorMaterial implements IArmorMaterial {
		
		private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
		   private final String name;
		   private final int durabilityMultiplier;
		   private final int[] slotProtections;
		   private final int enchantmentValue;
		   private final SoundEvent sound;
		   private final float toughness;
		   private final float knockbackResistance;
		   private final LazyValue<Ingredient> repairIngredient;

		   private ArmorMaterial(QuickArmorBuilder b) {
			  this.name = b.name.toLowerCase();
		      this.durabilityMultiplier = b.durability;
		      this.slotProtections = new int[] {b.armor.get(ArmorType.FEET), b.armor.get(ArmorType.LEGS), b.armor.get(ArmorType.CHEST), b.armor.get(ArmorType.HEAD)};
		      this.enchantmentValue = b.enchantability;
		      this.sound = SoundEvents.ARMOR_EQUIP_IRON; // TODO: Add new sound options
		      this.toughness = b.toughness;
		      this.knockbackResistance = b.knockbackResistance;
		      
		      if (b.repairIngredient != null) {
		    	  this.repairIngredient = new LazyValue<Ingredient>(() -> IIngredient.simple(b.repairIngredient).getModel());
		      } else {
		    	  this.repairIngredient = new LazyValue<Ingredient>(() -> IIngredient.simple(b.vanillaRepairIngredient).getModel());
		      }
		   }

		   public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
		      return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
		   }

		   public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
		      return this.slotProtections[p_200902_1_.getIndex()];
		   }

		   public int getEnchantmentValue() {
		      return this.enchantmentValue;
		   }

		   public SoundEvent getEquipSound() {
		      return this.sound;
		   }

		   public Ingredient getRepairIngredient() {
		      return this.repairIngredient.get();
		   }

		   @OnlyIn(Dist.CLIENT)
		   public String getName() {
		      return this.name;
		   }

		   public float getToughness() {
		      return this.toughness;
		   }

		   public float getKnockbackResistance() {
		      return this.knockbackResistance;
		   }
		
	}

}
