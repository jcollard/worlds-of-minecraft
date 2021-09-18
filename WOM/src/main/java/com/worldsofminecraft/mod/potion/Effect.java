package com.worldsofminecraft.mod.potion;

import com.google.common.base.Preconditions;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

/**
 * <h1>Effect</h1>
 * 
 * <p>
 * An {@link Effect} typically provides LivingEntities with a bonus or a
 * detriment. However, some Effects are completely neutral.
 * </p>
 * 
 * <h2>Constructing an Effect</h2>
 * 
 * <p>
 * Below is a simple example showing how to create an Effect which provides
 * </p>
 * 
 * <pre>
 * Effect magicEffect;
 * magicEffect = new Effect(Type.REGENERATION);
 * magicEffect.level(2);
 * magicEffect.seconds(10);
 * </pre>
 * 
 * <p>
 * The code above creates a level 2 regeneration effect that lasts 10 seconds.
 * </p>
 * 
 * <p>
 * By itself, the effect doesn't do much. However, once an effect has been
 * created, it can be applied to food, potions, or living entities.
 * </p>
 * 
 * <h2>Types of Effects</h2>
 * 
 * <p>
 * All of the built in effect types are available in the {@link Effect.Type}
 * class. If you type {@code Effect.Type.} and wait, a full list of effect types
 * will show up for you to choose from.
 * </p>
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class Effect {

	private final Type type;
	private int duration = 30 * 20; // 30 seconds default
	private int level = 0;
	private boolean showParticles = true;
	private boolean isVisible = true;
	private boolean showIcon = true;

	// TODO(2021-07-15 jcollard): EffectInstance has a hiddenEffect field.
	// Consider adding this in

	/**
	 * Creates a new effect specifying the type of the effect.
	 * 
	 * @param type
	 */
	public Effect(Type type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	/**
	 * Specifies the number of seconds this effect will last on a normal server.
	 * 
	 * @param seconds
	 * @return
	 */
	public Effect ticks(int ticks) {
		Preconditions.checkArgument(ticks > 0, "Cannot create an Effect with duration less than 1.");
		this.duration = ticks;
		return this;
	}

	/**
	 * Specifies the number of seconds this effect will last on a normal server.
	 * 
	 * @param seconds
	 * @return
	 */
	public Effect seconds(float seconds) {
		return ticks((int) (seconds * 20));
	}

	public int getLevel() {
		return level + 1;
	}

	/**
	 * Specifies the level of this effect. The higher the level, the more powerful
	 * the effect.
	 * 
	 * @param level
	 * @return
	 */
	public Effect level(int level) {
		Preconditions.checkArgument(level >= 1, "Cannot create an Effect with a level less than 1.");
		this.level = level - 1;
		return this;
	}

	public boolean showParticles() {
		return showParticles;
	}

	/**
	 * Specifies if this effect should show particles.
	 * 
	 * @param showParticles
	 * @return
	 */
	public Effect showParticles(boolean showParticles) {
		this.showParticles = showParticles;
		return this;
	}

	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Specifies if this effect is visible.
	 * 
	 * @param isVisible
	 * @return
	 */
	public Effect visible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public boolean showIcon() {
		return showIcon;
	}

	/**
	 * Specifies if this effect should display an icon when it is on the player
	 * 
	 * @param showIcon
	 * @return
	 */
	public Effect showIcon(boolean showIcon) {
		this.showIcon = showIcon;
		return this;
	}

	public EffectInstance toInstance() {
		return new EffectInstance(type.effect, duration, level, showParticles, isVisible, showIcon);
	}

	public static class Type {

		/** This is the {@code speed} effect in Minecraft 1.16 */
		public static final Type MOVEMENT_SPEED = new Type(Effects.MOVEMENT_SPEED);
		/** This is the {@code slowness} effect in Minecraft 1.16 */
		public static final Type MOVEMENT_SLOWDOWN = new Type(Effects.MOVEMENT_SLOWDOWN);
		/** This is the {@code haste} effect in Minecraft 1.16 */
		public static final Type DIG_SPEED = new Type(Effects.DIG_SPEED);
		/** This is the {@code mining_fatigue} effect in Minecraft 1.16 */
		public static final Type DIG_SLOWDOWN = new Type(Effects.DIG_SLOWDOWN);
		/** This is the {@code strength} effect in Minecraft 1.16 */
		public static final Type DAMAGE_BOOST = new Type(Effects.DAMAGE_BOOST);
		/** This is the {@code instant_health} effect in Minecraft 1.16 */
		public static final Type HEAL = new Type(Effects.HEAL);
		/** This is the {@code instant_damage} effect in Minecraft 1.16 */
		public static final Type HARM = new Type(Effects.HARM);
		/** This is the {@code jump_boost} effect in Minecraft 1.16 */
		public static final Type JUMP_BOOST = new Type(Effects.JUMP);
		/** This is the {@code nausea} effect in Minecraft 1.16 */
		public static final Type CONFUSION = new Type(Effects.CONFUSION);
		/** This is the {@code regeneration} effect in Minecraft 1.16 */
		public static final Type REGENERATION = new Type(Effects.REGENERATION);
		/** This is the {@code resistance} effect in Minecraft 1.16 */
		public static final Type DAMAGE_RESISTANCE = new Type(Effects.DAMAGE_RESISTANCE);
		/** This is the {@code fire_resistance} effect in Minecraft 1.16 */
		public static final Type FIRE_RESISTANCE = new Type(Effects.FIRE_RESISTANCE);
		/** This is the {@code water_breathing} effect in Minecraft 1.16 */
		public static final Type WATER_BREATHING = new Type(Effects.WATER_BREATHING);
		/** This is the {@code invisibility} effect in Minecraft 1.16 */
		public static final Type INVISIBILITY = new Type(Effects.INVISIBILITY);
		/** This is the {@code blindness} effect in Minecraft 1.16 */
		public static final Type BLINDNESS = new Type(Effects.BLINDNESS);
		/** This is the {@code night_vision} effect in Minecraft 1.16 */
		public static final Type NIGHT_VISION = new Type(Effects.NIGHT_VISION);
		/** This is the {@code hunger} effect in Minecraft 1.16 */
		public static final Type HUNGER = new Type(Effects.HUNGER);
		/** This is the {@code weakness} effect in Minecraft 1.16 */
		public static final Type WEAKNESS = new Type(Effects.WEAKNESS);
		/** This is the {@code poison} effect in Minecraft 1.16 */
		public static final Type POISON = new Type(Effects.POISON);
		/** This is the {@code wither} effect in Minecraft 1.16 */
		public static final Type WITHER = new Type(Effects.WITHER);
		/** This is the {@code health_boost} effect in Minecraft 1.16 */
		public static final Type HEALTH_BOOST = new Type(Effects.HEALTH_BOOST);
		/** This is the {@code absorption} effect in Minecraft 1.16 */
		public static final Type ABSORPTION = new Type(Effects.ABSORPTION);
		/** This is the {@code saturation} effect in Minecraft 1.16 */
		public static final Type SATURATION = new Type(Effects.SATURATION);
		/** This is the {@code glowing} effect in Minecraft 1.16 */
		public static final Type GLOWING = new Type(Effects.GLOWING);
		/** This is the {@code levitation} effect in Minecraft 1.16 */
		public static final Type LEVITATION = new Type(Effects.LEVITATION);
		/** This is the {@code luck} effect in Minecraft 1.16 */
		public static final Type LUCK = new Type(Effects.LUCK);
		/** This is the {@code unluck} effect in Minecraft 1.16 */
		public static final Type UNLUCK = new Type(Effects.UNLUCK);
		/** This is the {@code slow_falling} effect in Minecraft 1.16 */
		public static final Type SLOW_FALLING = new Type(Effects.SLOW_FALLING);
		/** This is the {@code conduit_power} effect in Minecraft 1.16 */
		public static final Type CONDUIT_POWER = new Type(Effects.CONDUIT_POWER);
		/** This is the {@code dolphins_grace} effect in Minecraft 1.16 */
		public static final Type DOLPHINS_GRACE = new Type(Effects.DOLPHINS_GRACE);
		/** This is the {@code bad_omen} effect in Minecraft 1.16 */
		public static final Type BAD_OMEN = new Type(Effects.BAD_OMEN);
		/** This is the {@code hero_of_the_village} effect in Minecraft 1.16 */
		public static final Type HERO_OF_THE_VILLAGE = new Type(Effects.HERO_OF_THE_VILLAGE);

		private final net.minecraft.potion.Effect effect;

		private Type(net.minecraft.potion.Effect effect) {
			this.effect = effect;
		}

	}

}
