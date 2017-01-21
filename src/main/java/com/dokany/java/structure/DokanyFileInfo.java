package com.dokany.java.structure;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

import lombok.ToString;

/**
 * From dokan.h
 *
 * @see {@link https://github.com/dokan-dev/dokany/blob/master/dokan/dokan.h}
 *
 */
@ToString
public class DokanyFileInfo extends Structure implements Structure.ByReference {
	/**
	 * This can be used to carry information between operation. HANDLE This can be whatever type such as {@link com.sun.jna.platform.win32.WinNT.HANDLE},
	 * {@link com.sun.jna.Structure}, {@link com.sun.jna.ptr.IntByReference}, {@link com.sun.jna.Pointer} that will help the implementation understand the request context of the
	 * event.
	 */
	public long Context;

	/**
	 * Reserved. Used internally by Dokany library. Never modify.
	 */
	public long DokanContext;

	/**
	 * A pointer to {@link DeviceOptions} which was passed to {@link com.dokany.java.NativeMethods#DokanMain}.
	 */
	public DeviceOptions DokanOptions;

	/**
	 * Process id for the thread that originally requested a given I/O operation.
	 */
	public int ProcessId;

	/**
	 * Requesting a directory file.
	 *
	 * Must be set in {@link com.dokany.java.DokanyOperations#ZwCreateFile} if the file object appears to be a directory.
	 */
	public byte IsDirectory;

	/**
	 * Flag if the file has to be delete during {@link com.dokany.java.DokanyOperations#Cleanup} event.
	 */
	public byte DeleteOnClose;

	/**
	 * Read or write is paging IO.
	 */
	public byte PagingIo;

	/**
	 * Read or write is synchronous IO.
	 */
	public byte SynchronousIo;

	/**
	 * Read or write directly from data source without cache.
	 */
	public byte Nocache;

	/**
	 * If true, write to the current end of file instead of using the Offset parameter.
	 */
	public byte WriteToEndOfFile;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
		        "Context",
		        "DokanContext",
		        "DokanOptions",
		        "ProcessId",
		        "IsDirectory",
		        "DeleteOnClose",
		        "PagingIo",
		        "SynchronousIo",
		        "Nocache",
		        "WriteToEndOfFile");
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
}