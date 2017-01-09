package com.dokany.java.structure;

public class FreeSpace {
	private final long total;
	private final long used;

	public FreeSpace(final long total, final long used) {
		this.total = total;
		this.used = used;
	}

	public long getTotalBytes() {
		return total;
	}

	public long getUsedBytes() {
		return used;
	}

	public long getFreeBytes() {
		return total - used;
	}

	@Override
	public String toString() {
		return "{total: " + getTotalBytes() + System.lineSeparator()
		        + "   used: " + getUsedBytes() + System.lineSeparator()
		        + "   free: " + getFreeBytes()
		        + "}";
	}
}
