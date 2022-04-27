package dev.dokan.java.constants;

public interface MountOptions {

	//-- Dokan Run Options --
	/**
	 * Enable ouput debug message
	 */
	int DEBUG = 1;
	/**
	 * Enable ouput debug message to stderr
	 */
	int STDERR = (1 << 1);
	/**
	 * Enable the use of alternate stream paths in the form
	 * &lt;file-name&gt;:&lt;stream-name&gt;. If this is not specified then the driver will
	 * fail any attempt to access a path with a colon.
	 */
	int ALT_STREAM = (1 << 2);
	/**
	 * Enable mount drive as write-protected
	 */
	int WRITE_PROTECT = (1 << 3);
	/**
	 * Use network drive - Dokan network provider needs to be installed and a \ref DOKAN_OPTIONS.UNCName provided
	 */
	int NETWORK = (1 << 4);
	/**
	 * Use removable drive
	 * Be aware that on some environments, the userland application will be denied
	 * to communicate with the drive which will result in a unwanted unmount.
	 * \see <a href="https://github.com/dokan-dev/dokany/issues/843">Issue #843</a>
	 */
	int REMOVABLE = (1 << 5);
	/**
	 * Use Windows Mount Manager.
	 * This option is highly recommended to use for better system integration
	 * <p>
	 * If a drive letter is used but is busy, Mount manager will assign one for us and
	 * \ref DOKAN_OPERATIONS.Mounted parameters will contain the new mount point.
	 */
	int MOUNT_MANAGER = (1 << 6);
	/**
	 * Mount the drive on current session only
	 */
	int CURRENT_SESSION = (1 << 7);
	/**
	 * Enable Lockfile/Unlockfile operations. Otherwise Dokan will take care of it
	 */
	int FILELOCK_USER_MODE = (1 << 8);
	/**
	 * Enable Case sensitive path.
	 * By default, all path are case-insensitive.
	 * For case-sensitive: {@code \dir\File} &amp; {@code \diR\file} are different files
	 * but for case-insensitive they are the same.
	 */
	int CASE_SENSITIVE = (1 << 9);
	/**
	 * Allows unmounting of network drive via explorer
	 */
	int ENABLE_UNMOUNT_NETWORK_DRIVE = (1 << 10);
	/**
	 * Forward the kernel driver global and volume logs to the userland.
	 * Can be very slow if single thread is enabled.
	 */
	int DISPATCH_DRIVER_LOGS = (1 << 11);
}
