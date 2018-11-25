package com.dokan.java.structure.filesecurity;

import com.dokan.java.Byteable;
import com.dokan.java.constants.microsoft.filesecurity.SidIdentifierAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Object-oriented implementation of the SID structure.
 * See also <a href=https://msdn.microsoft.com/en-us/library/gg465313.aspx> the microsoft doc</a>.
 * TODO: implement add() method to add subAuthorities
 */
public class SecurityIdentifier implements Byteable {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityIdentifier.class);

	private final byte revision = 0x01;

	private SidIdentifierAuthority sidAuth;

	private List<Integer> subAuthorities;

	/**
	 * Creates a SecurityIdentifier with the given Authority and Subauthorities.
	 * The number of Subauthorities is bounded from above by 15 and if the List of subauthorities exceeds this limit only the first 15 will be taken.
	 *
	 * @param sidAuth
	 * @param subAuthorities
	 */
	public SecurityIdentifier(SidIdentifierAuthority sidAuth, List<Integer> subAuthorities) {
		this.sidAuth = sidAuth;
		this.subAuthorities = new ArrayList<>(0);
		if (subAuthorities != null) {
			if (subAuthorities.size() <= 15) {
				this.subAuthorities.addAll(subAuthorities);
			} else {
				LOG.warn("Number of subauthorities exceeds the limit of 15. Only taking the first 15 ones.");
				int i = 0;
				for (Integer subAuth : subAuthorities) {
					this.subAuthorities.add(subAuth);
					if (i >= 15) {
						break;
					}
					i++;
				}

			}
		}
	}

	@Override
	public byte[] toByteArray() {
		ByteBuffer buf = ByteBuffer.allocate(sizeOfByteArray());
		buf.put(revision);
		buf.put((byte) subAuthorities.size());
		//dont reverse the authority
		buf.put(sidAuth.toByteArray());
		for (Integer subAuth : subAuthorities) {
			//but reverse the subauthorities
			buf.putInt(Integer.reverseBytes(subAuth));
		}
		return buf.array();
	}

	@Override
	public int sizeOfByteArray() {
		return 1 //revision
				+ 1 //subauthority count
				+ 6 //6 bytes of of the id authority
				+ 4 * subAuthorities.size();//each subauthority consists of 4 bytes
	}

	/**
	 * @param stringSid
	 * @return
	 */
	public static SecurityIdentifier fromString(String stringSid) {
		String[] sidTokenized = stringSid.split("-");
		List<Integer> subAuths = new ArrayList<>(sidTokenized.length - 3); // the first tokens are S-[Revision]-[IdAuthority]-...
		SidIdentifierAuthority idAuth = SidIdentifierAuthority.fromInt(Integer.parseUnsignedInt(sidTokenized[2]));
		for (int i = 3; i < sidTokenized.length; i++) {
			subAuths.add(Integer.parseUnsignedInt(sidTokenized[i]));
		}
		return new SecurityIdentifier(idAuth, subAuths);
	}
}
