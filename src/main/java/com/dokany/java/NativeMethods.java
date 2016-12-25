package com.dokany.java;

import com.dokany.java.structure.FileInfoRaw;
import com.dokany.java.structure.Options;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

class NativeMethods {
	static final String DOKAN_DLL = "dokan1";

	static {
		Native.register(DOKAN_DLL);
	}

	static native int DokanMain(Options options, Operations operations);

	static native long DokanVersion();

	static native long DokanDriverVersion();

	static native boolean DokanUnmount(char driveLetter);

	static native boolean DokanRemoveMountPoint(WString mountPoint);

	static native boolean DokanResetTimeout(long timeout, FileInfoRaw rawFileInfo);

	static native IntByReference DokanOpenRequestorToken(FileInfoRaw rawFileInfo);

	static native void DokanMapKernelToUserCreateFileFlags(long fileAttributes, long createOptions, long createDisposition, IntByReference outFileAttributesAndFlags, IntByReference outCreationDisposition);
}