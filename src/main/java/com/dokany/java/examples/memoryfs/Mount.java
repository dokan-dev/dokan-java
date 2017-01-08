package com.dokany.java.examples.memoryfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyDriver;
import com.dokany.java.constants.MountOptions;
import com.dokany.java.structure.DeviceOptions;

/**
 * Mounts MemoryFS at M:\\.
 */
public class Mount {
	private final static Logger LOGGER = LoggerFactory.getLogger(Mount.class);

	public static void main(final String[] args) throws Throwable {
		LOGGER.info("Starting Dokany MemoryFS");

		final String mountPoint = "M:\\";
		final short threadCount = 1;
		// final int options = MountOptions.toInt(MountOptions.DEBUG_MODE, MountOptions.STD_ERR_OUTPUT, MountOptions.MOUNT_MANAGER);
		final int options = MountOptions.toInt(MountOptions.DEBUG_MODE, MountOptions.STD_ERR_OUTPUT);
		final String uncName = "";
		final long timeout = 10000;
		final long allocationUnitSize = 4096;
		final long sectorSize = 4096;

		final DeviceOptions deviceOptions = new DeviceOptions(mountPoint, threadCount, options, uncName, timeout, allocationUnitSize, sectorSize);

		final DokanyDriver dokanyDriver = new DokanyDriver(deviceOptions, new MemoryFS(deviceOptions));
		dokanyDriver.start();
	}
}