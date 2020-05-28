package dev.dokan.dokan_java.wrappers.mountinfo;


import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.structure.EnumIntegerSet;


public interface RWMountInfo extends ROMountInfo {

    void setThreadCount(short threadCount);

    void setMountOptions(EnumIntegerSet<MountOption> mountOptions);

    void setFlags(int flags);

    boolean setFlag(MountOption flag);

    boolean unsetFlag(MountOption flag);

    boolean updateFlag(MountOption flag, boolean value);

    void setMountPoint(String mountPoint);

    void setUNCName(String uncName);

    void setTimeout(long timeout);

    void setAllocationUnitSize(long allocationUnitSize);

    void setSectorSize(long sectorSize);
}