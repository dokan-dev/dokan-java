package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileAttribute;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

@SuppressWarnings("WeakerAccess")
public class DokanOperations {
    public boolean defaultLog = false;

    public void mounted() {
        if (defaultLog) System.out.println("DokanOperations.mounted");
    }

    public void unmounted() {
        if (defaultLog) System.out.println("DokanOperations.unmounted");
    }

    public void createFile(String fileName, int securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
        if (defaultLog) System.out.println("DokanOperations.createFile: fileName = [" + fileName + "], securityContext = [" + securityContext + "], rawDesiredAccess = [" + rawDesiredAccess + "], rawFileAttributes = [" + rawFileAttributes + "], rawShareAccess = [" + rawShareAccess + "], rawCreateDisposition = [" + rawCreateDisposition + "], rawCreateOptions = [" + rawCreateOptions + "]");
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

    public void getFileInformation(String fileName, ByHandleFileInformation handleFileInfo) {
        handleFileInfo.setFileAttributes(FileAttribute.FILE_ATTRIBUTE_DIRECTORY);
        handleFileInfo.setFileSize(1024L);
        //handleFileInfo.nFileSizeHigh
        System.out.println(fileName);
    }

    public long getUsedBytes() {
        return 0L;
    }

    public long getFreeBytesAvailable() {
        return getTotalBytesAvailable() - getUsedBytes();
    }

    public long getTotalBytesAvailable() {
        return 1024L * 1024 * 1024;
    }

    public long getTotalFreeBytesAvailable() {
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
                    DokanOperations.this.mounted();
                    return NtStatus.Success;
                } catch (Throwable t) {
                    t.printStackTrace();
                    return NtStatus.Unsuccessful;
                }
            }
        };
        ops.Unmounted = new DOKAN_OPERATIONS.UnmountedDelegate() {
            @Override
            public long callback(DokanFileInfo rawFileInfo) {
                try {
                    DokanOperations.this.unmounted();
                    return NtStatus.Success;
                } catch (Throwable t) {
                    t.printStackTrace();
                    return NtStatus.Unsuccessful;
                }
            }
        };
        ops.ZwCreateFile = new DOKAN_OPERATIONS.ZwCreateFileDelegate() {
            @Override
            public long callback(WString rawFileName, IntByReference securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
                try {
                    DokanOperations.this.createFile(rawFileName.toString(), securityContext.getValue(), rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, dokanFileInfo);
                    return NtStatus.Success;
                } catch (Throwable t) {
                    t.printStackTrace();
                    return NtStatus.Unsuccessful;
                }
            }
        };
        ops.Cleanup = new DOKAN_OPERATIONS.CleanupDelegate() {
            @Override
            public void callback(WString rawFileName, DokanFileInfo rawFileInfo) {
                DokanOperations.this.cleanup(rawFileName.toString(), rawFileInfo);
            }
        };
        ops.CloseFile = new DOKAN_OPERATIONS.CloseFileDelegate() {
            @Override
            public void callback(WString fileName, DokanFileInfo rawFileInfo) {
                DokanOperations.this.closeFile(fileName.toString(), rawFileInfo);
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
                volumeNameBuffer.setWideString(0L, limitStringSize(DokanOperations.this.getVolumeName(), volumeNameSize));
                rawFileSystemNameBuffer.setWideString(0L, limitStringSize(DokanOperations.this.getFileSystemName(), rawFileSystemNameSize));
                rawVolumeSerialNumber.setValue(DokanOperations.this.getSerialNumber());
                rawMaximumComponentLength.setValue(DokanOperations.this.getMaxComponentLength());
                rawFileSystemFlags.setValue(DokanOperations.this.getFileSystemFeatures());

                System.out.println("volumeNameBuffer = [" + volumeNameBuffer + "], volumeNameSize = [" + volumeNameSize + "], rawVolumeSerialNumber = [" + rawVolumeSerialNumber + "], rawMaximumComponentLength = [" + rawMaximumComponentLength + "], rawFileSystemFlags = [" + rawFileSystemFlags + "], rawFileSystemNameBuffer = [" + rawFileSystemNameBuffer + "], rawFileSystemNameSize = [" + rawFileSystemNameSize + "], rawFileInfo = [" + rawFileInfo + "]");
                return NtStatus.Success;
            }
        };
        /*
        ops.GetFileInformation = new DOKAN_OPERATIONS.GetFileInformationDelegate() {
            @Override
            public long callback(WString fileName, ByHandleFileInformation handleFileInfo, DokanFileInfo fileInfo) {
                DokanOperations.this.getFileInformation(fileName.toString(), handleFileInfo);
                return NtStatus.Success;
            }
        };
        */
        ops.GetDiskFreeSpace = new DOKAN_OPERATIONS.GetDiskFreeSpaceDelegate() {
            @Override
            public long callback(LongByReference rawFreeBytesAvailable, LongByReference rawTotalNumberOfBytes, LongByReference rawTotalNumberOfFreeBytes, DokanFileInfo rawFileInfo) {
                rawFreeBytesAvailable.setValue(DokanOperations.this.getFreeBytesAvailable());
                rawTotalNumberOfBytes.setValue(DokanOperations.this.getTotalBytesAvailable());
                rawTotalNumberOfFreeBytes.setValue(getTotalFreeBytesAvailable());
                return 0;
            }
        };
        return ops;
    }

    static private String limitStringSize(String str, int len) {
        return str.substring(0, Math.min(str.length(), len));
    }
}
