package com.dokany.java.examples.memoryfs;

import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;
import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_NORMAL;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyException;
import com.dokany.java.Utils;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.ByHandleFileInfo;

final class Node {
	private Path path;
	private Node parent;
	private final LinkedHashMap<Path, Node> children = new LinkedHashMap<>();
	private byte[] data;
	private long handleID;
	private ByHandleFileInfo info = new ByHandleFileInfo();

	private final static Logger logger = LoggerFactory.getLogger(Node.class);

	// constructor
	Node(final Path pathForNewNode, final Node parentForNewNode, final ByHandleFileInfo infoForNewNode) {
		logger.trace("new Node({}, {}, {})", pathForNewNode, parentForNewNode, infoForNewNode);

		final Set<Path> parts = Utils.getPathParts(pathForNewNode);
		logger.trace("numParts: {}", parts.size());
		if (parts.size() > 1) {
			final Node createdNode = MemoryFS.find(MemoryFS.getRootNode(), pathForNewNode, true, FileAttribute.DIRECTORY);
			createdNode.copyTo(this);
		} else {
			setPath(pathForNewNode);

			setFamily(parentForNewNode);

			// No data initially so set to null (which means this is a directory)
			data = null;

			infoForNewNode.copyTo(info);
			logger.debug("successfully set info for path {} : {}", path, info);
		}
	}

	private void copyTo(final Node nodeToReceive) {
		nodeToReceive.path = path;
		nodeToReceive.parent = parent;
		nodeToReceive.children.putAll(children);
		nodeToReceive.data = data;
		nodeToReceive.handleID = handleID;
		nodeToReceive.info = info;
	}

	/**
	 * Will return
	 *
	 * @param childName
	 * @param create
	 * @return null if child does not currently exist
	 * @throws IOException
	 */
	final Node getChild(final Path childNodePath) {
		logger.trace("getChild({})", childNodePath);

		Node toReturn = null;

		if (Utils.isNull(childNodePath)) {
			throw new IllegalStateException("getChild cannot be called on null childNode");
		}

		logger.trace("children: {}", children);

		if (Utils.isNotNull(childNodePath)) {
			toReturn = children.get(childNodePath);
		}

		logger.trace("toReturn: {}", toReturn);
		return toReturn;
	}

	final Node createChild(final Path childNodePath, final Node parentNode, final FileAttribute attributes) {
		logger.info("createChild({}, {}, {})", childNodePath, parentNode, attributes);
		// TODO: What about new folder attributes along the path?

		final ByHandleFileInfo.Builder builder = new ByHandleFileInfo.Builder(childNodePath);
		builder.attributes(attributes);

		// Cannot set size yet
		// Cannot set index/id yet
		// Canot set volume serial number because there is no reference to MemoryFS

		final Node child = new Node(childNodePath, parentNode, builder.build());
		setAttributes(attributes);
		logger.debug("Created child {} in parentNode {}", child, parentNode);
		logger.trace("Child's info: {}", child.info);

		return child;
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

	final int write(final long loffset, final byte[] dataToWrite, final int dataLength) {
		final int offset = (int) loffset;
		if (data == null) {
			data = new byte[0];
		}
		setData(data, Math.max(data.length, offset + dataLength));
		System.arraycopy(dataToWrite, 0, data, offset, dataToWrite.length);
		return dataLength;
	}

	private final void setData(final byte[] bytes, final int length) {
		if (length == 0) {
			data = new byte[0];
		} else {
			data = Arrays.copyOf(bytes, length);
		}
		logger.debug("info inside set Data: {}", info);
		info.setSize(getSize());
	}

	final void setData(final byte[] bytes) {
		int length = 0;
		if (Utils.isNotNull(bytes)) {
			length = bytes.length;
		}
		setData(bytes, length);
	}

	final void setAttributes(final FileAttribute attributes) {
		int toSet = FILE_ATTRIBUTE_NORMAL;
		// Will be null if coming from findExisting method
		if (Utils.isNotNull(attributes)) {
			toSet = attributes.val;
		}

		info.dwFileAttributes = toSet;
	}

	final int getAttributes() {
		// Will be null if coming from findExisting method
		return info.dwFileAttributes;
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

	final void setHandleID(final long idToSet) {
		handleID = idToSet;
		logger.trace("setHandleID: {}", handleID);
	}

	final ByHandleFileInfo getInfo() {
		return info;
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
		long size = 0L;
		if (Utils.isNotNull(data)) {
			size = data.length;
		}
		return size;
	}

	final LinkedHashMap<Path, Node> getChildren() {
		return children;
	}

	@Override
	final public String toString() {
		return "path: " + path + "   info: " + info;
	}

	private void setPath(final Path initPath) {
		Path pathToSave = initPath;
		if (Utils.isNotNull(pathToSave)) {
			pathToSave = pathToSave.getFileName();

			// getFileName was null so set back to initPath which is not null
			if (Utils.isNull(pathToSave) && Utils.isNotNull(initPath)) {
				pathToSave = initPath;
			}
		}

		// Path should never be null at this point
		if (Utils.isNull(pathToSave)) {
			throw new IllegalStateException("Path or its name (via getFileName()) cannot be null");
		}
		path = pathToSave;
	}

	private void setFamily(final Node parentForNewNode) {
		parent = parentForNewNode;
		if (Utils.isNotNull(parent)) {
			parent.children.put(path, this);
		}
	}

}