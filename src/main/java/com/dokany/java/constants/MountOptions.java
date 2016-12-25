package com.dokany.java.constants;

public enum MountOptions {

	FIXED_DRIVE(0),
	DEBUG_MODE(1),
	STD_ERR_OUTPUT(2),
	ALT_STERAM(4),
	WRITE_PROTECTION(8),
	NETWORK_DRIVEV(16),
	REMOVABLE_DRIVE(32),
	MOUNT_MANAGER(64),
	CURRENT_SESSION(128),
	USER_MODE_LOCK(256);

	public final int val;

	private MountOptions(final int val) {
		this.val = val;
	}
}
