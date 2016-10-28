package com.github.dokandev.dokanjava;

import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

import java.util.Arrays;
import java.util.List;

public class DokanFileInfo extends Structure implements Structure.ByReference {
    public long _context;
    public long _dokanContext;
    public IntByReference _dokanOptions;
    public int _processId;
    public byte _isDirectory;
    public byte _deleteOnClose;
    public byte _pagingIo;
    public byte _synchronousIo;
    public byte _noCache;
    public byte _writeToEndOfFile;

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("_context", "_dokanContext", "_dokanOptions", "_processId", "_isDirectory", "_deleteOnClose", "_pagingIo", "_synchronousIo", "_noCache", "_writeToEndOfFile");
    }
}