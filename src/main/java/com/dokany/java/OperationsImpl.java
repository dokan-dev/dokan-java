package com.dokany.java;

import static com.dokany.java.constants.ErrorCodes.ERROR_ALREADY_EXISTS;
import static com.dokany.java.constants.ErrorCodes.ERROR_FILE_NOT_FOUND;
import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;
import static com.dokany.java.constants.ErrorCodes.ERROR_WRITE_FAULT;
import static com.dokany.java.constants.ErrorCodes.OBJECT_NAME_COLLISION;
import static com.dokany.java.constants.ErrorCodes.SUCCESS;
import static com.dokany.java.constants.NtStatus.FileInvalid;
import static com.dokany.java.constants.NtStatus.Success;
import static com.dokany.java.constants.NtStatus.Unsuccessful;
import static com.dokany.java.constants.WinError.ERROR_NOT_SUPPORTED;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.FileSystem.FileEmitter;
import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.FileSystemFeatures;
import com.dokany.java.structure.ByHandleFileInfo;
import com.dokany.java.structure.DokanFileInfo;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * Implementation of {@link com.dokany.java.Operations} which connects to {@link com.dokany.java.FileSystem}.
 *
 * @param <TNode>
 */
final class OperationsImpl<TNode> extends Operations {
	final FileSystem<TNode> fs;
	public static final int MAX_PATH = 260;
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
		        final DokanFileInfo dokanFileInfo) {

			final long rawFileAttributesLong = rawFileAttributes;
			final long rawCreateOptionsLong = rawCreateOptions;
			final long rawCreateDispositionLong = rawCreateDisposition;
			final IntByReference fileAttributesAndFlags = new IntByReference();
			final IntByReference creationDisposition = new IntByReference();
			NativeMethods.DokanMapKernelToUserCreateFileFlags(
			        rawFileAttributesLong,
			        rawCreateOptionsLong,
			        rawCreateDispositionLong,
			        fileAttributesAndFlags,
			        creationDisposition);

			final int fileAttributes = FileAttribute.fromAttributesAndFlags(fileAttributesAndFlags);
			// int fileOptions = (FileOptions )(fileAttributesAndFlags & FileOptionsMask);
			// int desiredAccess = (FileAccess )(rawDesiredAccess & FileAccessMask);
			// int shareAccess = (FileShare )(rawShareAccess & FileShareMask);

			final Path path = Paths.get(rawFileName.toString());
			final boolean isDirectory = dokanFileInfo.isDirectory();
			final String itemType = isDirectory ? "directory" : "file";
			final CreationDisposition disposition = CreationDisposition.fromInt(creationDisposition.getValue());

			// Set initial result to success
			long result = SUCCESS.val;

			try {
				if (path.equals(fs.getRootPath())) {
					switch (disposition) {
					case CREATE_NEW:
					case CREATE_ALWAYS: {
						return ERROR_ALREADY_EXISTS.val;
					}
					case OPEN_EXISTING:
					case OPEN_ALWAYS: {
						return SUCCESS.val;
					}
					case TRUNCATE_EXISTING: {
						return ERROR_NOT_SUPPORTED.val;
					}
					}

				}
				if (isSkipFile(rawFileName)) {
					return FileInvalid.val;
				}

				logger.debug("ZwCreateFile with {} and {}: {}", disposition.name, itemType, path);
				logger.debug("attribs: {} ", fileAttributes);

				TNode node;

				try {
					node = fs.findExisting(path, isDirectory);

					final boolean itemExists = Utils.isNotNull(node);
					logger.trace("item {} exists? {}", path, itemExists);

					switch (disposition) {

					case CREATE_NEW: {
						if (itemExists) {
							result = OBJECT_NAME_COLLISION.val;
						}
						break;
					}

					case OPEN_EXISTING: {
						if (!itemExists) {
							result = ERROR_FILE_NOT_FOUND.val;
						}
						break;
					}

					case TRUNCATE_EXISTING: {
						if (itemExists) {
							result = ERROR_FILE_NOT_FOUND.val;
						}
						// TODO: need to truncate somehow
						// May not be correct
						if (!isDirectory) {
							fs.truncate(getHandle(rawFileName, dokanFileInfo._context));
						}
						break;
					}

					case CREATE_ALWAYS:
					case OPEN_ALWAYS: {
						break;
					}
					}

					if (result == SUCCESS.val) {
						final FileAttribute attributes = FileAttribute.fromInt(fileAttributes);

						if (!itemExists) {
							if (isDirectory) {
								node = fs.createDirectory(path, rawCreateOptionsLong, attributes);
							} else {
								node = fs.createFile(path, rawCreateOptionsLong, attributes);
							}
						}

						dokanFileInfo._context = fs.getHandle(node).getID();
						dokanFileInfo.write();

						result = SUCCESS.val;
					}

				} catch (final Throwable t) {
					logger.warn("Caught error: ", t);
					result = exceptionToErrorCode(t);
					logger.warn("Set result to {}: ", result);
				}

				return result;
			} finally {
				logger.trace("final result for {}: {}", path, result);
			}
		}
	}

	private class Mounted implements Operations.MountedDelegate {
		@Override
		public long callback(final DokanFileInfo dokanFileInfo) {

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
		public long callback(final DokanFileInfo dokanFileInfo) {

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
		        final DokanFileInfo dokanFileInfo) {
			if (isSkipFile(fileName)) {
				return;
			}

			try {
				fs.cleanup(getHandle(fileName, dokanFileInfo._context));
				logger.trace("Cleaned up: {}", fileName);
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class CloseFile implements Operations.CloseFileDelegate {
		@Override
		public void callback(final WString fileName, final DokanFileInfo dokanFileInfo) {
			if (isSkipFile(fileName)) {
				return;
			}

			try {
				fs.close(getHandle(fileName, dokanFileInfo._context));
				logger.trace("Closed file: {}", fileName);
			} catch (final FileNotFoundException e) {
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class GetVolumeInformation implements Operations.GetVolumeInformationDelegate {
		@Override
		public long callback(
		        final Pointer volumeNameBuffer,
		        final int volumeNameSize,
		        final IntByReference rawVolumeSerialNumber,
		        final IntByReference rawMaximumComponentLength,
		        final IntByReference rawFileSystemFlags,
		        final Pointer rawFileSystemNameBuffer,
		        final int rawFileSystemNameSize,
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("GetVolumeInformation");

			try {
				volumeNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getVolumeName(), volumeNameSize));
				logger.debug("Volume name: {}", fs.getVolumeName());

				rawVolumeSerialNumber.setValue(fs.getVolumeSerialNumber());
				logger.debug("Serial number: {}", fs.getVolumeSerialNumber());

				rawMaximumComponentLength.setValue(fs.getMaxComponentLength());
				logger.debug("Max component length: {}", fs.getMaxComponentLength());

				rawFileSystemFlags.setValue(fs.getFileSystemFeatures());
				logger.debug("File system features: {}", FileSystemFeatures.fromInt(fs.getFileSystemFeatures()));

				rawFileSystemNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getFileSystemName(), rawFileSystemNameSize));
				logger.debug("File system name: {}", fs.getFileSystemName());

				logger.debug("dokanFileInfo: {}", dokanFileInfo);

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
		        final DokanFileInfo dokanFileInfo) {

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
		        final DokanFileInfo dokanFileInfo) {

			logger.trace("FindFiles");

			try {
				fs.findFiles(getHandle(fileName, dokanFileInfo._context), win32FindData -> rawFillFindData.callback(win32FindData, dokanFileInfo));
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private void findFilesWithPattern(final FileHandle<TNode> handle, final PathMatcher pathMatcher, final FileEmitter emitter) throws IOException {
		fs.findFiles(handle, win32FindData -> {
			final Path path = Paths.get(Native.toString(win32FindData.cFileName));
			if (pathMatcher.matches(path)) {
				emitter.emit(win32FindData);
			}
		});
	}

	private class FindFilesWithPattern implements Operations.FindFilesWithPatternDelegate {
		@Override
		public long callback(final WString fileName,
		        final WString searchPattern,
		        final Operations.FillWin32FindData rawFillFindData,
		        final DokanFileInfo dokanFileInfo) {

			try {
				final FileHandle<TNode> handle = getHandle(fileName, dokanFileInfo._context);
				final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(GLOB + searchPattern.toString());
				findFilesWithPattern(handle, matcher, win32FindData -> {
					rawFillFindData.callback(win32FindData, dokanFileInfo);
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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("ReadFile: {}", fileName);

			try {
				final byte[] data = new byte[bufferLength];
				final int read = fs.read(getHandle(fileName, dokanFileInfo._context), offset, data, bufferLength);
				buffer.write(0, data, 0, read);
				readLength.setValue(read);
				logger.debug("Read this number of bytes: {}", read);
				logger.debug("buffer.getString: {}", buffer.getString(0));
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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("WriteFile: {}", fileName);
			try {
				final byte[] data = new byte[numberOfBytesToWrite];
				buffer.read(0L, data, 0, numberOfBytesToWrite);
				final int written = fs.write(getHandle(fileName, dokanFileInfo._context), offset, data, numberOfBytesToWrite);
				numberOfBytesWritten.setValue(written);
				logger.debug("Wrote this number of bytes: {}", written);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class FlushFileBuffers implements Operations.FlushFileBuffersDelegate {
		@Override
		public long callback(final WString fileName,
		        final DokanFileInfo dokanFileInfo) {

			logger.trace("FlushFileBuffers");
			try {
				fs.flushFileBuffers(getHandle(fileName, dokanFileInfo._context));
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class GetFileInformation implements Operations.GetFileInformationDelegate {
		@Override
		public long callback(final WString fileName,
		        final ByHandleFileInfo info,
		        final DokanFileInfo dokanFileInfo) {
			logger.debug("GetFileInformation: {}", fileName);

			if (isSkipFile(fileName)) {
				return FileInvalid.val;
			}
			logger.debug("origInfo: {}", info);
			try {
				final ByHandleFileInfo retrievedInfo = getFileInfo(fileName, dokanFileInfo);
				// if (!fileName.toString().equals("\\")) {
				retrievedInfo.copyTo(info);
				// }

				logger.debug("updatedInfo: {}", info);

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
		        final DokanFileInfo rawInfo) {

			logger.debug("SetFileAttributes as {} for {}", FileAttribute.fromInt(attributes), fileName);

			try {
				fs.setAttributes(getHandle(fileName, rawInfo._context), FileAttribute.fromInt(attributes));
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileTime implements Operations.SetFileTimeDelegate {
		@Override
		public long callback(final WString fileName,
		        final FILETIME creationTime,
		        final FILETIME lastAccessTime,
		        final FILETIME lastWriteTime,
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("SetFileTime as creationTime = {}; lastAccessTime = {}; lastWriteTime = {}", creationTime, lastAccessTime, lastWriteTime);

			try {
				fs.setTime(getHandle(fileName, dokanFileInfo._context), null, null, null);
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
		        final DokanFileInfo dokanFileInfo) {

			logger.trace("FindStreams for {}", fileName);

			try {
				fs.findStreams(getHandle(fileName, dokanFileInfo._context), stream -> fill.callback(stream.toStruct(), dokanFileInfo));

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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("GetFileSecurity");

			try {
				final byte[] out = new byte[rawSecurityDescriptorLength];
				final int expectedLength = fs.getSecurity(getHandle(fileName, dokanFileInfo._context), rawRequestedInformation, out);
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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("SetFileSecurity");

			try {
				final byte[] data = new byte[rawSecurityDescriptorLength];
				rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
				fs.setSecurity(getHandle(fileName, dokanFileInfo._context), rawSecurityInformation, data);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteFile implements Operations.DeleteFileDelegate {
		@Override
		public long callback(final WString fileName,
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("DeleteFile: {}", fileName);

			try {
				fs.deleteFile(getHandle(fileName, dokanFileInfo._context));

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteDirectory implements Operations.DeleteDirectoryDelegate {
		@Override
		public long callback(final WString fileName,
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("DeleteDirectory: {}", fileName);

			try {
				fs.deleteDirectory(getHandle(fileName, dokanFileInfo._context));

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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("MoveFile: {} to {}; replace existing? ", oldFileName, newFileName, replaceIfExisting);

			try {
				fs.move(getHandle(oldFileName, dokanFileInfo._context), getHandle(newFileName, dokanFileInfo._context), replaceIfExisting);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class SetEndOfFile implements Operations.SetEndOfFileDelegate {
		@Override
		public long callback(final WString fileName, final long byteOffset, final DokanFileInfo dokanFileInfo) {
			logger.debug("SetEndOfFile");
			try {
				fs.setEndOfFile(getHandle(fileName, dokanFileInfo._context), byteOffset);
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
		        final DokanFileInfo dokanFileInfo) {
			logger.debug("SetAllocationSize");
			try {
				fs.setAllocationSize(getHandle(fileName, dokanFileInfo._context), length);
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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("LockFile");
			try {
				fs.lock(getHandle(fileName, dokanFileInfo._context), byteOffset, length);

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
		        final DokanFileInfo dokanFileInfo) {

			logger.debug("UnlockFile");
			try {
				fs.unlock(getHandle(fileName, dokanFileInfo._context), byteOffset, length);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	static class Win32FindStreamData extends Structure implements Operations.Win32FindStreamData {
		public long length;
		public char[] cFileName = new char[MAX_PATH + 36];

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
		logger.warn(t.getMessage(), t);

		if (t instanceof DokanyException) {
			return ((DokanyException) t).val;
		}
		if (t instanceof FileNotFoundException) {
			return ERROR_FILE_NOT_FOUND.val;
		}
		if (t instanceof FileAlreadyExistsException) {
			return ERROR_ALREADY_EXISTS.val;
		}

		return defaultCode;
	}

	private boolean isSkipFile(final WString fileName) {
		boolean toReturn = false;

		if (fileName.toString().endsWith("desktop.ini")
		        || fileName.toString().endsWith("folder.jpg")
		        || fileName.toString().endsWith("folder.gif")) {
			// logger.debug("Skipping file: " + fileName);
			toReturn = true;
		}
		return toReturn;
	}

	private FileHandle<TNode> getHandle(final WString path, final long id) throws IOException {
		return fs.getHandle(Paths.get(path.toString()), id);
	}

	private ByHandleFileInfo getFileInfo(final WString fileNameW, final DokanFileInfo dokanFileInfo) throws IOException {
		final String fileName = fileNameW.toString();

		final FileHandle<TNode> handle = getHandle(fileNameW, dokanFileInfo._context);
		final ByHandleFileInfo fileInfo;

		if (Paths.get(fileName).toString().equals(fs.getRootPath().toString())) {
			fileInfo = getRootInfo();
			logger.debug("retrieved root info");
		} else {
			fileInfo = fs.getInfo(handle);
		}
		return fileInfo;
	}

	/**
	 * Get root file info.
	 *
	 * @return ByHandleFileInfo for root
	 */
	private ByHandleFileInfo getRootInfo() {
		final ByHandleFileInfo.Builder builder = new ByHandleFileInfo.Builder(fs.getRootPath());
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
