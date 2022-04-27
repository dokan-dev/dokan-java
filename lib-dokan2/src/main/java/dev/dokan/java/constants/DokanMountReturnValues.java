package dev.dokan.java.constants;

public interface DokanMountReturnValues {

	/**
	 * Dokan mount succeed.
	 */
	int DOKAN_SUCCESS = 0;
	/**
	 * Dokan mount error.
	 */
	int DOKAN_ERROR = -1;
	/**
	 * Dokan mount failed - Bad drive letter.
	 */
	int DOKAN_DRIVE_LETTER_ERROR = -2;
	/**
	 * Dokan mount failed - Can't install driver.
	 */
	int DOKAN_DRIVER_INSTALL_ERROR = -3;
	/**
	 * Dokan mount failed - Driver answer that something is wrong.
	 */
	int DOKAN_START_ERROR = -4;
	/**
	 * Dokan mount failed.
	 * Can't assign a drive letter or mount point.
	 * Probably already used by another volume.
	 */
	int DOKAN_MOUNT_ERROR = -5;
	/**
	 * Dokan mount failed.
	 * Mount point is invalid.
	 */
	int DOKAN_MOUNT_POINT_ERROR = -6;
	/**
	 * Dokan mount failed.
	 * Requested an incompatible version.
	 */
	int DOKAN_VERSION_ERROR = -7;

}
