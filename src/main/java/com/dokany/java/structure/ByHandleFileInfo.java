package com.dokany.java.structure;

import java.util.Date;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.Utils;
import com.dokany.java.constants.FileAttribute;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;

import jetbrains.exodus.ArrayByteIterable;
import jetbrains.exodus.ByteIterable;
import jetbrains.exodus.ByteIterator;
import jetbrains.exodus.bindings.IntegerBinding;
import jetbrains.exodus.bindings.LongBinding;
import jetbrains.exodus.util.LightOutputStream;

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

	private final static Logger LOGGER = LoggerFactory.getLogger(ByHandleFileInfo.class);

	// Used to store actual values (instead of high/low) which can be retrieved using getter method
	private String path;
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

	public ByHandleFileInfo(@NotNull final String path, @NotNull final ByteIterable iterable) {
		final ByteIterator iterator = iterable.iterator();

		final long size = LongBinding.readCompressed(iterator);
		final long index = LongBinding.readCompressed(iterator);

		final int attributes = IntegerBinding.readCompressed(iterator);

		final FILETIME creationTime = new FILETIME(new Date(LongBinding.readCompressed(iterator)));
		final FILETIME lastAccessTime = new FILETIME(new Date(LongBinding.readCompressed(iterator)));
		final FILETIME lastWriteTime = new FILETIME(new Date(LongBinding.readCompressed(iterator)));

		setVariables(path, size, index, attributes, creationTime, lastAccessTime, lastWriteTime, 0, 0);
	}

	public ArrayByteIterable toByteIterable() {
		final LightOutputStream output = new LightOutputStream();

		LongBinding.writeCompressed(output, fileSize);
		LongBinding.writeCompressed(output, fileIndex);

		IntegerBinding.writeCompressed(output, dwFileAttributes);

		LongBinding.writeCompressed(output, ftCreationTime.toTime());
		LongBinding.writeCompressed(output, ftLastAccessTime.toTime());
		LongBinding.writeCompressed(output, ftLastWriteTime.toTime());

		return output.asArrayByteIterable();
	}

	ByHandleFileInfo(
	        final String path,
	        final long size,
	        final long index,
	        final int attributes,
	        final FILETIME creationTime,
	        final FILETIME lastAccessTime,
	        final FILETIME lastWriteTime,
	        final int numberOfLinks,
	        final int volumeSerialNumber) {
		setVariables(path, size, index, attributes, creationTime, lastAccessTime, lastWriteTime, numberOfLinks, volumeSerialNumber);
	}

	void setVariables(
	        final String path,
	        final long size,
	        final long index,
	        final int attributes,
	        final FILETIME creationTime,
	        final FILETIME lastAccessTime,
	        final FILETIME lastWriteTime,
	        final int numberOfLinks,
	        final int volumeSerialNumber) {

		this.path = path;

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

		final char[] cFileName = Utils.trimFrontSlash(Utils.trimStrToSize(path, 260)).toCharArray();
		final char[] cAlternateFileName = new char[1];
		// final char[] cAlternateFileName = Utils.trimFrontSlash(Utils.trimStrToSize(path, 14)).toCharArray();
		// TODO: Why does setting alternate name cause file name to show up twice??
		return new WIN32_FIND_DATA(
		        dwFileAttributes,
		        ftCreationTime, ftLastAccessTime, ftLastWriteTime,
		        nFileSizeHigh, nFileSizeLow,
		        0,
		        0,
		        cFileName, cAlternateFileName);
	}

	public void copyTo(final ByHandleFileInfo infoToReceive) {
		if (Utils.isNull(infoToReceive)) {
			throw new IllegalStateException("ByHandleFileInfo to copy cannot be null");
		}

		infoToReceive.setVariables(path, fileSize, fileIndex, dwFileAttributes, ftCreationTime, ftLastAccessTime, ftLastWriteTime, dwNumberOfLinks, dwVolumeSerialNumber);
	}

	private final void setSize(final long size) {
		fileSize = size;
		final LARGE_INTEGER largeInt = new LARGE_INTEGER(size);
		nFileSizeHigh = largeInt.getHigh().intValue();
		nFileSizeLow = largeInt.getLow().intValue();
	}

	public final long getSize() {
		return fileSize;
	}

	private final void setIndex(final long index) {
		fileIndex = index;
		final LARGE_INTEGER largeInt = new LARGE_INTEGER(index);
		nFileIndexHigh = largeInt.getHigh().intValue();
		nFileIndexLow = largeInt.getLow().intValue();
	}

	@Override
	public String toString() {
		return "FileInfo{" +
		        "attributes=" + dwFileAttributes +
		        ", creationTime=" + ftCreationTime +
		        ", lastAccessTime=" + ftLastAccessTime +
		        ", lastWriteTime=" + ftLastWriteTime +
		        ", fileSize=" + fileSize +
		        ", fileName='" + path + '\'' +
		        ", fileIndex=" + fileIndex +
		        ", numberOfLinks=" + dwNumberOfLinks +
		        ", volumeSerialNumber=" + dwVolumeSerialNumber +
		        '}';
	}

	@Override
	public List<String> getFieldOrder() {
		return createFieldsOrder(
		        "dwFileAttributes",
		        "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime",
		        "dwVolumeSerialNumber",
		        "nFileSizeHigh", "nFileSizeLow",
		        "dwNumberOfLinks",
		        "nFileIndexHigh", "nFileIndexLow");
	}

	public static class Builder {

		final String path;
		private long size;
		private long index;
		private int attributes;
		private FILETIME creationTime;
		private FILETIME lastAccessTime;
		private FILETIME lastWriteTime;
		private int numberOfLinks;
		private int volumeSerialNumber;

		public Builder(final String path) {
			this.path = path;
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

		public Builder creationTime(final long creationTimeToSet) {
			return creationTime(new FILETIME(new Date(creationTimeToSet)));
		}

		public Builder creationTime(final Date creationTimeToSet) {
			return creationTime(new FILETIME(creationTimeToSet));
		}

		public Builder creationTime(final FILETIME creationTimeToSet) {
			creationTime = creationTimeToSet;
			return this;
		}

		public Builder lastAccessTime(final long lastAccessTimeToSet) {
			return lastAccessTime(new FILETIME(new Date(lastAccessTimeToSet)));
		}

		public Builder lastAccessTime(final Date creationTimeToSet) {
			return creationTime(new FILETIME(creationTimeToSet));
		}

		public Builder lastAccessTime(final FILETIME lastAccessTimeToSet) {
			lastAccessTime = lastAccessTimeToSet;
			return this;
		}

		public Builder lastWriteTime(final long lastWriteTimeToSet) {
			return lastWriteTime(new FILETIME(new Date(lastWriteTimeToSet)));
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
			lastWriteTime = new FILETIME(lastWriteTimeToSet.toDate());
			lastAccessTime(lastWriteTimeToSet);
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
			return new ByHandleFileInfo(path, size, index, attributes, creationTime, lastAccessTime, lastWriteTime, numberOfLinks, volumeSerialNumber);
		}
	}
}