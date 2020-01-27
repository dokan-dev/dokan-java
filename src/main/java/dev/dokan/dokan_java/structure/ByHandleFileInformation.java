package dev.dokan.dokan_java.structure;

import dev.dokan.dokan_java.DokanyOperations;
import dev.dokan.dokan_java.DokanyUtils;
import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Contains information that the {@link DokanyOperations#GetFileInformation} function retrieves.
 * <p>
 * The identifier that is stored in the nFileIndexHigh and nFileIndexLow members is called the file ID. Support for file IDs is file system-specific. File IDs are not guaranteed to be unique over time, because file
 * systems are free to reuse them. In some cases, the file ID for a file can change over time.
 * <p>
 * In the FAT file system, the file ID is generated from the first cluster of the containing directory and the byte offset within the directory of the entry for the file. Some defragmentation products change this byte
 * offset. (Windows in-box defragmentation does not.) Thus, a FAT file ID can change over time. Renaming a file in the FAT file system can also change the file ID, but only if the new file name is longer than the old
 * one.
 * <p>
 * In the NTFS file system, a file keeps the same file ID until it is deleted. You can replace one file with another file without changing the file ID by using the ReplaceFile function. However, the file ID of the
 * replacement file, not the replaced file, is retained as the file ID of the resulting file.
 * <p>
 * Not all file systems can record creation and last access time, and not all file systems record them in the same manner. For example, on a Windows FAT file system, create time has a resolution of 10 milliseconds, write
 * time has a resolution of 2 seconds, and access time has a resolution of 1 day (the access date). On the NTFS file system, access time has a resolution of 1 hour.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows/desktop/api/fileapi/ns-fileapi-_by_handle_file_information">Microsoft Documentation of _BY_HANDLE_FILE_INFORMATION</a>
 */
public class ByHandleFileInformation extends Structure implements Structure.ByReference {

    /**
     * The file attributes of a file. For possible values and their descriptions, see File Attribute Constants. The FILE_ATTRIBUTE_SPARSE_FILE attribute on the file is set if any of the streams of the file have ever been
     * sparse.
     */
    public int dwFileAttributes;

    /**
     * A FILETIME structure that specifies when a file or directory was created. If the underlying file system does not support creation time, this member is zero.
     */
    public FILETIME ftCreationTime;

    /**
     * A FILETIME structure. For a file, the structure specifies when the file was last read from, written to, or for executable files, run. For a directory, the structure specifies when the directory is created. If the
     * underlying file system does not support last access time, this member is zero. On the FAT file system, the specified date for both files and directories is correct, but the time of day is always set to midnight.
     */
    public FILETIME ftLastAccessTime;

    /**
     * A FILETIME structure. For a file, the structure specifies when the file was last written to, truncated, or overwritten, for example, when WriteFile or SetEndOfFile are used. The date and time are not updated when
     * file attributes or security descriptors are changed. For a directory, the structure specifies when the directory is created. If the underlying file system does not support last write time, this member is zero.
     */
    public FILETIME ftLastWriteTime;

    /**
     * The serial number of the volume that contains a file.
     */
    public int dwVolumeSerialNumber;

    /**
     * The high-order DWORD value of the file size, in bytes. This value is zero unless the file size is greater than MAXDWORD. The size of the file is equal to (nFileSizeHigh * (MAXDWORD+1)) + nFileSizeLow.
     */
    public int nFileSizeHigh;

    /**
     * The low-order DWORD value of the file size, in bytes.
     */
    public int nFileSizeLow;

    /**
     * The high-order DWORD value of the file size, in bytes. This value is zero unless the file size is greater than MAXDWORD. The size of the file is equal to (nFileSizeHigh* (MAXDWORD+1)) + nFileSizeLow.
     */
    public int nFileIndexHigh;

    /**
     * The low-order DWORD value of the file size, in bytes.
     */
    public int nFileIndexLow;

    /**
     * The number of links to this file. For the FAT file system this member is always 1. For the NTFS file system, it can be more than 1.
     */
    public int nNumberOfLinks = 1;

    private Path filePath;
    private long fileIndex;
    private long fileSize;

    public ByHandleFileInformation() {
        this(null, null, null);
    }

    public ByHandleFileInformation(Path filePath, int attrs, FileTime creationTime, FileTime lastAccessTime, FileTime lastWriteTime, int volumeSerialNumber, long fileSize, long fileIndex) {
        this.filePath = filePath;
        this.dwFileAttributes = attrs;
        this.setTimes(creationTime.toMillis(), lastAccessTime.toMillis(), lastWriteTime.toMillis());
        this.setIndex(fileIndex);
        this.setFileSize(fileSize);
        this.dwVolumeSerialNumber = volumeSerialNumber;
    }

    public ByHandleFileInformation(Path filePath, EnumIntegerSet<FileAttribute> attrs, FileTime creationTime, FileTime lastAccessTime, FileTime lastWriteTime, int volumeSerialNumber, long fileSize, long fileIndex) {
        this.filePath = filePath;
        this.dwFileAttributes = attrs.toInt();
        this.setTimes(creationTime.toMillis(), lastAccessTime.toMillis(), lastWriteTime.toMillis());
        this.setIndex(fileIndex);
        this.setFileSize(fileSize);
        this.dwVolumeSerialNumber = volumeSerialNumber;
    }

    public ByHandleFileInformation(final FILETIME creationTime, final FILETIME lastAccessTime, final FILETIME lastWriteTime) {
        setTimes(creationTime, lastAccessTime, lastWriteTime);
    }

    public ByHandleFileInformation(final long creationTime, final long lastAccessTime, final long lastWriteTime) {
        setTimes(creationTime, lastAccessTime, lastWriteTime);
    }


    public void copyTo(final ByHandleFileInformation infoToReceive) {
        if (Objects.isNull(infoToReceive)) {
            throw new IllegalStateException("infoToReceive cannot be null");
        }
        infoToReceive.filePath = filePath;
        infoToReceive.setSizesExplicit(fileSize, nFileSizeHigh, nFileSizeLow);
        infoToReceive.setIndexExplicit(fileIndex, nFileIndexHigh, nFileIndexLow);
        infoToReceive.dwFileAttributes = dwFileAttributes;
        infoToReceive.setTimes(ftCreationTime, ftLastAccessTime, ftLastWriteTime);
        infoToReceive.nNumberOfLinks = nNumberOfLinks;
        infoToReceive.dwVolumeSerialNumber = dwVolumeSerialNumber;
    }

    public void setAttributes(final EnumIntegerSet<FileAttribute> attributes) {
        this.dwFileAttributes = attributes != null ? attributes.toInt() : FileAttribute.NORMAL.getMask();
    }

    public void setTimes(final long creationTime, final long lastAccessTime, final long lastWriteTime) {
        this.ftCreationTime = DokanyUtils.getTime(creationTime);
        this.ftLastAccessTime = DokanyUtils.getTime(lastAccessTime);
        this.ftLastWriteTime = DokanyUtils.getTime(lastWriteTime);
    }

    /**
     * Sets the file time of the file handle if it is not null. Otherwise the filetime stays the same.
     *
     * @param creationTime a {@link FILETIME} representing the point in time the file was created.
     * @param lastAccessTime a {@link FILETIME} representing the point in time the file was last accessed.
     * @param lastWriteTime a {@link FILETIME} representing the point in time the file was last written to.
     */
    void setTimes(final FILETIME creationTime, final FILETIME lastAccessTime, final FILETIME lastWriteTime) {
        if (creationTime != null) {
            ftCreationTime = creationTime;
        }
        if (lastAccessTime != null) {
            ftLastAccessTime = lastAccessTime;
        }
        if (lastWriteTime != null) {
            ftLastWriteTime = lastWriteTime;
        }
    }

    /**
     * Sets the last write time of the file.
     *
     * @param lastWriteTime the new last write timestamp of the file
     */
    public void setLastWriteTime(final long lastWriteTime) {
        this.ftLastWriteTime = DokanyUtils.getTime(lastWriteTime);
    }

    /**
     * Sets the creation time of the file.
     *
     * @param creationTime the new creation timestamp of the file
     */
    public void setCreationTime(final long creationTime) {
        this.ftCreationTime = DokanyUtils.getTime(creationTime);
    }

    /**
     * Sets the size of the file.
     *
     * @param sizeToSet the new size of the file
     */
    public void setFileSize(final long sizeToSet) {
        this.fileSize = sizeToSet;
        final WinNT.LARGE_INTEGER largeInt = new WinNT.LARGE_INTEGER(sizeToSet);
        this.nFileSizeHigh = largeInt.getHigh().intValue();
        this.nFileSizeLow = largeInt.getLow().intValue();
    }

    protected final void setSizesExplicit(final long size, final int sizeHigh, final int sizeLow) {
        this.fileSize = size;
        this.nFileSizeHigh = sizeHigh;
        this.nFileSizeLow = sizeLow;
    }

    public final long getSize() {
        return this.fileSize;
    }

    public void setIndex(final long index) {
        this.fileIndex = index;
        final WinNT.LARGE_INTEGER largeInt = new WinNT.LARGE_INTEGER(index);
        this.nFileIndexHigh = largeInt.getHigh().intValue();
        this.nFileIndexLow = largeInt.getLow().intValue();
    }

    protected void setIndexExplicit(final long index, final int indexHigh, final int indexLow) {
        this.fileIndex = index;
        this.nFileIndexHigh = indexHigh;
        this.nFileIndexLow = indexLow;
    }

    public Path getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getFileIndex() {
        return fileIndex;
    }

    public WinBase.WIN32_FIND_DATA toWin32FindData() {
        char[] cFileName = Arrays.copyOf(filePath.getFileName().toString().toCharArray(), WinBase.MAX_PATH);
        cFileName[259] = '\0'; //ensure the string is null terminated
        final char[] cAlternateFileName = new char[14]; //cannot be used by dokany, see https://github.com/dokan-dev/dokany/issues/301
        return new WinBase.WIN32_FIND_DATA(dwFileAttributes, ftCreationTime, ftLastAccessTime, ftLastWriteTime, nFileSizeHigh, nFileSizeLow, 0, 0, cFileName, cAlternateFileName);
    }

    @Override
    public List<String> getFieldOrder() {
        return createFieldsOrder("dwFileAttributes", "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime", "dwVolumeSerialNumber", "nFileSizeHigh", "nFileSizeLow", "nNumberOfLinks", "nFileIndexHigh", "nFileIndexLow");
    }

    @Override
    public String toString() {
        return "ByHandleFileInfo(filePath=" + this.filePath + ", fileIndex=" + this.fileIndex + ", fileSize=" + this.fileSize + ", nFileIndexHigh=" + this.nFileIndexHigh + ", nFileIndexLow=" + this.nFileIndexLow + ", dwFileAttributes=" + this.dwFileAttributes + ", ftCreationTime=" + this.ftCreationTime + ", ftLastAccessTime=" + this.ftLastAccessTime + ", ftLastWriteTime=" + this.ftLastWriteTime + ", nFileSizeHigh=" + this.nFileSizeHigh + ", nFileSizeLow=" + this.nFileSizeLow + ", dwVolumeSerialNumber=" + this.dwVolumeSerialNumber + ", nNumberOfLinks=" + this.nNumberOfLinks + ")";
    }
}
