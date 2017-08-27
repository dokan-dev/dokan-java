package com.dokany.java;

import com.dokany.java.constants.MountError;
import com.dokany.java.structure.DeviceOptions;
import com.sun.jna.WString;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * Main class to start and stop Dokany file system.
 *
 */
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class DokanyDriver {
	@NonNull
	DeviceOptions deviceOptions;
	@NonNull
	DokanyFileSystem fileSystem;

	public DokanyDriver(@NonNull final DeviceOptions deviceOptions, @NonNull final DokanyFileSystem fileSystem) {

		this.deviceOptions = deviceOptions;
		this.fileSystem = fileSystem;

		log.info("Dokany version: {}", getVersion());
		log.info("Dokany driver version: {}", getDriverVersion());
	}

	/**
	 * Get driver version.
	 *
	 * @see {@link NativeMethods#DokanDriverVersion()}
	 *
	 * @return
	 */
	public long getDriverVersion() {
		return NativeMethods.DokanDriverVersion();
	}

	/**
	 * Get Dokany version.
	 *
	 * @see {@link NativeMethods#DokanVersion()}
	 * @return
	 */
	public long getVersion() {
		return NativeMethods.DokanVersion();
	}

	/**
	 * Get file system.
	 *
	 * @return
	 */
	@NonNull
	public DokanyFileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * Calls {@link com.dokany.java.NativeMethods#DokanMain(DeviceOptions, Operations)}. Has {@link java.lang.Runtime#addShutdownHook(Thread)} which calls {@link #shutdown()}
	 */
	public void start() {
		try {
			val mountStatus = NativeMethods.DokanMain(deviceOptions, new DokanyOperationsProxy(fileSystem));

			if (mountStatus < 0) {
				throw new IllegalStateException(MountError.fromInt(mountStatus).getDescription());
			}

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					shutdown();
				}
			});
		} catch (final Throwable t) {
			log.warn("Error mounting", t);
			throw t;
		}
	}

	/**
	 * Calls {@link #stop(String)}.
	 */
	public void shutdown() {
		stop(deviceOptions.MountPoint.toString());
	}

	/**
	 * Calls {@link NativeMethods#DokanUnmount(char)} and {@link NativeMethods#DokanRemoveMountPoint(WString)}
	 *
	 * @param mountPoint
	 */
	public static void stop(@NonNull final String mountPoint) {
		log.info("Unmount and shutdown: {}", mountPoint);
		NativeMethods.DokanUnmount(mountPoint.charAt(0));
		NativeMethods.DokanRemoveMountPoint(new WString(mountPoint));
	}
}
