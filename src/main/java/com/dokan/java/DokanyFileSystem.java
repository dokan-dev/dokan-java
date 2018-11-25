package com.dokan.java;

import com.dokan.java.constants.dokany.MountOption;
import com.dokan.java.constants.microsoft.CreationDisposition;
import com.dokan.java.constants.microsoft.NtStatuses;
import com.dokan.java.constants.microsoft.FileSystemFlag;
import com.dokan.java.structure.ByHandleFileInformation;
import com.dokan.java.structure.DokanFileInfo;
import com.dokan.java.structure.DokanOptions;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;


/**
 *  Main interface to implement. These methods will be registered in the dokany kernel driver to handle filesystem requests.
 */
public interface DokanyFileSystem extends Mountable {

    /**
     * CreateFile Dokan API callback.
     * <p>
     * CreateFile is called each time a request is made on a file system object.
     * <p>
     * In case OPEN_ALWAYS &amp; CREATE_ALWAYS are successfully opening an existing file, STATUS_OBJECT_NAME_COLLISION should be returned instead of STATUS_SUCCESS . This will inform Dokan that the file has been opened
     * and not created during the request.
     * <p>
     * If the file is a directory, CreateFile is also called. In this case, CreateFile should return {@link NtStatuses#STATUS_SUCCESS} when that directory can be opened and {@link DokanFileInfo#IsDirectory} has to be set to TRUE.
     * On the other hand, if {@link DokanFileInfo#IsDirectory} is set to TRUE but the path targets a file, {@link NtStatuses#STATUS_NOT_A_DIRECTORY} must be returned.
     * <p>
     * {@link DokanFileInfo#Context} can be used to store Data (like a filehandle) that can be retrieved in all other requests related to the Context. To avoid memory leak, Context needs to be released in {@link
     * DokanyFileSystem#cleanup(WString, DokanFileInfo)} .
     *
     * @param rawPath Path requested by the Kernel on the File System. TODO: rewrite this parameter description to link to winBase
     * @param securityContext the security context of the kernel (see also in the windows driver API <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/ns-wdm-_io_security_context">IO_SECURITY_CONTEXT</a>)
     * @param rawDesiredAccess Permissions for file or directory. (see also in the windows API <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/kernel/access-mask">ACCESS_MASK</a>
     * @param rawFileAttributes Provides attributes for files and directories. (see also in the .NET API <a href="https://docs.microsoft.com/en-us/dotnet/api/system.io.fileattributes">System.IO.FileAttributes</a>}
     * @param rawShareAccess Type of share access to other threads. Device and intermediate drivers usually set ShareAccess to zero, which gives the caller exclusive access to the open file.
     * @param rawCreateDisposition Specifies the action to perform if the file does or does not exist. Can be translated into a readable thing via {@link CreationDisposition}
     * @param rawCreateOptions Specifies the options to apply when the driver creates or opens the file. (see also in the .NET API <a href="https://docs.microsoft.com/de-de/dotnet/api/system.io.fileoptions">System.IO.FileOptions</a>)
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return integer code of a {@link NtStatuses}
     *
     * @see <a href="https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html#a40c2f61e1287237f5fd5c2690e795183">Dokany documentation of ZwCreateFile</a>
     * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/ntifs/nf-ntifs-ntcreatefile">Microsoft documentation of zwCreateFile</a>
     * @see <a href="https://msdn.microsoft.com/en-us/library/aa363858%28VS.85%29.aspx">Microsoft FileManagement documentation of CreateFile</a>
     */
    int zwCreateFile(
            WString rawPath,
            WinBase.SECURITY_ATTRIBUTES securityContext,
            int rawDesiredAccess,
            int rawFileAttributes,
            int rawShareAccess,
            int rawCreateDisposition,
            int rawCreateOptions,
            DokanFileInfo dokanFileInfo);

    /**
     * Receipt of this request indicates that the last handle for a file object that is associated with the target device object has been closed (but, due to outstanding I/O requests, might not have been released).
     * <p>
     * Cleanup is requested before @{link {@link DokanyFileSystem#closeFile(WString, DokanFileInfo)} is called.
     *
     * @param rawPath
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     */
    void cleanup(
            WString rawPath,
            DokanFileInfo dokanFileInfo);

    /**
     * CloseFile is called at the end of the life of the context. Receipt of this request indicates that the last handle of the file object that is associated with the target device object has been closed and released.
     * All outstanding I/O requests have been completed or canceled.
     * <p>
     * CloseFile is requested after {@link DokanyFileSystem#cleanup(WString, DokanFileInfo)} is called. Anything remaining in {@link DokanFileInfo#Context} has to be cleared before return.
     *
     * @param rawPath
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     */
    void closeFile(
            WString rawPath,
            DokanFileInfo dokanFileInfo);

    /**
     * ReadFile callback on the file previously opened in {@link DokanyFileSystem#zwCreateFile(WString, WinBase.SECURITY_ATTRIBUTES, int, int, int, int, int, DokanFileInfo)}. It can be called by different thread at the
     * same time, therefore the read has to be thread safe.
     *
     * @param rawPath
     * @param rawBuffer
     * @param rawBufferLength
     * @param rawReadLength
     * @param rawOffset
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int readFile(
            WString rawPath,
            Pointer rawBuffer,
            int rawBufferLength,
            IntByReference rawReadLength,
            long rawOffset,
            DokanFileInfo dokanFileInfo);

    /**
     * WriteFile callback on the file previously opened in {@link DokanyFileSystem#zwCreateFile(WString, WinBase.SECURITY_ATTRIBUTES, int, int, int, int, int, DokanFileInfo)} It can be called by different thread at the
     * same time, therefore the write/context has to be thread safe.
     *
     * @param rawPath
     * @param rawBuffer
     * @param rawNumberOfBytesToWrite
     * @param rawNumberOfBytesWritten
     * @param rawOffset
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int writeFile(
            WString rawPath,
            Pointer rawBuffer,
            int rawNumberOfBytesToWrite,
            IntByReference rawNumberOfBytesWritten,
            long rawOffset,
            DokanFileInfo dokanFileInfo);


    /**
     * Clears buffers for this context and causes any buffered data to be written to the file.
     *
     * @param rawPath
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int flushFileBuffers(
            WString rawPath,
            DokanFileInfo dokanFileInfo);

    /**
     * Get specific informations on a file.
     *
     * @param fileName
     * @param handleFileInfo
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int getFileInformation(
            WString fileName,
            ByHandleFileInformation handleFileInfo,
            DokanFileInfo dokanFileInfo);

    /**
     * List all files in the path requested.
     *
     * @param rawPath
     * @param rawFillFindData
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int findFiles(
            WString rawPath,
            DokanyOperations.FillWin32FindData rawFillFindData,
            DokanFileInfo dokanFileInfo);

    /**
     * Same as {@link DokanyFileSystem#findFiles(WString, DokanyOperations.FillWin32FindData, DokanFileInfo)} but with a search pattern to filter the result.
     *
     * @param fileName
     * @param searchPattern
     * @param rawFillFindData
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int findFilesWithPattern(
            WString fileName,
            WString searchPattern,
            DokanyOperations.FillWin32FindData rawFillFindData,
            DokanFileInfo dokanFileInfo);

    /**
     * Set file attributes on a specific file.
     *
     * @param rawPath
     * @param rawAttributes
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int setFileAttributes(
            WString rawPath,
            int rawAttributes,
            DokanFileInfo dokanFileInfo);

    /**
     * Set file times on a specific file.
     *
     * @param rawPath path to file or directory
     * @param rawCreationTime time of creation
     * @param rawLastAccessTime time of last access
     * @param rawLastWriteTime time of last modification
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int setFileTime(
            WString rawPath,
            FILETIME rawCreationTime,
            FILETIME rawLastAccessTime,
            FILETIME rawLastWriteTime,
            DokanFileInfo dokanFileInfo);

    /**
     * Check if it is possible to delete a file.
     * <p>
     * You should NOT delete the file in this method, but instead you must only check whether you can delete the file or not, and return {@link NtStatuses#STATUS_SUCCESS} (when you can delete it) or appropriate error codes such
     * as {@link NtStatuses#STATUS_ACCESS_DENIED}, {@link NtStatuses#STATUS_OBJECT_NO_LONGER_EXISTS}, {@link NtStatuses#STATUS_OBJECT_NAME_NOT_FOUND}.
     * <p>
     * {@link DokanyFileSystem#deleteFile(WString, DokanFileInfo)} will also be called with {@link DokanFileInfo#DeleteOnClose} set to <i>false</i> to notify the driver when the file is no longer requested to be
     * deleted.
     * <p>
     * When you return {@link NtStatuses#STATUS_SUCCESS}, you get a {@link DokanyFileSystem#cleanup(WString, DokanFileInfo)} call afterwards with {@link DokanFileInfo#DeleteOnClose} set to <i>true</i> and only then you have to
     * actually delete the file being closed.
     *
     * @param rawPath
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file.
     * @return {@link NtStatuses}
     *
     * @see #deleteDirectory(WString, DokanFileInfo)
     */
    int deleteFile(
            WString rawPath,
            DokanFileInfo dokanFileInfo);

    /**
     * Check if it is possible to delete a directory.
     *
     * @param rawPath
     * @param dokanFileInfo {@link DokanFileInfo} with information about the directory.
     * @return {@link NtStatuses}
     *
     * @see #deleteFile(WString, DokanFileInfo)
     */
    int deleteDirectory(
            WString rawPath,
            DokanFileInfo dokanFileInfo);

    /**
     * Move a file or directory to a new location.
     *
     * @param rawPath
     * @param rawNewFileName
     * @param rawReplaceIfExisting
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int moveFile(
            WString rawPath,
            WString rawNewFileName,
            boolean rawReplaceIfExisting,
            DokanFileInfo dokanFileInfo);

    /**
     * SetEndOfFile is used to truncate or extend a file (physical file size).
     *
     * @param rawPath
     * @param rawByteOffset
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int setEndOfFile(
            WString rawPath,
            long rawByteOffset,
            DokanFileInfo dokanFileInfo);

    /**
     * SetAllocationSize is used to truncate or extend a file.
     *
     * @param rawPath
     * @param rawLength
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int setAllocationSize(
            WString rawPath,
            long rawLength,
            DokanFileInfo dokanFileInfo);

    /**
     * Lock file at a specific offset and data length. This is only used if {@link MountOption#FILELOCK_USER_MODE} is enabled.
     *
     * @param rawPath
     * @param rawByteOffset
     * @param rawLength
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int lockFile(
            WString rawPath,
            long rawByteOffset,
            long rawLength,
            DokanFileInfo dokanFileInfo);

    /**
     * Unlock file at a specific offset and data length. This is only used if {@link MountOption#FILELOCK_USER_MODE} is enabled.
     *
     * @param rawPath
     * @param rawByteOffset
     * @param rawLength
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int unlockFile(
            WString rawPath,
            long rawByteOffset,
            long rawLength,
            DokanFileInfo dokanFileInfo);

    /**
     * Retrieves information about the amount of space that is available on a disk volume, which is the total amount of space, the total amount of free space, and the total amount of free space available to the user that
     * is associated with the calling thread.
     * <p>
     * Neither this method nor {@link DokanyFileSystem#getVolumeInformation(Pointer, int, IntByReference, IntByReference, IntByReference, Pointer, int, DokanFileInfo)} save the {@link DokanFileInfo#Context}. Before these
     * methods are called, {@link DokanyFileSystem#zwCreateFile(WString, WinBase.SECURITY_ATTRIBUTES, int, int, int, int, int, DokanFileInfo)} may not be called. (ditto @{link DokanyOperations.CloseFile} and @{link
     * DokanyOperations.Cleanup}).
     *
     * @param freeBytesAvailable
     * @param totalNumberOfBytes
     * @param totalNumberOfFreeBytes
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int getDiskFreeSpace(
            LongByReference freeBytesAvailable,
            LongByReference totalNumberOfBytes,
            LongByReference totalNumberOfFreeBytes,
            DokanFileInfo dokanFileInfo);

    /**
     * Retrieves information about the file system and volume associated with the specified root directory.
     * <p>
     * Neither this method nor {@link DokanyFileSystem#getVolumeInformation(Pointer, int, IntByReference, IntByReference, IntByReference, Pointer, int, DokanFileInfo)} save the {@link DokanFileInfo#Context}. Before these
     * methods are called, {@link DokanyFileSystem#zwCreateFile(WString, WinBase.SECURITY_ATTRIBUTES, int, int, int, int, int, DokanFileInfo)} may not be called. (ditto @{link DokanyOperations.CloseFile} and @{link
     * DokanyOperations.Cleanup}).
     * <p>
     * {@link FileSystemFlag#READ_ONLY_VOLUME} is automatically added to the features if {@link MountOption#WRITE_PROTECTION} was specified during mount.
     * <p>
     * If {@link NtStatuses#STATUS_NOT_IMPLEMENTED} is returned, the Dokany kernel driver use following settings by default:
     *
     * <ul>
     * <li>rawVolumeSerialNumber = 0x19831116</li>
     * <li>rawMaximumComponentLength = 256</li>
     * <li>rawFileSystemFlags = CaseSensitiveSearch, CasePreservedNames, SupportsRemoteStorage, UnicodeOnDisk</li>
     * <li>rawFileSystemNameBuffer = NTFS</li>
     * </ul>
     *
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
    int getVolumeInformation(
            Pointer rawVolumeNameBuffer,
            int rawVolumeNameSize,
            IntByReference rawVolumeSerialNumber,
            IntByReference rawMaximumComponentLength,
            IntByReference /* FileSystemFeatures */ rawFileSystemFlags,
            Pointer rawFileSystemNameBuffer,
            int rawFileSystemNameSize,
            DokanFileInfo dokanFileInfo);

    /**
     * Is called when Dokany succeeded mounting the volume.
     */
    int mounted(
            DokanFileInfo dokanFileInfo);

    /**
     * Is called when Dokany succeeded unmounting the volume.
     */
    int unmounted(
            final DokanFileInfo dokanFileInfo);

    /**
     * Get specified information about the security of a file or directory.
     * <p>
     * Supported since version 0.6.0. You must specify the version in {@link DokanOptions#Version}.
     *
     * @param rawPath
     * @param rawSecurityInformation
     * @param rawSecurityDescriptor
     * @param rawSecurityDescriptorLength
     * @param rawSecurityDescriptorLengthNeeded
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int getFileSecurity(
            WString rawPath,
            int /* SecurityInformation */ rawSecurityInformation,
            Pointer rawSecurityDescriptor,
            int rawSecurityDescriptorLength,
            IntByReference rawSecurityDescriptorLengthNeeded,
            DokanFileInfo dokanFileInfo);

    /**
     * Sets the security of a file or directory object.
     * <p>
     * Supported since version 0.6.0. You must specify the version in {@link DokanOptions#Version}.
     *
     * @param rawPath
     * @param rawSecurityInformation
     * @param rawSecurityDescriptor
     * @param rawSecurityDescriptorLength
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int setFileSecurity(
            WString rawPath,
            int rawSecurityInformation,
            // @TODO: This is a pointer??
            Pointer rawSecurityDescriptor,
            int rawSecurityDescriptorLength,
            DokanFileInfo dokanFileInfo);

    /**
     * @param rawFillFindData
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     */
    void fillWin32FindData(
            WIN32_FIND_DATA rawFillFindData,
            DokanFileInfo dokanFileInfo);

    /**
     * Retrieve all NTFS Streams informations on the file. This is only called if {@link MountOption#ALT_STREAM} is enabled.
     *
     * @param rawPath
     * @param rawFillFindData
     * @param dokanFileInfo {@link DokanFileInfo} with information about the file or directory.
     * @return {@link NtStatuses}
     */
    int findStreams(
            WString rawPath,
            DokanyOperations.FillWin32FindStreamData rawFillFindData,
            DokanFileInfo dokanFileInfo);

}
