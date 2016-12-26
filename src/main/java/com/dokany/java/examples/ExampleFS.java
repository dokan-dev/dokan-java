package com.dokany.java.examples;

import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_NORMAL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.dokany.java.FileHandle;
import com.dokany.java.FileSystem;
import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.FileInfo;

public class ExampleFS implements FileSystem<Node> {

	private final Node root;

	public ExampleFS() throws IOException {
		root = new Node();

		final Node item1 = root.createFile("1.TXT", CreationDisposition.CREATE_ALWAYS, 0, false, FILE_ATTRIBUTE_NORMAL);
		item1.setData(new byte[] { 'H', 'E', 'L', 'L', 'O' });

		final Node item2 = root.createFile("2.txt", CreationDisposition.CREATE_ALWAYS, 0, false, FILE_ATTRIBUTE_NORMAL);

		final Node item3 = root.createFile("testFolder\\3.TXT", CreationDisposition.CREATE_ALWAYS, 0, false, FILE_ATTRIBUTE_NORMAL);
		item3.setData("File within test folder".getBytes(StandardCharsets.UTF_8));
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
		return true;
	}

	@Override
	public boolean isDebugStderrOutput() {
		return true;
	}

	@Override
	public FileHandle<Node> createHandle(final String fileName) throws IOException {
		return createHandle(fileName, root.findExisting(fileName));
	}

	private FileHandle<Node> createHandle(final String fileName, final Node node) throws IOException {
		final FileHandle<Node> handle = new NodeFileHandle(fileName, node);
		fileHandles.allocateFileHandle(handle);
		return handle;
	}

	@Override
	public FileHandle<Node> getFileHandle(final String fileName, final long id) throws IOException {
		FileHandle<Node> handle = fileHandles.getFileHandle(fileName, id);
		if (handle == null) {
			handle = createHandle(fileName);
		}
		return handle;
	}

	@Override
	public FileInfo getFileInformation(final FileHandle<Node> handle) throws IOException {
		return NodeFileHandle.getFileInfo(root.findExisting(handle.getFileName()));
	}

	@Override
	public void findFiles(final FileHandle<Node> handle, final FileEmitter emitter) throws IOException {
		for (final Node child : handle.getItem().getChildren().values()) {
			emitter.emit(NodeFileHandle.getFileInfo(child));
		}
	}

	@Override
	public void deleteFile(final FileHandle<Node> handle) throws IOException {
		root.findExisting(handle.getFileName()).delete();
	}

	@Override
	public void deleteDirectory(final FileHandle<Node> handle) throws IOException {
		root.findExisting(handle.getFileName()).delete();
	}

	@Override
	public void moveFile(final FileHandle<Node> oldHandle, final FileHandle<Node> newHandle, final boolean replaceIfExisting) throws IOException {
		root.findExisting(newHandle.getFileName()).replaceWith(root.findExisting(oldHandle.getFileName()));

		// was originally findOrCreateNew, but this seemed wrong
		// root.findOrCreateNew(newHandle.fileName).replaceWith(root.findExistant(oldHandle.fileName));
	}

	@Override
	public int readFile(final FileHandle<Node> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException {
		return handle.getItem().read(fileOffset, data, dataLength);
	}

	@Override
	public int writeFile(final FileHandle<Node> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException {
		return handle.getItem().write(fileOffset, data, dataLength);
	}

	@Override
	public long getUsedBytes() {
		return 1024 * 1024;
	}

	@Override
	public void unlockFile(final FileHandle<Node> handle, final long byteOffset, final long length) {
		// TODO Auto-generated method stub
	}

	@Override
	public void lockFile(final FileHandle<Node> handle, final long byteOffset, final long length) {
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
	public void setFileAttributes(final FileHandle<Node> handle, final int attributes) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setFileTime(final FileHandle<Node> handle, final Date creation, final Date access, final Date modification) {
		// TODO Auto-generated method stub
	}

	@Override
	public void findStreams(final FileHandle<Node> handle, final StreamEmitter emitter) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setFileSecurity(final FileHandle<Node> handle, final int kind, final byte[] data) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanup(final FileHandle<Node> handle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeFile(final FileHandle<Node> handle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mounted() {
		// TODO Auto-generated method stub
	}

	@Override
	public void unmounted() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node createFile(final String fileName, final CreationDisposition disposition, final long options, final boolean isDirectory, final FileAttribute... attributes) throws IOException {
		return root.createFile(fileName, disposition, options, isDirectory, attributes);
	}

	@Override
	public Node findExisting(final String fileName, final boolean isDirectory) throws IOException {
		return root.findExisting(fileName);
	}
}
