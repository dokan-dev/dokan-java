package com.dokany.java.examples.memoryfs;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyDriver;
import com.dokany.java.constants.FileSystemFeature;
import com.dokany.java.constants.MountOption;
import com.dokany.java.examples.mirrorfs.MirrorFS;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.EnumIntegerSet;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.VolumeInformation;

/**
 * Mounts MemoryFS at M:\\.
 */
public class MountMemoryFS {
	private final static Logger LOGGER = LoggerFactory.getLogger(MountMemoryFS.class);

	public static void main(final String[] args) throws Throwable {
		LOGGER.info("Starting Dokany MemoryFS");

		final String mountPoint = "M:\\";
		final short threadCount = 1;
		final EnumIntegerSet<MountOption> mountOptions = new EnumIntegerSet<>(MountOption.class);
		mountOptions.add(MountOption.DEBUG_MODE, MountOption.STD_ERR_OUTPUT, MountOption.MOUNT_MANAGER);
		final String uncName = "";
		final long timeout = 10000;
		final long allocationUnitSize = 4096;
		final long sectorSize = 4096;

		final DeviceOptions deviceOptions = new DeviceOptions(mountPoint, threadCount, mountOptions, uncName, timeout, allocationUnitSize, sectorSize);

		final EnumIntegerSet<FileSystemFeature> fsFeatures = new EnumIntegerSet<>(FileSystemFeature.class);
		fsFeatures.add(FileSystemFeature.CASE_PRESERVED_NAMES, FileSystemFeature.CASE_SENSITIVE_SEARCH, FileSystemFeature.UNICODE_ON_DISK);

		final VolumeInformation volumeInfo = new VolumeInformation(VolumeInformation.DEFAULT_MAX_COMPONENT_LENGTH, "Mirror", 0x234234, "MirrorFS", fsFeatures);
		final FreeSpace freeSpace = new FreeSpace(1024L * 1024L * 256L, 1024L * 1024L);

		final MirrorFS mirrorFS = new MirrorFS(deviceOptions, volumeInfo, freeSpace, new Date(), "/");

		final DokanyDriver dokanyDriver = new DokanyDriver(deviceOptions, mirrorFS);
		dokanyDriver.start();
	}
}