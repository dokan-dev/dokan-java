package com.github.dokandev.dokanjava;

import com.sun.jna.Structure;
import com.sun.jna.WString;

import java.util.Arrays;
import java.util.List;

public class DOKAN_OPTIONS extends Structure implements Structure.ByReference {
    public short Version; // Version of the dokan features requested (version "123" is equal to Dokan version 1.2.3).
    public short ThreadCount; // Number of threads to be used internally by Dokan library. More thread will handle more event at the same time.
    public int Options; // Features enable for the mount. See <see cref="DokanOptions"/>.
    public long GlobalContext; // FileSystem can store anything here.
    public WString MountPoint; // Mount point. Can be <c>M:\\</c>(drive letter) or <c>C:\\mount\\dokan</c> (path in NTFS).
    public WString UNCName; // UNC name used for network volume.
    public int Timeout; // Max timeout in milliseconds of each request before Dokan give up.
    public int AllocationUnitSize; // Allocation Unit Size of the volume. This will behave on the file size.
    public int SectorSize; // Sector Size of the volume. This will behave on the file size.

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("Version", "ThreadCount", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize");
    }
}