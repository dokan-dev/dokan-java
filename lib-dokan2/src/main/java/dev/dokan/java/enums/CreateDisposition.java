package dev.dokan.java.enums;


import static dev.dokan.java.constants.CreateDispositions.*;

public enum CreateDisposition {

	SUPERSEDE(FILE_SUPERSEDE),
	OPEN(FILE_OPEN),
	CREATE(FILE_CREATE),
	OPEN_IF(FILE_OPEN_IF),
	OVERWRITE(FILE_OVERWRITE),
	OVERWRITE_IF(FILE_OVERWRITE_IF);

	private int value;

	CreateDisposition(int value) {
		this.value = value;
	}

	public static CreateDisposition of(int value) {
		for(var createDispostion : CreateDisposition.values()) {
			if (createDispostion.value == value) {
				return createDispostion;
			}
		}
		throw new IllegalArgumentException("Unknown CreateDisposition value");
	}

	public int getValue() {
		return value;
	}
}
