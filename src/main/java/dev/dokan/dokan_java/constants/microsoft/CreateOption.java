package dev.dokan.dokan_java.constants.microsoft;

import dev.dokan.dokan_java.masking.MaskValueSet;
import dev.dokan.dokan_java.masking.MaskValueEnum;

/**
 * Enum of flags specifying the options to apply when the driver creates or opens the file.
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows-hardware/drivers/ddi/content/wdm/nf-wdm-zwcreatefile">Microsofts documentation of ZwCreateFile</a>
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_e_r_a_t_i_o_n_s.html#a40c2f61e1287237f5fd5c2690e795183">Dokany documentation of ZwCreateFile</a>
 */
public enum CreateOption implements MaskValueEnum {
    FILE_DIRECTORY_FILE(CreateOptions.FILE_DIRECTORY_FILE),
    FILE_WRITE_THROUGH(CreateOptions.FILE_WRITE_THROUGH),
    FILE_SEQUENTIAL_ONLY(CreateOptions.FILE_SEQUENTIAL_ONLY),
    FILE_NO_INTERMEDIATE_BUFFERING(CreateOptions.FILE_NO_INTERMEDIATE_BUFFERING),
    FILE_SYNCHRONOUS_IO_ALERT(CreateOptions.FILE_SYNCHRONOUS_IO_ALERT),
    FILE_SYNCHRONOUS_IO_NONALERT(CreateOptions.FILE_SYNCHRONOUS_IO_NONALERT),
    FILE_NON_DIRECTORY_FILE(CreateOptions.FILE_NON_DIRECTORY_FILE),
    FILE_CREATE_TREE_CONNECTION(CreateOptions.FILE_CREATE_TREE_CONNECTION),
    FILE_COMPLETE_IF_OPLOCKED(CreateOptions.FILE_COMPLETE_IF_OPLOCKED),
    FILE_NO_EA_KNOWLEDGE(CreateOptions.FILE_NO_EA_KNOWLEDGE),
    FILE_OPEN_REMOTE_INSTANCE(CreateOptions.FILE_OPEN_REMOTE_INSTANCE),
    FILE_RANDOM_ACCESS(CreateOptions.FILE_RANDOM_ACCESS),
    FILE_OPEN_REPARSE_POINT(CreateOptions.FILE_OPEN_REPARSE_POINT),
    FILE_DELETE_ON_CLOSE(CreateOptions.FILE_DELETE_ON_CLOSE),
    FILE_OPEN_BY_FILE_ID(CreateOptions.FILE_OPEN_BY_FILE_ID),
    FILE_OPEN_FOR_BACKUP_INTENT(CreateOptions.FILE_OPEN_FOR_BACKUP_INTENT),
    FILE_NO_COMPRESSION(CreateOptions.FILE_NO_COMPRESSION),
    FILE_SESSION_AWARE(CreateOptions.FILE_SESSION_AWARE);

    private final int maskingValue;

    CreateOption(final int maskingValue) {
        this.maskingValue = maskingValue;
    }

    public static MaskValueSet<CreateOption> maskValueSet(final int mask) {
        return MaskValueSet.maskValueSet(mask, values());
    }

    @Override
    public int intValue() {
        return this.maskingValue;
    }

}
