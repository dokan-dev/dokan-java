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

	private long lastAvailableID = 0;
	private final Queue<Long> availableIDs = new LinkedList<Long>();
	private final Map<Long, FileHandle<TNode>> handles = new HashMap<>();
	private final static Logger logger = LoggerFactory.getLogger(FileHandleStore.class);

	public final long getNextID() {
		long id;
		if (availableIDs.isEmpty()) {
			id = lastAvailableID++;
		} else {
			id = availableIDs.remove();
		}
		return id;
	}

	/**
	 * Only use if you know what you are doing.
	 *
	 * @param handle
	 * @param id Should be value from most recent call to {@link com.dokany.java.FileHandleStore#getNextID()}.
	 * @return
	 */
	public final long allocateHandle(final FileHandle<TNode> handle, final long id) {
		handles.put(id, handle);
		logger.trace("allocated id {} for {}", id, handle.getPath());
		handle.setID(id);
		return id;
	}

	public final long allocateHandle(final FileHandle<TNode> handle) {
		final long id = getNextID();
		handle.getFileInfo().setIndex(id);
		logger.debug("Set id to {} now printing out handle.getFileInfo to see if it actually stored {}", id, handle.getFileInfo());
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
		availableIDs.add(id);
		return handle;
	}

	public final FileHandle<TNode> getHandle(final long id) throws IOException {
		logger.trace("getHandle for ID {}", id);
		return handles.get(id);
	}
}
