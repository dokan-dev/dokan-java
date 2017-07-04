package com.dokany.java.constants;

import com.dokany.java.DokanyUtils;
import com.sun.jna.platform.win32.WinNT;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum CreationDisposition implements EnumInteger {

    /*-
     *                          |                    When the file...
    This argument:           |             Exists            Does not exist
    -------------------------+------------------------------------------------------
    CREATE_ALWAYS            |            Truncates             Creates
    CREATE_NEW         +-----------+        Fails               Creates
    OPEN_ALWAYS     ===| does this |===>    Opens               Creates
    OPEN_EXISTING      +-----------+        Opens                Fails
    TRUNCATE_EXISTING        |            Truncates              Fails
     */

	CREATE_NEW(WinNT.CREATE_NEW, "Create New"),
	CREATE_ALWAYS(WinNT.CREATE_ALWAYS, "Create Always"),
	OPEN_EXISTING(WinNT.OPEN_EXISTING, "Open Existing"),
	OPEN_ALWAYS(WinNT.OPEN_ALWAYS, "Open Always"),
	TRUNCATE_EXISTING(WinNT.TRUNCATE_EXISTING, "Truncate Existing");

	@Getter
	int mask;

	@Getter
	String description;

	@Getter
	boolean isReadonly;

	private CreationDisposition(final int i, final String desc) {
		mask = i;
		description = desc;

		// TODO: Is this logic correct?
		isReadonly = ((mask == 3) || (mask == 4));
	}

	public static CreationDisposition fromInt(final int value) {
		return DokanyUtils.enumFromInt(value, values());
	}
}