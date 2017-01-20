package com.dokany.java.structure;

import lombok.Data;

@Data
public class FreeSpace {
	private final long totalBytes;
	private final long totalUsed;

	public long getFreeBytes() {
		return totalBytes - totalUsed;
	}
}
