package dev.dokan.dokan_java.wrappers;

public class DiskSpaceInfo {

	private long usableSpace;
	private long totalSpace;
	private long unallocatedSpace;

	public DiskSpaceInfo() {
		this(0L, 0L, 0L);
	}

	public DiskSpaceInfo(long usableSpace, long totalSpace, long unallocatedSpace) {
		this.usableSpace = usableSpace;
		this.totalSpace = totalSpace;
		this.unallocatedSpace = unallocatedSpace;
	}

	public long getUsableSpace() {
		return this.usableSpace;
	}

	public void setUsableSpace(long usableSpace) {
		this.usableSpace = usableSpace;
	}

	public long getTotalSpace() {
		return this.totalSpace;
	}

	public void setTotalSpace(long totalSpace) {
		this.totalSpace = totalSpace;
	}

	public long getUnallocatedSpace() {
		return this.unallocatedSpace;
	}

	public void setUnallocatedSpace(long unallocatedSpace) {
		this.unallocatedSpace = unallocatedSpace;
	}
}