package com.github.dokandev.dokanjava;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("PointlessBitwiseExpression")
public class BY_HANDLE_FILE_INFORMATION extends Structure implements Structure.ByReference {
    public int dwFileAttributes;
    public FILETIME.VAL ftCreationTime;
    public FILETIME.VAL ftLastAccessTime;
    public FILETIME.VAL ftLastWriteTime;
    public int dwVolumeSerialNumber;
    public int nFileSizeHigh;
    public int nFileSizeLow;
    public int dwNumberOfLinks;
    public int nFileIndexHigh;
    public int nFileIndexLow;

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
}
