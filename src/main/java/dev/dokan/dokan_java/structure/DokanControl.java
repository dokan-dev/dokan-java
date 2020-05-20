package dev.dokan.dokan_java.structure;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Structure containing information for a single Dokan device.
 * <p>
 * Used by dev.dokan.dokan_java.NativeMethods#DokanGetMountPointList(boolean, LongByReference)} and dev.dokan.dokan_java.NativeMethods#DokanReleaseMountPointList(Pointer).
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

    /**
     * Creates a java {@link List} of {@link DokanControl} strcutures given the pointer returned by NativeMethods#DokanGetMountPointList(boolean, LongByReference).
     *
     * @param start  the initial pointer returned by the native method
     * @param length the number of elements in the array. Also acquired with the native method call.
     * @return a list of DokanControl structures
     */
    public static List<DokanControl> getDokanControlList(Pointer start, long length) {
        List<DokanControl> list = new ArrayList<>();
        if (length == 0) {
            return list;
        } else if (length < 0) {
            //TODO length is actually an unsigned long! -> java always treats them as signed
            return list;
        } else {
            list.add(new DokanControl(start));
            for (int i = 1; i < length; i++) {
                list.add(new DokanControl(start, i * list.get(0).size()));
            }
            return list;
        }
    }
}
