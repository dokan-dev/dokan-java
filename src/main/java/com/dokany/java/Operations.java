package com.dokany.java;

import java.util.Arrays;
import java.util.List;

import com.dokany.java.structure.ByHandleFileInformation;
import com.dokany.java.structure.FileInfoRaw;
import com.dokany.java.structure.FileTime;
import com.dokany.java.structure.Win32FindData;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public class Operations extends Structure implements Structure.ByReference {
	interface ZwCreateFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        IntByReference securityContext,
		        int rawDesiredAccess,
		        int rawFileAttributes,
		        int rawShareAccess,
		        int rawCreateDisposition,
		        int rawCreateOptions,
		        FileInfoRaw dokanFileInfo);
	}

	interface CleanupDelegate extends Callback {
		void callback(WString rawFileName,
		        FileInfoRaw rawFileInfo);
	}

	interface CloseFileDelegate extends Callback {
		void callback(WString rawFileName,
		        FileInfoRaw rawFileInfo);
	}

	interface ReadFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        Pointer rawBuffer,
		        int rawBufferLength,
		        IntByReference rawReadLength,
		        long rawOffset,
		        FileInfoRaw rawFileInfo);
	}

	interface WriteFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        Pointer rawBuffer,
		        int rawNumberOfBytesToWrite,
		        IntByReference rawNumberOfBytesWritten,
		        long rawOffset,
		        FileInfoRaw rawFileInfo);

	}

	interface FlushFileBuffersDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        FileInfoRaw rawFileInfo);
	}

	interface GetFileInformationDelegate extends Callback {
		long callback(
		        WString fileName,
		        ByHandleFileInformation handleFileInfo,
		        FileInfoRaw fileInfo);
	}

	interface FindFilesDelegate extends Callback {
		long callback(WString rawFileName,
		        FillWin32FindData rawFillFindData,
		        FileInfoRaw rawFileInfo);
	}

	interface FillWin32FindData extends Callback {
		void callback(Win32FindData rawFillFindData,
		        FileInfoRaw rawFileInfo);
	}

	interface FillWin32FindStreamData extends Callback {
		void callback(Win32FindStreamData rawFillFindData,
		        FileInfoRaw rawFileInfo);
	}

	interface FindFilesWithPatternDelegate extends Callback {
		long callback(
		        WString fileName,
		        WString searchPattern,
		        Operations.FillWin32FindData rawFillFindData,
		        FileInfoRaw rawFileInfo);
	}

	interface SetFileAttributesDelegate extends Callback {
		long callback(WString rawFileName,
		        int rawAttributes,
		        FileInfoRaw rawFileInfo);
	}

	interface SetFileTimeDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        FileTime rawCreationTime,
		        FileTime rawLastAccessTime,
		        FileTime rawLastWriteTime,
		        FileInfoRaw rawFileInfo);
	}

	interface DeleteFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        FileInfoRaw rawFileInfo);
	}

	interface DeleteDirectoryDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        FileInfoRaw rawFileInfo);
	}

	interface MoveFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        WString rawNewFileName,
		        boolean rawReplaceIfExisting,
		        FileInfoRaw rawFileInfo);
	}

	interface SetEndOfFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        long rawByteOffset,
		        FileInfoRaw rawFileInfo);
	}

	interface SetAllocationSizeDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        long rawLength,
		        FileInfoRaw rawFileInfo);
	}

	interface LockFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        long rawByteOffset,
		        long rawLength,
		        FileInfoRaw rawFileInfo);
	}

	interface UnlockFileDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        long rawByteOffset,
		        long rawLength,
		        FileInfoRaw rawFileInfo);
	}

	interface GetDiskFreeSpaceDelegate extends Callback {
		long callback(
		        LongByReference rawFreeBytesAvailable,
		        LongByReference rawTotalNumberOfBytes,
		        LongByReference rawTotalNumberOfFreeBytes,
		        FileInfoRaw rawFileInfo);
	}

	interface GetVolumeInformationDelegate extends Callback {
		long callback(
		        Pointer rawVolumeNameBuffer,
		        int rawVolumeNameSize,
		        IntByReference rawVolumeSerialNumber,
		        IntByReference rawMaximumComponentLength,
		        IntByReference /* FileSystemFeatures */ rawFileSystemFlags,
		        Pointer rawFileSystemNameBuffer,
		        int rawFileSystemNameSize,
		        FileInfoRaw rawFileInfo);
	}

	interface GetFileSecurityDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        int /* SecurityInformation */ rawRequestedInformation,
		        Pointer rawSecurityDescriptor,
		        int rawSecurityDescriptorLength,
		        IntByReference rawSecurityDescriptorLengthNeeded,
		        FileInfoRaw rawFileInfo);
	}

	interface SetFileSecurityDelegate extends Callback {
		long callback(
		        WString rawFileName,
		        int rawSecurityInformation, // @TODO: This is a pointer??
		        Pointer rawSecurityDescriptor,
		        int rawSecurityDescriptorLength,
		        FileInfoRaw rawFileInfo);
	}

	interface FindStreamsDelegate extends Callback {
		long callback(WString rawFileName,
		        FillWin32FindStreamData rawFillFindData,
		        FileInfoRaw rawFileInfo);
	}

	interface MountedDelegate extends Callback {
		long callback(FileInfoRaw rawFileInfo);
	}

	interface UnmountedDelegate extends Callback {
		long callback(final FileInfoRaw rawFileInfo);
	}

	interface Win32FindStreamData {
		public void length(long val);

		public char[] cFileName();
	}

	public ZwCreateFileDelegate ZwCreateFile;
	public CleanupDelegate Cleanup;
	public CloseFileDelegate CloseFile;
	public ReadFileDelegate ReadFile;
	public WriteFileDelegate WriteFile;
	public FlushFileBuffersDelegate FlushFileBuffers;
	public GetFileInformationDelegate GetFileInformation;
	public FindFilesDelegate FindFiles;
	public FindFilesWithPatternDelegate FindFilesWithPattern;
	public SetFileAttributesDelegate SetFileAttributes;
	public SetFileTimeDelegate SetFileTime;
	public DeleteFileDelegate DeleteFile;
	public DeleteDirectoryDelegate DeleteDirectory;
	public MoveFileDelegate MoveFile;
	public SetEndOfFileDelegate SetEndOfFile;
	public SetAllocationSizeDelegate SetAllocationSize;
	public LockFileDelegate LockFile;
	public UnlockFileDelegate UnlockFile;
	public GetDiskFreeSpaceDelegate GetDiskFreeSpace;
	public GetVolumeInformationDelegate GetVolumeInformation;
	public MountedDelegate Mounted;
	public UnmountedDelegate Unmounted;
	public GetFileSecurityDelegate GetFileSecurity;
	public SetFileSecurityDelegate SetFileSecurity;
	public FindStreamsDelegate FindStreams;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
		        "ZwCreateFile",
		        "Cleanup",
		        "CloseFile",
		        "ReadFile",
		        "WriteFile",
		        "FlushFileBuffers",
		        "GetFileInformation",
		        "FindFiles",
		        "FindFilesWithPattern",
		        "SetFileAttributes",
		        "SetFileTime",
		        "DeleteFile",
		        "DeleteDirectory",
		        "MoveFile",
		        "SetEndOfFile",
		        "SetAllocationSize",
		        "LockFile",
		        "UnlockFile",
		        "GetDiskFreeSpace",
		        "GetVolumeInformation",
		        "Mounted",
		        "Unmounted",
		        "GetFileSecurity",
		        "SetFileSecurity",
		        "FindStreams");
	}
}
