package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.dokany.java.Utils;
import com.sun.jna.Structure;
import com.sun.jna.WString;

/**
 * Dokan mount options used to describe dokan device behavior. This is the same structure as PDOKAN_OPTIONS (dokan.h) in the C++ version of Dokan.
 *
 */
public class DeviceOptions extends Structure implements Structure.ByReference {

	/**
	 * Version of the dokan features requested (version "123" is equal to Dokan version 1.2.3). Currently is 100.
	 */
	public short Version = 100;

	/**
	 * Number of threads to be used internally by Dokan library. More thread will handle more event at the same time.
	 */
	public short ThreadCount;

	/**
	 * Features enable for the mount.
	 * 
	 * @see {@link com.dokany.java.constants.MountOptions}
	 */
	public int Options;

	/**
	 * FileSystem can store anything here
	 */
	public long GlobalContext = 0L;

	/**
	 * Mount point. Can be M:\\ (drive letter) or C:\\mount\\dokan (path in NTFS).
	 */
	public WString MountPoint;

	/**
	 * UNC name used for network volume.
	 */
	public WString UNCName;

	/**
	 * Max timeout in milliseconds of each request before Dokan give up.
	 */
	public long Timeout;

	/**
	 * Allocation Unit Size of the volume.
	 */
	public long AllocationUnitSize;

	/**
	 * Sector Size of the volume.
	 */
	public final long SectorSize;

	public DeviceOptions(
	        final String mountPoint,
	        final short threadCount,
	        final int dokanyOptions,
	        final String uncName,
	        final long timeout,
	        final long allocationUnitSize,
	        final long sectorSize) {

		MountPoint = new WString(mountPoint);
		ThreadCount = threadCount;
		Options = dokanyOptions;
		if (Utils.isNotNull(uncName)) {
			UNCName = new WString(uncName);
		} else {
			UNCName = null;
		}
		Timeout = timeout;
		AllocationUnitSize = allocationUnitSize;
		SectorSize = sectorSize;
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("Version", "ThreadCount", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize");
	}

}