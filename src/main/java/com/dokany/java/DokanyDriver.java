package com.dokany.java;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.constants.MountError;
import com.dokany.java.structure.DeviceOptions;
import com.sun.jna.WString;

/**
 * Main class to start and stop Dokany file system.
 *
 */
public final class DokanyDriver {
	@NotNull
	private final DokanyFileSystem fileSystem;
	@NotNull
	private final DeviceOptions deviceOptions;
	private final static Logger LOGGER = LoggerFactory.getLogger(DokanyDriver.class);

	public DokanyDriver(@NotNull final DeviceOptions deviceOptions, @NotNull final DokanyFileSystem fileSystem) {

		this.deviceOptions = deviceOptions;
		this.fileSystem = fileSystem;

		LOGGER.info("Dokany version: {}", getVersion());
		LOGGER.info("Dokany driver version: {}", getDriverVersion());
	}

	/**
	 * Get driver version.
	 *
	 * @see {@link com.dokany.java.NativeMethods#DokanDriverVersion()}
	 *
	 * @return
	 */
	public final long getDriverVersion() {
		return NativeMethods.DokanDriverVersion();
	}

	/**
	 * Get Dokany version.
	 *
	 * @see {@link com.dokany.java.NativeMethods#DokanVersion()}
	 * @return
	 */
	public final long getVersion() {
		return NativeMethods.DokanVersion();
	}

	/**
	 * Get file system.
	 *
	 * @return
	 */
	@NotNull
	public final DokanyFileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * Calls {@link com.dokany.java.NativeMethods#DokanMain(DeviceOptions, Operations)}. Has {@link java.lang.Runtime#addShutdownHook(Thread)} which calls {@link #shutdown()}
	 */
	public final void start() {
		try {
			final int mountStatus = NativeMethods.DokanMain(deviceOptions, new DokanyOperationsProxy(fileSystem));

			if (mountStatus < 0) {
				throw new IllegalStateException(MountError.fromInt(mountStatus).name);
			}

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					shutdown();
				}
			});
		} catch (final Throwable t) {
			LOGGER.warn("Error mounting", t);
			throw t;
		}
	}

	/**
	 * Calls {@link #stop(String)}.
	 */
	public final void shutdown() {
		stop(deviceOptions.MountPoint.toString());
	}

	/**
	 * Calls {@link com.dokany.java.NativeMethods#DokanUnmount(char)} and {@link com.dokany.java.NativeMethods#DokanRemoveMountPoint(WString)}
	 *
	 * @param mountPoint
	 */
	public final static void stop(@NotNull final String mountPoint) {
		LOGGER.info("Unmount and shutdown: {}", mountPoint);
		NativeMethods.DokanUnmount(mountPoint.charAt(0));
		NativeMethods.DokanRemoveMountPoint(new WString(mountPoint));
	}
}
