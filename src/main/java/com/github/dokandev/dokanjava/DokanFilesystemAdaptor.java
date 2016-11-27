package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.CreationDisposition;
import com.github.dokandev.dokanjava.util.FileInfo;
import com.github.dokandev.dokanjava.util.FileTime;
import com.github.dokandev.dokanjava.util.StreamInfo;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.regex.Pattern;

public class DokanFilesystemAdaptor {
    static public <TFileHandle extends DokanFileHandle> DOKAN_OPERATIONS toStruct(final DokanFilesystem<TFileHandle> fs) {
        DOKAN_OPERATIONS ops = new DOKAN_OPERATIONS();
        ops.Mounted = new DOKAN_OPERATIONS.MountedDelegate() {
            @Override
            public long callback(DokanFileInfo rawFileInfo) {
                System.out.println("Mounted");
                try {
                    fs.mounted();
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.Unmounted = new DOKAN_OPERATIONS.UnmountedDelegate() {
            @Override
            public long callback(DokanFileInfo rawFileInfo) {
                System.out.println("Unmounted");
                try {
                    fs.unmounted();
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.ZwCreateFile = new DOKAN_OPERATIONS.ZwCreateFileDelegate() {
            @Override
            public long callback(WString rawFileName, IntByReference securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
                long res = -1;
                boolean isDirectory = dokanFileInfo._isDirectory != 0;
                System.out.println("--------------------------------------------");
                try {
                    System.out.println("%%%%%%%%%%%%%% CREATEFILE: rawFileName=" + rawFileName.toString() + " : rawDesiredAccess=" + rawDesiredAccess + " : rawFileAttributes=" + rawFileAttributes + " : rawShareAccess=" + rawShareAccess + " : rawCreateDisposition=" + rawCreateDisposition + " : rawCreateOptions=" + rawCreateOptions + " : isDirectory=" + isDirectory);
                    DokanFilesystem<TFileHandle>.OpenFileResult result = fs.createFile(rawFileName.toString(), securityContext.getValue(), rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, isDirectory);
                    System.out.println("result: " + result);
                    System.out.println("dokanFileInfo: " + dokanFileInfo);
                    dokanFileInfo._context = fs.allocateFileHandle(result.handle);
                    dokanFileInfo.write();
                    //long resultCode = NtStatus.Success;
                    //switch (rawCreateDisposition) {
                    //    case CreationDisposition.CREATE_NEW:
                    //        break;
                    //}
                    //if (rawCreateDisposition == CreationDisposition.CREATE_NEW) {
                    //    res = 0;
                    //} else {
                    if (isDirectory) {
                        res = result.exists ? ErrorCodes.ERROR_SUCCESS : ErrorCodes.ERROR_FILE_NOT_FOUND;
                    } else {
                        if (rawCreateDisposition == CreationDisposition.CREATE_NEW) {
                            //res = result.exists ? ErrorCodes.ERROR_FILE_EXISTS : ErrorCodes.ERROR_FILE_NOT_FOUND;
                            res = result.exists ? ErrorCodes.ObjectNameCollision : ErrorCodes.ERROR_SUCCESS;
                            //res = result.exists ? ErrorCodes.ERROR_ALREADY_EXISTS : ErrorCodes.ERROR_FILE_NOT_FOUND;
                        } else if (rawCreateDisposition == CreationDisposition.TRUNCATE_EXISTING) {
                            //res = result.exists ? ErrorCodes.ERROR_FILE_EXISTS : ErrorCodes.ERROR_FILE_NOT_FOUND;
                            res = result.exists ? ErrorCodes.ObjectNameCollision : ErrorCodes.ERROR_SUCCESS;
                            //res = result.exists ? ErrorCodes.ERROR_ALREADY_EXISTS : ErrorCodes.ERROR_FILE_NOT_FOUND;
                        } else {
                            res = result.exists ? ErrorCodes.ERROR_ALREADY_EXISTS : ErrorCodes.ERROR_FILE_NOT_FOUND;
                        }
                    }
                    //}
                } catch (FileAlreadyExistsException t) {
                    if (rawCreateDisposition == CreationDisposition.CREATE_NEW) {
                        res = ErrorCodes.ObjectNameCollision;
                    }
                    res = ErrorCodes.ERROR_SUCCESS;
                } catch (Throwable t) {
                    res = exceptionToErrorCode(t);
                }
                if (res < 0) {
                    System.out.println("TEST");
                }
                System.out.println(" CREATEFILE -> " + res);
                return res;
            }
        };
        ops.Cleanup = new DOKAN_OPERATIONS.CleanupDelegate() {
            @Override
            public void callback(WString fileName, DokanFileInfo rawFileInfo) {
                System.out.println("Cleanup: " + fileName + " : " + rawFileInfo);
                try {
                    //fs.cleanup(fs.getFileHandle(rawFileInfo));
                    fs.cleanup(fileName.toString());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        };
        ops.CloseFile = new DOKAN_OPERATIONS.CloseFileDelegate() {
            @Override
            public void callback(WString fileName, DokanFileInfo rawFileInfo) {
                System.out.println("CloseFile");
                try {
                    fs.closeFile(fs.getFileHandle(fileName, rawFileInfo));
                } catch (FileNotFoundException e) {
                } catch (Throwable t) {
                    t.printStackTrace();
                } finally {
                    fs.removeFileHandle(rawFileInfo._context);
                }
            }
        };
        ops.GetVolumeInformation = new DOKAN_OPERATIONS.GetVolumeInformationDelegate() {
            @Override
            public long callback(
                    Pointer volumeNameBuffer, int volumeNameSize,
                    IntByReference rawVolumeSerialNumber,
                    IntByReference rawMaximumComponentLength,
                    IntByReference rawFileSystemFlags,
                    Pointer rawFileSystemNameBuffer, int rawFileSystemNameSize,
                    DokanFileInfo rawFileInfo
            ) {
                System.out.println("GetVolumeInformation");
                try {
                    volumeNameBuffer.setWideString(0L, limitStringSize(fs.getVolumeName(), volumeNameSize));
                    rawFileSystemNameBuffer.setWideString(0L, limitStringSize(fs.getFileSystemName(), rawFileSystemNameSize));
                    rawVolumeSerialNumber.setValue(fs.getSerialNumber());
                    rawMaximumComponentLength.setValue(fs.getMaxComponentLength());
                    rawFileSystemFlags.setValue(fs.getFileSystemFeatures());

                    //System.out.println("volumeNameBuffer = [" + volumeNameBuffer + "], volumeNameSize = [" + volumeNameSize + "], rawVolumeSerialNumber = [" + rawVolumeSerialNumber + "], rawMaximumComponentLength = [" + rawMaximumComponentLength + "], rawFileSystemFlags = [" + rawFileSystemFlags + "], rawFileSystemNameBuffer = [" + rawFileSystemNameBuffer + "], rawFileSystemNameSize = [" + rawFileSystemNameSize + "], rawFileInfo = [" + rawFileInfo + "]");
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };

        ops.GetFileInformation = new DOKAN_OPERATIONS.GetFileInformationDelegate() {
            @Override
            public long callback(WString fileName, ByHandleFileInformation handleFileInfo, DokanFileInfo fileInfo) {
                System.out.println("GetFileInformation");
                long result = -1;
                FileInfo info = null;
                try {
                    //handleFileInfo.setInfo(fs.getFileInformation(fs.getFileHandle(fileInfo)));
                    info = fs.getFileInformation(fileName.toString());
                    handleFileInfo.setInfo(info);
                    handleFileInfo.write();
                    result = NtStatus.Success;
                } catch (Throwable t) {
                    result = exceptionToErrorCode(t);
                }
                System.out.println("GetFileInformation: " + fileName.toString() + " -> " + result + " | " + info);
                return result;
            }
        };

        ops.GetDiskFreeSpace = new DOKAN_OPERATIONS.GetDiskFreeSpaceDelegate() {
            @Override
            public long callback(LongByReference rawFreeBytesAvailable, LongByReference rawTotalNumberOfBytes, LongByReference rawTotalNumberOfFreeBytes, DokanFileInfo rawFileInfo) {
                System.out.println("GetDiskFreeSpace");
                rawFreeBytesAvailable.setValue(fs.getFreeBytesAvailable());
                rawTotalNumberOfBytes.setValue(fs.getTotalBytesAvailable());
                rawTotalNumberOfFreeBytes.setValue(fs.getTotalFreeBytesAvailable());
                return 0;
            }
        };
        ops.FindFiles = new DOKAN_OPERATIONS.FindFilesDelegate() {
            @Override
            public long callback(final WString fileName, final DOKAN_OPERATIONS.FillWin32FindData rawFillFindData, final DokanFileInfo rawFileInfo) {
                System.out.println("FindFiles");
                try {
                    fs.findFiles(fs.getFileHandle(fileName, rawFileInfo), new DokanFilesystem.FileEmitter() {
                        @Override
                        public void emit(FileInfo file) {
                            rawFillFindData.callback(file.toWin32FindData(), rawFileInfo);
                        }
                    });

                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.FindFilesWithPattern = new DOKAN_OPERATIONS.FindFilesWithPatternDelegate() {
            @Override
            public long callback(final WString fileName, final WString searchPattern, final DOKAN_OPERATIONS.FillWin32FindData rawFillFindData, final DokanFileInfo rawFileInfo) {
                System.out.println("FindFilesWithPattern");
                try {
                    fs.findFiles(fs.getFileHandle(fileName, rawFileInfo), fnmatchToPattern(searchPattern.toString()), new DokanFilesystem.FileEmitter() {
                        @Override
                        public void emit(FileInfo file) {
                            System.out.println("EMIT: " + file.fileName);
                            rawFillFindData.callback(file.toWin32FindData(), rawFileInfo);
                        }
                    });
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.ReadFile = new DOKAN_OPERATIONS.ReadFileDelegate() {
            @Override
            public long callback(WString fileName, Pointer buffer, int bufferLength, IntByReference readLength, long offset, DokanFileInfo fileInfo) {
                System.out.println("ReadFile");
                try {
                    byte[] data = new byte[bufferLength];
                    int read = fs.readFile(fs.getFileHandle(fileName, fileInfo), offset, data, bufferLength);
                    buffer.write(0, data, 0, read);
                    readLength.setValue(read);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t, ErrorCodes.ERROR_READ_FAULT);
                }
            }
        };
        ops.WriteFile = new DOKAN_OPERATIONS.WriteFileDelegate() {
            @Override
            public long callback(WString fileName, Pointer buffer, int numberOfBytesToWrite, IntByReference numberOfBytesWritten, long offset, DokanFileInfo fileInfo) {
                System.out.println("WriteFile");
                try {
                    byte[] data = new byte[numberOfBytesToWrite];
                    buffer.read(0L, data, 0, numberOfBytesToWrite);
                    int written = fs.writeFile(fs.getFileHandle(fileName, fileInfo), offset, data, numberOfBytesToWrite);
                    numberOfBytesWritten.setValue(written);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t, ErrorCodes.ERROR_WRITE_FAULT);
                }
            }
        };
        ops.FlushFileBuffers = new DOKAN_OPERATIONS.FlushFileBuffersDelegate() {
            @Override
            public long callback(WString fileName, DokanFileInfo fileInfo) {
                System.out.println("FlushFileBuffers");
                try {
                    fs.flushFileBuffers(fs.getFileHandle(fileName, fileInfo));
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t, ErrorCodes.ERROR_WRITE_FAULT);
                }
            }
        };
        ops.SetFileAttributes = new DOKAN_OPERATIONS.SetFileAttributesDelegate() {
            @Override
            public long callback(WString fileName, int attributes, DokanFileInfo fileInfo) {
                System.out.println("SetFileAttributes");
                try {
                    fs.setFileAttributes(fs.getFileHandle(fileName, fileInfo), attributes);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t, ErrorCodes.ERROR_WRITE_FAULT);
                }
            }
        };
        ops.SetFileTime = new DOKAN_OPERATIONS.SetFileTimeDelegate() {
            @Override
            public long callback(WString fileName, FileTime.REF creationTime, FileTime.REF lastAccessTime, FileTime.REF lastWriteTime, DokanFileInfo fileInfo) {
                System.out.println("SetFileTime");
                try {
                    fs.setFileTime(fs.getFileHandle(fileName, fileInfo), creationTime.getDate(), lastAccessTime.getDate(), lastWriteTime.getDate());
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t, ErrorCodes.ERROR_WRITE_FAULT);
                }
            }
        };
        ops.FindStreams = new DOKAN_OPERATIONS.FindStreamsDelegate() {
            @Override
            public long callback(WString fileName, final DOKAN_OPERATIONS.FillWin32FindStreamData fill, final DokanFileInfo fileInfo) {
                System.out.println("FindStreams");
                try {
                    fs.findStreams(fs.getFileHandle(fileName, fileInfo), new DokanFilesystem.StreamEmitter() {
                        @Override
                        public void emit(StreamInfo stream) {
                            fill.callback(stream.toStruct(), fileInfo);
                        }
                    });

                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.GetFileSecurity = new DOKAN_OPERATIONS.GetFileSecurityDelegate() {
            @Override
            public long callback(WString fileName, int rawRequestedInformation, Pointer rawSecurityDescriptor, int rawSecurityDescriptorLength, IntByReference rawSecurityDescriptorLengthNeeded, DokanFileInfo fileInfo) {
                System.out.println("GetFileSecurity");
                try {
                    byte[] out = new byte[rawSecurityDescriptorLength];
                    int expectedLength = fs.getFileSecurity(fs.getFileHandle(fileName, fileInfo), rawRequestedInformation, out);
                    rawSecurityDescriptor.write(0L, out, 0, rawSecurityDescriptorLength);
                    rawSecurityDescriptorLengthNeeded.setValue(expectedLength);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.SetFileSecurity = new DOKAN_OPERATIONS.SetFileSecurityDelegate() {
            @Override
            public long callback(WString fileName, int rawSecurityInformation, Pointer rawSecurityDescriptor, int rawSecurityDescriptorLength, DokanFileInfo fileInfo) {
                System.out.println("SetFileSecurity");
                try {
                    byte[] data = new byte[rawSecurityDescriptorLength];
                    rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
                    fs.setFileSecurity(fs.getFileHandle(fileName, fileInfo), rawSecurityInformation, data);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.DeleteFile = new DOKAN_OPERATIONS.DeleteFileDelegate() {
            @Override
            public long callback(WString fileName, DokanFileInfo rawFileInfo) {
                System.out.println("DeleteFile");
                try {
                    fs.deleteFile(fileName.toString());
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.DeleteDirectory = new DOKAN_OPERATIONS.DeleteDirectoryDelegate() {
            @Override
            public long callback(WString fileName, DokanFileInfo rawFileInfo) {
                System.out.println("DeleteDirectory");
                try {
                    fs.deleteDirectory(fileName.toString());
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.MoveFile = new DOKAN_OPERATIONS.MoveFileDelegate() {
            @Override
            public long callback(WString oldFileName, WString newFileName, boolean replaceIfExisting, DokanFileInfo rawFileInfo) {
                System.out.println("MoveFile");
                try {
                    fs.moveFile(oldFileName.toString(), newFileName.toString(), replaceIfExisting);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.SetEndOfFile = new DOKAN_OPERATIONS.SetEndOfFileDelegate() {
            @Override
            public long callback(WString fileName, long byteOffset, DokanFileInfo rawFileInfo) {
                System.out.println("SetEndOfFile");
                try {
                    fs.setEndOfFile(fs.getFileHandle(fileName, rawFileInfo), byteOffset);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.SetAllocationSize = new DOKAN_OPERATIONS.SetAllocationSizeDelegate() {
            @Override
            public long callback(WString fileName, long length, DokanFileInfo rawFileInfo) {
                System.out.println("SetAllocationSize");
                try {
                    fs.setAllocationSize(fs.getFileHandle(fileName, rawFileInfo), length);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.LockFile = new DOKAN_OPERATIONS.LockFileDelegate() {
            @Override
            public long callback(WString fileName, long byteOffset, long length, DokanFileInfo rawFileInfo) {
                System.out.println("LockFile");
                try {
                    fs.lockFile(fs.getFileHandle(fileName, rawFileInfo), byteOffset, length);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.UnlockFile = new DOKAN_OPERATIONS.UnlockFileDelegate() {
            @Override
            public long callback(WString fileName, long byteOffset, long length, DokanFileInfo rawFileInfo) {
                System.out.println("UnlockFile");
                try {
                    fs.unlockFile(fs.getFileHandle(fileName, rawFileInfo), byteOffset, length);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        return ops;
    }

    static public long exceptionToErrorCode(Throwable t) {
        return exceptionToErrorCode(t, NtStatus.Unsuccessful);
    }

    static public long exceptionToErrorCode(Throwable t, long defaultCode) {
        if (t instanceof DokanException) return ((DokanException) t).errorCode;
        if (t instanceof FileNotFoundException) return ErrorCodes.ERROR_FILE_NOT_FOUND;
        if (t instanceof FileAlreadyExistsException) return ErrorCodes.ERROR_ALREADY_EXISTS;
        t.printStackTrace();
        return defaultCode;
    }


    static private String limitStringSize(String str, int len) {
        return str.substring(0, Math.min(str.length(), len));
    }

    static private Pattern fnmatchToPattern(String match) {
        String out = "";

        for (char c : match.toCharArray()) {
            switch (c) {
                case '?':
                    out += '.';
                    break;
                case '*':
                    out += ".*";
                    break;
                default:
                    out += c;
                    break;
            }
        }

        return Pattern.compile("^" + out + "$", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    }
}