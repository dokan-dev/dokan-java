package com.dokany.java.examples;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;

import com.dokany.java.FileHandle;
import com.dokany.java.FileSystem;
import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.structure.FileInfo;

public class ExampleFS implements FileSystem<Node> {

	private final Node root;

	public ExampleFS() throws UnsupportedEncodingException, IOException {
		root = new Node();

		root.findOrCreateNew("1.TXT").setData(new byte[] { 'H', 'E', 'L', 'L', 'O' });
		root.findOrCreateNew("2.txt");
		root.findOrCreateNew("testFolder\\3.TXT").setData("File within test folder".getBytes(StandardCharsets.UTF_8));
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
		final FileHandle<Node> handle = new NodeFileHandle(fileName, root.findExistant(fileName));
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
	public OpenFileResult<Node> createFile(final String fileName, final int securityContext, final int desiredAccess, final int fileAttributes, final int shareAccess, final int createDisposition, final int createOptions, final boolean isDirectory)
	        throws IOException {
		switch (CreationDisposition.fromInt(createDisposition)) {
		case CREATE_ALWAYS:
		case OPEN_ALWAYS: {
			root.findOrCreateNew(fileName);
			break;
		}
		case CREATE_NEW: {
			System.out.println("CREATE_NEW:");
			if (root.exists(fileName)) {
				throw new FileAlreadyExistsException(fileName);
			}
			root.findOrCreateNew(fileName).setData(new byte[0]);
			break;
		}
		case OPEN_EXISTING: {
			root.findExistant(fileName);
			break;
		}
		case TRUNCATE_EXISTING: {
			// TODO: this seems broken
			root.findExistant(fileName);
			if (!isDirectory) {
				root.findOrCreateNew(fileName).setData(new byte[0]);
			}
			break;
		}
		}

		return new OpenFileResult<Node>(createHandle(fileName));
	}

	public FileInfo getFileInformation(final NodeFileHandle handle) throws IOException {
		return NodeFileHandle.getFileInfo(root.findExistant(handle.fileName));
	}

	@Override
	public void findFiles(final FileHandle<Node> handle, final FileEmitter emitter) throws IOException {
		for (final Node child : handle.item.getChildren().values()) {
			emitter.emit(NodeFileHandle.getFileInfo(child));
		}
	}

	@Override
	public void deleteFile(final FileHandle<Node> handle) throws IOException {
		root.findExistant(handle.fileName).delete();
	}

	@Override
	public void deleteDirectory(final FileHandle<Node> handle) throws IOException {
		root.findExistant(handle.fileName).delete();
	}

	@Override
	public void moveFile(final FileHandle<Node> oldHandle, final FileHandle<Node> newHandle, final boolean replaceIfExisting) throws IOException {
		root.findExistant(newHandle.fileName).replaceWith(root.findExistant(oldHandle.fileName));

		// was originally findOrCreateNew, but this seemed wrong
		// root.findOrCreateNew(newHandle.fileName).replaceWith(root.findExistant(oldHandle.fileName));
	}

	@Override
	public int readFile(final FileHandle<Node> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException {
		return handle.item.read(fileOffset, data, dataLength);
	}

	@Override
	public int writeFile(final FileHandle<Node> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException {
		return handle.item.write(fileOffset, data, dataLength);
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
	public long getTotalBytesAvailable() {
		return 256L * 1024L * 1024L;
	}
}
