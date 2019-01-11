package dev.dokan.dokan_java.structure.filesecurity;

import dev.dokan.dokan_java.Byteable;
import dev.dokan.dokan_java.constants.microsoft.filesecurity.AccessControlEntryFlag;
import dev.dokan.dokan_java.constants.microsoft.filesecurity.AccessControlEntryType;
import dev.dokan.dokan_java.structure.EnumIntegerSet;

public abstract class AccessControlEntry implements Byteable {

	protected final AccessControlEntryType type;

	protected final EnumIntegerSet<AccessControlEntryFlag> flags;

	protected AccessControlEntry(AccessControlEntryType type, EnumIntegerSet<AccessControlEntryFlag> flags) {
		this.type = type;
		this.flags = flags;
	}

	@Override
	public abstract byte[] toByteArray();

	@Override
	public abstract int sizeOfByteArray();

}
