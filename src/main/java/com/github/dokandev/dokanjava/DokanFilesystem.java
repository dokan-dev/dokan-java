package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileInfo;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class DokanFilesystem {
    public boolean defaultLog = false;

    public void mounted() {
        if (defaultLog) System.out.println("DokanOperations.mounted");
    }

    public void unmounted() {
        if (defaultLog) System.out.println("DokanOperations.unmounted");
    }

    public void createFile(String fileName, int securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) throws IOException {
        if (defaultLog)
            System.out.println("DokanOperations.createFile: fileName = [" + fileName + "], securityContext = [" + securityContext + "], rawDesiredAccess = [" + rawDesiredAccess + "], rawFileAttributes = [" + rawFileAttributes + "], rawShareAccess = [" + rawShareAccess + "], rawCreateDisposition = [" + rawCreateDisposition + "], rawCreateOptions = [" + rawCreateOptions + "]");
    }

    public void cleanup(String fileName, DokanFileInfo rawFileInfo) {
        if (defaultLog) System.out.println("DokanOperations.cleanup: " + fileName);
    }

    public void closeFile(String fileName, DokanFileInfo rawFileInfo) {
        if (defaultLog) System.out.println("DokanOperations.closeFile: " + fileName);
    }

    public String getVolumeName() {
        return "VOLUME";
    }

    public String getFileSystemName() {
        return "DOKAN";
    }

    public int getSerialNumber() {
        //return 0x12345678;
        return 0x00000000;
    }

    public int getFileSystemFeatures() {
        return FileSystemFeatures.CasePreservedNames;
    }

    public FileInfo getFileInformation(String fileName, DokanFileInfo fileInfo) throws IOException {
        //handleFileInfo.setFileAttributes(FileAttribute.FILE_ATTRIBUTE_DIRECTORY);
        //handleFileInfo.setFileSize(1024L);
        //handleFileInfo.nFileSizeHigh
        //System.out.println(fileName);
        return new FileInfo(fileName, 0L);
    }

    public long getUsedBytes() {
        return 0L;
    }

    final public long getFreeBytesAvailable() {
        return getTotalBytesAvailable() - getUsedBytes();
    }

    public long getTotalBytesAvailable() {
        return 1024L * 1024 * 1024;
    }

    final public long getTotalFreeBytesAvailable() {
        return getFreeBytesAvailable();
    }

    public int getMaxComponentLength() {
        return 256;
    }

    public DOKAN_OPERATIONS toStruct() {
        DOKAN_OPERATIONS ops = new DOKAN_OPERATIONS();
        ops.Mounted = new DOKAN_OPERATIONS.MountedDelegate() {
            @Override
            public long callback(DokanFileInfo rawFileInfo) {
                try {
                    DokanFilesystem.this.mounted();
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
                    DokanFilesystem.this.unmounted();
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
                    DokanFilesystem.this.createFile(rawFileName.toString(), securityContext.getValue(), rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, dokanFileInfo);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };
        ops.Cleanup = new DOKAN_OPERATIONS.CleanupDelegate() {
            @Override
            public void callback(WString rawFileName, DokanFileInfo rawFileInfo) {
                DokanFilesystem.this.cleanup(rawFileName.toString(), rawFileInfo);
            }
        };
        ops.CloseFile = new DOKAN_OPERATIONS.CloseFileDelegate() {
            @Override
            public void callback(WString fileName, DokanFileInfo rawFileInfo) {
                DokanFilesystem.this.closeFile(fileName.toString(), rawFileInfo);
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
                volumeNameBuffer.setWideString(0L, limitStringSize(DokanFilesystem.this.getVolumeName(), volumeNameSize));
                rawFileSystemNameBuffer.setWideString(0L, limitStringSize(DokanFilesystem.this.getFileSystemName(), rawFileSystemNameSize));
                rawVolumeSerialNumber.setValue(DokanFilesystem.this.getSerialNumber());
                rawMaximumComponentLength.setValue(DokanFilesystem.this.getMaxComponentLength());
                rawFileSystemFlags.setValue(DokanFilesystem.this.getFileSystemFeatures());

                System.out.println("volumeNameBuffer = [" + volumeNameBuffer + "], volumeNameSize = [" + volumeNameSize + "], rawVolumeSerialNumber = [" + rawVolumeSerialNumber + "], rawMaximumComponentLength = [" + rawMaximumComponentLength + "], rawFileSystemFlags = [" + rawFileSystemFlags + "], rawFileSystemNameBuffer = [" + rawFileSystemNameBuffer + "], rawFileSystemNameSize = [" + rawFileSystemNameSize + "], rawFileInfo = [" + rawFileInfo + "]");
                return NtStatus.Success;
            }
        };

        ops.GetFileInformation = new DOKAN_OPERATIONS.GetFileInformationDelegate() {
            @Override
            public long callback(WString fileName, ByHandleFileInformation handleFileInfo, DokanFileInfo fileInfo) {
                try {
                    handleFileInfo.setInfo(DokanFilesystem.this.getFileInformation(fileName.toString(), fileInfo));
                    return NtStatus.Success;
                } catch (Throwable t) {
                    return exceptionToErrorCode(t);
                }
            }
        };

        ops.GetDiskFreeSpace = new DOKAN_OPERATIONS.GetDiskFreeSpaceDelegate() {
            @Override
            public long callback(LongByReference rawFreeBytesAvailable, LongByReference rawTotalNumberOfBytes, LongByReference rawTotalNumberOfFreeBytes, DokanFileInfo rawFileInfo) {
                rawFreeBytesAvailable.setValue(DokanFilesystem.this.getFreeBytesAvailable());
                rawTotalNumberOfBytes.setValue(DokanFilesystem.this.getTotalBytesAvailable());
                rawTotalNumberOfFreeBytes.setValue(DokanFilesystem.this.getTotalFreeBytesAvailable());
                return 0;
            }
        };
        ops.FindFiles = new DOKAN_OPERATIONS.FindFilesDelegate() {
            @Override
            public long callback(final WString fileName, final DOKAN_OPERATIONS.FillWin32FindData rawFillFindData, final DokanFileInfo rawFileInfo) {
                try {
                    DokanFilesystem.this.findFiles(fileName.toString(), new FileEmitter() {
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
                    DokanFilesystem.this.findFiles(fileName.toString(), fnmatchToPattern(searchPattern.toString()), new FileEmitter() {
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
            public long callback(WString fileName, Pointer rawBuffer, int rawBufferLength, IntByReference rawReadLength, long rawOffset, DokanFileInfo fileInfo) {
                try {
                    byte[] data = new byte[rawBufferLength];
                    int read = DokanFilesystem.this.readFile(fileName.toString(), rawOffset, data, rawBufferLength, fileInfo);
                    rawBuffer.write(0, data, 0, read);
                    rawReadLength.setValue(read);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    long out = exceptionToErrorCode(t);
                    if (out == NtStatus.Unsuccessful) {
                        return ErrorCodes.ERROR_READ_FAULT;
                    } else {
                        return out;
                    }
                }
            }
        };
        return ops;
    }

    public int readFile(String fileName, long fileOffset, byte[] data, int dataLength, DokanFileInfo fileInfo) throws IOException {
        throw new FileNotFoundException();
    }

    public void findFiles(String fileName, FileEmitter emitter) throws IOException {
        //emitter.emit(new Win32FindData("HELLO.TXT", 1024L));
        //emitter.emit(new Win32FindData("HELLO2.TXT", 1024L));
        //emitter.emit(new Win32FindData("HELLO3.TXT", 1024L));
    }

    public void findFiles(String fileName, final Pattern searchPattern, final FileEmitter emitter) throws IOException {
        findFiles(fileName, new FileEmitter() {
            @Override
            public void emit(FileInfo file) {
                if (searchPattern.matcher(file.fileName).matches()) {
                    emitter.emit(file);
                }
            }
        });
    }

    interface FileEmitter {
        void emit(FileInfo file);
    }

    static public long exceptionToErrorCode(Throwable t) {
        if (t instanceof DokanException) return ((DokanException) t).errorCode;
        if (t instanceof FileNotFoundException) return ErrorCodes.ERROR_FILE_NOT_FOUND;
        if (t instanceof FileAlreadyExistsException) return ErrorCodes.ERROR_ALREADY_EXISTS;
        t.printStackTrace();
        return NtStatus.Unsuccessful;
    }

    static private String limitStringSize(String str, int len) {
        return str.substring(0, Math.min(str.length(), len));
    }

    static private Pattern fnmatchToPattern(String match) {
        String out = "";

        for (char c : match.toCharArray()) {
            switch (c) {
                case '?':
                    out += ".";
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
