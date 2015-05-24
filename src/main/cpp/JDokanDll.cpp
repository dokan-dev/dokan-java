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

#include "stdafx.h"

#include "jdokan_jni.h"
#include "dokan.h"
#include "JDokanDLL.h"
#include "Utils.h"
#include "DokanObjUtils.h"
#include "IDs.h"

/*
 * Class:     com_github_dokandev_dokanjava_Dokan
 * Method:    mount
 * Signature: (Lcom/github/dokandev/dokanjava/DokanOptions;Lcom/github/dokandev/dokanjava/DokanOperations;)I
 */
JNIEXPORT jint JNICALL Java_com_github_dokandev_dokanjava_Dokan_mount
  (JNIEnv *env, jclass, jobject joptions, jobject joperations)
{
	try {
		if (jvm != NULL)
			throw "You cannot mount twice at this version of Dokan";
		env->GetJavaVM(&jvm);
		gOperations =  env->NewGlobalRef(joperations);

		InitMethodIDs(env);

		DOKAN_OPTIONS options;
		ZeroMemory(&options, sizeof(DOKAN_OPTIONS));
		options.Version = env->GetShortField(joptions, versionID);
		options.ThreadCount = env->GetShortField(joptions, threadCountID);
		options.Options = env->GetIntField(joptions, optionsID);
		options.GlobalContext = env->GetLongField(joptions, globalContextID);
		/* mountPoint */
		jstring mountPoint = (jstring) env->GetObjectField(joptions, mountPointID);
		int len = env->GetStringLength(mountPoint);
		const jchar* chars = env->GetStringChars(mountPoint, NULL);
		wchar_t* wsz = new wchar_t[len+1];
		memcpy(wsz, chars, len*2);
	    wsz[len] = 0;
		options.MountPoint = wsz;
		env->ReleaseStringChars(mountPoint, chars);
		/* end MountPoint */
		


		DOKAN_OPERATIONS operations;
		ZeroMemory(&operations, sizeof(DOKAN_OPERATIONS));
		operations.CreateFile = OnCreateFile;
		operations.OpenDirectory = OnOpenDirectory;
		operations.CreateDirectoryW = OnCreateDirectory;
		operations.Cleanup = OnCleanup;
		operations.CloseFile = OnCloseFile;
		operations.ReadFile = OnReadFile;
		operations.WriteFile = OnWriteFile;
		operations.FlushFileBuffers = OnFlushFileBuffers;
		operations.GetFileInformation = OnGetFileInformation;
		operations.FindFiles = OnFindFiles;
		//operations.FindFilesWithPattern = OnFindFilesWithPattern;
		operations.SetFileAttributesW = OnSetFileAttributes;
		operations.SetFileTime = OnSetFileTime;
		operations.DeleteFileW = OnDeleteFile;
		operations.DeleteDirectory = OnDeleteDirectory;
		operations.MoveFileW = OnMoveFile;
		operations.SetEndOfFile = OnSetEndOfFile;
		operations.LockFile = OnLockFile;
		operations.UnlockFile = OnUnlockFile;
		operations.GetDiskFreeSpace = OnGetDiskFreeSpace;
		operations.GetVolumeInformation = OnGetVolumeInformation;
		operations.Unmount = OnUnmount;

		return DokanMain(&options, &operations);
	} catch(const char* msg) {
		env->ThrowNew(env->FindClass("java/lang/NoSuchFieldError"), msg);
		return FALSE;
	}
}

int DOKAN_CALLBACK OnCreateFile(
		LPCWSTR		FileName,
		DWORD		DesiredAccess,
		DWORD		ShareMode,
		DWORD		CreationDisposition,
		DWORD		FlagsAndAttributes,
		//HANDLE,       // TemplateFile
		PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnCreateFile] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		jlong handle = env->CallLongMethod(gOperations, onCreateFileID, 
			jfileName, DesiredAccess, ShareMode, CreationDisposition,
			FlagsAndAttributes, jdokanFileInfo);
		result = GetOperationResult(env);

		if (result == 0) {
			DokanFileInfo->Context = handle;
		}
		LOG(L"[OnCreateFile] result = %d, handle = %d\n", result, handle);
	} catch(const char* msg) {
		LOGA("[OnCreateFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnOpenDirectory(
	LPCWSTR				FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnOpenDirectory] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		jlong handle = env->CallLongMethod(gOperations, onOpenDirectoryID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);

		if (result == 0) {
			DokanFileInfo->Context = handle;
		}
	} catch(const char* msg) {
		LOGA("[OnOpenDirectory] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnCreateDirectory(
	LPCWSTR				FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnCreateDirectory] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onCreateDirectoryID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnCreateDirectory] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnCleanup(
	LPCWSTR      FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnCleanup] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onCleanupID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnCleanup] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnCloseFile(
	LPCWSTR      FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnCloseFile] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onCloseFileID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnCloseFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnReadFile(
	LPCWSTR  FileName,
	LPVOID   Buffer,
	DWORD    NumberOfBytesToRead,
	LPDWORD  NumberOfBytesRead,
	LONGLONG Offset,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnReadFile] FileName = %s, Offset = %lld, NumberOfBytesToRead = %d\n", 
		FileName, Offset, NumberOfBytesToRead);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		DWORD readed = env->CallIntMethod(gOperations, onReadFileID, 
			jfileName, 
			env->NewDirectByteBuffer(Buffer, NumberOfBytesToRead),
			Offset,
			jdokanFileInfo);
		if (NumberOfBytesRead)
			*NumberOfBytesRead = readed;
		result = GetOperationResult(env);
		if(result != 0) {
			LOGA("[OnReadFile] result = %d\n", result);
		}
	} catch(const char* msg) {
		LOGA("[OnReadFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnWriteFile(
	LPCWSTR  FileName,
	LPCVOID  Buffer,
	DWORD    NumberOfBytesToWrite,
	LPDWORD  NumberOfBytesWritten,
	LONGLONG Offset,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnWriteFile] FileName = %s, Offset = %lld, NumberOfBytesToWrite = %d\n", 
		FileName, Offset, NumberOfBytesToWrite);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		// Some one please modify here for the faster way !!
		LPVOID tmpBuffer = malloc(NumberOfBytesToWrite);
		if(tmpBuffer == NULL)
			throw "Cannot allocate memory";
		CopyMemory(tmpBuffer, Buffer, NumberOfBytesToWrite);
		DWORD written = env->CallIntMethod(gOperations, onWriteFileID, 
			jfileName, 
			env->NewDirectByteBuffer(tmpBuffer, NumberOfBytesToWrite),
			Offset,
			jdokanFileInfo);
		free(tmpBuffer);

		if (NumberOfBytesWritten)
			*NumberOfBytesWritten = written;
		result = GetOperationResult(env);
		if(result != 0) {
			LOGA("[OnWriteFile] ERROR result = %d\n", result);
		} else {
			LOGA("[OnWriteFile] written = %d\n", written);
		}
	} catch(const char* msg) {
		LOGA("[OnWriteFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnFlushFileBuffers(
	LPCWSTR FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnFlushFileBuffers] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onFlushFileBuffersID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnFlushFileBuffers] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnGetFileInformation(
	LPCWSTR          FileName,
	LPBY_HANDLE_FILE_INFORMATION ByHandleFileInfo,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnGetFileInformation] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		jobject jobj = env->CallObjectMethod(gOperations, onGetFileInformationID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);

		if (result == 0) {
			ToByHandleFileInfo(env, jobj, ByHandleFileInfo);
			LOGA("[OnGetFileInformation] %d %d %d\n", 
				ByHandleFileInfo->dwFileAttributes,
				ByHandleFileInfo->nFileSizeHigh,
				ByHandleFileInfo->nFileSizeLow);
		}
	} catch(const char* msg) {
		LOGA("[OnGetFileInformation] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnFindFiles(
	LPCWSTR			PathName,
	PFillFindData	pFillFindData,		// call this function with PWIN32_FIND_DATAW
	PDOKAN_FILE_INFO DokanFileInfo)   // (see PFillFindData definition)
{
	LOG(L"[OnFindFiles] PathName = %s\n", PathName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jpathName = ToJavaString(env, PathName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		jobjectArray ary = (jobjectArray)env->CallObjectMethod(gOperations, 
			onFindFilesID, 
			jpathName, jdokanFileInfo);
		result = GetOperationResult(env);

		if (result == 0 && ary != NULL && pFillFindData != NULL) {
			for(int i = 0; i < env->GetArrayLength(ary); i++) {
				WIN32_FIND_DATAW win32FindData;
				ToWin32FindData(env, env->GetObjectArrayElement(ary, i), &win32FindData);
				pFillFindData(&win32FindData, DokanFileInfo);
			}
		}
	} catch(const char* msg) {
		LOGA("[OnFindFiles] %s\n", msg); 
	}

	release_env(env);
	return result;
}

//You should implement either FindFires or FindFilesWithPattern
int DOKAN_CALLBACK OnFindFilesWithPattern(
	LPCWSTR			PathName,
	LPCWSTR			SearchPattern,
	PFillFindData	pFillFindData,		// call this function with PWIN32_FIND_DATAW
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnFindFilesWithPattern] PathName = %s\n", PathName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jpathName = ToJavaString(env, PathName);
		jstring jsearchPattern = ToJavaString(env, SearchPattern);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		jobjectArray ary = (jobjectArray)env->CallObjectMethod(gOperations, 
			onFindFilesWithPatternID, 
			jpathName, jsearchPattern, jdokanFileInfo);
		result = GetOperationResult(env);

		if (result == 0 && ary != NULL && pFillFindData != NULL) {
			for(int i = 0; i < env->GetArrayLength(ary); i++) {
				WIN32_FIND_DATAW win32FindData;
				ToWin32FindData(env, env->GetObjectArrayElement(ary, i), &win32FindData);
				pFillFindData(&win32FindData, DokanFileInfo);
			}
		}
	} catch(const char* msg) {
		LOGA("[OnFindFilesWithPattern] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnSetFileAttributes(
	LPCWSTR FileName,
	DWORD   FileAttributes,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnSetFileAttributes] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onSetFileAttributesID, 
			jfileName, FileAttributes, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnSetFileAttributes] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnSetFileTime(
	LPCWSTR		FileName,
	CONST FILETIME* CreationTime,
	CONST FILETIME* LastAccessTime,
	CONST FILETIME* LastWriteTime,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnSetFileTime] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onSetFileTimeID, 
			jfileName, FileTime2LongLong(CreationTime), 
			FileTime2LongLong(LastAccessTime), FileTime2LongLong(LastWriteTime), 
			jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnSetFileTime] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnDeleteFile(
	LPCWSTR FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnDeleteFile] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onDeleteFileID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnDeleteFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnDeleteDirectory( 
	LPCWSTR FileName,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnDeleteDirectory] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onDeleteDirectoryID, 
			jfileName, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnDeleteDirectory] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnMoveFile(
	LPCWSTR ExistingFileName,
	LPCWSTR NewFileName,
	BOOL	ReplaceExisiting,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnMoveFile] ExistingFileName = %s\n", ExistingFileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jExistingFileName = ToJavaString(env, ExistingFileName);
		jstring jNewFileName = ToJavaString(env, NewFileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onMoveFileID, 
			jExistingFileName, jNewFileName, ReplaceExisiting, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnMoveFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnSetEndOfFile(
	LPCWSTR  FileName,
	LONGLONG Length,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnSetEndOfFile] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onSetEndOfFileID, 
			jfileName, Length, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnSetEndOfFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnLockFile(
	LPCWSTR		FileName,
	LONGLONG	ByteOffset,
	LONGLONG	Length,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnLockFile] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onLockFileID, 
			jfileName, ByteOffset, Length, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnLockFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

int DOKAN_CALLBACK OnUnlockFile(
	LPCWSTR		FileName,
	LONGLONG	ByteOffset,
	LONGLONG	Length,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnUnlockFile] FileName = %s\n", FileName);
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jfileName = ToJavaString(env, FileName);
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onUnlockFileID, 
			jfileName, ByteOffset, Length, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnUnlockFile] %s\n", msg); 
	}

	release_env(env);
	return result;
}

// Neither GetDiskFreeSpace nor GetVolumeInformation
// save the DokanFileContext->Context.
// Before these methods are called, CreateFile may not be called.
// (ditto CloseFile and Cleanup)

// see Win32 API GetDiskFreeSpaceEx
int DOKAN_CALLBACK OnGetDiskFreeSpace(
	PULONGLONG FreeBytesAvailable,
	PULONGLONG TotalNumberOfBytes,
	PULONGLONG TotalNumberOfFreeBytes,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnGetDiskFreeSpace]\n");
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		NewDokanDiskFreeSpace(env);
		
		jobject jdiskFreeSpace = env->CallObjectMethod(gOperations, onGetDiskFreeSpaceID, jdokanFileInfo);
		result = GetOperationResult(env);

		if (FreeBytesAvailable)
			*FreeBytesAvailable = env->GetLongField(jdiskFreeSpace, freeBytesAvailableID);
		if (TotalNumberOfBytes)
			*TotalNumberOfBytes = env->GetLongField(jdiskFreeSpace, totalNumberOfBytesID);
		if (TotalNumberOfFreeBytes)
			*TotalNumberOfFreeBytes = env->GetLongField(jdiskFreeSpace, totalNumberOfFreeBytesID);
	} catch(const char* msg) {
		LOG(L"[OnGetDiskFreeSpace] %s\n", msg);
	}

	release_env(env);
	return result;

}

// see Win32 API GetVolumeInformation
int DOKAN_CALLBACK OnGetVolumeInformation(
	LPWSTR		VolumeNameBuffer,
	DWORD		VolumeNameSize,
	LPDWORD		VolumeSerialNumber,
	LPDWORD		MaximumComponentLength,
	LPDWORD		FileSystemFlags,
	LPWSTR		FileSystemNameBuffer,
	DWORD		FileSystemNameSize,
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnGetVolumeInformation]\n");
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jstring jvolumeName = ToJavaString(env, L"dokan");
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		jobject jvolumeInfo= env->CallObjectMethod(gOperations, onGetVolumeInformationID, jvolumeName, jdokanFileInfo);
		result = GetOperationResult(env);

		if (VolumeSerialNumber)
			*VolumeSerialNumber = env->GetIntField(jvolumeInfo, volumeSerialNumberID);
		if (MaximumComponentLength)
			*MaximumComponentLength = env->GetIntField(jvolumeInfo, maximumComponentLengthID);
		if (FileSystemFlags)
			*FileSystemFlags = env->GetIntField(jvolumeInfo, fileSystemFlagsID);

		// VolumeName, FileSystemName
		CopyStringField(env, jvolumeInfo, volumeNameID, VolumeNameBuffer, VolumeNameSize);
		CopyStringField(env, jvolumeInfo, fileSystemNameID, FileSystemNameBuffer, FileSystemNameSize);
	} catch(const char* msg) {
		LOGA("[OnGetVolumeInformation] %s\n", msg); 
	}

	release_env(env);
	return result;

}

int DOKAN_CALLBACK OnUnmount(
	PDOKAN_FILE_INFO DokanFileInfo)
{
	LOG(L"[OnUnmount]\n");
	JNIEnv* env = get_env();
	//jvm->AttachCurrentThread((void **)&env, NULL);

	int result = -ERROR_GEN_FAILURE;
	try {
		jobject jdokanFileInfo = ToDokanFileInfoJavaObject(env, DokanFileInfo);
		
		env->CallVoidMethod(gOperations, onUnmountID, jdokanFileInfo);
		result = GetOperationResult(env);
	} catch(const char* msg) {
		LOGA("[OnUnmount] %s\n", msg); 
	}

	release_env(env);
	return result;
}

///*
// * Class:     com_github_dokandev_dokanjava_Dokan
// * Method:    unmount
// * Signature: (C)Z
// */
//JNIEXPORT jboolean JNICALL Java_com_github_dokandev_dokanjava_Dokan_unmount
//  (JNIEnv *, jclass, jchar jdriveLetter)
//{
//	return DokanUnmount(jdriveLetter);
//}

/*
 * Class:     com_github_dokandev_dokanjava_Dokan
 * Method:    removeMountPoint
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jboolean JNICALL Java_com_github_dokandev_dokanjava_Dokan_removeMountPoint
  (JNIEnv *env, jclass, jstring jMountPoint)
{
		int len = env->GetStringLength(jMountPoint);
		const jchar* chars = env->GetStringChars(jMountPoint, NULL);
		wchar_t* wsz = new wchar_t[len+1];
		memcpy(wsz, chars, len*2);
	    wsz[len] = 0;
		BOOL result = DokanRemoveMountPoint(wsz);
		env->ReleaseStringChars(jMountPoint, chars);
		env->DeleteGlobalRef(gOperations);
		gOperations = NULL;
	return result;
}

/*
 * Class:     com_github_dokandev_dokanjava_Dokan
 * Method:    isNameInExpression
 * Signature: (Ljava/lang/String;Ljava/lang/String;Z)Z
 */
JNIEXPORT jboolean JNICALL Java_com_github_dokandev_dokanjava_Dokan_isNameInExpression
  (JNIEnv *env, jclass, jstring jexpression, jstring jname, jboolean jignoreCase)
{
	try {
		const jchar* pExp = env->GetStringChars(jexpression, NULL);
		if (pExp == NULL) 
			throw "Failed at GetStringChars for expression";

		const jchar* pName = env->GetStringChars(jname, NULL);
		if (pName == NULL)
			throw "Failed at GetStringChars for name";

		jboolean result = DokanIsNameInExpression((LPCWSTR)pExp, (LPCWSTR)pName, jignoreCase);

		env->ReleaseStringChars(jexpression, pExp);
		env->ReleaseStringChars(jname, pName);

		return result;
	} catch(const char* msg) {
		env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), msg);
		return FALSE;
	}
}

/*
 * Class:     com_github_dokandev_dokanjava_Dokan
 * Method:    getVersion
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_github_dokandev_dokanjava_Dokan_version
  (JNIEnv *env, jclass)
{
	return DokanVersion();
}

JNIEXPORT jint JNICALL Java_com_github_dokandev_dokanjava_Dokan_driverVersion
  (JNIEnv *env, jclass)
{
	return DokanDriverVersion();
}

JNIEnv *get_env()
{
   JNIEnv *env;
   JavaVMAttachArgs args;

   args.version = JNI_VERSION_1_4;
   args.name = NULL;
   args.group = NULL;

   // a GCJ 4.0 bug workarround (supplied by Alexander Bostrm <abo@stacken.kth.se>)
   //if ((*vm)->GetEnv(vm, (void**)&env, args.version) == JNI_OK)
   //   return env;

   //TRACE("will attach thread");
   // attach thread as daemon thread so that JVM can exit after unmounting the fuseFS
   (*jvm).AttachCurrentThreadAsDaemon((void**)&env, (void*)&args);

   //(*vm)->AttachCurrentThread(vm, (void**)&env, (void*)&args);
   //printf("did attach thread to env: %p \n", env);
   return env;
}

void release_env(JNIEnv *env)
{
  
}
