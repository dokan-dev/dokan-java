package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileAttribute;
import com.github.dokandev.dokanjava.util.FileTime;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Win32FindData extends Structure implements Structure.ByReference {
    static public final int MAX_PATH = 260;

    public int dwFileAttributes;
    public FileTime.VAL ftCreationTime;
    public FileTime.VAL ftLastAccessTime;
    public FileTime.VAL ftLastWriteTime;
    public int nFileSizeHigh;
    public int nFileSizeLow;
    public int dwReserved0;
    public int dwReserved1;
    public char[] cFileName = new char[MAX_PATH];
    public char[] cAlternateFileName = new char[14];

    public Win32FindData() {

    }

    static public Win32FindData file(String name, long size, Date time) {
        return new Win32FindData(name, size, FileAttribute.FILE_ATTRIBUTE_NORMAL, time, time, time);
    }

    static public Win32FindData file(String name, long size) {
        return file(name, size, new Date());
    }

    static public Win32FindData directory(String name) {
        return directory(name, new Date());
    }

    static public Win32FindData directory(String name, Date time) {
        return new Win32FindData(name, 4096, FileAttribute.FILE_ATTRIBUTE_DIRECTORY, time, time, time);
    }

    public Win32FindData(String name, long size) {
        this(name, size, FileAttribute.FILE_ATTRIBUTE_NORMAL, new Date(), new Date(), new Date());
    }

    public Win32FindData(String name, long size, Date time) {
        this(name, size, FileAttribute.FILE_ATTRIBUTE_NORMAL, time, time, time);
    }

    public Win32FindData(String name, long size, int attributes, Date creationTime, Date lastAccessTime, Date lastWriteTime) {
        this.setFileName(name);
        this.dwFileAttributes = attributes;
        this.ftCreationTime = new FileTime.VAL(creationTime);
        this.ftLastAccessTime = new FileTime.VAL(lastAccessTime);
        this.ftLastWriteTime = new FileTime.VAL(lastWriteTime);
        this.setSize(size);
    }

    public void setSize(long size) {
        this.nFileSizeHigh = (int) ((size >> 32L) & 0xFFFFFFFFL);
        this.nFileSizeLow = (int) ((size >> 0L) & 0xFFFFFFFFL);
    }

    public void setFileName(String name) {
        name.getChars(0, name.length(), cFileName, 0);
    }

    public String getFileName() {
        return Native.toString(cFileName);
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList(
                "dwFileAttributes",
                "ftCreationTime",
                "ftLastAccessTime",
                "ftLastWriteTime",
                "nFileSizeHigh",
                "nFileSizeLow",
                "dwReserved0",
                "dwReserved1",
                "cFileName",
                "cAlternateFileName"
        );
    }
}
