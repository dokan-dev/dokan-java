package com.dokany.java.constants;

public enum MountOptions {
	FIXED_DRIVE(0, "Use fixed drive"),
	DEBUG_MODE(1, "Enable ouput debug message"),
	STD_ERR_OUTPUT(2, "Enable ouput debug message to stderr"),
	ALT_STREAM(4, "Use alternate stream"),
	WRITE_PROTECTION(8, "Enable mount drive as write-protected"),
	NETWORK_DRIVE(16, "Use network drive - Dokan network provider need to be installed"),
	REMOVABLE_DRIVE(32, "Use removable drive"),
	MOUNT_MANAGER(64, "Use mount manager"),
	CURRENT_SESSION(128, "Mount the drive on current session only"),
	FILELOCK_USER_MODE(256, "Enable Lockfile/Unlockfile operations. Otherwise Dokan will take care of it");

	public final int val;
	public final String description;
	public final boolean isReadonly;

	private MountOptions(final int val, final String description) {
		this.val = val;
		this.description = description;
		isReadonly = ((val == 3) || (val == 4));
	}

	public final static int toInt(final MountOptions... opts) {
		if (opts == null) {
			throw new IllegalArgumentException("MountOptions cannot be null");
		}
		int toReturn = -1;
		for (final MountOptions current : opts) {
			if (toReturn == -1) {
				toReturn = current.val;
			} else {
				toReturn |= current.val;
			}
		}
		return toReturn;
	}

	public final static MountOptions fromInt(final int value) {
		for (final MountOptions current : values()) {
			if (current.val == value) {
				return current;
			}
		}
		throw new IllegalArgumentException("Invalid int value for MountOptions: " + value);
	}
}
