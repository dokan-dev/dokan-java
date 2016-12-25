package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.WString;

public class Options extends Structure implements Structure.ByReference {
	public short Version = 100; // Version of the dokan features requested
	// (version
	// "123" is equal to Dokan version 1.2.3).
	public short ThreadCount = 1; // Number of threads to be used internally
	// by Dokan library. More thread will handle
	// more
	// event at the same time.
	public int Options = 0; // Features enable for the mount. See <see
	// cref="DokanOptions"/>.
	public long GlobalContext = 0L; // FileSystem can store anything here.
	public WString MountPoint; // Mount point. Can be <c>M:\\</c>(drive
	                           // letter)
	// or <c>C:\\mount\\dokan</c> (path in NTFS).
	public WString UNCName = null; // UNC name used for network volume.
	public long Timeout; // Max timeout in milliseconds of each request
	                     // before Dokan give up.
	public long AllocationUnitSize; // Allocation Unit Size of the
	                                // volume.
	// This
	// will behave on the file size.
	public long SectorSize; // Sector Size of the volume. This will
	                        // behave
	// on the
	// file size.

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("Version", "ThreadCount", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize");
	}

	public Options(final String mountPoint) {
		MountPoint = new WString(mountPoint);
	}

}