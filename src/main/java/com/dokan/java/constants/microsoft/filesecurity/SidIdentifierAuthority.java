package com.dokan.java.constants.microsoft.filesecurity;

import com.dokan.java.Byteable;

/**
 * Enumeration of Well known SidIdentifierAuthoritys
 */
public enum SidIdentifierAuthority implements Byteable {

	/**
	 * Specifies the NULL SID authority. It defines only the NULL well-known-SID: S-1-0-0.
	 */
	NULL_SID_AUTHORITY(0),

	/**
	 * Specifies the World SID authority. It only defines the Everyone well-known-SID: S-1-1-0.
	 */
	WORLD_SID_AUTHORITY(1),

	/**
	 * Specifies the Local SID authority. It defines only the Local well-known-SID: S-1-2-0.
	 */
	LOCAL_SID_AUTHORITY(2),

	/**
	 * Specifies the Creator SID authority. It defines the Creator Owner, Creator Group, and Creator Owner Server well-known-SIDs: S-1-3-0, S-1-3-1, and S-1-3-2. These SIDs are used as placeholders in an access control list (ACL) and are replaced by the user, group, and machine SIDs of the security principal.
	 */
	CREATOR_SID_AUTHORITY(3),

	/**
	 * Not used.
	 */
	NON_UNIQUE_AUTHORITY(4),

	/**
	 * Specifies the Windows NT operating system security subsystem SID authority. It defines all other SIDs in the forest.
	 */
	SECURITY_NT_AUTHORITY(5),

	/**
	 * Specifies the application package authority. It defines application capability SIDs.
	 */
	SECURITY_APP_PACKAGE_AUTHORITY(15),

	/**
	 * Specifies the Mandatory label authority. It defines the integrity level SIDs.
	 */
	SECURITY_MANDATORY_LABEL_AUTHORITY(16),

	/**
	 * Specifies the Scoped Policy Authority. It defines all other scoped policy SIDs in the forest.
	 */
	SECURITY_SCOPED_POLICY_ID_AUTHORITY(17),

	/**
	 * Specifies the authentication authority asserting the client’s identity. It defines only the following well-known SIDs: S-1-18-1, and S-1-18-2.
	 */
	SECURITY_AUTHENTICATION_AUTHORITY(18);

	private int id;

	SidIdentifierAuthority(int id) {
		this.id = id;
	}

	@Override
	public byte[] toByteArray() {
		return new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, (byte) id};
	}

	@Override
	public int sizeOfByteArray() {
		return 6;
	}

	/**
	 * TODO: can be improved by exception handling
	 *
	 * @param id
	 * @return
	 */
	public static SidIdentifierAuthority fromInt(int id) {
		for (SidIdentifierAuthority idAuth : values()) {
			if (idAuth.id == id) {
				return idAuth;
			}
		}
		return null;
	}
}
