package dev.dokan.java.structures;

import dev.dokan.java.nativeannotations.Enum;
import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.nativeannotations.Unsigned;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.WIN32_FIND_DATA;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.win32.StdCallLibrary;

import static com.sun.jna.platform.win32.WinDef.MAX_PATH;


/**
 * The dokan operations struct.
 */
@Structure.FieldOrder({"ZwCreateFile", "Cleanup", "CloseFile", "ReadFile", "WriteFile", "FlushFileBuffers", "GetFileInformation", "FindFiles", "FindFilesWithPattern", "SetFileAttributes", "SetFileTime", "DeleteFile", "DeleteDirectory", "MoveFile", "SetEndOfFile", "SetAllocationSize", "LockFile", "UnlockFile", "GetDiskFreeSpace", "GetVolumeInformation", "Mounted", "Unmounted", "GetFileSecurity", "SetFileSecurity", "FindStreams"})
public class DokanOperations extends Structure {

	//TODO: should this class be final?
	public DokanOperations() {
	}


	public ZwCreateFile ZwCreateFile;
	public Cleanup Cleanup;
	public CloseFile CloseFile;
	public ReadFile ReadFile;
	public WriteFile WriteFile;
	public FlushFileBuffers FlushFileBuffers;
	public GetFileInformation GetFileInformation;
	public FindFiles FindFiles;
	public FindFilesWithPattern FindFilesWithPattern;
	public SetFileAttributes SetFileAttributes;
	public SetFileTime SetFileTime;
	public DeleteFile DeleteFile;
	public DeleteDirectory DeleteDirectory;
	public MoveFile MoveFile;
	public SetEndOfFile SetEndOfFile;
	public SetAllocationSize SetAllocationSize;
	public LockFile LockFile;
	public UnlockFile UnlockFile;
	public GetDiskFreeSpace GetDiskFreeSpace;
	public GetVolumeInformation GetVolumeInformation;
	public Mounted Mounted;
	public Unmounted Unmounted;
	public GetFileSecurity GetFileSecurity;
	public SetFileSecurity SetFileSecurity;
	public FindStreams FindStreams;

	@FunctionalInterface
	public interface ZwCreateFile extends DokanCallback {

		int invoke(WString fileName, DokanIOSecurityContext securityContext, @EnumSet int desiredAccess, @EnumSet int fileAttributes, @EnumSet int shareAccess, @Enum int createDisposition, @EnumSet int createOptions, DokanFileInfo dokanFileInfo);

	}

	@FunctionalInterface
	public interface Cleanup extends DokanCallback {

		void invoke(WString fileName, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface CloseFile extends DokanCallback {

		void invoke(WString fileName, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface ReadFile extends DokanCallback {

		int invoke(WString fileName, Pointer buffer, @Unsigned int bufferLength, @Unsigned IntByReference readLength, @Unsigned long offset, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface WriteFile extends DokanCallback {

		int invoke(WString fileName, Pointer buffer, @Unsigned int numberOfBytesToWrite, @Unsigned IntByReference numberOfBytesWritten, @Unsigned long offset, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface FlushFileBuffers extends DokanCallback {

		int invoke(WString fileName, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface GetFileInformation extends DokanCallback {

		int invoke(WString fileName, ByHandleFileInformation byHandleFileInformation, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface FindFiles extends DokanCallback {

		int invoke(WString fileName, FillWin32FindData fillFindData, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface FindFilesWithPattern extends DokanCallback {

		int invoke(WString fileName, WString searchPattern, FillWin32FindData fillFindData, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface SetFileAttributes extends DokanCallback {

		int invoke(WString fileName, @EnumSet int fileAttributes, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface SetFileTime extends DokanCallback {

		int invoke(WString fileName, FILETIME creationTime, FILETIME castAccessTime, FILETIME lastWriteTime, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface DeleteFile extends DokanCallback {

		int invoke(WString fileName, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface DeleteDirectory extends DokanCallback {

		int invoke(WString fileName, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface MoveFile extends DokanCallback {

		//TODO: check this boolean
		int invoke(WString fileName, WString newFileName, boolean replaceIfExisting, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface SetEndOfFile extends DokanCallback {

		int invoke(WString fileName, @Unsigned long byteOffset, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface SetAllocationSize extends DokanCallback {

		int callback(WString fileName, @Unsigned long alloSize, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface LockFile extends DokanCallback {

		int invoke(WString fileName, @Unsigned long byteOffset, @Unsigned long length, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface UnlockFile extends DokanCallback {

		int invoke(WString rawPath, @Unsigned long byteOffset, @Unsigned long length, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface GetDiskFreeSpace extends DokanCallback {

		int invoke(@Unsigned LongByReference freeBytesAvailable, @Unsigned LongByReference totalNumberOfBytes, @Unsigned LongByReference totalNumberOfFreeBytes, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface GetVolumeInformation extends DokanCallback {

		int invoke(Pointer volumeNameBuffer, @Unsigned int volumeNameSize, @Unsigned IntByReference volumeSerialNumber, @Unsigned IntByReference maximumComponentLength, @EnumSet IntByReference fileSystemFlags, Pointer fileSystemNameBuffer, @Unsigned int fileSystemNameSize, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface Mounted extends DokanCallback {

		int invoke(WString mountPoint, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface Unmounted extends DokanCallback {

		int invoke(final DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface GetFileSecurity extends DokanCallback {

		int invoke(WString fileName, @EnumSet IntByReference securityInformation, Pointer securityDescriptor, @Unsigned int bufferLength, @Unsigned IntByReference lengthNeeded, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface SetFileSecurity extends DokanCallback {

		int invoke(WString fileName, @EnumSet IntByReference securityInformation, Pointer securityDescriptor, @Unsigned int securityDescriptorLength, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface FindStreams extends DokanCallback {

		int invoke(WString fileName, FillWin32FindStreamData fillFindStreamData, Pointer findStreamContext, DokanFileInfo dokanFileInfo);
	}

	public void setZwCreateFile(DokanOperations.ZwCreateFile zwCreateFile) {
		ZwCreateFile = zwCreateFile;
	}

	public void setCleanup(DokanOperations.Cleanup cleanup) {
		Cleanup = cleanup;
	}

	public void setCloseFile(DokanOperations.CloseFile closeFile) {
		CloseFile = closeFile;
	}

	public void setReadFile(DokanOperations.ReadFile readFile) {
		ReadFile = readFile;
	}

	public void setWriteFile(DokanOperations.WriteFile writeFile) {
		WriteFile = writeFile;
	}

	public void setFlushFileBuffers(DokanOperations.FlushFileBuffers flushFileBuffers) {
		FlushFileBuffers = flushFileBuffers;
	}

	public void setGetFileInformation(DokanOperations.GetFileInformation getFileInformation) {
		GetFileInformation = getFileInformation;
	}

	public void setFindFiles(DokanOperations.FindFiles findFiles) {
		FindFiles = findFiles;
	}

	public void setFindFilesWithPattern(DokanOperations.FindFilesWithPattern findFilesWithPattern) {
		FindFilesWithPattern = findFilesWithPattern;
	}

	public void setSetFileAttributes(DokanOperations.SetFileAttributes setFileAttributes) {
		SetFileAttributes = setFileAttributes;
	}

	public void setSetFileTime(DokanOperations.SetFileTime setFileTime) {
		SetFileTime = setFileTime;
	}

	public void setDeleteFile(DokanOperations.DeleteFile deleteFile) {
		DeleteFile = deleteFile;
	}

	public void setDeleteDirectory(DokanOperations.DeleteDirectory deleteDirectory) {
		DeleteDirectory = deleteDirectory;
	}

	public void setMoveFile(DokanOperations.MoveFile moveFile) {
		MoveFile = moveFile;
	}

	public void setSetEndOfFile(DokanOperations.SetEndOfFile setEndOfFile) {
		SetEndOfFile = setEndOfFile;
	}

	public void setSetAllocationSize(DokanOperations.SetAllocationSize setAllocationSize) {
		SetAllocationSize = setAllocationSize;
	}

	public void setLockFile(DokanOperations.LockFile lockFile) {
		LockFile = lockFile;
	}

	public void setUnlockFile(DokanOperations.UnlockFile unlockFile) {
		UnlockFile = unlockFile;
	}

	public void setGetDiskFreeSpace(DokanOperations.GetDiskFreeSpace getDiskFreeSpace) {
		GetDiskFreeSpace = getDiskFreeSpace;
	}

	public void setGetVolumeInformation(DokanOperations.GetVolumeInformation getVolumeInformation) {
		GetVolumeInformation = getVolumeInformation;
	}

	public void setMounted(DokanOperations.Mounted mounted) {
		Mounted = mounted;
	}

	public void setUnmounted(DokanOperations.Unmounted unmounted) {
		Unmounted = unmounted;
	}

	public void setGetFileSecurity(DokanOperations.GetFileSecurity getFileSecurity) {
		GetFileSecurity = getFileSecurity;
	}

	public void setSetFileSecurity(DokanOperations.SetFileSecurity setFileSecurity) {
		SetFileSecurity = setFileSecurity;
	}

	public void setFindStreams(DokanOperations.FindStreams findStreams) {
		FindStreams = findStreams;
	}


	//-- helper functions --

	@FunctionalInterface
	public interface FillWin32FindData extends StdCallLibrary.StdCallCallback {

		int invoke(WIN32_FIND_DATA fillFindData, DokanFileInfo dokanFileInfo);
	}

	@FunctionalInterface
	public interface FillWin32FindStreamData extends DokanCallback {

		boolean invoke(Win32FindStreamData fillFindData, Pointer streamContext);
	}

	@FieldOrder({"StreamSize", "cStreamName"})
	static class Win32FindStreamData extends Structure {

		WinNT.LARGE_INTEGER StreamSize;
		byte[] cStreamName = new byte[MAX_PATH + 36];

	}

}
