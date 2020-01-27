package dev.dokan.dokan_java;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.constants.microsoft.CreationDisposition;

import java.util.Date;

import static com.sun.jna.platform.win32.WinError.*;
import static dev.dokan.dokan_java.constants.microsoft.CreateDispositions.*;
import static dev.dokan.dokan_java.constants.microsoft.CreateOptions.*;
import static dev.dokan.dokan_java.constants.microsoft.CreationDisposition.*;
import static dev.dokan.dokan_java.constants.microsoft.NtStatuses.*;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_CTX_WINSTATION_ACCESS_DENIED;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_CTX_WINSTATION_ALREADY_EXISTS;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_CTX_WINSTATION_BUSY;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_CTX_WINSTATION_NAME_INVALID;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_CTX_WINSTATION_NOT_FOUND;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_CURRENT_DOMAIN_NOT_ALLOWED;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_DECRYPTION_FAILED;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_DESTINATION_ELEMENT_FULL;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_DEVICE_DOOR_OPEN;
import static dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes.ERROR_DEVICE_NOT_CONNECTED;

/**
 * Utilities to do various operations.
 */
public class DokanyUtils {

    private static final boolean winVersionAtLeast8;

    static {
        boolean tmp = false;
        try {
            String s = System.getProperty("os.version");
            if (s.startsWith("10") || s.startsWith("8")) {
                tmp = true;
            }
        } catch (Exception e) {
            //NO-OP
        }
        winVersionAtLeast8 = tmp;
    }

    private DokanyUtils() {

    }

    public static String trimStrToSize(final String str, final int len) {
        return str.substring(0, Math.min(str.length(), len));
    }

    public static FILETIME getTime(final Date date) {
        return new FILETIME(date);
    }

    public static FILETIME getTime(final long time) {
        return getTime(new Date(time));
    }

    public static boolean canHandleShutdownHooks() {
        SecurityManager security = System.getSecurityManager();
        if (security == null) {
            return true;
        }
        try {
            security.checkPermission(new RuntimePermission("shutdownHooks"));
            return true;
        } catch (final SecurityException e) {
            return false;
        }
    }

    /**
     * Extends the file attributes number with the file flags given in the createOptions.
     *
     * @param fileAttributes
     * @param createOptions
     * @return fileAttributes extended with the file flags
     */
    public static int addFileFlagsToFileAttributes(int fileAttributes, int createOptions) {
        int outFileAttributesAndFlags = fileAttributes;
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_WRITE_THROUGH, FILE_WRITE_THROUGH);
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_SEQUENTIAL_SCAN, FILE_SEQUENTIAL_ONLY);
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_RANDOM_ACCESS, FILE_RANDOM_ACCESS);
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_NO_BUFFERING, FILE_NO_INTERMEDIATE_BUFFERING);
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_OPEN_REPARSE_POINT, FILE_OPEN_REPARSE_POINT);
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_DELETE_ON_CLOSE, FILE_DELETE_ON_CLOSE);
        outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, WinNT.FILE_FLAG_BACKUP_SEMANTICS, FILE_OPEN_FOR_BACKUP_INTENT);

        if (winVersionAtLeast8) {
            outFileAttributesAndFlags = DokanMapKernelBit(outFileAttributesAndFlags, createOptions, 0x00800000, FILE_SESSION_AWARE); //FILE_FLAG_SESSION_AWARE == 0x00800000
        }

        return outFileAttributesAndFlags;
    }

    private static int DokanMapKernelBit(int dest, int src, int userBit, int kernelBit) {
        if (((src) & (kernelBit)) == (kernelBit))
            return (dest |= userBit);
        else
            return dest;
    }

    /**
     * Maps the generic file accesses specific to files and directories to the general generic accesses.
     * Copied from dokan.c.
     *
     * @param fileAccess the
     * @return
     */
    public static int mapFileGenericAccessToGenericAccess(int fileAccess) {
        boolean genericRead = false, genericWrite = false, genericExecute = false, genericAll = false;
        int outDesiredAccess = fileAccess;

        if ((outDesiredAccess & WinNT.FILE_GENERIC_READ) == WinNT.FILE_GENERIC_READ) {
            outDesiredAccess |= WinNT.GENERIC_READ;
            genericRead = true;
        }
        if ((outDesiredAccess & WinNT.FILE_GENERIC_WRITE) == WinNT.FILE_GENERIC_WRITE) {
            outDesiredAccess |= WinNT.GENERIC_WRITE;
            genericWrite = true;
        }
        if ((outDesiredAccess & WinNT.FILE_GENERIC_EXECUTE) == WinNT.FILE_GENERIC_EXECUTE) {
            outDesiredAccess |= WinNT.GENERIC_EXECUTE;
            genericExecute = true;
        }
        if ((outDesiredAccess & WinNT.FILE_ALL_ACCESS) == WinNT.FILE_ALL_ACCESS) {
            outDesiredAccess |= WinNT.GENERIC_ALL;
            genericAll = true;
        }

        if (genericRead)
            outDesiredAccess &= ~WinNT.FILE_GENERIC_READ;
        if (genericWrite)
            outDesiredAccess &= ~WinNT.FILE_GENERIC_WRITE;
        if (genericExecute)
            outDesiredAccess &= ~WinNT.FILE_GENERIC_EXECUTE;
        if (genericAll)
            outDesiredAccess &= ~WinNT.FILE_ALL_ACCESS;

        return outDesiredAccess;
    }

    /**
     * Converts the kernel file creation flags to the win32 flags.
     * Copied from dokan.c.
     *
     * @param createDisposition
     * @return integer corresponding to an enum in {@link CreationDisposition}
     */
    public static int convertCreateDispositionToCreationDispostion(int createDisposition) {
        switch (createDisposition) {
            case FILE_CREATE:
                return CREATE_NEW.getMask();
            case FILE_OPEN:
                return OPEN_EXISTING.getMask();
            case FILE_OPEN_IF:
                return OPEN_ALWAYS.getMask();
            case FILE_OVERWRITE:
                return TRUNCATE_EXISTING.getMask();
            case FILE_SUPERSEDE:
                // The documentation isn't clear on the difference between replacing a file
                // and truncating it.
                // For now we just map it to create/truncate
            case FILE_OVERWRITE_IF:
                return CREATE_ALWAYS.getMask();
            default:
                //TODO: maybe throw an exception
                return 0;
        }
    }

    /**
     * Maps Win32 error codes to their respective NTStatus accordingly to ntstatus.i
     *
     * @param win32Errorcode a valid win32 error code
     * @return the corresponding NTStatus value
     */
    public static int ntStatusFromWin32ErrorCode(int win32Errorcode) {
        switch (win32Errorcode) {
            case EPT_S_CANT_CREATE:
                return EPT_NT_CANT_CREATE;
            case EPT_S_CANT_PERFORM_OP:
                return EPT_NT_CANT_PERFORM_OP;
            case EPT_S_INVALID_ENTRY:
                return EPT_NT_INVALID_ENTRY;
            case EPT_S_NOT_REGISTERED:
                return EPT_NT_NOT_REGISTERED;
            case ERROR_ACCESS_DENIED:
                return STATUS_ACCESS_DENIED;
            case ERROR_ACCESS_DISABLED_BY_POLICY:
                return STATUS_ACCESS_DISABLED_BY_POLICY_OTHER;
            case ERROR_ACCOUNT_DISABLED:
                return STATUS_ACCOUNT_DISABLED;
            case ERROR_ACCOUNT_EXPIRED:
                return STATUS_ACCOUNT_EXPIRED;
            case ERROR_ACCOUNT_LOCKED_OUT:
                return STATUS_ACCOUNT_LOCKED_OUT;
            case ERROR_ACCOUNT_RESTRICTION:
                return STATUS_ACCOUNT_RESTRICTION;
            case ERROR_ACTIVE_CONNECTIONS:
                return STATUS_ALREADY_DISCONNECTED;
            case ERROR_ADAP_HDW_ERR:
                return STATUS_ADAPTER_HARDWARE_ERROR;
            case ERROR_ADDRESS_ALREADY_ASSOCIATED:
                return STATUS_ADDRESS_ALREADY_ASSOCIATED;
            case ERROR_ADDRESS_NOT_ASSOCIATED:
                return STATUS_ADDRESS_NOT_ASSOCIATED;
            case ERROR_ALIAS_EXISTS:
                return STATUS_ALIAS_EXISTS;
            case ERROR_ALLOTTED_SPACE_EXCEEDED:
                return STATUS_ALLOTTED_SPACE_EXCEEDED;
            case ERROR_FILE_EXISTS:
                return STATUS_OBJECT_NAME_COLLISION;
            case ERROR_ALREADY_EXISTS:
                return STATUS_OBJECT_NAME_COLLISION;
            case ERROR_ARITHMETIC_OVERFLOW:
                return STATUS_INTEGER_OVERFLOW;
            case ERROR_AUDITING_DISABLED:
                return STATUS_AUDITING_DISABLED;
            case ERROR_BADDB:
                return STATUS_REGISTRY_CORRUPT;
            case ERROR_BAD_COMMAND:
                return STATUS_INVALID_DEVICE_STATE;
            case ERROR_BAD_DESCRIPTOR_FORMAT:
                return STATUS_BAD_DESCRIPTOR_FORMAT;
            case ERROR_BAD_DEV_TYPE:
                return STATUS_BAD_DEVICE_TYPE;
            case ERROR_BAD_DRIVER:
                return STATUS_DRIVER_UNABLE_TO_LOAD;
            case ERROR_BAD_EXE_FORMAT:
                return STATUS_INVALID_IMAGE_FORMAT;
            case ERROR_BAD_IMPERSONATION_LEVEL:
                return STATUS_BAD_IMPERSONATION_LEVEL;
            case ERROR_BAD_INHERITANCE_ACL:
                return STATUS_BAD_INHERITANCE_ACL;
            case ERROR_BAD_LENGTH:
                return STATUS_INFO_LENGTH_MISMATCH;
            case ERROR_BAD_LOGON_SESSION_STATE:
                return STATUS_BAD_LOGON_SESSION_STATE;
            case ERROR_BAD_NETPATH:
                return STATUS_BAD_NETWORK_PATH;
            case ERROR_BAD_NET_NAME:
                return STATUS_BAD_NETWORK_NAME;
            case ERROR_BAD_NET_RESP:
                return STATUS_INVALID_NETWORK_RESPONSE;
            case ERROR_BAD_PATHNAME:
                return STATUS_OBJECT_PATH_INVALID;
            case ERROR_BAD_PIPE:
                return STATUS_INVALID_PIPE_STATE;
            case ERROR_BAD_REM_ADAP:
                return STATUS_BAD_REMOTE_ADAPTER;
            case ERROR_BAD_TOKEN_TYPE:
                return STATUS_BAD_TOKEN_TYPE;
            case ERROR_BAD_VALIDATION_CLASS:
                return STATUS_BAD_VALIDATION_CLASS;
            case ERROR_BEGINNING_OF_MEDIA:
                return STATUS_BEGINNING_OF_MEDIA;
            case ERROR_BROKEN_PIPE:
                return STATUS_PIPE_BROKEN;
            case ERROR_BUSY:
                return STATUS_DEVICE_BUSY;
            case ERROR_BUS_RESET:
                return STATUS_BUS_RESET;
            case ERROR_CANNOT_IMPERSONATE:
                return STATUS_CANNOT_IMPERSONATE;
            case ERROR_CANNOT_MAKE:
                return STATUS_CANNOT_MAKE;
            case ERROR_CANT_ACCESS_DOMAIN_INFO:
                return STATUS_CANT_ACCESS_DOMAIN_INFO;
            case ERROR_CANT_ACCESS_FILE:
                return STATUS_IO_REPARSE_TAG_NOT_HANDLED;
            case ERROR_CANT_DISABLE_MANDATORY:
                return STATUS_CANT_DISABLE_MANDATORY;
            case ERROR_CANT_OPEN_ANONYMOUS:
                return STATUS_CANT_OPEN_ANONYMOUS;
            case ERROR_CANT_RESOLVE_FILENAME:
                return STATUS_REPARSE_POINT_NOT_RESOLVED;
            case ERROR_CHILD_MUST_BE_VOLATILE:
                return STATUS_CHILD_MUST_BE_VOLATILE;
            case ERROR_CLEANER_CARTRIDGE_INSTALLED:
                return STATUS_CLEANER_CARTRIDGE_INSTALLED;
            case ERROR_CLUSTER_INVALID_NETWORK:
                return STATUS_CLUSTER_INVALID_NETWORK;
            case ERROR_CLUSTER_INVALID_NETWORK_PROVIDER:
                return STATUS_CLUSTER_INVALID_NETWORK_PROVIDER;
            case ERROR_CLUSTER_INVALID_NODE:
                return STATUS_CLUSTER_INVALID_NODE;
            case ERROR_CLUSTER_INVALID_REQUEST:
                return STATUS_CLUSTER_INVALID_REQUEST;
            case ERROR_CLUSTER_JOIN_IN_PROGRESS:
                return STATUS_CLUSTER_JOIN_IN_PROGRESS;
            case ERROR_CLUSTER_JOIN_NOT_IN_PROGRESS:
                return STATUS_CLUSTER_JOIN_NOT_IN_PROGRESS;
            case ERROR_CLUSTER_LOCAL_NODE_NOT_FOUND:
                return STATUS_CLUSTER_LOCAL_NODE_NOT_FOUND;
            case ERROR_CLUSTER_NETINTERFACE_EXISTS:
                return STATUS_CLUSTER_NETINTERFACE_EXISTS;
            case ERROR_CLUSTER_NETINTERFACE_NOT_FOUND:
                return STATUS_CLUSTER_NETINTERFACE_NOT_FOUND;
            case ERROR_CLUSTER_NETWORK_ALREADY_OFFLINE:
                return STATUS_CLUSTER_NETWORK_ALREADY_OFFLINE;
            case ERROR_CLUSTER_NETWORK_ALREADY_ONLINE:
                return STATUS_CLUSTER_NETWORK_ALREADY_ONLINE;
            case ERROR_CLUSTER_NETWORK_EXISTS:
                return STATUS_CLUSTER_NETWORK_EXISTS;
            case ERROR_CLUSTER_NETWORK_NOT_FOUND:
                return STATUS_CLUSTER_NETWORK_NOT_FOUND;
            case ERROR_CLUSTER_NETWORK_NOT_INTERNAL:
                return STATUS_CLUSTER_NETWORK_NOT_INTERNAL;
            case ERROR_CLUSTER_NODE_ALREADY_DOWN:
                return STATUS_CLUSTER_NODE_ALREADY_DOWN;
            case ERROR_CLUSTER_NODE_ALREADY_MEMBER:
                return STATUS_CLUSTER_NODE_ALREADY_MEMBER;
            case ERROR_CLUSTER_NODE_ALREADY_UP:
                return STATUS_CLUSTER_NODE_ALREADY_UP;
            case ERROR_CLUSTER_NODE_DOWN:
                return STATUS_CLUSTER_NODE_DOWN;
            case ERROR_CLUSTER_NODE_EXISTS:
                return STATUS_CLUSTER_NODE_EXISTS;
            case ERROR_CLUSTER_NODE_NOT_FOUND:
                return STATUS_CLUSTER_NODE_NOT_FOUND;
            case ERROR_CLUSTER_NODE_NOT_MEMBER:
                return STATUS_CLUSTER_NODE_NOT_MEMBER;
            case ERROR_CLUSTER_NODE_NOT_PAUSED:
                return STATUS_CLUSTER_NODE_NOT_PAUSED;
            case ERROR_CLUSTER_NODE_PAUSED:
                return STATUS_CLUSTER_NODE_PAUSED;
            case ERROR_CLUSTER_NODE_UNREACHABLE:
                return STATUS_CLUSTER_NODE_UNREACHABLE;
            case ERROR_CLUSTER_NODE_UP:
                return STATUS_CLUSTER_NODE_UP;
            case ERROR_CLUSTER_NO_SECURITY_CONTEXT:
                return STATUS_CLUSTER_NO_SECURITY_CONTEXT;
            case ERROR_COMMITMENT_LIMIT:
                return STATUS_COMMITMENT_LIMIT;
            case ERROR_CONNECTION_ABORTED:
                return STATUS_CONNECTION_ABORTED;
            case ERROR_CONNECTION_ACTIVE:
                return STATUS_CONNECTION_ACTIVE;
            case ERROR_CONNECTION_COUNT_LIMIT:
                return STATUS_CONNECTION_COUNT_LIMIT;
            case ERROR_CONNECTION_INVALID:
                return STATUS_CONNECTION_INVALID;
            case ERROR_CONNECTION_REFUSED:
                return STATUS_CONNECTION_REFUSED;
            case ERROR_CONNECTION_UNAVAIL:
                return STATUS_DFS_UNAVAILABLE;
            case ERROR_CONTEXT_EXPIRED:
                return SEC_E_CONTEXT_EXPIRED;
            case ERROR_COUNTER_TIMEOUT:
                return STATUS_SERIAL_COUNTER_TIMEOUT;
            case ERROR_CRC:
                return STATUS_CRC_ERROR;
            case ERROR_CTX_BAD_VIDEO_MODE:
                return STATUS_CTX_BAD_VIDEO_MODE;
            case ERROR_CTX_CLIENT_LICENSE_IN_USE:
                return STATUS_CTX_CLIENT_LICENSE_IN_USE;
            case ERROR_CTX_CLIENT_LICENSE_NOT_SET:
                return STATUS_CTX_CLIENT_LICENSE_NOT_SET;
            case ERROR_CTX_CLIENT_QUERY_TIMEOUT:
                return STATUS_CTX_CLIENT_QUERY_TIMEOUT;
            case ERROR_CTX_CLOSE_PENDING:
                return STATUS_CTX_CLOSE_PENDING;
            case ERROR_CTX_CONSOLE_CONNECT:
                return STATUS_CTX_CONSOLE_CONNECT;
            case ERROR_CTX_CONSOLE_DISCONNECT:
                return STATUS_CTX_CONSOLE_DISCONNECT;
            case ERROR_CTX_GRAPHICS_INVALID:
                return STATUS_CTX_GRAPHICS_INVALID;
            case ERROR_CTX_INVALID_MODEMNAME:
                return STATUS_CTX_INVALID_MODEMNAME;
            case ERROR_CTX_INVALID_PD:
                return STATUS_CTX_INVALID_PD;
            case ERROR_CTX_INVALID_WD:
                return STATUS_CTX_INVALID_WD;
            case ERROR_CTX_LICENSE_CLIENT_INVALID:
                return STATUS_CTX_LICENSE_CLIENT_INVALID;
            case ERROR_CTX_LICENSE_EXPIRED:
                return STATUS_CTX_LICENSE_EXPIRED;
            case ERROR_CTX_LICENSE_NOT_AVAILABLE:
                return STATUS_CTX_LICENSE_NOT_AVAILABLE;
            case ERROR_CTX_MODEM_INF_NOT_FOUND:
                return STATUS_CTX_MODEM_INF_NOT_FOUND;
            case ERROR_CTX_MODEM_RESPONSE_BUSY:
                return STATUS_CTX_MODEM_RESPONSE_BUSY;
            case ERROR_CTX_MODEM_RESPONSE_ERROR:
                return STATUS_CTX_RESPONSE_ERROR;
            case ERROR_CTX_MODEM_RESPONSE_NO_CARRIER:
                return STATUS_CTX_MODEM_RESPONSE_NO_CARRIER;
            case ERROR_CTX_MODEM_RESPONSE_NO_DIALTONE:
                return STATUS_CTX_MODEM_RESPONSE_NO_DIALTONE;
            case ERROR_CTX_MODEM_RESPONSE_TIMEOUT:
                return STATUS_CTX_MODEM_RESPONSE_TIMEOUT;
            case ERROR_CTX_MODEM_RESPONSE_VOICE:
                return STATUS_CTX_MODEM_RESPONSE_VOICE;
            case ERROR_CTX_NOT_CONSOLE:
                return STATUS_CTX_NOT_CONSOLE;
            case ERROR_CTX_NO_OUTBUF:
                return STATUS_CTX_NO_OUTBUF;
            case ERROR_CTX_PD_NOT_FOUND:
                return STATUS_CTX_PD_NOT_FOUND;
            case ERROR_CTX_SHADOW_DENIED:
                return STATUS_CTX_SHADOW_DENIED;
            case ERROR_CTX_SHADOW_DISABLED:
                return STATUS_CTX_SHADOW_DISABLED;
            case ERROR_CTX_SHADOW_ENDED_BY_MODE_CHANGE:
                return STATUS_CTX_SHADOW_ENDED_BY_MODE_CHANGE;
            case ERROR_CTX_SHADOW_INVALID:
                return STATUS_CTX_SHADOW_INVALID;
            case ERROR_CTX_SHADOW_NOT_RUNNING:
                return STATUS_CTX_SHADOW_NOT_RUNNING;
            case ERROR_CTX_TD_ERROR:
                return STATUS_CTX_TD_ERROR;
            case ERROR_CTX_WD_NOT_FOUND:
                return STATUS_CTX_WD_NOT_FOUND;
            case ERROR_CTX_WINSTATION_ACCESS_DENIED:
                return STATUS_CTX_WINSTATION_ACCESS_DENIED;
            case ERROR_CTX_WINSTATION_ALREADY_EXISTS:
                return STATUS_CTX_WINSTATION_NAME_COLLISION;
            case ERROR_CTX_WINSTATION_BUSY:
                return STATUS_CTX_WINSTATION_BUSY;
            case ERROR_CTX_WINSTATION_NAME_INVALID:
                return STATUS_CTX_WINSTATION_NAME_INVALID;
            case ERROR_CTX_WINSTATION_NOT_FOUND:
                return STATUS_CTX_WINSTATION_NOT_FOUND;
            case ERROR_CURRENT_DOMAIN_NOT_ALLOWED:
                return STATUS_CURRENT_DOMAIN_NOT_ALLOWED;
            case ERROR_DECRYPTION_FAILED:
                return SEC_E_DECRYPT_FAILURE;
            case ERROR_DESTINATION_ELEMENT_FULL:
                return STATUS_DESTINATION_ELEMENT_FULL;
            case ERROR_DEVICE_DOOR_OPEN:
                return STATUS_DEVICE_DOOR_OPEN;
            case ERROR_DEVICE_IN_USE:
                return STATUS_CONNECTION_IN_USE;
            case ERROR_DEVICE_NOT_CONNECTED:
                return STATUS_DEVICE_NOT_CONNECTED;
            case ERROR_DEVICE_NOT_PARTITIONED:
                return STATUS_DEVICE_NOT_PARTITIONED;
            case ERROR_DEVICE_REINITIALIZATION_NEEDED:
                return STATUS_REINITIALIZATION_NEEDED;
            case ERROR_DEVICE_REMOVED:
                return STATUS_DEVICE_REMOVED;
            case ERROR_DEVICE_REQUIRES_CLEANING:
                return STATUS_DEVICE_REQUIRES_CLEANING;
            case ERROR_DEV_NOT_EXIST:
                return STATUS_DEVICE_DOES_NOT_EXIST;
            case ERROR_DIRECTORY:
                return STATUS_NOT_A_DIRECTORY;
            case ERROR_DIR_NOT_EMPTY:
                return STATUS_DIRECTORY_NOT_EMPTY;
            case ERROR_DISK_CORRUPT:
                return STATUS_DISK_CORRUPT_ERROR;
            case ERROR_DISK_FULL:
                return STATUS_DISK_FULL;
            case ERROR_DISK_OPERATION_FAILED:
                return STATUS_DISK_OPERATION_FAILED;
            case ERROR_DISK_RECALIBRATE_FAILED:
                return STATUS_DISK_RECALIBRATE_FAILED;
            case ERROR_DISK_RESET_FAILED:
                return STATUS_DISK_RESET_FAILED;
            case ERROR_DISK_TOO_FRAGMENTED:
                return STATUS_MFT_TOO_FRAGMENTED;
            case ERROR_DLL_INIT_FAILED:
                return STATUS_DLL_INIT_FAILED;
            case ERROR_DOMAIN_CONTROLLER_NOT_FOUND:
                return STATUS_DOMAIN_CONTROLLER_NOT_FOUND;
            case ERROR_DOMAIN_EXISTS:
                return STATUS_DOMAIN_EXISTS;
            case ERROR_DOMAIN_LIMIT_EXCEEDED:
                return STATUS_DOMAIN_LIMIT_EXCEEDED;
            case ERROR_DOMAIN_TRUST_INCONSISTENT:
                return STATUS_DOMAIN_TRUST_INCONSISTENT;
            case ERROR_DOWNGRADE_DETECTED:
                return STATUS_DOWNGRADE_DETECTED;
            case ERROR_DRIVER_BLOCKED:
                return STATUS_DRIVER_BLOCKED;
            case ERROR_DS_ADMIN_LIMIT_EXCEEDED:
                return STATUS_DS_ADMIN_LIMIT_EXCEEDED;
            case ERROR_DS_AG_CANT_HAVE_UNIVERSAL_MEMBER:
                return STATUS_DS_AG_CANT_HAVE_UNIVERSAL_MEMBER;
            case ERROR_DS_ATTRIBUTE_OR_VALUE_EXISTS:
                return STATUS_DS_ATTRIBUTE_OR_VALUE_EXISTS;
            case ERROR_DS_ATTRIBUTE_TYPE_UNDEFINED:
                return STATUS_DS_ATTRIBUTE_TYPE_UNDEFINED;
            case ERROR_DS_BUSY:
                return STATUS_DS_BUSY;
            case ERROR_DS_CANT_MOD_OBJ_CLASS:
                return STATUS_DS_CANT_MOD_OBJ_CLASS;
            case ERROR_DS_CANT_MOD_PRIMARYGROUPID:
                return STATUS_DS_CANT_MOD_PRIMARYGROUPID;
            case ERROR_DS_CANT_ON_NON_LEAF:
                return STATUS_DS_CANT_ON_NON_LEAF;
            case ERROR_DS_CANT_ON_RDN:
                return STATUS_DS_CANT_ON_RDN;
            case ERROR_DS_CANT_START:
                return STATUS_DS_CANT_START;
            case ERROR_DS_CROSS_DOM_MOVE_ERROR:
                return STATUS_DS_CROSS_DOM_MOVE_FAILED;
            case ERROR_DS_DS_REQUIRED:
                return STATUS_DIRECTORY_SERVICE_REQUIRED;
            case ERROR_DS_GC_NOT_AVAILABLE:
                return STATUS_DS_GC_NOT_AVAILABLE;
            case ERROR_DS_GC_REQUIRED:
                return STATUS_DS_GC_REQUIRED;
            case ERROR_DS_GLOBAL_CANT_HAVE_CROSSDOMAIN_MEMBER:
                return STATUS_DS_GLOBAL_CANT_HAVE_CROSSDOMAIN_MEMBER;
            case ERROR_DS_GLOBAL_CANT_HAVE_LOCAL_MEMBER:
                return STATUS_DS_GLOBAL_CANT_HAVE_LOCAL_MEMBER;
            case ERROR_DS_GLOBAL_CANT_HAVE_UNIVERSAL_MEMBER:
                return STATUS_DS_GLOBAL_CANT_HAVE_UNIVERSAL_MEMBER;
            case ERROR_DS_HAVE_PRIMARY_MEMBERS:
                return STATUS_DS_HAVE_PRIMARY_MEMBERS;
            case ERROR_DS_INCORRECT_ROLE_OWNER:
                return STATUS_DS_INCORRECT_ROLE_OWNER;
            case ERROR_DS_INIT_FAILURE:
                return STATUS_DS_INIT_FAILURE;
            case ERROR_DS_INIT_FAILURE_CONSOLE:
                return STATUS_DS_INIT_FAILURE_CONSOLE;
            case ERROR_DS_INVALID_ATTRIBUTE_SYNTAX:
                return STATUS_DS_INVALID_ATTRIBUTE_SYNTAX;
            case ERROR_DS_INVALID_GROUP_TYPE:
                return STATUS_DS_INVALID_GROUP_TYPE;
            case ERROR_DS_LOCAL_CANT_HAVE_CROSSDOMAIN_LOCAL_MEMBER:
                return STATUS_DS_LOCAL_CANT_HAVE_CROSSDOMAIN_LOCAL_MEMBER;
            case ERROR_DS_LOCAL_MEMBER_OF_LOCAL_ONLY:
                return STATUS_DS_LOCAL_MEMBER_OF_LOCAL_ONLY;
            case ERROR_DS_MACHINE_ACCOUNT_CREATED_PRENT4:
                return STATUS_PRENT4_MACHINE_ACCOUNT;
            case ERROR_DS_MACHINE_ACCOUNT_QUOTA_EXCEEDED:
                return STATUS_DS_MACHINE_ACCOUNT_QUOTA_EXCEEDED;
            case ERROR_DS_MEMBERSHIP_EVALUATED_LOCALLY:
                return STATUS_DS_MEMBERSHIP_EVALUATED_LOCALLY;
            case ERROR_DS_NO_ATTRIBUTE_OR_VALUE:
                return STATUS_DS_NO_ATTRIBUTE_OR_VALUE;
            case ERROR_DS_NO_FPO_IN_UNIVERSAL_GROUPS:
                return STATUS_DS_NO_FPO_IN_UNIVERSAL_GROUPS;
            case ERROR_DS_NO_MORE_RIDS:
                return STATUS_DS_NO_MORE_RIDS;
            case ERROR_DS_NO_NEST_GLOBALGROUP_IN_MIXEDDOMAIN:
                return STATUS_DS_NO_NEST_GLOBALGROUP_IN_MIXEDDOMAIN;
            case ERROR_DS_NO_NEST_LOCALGROUP_IN_MIXEDDOMAIN:
                return STATUS_DS_NO_NEST_LOCALGROUP_IN_MIXEDDOMAIN;
            case ERROR_DS_NO_RIDS_ALLOCATED:
                return STATUS_DS_NO_RIDS_ALLOCATED;
            case ERROR_DS_OBJ_CLASS_VIOLATION:
                return STATUS_DS_OBJ_CLASS_VIOLATION;
            case ERROR_DS_RIDMGR_INIT_ERROR:
                return STATUS_DS_RIDMGR_INIT_ERROR;
            case ERROR_DS_SAM_INIT_FAILURE:
                return STATUS_DS_SAM_INIT_FAILURE;
            case ERROR_DS_SAM_INIT_FAILURE_CONSOLE:
                return STATUS_DS_SAM_INIT_FAILURE_CONSOLE;
            case ERROR_DS_SAM_NEED_BOOTKEY_FLOPPY:
                return STATUS_SAM_NEED_BOOTKEY_FLOPPY;
            case ERROR_DS_SAM_NEED_BOOTKEY_PASSWORD:
                return STATUS_SAM_NEED_BOOTKEY_PASSWORD;
            case ERROR_DS_SENSITIVE_GROUP_VIOLATION:
                return STATUS_DS_SENSITIVE_GROUP_VIOLATION;
            case ERROR_DS_SHUTTING_DOWN:
                return STATUS_DS_SHUTTING_DOWN;
            case ERROR_DS_UNAVAILABLE:
                return STATUS_DS_UNAVAILABLE;
            case ERROR_DS_UNIVERSAL_CANT_HAVE_LOCAL_MEMBER:
                return STATUS_DS_UNIVERSAL_CANT_HAVE_LOCAL_MEMBER;
            case ERROR_DUP_NAME:
                return STATUS_DUPLICATE_NAME;
            case ERROR_EAS_NOT_SUPPORTED:
                return STATUS_EAS_NOT_SUPPORTED;
            case ERROR_EA_LIST_INCONSISTENT:
                return STATUS_EA_LIST_INCONSISTENT;
            case ERROR_EFS_ALG_BLOB_TOO_BIG:
                return STATUS_EFS_ALG_BLOB_TOO_BIG;
            case ERROR_ENCRYPTION_FAILED:
                return SEC_E_ENCRYPT_FAILURE;
            case ERROR_END_OF_MEDIA:
                return STATUS_END_OF_MEDIA;
            case ERROR_ENVVAR_NOT_FOUND:
                return STATUS_VARIABLE_NOT_FOUND;
            case ERROR_EOM_OVERFLOW:
                return STATUS_EOM_OVERFLOW;
            case ERROR_EVENTLOG_CANT_START:
                return STATUS_EVENTLOG_CANT_START;
            case ERROR_EVENTLOG_FILE_CHANGED:
                return STATUS_EVENTLOG_FILE_CHANGED;
            case ERROR_EVENTLOG_FILE_CORRUPT:
                return STATUS_EVENTLOG_FILE_CORRUPT;
            case ERROR_FILEMARK_DETECTED:
                return STATUS_FILEMARK_DETECTED;
            case ERROR_FILENAME_EXCED_RANGE:
                return STATUS_NAME_TOO_LONG;
            case ERROR_FILE_CORRUPT:
                return STATUS_FILE_CORRUPT_ERROR;
            case ERROR_FILE_ENCRYPTED:
                return STATUS_FILE_ENCRYPTED;
            case ERROR_FILE_INVALID:
                return STATUS_FILE_INVALID;
            case ERROR_FILE_NOT_ENCRYPTED:
                return STATUS_FILE_NOT_ENCRYPTED;
            case ERROR_FILE_NOT_FOUND:
                return STATUS_OBJECT_NAME_NOT_FOUND;
            case ERROR_FILE_OFFLINE:
                return STATUS_FILE_IS_OFFLINE;
            case ERROR_FLOPPY_BAD_REGISTERS:
                return STATUS_FLOPPY_BAD_REGISTERS;
            case ERROR_FLOPPY_ID_MARK_NOT_FOUND:
                return STATUS_FLOPPY_ID_MARK_NOT_FOUND;
            case ERROR_FLOPPY_UNKNOWN_ERROR:
                return STATUS_FLOPPY_UNKNOWN_ERROR;
            case ERROR_FLOPPY_WRONG_CYLINDER:
                return STATUS_FLOPPY_WRONG_CYLINDER;
            case ERROR_FULLSCREEN_MODE:
                return STATUS_FULLSCREEN_MODE;
            case ERROR_GENERIC_NOT_MAPPED:
                return STATUS_GENERIC_NOT_MAPPED;
            case ERROR_GEN_FAILURE:
                return STATUS_UNSUCCESSFUL;
            case ERROR_GRACEFUL_DISCONNECT:
                return STATUS_GRACEFUL_DISCONNECT;
            case ERROR_GROUP_EXISTS:
                return STATUS_GROUP_EXISTS;
            case ERROR_HANDLE_EOF:
                return STATUS_END_OF_FILE;
            case ERROR_HOST_DOWN:
                return STATUS_HOST_DOWN;
            case ERROR_HOST_UNREACHABLE:
                return STATUS_HOST_UNREACHABLE;
            case ERROR_ILLEGAL_ELEMENT_ADDRESS:
                return STATUS_ILLEGAL_ELEMENT_ADDRESS;
            case ERROR_ILL_FORMED_PASSWORD:
                return STATUS_ILL_FORMED_PASSWORD;
            case ERROR_INSUFFICIENT_BUFFER:
                return STATUS_BUFFER_TOO_SMALL;
            case ERROR_INTERNAL_DB_CORRUPTION:
                return STATUS_INTERNAL_DB_CORRUPTION;
            case ERROR_INTERNAL_DB_ERROR:
                return STATUS_INTERNAL_DB_ERROR;
            case ERROR_INTERNAL_ERROR:
                return STATUS_INTERNAL_ERROR;
            case ERROR_INVALID_ACCOUNT_NAME:
                return STATUS_INVALID_ACCOUNT_NAME;
            case ERROR_INVALID_ACL:
                return STATUS_INVALID_ACL;
            case ERROR_INVALID_ADDRESS:
                return STATUS_MEMORY_NOT_ALLOCATED;
            case ERROR_INVALID_BLOCK_LENGTH:
                return STATUS_INVALID_BLOCK_LENGTH;
            case ERROR_INVALID_COMPUTERNAME:
                return STATUS_INVALID_COMPUTER_NAME;
            case ERROR_INVALID_DOMAIN_ROLE:
                return STATUS_INVALID_DOMAIN_ROLE;
            case ERROR_INVALID_DOMAIN_STATE:
                return STATUS_INVALID_DOMAIN_STATE;
            case ERROR_INVALID_EA_NAME:
                return STATUS_INVALID_EA_NAME;
            case ERROR_INVALID_FUNCTION:
                return STATUS_NOT_IMPLEMENTED;
            case ERROR_INVALID_GROUP_ATTRIBUTES:
                return STATUS_INVALID_GROUP_ATTRIBUTES;
            case ERROR_INVALID_HANDLE:
                return STATUS_INVALID_HANDLE;
            case ERROR_INVALID_ID_AUTHORITY:
                return STATUS_INVALID_ID_AUTHORITY;
            case ERROR_INVALID_IMPORT_OF_NON_DLL:
                return STATUS_INVALID_IMPORT_OF_NON_DLL;
            case ERROR_INVALID_LEVEL:
                return STATUS_INVALID_LEVEL;
            case ERROR_INVALID_LOGON_HOURS:
                return STATUS_INVALID_LOGON_HOURS;
            case ERROR_INVALID_LOGON_TYPE:
                return STATUS_INVALID_LOGON_TYPE;
            case ERROR_INVALID_MEMBER:
                return STATUS_INVALID_MEMBER;
            case ERROR_INVALID_NAME:
                return STATUS_OBJECT_NAME_INVALID;
            case ERROR_INVALID_NETNAME:
                return STATUS_INVALID_ADDRESS_COMPONENT;
            case ERROR_INVALID_OPLOCK_PROTOCOL:
                return STATUS_INVALID_OPLOCK_PROTOCOL;
            case ERROR_INVALID_ORDINAL:
                return STATUS_ORDINAL_NOT_FOUND;
            case ERROR_INVALID_OWNER:
                return STATUS_INVALID_OWNER;
            case ERROR_INVALID_PARAMETER:
                return STATUS_INVALID_PARAMETER;
            case ERROR_INVALID_PASSWORD:
                return STATUS_WRONG_PASSWORD;
            case ERROR_INVALID_PRIMARY_GROUP:
                return STATUS_INVALID_PRIMARY_GROUP;
            case ERROR_INVALID_REPARSE_DATA:
                return STATUS_IO_REPARSE_DATA_INVALID;
            case ERROR_INVALID_SECURITY_DESCR:
                return STATUS_INVALID_SECURITY_DESCR;
            case ERROR_INVALID_SERVER_STATE:
                return STATUS_INVALID_SERVER_STATE;
            case ERROR_INVALID_SID:
                return STATUS_INVALID_SID;
            case ERROR_INVALID_SUB_AUTHORITY:
                return STATUS_INVALID_SUB_AUTHORITY;
            case ERROR_INVALID_THREAD_ID:
                return STATUS_NO_LDT;
            case ERROR_INVALID_USER_BUFFER:
                return STATUS_INVALID_USER_BUFFER;
            case ERROR_INVALID_WORKSTATION:
                return STATUS_INVALID_WORKSTATION;
            case ERROR_IO_DEVICE:
                return STATUS_IO_DEVICE_ERROR;
            case ERROR_IO_PENDING:
                return STATUS_PENDING;
            case ERROR_IRQ_BUSY:
                return STATUS_SHARED_IRQ_BUSY;
            case ERROR_JOURNAL_DELETE_IN_PROGRESS:
                return STATUS_JOURNAL_DELETE_IN_PROGRESS;
            case ERROR_JOURNAL_ENTRY_DELETED:
                return STATUS_JOURNAL_ENTRY_DELETED;
            case ERROR_JOURNAL_NOT_ACTIVE:
                return STATUS_JOURNAL_NOT_ACTIVE;
            case ERROR_KEY_DELETED:
                return STATUS_KEY_DELETED;
            case ERROR_KEY_HAS_CHILDREN:
                return STATUS_KEY_HAS_CHILDREN;
            case ERROR_LABEL_TOO_LONG:
                return STATUS_INVALID_VOLUME_LABEL;
            case ERROR_LAST_ADMIN:
                return STATUS_LAST_ADMIN;
            case ERROR_LICENSE_QUOTA_EXCEEDED:
                return STATUS_LICENSE_QUOTA_EXCEEDED;
            case ERROR_LM_CROSS_ENCRYPTION_REQUIRED:
                return STATUS_LM_CROSS_ENCRYPTION_REQUIRED;
            case ERROR_LOCAL_USER_SESSION_KEY:
                return STATUS_LOCAL_USER_SESSION_KEY;
            case ERROR_LOCK_VIOLATION:
                return STATUS_LOCK_NOT_GRANTED;
            case ERROR_LOGIN_TIME_RESTRICTION:
                return STATUS_LOGIN_TIME_RESTRICTION;
            case ERROR_LOGIN_WKSTA_RESTRICTION:
                return STATUS_LOGIN_WKSTA_RESTRICTION;
            case ERROR_LOGON_FAILURE:
                return STATUS_LOGON_FAILURE;
            case ERROR_LOGON_NOT_GRANTED:
                return STATUS_LOGON_NOT_GRANTED;
            case ERROR_LOGON_SESSION_COLLISION:
                return STATUS_LOGON_SESSION_COLLISION;
            case ERROR_LOGON_SESSION_EXISTS:
                return STATUS_LOGON_SESSION_EXISTS;
            case ERROR_LOGON_TYPE_NOT_GRANTED:
                return STATUS_LOGON_TYPE_NOT_GRANTED;
            case ERROR_LOG_FILE_FULL:
                return STATUS_LOG_FILE_FULL;
            case ERROR_LUIDS_EXHAUSTED:
                return STATUS_LUIDS_EXHAUSTED;
            case ERROR_MAGAZINE_NOT_PRESENT:
                return STATUS_MAGAZINE_NOT_PRESENT;
            case ERROR_MAPPED_ALIGNMENT:
                return STATUS_MAPPED_ALIGNMENT;
            case ERROR_MEDIA_CHANGED:
                return STATUS_MEDIA_CHANGED;
            case ERROR_MEMBERS_PRIMARY_GROUP:
                return STATUS_MEMBERS_PRIMARY_GROUP;
            case ERROR_MEMBER_IN_ALIAS:
                return STATUS_MEMBER_IN_ALIAS;
            case ERROR_MEMBER_IN_GROUP:
                return STATUS_MEMBER_IN_GROUP;
            case ERROR_MEMBER_NOT_IN_ALIAS:
                return STATUS_MEMBER_NOT_IN_ALIAS;
            case ERROR_MEMBER_NOT_IN_GROUP:
                return STATUS_MEMBER_NOT_IN_GROUP;
            case ERROR_MOD_NOT_FOUND:
                return STATUS_DLL_NOT_FOUND;
            case ERROR_MORE_DATA:
                return STATUS_BUFFER_OVERFLOW;
            case ERROR_MORE_WRITES:
                return STATUS_SERIAL_MORE_WRITES;
            case ERROR_MR_MID_NOT_FOUND:
                return STATUS_MESSAGE_NOT_FOUND;
            case ERROR_MUTUAL_AUTH_FAILED:
                return STATUS_MUTUAL_AUTHENTICATION_FAILED;
            case ERROR_NETLOGON_NOT_STARTED:
                return STATUS_NETLOGON_NOT_STARTED;
            case ERROR_NETNAME_DELETED:
                return STATUS_NETWORK_NAME_DELETED;
            case ERROR_NETWORK_ACCESS_DENIED:
                return STATUS_NETWORK_ACCESS_DENIED;
            case ERROR_NETWORK_BUSY:
                return STATUS_NETWORK_BUSY;
            case ERROR_NETWORK_UNREACHABLE:
                return STATUS_NETWORK_UNREACHABLE;
            case ERROR_NET_WRITE_FAULT:
                return STATUS_NET_WRITE_FAULT;
            case ERROR_NOACCESS:
                return STATUS_ACCESS_VIOLATION;
            case ERROR_NOLOGON_INTERDOMAIN_TRUST_ACCOUNT:
                return STATUS_NOLOGON_INTERDOMAIN_TRUST_ACCOUNT;
            case ERROR_NOLOGON_SERVER_TRUST_ACCOUNT:
                return STATUS_NOLOGON_SERVER_TRUST_ACCOUNT;
            case ERROR_NOLOGON_WORKSTATION_TRUST_ACCOUNT:
                return STATUS_NOLOGON_WORKSTATION_TRUST_ACCOUNT;
            case ERROR_NONE_MAPPED:
                return STATUS_NONE_MAPPED;
            case ERROR_NOTIFY_ENUM_DIR:
                return STATUS_NOTIFY_ENUM_DIR;
            case ERROR_NOT_ALL_ASSIGNED:
                return STATUS_NOT_ALL_ASSIGNED;
            case ERROR_NOT_A_REPARSE_POINT:
                return STATUS_NOT_A_REPARSE_POINT;
            case ERROR_NOT_ENOUGH_MEMORY:
                return STATUS_NO_MEMORY;
            case ERROR_NOT_ENOUGH_QUOTA:
                return STATUS_QUOTA_EXCEEDED;
            case ERROR_NOT_ENOUGH_SERVER_MEMORY:
                return STATUS_INSUFF_SERVER_RESOURCES;
            case ERROR_NOT_EXPORT_FORMAT:
                return STATUS_NOT_EXPORT_FORMAT;
            case ERROR_NOT_FOUND:
                return STATUS_NOT_FOUND;
            case ERROR_NOT_LOCKED:
                return STATUS_NOT_LOCKED;
            case ERROR_NOT_LOGON_PROCESS:
                return STATUS_NOT_LOGON_PROCESS;
            case ERROR_NOT_OWNER:
                return STATUS_RESOURCE_NOT_OWNED;
            case ERROR_NOT_READY:
                return STATUS_DEVICE_NOT_READY;
            case ERROR_NOT_REGISTRY_FILE:
                return STATUS_NOT_REGISTRY_FILE;
            case ERROR_NOT_SAME_DEVICE:
                return STATUS_NOT_SAME_DEVICE;
            case ERROR_NOT_SUPPORTED:
                return STATUS_NOT_SUPPORTED;
            case ERROR_NOT_SUPPORTED_ON_SBS:
                return STATUS_NOT_SUPPORTED_ON_SBS;
            case ERROR_NO_BROWSER_SERVERS_FOUND:
                return STATUS_NO_BROWSER_SERVERS_FOUND;
            case ERROR_NO_DATA:
                return STATUS_PIPE_EMPTY;
            case ERROR_NO_DATA_DETECTED:
                return STATUS_NO_DATA_DETECTED;
            case ERROR_NO_IMPERSONATION_TOKEN:
                return STATUS_NO_IMPERSONATION_TOKEN;
            case ERROR_NO_INHERITANCE:
                return STATUS_NO_INHERITANCE;
            case ERROR_NO_LOGON_SERVERS:
                return STATUS_NO_LOGON_SERVERS;
            case ERROR_NO_LOG_SPACE:
                return STATUS_NO_LOG_SPACE;
            case ERROR_NO_MATCH:
                return STATUS_NO_MATCH;
            case ERROR_NO_MEDIA_IN_DRIVE:
                return STATUS_NO_MEDIA;
            case ERROR_NO_MORE_FILES:
                return STATUS_NO_MORE_FILES;
            case ERROR_NO_MORE_ITEMS:
                return STATUS_NO_MORE_ENTRIES;
            case ERROR_NO_QUOTAS_FOR_ACCOUNT:
                return STATUS_NO_QUOTAS_FOR_ACCOUNT;
            case ERROR_NO_SECURITY_ON_OBJECT:
                return STATUS_NO_SECURITY_ON_OBJECT;
            case ERROR_NO_SPOOL_SPACE:
                return STATUS_NO_SPOOL_SPACE;
            case ERROR_NO_SUCH_ALIAS:
                return STATUS_NO_SUCH_ALIAS;
            case ERROR_NO_SUCH_DOMAIN:
                return STATUS_NO_SUCH_DOMAIN;
            case ERROR_NO_SUCH_GROUP:
                return STATUS_NO_SUCH_GROUP;
            case ERROR_NO_SUCH_LOGON_SESSION:
                return STATUS_NO_SUCH_LOGON_SESSION;
            case ERROR_NO_SUCH_MEMBER:
                return STATUS_NO_SUCH_MEMBER;
            case ERROR_NO_SUCH_PACKAGE:
                return STATUS_NO_SUCH_PACKAGE;
            case ERROR_NO_SUCH_PRIVILEGE:
                return STATUS_NO_SUCH_PRIVILEGE;
            case ERROR_NO_SUCH_USER:
                return STATUS_NO_SUCH_USER;
            case ERROR_NO_SYSTEM_RESOURCES:
                return STATUS_INSUFFICIENT_RESOURCES;
            case ERROR_NO_TOKEN:
                return STATUS_NO_TOKEN;
            case ERROR_NO_TRACKING_SERVICE:
                return STATUS_NO_TRACKING_SERVICE;
            case ERROR_NO_TRUST_LSA_SECRET:
                return STATUS_NO_TRUST_LSA_SECRET;
            case ERROR_NO_TRUST_SAM_ACCOUNT:
                return STATUS_NO_TRUST_SAM_ACCOUNT;
            case ERROR_NO_UNICODE_TRANSLATION:
                return STATUS_UNMAPPABLE_CHARACTER;
            case ERROR_NO_USER_SESSION_KEY:
                return STATUS_NO_USER_SESSION_KEY;
            case ERROR_NT_CROSS_ENCRYPTION_REQUIRED:
                return STATUS_NT_CROSS_ENCRYPTION_REQUIRED;
            case ERROR_NULL_LM_PASSWORD:
                return STATUS_NULL_LM_PASSWORD;
            case ERROR_ONLY_IF_CONNECTED:
                return STATUS_ONLY_IF_CONNECTED;
            case ERROR_OPEN_FILES:
                return STATUS_FILES_OPEN;
            case ERROR_OPERATION_ABORTED:
                return STATUS_CANCELLED;
            case ERROR_OPLOCK_NOT_GRANTED:
                return STATUS_OPLOCK_NOT_GRANTED;
            case ERROR_OUTOFMEMORY:
                return STATUS_SECTION_NOT_EXTENDED;
            case ERROR_OUT_OF_PAPER:
                return STATUS_DEVICE_PAPER_EMPTY;
            case ERROR_PAGEFILE_QUOTA:
                return STATUS_PAGEFILE_QUOTA;
            case ERROR_PARTIAL_COPY:
                return STATUS_PARTIAL_COPY;
            case ERROR_PARTITION_FAILURE:
                return STATUS_PARTITION_FAILURE;
            case ERROR_PASSWORD_EXPIRED:
                return STATUS_PASSWORD_EXPIRED;
            case ERROR_PASSWORD_MUST_CHANGE:
                return STATUS_PASSWORD_MUST_CHANGE;
            case ERROR_PASSWORD_RESTRICTION:
                return STATUS_PASSWORD_RESTRICTION;
            case ERROR_PATH_NOT_FOUND:
                return STATUS_OBJECT_PATH_NOT_FOUND;
            case ERROR_PIPE_BUSY:
                return STATUS_PIPE_BUSY;
            case ERROR_PIPE_CONNECTED:
                return STATUS_PIPE_CONNECTED;
            case ERROR_PIPE_LISTENING:
                return STATUS_PIPE_LISTENING;
            case ERROR_PIPE_NOT_CONNECTED:
                return STATUS_PIPE_DISCONNECTED;
            case ERROR_PKINIT_FAILURE:
                return STATUS_PKINIT_FAILURE;
            case ERROR_POLICY_OBJECT_NOT_FOUND:
                return STATUS_POLICY_OBJECT_NOT_FOUND;
            case ERROR_POLICY_ONLY_IN_DS:
                return STATUS_POLICY_ONLY_IN_DS;
            case ERROR_PORT_UNREACHABLE:
                return STATUS_PORT_UNREACHABLE;
            case ERROR_POSSIBLE_DEADLOCK:
                return STATUS_POSSIBLE_DEADLOCK;
            case ERROR_PRINTQ_FULL:
                return STATUS_PRINT_QUEUE_FULL;
            case ERROR_PRINT_CANCELLED:
                return STATUS_PRINT_CANCELLED;
            case ERROR_PRIVILEGE_NOT_HELD:
                return STATUS_PRIVILEGE_NOT_HELD;
            case ERROR_PROC_NOT_FOUND:
                return STATUS_PROCEDURE_NOT_FOUND;
            case ERROR_PROTOCOL_UNREACHABLE:
                return STATUS_PROTOCOL_UNREACHABLE;
            case ERROR_REDIR_PAUSED:
                return STATUS_REDIRECTOR_PAUSED;
            case ERROR_REGISTRY_IO_FAILED:
                return STATUS_REGISTRY_IO_FAILED;
            case ERROR_REGISTRY_RECOVERED:
                return STATUS_REGISTRY_RECOVERED;
            case ERROR_REG_NAT_CONSUMPTION:
                return STATUS_REG_NAT_CONSUMPTION;
            case ERROR_REMOTE_SESSION_LIMIT_EXCEEDED:
                return STATUS_REMOTE_SESSION_LIMIT;
            case ERROR_REMOTE_STORAGE_MEDIA_ERROR:
                return STATUS_REMOTE_STORAGE_MEDIA_ERROR;
            case ERROR_REMOTE_STORAGE_NOT_ACTIVE:
                return STATUS_REMOTE_STORAGE_NOT_ACTIVE;
            case ERROR_REM_NOT_LIST:
                return STATUS_REMOTE_NOT_LISTENING;
            case ERROR_REPARSE_ATTRIBUTE_CONFLICT:
                return STATUS_REPARSE_ATTRIBUTE_CONFLICT;
            case ERROR_REPARSE_TAG_INVALID:
                return STATUS_IO_REPARSE_TAG_INVALID;
            case ERROR_REPARSE_TAG_MISMATCH:
                return STATUS_IO_REPARSE_TAG_MISMATCH;
            case ERROR_REQUEST_ABORTED:
                return STATUS_REQUEST_ABORTED;
            case ERROR_REQ_NOT_ACCEP:
                return STATUS_REQUEST_NOT_ACCEPTED;
            case ERROR_RESOURCE_DATA_NOT_FOUND:
                return STATUS_RESOURCE_DATA_NOT_FOUND;
            case ERROR_RESOURCE_LANG_NOT_FOUND:
                return STATUS_RESOURCE_LANG_NOT_FOUND;
            case ERROR_RESOURCE_NAME_NOT_FOUND:
                return STATUS_RESOURCE_NAME_NOT_FOUND;
            case ERROR_RESOURCE_TYPE_NOT_FOUND:
                return STATUS_RESOURCE_TYPE_NOT_FOUND;
            case ERROR_RETRY:
                return STATUS_RETRY;
            case ERROR_REVISION_MISMATCH:
                return STATUS_REVISION_MISMATCH;
            case ERROR_RXACT_COMMIT_FAILURE:
                return STATUS_RXACT_COMMIT_FAILURE;
            case ERROR_RXACT_INVALID_STATE:
                return STATUS_RXACT_INVALID_STATE;
            case ERROR_SAM_INIT_FAILURE:
                return STATUS_SAM_INIT_FAILURE;
            case ERROR_SECRET_TOO_LONG:
                return STATUS_SECRET_TOO_LONG;
            case ERROR_SECTOR_NOT_FOUND:
                return STATUS_NONEXISTENT_SECTOR;
            case ERROR_SEM_TIMEOUT:
                return STATUS_IO_TIMEOUT;
            case ERROR_SERIAL_NO_DEVICE:
                return STATUS_SERIAL_NO_DEVICE_INITED;
            case ERROR_SERVER_DISABLED:
                return STATUS_SERVER_DISABLED;
            case ERROR_SERVER_NOT_DISABLED:
                return STATUS_SERVER_NOT_DISABLED;
            case ERROR_SERVER_SHUTDOWN_IN_PROGRESS:
                return STATUS_SERVER_SHUTDOWN_IN_PROGRESS;
            case ERROR_SERVICE_ALREADY_RUNNING:
                return STATUS_IMAGE_ALREADY_LOADED;
            case ERROR_SERVICE_DISABLED:
                return STATUS_PLUGPLAY_NO_DEVICE;
            case ERROR_SESSION_CREDENTIAL_CONFLICT:
                return STATUS_NETWORK_CREDENTIAL_CONFLICT;
            case ERROR_SETMARK_DETECTED:
                return STATUS_SETMARK_DETECTED;
            case ERROR_SET_NOT_FOUND:
                return STATUS_PROPSET_NOT_FOUND;
            case ERROR_SHARED_POLICY:
                return STATUS_SHARED_POLICY;
            case ERROR_SHARING_PAUSED:
                return STATUS_SHARING_PAUSED;
            case ERROR_SHARING_VIOLATION:
                return STATUS_SHARING_VIOLATION;
            case ERROR_SHUTDOWN_IN_PROGRESS:
                return STATUS_SHUTDOWN_IN_PROGRESS;
            case ERROR_SIGNAL_REFUSED:
                return STATUS_SUSPEND_COUNT_EXCEEDED;
            case ERROR_SMARTCARD_SUBSYSTEM_FAILURE:
                return STATUS_SMARTCARD_SUBSYSTEM_FAILURE;
            case ERROR_SOME_NOT_MAPPED:
                return STATUS_SOME_NOT_MAPPED;
            case ERROR_SOURCE_ELEMENT_EMPTY:
                return STATUS_SOURCE_ELEMENT_EMPTY;
            case ERROR_SPECIAL_ACCOUNT:
                return STATUS_SPECIAL_ACCOUNT;
            case ERROR_SPECIAL_GROUP:
                return STATUS_SPECIAL_GROUP;
            case ERROR_SPECIAL_USER:
                return STATUS_SPECIAL_USER;
            case ERROR_STACK_OVERFLOW:
                return STATUS_STACK_OVERFLOW;
            case ERROR_SWAPERROR:
                return STATUS_IN_PAGE_ERROR;
            case ERROR_SXS_ACTIVATION_CONTEXT_DISABLED:
                return STATUS_SXS_ACTIVATION_CONTEXT_DISABLED;
            case ERROR_SXS_ASSEMBLY_NOT_FOUND:
                return STATUS_SXS_ASSEMBLY_NOT_FOUND;
            case ERROR_SXS_CANT_GEN_ACTCTX:
                return STATUS_SXS_CANT_GEN_ACTCTX;
            case ERROR_SXS_INVALID_ACTCTXDATA_FORMAT:
                return STATUS_SXS_INVALID_ACTCTXDATA_FORMAT;
            case ERROR_SXS_KEY_NOT_FOUND:
                return STATUS_SXS_KEY_NOT_FOUND;
            case ERROR_SXS_MANIFEST_FORMAT_ERROR:
                return STATUS_SXS_MANIFEST_FORMAT_ERROR;
            case ERROR_SXS_MANIFEST_PARSE_ERROR:
                return STATUS_SXS_MANIFEST_PARSE_ERROR;
            case ERROR_SXS_PROCESS_DEFAULT_ALREADY_SET:
                return STATUS_SXS_PROCESS_DEFAULT_ALREADY_SET;
            case ERROR_SXS_SECTION_NOT_FOUND:
                return STATUS_SXS_SECTION_NOT_FOUND;
            case ERROR_SXS_THREAD_QUERIES_DISABLED:
                return STATUS_SXS_THREAD_QUERIES_DISABLED;
            case ERROR_SXS_WRONG_SECTION_TYPE:
                return STATUS_SXS_WRONG_SECTION_TYPE;
            case ERROR_TIME_SKEW:
                return STATUS_TIME_DIFFERENCE_AT_DC;
            case ERROR_TOKEN_ALREADY_IN_USE:
                return STATUS_TOKEN_ALREADY_IN_USE;
            case ERROR_TOO_MANY_CMDS:
                return STATUS_TOO_MANY_COMMANDS;
            case ERROR_TOO_MANY_CONTEXT_IDS:
                return STATUS_TOO_MANY_CONTEXT_IDS;
            case ERROR_TOO_MANY_LINKS:
                return STATUS_TOO_MANY_LINKS;
            case ERROR_TOO_MANY_LUIDS_REQUESTED:
                return STATUS_TOO_MANY_LUIDS_REQUESTED;
            case ERROR_TOO_MANY_NAMES:
                return STATUS_TOO_MANY_NAMES;
            case ERROR_TOO_MANY_OPEN_FILES:
                return STATUS_TOO_MANY_OPENED_FILES;
            case ERROR_TOO_MANY_POSTS:
                return STATUS_SEMAPHORE_LIMIT_EXCEEDED;
            case ERROR_TOO_MANY_SECRETS:
                return STATUS_TOO_MANY_SECRETS;
            case ERROR_TOO_MANY_SESS:
                return STATUS_TOO_MANY_SESSIONS;
            case ERROR_TOO_MANY_SIDS:
                return STATUS_TOO_MANY_SIDS;
            case ERROR_TRANSPORT_FULL:
                return STATUS_TRANSPORT_FULL;
            case ERROR_TRUSTED_DOMAIN_FAILURE:
                return STATUS_TRUSTED_DOMAIN_FAILURE;
            case ERROR_TRUSTED_RELATIONSHIP_FAILURE:
                return STATUS_TRUSTED_RELATIONSHIP_FAILURE;
            case ERROR_TRUST_FAILURE:
                return STATUS_TRUST_FAILURE;
            case ERROR_UNABLE_TO_LOCK_MEDIA:
                return STATUS_UNABLE_TO_LOCK_MEDIA;
            case ERROR_UNABLE_TO_UNLOAD_MEDIA:
                return STATUS_UNABLE_TO_UNLOAD_MEDIA;
            case ERROR_UNEXP_NET_ERR:
                return STATUS_UNEXPECTED_NETWORK_ERROR;
            case ERROR_UNKNOWN_REVISION:
                return STATUS_UNKNOWN_REVISION;
            case ERROR_UNRECOGNIZED_MEDIA:
                return STATUS_UNRECOGNIZED_MEDIA;
            case ERROR_UNRECOGNIZED_VOLUME:
                return STATUS_UNRECOGNIZED_VOLUME;
            case ERROR_USER_EXISTS:
                return STATUS_USER_EXISTS;
            case ERROR_USER_MAPPED_FILE:
                return STATUS_USER_MAPPED_FILE;
            case ERROR_VC_DISCONNECTED:
                return STATUS_VIRTUAL_CIRCUIT_CLOSED;
            case ERROR_WMI_ALREADY_DISABLED:
                return STATUS_WMI_ALREADY_DISABLED;
            case ERROR_WMI_ALREADY_ENABLED:
                return STATUS_WMI_ALREADY_ENABLED;
            case ERROR_WMI_GUID_DISCONNECTED:
                return STATUS_WMI_GUID_DISCONNECTED;
            case ERROR_WMI_GUID_NOT_FOUND:
                return STATUS_WMI_GUID_NOT_FOUND;
            case ERROR_WMI_INSTANCE_NOT_FOUND:
                return STATUS_WMI_INSTANCE_NOT_FOUND;
            case ERROR_WMI_ITEMID_NOT_FOUND:
                return STATUS_WMI_ITEMID_NOT_FOUND;
            case ERROR_WMI_READ_ONLY:
                return STATUS_WMI_READ_ONLY;
            case ERROR_WMI_SET_FAILURE:
                return STATUS_WMI_SET_FAILURE;
            case ERROR_WMI_TRY_AGAIN:
                return STATUS_WMI_TRY_AGAIN;
            case ERROR_WORKING_SET_QUOTA:
                return STATUS_WORKING_SET_QUOTA;
            case ERROR_WRITE_PROTECT:
                return STATUS_MEDIA_WRITE_PROTECTED;
            case ERROR_WRONG_DISK:
                return STATUS_WRONG_VOLUME;
            case ERROR_WRONG_TARGET_NAME:
                return SEC_E_WRONG_PRINCIPAL;
            case NO_ERROR:
                return STATUS_SUCCESS;
            case NTE_BAD_KEYSET:
                return STATUS_SMARTCARD_NO_KEYSET;
            case NTE_NO_KEY:
                return STATUS_SMARTCARD_NO_KEY_CONTAINER;
            case RPC_S_ADDRESS_ERROR:
                return RPC_NT_ADDRESS_ERROR;
            case RPC_S_ALREADY_LISTENING:
                return RPC_NT_ALREADY_LISTENING;
            case RPC_S_ALREADY_REGISTERED:
                return RPC_NT_ALREADY_REGISTERED;
            case RPC_S_BINDING_HAS_NO_AUTH:
                return RPC_NT_BINDING_HAS_NO_AUTH;
            case RPC_S_BINDING_INCOMPLETE:
                return RPC_NT_BINDING_INCOMPLETE;
            case RPC_S_CALL_CANCELLED:
                return RPC_NT_CALL_CANCELLED;
            case RPC_S_CALL_FAILED:
                return RPC_NT_CALL_FAILED;
            case RPC_S_CALL_FAILED_DNE:
                return RPC_NT_CALL_FAILED_DNE;
            case RPC_S_CALL_IN_PROGRESS:
                return RPC_NT_CALL_IN_PROGRESS;
            case RPC_S_CANNOT_SUPPORT:
                return RPC_NT_CANNOT_SUPPORT;
            case RPC_S_CANT_CREATE_ENDPOINT:
                return RPC_NT_CANT_CREATE_ENDPOINT;
            case RPC_S_COMM_FAILURE:
                return RPC_NT_COMM_FAILURE;
            case RPC_S_DUPLICATE_ENDPOINT:
                return RPC_NT_DUPLICATE_ENDPOINT;
            case RPC_S_ENTRY_ALREADY_EXISTS:
                return RPC_NT_ENTRY_ALREADY_EXISTS;
            case RPC_S_ENTRY_NOT_FOUND:
                return RPC_NT_ENTRY_NOT_FOUND;
            case RPC_S_FP_DIV_ZERO:
                return RPC_NT_FP_DIV_ZERO;
            case RPC_S_FP_OVERFLOW:
                return RPC_NT_FP_OVERFLOW;
            case RPC_S_FP_UNDERFLOW:
                return RPC_NT_FP_UNDERFLOW;
            case RPC_S_GROUP_MEMBER_NOT_FOUND:
                return RPC_NT_GROUP_MEMBER_NOT_FOUND;
            case RPC_S_INCOMPLETE_NAME:
                return RPC_NT_INCOMPLETE_NAME;
            case RPC_S_INTERFACE_NOT_FOUND:
                return RPC_NT_INTERFACE_NOT_FOUND;
            case RPC_S_INTERNAL_ERROR:
                return RPC_NT_INTERNAL_ERROR;
            case RPC_S_INVALID_ASYNC_CALL:
                return RPC_NT_INVALID_ASYNC_CALL;
            case RPC_S_INVALID_ASYNC_HANDLE:
                return RPC_NT_INVALID_ASYNC_HANDLE;
            case RPC_S_INVALID_AUTH_IDENTITY:
                return RPC_NT_INVALID_AUTH_IDENTITY;
            case RPC_S_INVALID_BOUND:
                return RPC_NT_INVALID_BOUND;
            case RPC_S_INVALID_ENDPOINT_FORMAT:
                return RPC_NT_INVALID_ENDPOINT_FORMAT;
            case RPC_S_INVALID_NAF_ID:
                return RPC_NT_INVALID_NAF_ID;
            case RPC_S_INVALID_NAME_SYNTAX:
                return RPC_NT_INVALID_NAME_SYNTAX;
            case RPC_S_INVALID_NETWORK_OPTIONS:
                return RPC_NT_INVALID_NETWORK_OPTIONS;
            case RPC_S_INVALID_NET_ADDR:
                return RPC_NT_INVALID_NET_ADDR;
            case RPC_S_INVALID_OBJECT:
                return RPC_NT_INVALID_OBJECT;
            case RPC_S_INVALID_RPC_PROTSEQ:
                return RPC_NT_INVALID_RPC_PROTSEQ;
            case RPC_S_INVALID_STRING_BINDING:
                return RPC_NT_INVALID_STRING_BINDING;
            case RPC_S_INVALID_STRING_UUID:
                return RPC_NT_INVALID_STRING_UUID;
            case RPC_S_INVALID_TAG:
                return RPC_NT_INVALID_TAG;
            case RPC_S_INVALID_TIMEOUT:
                return RPC_NT_INVALID_TIMEOUT;
            case RPC_S_INVALID_VERS_OPTION:
                return RPC_NT_INVALID_VERS_OPTION;
            case RPC_S_MAX_CALLS_TOO_SMALL:
                return RPC_NT_MAX_CALLS_TOO_SMALL;
            case RPC_S_NAME_SERVICE_UNAVAILABLE:
                return RPC_NT_NAME_SERVICE_UNAVAILABLE;
            case RPC_S_NOTHING_TO_EXPORT:
                return RPC_NT_NOTHING_TO_EXPORT;
            case RPC_S_NOT_ALL_OBJS_UNEXPORTED:
                return RPC_NT_NOT_ALL_OBJS_UNEXPORTED;
            case RPC_S_NOT_CANCELLED:
                return RPC_NT_NOT_CANCELLED;
            case RPC_S_NOT_LISTENING:
                return RPC_NT_NOT_LISTENING;
            case RPC_S_NOT_RPC_ERROR:
                return RPC_NT_NOT_RPC_ERROR;
            case RPC_S_NO_BINDINGS:
                return RPC_NT_NO_BINDINGS;
            case RPC_S_NO_CALL_ACTIVE:
                return RPC_NT_NO_CALL_ACTIVE;
            case RPC_S_NO_CONTEXT_AVAILABLE:
                return RPC_NT_NO_CONTEXT_AVAILABLE;
            case RPC_S_NO_ENDPOINT_FOUND:
                return RPC_NT_NO_ENDPOINT_FOUND;
            case RPC_S_NO_ENTRY_NAME:
                return RPC_NT_NO_ENTRY_NAME;
            case RPC_S_NO_INTERFACES:
                return RPC_NT_NO_INTERFACES;
            case RPC_S_NO_MORE_BINDINGS:
                return RPC_NT_NO_MORE_BINDINGS;
            case RPC_S_NO_MORE_MEMBERS:
                return RPC_NT_NO_MORE_MEMBERS;
            case RPC_S_NO_PRINC_NAME:
                return RPC_NT_NO_PRINC_NAME;
            case RPC_S_NO_PROTSEQS:
                return RPC_NT_NO_PROTSEQS;
            case RPC_S_NO_PROTSEQS_REGISTERED:
                return RPC_NT_NO_PROTSEQS_REGISTERED;
            case RPC_S_OBJECT_NOT_FOUND:
                return RPC_NT_OBJECT_NOT_FOUND;
            case RPC_S_OUT_OF_RESOURCES:
                return RPC_NT_OUT_OF_RESOURCES;
            case RPC_S_PROCNUM_OUT_OF_RANGE:
                return RPC_NT_PROCNUM_OUT_OF_RANGE;
            case RPC_S_PROTOCOL_ERROR:
                return RPC_NT_PROTOCOL_ERROR;
            case RPC_S_PROTSEQ_NOT_FOUND:
                return RPC_NT_PROTSEQ_NOT_FOUND;
            case RPC_S_PROTSEQ_NOT_SUPPORTED:
                return RPC_NT_PROTSEQ_NOT_SUPPORTED;
            case RPC_S_SEC_PKG_ERROR:
                return RPC_NT_SEC_PKG_ERROR;
            case RPC_S_SEND_INCOMPLETE:
                return RPC_NT_SEND_INCOMPLETE;
            case RPC_S_SERVER_TOO_BUSY:
                return RPC_NT_SERVER_TOO_BUSY;
            case RPC_S_SERVER_UNAVAILABLE:
                return RPC_NT_SERVER_UNAVAILABLE;
            case RPC_S_STRING_TOO_LONG:
                return RPC_NT_STRING_TOO_LONG;
            case RPC_S_TYPE_ALREADY_REGISTERED:
                return RPC_NT_TYPE_ALREADY_REGISTERED;
            case RPC_S_UNKNOWN_AUTHN_LEVEL:
                return RPC_NT_UNKNOWN_AUTHN_LEVEL;
            case RPC_S_UNKNOWN_AUTHN_SERVICE:
                return RPC_NT_UNKNOWN_AUTHN_SERVICE;
            case RPC_S_UNKNOWN_AUTHN_TYPE:
                return RPC_NT_UNKNOWN_AUTHN_TYPE;
            case RPC_S_UNKNOWN_AUTHZ_SERVICE:
                return RPC_NT_UNKNOWN_AUTHZ_SERVICE;
            case RPC_S_UNKNOWN_IF:
                return RPC_NT_UNKNOWN_IF;
            case RPC_S_UNKNOWN_MGR_TYPE:
                return RPC_NT_UNKNOWN_MGR_TYPE;
            case RPC_S_UNSUPPORTED_AUTHN_LEVEL:
                return RPC_NT_UNSUPPORTED_AUTHN_LEVEL;
            case RPC_S_UNSUPPORTED_NAME_SYNTAX:
                return RPC_NT_UNSUPPORTED_NAME_SYNTAX;
            case RPC_S_UNSUPPORTED_TRANS_SYN:
                return RPC_NT_UNSUPPORTED_TRANS_SYN;
            case RPC_S_UNSUPPORTED_TYPE:
                return RPC_NT_UNSUPPORTED_TYPE;
            case RPC_S_UUID_LOCAL_ONLY:
                return RPC_NT_UUID_LOCAL_ONLY;
            case RPC_S_UUID_NO_ADDRESS:
                return RPC_NT_UUID_NO_ADDRESS;
            case RPC_S_WRONG_KIND_OF_BINDING:
                return RPC_NT_WRONG_KIND_OF_BINDING;
            case RPC_S_ZERO_DIVIDE:
                return RPC_NT_ZERO_DIVIDE;
            case RPC_X_BAD_STUB_DATA:
                return RPC_NT_BAD_STUB_DATA;
            case RPC_X_BYTE_COUNT_TOO_SMALL:
                return RPC_NT_BYTE_COUNT_TOO_SMALL;
            case RPC_X_ENUM_VALUE_OUT_OF_RANGE:
                return RPC_NT_ENUM_VALUE_OUT_OF_RANGE;
            case RPC_X_INVALID_ES_ACTION:
                return RPC_NT_INVALID_ES_ACTION;
            case RPC_X_INVALID_PIPE_OBJECT:
                return RPC_NT_INVALID_PIPE_OBJECT;
            //case RPC_X_INVALID_PIPE_OPERATION:
            //return RPC_NT_INVALID_PIPE_OPERATION;
            case RPC_X_NO_MORE_ENTRIES:
                return RPC_NT_NO_MORE_ENTRIES;
            case RPC_X_NULL_REF_POINTER:
                return RPC_NT_NULL_REF_POINTER;
            case RPC_X_PIPE_CLOSED:
                return RPC_NT_PIPE_CLOSED;
            case RPC_X_PIPE_DISCIPLINE_ERROR:
                return RPC_NT_PIPE_DISCIPLINE_ERROR;
            case RPC_X_PIPE_EMPTY:
                return RPC_NT_PIPE_EMPTY;
            case RPC_X_SS_CANNOT_GET_CALL_HANDLE:
                return RPC_NT_SS_CANNOT_GET_CALL_HANDLE;
            case RPC_X_SS_CHAR_TRANS_OPEN_FAIL:
                return RPC_NT_SS_CHAR_TRANS_OPEN_FAIL;
            case RPC_X_SS_CHAR_TRANS_SHORT_FILE:
                return RPC_NT_SS_CHAR_TRANS_SHORT_FILE;
            case RPC_X_SS_CONTEXT_DAMAGED:
                return RPC_NT_SS_CONTEXT_DAMAGED;
            case RPC_X_SS_HANDLES_MISMATCH:
                return RPC_NT_SS_HANDLES_MISMATCH;
            case RPC_X_WRONG_ES_VERSION:
                return RPC_NT_WRONG_ES_VERSION;
            case RPC_X_WRONG_PIPE_VERSION:
                return RPC_NT_WRONG_PIPE_VERSION;
            case RPC_X_WRONG_STUB_VERSION:
                return RPC_NT_WRONG_STUB_VERSION;
            case SCARD_E_COMM_DATA_LOST:
                return STATUS_SMARTCARD_IO_ERROR;
            case SCARD_E_NO_SMARTCARD:
                return STATUS_SMARTCARD_NO_CARD;
            case SCARD_E_NO_SUCH_CERTIFICATE:
                return STATUS_SMARTCARD_NO_CERTIFICATE;
            case SCARD_W_CARD_NOT_AUTHENTICATED:
                return STATUS_SMARTCARD_CARD_NOT_AUTHENTICATED;
            case SCARD_W_CHV_BLOCKED:
                return STATUS_SMARTCARD_CARD_BLOCKED;
            case SCARD_W_WRONG_CHV:
                return STATUS_SMARTCARD_WRONG_PIN;
            case SEC_E_BAD_BINDINGS:
                return STATUS_BAD_BINDINGS;
            case SEC_E_CRYPTO_SYSTEM_INVALID:
                return STATUS_CRYPTO_SYSTEM_INVALID;
            case SEC_E_ISSUING_CA_UNTRUSTED:
                return STATUS_ISSUING_CA_UNTRUSTED;
            case SEC_E_KDC_INVALID_REQUEST:
                return STATUS_KDC_INVALID_REQUEST;
            case SEC_E_KDC_UNABLE_TO_REFER:
                return STATUS_KDC_UNABLE_TO_REFER;
            case SEC_E_KDC_UNKNOWN_ETYPE:
                return STATUS_KDC_UNKNOWN_ETYPE;
            case SEC_E_MAX_REFERRALS_EXCEEDED:
                return STATUS_MAX_REFERRALS_EXCEEDED;
            case SEC_E_MUST_BE_KDC:
                return STATUS_MUST_BE_KDC;
            case SEC_E_NO_IP_ADDRESSES:
                return STATUS_NO_IP_ADDRESSES;
            case SEC_E_NO_KERB_KEY:
                return STATUS_NO_KERB_KEY;
            case SEC_E_NO_PA_DATA:
                return STATUS_NO_PA_DATA;
            case SEC_E_NO_TGT_REPLY:
                return STATUS_NO_TGT_REPLY;
            case SEC_E_PKINIT_CLIENT_FAILURE:
                return STATUS_PKINIT_CLIENT_FAILURE;
            case SEC_E_PKINIT_NAME_MISMATCH:
                return STATUS_PKINIT_NAME_MISMATCH;
            case SEC_E_REVOCATION_OFFLINE_C:
                return STATUS_REVOCATION_OFFLINE_C;
            case SEC_E_SMARTCARD_CERT_EXPIRED:
                return STATUS_SMARTCARD_CERT_EXPIRED;
            case SEC_E_SMARTCARD_CERT_REVOKED:
                return STATUS_SMARTCARD_CERT_REVOKED;
            case SEC_E_SMARTCARD_LOGON_REQUIRED:
                return STATUS_SMARTCARD_LOGON_REQUIRED;
            case SEC_E_STRONG_CRYPTO_NOT_SUPPORTED:
                return STATUS_STRONG_CRYPTO_NOT_SUPPORTED;
            case SEC_E_TOO_MANY_PRINCIPALS:
                return STATUS_TOO_MANY_PRINCIPALS;
            case SEC_E_UNFINISHED_CONTEXT_DELETED:
                return STATUS_UNFINISHED_CONTEXT_DELETED;
            case SEC_E_UNSUPPORTED_PREAUTH:
                return STATUS_UNSUPPORTED_PREAUTH;
            case SEC_E_WRONG_CREDENTIAL_HANDLE:
                return STATUS_WRONG_CREDENTIAL_HANDLE;
            case STATUS_DUPLICATE_OBJECTID:
                return STATUS_DUPLICATE_OBJECTID;
            case STATUS_OBJECTID_EXISTS:
                return STATUS_OBJECTID_EXISTS;
            case STG_E_CSS_AUTHENTICATION_FAILURE:
                return STATUS_CSS_AUTHENTICATION_FAILURE;
            case STG_E_CSS_KEY_NOT_ESTABLISHED:
                return STATUS_CSS_KEY_NOT_ESTABLISHED;
            case STG_E_CSS_KEY_NOT_PRESENT:
                return STATUS_CSS_KEY_NOT_PRESENT;
            case STG_E_CSS_REGION_MISMATCH:
                return STATUS_CSS_REGION_MISMATCH;
            case STG_E_CSS_SCRAMBLED_SECTOR:
                return STATUS_CSS_SCRAMBLED_SECTOR;
            case STG_E_RESETS_EXHAUSTED:
                return STATUS_CSS_RESETS_EXHAUSTED;
            case STG_E_STATUS_COPY_PROTECTION_FAILURE:
                return STATUS_COPY_PROTECTION_FAILURE;
            case ERROR_INVALID_DATA:
                return STATUS_FILE_IS_A_DIRECTORY;
            default:
                return STATUS_UNSUCCESSFUL;
        }
    }

}
