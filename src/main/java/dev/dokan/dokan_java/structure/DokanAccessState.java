package dev.dokan.dokan_java.structure;


import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.Unsigned;

import java.util.Arrays;
import java.util.List;


/**
 * This is a Dokan specific implementation of the ACCESS_STATE structure of the windows kernel.
 *
 * @see <a href="https://docs.microsoft.com/de-de/windows-hardware/drivers/ddi/wdm/ns-wdm-_access_state">Microsoft Documentation</a>
 * @see <a href="https://github.com/dokan-dev/dokany/blob/master/sys/public.h>Definition in {@code public.h}</a> of the Dokany project.
 */
public class DokanAccessState extends Structure {

	/**
	 * A boolean value that specifies whether security was evaluated as part of the access check.
	 * This member is currently unused by drivers.
	 */
	public boolean SecurityEvaluated;

	/**
	 * A Boolean value that specifies whether the access should generate an audit.
	 * This member is currently unused by drivers.
	 */
	public boolean GenerateAudit;

	/**
	 * A Boolean value that specifies whether an audit should be generated when the handle being created is closed.
	 * This member is currently unused by drivers.
	 */
	public boolean GenerateOnClose;

	/**
	 * A Boolean value that specifies whether a privilege usage should be audited.
	 * This member is currently unused by drivers.
	 */
	public boolean AuditPrivileges;

	/**
	 * A 32-bit value that contains bit-field flags for the access.
	 * A driver can check for the traverse access flag (TOKEN_HAS_TRAVERSE_PRIVILEGE).
	 * For more information about how to check for traverse access, see <a href="https://docs.microsoft.com/windows-hardware/drivers/ifs/checking-for-traverse-privilege-on-irp-mj-create">Check for Traverse Privilege on IRP_MJ_CREATE</a>.
	 * A driver can also check for the TOKEN_IS_RESTRICTED flag.
	 * These flags are defined in Ntifs.h.
	 */
	@Unsigned
	public int Flags;

	/**
	 * An ACCESS_MASK type that describes the access rights that have not yet been granted to the caller.
	 * A driver uses this member to determine if the Windows security system can grant access.
	 * If access can be granted, the driver updates the PreviouslyGrantedAccess and RemainingDesiredAccess members accordingly.
	 */
	@Unsigned
	public int RemainingDesiredAccess;

	/**
	 * An ACCESS_MASK type that specifies the information about access that has already been granted to the caller of one of the <a href="https://docs.microsoft.com/previous-versions/windows/hardware/drivers/ff563711(v=vs.85)">Security Reference Monitor Routines</a>
	 * The Windows security system grants certain rights based on the privileges of the caller, such as traverse right (the ability to traverse through a directory as part of opening a subdirectory or file).
	 */
	@Unsigned
	public int PreviouslyGrantedAccess;

	/**
	 * An ACCESS_MASK type that contains the original access rights that were requested by the caller.
	 */
	@Unsigned
	public int OriginalDesiredAccess;

	/**
	 * A self relative security descriptor that contains security information for the object that this access relates to.
	 */
	public WinNT.SECURITY_DESCRIPTOR_RELATIVE SecurityDescriptor;

	/**
	 * A UNICODE_STRING structure that contains the object name string for the access. This member is used for auditing.
	 */
	public UnicodeString ObjectName;

	/**
	 * A UNICODE_STRING structure that contains the object type name string for the access. This member is used for auditing.
	 */
	public UnicodeString ObjectType;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(new String[]{"SecurityEvaluated",
				"GenerateAudit",
				"GenerateOnClose",
				"AuditPrivileges",
				"Flags",
				"RemainingDesiredAccess",
				"PreviouslyGrantedAccess",
				"OriginalDesiredAccess",
				"SecurityDescriptor",
				"ObjectName",
				"ObjectType"});
	}
}
