package com.dokany.java;

public class StreamInfo {
	public String name;
	public long length;

	StreamInfo(final String name, final long length) {
		this.name = name;
		this.length = length;
	}

	Win32FindStreamData toStruct() {
		final Win32FindStreamData out = new Win32FindStreamData();
		out.length(length);
		name.getChars(0, name.length(), out.cFileName(), 0);
		return out;
	}
}
