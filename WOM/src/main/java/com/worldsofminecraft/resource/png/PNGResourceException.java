package com.worldsofminecraft.resource.png;

/**
 * A PNGResourceException is typically raised when an {@link IPNGResource} cannot be resolved.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class PNGResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PNGResourceException(String message) {
		super(message);
	}
	
}
