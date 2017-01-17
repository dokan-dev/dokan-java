package com.dokany.java;

import static com.dokany.java.constants.ErrorCode.ERROR_ALREADY_EXISTS;
import static com.dokany.java.constants.ErrorCode.ERROR_FILE_NOT_FOUND;
import static com.dokany.java.constants.NtStatus.Unsuccessful;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.constants.EnumInteger;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.EnumIntegerSet;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;

public class DokanyUtils {
	// public static final String FORWARD_SLASH = "/";
	private final static Logger LOGGER = LoggerFactory.getLogger(DokanyUtils.class);

	public static String trimTailSeparator(final String str) {
		String toReturn = str;
		if (str.endsWith(File.separator)) {
			toReturn = str.substring(0, str.length() - 1);
		}
		return toReturn;
	}

	public static String trimFrontSeparator(final String str) {
		String toReturn = str;
		if (str.startsWith(File.separator)) {
			toReturn = str.substring(1, str.length());
		}
		return toReturn;
	}

	public static Path getPath(final String path) {
		return Paths.get(path);
	}

	public static File toFile(final String path) {
		return getPath(path).toFile();
	}

	public static String normalizeWithTrailingSlash(final String path) {
		String newPath = normalize(path);
		final int lastSeparatorIndex = FilenameUtils.indexOfLastSeparator(path);
		if (lastSeparatorIndex != path.length()) {
			newPath += File.separator;
		}
		return FilenameUtils.separatorsToSystem(newPath);
	}

	public static String normalize(final String path) {
		return FilenameUtils.normalize(path, true);
	}

	public static String normalize(final WString path) {
		return normalize(path.toString());
	}

	public static String normalize(final Path path) {
		return normalize(path.toString());
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

	static long exceptionToErrorCode(final Throwable t) {
		return exceptionToErrorCode(t, Unsuccessful.mask());
	}

	static long exceptionToErrorCode(final Throwable t, final long defaultCode) {
		LOGGER.warn(t.getMessage(), t);

		if (t instanceof DokanyException) {
			return ((DokanyException) t).val();
		}
		if (t instanceof FileNotFoundException) {
			return ERROR_FILE_NOT_FOUND.mask();
		}
		if (t instanceof FileAlreadyExistsException) {
			return ERROR_ALREADY_EXISTS.mask();
		}

		return defaultCode;
	}

	public static FileTime toFileTime(final FILETIME time) {
		return FileTime.from(time.toDate().toInstant());
	}

	public static FILETIME toFILETIME(final FileTime time) {
		return getTime(time.toMillis());
	}

	public static FILETIME getTime(final Date date) {
		return new FILETIME(date);
	}

	public static FILETIME getTime(final long time) {
		return getTime(new Date(time));
	}

	public static FILETIME getCurrentTime() {
		return getTime(new Date());
	}

	public static final LARGE_INTEGER getLargeInt(final long val, final int high, final int low) {
		LARGE_INTEGER largeInt = null;
		if ((val != 0) && ((high == 0) || (low == 0))) {
			largeInt = new LARGE_INTEGER(val);
		}
		return largeInt;
	}

	public static BasicFileAttributeView getBasicAttributes(final String path) {
		return getBasicAttributes(getPath(path));
	}

	public static BasicFileAttributeView getBasicAttributes(final Path path) {
		return Files.getFileAttributeView(path, BasicFileAttributeView.class);
	}

	public static <T extends Enum<T>> EnumIntegerSet<T> enumSetFromInt(final int value, final T[] allEnumValues) {
		final EnumIntegerSet<T> elements = new EnumIntegerSet<>(allEnumValues[0].getDeclaringClass());
		int remainingValues = value;
		for (final T current : allEnumValues) {
			final int mask = ((EnumInteger) current).mask();

			if ((remainingValues & mask) == mask) {
				elements.add(current);
				remainingValues -= mask;
			}
		}
		return elements;
	}

	public static <T extends EnumInteger> T enumFromInt(final int value, final T[] enumValues) {
		for (final T current : enumValues) {
			if ((value & current.mask()) == current.mask()) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value: " + value);
	}

	public static void setDeleteStatus(final File fileOrDirectory, final DokanyFileInfo dokanyFileInfo) {
		final boolean canDelete = fileOrDirectory.renameTo(fileOrDirectory);

		if (canDelete) {
			dokanyFileInfo._deleteOnClose = 1;
		}
	}
}
