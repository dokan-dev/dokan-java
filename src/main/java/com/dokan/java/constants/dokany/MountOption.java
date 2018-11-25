package com.dokan.java.constants.dokany;

import com.dokan.java.constants.EnumInteger;
import com.dokan.java.structure.EnumIntegerSet;
import com.dokan.java.structure.DokanOptions;

/**
 * Enumeration of features that can be enabled for a mount. Part of {@link DokanOptions}
 *
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/group___d_o_k_a_n___o_p_t_i_o_n.html">Dokany documentation</a>
 */
public enum MountOption implements EnumInteger {
    DEBUG_MODE(1, "Enable output debug message."),
    STD_ERR_OUTPUT(2, "Enable output debug message to stderr."),
    ALT_STREAM(4, "Use alternate stream."),
    WRITE_PROTECTION(8, "Enable mount drive as write-protected."),
    NETWORK_DRIVE(16, "Use network drive - Dokan network provider need to be installed."),
    REMOVABLE(32, "Use removable drive."),
    MOUNT_MANAGER(64, "Use mount manager."),
    CURRENT_SESSION(128, "Mount the drive on current session only."),
    FILELOCK_USER_MODE(256, "Enable Lockfile/Unlockfile operations. Otherwise Dokan will take care of it.");

    private final int mask;
    private final String description;

    MountOption(final int i, final String desc) {
        this.mask = i;
        this.description = desc;
    }

    public static EnumIntegerSet<MountOption> fromInt(final int value) {
        return EnumIntegerSet.enumSetFromInt(value, values());
    }

    public int getMask() {
        return this.mask;
    }

    public String getDescription() {
        return this.description;
    }

}
