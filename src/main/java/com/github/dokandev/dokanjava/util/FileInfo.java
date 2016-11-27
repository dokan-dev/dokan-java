package com.github.dokandev.dokanjava.util;

import com.github.dokandev.dokanjava.ByHandleFileInformation;
import com.github.dokandev.dokanjava.Win32FindData;

import java.util.Date;

public class FileInfo {
    public int attributes;
    public Date creationTime;
    public Date lastAccessTime;
    public Date lastWriteTime;
    public long fileSize;
    public String fileName;
    public long fileIndex;
    public int numberOfLinks;
    public int volumeSerialNumber;

    public FileInfo() {
    }

    public FileInfo(String name, long size) {
        this.fileName = name;
        this.fileSize = size;
    }

    public FileInfo(long fileIndex, String name, long size, int attributes, Date creationTime, Date lastAccessTime, Date lastWriteTime) {
        this.fileIndex = fileIndex;
        this.fileName = name;
        this.fileSize = size;
        this.attributes = attributes;
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastWriteTime = lastWriteTime;
    }

    public Win32FindData toWin32FindData() {
        return new Win32FindData(fileName, fileSize, attributes, creationTime, lastAccessTime, lastWriteTime);
    }


    public void setByHandleFileInfo(ByHandleFileInformation info) {
        info.setFileIndex(fileIndex);
        info.setFileSize(fileSize);
        info.dwNumberOfLinks = numberOfLinks;
        info.dwFileAttributes = attributes;
        info.ftCreationTime.setDate(this.creationTime);
        info.ftLastAccessTime.setDate(this.lastAccessTime);
        info.ftLastWriteTime.setDate(this.lastWriteTime);
        info.dwVolumeSerialNumber = volumeSerialNumber;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "attributes=" + attributes +
                ", creationTime=" + creationTime +
                ", lastAccessTime=" + lastAccessTime +
                ", lastWriteTime=" + lastWriteTime +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", fileIndex=" + fileIndex +
                ", numberOfLinks=" + numberOfLinks +
                ", volumeSerialNumber=" + volumeSerialNumber +
                '}';
    }
}
