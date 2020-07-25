package dev.dokan.dokan_java.constants.dokany;


import dev.dokan.dokan_java.masking.MaskValueEnum;
import dev.dokan.dokan_java.masking.MaskValueSet;

public enum DokanFileInfoFlag implements MaskValueEnum {

	DELETE_ON_CLOSE(1),
	IS_DIRECTORY(2),
	NO_CACHE(4),
	PAGING_IO(8),
	SYNCHRONOUS_IO(16),
	WRITE_TO_END_OF_FILE(32);

	private final int maskingValue;

	DokanFileInfoFlag(int maskingValue) {
		this.maskingValue = maskingValue;
	}

	public static MaskValueSet<DokanFileInfoFlag> maskValueSet(final int mask) {
		return MaskValueSet.maskValueSet(mask, values());
	}

	@Override
	public int intValue() {
		return this.maskingValue;
	}
}
