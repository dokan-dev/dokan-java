package dev.dokan.dokan_java.wrappers.mountinfo;


import com.sun.jna.WString;
import dev.dokan.dokan_java.DokanNativeMethods;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.structure.DokanOptions;
import dev.dokan.dokan_java.structure.EnumIntegerSet;


public class ImmutableMountInfo implements ROMountInfo {

//    private final long globalContext;

    private final short threadCount;
    private final int mountOptions;
    private final String mountPoint;
    private final String uncName;
    private final long timeout;
    private final long allocationUnitSize;
    private final long sectorSize;

    public ImmutableMountInfo(DokanOptions dokanOptions) {
        this.threadCount = dokanOptions.ThreadCount;
        this.mountOptions = dokanOptions.Options;
//        this.globalContext = dokanOptions.GlobalContext;
        this.mountPoint = dokanOptions.MountPoint.toString();
        this.uncName = dokanOptions.UNCName.toString();
        this.timeout = dokanOptions.Timeout;
        this.allocationUnitSize = dokanOptions.AllocationUnitSize;
        this.sectorSize = dokanOptions.SectorSize;
    }

    ImmutableMountInfo(ImmutableMountInfo info) {
        this.threadCount = info.threadCount;
        this.mountOptions = info.mountOptions;
//        this.globalContext = info.globalContext;
        this.mountPoint = info.mountPoint;
        this.uncName = info.uncName;
        this.timeout = info.timeout;
        this.allocationUnitSize = info.allocationUnitSize;
        this.sectorSize = info.sectorSize;
    }

    ImmutableMountInfo(MountInfo info) {
        this.threadCount = info.getThreadCount();
        this.mountOptions = info.getFlags();
//        this.globalContext = info.getGlobalContext();
        this.mountPoint = info.getMountPoint();
        this.uncName = info.getUNCName();
        this.timeout = info.getTimeout();
        this.allocationUnitSize = info.getAllocationUnitSize();
        this.sectorSize = info.getSectorSize();
    }

    @Override
    public short getDokanVersion() {
        return DokanNativeMethods.getMinimumRequiredDokanVersion();
    }

    @Override
    public short getThreadCount() {
        return this.threadCount;
    }

    @Override
    public EnumIntegerSet<MountOption> getMountOptions() {
        return MountOption.fromInt(this.mountOptions);
    }

    @Override
    public int getFlags() {
        return this.mountOptions;
    }

    @Override
    public boolean getFlag(MountOption flag) {
        return (this.mountOptions & flag.getMask()) != 0;
    }

//    @Override
//    public long getGlobalContext() {
//        return this.globalContext;
//    }

    @Override
    public String getMountPoint() {
        return this.mountPoint;
    }

    @Override
    public String getUNCName() {
        return this.uncName;
    }

    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public long getAllocationUnitSize() {
        return this.allocationUnitSize;
    }

    @Override
    public long getSectorSize() {
        return this.sectorSize;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public ROMountInfo immutableCopy() {
        return new ImmutableMountInfo(this);
    }

    @Override
    public RWMountInfo mutableCopy() {
        return new MountInfo(this);
    }

    @Override
    public DokanOptions nativeCopy() {
        DokanOptions options = new DokanOptions(this.mountPoint, this.threadCount, this.mountOptions, this.uncName, this.timeout, this.allocationUnitSize, this.sectorSize);
//        options.GlobalContext = this.globalContext;

        return options;
    }

    @Override
    public void nativeCopy(DokanOptions options) {
        options.ThreadCount = this.threadCount;
        options.Options = this.mountOptions;
//        options.GlobalContext = this.globalContext;
        options.MountPoint = new WString(this.mountPoint);
        options.UNCName = new WString(this.uncName);
        options.Timeout = this.timeout;
        options.AllocationUnitSize = this.allocationUnitSize;
        options.SectorSize = this.sectorSize;
    }
}