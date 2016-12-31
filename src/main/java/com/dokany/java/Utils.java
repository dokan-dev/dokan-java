package com.dokany.java;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.WString;

public class Utils {
	public static final String FORWARD_SLASH = "/";
	private final static Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	public static String trimTailSlash(final String str) {
		String toReturn = str;
		if (str.endsWith(FORWARD_SLASH)) {
			toReturn = str.substring(0, str.length() - 1);
		}
		return toReturn;
	}

	public static String trimFrontSlash(final String str) {
		String toReturn = str;
		if (str.startsWith(FORWARD_SLASH)) {
			toReturn = str.substring(1, str.length());
		}
		return toReturn;
	}

	public static String normalize(final String path) {
		return FilenameUtils.normalize(path, true);
	}

	public static String normalize(final WString path) {
		return normalize(path.toString());
	}

	public static Path toUnixStylePath(final Path path) {
		return Paths.get(normalize(path.toString()));
	}

	/**
	 * Split apart path using {@link java.nio.file.Path#forEach()}.
	 *
	 * @param path
	 * @return array containing parts between slashes.
	 */
	public static Set<Path> getPathParts(final Path path) {
		final Set<Path> toReturn = new LinkedHashSet<>();
		final Path unixPath = toUnixStylePath(path);
		for (int i = 0; i < unixPath.getNameCount(); ++i) {
			toReturn.add(unixPath.getName(i));
		}

		return toReturn;

	}

	static String getFileName(final String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

	static String getExtension(final String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	public static String toShortName(final Path path) {
		final String pathAsStr = path.toString();

		final String base = trimStrToSize(getFileName(pathAsStr), 8);
		LOGGER.trace("base: {}", base);

		String ext = trimStrToSize(getExtension(pathAsStr), 3);
		if (ext.length() > 0) {
			ext = "." + ext;
		}
		LOGGER.trace("ext: {}", ext);
		return base + ext;
	}

	public static String trimStrToSize(final String str, final int len) {
		return str.substring(0, Math.min(str.length(), len));
	}

	/**
	 * Test whether object is null
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isNull(final Object obj) {
		return obj == null;
	}

	/**
	 * Test whether object is null
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(final Object obj) {
		return !isNull(obj);
	}
}
