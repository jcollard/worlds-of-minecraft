package com.worldsofminecraft.mod.item.armor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.IMinecraftMod;
import com.worldsofminecraft.mod.exception.BuildFailedException;
import com.worldsofminecraft.mod.item.IIngredient;
import com.worldsofminecraft.mod.item.IItem;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class QuickArmor {
	
	private final String name;
	private String feetName;
	private IItemModel feetItemModel;
	private String legsName;
	private IItemModel legsItemModel;
	private String chestName;
	private IItemModel chestItemModel;
	private String helmName;
	private IItemModel helmItemModel;
	private static final int[] DURABILITY_PER_SLOT = new int[]{13, 15, 16, 11};
	private Map<ArmorType, Integer> armor = new TreeMap<>();
	private int durability = 10;
	private int enchantability = 0;
	private float toughness = 1.0f;
	private float knockbackResistance = 1.0f;
	private IItem repairIngredient = null;
	private VanillaItem vanillaRepairIngredient = null;
	private final IPNGResource textureLayer1;
	private final IPNGResource textureLayer2;
	private String simpleRegistryName;
	private boolean isRegistered = false;
	
	/** The registry object for this item */
    private Map<ArmorType, RegistryObject<Item>> registryObjects = new HashMap<>();
	
	public QuickArmor(@Nonnull String name, @Nonnull String textureLayer1, @Nonnull String textureLayer2) {
		Preconditions.checkArgument(name != null, "QuickArmor name cannot be null.");
		this.textureLayer1 = PNGResource.get(textureLayer1);
		this.textureLayer2 = PNGResource.get(textureLayer2);
		this.name = Utils.getInstance()
                .validateName(name);
		this.simpleRegistryName = Utils.getInstance()
                              .validateRegistryName(Utils.getInstance()
                              .safeRegistryName(name));
		this.feetName = String.format("%s Boots", name);
		this.legsName = String.format("%s Leggings", name);
		this.chestName = String.format("%s Chest", name);
		this.helmName = String.format("%s Helm", name);
		armor.put(ArmorType.FEET, 1);
		armor.put(ArmorType.LEGS, 4);
		armor.put(ArmorType.CHEST, 5);
		armor.put(ArmorType.HEAD, 2);
	}	
	
	public QuickArmor setProtection(@Nonnull ArmorType type, int protection) {
		Preconditions.checkArgument(type != null, "Cannot set protection for a null armor type.");
		Preconditions.checkArgument(protection > 0, "Armor must have a protection of at least 1.");
		armor.put(type, protection);
		return this;
	}
	
	public QuickArmor setFeetName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.feetName = Utils.getInstance()
                .validateName(name);
		Utils.getInstance()
        .validateRegistryName(Utils.getInstance()
        .safeRegistryName(name));
		return this;
	}
	
	public QuickArmor setLegsName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.legsName = Utils.getInstance()
                .validateName(name);;
		Utils.getInstance()
        .validateRegistryName(Utils.getInstance()
        .safeRegistryName(name));
		return this;
	}
	
	public QuickArmor setChestName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.chestName = Utils.getInstance()
                .validateName(name);;
		Utils.getInstance()
        .validateRegistryName(Utils.getInstance()
        .safeRegistryName(name));
		return this;
	}
	
	public QuickArmor setHelmName(@Nonnull String name) {
		Preconditions.checkArgument(name != null, "Name must not be null.");
		this.helmName = Utils.getInstance()
                .validateName(name);;
		Utils.getInstance()
        .validateRegistryName(Utils.getInstance()
        .safeRegistryName(name));
		return this;
	}
	
	public QuickArmor setDurability(int durability) {
		Preconditions.checkArgument(durability > 0, "Durability must be a positive number.");
		this.durability = durability;
		return this;
	}
	
	public QuickArmor setEnchantability(int enchantability) {
		Preconditions.checkArgument(durability >= 0, "Enchantability must be a non-negative number.");
		this.enchantability = enchantability;
		return this;
	}
	
	public QuickArmor setToughness(float toughness) {
		Preconditions.checkArgument(toughness > 0, "Toughness must be a positive number.");
		this.toughness = toughness;
		return this;
	}
	
	public QuickArmor setKnockbackResistance(float knockbackResistance) {
		Preconditions.checkArgument(knockbackResistance >= 0, "Knockback resistance must be a positive number.");
		this.knockbackResistance = knockbackResistance;
		return this;
	}
	
	public QuickArmor setFeetItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.feetItemModel = ItemModel.getBuilder(ItemTexture.get(PNGResource.get(texture))).build();
		return this;
	}

	public QuickArmor setLegsItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.legsItemModel = ItemModel.getBuilder(ItemTexture.get(PNGResource.get(texture))).build();
		return this;
	}

	public QuickArmor setChestItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.chestItemModel = ItemModel.getBuilder(ItemTexture.get(PNGResource.get(texture))).build();
		return this;
	}

	public QuickArmor setHelmItemTexture(@Nonnull String texture) {
		Preconditions.checkArgument(texture != null, "A texture must be non-null");
		this.helmItemModel = ItemModel.getBuilder(ItemTexture.get(PNGResource.get(texture))).build();
		return this;
	}
	
	public QuickArmor setRepairIngredient(@Nonnull IItem item) {
		Preconditions.checkArgument(item != null, "The repair item cannot be null.");
		Preconditions.checkState(this.repairIngredient == null && this.vanillaRepairIngredient == null, "Cannot specify more than one repair ingredient.");
		repairIngredient = item;
		return this;
	}
	
	public QuickArmor setRepairIngredient(@Nonnull VanillaItem item) {
		Preconditions.checkArgument(item != null, "The repair item cannot be null.");
		Preconditions.checkState(this.repairIngredient == null && this.vanillaRepairIngredient == null, "Cannot specify more than one repair ingredient.");
		vanillaRepairIngredient = item;
		return this;
	}
	
	public void generateResources(@Nonnull IMinecraftMod mod) throws IOException {
		if (getErrorMessage().isPresent()) {
			throw new IllegalStateException(getErrorMessage().get());
		}
		// Generate Item JSONs;
		generateItemResource(mod, chestName, chestItemModel);
		generateItemResource(mod, legsName, legsItemModel);
		generateItemResource(mod, feetName, feetItemModel);
		generateItemResource(mod, helmName, helmItemModel);
		
		// Generate Armor Models
//		Path textureDir = Utils.getInstance()
//                .getItemsTextureDir(mod);
//		Files.createDirectories(textureDir);
//		Path outfile = textureDir.resolve(png.getFileName());
//		Utils.getInstance()
//		.getLogger()
//		.info("Creating item texture: " + outfile);
//		Files.copy(png.getPath(), outfile, StandardCopyOption.REPLACE_EXISTING);
//		this.generated = mod.getModId() + ":items/" + png.getSimpleName();
//		return this.generated;
//		
	}
	
	private void generateItemResource(IMinecraftMod mod, String registryName, IItemModel model) {
		Utils utils = Utils.getInstance();
        Path outfile = utils.getItemModelsDir(mod)
                            .resolve(Utils.getInstance().safeRegistryName(registryName) + ".json");
        utils.getLogger().info("Creating model file: " + outfile);
        // TODO(2021-07-09 jcollard): CREATE_NEW and blow up if file alredy exists?
        try {
            Files.createDirectories(utils.getItemModelsDir(mod));
            Files.write(outfile, model
                                     .generateResource(mod)
                                     .getBytes(),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new BuildFailedException("Could not write file \"" + outfile + "\". ", e);
        }        
	}
	
    public Map<ArmorType, RegistryObject<Item>> register(DeferredRegister<Item> register) {
        Preconditions.checkState(this.registryObjects == null,
                "This item was previously registered. Cannot register an item multiple times.");
        ArmorMaterial material = new ArmorMaterial(this);
        Properties p = new Item.Properties();
		p.tab(ItemGroup.TAB_COMBAT);
		
        Supplier<Item> chestSupplier = () -> new ArmorItem(material, EquipmentSlotType.CHEST, p); 
        String chestRegistryName = Utils.getInstance().validateRegistryName(Utils.getInstance().safeRegistryName(this.chestName));
        this.registryObjects.put(ArmorType.CHEST, register.register(chestRegistryName, chestSupplier));
	
	
        Supplier<Item> footSupplier = () -> new ArmorItem(material, EquipmentSlotType.FEET, p); 
        String feetRegistryName = Utils.getInstance().validateRegistryName(Utils.getInstance().safeRegistryName(this.feetName));
        this.registryObjects.put(ArmorType.FEET, register.register(feetRegistryName, footSupplier));
	
	
        Supplier<Item> legsSupplier = () -> new ArmorItem(material, EquipmentSlotType.LEGS, p); 
        String legsRegistryName = Utils.getInstance().validateRegistryName(Utils.getInstance().safeRegistryName(this.legsName));
        this.registryObjects.put(ArmorType.LEGS, register.register(legsRegistryName, legsSupplier));
	
	
        Supplier<Item> helmSupplier = () -> new ArmorItem(material, EquipmentSlotType.HEAD, p); 
        String helmRegistryName = Utils.getInstance().validateRegistryName(Utils.getInstance().safeRegistryName(this.helmName));
        this.registryObjects.put(ArmorType.HEAD, register.register(helmRegistryName, helmSupplier));
		
        return this.registryObjects;
    }
	
	public Optional<String> getErrorMessage() {
		if (this.chestItemModel == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setChestItemTexture?", this.name));
		}
		
		if (this.feetItemModel == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setFeetItemTexture?", this.name));
		}
		
		if (this.helmItemModel == null) {
			return Optional.of(String.format("No textures set for %s, did you remember to call setHelmItemTexture?", this.name));
		}
		
		if (this.legsItemModel == null) {
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

		   private ArmorMaterial(QuickArmor b) {
			  this.name = b.simpleRegistryName;
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
