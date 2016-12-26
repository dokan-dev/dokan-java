package com.dokany.java;

import static com.dokany.java.constants.CreationDisposition.CREATE_NEW;
import static com.dokany.java.constants.CreationDisposition.TRUNCATE_EXISTING;
import static com.dokany.java.constants.ErrorCodes.ERROR_ALREADY_EXISTS;
import static com.dokany.java.constants.ErrorCodes.ERROR_FILE_NOT_FOUND;
import static com.dokany.java.constants.ErrorCodes.ERROR_READ_FAULT;
import static com.dokany.java.constants.ErrorCodes.ERROR_SUCCESS;
import static com.dokany.java.constants.ErrorCodes.ERROR_WRITE_FAULT;
import static com.dokany.java.constants.ErrorCodes.ObjectNameCollision;
import static com.dokany.java.constants.NtStatus.FileInvalid;
import static com.dokany.java.constants.NtStatus.Success;
import static com.dokany.java.constants.NtStatus.Unsuccessful;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;

import com.dokany.java.FileSystem.OpenFileResult;
import com.dokany.java.structure.ByHandleFileInformation;
import com.dokany.java.structure.FileInfoRaw;
import com.dokany.java.structure.FileTime;
import com.dokany.java.structure.Win32FindData;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

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

			// if (rawFileName.toString().equals("\\") ||
			// rawFileName.toString().equals("\\desktop.ini")) {
			// return FileInvalid.val;
			// }

			long res = -1;
			final boolean isDirectory = dokanFileInfo._isDirectory != 0;
			System.out.println("--------------------------------------------");
			try {
				System.out.println("%%%%%%%%%%%%%% CREATEFILE: rawFileName=" + rawFileName.toString() + " : rawDesiredAccess=" + rawDesiredAccess
				        + " : rawFileAttributes=" + rawFileAttributes + " : rawShareAccess=" + rawShareAccess
				        + " : rawCreateDisposition=" + rawCreateDisposition + " : rawCreateOptions=" + rawCreateOptions + " : isDirectory=" + isDirectory);
				final OpenFileResult<TItem> result = fs.createFile(rawFileName.toString(), securityContext.getValue(),
				        rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, isDirectory);

				System.out.println("result: " + result);
				System.out.println("dokanFileInfo: " + dokanFileInfo);

				dokanFileInfo._context = FileSystem.fileHandles.allocateFileHandle(result.handle);
				dokanFileInfo.write();

				// This is a directory
				if (isDirectory) {
					if (result.exists) {
						res = ERROR_SUCCESS.val;
					} else {
						res = ERROR_FILE_NOT_FOUND.val;
					}
				} else {
					// This is a file
					if ((rawCreateDisposition == CREATE_NEW.val) || (rawCreateDisposition == TRUNCATE_EXISTING.val)) {
						if (result.exists) {
							res = ObjectNameCollision.val;
						} else {
							res = ERROR_SUCCESS.val;
						}

					} else {
						if (result.exists) {
							res = ERROR_ALREADY_EXISTS.val;
						} else {
							res = ERROR_FILE_NOT_FOUND.val;
						}
					}
				}
			} catch (final FileAlreadyExistsException t) {
				if (rawCreateDisposition == CREATE_NEW.val) {
					res = ObjectNameCollision.val;
				}
				res = ERROR_SUCCESS.val;
			} catch (final Throwable t) {
				res = exceptionToErrorCode(t);
			}

			if (res < 0) {
				System.out.println("TEST");
			}

			System.out.println(" CREATEFILE -> " + res);

			return res;
		}

	}

	private class Mounted implements Operations.MountedDelegate {
		@Override
		public long callback(final FileInfoRaw rawFileInfo) {
			System.out.println("Mounted");
			try {
				fs.mounted();

				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	private class Unmounted implements Operations.UnmountedDelegate {
		@Override
		public long callback(final FileInfoRaw rawFileInfo) {
			System.out.println("Unmounted");
			try {
				fs.unmounted();

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

			System.out.println("Cleanup: " + fileName + " : " + rawFileInfo);

			try {
				fs.cleanup(fs.getFileHandle(fileName, rawFileInfo._context));
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private class CloseFile implements Operations.CloseFileDelegate {
		@Override
		public void callback(final WString fileName, final FileInfoRaw rawFileInfo) {

			System.out.println("CloseFile");

			try {
				fs.closeFile(fs.getFileHandle(fileName, rawFileInfo._context));
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

			System.out.println("GetVolumeInformation");

			try {
				volumeNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getVolumeName(), volumeNameSize));
				rawFileSystemNameBuffer.setWideString(0L, Utils.trimStrToSize(fs.getFileSystemName(), rawFileSystemNameSize));
				rawVolumeSerialNumber.setValue(fs.getSerialNumber());
				rawMaximumComponentLength.setValue(fs.getMaxComponentLength());
				rawFileSystemFlags.setValue(fs.getFileSystemFeatures());

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
		public long callback(final WString fileName, final Operations.FillWin32FindData rawFillFindData, final FileInfoRaw rawFileInfo) {

			System.out.println("FindFiles");

			try {
				fs.findFiles(fs.getFileHandle(fileName, rawFileInfo._context), file -> rawFillFindData.callback(file.toWin32FindData(), rawFileInfo));

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

			System.out.println("FindFilesWithPattern");

			try {
				fs.findFiles(fs.getFileHandle(fileName, rawFileInfo._context), FileSystems.getDefault().getPathMatcher(searchPattern.toString()), handle -> {
					System.out.println("EMIT: " + handle.fileName);
					rawFillFindData.callback(handle.toWin32FindData(), rawFileInfo);
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
				final int read = fs.readFile(fs.getFileHandle(fileName, rawFileInfo._context), offset, data, bufferLength);
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
				final int written = fs.writeFile(fs.getFileHandle(fileName, rawFileInfo._context), offset, data, numberOfBytesToWrite);
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
				fs.flushFileBuffers(fs.getFileHandle(fileName, rawFileInfo._context));

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

			System.out.println("GetFileInformation");

			if (fileName.toString().equals("\\") || fileName.toString().equals("\\desktop.ini")) {
				return FileInvalid.val;
			}

			try {
				fs.getFileInformation(fs.getFileHandle(fileName, rawFileInfo._context));

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
				fs.setFileAttributes(fs.getFileHandle(fileName, rawFileInfo._context), attributes);

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
				fs.setFileTime(fs.getFileHandle(fileName, rawFileInfo._context), creationTime.getDate(), lastAccessTime.getDate(), lastWriteTime.getDate());

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
				fs.findStreams(fs.getFileHandle(fileName, rawFileInfo._context), stream -> fill.callback(stream.toStruct(), rawFileInfo));

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
				final int expectedLength = fs.getFileSecurity(fs.getFileHandle(fileName, rawFileInfo._context), rawRequestedInformation, out);
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
				fs.setFileSecurity(fs.getFileHandle(fileName, rawFileInfo._context), rawSecurityInformation, data);

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
				fs.deleteFile(fs.getFileHandle(fileName, rawFileInfo._context));

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
				fs.deleteDirectory(fs.getFileHandle(fileName, rawFileInfo._context));

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
				fs.moveFile(fs.getFileHandle(oldFileName, rawFileInfo._context), fs.getFileHandle(newFileName, rawFileInfo._context), replaceIfExisting);

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
				fs.setEndOfFile(fs.getFileHandle(fileName, rawFileInfo._context), byteOffset);
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
				fs.setAllocationSize(fs.getFileHandle(fileName, rawFileInfo._context), length);
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
				fs.lockFile(fs.getFileHandle(fileName, rawFileInfo._context), byteOffset, length);

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
				fs.unlockFile(fs.getFileHandle(fileName, rawFileInfo._context), byteOffset, length);
				return Success.val;
			} catch (final Throwable t) {
				return exceptionToErrorCode(t);
			}
		}
	}

	protected static class Win32FindStreamData extends Structure implements Operations.Win32FindStreamData {
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
}
