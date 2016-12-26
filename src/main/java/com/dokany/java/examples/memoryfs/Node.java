package com.dokany.java.examples.memoryfs;

import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

import com.dokany.java.DokanyException;
import com.dokany.java.Utils;
import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;

class Node {
	private String name;
	private Node parent;
	private final LinkedHashMap<String, Node> children = new LinkedHashMap<String, Node>();
	private byte[] data = null;
	public Date date = new Date();

	// constructor
	Node() {

	}

	Node createChild(final String name) {
		final Node child = new Node();
		child.parent = this;
		child.name = (name == null) ? Utils.BACKSLASH : this.name;
		children.put(name, child);
		return child;
	}

	boolean isDirectory() {
		return data == null;
	}

	long getSize() {
		final long size = 0L;
		if (data != null) {
			return data.length;
		}
		return size;
	}

	void delete() {
		if (parent != null) {
			parent.children.remove(name);
		}
	}

	Node findExisting(final String path) throws IOException {
		return find(path, false);
	}

	Node createFile(final String fileName, final CreationDisposition disposition, final long options, final boolean isDirectory, final FileAttribute... attributes)
	        throws IOException {
		// TODO: Add other parameters
		return find(fileName, true);
	}

	// TODO: Add other parameters from createFile
	private Node find(final String path, final boolean create) throws IOException {
		final String[] parts = Utils.getPathParts(path);
		Node cur = this;
		for (int i = 0; (cur != null) && (i < parts.length); i++) {
			// final boolean last = i == (parts.length - 1);
			cur = cur.child(parts[i], true);
		}

		return cur;
	}

	private Node child(final String childName, final boolean create) throws IOException {
		if (childName.equals("..")) {
			return parent;
		}
		if (childName.equals(".")) {
			return this;
		}
		if (childName.equals("")) {
			return this;
		}
		if (children.containsKey(childName)) {
			return children.get(childName);
		}
		if (!create) {
			throw new FileNotFoundException(toString() + Utils.FORWARDSLASH + childName);
		}
		return createChild(childName);
	}

	int read(final long loffset, final byte[] out, final int len) {
		final int offset = (int) loffset;
		if (data == null) {
			throw new DokanyException(ERROR_READ_FAULT);
		}
		final int readlen = Math.min(len, data.length - offset);
		System.arraycopy(data, offset, out, 0, readlen);
		return readlen;
	}

	int write(final long loffset, final byte[] data, final int dataLength) {
		final int offset = (int) loffset;
		if (this.data == null) {
			this.data = new byte[0];
		}
		this.data = Arrays.copyOf(this.data, Math.max(this.data.length, offset + dataLength));
		System.arraycopy(data, 0, this.data, offset, data.length);
		return dataLength;
	}

	void setData(final byte[] bytes) {
		data = Arrays.copyOf(bytes, bytes.length);
	}

	void replaceWith(final Node node) {
		node.delete();
		delete();
		node.name = name;
		parent.children.put(node.name, node);
	}

	String getName() {
		return name;
	}

	Node getParent() {
		return parent;
	}

	LinkedHashMap<String, Node> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return name;
	}

}