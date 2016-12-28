package com.dokany.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
public class FileHandleStore<TNode> {

	private long lastAvailableId = 0;
	private final Queue<Long> availableIds = new LinkedList<Long>();
	private final Map<Long, FileHandle<TNode>> handles = new HashMap<>();
	private final static Logger logger = LoggerFactory.getLogger(FileHandleStore.class);

	public final long getNextID(final FileHandle<TNode> handle) {
		long id;
		if (availableIds.isEmpty()) {
			id = lastAvailableId++;
		} else {
			id = availableIds.remove();
		}
		return id;
	}

	private final long allocateHandle(final FileHandle<TNode> handle, final long id) {
		handles.put(id, handle);
		logger.debug("stashed id in allocateFileHandle: {}", id);
		handle.setID(id);
		return id;
	}

	public final long allocateHandle(final FileHandle<TNode> handle) {
		final long id = getNextID(handle);
		return allocateHandle(handle, id);
	}

	/**
	 * Returns the handle that was removed
	 *
	 * @param id
	 * @return
	 */
	public final FileHandle<TNode> removeHandle(final long id) {
		final FileHandle<TNode> handle = handles.remove(id);
		availableIds.add(id);
		return handle;
	}

	public final FileHandle<TNode> getHandle(final long id) throws IOException {
		logger.debug("getFileHandle for ID {}", id);
		return handles.get(id);
	}
}
