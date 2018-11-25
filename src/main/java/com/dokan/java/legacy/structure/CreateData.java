package com.dokan.java.legacy.structure;

import java.util.Arrays;
import java.util.List;

import com.dokan.java.constants.microsoft.CreationDisposition;
import com.dokan.java.constants.microsoft.FileAttribute;
import com.sun.jna.Structure;

public class CreateData extends Structure {
	public int DesiredAccess;
	public FileAttribute FileAttributes;
	public int ShareAccess;
	public CreationDisposition CreateDisposition;
	public int CreateOptions;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(
		        "DesiredAccess",
		        "FileAttributes",
		        "ShareAccess",
		        "CreateDisposition",
		        "CreateOptions");
	}
}
