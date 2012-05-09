/*
  JDokan : Java library for Dokan

  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/

  http://decas-dev.net/en

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

import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.NativeJavaObject;

/**
 * When using Rhino, use RhinoMounterWrapper.mount() insted of Dokan.mount().
 */
public class RhinoMountWrapper implements DokanOperations {
	private DokanOperations operations;

	private RhinoMountWrapper() {
	}

	public static void mount(DokanOptions options, DokanOperations operations) {
		new RhinoMountWrapper().mount2(options, operations);
	}

	void mount2(DokanOptions options, DokanOperations operations) {
		this.operations = operations;
		Dokan.mount(options, this);
	}

	void rethrow(JavaScriptException e) throws DokanOperationException {
		Object obj = ((NativeJavaObject) e.getValue()).unwrap();
		if (obj instanceof DokanOperationException)
			throw (DokanOperationException) obj;
		else
			throw new DokanOperationException(1);
	}

	public void onCleanup(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			operations.onCleanup(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onCloseFile(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			operations.onCloseFile(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onCreateDirectory(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onCreateDirectory(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public long onCreateFile(String fileName, int desiredAccess, int shareMode, int creationDisposition,
			int flagsAndAttributes, DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			return operations.onCreateFile(fileName, desiredAccess, shareMode, creationDisposition,
					flagsAndAttributes, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return 0;
		}
	}

	public void onDeleteDirectory(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onDeleteDirectory(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onDeleteFile(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			operations.onDeleteFile(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public Win32FindData[] onFindFiles(String pathName, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			return operations.onFindFiles(pathName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return null;
		}
	}

	public Win32FindData[] onFindFilesWithPattern(String pathName, String searchPattern,
			DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			return operations.onFindFilesWithPattern(pathName, searchPattern, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return null;
		}
	}

	public void onFlushFileBuffers(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onFlushFileBuffers(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public DokanDiskFreeSpace onGetDiskFreeSpace(DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			return operations.onGetDiskFreeSpace(fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return null;
		}
	}

	public ByHandleFileInformation onGetFileInformation(String fileName, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			return operations.onGetFileInformation(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return null;
		}
	}

	public DokanVolumeInformation onGetVolumeInformation(String volumeName, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			return operations.onGetVolumeInformation(volumeName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return null;
		}
	}

	public void onLockFile(String fileName, long byteOffset, long length, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onLockFile(fileName, byteOffset, length, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onMoveFile(String existingFileName, String newFileName, boolean replaceExisiting,
			DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			operations.onMoveFile(existingFileName, newFileName, replaceExisiting, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public long onOpenDirectory(String fileName, DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			return operations.onOpenDirectory(fileName, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return 0;
		}
	}

	public int onReadFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			return operations.onReadFile(fileName, buffer, offset, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return 0;
		}
	}

	public void onSetEndOfFile(String fileName, long length, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onSetEndOfFile(fileName, length, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onSetFileAttributes(String fileName, int fileAttributes, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onSetFileAttributes(fileName, fileAttributes, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onSetFileTime(String fileName, long creationTime, long lastAccessTime,
			long lastWriteTime, DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			operations.onSetFileTime(fileName, creationTime, lastAccessTime, lastWriteTime, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onUnlockFile(String fileName, long byteOffset, long length, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			operations.onUnlockFile(fileName, byteOffset, length, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public void onUnmount(DokanFileInfo fileInfo) throws DokanOperationException {
		try {
			operations.onUnmount(fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
		}
	}

	public int onWriteFile(String fileName, ByteBuffer buffer, long offset, DokanFileInfo fileInfo)
			throws DokanOperationException {
		try {
			return operations.onWriteFile(fileName, buffer, offset, fileInfo);
		} catch (JavaScriptException e) {
			rethrow(e);
			return 0;
		}
	}
}
