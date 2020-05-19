package dev.dokan.dokan_java;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import dev.dokan.dokan_java.constants.dokany.MountError;
import dev.dokan.dokan_java.structure.DokanControl;
import dev.dokan.dokan_java.structure.DokanFileInfo;
import dev.dokan.dokan_java.structure.DokanOptions;

/**
 * Native API to the kernel Dokany driver.
 */
public class NativeMethods {

	private static final String DOKAN_DLL = "dokan1";

	private static final short MINIMUM_REQUIRED_DOKAN_VERSION = 130;


	static {
		Native.register(DOKAN_DLL);
	}

	private NativeMethods() {

	}

	/**
	 * Returns the hard coded minimum required dokan driver version that is needed by this library.
	 * <p>
	 * The version is returned in "Dokan" style, i.e. without any dots.
	 *
	 * @return the minimum required dokan version without dots.
	 */
	public static short getMinimumRequiredDokanVersion() {
		return MINIMUM_REQUIRED_DOKAN_VERSION;
	}

	/**
	 * Mount a new Dokany Volume. This function block until the device is unmount. If the mount fail, it will directly return {@link MountError}.
	 *
	 * @param options A {@link DokanOptions} that describe the mount.
	 * @param operations Instance of {@link DokanyOperations} that will be called for each request made by the kernel.
	 * @return {@link MountError}.
	 */
	static native int DokanMain(DokanOptions options, DokanyOperations operations);

	/**
	 * Get the version of Dokan.
	 *
	 * @return The version number without the dots.
	 */
	static native long DokanVersion();

	/**
	 * Get the version of the Dokany driver.
	 *
	 * @return The version number without the dots.
	 */
	static native long DokanDriverVersion();

	/**
	 * Unmount a Dokany device from a driver letter.
	 *
	 * @param driveLetter Driver letter to unmount.
	 * @return True if device was unmounted or false (in case of failure or device not found).
	 */
	static native boolean DokanUnmount(char driveLetter);

	/**
	 * Unmount a Dokany device from a mount point
	 *
	 * @param mountPoint Mount point to unmount
	 * <ul>
	 * <li>Z</li>
	 * <li>Z:</li>
	 * <li>Z:\\</li>
	 * <li>Z:\MyMountPoint</li>
	 * </ul>
	 * @return if successfully unmounted mount point or not.
	 */
	static native boolean DokanRemoveMountPoint(WString mountPoint);

	/**
	 * Extends the time out of the current IO operation in driver.
	 *
	 * @param timeout Extended time in milliseconds requested.
	 * @param dokanFileInfo {@link DokanFileInfo} of the operation to extend.
	 * @return if the operation was successful or not.
	 */
	static native boolean DokanResetTimeout(long timeout, DokanFileInfo dokanFileInfo);

	/**
	 * Get the handle to Access Token.
	 *
	 * @param dokanFileInfo {@link DokanFileInfo} of the operation.
	 * @return A handle to the account token for the user on whose behalf the code is running.
	 */
	static native IntByReference DokanOpenRequestorToken(DokanFileInfo dokanFileInfo);

	/**
	 * Convert {@link DokanyOperations.ZwCreateFile} parameters to CreateFile parameters.
	 *
	 * @param desiredAccess
	 * @param fileAttributes FileAttributes
	 * @param createOptions CreateOptions
	 * @param createDisposition CreateDisposition
	 * @param genericDesiredAccess
	 * @param outFileAttributesAndFlags
	 * @param outCreationDisposition
	 */
	static native void DokanMapKernelToUserCreateFileFlags(
			long desiredAccess,
			long fileAttributes,
			long createOptions,
			long createDisposition,
			IntByReference genericDesiredAccess,
			IntByReference outFileAttributesAndFlags,
			IntByReference outCreationDisposition);

	/**
	 * Checks whether Name can match Expression.
	 *
	 * @param expression - Expression can contain wildcard characters (? and *)
	 * @param name - Name to check
	 * @param ignoreCase - Case sensitive or not
	 * @return result if name matches the expression
	 */
	static native boolean DokanIsNameInExpression(String expression, String name, boolean ignoreCase);

	/**
	 * @param serviceName
	 * @param serviceType
	 * @param serviceFullPath
	 * @return
	 */
	static native boolean DokanServiceInstall(String serviceName, int serviceType, String serviceFullPath);

	/**
	 * @param serviceName
	 * @return
	 */
	static native boolean DokanServiceDelete(String serviceName);

	/**
	 * @return
	 */
	static native boolean DokanNetworkProviderInstall();

	/**
	 * @return
	 */
	static native boolean DokanNetworkProviderUninstall();

	/**
	 * Determine if Dokany debug mode is enabled.
	 *
	 * @param mode
	 * @return true if Dokany debug mode is enabled
	 */
	static native boolean DokanSetDebugMode(int mode);

	/**
	 * Enable or disable standard error output for Dokany.
	 *
	 * @param status
	 */
	static native void DokanUseStdErr(boolean status);

	/**
	 * Enable or disable Dokany debug mode.
	 *
	 * @param status
	 */
	static native void DokanDebugMode(boolean status);

	/**
	 * Get active Dokany mount points. Returned array need to be released by calling {@link #DokanReleaseMountPointList}.
	 *
	 * @param uncOnly - Get only instances that have UNC Name.
	 * @param nbRead - Number of instances successfully retrieved
	 * @return a pointer to the start of the allocated array of {@link DokanControl} elemets. The actual list can be retrieved with {@link DokanyUtils#getDokanControlList(Pointer, long)}.
	 */
	static native Pointer DokanGetMountPointList(boolean uncOnly, LongByReference nbRead);

	/**
	 * Release Mount point list resources from {@link #DokanGetMountPointList}.
	 *
	 * @param startOfList Pointer to the start of the {@link DokanControl} list.
	 */
	static native void DokanReleaseMountPointList(Pointer startOfList);

	/**
	 * Convert Win32 error to NtStatus
	 *
	 * @param error - Win32 error to convert
	 * @return NtStatus associated to the error
	 * @see <a href="https://support.microsoft.com/en-us/kb/113996">kb113996</a>
	 */
	static native long DokanNtStatusFromWin32(int error);

}