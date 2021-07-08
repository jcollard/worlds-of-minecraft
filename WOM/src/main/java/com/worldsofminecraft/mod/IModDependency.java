package com.worldsofminecraft.mod;

public interface IModDependency {

	public String getModId();
	public boolean isMandatory();
	public String getVersionRange();
	public String getOrdering();
	public String getSide();
	
}
