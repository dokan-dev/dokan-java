package com.dokan.java;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class Win32FindStreamData extends Structure implements DokanyOperations.Win32FindStreamDataInterface {
	public long length;
	//max path = 260 under windows
	public char[] cFileName = new char[260 + 36];

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