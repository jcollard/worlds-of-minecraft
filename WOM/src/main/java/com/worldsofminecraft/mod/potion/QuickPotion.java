package com.worldsofminecraft.mod.potion;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.item.IIngredient;
import com.worldsofminecraft.mod.util.Utils;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class QuickPotion {

	private static final Set<QuickPotion> ALL_POTIONS = new HashSet<>();

	public static boolean checkRegistration(Builder b) {
		Set<String> key = b	.getPotions()
							.keySet();
		boolean pass = true;
		for (QuickPotion potion : ALL_POTIONS) {
			if (!key.contains(b.MOD_ID + "." + potion.simpleRegistryName)) {
				Utils	.getInstance()
						.getLogger()
						.warn("WARNING: \"" + potion.name
								+ "\" was created but never registered! Did you forget to add it to your mod?");
				pass = false;
			}
		}
		return pass;
	}

	private final String name;
	private final String simpleRegistryName;
	private final IIngredient ingredient;
	private RegistryObject<Potion> registryObject;
	private final Set<Effect> effects = new HashSet<>();

	public QuickPotion(@Nonnull String name, @Nonnull IIngredient ingredient) {
		Preconditions.checkNotNull(name, "Potion name must not be null.");
		Preconditions.checkNotNull(ingredient, "Potion ingredient must not be null.");
		this.ingredient = ingredient;
		this.name = Utils	.getInstance()
							.validateName(name);
		this.simpleRegistryName = Utils	.getInstance()
										.validateRegistryName(Utils	.getInstance()
																	.safeRegistryName(name));
		ALL_POTIONS.add(this);
	}

	public QuickPotion addEffect(@Nonnull Effect effect) {
		Preconditions.checkNotNull(effect, "Cannot add a null effect to a potion.");
		this.effects.add(effect);
		return this;
	}

	public RegistryObject<Potion> register(DeferredRegister<Potion> register, String modId) {
		Preconditions.checkState(this.registryObject == null,
				"This potion was previously registered. Cannot register a potion multiple times.");
		this.registryObject = register.register(modId + "." + simpleRegistryName, this.getPotionSupplier());
		return this.registryObject;
	}

	public String getName() {
		return this.name;
	}

	public IIngredient getIngredient() {
		return this.ingredient;
	}

	public String getSimpleRegistryName() {
		return this.simpleRegistryName;
	}

	public Supplier<Potion> getPotionSupplier() {
		EffectInstance[] instances = new EffectInstance[effects.size()];
		int ix = 0;
		for (Effect e : effects) {
			instances[ix] = e.toInstance();
			ix++;
		}
		return () -> new Potion(instances);
	}

	public RegistryObject<Potion> getRegistryObject() {
		return this.registryObject;
	}

}
