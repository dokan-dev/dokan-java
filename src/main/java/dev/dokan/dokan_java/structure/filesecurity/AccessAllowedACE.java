package dev.dokan.dokan_java.structure.filesecurity;

import dev.dokan.dokan_java.structure.EnumIntegerSet;
import dev.dokan.dokan_java.constants.microsoft.AccessMask;
import dev.dokan.dokan_java.constants.microsoft.filesecurity.AccessControlEntryFlag;
import dev.dokan.dokan_java.constants.microsoft.filesecurity.AccessControlEntryType;

import java.nio.ByteBuffer;

public class AccessAllowedACE extends AccessControlEntry {

	EnumIntegerSet<AccessMask> rights;

	SecurityIdentifier sid;

	public AccessAllowedACE(EnumIntegerSet<AccessControlEntryFlag> flags, SecurityIdentifier sid, EnumIntegerSet<AccessMask> rights) {
		super(AccessControlEntryType.ACCESS_ALLOWED_ACE_TYPE, flags);
		this.rights = rights;
		this.sid = sid;
	}

	@Override
	public byte[] toByteArray() {
		ByteBuffer buf = ByteBuffer.allocate(sizeOfByteArray());
		buf.put(type.toByteArray());
		buf.put((byte) flags.toInt());
		buf.putShort(Short.reverseBytes((short) sizeOfByteArray()));
		buf.putInt(Integer.reverseBytes(rights.toInt()));
		buf.put(sid.toByteArray());
		return buf.array();
	}

	@Override
	public int sizeOfByteArray() {
		return 1 //type
				+ 1 //flags
				+ 2 //size
				+ 4 //mask
				+ sid.sizeOfByteArray(); //size of sid
	}
}
