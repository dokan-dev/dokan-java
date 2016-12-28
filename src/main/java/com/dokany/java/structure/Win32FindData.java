package com.dokany.java.structure;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.Utils;
import com.dokany.java.structure.ByHandleFileInfo.FileInfoBuilder;
import com.sun.jna.Native;

/**
 * Contains information about the file that is found by the FindFirstFile, FindFirstFileEx, or FindNextFile function. If a file has a long file name, the complete name appears in
 * the cFileName member, and the 8.3 format truncated version of the name appears in {@link com.dokany.java.structure.Win32FindData#cAlternateFileName}. Otherwise,
 * {@link com.dokany.java.structure.Win32FindData#cAlternateFileName} is empty. If the FindFirstFileEx function was called with a value of FindExInfoBasic in the fInfoLevelId
 * parameter, the <see cref="cAlternateFileName"/> member will always contain a NULL string value. This remains true for all subsequent calls to the FindNextFile function. As an
 * alternative method of retrieving the 8.3 format version of a file name, you can use the GetShortPathName function. For more information about file names, see
 * <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa365247(v=vs.85).aspx">Naming Files, Paths, and Namespaces (MSDN)</a>.
 *
 * Not all file systems can record creation and last access times, and not all file systems record them in the same manner. For example, on the FAT file system, create time has a
 * resolution of 10 milliseconds, write time has a resolution of 2 seconds, and access time has a resolution of 1 day. The NTFS file system delays updates to the last access time
 * for a file by up to 1 hour after the last access. For more information, see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/ms724290(v=vs.85).aspx">File Times
 * (MSDN)</a>.
 *
 */
public class Win32FindData extends BaseFileInfo {
	public static final int MAX_PATH = 260;
	private final static Logger logger = LoggerFactory.getLogger(Win32FindData.class);

	/**
	 * If the {@link com.dokany.java.structure.FileInfo#dwFileAttributes} includes {@link com.dokany.java.constants.FileAttribute#FILE_ATTRIBUTE_REPARSE_POINT}, this member
	 * specifies the reparse point tag. Otherwise, this value is undefined and should not be used.
	 */
	public int dwReserved0;

	// Reserved for future use.
	public int dwReserved1;

	// The name of the file
	public char[] cFileName = new char[MAX_PATH];

	// An alternative name for the file. This name is in the classic 8.3 file name format.
	public char[] cAlternateFileName = new char[14];

	Win32FindData(final BaseFileInfoBuilder builder, final Path path) {
		super(builder);
		setFileName(path.toString(), Utils.toShortName(path));
	}

	Win32FindData(final FileInfoBuilder builder) {
		super(builder);
		setFileName(builder.path.toString(), Utils.toShortName(builder.path));
	}

	public final String getFileName() {
		return Native.toString(cFileName);
	}

	private final void setFileName(final String name, final String shortName) {
		logger.debug("set({}, {})", name, shortName);
		if (Utils.isNull(name)) {
			throw new IllegalStateException("name cannot be null");
		}
		name.getChars(0, name.length(), cFileName, 0);

		if (Utils.isNotNull(shortName)) {
			shortName.getChars(0, shortName.length(), cAlternateFileName, 0);
		}
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
		        "dwFileAttributes",
		        "ftCreationTime",
		        "ftLastAccessTime",
		        "ftLastWriteTime",
		        "nFileSizeHigh",
		        "nFileSizeLow",
		        "dwReserved0",
		        "dwReserved1",
		        "cFileName",
		        "cAlternateFileName");
	}
}