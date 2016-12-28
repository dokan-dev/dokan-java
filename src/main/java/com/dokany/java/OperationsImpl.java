package com.dokany.java;

import static com.dokany.java.constants.ErrorCodes.ERROR_FILE_NOT_FOUND;
import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;
import static com.dokany.java.constants.ErrorCodes.ERROR_WRITE_FAULT;
import static com.dokany.java.constants.ErrorCodes.OBJECT_NAME_COLLISION;
import static com.dokany.java.constants.ErrorCodes.SUCCESS;
import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_DIRECTORY;
import static com.dokany.java.constants.FileAttribute.FILE_ATTRIBUTE_NORMAL;
import static com.dokany.java.constants.NtStatus.FileInvalid;
import static com.dokany.java.constants.NtStatus.Success;
import static com.dokany.java.constants.NtStatus.Unsuccessful;
import static com.dokany.java.constants.WinError.ERROR_ALREADY_EXISTS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.dokany.java.FileSystem.FileEmitter;
import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.ErrorCodes;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.FileSystemFeatures;
import com.dokany.java.structure.ByHandleFileInformation;
import com.dokany.java.structure.FileInfo;
import com.dokany.java.structure.FileInfoRaw;
import com.dokany.java.structure.FileTime;
import com.dokany.java.structure.Win32FindData;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * Implementation of {@link com.dokany.java.Operations} which connects to {@link com.dokany.java.FileSystem}.
 *
 * @param <TNode>
 */
final class OperationsImpl<TNode> extends Operations {
	final FileSystem<TNode> fs;

	private final static Logger logger = LoggerFactory.getLogger(OperationsImpl.class);

	OperationsImpl(final FileSystem<TNode> fs) {
		this.fs = fs;

		ZwCreateFile = new ZwCreateFile();
		Mounted = new Mounted();
		CloseFile = new CloseFile();
		Cleanup = new Cleanup();
		ReadFile = new ReadFile();
		WriteFile = new WriteFile();
		FlushFileBuffers = new FlushFileBuffers();
		GetFileInformation = new GetFileInformation();
		FindFiles = new FindFiles();
		FindFilesWithPattern = new FindFilesWithPattern();
		SetFileAttributes = new SetFileAttributes();
		SetFileTime = new SetFileTime();
		DeleteFile = new DeleteFile();
		DeleteDirectory = new DeleteDirectory();
		MoveFile = new MoveFile();
		SetEndOfFile = new SetEndOfFile();
		SetAllocationSize = new SetAllocationSize();
		LockFile = new LockFile();
		UnlockFile = new UnlockFile();
		GetDiskFreeSpace = new GetDiskFreeSpace();
		GetVolumeInformation = new GetVolumeInformation();
		Mounted = new Mounted();
		Unmounted = new Unmounted();
		GetFileSecurity = new GetFileSecurity();
		SetFileSecurity = new SetFileSecurity();
		FindStreams = new FindStreams();
	}

	private class ZwCreateFile implements Operations.ZwCreateFileDelegate {
		@Override
		public long callback(
		        final WString rawFileName,
		        final IntByReference securityContext,
		        final int rawDesiredAccess,
		        final int rawFileAttributes,
		        final int rawShareAccess,
		        final int rawCreateDisposition,
		        final int rawCreateOptions,
		        final FileInfoRaw dokanFileInfo) {

			final CreationDisposition disposition = CreationDisposition.fromInt(rawCreateDisposition);
			System.out.println("Just entered ZwCreateFile with " + disposition.name);

			if (rawFileName.toString().equals("\\")) {
				// return ERROR_ALREADY_EXISTS.val;
				return ErrorCodes.SUCCESS.val;
			}
			if (isSkipFile(rawFileName)) {
				return FileInvalid.val;
			}

			long toReturn = -1;

			final String fileName = rawFileName.toString();
			final boolean isDirectory = dokanFileInfo._isDirectory != 0;

			TItem item;

			try {
				item = fs.findExisting(fileName, isDirectory);

				switch (disposition) {

				case CREATE_NEW: {
					if (Utils.isNotNull(item)) {
						toReturn = OBJECT_NAME_COLLISION.val;
					}
					break;
				}

				case CREATE_ALWAYS:
				case OPEN_ALWAYS: {
					break;
				}

				case OPEN_EXISTING: {
					if (Utils.isNull(item)) {
						toReturn = ERROR_FILE_NOT_FOUND.val;
					}
					break;
				}

				case TRUNCATE_EXISTING: {
					if (Utils.isNull(item)) {
						toReturn = ERROR_FILE_NOT_FOUND.val;
					}
					// TODO: need to truncate somehow
					// May not be correct
					if (!isDirectory) {
						fs.truncate(getHandle(rawFileName, dokanFileInfo._context));
					}
					break;
				}
				}
				if (toReturn == 0) {
					final FileAttribute attributes = FileAttribute.fromInt(rawFileAttributes);

					item = fs.createFile(fileName, disposition, rawCreateOptions, isDirectory, attributes);
					dokanFileInfo._context = fs.createHandle(fileName).getID();
					dokanFileInfo.write();

					toReturn = SUCCESS.val;
				}
			} catch (final Throwable t) {
				toReturn = exceptionToErrorCode(t);
			}

			return toReturn;
		}
	}

	private class Mounted implements Operations.MountedDelegate {
		@Override
		public long callback(final FileInfoRaw rawFileInfo) {

			try {
				fs.mounted();
				logger.info("Dokany File System mounted");
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class Unmounted implements Operations.UnmountedDelegate {
		@Override
		public long callback(final FileInfoRaw rawFileInfo) {

			try {
				fs.unmounted();
				logger.info("Dokany File System unmounted");
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class Cleanup implements Operations.CleanupDelegate {
		@Override
		public void callback(final WString fileName,
		        final FileInfoRaw rawFileInfo) {
			if (isSkipFile(fileName)) {
				return;
			}

			try {
				fs.cleanup(getHandle(fileName, rawFileInfo._context));
				logger.trace("Cleaned up: {}", fileName);
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class CloseFile implements Operations.CloseFileDelegate {
		@Override
		public void callback(final WString fileName, final FileInfoRaw rawFileInfo) {
			if (isSkipFile(fileName)) {
				return;
			}

			try {
				fs.close(getHandle(fileName, rawFileInfo._context));
				logger.trace("Closed file: {}", fileName);
			} catch (final FileNotFoundException e) {
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class GetVolumeInformation implements Operations.GetVolumeInformationDelegate {
		@Override
		public long callback(final Pointer volumeNameBuffer,
		        final int volumeNameSize,
		        final IntByReference rawVolumeSerialNumber,
		        final IntByReference rawMaximumComponentLength,
		        final IntByReference rawFileSystemFlags,
		        final Pointer rawFileSystemNameBuffer,
		        final int rawFileSystemNameSize,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("GetVolumeInformation");

			try {
				volumeNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getVolumeName(), volumeNameSize));
				logger.trace("Volume name: {}", fs.getVolumeName());

				rawFileSystemNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getFileSystemName(), rawFileSystemNameSize));
				logger.trace("File system name: {}", fs.getFileSystemName());

				rawVolumeSerialNumber.setValue(fs.getVolumeSerialNumber());
				logger.trace("Serial number: {}", fs.getVolumeSerialNumber());

				rawMaximumComponentLength.setValue(fs.getMaxComponentLength());
				logger.trace("Max component length: {}", fs.getMaxComponentLength());

				rawFileSystemFlags.setValue(fs.getFileSystemFeatures());
				logger.trace("File system features: {}", FileSystemFeatures.fromInt(fs.getFileSystemFeatures()));

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class GetDiskFreeSpace implements Operations.GetDiskFreeSpaceDelegate {
		@Override
		public long callback(final LongByReference rawFreeBytesAvailable,
		        final LongByReference rawTotalNumberOfBytes,
		        final LongByReference rawTotalNumberOfFreeBytes,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("GetDiskFreeSpace");

			rawFreeBytesAvailable.setValue(fs.getFreeBytesAvailable());
			rawTotalNumberOfBytes.setValue(fs.getTotalBytesAvailable());
			rawTotalNumberOfFreeBytes.setValue(fs.getFreeBytesAvailable());

			return 0;
		}
	}

	private class FindFiles implements Operations.FindFilesDelegate {
		@Override
		public long callback(final WString fileName,
		        final Operations.FillWin32FindData rawFillFindData,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("FindFiles");

			try {
				fs.findFiles(getHandle(fileName, rawFileInfo._context), file -> rawFillFindData.callback(file.toWin32FindData(), rawFileInfo));
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private void findFilesWithPattern(final FileHandle<TNode> handle, final PathMatcher pathMatcher, final FileEmitter emitter) throws IOException {
		fs.findFiles(handle, info -> {
			final Path path = Paths.get(info.fileName);
			if (pathMatcher.matches(path)) {
				emitter.emit(info);
			}
		});
	}

	private class FindFilesWithPattern implements Operations.FindFilesWithPatternDelegate {
		@Override
		public long callback(final WString fileName,
		        final WString searchPattern,
		        final Operations.FillWin32FindData rawFillFindData,
		        final FileInfoRaw rawFileInfo) {

			try {
				final FileHandle<TNode> handle = getHandle(fileName, rawFileInfo._context);
				final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(GLOB + searchPattern.toString());
				findFilesWithPattern(handle, matcher, info -> {
					rawFillFindData.callback(info.toWin32FindData(), rawFileInfo);
				});

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class ReadFile implements Operations.ReadFileDelegate {
		@Override
		public long callback(final WString fileName,
		        final Pointer buffer,
		        final int bufferLength,
		        final IntByReference readLength,
		        final long offset,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("ReadFile: {}", fileName);

			try {
				final byte[] data = new byte[bufferLength];
				final int read = fs.read(getHandle(fileName, rawFileInfo._context), offset, data, bufferLength);
				buffer.write(0, data, 0, read);
				readLength.setValue(read);
				logger.trace("Read this number of bytes: {}", read);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_READ_FAULT.val);
			}
		}
	}

	private class WriteFile implements Operations.WriteFileDelegate {
		@Override
		public long callback(final WString fileName,
		        final Pointer buffer,
		        final int numberOfBytesToWrite,
		        final IntByReference numberOfBytesWritten,
		        final long offset,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("WriteFile: {}", fileName);
			try {
				final byte[] data = new byte[numberOfBytesToWrite];
				buffer.read(0L, data, 0, numberOfBytesToWrite);
				final int written = fs.write(getHandle(fileName, rawFileInfo._context), offset, data, numberOfBytesToWrite);
				numberOfBytesWritten.setValue(written);
				logger.trace("Wrote this number of bytes: {}", written);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class FlushFileBuffers implements Operations.FlushFileBuffersDelegate {
		@Override
		public long callback(final WString fileName,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("FlushFileBuffers");
			try {
				fs.flushFileBuffers(getHandle(fileName, rawFileInfo._context));
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class GetFileInformation implements Operations.GetFileInformationDelegate {
		@Override
		public long callback(final WString fileName,
		        final ByHandleFileInfo handleFileInfo,
		        final FileInfoRaw rawFileInfo) {
			logger.trace("GetFileInformation: {}", fileName);

			if (isSkipFile(fileName)) {
				return FileInvalid.val;
			}

			try {
				handleFileInfo.copyTo(getFileInfo(fileName, handleFileInfo, rawFileInfo));
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileAttributes implements Operations.SetFileAttributesDelegate {
		@Override
		public long callback(final WString fileName,
		        final int attributes,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("SetFileAttributes as {} for {}", attributes, fileName);

			try {
				fs.setAttributes(getHandle(fileName, rawFileInfo._context), attributes);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileTime implements Operations.SetFileTimeDelegate {
		@Override
		public long callback(final WString fileName,
		        final FileTime creationTime,
		        final FileTime lastAccessTime,
		        final FileTime lastWriteTime,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("SetFileTime as creationTime = {}; lastAccessTime = {}; lastWriteTime = {}", creationTime, lastAccessTime, lastWriteTime);

			try {
				fs.setTime(getHandle(fileName, rawFileInfo._context), creationTime.getDate(), lastAccessTime.getDate(), lastWriteTime.getDate());
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class FindStreams implements Operations.FindStreamsDelegate {
		@Override
		public long callback(final WString fileName,
		        final Operations.FillWin32FindStreamData fill,
		        final FileInfoRaw rawFileInfo) {

			logger.trace("FindStreams for {}", fileName);

			try {
				fs.findStreams(getHandle(fileName, rawFileInfo._context), stream -> fill.callback(stream.toStruct(), rawFileInfo));

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class GetFileSecurity implements Operations.GetFileSecurityDelegate {
		@Override
		public long callback(final WString fileName,
		        final int rawRequestedInformation,
		        final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        final IntByReference rawSecurityDescriptorLengthNeeded,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("GetFileSecurity");

			try {
				final byte[] out = new byte[rawSecurityDescriptorLength];
				final int expectedLength = fs.getSecurity(getHandle(fileName, rawFileInfo._context), rawRequestedInformation, out);
				rawSecurityDescriptor.write(0L, out, 0, rawSecurityDescriptorLength);
				rawSecurityDescriptorLengthNeeded.setValue(expectedLength);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class SetFileSecurity implements Operations.SetFileSecurityDelegate {
		@Override
		public long callback(final WString fileName,
		        final int rawSecurityInformation,
		        final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("SetFileSecurity");

			try {
				final byte[] data = new byte[rawSecurityDescriptorLength];
				rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
				fs.setSecurity(getHandle(fileName, rawFileInfo._context), rawSecurityInformation, data);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteFile implements Operations.DeleteFileDelegate {
		@Override
		public long callback(final WString fileName,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("DeleteFile");

			try {
				fs.deleteFile(getHandle(fileName, rawFileInfo._context));

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteDirectory implements Operations.DeleteDirectoryDelegate {
		@Override
		public long callback(final WString fileName,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("DeleteDirectory");

			try {
				fs.deleteDirectory(getHandle(fileName, rawFileInfo._context));

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class MoveFile implements Operations.MoveFileDelegate {
		@Override
		public long callback(final WString oldFileName,
		        final WString newFileName,
		        final boolean replaceIfExisting,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("MoveFile");

			try {
				fs.move(getHandle(oldFileName, rawFileInfo._context), getHandle(newFileName, rawFileInfo._context), replaceIfExisting);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class SetEndOfFile implements Operations.SetEndOfFileDelegate {
		@Override
		public long callback(final WString fileName, final long byteOffset, final FileInfoRaw rawFileInfo) {
			logger.debug("SetEndOfFile");
			try {
				fs.setEndOfFile(getHandle(fileName, rawFileInfo._context), byteOffset);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class SetAllocationSize implements Operations.SetAllocationSizeDelegate {
		@Override
		public long callback(final WString fileName,
		        final long length,
		        final FileInfoRaw rawFileInfo) {
			logger.debug("SetAllocationSize");
			try {
				fs.setAllocationSize(getHandle(fileName, rawFileInfo._context), length);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class LockFile implements Operations.LockFileDelegate {
		@Override
		public long callback(final WString fileName,
		        final long byteOffset,
		        final long length,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("LockFile");

			try {
				fs.lock(getHandle(fileName, rawFileInfo._context), byteOffset, length);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class UnlockFile implements Operations.UnlockFileDelegate {
		@Override
		public long callback(final WString fileName,
		        final long byteOffset,
		        final long length,
		        final FileInfoRaw rawFileInfo) {

			logger.debug("UnlockFile");

			try {
				fs.unlock(getHandle(fileName, rawFileInfo._context), byteOffset, length);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	static class Win32FindStreamData extends Structure implements Operations.Win32FindStreamData {
		public long length;
		public char[] cFileName = new char[Win32FindData.MAX_PATH + 36];

		@Override
		public void length(final long val) {
			length = val;
		}

		@Override
		public char[] cFileName() {
			return cFileName;
		}

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("length", "cFileName");
		}
	}

	private static long exceptionToErrorCode(final Throwable t) {
		return exceptionToErrorCode(t, Unsuccessful.val);
	}

	private static long exceptionToErrorCode(final Throwable t, final long defaultCode) {
		if (t instanceof DokanyException) {
			return ((DokanyException) t).val;
		}
		if (t instanceof FileNotFoundException) {
			return ERROR_FILE_NOT_FOUND.val;
		}
		if (t instanceof FileAlreadyExistsException) {
			return ERROR_ALREADY_EXISTS.val;
		}

		logger.warn(t.getMessage(), t);
		;
		return defaultCode;
	}

	private boolean isSkipFile(final WString fileName) {
		boolean toReturn = false;

		if (// fileName.toString().equals(ROOT_PATH) ||
		fileName.toString().endsWith("desktop.ini")
		        || fileName.toString().endsWith("folder.jpg")) {
			// logger.debug("Skipping file: " + fileName);
			toReturn = true;
		}
		return toReturn;
	}

	private FileHandle<TNode> getHandle(final WString path, final long id) throws IOException {
		return fs.getHandle(Paths.get(path.toString()), id);
	}

	private ByHandleFileInfo getFileInfo(final WString fileNameW,
	        final ByHandleFileInfo handleFileInfo,
	        final FileInfoRaw rawFileInfo) throws IOException {

		final String fileName = fileNameW.toString();
		logger.debug("fileName: {}", fileName);

		final FileHandle<TNode> handle = getHandle(fileNameW, rawFileInfo._context);
		final ByHandleFileInfo fileInfo;
		logger.debug("Paths.get(fileName): {}", Paths.get(fileName));
		logger.debug("fs.getRootPath: {}", fs.getRootPath());

		if (Paths.get(fileName).equals(fs.getRootPath())) {
			fileInfo = getRootInfo();
		} else {
			fileInfo = fs.getFileInfo(handle);

		}
		return fileInfo;
	}

	/**
	 * Get root file info.
	 *
	 * @return ByHandleFileInfo for root
	 */
	private ByHandleFileInfo getRootInfo() {
		final FileInfoBuilder builder = new FileInfoBuilder(fs.getRootPath());
		builder.attributes(FILE_ATTRIBUTE_NORMAL, FILE_ATTRIBUTE_DIRECTORY);
		builder.creationTime(fs.getRootCreateDate());
		builder.lastWriteTime(fs.getRootCreateDate());

		builder.numberOfLinks(1);
		builder.volumeSerialNumber(fs.getVolumeSerialNumber());
		return builder.build();
	}

	/**
	 * @see {@link com.dokany.java.FileSystem#isDefaultLog()}
	 * @return true if default file system log is enabled.
	 */
	private boolean isDefaultLog() {
		return fs.isDefaultLog();
	}

	/**
	 * Path matcher glob
	 */
	private final static String GLOB = "glob:";
}
