package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.constants.microsoft.accessmaskflags.BasicAccessMaskFlag;
import dev.dokan.dokan_java.constants.microsoft.accessmaskflags.DirectoryAccessMaskFlag;
import dev.dokan.dokan_java.constants.microsoft.accessmaskflags.FileAccessMaskFlag;
import dev.dokan.dokan_java.masking.MaskValueSet;

import java.util.concurrent.atomic.AtomicInteger;

public class DesiredAccessMask {

	private final boolean isDirectory;
	private final AtomicInteger accessMask;

	public DesiredAccessMask(int rawAccessMask, int rawFileAttributes) {
		this(rawAccessMask, (rawFileAttributes & FileAttribute.DIRECTORY.maskingValue()) != 0);
	}

	public DesiredAccessMask(int rawAccessMask, MaskValueSet<FileAttribute> fileAttributes) {
		this(rawAccessMask, fileAttributes.contains(FileAttribute.DIRECTORY));
	}

	//TODO Check for illegal flags if isDirectory=true
	//See https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/nf-wdm-zwcreatefile Note below first table
	public DesiredAccessMask(int rawAccessMask, boolean isDirectory) {
		this.isDirectory = isDirectory;
		this.accessMask = new AtomicInteger(rawAccessMask);
	}

	public MaskValueSet<BasicAccessMaskFlag> getBasicRights() {
		return MaskValueSet.maskValueSet(this.accessMask.get(), BasicAccessMaskFlag.values());
	}

	public MaskValueSet<FileAccessMaskFlag> getSpecificRights() {
		return MaskValueSet.maskValueSet(this.accessMask.get(), FileAccessMaskFlag.values());
	}

	public boolean isDirectory() {
		return this.isDirectory;
	}

	public int getAccessMask() {
		return this.accessMask.get();
	}

	public void setAccessMask(int accessMask) {
		this.accessMask.set(accessMask);
	}

	public boolean getFlag(BasicAccessMaskFlag flag) {
		return getFlag(flag.maskingValue());
	}

	public boolean getFlag(FileAccessMaskFlag flag) {
		return getFlag(flag.maskingValue());
	}

	public boolean getFlag(DirectoryAccessMaskFlag flag) {
		if(!this.isDirectory) {
			throw new IllegalArgumentException("Not a directory!");
		}
		return silentGetFlag(flag);
	}

	public boolean silentGetFlag(DirectoryAccessMaskFlag flag) {
		return getFlag(flag.maskingValue());
	}

	public boolean getFlag(int flag) {
		ensureSingleFlag(flag);

		return (this.accessMask.get() & flag) != 0;
	}

	public boolean setFlag(BasicAccessMaskFlag flag) {
		return updateFlag(flag, true);
	}

	public boolean unsetFlag(BasicAccessMaskFlag flag) {
		return updateFlag(flag, false);
	}

	public boolean updateFlag(BasicAccessMaskFlag flag, boolean value) {
		return updateFlag(flag.maskingValue(), value);
	}

	public boolean setFlag(FileAccessMaskFlag flag) {
		return updateFlag(flag, true);
	}

	public boolean unsetFlag(FileAccessMaskFlag flag) {
		return updateFlag(flag, false);
	}

	public boolean updateFlag(FileAccessMaskFlag flag, boolean value) {
		return updateFlag(flag.maskingValue(), value);
	}

	public boolean setFlag(DirectoryAccessMaskFlag flag) {
		if(!this.isDirectory) {
			throw new IllegalArgumentException("Not a directory!");
		}
		return silentSetFlag(flag);
	}

	public boolean unsetFlag(DirectoryAccessMaskFlag flag) {
		if(!this.isDirectory) {
			throw new IllegalArgumentException("Not a directory!");
		}
		return silentUnsetFlag(flag);
	}

	public boolean updateFlag(DirectoryAccessMaskFlag flag, boolean value) {
		if(!this.isDirectory) {
			throw new IllegalArgumentException("Not a directory!");
		}
		return silentUpdateFlag(flag, value);
	}

	public boolean silentSetFlag(DirectoryAccessMaskFlag flag) {
		return silentUpdateFlag(flag, true);
	}

	public boolean silentUnsetFlag(DirectoryAccessMaskFlag flag) {
		return silentUpdateFlag(flag, false);
	}

	public boolean silentUpdateFlag(DirectoryAccessMaskFlag flag, boolean value) {
		return updateFlag(flag.maskingValue(), value);
	}

	public boolean updateFlag(int flag, boolean value) {
		ensureSingleFlag(flag);

		int prev = this.accessMask.getAndUpdate(current -> current & (value ? flag : ~flag));
		return (prev & flag) != 0;
	}

	private void ensureSingleFlag(int flag) {
		if(!isSingleFlag(flag)) {
			throw new IllegalArgumentException("Result for more than one flag is undefined!");
		}
	}

	private boolean isSingleFlag(int flag) {
		/*
		 * This may be more performant, but it doesn't really matter
		 * Integer.highestOneBit(flag) != Integer.lowestOneBit(flag)
		 */
		return Integer.bitCount(flag) == 1;
	}

	private FileAccessMaskFlag getFileAccessMaskFlag(DirectoryAccessMaskFlag attribute) {
		//This lookup is fine, but a different kind of mapping would be preferable
		switch(attribute) {
		case LIST_DIRECTORY: //1
			return FileAccessMaskFlag.READ_DATA;
		case TRAVERSE: //32
			return FileAccessMaskFlag.EXECUTE;
		}
		throw new IllegalStateException();
	}
}