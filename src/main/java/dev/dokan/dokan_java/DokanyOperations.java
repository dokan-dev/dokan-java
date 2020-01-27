package dev.dokan.dokan_java;

import java.util.Arrays;
import java.util.List;

import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.constants.microsoft.NtStatuses;
import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;
import dev.dokan.dokan_java.structure.ByHandleFileInformation;
import dev.dokan.dokan_java.structure.DokanFileInfo;
import dev.dokan.dokan_java.structure.DokanOptions;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;


/**
 * Dokany API callbacks interface. This is an internal class and should not used directly by code outside com.dokany.java.
 * <p>
 * A struct of callbacks that describe all Dokany API operation that will be called when Windows accesses the file system.
 * <p>
 * The return value is always one of the {@link NtStatuses} values.
 * <p>
 * All these callbacks can be set to <i>null</i> or return {@link NtStatuses#STATUS_NOT_IMPLEMENTED} if you don't want to support one of them. Be aware that returning such a value to important callbacks such as {@link
 * DokanyOperations.ZwCreateFile} or {@link DokanyOperations.ReadFile} would make the file system not working or unstable.
 * <p>
 * This is the same struct as <i>_DOKAN_OPERATIONS</i> (dokan.h) in the C++ version of Dokany.
 */
@SuppressWarnings("ALL")
public class DokanyOperations extends Structure {

    public DokanyOperations() {
    }

    // @Override
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

    public ZwCreateFile ZwCreateFile = null;
    public Cleanup Cleanup = null;
    public CloseFile CloseFile = null;
    public ReadFile ReadFile = null;
    public WriteFile WriteFile = null;
    public FlushFileBuffers FlushFileBuffers = null;
    public GetFileInformation GetFileInformation = null;
    public FindFiles FindFiles = null;
    public FindFilesWithPattern FindFilesWithPattern = null;
    public SetFileAttributes SetFileAttributes = null;
    public SetFileTime SetFileTime = null;
    public DeleteFile DeleteFile = null;
    public DeleteDirectory DeleteDirectory = null;
    public MoveFile MoveFile = null;
    public SetEndOfFile SetEndOfFile = null;
    public SetAllocationSize SetAllocationSize = null;
    public LockFile LockFile = null;
    public UnlockFile UnlockFile = null;
    public GetDiskFreeSpace GetDiskFreeSpace = null;
    public GetVolumeInformation GetVolumeInformation = null;
    public Mounted Mounted = null;
    public Unmounted Unmounted = null;
    public GetFileSecurity GetFileSecurity = null;
    public SetFileSecurity SetFileSecurity = null;
    public FindStreams FindStreams = null;

    /**
     * CreateFile is called each time a request is made on a file system object.
     * <p>
     * If the file is a directory, this method is also called. In this case, the method should return {@link NtStatuses#STATUS_SUCCESS} when that directory can be opened and {@link DokanFileInfo#IsDirectory} has to be set to
     * <i>true</i>. {@link DokanFileInfo#Context} can be used to store data FileStream that can be retrieved in all other request related to the context.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/hardware/ff566424(v=vs.85).aspx">MSDN for more information about the parameters of this callback.</a>
     */
    @FunctionalInterface
    interface ZwCreateFile extends Callback {

        /**
         * @param rawPath Path requested by the Kernel on the File System.
         * @param securityContext ??
         * @param rawDesiredAccess ?? Permissions for file or directory.
         * @param rawFileAttributes Provides attributes for files and directories. See <a href="https://msdn.microsoft.com/en-us/library/system.io.fileattributes(v=vs.110).aspx">MSDN</a>
         * @param rawShareAccess Type of share access to other threads. Device and intermediate drivers usually set ShareAccess to zero, which gives the caller exclusive access to the open file.
         * @param rawCreateDisposition
         * @param rawCreateOptions Represents advanced options for creating a File object. See <a href="https://msdn.microsoft.com/en-us/library/system.io.fileoptions(v=vs.110).aspx">MSDN</a>
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                WinBase.SECURITY_ATTRIBUTES securityContext,
                int rawDesiredAccess,
                int rawFileAttributes,
                int rawShareAccess,
                int rawCreateDisposition,
                int rawCreateOptions,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Receipt of this request indicates that the last handle for a file object that is associated with the target device object has been closed (but, due to outstanding I/O requests, might not have been released).
     * <p>
     * Cleanup is requested before @{link {@link DokanyOperations#CloseFile} is called.
     */
    @FunctionalInterface
    interface Cleanup extends Callback {

        /**
         * @param rawPath
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         */
        void callback(
                WString rawPath,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * CloseFile is called at the end of the life of the context. Receipt of this request indicates that the last handle of the file object that is associated with the target device object has been closed and released.
     * All outstanding I/O requests have been completed or canceled.
     * <p>
     * CloseFile is requested after {@link DokanyOperations.Cleanup} is called. Anything remaining in {@link DokanFileInfo#Context} has to be cleared before return.
     */
    @FunctionalInterface
    interface CloseFile extends Callback {

        /**
         * @param rawPath
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         */
        void callback(
                WString rawPath,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * ReadFile callback on the file previously opened in {@link DokanyOperations.ZwCreateFile}. It can be called by different thread at the same time, therefore the read has to be thread safe.
     */
    interface ReadFile extends Callback {

        /**
         * @param rawPath
         * @param rawBuffer
         * @param rawBufferLength
         * @param rawReadLength
         * @param rawOffset
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                Pointer rawBuffer,
                int rawBufferLength,
                IntByReference rawReadLength,
                long rawOffset,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * WriteFile callback on the file previously opened in {@link DokanyOperations.ZwCreateFile} It can be called by different thread at the same time, therefore the write/context has to be thread safe.
     */
    @FunctionalInterface
    interface WriteFile extends Callback {

        /**
         * @param rawPath
         * @param rawBuffer
         * @param rawNumberOfBytesToWrite
         * @param rawNumberOfBytesWritten
         * @param rawOffset
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                Pointer rawBuffer,
                int rawNumberOfBytesToWrite,
                IntByReference rawNumberOfBytesWritten,
                long rawOffset,
                DokanFileInfo dokanFileInfo);

    }

    /**
     * Clears buffers for this context and causes any buffered data to be written to the file.
     */
    @FunctionalInterface
    interface FlushFileBuffers extends Callback {

        /**
         * @param rawPath
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Get specific informations on a file.
     */
    @FunctionalInterface
    interface GetFileInformation extends Callback {

        /**
         * @param fileName
         * @param handleFileInfo
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString fileName,
                ByHandleFileInformation handleFileInfo,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * List all files in the path requested.
     */
    @FunctionalInterface
    interface FindFiles extends Callback {

        /**
         * @param rawPath
         * @param rawFillFindData
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                FillWin32FindData rawFillFindData,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Same as {@link DokanyOperations.FindFiles} but with a search pattern to filter the result.
     */
    @FunctionalInterface
    interface FindFilesWithPattern extends Callback {

        /**
         * @param fileName
         * @param searchPattern
         * @param rawFillFindData
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString fileName,
                WString searchPattern,
                FillWin32FindData rawFillFindData,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Set file attributes on a specific file.
     */
    @FunctionalInterface
    interface SetFileAttributes extends Callback {

        /**
         * @param rawPath
         * @param rawAttributes
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                int rawAttributes,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Set file times on a specific file.
     */
    @FunctionalInterface
    interface SetFileTime extends Callback {

        /**
         * @param rawPath path to file or directory
         * @param rawCreationTime time of creation
         * @param rawLastAccessTime time of last access
         * @param rawLastWriteTime time of last modification
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                FILETIME rawCreationTime,
                FILETIME rawLastAccessTime,
                FILETIME rawLastWriteTime,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Check if it is possible to delete a file.
     * <p>
     * You should NOT delete the file in this method, but instead you must only check whether you can delete the file or not, and return {@link NtStatuses#STATUS_SUCCESS} (when you can delete it) or appropriate error codes such
     * as {@link NtStatuses#STATUS_ACCESS_DENIED}, {@link NtStatuses#STATUS_OBJECT_NO_LONGER_EXISTS}, {@link NtStatuses#STATUS_OBJECT_NAME_NOT_FOUND}.
     * <p>
     * {@link DokanyOperations.DeleteFile} will also be called with {@link DokanFileInfo#DeleteOnClose} set to <i>false</i> to notify the driver when the file is no longer requested to be deleted.
     * <p>
     * When you return {@link NtStatuses#STATUS_SUCCESS}, you get a {@link DokanyOperations.Cleanup}> call afterwards with {@link DokanFileInfo#DeleteOnClose} set to <i>true</i> and only then you have to actually delete the file
     * being closed.
     *
     * @see {@link DokanyOperations.DeleteDirectory}
     */
    @FunctionalInterface
    interface DeleteFile extends Callback {

        /**
         * @param rawPath
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Check if it is possible to delete a directory.
     *
     * @see {@link DokanyOperations.DeleteFile} for more specifics.
     */
    @FunctionalInterface
    interface DeleteDirectory extends Callback {

        /**
         * @param rawPath
         * @param dokanFileInfo {@link DokanFileInfo} with information about the directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Move a file or directory to a new location.
     */
    @FunctionalInterface
    interface MoveFile extends Callback {

        /**
         * @param rawPath
         * @param rawNewFileName
         * @param rawReplaceIfExisting
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                WString rawNewFileName,
                boolean rawReplaceIfExisting,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * SetEndOfFile is used to truncate or extend a file (physical file size).
     */
    @FunctionalInterface
    interface SetEndOfFile extends Callback {

        /**
         * @param rawPath
         * @param rawByteOffset
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                long rawByteOffset,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * SetAllocationSize is used to truncate or extend a file.
     */
    @FunctionalInterface
    interface SetAllocationSize extends Callback {

        /**
         * @param rawPath
         * @param rawLength
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                long rawLength,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Lock file at a specific offset and data length. This is only used if {@link MountOption#FILELOCK_USER_MODE} is enabled.
     */
    @FunctionalInterface
    interface LockFile extends Callback {

        /**
         * @param rawPath
         * @param rawByteOffset
         * @param rawLength
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                long rawByteOffset,
                long rawLength,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Unlock file at a specific offset and data length. This is only used if {@link MountOption#FILELOCK_USER_MODE} is enabled.
     */
    @FunctionalInterface
    interface UnlockFile extends Callback {

        /**
         * @param rawPath
         * @param rawByteOffset
         * @param rawLength
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                long rawByteOffset,
                long rawLength,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Retrieves information about the amount of space that is available on a disk volume, which is the total amount of space, the total amount of free space, and the total amount of free space available to the user that
     * is associated with the calling thread.
     * <p>
     * Neither this method nor {@link DokanyOperations.GetVolumeInformation} save the {@link DokanFileInfo#Context}. Before these methods are called, {@link DokanyOperations.ZwCreateFile} may not be called. (ditto @{link
     * DokanyOperations.CloseFile} and @{link DokanyOperations.Cleanup}).
     */
    @FunctionalInterface
    interface GetDiskFreeSpace extends Callback {

        /**
         * @param freeBytesAvailable
         * @param totalNumberOfBytes
         * @param totalNumberOfFreeBytes
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                LongByReference freeBytesAvailable,
                LongByReference totalNumberOfBytes,
                LongByReference totalNumberOfFreeBytes,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Retrieves information about the file system and volume associated with the specified root directory.
     * <p>
     * Neither this method nor {@link DokanyOperations.GetVolumeInformation} save the {@link DokanFileInfo#Context}. Before these methods are called, {@link DokanyOperations.ZwCreateFile} may not be called. (ditto @{link
     * DokanyOperations.CloseFile} and @{link DokanyOperations.Cleanup}).
     *
     * @see {@link FileSystemFlag#READ_ONLY_VOLUME} is automatically added to the <paramref name="features"/> if <see cref="DokanOpts.WriteProtection"/> was specified when the volume was mounted.
     * <p>
     * If {@link NtStatuses#STATUS_NOT_IMPLEMENTED} is returned, the Dokany kernel driver use following settings by default:
     *
     * <ul>
     * <li>rawVolumeSerialNumber = 0x19831116</li>
     * <li>rawMaximumComponentLength = 256</li>
     * <li>rawFileSystemFlags = CaseSensitiveSearch, CasePreservedNames, SupportsRemoteStorage, UnicodeOnDisk</li>
     * <li>rawFileSystemNameBuffer = NTFS</li>
     * </ul>
     */
    @FunctionalInterface
    interface GetVolumeInformation extends Callback {

        /**
         * @param rawVolumeNameBuffer
         * @param rawVolumeNameSize
         * @param rawVolumeSerialNumber
         * @param rawMaximumComponentLength
         * @param rawFileSystemFlags
         * @param rawFileSystemNameBuffer
         * @param rawFileSystemNameSize
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                Pointer rawVolumeNameBuffer,
                int rawVolumeNameSize,
                IntByReference rawVolumeSerialNumber,
                IntByReference rawMaximumComponentLength,
                IntByReference /* FileSystemFeatures */ rawFileSystemFlags,
                Pointer rawFileSystemNameBuffer,
                int rawFileSystemNameSize,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Is called when Dokany succeeded mounting the volume.
     */
    @FunctionalInterface
    interface Mounted extends Callback {

        long mounted(
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Is called when Dokany succeeded unmounting the volume.
     */
    @FunctionalInterface
    interface Unmounted extends Callback {

        long unmounted(
                final DokanFileInfo dokanFileInfo);
    }

    /**
     * Get specified information about the security of a file or directory.
     * <p>
     * Supported since version 0.6.0. You must specify the version in {@link DokanOptions#Version}.
     */
    @FunctionalInterface
    interface GetFileSecurity extends Callback {

        /**
         * @param rawPath
         * @param rawSecurityInformation
         * @param rawSecurityDescriptor
         * @param rawSecurityDescriptorLength
         * @param rawSecurityDescriptorLengthNeeded
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                int /* SecurityInformation */ rawSecurityInformation,
                Pointer rawSecurityDescriptor,
                int rawSecurityDescriptorLength,
                IntByReference rawSecurityDescriptorLengthNeeded,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Sets the security of a file or directory object.
     * <p>
     * Supported since version 0.6.0. You must specify the version in {@link DokanOptions#Version}.
     */
    @FunctionalInterface
    interface SetFileSecurity extends Callback {

        /**
         * @param rawPath
         * @param rawSecurityInformation
         * @param rawSecurityDescriptor
         * @param rawSecurityDescriptorLength
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                int rawSecurityInformation,
                Pointer rawSecurityDescriptor,
                int rawSecurityDescriptorLength,
                DokanFileInfo dokanFileInfo);
    }

    @FunctionalInterface
    public interface FillWin32FindData extends Callback {

        /**
         * @param rawFillFindData
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         */
        void fillWin32FindData(
                WIN32_FIND_DATA rawFillFindData,
                DokanFileInfo dokanFileInfo);
    }

    /**
     * Retrieve all NTFS Streams informations on the file. This is only called if {@link MountOption#ALT_STREAM} is enabled.
     */
    @FunctionalInterface
    interface FindStreams extends Callback {

        /**
         * @param rawPath
         * @param rawFillFindData
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         * @return {@link NtStatuses}
         */
        long callback(
                WString rawPath,
                FillWin32FindStreamData rawFillFindData,
                DokanFileInfo dokanFileInfo);
    }

    /**
     *
     *
     */
    @FunctionalInterface
    public interface FillWin32FindStreamData extends Callback {

        /**
         * @param rawFillFindData
         * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
         */
        void callback(
                Win32FindStreamData rawFillFindData,
                DokanFileInfo dokanFileInfo);
    }

    interface Win32FindStreamDataInterface {

        void length(long val);

        char[] cFileName();
    }

    public void setZwCreateFile(DokanyOperations.ZwCreateFile zwCreateFile) {
        ZwCreateFile = zwCreateFile;
    }

    public void setCleanup(DokanyOperations.Cleanup cleanup) {
        Cleanup = cleanup;
    }

    public void setCloseFile(DokanyOperations.CloseFile closeFile) {
        CloseFile = closeFile;
    }

    public void setReadFile(DokanyOperations.ReadFile readFile) {
        ReadFile = readFile;
    }

    public void setWriteFile(DokanyOperations.WriteFile writeFile) {
        WriteFile = writeFile;
    }

    public void setFlushFileBuffers(DokanyOperations.FlushFileBuffers flushFileBuffers) {
        FlushFileBuffers = flushFileBuffers;
    }

    public void setGetFileInformation(DokanyOperations.GetFileInformation getFileInformation) {
        GetFileInformation = getFileInformation;
    }

    public void setFindFiles(DokanyOperations.FindFiles findFiles) {
        FindFiles = findFiles;
    }

    public void setFindFilesWithPattern(DokanyOperations.FindFilesWithPattern findFilesWithPattern) {
        FindFilesWithPattern = findFilesWithPattern;
    }

    public void setSetFileAttributes(DokanyOperations.SetFileAttributes setFileAttributes) {
        SetFileAttributes = setFileAttributes;
    }

    public void setSetFileTime(DokanyOperations.SetFileTime setFileTime) {
        SetFileTime = setFileTime;
    }

    public void setDeleteFile(DokanyOperations.DeleteFile deleteFile) {
        DeleteFile = deleteFile;
    }

    public void setDeleteDirectory(DokanyOperations.DeleteDirectory deleteDirectory) {
        DeleteDirectory = deleteDirectory;
    }

    public void setMoveFile(DokanyOperations.MoveFile moveFile) {
        MoveFile = moveFile;
    }

    public void setSetEndOfFile(DokanyOperations.SetEndOfFile setEndOfFile) {
        SetEndOfFile = setEndOfFile;
    }

    public void setSetAllocationSize(DokanyOperations.SetAllocationSize setAllocationSize) {
        SetAllocationSize = setAllocationSize;
    }

    public void setLockFile(DokanyOperations.LockFile lockFile) {
        LockFile = lockFile;
    }

    public void setUnlockFile(DokanyOperations.UnlockFile unlockFile) {
        UnlockFile = unlockFile;
    }

    public void setGetDiskFreeSpace(DokanyOperations.GetDiskFreeSpace getDiskFreeSpace) {
        GetDiskFreeSpace = getDiskFreeSpace;
    }

    public void setGetVolumeInformation(DokanyOperations.GetVolumeInformation getVolumeInformation) {
        GetVolumeInformation = getVolumeInformation;
    }

    public void setMounted(DokanyOperations.Mounted mounted) {
        Mounted = mounted;
    }

    public void setUnmounted(DokanyOperations.Unmounted unmounted) {
        Unmounted = unmounted;
    }

    public void setGetFileSecurity(DokanyOperations.GetFileSecurity getFileSecurity) {
        GetFileSecurity = getFileSecurity;
    }

    public void setSetFileSecurity(DokanyOperations.SetFileSecurity setFileSecurity) {
        SetFileSecurity = setFileSecurity;
    }

    public void setFindStreams(DokanyOperations.FindStreams findStreams) {
        FindStreams = findStreams;
    }
}
