package com.worldsofminecraft.resource.png;

import java.nio.file.Path;

/**
 * An IPNGResource is used to reference a PNG file to be used within the project. It is used during build to create the project resources structures.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public interface IPNGResource {

	/**
	 * Gets the simple name of this IPNGResource. For example `banana.png` returns `banana`.
	 * @return The simple name of this IPNGResource
	 */
	public String getSimpleName();
	
	/**
	 * Gets the file name of this IPNGResource.
	 * @return the file name of this IPNGResource.
	 */
	public String getFileName();
	
	/**
	 * Returns the {@link Path} to the underlying png file.
	 * @return the {@link Path} to the underlying png file.
	 */
	public Path getPath();
	
}
