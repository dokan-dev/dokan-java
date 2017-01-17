package com.dokany.java.constants;

import com.dokany.java.DokanyUtils;

public enum MountError implements EnumInteger {

	SUCCESS(0, "Successfully mounted"),

	MOUNT_ERROR(-1, "Mount error"),

	DRIVE_LETTER_ERROR(-2, "Mount failed: Bad drive letter."),

	DRIVER_INSTALL_ERROR(-3, "Mount failed: Cannot install driver."),

	START_ERROR(-4, "Mount failed: Driver answer that something is wrong."),

	CANNOT_ASSIGN(-5, "Mount failed: Cannot assign a drive letter or mount point. Probably already used by another volume."),

	MOUNT_POINT_ERROR(-6, "Mount failed: Mount point is invalid."),

	VERSION_ERROR(-7, "Mount failed: Requested an incompatible version.");

	private final int mask;

	@Override
	public int mask() {
		return mask;
	}

	private final String description;

	public String description() {
		return description;
	}

	private MountError(final int i, final String desc) {
		mask = i;
		description = desc;
	}

	public static MountError fromInt(final int value) {
		return DokanyUtils.enumFromInt(value, values());
	}
}
