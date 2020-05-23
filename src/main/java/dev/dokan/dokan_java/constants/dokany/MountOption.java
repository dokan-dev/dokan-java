package dev.dokan.dokan_java.constants.dokany;

import dev.dokan.dokan_java.conv.EnumIntegerSet;
import dev.dokan.dokan_java.conv.MaskValueEnum;
import dev.dokan.dokan_java.structure.DokanOptions;

/**
 * Enumeration of features that can be enabled for a mount. Part of {@link DokanOptions}
 *
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/group___d_o_k_a_n___o_p_t_i_o_n.html">Dokany documentation</a>
 */
public enum MountOption implements MaskValueEnum {
    DEBUG_MODE(1, "Enable output debug message."),
    STD_ERR_OUTPUT(2, "Enable output debug message to stderr."),
    ALT_STREAM(4, "Use alternate stream."),
    WRITE_PROTECTION(8, "Enable mount drive as write-protected."),
    NETWORK_DRIVE(16, "Use network drive - Dokan network provider need to be installed."),
    REMOVABLE(32, "Use removable drive."),
    MOUNT_MANAGER(64, "Use mount manager."),
    CURRENT_SESSION(128, "Mount the drive on current session only."),
    FILELOCK_USER_MODE(256, "Enable Lockfile/Unlockfile operations. Otherwise Dokan will take care of it.");

    private final int maskValue;
    private final String description;

    MountOption(final int maskValue, final String desc) {
        this.maskValue = maskValue;
        this.description = desc;
    }

    public static EnumIntegerSet<MountOption> fromInt(final int value) {
        return EnumIntegerSet.enumSetFromInt(value, values());
    }

    @Override
    public int intValue() {
        return this.maskValue;
    }

    public String getDescription() {
        return this.description;
    }

}
