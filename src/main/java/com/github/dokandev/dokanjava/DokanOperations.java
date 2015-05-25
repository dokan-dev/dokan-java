/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/ 
 *               2009 Caleido AG http://www.wuala.com/
 *               2015 Simon Herter <sim.herter@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.dokandev.dokanjava;

import java.nio.ByteBuffer;

public interface DokanOperations {

  int createFile(String fileName, int desiredAccess, int shareMode, int creationDisposition,
      int flagsAndAttributes, DokanFileInfo fileInfo);

  public long onOpenDirectory(String fileName, DokanFileInfo fileInfo)
      throws DokanException;

  public void onCreateDirectory(String fileName, DokanFileInfo fileInfo)
      throws DokanException;

  public void onCleanup(String fileName, DokanFileInfo fileInfo) throws DokanException;

  public void onCloseFile(String fileName, DokanFileInfo fileInfo) throws DokanException;

  public int onReadFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo fileInfo)
      throws DokanException;

  public int onWriteFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo fileInfo)
      throws DokanException;

  public void onFlushFileBuffers(String fileName, DokanFileInfo fileInfo)
      throws DokanException;

  public ByHandleFileInformation onGetFileInformation(String fileName, DokanFileInfo fileInfo)
      throws DokanException;

  public Win32FindData[] onFindFiles(String pathName, DokanFileInfo fileInfo)
      throws DokanException;

  public Win32FindData[] onFindFilesWithPattern(String pathName, String searchPattern,
      DokanFileInfo fileInfo) throws DokanException;

  public void onSetFileAttributes(String fileName, int fileAttributes, DokanFileInfo fileInfo)
      throws DokanException;

  /**
   * @param creationTime FILETIME
   * @param lastAccessTime FILETIME
   * @param lastWriteTime FILETIME
   */
  public void onSetFileTime(String fileName, long creationTime, long lastAccessTime,
      long lastWriteTime, DokanFileInfo fileInfo) throws DokanException;

  public void onDeleteFile(String fileName, DokanFileInfo fileInfo) throws DokanException;

  public void onDeleteDirectory(String fileName, DokanFileInfo fileInfo)
      throws DokanException;

  public void onMoveFile(String existingFileName, String newFileName, boolean replaceExisiting,
      DokanFileInfo fileInfo) throws DokanException;

  public void onSetEndOfFile(String fileName, long length, DokanFileInfo fileInfo)
      throws DokanException;

  public void onLockFile(String fileName, long byteOffset, long length, DokanFileInfo fileInfo)
      throws DokanException;

  public void onUnlockFile(String fileName, long byteOffset, long length, DokanFileInfo fileInfo)
      throws DokanException;

  /**
   * Neither GetDiskFreeSpace nor GetVolumeInformation save the DokanFileContext-&gt;Context. Before
   * these methods are called, CreateFile may not be called. (ditto CloseFile and Cleanup)
   */
  public DokanDiskFreeSpace onGetDiskFreeSpace(DokanFileInfo fileInfo)
      throws DokanException;


  public DokanVolumeInformation onGetVolumeInformation(String volumeName, DokanFileInfo fileInfo)
      throws DokanException;

  public void onUnmount(DokanFileInfo fileInfo) throws DokanException;
}
