package dev.dokan.dokan_java.constants.dokany;

import dev.dokan.dokan_java.constants.EnumInteger;

/**
 * [TO BE REPLACED WITH LICENSE NOTE]
 */
public enum DokanFileInfoFlag implements EnumInteger {

	DELETE_ON_CLOSE(1),
	IS_DIRECTORY(2),
	NO_CACHE(4),
	PAGING_IO(8),
	SYNCHRONOUS_IO(16),
	WRITE_TO_END_OF_FILE(32);

	private final int mask;

	DokanFileInfoFlag(int mask) {
		this.mask = mask;
	}

	@Override
	public int getMask() {
		return this.mask;
	}
}
