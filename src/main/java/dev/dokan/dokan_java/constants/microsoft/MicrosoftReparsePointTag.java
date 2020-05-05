package dev.dokan.dokan_java.constants.microsoft;

import dev.dokan.dokan_java.constants.EnumInteger;

/**
 * [TO BE REPLACED WITH LICENSE NOTE]
 */
public enum MicrosoftReparsePointTag implements EnumInteger {

	IO_REPARSE_TAG_CSV(0x80000009),
	IO_REPARSE_TAG_DEDUP(0x80000013),
	IO_REPARSE_TAG_DFS(0x8000000A),
	IO_REPARSE_TAG_DFSR(0x80000012),
	IO_REPARSE_TAG_HSM(0xC0000004),
	IO_REPARSE_TAG_HSM2(0x80000006),
	IO_REPARSE_TAG_MOUNT_POINT(0xA0000003),
	IO_REPARSE_TAG_NFS(0x80000014),
	IO_REPARSE_TAG_SIS(0x80000007),
	IO_REPARSE_TAG_SYMLINK(0xA000000C),
	IO_REPARSE_TAG_WIM(0x80000008);

	private final int mask;

	MicrosoftReparsePointTag(int mask) {
		this.mask = mask;
	}

	@Override
	public int getMask() {
		return this.mask;
	}
}
