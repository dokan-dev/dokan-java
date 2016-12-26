package com.dokany.java;

import com.dokany.java.structure.FileInfo;

public abstract class FileHandle<TItem> {
	private final String fileName;
	private final TItem item;
	private long id;

	public FileHandle(final String fileName, final TItem item) {
		this.fileName = fileName;
		this.item = item;
	}

	public String getFileName() {
		return fileName;
	}

	public TItem getItem() {
		return item;
	}

	public void setID(final long id) {
		this.id = id;
	}

	public long getID() {
		return id;
	}

	public abstract FileInfo getFileInfo();

	@Override
	public String toString() {
		return fileName;
	}
}
