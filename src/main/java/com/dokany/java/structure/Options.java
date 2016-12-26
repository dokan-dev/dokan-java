package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.WString;

public class Options extends Structure implements Structure.ByReference {

	// Version of the dokan features requested (version "123" is equal to Dokan version 1.2.3).
	public short Version = 100;

	// Number of threads to be used internally by Dokan library. More thread will handle more event at the same time.
	public short ThreadCount = 1;

	// Features enable for the mount. See DokanOptions
	public int Options = 0;

	// FileSystem can store anything here
	public long GlobalContext = 0L;

	// Mount point. Can be M:\\ (drive letter) or C:\\mount\\dokan (path in NTFS).
	public WString MountPoint;

	// UNC name used for network volume.
	public WString UNCName = null;

	// Max timeout in milliseconds of each request before Dokan give up.
	public long Timeout;

	// Allocation Unit Size of the volume.
	public long AllocationUnitSize;

	// Sector Size of the volume.
	public long SectorSize;

	public Options(final String mountPoint) {
		MountPoint = new WString(mountPoint);
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("Version", "ThreadCount", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize");
	}

}