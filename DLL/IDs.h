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

extern JavaVM* jvm;
extern jobject gOperations;

extern jclass dokanOptionsClass;
extern jclass dokanDokanOperationsClass;
extern jclass byHandleFileInfoClass;
extern jclass dokanDiskFreeSpaceClass;
extern jclass dokanOperationExceptionClass;
//extern jclass dokanFileInfoClass;
extern jclass dokanVolumeInfoClass;
extern jclass win32FindDataClass;

//extern jfieldID driveLetterID;
extern jfieldID mountPointID;
extern jfieldID threadCountID;
extern jfieldID optionsModeID;
//extern jfieldID useStdErrID;
//extern jfieldID useAltStreamID;
extern jfieldID errorCodeID;
extern jfieldID freeBytesAvailableID;
extern jfieldID totalNumberOfBytesID;
extern jfieldID totalNumberOfFreeBytesID;
extern jfieldID volumeNameID;
extern jfieldID volumeSerialNumberID;
extern jfieldID maximumComponentLengthID;
extern jfieldID fileSystemFlagsID;
extern jfieldID fileSystemNameID;

extern jfieldID Win32FindData_fileAttributesID;
extern jfieldID Win32FindData_creationTimeID;
extern jfieldID Win32FindData_lastAccessTimeID;
extern jfieldID Win32FindData_lastWriteTimeID;
extern jfieldID Win32FindData_fileSizeID;
extern jfieldID Win32FindData_reserved0ID;
extern jfieldID Win32FindData_reserved1ID;
extern jfieldID Win32FindData_fileNameID;
extern jfieldID Win32FindData_alternateFileNameID;

extern jfieldID ByHandleFileInfo_fileAttributesID;
extern jfieldID ByHandleFileInfo_creationTimeID;
extern jfieldID ByHandleFileInfo_lastAccessTimeID;
extern jfieldID ByHandleFileInfo_lastWriteTimeID;
extern jfieldID ByHandleFileInfo_volumeSerialNumberID;
extern jfieldID ByHandleFileInfo_fileSizeID;
extern jfieldID ByHandleFileInfo_numberOfLinksID;
extern jfieldID ByHandleFileInfo_fileIndexID;

extern jmethodID byHandleFileInfoConstID;
extern jmethodID dokanDiskFreeSpaceConstID;
extern jmethodID dokanOperationExceptionConstID;
//extern jmethodID dokanFileInfoConstID;
extern jmethodID dokanVolumeInfoConstID;
extern jmethodID win32FindDataConstID;

extern jmethodID onCreateFileID;
extern jmethodID onOpenDirectoryID;
extern jmethodID onCreateDirectoryID;
extern jmethodID onCleanupID;
extern jmethodID onCloseFileID;
extern jmethodID onReadFileID;
extern jmethodID onWriteFileID;
extern jmethodID onFlushFileBuffersID;
extern jmethodID onGetFileInformationID;
extern jmethodID onFindFilesID;
extern jmethodID onFindFilesWithPatternID;
extern jmethodID onSetFileAttributesID;
extern jmethodID onSetFileTimeID;
extern jmethodID onDeleteFileID;
extern jmethodID onDeleteDirectoryID;
extern jmethodID onMoveFileID;
extern jmethodID onSetEndOfFileID;
extern jmethodID onLockFileID;
extern jmethodID onUnlockFileID;
extern jmethodID onGetDiskFreeSpaceID;
extern jmethodID onGetVolumeInformationID;
extern jmethodID onUnmountID;
