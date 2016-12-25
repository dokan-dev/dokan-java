package com.dokany.java;

import com.dokany.java.structure.FileInfo;

public abstract class FileHandle<TItem> {
	public final String fileName;
	public final TItem item;

	public FileHandle(final String fileName, final TItem item) {
		this.fileName = fileName;
		this.item = item;
	}

	public abstract FileInfo getFileInfo();
}
