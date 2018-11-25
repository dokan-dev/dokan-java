package com.dokan.java.legacy.constants;

import com.dokan.java.constants.EnumInteger;
import com.sun.jna.platform.win32.WinNT;

/**
 * Identifies the object-related security information being set or queried. This security information includes:
 * <ul>
 * <li>The owner of an object.</li>
 * <li>The primary group of an object.</li>
 * <li>The discretionary access control list(DACL) of an object.</li>
 * <li>The system access control list(SACL) of an object.</li>
 * </ul>
 * <p>
 * Structure taken from <a href="http://www.pinvoke.net/default.aspx/Enums/SECURITY_INFORMATION.html">pinvoke.net</a>
 *
 * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa379573(v=vs.85).aspx">SECURITY_INFORMATION (MSDN)</a>
 */
public enum SecurityInformation implements EnumInteger {
    OWNER_SECURITY_INFORMATION(WinNT.OWNER_SECURITY_INFORMATION, "The owner identifier of the object is being referenced"),
    GROUP_SECURITY_INFORMATION(WinNT.GROUP_SECURITY_INFORMATION, "The primary group identifier of the object is being referenced"),
    DACL_SECURITY_INFORMATION(WinNT.DACL_SECURITY_INFORMATION, "The DACL of the object is being referenced"),
    SACL_SECURITY_INFORMATION(WinNT.SACL_SECURITY_INFORMATION, "The SACL of the object is being referenced"),
    LABEL_SECURITY_INFORMATION(WinNT.LABEL_SECURITY_INFORMATION, "The mandatory integrity label is being referenced"),
    ATTRIBUTE_SECURITY_INFORMATION((int) 0x00000020L, "The resource properties of the object being referenced."),
    SCOPE_SECURITY_INFORMATION((int) 0x00000040L, "The Central Access Policy (CAP) identifier applicable on the object that is being referenced. Each CAP identifier is stored in a SYSTEM_SCOPED_POLICY_ID_ACE type in the SACL of the SD."),
    PROCESS_TRUST_LABEL_SECURITY_INFORMATION((int) 0x00000080L, "?"),
    ACCESS_FILTER_SECURITY_INFORMATION((int) 0x00000100L, "?"),
    BACKUP_SECURITY_INFORMATION((int) 0x00010000L, "?"),
    PROTECTED_SACL_SECURITY_INFORMATION(WinNT.PROTECTED_SACL_SECURITY_INFORMATION, "The SACL cannot inherit ACEs"),
    PROTECTED_DACL_SECURITY_INFORMATION(WinNT.PROTECTED_SACL_SECURITY_INFORMATION, "The DACL cannot inherit access control entries (ACEs)"),
    UNPROTECTED_SACL_SECURITY_INFORMATION(WinNT.UNPROTECTED_SACL_SECURITY_INFORMATION, "The SACL inherits ACEs from the parent object. Dokany may not be passing Label ?? 0x00000010"),
    UNPROTECTED_DACL_SECURITY_INFORMATION(WinNT.UNPROTECTED_DACL_SECURITY_INFORMATION, "The DACL inherits ACEs from the parent object");

    private final int mask;
    private final String description;

    SecurityInformation(final int mask, final String description) {
        this.mask = mask;
        this.description = description;
    }

    public int getMask() {
        return this.mask;
    }

    public String getDescription() {
        return this.description;
    }
}
