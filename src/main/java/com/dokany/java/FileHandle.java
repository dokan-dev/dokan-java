package com.dokany.java;

import java.nio.file.Path;

import com.dokany.java.structure.ByHandleFileInfo;

/**
 *
 * This should be extended by file system providers.
 *
 * @param <TNode>
 */
public abstract class FileHandle<TNode> {
	private final Path path;
	private final TNode node;
	private long id;

	public FileHandle(final Path path, final TNode node) {
		this.path = path;
		this.node = node;
	}

	public final Path getPath() {
		return path;
	}

	public final TNode getNode() {
		return node;
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
		return path.toString();
	}
}
