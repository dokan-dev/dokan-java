package com.dokany.java.examples;

import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_DIRECTORY;
import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_NORMAL;

import com.dokany.java.FileHandle;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.FileInfo;

class NodeFileHandle extends FileHandle<Node> {
	NodeFileHandle(final String fileName, final Node item) {
		super(fileName, item);
	}

	static FileInfo getFileInfo(final Node item) {
		final String name = item.getName();
		final FileAttribute attributes = item.isDirectory() ? FILE_ATTRIBUTE_DIRECTORY : FILE_ATTRIBUTE_NORMAL;
		final long size = item.getSize();
		return new FileInfo.Builder(name).size(size).attributes(attributes).buildFileInfo();
	}

	@Override
	public FileInfo getFileInfo() {
		return NodeFileHandle.getFileInfo(getItem());
	}
}
