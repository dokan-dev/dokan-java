package com.dokany.java.examples.memoryfs;

import java.nio.file.Path;

import com.dokany.java.FileHandle;
import com.dokany.java.structure.ByHandleFileInfo;

class NodeFileHandle extends FileHandle<Node> {
	NodeFileHandle(final Path path, final Node node) {
		super(path, node);
	}

	static ByHandleFileInfo getFileInfo(final Node node) {
		return node.getInfo();
	}

	@Override
	public ByHandleFileInfo getFileInfo() {
		return NodeFileHandle.getFileInfo(getNode());
	}
}
