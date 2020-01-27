package dev.dokan.dokan_java.structure.filesecurity;

import dev.dokan.dokan_java.Byteable;
import dev.dokan.dokan_java.constants.microsoft.filesecurity.AccessControlEntryType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Objectoriented implementation of the ACL-structure used in a {@link SelfRelativeSecurityDescriptor}.
 * For more information, please read the <a href="https://msdn.microsoft.com/en-us/library/cc230297.aspx">official Microsoft documentation</a>.
 */
public class AccessControlList implements Byteable {

	private enum ACLType {
		DACL,
		SACL;
	}

	private final ACLType aclType;

	private static final AccessControlEntryType[] allowedACEsForDaclRev2 = new AccessControlEntryType[]{
			AccessControlEntryType.ACCESS_ALLOWED_ACE_TYPE, AccessControlEntryType.ACCESS_DENIED_ACE_TYPE
	};

	private static final AccessControlEntryType[] allowedACEsForDaclRev4 = new AccessControlEntryType[]{
			AccessControlEntryType.ACCESS_ALLOWED_COMPOUND_ACE_TYPE, AccessControlEntryType.ACCESS_ALLOWED_OBJECT_ACE_TYPE, AccessControlEntryType.ACCESS_DENIED_OBJECT_ACE_TYPE
	};

	private static final AccessControlEntryType[] allowedACEsForSaclRev2 = new AccessControlEntryType[]{
			AccessControlEntryType.SYSTEM_AUDIT_ACE_TYPE, AccessControlEntryType.SYSTEM_ALARM_ACE_TYPE, AccessControlEntryType.SYSTEM_MANDATORY_LABEL_ACE_TYPE, AccessControlEntryType.SYSTEM_RESOURCE_ATTRIBUTE_ACE_TYPE, AccessControlEntryType.SYSTEM_SCOPED_POLICY_ID_ACE_TYPE
	};

	private static final AccessControlEntryType[] allowedACEsForSaclRev4 = new AccessControlEntryType[]{
			AccessControlEntryType.SYSTEM_AUDIT_OBJECT_ACE_TYPE, AccessControlEntryType.SYSTEM_ALARM_OBJECT_ACE_TYPE, AccessControlEntryType.SYSTEM_MANDATORY_LABEL_ACE_TYPE
	};

	/**
	 * AclRevision
	 * An unsigned 8-bit value that specifies the revision of the ACL. The only two legitimate forms of ACLs supported for on-the-wire management or manipulation are type 2 and type 4. No other form is valid for manipulation on the wire.
	 */
	private final byte aclRevision;

	/**
	 * Sbz1
	 * An unsigned 8-bit value. This field is reserved and MUST be set to zero.
	 */
	private final byte sbz1 = 0;

	/**
	 * Sbz2
	 * An unsigned 16-bit integer. This field is reserved and MUST be set to zero.
	 */
	private final short sbz2 = 0;

	/**
	 * List of AccessControlEntrys in this ACL
	 */
	private List<AccessControlEntry> aces;

	private AccessControlList(ACLType aclType, byte aclRevision, List<? extends AccessControlEntry> aces) {
		this.aclType = aclType;
		this.aclRevision = aclRevision;
		this.aces = new ArrayList<>(15);
		this.aces.addAll(aces);
	}


	@Override
	public byte[] toByteArray() {
		ByteBuffer buf = ByteBuffer.allocate(sizeOfByteArray());
		buf.put(aclRevision);
		buf.put(sbz1);
		buf.putShort(Short.reverseBytes((short) sizeOfByteArray()));
		buf.putShort(Short.reverseBytes((short) aces.size()));
		buf.putShort(Short.reverseBytes(sbz2));
		for (AccessControlEntry ace : aces) {
			buf.put(ace.toByteArray());
		}
		return buf.array();
	}

	@Override
	public int sizeOfByteArray() {
		return 1 // aclRevision
				+ 1 //sbz1
				+ 2 // aclSize
				+ 2 //ace count
				+ 2 //sbz2
				+ aces.stream().reduce(0, (sum, ace) -> sum + ace.sizeOfByteArray(), Integer::sum);
	}

	public static AccessControlList createDaclRevision2(List<? extends AccessControlEntry> aces) {
		for (AccessControlEntry ace : aces) {
			if (!isValidAce(ace.type, allowedACEsForDaclRev2)) {
				//we found an invalid ace
				//aborting
				return null;
			}
		}
		return new AccessControlList(ACLType.DACL, (byte) 0x02, aces);
	}

	public static AccessControlList createDaclRevision4(List<? extends AccessControlEntry> aces) {
		for (AccessControlEntry ace : aces) {
			if (!isValidAce(ace.type, allowedACEsForDaclRev4)) {
				//we found an invalid ace
				//aborting
				return null;
			}
		}
		return new AccessControlList(ACLType.DACL, (byte) 0x04, aces);
	}

	public static AccessControlList createSaclRevision2(List<? extends AccessControlEntry> aces) {
		for (AccessControlEntry ace : aces) {
			if (!isValidAce(ace.type, allowedACEsForSaclRev2)) {
				//we found an invalid ace
				//aborting
				return null;
			}
		}
		return new AccessControlList(ACLType.SACL, (byte) 0x02, aces);
	}

	public static AccessControlList createSaclRevision4(List<? extends AccessControlEntry> aces) {
		for (AccessControlEntry ace : aces) {
			if (!isValidAce(ace.type, allowedACEsForSaclRev4)) {
				//we found an invalid ace
				//aborting
				return null;
			}
		}
		return new AccessControlList(ACLType.SACL, (byte) 0x04, aces);
	}

	private static boolean isValidAce(AccessControlEntryType aceType, AccessControlEntryType[] validACEs) {
		for (AccessControlEntryType validAceType : validACEs) {
			if (aceType.ordinal() == validAceType.ordinal()) {
				return true;
			}
		}
		return false;
	}

}
