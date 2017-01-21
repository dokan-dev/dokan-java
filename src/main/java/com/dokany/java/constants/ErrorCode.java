package com.dokany.java.constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public enum ErrorCode {

	SUCCESS(0),

	ERROR_WRITE_FAULT(29),

	ERROR_READ_FAULT(30),

	ERROR_FILE_NOT_FOUND(0xc0000034),

	OBJECT_NAME_COLLISION(0xc0000035),

	ERROR_FILE_EXISTS(80),

	ERROR_ALREADY_EXISTS(183);

	@Getter
	int mask;
}
