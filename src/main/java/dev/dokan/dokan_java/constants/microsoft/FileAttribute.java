package dev.dokan.dokan_java.constants.microsoft;


import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.conv.MaskValueSet;
import dev.dokan.dokan_java.conv.MaskValueEnum;


/**
 * File attribute flags. They are  metadata values stored by the file system on disk and are used by the system.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows/desktop/FileIO/file-attribute-constants">Microsoft documentation of file attribute constants</a>
 * @see <a href="https://docs.microsoft.com/en-us/windows/desktop/api/fileapi/nf-fileapi-createfilea">Microsoft documentation of CreateFileA function including the list of valid file attributes</a>
 */
public enum FileAttribute implements MaskValueEnum {
    ARCHIVE(WinNT.FILE_ATTRIBUTE_ARCHIVE),
    COMPRESSED(WinNT.FILE_ATTRIBUTE_COMPRESSED),
    DEVICE(WinNT.FILE_ATTRIBUTE_DEVICE),
    DIRECTORY(WinNT.FILE_ATTRIBUTE_DIRECTORY),
    ENCRYPTED(WinNT.FILE_ATTRIBUTE_ENCRYPTED),
    HIDDEN(WinNT.FILE_ATTRIBUTE_HIDDEN),
    INTEGRITY_STREAM(32768),
    NORMAL(WinNT.FILE_ATTRIBUTE_NORMAL),
    NOT_CONTENT_INDEXED(WinNT.FILE_ATTRIBUTE_NOT_CONTENT_INDEXED),
    NO_SCRUB_DATA(131072),
    OFFLINE(WinNT.FILE_ATTRIBUTE_OFFLINE),
    READONLY(WinNT.FILE_ATTRIBUTE_READONLY),
    REPARSE_POINT(WinNT.FILE_ATTRIBUTE_REPARSE_POINT),
    SPARSE_FILE(WinNT.FILE_ATTRIBUTE_SPARSE_FILE),
    SYSTEM(WinNT.FILE_ATTRIBUTE_SYSTEM),
    TEMPORARY(WinNT.FILE_ATTRIBUTE_TEMPORARY),
    VIRTUAL(WinNT.FILE_ATTRIBUTE_VIRTUAL),
    RECALL_ON_DATA_ACCESS(4194394),
    RECALL_ON_OPEN(262144);

    private final int maskingValue;

    public static MaskValueSet<FileAttribute> getSetFromInt(final int value) {
        return MaskValueSet.getSetFromInt(value, values());
    }

    FileAttribute(final int maskingValue) {
        this.maskingValue = maskingValue;
    }

    @Override
    public int intValue() {
        return this.maskingValue;
    }
}
