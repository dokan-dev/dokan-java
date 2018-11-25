package com.dokan.java.structure.filesecurity;

import com.dokan.java.Byteable;
import com.dokan.java.constants.microsoft.filesecurity.SecurityDescriptorControlFlag;
import com.dokan.java.structure.EnumIntegerSet;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * Class implementing the self-relative SelfRelativeSecurityDescriptor structure described in the <a href="https://msdn.microsoft.com/en-us/library/cc230366.aspx">Microsoft documentation</a>. The following is a copy of the documentation:
 * <p>
 * The SECURITY_DESCRIPTOR structure defines the security attributes of an object. These attributes specify who owns the object; who can access the object and what they can do with it; what level of audit logging can be applied to the object; and what kind of restrictions apply to the use of the security descriptor.
 * <p>
 * Security descriptors appear in one of two forms, absolute or self-relative.
 * <p>
 * A security descriptor is said to be in absolute format if it stores all of its security information via pointer fields, as specified in the RPC representation in section 2.4.6.1.
 * <p>
 * A security descriptor is said to be in self-relative format if it stores all of its security information in a contiguous block of memory and expresses all of its pointer fields as offsets from its beginning. The order of appearance of pointer target fields is not required to be in any particular order; the location of  the OwnerSid, GroupSid, Sacl, and/or Dacl is only based on OffsetOwner, OffsetGroup, OffsetSacl, and/or OffsetDacl pointers found in the fixed portion of the relative security descriptor.
 * <p>
 * The self-relative form of the security descriptor is required if one wants to transmit the SECURITY_DESCRIPTOR structure as an opaque data structure for transmission in communication protocols over a wire, or for storage on secondary media; the absolute form cannot be transmitted because it contains pointers to objects that are generally not accessible to the recipient.
 * <p>
 * When a self-relative security descriptor is transmitted over a wire, it is sent in little-endian format and requires no padding.
 */
public class SelfRelativeSecurityDescriptor implements Byteable {

	private static final byte[] EMPTY = new byte[0];

	/**
	 * Revision
	 * An unsigned 8-bit value that specifies the revision of the SECURITY_DESCRIPTOR structure. This field MUST be set to one.
	 */
	private final byte revision = (byte) 0x01;

	/**
	 * Sbz1
	 * An unsigned 8-bit value with no meaning unless the Control RM bit is set to 0x1. If the RM bit is set to 0x1, Sbz1 is interpreted as the resource manager control bits that contain specific information<70> for the specific resource manager that is accessing the structure. The permissible values and meanings of these bits are determined by the implementation of the resource manager.
	 */
	private final byte sbz1 = (byte) 0x00;

	/**
	 * Control
	 * An unsigned 16-bit field that specifies control access bit flags. The Self Relative (SR) bit MUST be set when the security descriptor is in self-relative format, represented by a EnumIntegerSet of ControlFlags
	 *
	 * @see EnumIntegerSet
	 * @see SecurityDescriptorControlFlag
	 */
	private EnumIntegerSet<SecurityDescriptorControlFlag> control;

	/**
	 * OwnerSid
	 * The SID of the owner of the object. The length of the SID MUST be a multiple of 4. This field MUST be present if the OffsetOwner field is not zero.
	 * <p>
	 * This implementation guarantees the existence of a SID if the offset is not zero by only writing an offset greater zero if this structure is present.
	 *
	 * @see SecurityIdentifier
	 */
	private Optional<SecurityIdentifier> ownerSid;


	/**
	 * GroupSid
	 * The SID of the group of the object. The length of the SID MUST be a multiple of 4. This field MUST be present if the OffsetOwner field is not zero.
	 * <p>
	 * This implementation guarantees the existence of a SID if the offset is not zero by only writing an offset greater zero if this structure is present.
	 *
	 * @see SecurityIdentifier
	 */
	private Optional<SecurityIdentifier> groupSid;

	/**
	 * Sacl
	 * The SACL of the object. The length of the SID MUST be a multiple of 4. This field MUST be present if the SP flag is set.
	 * <p>
	 * This implementation guarantees the existence of a SACL if SP-flag is set by only writing the flag if this strucutre is present.
	 */
	private Optional<AccessControlList> sacl;

	/**
	 * Dacl
	 * The DACL of the object. The length of the SID MUST be a multiple of 4. This field MUST be present if the DP flag is set.
	 * <p>
	 * This implementation guarantees the existence of a DACL if DP-flag is set by only writing the flag if this strucutre is present.
	 */
	private Optional<AccessControlList> dacl;

	/**
	 * Creates an empty SecurtiyDescriptor.
	 *
	 * @param control
	 */
	private SelfRelativeSecurityDescriptor(EnumIntegerSet<SecurityDescriptorControlFlag> control) {
		this.control = control;
		this.ownerSid = Optional.empty();
		this.groupSid = Optional.empty();
		this.sacl = Optional.empty();
		this.dacl = Optional.empty();
	}

	private SelfRelativeSecurityDescriptor(EnumIntegerSet<SecurityDescriptorControlFlag> control, SecurityIdentifier ownerSid, SecurityIdentifier groupSid, AccessControlList sacl, AccessControlList dacl) {
		this.control = control;
		if (ownerSid != null) {
			this.ownerSid = Optional.of(ownerSid);
		} else {
			this.ownerSid = Optional.empty();
		}
		if (groupSid != null) {
			this.groupSid = Optional.of(groupSid);
		} else {
			this.groupSid = Optional.empty();
		}
		if (sacl != null) {
			this.sacl = Optional.of(sacl);
		} else {
			this.sacl = Optional.empty();
		}
		if (dacl != null) {
			this.dacl = Optional.of(dacl);
		} else {
			this.dacl = Optional.empty();
		}
	}

	@Override
	public byte[] toByteArray() {
		int offset = 20; // basic header offset: revision, sbz1, controlmask and the four offsets
		int offsetOwner = 0, offsetGroup = 0, offsetSacl = 0, offsetDacl = 0;
		if (ownerSid.isPresent()) {
			offsetOwner = offset;
			offset += ownerSid.get().sizeOfByteArray();
		}
		if (groupSid.isPresent()) {
			offsetGroup = offset;
			offset += groupSid.get().sizeOfByteArray();
		}
		if (sacl.isPresent()) {
			offsetSacl = offset;
			offset += sacl.get().sizeOfByteArray();
		}
		if (dacl.isPresent()) {
			offsetDacl = offset;
			offset += dacl.get().sizeOfByteArray(); // not really necessary
		}
		//do some computations of the size
		ByteBuffer buf = ByteBuffer.allocate(sizeOfByteArray());
		buf.put(revision);
		buf.put(sbz1);
		buf.putShort(Short.reverseBytes((short) control.toInt()));
		buf.putInt(Integer.reverseBytes(offsetOwner));
		buf.putInt(Integer.reverseBytes(offsetGroup));
		buf.putInt(Integer.reverseBytes(offsetSacl));
		buf.putInt(Integer.reverseBytes(offsetDacl));
		buf.put(ownerSid.map(SecurityIdentifier::toByteArray).orElse(EMPTY));
		buf.put(groupSid.map(SecurityIdentifier::toByteArray).orElse(EMPTY));
		buf.put(sacl.map(AccessControlList::toByteArray).orElse(EMPTY));
		buf.put(dacl.map(AccessControlList::toByteArray).orElse(EMPTY));
		return buf.array();
	}

	@Override
	public int sizeOfByteArray() {
		return 2 // the first fixed bytes (revision and sbz1)
				+ 2 // the 16bit big  control mask
				+ 4 * 4 // the 4 32bit integer offset values indicating the offset to the following varaible length data fields
				+ ownerSid.map(SecurityIdentifier::sizeOfByteArray).orElse(0)
				+ groupSid.map(SecurityIdentifier::sizeOfByteArray).orElse(0)
				+ sacl.map(AccessControlList::sizeOfByteArray).orElse(0)
				+ dacl.map(AccessControlList::sizeOfByteArray).orElse(0);
	}

	public static SelfRelativeSecurityDescriptor createEmptySD(EnumIntegerSet<SecurityDescriptorControlFlag> flags) {
		if ((flags.toInt() & (SecurityDescriptorControlFlag.DP.getMask() | SecurityDescriptorControlFlag.SP.getMask())) == 0) {
			flags.add(SecurityDescriptorControlFlag.SR);
			return new SelfRelativeSecurityDescriptor(flags);
		} else {
			//wrong control flags, abort
			return null;
		}
	}

	public static SelfRelativeSecurityDescriptor createSD(EnumIntegerSet<SecurityDescriptorControlFlag> flags, SecurityIdentifier owner, SecurityIdentifier group, AccessControlList sacl, AccessControlList dacl) {
		int controlMask = flags.toInt();
		if ((controlMask & SecurityDescriptorControlFlag.DP.getMask()) != 0 && dacl == null) {
			//abort
			return null;
		}
		if ((controlMask & SecurityDescriptorControlFlag.SP.getMask()) != 0 && sacl == null) {
			//abort
			return null;
		}
		flags.add(SecurityDescriptorControlFlag.SR);
		return new SelfRelativeSecurityDescriptor(flags, owner, group, sacl, dacl);


	}

}
