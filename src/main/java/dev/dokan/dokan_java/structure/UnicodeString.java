package dev.dokan.dokan_java.structure;


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
	public short Length;

	/**
	 * The length, in bytes, of {@link UnicodeString#Buffer}.
	 */
	public short MaximumLength;

	/**
	 * Pointer to a buffer used to contain a string of wide characters.
	 */
	public Pointer Buffer;

}
