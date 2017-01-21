package com.dokany.java;

import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class StreamInfo {
	public String name;
	public long length;

	/**
	 *
	 * @return Win32FindStreamData containing the length and file name
	 */
	Win32FindStreamData toStruct() {
		val out = new Win32FindStreamData();
		out.length(length);
		name.getChars(0, name.length(), out.cFileName(), 0);
		return out;
	}
}
