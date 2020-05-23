package dev.dokan.dokan_java.constants.microsoft;

import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.conv.MaskValueEnum;
import dev.dokan.dokan_java.conv.MaskValueSet;

/**
 * Additional {@link AccessMask} values specific to directories.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/nf-wdm-zwcreatefile">Microsoft documentation of ZwCreateFile</a>, Section Parameters, Parameter {@code DesiredAccess}
 */
public enum DirectoryAccessMask implements MaskValueEnum {
    LIST_DIRECTORY(WinNT.FILE_LIST_DIRECTORY),
    TRAVERSE(WinNT.FILE_TRAVERSE);

	private final int maskValue;

	DirectoryAccessMask(int maskValue) {
		this.maskValue = maskValue;
	}

	public static MaskValueSet<DirectoryAccessMask> getSetFromInt(final int value) {
		return MaskValueSet.getSetFromInt(value, values());
	}

	@Override
	public int intValue() {
		return this.maskValue;
	}
}
