package com.dokan.java.structure;

import com.dokan.java.constants.dokany.MountOption;
import com.sun.jna.Structure;
import com.sun.jna.WString;

import java.util.Arrays;
import java.util.List;

/**
 * Dokan mount options used to describe Dokan device behavior.
 *
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_t_i_o_n_s.html">Dokany Documentation of PDOKAN_OPTIONS</a>
 */
public class DokanOptions extends Structure implements Structure.ByReference {

    /**
     * Version of the Dokany features requested (version "123" is equal to Dokany version 1.2.3).
     */
    public short Version = 110;

    /**
     * Number of threads to be used internally by Dokany library. More thread will handle more events at the same time.
     */
    public short ThreadCount;

    /**
     * Features enable for the mount. It is a combination of {@link MountOption} masks.
     */
    public int Options;

    /**
     * FileSystem can store anything here
     */
    public long GlobalContext = 0L;

    /**
     * Mount point. It can be a drive letter like \"M:\\\" or a folder path \"C:\\mount\\dokany\" on a NTFS partition.
     */
    public WString MountPoint;

    /**
     * UNC name used for the Network Redirector.
     *
     * @see <a href="https://docs.microsoft.com/de-de/windows-hardware/drivers/ifs/support-for-unc-naming-and-mup">Support for UNC Naming</a>
     */
    public WString UNCName;

    /**
     * Max timeout in milliseconds of each request before Dokany gives up to wait events to complete.
     */
    public long Timeout;

    /**
     * Allocation Unit Size of the volume. This will affect the file size.
     */
    public long AllocationUnitSize;

    /**
     * Sector Size of the volume. This will affect then file size.
     */
    public long SectorSize;

    public DokanOptions() {
    }

    public DokanOptions(final String mountPoint, final short threadCount, final EnumIntegerSet<MountOption> mountOptions, final String uncName, final long timeout, final long allocationUnitSize, final long sectorSize) {
        MountPoint = new WString(mountPoint);
        ThreadCount = threadCount;
        Options = mountOptions.toInt();
        if (uncName != null) {
            UNCName = new WString(uncName);
        } else {
            UNCName = null;
        }
        Timeout = timeout;
        AllocationUnitSize = allocationUnitSize;
        SectorSize = sectorSize;
    }

    public EnumIntegerSet<MountOption> getMountOptions() {
        return EnumIntegerSet.enumSetFromInt(this.Options, MountOption.values());
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("Version", "ThreadCount", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize");
    }

    @Override
    public String toString() {
        return "DeviceOptions(Version=" + this.Version + ", ThreadCount=" + this.ThreadCount + ", Options=" + this.Options + ", mountOptions=" + this.getMountOptions() + ", GlobalContext=" + this.GlobalContext + ", MountPoint=" + this.MountPoint + ", UNCName=" + this.UNCName + ", Timeout=" + this.Timeout + ", AllocationUnitSize=" + this.AllocationUnitSize + ", SectorSize=" + this.SectorSize + ")";
    }
}
