package com.github.dokandev.dokanjava;

import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

public class Dokan {
    static public final int LIBRARY_VERSION = 100;

    static public DOKAN_OPTIONS createOptions(int options, String mountPoint, int timeout) {
        DOKAN_OPTIONS opts = new DOKAN_OPTIONS();
        opts.Version = LIBRARY_VERSION;
        opts.ThreadCount = 1;
        opts.Options = options;
        opts.GlobalContext = 0L; // context
        opts.MountPoint = new WString(mountPoint);
        opts.UNCName = null;
        opts.Timeout = timeout;
        opts.AllocationUnitSize = 4096;
        opts.SectorSize = 4096;
        return opts;
    }

    static public int main(String mountPoint, DokanFilesystem fs) {
        DOKAN_OPTIONS opts = createOptions(0, mountPoint, fs.getTimeout());
        if (fs.getDebug()) opts.Options |= DokanOptions.DebugMode;
        if (fs.getDebugStderrOutput()) opts.Options |= DokanOptions.StderrOutput;
        opts.AllocationUnitSize = fs.getAllocationUnitSize();
        opts.SectorSize = fs.getSectorSize();
        opts.Timeout = fs.getTimeout();
        return main(opts, DokanFilesystemAdaptor.toStruct(fs));
    }

    static public int main(DOKAN_OPTIONS options, DOKAN_OPERATIONS operations) {
        return DokanNativeMethods.INSTANCE.DokanMain(options, operations);
    }

    static public boolean unmount(char driveLetter) {
        return DokanNativeMethods.INSTANCE.DokanUnmount(driveLetter);
    }

    static public int version() {
        return DokanNativeMethods.INSTANCE.DokanVersion();
    }

    static public int driverVersion() {
        return DokanNativeMethods.INSTANCE.DokanDriverVersion();
    }

    static public boolean removeMountPoint(String mountPoint) {
        return DokanNativeMethods.INSTANCE.DokanRemoveMountPoint(new WString(mountPoint));
    }

    static public boolean resetTimeout(int timeout, DokanFileInfo rawFileInfo) {
        return DokanNativeMethods.INSTANCE.DokanResetTimeout(timeout, rawFileInfo);
    }

    static public IntByReference openRequestorToken(DokanFileInfo rawFileInfo) {
        return DokanNativeMethods.INSTANCE.DokanOpenRequestorToken(rawFileInfo);
    }

    static public void mapKernelToUserCreateFileFlags(int fileAttributes, int createOptions, int createDisposition, IntByReference outFileAttributesAndFlags, IntByReference outCreationDisposition) {
        DokanNativeMethods.INSTANCE.DokanMapKernelToUserCreateFileFlags(fileAttributes, createOptions, createDisposition, outFileAttributesAndFlags, outCreationDisposition);
    }
}
