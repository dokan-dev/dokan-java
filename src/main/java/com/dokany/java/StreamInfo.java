package com.dokany.java;

public class StreamInfo {
	public String name;
	public long length;

	StreamInfo(final String name, final long length) {
		this.name = name;
		this.length = length;
	}

	Operations.Win32FindStreamData toStruct() {
		final Operations.Win32FindStreamData out = new OperationsImpl.Win32FindStreamData();
		out.length(length);
		name.getChars(0, name.length(), out.cFileName(), 0);
		return out;
	}
}
