package com.dokany.java;

import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.FileInfoRaw;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

/**
 * Native API to the kernel Dokany driver.
 *
 */
class NativeMethods {
	static final String DOKAN_DLL = "dokan1";

	static {
		Native.register(DOKAN_DLL);
	}

	/**
	 * Mount a new Dokan Volume. This function block until the device is unmount. If the mount fail, it will directly return {@link com.dokany.java.constants.MountError}.
	 *
	 * @param options A {@link com.dokany.java.structure.DeviceOptions} that describe the mount.
	 * @param operations Instance of {@link com.dokany.java.Operations} that will be called for each request made by the kernel.
	 * @return {@link com.dokany.java.constants.MountError}.
	 */
	static native int DokanMain(DeviceOptions options, Operations operations);

	/**
	 * Get the version of Dokan.
	 *
	 * @return The version number without the dots.
	 */
	static native long DokanVersion();

	/**
	 * Get the version of the Dokan driver.
	 *
	 * @return The version number without the dots.
	 */
	static native long DokanDriverVersion();

	/**
	 * Unmount a dokan device from a driver letter.
	 *
	 * @param driveLetter Dokan driver letter to unmount.
	 * @return Trueif device was unmount or false in case of failure or device not found.
	 */
	static native boolean DokanUnmount(char driveLetter);

	/**
	 * Unmount a dokan device from a mount point
	 *
	 * @param mountPoint Mount point to unmount Z or Z: or Z:\\ or Z:\MyMountPoint.
	 * @return
	 */
	static native boolean DokanRemoveMountPoint(WString mountPoint);

	/**
	 * Extends the time out of the current IO operation in driver.
	 *
	 * @param timeout Extended time in milliseconds requested.
	 * @param rawFileInfo {@link com.dokany.java.structure.FileInfoRaw} of the operation to extend.
	 * @return if the operation was successful or not.
	 */
	static native boolean DokanResetTimeout(long timeout, FileInfoRaw rawFileInfo);

	/**
	 * Get the handle to Access Token.
	 *
	 * @param rawFileInfo {@link com.dokany.java.structure.FileInfoRaw} of the operation.
	 * @return A handle to the account token for the user on whose behalf the code is running.
	 */
	static native IntByReference DokanOpenRequestorToken(FileInfoRaw rawFileInfo);

	/**
	 * Convert {@link com.dokany.java.structure.Operations.ZwCreateFileDelegate} parameters to CreateFile parameters.
	 *
	 * @param fileAttributes FileAttributes
	 * @param createOptions CreateOptions
	 * @param createDisposition CreateDisposition
	 * @param outFileAttributesAndFlags
	 * @param outCreationDisposition
	 */
	static native void DokanMapKernelToUserCreateFileFlags(long fileAttributes, long createOptions, long createDisposition, IntByReference outFileAttributesAndFlags,
	        IntByReference outCreationDisposition);
}