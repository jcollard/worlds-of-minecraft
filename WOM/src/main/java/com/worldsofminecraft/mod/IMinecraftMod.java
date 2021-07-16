package com.worldsofminecraft.mod;

import java.util.List;

import com.worldsofminecraft.resource.png.IPNGResource;

public interface IMinecraftMod {

	public String getModLoader();

	public String getLoaderVersion();

	public String getLicense();

	public String getModId();

	public String getVersion();

	public String getDisplayName();

	public String getUpdateJSONURL();

	public String getIssueTrackerURL();

	public String getDisplayURL();

	public IPNGResource getLogo();

	public String getCredits();

	public String getAuthors();

	public String getDescription();

	public List<IModDependency> getDependencies();

}
