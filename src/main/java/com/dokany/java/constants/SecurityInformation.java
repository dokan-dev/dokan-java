package com.dokany.java.constants;

import com.sun.jna.platform.win32.WinNT;

/**
 * Identifies the object-related security information being set or queried. This security information includes:
 * <ul>
 * <li>The owner of an object.</li>
 * <li>The primary group of an object.></li>
 * <li>The discretionary access control list(DACL) of an object.</li>
 * <li>The system access control list(SACL) of an object.</li>
 * </ul>
 *
 * Structure taken from <a href="http://www.pinvoke.net/default.aspx/Enums/SECURITY_INFORMATION.html">pinvoke.net</a>
 *
 * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa379573(v=vs.85).aspx">SECURITY_INFORMATION (MSDN)</a>
 *
 */
public enum SecurityInformation implements EnumInteger {
	OWNER_SECURITY_INFORMATION(WinNT.OWNER_SECURITY_INFORMATION, "The owner identifier of the object is being referenced"),

	GROUP_SECURITY_INFORMATION(WinNT.GROUP_SECURITY_INFORMATION, "The primary group identifier of the object is being referenced"),

	DACL_SECURITY_INFORMATION(WinNT.DACL_SECURITY_INFORMATION, "The DACL of the object is being referenced"),

	SACL_SECURITY_INFORMATION(WinNT.SACL_SECURITY_INFORMATION, "The SACL of the object is being referenced"),

	LABEL_SECURITY_INFORMATION(WinNT.LABEL_SECURITY_INFORMATION, "The mandatory integrity label is being referenced"),

	UNPROTECTED_SACL_SECURITY_INFORMATION(
	        WinNT.UNPROTECTED_SACL_SECURITY_INFORMATION,
	        "The SACL inherits ACEs from the parent object. Dokany may not be passing Label ?? 0x00000010"),

	UNPROTECTED_DACL_SECURITY_INFORMATION(WinNT.UNPROTECTED_DACL_SECURITY_INFORMATION, "The DACL inherits ACEs from the parent object"),

	PROTECTED_SACL_SECURITY_INFORMATION(WinNT.PROTECTED_SACL_SECURITY_INFORMATION, "The SACL cannot inherit ACEs"),

	PROTECTED_DACL_SECURITY_INFORMATION(WinNT.PROTECTED_SACL_SECURITY_INFORMATION, "The DACL cannot inherit access control entries (ACEs)");

	private final int mask;

	@Override
	public int mask() {
		return mask;
	}

	private final String description;

	public String description() {
		return description;
	}

	private SecurityInformation(final int i, final String desc) {
		mask = i;
		description = desc;
	}
}