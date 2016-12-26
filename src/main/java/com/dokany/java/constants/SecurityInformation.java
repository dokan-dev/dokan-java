package com.dokany.java.constants;

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
public enum SecurityInformation {
    // The owner identifier of the object is being referenced.
	OWNER_SECURITY_INFORMATION(0x00000001),

    // The primary group identifier of the object is being referenced.
	GROUP_SECURITY_INFORMATION(0x00000002),

    // The DACL of the object is being referenced.
	DACL_SECURITY_INFORMATION(0x00000004),

    // The SACL of the object is being referenced.
	SACL_SECURITY_INFORMATION(0x00000008),

    // The SACL inherits ACEs from the parent object. Dokan may not be passing Label ?? 0x00000010
	UNPROTECTED_SACL_SECURITY_INFORMATION(0x10000000),

    // The DACL inherits ACEs from the parent object.
	UNPROTECTED_DACL_SECURITY_INFORMATION(0x20000000),

    // The SACL cannot inherit ACEs.
	PROTECTED_SACL_SECURITY_INFORMATION(0x40000000),

    // The DACL cannot inherit access control entries (ACEs).
	PROTECTED_DACL_SECURITY_INFORMATION(0x8000000);

	public final int val;

	private SecurityInformation(final int i) {
		val = i;
	}
}