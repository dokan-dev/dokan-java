package com.dokan.java.constants.microsoft.filesecurity;

import com.dokan.java.constants.EnumInteger;

/**
 * Enumeration of the different control flags that can be set in the header of a security descriptor
 * The documentation is taken from the official <a href=https://msdn.microsoft.com/en-us/library/cc230366.aspx>Microsoft doc</a>.
 */
public enum SecurityDescriptorControlFlag implements EnumInteger {

	/**
	 * Self-Relative
	 * Set when the security descriptor is in self-relative format. Cleared when the security descriptor is in absolute format.
	 */
	SR(1 << 15),

	/**
	 * RM Control Valid
	 * Set to 0x1 when the Sbz1 field is to be interpreted as resource manager control bits.
	 */
	RM(1 << 14),

	/**
	 * SACL Protected
	 * Set when the SACL will be protected from inherit operations.
	 */
	PS(1 << 13),

	/**
	 * DACL Protected
	 * Set when the DACL will be protected from inherit operations.
	 */
	PD(1 << 12),

	/**
	 * SACL Auto-Inherited
	 * Set when the SACL was created through inheritance.
	 */
	SI(1 << 11),

	/**
	 * DACL Auto-Inherited
	 * Set when the DACL was created through inheritance.
	 */
	DI(1 << 10),

	/**
	 * SACL Computed Inheritance Required
	 * Set when the SACL is to be computed through inheritance. When both SC and SI are set, the resulting security descriptor sets SI; the SC setting is not preserved.
	 */
	SC(1 << 9),

	/**
	 * DACL Computed Inheritance Required
	 * Set when the DACL is to be computed through inheritance. When both DC and DI are set, the resulting security descriptor sets DI; the DC setting is not preserved.
	 */
	DC(1 << 8),

	/**
	 * Server Security
	 * Set when the caller wants the system to create a Server ACL based on the input ACL, regardless of its source (explicit or defaulting).
	 */
	SS(1 << 7),

	/**
	 * DACL Trusted
	 * Set when the ACL that is pointed to by the DACL field was provided by a trusted source and does not require any editing of compound ACEs.
	 */
	DT(1 << 6),

	/**
	 * SACL Defaulted
	 * Set when the SACL was established by default means.
	 */
	SD(1 << 5),

	/**
	 * SACL Present
	 * Set when the SACL is present on the object.
	 */
	SP(1 << 4),

	/**
	 * DACL Defaulted
	 * Set when the DACL was established by default means.
	 */
	DD(1 << 3),

	/**
	 * DACL Present
	 * Set when the DACL is present on the object.
	 */
	DP(1 << 2),

	/**
	 * Group Defaulted
	 * Set when the group was established by default means.
	 */
	GD(1 << 1),

	/**
	 * Owner Defaulted
	 * Set when the owner was established by default means.
	 */
	OD(1 << 0);

	private final int mask;

	SecurityDescriptorControlFlag(int mask) {
		this.mask = mask;
	}

	@Override
	public int getMask() {
		return mask;
	}
}
