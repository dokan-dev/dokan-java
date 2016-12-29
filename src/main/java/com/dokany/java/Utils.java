package com.dokany.java;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	public static final String BACKSLASH = "\\";
	private final static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static String trimTailBackslash(final String path) {
		String toReturn = path;
		if (path.endsWith(BACKSLASH)) {
			toReturn = path.substring(0, path.length() - 1);
		}
		return toReturn;
	}

	public static Path toUnixStylePath(final Path path) {
		return Paths.get(FilenameUtils.normalize(path.toString(), true));
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

		String base = getFileName(pathAsStr);
		if (base.length() > 8) {
			base = base.substring(0, 8);
		}
		logger.trace("base: {}", base);

		String ext = getExtension(pathAsStr);
		if (ext.length() > 3) {
			ext = ext.substring(3);
		}
		if (ext.length() > 0) {
			ext = "." + ext;
		}
		logger.trace("ext: {}", ext);

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
