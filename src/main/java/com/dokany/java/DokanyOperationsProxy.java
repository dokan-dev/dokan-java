package com.dokany.java;

import static com.dokany.java.constants.ErrorCode.ERROR_ALREADY_EXISTS;
import static com.dokany.java.constants.ErrorCode.ERROR_READ_FAULT;
import static com.dokany.java.constants.ErrorCode.ERROR_WRITE_FAULT;
import static com.dokany.java.constants.WinError.ERROR_NOT_SUPPORTED;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.ErrorCode;
import com.dokany.java.constants.FileAttribute;
import com.dokany.java.constants.NtStatus;
import com.dokany.java.structure.ByHandleFileInfo;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.EnumIntegerSet;
import com.dokany.java.structure.FileData;
import com.dokany.java.structure.FreeSpace;
import com.dokany.java.structure.VolumeInformation;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link DokanyOperations} which connects to {@link DokanyFileSystem}.
 */
@Slf4j
final class DokanyOperationsProxy extends DokanyOperations {

	final DokanyFileSystem fileSystem;
	final VolumeInformation volumeInfo;
	final FreeSpace freeSpace;

	public final static int MAX_PATH = 260;

	DokanyOperationsProxy(@NotNull final DokanyFileSystem fileSystem) {
		this.fileSystem = fileSystem;
		volumeInfo = fileSystem.getVolumeInfo();
		freeSpace = fileSystem.getFreeSpace();

		ZwCreateFile = new ZwCreateFile();
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
		FindStreams = new FindStreams();
	}
	/*-
	private static void convertCreateFileVariables(
	        final int rawDesiredAccess,
	        final int rawFileAttributes,
	        final int rawShareAccess,
	        final int rawCreateDisposition,
	        final int rawCreateOptions,
	        @NotNull final DokanyFileInfo dokanyFileInfo) {
		final long rawFileAttributesLong = rawFileAttributes;
		final long rawCreateOptionsLong = rawCreateOptions;
		final long rawCreateDispositionLong = rawCreateDisposition;
		final IntByReference fileAttributesAndFlags = new IntByReference();
		final IntByReference creationDisposition = new IntByReference();
	
		// TODO: Is calling this actually necessary? It does not seem to be.
		//NativeMethods.DokanMapKernelToUserCreateFileFlags(rawFileAttributesLong, rawCreateOptionsLong, rawCreateDispositionLong, fileAttributesAndFlags,creationDisposition);
		//LOGGER.trace("rawDesiredAccess: {}", rawDesiredAccess);
	
	
		final long genericDesiredAccess = NativeMethods.DokanMapStandardToGenericAccess(rawDesiredAccess);
		LOGGER.trace("genericDesiredAccess: {}", genericDesiredAccess);

		// int fileOptions = (FileOptions)(fileAttributesAndFlags & FileOptionsMask);

		final FileAccess fileAccess = FileAccess.fromInt(rawDesiredAccess);
		LOGGER.trace("fileAccess: {}", fileAccess);

		// int shareAccess = (FileShare)(rawShareAccess & FileShareMask);
	}
	*/

	/*-
	 *                          |                    When the file...
	This argument:           |             Exists            Does not exist
	-------------------------+------------------------------------------------------
	CREATE_ALWAYS            |            Truncates             Creates
	CREATE_NEW         +-----------+        Fails               Creates
	OPEN_ALWAYS     ===| does this |===>    Opens               Creates
	OPEN_EXISTING      +-----------+        Opens                Fails
	TRUNCATE_EXISTING        |            Truncates              Fails
	 */
	private final class ZwCreateFile implements DokanyOperations.ZwCreateFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final WinBase.SECURITY_ATTRIBUTES securityContext,
		        final int rawDesiredAccess,
		        final int rawFileAttributes,
		        final int rawShareAccess,
		        final int rawCreateDisposition,
		        final int rawCreateOptions,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			// Normalize path
			final String normalizedPath = DokanyUtils.normalize(path);

			Kernel32.INSTANCE.CreateFile(normalizedPath, rawDesiredAccess, rawShareAccess, securityContext, rawCreateDisposition, rawFileAttributes, null);

			return ErrorCode.SUCCESS.mask();
			/*-
			final CreationDisposition fileMode = CreationDisposition.fromInt(rawCreateDisposition);
			LOGGER.debug("CreateFile: {} {}", fileMode, normalizedPath);
			
			// Determine if directory
			final boolean isDirectory = dokanyFileInfo.isDirectory();
			LOGGER.debug("isDirectory: {}", isDirectory);
			
			// Set initial result to SUCCESS
			long result = ErrorCode.SUCCESS.getVal();
			
			try {
				if (normalizedPath.equals(fileSystem.getRootPath())) {
					return getRootReturnCode(normalizedPath, fileMode);
				}
			
				if (isSkipFile(normalizedPath)) {
					return NtStatus.FILE_INVALID.getVal();
				}
			
				try {
					final boolean itemExists = fileSystem.doesPathExist(normalizedPath);
					LOGGER.trace("item {} exists? {}", normalizedPath, itemExists);
			
					switch (fileMode) {
			
					case CREATE_NEW: {
						if (itemExists) {
							result = OBJECT_NAME_COLLISION.getVal();
						}
						break;
					}
			
					case OPEN_EXISTING: {
						if (!itemExists) {
							result = ERROR_FILE_NOT_FOUND.getVal();
						}
						break;
					}
			
					case TRUNCATE_EXISTING: {
						if (itemExists) {
							result = ERROR_FILE_NOT_FOUND.getVal();
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
			
					if (result == ErrorCode.SUCCESS.getVal()) {
						final FileAttribute attribute = FileAttribute.fromInt(rawFileAttributes);
						final EnumIntegerSet<FileAttribute> attributes = new EnumIntegerSet<>(attribute);
			
						if (!itemExists) {
							if (isDirectory) {
								fileSystem.createEmptyDirectory(normalizedPath, rawCreateOptions, attributes);
							} else {
								fileSystem.createEmptyFile(normalizedPath, rawCreateOptions, attributes);
							}
						}
					}
			
				} catch (final Throwable t) {
					LOGGER.warn("Caught error: ", t);
					result = DokanyUtils.exceptionToErrorCode(t);
					LOGGER.warn("Set result to {}: ", result);
				}
			
				return result;
			} finally {
				LOGGER.trace("final result for {}: {}", normalizedPath, result);
			}
			*/
		}
	}

	private final class Cleanup implements DokanyOperations.Cleanup {
		@Override
		public void callback(
		        @NotNull final WString path,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {
			if (isSkipFile(path)) {
				return;
			}

			try {
				final String normalizedPath = DokanyUtils.normalize(path);

				// TODO: Can cleanup always be done here not matter the FS?

				CloseFile.callback(path, dokanyFileInfo);
				fileSystem.cleanup(normalizedPath, dokanyFileInfo);

				log.trace("Cleaned up: {}", normalizedPath);
			} catch (final Throwable t) {
				log.warn("Error in clearning up file: {}", path, t);
			}
		}
	}

	private final class CloseFile implements DokanyOperations.CloseFile {
		@Override
		public void callback(
		        @NotNull final WString path,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {
			if (isSkipFile(path)) {
				return;
			}

			try {
				// TODO: Can close always be done here not matter the FS?
				dokanyFileInfo.Context = 0;
				final String normalizedPath = DokanyUtils.normalize(path);
				// fileSystem.close(normalizedPath, dokanyFileInfo);

				log.trace("Closed file: {}", normalizedPath);
			} catch (final Throwable e) {
				log.warn("Error in closing file: {}", path, e);
			}
		}
	}

	private final class FindFiles implements DokanyOperations.FindFiles {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanyOperations.FillWin32FindData rawFillFindData,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {
			return FindFilesWithPattern.callback(path, null, rawFillFindData, dokanyFileInfo);
		}
	}

	private final class FindFilesWithPattern implements DokanyOperations.FindFilesWithPattern {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final WString searchPattern,
		        @NotNull final DokanyOperations.FillWin32FindData rawFillFindData,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String pathToSearch = DokanyUtils.normalize(path);
			log.trace("FindFilesWithPattern {}", pathToSearch);

			try {
				final Set<WIN32_FIND_DATA> filesFound = fileSystem.findFilesWithPattern(pathToSearch, DokanyUtils.wStrToStr(searchPattern));
				log.debug("Found {} paths", filesFound.size());
				log.debug("rawFillFindData: {}", rawFillFindData);
				try {
					filesFound.forEach(file -> {
						log.trace("file in find: {}", file.getFileName());
						rawFillFindData.fillWin32FindData(file, dokanyFileInfo);
					});
				} catch (final Error e) {
					log.warn("Error filling Win32FindData", e);
				}
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class ReadFile implements DokanyOperations.ReadFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final Pointer buffer,
		        final int bufferLength,
		        @NotNull final IntByReference readLengthRef,
		        final long offset,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.debug("ReadFile: {} with readLength ", normalizedPath, bufferLength);

			try {
				final FileData data = fileSystem.read(normalizedPath, (int) offset, bufferLength);
				final int numRead = data.getLength();
				log.debug("numRead: {}", numRead);

				if (numRead > 0) {
					buffer.write(0, data.getBytes(), 0, numRead);
					log.debug("wrote data length: {}", data.getBytes().length);
				}

				readLengthRef.setValue(numRead);
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t, ERROR_READ_FAULT.mask());
			}
			return ErrorCode.SUCCESS.mask();
		}
	}

	private final class WriteFile implements DokanyOperations.WriteFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final Pointer buffer,
		        @NotNull final int numberOfBytesToWrite,
		        @NotNull final IntByReference numberOfBytesWritten,
		        @NotNull final long offset,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.debug("WriteFile: {}", normalizedPath);

			try {
				final byte[] data = new byte[numberOfBytesToWrite];
				buffer.read(0L, data, 0, numberOfBytesToWrite);
				final int written = fileSystem.write(normalizedPath, (int) offset, data, numberOfBytesToWrite);
				numberOfBytesWritten.setValue(written);
				log.debug("Wrote this number of bytes: {}", written);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.mask());
			}
		}
	}

	private final class FlushFileBuffers implements DokanyOperations.FlushFileBuffers {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("FlushFileBuffers: {}", normalizedPath);
			try {
				fileSystem.flushFileBuffers(normalizedPath);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.mask());
			}
		}
	}

	private final class GetFileInformation implements DokanyOperations.GetFileInformation {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final ByHandleFileInfo info,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.debug("GetFileInformation: {}", normalizedPath);

			if (isSkipFile(path)) {
				return NtStatus.FILE_INVALID.mask();
			}
			try {
				final ByHandleFileInfo retrievedInfo = fileSystem.getInfo(normalizedPath);
				retrievedInfo.copyTo(info);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				log.warn("Error reading info: {}", t.getMessage());
				return DokanyUtils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.mask());
			}
		}
	}

	private final class SetFileAttributes implements DokanyOperations.SetFileAttributes {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final int attributes,
		        @NotNull final DokanyFileInfo rawInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			// TODO: fix
			final EnumIntegerSet<FileAttribute> attribs = null;// FileAttribute.fromInt(attributes);
			log.trace("SetFileAttributes as {} for {}", attribs, normalizedPath);

			try {
				fileSystem.setAttributes(normalizedPath, attribs);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.mask());
			}
		}
	}

	private final class SetFileTime implements DokanyOperations.SetFileTime {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final FILETIME creationTime,
		        @NotNull final FILETIME lastAccessTime,
		        @NotNull final FILETIME lastWriteTime,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("SetFileTime for {}; creationTime = {}; lastAccessTime = {}; lastWriteTime = {}", normalizedPath, creationTime, lastAccessTime, lastWriteTime);

			try {
				fileSystem.setTime(DokanyUtils.normalize(path), creationTime, lastAccessTime, lastWriteTime);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t, ERROR_WRITE_FAULT.mask());
			}
		}
	}

	private final class DeleteFile implements DokanyOperations.DeleteFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("DeleteFile: {}", normalizedPath);

			try {
				fileSystem.deleteFile(normalizedPath, dokanyFileInfo);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class DeleteDirectory implements DokanyOperations.DeleteDirectory {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("DeleteDirectory: {}", normalizedPath);

			try {
				fileSystem.deleteDirectory(normalizedPath, dokanyFileInfo);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class MoveFile implements DokanyOperations.MoveFile {
		@Override
		public long callback(
		        @NotNull final WString oldPath,
		        @NotNull final WString newPath,
		        final boolean replaceIfExisting,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String oldNormalizedPath = DokanyUtils.normalize(oldPath);
			final String newNormalizedPath = DokanyUtils.normalize(newPath);
			log.debug("trace: {} to {}; replace existing? ", oldNormalizedPath, newNormalizedPath, replaceIfExisting);

			try {
				fileSystem.move(oldNormalizedPath, newNormalizedPath, replaceIfExisting);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class SetEndOfFile implements DokanyOperations.SetEndOfFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long offset,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("SetEndOfFile: {}", normalizedPath);

			try {
				fileSystem.setEndOfFile(normalizedPath, (int) offset);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class SetAllocationSize implements DokanyOperations.SetAllocationSize {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long length,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("SetAllocationSize: {}", normalizedPath);

			try {
				fileSystem.setAllocationSize(normalizedPath, (int) length);
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class LockFile implements DokanyOperations.LockFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long offset,
		        final long length,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("LockFile: {}", normalizedPath);

			try {
				fileSystem.lock(normalizedPath, (int) offset, (int) length);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class UnlockFile implements DokanyOperations.UnlockFile {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final long offset,
		        final long length,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("UnlockFile: {}", normalizedPath);
			try {
				fileSystem.unlock(normalizedPath, (int) offset, (int) length);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class GetDiskFreeSpace implements DokanyOperations.GetDiskFreeSpace {
		@Override
		public long callback(
		        @NotNull final LongByReference rawFreeBytesAvailable,
		        @NotNull final LongByReference rawTotalNumberOfBytes,
		        @NotNull final LongByReference rawTotalNumberOfFreeBytes,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			log.trace("GetDiskFreeSpace");
			log.trace("rawFreeBytesAvailable: {}", rawFreeBytesAvailable.getValue());
			log.debug("rawTotalNumberOfBytes", rawTotalNumberOfBytes);
			log.debug("rawTotalNumberOfFreeBytes", rawTotalNumberOfFreeBytes);

			// rawTotalNumberOfBytes.setValue(new LONGLONG(freeSpace.getTotalBytes()));

			// These two are the same unless per-user quotas are enabled
			// rawTotalNumberOfFreeBytes.setValue(new LONGLONG(freeSpace.getFreeBytes()));

			// If per-user quotas are being used, this value may be less than the total number of free bytes on a disk
			rawFreeBytesAvailable.setValue(freeSpace.getFreeBytes());
			log.trace("new rawFreeBytesAvailable: {}", rawFreeBytesAvailable.getValue());

			return 0;
		}
	}

	private final class GetVolumeInformation implements DokanyOperations.GetVolumeInformation {
		@Override
		public long callback(
		        @NotNull final Pointer volumeNameBuffer,
		        final int volumeNameSize,
		        @NotNull final IntByReference rawVolumeSerialNumber,
		        @NotNull final IntByReference rawMaximumComponentLength,
		        @NotNull final IntByReference rawFileSystemFlags,
		        @NotNull final Pointer rawFileSystemNameBuffer,
		        final int rawFileSystemNameSize,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			log.trace("GetVolumeInformation");

			try {
				volumeNameBuffer.setWideString(0L, DokanyUtils.trimStrToSize(fileSystem.getVolumeInfo().getName(), volumeNameSize));

				rawVolumeSerialNumber.setValue(fileSystem.getVolumeInfo().getSerialNumber());

				rawMaximumComponentLength.setValue(fileSystem.getVolumeInfo().getMaxComponentLength());

				rawFileSystemFlags.setValue(fileSystem.getVolumeInfo().getFileSystemFeatures().toInt());

				rawFileSystemNameBuffer.setWideString(0L, DokanyUtils.trimStrToSize(fileSystem.getVolumeInfo().getFileSystemName(), rawFileSystemNameSize));

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class Mounted implements DokanyOperations.Mounted {
		@Override
		public long mounted(final DokanyFileInfo dokanyFileInfo) {
			try {
				fileSystem.mounted();
				log.info("Dokany File System mounted");
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class Unmounted implements DokanyOperations.Unmounted {
		@Override
		public long unmounted(final DokanyFileInfo dokanyFileInfo) {

			try {
				fileSystem.unmounted();
				log.info("Dokany File System unmounted");
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class GetFileSecurity implements DokanyOperations.GetFileSecurity {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final int rawRequestedInformation,
		        @NotNull final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        @NotNull final IntByReference rawSecurityDescriptorLengthNeeded,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("SetFileSecurity: {}", normalizedPath);

			try {
				final byte[] out = new byte[rawSecurityDescriptorLength];
				final int expectedLength = fileSystem.getSecurity(normalizedPath, rawRequestedInformation, out);
				rawSecurityDescriptor.write(0L, out, 0, rawSecurityDescriptorLength);
				rawSecurityDescriptorLengthNeeded.setValue(expectedLength);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class SetFileSecurity implements DokanyOperations.SetFileSecurity {
		@Override
		public long callback(
		        @NotNull final WString path,
		        final int rawSecurityInformation,
		        @NotNull final Pointer rawSecurityDescriptor,
		        final int rawSecurityDescriptorLength,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("SetFileSecurity: {}", normalizedPath);

			try {
				final byte[] data = new byte[rawSecurityDescriptorLength];
				rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
				fileSystem.setSecurity(normalizedPath, rawSecurityInformation, data);

				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private final class FindStreams implements DokanyOperations.FindStreams {
		@Override
		public long callback(
		        @NotNull final WString path,
		        @NotNull final FillWin32FindStreamData rawFillFindData,
		        @NotNull final DokanyFileInfo dokanyFileInfo) {

			final String normalizedPath = DokanyUtils.normalize(path);
			log.trace("FindStreams: {}", normalizedPath);

			try {
				final Set<Win32FindStreamData> streams = fileSystem.findStreams(normalizedPath);
				log.debug("Found {} streams", streams.size());
				streams.forEach(file -> {
					rawFillFindData.callback(file, dokanyFileInfo);
				});
				return ErrorCode.SUCCESS.mask();
			} catch (final Throwable t) {
				return DokanyUtils.exceptionToErrorCode(t);
			}
		}
	}

	private static int getRootReturnCode(final String normalizedPath, final CreationDisposition fileMode) {
		switch (fileMode) {
		case CREATE_NEW:
		case CREATE_ALWAYS: {
			return ERROR_ALREADY_EXISTS.mask();
		}
		case OPEN_EXISTING:
		case OPEN_ALWAYS: {
			return ErrorCode.SUCCESS.mask();
		}
		case TRUNCATE_EXISTING: {
			return ERROR_NOT_SUPPORTED.mask();
		}
		}
		return ErrorCode.SUCCESS.mask();
	}

	static boolean isSkipFile(
	        @NotNull final WString path) {
		return isSkipFile(DokanyUtils.normalize(path));

	}

	static boolean isSkipFile(
	        @NotNull final String normalizedPath) {

		boolean toReturn = false;

		final String pathLowerCase = normalizedPath.toLowerCase();

		if (pathLowerCase.endsWith("desktop.ini")
		        || pathLowerCase.endsWith("autorun.inf")
		        || pathLowerCase.endsWith("folder.jpg")
		        || pathLowerCase.endsWith("folder.gif")) {
			log.trace("Skipping file: " + pathLowerCase);
			toReturn = true;
		}
		// TODO: change back to toReturn
		return toReturn;
		// return false;
	}

}