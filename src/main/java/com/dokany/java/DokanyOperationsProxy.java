package com.dokany.java;

import static com.dokany.java.constants.ErrorCodes.ERROR_ALREADY_EXISTS;
import static com.dokany.java.constants.ErrorCodes.ERROR_FILE_NOT_FOUND;
import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;
import static com.dokany.java.constants.ErrorCodes.ERROR_WRITE_FAULT;
import static com.dokany.java.constants.ErrorCodes.OBJECT_NAME_COLLISION;
import static com.dokany.java.constants.ErrorCodes.SUCCESS;
import static com.dokany.java.constants.NtStatus.FileInvalid;
import static com.dokany.java.constants.NtStatus.Success;
import static com.dokany.java.constants.WinError.ERROR_NOT_SUPPORTED;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAccess;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.structure.ByHandleFileInfo;
import com.dokany.java.structure.DokanFileInfo;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.VolumeInformation;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * Implementation of {@link com.dokany.java.DokanyOperations} which connects to {@link com.dokany.java.FileSystem}.
 */
final class DokanyOperationsProxy extends DokanyOperations {

	final FileSystem fileSystem;
	final VolumeInformation volumeInfo;
	final FreeSpace freeSpace;

	public final static int MAX_PATH = 260;
	private final static Logger LOGGER = LoggerFactory.getLogger(DokanyOperationsProxy.class);

	DokanyOperationsProxy(@NotNull final FileSystem fileSystem) {
		this.fileSystem = fileSystem;
		volumeInfo = fileSystem.getVolumeInfo();
		freeSpace = fileSystem.getFreeSpace();

		CreateFile = new CreateFile();
		CloseFile = new CloseFile();
		Cleanup = new Cleanup();
		ReadFile = new ReadFile();
		WriteFile = new WriteFile();
		FlushFileBuffers = new FlushFileBuffers();
		GetFileInformation = new GetFileInformation();
		GetVolumeInformation = new GetVolumeInformation();
		GetDiskFreeSpace = new GetDiskFreeSpace();
		FindFiles = new FindFiles();
		FindFilesWithPattern = new FindFilesWithPattern();
		FindStreams = new FindStreams();
		SetFileAttributes = new SetFileAttributes();
		SetFileTime = new SetFileTime();
		DeleteFile = new DeleteFile();
		DeleteDirectory = new DeleteDirectory();
		MoveFile = new MoveFile();
		SetEndOfFile = new SetEndOfFile();
		SetAllocationSize = new SetAllocationSize();
		LockFile = new LockFile();
		UnlockFile = new UnlockFile();
		Mounted = new Mounted();
		Unmounted = new Unmounted();
		GetFileSecurity = new GetFileSecurity();
		SetFileSecurity = new SetFileSecurity();
	}

	private class CreateFile implements DokanyOperations.CreateFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final IntByReference securityContext,
		        final int rawDesiredAccess,
		        final int rawFileAttributes,
		        final int rawShareAccess,
		        final int rawCreateDisposition,
		        final int rawCreateOptions,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final long rawFileAttributesLong = rawFileAttributes;
			final long rawCreateOptionsLong = rawCreateOptions;
			final long rawCreateDispositionLong = rawCreateDisposition;
			final IntByReference fileAttributesAndFlags = new IntByReference();
			final IntByReference creationDisposition = new IntByReference();

			final String normalizedPath = Utils.normalize(path);
			final boolean isDirectory = dokanFileInfo.isDirectory();

			NativeMethods.DokanMapKernelToUserCreateFileFlags(rawFileAttributesLong, rawCreateOptionsLong, rawCreateDispositionLong, fileAttributesAndFlags,
			        creationDisposition);
			final int fileAttributes = FileAttribute.fromAttributesAndFlags(fileAttributesAndFlags);
			// int fileOptions = (FileOptions )(fileAttributesAndFlags & FileOptionsMask);
			final long desiredAccess = FileAccess.fromAttributesAndFlags(rawDesiredAccess);
			// int shareAccess = (FileShare )(rawShareAccess & FileShareMask);

			final CreationDisposition disposition = CreationDisposition.fromInt(creationDisposition.getValue());
			LOGGER.debug("CreateFile: {} {}", disposition, normalizedPath);

			// Set initial result to success
			long result = SUCCESS.val;

			try {
				if (normalizedPath.equals(fileSystem.getRootPath())) {
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
					final boolean itemExists = fileSystem.doesPathExist(normalizedPath);
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
							fileSystem.truncate(normalizedPath);
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
								fileSystem.createEmptyDirectory(normalizedPath, rawCreateOptionsLong, attributes);
							} else {
								fileSystem.createEmptyFile(normalizedPath, rawCreateOptionsLong, attributes);
							}
						}

						result = SUCCESS.val;
					}

				} catch (final Throwable t) {
					LOGGER.warn("Caught error: ", t);
					result = Utils.exceptionToErrorCode(t);
					LOGGER.warn("Set result to {}: ", result);
				}

				return result;
			} finally {
				LOGGER.trace("final result for {}: {}", normalizedPath, result);
			}
		}
	}

	private final class Mounted implements DokanyOperations.Mounted {
		@Override
		public long mounted(final DokanFileInfo dokanFileInfo) {
			try {
				fileSystem.mounted();
				LOGGER.info("Dokany File System mounted");
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class Unmounted implements DokanyOperations.Unmounted {
		@Override
		public long unmounted(final DokanFileInfo dokanFileInfo) {

			try {
				fileSystem.unmounted();
				LOGGER.info("Dokany File System unmounted");
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class Cleanup implements DokanyOperations.Cleanup {
		@Override
		public void callback(
		        @NotNull final WString path,
		        @NotNull final DokanFileInfo dokanFileInfo) {
			if (isSkipFile(path)) {
				return;
			}

			try {
				final String normalizedPath = Utils.normalize(path);
				fileSystem.cleanup(normalizedPath);
				LOGGER.trace("Cleaned up: {}", normalizedPath);
			} catch (final Throwable t) {
				LOGGER.warn("Error in clearning up file: {}", path, t);
			}
		}
	}

	private class CloseFile implements DokanyOperations.CloseFile {
		@Override
		public void callback(
		        @NotNull final WString path,
		        @NotNull final DokanFileInfo dokanFileInfo) {
			if (isSkipFile(path)) {
				return;
			}

			try {
				final String normalizedPath = Utils.normalize(path);
				fileSystem.close(normalizedPath);
				LOGGER.trace("Closed file: {}", normalizedPath);
			} catch (final Throwable e) {
				LOGGER.warn("Error in closing file: {}", path, e);
			}
		}
	}

	private class GetVolumeInformation implements DokanyOperations.GetVolumeInformation {
		@Override
		public long callback(
		        @NotNull final Pointer volumeNameBuffer,
		        final int volumeNameSize,
		        @NotNull final IntByReference rawVolumeSerialNumber,
		        @NotNull final IntByReference rawMaximumComponentLength,
		        @NotNull final IntByReference rawFileSystemFlags,
		        @NotNull final Pointer rawFileSystemNameBuffer,
		        final int rawFileSystemNameSize,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			LOGGER.trace("GetVolumeInformation");

			try {
				volumeNameBuffer.setWideString(0L, Utils.trimStrToSize(fileSystem.getVolumeInfo().getVolumeName(), volumeNameSize));

				rawVolumeSerialNumber.setValue(fileSystem.getVolumeInfo().getVolumeSerialNumber());

				rawMaximumComponentLength.setValue(fileSystem.getVolumeInfo().getMaxComponentLength());

				rawFileSystemFlags.setValue(fileSystem.getVolumeInfo().getFileSystemFeatures().val);

				rawFileSystemNameBuffer.setWideString(0L, Utils.trimStrToSize(fileSystem.getVolumeInfo().getFileSystemName(), rawFileSystemNameSize));

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class GetDiskFreeSpace implements DokanyOperations.GetDiskFreeSpace {
		@Override
		public long callback(
		        @NotNull final LongByReference rawFreeBytesAvailable,
		        @NotNull final LongByReference rawTotalNumberOfBytes,
		        @NotNull final LongByReference rawTotalNumberOfFreeBytes,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			LOGGER.trace("GetDiskFreeSpace");
			LOGGER.trace("rawFreeBytesAvailable: {}", rawFreeBytesAvailable.getValue());
			LOGGER.debug("rawTotalNumberOfBytes", rawTotalNumberOfBytes);
			LOGGER.debug("rawTotalNumberOfFreeBytes", rawTotalNumberOfFreeBytes);

			// rawTotalNumberOfBytes.setValue(new LONGLONG(freeSpace.getTotalBytes()));

			// These two are the same unless per-user quotas are enabled
			// rawTotalNumberOfFreeBytes.setValue(new LONGLONG(freeSpace.getFreeBytes()));

			// If per-user quotas are being used, this value may be less than the total number of free bytes on a disk
			rawFreeBytesAvailable.setValue(freeSpace.getFreeBytes());
			LOGGER.trace("new rawFreeBytesAvailable: {}", rawFreeBytesAvailable.getValue());

			return 0;
		}
	}

	private class FindFiles implements DokanyOperations.FindFiles {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanyOperations.FillWin32FindData rawFillFindData,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String pathToSearch = Utils.normalize(path);
			LOGGER.trace("FindFiles: {}", pathToSearch);

			try {
				final Set<WIN32_FIND_DATA> filesFound = fileSystem.findFiles(pathToSearch);
				LOGGER.debug("Found {} files", filesFound.size());
				filesFound.forEach(file -> {
					rawFillFindData.fillWin32FindData(file, dokanFileInfo);
				});

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class FindFilesWithPattern implements DokanyOperations.FindFilesWithPattern {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final WString searchPattern,
		        @NotNull final DokanyOperations.FillWin32FindData rawFillFindData,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String pathToSearch = Utils.normalize(path);
			final String pattern = searchPattern.toString();
			LOGGER.trace("FindFilesWithPattern {} with pattern {}", pathToSearch, pattern);

			try {
				final Set<WIN32_FIND_DATA> filesFound = fileSystem.findFilesWithPattern(pathToSearch, pattern);
				LOGGER.debug("Found {} files", filesFound.size());
				LOGGER.debug("rawFillFindData: {}", rawFillFindData);
				filesFound.forEach(file -> {
					LOGGER.trace("file in find: {}", file.getFileName());
					rawFillFindData.fillWin32FindData(file, dokanFileInfo);
				});

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class FindStreams implements DokanyOperations.FindStreams {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final FillWin32FindStreamData rawFillFindData,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("FindStreams: {}", normalizedPath);

			try {
				final Set<Win32FindStreamData> streams = fileSystem.findStreams(normalizedPath);
				LOGGER.debug("Found {} streams", streams.size());
				streams.forEach(file -> {
					rawFillFindData.callback(file, dokanFileInfo);
				});
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class ReadFile implements DokanyOperations.ReadFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final Pointer buffer,
		        final int bufferLength,
		        @NotNull final IntByReference readLengthRef,
		        final long offset,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.debug("ReadFile: {} with readLength ", normalizedPath, bufferLength);

			try {
				final FileData data = fileSystem.read(normalizedPath, (int) offset, bufferLength);
				final int numRead = data.getLength();
				LOGGER.debug("numRead: {}", numRead);

				if (numRead > 0) {
					buffer.write(0, data.getBytes(), 0, numRead);
					LOGGER.debug("wrote data length: {}", data.getBytes().length);
				}

				readLengthRef.setValue(numRead);
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t, ERROR_READ_FAULT.val);
			}
			return Success.val;
		}
	}

	private class WriteFile implements DokanyOperations.WriteFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final Pointer buffer,
		        @NotNull final int numberOfBytesToWrite,
		        @NotNull final IntByReference numberOfBytesWritten,
		        @NotNull final long offset,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.debug("WriteFile: {}", normalizedPath);

			try {
				final byte[] data = new byte[numberOfBytesToWrite];
				buffer.read(0L, data, 0, numberOfBytesToWrite);
				final int written = fileSystem.write(normalizedPath, (int) offset, data, numberOfBytesToWrite);
				numberOfBytesWritten.setValue(written);
				LOGGER.debug("Wrote this number of bytes: {}", written);
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class FlushFileBuffers implements DokanyOperations.FlushFileBuffers {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("FlushFileBuffers: {}", normalizedPath);
			try {
				fileSystem.flushFileBuffers(normalizedPath);
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class GetFileInformation implements DokanyOperations.GetFileInformation {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final ByHandleFileInfo info,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.debug("GetFileInformation: {}", normalizedPath);

			if (isSkipFile(path)) {
				return FileInvalid.val;
			}
			try {
				final ByHandleFileInfo retrievedInfo = fileSystem.getInfo(normalizedPath);
				retrievedInfo.copyTo(info);
				return Success.val;
			} catch (final Throwable t) {
				LOGGER.warn("Error reading info: {}", t.getMessage());
				return Utils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileAttributes implements DokanyOperations.SetFileAttributes {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final int attributes,
		        @NotNull final DokanFileInfo rawInfo) {

			final String normalizedPath = Utils.normalize(path);
			final FileAttribute attribs = FileAttribute.fromInt(attributes);
			LOGGER.trace("SetFileAttributes as {} for {}", attribs, normalizedPath);

			try {
				fileSystem.setAttributes(normalizedPath, attribs);
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class SetFileTime implements DokanyOperations.SetFileTime {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final FILETIME creationTime,
		        @NotNull final FILETIME lastAccessTime,
		        @NotNull final FILETIME lastWriteTime,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetFileTime for {}; creationTime = {}; lastAccessTime = {}; lastWriteTime = {}", normalizedPath, creationTime, lastAccessTime, lastWriteTime);

			try {
				fileSystem.setTime(Utils.normalize(path), creationTime, lastAccessTime, lastWriteTime);
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.val);
			}
		}
	}

	private class GetFileSecurity implements DokanyOperations.GetFileSecurity {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final int rawRequestedInformation,
		        @NotNull final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        @NotNull final IntByReference rawSecurityDescriptorLengthNeeded,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetFileSecurity: {}", normalizedPath);

			try {
				final byte[] out = new byte[rawSecurityDescriptorLength];
				final int expectedLength = fileSystem.getSecurity(normalizedPath, rawRequestedInformation, out);
				rawSecurityDescriptor.write(0L, out, 0, rawSecurityDescriptorLength);
				rawSecurityDescriptorLengthNeeded.setValue(expectedLength);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class SetFileSecurity implements DokanyOperations.SetFileSecurity {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final int rawSecurityInformation,
		        @NotNull final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetFileSecurity: {}", normalizedPath);

			try {
				final byte[] data = new byte[rawSecurityDescriptorLength];
				rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
				fileSystem.setSecurity(normalizedPath, rawSecurityInformation, data);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteFile implements DokanyOperations.DeleteFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("DeleteFile: {}", normalizedPath);

			try {
				fileSystem.deleteFile(normalizedPath);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class DeleteDirectory implements DokanyOperations.DeleteDirectory {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("DeleteDirectory: {}", normalizedPath);

			try {
				fileSystem.deleteDirectory(normalizedPath);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class MoveFile implements DokanyOperations.MoveFile {
		@Override
		public long callback(
		        @NotNull final WString oldPath,
		        @NotNull final WString newPath,
		        final boolean replaceIfExisting,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String oldNormalizedPath = Utils.normalize(oldPath);
			final String newNormalizedPath = Utils.normalize(newPath);
			LOGGER.debug("trace: {} to {}; replace existing? ", oldNormalizedPath, newNormalizedPath, replaceIfExisting);

			try {
				fileSystem.move(oldNormalizedPath, newNormalizedPath, replaceIfExisting);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class SetEndOfFile implements DokanyOperations.SetEndOfFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long offset,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetEndOfFile: {}", normalizedPath);

			try {
				fileSystem.setEndOfFile(normalizedPath, (int) offset);
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class SetAllocationSize implements DokanyOperations.SetAllocationSize {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long length,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("SetAllocationSize: {}", normalizedPath);

			try {
				fileSystem.setAllocationSize(normalizedPath, (int) length);
				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class LockFile implements DokanyOperations.LockFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long offset,
		        final long length,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("LockFile: {}", normalizedPath);

			try {
				fileSystem.lock(normalizedPath, (int) offset, (int) length);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	private class UnlockFile implements DokanyOperations.UnlockFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long offset,
		        final long length,
		        @NotNull final DokanFileInfo dokanFileInfo) {

			final String normalizedPath = Utils.normalize(path);
			LOGGER.trace("UnlockFile: {}", normalizedPath);
			try {
				fileSystem.unlock(normalizedPath, (int) offset, (int) length);

				return Success.val;
			} catch (final Throwable t) {
				return Utils.exceptionToErrorCode(t);
			}
		}
	}

	boolean isSkipFile(
	        @NotNull final WString path) {

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
		return fileSystem.isDefaultLog();
	}
}