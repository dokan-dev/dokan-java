package com.dokan.java.constants.microsoft;

import com.dokan.java.constants.EnumInteger;
import com.sun.jna.platform.win32.WinNT;

/**
 * Enumeration of the possible AccesMask options.
 * For more info see the <a href="https://msdn.microsoft.com/en-us/library/cc230294.aspx"> Microsoft Developer Documentation</a> or <a href="https://docs.microsoft.com/en-us/windows/desktop/SecAuthZ/access-mask">the normal documentation.</a>
 */
public enum AccessMask implements EnumInteger {
	/**
	 * GENERIC_READ
	 * When used in an Access Request operation: When read access to an object is requested, this bit is translated to a combination of bits. These are most often set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) The bits that are set are implementation dependent. During this translation, the GENERIC_READ bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are checked against the ACE structures in the security descriptor that attached to the object.
	 * <p>
	 * When used to set the Security Descriptor on an object: When the GENERIC_READ bit is set in an ACE that is to be attached to an object, it is translated into a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) The bits that are set are implementation dependent. During this translation, the GENERIC_READ bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are granted by this ACE.
	 */
	GENERIC_READ(WinNT.GENERIC_READ),


	/**
	 * GENERIC_WRITE
	 * When used in an Access Request operation: When write access to an object is requested, this bit is translated to a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) The bits that are set are implementation dependent. During this translation, the GENERIC_WRITE bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are checked against the ACE structures in the security descriptor that attached to the object.
	 * <p>
	 * When used to set the Security Descriptor on an object: When the GENERIC_WRITE bit is set in an ACE that is to be attached to an object, it is translated into a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) The bits that are set are implementation dependent. During this translation, the GENERIC_WRITE bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are granted by this ACE.
	 */
	GENERIC_WRITE(WinNT.GENERIC_WRITE),


	/**
	 * GENERIC_EXECUTE
	 * <p>
	 * When used in an Access Request operation: When execute access to an object is requested, this bit is translated to a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) The bits that are set are implementation dependent. During this translation, the GENERIC_EXECUTE bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are checked against the ACE structures in the security descriptor that attached to the object.
	 * <p>
	 * When used to set the Security Descriptor on an object: When the GENERIC_EXECUTE bit is set in an ACE that is to be attached to an object, it is translated into a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) The bits that are set are implementation dependent. During this translation, the GENERIC_EXECUTE bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are granted by this ACE.
	 */
	GENERIC_EXECUTE(WinNT.GENERIC_EXECUTE),


	/**
	 * GENERIC_ALL
	 * When used in an Access Request operation: When all access permissions to an object are requested, this bit is translated to a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) Objects are free to include bits from the upper 16 bits in that translation as required by the objects semantics. The bits that are set are implementation dependent. During this translation, the GENERIC_ALL bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are checked against the ACE structures in the security descriptor that attached to the object.
	 * <p>
	 * When used to set the Security Descriptor on an object: When the GENERIC_ALL bit is set in an ACE that is to be attached to an object, it is translated into a combination of bits, which are usually set in the lower 16 bits of the ACCESS_MASK. (Individual protocol specifications MAY specify a different configuration.) Objects are free to include bits from the upper 16 bits in that translation, if required by the objects semantics. The bits that are set are implementation dependent. During this translation, the GENERIC_ALL bit is cleared. The resulting ACCESS_MASK bits are the actual permissions that are granted by this ACE.
	 */
	GENERIC_ALL(WinNT.GENERIC_ALL),


	/**
	 * MAXIMUM_ALLOWED
	 * <p>
	 * When used in an Access Request operation: When requested, this bit grants the requestor the maximum permissions allowed to the object through the Access Check Algorithm. This bit can only be requested; it cannot be set in an ACE.
	 * <p>
	 * When used to set the Security Descriptor on an object: Specifying the Maximum Allowed bit in the SECURITY_DESCRIPTOR has no meaning. The MAXIMUM_ALLOWED bit SHOULD NOT be set and SHOULD be ignored when part of a SECURITY_DESCRIPTOR structure.
	 */
	MAXIMUM_ALLOWED(0x02000000L),


	/**
	 * ACCESS_SYSTEM_SECURITY
	 * When used in an Access Request operation: When requested, this bit grants the requestor the right to change the SACL of an object. This bit MUST NOT be set in an ACE that is part of a DACL. When set in an ACE that is part of a SACL, this bit controls auditing of accesses to the SACL itself.
	 */
	ACCESS_SYSTEM_SECURITY(WinNT.ACCESS_SYSTEM_SECURITY),


	/**
	 * SYNCHRONIZE
	 * <p>
	 * Specifies access to the object sufficient to synchronize or wait on the object.
	 */
	SYNCHRONIZE(WinNT.SYNCHRONIZE),


	/**
	 * WRITE_OWNER
	 * Specifies access to change the owner of the object as listed in the security descriptor.
	 */
	WRITE_OWNER(WinNT.WRITE_OWNER),


	/**
	 * WRITE_DAC
	 * Specifies access to change the discretionary access control list of the security descriptor of an object.
	 */
	WRITE_DAC(WinNT.WRITE_DAC),


	/**
	 * READ_CONTROL
	 * Specifies access to read the security descriptor of an object.
	 */
	READ_CONTROL(WinNT.READ_CONTROL),


	/**
	 * DELETE
	 * Specifies access to delete an object.
	 */
	DELETE(WinNT.DELETE);

	private int mask;

	AccessMask(long mask) {
		this.mask = (int) mask;
	}


	@Override
	public int getMask() {
		return mask;
	}
}
