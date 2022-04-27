package dev.dokan.java;

import dev.dokan.java.nativeannotations.Enum;
import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.nativeannotations.Unsigned;
import dev.dokan.java.structures.DokanFileInfo;
import dev.dokan.java.structures.DokanOperations;
import dev.dokan.java.structures.DokanOptions;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

public final class DokanAPI {

	private static final String DOKAN_DLL = System.getProperty("dev.dokan.java.libraryPath","dokan2");

	static {
		Native.register(DOKAN_DLL);
	}

	private DokanAPI() {
	}

	public static native void DokanInit();

	public static native void DokanShutdown();

	public static native void DokanMain(DokanOptions options, DokanOperations operations);

	//TODO: return value?
	public static native int DokanCreateFileSystem(DokanOptions options, DokanOperations operations, Pointer ptrToInstance);

	public static native boolean DokanIsFileSystemRunning(Pointer instance);

	//TODO: return value?
	public static native int DokanWaitForFileSystemClosed(Pointer instance, @Unsigned int dwMilliseconds);

	public static native void DokanCloseHandle(Pointer instance);

	public static native boolean DokanUnmount(char driveLetter);

	public static native boolean DokanRemoveMountPoint(WString mountPoint);

	public static native boolean DokanIsNameInExpression(WString Expression, WString Name, boolean IgnoreCase);

	public static native @Unsigned int DokanVersion();

	public static native @Unsigned int DokanDriverVersion();

	public static native boolean DokanResetTimeout(@Unsigned int Timeout, DokanFileInfo DokanFileInfo);

	public static native WinNT.HANDLE DokanOpenRequestorToken(DokanFileInfo DokanFileInfo);

	/**
	 * private static native PDOKAN_MOUNT_POINT_INFO DokanGetMountPointList(boolean uncOnly, IntByReference nbRead);
	 * <p>
	 * private static native void DokanReleaseMountPointList(PDOKAN_MOUNT_POINT_INFO list);
	 **/

	public static native void DokanMapKernelToUserCreateFileFlags(@EnumSet int desiredAccess, @EnumSet int fileAttributes, @EnumSet int createOptions, @EnumSet int createDisposition, @EnumSet IntByReference outDesiredAccess, @EnumSet IntByReference outFileAttributesAndFlags, @EnumSet IntByReference outCreationDisposition);


	//Notify

	/**
	 * BOOL DOKANAPI DokanNotifyCreate(_In_ DOKAN_HANDLE DokanInstance,
	 * _In_ LPCWSTR FilePath, _In_ BOOL IsDirectory);
	 * <p>
	 * BOOL DOKANAPI DokanNotifyDelete(_In_ DOKAN_HANDLE DokanInstance,
	 * _In_ LPCWSTR FilePath, _In_ BOOL IsDirectory);
	 * <p>
	 * BOOL DOKANAPI DokanNotifyUpdate(_In_ DOKAN_HANDLE DokanInstance,
	 * _In_ LPCWSTR FilePath);
	 * <p>
	 * BOOL DOKANAPI DokanNotifyXAttrUpdate(_In_ DOKAN_HANDLE DokanInstance,
	 * _In_ LPCWSTR FilePath);
	 * <p>
	 * BOOL DOKANAPI DokanNotifyRename(_In_ DOKAN_HANDLE DokanInstance,
	 * _In_ LPCWSTR OldPath, _In_ LPCWSTR NewPath,
	 * _In_ BOOL IsDirectory,
	 * _In_ BOOL IsInSameDirectory);
	 */

	//first int is NTSTATUS, second DWORD
	public static native int DokanNtStatusFromWin32(@Enum int Error);


}
