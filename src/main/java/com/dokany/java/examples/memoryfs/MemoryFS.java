package com.dokany.java.examples.memoryfs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.FileHandle;
import com.dokany.java.FileHandleStore;
import com.dokany.java.FileSystem;
import com.dokany.java.constants.ErrorCodes;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.ByHandleFileInfo;
import com.dokany.java.structure.FileTime;

public class MemoryFS implements FileSystem<Node> {

	private final Path rootPath = Paths.get("/");
	private Node root;
	private FileHandle<Node> rootHandle;
	private final static Logger logger = LoggerFactory.getLogger(MemoryFS.class);
	final FileHandleStore<Node> fileHandles = new FileHandleStore<Node>();

	public MemoryFS() throws IOException {
	}

	@Override
	public void mounted() {

		try {
			System.out.println("root: " + rootPath);
			root = new Node(rootPath, null);
			rootHandle = createHandle(rootPath, root);
			// root = null;

			final String item1Name = "1.txt";
			// final Node item1 = createFile(item1Name, 0, FILE_ATTRIBUTE_NORMAL);
			// item1.setData(new byte[] { 'H', 'E', 'L', 'L', 'O' });
			// createHandle(item1Name, item1);

			final String item2Name = "2.txt";
			// final Node item2 = createFile(item2Name, 0, FILE_ATTRIBUTE_NORMAL);
			// item2.setData(null);
			// createHandle(item2Name, item2);

			final String item3Name = "testFolder\\3.TXT";
			// final Node item3 = createFile(item3Name, 0, FILE_ATTRIBUTE_NORMAL);
			// item3.setData("File within test folder".getBytes(StandardCharsets.UTF_8));
			// createHandle(item3Name, item3);

			logger.debug("Root children: {}", rootHandle.getNode().getChildren().keySet().toString());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Default is VOLUME;
	 *
	 * @return
	 */
	@Override
	public String getVolumeName() {
		return "EXAMPLE_FS";
	}

	/**
	 * Default is DOKANY.
	 *
	 * @return
	 */
	@Override
	public String getFileSystemName() {
		return "Dokany ExampleFS";
	}

	@Override
	public long getTotalBytesAvailable() {
		return 256L * 1024L * 1024L;
	}

	@Override
	public boolean isDebug() {
		return false;
	}

	@Override
	public boolean isDebugStderrOutput() {
		return false;
	}

	@Override
	public FileHandle<Node> createHandle(final Path path) throws IOException {
		return createHandle(path, root.findExisting(path));
	}

	private FileHandle<Node> createHandle(final Path path, final Node node) throws IOException {
		logger.debug("createHandle({}, {})", path, node);
		final FileHandle<Node> handle = new NodeFileHandle(path, node);
		final long id = fileHandles.allocateHandle(handle);
		node.setHandleID(id);
		return handle;
	}

	@Override
	public FileHandle<Node> getHandle(final Node node) throws IOException {
		return getHandle(node.getPath(), node.getHandleID());
	}

	@Override
	public FileHandle<Node> getHandle(final Path nodePath, final long id) throws IOException {
		FileHandle<Node> handle;
		if (id == 0) {
			handle = rootHandle;
		} else {
			handle = fileHandles.getHandle(id);
		}
		if (handle == null) {
			handle = createHandle(nodePath);
		}
		return handle;
	}

	@Override
	public ByHandleFileInfo getFileInfo(final FileHandle<Node> handle) throws IOException {
		return NodeFileHandle.getFileInfo(root.findExisting(handle.getPath()));
	}

	@Override
	public void findFiles(final FileHandle<Node> handle, final FileEmitter emitter) throws IOException {
		for (final Node child : handle.getNode().getChildren().values()) {
			emitter.emit(NodeFileHandle.getFileInfo(child));
		}
	}

	@Override
	public void findStreams(final FileHandle<Node> handle, final StreamEmitter emitter) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteFile(final FileHandle<Node> handle) throws IOException {
		root.findExisting(handle.getPath()).delete();
	}

	@Override
	public void deleteDirectory(final FileHandle<Node> handle) throws IOException {
		root.findExisting(handle.getPath()).delete();
	}

	@Override
	public void move(final FileHandle<Node> oldHandle, final FileHandle<Node> newHandle, final boolean replaceIfExisting) throws IOException {
		root.findExisting(newHandle.getPath()).replaceWith(root.findExisting(oldHandle.getPath()));

		// was originally findOrCreateNew, but this seemed wrong
		// root.findOrCreateNew(newHandle.fileName).replaceWith(root.findExistant(oldHandle.fileName));
	}

	@Override
	public int read(final FileHandle<Node> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException {
		return handle.getNode().read(fileOffset, data, dataLength);
	}

	@Override
	public int write(final FileHandle<Node> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException {
		return handle.getNode().write(fileOffset, data, dataLength);
	}

	@Override
	public long getUsedBytes() {
		return 1024 * 1024;
	}

	@Override
	public void unlock(final FileHandle<Node> handle, final long byteOffset, final long length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void lock(final FileHandle<Node> handle, final long byteOffset, final long length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setAllocationSize(final FileHandle<Node> handle, final long length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setEndOfFile(final FileHandle<Node> handle, final long byteOffset) {
		// TODO Auto-generated method stub
	}

	@Override
	public void flushFileBuffers(final FileHandle<Node> handle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setAttributes(final FileHandle<Node> handle, final int attributes) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setTime(final FileHandle<Node> handle, final Date creation, final Date access, final Date modification) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setSecurity(final FileHandle<Node> handle, final int kind, final byte[] data) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanup(final FileHandle<Node> handle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void close(final FileHandle<Node> handle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void unmounted() {
		// TODO Auto-generated method stub
	}

	@Override
	public Node createFile(final Path path, final long options, final FileAttribute... attributes)
	        throws IOException {
		return root.createFile(path, options, attributes);
	}

	@Override
	public Node createDirectory(final Path path, final long options, final FileAttribute... attributes)
	        throws IOException {
		return root.createFile(path, options, attributes);
	}

	@Override
	public Node findExisting(final Path path, final boolean isDirectory) throws IOException {
		return root.findExisting(path);
	}

	@Override
	public long truncate(final FileHandle<Node> handle) throws IOException {
		final Node item = handle.getNode();
		final Node parent = item.getParent();

		item.setData(new byte[0]);

		final ByHandleFileInfo fileInfo = handle.getFileInfo();
		final ByHandleFileInfo parentFileInfo = createHandle(parent.getPath(), parent).getFileInfo();

		final FileTime.VAL now = new FileTime.VAL();

		fileInfo.ftLastAccessTime = now;
		parentFileInfo.ftLastAccessTime = now;
		fileInfo.ftLastWriteTime = now;
		parentFileInfo.ftLastWriteTime = now;

		// handle.getFileInfo().fileIndex = nextFileHandleId();
		return ErrorCodes.ERROR_ALREADY_EXISTS.val;
	}

	@Override
	public final Path getRootPath() {
		return rootPath;
	}
}
