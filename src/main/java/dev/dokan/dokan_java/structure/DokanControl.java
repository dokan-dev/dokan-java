package dev.dokan.dokan_java.structure;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.LongByReference;

import java.util.Arrays;
import java.util.List;

/**
 * Structure containing information for a single dokany mount.
 * <p>
 * Used by {@link dev.dokan.dokan_java.NativeMethods#DokanGetMountPointList(boolean, LongByReference)} and {@link dev.dokan.dokan_java.NativeMethods#DokanReleaseMountPointList(Pointer)}.
 */
public class DokanControl extends Structure implements Structure.ByReference {

    /**
     * File System Type
     */
    public long Type;

    /**
     * Mount point. Can be "M:\" (drive letter) or "C:\mount\dokan" (path in NTFS)
     */
    public char[] MountPoint = new char[256];

    /**
     * UNC name used for network volume
     */
    public char[] UNCName = new char[64];

    /**
     * Disk Device Name
     */
    public char[] DeviceName = new char[64];

    /**
     * Volume Device Object
     */
    public Pointer DeviceObject;

    /**
     * Session ID of calling process
     */
    public long SessionId;

    public DokanControl(Pointer p) {
        this(p, 0);
    }

    public DokanControl(Pointer p, long offset) {
        super(p);
        long currentOffset = offset;
        this.Type = p.getLong(currentOffset);
        currentOffset += NativeLong.SIZE;
        this.MountPoint = p.getCharArray(currentOffset, WinNT.MAX_PATH);
        currentOffset += WinNT.MAX_PATH * 2;
        this.UNCName = p.getCharArray(currentOffset, 64);
        currentOffset += 64 * 2;
        this.DeviceName = p.getCharArray(currentOffset, 64);
        currentOffset += 64 * 2;
        this.DeviceObject = new Pointer(p.getLong(currentOffset));
        currentOffset += NativeLong.SIZE;
        this.SessionId = p.getLong(currentOffset);
    }


    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("Type", "MountPoint", "UNCName", "DeviceName", "DeviceObject", "SessionId");
    }
}
