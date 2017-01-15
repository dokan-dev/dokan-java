package com.dokany.java.constants;

public enum MountOption implements EnumInteger {

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

	@Override
	public int getVal() {
		return val;
	}

	public final String description;
	public final boolean isReadonly;

	private MountOption(final int val, final String description) {
		this.val = val;
		this.description = description;

		// TODO: is this proper logic?
		isReadonly = (val == 8);
	}

	public String getDescription() {
		return description;
	}
}
