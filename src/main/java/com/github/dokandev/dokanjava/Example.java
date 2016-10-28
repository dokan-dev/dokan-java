package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileAttribute;

import java.io.FileNotFoundException;
import java.io.IOException;

@SuppressWarnings("unused")
public class Example {
    static public void main(String[] args) {
        System.out.println("hello");
        System.out.println(Dokan.version());
        System.out.println(Dokan.driverVersion());
        Dokan.unmount('M');
        Dokan.main(DokanOptions.DebugMode | DokanOptions.StderrOutput, "M:\\", 10000, new DokanFilesystem() {
            {
                defaultLog = true;
            }

            @Override
            public void createFile(String fileName, int securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) throws IOException {
                //CreationDisposition.CREATE_NEW
                //super.createFile(fileName, securityContext, rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, dokanFileInfo);
                //throw new DokanException(NtStatus.NoSuchFile);
                //throw new DokanException(NtStatus.CrmProtocolAlreadyExists);
                //throw new FileAlreadyExistsException("exists");
                //throw new FileNotFoundException();
                //throw new DokanException(NtStatus.UserExists);
                if (fileName.equals("\\")) {

                } else {
                    throw new FileNotFoundException();
                }
            }

            @Override
            public void getFileInformation(String fileName, ByHandleFileInformation handleFileInfo, DokanFileInfo fileInfo) throws IOException {
                if (fileName.equals("\\")) {
                    handleFileInfo.setFileAttributes(FileAttribute.FILE_ATTRIBUTE_DIRECTORY);
                    handleFileInfo.setFileSize(1024L);
                    //handleFileInfo.nFileSizeHigh
                    System.out.println(fileName);
                } else if (fileName.equals("\\HELLO.TXT")) {
                    handleFileInfo.setFileAttributes(FileAttribute.FILE_ATTRIBUTE_NORMAL);
                    handleFileInfo.setFileSize(3L);
                } else {
                    throw new FileNotFoundException();
                }
            }

            @Override
            public void findFiles(String fileName, FileEmitter emitter) {
                if (fileName.equals("\\")) {
                    emitter.emit(Win32FindData.file("HELLO.TXT", 1024L));
                    emitter.emit(Win32FindData.file("HELLO2.TXT", 1024L));
                    emitter.emit(Win32FindData.file("HELLO3.TXT", 1024L));
                    emitter.emit(Win32FindData.directory("demo"));
                } else if (fileName.equals("\\demo")) {
                    emitter.emit(Win32FindData.file("HELLO.TXT", 1024L));
                } else {

                }
            }

            @Override
            public int readFile(String fileName, long fileOffset, byte[] data, int dataLength, DokanFileInfo fileInfo) throws IOException {
                //System.out.println("Example.readFile");
                //System.out.println("fileName = [" + fileName + "], fileOffset = [" + fileOffset + "], data = [" + data + "], dataLength = [" + dataLength + "], fileInfo = [" + fileInfo + "]");
                if (fileOffset != 0L) return 0;
                data[0] = 'H';
                data[1] = 'E';
                data[2] = 'L';
                return 3;
            }

            @Override
            public long getUsedBytes() {
                return 256L * 1024 * 1024;
            }
        });
        //System.out.println(NativeMethods.INSTANCE.DokanRemoveMountPoint(new WString("M")));
        //DokanNative.INSTANCE.printf("hello %s", "world");
    }
}
