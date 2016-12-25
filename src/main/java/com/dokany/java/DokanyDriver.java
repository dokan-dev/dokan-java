package com.dokany.java;

import static com.dokany.java.constants.MountOptions.DEBUG_MODE;
import static com.dokany.java.constants.MountOptions.STD_ERR_OUTPUT;

import com.dokany.java.structure.Options;
import com.sun.jna.WString;

public class DokanyDriver<TItem> {

	private final FileSystem<TItem> fs;

	private final Options driverOptions;

	public DokanyDriver(final String mountPoint, final FileSystem<TItem> fs) {
		this.fs = fs;
		int options = 0;

		if (fs.isDebug()) {
			options |= DEBUG_MODE.val;
		}

		if (fs.isDebugStderrOutput()) {
			options |= STD_ERR_OUTPUT.val;
		}

		driverOptions = new Options(mountPoint);
		driverOptions.Options = options;
		driverOptions.Timeout = fs.getTimeout();
		driverOptions.AllocationUnitSize = fs.getAllocationUnitSize();
		driverOptions.SectorSize = fs.getSectorSize();
	}

	public long getDriverVersion() {
		return NativeMethods.DokanDriverVersion();
	}

	public long getVersion() {
		return NativeMethods.DokanVersion();
	}

	public FileSystem<TItem> getFileSystem() {
		return fs;
	}

	public void start() {
		System.out.println("Dokany version: " + getVersion());
		System.out.println("Dokany driver version: " + getDriverVersion());

		NativeMethods.DokanMain(driverOptions, new OperationsImpl<TItem>(fs) {
		});

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdown();
			}
		});
	}

	public void shutdown() {
		stop(driverOptions.MountPoint.toString());
	}

	public static void stop(final String mountPoint) {
		System.out.println("Unmount and shutdown: " + mountPoint);
		NativeMethods.DokanUnmount(mountPoint.charAt(0));
		NativeMethods.DokanRemoveMountPoint(new WString(mountPoint));
	}
}
