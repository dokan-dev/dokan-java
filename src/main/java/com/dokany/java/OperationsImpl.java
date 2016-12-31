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
import java.nio.file.FileAlreadyExistsException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.FileSystemFeatures;
import com.dokany.java.structure.ByHandleFileInfo;
import com.dokany.java.structure.DokanFileInfo;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * Implementation of {@link com.dokany.java.Operations} which connects to {@link com.dokany.java.FileSystem}.
 *
 * @param <TNode>
 */
final class OperationsImpl extends Operations {
	final FileSystem fs;
	public static final int MAX_PATH = 260;
	private final static Logger LOGGER = LoggerFactory.getLogger(OperationsImpl.class);

	OperationsImpl(final FileSystem fs) {
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
		        final WString path,
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

			final String normalizedPath = Utils.normalize(path);
			final boolean isDirectory = dokanFileInfo.isDirectory();
			final CreationDisposition disposition = CreationDisposition.fromInt(creationDisposition.getValue());

			// Set initial result to success
			long result = SUCCESS.val;

			try {
				if (normalizedPath.equals(fs.getRootPath())) {
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
				if (isSkipFile(path)) {
					return FileInvalid.val;
				}

				try {
					final boolean itemExists = fs.pathExists(normalizedPath);
					LOGGER.trace("item {} exists? {}", normalizedPath, itemExists);

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
							fs.truncate(normalizedPath);
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
								fs.createEmptyDirectory(normalizedPath, rawCreateOptionsLong, attributes);
							} else {
								fs.createEmptyFile(normalizedPath, rawCreateOptionsLong, attributes);
							}
						}

						result = SUCCESS.val;
					}

				} catch (final Throwable t) {
					LOGGER.warn("Caught error: ", t);
					result = exceptionToErrorCode(t);
					LOGGER.warn("Set result to {}: ", result);
				}

				return result;
			} finally {
				LOGGER.trace("final result for {}: {}", normalizedPath, result);
			}
		}
	}

	private class Mounted implements Operations.MountedDelegate {
		@Override
		public long callback(final DokanFileInfo dokanFileInfo) {

			try {
				fs.mounted();
				LOGGER.info("Dokany File System mounted");
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
				LOGGER.info("Dokany File System unmounted");
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class Cleanup implements Operations.CleanupDelegate {
		@Override
		public void callback(final WString path,
		        final DokanFileInfo dokanFileInfo) {
			if (isSkipFile(path)) {
				return;
			}

			try {
				final String normalizedPath = Utils.normalize(path);
				fs.cleanup(normalizedPath);
				LOGGER.trace("Cleaned up: {}", normalizedPath);
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class CloseFile implements Operations.CloseFileDelegate {
		@Override
		public void callback(final WString path, final DokanFileInfo dokanFileInfo) {
			if (isSkipFile(path)) {
				return;
			}

			try {
				final String normalizedPath = Utils.normalize(path);
				fs.close(normalizedPath);
				LOGGER.trace("Closed file: {}", normalizedPath);
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

			LOGGER.trace("GetVolumeInformation");

			try {
				volumeNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getVolumeName(), volumeNameSize));
				LOGGER.trace("Volume name: {}", fs.getVolumeName());

				rawVolumeSerialNumber.setValue(fs.getVolumeSerialNumber());
				LOGGER.trace("Serial number: {}", fs.getVolumeSerialNumber());

				rawMaximumComponentLength.setValue(fs.getMaxComponentLength());
				LOGGER.trace("Max component length: {}", fs.getMaxComponentLength());

				rawFileSystemFlags.setValue(fs.getFileSystemFeatures());
				LOGGER.trace("File system features: {}", FileSystemFeatures.fromInt(fs.getFileSystemFeatures()));

				rawFileSystemNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getFileSystemName(), rawFileSystemNameSize));
				LOGGER.trace("File system name: {}", fs.getFileSystemName());

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

			LOGGER.trace("GetDiskFreeSpace");

			rawFreeBytesAvailable.setValue(fs.getFreeBytesAvailable());
			rawTotalNumberOfBytes.setValue(fs.getTotalBytesAvailable());
			rawTotalNumberOfFreeBytes.setValue(fs.getFreeBytesAvailable());

			return 0;
		}
	}

	private class FindFiles implements Operations.FindFilesDelegate {
		@Override
		public long callback(final WString path,
		        final Operations.FillWin32FindData rawFillFindData,
		        final DokanFileInfo dokanFileInfo) {

			final String pathToSearch = Utils.normalize(path);
			LOGGER.trace("FindFiles: {}", pathToSearch);

			try {
				final Set<WIN32_FIND_DATA> files = fs.findFiles(pathToSearch);
				LOGGER.debug("Found {} files", files.size());
				files.forEach(file -> {
					rawFillFindData.callback(file, dokanFileInfo);
				});

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class FindFilesWithPattern implements Operations.FindFilesWithPatternDelegate {
		@Override
		public long callback(final WString path,
		        final WString searchPattern,
		        final Operations.FillWin32FindData rawFillFindData,
		        final DokanFileInfo dokanFileInfo) {

			final String pathToSearch = Utils.normalize(path);
			final String pattern = searchPattern.toString();
			LOGGER.trace("FindFilesWithPattern {} with pattern {}", pathToSearch, pattern);

			try {
				final Set<WIN32_FIND_DATA> files = fs.findFilesWithPattern(pathToSearch, pattern);
				LOGGER.debug("Found {} files", files.size());
				files.forEach(file -> {
					rawFillFindData.callback(file, dokanFileInfo);
				});

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class FindStreams implements Operations.FindStreamsDelegate {
		@Override
		public long callback(final WString path,
		        final FillWin32FindStreamData rawFillFindData,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("FindStreams: {}", normalizedPath);

			try {
				final Set<Win32FindStreamData> streams = fs.findStreams(normalizedPath);
				LOGGER.debug("Found {} streams", streams.size());
				streams.forEach(file -> {
					rawFillFindData.callback(file, dokanFileInfo);
				});
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class ReadFile implements Operations.ReadFileDelegate {
		@Override
		public long callback(final WString path,
		        final Pointer buffer,
		        final int bufferLength,
		        final IntByReference readLengthRef,
		        final long offset,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("ReadFile: {}", normalizedPath);

			try {
				final byte[] data = new byte[bufferLength];
				final int numRead = fs.read(normalizedPath, (int) offset, data, bufferLength);
				buffer.write(0, data, 0, numRead);
				readLengthRef.setValue(numRead);
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_READ_FAULT.val);
			}
			return Success.val;
		}
	}

	private class WriteFile implements Operations.WriteFileDelegate {
		@Override
		public long callback(final WString path,
		        final Pointer buffer,
		        final int numberOfBytesToWrite,
		        final IntByReference numberOfBytesWritten,
		        final long offset,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.debug("WriteFile: {}", normalizedPath);

			try {
				final byte[] data = new byte[numberOfBytesToWrite];
				buffer.read(0L, data, 0, numberOfBytesToWrite);
				final int written = fs.write(normalizedPath, (int) offset, data, numberOfBytesToWrite);
				numberOfBytesWritten.setValue(written);
				LOGGER.debug("Wrote this number of bytes: {}", written);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class FlushFileBuffers implements Operations.FlushFileBuffersDelegate {
		@Override
		public long callback(final WString path,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("FlushFileBuffers: {}", normalizedPath);
			try {
				fs.flushFileBuffers(normalizedPath);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class GetFileInformation implements Operations.GetFileInformationDelegate {
		@Override
		public long callback(final WString path,
		        final ByHandleFileInfo info,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.debug("GetFileInformation native: {}", path.toString());
			LOGGER.debug("GetFileInformation normalized: {}", normalizedPath);

			if (isSkipFile(path)) {
				return FileInvalid.val;
			}
			try {
				final ByHandleFileInfo retrievedInfo = fs.getInfo(normalizedPath);
				retrievedInfo.copyTo(info);
				LOGGER.debug("info for {}: {}", normalizedPath, info);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileAttributes implements Operations.SetFileAttributesDelegate {
		@Override
		public long callback(final WString path,
		        final int attributes,
		        final DokanFileInfo rawInfo) {

			final String normalizedPath = Utils.normalize(path);
			final FileAttribute attribs = FileAttribute.fromInt(attributes);
			LOGGER.trace("SetFileAttributes as {} for {}", attribs, normalizedPath);

			try {
				fs.setAttributes(normalizedPath, attribs);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileTime implements Operations.SetFileTimeDelegate {
		@Override
		public long callback(final WString path,
		        final FILETIME creationTime,
		        final FILETIME lastAccessTime,
		        final FILETIME lastWriteTime,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetFileTime for {}; creationTime = {}; lastAccessTime = {}; lastWriteTime = {}", normalizedPath, creationTime, lastAccessTime, lastWriteTime);

			try {
				fs.setTime(Utils.normalize(path), creationTime, lastAccessTime, lastWriteTime);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class GetFileSecurity implements Operations.GetFileSecurityDelegate {
		@Override
		public long callback(final WString path,
		        final int rawRequestedInformation,
		        final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        final IntByReference rawSecurityDescriptorLengthNeeded,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetFileSecurity: {}", normalizedPath);

			try {
				final byte[] out = new byte[rawSecurityDescriptorLength];
				final int expectedLength = fs.getSecurity(normalizedPath, rawRequestedInformation, out);
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
		public long callback(final WString path,
		        final int rawSecurityInformation,
		        final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetFileSecurity: {}", normalizedPath);

			try {
				final byte[] data = new byte[rawSecurityDescriptorLength];
				rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
				fs.setSecurity(normalizedPath, rawSecurityInformation, data);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteFile implements Operations.DeleteFileDelegate {
		@Override
		public long callback(final WString path,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("DeleteFile: {}", normalizedPath);

			try {
				fs.deleteFile(normalizedPath);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteDirectory implements Operations.DeleteDirectoryDelegate {
		@Override
		public long callback(final WString path,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("DeleteDirectory: {}", normalizedPath);

			try {
				fs.deleteDirectory(normalizedPath);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class MoveFile implements Operations.MoveFileDelegate {
		@Override
		public long callback(final WString oldPath,
		        final WString newPath,
		        final boolean replaceIfExisting,
		        final DokanFileInfo dokanFileInfo) {

			final String oldNormalizedPath = Utils.normalize(oldPath);
			final String newNormalizedPath = Utils.normalize(newPath);
			LOGGER.debug("trace: {} to {}; replace existing? ", oldNormalizedPath, newNormalizedPath, replaceIfExisting);

			try {
				fs.move(oldNormalizedPath, newNormalizedPath, replaceIfExisting);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class SetEndOfFile implements Operations.SetEndOfFileDelegate {
		@Override
		public long callback(final WString path, final long offset, final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetEndOfFile: {}", normalizedPath);

			try {
				fs.setEndOfFile(normalizedPath, (int) offset);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class SetAllocationSize implements Operations.SetAllocationSizeDelegate {
		@Override
		public long callback(final WString path,
		        final long length,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetAllocationSize: {}", normalizedPath);

			try {
				fs.setAllocationSize(normalizedPath, (int) length);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class LockFile implements Operations.LockFileDelegate {
		@Override
		public long callback(final WString path,
		        final long offset,
		        final long length,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("LockFile: {}", normalizedPath);

			try {
				fs.lock(normalizedPath, (int) offset, (int) length);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class UnlockFile implements Operations.UnlockFileDelegate {
		@Override
		public long callback(final WString path,
		        final long offset,
		        final long length,
		        final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("UnlockFile: {}", normalizedPath);
			try {
				fs.unlock(normalizedPath, (int) offset, (int) length);

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private static long exceptionToErrorCode(final Throwable t) {
		return exceptionToErrorCode(t, Unsuccessful.val);
	}

	private static long exceptionToErrorCode(final Throwable t, final long defaultCode) {
		LOGGER.warn(t.getMessage(), t);

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

	private boolean isSkipFile(final WString path) {
		boolean toReturn = false;

		final String normalizedPath = Utils.normalize(path);

		if (normalizedPath.endsWith("desktop.ini")
		        || normalizedPath.endsWith("folder.jpg")
		        || normalizedPath.endsWith("folder.gif")) {
			LOGGER.trace("Skipping file: " + normalizedPath);
			toReturn = true;
		}
		return toReturn;
	}

	/**
	 * @see {@link com.dokany.java.FileSystem#isDefaultLog()}
	 * @return true if default file system log is enabled.
	 */
	private boolean isDefaultLog() {
		return fs.isDefaultLog();
	}
}
