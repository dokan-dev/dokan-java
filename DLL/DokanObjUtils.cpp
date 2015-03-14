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
#include "net_decasdev_dokan_Dokan.h"
#include "dokan.h"
#include "JDokanDLL.h"
#include "Utils.h"
#include "IDs.h"

jstring ToJavaString(JNIEnv* env, PCWSTR str) throw(...)
{
	if (str == NULL)
		throw "Try to NewString for NULL";
	jstring jstr = env->NewString((const jchar*)str, (jsize)wcslen(str)); 
	if (jstr == NULL)
		throw "Failed at NewString";
	return jstr;
}

void CopyStringField(JNIEnv* env, jobject jobj, jfieldID jfid, LPWSTR Buffer, DWORD Size)
{
	if(Buffer == NULL || Size <= 0) return;
	jstring jstr = (jstring)env->GetObjectField(jobj, jfid);
	if (jstr != NULL) {
		jsize copyCount = MIN(env->GetStringLength(jstr), (jsize)(Size - 1));
		env->GetStringRegion(jstr, 0, copyCount, (jchar*)Buffer);
		Buffer[Size - 1] = '\0';
	}
}

int GetOperationResult(JNIEnv* env)
{
	if (!env->ExceptionCheck()) 
		return 0;

	int result = ERROR_GEN_FAILURE;
	jthrowable t = env->ExceptionOccurred();
	if (env->IsInstanceOf(t, dokanOperationExceptionClass)) {
		result = -(env->GetIntField(t, errorCodeID));
		LOG(L"[GetOperationResult] DokanOperationException. ErrorCode = %d\n", -result);
	} else {
		// TODO More detailed information !!
		LOG(L"[GetOperationResult] Exception occurred !!!\n");
	}
	env->ExceptionClear();		

	return result;
}

jobject NewDokanDiskFreeSpace(JNIEnv* env) throw(...)
{
	jobject jobj = env->NewObject(dokanDiskFreeSpaceClass,
		dokanDiskFreeSpaceConstID);
	if (jobj == NULL)
		throw "Failed at NewObject for DokanDiskFreeSpace";
	return jobj;
}

jobject NewDokanVolumeInfo(JNIEnv* env) throw(...)
{
	jobject jobj = env->NewObject(dokanVolumeInfoClass,
		dokanVolumeInfoConstID);
	if (jobj == NULL)
		throw "Failed at NewObject for DokanVolumeInfo";
	return jobj;
}

jobject ToDokanFileInfoJavaObject(JNIEnv* env, PDOKAN_FILE_INFO DokanFileInfo) throw(...)
{
	if (DokanFileInfo == NULL)
		return NULL;

	jclass clz = env->FindClass("net/decasdev/dokan/DokanFileInfo");
	if (clz == NULL) 
		return NULL;
	
	jmethodID mid = env->GetMethodID(clz, "<init>", "(JIZ)V");
	if (mid == NULL) 
		return NULL;
	
	jobject jdokanFileInfo = env->NewObject(
		clz, mid,
		DokanFileInfo->Context,
		DokanFileInfo->ProcessId,
		DokanFileInfo->IsDirectory);
	if (jdokanFileInfo == NULL)
		throw "Failed at NewObject for DokanFileInfo";
	
	return jdokanFileInfo;
}

jobject ToByHandleFileInfoJavaObject(JNIEnv* env, LPBY_HANDLE_FILE_INFORMATION ByHandleFileInfo) throw(...)
{
	if (ByHandleFileInfo == NULL)
		return NULL;
	jobject jByHandleFileInfo = env->NewObject(byHandleFileInfoClass,
		byHandleFileInfoConstID,
		ByHandleFileInfo->dwFileAttributes,
		FileTime2LongLong(&ByHandleFileInfo->ftCreationTime),
		FileTime2LongLong(&ByHandleFileInfo->ftLastAccessTime),
		FileTime2LongLong(&ByHandleFileInfo->ftLastWriteTime),
		ByHandleFileInfo->dwVolumeSerialNumber,
		ToLongLong(ByHandleFileInfo->nFileSizeHigh, ByHandleFileInfo->nFileSizeLow),
		ByHandleFileInfo->nNumberOfLinks,
		ToLongLong(ByHandleFileInfo->nFileIndexHigh, ByHandleFileInfo->nFileIndexLow));
	if (jByHandleFileInfo == NULL)
		throw "Failed at NewObject for DokanFileInfo";
	return jByHandleFileInfo;
}

jobject ToVolumeInfoJavaObject(JNIEnv* env, int volumeSerialNumber, int maximumComponentLength,
							   int fileSystemFlags, LPCWSTR fileSystemName)
{
	jobject jobj = env->NewObject(dokanVolumeInfoClass,
		dokanVolumeInfoConstID,
		volumeSerialNumber,
		maximumComponentLength,
		fileSystemFlags,
		ToJavaString(env, fileSystemName));
	if (jobj == NULL)
		throw "Failed at NewObject for VolumeInfo";
	return jobj;
}
/*
jobject ToWin32FindDataJavaObject(JNIEnv* env, PWIN32_FIND_DATA win32FindData) throw(...)
{
	if (win32FindData == NULL)
		return NULL;
	jobject jobj = env->NewObject(win32FindDataClass,
		win32FindDataConstID,
		win32FindData->dwFileAttributes,
		FileTime2LongLong(&win32FindData->ftCreationTime),
		FileTime2LongLong(&win32FindData->ftLastAccessTime),
		FileTime2LongLong(&win32FindData->ftLastWriteTime),
		ToLongLong(win32FindData->nFileSizeHigh, win32FindData->nFileSizeLow),
		win32FindData->dwReserved0,
		win32FindData->dwReserved1,
		ToJavaString(env, win32FindData->cFileName),
		ToJavaString(env, win32FindData->cAlternateFileName));
	if (jobj == NULL)
		throw "Failed at NewObject for Win32FindData";
	return jobj;
}
*/
void ToWin32FindData(JNIEnv* env, jobject obj, PWIN32_FIND_DATAW win32FindData)
{
	if(win32FindData == NULL)
		return;
	ZeroMemory(win32FindData, sizeof(WIN32_FIND_DATAW));
	if (obj == NULL)
		return;

	win32FindData->dwFileAttributes = env->GetIntField(obj, Win32FindData_fileAttributesID);
	win32FindData->ftCreationTime = LongLong2FileTime(env->GetLongField(obj, Win32FindData_creationTimeID));
	win32FindData->ftLastAccessTime = LongLong2FileTime(env->GetLongField(obj, Win32FindData_lastAccessTimeID));
	win32FindData->ftLastWriteTime = LongLong2FileTime(env->GetLongField(obj, Win32FindData_lastWriteTimeID));
	LONGLONG fileSize = env->GetLongField(obj, Win32FindData_fileSizeID);
	win32FindData->nFileSizeHigh = LongLongToHigh(fileSize);
	win32FindData->nFileSizeLow = LongLongToLow(fileSize);
	win32FindData->dwReserved0 = env->GetIntField(obj, Win32FindData_reserved0ID);
	win32FindData->dwReserved1 = env->GetIntField(obj, Win32FindData_reserved1ID);
	CopyStringField(env, obj, Win32FindData_fileNameID, win32FindData->cFileName, MAX_PATH);
	CopyStringField(env, obj, Win32FindData_alternateFileNameID, win32FindData->cAlternateFileName, 14);
}

void ToByHandleFileInfo(JNIEnv* env, jobject obj, LPBY_HANDLE_FILE_INFORMATION fileInfo)
{
	if (fileInfo == NULL)
		return;
	ZeroMemory(fileInfo, sizeof(BY_HANDLE_FILE_INFORMATION));
	if (obj == NULL)
		return;

	fileInfo->dwFileAttributes = env->GetIntField(obj, ByHandleFileInfo_fileAttributesID);
	fileInfo->ftCreationTime = LongLong2FileTime(env->GetLongField(obj, ByHandleFileInfo_creationTimeID));
	fileInfo->ftLastAccessTime = LongLong2FileTime(env->GetLongField(obj, ByHandleFileInfo_lastAccessTimeID));
	fileInfo->ftLastWriteTime = LongLong2FileTime(env->GetLongField(obj, ByHandleFileInfo_lastWriteTimeID));
	fileInfo->dwVolumeSerialNumber = env->GetIntField(obj, ByHandleFileInfo_volumeSerialNumberID);
	LONGLONG fileSize = env->GetLongField(obj, ByHandleFileInfo_fileSizeID);
	fileInfo->nFileSizeHigh = LongLongToHigh(fileSize);
	fileInfo->nFileSizeLow = LongLongToLow(fileSize);
	fileInfo->nNumberOfLinks = env->GetIntField(obj, ByHandleFileInfo_numberOfLinksID);
	LONGLONG fileIndex = env->GetLongField(obj, ByHandleFileInfo_fileIndexID);
	fileInfo->nFileIndexHigh = LongLongToHigh(fileIndex);
	fileInfo->nFileIndexLow = LongLongToLow(fileIndex);
}
