package com.dokan.java.constants.microsoft;

import com.dokan.java.constants.EnumInteger;
import com.sun.jna.platform.win32.WinNT;

/**
 * Additional {@link AccessMask} values specific to files.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/nf-wdm-zwcreatefile">Microsoft documentation of ZwCreateFile</a>, Section Parameters, Parameter {@code DesiredAccess}
 */
public enum FileAccessMask implements EnumInteger {
    READ_DATA(WinNT.FILE_READ_DATA),
    READ_ATTRIBUTES(WinNT.FILE_READ_ATTRIBUTES),
    READ_EA(WinNT.FILE_READ_EA),
    WRITE_DATA(WinNT.FILE_WRITE_DATA),
    WRITE_ATTRIBUTES(WinNT.FILE_WRITE_ATTRIBUTES),
    WRITE_EA(WinNT.FILE_WRITE_EA),
    APPEND_DATA(WinNT.FILE_APPEND_DATA),
    EXECUTE(WinNT.FILE_EXECUTE);

    private final int mask;

    FileAccessMask(int mask) {
        this.mask = mask;
    }

    @Override
    public int getMask() {
        return mask;
    }
}
