package com.dokany.java.examples.memoryfs;

import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyException;
import com.dokany.java.Utils;
import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;

final class Node {
	private String name;
	private final Node parent;
	private final LinkedHashMap<String, Node> children = new LinkedHashMap<String, Node>();
	private byte[] data = null;
	public Date date = new Date();
	private final static Logger logger = LoggerFactory.getLogger(Mount.class);

	// constructor
	Node(final String name, final Node parent) {
		this.name = name;
		this.parent = parent;
		if (Utils.isNotNull(this.parent)) {
			this.parent.children.put(name, this);
		}
	}

	final Node findExisting(final String path) throws IOException {
		return find(path, false);
	}

	final Node createFile(final String fileName, final CreationDisposition disposition, final long options, final boolean isDirectory, final FileAttribute... attributes)
	        throws IOException {
		// TODO: Add other parameters
		return find(fileName, true);
	}

	// TODO: Add other parameters from createFile
	private final Node find(final String path, final boolean create) throws IOException {
		logger.debug("find({},{})", path, create);

		final String[] parts = Utils.getPathParts(path);
		Node cur = this;
		for (int i = 0; (cur != null) && (i < parts.length); i++) {
			// final boolean last = i == (parts.length - 1);
			logger.debug("parts[i]: {}", parts[i]);
			// TODO: always create??
			cur = cur.child(parts[i], true);
		}

		return cur;
	}

	private final Node child(final String childName, final boolean create) throws IOException {
		logger.debug("child({}, {})", childName, create);

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
		return new Node(childName, this);
	}

	final int read(final long loffset, final byte[] out, final int len) {
		final int offset = (int) loffset;
		if (data == null) {
			throw new DokanyException(ERROR_READ_FAULT);
		}
		final int readlen = Math.min(len, data.length - offset);
		System.arraycopy(data, offset, out, 0, readlen);
		return readlen;
	}

	final int write(final long loffset, final byte[] data, final int dataLength) {
		final int offset = (int) loffset;
		if (this.data == null) {
			this.data = new byte[0];
		}
		this.data = Arrays.copyOf(this.data, Math.max(this.data.length, offset + dataLength));
		System.arraycopy(data, 0, this.data, offset, data.length);
		return dataLength;
	}

	final void setData(final byte[] bytes) {
		data = Arrays.copyOf(bytes, bytes.length);
	}

	final void replaceWith(final Node node) {
		if (node.name.equals("Root")) {
			throw new IllegalArgumentException("Cannot replace root node");
		}
		node.delete();
		delete();
		node.name = name;
		parent.children.put(node.name, node);
	}

	final void delete() {
		if (parent != null) {
			parent.children.remove(name);
		}
	}

	final byte[] getData() {
		return data;
	}

	final String getName() {
		return name;
	}

	final Node getParent() {
		return parent;
	}

	final boolean isDirectory() {
		return data == null;
	}

	final long getSize() {
		final long size = 0L;
		if (data != null) {
			return data.length;
		}
		return size;
	}

	final LinkedHashMap<String, Node> getChildren() {
		return children;
	}

	@Override
	final public String toString() {
		return name;
	}

}