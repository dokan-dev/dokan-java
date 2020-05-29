package dev.dokan.dokan_java.constants.microsoft;

import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.conv.MaskValueEnum;
import dev.dokan.dokan_java.conv.MaskValueSet;

/**
 * Additional {@link AccessMask} values specific to files.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/nf-wdm-zwcreatefile">Microsoft documentation of ZwCreateFile</a>, Section Parameters, Parameter {@code DesiredAccess}
 */
public enum FileAccessMask implements MaskValueEnum {
    READ_DATA(WinNT.FILE_READ_DATA),
    READ_ATTRIBUTES(WinNT.FILE_READ_ATTRIBUTES),
    READ_EA(WinNT.FILE_READ_EA),
    WRITE_DATA(WinNT.FILE_WRITE_DATA),
    WRITE_ATTRIBUTES(WinNT.FILE_WRITE_ATTRIBUTES),
    WRITE_EA(WinNT.FILE_WRITE_EA),
    APPEND_DATA(WinNT.FILE_APPEND_DATA),
    EXECUTE(WinNT.FILE_EXECUTE);

    private final int maskingValue;

    FileAccessMask(int maskingValue) {
        this.maskingValue = maskingValue;
    }

    public static MaskValueSet<FileAccessMask> getSetFromInt(final int value) {
        return MaskValueSet.getSetFromInt(value, values());
    }

    @Override
    public int intValue() {
        return this.maskingValue;
    }
}
