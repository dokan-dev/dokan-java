package com.dokan.java.constants.microsoft;

import com.dokan.java.constants.EnumInteger;
import com.sun.jna.platform.win32.WinNT;

/**
 * Additional {@link AccessMask} values specific to directories.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/nf-wdm-zwcreatefile">Microsoft documentation of ZwCreateFile</a>, Section Parameters, Parameter {@code DesiredAccess}
 */
public enum DirectoryAccessMask implements EnumInteger {
    LIST_DIRECTORY(WinNT.FILE_LIST_DIRECTORY),
    TRAVERSE(WinNT.FILE_TRAVERSE);

    private final int mask;

    DirectoryAccessMask(int mask) {
        this.mask = mask;
    }

    @Override
    public int getMask() {
        return mask;
    }
}
