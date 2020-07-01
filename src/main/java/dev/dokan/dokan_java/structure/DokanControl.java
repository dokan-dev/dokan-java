package dev.dokan.dokan_java.structure;


import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.Unsigned;

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
    @Unsigned
    public int Type;

    /**
     * Mount point. Can be "M:\" (drive letter) or "C:\mount\dokan" (path in NTFS)
     */
    public char[] MountPoint = new char[WinNT.MAX_PATH];

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
    @Unsigned
    public int SessionId;

    public DokanControl(Pointer p) {
        this(p, 0);
    }

    public DokanControl(Pointer p, long currentOffset) {
        super(p);
        this.Type = p.getInt(currentOffset);
        currentOffset += NativeLong.SIZE;
        this.MountPoint = p.getCharArray(currentOffset, WinNT.MAX_PATH);
        currentOffset += WinNT.MAX_PATH * 2;
        this.UNCName = p.getCharArray(currentOffset, 64);
        currentOffset += 64 * 2;
        this.DeviceName = p.getCharArray(currentOffset, 64);
        currentOffset += 64 * 2;
        this.DeviceObject = new Pointer(p.getLong(currentOffset));
        currentOffset += Native.POINTER_SIZE;
        this.SessionId = p.getInt(currentOffset);
    }


    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("Type", "MountPoint", "UNCName", "DeviceName", "DeviceObject", "SessionId");
    }

    /**
     * Creates a java {@link List} of {@link DokanControl} structures given the pointer returned by NativeMethods#DokanGetMountPointList(boolean, LongByReference).<br>
     * <br>
     * <b>Implementation note:</b><br>
     * Length is an unsigned 32-bit int. Java only supports arrays and lists up to an index size of 2<sup>31</sup>-1 ({@link Integer#MAX_VALUE Integer.MAX_VALUE}).
     * A list that exceeds this size is unrealistic (it would need at least 2 GB of space, even if it only stored unique Byte-Objects).
     * Any value that exceeds {@link Integer#MAX_VALUE Integer.MAX_VALUE}, has it's 32nd bit set (at least when using Two's complement for storing it).
     * The 32nd bit defines the sign of a java int, therefore a {@code length < 0} indicates that this threshold has been reached.
     * In this case the application crashes ("fail-fast") to allow someone to take care of this issue.
     *
     * @param start  the initial pointer returned by the native method
     * @param length the number of elements in the array. Also acquired with the native method call.
     * @return a list of DokanControl structures
     */
    public static List<DokanControl> getDokanControlList(Pointer start, @Unsigned int length) { //TODO Relocate
        List<DokanControl> list = new ArrayList<>();

        if(length < 0) {
            throw new AssertionError(String.format("Illegal length: %s (%d)", Integer.toUnsignedString(length), length));
        }
        if (length != 0) {
            long offset = 0;
            for(int i = 0; i < length; i++) {
                DokanControl control = new DokanControl(start, offset);
                list.add(control);
                offset += control.size();
            }
        }
        return list;
    }
}
