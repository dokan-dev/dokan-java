package dev.dokan.dokan_java.constants.microsoft;

import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.masking.MaskValueEnum;
import dev.dokan.dokan_java.masking.MaskValueSet;

/**
 * Additional {@link BasicAccessMaskFlag} values specific to directories.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/nf-wdm-zwcreatefile">Microsoft documentation of ZwCreateFile</a>, Section Parameters, Parameter {@code DesiredAccess}
 */
public enum DirectoryAccessMaskFlag implements MaskValueEnum {
    LIST_DIRECTORY(WinNT.FILE_LIST_DIRECTORY),
    TRAVERSE(WinNT.FILE_TRAVERSE);

	private final int maskingValue;

	DirectoryAccessMaskFlag(int maskingValue) {
		this.maskingValue = maskingValue;
	}

	public static MaskValueSet<DirectoryAccessMaskFlag> maskValueSet(final int mask) {
		return MaskValueSet.maskValueSet(mask, values());
	}

	@Override
	public int intValue() {
		return this.maskingValue;
	}
}
