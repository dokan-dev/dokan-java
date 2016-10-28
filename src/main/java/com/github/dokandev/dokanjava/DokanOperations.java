package com.github.dokandev.dokanjava;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

@SuppressWarnings("WeakerAccess")
public class DokanOperations {
    public boolean defaultLog = false;

    public void mounted() {
        if (defaultLog) System.out.println("DokanOperations.mounted");
    }

    public void unmounted() {
        if (defaultLog) System.out.println("DokanOperations.unmounted");
    }

    public void createFile(String rawFileName, int securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
        if (defaultLog) System.out.println("DokanOperations.createFile: rawFileName = [" + rawFileName + "], securityContext = [" + securityContext + "], rawDesiredAccess = [" + rawDesiredAccess + "], rawFileAttributes = [" + rawFileAttributes + "], rawShareAccess = [" + rawShareAccess + "], rawCreateDisposition = [" + rawCreateDisposition + "], rawCreateOptions = [" + rawCreateOptions + "]");
    }

    public void cleanup(String rawFileName, DokanFileInfo rawFileInfo) {
        if (defaultLog) System.out.println("DokanOperations.cleanup: " + rawFileName);
    }

    public void closeFile(String rawFileName, DokanFileInfo rawFileInfo) {
        if (defaultLog) System.out.println("DokanOperations.closeFile: " + rawFileName);
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

    public void getFileInformation(String rawFileName, BY_HANDLE_FILE_INFORMATION handleFileInfo) {
        handleFileInfo.setFileAttributes(FileAttribute.DIRECTORY);
        handleFileInfo.setFileSize(1024L);
        //handleFileInfo.nFileSizeHigh
        System.out.println(rawFileName);
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
            public void callback(WString rawFileName, DokanFileInfo rawFileInfo) {
                DokanOperations.this.closeFile(rawFileName.toString(), rawFileInfo);
            }
        };
        ops.GetVolumeInformation = new DOKAN_OPERATIONS.GetVolumeInformationDelegate() {
            @Override
            public long callback(
                    Pointer rawVolumeNameBuffer, int rawVolumeNameSize,
                    IntByReference rawVolumeSerialNumber,
                    IntByReference rawMaximumComponentLength,
                    IntByReference rawFileSystemFlags,
                    Pointer rawFileSystemNameBuffer, int rawFileSystemNameSize,
                    DokanFileInfo rawFileInfo
            ) {
                rawVolumeNameBuffer.setWideString(0L, limitStringSize(DokanOperations.this.getVolumeName(), rawVolumeNameSize));
                rawFileSystemNameBuffer.setWideString(0L, limitStringSize(DokanOperations.this.getFileSystemName(), rawFileSystemNameSize));
                rawVolumeSerialNumber.setValue(DokanOperations.this.getSerialNumber());
                rawMaximumComponentLength.setValue(DokanOperations.this.getFileSystemFeatures());
                rawFileSystemFlags.setValue(FileSystemFeatures.CasePreservedNames);

                System.out.println("rawVolumeNameBuffer = [" + rawVolumeNameBuffer + "], rawVolumeNameSize = [" + rawVolumeNameSize + "], rawVolumeSerialNumber = [" + rawVolumeSerialNumber + "], rawMaximumComponentLength = [" + rawMaximumComponentLength + "], rawFileSystemFlags = [" + rawFileSystemFlags + "], rawFileSystemNameBuffer = [" + rawFileSystemNameBuffer + "], rawFileSystemNameSize = [" + rawFileSystemNameSize + "], rawFileInfo = [" + rawFileInfo + "]");
                return NtStatus.Success;
            }
        };
        ops.GetFileInformation = new DOKAN_OPERATIONS.GetFileInformationDelegate() {
            @Override
            public long callback(WString fileName, BY_HANDLE_FILE_INFORMATION handleFileInfo, DokanFileInfo fileInfo) {
                DokanOperations.this.getFileInformation(fileName.toString(), handleFileInfo);
                return NtStatus.Success;
            }
        };
        return ops;
    }

    static private String limitStringSize(String str, int len) {
        return str.substring(0, Math.min(str.length(), len));
    }
}
