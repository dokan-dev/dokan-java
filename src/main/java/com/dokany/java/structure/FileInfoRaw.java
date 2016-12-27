package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

public class FileInfoRaw extends Structure implements Structure.ByReference {
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
	protected List<String> getFieldOrder() {
		return Arrays.asList("_context", "_dokanContext", "_dokanOptions", "_processId", "_isDirectory", "_deleteOnClose", "_pagingIo", "_synchronousIo", "_noCache",
		        "_writeToEndOfFile");
	}

	@Override
	public String toString() {
		return "DokanFileInfo{" +
		        "_context=" + _context +
		        ", _dokanContext=" + _dokanContext +
		        ", _dokanOptions=" + _dokanOptions +
		        ", _processId=" + _processId +
		        ", _isDirectory=" + _isDirectory +
		        ", _deleteOnClose=" + _deleteOnClose +
		        ", _pagingIo=" + _pagingIo +
		        ", _synchronousIo=" + _synchronousIo +
		        ", _noCache=" + _noCache +
		        ", _writeToEndOfFile=" + _writeToEndOfFile +
		        '}';
	}

	public final boolean isDirectory() {
		return _isDirectory != 0;
	}

	public final boolean deleteOnClose() {
		return _deleteOnClose != 0;
	}

	public final boolean pagingIo() {
		return _pagingIo != 0;
	}

	public final boolean synchronousIo() {
		return _synchronousIo != 0;
	}

	public final boolean noCache() {
		return _noCache != 0;
	}

	public final boolean writeToEndOfFile() {
		return _writeToEndOfFile != 0;
	}
}