package com.dokany.java.constants;

import com.sun.jna.platform.win32.WinNT;

/**
 *
 * Defines standard, specific, and generic rights. These rights are used in access control entries (ACEs) and are the primary means of specifying the requested or granted access to
 * an object.
 *
 * @see @{linkplain https://msdn.microsoft.com/en-us/library/windows/desktop/aa374896(v=vs.85).aspx}
 * @see @{linkplain https://msdn.microsoft.com/en-us/library/windows/desktop/aa374892(v=vs.85).aspx}
 */
public enum FileAccess {

	/**
	 * Read access right to an object.
	 *
	 * nativeconst{FILE_READ_DATA,0x00000001,File & pipe}
	 *
	 * nativeconst{FILE_LIST_DIRECTORY,0x00000001,Directory}
	 */
	READ_DATA(WinNT.FILE_READ_DATA),

	/**
	 * Write access right to an object.
	 *
	 * nativeconst{FILE_WRITE_DATA,0x00000002,File & pipe}
	 *
	 * nativeconst{FILE_ADD_FILE,0x00000002,Directory}
	 */
	WRITE_DATA(WinNT.FILE_WRITE_DATA),

	/**
	 * For a file object, the right to append data to the file.
	 *
	 * nativeconst{FILE_APPEND_DATA,0x00000004,File}
	 *
	 * nativeconst{FILE_ADD_SUBDIRECTORY,0x00000004,Directory}
	 *
	 * nativeconst{FILE_CREATE_PIPE_INSTANCE,0x00000004,Named pipe}
	 */
	APPEND_DATA(WinNT.FILE_APPEND_DATA),

	/**
	 * The right to read extended file attributes.
	 */
	READ_EXTENDED_ATTRIBUTES(WinNT.FILE_READ_EA),

	/**
	 * The right to write extended file attributes.
	 *
	 * nativeconst{FILE_WRITE_EA,0x00000010,File & directory}
	 */
	WRITE_EXTENDED_ATTRIBUTES(1L << 4),

	/**
	 * For a native code file, the right to execute the file. This access right given to scripts may cause the script to be executable, depending on the script interpreter.
	 */
	EXECUTE(WinNT.FILE_EXECUTE),

	/**
	 * For a directory, the right to delete a directory and all the files it contains, including read-only files.
	 */
	DELETE_CHILD(WinNT.FILE_DELETE_CHILD),

	/**
	 * The right to read file attributes.
	 */
	READ_ATTRIBUTES(WinNT.FILE_READ_ATTRIBUTES),

	/**
	 * The right to write file attributes.
	 */
	WRITE_ATTRIBUTES(WinNT.FILE_WRITE_ATTRIBUTES),

	/**
	 * The right to delete the object.
	 */
	DELETE(WinNT.DELETE),

	/**
	 * The right to read the information in the object's security descriptor, not including the information in the system access control list (SACL).
	 *
	 * nativeconst{READ_CONTROL,0x00020000}
	 */
	READ_PERMISSIONS(WinNT.READ_CONTROL),

	/**
	 * The right to modify the discretionary access control list (DACL) in /// the object's security descriptor.
	 *
	 * nativeconst{WRITE_DAC,0x00040000}
	 */
	CHANGE_PERMISSIONS(WinNT.WRITE_DAC),

	/**
	 * The right to change the owner in the object's security descriptor.
	 *
	 * nativeconst{WRITE_OWNER,0x00080000}
	 */
	SET_OWNERSHIP(WinNT.WRITE_OWNER),

	/**
	 * The right to use the object for synchronization. This enables a thread to wait until the object is in the signaled state. Some object types do not support this access right.
	 *
	 * nativeconst{SYNCHRONIZE,0x00100000}
	 */
	SYNCHRONIZE(WinNT.SYNCHRONIZE),

	/**
	 * It is used to indicate access to a system access control list (SACL). This type of access requires the calling process to have the <i>SE_SECURITY_NAME</i> (Manage auditing
	 * and security log) privilege. If this flag is set in the access mask of an audit access ACE (successful or unsuccessful access), the SACL access will be audited.
	 *
	 * nativeconst{ACCESS_SYSTEM_SECURITY,0x01000000}
	 */
	ACCESS_SYSTEM_SECURITY(WinNT.ACCESS_SYSTEM_SECURITY),

	/**
	 * Obsolete, use {@link #ACCESS_SYSTEM_SECURITY} instead.
	 */
	@Deprecated
	RESERVED(ACCESS_SYSTEM_SECURITY.val),

	/**
	 * All the access rights that are valid for the caller.
	 *
	 * nativeconst{MAXIMUM_ALLOWED,0x02000000}
	 */
	MAXIMUM_ALLOWED(1L << 25),

	/**
	 * All possible access rights.
	 *
	 * nativeconst{GENERIC_ALL,0x10000000}
	 */
	GENERIC_ALL(WinNT.GENERIC_ALL),

	/**
	 * Generic execute access.
	 *
	 * nativeconst{GENERIC_EXECUTE,0x20000000}
	 */
	GENERIC_EXECUTE(WinNT.GENERIC_EXECUTE),

	/**
	 * Generic write access.
	 *
	 * nativeconst{GENERIC_WRITE,0x40000000}
	 */
	GENERIC_WRITE(WinNT.GENERIC_WRITE),

	/**
	 * Generic read access.
	 *
	 * nativeconst{GENERIC_READ,0x80000000}
	 */
	GENERIC_READ(WinNT.GENERIC_READ);

	public final long val;

	private FileAccess(final long i) {
		val = i;
	}

	public final static long MASK;

	static {
		MASK = READ_DATA.val | WRITE_DATA.val | APPEND_DATA.val
		        | READ_EXTENDED_ATTRIBUTES.val | WRITE_EXTENDED_ATTRIBUTES.val | EXECUTE.val
		        | DELETE_CHILD.val | READ_ATTRIBUTES.val | WRITE_ATTRIBUTES.val
		        | DELETE.val | READ_PERMISSIONS.val | CHANGE_PERMISSIONS.val
		        | SET_OWNERSHIP.val | SYNCHRONIZE.val | ACCESS_SYSTEM_SECURITY.val
		        | MAXIMUM_ALLOWED.val | GENERIC_ALL.val | GENERIC_EXECUTE.val
		        | GENERIC_WRITE.val | GENERIC_READ.val;
	}

	public final static long fromAttributesAndFlags(final int rawDesiredAccess) {
		return (rawDesiredAccess & MASK);
	}
}
