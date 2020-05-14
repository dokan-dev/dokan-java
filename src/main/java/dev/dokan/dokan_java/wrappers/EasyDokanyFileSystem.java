package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.NativeName;
import dev.dokan.dokan_java.constants.microsoft.CreateOption;
import dev.dokan.dokan_java.constants.microsoft.CreationDisposition;
import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.constants.microsoft.FileShareAccess;
import dev.dokan.dokan_java.structure.EnumIntegerSet;
import dev.dokan.dokan_java.structure.filesecurity.SelfRelativeSecurityDescriptor;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Collection;

public interface EasyDokanyFileSystem {

	//public int zwCreateFile(WString rawPath,
	//		WinBase.SECURITY_ATTRIBUTES securityContext,
	//		int rawDesiredAccess,
	//		int rawFileAttributes,
	//		int rawShareAccess,
	//		int rawCreateDisposition,
	//		int rawCreateOptions,
	//		DokanFileInfo dokanFileInfo) { //TODO Return type //TODO Incorporate path into DokanyFileHandle
	@NativeName("zwCreateFile")
	void createHandle(Path absolutePath,
		String relativePath,
		SecurityContext securityContext,
		DesiredAccessMask desiredAccess,
		EnumIntegerSet<FileAttribute> fileAttributes,
		EnumIntegerSet<FileShareAccess> shareAccess,
		CreationDisposition creationDisposition,
		EnumIntegerSet<CreateOption> createOptions,
		DokanyFileHandle dokanyFileHandle
	); //--> zwCreateFile

	@NativeName("cleanup")
	void cleanup(Path absolutePath, String relativePath, DokanyFileHandle dokanyFileHandle);

	@NativeName("closeFile")
	void closeHandle(Path absolutePath, String relativePath, DokanyFileHandle dokanyFileHandle); //--> CloseFile

	@NativeName("readFile")
	byte[] readFile(Path absolutePath,
		String relativePath,
		long offset,
		int readLength,
		DokanyFileHandle dokanyFileHandle); //TODO return -> callback-array?

	@NativeName("writeFile")
	int writeFile(Path absolutePath, String relativePath, byte[] buffer, long offset, DokanyFileHandle dokanyFileHandle);

	@NativeName("flushFileBuffers")
	void flush(Path absolutePath, String relativePath, DokanyFileHandle dokanyFileHandle);

	@NativeName("getFileInformation")
	EasyFileInfo getFileInformation(Path absolutePath, String relativePath, DokanyFileHandle dokanyFileHandle);

	@NativeName("findFiles")
	Collection<FindFileInfo> findFiles(Path absolutePath, String relativePath, DokanyFileHandle dokanyFileHandle);
	//TODO --> Collection to callback?! --> default method?

	@NativeName("findFilesWithPattern")
	Collection<FindFileInfo> findFilesWithPattern(Path absolutePath,
		String relativePath,
		String pattern, //TODO Regex
		DokanyFileHandle dokanyFileHandle);
	//TODO --> Collection to callback?!

	@NativeName("setFileAttributes")
	void setFileAttributes(Path absolutePath,
		String relativePath,
		EnumIntegerSet<FileAttribute> fileAttributes,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("setFileTime")
	void setFileTime(Path absolutePath,
		String relativePath,
		FileTime creationTime,
		FileTime lastAccessTime,
		FileTime lastWriteTime,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("deleteFile")
	void prepareDeleteFile(Path absolutePath,
		String relativePath,
		DokanyFileHandle dokanyFileHandle); //TODO Rename, return value?

	@NativeName("deleteDirectory")
	void prepareDeleteDirectory(Path absolutePath,
		String relativePath,
		DokanyFileHandle dokanyFileHandle); //TODO Rename, return value?

	@NativeName("moveFile")
	void moveFile(Path sourceAbsolutePath,
		String sourceRelativePath,
		Path destinationAbsolutePath,
		String destinationRelativePath,
		boolean replaceIfExisting,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("setEndOfFile")
	void setEndOfFile(Path absolutePath,
		String relativePath,
		long fileSize,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("setAllocationSize")
	void setAllocationSize(Path absolutePath,
		String relativePath,
		long allocationSize,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("lockFile")
	void lockFile(Path absolutePath,
		String relativePath,
		long lockOffset,
		long lockLength,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("unlockFile")
	void unlockFile(Path absolutePath,
		String relativePath,
		long lockOffset,
		long lockLength,
		DokanyFileHandle dokanyFileHandle);

	@NativeName("getDiskFreeSpace")
	DiskSpaceInfo getDiskSpaceInfo(DokanyFileHandle dokanyFileHandle); //TODO Path?

	@NativeName("getVolumeInformation")
	VolumeInformation getVolumeInformation(DokanyFileHandle dokanyFileHandle); //TODO Path?

	@NativeName("mounted")
	void mounted(DokanyFileHandle dokanyFileHandle); //TODO Path?

	@NativeName("unmounted")
	void unmounted(DokanyFileHandle dokanyFileHandle); //TODO Path?

	//	int getFileSecurity(WString rawPath,
	//		int /* SecurityInformation */ rawSecurityInformation, //Requested information //DesiredAccessMask OR Similar CLASS OR EnumIntegerSet
	//		Pointer rawSecurityDescriptor, //Pointer to Buffer. Buffer gets copy of Security Descriptor. SECURITY_DESCRIPTOR in Self relative secdesc format //SelfRelativeSecurityDescriptor
	//		int rawSecurityDescriptorLength, //available length for secdesc
	//		IntByReference rawSecurityDescriptorLengthNeeded, //callback needed length for secdesc //Should be computed by dokany
	//		DokanFileInfo dokanFileInfo);
	@NativeName("getFileSecurity")
	SelfRelativeSecurityDescriptor getFileSecurity(Path absolutePath, //TODO Return type
		String relativePath,
		DesiredAccessMask requestedDescriptorInfo,
		int availableLength,
		DokanyFileHandle dokanyFileHandle);

	// int setFileSecurity(
	//            WString rawPath,
	//            int rawSecurityInformation,
	//            Pointer rawSecurityDescriptor,
	//            int rawSecurityDescriptorLength,
	//            DokanFileInfo dokanFileInfo);
	@NativeName("setFileSecurity")
	void setFileSecurity(Path absolutePath,
		String relativePath,
		DesiredAccessMask suppliedDescriptorInfo,
		SelfRelativeSecurityDescriptor securityDescriptor,
		int availableLength,
		DokanyFileHandle dokanyFileHandle);

	//TODO fillWin32FindData, findStreams

}