package com.dokany.java;

import static com.dokany.java.constants.FileSystemFeatures.CasePreservedNames;

import java.io.IOException;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Date;

import com.dokany.java.structure.FileInfo;
import com.sun.jna.WString;

//@SuppressWarnings("WeakerAccess")
public interface FileSystem<TItem> {

	FileHandleStore fileHandles = new FileHandleStore();

	public default boolean isDefaultLog() {
		return isDebug();
	}

	public default boolean isDebug() {
		return false;
	}

	public default boolean isDebugStderrOutput() {
		return false;
	}

	public default int getMaxComponentLength() {
		return 256;
	}

	public long getTotalBytesAvailable();

	public long getUsedBytes();

	public default long getFreeBytesAvailable() {
		return getTotalBytesAvailable() - getUsedBytes();
	}

	public default int getAllocationUnitSize() {
		return 4096;
	}

	public default int getSectorSize() {
		return 4096;
	}

	public default int getTimeout() {
		return 10000;
	}

	public default String getVolumeName() {
		return "VOLUME";
	}

	public default String getFileSystemName() {
		return "DOKAN";
	}

	public default int getSerialNumber() {
		// return 0x12345678;
		return 0x00000000;
	}

	public default void cleanup(final FileHandle<TItem> handle) {
		if (isDefaultLog()) {
			System.out.println("cleanup: " + handle.fileName);
		}
	}

	public default void closeFile(final FileHandle<TItem> handle) {
		if (isDefaultLog()) {
			System.out.println("closeFile: " + handle);
		}
	}

	public default void mounted() {
		if (isDefaultLog()) {
			System.out.println("Dokany File System mounted");
		}
	}

	public default void unmounted() {
		if (isDefaultLog()) {
			System.out.println("Dokany File System unmounted");
		}
	}

	public default int getFileSecurity(final FileHandle<TItem> handle, final int kind, final byte[] out) {
		return 0;
	}

	/**
	 * Default is FileSystemFeatures.CasePreservedNames
	 *
	 * @return
	 */
	public default int getFileSystemFeatures() {
		return CasePreservedNames.val;
	}

	public default FileInfo getFileInformation(final FileHandle<TItem> handle) throws IOException {
		return new FileInfo.Builder(handle.fileName).buildFileInfo();
	}

	public default OpenFileResult<TItem> createFile(final String fileName, final int securityContext, final int rawDesiredAccess, final int rawFileAttributes, final int rawShareAccess, final int rawCreateDisposition,
	        final int rawCreateOptions,
	        final boolean isDirectory) throws IOException {
		if (isDefaultLog()) {
			System.out.println("DokanOperations.createFile: fileName = [" + fileName + "], securityContext = [" + securityContext + "], rawDesiredAccess = [" + rawDesiredAccess + "], rawFileAttributes = [" + rawFileAttributes
			        + "], rawShareAccess = [" + rawShareAccess + "], rawCreateDisposition = [" + rawCreateDisposition + "], rawCreateOptions = [" + rawCreateOptions + "], isDirectory = [" + isDirectory + "]");
		}
		return new OpenFileResult<TItem>(createHandle(fileName));
	}

	public void unlockFile(final FileHandle<TItem> handle, final long byteOffset, final long length);

	public void lockFile(final FileHandle<TItem> handle, final long byteOffset, final long length);

	public void setAllocationSize(final FileHandle<TItem> handle, final long length);

	public abstract void setEndOfFile(final FileHandle<TItem> handle, final long byteOffset);

	public void moveFile(final FileHandle<TItem> oldHandle, final FileHandle<TItem> newHandle, final boolean replaceIfExisting) throws IOException;

	public void deleteFile(final FileHandle<TItem> handle) throws IOException;

	public void deleteDirectory(final FileHandle<TItem> handle) throws IOException;

	public int readFile(final FileHandle<TItem> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException;

	public int writeFile(final FileHandle<TItem> handle, final long fileOffset, final byte[] data, final int dataLength) throws IOException;

	public void findFiles(final FileHandle<TItem> handle, final FileEmitter emitter) throws IOException;

	public default void findFiles(final FileHandle<TItem> handle, final PathMatcher pathMatcher, final FileEmitter emitter) throws IOException {
		findFiles(handle, file -> {
			if (pathMatcher.matches(Paths.get(file.fileName))) {
				emitter.emit(file);
			}
		});
	}

	public abstract void flushFileBuffers(final FileHandle<TItem> handle);

	public void setFileAttributes(final FileHandle<TItem> handle, final int attributes);

	public void setFileTime(final FileHandle<TItem> handle, final Date creation, final Date access, final Date modification);

	public void findStreams(final FileHandle<TItem> handle, final StreamEmitter emitter);

	public void setFileSecurity(final FileHandle<TItem> handleHandle, final int kind, final byte[] data);

	FileHandle<TItem> createHandle(String fileName) throws IOException;

	FileHandle<TItem> getFileHandle(final String fileName, final long id) throws IOException;

	default FileHandle<TItem> getFileHandle(final WString fileName, final long id) throws IOException {
		return getFileHandle(fileName.toString(), id);
	}

	public interface FileEmitter {
		void emit(FileInfo info);
	}

	public interface StreamEmitter {
		void emit(StreamInfo info);
	}

	public class OpenFileResult<TItem> {
		public FileHandle<TItem> handle;
		public boolean exists;

		public OpenFileResult(final FileHandle<TItem> handle) {
			this.handle = handle;
			exists = handle != null;
		}

		@Override
		public String toString() {
			return "OpenFileResult{" +
			        "exists=" + exists +
			        ", handle=" + handle +
			        '}';
		}
	}
}