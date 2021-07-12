package com.worldsofminecraft.resource.png;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;

/**
 * A PNGResource is used to reference a PNG file within the project.
 * 
 * A PNGResource can be acquired by using the static
 * {@link PNGResource#getPNGResource} method.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public class PNGResource implements IPNGResource {

	private static final Map<Path, PNGResource> lookup = new HashMap<>();

	private final Path path;

	private PNGResource(Path p) {
		this.path = p;
	}

	/**
	 * Retrieve a {@link PNGResource} from a string path. The path provided is
	 * relative to the root directory of the project as specified by
	 * {@Utils#getRootDir}.
	 * 
	 * @param path The relative path to the PNG to be loaded
	 * @return a {@link PNGResource} from the relative path
	 * @throws {@link PNGResourceException} if the PNG cannot be resolved.
	 */
	public static PNGResource get(@Nonnull String path) {
		return get(Utils.getInstance().getRootDir().resolve(path));
	}

	public static void validateFileName(@Nonnull Path path) {
		if (!path.getFileName().toString().matches("^[a-z][a-z_0-9]*.png$")) {
			throw new PNGResourceException("Invalid PNG file name detected: \"" + path.getFileName().toString()
					+ "\". A PNG name end with \".png\", may contain digits, lower case letters, and underscores, and must begin with a letter.");
		}
	}

	/**
	 * Retrieve a {@link PNGResource} from a Path.
	 * 
	 * @param path The path to the PNG to be loaded
	 * @return a {@link PNGResource} from the relative path
	 * @throws {@link PNGResourceException} if the PNG cannot be resolved.
	 */
	public static PNGResource get(@Nonnull Path path) {
		Preconditions.checkArgument(path != null);
		validateFileName(path);

		// If we are live, we don't have access to the local file structure.
		if (Utils.getInstance().isLive()) {
			return new PNGResource(path);
		}

		if (Files.notExists(path)) {
			throw new PNGResourceException("No file found at " + path);
		}

		String type;
		try {
			type = Files.probeContentType(path);
			if (!type.toLowerCase().trim().equals("image/png")) {
				throw new PNGResourceException("The specified path \"" + path
						+ "\" does not appear to be a PNG. Expected the mime-type \"image/png\" but found \"" + type
						+ "\".");
			}

			path = path.toAbsolutePath();
			if (!lookup.containsKey(path)) {
				lookup.put(path, new PNGResource(path));
			}

			return lookup.get(path);
		} catch (IOException e) {
			throw new PNGResourceException(e.getMessage());
		}

	}

	@Override
	public String getSimpleName() {
		String name = path.getFileName().toString();
		return name.substring(0, name.length() - 4);
	}

	@Override
	public String getFileName() {
		return path.getFileName().toString();
	}

	@Override
	public Path getPath() {
		return path;
	}

}
