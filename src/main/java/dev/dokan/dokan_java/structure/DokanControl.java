package dev.dokan.dokan_java.structure;

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
    public long Type;

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
    public static List<DokanControl> getDokanControlList(Pointer start, @Unsigned int length) { //TODO Relocate
        List<DokanControl> list = new ArrayList<>();
        /*
         * Let's do the math:
         * A list that uses an unsigned int (32 bit) as index could save up to 2^32 objects.
         * Even if it only saved one byte in each entry (not accounting for the actual overhead of the entries themselves),
         * that would be 2^32 bytes = 4 GB for that list alone!
         * If the list of active Dokan MountPoints exceeds 2^31 (signed int) entries, we probably have other problems.
         *
         * But let's assume that this could still happen:
         * In this case we want the application to crash so that someone else can fix that nonsense.
         * "assert" seems like a good choice for this case (for once).
         */
        assert !(length < 0);
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
