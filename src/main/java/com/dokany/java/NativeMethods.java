package com.dokany.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.constants.FileAccess;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.DokanFileInfo;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.NTStatus;
import com.sun.jna.ptr.IntByReference;

/**
 * Native API to the kernel Dokany driver. This is an internal class and should not used directly by code outside com.dokany.java.
 *
 */
final class NativeMethods {
	private final static String DOKAN_DLL = "dokan1";
	private final static Logger LOGGER = LoggerFactory.getLogger(NativeMethods.class);

	static {
		Native.register(DOKAN_DLL);
	}

	/**
	 * Mount a new Dokan Volume. This function block until the device is unmount. If the mount fail, it will directly return {@link com.dokany.java.constants.MountError}.
	 *
	 * @param options A {@link com.dokany.java.structure.DeviceOptions} that describe the mount.
	 * @param operations Instance of {@link com.dokany.java.DokanyOperations} that will be called for each request made by the kernel.
	 * @return {@link com.dokany.java.constants.MountError}.
	 */
	final static native int DokanMain(DeviceOptions options, DokanyOperations operations);

	/**
	 * Get the version of Dokan.
	 *
	 * @return The version number without the dots.
	 */
	final static native long DokanVersion();

	/**
	 * Get the version of the Dokan driver.
	 *
	 * @return The version number without the dots.
	 */
	final static native long DokanDriverVersion();

	/**
	 * Unmount a dokan device from a driver letter.
	 *
	 * @param driveLetter Dokan driver letter to unmount.
	 * @return Trueif device was unmount or false in case of failure or device not found.
	 */
	final static native boolean DokanUnmount(char driveLetter);

	/**
	 * Unmount a dokan device from a mount point
	 *
	 * @param mountPoint Mount point to unmount Z or Z: or Z:\\ or Z:\MyMountPoint.
	 * @return
	 */
	final static native boolean DokanRemoveMountPoint(WString mountPoint);

	/**
	 * Extends the time out of the current IO operation in driver.
	 *
	 * @param timeout Extended time in milliseconds requested.
	 * @param dokanFileInfo {@link com.dokany.java.structure.DokanFileInfo} of the operation to extend.
	 * @return if the operation was successful or not.
	 */
	static native boolean DokanResetTimeout(long timeout, DokanFileInfo dokanFileInfo);

	/**
	 * Get the handle to Access Token.
	 *
	 * @param rawFileInfo {@link com.dokany.java.structure.DokanFileInfo} of the operation.
	 * @return A handle to the account token for the user on whose behalf the code is running.
	 */
	final static native IntByReference DokanOpenRequestorToken(DokanFileInfo dokanFileInfo);

	/**
	 * Convert {@link com.dokany.java.DokanyOperations.CreateFile} parameters to CreateFile parameters.
	 *
	 * @param fileAttributes FileAttributes
	 * @param createOptions CreateOptions
	 * @param createDisposition CreateDisposition
	 * @param outFileAttributesAndFlags
	 * @param outCreationDisposition
	 */
	final static native void DokanMapKernelToUserCreateFileFlags(
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
	final static native FileAccess DokanMapStandardToGenericAccess(FileAccess DesiredAccess);

	/**
	 * Checks whether Name can match Expression
	 *
	 * @param expression - Expression can contain wildcard characters (? and *)
	 * @param name - Name to check
	 * @param ignoreCase - Case sensitive or not
	 * @return result if name matches the expression
	 */
	final static native boolean DokanIsNameInExpression(String expression, String name, boolean ignoreCase);

	final static native boolean DokanServiceInstall(String serviceName, int serviceType, String serviceFullPath);

	final static native boolean DokanServiceDelete(String serviceName);

	final static native boolean DokanNetworkProviderInstall();

	final static native boolean DokanNetworkProviderUninstall();

	final static native boolean DokanSetDebugMode(int mode);

	final static native void DokanUseStdErr(boolean status);

	final static native void DokanDebugMode(boolean status);

	/**
	 * Get active Dokan mount points
	 *
	 * @param list - Allocate array of DOKAN_CONTROL
	 * @param length - Number of DOKAN_CONTROL instance in list.
	 * @param uncOnly - Get only instances that have UNC Name.
	 * @param nbRead- Number of instances successfully retrieved
	 * @return List retrieved or not.
	 */
	final static native boolean DokanGetMountPointList(long fileAttributes, long length, boolean uncOnly, long nbRead);

	/**
	 * Convert WIN32 error to NTSTATUS
	 *
	 * @see {@linkplain https://support.microsoft.com/en-us/kb/113996}
	 *
	 * @param error - Win32 Error to convert
	 * @return NtStatus associated to the error
	 */
	final static native NTStatus DokanNtStatusFromWin32(int error);
}