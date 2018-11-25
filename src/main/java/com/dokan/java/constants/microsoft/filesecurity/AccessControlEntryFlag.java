package com.dokan.java.constants.microsoft.filesecurity;

import com.dokan.java.constants.EnumInteger;

/**
 * Enumeration of the different ACE control flags.
 * <p>
 * From the <a href="https://msdn.microsoft.com/en-us/library/cc230296.aspx">Microsoft documentation</a>: An unsigned 8-bit integer that specifies a set of ACE type-specific control flags. This field can be a combination of the following values.
 */
public enum AccessControlEntryFlag implements EnumInteger {

	/**
	 * Child objects that are containers, such as directories, inherit the ACE as an effective ACE. The inherited ACE is inheritable unless the NO_PROPAGATE_INHERIT_ACE bit flag is also set.
	 */
	CONTAINER_INHERIT_ACE(0x02),


	/**
	 * Used with system-audit ACEs in a system access control list (SACL) to generate audit messages for failed access attempts.
	 */
	FAILED_ACCESS_ACE_FLAG(0x80),


	/**
	 * Indicates an inherit-only ACE, which does not control access to the object to which it is attached. If this flag is not set, the ACE is an effective ACE that controls access to the object to which it is attached.
	 * Both effective and inherit-only ACEs can be inherited depending on the state of the other inheritance flags.
	 */
	INHERIT_ONLY_ACE(0x08),


	/**
	 * Indicates that the ACE was inherited. The system sets this bit when it propagates an inherited ACE to a child object.
	 */
	INHERITED_ACE(0x10),


	/**
	 * If the ACE is inherited by a child object, the system clears the OBJECT_INHERIT_ACE and CONTAINER_INHERIT_ACE flags in the inherited ACE. This prevents the ACE from being inherited by subsequent generations of objects.
	 */
	NO_PROPAGATE_INHERIT_ACE(0x04),


	/**
	 * Noncontainer child objects inherit the ACE as an effective ACE.
	 * For child objects that are containers, the ACE is inherited as an inherit-only ACE unless the NO_PROPAGATE_INHERIT_ACE bit flag is also set.
	 */
	OBJECT_INHERIT_ACE(0x01),


	/**
	 * Used with system-audit ACEs in a SACL to generate audit messages for successful access attempts.
	 */
	SUCCESSFUL_ACCESS_ACE_FLAG(0x40);


	private final int mask;

	AccessControlEntryFlag(int mask) {
		this.mask = mask;
	}


	@Override
	public int getMask() {
		return mask;
	}
}
