package dev.dokan.java.structures;

import dev.dokan.java.nativeannotations.Boolean;
import dev.dokan.java.nativeannotations.Reserved;
import dev.dokan.java.nativeannotations.Unsigned;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * The DokanFileInfo Struct.
 * <p>
 * For field descriptions, see the <a href=https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___f_i_l_e___i_n_f_o.html>dokany documention</a>.
 */
@Structure.FieldOrder({"context", "dokanContext", "dokanOptions", "processingContext", "processId", "isDirectory", "deleteOnClose", "pagingIo", "synchronousIo", "noCache", "writeToEndOfFile"})
public class DokanFileInfo extends Structure implements Structure.ByReference {

	@Unsigned
	public long context;

	@Reserved
	public final long dokanContext;
	{
		this.dokanContext = -1;
	}

	public DokanOptions dokanOptions;

	@Reserved
	public final Pointer processingContext;
	{
		this.processingContext = Pointer.NULL;
	}

	@Unsigned
	public volatile int processId;

	@Boolean
	public byte isDirectory;

	@Boolean
	public byte deleteOnClose;

	@Boolean
	public byte pagingIo;

	@Boolean
	public byte synchronousIo;

	@Boolean
	public byte noCache;

	@Boolean
	public byte writeToEndOfFile;

	public long getProcessId() {
		return Integer.toUnsignedLong(processId);
	}

	public boolean getIsDirectory() {
		return isDirectory != 0;
	}

	public void setIsDirectory(boolean value) {
		this.isDirectory = (byte) (value? 1 : 0);
	}

	public boolean getDeleteOnClose() {
		return deleteOnClose != 0;
	}

	public boolean getPagingIo() {
		return pagingIo != 0;
	}

	public boolean getSynchronousIo() {
		return synchronousIo != 0;
	}

	public boolean getNoCache() {
		return noCache != 0;
	}

	public boolean getWriteToEndOfFile() {
		return writeToEndOfFile != 0;
	}

}
