package com.dokany.java.examples.memoryfs;

import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_DIRECTORY;
import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_NORMAL;

import java.nio.file.Path;

import com.dokany.java.FileHandle;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.ByHandleFileInfo;
import com.dokany.java.structure.ByHandleFileInfo.FileInfoBuilder;

class NodeFileHandle extends FileHandle<Node> {
	NodeFileHandle(final Path path, final Node item) {
		super(path, item);
	}

	static ByHandleFileInfo getFileInfo(final Node item) {
		final Path path = item.getPath();
		final FileAttribute attributes = item.isDirectory() ? FILE_ATTRIBUTE_DIRECTORY : FILE_ATTRIBUTE_NORMAL;
		final long size = item.getSize();
		final FileInfoBuilder builder = new ByHandleFileInfo.FileInfoBuilder(path);
		builder.size(size);
		builder.attributes(attributes);
		return builder.build();
	}

	@Override
	public ByHandleFileInfo getFileInfo() {
		return NodeFileHandle.getFileInfo(getNode());
	}
}
