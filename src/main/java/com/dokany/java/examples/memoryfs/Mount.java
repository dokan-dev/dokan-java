package com.dokany.java.examples.memoryfs;

import static com.dokany.java.constants.MountOptions.DEBUG_MODE;
import static com.dokany.java.constants.MountOptions.STD_ERR_OUTPUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyDriver;
import com.dokany.java.structure.DeviceOptions;

/**
 * Mounts MemoryFS at M:\\.
 */
public class Mount {
	private final static Logger LOGGER = LoggerFactory.getLogger(Mount.class);

	public static void main(final String[] args) throws Throwable {
		LOGGER.info("Starting Dokany MemoryFS");

		final String mountPoint = "M:\\";
		final short threadCount = 5;
		int options = 0;
		options |= DEBUG_MODE.val;
		options |= STD_ERR_OUTPUT.val;
		final String uncName = null;
		final long timeout = 10000;
		final long allocationUnitSize = 4096;
		final long sectorSize = 4096;

		final DeviceOptions deviceOptions = new DeviceOptions(mountPoint, threadCount, options, uncName, timeout, allocationUnitSize, sectorSize);

		final DokanyDriver dokanyDriver = new DokanyDriver(deviceOptions, new MemoryFS(deviceOptions));
		dokanyDriver.start();
	}
}