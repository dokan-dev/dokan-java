package dev.dokan.java.structures;

import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.nativeannotations.Unsigned;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.FILETIME;

@Structure.FieldOrder({"dwFileAttributes", "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime", "dwVolumeSerialNumber", "nFileSizeHigh", "nFileSizeLow", "nNumberOfLinks", "nFileIndexHigh", "nFileIndexLow"})
public class ByHandleFileInformation extends Structure {

	@EnumSet
	public int dwFileAttributes;
	public FILETIME ftCreationTime;
	public FILETIME ftLastAccessTime;
	public FILETIME ftLastWriteTime;
	@Unsigned
	public int dwVolumeSerialNumber;
	@Unsigned
	public int nFileSizeHigh;
	@Unsigned
	public int nFileSizeLow;
	@Unsigned
	public int nNumberOfLinks;
	@Unsigned
	public int nFileIndexHigh;
	@Unsigned
	public int nFileIndexLow;

	public long getDwFileAttributes() {
		return Integer.toUnsignedLong(dwFileAttributes);
	}

	public long getDwVolumeSerialNumber() {
		return Integer.toUnsignedLong(dwVolumeSerialNumber);
	}

	public long getnFileSizeHigh() {
		return Integer.toUnsignedLong(nFileSizeHigh);
	}

	public long getnFileSizeLow() {
		return Integer.toUnsignedLong(nFileSizeLow);
	}

	public long getnNumberOfLinks() {
		return Integer.toUnsignedLong(nNumberOfLinks);
	}

	public long getnFileIndexHigh() {
		return Integer.toUnsignedLong(nFileIndexHigh);
	}

	public long getnFileIndexLow() {
		return Integer.toUnsignedLong(nFileIndexLow);
	}

	public void setFileIndex(long index) {
		nFileIndexLow = (int) index;
		nFileIndexHigh = (int) (index >>> 32);
	}

	public void setFileSize(long size) {
		nFileSizeLow = (int) size;
		nFileSizeHigh = (int) (size >>> 32);
	}
}
