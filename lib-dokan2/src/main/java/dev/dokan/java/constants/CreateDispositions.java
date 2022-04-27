package dev.dokan.java.constants;

/**
 * As defined in wdm.h
 */
public interface CreateDispositions {

	int FILE_SUPERSEDE = 0x00000000;
	int FILE_OPEN = 0x00000001;
	int FILE_CREATE = 0x00000002;
	int FILE_OPEN_IF = 0x00000003;
	int FILE_OVERWRITE = 0x00000004;
	int FILE_OVERWRITE_IF = 0x00000005;
	int FILE_MAXIMUM_DISPOSITION = 0x00000005;

}
