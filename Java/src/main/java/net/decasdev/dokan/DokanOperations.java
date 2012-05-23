/*
  JDokan : Java library for Dokan

  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
  				2009 Caleido AG   http://www.wuala.com/

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 3 of the License, or (at your option) any
later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along
with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package net.decasdev.dokan;

import java.nio.ByteBuffer;

/**
 * Dokan callbacks.
 */
public interface DokanOperations {
	/**
	 * If file is a directory, CreateFile (not OpenDirectory) may be called. In this case, CreateFile
	 * should return 0 when that directory can be opened. You should set TRUE on
	 * DokanFileInfo->IsDirectory when file is a directory. When CreationDisposition is CREATE_ALWAYS or
	 * OPEN_ALWAYS and a file already exists, you should return ERROR_ALREADY_EXISTS(183) (not negative
	 * value)
	 * 
	 * @param fileName
	 * @param desiredAccess
	 * @param shareMode
	 * @param creationDisposition
	 * @param flagsAndAttributes
	 * @param fileInfo
	 * @return handle
	 * @throws DokanOperationException
	 */
	public long onCreateFile(String fileName, int desiredAccess, int shareMode, int creationDisposition,
			int flagsAndAttributes, DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @return handle
	 * @throws DokanOperationException
	 */
	public long onOpenDirectory(String fileName, DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onCreateDirectory(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onCleanup(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onCloseFile(String fileName, DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param buffer
	 * @param offset
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public int onReadFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param buffer
	 * @param offset
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public int onWriteFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onFlushFileBuffers(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public ByHandleFileInformation onGetFileInformation(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param pathName
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public Win32FindData[] onFindFiles(String pathName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param pathName
	 * @param searchPattern
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public Win32FindData[] onFindFilesWithPattern(String pathName, String searchPattern,
			DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileAttributes
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onSetFileAttributes(String fileName, int fileAttributes, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param creationTime FILETIME
	 * @param lastAccessTime FILETIME
	 * @param lastWriteTime FILETIME
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onSetFileTime(String fileName, long creationTime, long lastAccessTime,
			long lastWriteTime, DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onDeleteFile(String fileName, DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onDeleteDirectory(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param existingFileName
	 * @param newFileName
	 * @param replaceExisiting
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onMoveFile(String existingFileName, String newFileName, boolean replaceExisiting,
			DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param length
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onSetEndOfFile(String fileName, long length, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param byteOffset
	 * @param length
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onLockFile(String fileName, long byteOffset, long length, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileName
	 * @param byteOffset
	 * @param length
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onUnlockFile(String fileName, long byteOffset, long length, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * Neither GetDiskFreeSpace nor GetVolumeInformation save the DokanFileContext->Context. Before these
	 * methods are called, CreateFile may not be called. (ditto CloseFile and Cleanup)
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public DokanDiskFreeSpace onGetDiskFreeSpace(DokanFileInfo fileInfo) throws DokanOperationException;

	/**
	 * 
	 * @param volumeName
	 * @param fileInfo
	 * @return
	 * @throws DokanOperationException
	 */
	public DokanVolumeInformation onGetVolumeInformation(String volumeName, DokanFileInfo fileInfo)
			throws DokanOperationException;

	/**
	 * 
	 * @param fileInfo
	 * @throws DokanOperationException
	 */
	public void onUnmount(DokanFileInfo fileInfo) throws DokanOperationException;
}
