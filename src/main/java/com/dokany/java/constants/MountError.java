package com.dokany.java.constants;

public enum MountError {

	DOKAN_SUCCESS(0, "Successfully mounted"),

	DOKAN_ERROR(-1, "Mount error"),

	DOKAN_DRIVE_LETTER_ERROR(-2, "Mount failed: Bad drive letter."),

	DOKAN_DRIVER_INSTALL_ERROR(-3, "Mount failed: Cannot install driver."),

	DOKAN_START_ERROR(-4, "Mount failed: Driver answer that something is wrong."),

	DOKAN_MOUNT_ERROR(-5, "Mount failed: Cannot assign a drive letter or mount point. Probably already used by another volume."),

	DOKAN_MOUNT_POINT_ERROR(-6, "Mount failed: Mount point is invalid."),

	DOKAN_VERSION_ERROR(-7, "Mount failed: Requested an incompatible version.");

	public final int val;
	public final String name;

	private MountError(final int val, final String name) {
		this.val = val;
		this.name = name;
	}

	public final static MountError fromInt(final int value) {
		for (final MountError current : values()) {
			if (current.val == value) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value for MountError");
	}
}
