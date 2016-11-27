package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileInfo;
import com.github.dokandev.dokanjava.util.StreamInfo;
import com.sun.jna.WString;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public abstract class DokanFilesystem<TFileHandle extends DokanFileHandle> {
    public class OpenFileResult {
        public boolean exists;
        public TFileHandle handle;

        public OpenFileResult(boolean exists, TFileHandle handle) {
            this.exists = exists;
            this.handle = handle;
        }

        @Override
        public String toString() {
            return "OpenFileResult{" +
                    "exists=" + exists +
                    ", handle=" + handle +
                    '}';
        }
    }

    public boolean getDefaultLog() {
        return getDebug();
    }

    public boolean getDebug() {
        return false;
    }

    public boolean getDebugStderrOutput() {
        return false;
    }

    public int getAllocationUnitSize() {
        return 4096;
    }

    public int getSectorSize() {
        return 4096;
    }

    public int getTimeout() {
        return 10000;
    }

    public void mounted() {
        if (getDefaultLog()) System.out.println("DokanOperations.mounted");
    }

    public void unmounted() {
        if (getDefaultLog()) System.out.println("DokanOperations.unmounted");
    }

    public OpenFileResult createFile(String fileName, int securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, boolean isDirectory) throws IOException {
        if (getDefaultLog())
            System.out.println("DokanOperations.createFile: fileName = [" + fileName + "], securityContext = [" + securityContext + "], rawDesiredAccess = [" + rawDesiredAccess + "], rawFileAttributes = [" + rawFileAttributes + "], rawShareAccess = [" + rawShareAccess + "], rawCreateDisposition = [" + rawCreateDisposition + "], rawCreateOptions = [" + rawCreateOptions + "], isDirectory = [" + isDirectory + "]");

        return new OpenFileResult(false, createHandle(fileName));
    }

    abstract protected TFileHandle createHandle(String fileName) throws IOException;

    //public void cleanup(TFileHandle file) {
    public void cleanup(String fileName) {
        if (getDefaultLog()) System.out.println("DokanOperations.cleanup: " + fileName);
    }

    public void closeFile(TFileHandle file) {
        if (getDefaultLog()) System.out.println("DokanOperations.closeFile: " + file.fileName);
    }

    public String getVolumeName() {
        return "VOLUME";
    }

    public String getFileSystemName() {
        return "DOKAN";
    }

    private long lastAvailableId = 1;
    final private Queue<Long> availableIds = new LinkedList<Long>();
    final private Map<Long, TFileHandle> handles = new HashMap<Long, TFileHandle>();

    final long allocateFileHandle(TFileHandle info) {
        long id;
        if (availableIds.isEmpty()) {
            id = lastAvailableId++;
        } else {
            id = availableIds.remove();
        }
        handles.put(id, info);
        System.out.println("&&&&&&&&& ALLOCATE: " + id);
        return id;
    }

    final TFileHandle removeFileHandle(long id) {
        System.out.println("&&&&&&&&& REMOVE: " + id);
        TFileHandle handle = handles.remove(id);
        availableIds.add(id);
        return handle;
    }

    final TFileHandle getFileHandle(WString fileName, long id) throws IOException {
        if (id == 0) {
            System.out.println("*");
            return createHandle(fileName.toString());
        }
        System.out.println("&&&&&&&&& GET: " + id);
        return handles.get(id);
    }

    final TFileHandle getFileHandle(WString fileName, DokanFileInfo info) throws IOException {
        TFileHandle handle = getFileHandle(fileName, info._context);
        if (handle != null) handle.info = info;
        return handle;
    }

    public int getSerialNumber() {
        //return 0x12345678;
        return 0x00000000;
    }

    public int getFileSystemFeatures() {
        return FileSystemFeatures.CasePreservedNames;
    }

    //public FileInfo getFileInformation(TFileHandle handle) throws IOException {
    public FileInfo getFileInformation(String fileName) throws IOException {
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


    public void unlockFile(TFileHandle file, long byteOffset, long length) {
    }

    public void lockFile(TFileHandle file, long byteOffset, long length) {
    }

    public void setAllocationSize(TFileHandle file, long length) {
    }

    public void setEndOfFile(TFileHandle file, long byteOffset) {
    }

    public void moveFile(String oldFileName, String newFileName, boolean replaceIfExisting) throws IOException {
    }

    public void deleteFile(String fileName) throws IOException {
    }

    public void deleteDirectory(String fileName) throws IOException {
    }

    public int readFile(TFileHandle file, long fileOffset, byte[] data, int dataLength) throws IOException {
        throw new FileNotFoundException();
    }

    public int writeFile(TFileHandle file, long fileOffset, byte[] data, int dataLength) throws IOException {
        throw new FileNotFoundException();
    }

    public void findFiles(TFileHandle file, FileEmitter emitter) throws IOException {
        //emitter.emit(new Win32FindData("HELLO.TXT", 1024L));
        //emitter.emit(new Win32FindData("HELLO2.TXT", 1024L));
        //emitter.emit(new Win32FindData("HELLO3.TXT", 1024L));
    }

    public void findFiles(TFileHandle file, final Pattern searchPattern, final FileEmitter emitter) throws IOException {
        findFiles(file, new FileEmitter() {
            @Override
            public void emit(FileInfo file) {
                if (searchPattern.matcher(file.fileName).matches()) {
                    emitter.emit(file);
                }
            }
        });
    }

    public void flushFileBuffers(TFileHandle file) {
    }

    public void setFileAttributes(TFileHandle file, int attributes) {
    }

    public void setFileTime(TFileHandle file, Date creation, Date access, Date modification) {
    }

    public void findStreams(TFileHandle file, StreamEmitter emitter) {
    }

    public int getFileSecurity(TFileHandle file, int kind, byte[] out) {
        return 0;
    }

    public void setFileSecurity(TFileHandle fileHandle, int kind, byte[] data) {
    }

    public interface FileEmitter {
        void emit(FileInfo file);
    }

    public interface StreamEmitter {
        void emit(StreamInfo stream);
    }
}
