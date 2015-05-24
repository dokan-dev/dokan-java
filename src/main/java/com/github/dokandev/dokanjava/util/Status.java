package com.github.dokandev.dokanjava.util;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

	SUCCESS(0), ERROR(-1), DRIVE_LETTER_ERROR(-2), DRIVER_INSTALL_ERROR(-3), START_ERROR(-4),
	MOUNT_ERROR(-5), MOUNT_POINT_ERROR(-6), UNKNOWN(Integer.MAX_VALUE);

	private final int exitCode;

	private Status(int exitCode) {
		this.exitCode = exitCode;
	}
	
	public int code() {
	  return exitCode;
	}

	public static Status from(int exitCode) {
		Optional<Status> status = Arrays.stream(Status.values())
				.filter(s -> s.exitCode == exitCode).findAny();
		if (status.isPresent()) {
			return status.get();
		} else {
			return Status.UNKNOWN;
		}
	}

}
