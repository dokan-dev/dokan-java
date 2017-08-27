package com.dokany.java;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class Win32FindStreamData extends Structure implements DokanyOperations.Win32FindStreamDataInterface {
	public long length;
	public char[] cFileName = new char[DokanyOperationsProxy.MAX_PATH + 36];

	@Override
	public void length(final long val) {
		length = val;
	}

	@Override
	public char[] cFileName() {
		return cFileName;
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
				"length", 
				"cFileName");
	}
}