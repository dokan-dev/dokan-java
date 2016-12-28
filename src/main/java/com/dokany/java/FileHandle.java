package com.dokany.java;

import com.dokany.java.structure.ByHandleFileInfo;

/**
 *
 * This should be extended by file system providers.
 *
 * @param <TItem>
 */
public abstract class FileHandle<TItem> {
	private final String fileName;
	private final TItem item;
	private long id;

	public FileHandle(final String fileName, final TItem item) {
		this.fileName = fileName;
		this.item = item;
	}

	public final String getFileName() {
		return fileName;
	}

	public final TItem getItem() {
		return item;
	}

	public void setID(final long id) {
		this.id = id;
	}

	public final long getID() {
		return id;
	}

	public abstract ByHandleFileInfo getFileInfo();

	@Override
	public String toString() {
		// TODO: add id
		return fileName;
	}
}
