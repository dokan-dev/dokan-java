package dev.dokan.dokan_java.wrappers.mountinfo;


import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.structure.DokanOptions;
import dev.dokan.dokan_java.structure.EnumIntegerSet;


public interface ROMountInfo {

    short getDokanVersion();

    short getThreadCount();

    EnumIntegerSet<MountOption> getMountOptions();

    int getFlags();

    boolean getFlag(MountOption flag);

//    long getGlobalContext();

    String getMountPoint();

    String getUNCName();

    long getTimeout();

    long getAllocationUnitSize();

    long getSectorSize();

    boolean isMutable();

    ROMountInfo immutableCopy();

    RWMountInfo mutableCopy();

    DokanOptions nativeCopy();

    void nativeCopy(DokanOptions options);
}