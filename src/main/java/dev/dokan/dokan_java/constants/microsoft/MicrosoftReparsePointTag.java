package dev.dokan.dokan_java.constants.microsoft;


import dev.dokan.dokan_java.masking.EnumInteger;
import dev.dokan.dokan_java.structure.ReparsePointTag;


public enum MicrosoftReparsePointTag implements ReparsePointTag {

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

	private static final String SOURCE = "WinNT.h";
	private static final String DEFINED_BY = "Microsoft";

	private final int intValue;

	MicrosoftReparsePointTag(int intValue) {
		this.intValue = intValue;
	}

	public static MicrosoftReparsePointTag fromInt(final int value) {
		return EnumInteger.enumFromInt(value, values());
	}

	@Override
	public int intValue() {
		return this.intValue;
	}

	@Override
	public String getSource() {
		return SOURCE;
	}

	@Override
	public String getDefinedBy() {
		return DEFINED_BY;
	}
}
