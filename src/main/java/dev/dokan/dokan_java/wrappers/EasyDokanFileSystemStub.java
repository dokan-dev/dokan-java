package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.FileSystemInformation;
import dev.dokan.dokan_java.NotImplemented;
import dev.dokan.dokan_java.constants.microsoft.CreateOption;
import dev.dokan.dokan_java.constants.microsoft.CreationDisposition;
import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.constants.microsoft.FileShareAccess;
import dev.dokan.dokan_java.masking.MaskValueSet;
import dev.dokan.dokan_java.structure.filesecurity.SelfRelativeSecurityDescriptor;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Collection;

public class EasyDokanFileSystemStub extends AbstractEasyDokanFileSystem {

	public EasyDokanFileSystemStub(FileSystemInformation fileSystemInformation) {
		super(fileSystemInformation);
	}

	@NotImplemented
	@Override
	public void createHandle(Path absolutePath,
		String relativePath,
		EasyDokanIOSecurityContext securityContext,
		DesiredAccessMask desiredAccess,
		MaskValueSet<FileAttribute> fileAttributes,
		MaskValueSet<FileShareAccess> shareAccess,
		CreationDisposition creationDisposition,
		MaskValueSet<CreateOption> createOptions,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void cleanup(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void closeHandle(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public byte[] readFile(Path absolutePath,
		String relativePath,
		long offset,
		int readLength,
		DokanFileHandle dokanFileHandle) {
		return new byte[0];
	}

	@NotImplemented
	@Override
	public int writeFile(Path absolutePath,
		String relativePath,
		byte[] buffer,
		long offset,
		DokanFileHandle dokanFileHandle) {
		return 0;
	}

	@NotImplemented
	@Override
	public void flush(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public EasyFileInfo getFileInformation(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {
		return null;
	}

	@NotImplemented
	@Override
	public Collection<FindFileInfo> findFiles(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {
		return null;
	}

	@NotImplemented
	@Override
	public Collection<FindFileInfo> findFilesWithPattern(Path absolutePath,
		String relativePath,
		String pattern,
		DokanFileHandle dokanFileHandle) {
		return null;
	}

	@NotImplemented
	@Override
	public void setFileAttributes(Path absolutePath,
		String relativePath,
		MaskValueSet<FileAttribute> fileAttributes,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void setFileTime(Path absolutePath,
		String relativePath,
		FileTime creationTime,
		FileTime lastAccessTime,
		FileTime lastWriteTime,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void prepareDeleteFile(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void prepareDeleteDirectory(Path absolutePath, String relativePath, DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void moveFile(Path sourceAbsolutePath,
		String sourceRelativePath,
		Path destinationAbsolutePath,
		String destinationRelativePath,
		boolean replaceIfExisting,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void setEndOfFile(Path absolutePath, String relativePath, long fileSize, DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void setAllocationSize(Path absolutePath,
		String relativePath,
		long allocationSize,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void lockFile(Path absolutePath,
		String relativePath,
		long lockOffset,
		long lockLength,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void unlockFile(Path absolutePath,
		String relativePath,
		long lockOffset,
		long lockLength,
		DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public DiskSpaceInfo getDiskSpaceInfo(DokanFileHandle dokanFileHandle) {
		return null;
	}

	@NotImplemented
	@Override
	public VolumeInformation getVolumeInformation(DokanFileHandle dokanFileHandle) {
		return null;
	}

	@NotImplemented
	@Override
	public void mounted(DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public void unmounted(DokanFileHandle dokanFileHandle) {

	}

	@NotImplemented
	@Override
	public SelfRelativeSecurityDescriptor getFileSecurity(Path absolutePath,
		String relativePath,
		DesiredAccessMask requestedDescriptorInfo,
		int availableLength,
		DokanFileHandle dokanFileHandle) {
		return null;
	}

	@NotImplemented
	@Override
	public void setFileSecurity(Path absolutePath,
		String relativePath,
		DesiredAccessMask suppliedDescriptorInfo,
		SelfRelativeSecurityDescriptor securityDescriptor,
		int availableLength,
		DokanFileHandle dokanFileHandle) {

	}
}
