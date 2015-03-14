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

jstring ToJavaString(JNIEnv* env, PCWSTR str);
void CopyStringField(JNIEnv* env, jobject jobj, jfieldID jfid, LPWSTR Buffer, DWORD Size);
int GetOperationResult(JNIEnv* env);
jobject NewDokanDiskFreeSpace(JNIEnv* env);
jobject NewDokanVolumeInfo(JNIEnv* env);
jobject ToDokanFileInfoJavaObject(JNIEnv* env, PDOKAN_FILE_INFO DokanFileInfo);
jobject ToByHandleFileInfoJavaObject(JNIEnv* env, LPBY_HANDLE_FILE_INFORMATION ByHandleFileInfo);
jobject ToVolumeInfoJavaObject(JNIEnv* env, int volumeSerialNumber, int maximumComponentLength,
							   int fileSystemFlags, LPCWSTR fileSystemName);
//jobject ToWin32FindDataJavaObject(JNIEnv* env, PWIN32_FIND_DATA win32FindData);
void ToWin32FindData(JNIEnv* env, jobject obj, PWIN32_FIND_DATA win32FindData);
void ToByHandleFileInfo(JNIEnv* env, jobject obj, LPBY_HANDLE_FILE_INFORMATION fileInfo);
