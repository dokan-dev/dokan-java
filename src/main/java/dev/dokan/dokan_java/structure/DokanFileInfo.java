package dev.dokan.dokan_java.structure;


import com.sun.jna.Structure;
import dev.dokan.dokan_java.DokanNativeMethods;
import dev.dokan.dokan_java.DokanOperations;
import dev.dokan.dokan_java.Unsigned;
import dev.dokan.dokan_java.UnsignedNumbers;

import java.util.Arrays;
import java.util.List;

/**
 * Dokan file information on the current operation.
 *
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___f_i_l_e___i_n_f_o.html">Dokany Documentation of PDOKAN_FILE_INFO</a>
 */
public class DokanFileInfo extends Structure implements Structure.ByReference {

    /**
     * Context that can be used to carry information between operation. The context can carry whatever type like {@link com.sun.jna.platform.win32.WinNT.HANDLE}, {@link Structure}, {@link com.sun.jna.ptr.IntByReference},
     * {@link com.sun.jna.Pointer} that will help the implementation understand the request context of the event.
     */
    @Unsigned
    public long Context;

    /**
     * Flag if the file has to be delete during {@link DokanOperations#Cleanup} event.
     */
    public byte DeleteOnClose;

    /**
     * Reserved. Used internally by Dokan library. Never modify.
     */
    @Unsigned
    public long DokanContext;

    /**
     * A pointer to {@link DokanOptions} which was passed to {@link DokanNativeMethods#DokanMain}.
     */
    public DokanOptions DokanOpts;

    /**
     * Requesting a directory file. Must be set in {@link DokanOperations#ZwCreateFile} if the file object appears to be a directory.
     */
    public byte IsDirectory;

    /**
     * Read or write directly from data source without cache.
     */
    public byte Nocache;

    /**
     * Read or write is paging IO.
     */
    public byte PagingIo;

    /**
     * Process ID for the thread that originally requested a given I/O operation.
     */
    @Unsigned
    public int ProcessId;

    /**
     * Read or write is synchronous IO.
     */
    public byte SynchronousIo;

    /**
     * If true, write to the current end of file instead of using the Offset parameter.
     */
    public byte WriteToEndOfFile;

    public DokanFileInfo() {
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("Context", "DokanContext", "DokanOpts", "ProcessId", "IsDirectory", "DeleteOnClose", "PagingIo", "SynchronousIo", "Nocache", "WriteToEndOfFile");
    }

    public final boolean isDirectory() {
        return IsDirectory != 0;
    }

    public final boolean deleteOnClose() {
        return DeleteOnClose != 0;
    }

    public final boolean pagingIo() {
        return PagingIo != 0;
    }

    public final boolean synchronousIo() {
        return SynchronousIo != 0;
    }

    public final boolean noCache() {
        return Nocache != 0;
    }

    public final boolean writeToEndOfFile() {
        return WriteToEndOfFile != 0;
    }

    @Override
    public String toString() {
        return String.format("DokanFileInfo(Context=%s, DokanContext=%s, DokanOpts=%s, ProcessId=%s, IsDirectory=%s/%s, DeleteOnClose=%s/%s, PagingIo=%s/%s, SynchronousIo=%s/%s, Nocache=%s/%s, WriteToEndOfFile=%s/%s)",
                UnsignedNumbers.toUnsignedString(this.Context),
                UnsignedNumbers.toUnsignedString(this.DokanContext),
                this.DokanOpts,
                UnsignedNumbers.toUnsignedString(this.ProcessId),
                this.IsDirectory, isDirectory(),
                this.DeleteOnClose, deleteOnClose(),
                this.PagingIo, pagingIo(),
                this.SynchronousIo, synchronousIo(),
                this.Nocache, noCache(),
                this.WriteToEndOfFile, writeToEndOfFile());
    }

}
