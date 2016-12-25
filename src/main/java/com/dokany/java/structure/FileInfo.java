package com.dokany.java.structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.dokany.java.constants.FileAttribute;
import com.sun.jna.Structure;

public class FileInfo extends Structure implements Structure.ByReference {
	public int dwFileAttributes;

	public int dwNumberOfLinks;
	public int dwVolumeSerialNumber;

	public long fileIndex;

	public String fileName;

	public long fileSize;

	public FileTime.VAL ftCreationTime;
	public FileTime.VAL ftLastAccessTime;
	public FileTime.VAL ftLastWriteTime;

	public int nFileIndexHigh;
	public int nFileIndexLow;

	public int nFileSizeHigh;
	public int nFileSizeLow;

	private Builder builder;

	public FileInfo() {
	}

	FileInfo(final Builder builder) {
		this.builder = builder;
		setVariables(builder.name, builder.size, builder.attributes, builder.creationTime, builder.lastAccessTime, builder.lastWriteTime, builder.index);
	}

	FileInfo(final String name, final long size, final FileAttribute attributes, final FileTime.VAL creationTime, final FileTime.VAL lastAccessTime, final FileTime.VAL lastWriteTime, final long index) {
		this(new Builder(name));
	}

	private void setVariables(final String name, final long size, final FileAttribute attributes, final FileTime.VAL creationTime, final FileTime.VAL lastAccessTime, final FileTime.VAL lastWriteTime, final long index) {
		fileName = name;

		size(size);
		index(index);

		if (attributes != null) {
			dwFileAttributes = attributes.val;
		}
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

	public void setByHandleFileInfo(final ByHandleFileInformation info) {
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
		private FileAttribute attributes;
		private FileTime.VAL creationTime;
		private FileTime.VAL lastAccessTime;
		private FileTime.VAL lastWriteTime;

		private long size;
		private long index;

		public Builder(final String name) {
			this.name = name;
		}

		public FileInfo buildFileInfo() {
			return new FileInfo(this);
		}

		public Win32FindData buildWin32FindData() {
			return new Win32FindData(this);
		}

		public ByHandleFileInformation buildByHandleFileInformation(final int volumeSerialNumber, final int numberOfLinks) {
			return new ByHandleFileInformation(this, volumeSerialNumber, numberOfLinks);
		}

		public Builder size(final long size) {
			this.size = size;
			return this;
		}

		public Builder attributes(final FileAttribute attributes) {
			this.attributes = attributes;
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