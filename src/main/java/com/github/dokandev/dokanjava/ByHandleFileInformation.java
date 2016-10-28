package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileInfo;
import com.github.dokandev.dokanjava.util.FileTime;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"PointlessBitwiseExpression", "unused"})
public class ByHandleFileInformation extends Structure implements Structure.ByReference {
    public int dwFileAttributes;
    public FileTime.VAL ftCreationTime;
    public FileTime.VAL ftLastAccessTime;
    public FileTime.VAL ftLastWriteTime;
    public int dwVolumeSerialNumber;
    public int nFileSizeHigh;
    public int nFileSizeLow;
    public int dwNumberOfLinks;
    public int nFileIndexHigh;
    public int nFileIndexLow;

    public ByHandleFileInformation() {
    }

    public ByHandleFileInformation(int fileAttribute, long creationTime, long lastAccessTime, long lastWriteTime, int volumeSerialNumber, long fileSize, int numberOfLinks, long fileIndex) {
        this.dwFileAttributes = fileAttribute;
        this.ftCreationTime = new FileTime.VAL(creationTime);
        this.ftLastAccessTime = new FileTime.VAL(lastAccessTime);
        this.ftLastWriteTime = new FileTime.VAL(lastWriteTime);
        this.dwVolumeSerialNumber = volumeSerialNumber;
        this.setFileSize(fileSize);
        this.dwNumberOfLinks = numberOfLinks;
        this.setFileIndex(fileIndex);
    }

    public void setFileAttributes(int /*FileAttribute*/ attributes) {
        dwFileAttributes = attributes;
    }

    public void setCreationTime(Date date) {
        ftCreationTime.setDate(date);
    }

    public void setLastAccessTime(Date date) {
        ftLastAccessTime.setDate(date);
    }

    public void setLastWriteTime(Date date) {
        ftLastWriteTime.setDate(date);
    }

    public void setVolumeSerialNumber(int sn) {
        dwVolumeSerialNumber = sn;
    }

    public void setNumberOfLinks(int links) {
        dwNumberOfLinks = links;
    }

    public void setFileSize(long size) {
        nFileSizeHigh = (int)((size >> 32L) & 0xFFFFFFFFL);
        nFileSizeLow = (int)((size >> 0L) & 0xFFFFFFFFL);
    }

    public void setFileIndex(long index) {
        nFileIndexHigh = (int)((index >> 32L) & 0xFFFFFFFFL);
        nFileIndexLow = (int)((index >> 0L) & 0xFFFFFFFFL);
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList(
                "dwFileAttributes",
                "ftCreationTime",
                "ftLastAccessTime",
                "ftLastWriteTime",
                "dwVolumeSerialNumber",
                "nFileSizeHigh",
                "nFileSizeLow",
                "dwNumberOfLinks",
                "nFileIndexHigh",
                "nFileIndexLow"
        );
    }


    public void setInfo(FileInfo fileInformation) {
        fileInformation.setByHandleFileInfo(this);
    }
}
