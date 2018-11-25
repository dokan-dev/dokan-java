package com.dokan.java.constants.microsoft;

import com.dokan.java.DokanyOperations;
import com.dokan.java.constants.EnumInteger;
import com.dokan.java.structure.EnumIntegerSet;
import com.sun.jna.platform.win32.WinNT;

/**
 * Properties which the implemented filesystem supports.
 *
 * <p>
 * Returned in {@link DokanyOperations#GetVolumeInformation} to the kernel layer indicating what properties your file system implementation supports.
 * </p>
 *
 * <p> They can be arbitrary combined within an {@link EnumIntegerSet}. However FILE_FILE_COMPRESSION and FILE_VOL_IS_COMPRESSED are mutually exclusive.</p>
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows/desktop/api/fileapi/nf-fileapi-getvolumeinformationa#parameters">Microsoft Documentation of function GetVolumeInformation</a>, Parameter {@code lpFileSystemFlags}
 * @see <a href="https://msdn.microsoft.com/en-us/library/cc246323.aspx">Listing of possible values</a>
 */
public enum FileSystemFlag implements EnumInteger {
    NONE(0),
    CASE_PRESERVED_NAMES(WinNT.FILE_CASE_PRESERVED_NAMES),
    CASE_SENSITIVE_SEARCH(WinNT.FILE_CASE_SENSITIVE_SEARCH),
    DAX_VOLUME(0x20000000),
    FILE_COMPRESSION(WinNT.FILE_FILE_COMPRESSION),
    NAMED_STREAMS(WinNT.FILE_NAMED_STREAMS),
    PERSISTENT_ACLS(WinNT.FILE_PERSISTENT_ACLS),
    READ_ONLY_VOLUME(WinNT.FILE_READ_ONLY_VOLUME),
    SEQUENTIAL_WRITE_ONCE(WinNT.FILE_SEQUENTIAL_WRITE_ONCE),
    SUPPORTS_ENCRYPTION(WinNT.FILE_SUPPORTS_ENCRYPTION),
    SUPPORTS_EXTENDED_ATTRIBUTES(WinNT.FILE_SUPPORTS_EXTENDED_ATTRIBUTES),
    SUPPORTS_HARD_LINKS(WinNT.FILE_SUPPORTS_HARD_LINKS),
    SUPPORTS_OBJECT_IDS(WinNT.FILE_SUPPORTS_OBJECT_IDS),
    SUPPORTS_OPEN_BY_FILE_ID(WinNT.FILE_SUPPORTS_OPEN_BY_FILE_ID),
    SUPPORTS_REPARSE_POINTS(WinNT.FILE_SUPPORTS_REPARSE_POINTS),
    SUPPORTS_SPARSE_FILES(WinNT.FILE_SUPPORTS_SPARSE_FILES),
    SUPPORTS_TRANSACTIONS(WinNT.FILE_SUPPORTS_TRANSACTIONS),
    SUPPORTS_USN_JOURNAL(WinNT.FILE_SUPPORTS_USN_JOURNAL),
    UNICODE_ON_DISK(WinNT.FILE_UNICODE_ON_DISK),
    SUPPORTS_REMOTE_STORAGE(WinNT.FILE_SUPPORTS_REMOTE_STORAGE),
    VOLUME_IS_COMPRESSED(WinNT.FILE_VOLUME_IS_COMPRESSED),
    VOLUME_QUOTAS(WinNT.FILE_VOLUME_QUOTAS);

    private final int mask;

    public static EnumIntegerSet<FileSystemFlag> fromInt(final int value) {
        return EnumIntegerSet.enumSetFromInt(value, values());
    }

    FileSystemFlag(final int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return this.mask;
    }
}
