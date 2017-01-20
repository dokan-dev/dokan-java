package com.dokany.java;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StreamInfo {
	public String name;
	public long length;

	/**
	 *
	 * @return Win32FindStreamData containing the length and file name
	 */
	Win32FindStreamData toStruct() {
		final Win32FindStreamData out = new Win32FindStreamData();
		out.length(length);
		name.getChars(0, name.length(), out.cFileName(), 0);
		return out;
	}
}
