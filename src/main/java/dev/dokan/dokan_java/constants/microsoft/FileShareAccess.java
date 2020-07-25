package dev.dokan.dokan_java.constants.microsoft;


import dev.dokan.dokan_java.masking.MaskValueEnum;
import dev.dokan.dokan_java.masking.MaskValueSet;

public enum FileShareAccess implements MaskValueEnum {

	FILE_SHARE_READ(1),
	FILE_SHARE_WRITE(2),
	FILE_SHARE_DELETE(4);

	private final int maskingValue;

	FileShareAccess(int maskingValue) {
		this.maskingValue = maskingValue;
	}

	public static MaskValueSet<FileShareAccess> maskValueSet(final int mask) {
		return MaskValueSet.maskValueSet(mask, values());
	}

	@Override
	public int intValue() {
		return this.maskingValue;
	}
}
