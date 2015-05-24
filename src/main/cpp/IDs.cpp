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
#include "IDs.h"

JavaVM* jvm = NULL;
jobject gOperations = NULL;

jclass dokanOptionsClass = NULL;
jclass dokanDokanOperationsClass = NULL;
jclass byHandleFileInfoClass = NULL;
jclass dokanDiskFreeSpaceClass = NULL;
jclass dokanExceptionClass = NULL;
//jclass dokanFileInfoClass = NULL;
jclass dokanVolumeInfoClass = NULL;
jclass win32FindDataClass = NULL;

jfieldID versionID = NULL;
jfieldID threadCountID = NULL;
jfieldID optionsID = NULL;
jfieldID globalContextID = NULL;
jfieldID mountPointID = NULL;
//jfieldID useStdErrID = NULL;
//jfieldID useAltStreamID = NULL;
jfieldID errorCodeID = NULL;
jfieldID freeBytesAvailableID = NULL;
jfieldID totalNumberOfBytesID = NULL;
jfieldID totalNumberOfFreeBytesID = NULL;
jfieldID volumeNameID = NULL;
jfieldID volumeSerialNumberID = NULL;
jfieldID maximumComponentLengthID = NULL;
jfieldID fileSystemFlagsID = NULL;
jfieldID fileSystemNameID = NULL;

jfieldID Win32FindData_fileAttributesID = NULL;
jfieldID Win32FindData_creationTimeID = NULL;
jfieldID Win32FindData_lastAccessTimeID = NULL;
jfieldID Win32FindData_lastWriteTimeID = NULL;
jfieldID Win32FindData_fileSizeID = NULL;
jfieldID Win32FindData_reserved0ID = NULL;
jfieldID Win32FindData_reserved1ID = NULL;
jfieldID Win32FindData_fileNameID = NULL;
jfieldID Win32FindData_alternateFileNameID = NULL;

jfieldID ByHandleFileInfo_fileAttributesID = NULL;
jfieldID ByHandleFileInfo_creationTimeID = NULL;
jfieldID ByHandleFileInfo_lastAccessTimeID = NULL;
jfieldID ByHandleFileInfo_lastWriteTimeID = NULL;
jfieldID ByHandleFileInfo_volumeSerialNumberID = NULL;
jfieldID ByHandleFileInfo_fileSizeID = NULL;
jfieldID ByHandleFileInfo_numberOfLinksID = NULL;
jfieldID ByHandleFileInfo_fileIndexID = NULL;

jmethodID byHandleFileInfoConstID = NULL;
jmethodID dokanDiskFreeSpaceConstID = NULL;
jmethodID dokanExceptionConstID = NULL;
//jmethodID dokanFileInfoConstID = NULL;
jmethodID dokanVolumeInfoConstID = NULL;
jmethodID win32FindDataConstID = NULL;

jmethodID onCreateFileID = NULL;
jmethodID onOpenDirectoryID = NULL;
jmethodID onCreateDirectoryID = NULL;
jmethodID onCleanupID = NULL;
jmethodID onCloseFileID = NULL;
jmethodID onReadFileID = NULL;
jmethodID onWriteFileID = NULL;
jmethodID onFlushFileBuffersID = NULL;
jmethodID onGetFileInformationID = NULL;
jmethodID onFindFilesID = NULL;
jmethodID onFindFilesWithPatternID = NULL;
jmethodID onSetFileAttributesID = NULL;
jmethodID onSetFileTimeID = NULL;
jmethodID onDeleteFileID = NULL;
jmethodID onDeleteDirectoryID = NULL;
jmethodID onMoveFileID = NULL;
jmethodID onSetEndOfFileID = NULL;
jmethodID onLockFileID = NULL;
jmethodID onUnlockFileID = NULL;
jmethodID onGetDiskFreeSpaceID = NULL;
jmethodID onGetVolumeInformationID = NULL;
jmethodID onUnmountID = NULL;
	
BOOL APIENTRY DllMain(HMODULE hModule, DWORD ul_reason_for_call, LPVOID lpReserved)
{
    return TRUE;
}

void InitMethodIDs(JNIEnv *env) throw(...)
{
	LOG(L"[InitMethodIDs]\n");

	// DokanDiskFreeSpace
	dokanDiskFreeSpaceClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/DokanDiskFreeSpace"));
	if(dokanDiskFreeSpaceClass == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.DokanDiskFreeSpace class";
	
	// DokanDiskFreeSpace.<init>
	dokanDiskFreeSpaceConstID = env->GetMethodID(dokanDiskFreeSpaceClass, "<init>", "()V");
	if(dokanDiskFreeSpaceConstID == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.DokanDiskFreeSpace constructor method";

	// DokanDiskFreeSpace.totalNumberOfBytes
	totalNumberOfBytesID = env->GetFieldID(dokanDiskFreeSpaceClass, "totalNumberOfBytes", "J");
	if(totalNumberOfBytesID == NULL)
		throw "Cannot find field totalNumberOfBytes at DokanDiskFreeSpace class";

	// DokanDiskFreeSpace.freeBytesAvailable
	freeBytesAvailableID = env->GetFieldID(dokanDiskFreeSpaceClass, "freeBytesAvailable", "J");
	if(freeBytesAvailableID == NULL)
		throw "Cannot find field freeBytesAvailable at DokanDiskFreeSpace class";

	// DokanDiskFreeSpace.totalNumberOfFreeBytes
	totalNumberOfFreeBytesID = env->GetFieldID(dokanDiskFreeSpaceClass, "totalNumberOfFreeBytes", "J");
	if(totalNumberOfFreeBytesID == NULL)
		throw "Cannot find field totalNumberOfFreeBytes at DokanDiskFreeSpace class";

	// dokanException class
	dokanExceptionClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/DokanException"));
	if(dokanExceptionClass == NULL) 
		throw "Cannot find com.github.dokandev.dokanjava.DokanException class";

	// dokanException.errorCode
	errorCodeID = env->GetFieldID(dokanExceptionClass, "errorCode", "I");
	if(errorCodeID == NULL)
		throw "Cannot find field errorCode at dokanException class";

	LOG(L"[InitMethodIDs] 1\n");

	// DokanFileInfo
	/*
	dokanFileInfoClass = env->FindClass("com/github/dokandev/dokanjava/DokanFileInfo");
	if(dokanFileInfoClass == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.DokanFileInfo class";
	*/
	// DokanFileInfo.<init>
	/*
	dokanFileInfoConstID = env->GetMethodID(dokanFileInfoClass, "<init>", "(JIZ)V");
	if(dokanFileInfoConstID == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.DokanFileInfo constructor method";
	*/
	LOG(L"[InitMethodIDs] 1-1\n");

	// DokanVolumeInformation
	dokanVolumeInfoClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/DokanVolumeInformation"));
	if(dokanVolumeInfoClass == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.DokanVolumeInformation class";
	
	// DokanVolumeInformation.<init>
	dokanVolumeInfoConstID = env->GetMethodID(dokanVolumeInfoClass, "<init>", "()V");
	if(dokanVolumeInfoConstID == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.DokanVolumeInformation constructor method";

	// DokanVolumeInformation.volumeName
	volumeNameID = env->GetFieldID(dokanVolumeInfoClass, "volumeName", "Ljava/lang/String;");
	if(volumeNameID == NULL)
		throw "Cannot find field volumeName at DokanVolumeInformation class";

	// DokanVolumeInformation.volumeSerialNumber
	volumeSerialNumberID = env->GetFieldID(dokanVolumeInfoClass, "volumeSerialNumber", "I");
	if(volumeSerialNumberID == NULL)
		throw "Cannot find field volumeSerialNumber at DokanVolumeInformation class";

	// DokanVolumeInformation.maximumComponentLength
	maximumComponentLengthID = env->GetFieldID(dokanVolumeInfoClass, "maximumComponentLength", "I");
	if(maximumComponentLengthID == NULL)
		throw "Cannot find field maximumComponentLength at DokanVolumeInformation class";

	// DokanVolumeInformation.fileSystemFlags
	fileSystemFlagsID = env->GetFieldID(dokanVolumeInfoClass, "fileSystemFlags", "I");
	if(fileSystemFlagsID == NULL)
		throw "Cannot find field fileSystemFlags at DokanVolumeInformation class";

	// DokanVolumeInformation.fileSystemName
	fileSystemNameID = env->GetFieldID(dokanVolumeInfoClass, "fileSystemName", "Ljava/lang/String;");
	if(fileSystemNameID == NULL)
		throw "Cannot find field fileSystemName at DokanVolumeInformation class";

	LOG(L"[InitMethodIDs] 1-2\n");

	// Win32FindData
	win32FindDataClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/Win32FindData"));
	if(win32FindDataClass == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.Win32FindData class";
	
	// Win32FindData.<init>
	win32FindDataConstID = env->GetMethodID(win32FindDataClass, "<init>", 
		"(IJJJJIILjava/lang/String;Ljava/lang/String;)V");
	if(win32FindDataConstID == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.Win32FindData constructor method";

	// Win32FindData.fileAttributes
	Win32FindData_fileAttributesID = env->GetFieldID(win32FindDataClass, "fileAttributes", "I");
	if(Win32FindData_fileAttributesID == NULL)
		throw "Cannot find field fileAttributes at Win32FindData class";

	// Win32FindData.creationTime
	Win32FindData_creationTimeID = env->GetFieldID(win32FindDataClass, "creationTime", "J");
	if(Win32FindData_creationTimeID == NULL)
		throw "Cannot find field creationTime at Win32FindData class";

	// Win32FindData.lastAccessTime
	Win32FindData_lastAccessTimeID = env->GetFieldID(win32FindDataClass, "lastAccessTime", "J");
	if(Win32FindData_lastAccessTimeID == NULL)
		throw "Cannot find field lastAccessTime at Win32FindData class";

	// Win32FindData.lastWriteTime
	Win32FindData_lastWriteTimeID = env->GetFieldID(win32FindDataClass, "lastWriteTime", "J");
	if(Win32FindData_lastWriteTimeID == NULL)
		throw "Cannot find field lastWriteTime at Win32FindData class";

	// Win32FindData.fileSize
	Win32FindData_fileSizeID = env->GetFieldID(win32FindDataClass, "fileSize", "J");
	if(Win32FindData_fileSizeID == NULL)
		throw "Cannot find field fileSize at Win32FindData class";

	// Win32FindData.reserved0
	Win32FindData_reserved0ID = env->GetFieldID(win32FindDataClass, "reserved0", "I");
	if(Win32FindData_reserved0ID == NULL)
		throw "Cannot find field reserved0 at Win32FindData class";

	// Win32FindData.reserved1
	Win32FindData_reserved1ID = env->GetFieldID(win32FindDataClass, "reserved1", "I");
	if(Win32FindData_reserved1ID == NULL)
		throw "Cannot find field reserved1 at Win32FindData class";

	// Win32FindData.fileName
	Win32FindData_fileNameID = env->GetFieldID(win32FindDataClass, "fileName", "Ljava/lang/String;");
	if(Win32FindData_fileNameID == NULL)
		throw "Cannot find field fileName at Win32FindData class";

	// Win32FindData.alternateFileName
	Win32FindData_alternateFileNameID = env->GetFieldID(win32FindDataClass, "alternateFileName", "Ljava/lang/String;");
	if(Win32FindData_alternateFileNameID == NULL)
		throw "Cannot find field alternateFileName at Win32FindData class";

	LOG(L"[InitMethodIDs] 2\n");
	//------------------------------------------------------------------

	// ByHandleFileInformation
	byHandleFileInfoClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/ByHandleFileInformation"));
	if(byHandleFileInfoClass == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.ByHandleFileInformation class";
	
	// ByHandleFileInformation.<init>
	byHandleFileInfoConstID = env->GetMethodID(byHandleFileInfoClass, "<init>", "(IJJJIJIJ)V");
	if(byHandleFileInfoConstID == NULL)
		throw "Cannot find com.github.dokandev.dokanjava.ByHandleFileInformation constructor method";

	// ByHandleFileInfo.fileAttributes
	ByHandleFileInfo_fileAttributesID = env->GetFieldID(byHandleFileInfoClass, "fileAttributes", "I");
	if(ByHandleFileInfo_fileAttributesID == NULL)
		throw "Cannot find field fileAttributes at ByHandleFileInfo class";

	// ByHandleFileInfo.creationTime
	ByHandleFileInfo_creationTimeID = env->GetFieldID(byHandleFileInfoClass, "creationTime", "J");
	if(ByHandleFileInfo_creationTimeID == NULL)
		throw "Cannot find field creationTime at ByHandleFileInfo class";

	// ByHandleFileInfo.lastAccessTime
	ByHandleFileInfo_lastAccessTimeID = env->GetFieldID(byHandleFileInfoClass, "lastAccessTime", "J");
	if(ByHandleFileInfo_lastAccessTimeID == NULL)
		throw "Cannot find field lastAccessTime at ByHandleFileInfo class";

	// ByHandleFileInfo.lastWriteTime
	ByHandleFileInfo_lastWriteTimeID = env->GetFieldID(byHandleFileInfoClass, "lastWriteTime", "J");
	if(ByHandleFileInfo_lastWriteTimeID == NULL)
		throw "Cannot find field lastWriteTime at ByHandleFileInfo class";

	// ByHandleFileInfo.volumeSerialNumber
	ByHandleFileInfo_volumeSerialNumberID = env->GetFieldID(byHandleFileInfoClass, "volumeSerialNumber", "I");
	if(ByHandleFileInfo_volumeSerialNumberID == NULL)
		throw "Cannot find field volumeSerialNumber at ByHandleFileInfo class";

	// ByHandleFileInfo.fileSize
	ByHandleFileInfo_fileSizeID = env->GetFieldID(byHandleFileInfoClass, "fileSize", "J");
	if(ByHandleFileInfo_fileSizeID == NULL)
		throw "Cannot find field fileSize at ByHandleFileInfo class";

	// ByHandleFileInfo.numberOfLinks
	ByHandleFileInfo_numberOfLinksID = env->GetFieldID(byHandleFileInfoClass, "numberOfLinks", "I");
	if(ByHandleFileInfo_numberOfLinksID == NULL)
		throw "Cannot find field numberOfLinks at ByHandleFileInfo class";

	// ByHandleFileInfo.fileIndex
	ByHandleFileInfo_fileIndexID = env->GetFieldID(byHandleFileInfoClass, "fileIndex", "J");
	if(ByHandleFileInfo_fileIndexID == NULL)
		throw "Cannot find field fileIndex at ByHandleFileInfo class";

	LOG(L"[InitMethodIDs] 2\n");

	//------------------------------------------------------------------

	// com.github.dokandev.dokanjava.DokanOptions class
	dokanOptionsClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/DokanOptions"));
	if(dokanOptionsClass == NULL) 
		throw "Cannot find com.github.dokandev.dokanjava.DokanOptions class";

//	// DokanOptions.driveLetter
//	driveLetterID = env->GetFieldID(dokanOptionsClass, "driveLetter", "C");
//	if(driveLetterID == NULL)
//		throw "Cannot find field driverLetter at DokanOperations class";

	// DokanOptions.mountPoint
	mountPointID = env->GetFieldID(dokanOptionsClass, "mountPoint", "Ljava/lang/String;");
	if(mountPointID == NULL)
		throw "Cannot find field mountPoint at DokanOperations class";

	// DokanOptions.version
	versionID = env->GetFieldID(dokanOptionsClass, "version", "S");
	if (versionID == NULL)
		throw "Cannot find field threadCount at DokanOperations class";

	// DokanOptions.threadCount
	threadCountID = env->GetFieldID(dokanOptionsClass, "threadCount", "S");
	if(threadCountID == NULL)
		throw "Cannot find field threadCount at DokanOperations class";

	// DokanOptions.options
	optionsID = env->GetFieldID(dokanOptionsClass, "options", "I");
	if(optionsID == NULL)
		throw "Cannot find field options at DokanOperations class";

	// DokanOptions.globalContext
	globalContextID = env->GetFieldID(dokanOptionsClass, "globalContext", "J");
	if (globalContextID == NULL)
		throw "Cannot find field options at DokanOperations class";

	// DokanOptions.useStdErr
	/*
	useStdErrID = env->GetFieldID(dokanOptionsClass, "useStdErr", "B");
	if(useStdErrID == NULL)
		throw "Cannot find field useStdErr at DokanOperations class";

		*/
	// DokanOptions.useAltStream
	/*
	useAltStreamID = env->GetFieldID(dokanOptionsClass, "useAltStream", "B");
	if(useAltStreamID == NULL)
		throw "Cannot find field useAltStream at DokanOperations class";
		*/
	//------------------------------------------------------------------
	LOG(L"[InitMethodIDs] 3\n");

	// com.github.dokandev.dokanjava.DokanOperations class
	dokanDokanOperationsClass = (jclass) env->NewGlobalRef(env->FindClass("com/github/dokandev/dokanjava/DokanOperations"));
	if(dokanDokanOperationsClass == NULL) 
		throw "Cannot find com.github.dokandev.dokanjava.DokanOperations class";

	// DokanOperations.onCreateFile
	onCreateFileID = env->GetMethodID(dokanDokanOperationsClass, "onCreateFile", 
		"(Ljava/lang/String;IIIILcom/github/dokandev/dokanjava/DokanFileInfo;)J");
	if(onCreateFileID == NULL)
		throw "Cannot find DokanOperations.onCreateFile method";

	// DokanOperations.onOpenDirectory
	onOpenDirectoryID = env->GetMethodID(dokanDokanOperationsClass, "onOpenDirectory", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)J");
	if(onOpenDirectoryID == NULL)
		throw "Cannot find DokanOperations.onOpenDirectory method";

	// DokanOperations.onCreateDirectory
	onCreateDirectoryID = env->GetMethodID(dokanDokanOperationsClass, "onCreateDirectory", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onCreateDirectoryID == NULL)
		throw "Cannot find DokanOperations.onCreateDirectory method";

	// DokanOperations.onCleanup
	onCleanupID = env->GetMethodID(dokanDokanOperationsClass, "onCleanup", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onCleanupID == NULL)
		throw "Cannot find DokanOperations.onCleanup method";

	LOG(L"[InitMethodIDs] 3-1\n");

	// DokanOperations.onCloseFile
	onCloseFileID = env->GetMethodID(dokanDokanOperationsClass, "onCloseFile", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onCloseFileID == NULL)
		throw "Cannot find DokanOperations.onCloseFile method";

	// DokanOperations.onReadFile
	onReadFileID = env->GetMethodID(dokanDokanOperationsClass, "onReadFile", 
		"(Ljava/lang/String;Ljava/nio/ByteBuffer;JLcom/github/dokandev/dokanjava/DokanFileInfo;)I");
	if(onReadFileID == NULL)
		throw "Cannot find DokanOperations.onReadFile method";

	// DokanOperations.onWriteFile
	onWriteFileID = env->GetMethodID(dokanDokanOperationsClass, "onWriteFile", 
		"(Ljava/lang/String;Ljava/nio/ByteBuffer;JLcom/github/dokandev/dokanjava/DokanFileInfo;)I");
	if(onWriteFileID == NULL)
		throw "Cannot find DokanOperations.onWriteFile method";

	// DokanOperations.onFlushFileBuffers
	onFlushFileBuffersID = env->GetMethodID(dokanDokanOperationsClass, "onFlushFileBuffers", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onFlushFileBuffersID == NULL)
		throw "Cannot find DokanOperations.onFlushFileBuffers method";

	// DokanOperations.onGetFileInformation
	onGetFileInformationID = env->GetMethodID(dokanDokanOperationsClass, "onGetFileInformation", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)Lcom/github/dokandev/dokanjava/ByHandleFileInformation;");
	if(onGetFileInformationID == NULL)
		throw "Cannot find DokanOperations.onGetFileInformation method";

	LOG(L"[InitMethodIDs] 3-2\n");

	// DokanOperations.onFindFiles
	onFindFilesID = env->GetMethodID(dokanDokanOperationsClass, "onFindFiles", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)[Lcom/github/dokandev/dokanjava/Win32FindData;");
	if(onFindFilesID == NULL)
		throw "Cannot find DokanOperations.onFindFiles method";

	// DokanOperations.onFindFilesWithPattern
	onFindFilesWithPatternID = env->GetMethodID(dokanDokanOperationsClass, "onFindFilesWithPattern", 
		"(Ljava/lang/String;Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)[Lcom/github/dokandev/dokanjava/Win32FindData;");
	if(onFindFilesWithPatternID == NULL)
		throw "Cannot find DokanOperations.onFindFilesWithPattern method";

	LOG(L"[InitMethodIDs] 3-2-1\n");


	// DokanOperations.onSetFileAttributes
	onSetFileAttributesID = env->GetMethodID(dokanDokanOperationsClass, "onSetFileAttributes", 
		"(Ljava/lang/String;ILcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onSetFileAttributesID == NULL)
		throw "Cannot find DokanOperations.onSetFileAttributes method";

	// DokanOperations.onSetFileTime
	onSetFileTimeID = env->GetMethodID(dokanDokanOperationsClass, "onSetFileTime", 
		"(Ljava/lang/String;JJJLcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onSetFileTimeID == NULL)
		throw "Cannot find DokanOperations.onSetFileTime method";

	// DokanOperations.onDeleteFile
	onDeleteFileID = env->GetMethodID(dokanDokanOperationsClass, "onDeleteFile", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onDeleteFileID == NULL)
		throw "Cannot find DokanOperations.onDeleteFile method";

	LOG(L"[InitMethodIDs] 3-3\n");

	// DokanOperations.onDeleteDirectory
	onDeleteDirectoryID = env->GetMethodID(dokanDokanOperationsClass, "onDeleteDirectory", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onDeleteDirectoryID == NULL)
		throw "Cannot find DokanOperations.onDeleteDirectory method";

	// DokanOperations.onMoveFile
	onMoveFileID = env->GetMethodID(dokanDokanOperationsClass, "onMoveFile", 
		"(Ljava/lang/String;Ljava/lang/String;ZLcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onMoveFileID == NULL)
		throw "Cannot find DokanOperations.onMoveFile method";

	// DokanOperations.onSetEndOfFile
	onSetEndOfFileID = env->GetMethodID(dokanDokanOperationsClass, "onSetEndOfFile", 
		"(Ljava/lang/String;JLcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onSetEndOfFileID == NULL)
		throw "Cannot find DokanOperations.onSetEndOfFile method";

	// DokanOperations.onLockFile
	onLockFileID = env->GetMethodID(dokanDokanOperationsClass, "onLockFile", 
		"(Ljava/lang/String;JJLcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onLockFileID == NULL)
		throw "Cannot find DokanOperations.onLockFile method";

	// DokanOperations.onUnlockFile
	onUnlockFileID = env->GetMethodID(dokanDokanOperationsClass, "onUnlockFile", 
		"(Ljava/lang/String;JJLcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onUnlockFileID == NULL)
		throw "Cannot find DokanOperations.onUnlockFile method";

	LOG(L"[InitMethodIDs] 3-4s\n");

	// DokanOperations.onGetDiskFreeSpace
	onGetDiskFreeSpaceID = env->GetMethodID(dokanDokanOperationsClass, "onGetDiskFreeSpace", 
		"(Lcom/github/dokandev/dokanjava/DokanFileInfo;)Lcom/github/dokandev/dokanjava/DokanDiskFreeSpace;");
	if(onGetDiskFreeSpaceID == NULL)
		throw "Cannot find DokanOperations.onGetDiskFreeSpace method";

	// DokanOperations.onGetVolumeInformation
	onGetVolumeInformationID = env->GetMethodID(dokanDokanOperationsClass, "onGetVolumeInformation", 
		"(Ljava/lang/String;Lcom/github/dokandev/dokanjava/DokanFileInfo;)Lcom/github/dokandev/dokanjava/DokanVolumeInformation;");
	if(onGetVolumeInformationID == NULL)
		throw "Cannot find DokanOperations.onGetVolumeInformation method";

	// DokanOperations.onUnmount
	onUnmountID = env->GetMethodID(dokanDokanOperationsClass, "onUnmount", 
		"(Lcom/github/dokandev/dokanjava/DokanFileInfo;)V");
	if(onUnmountID == NULL)
		throw "Cannot find DokanOperations.onUnmount method";

	LOG(L"[InitMethodIDs] Done\n");
}
