/*
 * The MIT License (MIT)
 * 
 * Copyright (C) 
 *   2008 Yu Kobayashi (http://yukoba.accelart.jp)
 *   2015 Simon Herter <sim.herter@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.dokandev.dokanjava.samples.memoryfs;

import static com.github.dokandev.dokanjava.DokanOption.DEBUG;
import static com.github.dokandev.dokanjava.DokanOption.KEEP_ALIVE;
import static com.github.dokandev.dokanjava.DokanOption.REMOVABLE;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.github.dokandev.dokanjava.ByHandleFileInformation;
import com.github.dokandev.dokanjava.CreationDisposition;
import com.github.dokandev.dokanjava.Dokan;
import com.github.dokandev.dokanjava.DokanDiskFreeSpace;
import com.github.dokandev.dokanjava.DokanFileInfo;
import com.github.dokandev.dokanjava.DokanOperationException;
import com.github.dokandev.dokanjava.DokanOperations;
import com.github.dokandev.dokanjava.DokanOptions;
import com.github.dokandev.dokanjava.DokanVolumeInformation;
import com.github.dokandev.dokanjava.FileAccess;
import com.github.dokandev.dokanjava.FileAttribute;
import com.github.dokandev.dokanjava.FileFlag;
import com.github.dokandev.dokanjava.FileTimeUtils;
import com.github.dokandev.dokanjava.Win32FindData;
import com.github.dokandev.dokanjava.WinError;
import com.github.dokandev.dokanjava.util.Status;

public class MemoryFS implements DokanOperations {

  public static final int FILE_CASE_PRESERVED_NAMES = 0x00000002;
  public static final int FILE_FILE_COMPRESSION = 0x00000010;
  public static final int FILE_SUPPORTS_SPARSE_FILES = 0x00000040;
  public static final int FILE_UNICODE_ON_DISK = 0x00000004;

  public static final int SUPPORTED_FLAGS = FILE_CASE_PRESERVED_NAMES | FILE_UNICODE_ON_DISK
      | FILE_SUPPORTS_SPARSE_FILES;
  final static int volumeSerialNumber = 5454;

  private final ConcurrentHashMap<String, MemFileInfo> files = new ConcurrentHashMap<>();
  private final AtomicLong idGenerator = new AtomicLong();
  private final long rootCreateTime = FileTimeUtils.toFileTime(new Date());
  
  private long rootLastWrite = rootCreateTime;

  public static void main(String[] args) {
    String driveLetter = (args.length == 0) ? "S:\\" : args[0];
    new MemoryFS().mount(driveLetter);
  }

  void mount(String mountPoint) {
    showVersions();
    DokanOptions dokanOptions = DokanOptions
        .builder(mountPoint)
        .version(730)
        .threadCount(1)
        .options(REMOVABLE, KEEP_ALIVE, DEBUG)
        .globalContext(12345L)
        .build();
    Status status = Status.from(Dokan.mount(dokanOptions, this));
    log("[MemoryFS] status = %s", status);

    if (status != Status.SUCCESS) {
      System.exit(-1);
    } else {
      log("######## mounted " + mountPoint + " with result " + status + " #############");
    }
  }
  
  void showVersions() {
    log("user library version: %s", Dokan.version());
    log("driver version: %s", Dokan.driverVersion());
  }
  static void log(String format, Object... arguments) {
    System.out.println(String.format(format, arguments));
  }

  private long nextFileHandleId() {
    return idGenerator.getAndIncrement();
  }
  
  
  public long onCreateFile(String fileName, int desiredAccess, int shareMode,
      int creationDisposition, int flagsAndAttributes, DokanFileInfo arg5)
      throws DokanOperationException {
    CreationDisposition disposition = CreationDisposition.build(creationDisposition);

    log("[onCreateFile] " + fileName + ",\n\t creationDisposition = " + creationDisposition
        + ",\n\t desiredAccess = " + FileAccess.toString(desiredAccess) + ",\n\t flags = "
        + FileFlag.toString(flagsAndAttributes) + ",\n\t attributes = "
        + FileAttribute.toString(flagsAndAttributes));

    if (fileName.equals("\\")) {
      switch (disposition) {
        case CREATE_NEW:
        case CREATE_ALWAYS:
          throw new DokanOperationException(WinError.ERROR_ALREADY_EXISTS);
        case OPEN_ALWAYS:
        case OPEN_EXISTING:
        case TRUNCATE_EXISTING:
          return nextFileHandleId();
        case UNDEFINED:
          assert (false);
      }
    } else if (files.containsKey(fileName)) {
      switch (disposition) {
        case CREATE_NEW:
          throw new DokanOperationException(WinError.ERROR_ALREADY_EXISTS);
        case OPEN_ALWAYS:
        case OPEN_EXISTING:
          return nextFileHandleId();
        case CREATE_ALWAYS:
        case TRUNCATE_EXISTING:
          files.get(fileName).content.clear();
          updateParentLastWrite(fileName);
          return nextFileHandleId();
        case UNDEFINED:
          assert (false);
      }
    } else {
      switch (disposition) {
        case CREATE_NEW:
        case CREATE_ALWAYS:
        case OPEN_ALWAYS:
          MemFileInfo fi = new MemFileInfo(fileName, false);
          files.put(fi.fileName, fi);
          updateParentLastWrite(fileName);
          return nextFileHandleId();
        case OPEN_EXISTING:
        case TRUNCATE_EXISTING:
          throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
        case UNDEFINED:
          assert (false);
      }
    }
    throw new DokanOperationException(WinError.ERROR_INVALID_FUNCTION);
  }

  public long onOpenDirectory(String pathName, DokanFileInfo arg1) throws DokanOperationException {
    log("[onOpenDirectory] " + pathName);
    if (pathName.equals("\\"))
      return nextFileHandleId();

    pathName = Utils.trimTailBackSlash(pathName);
    log("[onOpenDirectory] step 2");
    if (files.containsKey(pathName))
      return nextFileHandleId();
    else
      throw new DokanOperationException(WinError.ERROR_PATH_NOT_FOUND);
  }

  public void onCreateDirectory(String fileName, DokanFileInfo file) throws DokanOperationException {
    log("[onCreateDirectory] " + fileName);
    fileName = Utils.trimTailBackSlash(fileName);
    if (files.containsKey(fileName) || fileName.length() == 0)
      throw new DokanOperationException(WinError.ERROR_ALREADY_EXISTS);
    MemFileInfo fi = new MemFileInfo(fileName, true);
    files.put(fi.fileName, fi);
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
    MemFileInfo fi = files.get(fileName);
    if (fi == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
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
      throw new DokanOperationException(WinError.ERROR_READ_FAULT);
    }
  }

  public int onWriteFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo arg3)
      throws DokanOperationException {
    log("[onWriteFile] " + fileName);
    MemFileInfo fi = files.get(fileName);
    if (fi == null) {
      log("[onWriteFile] file not found");
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
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
      throw new DokanOperationException(WinError.ERROR_WRITE_FAULT);
    }
  }

  public void onSetEndOfFile(String fileName, long length, DokanFileInfo arg2)
      throws DokanOperationException {
    log("[onSetEndOfFile] " + fileName);
    MemFileInfo fi = files.get(fileName);
    if (fi == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    if (fi.getFileSize() == length)
      return;
    if (fi.getFileSize() < length) {
      byte[] tmp = new byte[(int) length - fi.getFileSize()];
      fi.content.add(tmp);
    } else {
      fi.content.remove((int) length, fi.getFileSize() - (int) length);
    }
  }

  public void onFlushFileBuffers(String fileName, DokanFileInfo fileInfo)
      throws DokanOperationException {
    log("[onFlushFileBuffers] " + fileName);
  }

  public ByHandleFileInformation onGetFileInformation(String fileName, DokanFileInfo arg1)
      throws DokanOperationException {
    log("[onGetFileInformation] " + fileName);
    if (fileName.equals("\\")) {
      return new ByHandleFileInformation(
          FileAttribute.FileAttributeFlags.FILE_ATTRIBUTE_NORMAL.getValue()
              | FileAttribute.FileAttributeFlags.FILE_ATTRIBUTE_DIRECTORY.getValue(),
          rootCreateTime, rootCreateTime, rootLastWrite, volumeSerialNumber, 0, 1, 1);
    }
    MemFileInfo fi = files.get(fileName);
    if (fi == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    return fi.toByHandleFileInformation();
  }

  public Win32FindData[] onFindFiles(String pathName, DokanFileInfo arg1)
      throws DokanOperationException {
    log("[onFindFiles] " + pathName);
    Collection<MemFileInfo> fis = files.values();
    ArrayList<Win32FindData> files = new ArrayList<Win32FindData>();
    File pathNameFile = new File(pathName);
    for (MemFileInfo fi : fis) {
      if (pathNameFile.equals(new File(fi.fileName).getParentFile())) {
        files.add(fi.toWin32FindData());
      }
    }
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
    MemFileInfo fi = files.get(fileName);
    if (fi == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    fi.fileAttribute = fileAttributes;
  }

  public void onSetFileTime(String fileName, long creationTime, long lastAccessTime,
      long lastWriteTime, DokanFileInfo arg4) throws DokanOperationException {
    log("[onSetFileTime] " + fileName);
    MemFileInfo fi = files.get(fileName);
    if (fi == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    fi.creationTime = creationTime;
    fi.lastAccessTime = lastAccessTime;
    fi.lastWriteTime = lastWriteTime;
  }

  public void onDeleteFile(String fileName, DokanFileInfo arg1) throws DokanOperationException {
    log("[onDeleteFile] " + fileName);
    MemFileInfo removed = files.remove(fileName);
    if (removed == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    updateParentLastWrite(fileName);
  }

  public void onDeleteDirectory(String fileName, DokanFileInfo arg1) throws DokanOperationException {
    log("[onDeleteDirectory] " + fileName);
    MemFileInfo removed = files.remove(fileName);
    if (removed == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    updateParentLastWrite(fileName);
  }

  public void onMoveFile(String existingFileName, String newFileName, boolean replaceExisiting,
      DokanFileInfo arg3) throws DokanOperationException {
    log("==> [onMoveFile] " + existingFileName + " -> " + newFileName + ", replaceExisiting = "
        + replaceExisiting);
    MemFileInfo existing = files.get(existingFileName);
    if (existing == null)
      throw new DokanOperationException(WinError.ERROR_FILE_NOT_FOUND);
    // TODO Fix this
    if (existing.isDirectory)
      throw new DokanOperationException(WinError.ERROR_DIRECTORY);
    MemFileInfo newFile = files.get(newFileName);
    if (newFile != null && !replaceExisiting)
      throw new DokanOperationException(WinError.ERROR_FILE_EXISTS);
    files.remove(existingFileName);
    existing.fileName = newFileName;
    files.put(newFileName, existing);
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


  public DokanDiskFreeSpace onGetDiskFreeSpace(DokanFileInfo fileInfo)
      throws DokanOperationException {
    log("[onGetDiskFreeSpace] ");
    DokanDiskFreeSpace free = new DokanDiskFreeSpace();
    // TODO: this is completely wrong, the size is not calculated
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
      MemFileInfo fi = files.get(parent);
      if (fi == null)
        return;
      fi.lastWriteTime = FileTimeUtils.toFileTime(new Date());
    }
  }
}
