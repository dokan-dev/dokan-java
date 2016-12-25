package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.dokany.java.Utils;
import com.sun.jna.Native;

public class Win32FindData extends FileInfo {
	public static final int MAX_PATH = 260;

	public int dwReserved0;
	public int dwReserved1;
	public char[] cFileName = new char[MAX_PATH];
	public char[] cAlternateFileName = new char[14];

	Win32FindData(final Builder builder) {
		super(builder);
		setFileName(builder.name, Utils.toShortName(builder.name));
	}

	public String getFileName() {
		return Native.toString(cFileName);
	}

	private void setFileName(final String name, final String shortName) {
		name.getChars(0, name.length(), cFileName, 0);
		// Utils.toShortName(name).getChars(0, shortName.length(),
		// cAlternateFileName, 0);
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