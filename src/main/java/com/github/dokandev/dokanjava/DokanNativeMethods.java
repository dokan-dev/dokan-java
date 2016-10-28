package com.github.dokandev.dokanjava;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

public interface DokanNativeMethods extends Library {
    String DOKAN_DLL = "dokan1";
    DokanNativeMethods INSTANCE = (DokanNativeMethods) Native.loadLibrary(DOKAN_DLL, DokanNativeMethods.class);

    int DokanMain(DOKAN_OPTIONS options, DOKAN_OPERATIONS operations);

    boolean DokanUnmount(char driveLetter);

    int DokanVersion();

    int DokanDriverVersion();

    boolean DokanRemoveMountPoint(WString mountPoint);

    boolean DokanResetTimeout(int timeout, DokanFileInfo rawFileInfo);

    IntByReference DokanOpenRequestorToken(DokanFileInfo rawFileInfo);

    void DokanMapKernelToUserCreateFileFlags(int fileAttributes, int createOptions, int createDisposition, IntByReference outFileAttributesAndFlags, IntByReference outCreationDisposition);
}