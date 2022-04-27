package dev.dokan.java.structures;


import dev.dokan.java.nativeannotations.Unsigned;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;


/**
 * Supplemental class used to define Unicode Strings.
 * <p>
 * This class is needed to fully implement {@link DokanAccessState}.
 * It is defined in <a href="https://github.com/dokan-dev/dokany/blob/master/dokan/fileinfo.h">fileinfo.h</a> in the dokan module of the Dokany project.
 */
@Structure.FieldOrder({"Length", "MaximumLength", "Buffer"})
public class UnicodeString extends Structure {

	/**
	 * The length, in bytes, of the string stored in {@link UnicodeString#Buffer}.
	 */
	@Unsigned
	public short Length;

	/**
	 * The length, in bytes, of {@link UnicodeString#Buffer}.
	 */
	@Unsigned
	public volatile short MaximumLength;

	/**
	 * Pointer to a buffer used to contain a string of wide characters.
	 */
	public volatile Pointer Buffer;

	public int getLength() {
		return Short.toUnsignedInt(Length);
	}

	public int getMaximumLength() {
		return Short.toUnsignedInt(MaximumLength);
	}

	@Override
	public String toString() {
		return Buffer.getWideString(0);
	}

}
