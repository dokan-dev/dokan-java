package com.dokany.java.examples.mirrorfs;

import java.util.Date;

import com.dokany.java.DokanyDriver;
import com.dokany.java.constants.FileSystemFeature;
import com.dokany.java.constants.MountOption;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.EnumIntegerSet;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.VolumeInformation;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * Mounts MirrorFS at L:\\.
 */
@Slf4j
public class MountMirrorFS {

	public static void main(final String[] args) throws Throwable {
		log.info("Starting Dokany MirrorFS");

		val mountPoint = "K:\\";
		final short threadCount = 1;
		val mountOptions = new EnumIntegerSet<>(MountOption.class);
		mountOptions.add(MountOption.DEBUG_MODE, MountOption.STD_ERR_OUTPUT, MountOption.MOUNT_MANAGER);
		val uncName = "";
		val timeout = 10000;
		val allocationUnitSize = 4096;
		val sectorSize = 4096;

		val deviceOptions = new DeviceOptions(mountPoint, threadCount, mountOptions, uncName, timeout, allocationUnitSize, sectorSize);

		val fsFeatures = new EnumIntegerSet<>(FileSystemFeature.class);
		fsFeatures.add(FileSystemFeature.CASE_PRESERVED_NAMES, FileSystemFeature.CASE_SENSITIVE_SEARCH,
		        FileSystemFeature.PERSISTENT_ACLS, FileSystemFeature.SUPPORTS_REMOTE_STORAGE, FileSystemFeature.UNICODE_ON_DISK);

		val volumeInfo = new VolumeInformation(VolumeInformation.DEFAULT_MAX_COMPONENT_LENGTH, "Mirror", 0x98765432, "Dokany MirrorFS", fsFeatures);
		val freeSpace = new FreeSpace(200000, 200);

		val mirrorFS = new MirrorFS(deviceOptions, volumeInfo, freeSpace, new Date(), "C:\\development");
		val dokanyDriver = new DokanyDriver(deviceOptions, mirrorFS);

		dokanyDriver.start();
	}
}
