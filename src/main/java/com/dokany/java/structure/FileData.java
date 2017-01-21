package com.dokany.java.structure;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class FileData {
	byte[] bytes;
	int length;
}
