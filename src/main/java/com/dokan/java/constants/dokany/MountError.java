package com.dokan.java.constants.dokany;

import com.dokan.java.DokanyOperations;
import com.dokan.java.constants.EnumInteger;
import com.dokan.java.structure.DokanOptions;

/**
 * Return Values of {@link com.dokan.java.NativeMethods#DokanMain(DokanOptions, DokanyOperations)}
 *
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/group___dokan_main_result.html#ga39e6334f1cca7437400660e123c7d98f">Dokany documentation</a>
 */
public enum MountError implements EnumInteger {
    SUCCESS(0, "Mount succeed."),
    MOUNT_ERROR(-1, "Mount error."),
    DRIVE_LETTER_ERROR(-2, "Mount failed: Bad drive letter."),
    DRIVER_INSTALL_ERROR(-3, "Mount failed: Can't install driver."),
    START_ERROR(-4, "Mount failed: Driver answer that something is wrong."),
    CANNOT_ASSIGN(-5, "Mount failed: Cannot assign a drive letter or mount point. Probably already used by another volume."),
    MOUNT_POINT_ERROR(-6, "Mount failed: Mount point is invalid."),
    VERSION_ERROR(-7, "Mount failed: Requested an incompatible version.");
    private final int mask;
    private final String description;

    public static MountError fromInt(final int value) {
        return EnumInteger.enumFromInt(value, values());
    }

    MountError(final int mask, final String description) {
        this.mask = mask;
        this.description = description;
    }

    public int getMask() {
        return this.mask;
    }

    public String getDescription() {
        return this.description;
    }
}
