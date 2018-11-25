package com.dokan.java;

import com.dokan.java.structure.ByHandleFileInformation;
import com.dokan.java.structure.DokanFileInfo;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public class DokanyFileSystemStub extends AbstractDokanyFileSystem{

    public DokanyFileSystemStub(FileSystemInformation fileSystemInformation, boolean usesKernelFlagsAndCodes) {
        super(fileSystemInformation, usesKernelFlagsAndCodes);
    }

    @Override
    @NotImplemented
    public int zwCreateFile(WString rawPath, WinBase.SECURITY_ATTRIBUTES securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public void cleanup(WString rawPath, DokanFileInfo dokanFileInfo) {

    }

    @Override
    @NotImplemented
    public void closeFile(WString rawPath, DokanFileInfo dokanFileInfo) {

    }

    @Override
    @NotImplemented
    public int readFile(WString rawPath, Pointer rawBuffer, int rawBufferLength, IntByReference rawReadLength, long rawOffset, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int writeFile(WString rawPath, Pointer rawBuffer, int rawNumberOfBytesToWrite, IntByReference rawNumberOfBytesWritten, long rawOffset, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int flushFileBuffers(WString rawPath, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int getFileInformation(WString fileName, ByHandleFileInformation handleFileInfo, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int findFiles(WString rawPath, DokanyOperations.FillWin32FindData rawFillFindData, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int findFilesWithPattern(WString fileName, WString searchPattern, DokanyOperations.FillWin32FindData rawFillFindData, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int setFileAttributes(WString rawPath, int rawAttributes, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int setFileTime(WString rawPath, WinBase.FILETIME rawCreationTime, WinBase.FILETIME rawLastAccessTime, WinBase.FILETIME rawLastWriteTime, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int deleteFile(WString rawPath, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int deleteDirectory(WString rawPath, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int moveFile(WString rawPath, WString rawNewFileName, boolean rawReplaceIfExisting, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int setEndOfFile(WString rawPath, long rawByteOffset, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int setAllocationSize(WString rawPath, long rawLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int lockFile(WString rawPath, long rawByteOffset, long rawLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int unlockFile(WString rawPath, long rawByteOffset, long rawLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int getDiskFreeSpace(LongByReference freeBytesAvailable, LongByReference totalNumberOfBytes, LongByReference totalNumberOfFreeBytes, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int getVolumeInformation(Pointer rawVolumeNameBuffer, int rawVolumeNameSize, IntByReference rawVolumeSerialNumber, IntByReference rawMaximumComponentLength, IntByReference rawFileSystemFlags, Pointer rawFileSystemNameBuffer, int rawFileSystemNameSize, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int mounted(DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int unmounted(DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int getFileSecurity(WString rawPath, int rawSecurityInformation, Pointer rawSecurityDescriptor, int rawSecurityDescriptorLength, IntByReference rawSecurityDescriptorLengthNeeded, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public int setFileSecurity(WString rawPath, int rawSecurityInformation, Pointer rawSecurityDescriptor, int rawSecurityDescriptorLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    @NotImplemented
    public void fillWin32FindData(WinBase.WIN32_FIND_DATA rawFillFindData, DokanFileInfo dokanFileInfo) {

    }

    @Override
    @NotImplemented
    public int findStreams(WString rawPath, DokanyOperations.FillWin32FindStreamData rawFillFindData, DokanFileInfo dokanFileInfo) {
        return 0;
    }
}
