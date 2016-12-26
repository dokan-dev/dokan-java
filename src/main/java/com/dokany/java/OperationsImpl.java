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
 * @param <TItem>
 */
abstract class OperationsImpl<TItem> extends Operations {
	final FileSystem<TItem> fs;

	OperationsImpl(final FileSystem<TItem> fs) {
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
		public long callback(final WString rawFileName,
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

				if (isDefaultLog()) {
					System.out.println("Dokany File System mounted");
				}
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

				if (isDefaultLog()) {
					System.out.println("Dokany File System unmounted");
				}

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
			System.out.println("Cleanup");

			if (isSkipFile(fileName)) {
				return;
			}

			try {
				fs.cleanup(getHandle(fileName, rawFileInfo._context));

				if (isDefaultLog()) {
					System.out.println("Cleaned up: " + fileName);
				}

			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class CloseFile implements Operations.CloseFileDelegate {
		@Override
		public void callback(final WString fileName, final FileInfoRaw rawFileInfo) {
			System.out.println("CloseFile");

			if (isSkipFile(fileName)) {
				return;
			}

			try {
				fs.close(getHandle(fileName, rawFileInfo._context));

				if (isDefaultLog()) {
					System.out.println("Closed file: " + fileName.toString());
				}
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

			try {
				volumeNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getVolumeName(), volumeNameSize));
				System.out.println("Volume name: " + fs.getVolumeName());

				rawFileSystemNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getFileSystemName(), rawFileSystemNameSize));
				System.out.println("File system name: " + fs.getFileSystemName());

				rawVolumeSerialNumber.setValue(fs.getVolumeSerialNumber());
				System.out.println("Serial number: " + fs.getVolumeSerialNumber());

				rawMaximumComponentLength.setValue(fs.getMaxComponentLength());
				System.out.println("Max component length: " + fs.getMaxComponentLength());

				rawFileSystemFlags.setValue(fs.getFileSystemFeatures());
				System.out.println("File system features: " + FileSystemFeatures.fromInt(fs.getFileSystemFeatures()));

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

			System.out.println("GetDiskFreeSpace");

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

			System.out.println("FindFiles");

			try {
				fs.findFiles(getHandle(fileName, rawFileInfo._context), file -> rawFillFindData.callback(file.toWin32FindData(), rawFileInfo));

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class FindFilesWithPattern implements Operations.FindFilesWithPatternDelegate {
		@Override
		public long callback(final WString fileName,
		        final WString searchPattern,
		        final Operations.FillWin32FindData rawFillFindData,
		        final FileInfoRaw rawFileInfo) {

			System.out.println("FindFilesWithPattern: " + searchPattern.toString());

			try {
				find(getHandle(fileName, rawFileInfo._context), FileSystems.getDefault().getPathMatcher(GLOB + searchPattern.toString()), fileInfo -> {
					System.out.println("EMIT: " + fileInfo.fileName);
					rawFillFindData.callback(fileInfo.toWin32FindData(), rawFileInfo);
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

			System.out.println("ReadFile");

			try {
				final byte[] data = new byte[bufferLength];
				final int read = fs.read(getHandle(fileName, rawFileInfo._context), offset, data, bufferLength);
				buffer.write(0, data, 0, read);
				readLength.setValue(read);

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

			System.out.println("WriteFile");

			try {
				final byte[] data = new byte[numberOfBytesToWrite];
				buffer.read(0L, data, 0, numberOfBytesToWrite);
				final int written = fs.write(getHandle(fileName, rawFileInfo._context), offset, data, numberOfBytesToWrite);
				numberOfBytesWritten.setValue(written);

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

			System.out.println("FlushFileBuffers");

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
		        final ByHandleFileInformation handleFileInfo,
		        final FileInfoRaw rawFileInfo) {
			System.out.println("GetFileInformation: " + fileName);

			if (isSkipFile(fileName)) {
				return FileInvalid.val;
			}

			try {
				final FileHandle<TItem> handle = getHandle(fileName, rawFileInfo._context);
				final int numberOfLinks = 1;
				final int volumeSerialNumber = fs.getVolumeSerialNumber();
				final ByHandleFileInformation bhfi;
				final FileInfo fi;

				if (fileName.toString().equals("\\")) {
					fi = new FileInfo.Builder(fileName)
					        .attributes(FILE_ATTRIBUTE_NORMAL, FILE_ATTRIBUTE_DIRECTORY)
					        .creationTime(fs.getRootCreateDate())
					        .lastWriteTime(fs.getRootCreateDate())
					        .buildFileInfo();
				} else {
					fi = fs.getFileInformation(handle);

				}
				bhfi = fi.toByHandleFileInformation(numberOfLinks, volumeSerialNumber);
				handleFileInfo.copyTo(bhfi);

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

			System.out.println("SetFileAttributes");

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

			System.out.println("SetFileTime");

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

			System.out.println("FindStreams");

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

			System.out.println("GetFileSecurity");

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

			System.out.println("SetFileSecurity");

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

			System.out.println("DeleteFile");

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

			System.out.println("DeleteDirectory");

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

			System.out.println("MoveFile");

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
			System.out.println("SetEndOfFile");
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
			System.out.println("SetAllocationSize");
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

			System.out.println("LockFile");

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

			System.out.println("UnlockFile");

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

		t.printStackTrace();
		return defaultCode;
	}

	private boolean isSkipFile(final WString fileName) {
		boolean toReturn = false;

		System.out.println("fileName: " + fileName.toString());

		if (// fileName.toString().equals("\\") ||
		fileName.toString().equals("\\desktop.ini")) {
			System.out.println("Skipping file: " + fileName);
			toReturn = true;
		}
		return toReturn;
	}

	private FileHandle<TItem> getHandle(final WString fileName, final long id) throws IOException {
		return fs.getHandle(fileName, id);
	}

	private boolean isDefaultLog() {
		return fs.isDefaultLog();
	}

	public void find(final FileHandle<TItem> handle, final PathMatcher pathMatcher, final FileEmitter emitter) throws IOException {
		fs.findFiles(handle, file -> {
			if (pathMatcher.matches(Paths.get(file.fileName))) {
				emitter.emit(file);
			}
		});
	}

	private final static String GLOB = "glob:";
}
