package com.dokany.java.structure;

import lombok.Data;

@Data
public final class FileData {
	private final byte[] bytes;
	private final int length;
}
