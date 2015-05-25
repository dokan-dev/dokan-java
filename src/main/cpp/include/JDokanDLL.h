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

#pragma once

void InitMethodIDs(JNIEnv *env);

int DOKAN_CALLBACK CreateFile(
		LPCWSTR,      // FileName
		DWORD,        // DesiredAccess
		DWORD,        // ShareMode
		DWORD,        // CreationDisposition
		DWORD,        // FlagsAndAttributes
		//HANDLE,       // TemplateFile
		PDOKAN_FILE_INFO);

int DOKAN_CALLBACK OnOpenDirectory(
	LPCWSTR,				// FileName
	PDOKAN_FILE_INFO);

int DOKAN_CALLBACK OnCreateDirectory(
	LPCWSTR,				// FileName
	PDOKAN_FILE_INFO);

int DOKAN_CALLBACK OnCleanup(
	LPCWSTR,      // FileName
	PDOKAN_FILE_INFO);

int DOKAN_CALLBACK OnCloseFile(
	LPCWSTR,      // FileName
	PDOKAN_FILE_INFO);

int DOKAN_CALLBACK OnReadFile(
	LPCWSTR,  // FileName
	LPVOID,   // Buffer
	DWORD,    // NumberOfBytesToRead
	LPDWORD,  // NumberOfBytesRead
	LONGLONG, // Offset
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnWriteFile(
	LPCWSTR,  // FileName
	LPCVOID,  // Buffer
	DWORD,    // NumberOfBytesToWrite
	LPDWORD,  // NumberOfBytesWritten
	LONGLONG, // Offset
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnFlushFileBuffers(
	LPCWSTR, // FileName
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnGetFileInformation(
	LPCWSTR,          // FileName
	LPBY_HANDLE_FILE_INFORMATION, // Buffer
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnFindFiles(
	LPCWSTR,			// PathName
	PFillFindData,		// call this function with PWIN32_FIND_DATAW
	PDOKAN_FILE_INFO);  //  (see PFillFindData definition)


// You should implement either FindFires or FindFilesWithPattern
int DOKAN_CALLBACK OnFindFilesWithPattern(
	LPCWSTR,			// PathName
	LPCWSTR,			// SearchPattern
	PFillFindData,		// call this function with PWIN32_FIND_DATAW
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnSetFileAttributes(
	LPCWSTR, // FileName
	DWORD,   // FileAttributes
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnSetFileTime(
	LPCWSTR,		// FileName
	CONST FILETIME*, // CreationTime
	CONST FILETIME*, // LastAccessTime
	CONST FILETIME*, // LastWriteTime
	PDOKAN_FILE_INFO);

int DOKAN_CALLBACK OnDeleteFile(
	LPCWSTR, // FileName
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnDeleteDirectory( 
	LPCWSTR, // FileName
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnMoveFile(
	LPCWSTR, // ExistingFileName
	LPCWSTR, // NewFileName
	BOOL,	// ReplaceExisiting
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnSetEndOfFile(
	LPCWSTR,  // FileName
	LONGLONG, // Length
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnLockFile(
	LPCWSTR, // FileName
	LONGLONG, // ByteOffset
	LONGLONG, // Length
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnUnlockFile(
	LPCWSTR, // FileName
	LONGLONG,// ByteOffset
	LONGLONG,// Length
	PDOKAN_FILE_INFO);


// Neither GetDiskFreeSpace nor GetVolumeInformation
// save the DokanFileContext->Context.
// Before these methods are called, CreateFile may not be called.
// (ditto CloseFile and Cleanup)

// see Win32 API GetDiskFreeSpaceEx
int DOKAN_CALLBACK OnGetDiskFreeSpace(
	PULONGLONG, // FreeBytesAvailable
	PULONGLONG, // TotalNumberOfBytes
	PULONGLONG, // TotalNumberOfFreeBytes
	PDOKAN_FILE_INFO);


// see Win32 API GetVolumeInformation
int DOKAN_CALLBACK OnGetVolumeInformation(
	LPWSTR, // VolumeNameBuffer
	DWORD,	// VolumeNameSize
	LPDWORD,// VolumeSerialNumber
	LPDWORD,// MaximumComponentLength
	LPDWORD,// FileSystemFlags
	LPWSTR,	// FileSystemNameBuffer
	DWORD,	// FileSystemNameSize
	PDOKAN_FILE_INFO);


int DOKAN_CALLBACK OnUnmount(
	PDOKAN_FILE_INFO);

JNIEnv * get_env();
void     release_env(JNIEnv *env);
