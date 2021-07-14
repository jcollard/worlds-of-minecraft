package com.worldsofminecraft.mod.exception;

public class BuildFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BuildFailedException(String message, Exception e) {
		super(message + " " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
	}
	
}
