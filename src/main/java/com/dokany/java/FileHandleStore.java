package com.dokany.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FileHandleStore {

	private long lastAvailableId = 1;
	private final Queue<Long> availableIds = new LinkedList<Long>();
	final Map<Long, FileHandle> handles = new HashMap<>();

	public final <TItem> long allocateFileHandle(final FileHandle<TItem> handle) {
		long id;
		if (availableIds.isEmpty()) {
			id = lastAvailableId++;
		} else {
			id = availableIds.remove();
		}
		handles.put(id, handle);
		System.out.println("&&&&&&&&& ALLOCATE: " + id);
		return id;
	}

	public final <TItem> FileHandle<TItem> removeFileHandle(final long id) {
		System.out.println("&&&&&&&&& REMOVE: " + id);
		final FileHandle<TItem> handle = handles.remove(id);
		availableIds.add(id);
		return handle;
	}

	public final <TItem> FileHandle<TItem> getFileHandle(final String fileName, final long id) throws IOException {
		if (id == 0) {
			System.out.println("*");
			return null;
		}
		System.out.println("&&&&&&&&& GET: " + id);
		return handles.get(id);
	}
}
