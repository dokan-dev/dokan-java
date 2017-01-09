package com.dokany.java.structure;

public final class FileData {
	private final byte[] bytes;
	private final int length;

	public FileData(final byte[] data, final int numReadOrWritten) {
		bytes = data;
		length = numReadOrWritten;
	}

	public final byte[] getBytes() {
		return bytes;
	}

	public final int getLength() {
		return length;
	}
}
