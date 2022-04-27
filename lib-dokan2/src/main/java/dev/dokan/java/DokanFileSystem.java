package dev.dokan.java;

import dev.dokan.java.nativeannotations.Enum;
import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.nativeannotations.Out;
import dev.dokan.java.nativeannotations.Unsigned;
import dev.dokan.java.structures.ByHandleFileInformation;
import dev.dokan.java.structures.DokanFileInfo;
import dev.dokan.java.structures.DokanIOSecurityContext;
import dev.dokan.java.structures.DokanOperations;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;


/**
 * Main interface to implement. These methods will be registered in the dokany kernel driver to handle filesystem requests.
 */
public interface DokanFileSystem {

	@NotImplemented
	default int zwCreateFile(WString path, DokanIOSecurityContext securityContext, @EnumSet int desiredAccess, @EnumSet int fileAttributes, @EnumSet int shareAccess, @Enum int createDisposition, @EnumSet int createOptions, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default void cleanup(WString path, DokanFileInfo dokanFileInfo) {}

	@NotImplemented
	default void closeFile(WString path, DokanFileInfo dokanFileInfo) {}

	@NotImplemented
	default int readFile(WString path, @Out Pointer bufferToWriteInto, @Unsigned int bufferSize, @Out @Unsigned IntByReference numberOfBytesRead, @Unsigned long offset, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int writeFile(WString path, Pointer bufferToReadFrom, @Unsigned int bufferSize, @Out @Unsigned IntByReference numberOfBytesWritten, @Unsigned long offset, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int flushFileBuffers(WString path, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int getFileInformation(WString path, @Out ByHandleFileInformation handleFileInfo, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int findFiles(WString path, DokanOperations.FillWin32FindData fillFindDataCallback, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int findFilesWithPattern(WString path, WString searchPattern, DokanOperations.FillWin32FindData fillFindDataCallback, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int setFileAttributes(WString path, @EnumSet int attributes, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int setFileTime(WString path, FILETIME creationTime, FILETIME lastAccessTime, FILETIME lastWriteTime, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int deleteFile(WString path, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int deleteDirectory(WString path, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int moveFile(WString path, WString newFileName, boolean ReplaceIfExisting, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int setEndOfFile(WString path, @Unsigned long byteOffset, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int setAllocationSize(WString path, @Unsigned long Length, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int lockFile(WString path, @Unsigned long ByteOffset, @Unsigned long Length, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int unlockFile(WString path, @Unsigned long ByteOffset, @Unsigned long Length, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int getDiskFreeSpace(@Out @Unsigned LongByReference freeBytesAvailable, @Out @Unsigned LongByReference totalNumberOfBytes, @Out @Unsigned LongByReference totalNumberOfFreeBytes, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int getVolumeInformation(@Out Pointer volumeNameBuffer, @Unsigned int volumeNameBufferSize, @Out @Unsigned IntByReference volumeSerialNumber, @Out @Unsigned IntByReference maximumComponentLength, @Out @EnumSet IntByReference fileSystemFlags, @Out Pointer filesystemNameBuffer, @Unsigned int filesystemNameBufferSize, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int mounted(WString actualMountPoint, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int unmounted(DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int getFileSecurity(WString path, @Out @EnumSet IntByReference securityInformation, @Out Pointer securityDescriptorBufferToWriteTo, @Unsigned int securityDescriptorBufferSize, @Out @Unsigned IntByReference securityDescriptorLengthNeeded, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int setFileSecurity(WString path, @Out @EnumSet IntByReference securityInformation, Pointer securityDescriptorBufferToReadFrom, @Unsigned int securityDescriptorBufferSize, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

	@NotImplemented
	default int findStreams(WString path, DokanOperations.FillWin32FindStreamData FillFindData, Pointer findStreamContext, DokanFileInfo dokanFileInfo) {
		return NTStatus.STATUS_NOT_IMPLEMENTED;
	}

}
