package com.github.dokandev.dokanjava;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import java.util.Arrays;
import java.util.List;

//@SuppressWarnings({"unused", "WeakerAccess"})
public class DOKAN_OPERATIONS extends Structure implements Structure.ByReference {
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
    protected List getFieldOrder() {
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
                "FindStreams"
        );
    }

    interface ZwCreateFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                IntByReference securityContext,
                int rawDesiredAccess,
                int rawFileAttributes,
                int rawShareAccess,
                int rawCreateDisposition,
                int rawCreateOptions,
                DokanFileInfo dokanFileInfo
        );
    }

    interface CleanupDelegate extends Callback {
        void callback(WString rawFileName, DokanFileInfo rawFileInfo);
    }

    interface CloseFileDelegate extends Callback {
        void callback(WString rawFileName, DokanFileInfo rawFileInfo);
    }

    interface ReadFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                byte[] rawBuffer,
                int rawBufferLength,
                IntByReference rawReadLength,
                long rawOffset,
                DokanFileInfo rawFileInfo
        );
    }

    interface WriteFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                byte[] rawBuffer,
                int rawNumberOfBytesToWrite,
                IntByReference rawNumberOfBytesWritten,
                long rawOffset,
                DokanFileInfo rawFileInfo
        );

    }

    interface FlushFileBuffersDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                DokanFileInfo rawFileInfo
        );
    }

    interface GetFileInformationDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString fileName,
                BY_HANDLE_FILE_INFORMATION handleFileInfo,
                DokanFileInfo fileInfo
        );
    }

    interface FindFilesDelegate extends Callback {
        long /*NtStatus*/ callback(WString rawFileName, IntByReference rawFillFindData, DokanFileInfo rawFileInfo);
    }

    interface FindFilesWithPatternDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                WString rawSearchPattern,
                IntByReference rawFillFindData,
                DokanFileInfo rawFileInfo
        );
    }

    interface SetFileAttributesDelegate extends Callback {
        long /*NtStatus*/ callback(WString rawFileName, int rawAttributes, DokanFileInfo rawFileInfo);
    }

    interface SetFileTimeDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                FILETIME.REF rawCreationTime,
                FILETIME.REF rawLastAccessTime,
                FILETIME.REF rawLastWriteTime,
                DokanFileInfo rawFileInfo
        );
    }


    interface DeleteFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                DokanFileInfo rawFileInfo
        );
    }


    interface DeleteDirectoryDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                DokanFileInfo rawFileInfo
        );
    }

    interface MoveFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                WString rawNewFileName,
                boolean rawReplaceIfExisting,
                DokanFileInfo rawFileInfo
        );
    }

    interface SetEndOfFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName, long rawByteOffset,
                DokanFileInfo rawFileInfo
        );
    }

    interface SetAllocationSizeDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName, long rawLength,
                DokanFileInfo rawFileInfo
        );
    }

    interface LockFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName, long rawByteOffset, long rawLength,
                DokanFileInfo rawFileInfo
        );
    }

    interface UnlockFileDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName, long rawByteOffset, long rawLength,
                DokanFileInfo rawFileInfo
        );
    }

    interface GetDiskFreeSpaceDelegate extends Callback {
        long /*NtStatus*/ callback(
                LongByReference rawFreeBytesAvailable, LongByReference rawTotalNumberOfBytes, LongByReference rawTotalNumberOfFreeBytes,
                DokanFileInfo rawFileInfo
        );
    }

    interface GetVolumeInformationDelegate extends Callback {
        long /*NtStatus*/ callback(
                Pointer rawVolumeNameBuffer,
                int rawVolumeNameSize,
                IntByReference rawVolumeSerialNumber,
                IntByReference rawMaximumComponentLength,
                IntByReference /*FileSystemFeatures*/ rawFileSystemFlags,
                Pointer rawFileSystemNameBuffer,
                int rawFileSystemNameSize,
                DokanFileInfo rawFileInfo
        );
    }

    interface GetFileSecurityDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                int /*SECURITY_INFORMATION*/ rawRequestedInformation,
                IntByReference rawSecurityDescriptor,
                int rawSecurityDescriptorLength,
                IntByReference rawSecurityDescriptorLengthNeeded,
                DokanFileInfo rawFileInfo
        );
    }

    interface SetFileSecurityDelegate extends Callback {
        long /*NtStatus*/ callback(
                WString rawFileName,
                int rawSecurityInformation, // @TODO: This is a pointer??
                IntByReference rawSecurityDescriptor,
                int rawSecurityDescriptorLength,
                DokanFileInfo rawFileInfo
        );
    }

    interface FindStreamsDelegate extends Callback {
        long /*NtStatus*/ callback(WString rawFileName, IntByReference rawFillFindData, DokanFileInfo rawFileInfo);
    }

    interface MountedDelegate extends Callback {
        long /*NtStatus*/ callback(DokanFileInfo rawFileInfo);
    }

    interface UnmountedDelegate extends Callback {
        long /*NtStatus*/ callback(DokanFileInfo rawFileInfo);
    }
}
