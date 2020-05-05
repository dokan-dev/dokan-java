package dev.dokan.dokan_java.constants.microsoft;

import dev.dokan.dokan_java.constants.EnumInteger;

public enum FileShareAccess implements EnumInteger {

	FILE_SHARE_READ(1),
	FILE_SHARE_WRITE(2),
	FILE_SHARE_DELETE(4);

	private final int mask;

	FileShareAccess(int mask) {
		this.mask = mask;
	}

	@Override
	public int getMask() {
		return this.mask;
	}
}
