package com.dokany.java.examples.memoryfs;

import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyException;
import com.dokany.java.Utils;
import com.dokany.java.constants.FileAttribute;

final class Node {
	private Path path;
	private final Node parent;
	private final LinkedHashMap<Path, Node> children = new LinkedHashMap<>();
	private byte[] data = null;
	private long handleID;

	// TODO: how are these read?
	private int attributes;

	public Date date = new Date();
	private final static Logger logger = LoggerFactory.getLogger(Node.class);

	// constructor
	Node(final Path path, final Node parent) {
		this.path = path;
		if (Utils.isNotNull(path)) {
			this.path = path.getFileName();
			if (Utils.isNull(this.path)) {
				this.path = path;
			}
		}

		this.parent = parent;
		if (Utils.isNotNull(this.parent)) {
			this.parent.children.put(this.path, this);
		}
	}

	final Node findExisting(final Path path) throws IOException {
		return find(path, false, null);
	}

	final Node createFile(final Path filePath, final long options, final FileAttribute... attributes)
	        throws IOException {
		// TODO: Add other parameters
		return find(filePath, true, attributes);
	}

	final Node createDirectory(final Path directoryPath, final long options, final FileAttribute... attributes)
	        throws IOException {
		// TODO: Add other parameters
		return find(directoryPath, true, attributes);
	}

	// TODO: Add other parameters from createFile
	private final Node find(final Path path, final boolean create, final FileAttribute... attributes) throws FileNotFoundException {
		logger.debug("find({}, {}, {})", path, create, attributes);

		Node parentNode = this;
		Node currentNode = this;

		final Iterator<Path> i = Utils.getPathParts(path).iterator();
		while (i.hasNext()) {

			final Path childNodePath = i.next();
			// TODO: What about new folder attributes along the path?
			currentNode = parentNode.getChild(childNodePath);

			if (Utils.isNull(currentNode)) {
				// Create child if it does not exist
				if (create) {
					currentNode = createChild(childNodePath, parentNode);
				}

				// TODO: get rid of if not needed
				// else if (!create) {
				// throw new FileNotFoundException(toString() + Utils.FORWARDSLASH + currentPathPart);
				// }
			}

			if (Utils.isNotNull(currentNode)) {
				parentNode = currentNode;
			}
		}
		logger.debug("returning: {}", currentNode);
		return currentNode;
	}

	/**
	 * Will return
	 *
	 * @param childName
	 * @param create
	 * @return null if child does not currently exist
	 * @throws IOException
	 */
	private final Node getChild(final Path childNode) {
		logger.debug("getChild({})", childNode);

		Node toReturn = null;

		if (Utils.isNull(childNode)) {
			throw new IllegalStateException("getChild cannot be called on null childNode");
		}

		if ((toReturn == null) && children.containsKey(childNode)) {
			toReturn = children.get(childNode);
			logger.debug("toReturn: {}", toReturn);
		}

		return toReturn;
	}

	private final Node createChild(final Path childNode, final Node parentNode) {
		final Node child = new Node(childNode, parentNode);
		logger.debug("Created child {} in parentNode {}", child, parentNode);
		return child;
		// TODO: should this set attributes or do elsewhere?
	};

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
		setData(this.data, Math.max(this.data.length, offset + dataLength));
		System.arraycopy(data, 0, this.data, offset, data.length);
		return dataLength;
	}

	private final void setData(final byte[] bytes, final int length) {
		if (length == 0) {
			data = new byte[0];
		} else {
			data = Arrays.copyOf(bytes, length);
		}
	}

	final void setData(final byte[] bytes) {
		int length = 0;
		if (Utils.isNotNull(bytes)) {
			length = bytes.length;
		}
		setData(bytes, length);
	}

	final void setAttributes(final FileAttribute attributes) {
		this.attributes = attributes.val;
	}

	final void replaceWith(final Node node) {
		// TODO: get root path from elsewhere
		if (node.path.equals("/")) {
			throw new IllegalArgumentException("Cannot replace root node");
		}
		node.delete();
		delete();
		node.path = path;
		parent.children.put(node.path, node);
	}

	final void delete() {
		if (parent != null) {
			parent.children.remove(path);
		}
	}

	final long getHandleID() {
		return handleID;
	}

	final void setHandleID(final long handleID) {
		this.handleID = handleID;
		logger.debug("setHandleID: {}", handleID);
	}

	final byte[] getData() {
		return data;
	}

	final Path getPath() {
		return path;
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

	final LinkedHashMap<Path, Node> getChildren() {
		return children;
	}

	@Override
	final public String toString() {
		return path.toString();
	}

}