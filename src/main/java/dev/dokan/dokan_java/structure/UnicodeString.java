package dev.dokan.dokan_java.structure;


import com.sun.jna.Structure;
import com.sun.jna.WString;


/**
 * Supplemental class used to define Unicode Strings.
 *
 * This class is needed to fully implement {@link DokanAccessState}.
 * It is defined in <a href="https://github.com/dokan-dev/dokany/blob/master/dokan/fileinfo.h">fileinfo.h</a> in the dokan module of the Dokany project.
 *
 */
public class UnicodeString extends Structure implements Structure.ByReference {

	/**
	 * The length, in bytes, of the string stored in {@link UnicodeString#Buffer}.
	 */
	public short Length;

	/**
	 * The length, in bytes, of {@link UnicodeString#Buffer}.
	 */
	public short MaximumLength;

	/**
	 * Pointer to a buffer used to contain a string of wide characters.
	 *
	 * Just kiddin, we directly convert it to a WString.
	 * TODO: maybe just make a reference here!
	 */
	public WString Buffer;

}
