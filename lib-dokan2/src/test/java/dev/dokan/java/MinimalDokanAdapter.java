package dev.dokan.java;

import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.structures.DokanFileInfo;
import dev.dokan.java.structures.DokanIOSecurityContext;
import com.sun.jna.WString;

/**
 * An empty filesystem intended to test mounting.
 */
public class MinimalDokanAdapter implements DokanFileSystem {


	@Override
	public int zwCreateFile(WString path, DokanIOSecurityContext securityContext, @EnumSet int DesiredAccess, @EnumSet int FileAttributes, @EnumSet int ShareAccess, int CreateDisposition, @EnumSet int CreateOptions, DokanFileInfo dokanFileInfo) {
		return NTStatus.NO_SUCH_FILE;
	}

	@Override
	public int mounted(WString actualMountPoint, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_SUCCESS;
	}

	@Override
	public int unmounted(DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_SUCCESS;
	}
}
