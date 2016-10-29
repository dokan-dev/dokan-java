package com.github.dokandev.dokanjava;

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
                try {
                    System.out.println("%%%%%%%%%%%%%% CREATEFILE: " + rawFileName.toString());
                    dokanFileInfo._context = fs.allocateFileHandle(
                            fs.createFile(rawFileName.toString(), securityContext.getValue(), rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions)
                    );
                    dokanFileInfo.write();
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.Cleanup = new DOKAN_OPERATIONS.CleanupDelegate() {
            @Override
            public void callback(WString fileName, DokanFileInfo rawFileInfo) {
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
                try {
                    fs.closeFile(fs.getFileHandle(fileName, rawFileInfo));
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
                try {
                    //handleFileInfo.setInfo(fs.getFileInformation(fs.getFileHandle(fileInfo)));
                    handleFileInfo.setInfo(fs.getFileInformation(fileName.toString()));
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };

        ops.GetDiskFreeSpace = new DOKAN_OPERATIONS.GetDiskFreeSpaceDelegate() {
            @Override
            public long callback(LongByReference rawFreeBytesAvailable, LongByReference rawTotalNumberOfBytes, LongByReference rawTotalNumberOfFreeBytes, DokanFileInfo rawFileInfo) {
                rawFreeBytesAvailable.setValue(fs.getFreeBytesAvailable());
                rawTotalNumberOfBytes.setValue(fs.getTotalBytesAvailable());
                rawTotalNumberOfFreeBytes.setValue(fs.getTotalFreeBytesAvailable());
                return 0;
            }
        };
        ops.FindFiles = new DOKAN_OPERATIONS.FindFilesDelegate() {
            @Override
            public long callback(final WString fileName, final DOKAN_OPERATIONS.FillWin32FindData rawFillFindData, final DokanFileInfo rawFileInfo) {
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
                try {
                    fs.findFiles(fs.getFileHandle(fileName, rawFileInfo), fnmatchToPattern(searchPattern.toString()), new DokanFilesystem.FileEmitter() {
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
        ops.ReadFile = new DOKAN_OPERATIONS.ReadFileDelegate() {
            @Override
            public long callback(WString fileName, Pointer buffer, int bufferLength, IntByReference readLength, long offset, DokanFileInfo fileInfo) {
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
                try {
                    byte[] data = new byte[rawSecurityDescriptorLength];
                    rawSecurityDescriptor.read(0L, data, 0, rawSecurityDescriptorLength);
                    fs.setFileSecurity(fs.getFileHandle(fileName, fileInfo), rawSecurityInformation, data);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        }
        ops.DeleteFile = new DOKAN_OPERATIONS.DeleteFileDelegate() {
            @Override
            public long callback(WString fileName, DokanFileInfo rawFileInfo) {
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