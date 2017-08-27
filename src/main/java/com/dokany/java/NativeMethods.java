package com.dokany.java;

import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.DokanyFileInfo;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Native API to the kernel Dokany driver. This is an internal class and should not used directly by code outside com.dokany.java.
 *
 */
@Slf4j
@UtilityClass
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class NativeMethods {
	String DOKAN_DLL = "dokan1";

	static {
		Native.register(DOKAN_DLL);
	}

	/**
	 * Mount a new Dokany Volume. This function block until the device is unmount. If the mount fail, it will directly return {@link com.dokany.java.constants.MountError}.
	 *
	 * @param options A {@link com.dokany.java.structure.DeviceOptions} that describe the mount.
	 * @param operations Instance of {@link com.dokany.java.DokanyOperations} that will be called for each request made by the kernel.
	 * @return {@link com.dokany.java.constants.MountError}.
	 */
	native int DokanMain(@NonNull DeviceOptions options, @NonNull DokanyOperations operations);

	/**
	 * Get the version of Dokan.
	 *
	 * @return The version number without the dots.
	 */
	native long DokanVersion();

	/**
	 * Get the version of the Dokany driver.
	 *
	 * @return The version number without the dots.
	 */
	native long DokanDriverVersion();

	/**
	 * Unmount a Dokany device from a driver letter.
	 *
	 * @param driveLetter Driver letter to unmount.
	 * @return True if device was unmounted or false (in case of failure or device not found).
	 */
	native boolean DokanUnmount(char driveLetter);

	/**
	 * Unmount a Dokany device from a mount point
	 *
	 * @param mountPoint Mount point to unmount
	 *            <ul>
	 *            <li>Z</li>
	 *            <li>Z:</li>
	 *            <li>Z:\\</li>
	 *            <li>Z:\MyMountPoint</li>
	 *            </ul>
	 * @return if successfully unmounted mount point or not.
	 */
	native boolean DokanRemoveMountPoint(WString mountPoint);

	/**
	 * Extends the time out of the current IO operation in driver.
	 *
	 * @param timeout Extended time in milliseconds requested.
	 * @param dokanyFileInfo {@link com.dokany.java.structure.DokanyFileInfo} of the operation to extend.
	 * @return if the operation was successful or not.
	 */
	native boolean DokanResetTimeout(long timeout, @NonNull DokanyFileInfo dokanyFileInfo);

	/**
	 * Get the handle to Access Token.
	 *
	 * @param rawFileInfo {@link com.dokany.java.structure.DokanyFileInfo} of the operation.
	 * @return A handle to the account token for the user on whose behalf the code is running.
	 */
	native IntByReference DokanOpenRequestorToken(@NonNull DokanyFileInfo dokanyFileInfo);

	/**
	 * Convert {@link com.dokany.java.DokanyOperations.ZwCreateFile} parameters to CreateFile parameters.
	 *
	 * @param fileAttributes FileAttributes
	 * @param createOptions CreateOptions
	 * @param createDisposition CreateDisposition
	 * @param outFileAttributesAndFlags
	 * @param outCreationDisposition
	 */
	native void DokanMapKernelToUserCreateFileFlags(
	        long fileAttributes,
	        long createOptions,
	        long createDisposition,
	        IntByReference outFileAttributesAndFlags,
	        IntByReference outCreationDisposition);

	/**
	 * Convert IRP_MJ_CREATE DesiredAccess to generic rights.
	 *
	 * @param DesiredAccess Standard rights to convert
	 * @return New DesiredAccess with generic rights.
	 * @see {@linkplain https://msdn.microsoft.com/windows/hardware/drivers/ifs/access-mask}
	 */
	// TODO: change return type and method parameter type to FileAccess
	native long DokanMapStandardToGenericAccess(long desiredAccess);

	/**
	 * Checks whether Name can match Expression.
	 *
	 * @param expression - Expression can contain wildcard characters (? and *)
	 * @param name - Name to check
	 * @param ignoreCase - Case sensitive or not
	 * @return result if name matches the expression
	 */
	native boolean DokanIsNameInExpression(@NonNull String expression, String name, boolean ignoreCase);

	/**
	 *
	 * @param serviceName
	 * @param serviceType
	 * @param serviceFullPath
	 * @return
	 */
	native boolean DokanServiceInstall(@NonNull String serviceName, int serviceType, @NonNull String serviceFullPath);

	/**
	 *
	 * @param serviceName
	 * @return
	 */
	native boolean DokanServiceDelete(@NonNull String serviceName);

	/**
	 *
	 * @return
	 */
	native boolean DokanNetworkProviderInstall();

	/**
	 *
	 * @return
	 */
	native boolean DokanNetworkProviderUninstall();

	/**
	 * Determine if Dokany debug mode is enabled.
	 *
	 * @param mode
	 * @return true if Dokany debug mode is enabled
	 */
	native boolean DokanSetDebugMode(int mode);

	/**
	 * Enable or disable standard error output for Dokany.
	 *
	 * @param status
	 */
	native void DokanUseStdErr(boolean status);

	/**
	 * Enable or disable Dokany debug mode.
	 *
	 * @param status
	 */
	native void DokanDebugMode(boolean status);

	/**
	 * Get active Dokany mount points.
	 *
	 * @param list - Allocate array of DOKAN_CONTROL
	 * @param length - Number of DOKAN_CONTROL instance in list.
	 * @param uncOnly - Get only instances that have UNC Name.
	 * @param nbRead- Number of instances successfully retrieved
	 * @return List retrieved or not.
	 */
	// TODO: Does this have proper params?
	native boolean DokanGetMountPointList(long fileAttributes, long length, boolean uncOnly, long nbRead);

	/**
	 * Convert Win32 error to NtStatus
	 *
	 * @see {@linkplain https://support.microsoft.com/en-us/kb/113996}
	 *
	 * @param error - Win32 error to convert
	 * @return NtStatus associated to the error
	 */
	// TODO: Switch to NtStatus return type
	native long DokanNtStatusFromWin32(int error);
}