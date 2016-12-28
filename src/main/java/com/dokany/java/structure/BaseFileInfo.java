package com.dokany.java.structure;

import java.util.Date;

import com.dokany.java.constants.FileAttribute;
import com.sun.jna.Structure;

public abstract class BaseFileInfo extends Structure implements Structure.ByReference {

	/*
	 * The file attributes of a file. For possible values and their descriptions, see <see cref="FileAttributes"/>. The <see cref="FileAttributes.SparseFile"/> attribute on the
	 * file is set if any of the streams of the file have ever been sparse.
	 */
	public int dwFileAttributes;

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

	public int nFileSizeHigh;
	public int nFileSizeLow;

	BaseFileInfo() {
	}

	BaseFileInfoBuilder baseFileInfoBuilder;

	BaseFileInfo(final BaseFileInfoBuilder builder) {
		setVariables(builder.attributes, builder.creationTime, builder.lastAccessTime, builder.lastWriteTime);
		baseFileInfoBuilder = builder;
	}

	void setVariables(final int attributes, final FileTime.VAL creationTime, final FileTime.VAL lastAccessTime, final FileTime.VAL lastWriteTime) {
		dwFileAttributes = attributes;
		ftCreationTime = creationTime;
		ftLastAccessTime = lastAccessTime;
		ftLastWriteTime = lastWriteTime;
	}

	public static class BaseFileInfoBuilder {

		private int attributes;
		private FileTime.VAL creationTime;
		private FileTime.VAL lastAccessTime;
		private FileTime.VAL lastWriteTime;

		public BaseFileInfoBuilder attributes(final FileAttribute... attributes) {
			this.attributes = FileAttribute.fromAttributes(attributes);
			return this;
		}

		public BaseFileInfoBuilder creationTime(final Date creationTime) {
			return creationTime(new FileTime.VAL(creationTime));
		}

		public BaseFileInfoBuilder creationTime(final FileTime.VAL creationTime) {
			this.creationTime = creationTime;
			return this;
		}

		public BaseFileInfoBuilder lastAccessTime(final Date lastAccessTime) {
			return lastAccessTime(new FileTime.VAL(lastAccessTime));
		}

		public BaseFileInfoBuilder lastAccessTime(final FileTime.VAL lastAccessTime) {
			this.lastAccessTime = lastAccessTime;
			return this;
		}

		public BaseFileInfoBuilder lastWriteTime(final Date lastWriteTime) {
			return lastWriteTime(new FileTime.VAL(lastWriteTime));
		}

		/**
		 * Also sets lastAccessTime to same time
		 *
		 * @param lastWriteTime
		 * @return
		 */
		public BaseFileInfoBuilder lastWriteTime(final FileTime.VAL lastWriteTime) {
			lastAccessTime(lastWriteTime);
			this.lastWriteTime = lastWriteTime;
			return this;
		}
	}

}
