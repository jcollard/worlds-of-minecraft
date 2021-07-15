package com.worldsofminecraft.mod.potion;

import com.google.common.base.Preconditions;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Effect {

	private final Type type;
	private int duration = 30*20; // 30 seconds default
	private int level = 0;
	private boolean showParticles = true;
	private boolean isVisible = true;
	private boolean showIcon = true;
	
	//TODO(2021-07-15 jcollard): EffectInstance has a hiddenEffect field. 
	// Consider adding this in

	public Effect(Type type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public Effect duration(int ticks) {
		Preconditions.checkArgument(ticks > 0, "Cannot create an Effect with duration less than 1.");
		this.duration = ticks;
		return this;
	}
	
	public Effect duration(float seconds) {
		return duration((int)(seconds * 20));
	}
	
	public int getLevel() {
		return level + 1;
	}

	public Effect level(int level) {
		Preconditions.checkArgument(level >= 1, "Cannot create an Effect with a level less than 1.");
		this.level = level - 1;
		return this;
	}

	public boolean showParticles() {
		return showParticles;
	}

	public Effect showParticles(boolean showParticles) {
		this.showParticles = showParticles;
		return this;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public Effect visible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public boolean showIcon() {
		return showIcon;
	}

	public Effect showIcon(boolean showIcon) {
		this.showIcon = showIcon;
		return this;
	}

	public EffectInstance toInstance() {
		return new EffectInstance(type.effect, duration, level, showParticles, isVisible, showIcon);
	}

	public static class Type {

		public static final Type MOVEMENT_SPEED = new Type(Effects.MOVEMENT_SPEED);
		public static final Type MOVEMENT_SLOWDOWN = new Type(Effects.MOVEMENT_SLOWDOWN);
		public static final Type DIG_SPEED = new Type(Effects.DIG_SPEED);
		public static final Type DIG_SLOWDOWN = new Type(Effects.DIG_SLOWDOWN);
		public static final Type DAMAGE_BOOST = new Type(Effects.DAMAGE_BOOST);
		public static final Type HEAL = new Type(Effects.HEAL);
		public static final Type HARM = new Type(Effects.HARM);
		public static final Type JUMP_BOOST = new Type(Effects.JUMP);
		public static final Type CONFUSION = new Type(Effects.CONFUSION);
		public static final Type REGENERATION = new Type(Effects.REGENERATION);
		public static final Type DAMAGE_RESISTANCE = new Type(Effects.DAMAGE_RESISTANCE);
		public static final Type FIRE_RESISTANCE = new Type(Effects.FIRE_RESISTANCE);
		public static final Type WATER_BREATHING = new Type(Effects.WATER_BREATHING);
		public static final Type INVISIBILITY = new Type(Effects.INVISIBILITY);
		public static final Type BLINDNESS = new Type(Effects.BLINDNESS);
		public static final Type NIGHT_VISION = new Type(Effects.NIGHT_VISION);
		public static final Type HUNGER = new Type(Effects.HUNGER);
		public static final Type WEAKNESS = new Type(Effects.WEAKNESS);
		public static final Type POISON = new Type(Effects.POISON);
		public static final Type WITHER = new Type(Effects.WITHER);
		public static final Type HEALTH_BOOST = new Type(Effects.HEALTH_BOOST);
		public static final Type ABSORPTION = new Type(Effects.ABSORPTION);
		public static final Type SATURATION = new Type(Effects.SATURATION);
		public static final Type GLOWING = new Type(Effects.GLOWING);
		public static final Type LEVITATION = new Type(Effects.LEVITATION);
		public static final Type LUCK = new Type(Effects.LUCK);
		public static final Type UNLUCK = new Type(Effects.UNLUCK);
		public static final Type SLOW_FALLING = new Type(Effects.SLOW_FALLING);
		public static final Type CONDUIT_POWER = new Type(Effects.CONDUIT_POWER);
		public static final Type BAD_OMEN = new Type(Effects.BAD_OMEN);
		public static final Type HERO_OF_THE_VILLAGE = new Type(Effects.HERO_OF_THE_VILLAGE);

		private final net.minecraft.potion.Effect effect;

		private Type(net.minecraft.potion.Effect effect) {
			this.effect = effect;
		}

	}

}
