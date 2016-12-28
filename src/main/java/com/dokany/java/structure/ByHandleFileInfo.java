package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.WString;

/**
 *
 * Contains information that the {@link com.dokany.java.Operations.GetFileInformation} function retrieves.
 *
 * The identifier that is stored in the nFileIndexHigh and nFileIndexLow members is called the file ID. Support for file IDs is file system-specific. File IDs are not guaranteed to
 * be unique over time, because file systems are free to reuse them. In some cases, the file ID for a file can change over time.
 *
 * In the FAT file system, the file ID is generated from the first cluster of the containing directory and the byte offset within the directory of the entry for the file. Some
 * defragmentation products change this byte offset. (Windows in-box defragmentation does not.) Thus, a FAT file ID can change over time.Renaming a file in the FAT file system can
 * also change the file ID, but only if the new file name is longer than the old one.
 *
 * In the NTFS file system, a file keeps the same file ID until it is deleted. You can replace one file with another file without changing the file ID by using the ReplaceFile
 * function. However, the file ID of the replacement file, not the replaced file, is retained as the file ID of the resulting file.
 *
 * Not all file systems can record creation and last access time, and not all file systems record them in the same manner. For example, on a Windows FAT file system, create time
 * has a resolution of 10 milliseconds, write time has a resolution of 2 seconds, and access time has a resolution of 1 day (the access date). On the NTFS file system, access time
 * has a resolution of 1 hour.
 *
 *
 */
public class ByHandleFileInfo extends BaseFileInfo {

	private final static Logger logger = LoggerFactory.getLogger(ByHandleFileInfo.class);

	public int dwNumberOfLinks = 1;
	public int dwVolumeSerialNumber;

	public long fileIndex;

	public String fileName;

	public long fileSize;

	/*
	 * The high-order DWORD value of the file size, in bytes. This value is zero unless the file size is greater than MAXDWORD. The size of the file is equal to (nFileSizeHigh*
	 * (MAXDWORD+1)) + nFileSizeLow.
	 */
	public int nFileIndexHigh;

	/*
	 * The low-order DWORD value of the file size, in bytes.
	 */
	public int nFileIndexLow;

	private FileInfoBuilder fileInfoBuilder;

	public ByHandleFileInfo() {
	}

	ByHandleFileInfo(final FileInfoBuilder builder) {
		super(builder);
		setVariables(builder.name, builder.size, builder.index, builder.numberOfLinks, builder.volumeSerialNumber);
		fileInfoBuilder = builder;
	}

	void setVariables(final String name, final long size, final long index, final int numberOfLinks, final int volumeSerialNumber) {
		fileName = name;

		size(size);
		index(index);

		dwNumberOfLinks = numberOfLinks;
		dwVolumeSerialNumber = volumeSerialNumber;
	}

	public Win32FindData toWin32FindData() {
		if (fileInfoBuilder == null) {
			throw new IllegalStateException("Builder cannot be null");
		}

		return fileInfoBuilder.buildWin32FindData();
	}

	public void copyTo(final ByHandleFileInfo info) {
		info.setVariables(dwFileAttributes, ftCreationTime, ftLastAccessTime, ftLastWriteTime);
		info.setVariables(fileName, fileSize, fileIndex, dwNumberOfLinks, dwVolumeSerialNumber);
	}

	@Override
	public String toString() {
		return "FileInfo{" +
		        "attributes=" + dwFileAttributes +
		        ", creationTime=" + ftCreationTime +
		        ", lastAccessTime=" + ftLastAccessTime +
		        ", lastWriteTime=" + ftLastWriteTime +
		        ", fileSize=" + fileSize +
		        ", fileName='" + fileName + '\'' +
		        ", fileIndex=" + fileIndex +
		        ", numberOfLinks=" + dwNumberOfLinks +
		        ", volumeSerialNumber=" + dwVolumeSerialNumber +
		        '}';
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
		        "dwFileAttributes",
		        "dwNumberOfLinks", "dwVolumeSerialNumber",
		        "fileIndex",
		        "fileName",
		        "fileSize",
		        "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime",
		        "nFileIndexHigh", "nFileIndexLow",
		        "nFileSizeHigh", "nFileSizeLow");
	}

	void size(final long size) {
		fileSize = size;
		nFileSizeHigh = (int) ((size >> 32L) & 0xFFFFFFFFL);
		nFileSizeLow = (int) ((size >> 0L) & 0xFFFFFFFFL);
	}

	void index(final long index) {
		fileIndex = index;
		nFileIndexHigh = (int) ((index >> 32L) & 0xFFFFFFFFL);
		nFileIndexLow = (int) ((index >> 0L) & 0xFFFFFFFFL);
	}

	public static class FileInfoBuilder extends BaseFileInfoBuilder {

		final String name;
		private long size;
		private long index;
		public int numberOfLinks = 1;
		public int volumeSerialNumber;

		public FileInfoBuilder(final String name) {
			this.name = name;
		}

		public FileInfoBuilder(final WString name) {
			this(name.toString());
		}

		public FileInfoBuilder size(final long size) {
			this.size = size;
			return this;
		}

		public FileInfoBuilder index(final long index) {
			this.index = index;
			return this;
		}

		public FileInfoBuilder numberOfLinks(final int numberOfLinks) {
			this.numberOfLinks = numberOfLinks;
			return this;
		}

		public FileInfoBuilder volumeSerialNumber(final int volumeSerialNumber) {
			this.volumeSerialNumber = volumeSerialNumber;
			return this;
		}

		public ByHandleFileInfo build() {
			return new ByHandleFileInfo(this);
		}

		public Win32FindData buildWin32FindData() {
			return new Win32FindData(this);
		}
	}
}