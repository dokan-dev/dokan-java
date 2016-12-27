package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.dokany.java.Utils;
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
 * for a file by up to 1 hour after the last access.For more information, see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/ms724290(v=vs.85).aspx">File Times
 * (MSDN)</a>.
 *
 */
public class Win32FindData extends FileInfo {
	public static final int MAX_PATH = 260;

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

	Win32FindData(final Builder builder) {
		super(builder);
		setFileName(builder.name, Utils.toShortName(builder.name));
	}

	public final String getFileName() {
		return Native.toString(cFileName);
	}

	private final void setFileName(final String name, final String shortName) {
		name.getChars(0, name.length(), cFileName, 0);
		shortName.getChars(0, shortName.length(), cAlternateFileName, 0);
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
		        "cAlternateFileName",
		        "cFileName",
		        "dwFileAttributes",
		        "dwNumberOfLinks",
		        "dwReserved0",
		        "dwReserved1",
		        "dwVolumeSerialNumber",
		        "fileIndex",
		        "fileName",
		        "fileSize",
		        "ftCreationTime",
		        "ftLastAccessTime",
		        "ftLastWriteTime",
		        "nFileIndexHigh",
		        "nFileIndexLow",
		        "nFileSizeHigh",
		        "nFileSizeLow");
	}
}