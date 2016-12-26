package com.dokany.java;

import com.google.common.io.Files;

public class Utils {

	public static final String BACKSLASH = "\\";
	public static final String FORWARDSLASH = "/";

	public static String trimTailBackSlash(final String path) {
		final String toReturn = path;
		if (path.endsWith(BACKSLASH)) {
			return path.substring(0, path.length() - 1);
		}
		return toReturn;
	}

	public static String toUnixStylePath(final String path) {
		final String unixPath = path.replace(BACKSLASH, FORWARDSLASH);
		System.out.println("path:" + unixPath);
		return unixPath;
	}

	/**
	 * Split apart path using split("/").
	 *
	 * @param path
	 * @return array containing parts between slashes.
	 */
	public static String[] getPathParts(final String path) {
		return toUnixStylePath(path).split(FORWARDSLASH);
	}

	/**
	 * Get file name from partParts.
	 *
	 * @param pathParts
	 * @return actual file name
	 */
	public static String getFileNameFromPath(final String[] pathParts) {
		return pathParts[pathParts.length - 1];
	}

	public static String getFileName(final String fileName) {
		return Files.getNameWithoutExtension(fileName);
	}

	public static String getExtension(final String fileName) {
		return Files.getFileExtension(fileName);
	}

	// This is not correct.
	public static String toShortName(final String fileName) {
		String base = getFileName(fileName);
		if (base.length() > 8) {
			base = base.substring(8);
		}

		String ext = getExtension(fileName);
		if (ext.length() > 3) {
			ext = ext.substring(3);
		}
		if (ext.length() > 0) {
			ext = "." + ext;
		}

		return base + ext;
	}

	public static String trimStrToSize(final String str, final int len) {
		return str.substring(0, Math.min(str.length(), len));
	}
}
