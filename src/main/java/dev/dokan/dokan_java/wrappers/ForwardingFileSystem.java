package dev.dokan.dokan_java.wrappers;


import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import dev.dokan.dokan_java.AbstractDokanFileSystem;
import dev.dokan.dokan_java.DokanException;
import dev.dokan.dokan_java.DokanOperations;
import dev.dokan.dokan_java.FileSystemInformation;
import dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes;
import dev.dokan.dokan_java.structure.ByHandleFileInformation;
import dev.dokan.dokan_java.structure.DokanFileInfo;
import dev.dokan.dokan_java.structure.DokanIOSecurityContext;

import java.nio.file.Path;
import java.util.Set;


public class ForwardingFileSystem extends AbstractDokanFileSystem {

    private final Set<String> notImplementedMethods;
    private final AbstractEasyDokanFileSystem creator;

    public ForwardingFileSystem(FileSystemInformation fileSystemInformation, Set<String> notImplementedMethods, AbstractEasyDokanFileSystem target) {
        super(fileSystemInformation);
        this.notImplementedMethods = notImplementedMethods;
        this.creator = target;
    }

    @Override
    public boolean isImplemented(String funcName) {
        return !this.notImplementedMethods.contains(funcName);
    }


    @Override
    public int zwCreateFile(WString rawPath, DokanIOSecurityContext securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public void cleanup(WString rawPath, DokanFileInfo dokanFileInfo) {

    }

    @Override
    public void closeFile(WString rawPath, DokanFileInfo dokanFileInfo) {

    }

    @Override
    public int readFile(WString rawPath,
                        Pointer rawBuffer,
                        int rawBufferLength,
                        IntByReference rawReadLength,
                        long rawOffset,
                        DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int writeFile(WString rawPath,
                         Pointer rawBuffer,
                         int rawNumberOfBytesToWrite,
                         IntByReference rawNumberOfBytesWritten,
                         long rawOffset,
                         DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int flushFileBuffers(WString rawPath, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int getFileInformation(WString fileName,
                                  ByHandleFileInformation handleFileInfo,
                                  DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int findFiles(WString rawPath,
                         DokanOperations.FillWin32FindData rawFillFindData,
                         DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int findFilesWithPattern(WString fileName,
                                    WString searchPattern,
                                    DokanOperations.FillWin32FindData rawFillFindData,
                                    DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int setFileAttributes(WString rawPath, int rawAttributes, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int setFileTime(WString rawPath,
                           WinBase.FILETIME rawCreationTime,
                           WinBase.FILETIME rawLastAccessTime,
                           WinBase.FILETIME rawLastWriteTime,
                           DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int deleteFile(WString rawPath, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int deleteDirectory(WString rawPath, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int moveFile(WString rawPath,
                        WString rawNewFileName,
                        boolean rawReplaceIfExisting,
                        DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int setEndOfFile(WString rawPath, long rawByteOffset, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int setAllocationSize(WString rawPath, long rawLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int lockFile(WString rawPath, long rawByteOffset, long rawLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int unlockFile(WString rawPath, long rawByteOffset, long rawLength, DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int getDiskFreeSpace(LongByReference freeBytesAvailable,
                                LongByReference totalNumberOfBytes,
                                LongByReference totalNumberOfFreeBytes,
                                DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int getVolumeInformation(Pointer rawVolumeNameBuffer,
                                    int rawVolumeNameSize,
                                    IntByReference rawVolumeSerialNumber,
                                    IntByReference rawMaximumComponentLength,
                                    IntByReference rawFileSystemFlags,
                                    Pointer rawFileSystemNameBuffer,
                                    int rawFileSystemNameSize,
                                    DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int mounted(DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int unmounted(DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int getFileSecurity(WString rawPath,
                               int rawSecurityInformation,
                               Pointer rawSecurityDescriptor,
                               int rawSecurityDescriptorLength,
                               IntByReference rawSecurityDescriptorLengthNeeded,
                               DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public int setFileSecurity(WString rawPath,
                               int rawSecurityInformation,
                               Pointer rawSecurityDescriptor,
                               int rawSecurityDescriptorLength,
                               DokanFileInfo dokanFileInfo) {
        return 0;
    }

    @Override
    public void fillWin32FindData(WinBase.WIN32_FIND_DATA rawFillFindData, DokanFileInfo dokanFileInfo) {

    }

    @Override
    public int findStreams(WString rawPath,
                           DokanOperations.FillWin32FindStreamData rawFillFindData,
                           DokanFileInfo dokanFileInfo) {
        return 0;
    }

    private int handleException(DokanException exc) { //TODO Reformat to allow any kind of exception
        if (exc == null) {
            return Win32ErrorCodes.ERROR_GEN_FAILURE;
        }

        if (exc.getErrorCode() == Integer.MIN_VALUE) {
            //TODO
        }
        //TODO Log
        return exc.getErrorCode();
    }

    private String resolveRelativePath(WString path) {
        return resolveRelativePath(path.toString());
    }

    private String resolveRelativePath(String path) {
        return path;
    }

    private Path resolveAbsolutePath(WString path) {
        return resolveAbsolutePath(path.toString());
    }

    private Path resolveAbsolutePath(String path) {
        return null; //TODO Implement
    }
}