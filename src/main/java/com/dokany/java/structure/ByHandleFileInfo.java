package com.dokany.java.structure;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.Utils;
import com.dokany.java.constants.FileAttribute;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;

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
public class ByHandleFileInfo extends Structure implements Structure.ByReference {

	private final static Logger logger = LoggerFactory.getLogger(ByHandleFileInfo.class);

	// Used to store actual values (instead of high/low) which can be retrieved using getter method
	private String fileName;
	private long fileIndex;
	private long fileSize;

	/**
	 * The high-order DWORD value of the file size, in bytes. This value is zero unless the file size is greater than MAXDWORD. The size of the file is equal to (nFileSizeHigh*
	 * (MAXDWORD+1)) + nFileSizeLow.
	 */
	public int nFileIndexHigh;

	/**
	 * The low-order DWORD value of the file size, in bytes.
	 */
	public int nFileIndexLow;

	/**
	 * The file attributes of a file. For possible values and their descriptions, see File Attribute Constants. The FILE_ATTRIBUTE_SPARSE_FILE attribute on the file is set if any
	 * of the streams of the file have ever been sparse.
	 */
	public int dwFileAttributes;

	/**
	 * A FILETIME structure that specifies when a file or directory was created. If the underlying file system does not support creation time, this member is zero.
	 */
	public FILETIME ftCreationTime;

	/**
	 * A FILETIME structure. For a file, the structure specifies when the file was last read from, written to, or for executable files, run. For a directory, the structure
	 * specifies when the directory is created. If the underlying file system does not support last access time, this member is zero. On the FAT file system, the specified date for
	 * both files and directories is correct, but the time of day is always set to midnight.
	 */
	public FILETIME ftLastAccessTime;

	/**
	 * A FILETIME structure. For a file, the structure specifies when the file was last written to, truncated, or overwritten, for example, when WriteFile or SetEndOfFile are used.
	 * The date and time are not updated when file attributes or security descriptors are changed. For a directory, the structure specifies when the directory is created. If the
	 * underlying file system does not support last write time, this member is zero.
	 */
	public FILETIME ftLastWriteTime;

	/**
	 * The high-order DWORD value of the file size, in bytes. This value is zero unless the file size is greater than MAXDWORD. The size of the file is equal to (nFileSizeHigh *
	 * (MAXDWORD+1)) + nFileSizeLow.
	 */
	public int nFileSizeHigh;

	/**
	 * The low-order DWORD value of the file size, in bytes.
	 */
	public int nFileSizeLow;

	// Additional variables that are not defined in WIN32_FIND_DATA
	public int dwVolumeSerialNumber;
	public int dwNumberOfLinks = 1;

	public ByHandleFileInfo() {
		super();
	}

	ByHandleFileInfo(
	        final String name,
	        final long size,
	        final long index,
	        final int attributes,
	        final FILETIME creationTime,
	        final FILETIME lastAccessTime,
	        final FILETIME lastWriteTime,
	        final int numberOfLinks,
	        final int volumeSerialNumber) {

		this();
		setVariables(name, size, index, attributes, creationTime, lastAccessTime, lastWriteTime, numberOfLinks, volumeSerialNumber);
	}

	void setVariables(
	        final String name,
	        final long size,
	        final long index,
	        final int attributes,
	        final FILETIME creationTime,
	        final FILETIME lastAccessTime,
	        final FILETIME lastWriteTime,
	        final int numberOfLinks,
	        final int volumeSerialNumber) {

		fileName = name;

		setSize(size);
		setIndex(index);

		dwFileAttributes = attributes;

		final Date now = new Date();

		ftCreationTime = Utils.isNull(creationTime) ? new FILETIME(now) : creationTime;
		ftLastAccessTime = Utils.isNull(lastAccessTime) ? new FILETIME(now) : lastAccessTime;
		ftLastWriteTime = Utils.isNull(lastWriteTime) ? new FILETIME(now) : lastWriteTime;

		dwNumberOfLinks = numberOfLinks;
		dwVolumeSerialNumber = volumeSerialNumber;
	}

	public WIN32_FIND_DATA toWin32FindData() {
		final char[] cFileName = Utils.trimStrToSize(fileName, 260).toCharArray();
		final char[] cAlternateFileName = Utils.trimStrToSize(fileName, 14).toCharArray();
		return new WIN32_FIND_DATA(
		        dwFileAttributes,
		        ftCreationTime, ftLastAccessTime, ftLastWriteTime,
		        nFileIndexLow, nFileIndexHigh,
		        dwVolumeSerialNumber,
		        dwNumberOfLinks,
		        cFileName, cAlternateFileName);
	}

	public void copyTo(final ByHandleFileInfo infoToReceive) {

		if (Utils.isNull(infoToReceive)) {
			throw new IllegalStateException("ByHandleFileInfo to copy cannot be null");
		}

		logger.debug("infoToReceive inside copyTo: {}", infoToReceive);

		infoToReceive.setVariables(fileName, fileSize, fileIndex, dwFileAttributes, ftCreationTime, ftLastAccessTime, ftLastWriteTime, dwNumberOfLinks, dwVolumeSerialNumber);
	}

	public final void setSize(final long size) {
		fileSize = size;
		nFileSizeHigh = toHighVal(fileSize);
		nFileSizeLow = toLowVal(fileSize);
	}

	public final long getSize() {
		return fileSize;
	}

	public final void setIndex(final long index) {
		fileIndex = index;
		nFileIndexHigh = toHighVal(fileIndex);
		nFileIndexLow = toLowVal(fileIndex);
	}

	private int toHighVal(final long val) {
		return (int) ((val >> 32L) & 0xffffffffL);
	}

	private int toLowVal(final long val) {
		return (int) ((val >> 0L) & 0xffffffffL);
	}

	public final long getIndex() {
		return fileIndex;
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
		        "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime",
		        "dwVolumeSerialNumber",
		        "nFileSizeHigh", "nFileSizeLow",
		        "dwNumberOfLinks",
		        "nFileIndexHigh", "nFileIndexLow");
	}

	public static class Builder {

		final String name;
		private long size;
		private long index;
		private int attributes;
		private FILETIME creationTime;
		private FILETIME lastAccessTime;
		private FILETIME lastWriteTime;
		private int numberOfLinks;
		private int volumeSerialNumber;

		public Builder(final Path path) {
			name = path.toString();
		}

		public Builder size(final long sizeToSet) {
			size = sizeToSet;
			return this;
		}

		public Builder index(final long indexToSet) {
			index = indexToSet;
			return this;
		}

		public Builder attributes(final FileAttribute attributes) {
			int toSet = FileAttribute.NORMAL.val;
			if (Utils.isNotNull(this)) {
				toSet = attributes.val;
			}
			this.attributes = toSet;
			return this;
		}

		public Builder creationTime(final Date creationTimeToSet) {
			return creationTime(new FILETIME(creationTimeToSet));
		}

		public Builder creationTime(final FILETIME creationTimeToSet) {
			creationTime = creationTimeToSet;
			return this;
		}

		public Builder lastAccessTime(final Date lastAccessTimeToSet) {
			return lastAccessTime(new FILETIME(lastAccessTimeToSet));
		}

		public Builder lastAccessTime(final FILETIME lastAccessTimeToSet) {
			lastAccessTime = lastAccessTimeToSet;
			return this;
		}

		public Builder lastWriteTime(final Date lastWriteTimeToSet) {
			return lastWriteTime(new FILETIME(lastWriteTimeToSet));
		}

		/**
		 * Also sets lastAccessTime to same time.
		 *
		 * @param lastWriteTimeToSet
		 * @return
		 */
		public Builder lastWriteTime(final FILETIME lastWriteTimeToSet) {
			lastAccessTime(lastWriteTimeToSet);
			lastWriteTime = lastWriteTimeToSet;
			return this;
		}

		public Builder numberOfLinks(final int numberOfLinksToSet) {
			numberOfLinks = numberOfLinksToSet;
			return this;
		}

		public Builder volumeSerialNumber(final int volumeSerialNumberToSet) {
			volumeSerialNumber = volumeSerialNumberToSet;
			return this;
		}

		public ByHandleFileInfo build() {
			return new ByHandleFileInfo(name, size, index, attributes, creationTime, lastAccessTime, lastWriteTime, numberOfLinks, volumeSerialNumber);
		}
	}
}