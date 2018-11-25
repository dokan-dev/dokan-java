package com.dokan.java;

import com.dokan.java.constants.dokany.MountOption;
import com.dokan.java.structure.EnumIntegerSet;

import java.nio.file.Path;

/**
 * An object which can be mounted in a filesystem.
 *
 * @author Armin Schrnek
 * @since 2.0
 */
public interface Mountable extends AutoCloseable {

    /**
     * Mount this object on a mount point with the given options.
     *
     * @param mountPoint Path pointing to an empty Directory or unused drive letter
     * @param volumeName The displayed name of the volume (only important in combination with a drive letter)
     * @param volumeSerialnumber the serial number of the volume
     * @param blocking If true the mount and further file system calls are foreground operations and thus will block this thread
     * @param timeout Timeout after which a not processed file system call is canceled
     * @param allocationUnitSize the size of the smallest allocatable space in bytes
     * @param sectorSize the sector size
     * @param UNCName
     * @param threadCount the number of threads spawned for processing filesystem calls
     * @param options an {@link EnumIntegerSet} containing {@link MountOption}s
     */
    void mount(Path mountPoint, String volumeName, int volumeSerialnumber, boolean blocking, long timeout, long allocationUnitSize, long sectorSize, String UNCName, short threadCount, EnumIntegerSet<MountOption> options);

    /**
     * Unmount this object.
     */
    void unmount();

}
