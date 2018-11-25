package com.dokan.java.structure.filesecurity;


import com.dokan.java.constants.microsoft.AccessMask;
import com.dokan.java.constants.microsoft.filesecurity.AccessControlEntryFlag;
import com.dokan.java.constants.microsoft.filesecurity.SecurityDescriptorControlFlag;
import com.dokan.java.constants.microsoft.filesecurity.SidIdentifierAuthority;
import com.dokan.java.structure.EnumIntegerSet;
import com.dokan.java.structure.filesecurity.AccessAllowedACE;
import com.dokan.java.structure.filesecurity.AccessControlList;
import com.dokan.java.structure.filesecurity.SecurityIdentifier;
import com.dokan.java.structure.filesecurity.SelfRelativeSecurityDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * TODO: the harcoded byte arrays are not directly bind to the methods, so changing some sid constant or something like this can lead to failures!
 * TODO: combine the selection of sid/whatever and the hardcoded array in one method
 * <p>
 * TODO: refactor in suhc a way, that all test methods for a specific class has an own test class
 */
public class SelfRelativeSecurityDescriptorTest {

	@Test
	public void testControlField() {
		EnumIntegerSet<SecurityDescriptorControlFlag> control = new EnumIntegerSet<>(SecurityDescriptorControlFlag.class);
		control.add(SecurityDescriptorControlFlag.GD, SecurityDescriptorControlFlag.OD, SecurityDescriptorControlFlag.DD, SecurityDescriptorControlFlag.SD);
		ByteBuffer buf = ByteBuffer.allocate(2);

		Assertions.assertEquals((43 << 8 + 0) << 16, Integer.reverseBytes(control.toInt()));
		Assertions.assertEquals((43 << 8 + 0), Short.reverseBytes((short) control.toInt()));
		Assertions.assertArrayEquals(new byte[]{43, 0}, buf.putShort(Short.reverseBytes((short) control.toInt())).array());
	}

	@Test
	public void testSidWithoutSubAuthorities() {
		SecurityIdentifier sid = new SecurityIdentifier(SidIdentifierAuthority.WORLD_SID_AUTHORITY, null);
		byte[] expected = new byte[]{0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01};
		Assertions.assertArrayEquals(expected, sid.toByteArray());
	}

	@Test
	public void testValidEveryoneSid() {
		SecurityIdentifier sid = new SecurityIdentifier(SidIdentifierAuthority.WORLD_SID_AUTHORITY, Collections.singletonList(0));
		Assertions.assertArrayEquals(getEveryoneSid(), sid.toByteArray());
	}

	@Test
	public void testValidBuiltinAdminitstratorsSid() {
		ArrayList<Integer> subAuths = new ArrayList<>(2);
		subAuths.add(32);
		subAuths.add(544);
		SecurityIdentifier sid = new SecurityIdentifier(SidIdentifierAuthority.SECURITY_NT_AUTHORITY, subAuths);
		Assertions.assertArrayEquals(new byte[]{0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x05, 0x20, 0x00, 0x00, 0x00, 0x20, 0x02, 0x00, 0x00}, sid.toByteArray());
	}

	@Test
	public void testSidFromString() {
		SecurityIdentifier sid = SecurityIdentifier.fromString("S-1-5-32-544");
		Assertions.assertArrayEquals(new byte[]{0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x05, 0x20, 0x00, 0x00, 0x00, 0x20, 0x02, 0x00, 0x00}, sid.toByteArray());
	}

	@Test
	public void testAccessAllowedACE() {
		//set the flag
		EnumIntegerSet<AccessControlEntryFlag> flags = new EnumIntegerSet<AccessControlEntryFlag>(AccessControlEntryFlag.class);
		flags.add(AccessControlEntryFlag.CONTAINER_INHERIT_ACE, AccessControlEntryFlag.OBJECT_INHERIT_ACE);
		//set the mask
		EnumIntegerSet<AccessMask> mask = new EnumIntegerSet<>(AccessMask.class);
		mask.add(AccessMask.GENERIC_ALL);
		//set the sid to world sid resp. everyone
		SecurityIdentifier sid = SecurityIdentifier.fromString("S-1-1-0");// everyone sid
		//create ace
		AccessAllowedACE allowedACE = new AccessAllowedACE(flags, sid, mask);
		Assertions.assertArrayEquals(getAllowedAccessACE(), allowedACE.toByteArray());
	}

	@Test
	public void testACL() {
		//test empty DACL rev2
		AccessControlList emptyDaclRev2 = AccessControlList.createDaclRevision2(new ArrayList<>(0));
		Assertions.assertArrayEquals(new byte[]{0x02, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00}, emptyDaclRev2.toByteArray());
		//test empty DACL rev4
		AccessControlList emptyDaclRev4 = AccessControlList.createDaclRevision4(new ArrayList<>(0));
		Assertions.assertArrayEquals(new byte[]{0x04, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00}, emptyDaclRev4.toByteArray());
		//test empty SACL rev2
		AccessControlList emptySaclRev2 = AccessControlList.createSaclRevision2(new ArrayList<>(0));
		Assertions.assertArrayEquals(new byte[]{0x02, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00}, emptySaclRev2.toByteArray());
		//test empty SACL rev4
		AccessControlList emptySaclRev4 = AccessControlList.createSaclRevision4(new ArrayList<>(0));
		Assertions.assertArrayEquals(new byte[]{0x04, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00}, emptySaclRev4.toByteArray());

		//test DACL rev2 with accessAllowACE
		EnumIntegerSet<AccessControlEntryFlag> flags = new EnumIntegerSet<AccessControlEntryFlag>(AccessControlEntryFlag.class);
		flags.add(AccessControlEntryFlag.CONTAINER_INHERIT_ACE, AccessControlEntryFlag.OBJECT_INHERIT_ACE);
		//set the mask
		EnumIntegerSet<AccessMask> mask = new EnumIntegerSet<>(AccessMask.class);
		mask.add(AccessMask.GENERIC_ALL);
		//set the sid to world sid resp. everyone
		SecurityIdentifier sid = SecurityIdentifier.fromString("S-1-1-0");
		//create ace
		AccessControlList daclRev2WithAccessAllow = AccessControlList.createDaclRevision2(Collections.singletonList(new AccessAllowedACE(flags, sid, mask)));

		Assertions.assertArrayEquals(getAclWithAAAce(), daclRev2WithAccessAllow.toByteArray());
	}

	@Test
	public void testEmptySecurityDescriptor() {
		EnumIntegerSet<SecurityDescriptorControlFlag> flags = new EnumIntegerSet<>(SecurityDescriptorControlFlag.class);
		flags.add(SecurityDescriptorControlFlag.GD, SecurityDescriptorControlFlag.OD, SecurityDescriptorControlFlag.DD, SecurityDescriptorControlFlag.SD);
		byte[] expected = getEmptySelfRelativeSecurityDescriptorWithEmptyFlags();
		expected[2] = 43;
		Assertions.assertArrayEquals(expected, SelfRelativeSecurityDescriptor.createEmptySD(flags).toByteArray());
	}

	@Test
	public void testOwnerAndGroupSD() {
		//control
		EnumIntegerSet<SecurityDescriptorControlFlag> control = new EnumIntegerSet<>(SecurityDescriptorControlFlag.class);
		control.add(SecurityDescriptorControlFlag.SR, SecurityDescriptorControlFlag.SD, SecurityDescriptorControlFlag.DD);
		//owner
		SecurityIdentifier oSid = SecurityIdentifier.fromString("S-1-1-0");
		//group
		SecurityIdentifier gSid = SecurityIdentifier.fromString("S-1-1-0");
		//security descriptor
		SelfRelativeSecurityDescriptor sd = SelfRelativeSecurityDescriptor.createSD(control, oSid, gSid, null, null);

		//our expected stuff
		byte[] emptySD = getEmptySelfRelativeSecurityDescriptorWithEmptyFlags();
		emptySD[2] = 40; //we defaulting only sacl and dacl
		emptySD[4] = 20; //offsets
		emptySD[8] = 32;
		byte[] sid = getEveryoneSid();
		byte[] expected = concat(concat(emptySD, sid), sid);

		Assertions.assertArrayEquals(expected, sd.toByteArray());
	}

	@Test
	public void testSDWithOwnerGroupAndDacl() {
		//control
		EnumIntegerSet<SecurityDescriptorControlFlag> control = new EnumIntegerSet<>(SecurityDescriptorControlFlag.class);
		control.add(SecurityDescriptorControlFlag.SR, SecurityDescriptorControlFlag.DP, SecurityDescriptorControlFlag.SD);
		//owner
		SecurityIdentifier oSid = SecurityIdentifier.fromString("S-1-1-0");
		SecurityIdentifier gSid = SecurityIdentifier.fromString("S-1-1-0");
		//ace
		//ace control
		EnumIntegerSet<AccessControlEntryFlag> flags = new EnumIntegerSet<>(AccessControlEntryFlag.class);
		flags.add(AccessControlEntryFlag.CONTAINER_INHERIT_ACE, AccessControlEntryFlag.OBJECT_INHERIT_ACE);
		//set the mask
		EnumIntegerSet<AccessMask> mask = new EnumIntegerSet<>(AccessMask.class);
		mask.add(AccessMask.GENERIC_ALL);
		//create ace
		AccessControlList daclRev2WithAccessAllow = AccessControlList.createDaclRevision2(Collections.singletonList(new AccessAllowedACE(flags, oSid, mask)));

		SelfRelativeSecurityDescriptor sd = SelfRelativeSecurityDescriptor.createSD(control, oSid, gSid, null, daclRev2WithAccessAllow);

		//our expected stuff
		byte[] emptySD = getEmptySelfRelativeSecurityDescriptorWithEmptyFlags();
		emptySD[2] = 36; //we defaulting only sacl and dacl is present!
		emptySD[4] = 20; //offsets
		emptySD[8] = 32;
		emptySD[16] = 44;
		byte[] sid = getEveryoneSid();
		byte[] acl = new byte[]{
				0x02, //revision
				0x00, //sbz1
				0x1C,
				0x00, //size
				0x01, 0x00, //count
				0x00, 0x00, //sbz2
				0x00, // ace Type
				0x03, //ace flags
				0x14, 0x00, //ace size
				0x00, 0x00, 0x00, 0x10, //access mask
				0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00};
		byte[] expected = concat(concat(concat(emptySD, sid), sid), acl);
		Assertions.assertArrayEquals(expected, sd.toByteArray());
	}

	private static byte[] getEmptySelfRelativeSecurityDescriptorWithEmptyFlags() {
		return new byte[]{
				0x01, //revision
				0x00, //sbz1
				0x00,// second half of control flag
				-128, //first half indicating a self relative sec. desc.
				0x00,
				0x00,
				0x00,
				0x00, //owner offset
				0x00,
				0x00,
				0x00,
				0x00, //group offset
				0x00,
				0x00,
				0x00,
				0x00, //sacl offset
				0x00,
				0x00,
				0x00,
				0x00, //dacl offset
		};
	}

	private static byte[] getEveryoneSid() {
		return new byte[]{0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00};
	}

	private static byte[] getAllowedAccessACE() {
		return new byte[]{
				0x00, // ace Type
				0x03, //ace flags
				0x14, 0x00, //ace size
				0x00, 0x00, 0x00, 0x10, //access mask
				0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00};
	}

	private static byte[] getAclWithAAAce() {
		return concat(new byte[]{
				0x02, //revision
				0x00, //sbz1
				0x1C,
				0x00, //size
				0x01, 0x00, //count
				0x00, 0x00 //sbz2
		}, getAllowedAccessACE());
	}

	private static byte[] getSDWithDaclWithAAAce() {
		byte[] tmp = concat(new byte[]{
				0x01, //revision
				0x00, //sbz1
				0x01,// first half of control flag indicating a self relative sec. desc.
				36, //second half indicating sacl defaulted and dacl present
				0x10,
				0x00,
				0x00,
				0x00, //owner offset (this header = 16 Byte)
				0x18,
				0x00,
				0x00,
				0x00, //group offset (header + owner = 16 + 8)
				0x00,
				0x00,
				0x00,
				0x00, //sacl offset (zero)
				0x20,
				0x00,
				0x00,
				0x00, //dacl offset (header +owner +group = 16+8+8)
		}, getEveryoneSid());
		tmp = concat(tmp, getEveryoneSid());
		return concat(tmp, getAclWithAAAce());
	}

	private static byte[] concat(byte[] arr1, byte[] arr2) {
		byte[] arr3 = Arrays.copyOf(arr1, arr1.length + arr2.length);
		for (int i = 0; i < arr2.length; i++) {
			arr3[i + arr1.length] = arr2[i];
		}
		return arr3;
	}

}
