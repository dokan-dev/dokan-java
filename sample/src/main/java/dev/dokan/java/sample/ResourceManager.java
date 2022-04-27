package dev.dokan.java.sample;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ResourceManager {

	private final ConcurrentHashMap<MemoryPath, Resource> existingResources;
	private final AtomicLong numberOfFiles;
	private final AtomicLong numberOfDirs;

	public ResourceManager() {
		this.existingResources = new ConcurrentHashMap<>();
		existingResources.put(MemoryPath.ROOT,new Directory("\\"));
		this.numberOfFiles = new AtomicLong();
		this.numberOfDirs = new AtomicLong();
	}

	public void put(MemoryPath key, Resource resource) {
		existingResources.put(key, resource);
		if (existingResources.get(key.getParent()) instanceof Directory d) {
			d.addResource(resource);
		}
		switch (resource.getType()) {
			case FILE -> numberOfFiles.incrementAndGet();
			case DIR -> numberOfDirs.incrementAndGet();
		}
	}

	public boolean exists(MemoryPath path) {
		return existingResources.containsKey(path);
	}

	public Resource get(MemoryPath path) {
		return existingResources.get(path);
	}

	public void remove(MemoryPath path) {
		var resource = existingResources.remove(path);
		if (existingResources.get(path.getParent()) instanceof Directory d) {
			d.removeResource(resource);
		}
		switch (resource.getType()) {
			case FILE -> numberOfFiles.decrementAndGet();
			case DIR -> numberOfDirs.decrementAndGet();
		}
	}

	/**
	 * Removes all resources.
	 *
	 * @apiNote Visible for testing
	 */
	void clear() {
		existingResources.clear();
		numberOfDirs.set(0);
		numberOfFiles.set(0);
	}


	//with Runtime.getRuntime().maxMemory() we get Heapsize -> can set the quota to this!
	class Quota {

		private final AtomicLong bytesUsed;
		private final long capacity;

		Quota (long capacity) {
			this.capacity = capacity;
			this.bytesUsed = new AtomicLong(0);
		}
	}
}
