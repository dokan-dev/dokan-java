package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.constants.microsoft.accessmaskflags.DirectoryAccessMaskFlag;
import dev.dokan.dokan_java.constants.microsoft.accessmaskflags.FileAccessMaskFlag;
import dev.dokan.dokan_java.masking.MaskValueSet;


public class DesiredAccessMask extends BasicDesiredAccessMask {

	private final boolean isDirectory;

	public DesiredAccessMask(int rawAccessMask, int rawFileAttributes) {
		this(rawAccessMask, (rawFileAttributes & FileAttribute.DIRECTORY.maskingValue()) != 0);
	}

	public DesiredAccessMask(int rawAccessMask, MaskValueSet<FileAttribute> fileAttributes) {
		this(rawAccessMask, fileAttributes.contains(FileAttribute.DIRECTORY));
	}

	//TODO Check for illegal flags if isDirectory=true
	//See https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/nf-wdm-zwcreatefile Note below first table
	public DesiredAccessMask(int rawAccessMask, boolean isDirectory) {
		super(rawAccessMask);
		this.isDirectory = isDirectory;
	}

	public MaskValueSet<FileAccessMaskFlag> getSpecificRights() {
		return FileAccessMaskFlag.maskValueSet(this.accessMask.get());
	}

	public boolean isDirectory() {
		return this.isDirectory;
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

	private FileAccessMaskFlag getFileAccessMask(DirectoryAccessMaskFlag attribute) {
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