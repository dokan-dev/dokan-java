package com.dokany.java.structure;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FreeSpace {
	long totalBytes;
	long totalUsed;
 
	public long getFreeBytes() {
		return totalBytes - totalUsed;
	}
}
