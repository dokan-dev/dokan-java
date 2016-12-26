package com.dokany.java;

import static com.dokany.java.constants.MountOptions.DEBUG_MODE;
import static com.dokany.java.constants.MountOptions.STD_ERR_OUTPUT;

import com.dokany.java.constants.MountError;
import com.dokany.java.structure.DeviceOptions;
import com.sun.jna.WString;

/**
 * Main class to start and stop Dokany file system.
 *
 * @param <TItem> This is used in {@link com.dokany.java.FileHandle}
 */
public class DokanyDriver<TItem> {

	private final FileSystem<TItem> fs;
	private final DeviceOptions driverOptions;

	public DokanyDriver(final String mountPoint, final FileSystem<TItem> fs) {
		this.fs = fs;
		int options = 0;

		if (fs.isDebug()) {
			options |= DEBUG_MODE.val;
		}

		if (fs.isDebugStderrOutput()) {
			options |= STD_ERR_OUTPUT.val;
		}

		final short threadCount = 1;
		driverOptions = new DeviceOptions(mountPoint, threadCount, options, null, fs.getTimeout(), fs.getAllocationUnitSize(), fs.getSectorSize());

		System.out.println("Dokany version: " + getVersion());
		System.out.println("Dokany driver version: " + getDriverVersion());
	}

	/**
	 * Get driver version.
	 *
	 * @see {@link com.dokany.java.NativeMethods#DokanDriverVersion()}
	 *
	 * @return
	 */
	public long getDriverVersion() {
		return NativeMethods.DokanDriverVersion();
	}

	/**
	 * Get Dokany version.
	 *
	 * @see {@link com.dokany.java.NativeMethods#DokanVersion()}
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
	public FileSystem<TItem> getFileSystem() {
		return fs;
	}

	/**
	 * Calls {@link com.dokany.java.NativeMethods#DokanMain(DeviceOptions, Operations)}. Has {@link java.lang.Runtime#addShutdownHook(Thread)} which calls {@link #shutdown()}
	 */
	public void start() {
		final int mountStatus = NativeMethods.DokanMain(driverOptions, new OperationsImpl<TItem>(fs) {
		});

		if (mountStatus < 0) {
			throw new IllegalStateException(MountError.fromInt(mountStatus).name);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdown();
			}
		});
	}

	/**
	 * Calls {@link #stop(String)}.
	 */
	public void shutdown() {
		stop(driverOptions.MountPoint.toString());
	}

	/**
	 * Calls {@link com.dokany.java.NativeMethods#DokanUnmount(char)} and {@link com.dokany.java.NativeMethods#DokanRemoveMountPoint(WString)}
	 *
	 * @param mountPoint
	 */
	public static void stop(final String mountPoint) {
		System.out.println("Unmount and shutdown: " + mountPoint);
		NativeMethods.DokanUnmount(mountPoint.charAt(0));
		NativeMethods.DokanRemoveMountPoint(new WString(mountPoint));
	}
}
