package com.github.dokandev.dokanjava;

public class SECURITY_INFORMATION {
    static public final int OWNER_SECURITY_INFORMATION = 0x00000001;
    static public final int GROUP_SECURITY_INFORMATION = 0x00000002;
    static public final int DACL_SECURITY_INFORMATION = 0x00000004;
    static public final int SACL_SECURITY_INFORMATION = 0x00000008;
    static public final int UNPROTECTED_SACL_SECURITY_INFORMATION = 0x10000000;
    static public final int UNPROTECTED_DACL_SECURITY_INFORMATION = 0x20000000;
    static public final int PROTECTED_SACL_SECURITY_INFORMATION = 0x40000000;
    static public final int PROTECTED_DACL_SECURITY_INFORMATION = 0x8000000;
}