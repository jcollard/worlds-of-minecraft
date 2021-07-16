package com.worldsofminecraft.resource.model.item;

import java.util.Map;

public interface IItemDisplay {

	public enum Position {
		THIRDPERSON_RIGHTHAND("thirdperson_righthand"), THIRDPERSON_LEFTHAND("thirdperson_lefthand"),
		FIRSTPERSON_RIGHTHAND("firstperson_righthand"), FIRSTPERSON_LEFTHAND("firstperson_lefthand"), GUI("gui"),
		HEAD("head"), GROUND("ground"), FIXED("fixed");

		public final String KEY;

		private Position(String key) {
			this.KEY = key;
		}

	}

	Map<Position, IItemTransform> getTransforms();

}
