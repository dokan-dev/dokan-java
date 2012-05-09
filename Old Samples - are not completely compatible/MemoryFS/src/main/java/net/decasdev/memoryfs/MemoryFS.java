/*
The MIT License

Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package net.decasdev.memoryfs;

import static net.decasdev.dokan.CreationDisposition.CREATE_ALWAYS;
import static net.decasdev.dokan.CreationDisposition.CREATE_NEW;
import static net.decasdev.dokan.CreationDisposition.OPEN_ALWAYS;
import static net.decasdev.dokan.CreationDisposition.OPEN_EXISTING;
import static net.decasdev.dokan.CreationDisposition.TRUNCATE_EXISTING;
import static net.decasdev.dokan.FileAttribute.FILE_ATTRIBUTE_DIRECTORY;
import static net.decasdev.dokan.FileAttribute.FILE_ATTRIBUTE_NORMAL;
import static net.decasdev.dokan.WinError.ERROR_ALREADY_EXISTS;
import static net.decasdev.dokan.WinError.ERROR_DIRECTORY;
import static net.decasdev.dokan.WinError.ERROR_FILE_EXISTS;
import static net.decasdev.dokan.WinError.ERROR_FILE_NOT_FOUND;
import static net.decasdev.dokan.WinError.ERROR_PATH_NOT_FOUND;
import static net.decasdev.dokan.WinError.ERROR_READ_FAULT;
import static net.decasdev.dokan.WinError.ERROR_WRITE_FAULT;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import net.decasdev.dokan.ByHandleFileInformation;
import net.decasdev.dokan.Dokan;
import net.decasdev.dokan.DokanDiskFreeSpace;
import net.decasdev.dokan.DokanFileInfo;
import net.decasdev.dokan.DokanOperationException;
import net.decasdev.dokan.DokanOperations;
import net.decasdev.dokan.DokanOptions;
import net.decasdev.dokan.DokanVolumeInformation;
import net.decasdev.dokan.FileTimeUtils;
import net.decasdev.dokan.Win32FindData;

public class MemoryFS implements DokanOperations {
    public static final int FILE_CASE_PRESERVED_NAMES = 0x00000002;
    public static final int FILE_FILE_COMPRESSION = 0x00000010;
    public static final int FILE_SUPPORTS_SPARSE_FILES = 0x00000040;
    public static final int FILE_UNICODE_ON_DISK = 0x00000004;

    public static final int SUPPORTED_FLAGS = FILE_CASE_PRESERVED_NAMES
            | FILE_UNICODE_ON_DISK | FILE_SUPPORTS_SPARSE_FILES;
	/** fileName -> MemFileInfo */
	final ConcurrentHashMap<String, MemFileInfo> fileInfoMap = new ConcurrentHashMap<String, MemFileInfo>();
	// TODO FIX THIS
	final static int volumeSerialNumber = 5454;
	/** Next handle */
	long nextHandleNo = 1;
	final long rootCreateTime = FileTimeUtils.toFileTime(new Date());
	long rootLastWrite = rootCreateTime;

    private String driveLetter = "S:\\";

	static void log(String msg) {
		System.out.println("== app == " + msg);
	}

	public static void main(String[] args) {
		String driveLetter = (args.length == 0) ? "S:\\" : args[0];
		new MemoryFS().mount(driveLetter);
	}

	public MemoryFS() {
		showVersions();
	}

	void showVersions() {
		int version = Dokan.getVersion();
		System.out.println("version = " + version);
		int driverVersion = Dokan.getDriverVersion();
		System.out.println("driverVersion = " + driverVersion);
	}

	void mount(String driveLetter) {
		DokanOptions dokanOptions = new DokanOptions();
		dokanOptions.mountPoint = driveLetter;
        dokanOptions.threadCount = 1;
        this.driveLetter = driveLetter;
		int result = Dokan.mount(dokanOptions, this);
		log("[MemoryFS] result = " + result);

        if (result < 0) {
            System.out.println("Unable to mount volume because result = "
                    + result);
            log("Unable to mount volume because result = " + result);
            if (result == -1)
                System.out.println("General Error");
            if (result == -2)
                System.out.println("Bad Drive letter");
            if (result == -3)
                System.out.println("Can't install driver");
            if (result == -4)
                System.out.println("Driver something wrong");
            if (result == -5)
                System.out
                        .println("Can't assign a drive letter or mount point");
            if (result == -6)
                System.out.println("Mount point is invalid");
            System.exit(-1);

        } else {
            log("######## mounted " + driveLetter + " with result " + result
                    + " #############");
        }
	}

	synchronized long getNextHandle() {
		return nextHandleNo++;
	}

	public long onCreateFile(String fileName, int desiredAccess, int shareMode, int creationDisposition,
			int flagsAndAttributes, DokanFileInfo arg5) throws DokanOperationException {
		log("[onCreateFile] " + fileName + ", creationDisposition = " + creationDisposition);
		if (fileName.equals("\\")) {
			switch (creationDisposition) {
			case CREATE_NEW:
			case CREATE_ALWAYS:
				throw new DokanOperationException(ERROR_ALREADY_EXISTS);
			case OPEN_ALWAYS:
			case OPEN_EXISTING:
			case TRUNCATE_EXISTING:
				return getNextHandle();
			}
		} else if (fileInfoMap.containsKey(fileName)) {
			switch (creationDisposition) {
			case CREATE_NEW:
				throw new DokanOperationException(ERROR_ALREADY_EXISTS);
			case OPEN_ALWAYS:
			case OPEN_EXISTING:
				return getNextHandle();
			case CREATE_ALWAYS:
			case TRUNCATE_EXISTING:
				fileInfoMap.get(fileName).content.clear();
				updateParentLastWrite(fileName);
				return getNextHandle();
			}
		} else {
			switch (creationDisposition) {
			case CREATE_NEW:
			case CREATE_ALWAYS:
			case OPEN_ALWAYS:
				MemFileInfo fi = new MemFileInfo(fileName, false);
				fileInfoMap.put(fi.fileName, fi);
				updateParentLastWrite(fileName);
				return getNextHandle();
			case OPEN_EXISTING:
			case TRUNCATE_EXISTING:
				throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
			}
		}
		throw new DokanOperationException(1);
	}

	public long onOpenDirectory(String pathName, DokanFileInfo arg1) throws DokanOperationException {
		log("[onOpenDirectory] " + pathName);
		if (pathName.equals("\\"))
			return getNextHandle();
		pathName = Utils.trimTailBackSlash(pathName);
        log("[onOpenDirectory] step 2");
		if (fileInfoMap.containsKey(pathName))
			return getNextHandle();
		else
			throw new DokanOperationException(ERROR_PATH_NOT_FOUND);
	}

	public void onCreateDirectory(String fileName, DokanFileInfo file) throws DokanOperationException {
		log("[onCreateDirectory] " + fileName);
		fileName = Utils.trimTailBackSlash(fileName);
		if (fileInfoMap.containsKey(fileName) || fileName.length() == 0)
			throw new DokanOperationException(ERROR_ALREADY_EXISTS);
		MemFileInfo fi = new MemFileInfo(fileName, true);
		fileInfoMap.put(fi.fileName, fi);
		updateParentLastWrite(fileName);
        log("[onCreateDirectory] END");
	}

	public void onCleanup(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
        log("[onCleanup] " + fileName);
	}

	public void onCloseFile(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
        log("[onCloseFile] " + fileName);
	}

	public int onReadFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo arg3)
			throws DokanOperationException {
		log("[onReadFile] " + fileName);
		MemFileInfo fi = fileInfoMap.get(fileName);
		if (fi == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		if (fi.getFileSize() == 0)
			return 0;
		try {
			int copySize = Math.min(buffer.capacity(), fi.getFileSize() - (int) offset);
			if (copySize <= 0)
				return 0;
			buffer.put(fi.content.toNativeArray((int) offset, copySize));
			return copySize;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DokanOperationException(ERROR_READ_FAULT);
		}
	}

	public int onWriteFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo arg3)
			throws DokanOperationException {
		log("[onWriteFile] " + fileName);
		MemFileInfo fi = fileInfoMap.get(fileName);
		if (fi == null) {
			log("[onWriteFile] file not found");
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		}
		try {
			int copySize = buffer.capacity();
			byte[] tmpBuff = new byte[copySize];
			buffer.get(tmpBuff);
			int overwriteSize = Math.min((int) offset + copySize, fi.content.size()) - (int) offset;
			if (overwriteSize > 0)
				fi.content.set((int) offset, tmpBuff, 0, overwriteSize);
			int addSize = copySize - overwriteSize;
			if (addSize > 0)
				fi.content.add(tmpBuff, overwriteSize, addSize);
			fi.lastWriteTime = FileTimeUtils.toFileTime(new Date());
			return copySize;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DokanOperationException(ERROR_WRITE_FAULT);
		}
	}

	public void onSetEndOfFile(String fileName, long length, DokanFileInfo arg2)
			throws DokanOperationException {
		log("[onSetEndOfFile] " + fileName);
		MemFileInfo fi = fileInfoMap.get(fileName);
		if (fi == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		if (fi.getFileSize() == length)
			return;
		if (fi.getFileSize() < length) {
			byte[] tmp = new byte[(int) length - fi.getFileSize()];
			fi.content.add(tmp);
		} else {
			fi.content.remove((int) length, fi.getFileSize() - (int) length);
		}
	}

	public void onFlushFileBuffers(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
        log("[onFlushFileBuffers] " + fileName);
	}

	public ByHandleFileInformation onGetFileInformation(String fileName, DokanFileInfo arg1)
			throws DokanOperationException {
		log("[onGetFileInformation] " + fileName);
		if (fileName.equals("\\")) {
			return new ByHandleFileInformation(FILE_ATTRIBUTE_NORMAL | FILE_ATTRIBUTE_DIRECTORY,
					rootCreateTime, rootCreateTime, rootLastWrite, volumeSerialNumber, 0, 1, 1);
		}
		MemFileInfo fi = fileInfoMap.get(fileName);
		if (fi == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		return fi.toByHandleFileInformation();
	}

	public Win32FindData[] onFindFiles(String pathName, DokanFileInfo arg1)
			throws DokanOperationException {
		log("[onFindFiles] " + pathName);
		Collection<MemFileInfo> fis = fileInfoMap.values();
		ArrayList<Win32FindData> files = new ArrayList<Win32FindData>();
		File pathNameFile = new File(pathName);
		for (MemFileInfo fi : fis) {
			if (pathNameFile.equals(new File(fi.fileName).getParentFile())) {
				files.add(fi.toWin32FindData());
			}
		}
		log("[onFindFiles] " + files);
		return files.toArray(new Win32FindData[0]);
	}

	public Win32FindData[] onFindFilesWithPattern(String arg0, String arg1, DokanFileInfo arg2)
			throws DokanOperationException {
        log("[onFindFilesWithPattern] ");
		return null;
	}

	public void onSetFileAttributes(String fileName, int fileAttributes, DokanFileInfo arg2)
			throws DokanOperationException {
		log("[onSetFileAttributes] " + fileName);
		MemFileInfo fi = fileInfoMap.get(fileName);
		if (fi == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		fi.fileAttribute = fileAttributes;
	}

	public void onSetFileTime(String fileName, long creationTime, long lastAccessTime,
			long lastWriteTime, DokanFileInfo arg4) throws DokanOperationException {
		log("[onSetFileTime] " + fileName);
		MemFileInfo fi = fileInfoMap.get(fileName);
		if (fi == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		fi.creationTime = creationTime;
		fi.lastAccessTime = lastAccessTime;
		fi.lastWriteTime = lastWriteTime;
	}

	public void onDeleteFile(String fileName, DokanFileInfo arg1) throws DokanOperationException {
		log("[onDeleteFile] " + fileName);
		MemFileInfo removed = fileInfoMap.remove(fileName);
		if (removed == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		updateParentLastWrite(fileName);
	}

	public void onDeleteDirectory(String fileName, DokanFileInfo arg1) throws DokanOperationException {
		log("[onDeleteDirectory] " + fileName);
		MemFileInfo removed = fileInfoMap.remove(fileName);
		if (removed == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		updateParentLastWrite(fileName);
	}

	public void onMoveFile(String existingFileName, String newFileName, boolean replaceExisiting,
			DokanFileInfo arg3) throws DokanOperationException {
		log("==> [onMoveFile] " + existingFileName + " -> " + newFileName + ", replaceExisiting = "
				+ replaceExisiting);
		MemFileInfo existing = fileInfoMap.get(existingFileName);
		if (existing == null)
			throw new DokanOperationException(ERROR_FILE_NOT_FOUND);
		// TODO Fix this
		if (existing.isDirectory)
			throw new DokanOperationException(ERROR_DIRECTORY);
		MemFileInfo newFile = fileInfoMap.get(newFileName);
		if (newFile != null && !replaceExisiting)
			throw new DokanOperationException(ERROR_FILE_EXISTS);
		fileInfoMap.remove(existingFileName);
		existing.fileName = newFileName;
		fileInfoMap.put(newFileName, existing);
		updateParentLastWrite(existingFileName);
		updateParentLastWrite(newFileName);
		log("<== [onMoveFile]");
	}

	public void onLockFile(String fileName, long arg1, long arg2, DokanFileInfo arg3)
			throws DokanOperationException {
		log("[onLockFile] " + fileName);
	}

	public void onUnlockFile(String fileName, long arg1, long arg2, DokanFileInfo arg3)
			throws DokanOperationException {
		log("[onUnlockFile] " + fileName);
	}

	public DokanDiskFreeSpace onGetDiskFreeSpace(DokanFileInfo fileInfo) throws DokanOperationException {
        log("[onGetDiskFreeSpace] ");
        DokanDiskFreeSpace free = new DokanDiskFreeSpace();
        free.totalNumberOfBytes = 1024L * 1024L;
        free.freeBytesAvailable = free.totalNumberOfFreeBytes / 2;
        free.totalNumberOfFreeBytes = free.freeBytesAvailable;
		return free;
	}

	public DokanVolumeInformation onGetVolumeInformation(String arg0, DokanFileInfo arg1)
			throws DokanOperationException {
        DokanVolumeInformation info = new DokanVolumeInformation();
        info.fileSystemFlags = SUPPORTED_FLAGS;
        info.maximumComponentLength = 256;
        info.volumeName = "Dedup Filesystem";
        info.fileSystemName = "SDFS";
        info.volumeSerialNumber = volumeSerialNumber;
        return info;
	}

	public void onUnmount(DokanFileInfo arg0) throws DokanOperationException {
		log("[onUnmount]");
        Dokan.removeMountPoint(driveLetter);
	}

	void updateParentLastWrite(String fileName) {
        log("[updateParentLastWrite]");
		if (fileName.length() <= 1)
			return;
		String parent = new File(fileName).getParent();
		log("[updateParentLastWrite] parent = " + parent);
		if (parent == "\\") {
			rootLastWrite = FileTimeUtils.toFileTime(new Date());
		} else {
			MemFileInfo fi = fileInfoMap.get(parent);
			if (fi == null)
				return;
			fi.lastWriteTime = FileTimeUtils.toFileTime(new Date());
		}
	}
}
