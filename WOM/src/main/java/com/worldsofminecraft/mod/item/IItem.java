package com.worldsofminecraft.mod.item;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.entity.ILivingEntity;
import com.worldsofminecraft.mod.exception.BuildFailedException;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.item.util.functional.ItemUseContext;
import com.worldsofminecraft.mod.util.Volatile;
import com.worldsofminecraft.mod.world.IWorld;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.vanilla.VanillaItem;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public interface IItem {

	String getName();

	IItemModel getItemModel();

	String getSimpleRegistryName();

	IItem.Properties getProperties();

	Action getUseAction();

	int getUseDuration();

	RegistryObject<Item> getRegistryObject();

	RegistryObject<Item> register(DeferredRegister<Item> register);

	default IItemStack onUse(IItemStack stack, IWorld world, ILivingEntity livingEntity,
			@Nonnull Supplier<IItemStack> defaultAction) {
		return defaultAction.get();
	}

	public static class Properties {

		private ItemTab tab = ItemTab.MISC;

		public Properties tab(@Nullable ItemTab tab) {
			this.tab = tab;
			return this;
		}

		public ItemTab getTab() {
			return this.tab;
		}

	}

	public enum Action {
		NONE(UseAction.NONE), EAT(UseAction.EAT), DRINK(UseAction.DRINK), BLOCK(UseAction.BLOCK), BOW(UseAction.BOW),
		SPEAR(UseAction.SPEAR), CROSSBOW(UseAction.CROSSBOW);

		public final UseAction action;

		private Action(UseAction action) {
			this.action = action;
		}

		@Volatile
		public UseAction getUseAction() {
			return this.action;
		}

	}


	public static class Tier {
	
		private int level = 0;
		private int uses = 59;
		private float speed = 2.0F;
		private float damage = 0.0F;
		private int enchantability = 15;
		private IIngredient repairIngredient = IIngredient.EMPTY;
		
		//TODO(2021-07-14 jcollard): Need to be able to set repair ingredient based on a tag.
		private static VanillaItem[] planks = new VanillaItem[] {
			VanillaItem.OAK_PLANKS,
			VanillaItem.SPRUCE_PLANKS,
			VanillaItem.BIRCH_PLANKS,
			VanillaItem.JUNGLE_PLANKS,
			VanillaItem.ACACIA_PLANKS,
			VanillaItem.DARK_OAK_PLANKS,
			VanillaItem.CRIMSON_PLANKS,
			VanillaItem.WARPED_PLANKS
		};
		
		//TODO(2021-07-14 jcollard): Tiers should be immutable so the static classes cannot be changed.
		public static Tier WOOD = new Tier().level(0).durability(59).speed(2.0F).damage(0.0F).enchantabilty(15).repairIngredient(planks);		
		//TODO(2021-07-14 jcollard): Cobbled Deepslate / Blackstone are listed as repair ingredients
		public static Tier STONE = new Tier().level(1).durability(131).speed(4.0F).damage(1.0F).enchantabilty(5).repairIngredient(VanillaItem.COBBLESTONE);
		public static Tier IRON = new Tier().level(2).durability(250).speed(6.0F).damage(2.0F).enchantabilty(14).repairIngredient(VanillaItem.IRON_INGOT);
		public static Tier DIAMOND = new Tier().level(3).durability(1561).speed(8.0F).damage(3.0F).enchantabilty(10).repairIngredient(VanillaItem.DIAMOND);
		public static Tier GOLD = new Tier().level(0).durability(2031).speed(9.0F).damage(4.0F).enchantabilty(15).repairIngredient(VanillaItem.GOLD_INGOT);
		public static Tier NETHERITE = new Tier().level(4).durability(32).speed(12.0F).damage(0.0F).enchantabilty(22).repairIngredient(VanillaItem.NETHERITE_INGOT);
		
		public int getLevel() {
			return level;
		}
		
		public Tier level(int level) {
			Preconditions.checkArgument(level >= 0, "Level must be greater than or equal to 0.");
			this.level = level;
			return this;
		}
		
		public int getUses() {
			return uses;
		}
		
		public Tier durability(int uses) {
			Preconditions.checkArgument(uses >= 0, "Uses must be greater than or equal to 0.");
			this.uses = uses;
			return this;
		}
		
		public float getSpeed() {
			return speed;
		}
		
		public Tier speed(float speed) {
			Preconditions.checkArgument(speed >= 0, "Speed must be greater than or equal to 0.");
			this.speed = speed;
			return this;
		}
		
		public float getDamage() {
			return damage;
		}
		
		public Tier damage(float damage) {
			Preconditions.checkArgument(damage >= 0, "Damage must be greater than or equal to 0.");
			this.damage = damage;
			return this;
		}
		
		public int getEnchantability() {
			return enchantability;
		}
		
		public Tier enchantabilty(int enchantability) {
			Preconditions.checkArgument(enchantability >= 0, "Enchantability must be greater than or equal to 0.");
			this.enchantability = enchantability;
			return this;
		}
		
		public IIngredient getRepairIngredient() {
			return repairIngredient;
		}
		
		public Tier repairIngredient(@Nonnull IIngredient repairIngredient) {
			this.repairIngredient = repairIngredient;
			return this;
		}
		
		public Tier repairIngredient(@Nonnull VanillaItem vanillaItem) {
			this.repairIngredient = IIngredient.simple(vanillaItem);
			return this;
		}
		
		public Tier repairIngredient(@Nonnull IItem item) {
			this.repairIngredient = IIngredient.simple(item);
			return this;
		}
		
		public Tier repairIngredient(@Nonnull IItem ... items) {
			this.repairIngredient = IIngredient.simple(items);
			return this;
		}
		
		public Tier repairIngredient(@Nonnull VanillaItem ... items) {
			this.repairIngredient = IIngredient.simple(items);
			return this;
		}
		
		
	}
	
	public static class Adapter<T extends Item> {

		private final IItem item;
		public final T MODEL;
		private final T defaultItem;
		
		public static class Builder<T extends Item> {
			
			public final Class<? extends T> klass;
			private Class<?>[] constructor = {};
			private Object[] args = {};
			
			public Builder(@Nonnull Class<? extends T> klass) {
				Preconditions.checkNotNull(klass);
				this.klass = klass;
			}
			
			public Builder<T> constructor(Class<?> ... classes) {
				this.constructor = classes;
				return this;
			}
			
			public Builder<T> args(Object ... objects){
				this.args = objects;
				return this;
			}
			
			public Class<?>[] getConstructorArgs(){
				return constructor;
			}
			public Object[] getArgs() {
				return args;
			}
			
			public Adapter<T> build(@Nonnull IItem item) {
				return new Adapter<T>(item, this);
			}
			
		}

		public Adapter(@Nonnull IItem item, Builder<T> builder) {
			Preconditions.checkNotNull(item, "Cannot create adapter on null reference.");
			this.item = item;
			Implementation implementation = MethodDelegation.to(this);
			Class<? extends T> dynamicType = new ByteBuddy()
					  .subclass(builder.klass)
					  .method(ElementMatchers.named("finishUsingItem"))
					  .intercept(implementation)
					  .method(ElementMatchers.named("getUseAnimation"))
					  .intercept(implementation)
					  .method(ElementMatchers.named("getUseDuration"))
					  .intercept(implementation)
					  .method(ElementMatchers.named("use"))
					  .intercept(implementation)
					  .make()
					  .load(getClass().getClassLoader())
					  .getLoaded();
			try {
				defaultItem = builder.klass.getConstructor(builder.getConstructorArgs()).newInstance(builder.getArgs());
				MODEL = dynamicType.getConstructor(builder.getConstructorArgs()).newInstance(builder.getArgs());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				throw new BuildFailedException("Could not create class for item: " + item, e);
			}
			
		}

		public static Item.Properties getProperties(@Nonnull IItem item) {
			Preconditions.checkNotNull(item, "Cannot create an ItemAdapter from a null IItem.");
			Item.Properties p = new Item.Properties();
			if(item.getProperties().getTab() != null) {
				p.tab(item.getProperties().getTab().getItemGroup());
			}
			return p;
		}

		public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
			Supplier<IItemStack> defaultAction = () -> IItemStack
					.convert(defaultItem.finishUsingItem(stack, world, livingEntity));
			ItemUseContext context = new ItemUseContext(stack, world, livingEntity, defaultAction);
			IItemStack s = item.onUse(context.itemStack, context.world, context.entity, defaultAction);
			return s.getModel();
		}

		public UseAction getUseAnimation(ItemStack stack) {
			return this.item.getUseAction().getUseAction();
		}

		public int getUseDuration(ItemStack stack) {
			return this.item.getUseDuration();
		}

		public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
			player.startUsingItem(hand);
			return ActionResult.pass(player.getItemInHand(hand));

		}

	}
	
}
