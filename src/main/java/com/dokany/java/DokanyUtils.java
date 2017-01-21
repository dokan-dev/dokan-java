package com.dokany.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;

import com.dokany.java.constants.EnumInteger;
import com.dokany.java.constants.ErrorCode;
import com.dokany.java.constants.NtStatus;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.EnumIntegerSet;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;

import lombok.NonNull;
import lombok.val;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * Utilities to do various operations.
 *
 */
@UtilityClass
@Slf4j
public class DokanyUtils {
	/**
	 * Uses *nix separator
	 *
	 * @param str
	 * @return
	 */
	public String trimTailSeparator(@NonNull final String str) {
		return str.endsWith(UNIX_SEPARATOR) ? str.substring(0, str.length() - 1) : str;
	}

	/**
	 * Uses *nix separator
	 *
	 * @param str
	 * @return
	 */
	@NonNull
	public String trimFrontSeparator(@NonNull final String str) {
		return str.startsWith(UNIX_SEPARATOR) ? str.substring(1, str.length()) : str;
	}

	// TODO: can this return null?
	public Path getPath(@NonNull final String path) {
		return Paths.get(path);
	}

	// TODO: can this return null?
	public File toFile(@NonNull final String path) {
		return getPath(path).toFile();
	}

	public String UNIX_SEPARATOR = FilenameUtils.separatorsToUnix(File.separator);

	/**
	 * Will add tail UNIX_SEPARATOR if file is a directory and tail separator is not already present
	 *
	 * @param path
	 * @return
	 */
	public String normalize(@NonNull final String path) {
		String normalizedPath = FilenameUtils.normalize(path, true);

		if (new File(normalizedPath).isDirectory()) {
			final int lastSeparator = indexOfLastSeparator(normalizedPath);

			if ((lastSeparator == -1) || (lastSeparator != (normalizedPath.length() - 1))) {
				normalizedPath += UNIX_SEPARATOR;
			}
		}
		return normalizedPath;
	}

	public int indexOfLastSeparator(@NonNull final String normalizedPath) {
		return FilenameUtils.indexOfLastSeparator(normalizedPath);
	}

	// TODO: can this return null?
	public String normalize(@NonNull final WString path) {
		return normalize(path.toString());
	}

	// TODO: can this return null?
	public String normalize(@NonNull final Path path) {
		return normalize(path.toString());
	}

	// TODO: can this return null?
	String getFileName(@NonNull final String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

	// TODO: can this return null?
	String getExtension(@NonNull final String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	// TODO: can this return null?
	public String toShortName(@NonNull final Path path) {
		val pathAsStr = path.toString();

		val base = trimStrToSize(getFileName(pathAsStr), 8);
		log.trace("base: {}", base);

		String ext = trimStrToSize(getExtension(pathAsStr), 3);
		if (ext.length() > 0) {
			ext = "." + ext;
		}
		log.trace("ext: {}", ext);
		return base + ext;
	}

	@NonNull
	public String trimStrToSize(@NonNull final String str, final int len) {
		return str.substring(0, Math.min(str.length(), len));
	}

	long exceptionToErrorCode(@NonNull final Throwable t) {
		return exceptionToErrorCode(t, NtStatus.UNSUCCESSFUL.getMask());
	}

	long exceptionToErrorCode(@NonNull final Throwable t, final long defaultCode) {
		log.warn(t.getMessage(), t);

		if (t instanceof DokanyException) {
			return ((DokanyException) t).getVal();
		}
		if (t instanceof FileNotFoundException) {
			return ErrorCode.ERROR_FILE_NOT_FOUND.getMask();
		}
		if (t instanceof FileAlreadyExistsException) {
			return ErrorCode.ERROR_ALREADY_EXISTS.getMask();
		}

		return defaultCode;
	}

	public FileTime toFileTime(@NonNull final FILETIME time) {
		return FileTime.from(time.toDate().toInstant());
	}

	public FILETIME toFILETIME(@NonNull final FileTime time) {
		return getTime(time.toMillis());
	}

	public FILETIME getTime(@NonNull final Date date) {
		return new FILETIME(date);
	}

	public FILETIME getTime(final long time) {
		return getTime(new Date(time));
	}

	public FILETIME getCurrentTime() {
		return getTime(new Date());
	}

	public LARGE_INTEGER getLargeInt(final long val, final int high, final int low) {
		LARGE_INTEGER largeInt = null;
		if ((val != 0) && ((high == 0) || (low == 0))) {
			largeInt = new LARGE_INTEGER(val);
		}
		return largeInt;
	}

	/**
	 *
	 * @param path
	 * @return
	 */
	public BasicFileAttributeView getBasicAttributes(@NonNull final String path) {
		return getBasicAttributes(getPath(path));
	}

	/**
	 *
	 * @param path
	 * @return
	 */
	public BasicFileAttributeView getBasicAttributes(@NonNull final Path path) {
		return Files.getFileAttributeView(path, BasicFileAttributeView.class);
	}

	/**
	 * Will return an
	 *
	 * @param value
	 * @param allEnumValues
	 * @return
	 */
	@NonNull
	public <T extends Enum<T>> EnumIntegerSet<T> enumSetFromInt(final int value, final T[] allEnumValues) {
		val elements = new EnumIntegerSet<>(allEnumValues[0].getDeclaringClass());
		int remainingValues = value;
		for (val current : allEnumValues) {
			val mask = ((EnumInteger) current).getMask();

			if ((remainingValues & mask) == mask) {
				elements.add(current);
				remainingValues -= mask;
			}
		}
		return elements;
	}

	@NonNull
	public <T extends EnumInteger> T enumFromInt(final int value, @NonNull final T[] enumValues) {
		for (final T current : enumValues) {
			if ((value & current.getMask()) == current.getMask()) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value: " + value);
	}

	public void setDeleteStatus(@NonNull final File fileOrDirectory, @NonNull final DokanyFileInfo dokanyFileInfo) {
		val canDelete = fileOrDirectory.renameTo(fileOrDirectory);

		if (canDelete) {
			dokanyFileInfo.DeleteOnClose = 1;
		}
	}

	/**
	 * Returns String representation of WString.
	 *
	 * @param wStr .
	 * @return if wStr is null, method will return null
	 */
	public String wStrToStr(final WString wStr) {
		return Objects.nonNull(wStr) ? wStr.toString() : null;
	}
}
