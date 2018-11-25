package com.dokan.java;

import com.dokan.java.structure.ByHandleFileInformation;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Date;

/**
 * Utilities to do various operations.
 */
public class DokanyUtils {

	public static String UNIX_SEPARATOR = FilenameUtils.separatorsToUnix(File.separator);

	private static final Logger LOG = LoggerFactory.getLogger(DokanyUtils.class);

	private DokanyUtils() {

	}

	/**
	 * Uses *nix separator
	 *
	 * @param str
	 * @return
	 */
	public static String trimTailSeparator(final String str) {
		return str.endsWith(UNIX_SEPARATOR) ? str.substring(0, str.length() - 1) : str;
	}

	/**
	 * Uses *nix separator
	 *
	 * @param str
	 * @return
	 */
	public static String trimFrontSeparator(final String str) {
		return str.startsWith(UNIX_SEPARATOR) ? str.substring(1, str.length()) : str;
	}

	// TODO: can this return null?

	/**
	 * @param path
	 * @return
	 */
	public static File toFile(final String path) {
		return Paths.get(path).toFile();
	}


	/**
	 * Will add tail UNIX_SEPARATOR if file is a directory and tail separator is not already present
	 *
	 * @param path
	 * @return
	 */
	public static String normalize(final String path) {
		String normalizedPath = FilenameUtils.normalize(path, true);

		if (new File(normalizedPath).isDirectory()) {
			final int lastSeparator = indexOfLastSeparator(normalizedPath);

			if ((lastSeparator == -1) || (lastSeparator != (normalizedPath.length() - 1))) {
				normalizedPath += UNIX_SEPARATOR;
			}
		}
		return normalizedPath;
	}

	public static int indexOfLastSeparator(final String normalizedPath) {
		return FilenameUtils.indexOfLastSeparator(normalizedPath);
	}

	// TODO: can this return null?
	public static String normalize(final WString path) {
		return normalize(path.toString());
	}

	// TODO: can this return null?
	public static String normalize(final Path path) {
		return normalize(path.toString());
	}

	// TODO: can this return null?
	static String getFileName(final String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

	// TODO: can this return null?
	static String getExtension(final String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	// TODO: can this return null?
	public static String toShortName(final Path path) {
		String pathAsStr = path.toString();

		String base = trimStrToSize(getFileName(pathAsStr), 8);
		LOG.trace("base: {}", base);

		String ext = trimStrToSize(getExtension(pathAsStr), 3);
		if (ext.length() > 0) {
			ext = "." + ext;
		}
		LOG.trace("ext: {}", ext);
		return base + ext;
	}


	public static String trimStrToSize(final String str, final int len) {
		return str.substring(0, Math.min(str.length(), len));
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

	/**
	 * Creates a {@link WIN32_FIND_DATA} structure from {@link ByHandleFileInformation}.
	 * TODO: computation of dwReserved 0 is unfinished. REPARSEPOINTS are not handled.
	 * @param fileInformation the ByHandleFileInformation object providing the needed information.
	 * @return a {@link WIN32_FIND_DATA} object containing the information of ByHandleFileInformation
	 */
	public static WIN32_FIND_DATA win32FindDataStructureFromByHandleFileInformation(ByHandleFileInformation fileInformation){
		final char[] cFileName = trimStrToSize(fileInformation.getFilePath().toString(),WinBase.MAX_PATH).toCharArray(); //trimFrontSeparator(DokanyUtils.trimStrToSize(filePath.toString(), 260)).toCharArray();
		final char[] cAlternateFileName = new char[14];
		int dwReserved0 = 0x0;
		int dwReserved1 = 0x0;
		return new WIN32_FIND_DATA(fileInformation.dwFileAttributes,
				fileInformation.ftCreationTime,
				fileInformation.ftLastAccessTime,
				fileInformation.ftLastWriteTime,
				fileInformation.nFileSizeHigh,
				fileInformation.nFileSizeLow,
				dwReserved0,
				dwReserved1,
				cFileName,
				cAlternateFileName);

	}

    public static boolean canHandleShutdownHooks() {
        SecurityManager security = System.getSecurityManager();
        if (security == null) {
            return true;
        }
        try {
            security.checkPermission(new RuntimePermission("shutdownHooks"));
            return true;
        } catch (final SecurityException e) {
            return false;
        }
    }

}
