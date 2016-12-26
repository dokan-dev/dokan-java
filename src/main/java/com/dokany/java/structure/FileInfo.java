package com.dokany.java.structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.dokany.java.constants.FileAttribute;
import com.sun.jna.Structure;
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
public class FileInfo extends Structure implements Structure.ByReference {

	/*
	 * The file attributes of a file. For possible values and their descriptions, see <see cref="FileAttributes"/>. The <see cref="FileAttributes.SparseFile"/> attribute on the
	 * file is set if any of the streams of the file have ever been sparse.
	 */
	public int dwFileAttributes;

	public int dwNumberOfLinks = 1;
	public int dwVolumeSerialNumber;

	public long fileIndex;

	public String fileName;

	public long fileSize;
	/*
	 * A FileTime structure that specifies when a file or directory was created. If the underlying file system does not support creation time, this member is zero.
	 */
	public FileTime.VAL ftCreationTime;

	/*
	 * For a file, the structure specifies when the file was last read from, written to, or for executable files, run. For a directory, the structure specifies when the directory
	 * is created. If the underlying file system does not support last access time, this member is zero. On the FAT file system, the specified date for both files and directories
	 * is correct, but the time of day is always set to midnight.
	 */
	public FileTime.VAL ftLastAccessTime;

	/*
	 * For a file, the structure specifies when the file was last written to, truncated, or overwritten, for example, when WriteFile or SetEndOfFile are used. The date and time are
	 * not updated when file attributes or security descriptors are changed. For a directory, the structure specifies when the directory is created. If the underlying file system
	 * does not support last write time,
	 */
	public FileTime.VAL ftLastWriteTime;

	/*
	 * The high-order DWORD value of the file size, in bytes. This value is zero unless the file size is greater than MAXDWORD. The size of the file is equal to (nFileSizeHigh*
	 * (MAXDWORD+1)) + nFileSizeLow.
	 */
	public int nFileIndexHigh;

	/*
	 * The low-order DWORD value of the file size, in bytes.
	 */
	public int nFileIndexLow;

	/*
	 *
	 */
	public int nFileSizeHigh;
	public int nFileSizeLow;

	private Builder builder;

	public FileInfo() {
	}

	FileInfo(final Builder builder) {
		this.builder = builder;
		setVariables(builder.name, builder.size, builder.attributes, builder.creationTime, builder.lastAccessTime, builder.lastWriteTime, builder.index);
	}
/*
	FileInfo(
	        final String name,
	        final long size,
	        final FileAttribute attributes,
	        final FileTime.VAL creationTime,
	        final FileTime.VAL lastAccessTime,
	        final FileTime.VAL lastWriteTime,
	        final long index) {
		this(new Builder(name));
	}*/

	private void setVariables(final String name, final long size, final int attributes, final FileTime.VAL creationTime, final FileTime.VAL lastAccessTime,
	        final FileTime.VAL lastWriteTime, final long index) {
		fileName = name;

		size(size);
		index(index);

		dwFileAttributes = attributes;
		ftCreationTime = creationTime;
		ftLastAccessTime = lastAccessTime;
		ftLastWriteTime = lastWriteTime;
	}

	public Win32FindData toWin32FindData() {
		if (builder == null) {
			throw new IllegalStateException("Builder cannot be null");
		}
		return builder.buildWin32FindData();
	}

	public ByHandleFileInformation toByHandleFileInformation(final int numberOfLinks, final int volumeSerialNumber) {
		if (builder == null) {
			throw new IllegalStateException("Builder cannot be null");
		}
		return builder.buildByHandleFileInformation(numberOfLinks, volumeSerialNumber);
	}

	public void copyTo(final ByHandleFileInformation info) {
		info.index(fileIndex);
		info.size(fileSize);
		info.dwNumberOfLinks = dwNumberOfLinks;
		info.dwFileAttributes = dwFileAttributes;
		info.ftCreationTime = ftCreationTime;
		info.ftLastAccessTime = ftLastAccessTime;
		info.ftLastWriteTime = ftLastWriteTime;
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

	public static class Builder {
		final String name;
		private int attributes;
		private FileTime.VAL creationTime;
		private FileTime.VAL lastAccessTime;
		private FileTime.VAL lastWriteTime;

		private long size;
		private long index;

		public Builder(final WString name) {
			this(name.toString());
		}

		public Builder(final String name) {
			this.name = name;
		}

		public FileInfo buildFileInfo() {
			return new FileInfo(this);
		}

		public Win32FindData buildWin32FindData() {
			return new Win32FindData(this);
		}

		public ByHandleFileInformation buildByHandleFileInformation(final int numberOfLinks, final int volumeSerialNumber) {
			return new ByHandleFileInformation(this, numberOfLinks, volumeSerialNumber);
		}

		public Builder size(final long size) {
			this.size = size;
			return this;
		}

		public Builder attributes(final FileAttribute... attributes) {
			this.attributes = FileAttribute.fromAttributes(attributes);
			return this;
		}

		public Builder creationTime(final Date creationTime) {
			return creationTime(new FileTime.VAL(creationTime));
		}

		public Builder creationTime(final FileTime.VAL creationTime) {
			this.creationTime = creationTime;
			return this;
		}

		public Builder lastAccessTime(final Date lastAccessTime) {
			return lastAccessTime(new FileTime.VAL(lastAccessTime));
		}

		public Builder lastAccessTime(final FileTime.VAL lastAccessTime) {
			this.lastAccessTime = lastAccessTime;
			return this;
		}

		public Builder lastWriteTime(final Date lastWriteTime) {
			return lastWriteTime(new FileTime.VAL(lastWriteTime));
		}

		/**
		 * Also sets lastAccessTime to same time
		 *
		 * @param lastWriteTime
		 * @return
		 */
		public Builder lastWriteTime(final FileTime.VAL lastWriteTime) {
			lastAccessTime(lastWriteTime);
			this.lastWriteTime = lastWriteTime;
			return this;
		}
	}
}