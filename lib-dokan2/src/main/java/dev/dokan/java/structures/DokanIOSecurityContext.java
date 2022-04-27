package dev.dokan.java.structures;


import dev.dokan.java.nativeannotations.EnumSet;
import com.sun.jna.Structure;


/**
 * The DokanIOSecurityContext contains the Dokan specific security context of the Windows kernel create request.
 * It is a parameter in the {@link DokanOperations.ZwCreateFile} callback.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/wdm/ns-wdm-_io_security_context?redirectedfrom=MSDN">Microsoft documentation</a> of the original structure
 * @see <a href="https://github.com/dokan-dev/dokany/blob/master/sys/public.h">Definition in {@code public.h}</a> of the Dokany project.
 */
@Structure.FieldOrder({"accessState", "desiredAccess"})
public class DokanIOSecurityContext extends Structure implements Structure.ByReference {

	/**
	 * Dokan ACCESS_STATE structure that contains the object's subject context, granted access types, and remaining desired access types.
	 */
	public volatile DokanAccessState accessState;

	/**
	 * An ACCESS_MASK value that expresses the access rights that are requested in the IRP_MJ_CREATE request.
	 */
	@EnumSet
	public volatile int desiredAccess;

	public long getDesiredAccess() {
		return Integer.toUnsignedLong(desiredAccess);
	}

}
