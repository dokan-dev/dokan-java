package com.dokan.java.constants.microsoft;


import com.dokan.java.FileSystemInformation;

/**
 * Win32 Error Codes
 * <p>
 * These should be returned instead of {@link NtStatuses} values when in the {@link com.dokan.java.AbstractDokanyFileSystem#AbstractDokanyFileSystem(FileSystemInformation, boolean)} the `useKernelFlagsAndCodes` is set to
 * false.
 * </p>
 *
 * @see <a href="https://msdn.microsoft.com/en-us/library/cc231199.aspx">Microsoft developer documentation</a>
 */
public final class Win32ErrorCodes {

    /**
     * The operation completed successfully.
     */
    public static final int ERROR_SUCCESS = 0X00000000;

    /**
     * The operation completed successfully.
     */
    public static final int NERR_Success = 0X00000000;

    /**
     * Incorrect function.
     */
    public static final int ERROR_INVALID_FUNCTION = 0X00000001;

    /**
     * The system cannot find the file specified.
     */
    public static final int ERROR_FILE_NOT_FOUND = 0X00000002;

    /**
     * The system cannot find the path specified.
     */
    public static final int ERROR_PATH_NOT_FOUND = 0X00000003;

    /**
     * The system cannot open the file.
     */
    public static final int ERROR_TOO_MANY_OPEN_FILES = 0X00000004;

    /**
     * Access is denied.
     */
    public static final int ERROR_ACCESS_DENIED = 0X00000005;

    /**
     * The handle is invalid.
     */
    public static final int ERROR_INVALID_HANDLE = 0X00000006;

    /**
     * The storage control blocks were destroyed.
     */
    public static final int ERROR_ARENA_TRASHED = 0X00000007;

    /**
     * Not enough storage is available to process this   command.
     */
    public static final int ERROR_NOT_ENOUGH_MEMORY = 0X00000008;

    /**
     * The storage control block address is invalid.
     */
    public static final int ERROR_INVALID_BLOCK = 0X00000009;

    /**
     * The environment is incorrect.
     */
    public static final int ERROR_BAD_ENVIRONMENT = 0X0000000A;

    /**
     * An attempt was made to load a program with an   incorrect format.
     */
    public static final int ERROR_BAD_FORMAT = 0X0000000B;

    /**
     * The access code is invalid.
     */
    public static final int ERROR_INVALID_ACCESS = 0X0000000C;

    /**
     * The data is invalid.
     */
    public static final int ERROR_INVALID_DATA = 0X0000000D;

    /**
     * Not enough storage is available to complete this   operation.
     */
    public static final int ERROR_OUTOFMEMORY = 0X0000000E;

    /**
     * The system cannot find the drive specified.
     */
    public static final int ERROR_INVALID_DRIVE = 0X0000000F;

    /**
     * The directory cannot be removed.
     */
    public static final int ERROR_CURRENT_DIRECTORY = 0X00000010;

    /**
     * The system cannot move the file to a different disk   drive.
     */
    public static final int ERROR_NOT_SAME_DEVICE = 0X00000011;

    /**
     * There are no more files.
     */
    public static final int ERROR_NO_MORE_FILES = 0X00000012;

    /**
     * The media is write-protected.
     */
    public static final int ERROR_WRITE_PROTECT = 0X00000013;

    /**
     * The system cannot find the device specified.
     */
    public static final int ERROR_BAD_UNIT = 0X00000014;

    /**
     * The device is not ready.
     */
    public static final int ERROR_NOT_READY = 0X00000015;

    /**
     * The device does not recognize the command.
     */
    public static final int ERROR_BAD_COMMAND = 0X00000016;

    /**
     * Data error (cyclic redundancy check).
     */
    public static final int ERROR_CRC = 0X00000017;

    /**
     * The program issued a command but the command length is   incorrect.
     */
    public static final int ERROR_BAD_LENGTH = 0X00000018;

    /**
     * The drive cannot locate a specific area or track on   the disk.
     */
    public static final int ERROR_SEEK = 0X00000019;

    /**
     * The specified disk cannot be accessed.
     */
    public static final int ERROR_NOT_DOS_DISK = 0X0000001A;

    /**
     * The drive cannot find the sector requested.
     */
    public static final int ERROR_SECTOR_NOT_FOUND = 0X0000001B;

    /**
     * The printer is out of paper.
     */
    public static final int ERROR_OUT_OF_PAPER = 0X0000001C;

    /**
     * The system cannot write to the specified device.
     */
    public static final int ERROR_WRITE_FAULT = 0X0000001D;

    /**
     * The system cannot read from the specified device.
     */
    public static final int ERROR_READ_FAULT = 0X0000001E;

    /**
     * A device attached to the system is not functioning.
     */
    public static final int ERROR_GEN_FAILURE = 0X0000001F;

    /**
     * The process cannot access the file because it is being   used by another process.
     */
    public static final int ERROR_SHARING_VIOLATION = 0X00000020;

    /**
     * The process cannot access the file because another   process has locked a portion of the file.
     */
    public static final int ERROR_LOCK_VIOLATION = 0X00000021;

    /**
     * The wrong disk is in the drive. Insert %2 (Volume Serial   Number: %3) into drive %1.
     */
    public static final int ERROR_WRONG_DISK = 0X00000022;

    /**
     * Too many files opened for sharing.
     */
    public static final int ERROR_SHARING_BUFFER_EXCEEDED = 0X00000024;

    /**
     * Reached the end of the file.
     */
    public static final int ERROR_HANDLE_EOF = 0X00000026;

    /**
     * The disk is full.
     */
    public static final int ERROR_HANDLE_DISK_FULL = 0X00000027;

    /**
     * The request is not supported.
     */
    public static final int ERROR_NOT_SUPPORTED = 0X00000032;

    /**
     * Windows cannot find the network path. Verify that the   network path is correct and the destination computer is not busy or turned   off. If Windows still cannot find the network path, contact your network
     * administrator.
     */
    public static final int ERROR_REM_NOT_LIST = 0X00000033;

    /**
     * You were not connected because a duplicate name exists   on the network. Go to System in Control Panel to change the computer name,   and then try again.
     */
    public static final int ERROR_DUP_NAME = 0X00000034;

    /**
     * The network path was not found.
     */
    public static final int ERROR_BAD_NETPATH = 0X00000035;

    /**
     * The network is busy.
     */
    public static final int ERROR_NETWORK_BUSY = 0X00000036;

    /**
     * The specified network resource or device is no longer   available.
     */
    public static final int ERROR_DEV_NOT_EXIST = 0X00000037;

    /**
     * The network BIOS command limit has been reached.
     */
    public static final int ERROR_TOO_MANY_CMDS = 0X00000038;

    /**
     * A network adapter hardware error occurred.
     */
    public static final int ERROR_ADAP_HDW_ERR = 0X00000039;

    /**
     * The specified server cannot perform the requested   operation.
     */
    public static final int ERROR_BAD_NET_RESP = 0X0000003A;

    /**
     * An unexpected network error occurred.
     */
    public static final int ERROR_UNEXP_NET_ERR = 0X0000003B;

    /**
     * The remote adapter is not compatible.
     */
    public static final int ERROR_BAD_REM_ADAP = 0X0000003C;

    /**
     * The print queue is full.
     */
    public static final int ERROR_PRINTQ_FULL = 0X0000003D;

    /**
     * Space to store the file waiting to be printed is not   available on the server.
     */
    public static final int ERROR_NO_SPOOL_SPACE = 0X0000003E;

    /**
     * Your file waiting to be printed was deleted.
     */
    public static final int ERROR_PRINT_CANCELLED = 0X0000003F;

    /**
     * The specified network name is no longer available.
     */
    public static final int ERROR_NETNAME_DELETED = 0X00000040;

    /**
     * Network access is denied.
     */
    public static final int ERROR_NETWORK_ACCESS_DENIED = 0X00000041;

    /**
     * The network resource type is not correct.
     */
    public static final int ERROR_BAD_DEV_TYPE = 0X00000042;

    /**
     * The network name cannot be found.
     */
    public static final int ERROR_BAD_NET_NAME = 0X00000043;

    /**
     * The name limit for the local computer network adapter   card was exceeded.
     */
    public static final int ERROR_TOO_MANY_NAMES = 0X00000044;

    /**
     * The network BIOS session limit was exceeded.
     */
    public static final int ERROR_TOO_MANY_SESS = 0X00000045;

    /**
     * The remote server has been paused or is in the process   of being started.
     */
    public static final int ERROR_SHARING_PAUSED = 0X00000046;

    /**
     * No more connections can be made to this remote   computer at this time because the computer has accepted the maximum number of   connections.
     */
    public static final int ERROR_REQ_NOT_ACCEP = 0X00000047;

    /**
     * The specified printer or disk device has been paused.
     */
    public static final int ERROR_REDIR_PAUSED = 0X00000048;

    /**
     * The file exists.
     */
    public static final int ERROR_FILE_EXISTS = 0X00000050;

    /**
     * The directory or file cannot be created.
     */
    public static final int ERROR_CANNOT_MAKE = 0X00000052;

    /**
     * Fail on INT 24.
     */
    public static final int ERROR_FAIL_I24 = 0X00000053;

    /**
     * Storage to process this request is not available.
     */
    public static final int ERROR_OUT_OF_STRUCTURES = 0X00000054;

    /**
     * The local device name is already in use.
     */
    public static final int ERROR_ALREADY_ASSIGNED = 0X00000055;

    /**
     * The specified network password is not correct.
     */
    public static final int ERROR_INVALID_PASSWORD = 0X00000056;

    /**
     * The parameter is incorrect.
     */
    public static final int ERROR_INVALID_PARAMETER = 0X00000057;

    /**
     * A write fault occurred on the network.
     */
    public static final int ERROR_NET_WRITE_FAULT = 0X00000058;

    /**
     * The system cannot start another process at this time.
     */
    public static final int ERROR_NO_PROC_SLOTS = 0X00000059;

    /**
     * Cannot create another system semaphore.
     */
    public static final int ERROR_TOO_MANY_SEMAPHORES = 0X00000064;

    /**
     * The exclusive semaphore is owned by another process.
     */
    public static final int ERROR_EXCL_SEM_ALREADY_OWNED = 0X00000065;

    /**
     * The semaphore is set and cannot be closed.
     */
    public static final int ERROR_SEM_IS_SET = 0X00000066;

    /**
     * The semaphore cannot be set again.
     */
    public static final int ERROR_TOO_MANY_SEM_REQUESTS = 0X00000067;

    /**
     * Cannot request exclusive semaphores at interrupt time.
     */
    public static final int ERROR_INVALID_AT_INTERRUPT_TIME = 0X00000068;

    /**
     * The previous ownership of this semaphore has ended.
     */
    public static final int ERROR_SEM_OWNER_DIED = 0X00000069;

    /**
     * Insert the disk for drive %1.
     */
    public static final int ERROR_SEM_USER_LIMIT = 0X0000006A;

    /**
     * The program stopped because an alternate disk was not   inserted.
     */
    public static final int ERROR_DISK_CHANGE = 0X0000006B;

    /**
     * The disk is in use or locked by another process.
     */
    public static final int ERROR_DRIVE_LOCKED = 0X0000006C;

    /**
     * The pipe has been ended.
     */
    public static final int ERROR_BROKEN_PIPE = 0X0000006D;

    /**
     * The system cannot open the device or file specified.
     */
    public static final int ERROR_OPEN_FAILED = 0X0000006E;

    /**
     * The file name is too long.
     */
    public static final int ERROR_BUFFER_OVERFLOW = 0X0000006F;

    /**
     * There is not enough space on the disk.
     */
    public static final int ERROR_DISK_FULL = 0X00000070;

    /**
     * No more internal file identifiers are available.
     */
    public static final int ERROR_NO_MORE_SEARCH_HANDLES = 0X00000071;

    /**
     * The target internal file identifier is incorrect.
     */
    public static final int ERROR_INVALID_TARGET_HANDLE = 0X00000072;

    /**
     * The Input Output Control (IOCTL) call made by the application   program is not correct.
     */
    public static final int ERROR_INVALID_CATEGORY = 0X00000075;

    /**
     * The verify-on-write switch parameter value is not   correct.
     */
    public static final int ERROR_INVALID_VERIFY_SWITCH = 0X00000076;

    /**
     * The system does not support the command requested.
     */
    public static final int ERROR_BAD_DRIVER_LEVEL = 0X00000077;

    /**
     * This function is not supported on this system.
     */
    public static final int ERROR_CALL_NOT_IMPLEMENTED = 0X00000078;

    /**
     * The semaphore time-out period has expired.
     */
    public static final int ERROR_SEM_TIMEOUT = 0X00000079;

    /**
     * The data area passed to a system call is too small.
     */
    public static final int ERROR_INSUFFICIENT_BUFFER = 0X0000007A;

    /**
     * The file name, directory name, or volume label syntax   is incorrect.
     */
    public static final int ERROR_INVALID_NAME = 0X0000007B;

    /**
     * The system call level is not correct.
     */
    public static final int ERROR_INVALID_LEVEL = 0X0000007C;

    /**
     * The disk has no volume label.
     */
    public static final int ERROR_NO_VOLUME_LABEL = 0X0000007D;

    /**
     * The specified module could not be found.
     */
    public static final int ERROR_MOD_NOT_FOUND = 0X0000007E;

    /**
     * The specified procedure could not be found.
     */
    public static final int ERROR_PROC_NOT_FOUND = 0X0000007F;

    /**
     * There are no child processes to wait for.
     */
    public static final int ERROR_WAIT_NO_CHILDREN = 0X00000080;

    /**
     * The %1 application cannot be run in Win32 mode.
     */
    public static final int ERROR_CHILD_NOT_COMPLETE = 0X00000081;

    /**
     * Attempt to use a file handle to an open disk partition   for an operation other than raw disk I/O.
     */
    public static final int ERROR_DIRECT_ACCESS_HANDLE = 0X00000082;

    /**
     * An attempt was made to move the file pointer before   the beginning of the file.
     */
    public static final int ERROR_NEGATIVE_SEEK = 0X00000083;

    /**
     * The file pointer cannot be set on the specified device   or file.
     */
    public static final int ERROR_SEEK_ON_DEVICE = 0X00000084;

    /**
     * None
     */
    public static final int ERROR_IS_JOIN_TARGET = 0X00000085;

    /**
     * None
     */
    public static final int ERROR_IS_JOINED = 0X00000086;

    /**
     * None
     */
    public static final int ERROR_IS_SUBSTED = 0X00000087;

    /**
     * None
     */
    public static final int ERROR_NOT_JOINED = 0X00000088;

    /**
     * The system tried to delete the substitution of a drive   that is not substituted.
     */
    public static final int ERROR_NOT_SUBSTED = 0X00000089;

    /**
     * The system tried to join a drive to a directory on a   joined drive.
     */
    public static final int ERROR_JOIN_TO_JOIN = 0X0000008A;

    /**
     * The system tried to substitute a drive to a directory   on a substituted drive.
     */
    public static final int ERROR_SUBST_TO_SUBST = 0X0000008B;

    /**
     * The system tried to join a drive to a directory on a   substituted drive.
     */
    public static final int ERROR_JOIN_TO_SUBST = 0X0000008C;

    /**
     * None
     */
    public static final int ERROR_SUBST_TO_JOIN = 0X0000008D;

    /**
     * None
     */
    public static final int ERROR_BUSY_DRIVE = 0X0000008E;

    /**
     * The system cannot join or substitute a drive to or for   a directory on the same drive.
     */
    public static final int ERROR_SAME_DRIVE = 0X0000008F;

    /**
     * The directory is not a subdirectory of the root   directory.
     */
    public static final int ERROR_DIR_NOT_ROOT = 0X00000090;

    /**
     * The directory is not empty.
     */
    public static final int ERROR_DIR_NOT_EMPTY = 0X00000091;

    /**
     * The path specified is being used in a substitute.
     */
    public static final int ERROR_IS_SUBST_PATH = 0X00000092;

    /**
     * Not enough resources are available to process this   command.
     */
    public static final int ERROR_IS_JOIN_PATH = 0X00000093;

    /**
     * The path specified cannot be used at this time.
     */
    public static final int ERROR_PATH_BUSY = 0X00000094;

    /**
     * An attempt was made to join or substitute a drive for   which a directory on the drive is the target of a previous substitute.
     */
    public static final int ERROR_IS_SUBST_TARGET = 0X00000095;

    /**
     * System trace information was not specified in your   CONFIG.SYS file, or tracing is disallowed.
     */
    public static final int ERROR_SYSTEM_TRACE = 0X00000096;

    /**
     * The number of specified semaphore events for   DosMuxSemWait is not correct.
     */
    public static final int ERROR_INVALID_EVENT_COUNT = 0X00000097;

    /**
     * DosMuxSemWait did not execute; too many semaphores are   already set.
     */
    public static final int ERROR_TOO_MANY_MUXWAITERS = 0X00000098;

    /**
     * The DosMuxSemWait list is not correct.
     */
    public static final int ERROR_INVALID_LIST_FORMAT = 0X00000099;

    /**
     * The volume label you entered exceeds the label   character limit of the destination file system.
     */
    public static final int ERROR_LABEL_TOO_LONG = 0X0000009A;

    /**
     * Cannot create another thread.
     */
    public static final int ERROR_TOO_MANY_TCBS = 0X0000009B;

    /**
     * The recipient process has refused the signal.
     */
    public static final int ERROR_SIGNAL_REFUSED = 0X0000009C;

    /**
     * The segment is already discarded and cannot be locked.
     */
    public static final int ERROR_DISCARDED = 0X0000009D;

    /**
     * The segment is already unlocked.
     */
    public static final int ERROR_NOT_LOCKED = 0X0000009E;

    /**
     * The address for the thread ID is not correct.
     */
    public static final int ERROR_BAD_THREADID_ADDR = 0X0000009F;

    /**
     * One or more arguments are not correct.
     */
    public static final int ERROR_BAD_ARGUMENTS = 0X000000A0;

    /**
     * The specified path is invalid.
     */
    public static final int ERROR_BAD_PATHNAME = 0X000000A1;

    /**
     * A signal is already pending.
     */
    public static final int ERROR_SIGNAL_PENDING = 0X000000A2;

    /**
     * No more threads can be created in the system.
     */
    public static final int ERROR_MAX_THRDS_REACHED = 0X000000A4;

    /**
     * Unable to lock a region of a file.
     */
    public static final int ERROR_LOCK_FAILED = 0X000000A7;

    /**
     * The requested resource is in use.
     */
    public static final int ERROR_BUSY = 0X000000AA;

    /**
     * A lock request was not outstanding for the supplied   cancel region.
     */
    public static final int ERROR_CANCEL_VIOLATION = 0X000000AD;

    /**
     * The file system does not support atomic changes to the   lock type.
     */
    public static final int ERROR_ATOMIC_LOCKS_NOT_SUPPORTED = 0X000000AE;

    /**
     * The system detected a segment number that was not   correct.
     */
    public static final int ERROR_INVALID_SEGMENT_NUMBER = 0X000000B4;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INVALID_ORDINAL = 0X000000B6;

    /**
     * Cannot create a file when that file already exists.
     */
    public static final int ERROR_ALREADY_EXISTS = 0X000000B7;

    /**
     * The flag passed is not correct.
     */
    public static final int ERROR_INVALID_FLAG_NUMBER = 0X000000BA;

    /**
     * The specified system semaphore name was not found.
     */
    public static final int ERROR_SEM_NOT_FOUND = 0X000000BB;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INVALID_STARTING_CODESEG = 0X000000BC;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INVALID_STACKSEG = 0X000000BD;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INVALID_MODULETYPE = 0X000000BE;

    /**
     * Cannot run %1 in Win32 mode.
     */
    public static final int ERROR_INVALID_EXE_SIGNATURE = 0X000000BF;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_EXE_MARKED_INVALID = 0X000000C0;

    /**
     * %1 is not a valid Win32 application.
     */
    public static final int ERROR_BAD_EXE_FORMAT = 0X000000C1;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_ITERATED_DATA_EXCEEDS_64k = 0X000000C2;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INVALID_MINALLOCSIZE = 0X000000C3;

    /**
     * The operating system cannot run this application   program.
     */
    public static final int ERROR_DYNLINK_FROM_INVALID_RING = 0X000000C4;

    /**
     * The operating system is not presently configured to   run this application.
     */
    public static final int ERROR_IOPL_NOT_ENABLED = 0X000000C5;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INVALID_SEGDPL = 0X000000C6;

    /**
     * The operating system cannot run this application   program.
     */
    public static final int ERROR_AUTODATASEG_EXCEEDS_64k = 0X000000C7;

    /**
     * The code segment cannot be greater than or equal to 64   KB.
     */
    public static final int ERROR_RING2SEG_MUST_BE_MOVABLE = 0X000000C8;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_RELOC_CHAIN_XEEDS_SEGLIM = 0X000000C9;

    /**
     * The operating system cannot run %1.
     */
    public static final int ERROR_INFLOOP_IN_RELOC_CHAIN = 0X000000CA;

    /**
     * The system could not find the environment option that   was entered.
     */
    public static final int ERROR_ENVVAR_NOT_FOUND = 0X000000CB;

    /**
     * No process in the command subtree has a signal   handler.
     */
    public static final int ERROR_NO_SIGNAL_SENT = 0X000000CD;

    /**
     * The file name or extension is too long.
     */
    public static final int ERROR_FILENAME_EXCED_RANGE = 0X000000CE;

    /**
     * The ring 2 stack is in use.
     */
    public static final int ERROR_RING2_STACK_IN_USE = 0X000000CF;

    /**
     * The asterisk (*) or question mark (?) global file name   characters are entered incorrectly, or too many global file name characters   are specified.
     */
    public static final int ERROR_META_EXPANSION_TOO_LONG = 0X000000D0;

    /**
     * The signal being posted is not correct.
     */
    public static final int ERROR_INVALID_SIGNAL_NUMBER = 0X000000D1;

    /**
     * The signal handler cannot be set.
     */
    public static final int ERROR_THREAD_1_INACTIVE = 0X000000D2;

    /**
     * The segment is locked and cannot be reallocated.
     */
    public static final int ERROR_LOCKED = 0X000000D4;

    /**
     * Too many dynamic-link modules are attached to this   program or dynamic-link module.
     */
    public static final int ERROR_TOO_MANY_MODULES = 0X000000D6;

    /**
     * Cannot nest calls to LoadModule.
     */
    public static final int ERROR_NESTING_NOT_ALLOWED = 0X000000D7;

    /**
     * This version of %1 is not compatible with the version   of Windows you\'re running. Check your computer\'s system information to see   whether you need an x86 (32-bit) or x64 (64-bit) version of the program, and
     * then contact the software publisher.
     */
    public static final int ERROR_EXE_MACHINE_TYPE_MISMATCH = 0X000000D8;

    /**
     * The image file %1 is signed, unable to modify.
     */
    public static final int ERROR_EXE_CANNOT_MODIFY_SIGNED_BINARY = 0X000000D9;

    /**
     * The image file %1 is strong signed, unable to modify.
     */
    public static final int ERROR_EXE_CANNOT_MODIFY_STRONG_SIGNED_BINARY = 0X000000DA;

    /**
     * This file is checked out or locked for editing by   another user.
     */
    public static final int ERROR_FILE_CHECKED_OUT = 0X000000DC;

    /**
     * The file must be checked out before saving changes.
     */
    public static final int ERROR_CHECKOUT_REQUIRED = 0X000000DD;

    /**
     * The file type being saved or retrieved has been   blocked.
     */
    public static final int ERROR_BAD_FILE_TYPE = 0X000000DE;

    /**
     * The file size exceeds the limit allowed and cannot be   saved.
     */
    public static final int ERROR_FILE_TOO_LARGE = 0X000000DF;

    /**
     * Access denied. Before opening files in this location,   you must first browse to the website and select the option to sign in   automatically.
     */
    public static final int ERROR_FORMS_AUTH_REQUIRED = 0X000000E0;

    /**
     * Operation did not complete successfully because the   file contains a virus.
     */
    public static final int ERROR_VIRUS_INFECTED = 0X000000E1;

    /**
     * This file contains a virus and cannot be opened. Due   to the nature of this virus, the file has been removed from this location.
     */
    public static final int ERROR_VIRUS_DELETED = 0X000000E2;

    /**
     * The pipe is local.
     */
    public static final int ERROR_PIPE_LOCAL = 0X000000E5;

    /**
     * The pipe state is invalid.
     */
    public static final int ERROR_BAD_PIPE = 0X000000E6;

    /**
     * All pipe instances are busy.
     */
    public static final int ERROR_PIPE_BUSY = 0X000000E7;

    /**
     * The pipe is being closed.
     */
    public static final int ERROR_NO_DATA = 0X000000E8;

    /**
     * No process is on the other end of the pipe.
     */
    public static final int ERROR_PIPE_NOT_CONNECTED = 0X000000E9;

    /**
     * More data is available.
     */
    public static final int ERROR_MORE_DATA = 0X000000EA;

    /**
     * The session was canceled.
     */
    public static final int ERROR_VC_DISCONNECTED = 0X000000F0;

    /**
     * The specified extended attribute name was invalid.
     */
    public static final int ERROR_INVALID_EA_NAME = 0X000000FE;

    /**
     * The extended attributes are inconsistent.
     */
    public static final int ERROR_EA_LIST_INCONSISTENT = 0X000000FF;

    /**
     * The wait operation timed out.
     */
    public static final int WAIT_TIMEOUT = 0X00000102;

    /**
     * No more data is available.
     */
    public static final int ERROR_NO_MORE_ITEMS = 0X00000103;

    /**
     * The copy functions cannot be used.
     */
    public static final int ERROR_CANNOT_COPY = 0X0000010A;

    /**
     * The directory name is invalid.
     */
    public static final int ERROR_DIRECTORY = 0X0000010B;

    /**
     * The extended attributes did not fit in the buffer.
     */
    public static final int ERROR_EAS_DIDNT_FIT = 0X00000113;

    /**
     * The extended attribute file on the mounted file system   is corrupt.
     */
    public static final int ERROR_EA_FILE_CORRUPT = 0X00000114;

    /**
     * The extended attribute table file is full.
     */
    public static final int ERROR_EA_TABLE_FULL = 0X00000115;

    /**
     * The specified extended attribute handle is invalid.
     */
    public static final int ERROR_INVALID_EA_HANDLE = 0X00000116;

    /**
     * The mounted file system does not support extended   attributes.
     */
    public static final int ERROR_EAS_NOT_SUPPORTED = 0X0000011A;

    /**
     * Attempt to release mutex not owned by caller.
     */
    public static final int ERROR_NOT_OWNER = 0X00000120;

    /**
     * Too many posts were made to a semaphore.
     */
    public static final int ERROR_TOO_MANY_POSTS = 0X0000012A;

    /**
     * Only part of a ReadProcessMemory or WriteProcessMemory   request was completed.
     */
    public static final int ERROR_PARTIAL_COPY = 0X0000012B;

    /**
     * The oplock request is denied.
     */
    public static final int ERROR_OPLOCK_NOT_GRANTED = 0X0000012C;

    /**
     * An invalid oplock acknowledgment was received by the   system.
     */
    public static final int ERROR_INVALID_OPLOCK_PROTOCOL = 0X0000012D;

    /**
     * The volume is too fragmented to complete this   operation.
     */
    public static final int ERROR_DISK_TOO_FRAGMENTED = 0X0000012E;

    /**
     * The file cannot be opened because it is in the process   of being deleted.
     */
    public static final int ERROR_DELETE_PENDING = 0X0000012F;

    /**
     * The system cannot find message text for message number   0x%1 in the message file for %2.
     */
    public static final int ERROR_MR_MID_NOT_FOUND = 0X0000013D;

    /**
     * The scope specified was not found.
     */
    public static final int ERROR_SCOPE_NOT_FOUND = 0X0000013E;

    /**
     * No action was taken because a system reboot is   required.
     */
    public static final int ERROR_FAIL_NOACTION_REBOOT = 0X0000015E;

    /**
     * The shutdown operation failed.
     */
    public static final int ERROR_FAIL_SHUTDOWN = 0X0000015F;

    /**
     * The restart operation failed.
     */
    public static final int ERROR_FAIL_RESTART = 0X00000160;

    /**
     * The maximum number of sessions has been reached.
     */
    public static final int ERROR_MAX_SESSIONS_REACHED = 0X00000161;

    /**
     * The thread is already in background processing mode.
     */
    public static final int ERROR_THREAD_MODE_ALREADY_BACKGROUND = 0X00000190;

    /**
     * The thread is not in background processing mode.
     */
    public static final int ERROR_THREAD_MODE_NOT_BACKGROUND = 0X00000191;

    /**
     * The process is already in background processing mode.
     */
    public static final int ERROR_PROCESS_MODE_ALREADY_BACKGROUND = 0X00000192;

    /**
     * The process is not in background processing mode.
     */
    public static final int ERROR_PROCESS_MODE_NOT_BACKGROUND = 0X00000193;

    /**
     * Attempt to access invalid address.
     */
    public static final int ERROR_INVALID_ADDRESS = 0X000001E7;

    /**
     * User profile cannot be loaded.
     */
    public static final int ERROR_USER_PROFILE_LOAD = 0X000001F4;

    /**
     * Arithmetic result exceeded 32 bits.
     */
    public static final int ERROR_ARITHMETIC_OVERFLOW = 0X00000216;

    /**
     * There is a process on the other end of the pipe.
     */
    public static final int ERROR_PIPE_CONNECTED = 0X00000217;

    /**
     * Waiting for a process to open the other end of the   pipe.
     */
    public static final int ERROR_PIPE_LISTENING = 0X00000218;

    /**
     * Application verifier has found an error in the current   process.
     */
    public static final int ERROR_VERIFIER_STOP = 0X00000219;

    /**
     * An error occurred in the ABIOS subsystem.
     */
    public static final int ERROR_ABIOS_ERROR = 0X0000021A;

    /**
     * A warning occurred in the WX86 subsystem.
     */
    public static final int ERROR_WX86_WARNING = 0X0000021B;

    /**
     * An error occurred in the WX86 subsystem.
     */
    public static final int ERROR_WX86_ERROR = 0X0000021C;

    /**
     * An attempt was made to cancel or set a timer that has   an associated asynchronous procedure call (APC) and the subject thread is not   the thread that originally set the timer with an associated APC routine.
     */
    public static final int ERROR_TIMER_NOT_CANCELED = 0X0000021D;

    /**
     * Unwind exception code.
     */
    public static final int ERROR_UNWIND = 0X0000021E;

    /**
     * An invalid or unaligned stack was encountered during   an unwind operation.
     */
    public static final int ERROR_BAD_STACK = 0X0000021F;

    /**
     * An invalid unwind target was encountered during an   unwind operation.
     */
    public static final int ERROR_INVALID_UNWIND_TARGET = 0X00000220;

    /**
     * Invalid object attributes specified to NtCreatePort or   invalid port attributes specified to NtConnectPort.
     */
    public static final int ERROR_INVALID_PORT_ATTRIBUTES = 0X00000221;

    /**
     * Length of message passed to NtRequestPort or   NtRequestWaitReplyPort was longer than the maximum message allowed by the   port.
     */
    public static final int ERROR_PORT_MESSAGE_TOO_LONG = 0X00000222;

    /**
     * An attempt was made to lower a quota limit below the current   usage.
     */
    public static final int ERROR_INVALID_QUOTA_LOWER = 0X00000223;

    /**
     * An attempt was made to attach to a device that was   already attached to another device.
     */
    public static final int ERROR_DEVICE_ALREADY_ATTACHED = 0X00000224;

    /**
     * An attempt was made to execute an instruction at an   unaligned address, and the host system does not support unaligned instruction   references.
     */
    public static final int ERROR_INSTRUCTION_MISALIGNMENT = 0X00000225;

    /**
     * Profiling not started.
     */
    public static final int ERROR_PROFILING_NOT_STARTED = 0X00000226;

    /**
     * Profiling not stopped.
     */
    public static final int ERROR_PROFILING_NOT_STOPPED = 0X00000227;

    /**
     * The passed ACL did not contain the minimum required   information.
     */
    public static final int ERROR_COULD_NOT_INTERPRET = 0X00000228;

    /**
     * The number of active profiling objects is at the   maximum and no more can be started.
     */
    public static final int ERROR_PROFILING_AT_LIMIT = 0X00000229;

    /**
     * Used to indicate that an operation cannot continue   without blocking for I/O.
     */
    public static final int ERROR_CANT_WAIT = 0X0000022A;

    /**
     * Indicates that a thread attempted to terminate itself   by default (called NtTerminateThread with NULL) and it was the last thread in   the current process.
     */
    public static final int ERROR_CANT_TERMINATE_SELF = 0X0000022B;

    /**
     * If an MM error is returned that is not defined in the   standard FsRtl filter, it is converted to one of the following errors that is   guaranteed to be in the filter. In this case, information is lost; however,
     * the filter correctly handles the exception.
     */
    public static final int ERROR_UNEXPECTED_MM_CREATE_ERR = 0X0000022C;

    /**
     * If an MM error is returned that is not defined in the   standard FsRtl filter, it is converted to one of the following errors that is   guaranteed to be in the filter. In this case, information is lost; however,
     * the filter correctly handles the exception.
     */
    public static final int ERROR_UNEXPECTED_MM_MAP_ERROR = 0X0000022D;

    /**
     * If an MM error is returned that is not defined in the   standard FsRtl filter, it is converted to one of the following errors that is   guaranteed to be in the filter. In this case, information is lost; however,
     * the filter correctly handles the exception.
     */
    public static final int ERROR_UNEXPECTED_MM_EXTEND_ERR = 0X0000022E;

    /**
     * A malformed function table was encountered during an   unwind operation.
     */
    public static final int ERROR_BAD_FUNCTION_TABLE = 0X0000022F;

    /**
     * Indicates that an attempt was made to assign   protection to a file system file or directory and one of the SIDs in the   security descriptor could not be translated into a GUID that could be stored   by the file
     * system. This causes the protection attempt to fail, which might   cause a file creation attempt to fail.
     */
    public static final int ERROR_NO_GUID_TRANSLATION = 0X00000230;

    /**
     * Indicates that an attempt was made to grow a local   domain table (LDT) by setting its size, or that the size was not an even   number of selectors.
     */
    public static final int ERROR_INVALID_LDT_SIZE = 0X00000231;

    /**
     * Indicates that the starting value for the LDT   information was not an integral multiple of the selector size.
     */
    public static final int ERROR_INVALID_LDT_OFFSET = 0X00000233;

    /**
     * Indicates that the user supplied an invalid descriptor   when trying to set up LDT descriptors.
     */
    public static final int ERROR_INVALID_LDT_DESCRIPTOR = 0X00000234;

    /**
     * Indicates a process has too many threads to perform   the requested action. For example, assignment of a primary token can be   performed only when a process has zero or one threads.
     */
    public static final int ERROR_TOO_MANY_THREADS = 0X00000235;

    /**
     * An attempt was made to operate on a thread within a   specific process, but the thread specified is not in the process specified.
     */
    public static final int ERROR_THREAD_NOT_IN_PROCESS = 0X00000236;

    /**
     * Page file quota was exceeded.
     */
    public static final int ERROR_PAGEFILE_QUOTA_EXCEEDED = 0X00000237;

    /**
     * The Netlogon service cannot start because another   Netlogon service running in the domain conflicts with the specified role.
     */
    public static final int ERROR_LOGON_SERVER_CONFLICT = 0X00000238;

    /**
     * On applicable Windows Server releases, the Security   Accounts Manager (SAM) database is significantly out of synchronization with   the copy on the domain controller. A complete synchronization is required.
     */
    public static final int ERROR_SYNCHRONIZATION_REQUIRED = 0X00000239;

    /**
     * The NtCreateFile API failed. This error should never   be returned to an application, it is a place holder for the Windows LAN   Manager Redirector to use in its internal error mapping routines.
     */
    public static final int ERROR_NET_OPEN_FAILED = 0X0000023A;

    /**
     * {Privilege Failed} The I/O permissions for the process   could not be changed.
     */
    public static final int ERROR_IO_PRIVILEGE_FAILED = 0X0000023B;

    /**
     * {Application Exit by CTRL+C} The application   terminated as a result of a CTRL+C.
     */
    public static final int ERROR_CONTROL_C_EXIT = 0X0000023C;

    /**
     * {Missing System File} The required system file %hs is   bad or missing.
     */
    public static final int ERROR_MISSING_SYSTEMFILE = 0X0000023D;

    /**
     * {Application Error} The exception %s (0x%08lx)   occurred in the application at location 0x%08lx.
     */
    public static final int ERROR_UNHANDLED_EXCEPTION = 0X0000023E;

    /**
     * {Application Error} The application failed to   initialize properly (0x%lx). Click OK to terminate the application.
     */
    public static final int ERROR_APP_INIT_FAILURE = 0X0000023F;

    /**
     * {Unable to Create Paging File} The creation of the   paging file %hs failed (%lx). The requested size was %ld.
     */
    public static final int ERROR_PAGEFILE_CREATE_FAILED = 0X00000240;

    /**
     * The hash for the image cannot be found in the system   catalogs. The image is likely corrupt or the victim of tampering.
     */
    public static final int ERROR_INVALID_IMAGE_HASH = 0X00000241;

    /**
     * {No Paging File Specified} No paging file was   specified in the system configuration.
     */
    public static final int ERROR_NO_PAGEFILE = 0X00000242;

    /**
     * {EXCEPTION} A real-mode application issued a   floating-point instruction, and floating-point hardware is not present.
     */
    public static final int ERROR_ILLEGAL_FLOAT_CONTEXT = 0X00000243;

    /**
     * An event pair synchronization operation was performed   using the thread-specific client/server event pair object, but no event pair   object was associated with the thread.
     */
    public static final int ERROR_NO_EVENT_PAIR = 0X00000244;

    /**
     * A domain server has an incorrect configuration.
     */
    public static final int ERROR_DOMAIN_CTRLR_CONFIG_ERROR = 0X00000245;

    /**
     * An illegal character was encountered. For a multibyte   character set, this includes a lead byte without a succeeding trail byte. For   the Unicode character set, this includes the characters 0xFFFF and 0xFFFE.
     */
    public static final int ERROR_ILLEGAL_CHARACTER = 0X00000246;

    /**
     * The Unicode character is not defined in the Unicode   character set installed on the system.
     */
    public static final int ERROR_UNDEFINED_CHARACTER = 0X00000247;

    /**
     * The paging file cannot be created on a floppy disk.
     */
    public static final int ERROR_FLOPPY_VOLUME = 0X00000248;

    /**
     * The system bios failed to connect a system interrupt   to the device or bus for which the device is connected.
     */
    public static final int ERROR_BIOS_FAILED_TO_CONNECT_INTERRUPT = 0X00000249;

    /**
     * This operation is only allowed for the primary domain   controller (PDC) of the domain.
     */
    public static final int ERROR_BACKUP_CONTROLLER = 0X0000024A;

    /**
     * An attempt was made to acquire a mutant such that its   maximum count would have been exceeded.
     */
    public static final int ERROR_MUTANT_LIMIT_EXCEEDED = 0X0000024B;

    /**
     * A volume has been accessed for which a file system   driver is required that has not yet been loaded.
     */
    public static final int ERROR_FS_DRIVER_REQUIRED = 0X0000024C;

    /**
     * {Registry File Failure} The registry cannot load the   hive (file): %hs or its log or alternate. It is corrupt, absent, or not   writable.
     */
    public static final int ERROR_CANNOT_LOAD_REGISTRY_FILE = 0X0000024D;

    /**
     * {Unexpected Failure in DebugActiveProcess} An unexpected   failure occurred while processing a DebugActiveProcess API request. Choosing   OK will terminate the process, and choosing Cancel will ignore the error.
     */
    public static final int ERROR_DEBUG_ATTACH_FAILED = 0X0000024E;

    /**
     * {Fatal System Error} The %hs system process terminated   unexpectedly with a status of 0x%08x (0x%08x 0x%08x). The system has been   shut down.
     */
    public static final int ERROR_SYSTEM_PROCESS_TERMINATED = 0X0000024F;

    /**
     * {Data Not Accepted} The transport driver interface   (TDI) client could not handle the data received during an indication.
     */
    public static final int ERROR_DATA_NOT_ACCEPTED = 0X00000250;

    /**
     * The NT Virtual DOS Machine (NTVDM) encountered a hard   error.
     */
    public static final int ERROR_VDM_HARD_ERROR = 0X00000251;

    /**
     * {Cancel Timeout} The driver %hs failed to complete a   canceled I/O request in the allotted time.
     */
    public static final int ERROR_DRIVER_CANCEL_TIMEOUT = 0X00000252;

    /**
     * {Reply Message Mismatch} An attempt was made to reply   to a local procedure call (LPC) message, but the thread specified by the   client ID in the message was not waiting on that message.
     */
    public static final int ERROR_REPLY_MESSAGE_MISMATCH = 0X00000253;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs. The data has been lost. This error might be caused   by a failure of your computer hardware or network connection. Try to save
     * this file elsewhere.
     */
    public static final int ERROR_LOST_WRITEBEHIND_DATA = 0X00000254;

    /**
     * The parameters passed to the server in the   client/server shared memory window were invalid. Too much data might have   been put in the shared memory window.
     */
    public static final int ERROR_CLIENT_SERVER_PARAMETERS_INVALID = 0X00000255;

    /**
     * The stream is not a tiny stream.
     */
    public static final int ERROR_NOT_TINY_STREAM = 0X00000256;

    /**
     * The request must be handled by the stack overflow   code.
     */
    public static final int ERROR_STACK_OVERFLOW_READ = 0X00000257;

    /**
     * Internal OFS status codes indicating how an allocation   operation is handled. Either it is retried after the containing onode is   moved or the extent stream is converted to a large stream.
     */
    public static final int ERROR_CONVERT_TO_LARGE = 0X00000258;

    /**
     * The attempt to find the object found an object   matching by ID on the volume but it is out of the scope of the handle used   for the operation.
     */
    public static final int ERROR_FOUND_OUT_OF_SCOPE = 0X00000259;

    /**
     * The bucket array must be grown. Retry transaction   after doing so.
     */
    public static final int ERROR_ALLOCATE_BUCKET = 0X0000025A;

    /**
     * The user/kernel marshaling buffer has overflowed.
     */
    public static final int ERROR_MARSHALL_OVERFLOW = 0X0000025B;

    /**
     * The supplied variant structure contains invalid data.
     */
    public static final int ERROR_INVALID_VARIANT = 0X0000025C;

    /**
     * The specified buffer contains ill-formed data.
     */
    public static final int ERROR_BAD_COMPRESSION_BUFFER = 0X0000025D;

    /**
     * {Audit Failed} An attempt to generate a security audit   failed.
     */
    public static final int ERROR_AUDIT_FAILED = 0X0000025E;

    /**
     * The timer resolution was not previously set by the   current process.
     */
    public static final int ERROR_TIMER_RESOLUTION_NOT_SET = 0X0000025F;

    /**
     * There is insufficient account information to log you   on.
     */
    public static final int ERROR_INSUFFICIENT_LOGON_INFO = 0X00000260;

    /**
     * {Invalid DLL Entrypoint} The dynamic link library %hs   is not written correctly. The stack pointer has been left in an inconsistent   state. The entry point should be declared as WINAPI or STDCALL. Select YES to
     * fail the DLL load. Select NO to continue execution. Selecting NO can cause   the application to operate incorrectly.
     */
    public static final int ERROR_BAD_DLL_ENTRYPOINT = 0X00000261;

    /**
     * {Invalid Service Callback Entrypoint} The %hs service   is not written correctly. The stack pointer has been left in an inconsistent   state. The callback entry point should be declared as WINAPI or STDCALL.
     * Selecting OK will cause the service to continue operation. However, the   service process might operate incorrectly.
     */
    public static final int ERROR_BAD_SERVICE_ENTRYPOINT = 0X00000262;

    /**
     * There is an IP address conflict with another system on   the network.
     */
    public static final int ERROR_IP_ADDRESS_CONFLICT1 = 0X00000263;

    /**
     * There is an IP address conflict with another system on   the network.
     */
    public static final int ERROR_IP_ADDRESS_CONFLICT2 = 0X00000264;

    /**
     * {Low On Registry Space} The system has reached the   maximum size allowed for the system part of the registry. Additional storage   requests will be ignored.
     */
    public static final int ERROR_REGISTRY_QUOTA_LIMIT = 0X00000265;

    /**
     * A callback return system service cannot be executed   when no callback is active.
     */
    public static final int ERROR_NO_CALLBACK_ACTIVE = 0X00000266;

    /**
     * The password provided is too short to meet the policy   of your user account. Choose a longer password.
     */
    public static final int ERROR_PWD_TOO_SHORT = 0X00000267;

    /**
     * The policy of your user account does not allow you to   change passwords too frequently. This is done to prevent users from changing   back to a familiar, but potentially discovered, password. If you feel your
     * password has been compromised, contact your administrator immediately to have   a new one assigned.
     */
    public static final int ERROR_PWD_TOO_RECENT = 0X00000268;

    /**
     * You have attempted to change your password to one that   you have used in the past. The policy of your user account does not allow   this. Select a password that you have not previously used.
     */
    public static final int ERROR_PWD_HISTORY_CONFLICT = 0X00000269;

    /**
     * The specified compression format is unsupported.
     */
    public static final int ERROR_UNSUPPORTED_COMPRESSION = 0X0000026A;

    /**
     * The specified hardware profile configuration is   invalid.
     */
    public static final int ERROR_INVALID_HW_PROFILE = 0X0000026B;

    /**
     * The specified Plug and Play registry device path is   invalid.
     */
    public static final int ERROR_INVALID_PLUGPLAY_DEVICE_PATH = 0X0000026C;

    /**
     * The specified quota list is internally inconsistent   with its descriptor.
     */
    public static final int ERROR_QUOTA_LIST_INCONSISTENT = 0X0000026D;

    /**
     * {Windows Evaluation Notification} The evaluation   period for this installation of Windows has expired. This system will shut   down in 1 hour. To restore access to this installation of Windows, upgrade   this
     * installation using a licensed distribution of this product.
     */
    public static final int ERROR_EVALUATION_EXPIRATION = 0X0000026E;

    /**
     * {Illegal System DLL Relocation} The system DLL %hs was   relocated in memory. The application will not run properly. The relocation   occurred because the DLL %hs occupied an address range reserved for Windows
     * system DLLs. The vendor supplying the DLL should be contacted for a new DLL.
     */
    public static final int ERROR_ILLEGAL_DLL_RELOCATION = 0X0000026F;

    /**
     * {DLL Initialization Failed} The application failed to   initialize because the window station is shutting down.
     */
    public static final int ERROR_DLL_INIT_FAILED_LOGOFF = 0X00000270;

    /**
     * The validation process needs to continue on to the   next step.
     */
    public static final int ERROR_VALIDATE_CONTINUE = 0X00000271;

    /**
     * There are no more matches for the current index   enumeration.
     */
    public static final int ERROR_NO_MORE_MATCHES = 0X00000272;

    /**
     * The range could not be added to the range list because   of a conflict.
     */
    public static final int ERROR_RANGE_LIST_CONFLICT = 0X00000273;

    /**
     * The server process is running under a SID different   than that required by the client.
     */
    public static final int ERROR_SERVER_SID_MISMATCH = 0X00000274;

    /**
     * A group marked use for deny only cannot be enabled.
     */
    public static final int ERROR_CANT_ENABLE_DENY_ONLY = 0X00000275;

    /**
     * {EXCEPTION} Multiple floating point faults.
     */
    public static final int ERROR_FLOAT_MULTIPLE_FAULTS = 0X00000276;

    /**
     * {EXCEPTION} Multiple floating point traps.
     */
    public static final int ERROR_FLOAT_MULTIPLE_TRAPS = 0X00000277;

    /**
     * The requested interface is not supported.
     */
    public static final int ERROR_NOINTERFACE = 0X00000278;

    /**
     * {System Standby Failed} The driver %hs does not   support standby mode. Updating this driver might allow the system to go to   standby mode.
     */
    public static final int ERROR_DRIVER_FAILED_SLEEP = 0X00000279;

    /**
     * The system file %1 has become corrupt and has been   replaced.
     */
    public static final int ERROR_CORRUPT_SYSTEM_FILE = 0X0000027A;

    /**
     * {Virtual Memory Minimum Too Low} Your system is low on   virtual memory. Windows is increasing the size of your virtual memory paging   file. During this process, memory requests for some applications might be
     * denied. For more information, see Help.
     */
    public static final int ERROR_COMMITMENT_MINIMUM = 0X0000027B;

    /**
     * A device was removed so enumeration must be restarted.
     */
    public static final int ERROR_PNP_RESTART_ENUMERATION = 0X0000027C;

    /**
     * {Fatal System Error} The system image %s is not   properly signed. The file has been replaced with the signed file. The system   has been shut down.
     */
    public static final int ERROR_SYSTEM_IMAGE_BAD_SIGNATURE = 0X0000027D;

    /**
     * Device will not start without a reboot.
     */
    public static final int ERROR_PNP_REBOOT_REQUIRED = 0X0000027E;

    /**
     * There is not enough power to complete the requested   operation.
     */
    public static final int ERROR_INSUFFICIENT_POWER = 0X0000027F;

    /**
     * The system is in the process of shutting down.
     */
    public static final int ERROR_SYSTEM_SHUTDOWN = 0X00000281;

    /**
     * An attempt to remove a process DebugPort was made, but   a port was not already associated with the process.
     */
    public static final int ERROR_PORT_NOT_SET = 0X00000282;

    /**
     * This version of Windows is not compatible with the   behavior version of directory forest, domain, or domain controller.
     */
    public static final int ERROR_DS_VERSION_CHECK_FAILURE = 0X00000283;

    /**
     * The specified range could not be found in the range   list.
     */
    public static final int ERROR_RANGE_NOT_FOUND = 0X00000284;

    /**
     * The driver was not loaded because the system is   booting into safe mode.
     */
    public static final int ERROR_NOT_SAFE_MODE_DRIVER = 0X00000286;

    /**
     * The driver was not loaded because it failed its   initialization call.
     */
    public static final int ERROR_FAILED_DRIVER_ENTRY = 0X00000287;

    /**
     * The device encountered an error while applying power   or reading the device configuration. This might be caused by a failure of   your hardware or by a poor connection.
     */
    public static final int ERROR_DEVICE_ENUMERATION_ERROR = 0X00000288;

    /**
     * The create operation failed because the name contained   at least one mount point that resolves to a volume to which the specified   device object is not attached.
     */
    public static final int ERROR_MOUNT_POINT_NOT_RESOLVED = 0X00000289;

    /**
     * The device object parameter is either not a valid   device object or is not attached to the volume specified by the file name.
     */
    public static final int ERROR_INVALID_DEVICE_OBJECT_PARAMETER = 0X0000028A;

    /**
     * A machine check error has occurred. Check the system event   log for additional information.
     */
    public static final int ERROR_MCA_OCCURED = 0X0000028B;

    /**
     * There was an error [%2] processing the driver   database.
     */
    public static final int ERROR_DRIVER_DATABASE_ERROR = 0X0000028C;

    /**
     * The system hive size has exceeded its limit.
     */
    public static final int ERROR_SYSTEM_HIVE_TOO_LARGE = 0X0000028D;

    /**
     * The driver could not be loaded because a previous   version of the driver is still in memory.
     */
    public static final int ERROR_DRIVER_FAILED_PRIOR_UNLOAD = 0X0000028E;

    /**
     * {Volume Shadow Copy Service} Wait while the Volume   Shadow Copy Service prepares volume %hs for hibernation.
     */
    public static final int ERROR_VOLSNAP_PREPARE_HIBERNATE = 0X0000028F;

    /**
     * The system has failed to hibernate (the error code is   %hs). Hibernation will be disabled until the system is restarted.
     */
    public static final int ERROR_HIBERNATION_FAILURE = 0X00000290;

    /**
     * The requested operation could not be completed due to   a file system limitation.
     */
    public static final int ERROR_FILE_SYSTEM_LIMITATION = 0X00000299;

    /**
     * An assertion failure has occurred.
     */
    public static final int ERROR_ASSERTION_FAILURE = 0X0000029C;

    /**
     * An error occurred in the Advanced Configuration and   Power Interface (ACPI) subsystem.
     */
    public static final int ERROR_ACPI_ERROR = 0X0000029D;

    /**
     * WOW assertion error.
     */
    public static final int ERROR_WOW_ASSERTION = 0X0000029E;

    /**
     * A device is missing in the system BIOS MultiProcessor   Specification (MPS) table. This device will not be used. Contact your system   vendor for system BIOS update.
     */
    public static final int ERROR_PNP_BAD_MPS_TABLE = 0X0000029F;

    /**
     * A translator failed to translate resources.
     */
    public static final int ERROR_PNP_TRANSLATION_FAILED = 0X000002A0;

    /**
     * An interrupt request (IRQ) translator failed to   translate resources.
     */
    public static final int ERROR_PNP_IRQ_TRANSLATION_FAILED = 0X000002A1;

    /**
     * Driver %2 returned invalid ID for a child device (%3).
     */
    public static final int ERROR_PNP_INVALID_ID = 0X000002A2;

    /**
     * {Kernel Debugger Awakened} the system debugger was   awakened by an interrupt.
     */
    public static final int ERROR_WAKE_SYSTEM_DEBUGGER = 0X000002A3;

    /**
     * {Handles Closed} Handles to objects have been   automatically closed because of the requested operation.
     */
    public static final int ERROR_HANDLES_CLOSED = 0X000002A4;

    /**
     * {Too Much Information} The specified ACL contained   more information than was expected.
     */
    public static final int ERROR_EXTRANEOUS_INFORMATION = 0X000002A5;

    /**
     * This warning level status indicates that the   transaction state already exists for the registry subtree, but that a   transaction commit was previously aborted. The commit has NOT been completed,   but it has not
     * been rolled back either (so it can still be committed if   desired).
     */
    public static final int ERROR_RXACT_COMMIT_NECESSARY = 0X000002A6;

    /**
     * {Media Changed} The media might have changed.
     */
    public static final int ERROR_MEDIA_CHECK = 0X000002A7;

    /**
     * {GUID Substitution} During the translation of a GUID   to a Windows SID, no administratively defined GUID prefix was found. A   substitute prefix was used, which will not compromise system security.   However,
     * this might provide more restrictive access than intended.
     */
    public static final int ERROR_GUID_SUBSTITUTION_MADE = 0X000002A8;

    /**
     * The create operation stopped after reaching a symbolic   link.
     */
    public static final int ERROR_STOPPED_ON_SYMLINK = 0X000002A9;

    /**
     * A long jump has been executed.
     */
    public static final int ERROR_LONGJUMP = 0X000002AA;

    /**
     * The Plug and Play query operation was not successful.
     */
    public static final int ERROR_PLUGPLAY_QUERY_VETOED = 0X000002AB;

    /**
     * A frame consolidation has been executed.
     */
    public static final int ERROR_UNWIND_CONSOLIDATE = 0X000002AC;

    /**
     * {Registry Hive Recovered} Registry hive (file): %hs   was corrupted and it has been recovered. Some data might have been lost.
     */
    public static final int ERROR_REGISTRY_HIVE_RECOVERED = 0X000002AD;

    /**
     * The application is attempting to run executable code   from the module %hs. This might be insecure. An alternative, %hs, is   available. Should the application use the secure module %hs?
     */
    public static final int ERROR_DLL_MIGHT_BE_INSECURE = 0X000002AE;

    /**
     * The application is loading executable code from the   module %hs. This is secure, but might be incompatible with previous releases   of the operating system. An alternative, %hs, is available. Should the
     * application use the secure module %hs?
     */
    public static final int ERROR_DLL_MIGHT_BE_INCOMPATIBLE = 0X000002AF;

    /**
     * Debugger did not handle the exception.
     */
    public static final int ERROR_DBG_EXCEPTION_NOT_HANDLED = 0X000002B0;

    /**
     * Debugger will reply later.
     */
    public static final int ERROR_DBG_REPLY_LATER = 0X000002B1;

    /**
     * Debugger cannot provide handle.
     */
    public static final int ERROR_DBG_UNABLE_TO_PROVIDE_HANDLE = 0X000002B2;

    /**
     * Debugger terminated thread.
     */
    public static final int ERROR_DBG_TERMINATE_THREAD = 0X000002B3;

    /**
     * Debugger terminated process.
     */
    public static final int ERROR_DBG_TERMINATE_PROCESS = 0X000002B4;

    /**
     * Debugger got control C.
     */
    public static final int ERROR_DBG_CONTROL_C = 0X000002B5;

    /**
     * Debugger printed exception on control C.
     */
    public static final int ERROR_DBG_PRINTEXCEPTION_C = 0X000002B6;

    /**
     * Debugger received Routing Information Protocol (RIP)   exception.
     */
    public static final int ERROR_DBG_RIPEXCEPTION = 0X000002B7;

    /**
     * Debugger received control break.
     */
    public static final int ERROR_DBG_CONTROL_BREAK = 0X000002B8;

    /**
     * Debugger command communication exception.
     */
    public static final int ERROR_DBG_COMMAND_EXCEPTION = 0X000002B9;

    /**
     * {Object Exists} An attempt was made to create an   object and the object name already existed.
     */
    public static final int ERROR_OBJECT_NAME_EXISTS = 0X000002BA;

    /**
     * {Thread Suspended} A thread termination occurred while   the thread was suspended. The thread was resumed and termination proceeded.
     */
    public static final int ERROR_THREAD_WAS_SUSPENDED = 0X000002BB;

    /**
     * {Image Relocated} An image file could not be mapped at   the address specified in the image file. Local fixes must be performed on   this image.
     */
    public static final int ERROR_IMAGE_NOT_AT_BASE = 0X000002BC;

    /**
     * This informational level status indicates that a   specified registry subtree transaction state did not yet exist and had to be   created.
     */
    public static final int ERROR_RXACT_STATE_CREATED = 0X000002BD;

    /**
     * {Segment Load} A virtual DOS machine (VDM) is loading,   unloading, or moving an MS-DOS or Win16 program segment image. An exception   is raised so a debugger can load, unload, or track symbols and breakpoints
     * within these 16-bit segments.
     */
    public static final int ERROR_SEGMENT_NOTIFICATION = 0X000002BE;

    /**
     * {Invalid Current Directory} The process cannot switch   to the startup current directory %hs. Select OK to set current directory to   %hs, or select CANCEL to exit.
     */
    public static final int ERROR_BAD_CURRENT_DIRECTORY = 0X000002BF;

    /**
     * {Redundant Read} To satisfy a read request, the NT   fault-tolerant file system successfully read the requested data from a   redundant copy. This was done because the file system encountered a failure   on a
     * member of the fault-tolerant volume, but it was unable to reassign the   failing area of the device.
     */
    public static final int ERROR_FT_READ_RECOVERY_FROM_BACKUP = 0X000002C0;

    /**
     * {Redundant Write} To satisfy a write request, the   Windows NT operating system fault-tolerant file system successfully wrote a   redundant copy of the information. This was done because the file system
     * encountered a failure on a member of the fault-tolerant volume, but it was   not able to reassign the failing area of the device.
     */
    public static final int ERROR_FT_WRITE_RECOVERY = 0X000002C1;

    /**
     * {Machine Type Mismatch} The image file %hs is valid,   but is for a machine type other than the current machine. Select OK to   continue, or CANCEL to fail the DLL load.
     */
    public static final int ERROR_IMAGE_MACHINE_TYPE_MISMATCH = 0X000002C2;

    /**
     * {Partial Data Received} The network transport returned   partial data to its client. The remaining data will be sent later.
     */
    public static final int ERROR_RECEIVE_PARTIAL = 0X000002C3;

    /**
     * {Expedited Data Received} The network transport   returned data to its client that was marked as expedited by the remote   system.
     */
    public static final int ERROR_RECEIVE_EXPEDITED = 0X000002C4;

    /**
     * {Partial Expedited Data Received} The network   transport returned partial data to its client and this data was marked as   expedited by the remote system. The remaining data will be sent later.
     */
    public static final int ERROR_RECEIVE_PARTIAL_EXPEDITED = 0X000002C5;

    /**
     * {TDI Event Done} The TDI indication has completed   successfully.
     */
    public static final int ERROR_EVENT_DONE = 0X000002C6;

    /**
     * {TDI Event Pending} The TDI indication has entered the   pending state.
     */
    public static final int ERROR_EVENT_PENDING = 0X000002C7;

    /**
     * Checking file system on %wZ.
     */
    public static final int ERROR_CHECKING_FILE_SYSTEM = 0X000002C8;

    /**
     * {Fatal Application Exit} %hs.
     */
    public static final int ERROR_FATAL_APP_EXIT = 0X000002C9;

    /**
     * The specified registry key is referenced by a   predefined handle.
     */
    public static final int ERROR_PREDEFINED_HANDLE = 0X000002CA;

    /**
     * {Page Unlocked} The page protection of a locked page   was changed to \'No Access\' and the page was unlocked from memory and from the   process.
     */
    public static final int ERROR_WAS_UNLOCKED = 0X000002CB;

    /**
     * {Page Locked} One of the pages to lock was already   locked.
     */
    public static final int ERROR_WAS_LOCKED = 0X000002CD;

    /**
     * The value already corresponds with a Win 32 error   code.
     */
    public static final int ERROR_ALREADY_WIN32 = 0X000002CF;

    /**
     * {Machine Type Mismatch} The image file %hs is valid,   but is for a machine type other than the current machine.
     */
    public static final int ERROR_IMAGE_MACHINE_TYPE_MISMATCH_EXE = 0X000002D0;

    /**
     * A yield execution was performed and no thread was   available to run.
     */
    public static final int ERROR_NO_YIELD_PERFORMED = 0X000002D1;

    /**
     * The resume flag to a timer API was ignored.
     */
    public static final int ERROR_TIMER_RESUME_IGNORED = 0X000002D2;

    /**
     * The arbiter has deferred arbitration of these   resources to its parent.
     */
    public static final int ERROR_ARBITRATION_UNHANDLED = 0X000002D3;

    /**
     * The inserted CardBus device cannot be started because   of a configuration error on %hs\".\"
     */
    public static final int ERROR_CARDBUS_NOT_SUPPORTED = 0X000002D4;

    /**
     * The CPUs in this multiprocessor system are not all the   same revision level. To use all processors the operating system restricts   itself to the features of the least capable processor in the system. If problems
     * occur with this system, contact the CPU manufacturer to see if this   mix of processors is supported.
     */
    public static final int ERROR_MP_PROCESSOR_MISMATCH = 0X000002D5;

    /**
     * The system was put into hibernation.
     */
    public static final int ERROR_HIBERNATED = 0X000002D6;

    /**
     * The system was resumed from hibernation.
     */
    public static final int ERROR_RESUME_HIBERNATION = 0X000002D7;

    /**
     * Windows has detected that the system firmware (BIOS)   was updated (previous firmware date = %2, current firmware date %3).
     */
    public static final int ERROR_FIRMWARE_UPDATED = 0X000002D8;

    /**
     * A device driver is leaking locked I/O pages, causing   system degradation. The system has automatically enabled a tracking code to   try and catch the culprit.
     */
    public static final int ERROR_DRIVERS_LEAKING_LOCKED_PAGES = 0X000002D9;

    /**
     * The system has awoken.
     */
    public static final int ERROR_WAKE_SYSTEM = 0X000002DA;

    /**
     * The call failed because the handle associated with it   was closed.
     */
    public static final int ERROR_ABANDONED_WAIT_0 = 0X000002DF;

    /**
     * The requested operation requires elevation.
     */
    public static final int ERROR_ELEVATION_REQUIRED = 0X000002E4;

    /**
     * A reparse should be performed by the object manager   because the name of the file resulted in a symbolic link.
     */
    public static final int ERROR_REPARSE = 0X000002E5;

    /**
     * An open/create operation completed while an oplock   break is underway.
     */
    public static final int ERROR_OPLOCK_BREAK_IN_PROGRESS = 0X000002E6;

    /**
     * A new volume has been mounted by a file system.
     */
    public static final int ERROR_VOLUME_MOUNTED = 0X000002E7;

    /**
     * This success level status indicates that the   transaction state already exists for the registry subtree, but that a   transaction commit was previously aborted. The commit has now been completed.
     */
    public static final int ERROR_RXACT_COMMITTED = 0X000002E8;

    /**
     * This indicates that a notify change request has been   completed due to closing the handle which made the notify change request.
     */
    public static final int ERROR_NOTIFY_CLEANUP = 0X000002E9;

    /**
     * {Connect Failure on Primary Transport} An attempt was   made to connect to the remote server %hs on the primary transport, but the   connection failed. The computer was able to connect on a secondary transport.
     */
    public static final int ERROR_PRIMARY_TRANSPORT_CONNECT_FAILED = 0X000002EA;

    /**
     * Page fault was a transition fault.
     */
    public static final int ERROR_PAGE_FAULT_TRANSITION = 0X000002EB;

    /**
     * Page fault was a demand zero fault.
     */
    public static final int ERROR_PAGE_FAULT_DEMAND_ZERO = 0X000002EC;

    /**
     * Page fault was a demand zero fault.
     */
    public static final int ERROR_PAGE_FAULT_COPY_ON_WRITE = 0X000002ED;

    /**
     * Page fault was a demand zero fault.
     */
    public static final int ERROR_PAGE_FAULT_GUARD_PAGE = 0X000002EE;

    /**
     * Page fault was satisfied by reading from a secondary   storage device.
     */
    public static final int ERROR_PAGE_FAULT_PAGING_FILE = 0X000002EF;

    /**
     * Cached page was locked during operation.
     */
    public static final int ERROR_CACHE_PAGE_LOCKED = 0X000002F0;

    /**
     * Crash dump exists in paging file.
     */
    public static final int ERROR_CRASH_DUMP = 0X000002F1;

    /**
     * Specified buffer contains all zeros.
     */
    public static final int ERROR_BUFFER_ALL_ZEROS = 0X000002F2;

    /**
     * A reparse should be performed by the object manager   because the name of the file resulted in a symbolic link.
     */
    public static final int ERROR_REPARSE_OBJECT = 0X000002F3;

    /**
     * The device has succeeded a query-stop and its resource   requirements have changed.
     */
    public static final int ERROR_RESOURCE_REQUIREMENTS_CHANGED = 0X000002F4;

    /**
     * The translator has translated these resources into the   global space and no further translations should be performed.
     */
    public static final int ERROR_TRANSLATION_COMPLETE = 0X000002F5;

    /**
     * A process being terminated has no threads to   terminate.
     */
    public static final int ERROR_NOTHING_TO_TERMINATE = 0X000002F6;

    /**
     * The specified process is not part of a job.
     */
    public static final int ERROR_PROCESS_NOT_IN_JOB = 0X000002F7;

    /**
     * The specified process is part of a job.
     */
    public static final int ERROR_PROCESS_IN_JOB = 0X000002F8;

    /**
     * {Volume Shadow Copy Service} The system is now ready   for hibernation.
     */
    public static final int ERROR_VOLSNAP_HIBERNATE_READY = 0X000002F9;

    /**
     * A file system or file system filter driver has   successfully completed an FsFilter operation.
     */
    public static final int ERROR_FSFILTER_OP_COMPLETED_SUCCESSFULLY = 0X000002FA;

    /**
     * The specified interrupt vector was already connected.
     */
    public static final int ERROR_INTERRUPT_VECTOR_ALREADY_CONNECTED = 0X000002FB;

    /**
     * The specified interrupt vector is still connected.
     */
    public static final int ERROR_INTERRUPT_STILL_CONNECTED = 0X000002FC;

    /**
     * An operation is blocked waiting for an oplock.
     */
    public static final int ERROR_WAIT_FOR_OPLOCK = 0X000002FD;

    /**
     * Debugger handled exception.
     */
    public static final int ERROR_DBG_EXCEPTION_HANDLED = 0X000002FE;

    /**
     * Debugger continued.
     */
    public static final int ERROR_DBG_CONTINUE = 0X000002FF;

    /**
     * An exception occurred in a user mode callback and the   kernel callback frame should be removed.
     */
    public static final int ERROR_CALLBACK_POP_STACK = 0X00000300;

    /**
     * Compression is disabled for this volume.
     */
    public static final int ERROR_COMPRESSION_DISABLED = 0X00000301;

    /**
     * The data provider cannot fetch backward through a   result set.
     */
    public static final int ERROR_CANTFETCHBACKWARDS = 0X00000302;

    /**
     * The data provider cannot scroll backward through a   result set.
     */
    public static final int ERROR_CANTSCROLLBACKWARDS = 0X00000303;

    /**
     * The data provider requires that previously fetched   data is released before asking for more data.
     */
    public static final int ERROR_ROWSNOTRELEASED = 0X00000304;

    /**
     * The data provider was not able to interpret the flags   set for a column binding in an accessor.
     */
    public static final int ERROR_BAD_ACCESSOR_FLAGS = 0X00000305;

    /**
     * One or more errors occurred while processing the   request.
     */
    public static final int ERROR_ERRORS_ENCOUNTERED = 0X00000306;

    /**
     * The implementation is not capable of performing the   request.
     */
    public static final int ERROR_NOT_CAPABLE = 0X00000307;

    /**
     * The client of a component requested an operation that   is not valid given the state of the component instance.
     */
    public static final int ERROR_REQUEST_OUT_OF_SEQUENCE = 0X00000308;

    /**
     * A version number could not be parsed.
     */
    public static final int ERROR_VERSION_PARSE_ERROR = 0X00000309;

    /**
     * The iterator\'s start position is invalid.
     */
    public static final int ERROR_BADSTARTPOSITION = 0X0000030A;

    /**
     * The hardware has reported an uncorrectable memory   error.
     */
    public static final int ERROR_MEMORY_HARDWARE = 0X0000030B;

    /**
     * The attempted operation required self-healing to be   enabled.
     */
    public static final int ERROR_DISK_REPAIR_DISABLED = 0X0000030C;

    /**
     * The Desktop heap encountered an error while allocating   session memory. There is more information in the system event log.
     */
    public static final int ERROR_INSUFFICIENT_RESOURCE_FOR_SPECIFIED_SHARED_SECTION_SIZE = 0X0000030D;

    /**
     * The system power state is transitioning from %2 to %3.
     */
    public static final int ERROR_SYSTEM_POWERSTATE_TRANSITION = 0X0000030E;

    /**
     * The system power state is transitioning from %2 to %3   but could enter %4.
     */
    public static final int ERROR_SYSTEM_POWERSTATE_COMPLEX_TRANSITION = 0X0000030F;

    /**
     * A thread is getting dispatched with MCA EXCEPTION   because of MCA.
     */
    public static final int ERROR_MCA_EXCEPTION = 0X00000310;

    /**
     * Access to %1 is monitored by policy rule %2.
     */
    public static final int ERROR_ACCESS_AUDIT_BY_POLICY = 0X00000311;

    /**
     * Access to %1 has been restricted by your administrator   by policy rule %2.
     */
    public static final int ERROR_ACCESS_DISABLED_NO_SAFER_UI_BY_POLICY = 0X00000312;

    /**
     * A valid hibernation file has been invalidated and   should be abandoned.
     */
    public static final int ERROR_ABANDON_HIBERFILE = 0X00000313;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs; the data has been lost. This error can be caused   by network connectivity issues. Try to save this file elsewhere.
     */
    public static final int ERROR_LOST_WRITEBEHIND_DATA_NETWORK_DISCONNECTED = 0X00000314;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs; the data has been lost. This error was returned by   the server on which the file exists. Try to save this file elsewhere.
     */
    public static final int ERROR_LOST_WRITEBEHIND_DATA_NETWORK_SERVER_ERROR = 0X00000315;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs; the data has been lost. This error can be caused   if the device has been removed or the media is write-protected.
     */
    public static final int ERROR_LOST_WRITEBEHIND_DATA_LOCAL_DISK_ERROR = 0X00000316;

    /**
     * Access to the extended attribute was denied.
     */
    public static final int ERROR_EA_ACCESS_DENIED = 0X000003E2;

    /**
     * The I/O operation has been aborted because of either a   thread exit or an application request.
     */
    public static final int ERROR_OPERATION_ABORTED = 0X000003E3;

    /**
     * Overlapped I/O event is not in a signaled state.
     */
    public static final int ERROR_IO_INCOMPLETE = 0X000003E4;

    /**
     * Overlapped I/O operation is in progress.
     */
    public static final int ERROR_IO_PENDING = 0X000003E5;

    /**
     * Invalid access to memory location.
     */
    public static final int ERROR_NOACCESS = 0X000003E6;

    /**
     * Error performing in-page operation.
     */
    public static final int ERROR_SWAPERROR = 0X000003E7;

    /**
     * Recursion too deep; the stack overflowed.
     */
    public static final int ERROR_STACK_OVERFLOW = 0X000003E9;

    /**
     * The window cannot act on the sent message.
     */
    public static final int ERROR_INVALID_MESSAGE = 0X000003EA;

    /**
     * Cannot complete this function.
     */
    public static final int ERROR_CAN_NOT_COMPLETE = 0X000003EB;

    /**
     * Invalid flags.
     */
    public static final int ERROR_INVALID_FLAGS = 0X000003EC;

    /**
     * The volume does not contain a recognized file system.   Be sure that all required file system drivers are loaded and that the volume   is not corrupted.
     */
    public static final int ERROR_UNRECOGNIZED_VOLUME = 0X000003ED;

    /**
     * The volume for a file has been externally altered so   that the opened file is no longer valid.
     */
    public static final int ERROR_FILE_INVALID = 0X000003EE;

    /**
     * The requested operation cannot be performed in   full-screen mode.
     */
    public static final int ERROR_FULLSCREEN_MODE = 0X000003EF;

    /**
     * An attempt was made to reference a token that does not   exist.
     */
    public static final int ERROR_NO_TOKEN = 0X000003F0;

    /**
     * The configuration registry database is corrupt.
     */
    public static final int ERROR_BADDB = 0X000003F1;

    /**
     * The configuration registry key is invalid.
     */
    public static final int ERROR_BADKEY = 0X000003F2;

    /**
     * The configuration registry key could not be opened.
     */
    public static final int ERROR_CANTOPEN = 0X000003F3;

    /**
     * The configuration registry key could not be read.
     */
    public static final int ERROR_CANTREAD = 0X000003F4;

    /**
     * The configuration registry key could not be written.
     */
    public static final int ERROR_CANTWRITE = 0X000003F5;

    /**
     * One of the files in the registry database had to be   recovered by use of a log or alternate copy. The recovery was successful.
     */
    public static final int ERROR_REGISTRY_RECOVERED = 0X000003F6;

    /**
     * The registry is corrupted. The structure of one of the   files containing registry data is corrupted, or the system\'s memory image of   the file is corrupted, or the file could not be recovered because the
     * alternate copy or log was absent or corrupted.
     */
    public static final int ERROR_REGISTRY_CORRUPT = 0X000003F7;

    /**
     * An I/O operation initiated by the registry failed and   cannot be recovered. The registry could not read in, write out, or flush one   of the files that contain the system\'s image of the registry.
     */
    public static final int ERROR_REGISTRY_IO_FAILED = 0X000003F8;

    /**
     * The system attempted to load or restore a file into   the registry, but the specified file is not in a registry file format.
     */
    public static final int ERROR_NOT_REGISTRY_FILE = 0X000003F9;

    /**
     * Illegal operation attempted on a registry key that has   been marked for deletion.
     */
    public static final int ERROR_KEY_DELETED = 0X000003FA;

    /**
     * System could not allocate the required space in a   registry log.
     */
    public static final int ERROR_NO_LOG_SPACE = 0X000003FB;

    /**
     * Cannot create a symbolic link in a registry key that   already has subkeys or values.
     */
    public static final int ERROR_KEY_HAS_CHILDREN = 0X000003FC;

    /**
     * Cannot create a stable subkey under a volatile parent   key.
     */
    public static final int ERROR_CHILD_MUST_BE_VOLATILE = 0X000003FD;

    /**
     * A notify change request is being completed and the   information is not being returned in the caller\'s buffer. The caller now   needs to enumerate the files to find the changes.
     */
    public static final int ERROR_NOTIFY_ENUM_DIR = 0X000003FE;

    /**
     * A stop control has been sent to a service that other   running services are dependent on.
     */
    public static final int ERROR_DEPENDENT_SERVICES_RUNNING = 0X0000041B;

    /**
     * The requested control is not valid for this service.
     */
    public static final int ERROR_INVALID_SERVICE_CONTROL = 0X0000041C;

    /**
     * The service did not respond to the start or control   request in a timely fashion.
     */
    public static final int ERROR_SERVICE_REQUEST_TIMEOUT = 0X0000041D;

    /**
     * A thread could not be created for the service.
     */
    public static final int ERROR_SERVICE_NO_THREAD = 0X0000041E;

    /**
     * The service database is locked.
     */
    public static final int ERROR_SERVICE_DATABASE_LOCKED = 0X0000041F;

    /**
     * An instance of the service is already running.
     */
    public static final int ERROR_SERVICE_ALREADY_RUNNING = 0X00000420;

    /**
     * The account name is invalid or does not exist, or the   password is invalid for the account name specified.
     */
    public static final int ERROR_INVALID_SERVICE_ACCOUNT = 0X00000421;

    /**
     * The service cannot be started, either because it is   disabled or because it has no enabled devices associated with it.
     */
    public static final int ERROR_SERVICE_DISABLED = 0X00000422;

    /**
     * Circular service dependency was specified.
     */
    public static final int ERROR_CIRCULAR_DEPENDENCY = 0X00000423;

    /**
     * The specified service does not exist as an installed   service.
     */
    public static final int ERROR_SERVICE_DOES_NOT_EXIST = 0X00000424;

    /**
     * The service cannot accept control messages at this   time.
     */
    public static final int ERROR_SERVICE_CANNOT_ACCEPT_CTRL = 0X00000425;

    /**
     * The service has not been started.
     */
    public static final int ERROR_SERVICE_NOT_ACTIVE = 0X00000426;

    /**
     * The service process could not connect to the service   controller.
     */
    public static final int ERROR_FAILED_SERVICE_CONTROLLER_CONNECT = 0X00000427;

    /**
     * An exception occurred in the service when handling the   control request.
     */
    public static final int ERROR_EXCEPTION_IN_SERVICE = 0X00000428;

    /**
     * The database specified does not exist.
     */
    public static final int ERROR_DATABASE_DOES_NOT_EXIST = 0X00000429;

    /**
     * The service has returned a service-specific error   code.
     */
    public static final int ERROR_SERVICE_SPECIFIC_ERROR = 0X0000042A;

    /**
     * The process terminated unexpectedly.
     */
    public static final int ERROR_PROCESS_ABORTED = 0X0000042B;

    /**
     * The dependency service or group failed to start.
     */
    public static final int ERROR_SERVICE_DEPENDENCY_FAIL = 0X0000042C;

    /**
     * The service did not start due to a logon failure.
     */
    public static final int ERROR_SERVICE_LOGON_FAILED = 0X0000042D;

    /**
     * After starting, the service stopped responding in a   start-pending state.
     */
    public static final int ERROR_SERVICE_START_HANG = 0X0000042E;

    /**
     * The specified service database lock is invalid.
     */
    public static final int ERROR_INVALID_SERVICE_LOCK = 0X0000042F;

    /**
     * The specified service has been marked for deletion.
     */
    public static final int ERROR_SERVICE_MARKED_FOR_DELETE = 0X00000430;

    /**
     * The specified service already exists.
     */
    public static final int ERROR_SERVICE_EXISTS = 0X00000431;

    /**
     * The system is currently running with the   last-known-good configuration.
     */
    public static final int ERROR_ALREADY_RUNNING_LKG = 0X00000432;

    /**
     * The dependency service does not exist or has been   marked for deletion.
     */
    public static final int ERROR_SERVICE_DEPENDENCY_DELETED = 0X00000433;

    /**
     * The current boot has already been accepted for use as   the last-known-good control set.
     */
    public static final int ERROR_BOOT_ALREADY_ACCEPTED = 0X00000434;

    /**
     * No attempts to start the service have been made since   the last boot.
     */
    public static final int ERROR_SERVICE_NEVER_STARTED = 0X00000435;

    /**
     * The name is already in use as either a service name or   a service display name.
     */
    public static final int ERROR_DUPLICATE_SERVICE_NAME = 0X00000436;

    /**
     * The account specified for this service is different   from the account specified for other services running in the same process.
     */
    public static final int ERROR_DIFFERENT_SERVICE_ACCOUNT = 0X00000437;

    /**
     * Failure actions can only be set for Win32 services,   not for drivers.
     */
    public static final int ERROR_CANNOT_DETECT_DRIVER_FAILURE = 0X00000438;

    /**
     * This service runs in the same process as the service   control manager. Therefore, the service control manager cannot take action if   this service\'s process terminates unexpectedly.
     */
    public static final int ERROR_CANNOT_DETECT_PROCESS_ABORT = 0X00000439;

    /**
     * No recovery program has been configured for this   service.
     */
    public static final int ERROR_NO_RECOVERY_PROGRAM = 0X0000043A;

    /**
     * The executable program that this service is configured   to run in does not implement the service.
     */
    public static final int ERROR_SERVICE_NOT_IN_EXE = 0X0000043B;

    /**
     * This service cannot be started in Safe Mode.
     */
    public static final int ERROR_NOT_SAFEBOOT_SERVICE = 0X0000043C;

    /**
     * The physical end of the tape has been reached.
     */
    public static final int ERROR_END_OF_MEDIA = 0X0000044C;

    /**
     * A tape access reached a filemark.
     */
    public static final int ERROR_FILEMARK_DETECTED = 0X0000044D;

    /**
     * The beginning of the tape or a partition was   encountered.
     */
    public static final int ERROR_BEGINNING_OF_MEDIA = 0X0000044E;

    /**
     * A tape access reached the end of a set of files.
     */
    public static final int ERROR_SETMARK_DETECTED = 0X0000044F;

    /**
     * No more data is on the tape.
     */
    public static final int ERROR_NO_DATA_DETECTED = 0X00000450;

    /**
     * Tape could not be partitioned.
     */
    public static final int ERROR_PARTITION_FAILURE = 0X00000451;

    /**
     * When accessing a new tape of a multivolume partition,   the current block size is incorrect.
     */
    public static final int ERROR_INVALID_BLOCK_LENGTH = 0X00000452;

    /**
     * Tape partition information could not be found when   loading a tape.
     */
    public static final int ERROR_DEVICE_NOT_PARTITIONED = 0X00000453;

    /**
     * Unable to lock the media eject mechanism.
     */
    public static final int ERROR_UNABLE_TO_LOCK_MEDIA = 0X00000454;

    /**
     * Unable to unload the media.
     */
    public static final int ERROR_UNABLE_TO_UNLOAD_MEDIA = 0X00000455;

    /**
     * The media in the drive might have changed.
     */
    public static final int ERROR_MEDIA_CHANGED = 0X00000456;

    /**
     * The I/O bus was reset.
     */
    public static final int ERROR_BUS_RESET = 0X00000457;

    /**
     * No media in drive.
     */
    public static final int ERROR_NO_MEDIA_IN_DRIVE = 0X00000458;

    /**
     * No mapping for the Unicode character exists in the   target multibyte code page.
     */
    public static final int ERROR_NO_UNICODE_TRANSLATION = 0X00000459;

    /**
     * A DLL initialization routine failed.
     */
    public static final int ERROR_DLL_INIT_FAILED = 0X0000045A;

    /**
     * A system shutdown is in progress.
     */
    public static final int ERROR_SHUTDOWN_IN_PROGRESS = 0X0000045B;

    /**
     * Unable to abort the system shutdown because no   shutdown was in progress.
     */
    public static final int ERROR_NO_SHUTDOWN_IN_PROGRESS = 0X0000045C;

    /**
     * The request could not be performed because of an I/O   device error.
     */
    public static final int ERROR_IO_DEVICE = 0X0000045D;

    /**
     * No serial device was successfully initialized. The   serial driver will unload.
     */
    public static final int ERROR_SERIAL_NO_DEVICE = 0X0000045E;

    /**
     * Unable to open a device that was sharing an IRQ with   other devices. At least one other device that uses that IRQ was already   opened.
     */
    public static final int ERROR_IRQ_BUSY = 0X0000045F;

    /**
     * A serial I/O operation was completed by another write   to the serial port. (The IOCTL_SERIAL_XOFF_COUNTER reached zero.)
     */
    public static final int ERROR_MORE_WRITES = 0X00000460;

    /**
     * A serial I/O operation completed because the time-out   period expired. (The IOCTL_SERIAL_XOFF_COUNTER did not reach zero.)
     */
    public static final int ERROR_COUNTER_TIMEOUT = 0X00000461;

    /**
     * No ID address mark was found on the floppy disk.
     */
    public static final int ERROR_FLOPPY_ID_MARK_NOT_FOUND = 0X00000462;

    /**
     * Mismatch between the floppy disk sector ID field and   the floppy disk controller track address.
     */
    public static final int ERROR_FLOPPY_WRONG_CYLINDER = 0X00000463;

    /**
     * The floppy disk controller reported an error that is   not recognized by the floppy disk driver.
     */
    public static final int ERROR_FLOPPY_UNKNOWN_ERROR = 0X00000464;

    /**
     * The floppy disk controller returned inconsistent   results in its registers.
     */
    public static final int ERROR_FLOPPY_BAD_REGISTERS = 0X00000465;

    /**
     * While accessing the hard disk, a recalibrate operation   failed, even after retries.
     */
    public static final int ERROR_DISK_RECALIBRATE_FAILED = 0X00000466;

    /**
     * While accessing the hard disk, a disk operation failed   even after retries.
     */
    public static final int ERROR_DISK_OPERATION_FAILED = 0X00000467;

    /**
     * While accessing the hard disk, a disk controller reset   was needed, but that also failed.
     */
    public static final int ERROR_DISK_RESET_FAILED = 0X00000468;

    /**
     * Physical end of tape encountered.
     */
    public static final int ERROR_EOM_OVERFLOW = 0X00000469;

    /**
     * Not enough server storage is available to process this   command.
     */
    public static final int ERROR_NOT_ENOUGH_SERVER_MEMORY = 0X0000046A;

    /**
     * A potential deadlock condition has been detected.
     */
    public static final int ERROR_POSSIBLE_DEADLOCK = 0X0000046B;

    /**
     * The base address or the file offset specified does not   have the proper alignment.
     */
    public static final int ERROR_MAPPED_ALIGNMENT = 0X0000046C;

    /**
     * An attempt to change the system power state was vetoed   by another application or driver.
     */
    public static final int ERROR_SET_POWER_STATE_VETOED = 0X00000474;

    /**
     * The system BIOS failed an attempt to change the system   power state.
     */
    public static final int ERROR_SET_POWER_STATE_FAILED = 0X00000475;

    /**
     * An attempt was made to create more links on a file   than the file system supports.
     */
    public static final int ERROR_TOO_MANY_LINKS = 0X00000476;

    /**
     * The specified program requires a newer version of   Windows.
     */
    public static final int ERROR_OLD_WIN_VERSION = 0X0000047E;

    /**
     * The specified program is not a Windows or MS-DOS   program.
     */
    public static final int ERROR_APP_WRONG_OS = 0X0000047F;

    /**
     * Cannot start more than one instance of the specified   program.
     */
    public static final int ERROR_SINGLE_INSTANCE_APP = 0X00000480;

    /**
     * The specified program was written for an earlier   version of Windows.
     */
    public static final int ERROR_RMODE_APP = 0X00000481;

    /**
     * One of the library files needed to run this application   is damaged.
     */
    public static final int ERROR_INVALID_DLL = 0X00000482;

    /**
     * No application is associated with the specified file   for this operation.
     */
    public static final int ERROR_NO_ASSOCIATION = 0X00000483;

    /**
     * An error occurred in sending the command to the   application.
     */
    public static final int ERROR_DDE_FAIL = 0X00000484;

    /**
     * One of the library files needed to run this   application cannot be found.
     */
    public static final int ERROR_DLL_NOT_FOUND = 0X00000485;

    /**
     * The current process has used all of its system   allowance of handles for Windows manager objects.
     */
    public static final int ERROR_NO_MORE_USER_HANDLES = 0X00000486;

    /**
     * The message can be used only with synchronous   operations.
     */
    public static final int ERROR_MESSAGE_SYNC_ONLY = 0X00000487;

    /**
     * The indicated source element has no media.
     */
    public static final int ERROR_SOURCE_ELEMENT_EMPTY = 0X00000488;

    /**
     * The indicated destination element already contains   media.
     */
    public static final int ERROR_DESTINATION_ELEMENT_FULL = 0X00000489;

    /**
     * The indicated element does not exist.
     */
    public static final int ERROR_ILLEGAL_ELEMENT_ADDRESS = 0X0000048A;

    /**
     * The indicated element is part of a magazine that is   not present.
     */
    public static final int ERROR_MAGAZINE_NOT_PRESENT = 0X0000048B;

    /**
     * The indicated device requires re-initialization due to   hardware errors.
     */
    public static final int ERROR_DEVICE_REINITIALIZATION_NEEDED = 0X0000048C;

    /**
     * The device has indicated that cleaning is required   before further operations are attempted.
     */
    public static final int ERROR_DEVICE_REQUIRES_CLEANING = 0X0000048D;

    /**
     * The device has indicated that its door is open.
     */
    public static final int ERROR_DEVICE_DOOR_OPEN = 0X0000048E;

    /**
     * The device is not connected.
     */
    public static final int ERROR_DEVICE_NOT_CONNECTED = 0X0000048F;

    /**
     * Element not found.
     */
    public static final int ERROR_NOT_FOUND = 0X00000490;

    /**
     * There was no match for the specified key in the index.
     */
    public static final int ERROR_NO_MATCH = 0X00000491;

    /**
     * The property set specified does not exist on the   object.
     */
    public static final int ERROR_SET_NOT_FOUND = 0X00000492;

    /**
     * The point passed to GetMouseMovePoints is not in the   buffer.
     */
    public static final int ERROR_POINT_NOT_FOUND = 0X00000493;

    /**
     * The tracking (workstation) service is not running.
     */
    public static final int ERROR_NO_TRACKING_SERVICE = 0X00000494;

    /**
     * The volume ID could not be found.
     */
    public static final int ERROR_NO_VOLUME_ID = 0X00000495;

    /**
     * Unable to remove the file to be replaced.
     */
    public static final int ERROR_UNABLE_TO_REMOVE_REPLACED = 0X00000497;

    /**
     * Unable to move the replacement file to the file to be   replaced. The file to be replaced has retained its original name.
     */
    public static final int ERROR_UNABLE_TO_MOVE_REPLACEMENT = 0X00000498;

    /**
     * Unable to move the replacement file to the file to be   replaced. The file to be replaced has been renamed using the backup name.
     */
    public static final int ERROR_UNABLE_TO_MOVE_REPLACEMENT_2 = 0X00000499;

    /**
     * The volume change journal is being deleted.
     */
    public static final int ERROR_JOURNAL_DELETE_IN_PROGRESS = 0X0000049A;

    /**
     * The volume change journal is not active.
     */
    public static final int ERROR_JOURNAL_NOT_ACTIVE = 0X0000049B;

    /**
     * A file was found, but it might not be the correct   file.
     */
    public static final int ERROR_POTENTIAL_FILE_FOUND = 0X0000049C;

    /**
     * The journal entry has been deleted from the journal.
     */
    public static final int ERROR_JOURNAL_ENTRY_DELETED = 0X0000049D;

    /**
     * A system shutdown has already been scheduled.
     */
    public static final int ERROR_SHUTDOWN_IS_SCHEDULED = 0X000004A6;

    /**
     * The system shutdown cannot be initiated because there   are other users logged on to the computer.
     */
    public static final int ERROR_SHUTDOWN_USERS_LOGGED_ON = 0X000004A7;

    /**
     * The specified device name is invalid.
     */
    public static final int ERROR_BAD_DEVICE = 0X000004B0;

    /**
     * The device is not currently connected but it is a   remembered connection.
     */
    public static final int ERROR_CONNECTION_UNAVAIL = 0X000004B1;

    /**
     * The local device name has a remembered connection to   another network resource.
     */
    public static final int ERROR_DEVICE_ALREADY_REMEMBERED = 0X000004B2;

    /**
     * The network path was either typed incorrectly, does   not exist, or the network provider is not currently available. Try retyping   the path or contact your network administrator.
     */
    public static final int ERROR_NO_NET_OR_BAD_PATH = 0X000004B3;

    /**
     * The specified network provider name is invalid.
     */
    public static final int ERROR_BAD_PROVIDER = 0X000004B4;

    /**
     * Unable to open the network connection profile.
     */
    public static final int ERROR_CANNOT_OPEN_PROFILE = 0X000004B5;

    /**
     * The network connection profile is corrupted.
     */
    public static final int ERROR_BAD_PROFILE = 0X000004B6;

    /**
     * Cannot enumerate a noncontainer.
     */
    public static final int ERROR_NOT_CONTAINER = 0X000004B7;

    /**
     * An extended error has occurred.
     */
    public static final int ERROR_EXTENDED_ERROR = 0X000004B8;

    /**
     * The format of the specified group name is invalid.
     */
    public static final int ERROR_INVALID_GROUPNAME = 0X000004B9;

    /**
     * The format of the specified computer name is invalid.
     */
    public static final int ERROR_INVALID_COMPUTERNAME = 0X000004BA;

    /**
     * The format of the specified event name is invalid.
     */
    public static final int ERROR_INVALID_EVENTNAME = 0X000004BB;

    /**
     * The format of the specified domain name is invalid.
     */
    public static final int ERROR_INVALID_DOMAINNAME = 0X000004BC;

    /**
     * The format of the specified service name is invalid.
     */
    public static final int ERROR_INVALID_SERVICENAME = 0X000004BD;

    /**
     * The format of the specified network name is invalid.
     */
    public static final int ERROR_INVALID_NETNAME = 0X000004BE;

    /**
     * The format of the specified share name is invalid.
     */
    public static final int ERROR_INVALID_SHARENAME = 0X000004BF;

    /**
     * The format of the specified password is invalid.
     */
    public static final int ERROR_INVALID_PASSWORDNAME = 0X000004C0;

    /**
     * The format of the specified message name is invalid.
     */
    public static final int ERROR_INVALID_MESSAGENAME = 0X000004C1;

    /**
     * The format of the specified message destination is   invalid.
     */
    public static final int ERROR_INVALID_MESSAGEDEST = 0X000004C2;

    /**
     * Multiple connections to a server or shared resource by   the same user, using more than one user name, are not allowed. Disconnect all   previous connections to the server or shared resource and try again.
     */
    public static final int ERROR_SESSION_CREDENTIAL_CONFLICT = 0X000004C3;

    /**
     * An attempt was made to establish a session to a   network server, but there are already too many sessions established to that   server.
     */
    public static final int ERROR_REMOTE_SESSION_LIMIT_EXCEEDED = 0X000004C4;

    /**
     * The workgroup or domain name is already in use by   another computer on the network.
     */
    public static final int ERROR_DUP_DOMAINNAME = 0X000004C5;

    /**
     * The network is not present or not started.
     */
    public static final int ERROR_NO_NETWORK = 0X000004C6;

    /**
     * The operation was canceled by the user.
     */
    public static final int ERROR_CANCELLED = 0X000004C7;

    /**
     * The requested operation cannot be performed on a file   with a user-mapped section open.
     */
    public static final int ERROR_USER_MAPPED_FILE = 0X000004C8;

    /**
     * The remote system refused the network connection.
     */
    public static final int ERROR_CONNECTION_REFUSED = 0X000004C9;

    /**
     * The network connection was gracefully closed.
     */
    public static final int ERROR_GRACEFUL_DISCONNECT = 0X000004CA;

    /**
     * The network transport endpoint already has an address   associated with it.
     */
    public static final int ERROR_ADDRESS_ALREADY_ASSOCIATED = 0X000004CB;

    /**
     * An address has not yet been associated with the   network endpoint.
     */
    public static final int ERROR_ADDRESS_NOT_ASSOCIATED = 0X000004CC;

    /**
     * An operation was attempted on a nonexistent network   connection.
     */
    public static final int ERROR_CONNECTION_INVALID = 0X000004CD;

    /**
     * An invalid operation was attempted on an active   network connection.
     */
    public static final int ERROR_CONNECTION_ACTIVE = 0X000004CE;

    /**
     * The network location cannot be reached. For information   about network troubleshooting, see Windows Help.
     */
    public static final int ERROR_NETWORK_UNREACHABLE = 0X000004CF;

    /**
     * The network location cannot be reached. For   information about network troubleshooting, see Windows Help.
     */
    public static final int ERROR_HOST_UNREACHABLE = 0X000004D0;

    /**
     * The network location cannot be reached. For   information about network troubleshooting, see Windows Help.
     */
    public static final int ERROR_PROTOCOL_UNREACHABLE = 0X000004D1;

    /**
     * No service is operating at the destination network   endpoint on the remote system.
     */
    public static final int ERROR_PORT_UNREACHABLE = 0X000004D2;

    /**
     * The request was aborted.
     */
    public static final int ERROR_REQUEST_ABORTED = 0X000004D3;

    /**
     * The network connection was aborted by the local   system.
     */
    public static final int ERROR_CONNECTION_ABORTED = 0X000004D4;

    /**
     * The operation could not be completed. A retry should   be performed.
     */
    public static final int ERROR_RETRY = 0X000004D5;

    /**
     * A connection to the server could not be made because   the limit on the number of concurrent connections for this account has been   reached.
     */
    public static final int ERROR_CONNECTION_COUNT_LIMIT = 0X000004D6;

    /**
     * Attempting to log on during an unauthorized time of   day for this account.
     */
    public static final int ERROR_LOGIN_TIME_RESTRICTION = 0X000004D7;

    /**
     * The account is not authorized to log on from this   station.
     */
    public static final int ERROR_LOGIN_WKSTA_RESTRICTION = 0X000004D8;

    /**
     * The network address could not be used for the   operation requested.
     */
    public static final int ERROR_INCORRECT_ADDRESS = 0X000004D9;

    /**
     * The service is already registered.
     */
    public static final int ERROR_ALREADY_REGISTERED = 0X000004DA;

    /**
     * The specified service does not exist.
     */
    public static final int ERROR_SERVICE_NOT_FOUND = 0X000004DB;

    /**
     * The operation being requested was not performed   because the user has not been authenticated.
     */
    public static final int ERROR_NOT_AUTHENTICATED = 0X000004DC;

    /**
     * The operation being requested was not performed   because the user has not logged on to the network. The specified service does   not exist.
     */
    public static final int ERROR_NOT_LOGGED_ON = 0X000004DD;

    /**
     * Continue with work in progress.
     */
    public static final int ERROR_CONTINUE = 0X000004DE;

    /**
     * An attempt was made to perform an initialization   operation when initialization has already been completed.
     */
    public static final int ERROR_ALREADY_INITIALIZED = 0X000004DF;

    /**
     * No more local devices.
     */
    public static final int ERROR_NO_MORE_DEVICES = 0X000004E0;

    /**
     * The specified site does not exist.
     */
    public static final int ERROR_NO_SUCH_SITE = 0X000004E1;

    /**
     * A domain controller with the specified name already   exists.
     */
    public static final int ERROR_DOMAIN_CONTROLLER_EXISTS = 0X000004E2;

    /**
     * This operation is supported only when you are   connected to the server.
     */
    public static final int ERROR_ONLY_IF_CONNECTED = 0X000004E3;

    /**
     * The group policy framework should call the extension   even if there are no changes.
     */
    public static final int ERROR_OVERRIDE_NOCHANGES = 0X000004E4;

    /**
     * The specified user does not have a valid profile.
     */
    public static final int ERROR_BAD_USER_PROFILE = 0X000004E5;

    /**
     * This operation is not supported on a computer running   Windows Server 2003 operating system for Small Business Server.
     */
    public static final int ERROR_NOT_SUPPORTED_ON_SBS = 0X000004E6;

    /**
     * The server machine is shutting down.
     */
    public static final int ERROR_SERVER_SHUTDOWN_IN_PROGRESS = 0X000004E7;

    /**
     * The remote system is not available. For information   about network troubleshooting, see Windows Help.
     */
    public static final int ERROR_HOST_DOWN = 0X000004E8;

    /**
     * The security identifier provided is not from an   account domain.
     */
    public static final int ERROR_NON_ACCOUNT_SID = 0X000004E9;

    /**
     * The security identifier provided does not have a   domain component.
     */
    public static final int ERROR_NON_DOMAIN_SID = 0X000004EA;

    /**
     * AppHelp dialog canceled, thus preventing the   application from starting.
     */
    public static final int ERROR_APPHELP_BLOCK = 0X000004EB;

    /**
     * This program is blocked by Group Policy. For more   information, contact your system administrator.
     */
    public static final int ERROR_ACCESS_DISABLED_BY_POLICY = 0X000004EC;

    /**
     * A program attempt to use an invalid register value.   Normally caused by an uninitialized register. This error is Itanium specific.
     */
    public static final int ERROR_REG_NAT_CONSUMPTION = 0X000004ED;

    /**
     * The share is currently offline or does not exist.
     */
    public static final int ERROR_CSCSHARE_OFFLINE = 0X000004EE;

    /**
     * The Kerberos protocol encountered an error while   validating the KDC certificate during smartcard logon. There is more   information in the system event log.
     */
    public static final int ERROR_PKINIT_FAILURE = 0X000004EF;

    /**
     * The Kerberos protocol encountered an error while   attempting to utilize the smartcard subsystem.
     */
    public static final int ERROR_SMARTCARD_SUBSYSTEM_FAILURE = 0X000004F0;

    /**
     * The system detected a possible attempt to compromise   security. Ensure that you can contact the server that authenticated you.
     */
    public static final int ERROR_DOWNGRADE_DETECTED = 0X000004F1;

    /**
     * The machine is locked and cannot be shut down without   the force option.
     */
    public static final int ERROR_MACHINE_LOCKED = 0X000004F7;

    /**
     * An application-defined callback gave invalid data when   called.
     */
    public static final int ERROR_CALLBACK_SUPPLIED_INVALID_DATA = 0X000004F9;

    /**
     * The Group Policy framework should call the extension   in the synchronous foreground policy refresh.
     */
    public static final int ERROR_SYNC_FOREGROUND_REFRESH_REQUIRED = 0X000004FA;

    /**
     * This driver has been blocked from loading.
     */
    public static final int ERROR_DRIVER_BLOCKED = 0X000004FB;

    /**
     * A DLL referenced a module that was neither a DLL nor   the process\'s executable image.
     */
    public static final int ERROR_INVALID_IMPORT_OF_NON_DLL = 0X000004FC;

    /**
     * Windows cannot open this program because it has been   disabled.
     */
    public static final int ERROR_ACCESS_DISABLED_WEBBLADE = 0X000004FD;

    /**
     * Windows cannot open this program because the license   enforcement system has been tampered with or become corrupted.
     */
    public static final int ERROR_ACCESS_DISABLED_WEBBLADE_TAMPER = 0X000004FE;

    /**
     * A transaction recover failed.
     */
    public static final int ERROR_RECOVERY_FAILURE = 0X000004FF;

    /**
     * The current thread has already been converted to a   fiber.
     */
    public static final int ERROR_ALREADY_FIBER = 0X00000500;

    /**
     * The current thread has already been converted from a   fiber.
     */
    public static final int ERROR_ALREADY_THREAD = 0X00000501;

    /**
     * The system detected an overrun of a stack-based buffer   in this application. This overrun could potentially allow a malicious user to   gain control of this application.
     */
    public static final int ERROR_STACK_BUFFER_OVERRUN = 0X00000502;

    /**
     * Data present in one of the parameters is more than the   function can operate on.
     */
    public static final int ERROR_PARAMETER_QUOTA_EXCEEDED = 0X00000503;

    /**
     * An attempt to perform an operation on a debug object   failed because the object is in the process of being deleted.
     */
    public static final int ERROR_DEBUGGER_INACTIVE = 0X00000504;

    /**
     * An attempt to delay-load a .dll or get a function   address in a delay-loaded .dll failed.
     */
    public static final int ERROR_DELAY_LOAD_FAILED = 0X00000505;

    /**
     * %1 is a 16-bit application. You do not have   permissions to execute 16-bit applications. Check your permissions with your   system administrator.
     */
    public static final int ERROR_VDM_DISALLOWED = 0X00000506;

    /**
     * Insufficient information exists to identify the cause   of failure.
     */
    public static final int ERROR_UNIDENTIFIED_ERROR = 0X00000507;

    /**
     * None
     */
    public static final int ERROR_INVALID_CRUNTIME_PARAMETER = 0X00000508;

    /**
     * The operation occurred beyond the valid data length of   the file.
     */
    public static final int ERROR_BEYOND_VDL = 0X00000509;

    /**
     * The service start failed because one or more services   in the same process have an incompatible service SID type setting. A service   with a restricted service SID type can only coexist in the same process with
     * other services with a restricted SID type.
     */
    public static final int ERROR_INCOMPATIBLE_SERVICE_SID_TYPE = 0X0000050A;

    /**
     * The process hosting the driver for this device has   been terminated.
     */
    public static final int ERROR_DRIVER_PROCESS_TERMINATED = 0X0000050B;

    /**
     * An operation attempted to exceed an   implementation-defined limit.
     */
    public static final int ERROR_IMPLEMENTATION_LIMIT = 0X0000050C;

    /**
     * Either the target process, or the target thread\'s   containing process, is a protected process.
     */
    public static final int ERROR_PROCESS_IS_PROTECTED = 0X0000050D;

    /**
     * The service notification client is lagging too far   behind the current state of services in the machine.
     */
    public static final int ERROR_SERVICE_NOTIFY_CLIENT_LAGGING = 0X0000050E;

    /**
     * An operation failed because the storage quota was   exceeded.
     */
    public static final int ERROR_DISK_QUOTA_EXCEEDED = 0X0000050F;

    /**
     * An operation failed because the content was blocked.
     */
    public static final int ERROR_CONTENT_BLOCKED = 0X00000510;

    /**
     * A privilege that the service requires to function   properly does not exist in the service account configuration. The Services   Microsoft Management Console (MMC) snap-in (Services.msc) and the Local   Security
     * Settings MMC snap-in (Secpol.msc) can be used to view the service   configuration and the account configuration.
     */
    public static final int ERROR_INCOMPATIBLE_SERVICE_PRIVILEGE = 0X00000511;

    /**
     * Indicates a particular SID cannot be assigned as the   label of an object.
     */
    public static final int ERROR_INVALID_LABEL = 0X00000513;

    /**
     * Not all privileges or groups referenced are assigned   to the caller.
     */
    public static final int ERROR_NOT_ALL_ASSIGNED = 0X00000514;

    /**
     * Some mapping between account names and SIDs was not   done.
     */
    public static final int ERROR_SOME_NOT_MAPPED = 0X00000515;

    /**
     * No system quota limits are specifically set for this   account.
     */
    public static final int ERROR_NO_QUOTAS_FOR_ACCOUNT = 0X00000516;

    /**
     * No encryption key is available. A well-known   encryption key was returned.
     */
    public static final int ERROR_LOCAL_USER_SESSION_KEY = 0X00000517;

    /**
     * The password is too complex to be converted to a LAN   Manager password. The LAN Manager password returned is a null string.
     */
    public static final int ERROR_NULL_LM_PASSWORD = 0X00000518;

    /**
     * The revision level is unknown.
     */
    public static final int ERROR_UNKNOWN_REVISION = 0X00000519;

    /**
     * Indicates two revision levels are incompatible.
     */
    public static final int ERROR_REVISION_MISMATCH = 0X0000051A;

    /**
     * This SID cannot be assigned as the owner of this   object.
     */
    public static final int ERROR_INVALID_OWNER = 0X0000051B;

    /**
     * This SID cannot be assigned as the primary group of an   object.
     */
    public static final int ERROR_INVALID_PRIMARY_GROUP = 0X0000051C;

    /**
     * An attempt has been made to operate on an   impersonation token by a thread that is not currently impersonating a client.
     */
    public static final int ERROR_NO_IMPERSONATION_TOKEN = 0X0000051D;

    /**
     * The group cannot be disabled.
     */
    public static final int ERROR_CANT_DISABLE_MANDATORY = 0X0000051E;

    /**
     * There are currently no logon servers available to   service the logon request.
     */
    public static final int ERROR_NO_LOGON_SERVERS = 0X0000051F;

    /**
     * A specified logon session does not exist. It might   already have been terminated.
     */
    public static final int ERROR_NO_SUCH_LOGON_SESSION = 0X00000520;

    /**
     * A specified privilege does not exist.
     */
    public static final int ERROR_NO_SUCH_PRIVILEGE = 0X00000521;

    /**
     * A required privilege is not held by the client.
     */
    public static final int ERROR_PRIVILEGE_NOT_HELD = 0X00000522;

    /**
     * The name provided is not a properly formed account   name.
     */
    public static final int ERROR_INVALID_ACCOUNT_NAME = 0X00000523;

    /**
     * The specified account already exists.
     */
    public static final int ERROR_USER_EXISTS = 0X00000524;

    /**
     * The specified account does not exist.
     */
    public static final int ERROR_NO_SUCH_USER = 0X00000525;

    /**
     * The specified group already exists.
     */
    public static final int ERROR_GROUP_EXISTS = 0X00000526;

    /**
     * The specified group does not exist.
     */
    public static final int ERROR_NO_SUCH_GROUP = 0X00000527;

    /**
     * Either the specified user account is already a member   of the specified group, or the specified group cannot be deleted because it   contains a member.
     */
    public static final int ERROR_MEMBER_IN_GROUP = 0X00000528;

    /**
     * The specified user account is not a member of the   specified group account.
     */
    public static final int ERROR_MEMBER_NOT_IN_GROUP = 0X00000529;

    /**
     * The last remaining administration account cannot be   disabled or deleted.
     */
    public static final int ERROR_LAST_ADMIN = 0X0000052A;

    /**
     * Unable to update the password. The value provided as   the current password is incorrect.
     */
    public static final int ERROR_WRONG_PASSWORD = 0X0000052B;

    /**
     * Unable to update the password. The value provided for   the new password contains values that are not allowed in passwords.
     */
    public static final int ERROR_ILL_FORMED_PASSWORD = 0X0000052C;

    /**
     * Unable to update the password. The value provided for   the new password does not meet the length, complexity, or history   requirements of the domain.
     */
    public static final int ERROR_PASSWORD_RESTRICTION = 0X0000052D;

    /**
     * Logon failure: Unknown user name or bad password.
     */
    public static final int ERROR_LOGON_FAILURE = 0X0000052E;

    /**
     * Logon failure: User account restriction. Possible   reasons are blank passwords not allowed, logon hour restrictions, or a policy   restriction has been enforced.
     */
    public static final int ERROR_ACCOUNT_RESTRICTION = 0X0000052F;

    /**
     * Logon failure: Account logon time restriction   violation.
     */
    public static final int ERROR_INVALID_LOGON_HOURS = 0X00000530;

    /**
     * Logon failure: User not allowed to log on to this   computer.
     */
    public static final int ERROR_INVALID_WORKSTATION = 0X00000531;

    /**
     * Logon failure: The specified account password has   expired.
     */
    public static final int ERROR_PASSWORD_EXPIRED = 0X00000532;

    /**
     * Logon failure: Account currently disabled.
     */
    public static final int ERROR_ACCOUNT_DISABLED = 0X00000533;

    /**
     * No mapping between account names and SIDs was done.
     */
    public static final int ERROR_NONE_MAPPED = 0X00000534;

    /**
     * Too many local user identifiers (LUIDs) were requested   at one time.
     */
    public static final int ERROR_TOO_MANY_LUIDS_REQUESTED = 0X00000535;

    /**
     * No more LUIDs are available.
     */
    public static final int ERROR_LUIDS_EXHAUSTED = 0X00000536;

    /**
     * The sub-authority part of an SID is invalid for this   particular use.
     */
    public static final int ERROR_INVALID_SUB_AUTHORITY = 0X00000537;

    /**
     * The ACL structure is invalid.
     */
    public static final int ERROR_INVALID_ACL = 0X00000538;

    /**
     * The SID structure is invalid.
     */
    public static final int ERROR_INVALID_SID = 0X00000539;

    /**
     * The security descriptor structure is invalid.
     */
    public static final int ERROR_INVALID_SECURITY_DESCR = 0X0000053A;

    /**
     * The inherited ACL or ACE could not be built.
     */
    public static final int ERROR_BAD_INHERITANCE_ACL = 0X0000053C;

    /**
     * The server is currently disabled.
     */
    public static final int ERROR_SERVER_DISABLED = 0X0000053D;

    /**
     * The server is currently enabled.
     */
    public static final int ERROR_SERVER_NOT_DISABLED = 0X0000053E;

    /**
     * The value provided was an invalid value for an   identifier authority.
     */
    public static final int ERROR_INVALID_ID_AUTHORITY = 0X0000053F;

    /**
     * No more memory is available for security information   updates.
     */
    public static final int ERROR_ALLOTTED_SPACE_EXCEEDED = 0X00000540;

    /**
     * The specified attributes are invalid, or incompatible   with the attributes for the group as a whole.
     */
    public static final int ERROR_INVALID_GROUP_ATTRIBUTES = 0X00000541;

    /**
     * Either a required impersonation level was not   provided, or the provided impersonation level is invalid.
     */
    public static final int ERROR_BAD_IMPERSONATION_LEVEL = 0X00000542;

    /**
     * Cannot open an anonymous level security token.
     */
    public static final int ERROR_CANT_OPEN_ANONYMOUS = 0X00000543;

    /**
     * The validation information class requested was   invalid.
     */
    public static final int ERROR_BAD_VALIDATION_CLASS = 0X00000544;

    /**
     * The type of the token is inappropriate for its   attempted use.
     */
    public static final int ERROR_BAD_TOKEN_TYPE = 0X00000545;

    /**
     * Unable to perform a security operation on an object   that has no associated security.
     */
    public static final int ERROR_NO_SECURITY_ON_OBJECT = 0X00000546;

    /**
     * Configuration information could not be read from the   domain controller, either because the machine is unavailable, or access has   been denied.
     */
    public static final int ERROR_CANT_ACCESS_DOMAIN_INFO = 0X00000547;

    /**
     * The SAM or local security authority (LSA) server was   in the wrong state to perform the security operation.
     */
    public static final int ERROR_INVALID_SERVER_STATE = 0X00000548;

    /**
     * The domain was in the wrong state to perform the   security operation.
     */
    public static final int ERROR_INVALID_DOMAIN_STATE = 0X00000549;

    /**
     * This operation is only allowed for the PDC of the   domain.
     */
    public static final int ERROR_INVALID_DOMAIN_ROLE = 0X0000054A;

    /**
     * The specified domain either does not exist or could   not be contacted.
     */
    public static final int ERROR_NO_SUCH_DOMAIN = 0X0000054B;

    /**
     * The specified domain already exists.
     */
    public static final int ERROR_DOMAIN_EXISTS = 0X0000054C;

    /**
     * An attempt was made to exceed the limit on the number   of domains per server.
     */
    public static final int ERROR_DOMAIN_LIMIT_EXCEEDED = 0X0000054D;

    /**
     * Unable to complete the requested operation because of   either a catastrophic media failure or a data structure corruption on the   disk.
     */
    public static final int ERROR_INTERNAL_DB_CORRUPTION = 0X0000054E;

    /**
     * An internal error occurred.
     */
    public static final int ERROR_INTERNAL_ERROR = 0X0000054F;

    /**
     * Generic access types were contained in an access mask   that should already be mapped to nongeneric types.
     */
    public static final int ERROR_GENERIC_NOT_MAPPED = 0X00000550;

    /**
     * A security descriptor is not in the right format (absolute   or self-relative).
     */
    public static final int ERROR_BAD_DESCRIPTOR_FORMAT = 0X00000551;

    /**
     * The requested action is restricted for use by logon   processes only. The calling process has not registered as a logon process.
     */
    public static final int ERROR_NOT_LOGON_PROCESS = 0X00000552;

    /**
     * Cannot start a new logon session with an ID that is   already in use.
     */
    public static final int ERROR_LOGON_SESSION_EXISTS = 0X00000553;

    /**
     * A specified authentication package is unknown.
     */
    public static final int ERROR_NO_SUCH_PACKAGE = 0X00000554;

    /**
     * The logon session is not in a state that is consistent   with the requested operation.
     */
    public static final int ERROR_BAD_LOGON_SESSION_STATE = 0X00000555;

    /**
     * The logon session ID is already in use.
     */
    public static final int ERROR_LOGON_SESSION_COLLISION = 0X00000556;

    /**
     * A logon request contained an invalid logon type value.
     */
    public static final int ERROR_INVALID_LOGON_TYPE = 0X00000557;

    /**
     * Unable to impersonate using a named pipe until data   has been read from that pipe.
     */
    public static final int ERROR_CANNOT_IMPERSONATE = 0X00000558;

    /**
     * The transaction state of a registry subtree is   incompatible with the requested operation.
     */
    public static final int ERROR_RXACT_INVALID_STATE = 0X00000559;

    /**
     * An internal security database corruption has been encountered.
     */
    public static final int ERROR_RXACT_COMMIT_FAILURE = 0X0000055A;

    /**
     * Cannot perform this operation on built-in accounts.
     */
    public static final int ERROR_SPECIAL_ACCOUNT = 0X0000055B;

    /**
     * Cannot perform this operation on this built-in special   group.
     */
    public static final int ERROR_SPECIAL_GROUP = 0X0000055C;

    /**
     * Cannot perform this operation on this built-in special   user.
     */
    public static final int ERROR_SPECIAL_USER = 0X0000055D;

    /**
     * The user cannot be removed from a group because the   group is currently the user\'s primary group.
     */
    public static final int ERROR_MEMBERS_PRIMARY_GROUP = 0X0000055E;

    /**
     * The token is already in use as a primary token.
     */
    public static final int ERROR_TOKEN_ALREADY_IN_USE = 0X0000055F;

    /**
     * The specified local group does not exist.
     */
    public static final int ERROR_NO_SUCH_ALIAS = 0X00000560;

    /**
     * The specified account name is not a member of the   group.
     */
    public static final int ERROR_MEMBER_NOT_IN_ALIAS = 0X00000561;

    /**
     * The specified account name is already a member of the group.
     */
    public static final int ERROR_MEMBER_IN_ALIAS = 0X00000562;

    /**
     * The specified local group already exists.
     */
    public static final int ERROR_ALIAS_EXISTS = 0X00000563;

    /**
     * Logon failure: The user has not been granted the   requested logon type at this computer.
     */
    public static final int ERROR_LOGON_NOT_GRANTED = 0X00000564;

    /**
     * The maximum number of secrets that can be stored in a   single system has been exceeded.
     */
    public static final int ERROR_TOO_MANY_SECRETS = 0X00000565;

    /**
     * The length of a secret exceeds the maximum length   allowed.
     */
    public static final int ERROR_SECRET_TOO_LONG = 0X00000566;

    /**
     * The local security authority database contains an   internal inconsistency.
     */
    public static final int ERROR_INTERNAL_DB_ERROR = 0X00000567;

    /**
     * During a logon attempt, the user\'s security context   accumulated too many SIDs.
     */
    public static final int ERROR_TOO_MANY_CONTEXT_IDS = 0X00000568;

    /**
     * Logon failure: The user has not been granted the   requested logon type at this computer.
     */
    public static final int ERROR_LOGON_TYPE_NOT_GRANTED = 0X00000569;

    /**
     * A cross-encrypted password is necessary to change a   user password.
     */
    public static final int ERROR_NT_CROSS_ENCRYPTION_REQUIRED = 0X0000056A;

    /**
     * A member could not be added to or removed from the   local group because the member does not exist.
     */
    public static final int ERROR_NO_SUCH_MEMBER = 0X0000056B;

    /**
     * A new member could not be added to a local group   because the member has the wrong account type.
     */
    public static final int ERROR_INVALID_MEMBER = 0X0000056C;

    /**
     * Too many SIDs have been specified.
     */
    public static final int ERROR_TOO_MANY_SIDS = 0X0000056D;

    /**
     * A cross-encrypted password is necessary to change this   user password.
     */
    public static final int ERROR_LM_CROSS_ENCRYPTION_REQUIRED = 0X0000056E;

    /**
     * Indicates an ACL contains no inheritable components.
     */
    public static final int ERROR_NO_INHERITANCE = 0X0000056F;

    /**
     * The file or directory is corrupted and unreadable.
     */
    public static final int ERROR_FILE_CORRUPT = 0X00000570;

    /**
     * The disk structure is corrupted and unreadable.
     */
    public static final int ERROR_DISK_CORRUPT = 0X00000571;

    /**
     * There is no user session key for the specified logon   session.
     */
    public static final int ERROR_NO_USER_SESSION_KEY = 0X00000572;

    /**
     * The service being accessed is licensed for a   particular number of connections. No more connections can be made to the   service at this time because the service has accepted the maximum number of   connections.
     */
    public static final int ERROR_LICENSE_QUOTA_EXCEEDED = 0X00000573;

    /**
     * Logon failure: The target account name is incorrect.
     */
    public static final int ERROR_WRONG_TARGET_NAME = 0X00000574;

    /**
     * Mutual authentication failed. The server\'s password is   out of date at the domain controller.
     */
    public static final int ERROR_MUTUAL_AUTH_FAILED = 0X00000575;

    /**
     * There is a time and/or date difference between the   client and server.
     */
    public static final int ERROR_TIME_SKEW = 0X00000576;

    /**
     * This operation cannot be performed on the current   domain.
     */
    public static final int ERROR_CURRENT_DOMAIN_NOT_ALLOWED = 0X00000577;

    /**
     * Invalid window handle.
     */
    public static final int ERROR_INVALID_WINDOW_HANDLE = 0X00000578;

    /**
     * Invalid menu handle.
     */
    public static final int ERROR_INVALID_MENU_HANDLE = 0X00000579;

    /**
     * Invalid cursor handle.
     */
    public static final int ERROR_INVALID_CURSOR_HANDLE = 0X0000057A;

    /**
     * Invalid accelerator table handle.
     */
    public static final int ERROR_INVALID_ACCEL_HANDLE = 0X0000057B;

    /**
     * Invalid hook handle.
     */
    public static final int ERROR_INVALID_HOOK_HANDLE = 0X0000057C;

    /**
     * Invalid handle to a multiple-window position   structure.
     */
    public static final int ERROR_INVALID_DWP_HANDLE = 0X0000057D;

    /**
     * Cannot create a top-level child window.
     */
    public static final int ERROR_TLW_WITH_WSCHILD = 0X0000057E;

    /**
     * Cannot find window class.
     */
    public static final int ERROR_CANNOT_FIND_WND_CLASS = 0X0000057F;

    /**
     * Invalid window; it belongs to other thread.
     */
    public static final int ERROR_WINDOW_OF_OTHER_THREAD = 0X00000580;

    /**
     * Hot key is already registered.
     */
    public static final int ERROR_HOTKEY_ALREADY_REGISTERED = 0X00000581;

    /**
     * Class already exists.
     */
    public static final int ERROR_CLASS_ALREADY_EXISTS = 0X00000582;

    /**
     * Class does not exist.
     */
    public static final int ERROR_CLASS_DOES_NOT_EXIST = 0X00000583;

    /**
     * Class still has open windows.
     */
    public static final int ERROR_CLASS_HAS_WINDOWS = 0X00000584;

    /**
     * Invalid index.
     */
    public static final int ERROR_INVALID_INDEX = 0X00000585;

    /**
     * Invalid icon handle.
     */
    public static final int ERROR_INVALID_ICON_HANDLE = 0X00000586;

    /**
     * Using private DIALOG window words.
     */
    public static final int ERROR_PRIVATE_DIALOG_INDEX = 0X00000587;

    /**
     * The list box identifier was not found.
     */
    public static final int ERROR_LISTBOX_ID_NOT_FOUND = 0X00000588;

    /**
     * No wildcards were found.
     */
    public static final int ERROR_NO_WILDCARD_CHARACTERS = 0X00000589;

    /**
     * Thread does not have a clipboard open.
     */
    public static final int ERROR_CLIPBOARD_NOT_OPEN = 0X0000058A;

    /**
     * Hot key is not registered.
     */
    public static final int ERROR_HOTKEY_NOT_REGISTERED = 0X0000058B;

    /**
     * The window is not a valid dialog window.
     */
    public static final int ERROR_WINDOW_NOT_DIALOG = 0X0000058C;

    /**
     * Control ID not found.
     */
    public static final int ERROR_CONTROL_ID_NOT_FOUND = 0X0000058D;

    /**
     * Invalid message for a combo box because it does not   have an edit control.
     */
    public static final int ERROR_INVALID_COMBOBOX_MESSAGE = 0X0000058E;

    /**
     * The window is not a combo box.
     */
    public static final int ERROR_WINDOW_NOT_COMBOBOX = 0X0000058F;

    /**
     * Height must be less than 256.
     */
    public static final int ERROR_INVALID_EDIT_HEIGHT = 0X00000590;

    /**
     * Invalid device context (DC) handle.
     */
    public static final int ERROR_DC_NOT_FOUND = 0X00000591;

    /**
     * Invalid hook procedure type.
     */
    public static final int ERROR_INVALID_HOOK_FILTER = 0X00000592;

    /**
     * Invalid hook procedure.
     */
    public static final int ERROR_INVALID_FILTER_PROC = 0X00000593;

    /**
     * Cannot set nonlocal hook without a module handle.
     */
    public static final int ERROR_HOOK_NEEDS_HMOD = 0X00000594;

    /**
     * This hook procedure can only be set globally.
     */
    public static final int ERROR_GLOBAL_ONLY_HOOK = 0X00000595;

    /**
     * The journal hook procedure is already installed.
     */
    public static final int ERROR_JOURNAL_HOOK_SET = 0X00000596;

    /**
     * The hook procedure is not installed.
     */
    public static final int ERROR_HOOK_NOT_INSTALLED = 0X00000597;

    /**
     * Invalid message for single-selection list box.
     */
    public static final int ERROR_INVALID_LB_MESSAGE = 0X00000598;

    /**
     * LB_SETCOUNT sent to non-lazy list box.
     */
    public static final int ERROR_SETCOUNT_ON_BAD_LB = 0X00000599;

    /**
     * This list box does not support tab stops.
     */
    public static final int ERROR_LB_WITHOUT_TABSTOPS = 0X0000059A;

    /**
     * Cannot destroy object created by another thread.
     */
    public static final int ERROR_DESTROY_OBJECT_OF_OTHER_THREAD = 0X0000059B;

    /**
     * Child windows cannot have menus.
     */
    public static final int ERROR_CHILD_WINDOW_MENU = 0X0000059C;

    /**
     * The window does not have a system menu.
     */
    public static final int ERROR_NO_SYSTEM_MENU = 0X0000059D;

    /**
     * Invalid message box style.
     */
    public static final int ERROR_INVALID_MSGBOX_STYLE = 0X0000059E;

    /**
     * Invalid system-wide (SPI_*) parameter.
     */
    public static final int ERROR_INVALID_SPI_VALUE = 0X0000059F;

    /**
     * Screen already locked.
     */
    public static final int ERROR_SCREEN_ALREADY_LOCKED = 0X000005A0;

    /**
     * All handles to windows in a multiple-window position   structure must have the same parent.
     */
    public static final int ERROR_HWNDS_HAVE_DIFF_PARENT = 0X000005A1;

    /**
     * The window is not a child window.
     */
    public static final int ERROR_NOT_CHILD_WINDOW = 0X000005A2;

    /**
     * Invalid GW_* command.
     */
    public static final int ERROR_INVALID_GW_COMMAND = 0X000005A3;

    /**
     * Invalid thread identifier.
     */
    public static final int ERROR_INVALID_THREAD_ID = 0X000005A4;

    /**
     * Cannot process a message from a window that is not a   multiple document interface (MDI) window.
     */
    public static final int ERROR_NON_MDICHILD_WINDOW = 0X000005A5;

    /**
     * Pop-up menu already active.
     */
    public static final int ERROR_POPUP_ALREADY_ACTIVE = 0X000005A6;

    /**
     * The window does not have scroll bars.
     */
    public static final int ERROR_NO_SCROLLBARS = 0X000005A7;

    /**
     * Scroll bar range cannot be greater than MAXLONG.
     */
    public static final int ERROR_INVALID_SCROLLBAR_RANGE = 0X000005A8;

    /**
     * Cannot show or remove the window in the way specified.
     */
    public static final int ERROR_INVALID_SHOWWIN_COMMAND = 0X000005A9;

    /**
     * Insufficient system resources exist to complete the   requested service.
     */
    public static final int ERROR_NO_SYSTEM_RESOURCES = 0X000005AA;

    /**
     * Insufficient system resources exist to complete the   requested service.
     */
    public static final int ERROR_NONPAGED_SYSTEM_RESOURCES = 0X000005AB;

    /**
     * Insufficient system resources exist to complete the   requested service.
     */
    public static final int ERROR_PAGED_SYSTEM_RESOURCES = 0X000005AC;

    /**
     * Insufficient quota to complete the requested service.
     */
    public static final int ERROR_WORKING_SET_QUOTA = 0X000005AD;

    /**
     * Insufficient quota to complete the requested service.
     */
    public static final int ERROR_PAGEFILE_QUOTA = 0X000005AE;

    /**
     * The paging file is too small for this operation to   complete.
     */
    public static final int ERROR_COMMITMENT_LIMIT = 0X000005AF;

    /**
     * A menu item was not found.
     */
    public static final int ERROR_MENU_ITEM_NOT_FOUND = 0X000005B0;

    /**
     * Invalid keyboard layout handle.
     */
    public static final int ERROR_INVALID_KEYBOARD_HANDLE = 0X000005B1;

    /**
     * Hook type not allowed.
     */
    public static final int ERROR_HOOK_TYPE_NOT_ALLOWED = 0X000005B2;

    /**
     * This operation requires an interactive window station.
     */
    public static final int ERROR_REQUIRES_INTERACTIVE_WINDOWSTATION = 0X000005B3;

    /**
     * This operation returned because the time-out period   expired.
     */
    public static final int ERROR_TIMEOUT = 0X000005B4;

    /**
     * Invalid monitor handle.
     */
    public static final int ERROR_INVALID_MONITOR_HANDLE = 0X000005B5;

    /**
     * Incorrect size argument.
     */
    public static final int ERROR_INCORRECT_SIZE = 0X000005B6;

    /**
     * The symbolic link cannot be followed because its type   is disabled.
     */
    public static final int ERROR_SYMLINK_CLASS_DISABLED = 0X000005B7;

    /**
     * This application does not support the current   operation on symbolic links.
     */
    public static final int ERROR_SYMLINK_NOT_SUPPORTED = 0X000005B8;

    /**
     * The event log file is corrupted.
     */
    public static final int ERROR_EVENTLOG_FILE_CORRUPT = 0X000005DC;

    /**
     * No event log file could be opened, so the event   logging service did not start.
     */
    public static final int ERROR_EVENTLOG_CANT_START = 0X000005DD;

    /**
     * The event log file is full.
     */
    public static final int ERROR_LOG_FILE_FULL = 0X000005DE;

    /**
     * The event log file has changed between read   operations.
     */
    public static final int ERROR_EVENTLOG_FILE_CHANGED = 0X000005DF;

    /**
     * The specified task name is invalid.
     */
    public static final int ERROR_INVALID_TASK_NAME = 0X0000060E;

    /**
     * The specified task index is invalid.
     */
    public static final int ERROR_INVALID_TASK_INDEX = 0X0000060F;

    /**
     * The specified thread is already joining a task.
     */
    public static final int ERROR_THREAD_ALREADY_IN_TASK = 0X00000610;

    /**
     * The Windows Installer service could not be accessed.   This can occur if the Windows Installer is not correctly installed. Contact   your support personnel for assistance.
     */
    public static final int ERROR_INSTALL_SERVICE_FAILURE = 0X00000641;

    /**
     * User canceled installation.
     */
    public static final int ERROR_INSTALL_USEREXIT = 0X00000642;

    /**
     * Fatal error during installation.
     */
    public static final int ERROR_INSTALL_FAILURE = 0X00000643;

    /**
     * Installation suspended, incomplete.
     */
    public static final int ERROR_INSTALL_SUSPEND = 0X00000644;

    /**
     * This action is valid only for products that are   currently installed.
     */
    public static final int ERROR_UNKNOWN_PRODUCT = 0X00000645;

    /**
     * Feature ID not registered.
     */
    public static final int ERROR_UNKNOWN_FEATURE = 0X00000646;

    /**
     * Component ID not registered.
     */
    public static final int ERROR_UNKNOWN_COMPONENT = 0X00000647;

    /**
     * Unknown property.
     */
    public static final int ERROR_UNKNOWN_PROPERTY = 0X00000648;

    /**
     * Handle is in an invalid state.
     */
    public static final int ERROR_INVALID_HANDLE_STATE = 0X00000649;

    /**
     * The configuration data for this product is corrupt.   Contact your support personnel.
     */
    public static final int ERROR_BAD_CONFIGURATION = 0X0000064A;

    /**
     * Component qualifier not present.
     */
    public static final int ERROR_INDEX_ABSENT = 0X0000064B;

    /**
     * The installation source for this product is not   available. Verify that the source exists and that you can access it.
     */
    public static final int ERROR_INSTALL_SOURCE_ABSENT = 0X0000064C;

    /**
     * This installation package cannot be installed by the   Windows Installer service. You must install a Windows service pack that   contains a newer version of the Windows Installer service.
     */
    public static final int ERROR_INSTALL_PACKAGE_VERSION = 0X0000064D;

    /**
     * Product is uninstalled.
     */
    public static final int ERROR_PRODUCT_UNINSTALLED = 0X0000064E;

    /**
     * SQL query syntax invalid or unsupported.
     */
    public static final int ERROR_BAD_QUERY_SYNTAX = 0X0000064F;

    /**
     * Record field does not exist.
     */
    public static final int ERROR_INVALID_FIELD = 0X00000650;

    /**
     * The device has been removed.
     */
    public static final int ERROR_DEVICE_REMOVED = 0X00000651;

    /**
     * Another installation is already in progress. Complete   that installation before proceeding with this install.
     */
    public static final int ERROR_INSTALL_ALREADY_RUNNING = 0X00000652;

    /**
     * This installation package could not be opened. Verify   that the package exists and that you can access it, or contact the   application vendor to verify that this is a valid Windows Installer package.
     */
    public static final int ERROR_INSTALL_PACKAGE_OPEN_FAILED = 0X00000653;

    /**
     * This installation package could not be opened. Contact   the application vendor to verify that this is a valid Windows Installer   package.
     */
    public static final int ERROR_INSTALL_PACKAGE_INVALID = 0X00000654;

    /**
     * There was an error starting the Windows Installer   service user interface. Contact your support personnel.
     */
    public static final int ERROR_INSTALL_UI_FAILURE = 0X00000655;

    /**
     * Error opening installation log file. Verify that the   specified log file location exists and that you can write to it.
     */
    public static final int ERROR_INSTALL_LOG_FAILURE = 0X00000656;

    /**
     * The language of this installation package is not supported   by your system.
     */
    public static final int ERROR_INSTALL_LANGUAGE_UNSUPPORTED = 0X00000657;

    /**
     * Error applying transforms. Verify that the specified   transform paths are valid.
     */
    public static final int ERROR_INSTALL_TRANSFORM_FAILURE = 0X00000658;

    /**
     * This installation is forbidden by system policy.   Contact your system administrator.
     */
    public static final int ERROR_INSTALL_PACKAGE_REJECTED = 0X00000659;

    /**
     * Function could not be executed.
     */
    public static final int ERROR_FUNCTION_NOT_CALLED = 0X0000065A;

    /**
     * Function failed during execution.
     */
    public static final int ERROR_FUNCTION_FAILED = 0X0000065B;

    /**
     * Invalid or unknown table specified.
     */
    public static final int ERROR_INVALID_TABLE = 0X0000065C;

    /**
     * Data supplied is of wrong type.
     */
    public static final int ERROR_DATATYPE_MISMATCH = 0X0000065D;

    /**
     * Data of this type is not supported.
     */
    public static final int ERROR_UNSUPPORTED_TYPE = 0X0000065E;

    /**
     * The Windows Installer service failed to start. Contact   your support personnel.
     */
    public static final int ERROR_CREATE_FAILED = 0X0000065F;

    /**
     * The Temp folder is on a drive that is full or is   inaccessible. Free up space on the drive or verify that you have write   permission on the Temp folder.
     */
    public static final int ERROR_INSTALL_TEMP_UNWRITABLE = 0X00000660;

    /**
     * This installation package is not supported by this   processor type. Contact your product vendor.
     */
    public static final int ERROR_INSTALL_PLATFORM_UNSUPPORTED = 0X00000661;

    /**
     * Component not used on this computer.
     */
    public static final int ERROR_INSTALL_NOTUSED = 0X00000662;

    /**
     * This update package could not be opened. Verify that   the update package exists and that you can access it, or contact the   application vendor to verify that this is a valid Windows Installer update   package.
     */
    public static final int ERROR_PATCH_PACKAGE_OPEN_FAILED = 0X00000663;

    /**
     * This update package could not be opened. Contact the   application vendor to verify that this is a valid Windows Installer update   package.
     */
    public static final int ERROR_PATCH_PACKAGE_INVALID = 0X00000664;

    /**
     * This update package cannot be processed by the Windows   Installer service. You must install a Windows service pack that contains a   newer version of the Windows Installer service.
     */
    public static final int ERROR_PATCH_PACKAGE_UNSUPPORTED = 0X00000665;

    /**
     * Another version of this product is already installed.   Installation of this version cannot continue. To configure or remove the   existing version of this product, use Add/Remove Programs in Control Panel.
     */
    public static final int ERROR_PRODUCT_VERSION = 0X00000666;

    /**
     * Invalid command-line argument. Consult the Windows   Installer SDK for detailed command line help.
     */
    public static final int ERROR_INVALID_COMMAND_LINE = 0X00000667;

    /**
     * None
     */
    public static final int ERROR_INSTALL_REMOTE_DISALLOWED = 0X00000668;

    /**
     * The requested operation completed successfully. The   system will be restarted so the changes can take effect.
     */
    public static final int ERROR_SUCCESS_REBOOT_INITIATED = 0X00000669;

    /**
     * The upgrade cannot be installed by the Windows   Installer service because the program to be upgraded might be missing, or the   upgrade might update a different version of the program. Verify that the   program
     * to be upgraded exists on your computer and that you have the correct   upgrade.
     */
    public static final int ERROR_PATCH_TARGET_NOT_FOUND = 0X0000066A;

    /**
     * The update package is not permitted by a software   restriction policy.
     */
    public static final int ERROR_PATCH_PACKAGE_REJECTED = 0X0000066B;

    /**
     * One or more customizations are not permitted by a   software restriction policy.
     */
    public static final int ERROR_INSTALL_TRANSFORM_REJECTED = 0X0000066C;

    /**
     * The Windows Installer does not permit installation   from a Remote Desktop Connection.
     */
    public static final int ERROR_INSTALL_REMOTE_PROHIBITED = 0X0000066D;

    /**
     * Uninstallation of the update package is not supported.
     */
    public static final int ERROR_PATCH_REMOVAL_UNSUPPORTED = 0X0000066E;

    /**
     * The update is not applied to this product.
     */
    public static final int ERROR_UNKNOWN_PATCH = 0X0000066F;

    /**
     * No valid sequence could be found for the set of   updates.
     */
    public static final int ERROR_PATCH_NO_SEQUENCE = 0X00000670;

    /**
     * Update removal was disallowed by policy.
     */
    public static final int ERROR_PATCH_REMOVAL_DISALLOWED = 0X00000671;

    /**
     * The XML update data is invalid.
     */
    public static final int ERROR_INVALID_PATCH_XML = 0X00000672;

    /**
     * Windows Installer does not permit updating of managed   advertised products. At least one feature of the product must be installed   before applying the update.
     */
    public static final int ERROR_PATCH_MANAGED_ADVERTISED_PRODUCT = 0X00000673;

    /**
     * The Windows Installer service is not accessible in   Safe Mode. Try again when your computer is not in Safe Mode or you can use   System Restore to return your machine to a previous good state.
     */
    public static final int ERROR_INSTALL_SERVICE_SAFEBOOT = 0X00000674;

    /**
     * The string binding is invalid.
     */
    public static final int RPC_S_INVALID_STRING_BINDING = 0X000006A4;

    /**
     * The binding handle is not the correct type.
     */
    public static final int RPC_S_WRONG_KIND_OF_BINDING = 0X000006A5;

    /**
     * The binding handle is invalid.
     */
    public static final int RPC_S_INVALID_BINDING = 0X000006A6;

    /**
     * The RPC protocol sequence is not supported.
     */
    public static final int RPC_S_PROTSEQ_NOT_SUPPORTED = 0X000006A7;

    /**
     * The RPC protocol sequence is invalid.
     */
    public static final int RPC_S_INVALID_RPC_PROTSEQ = 0X000006A8;

    /**
     * None
     */
    public static final int RPC_S_INVALID_STRING_UUID = 0X000006A9;

    /**
     * The endpoint format is invalid.
     */
    public static final int RPC_S_INVALID_ENDPOINT_FORMAT = 0X000006AA;

    /**
     * The network address is invalid.
     */
    public static final int RPC_S_INVALID_NET_ADDR = 0X000006AB;

    /**
     * No endpoint was found.
     */
    public static final int RPC_S_NO_ENDPOINT_FOUND = 0X000006AC;

    /**
     * The time-out value is invalid.
     */
    public static final int RPC_S_INVALID_TIMEOUT = 0X000006AD;

    /**
     * The object UUID) was not found.
     */
    public static final int RPC_S_OBJECT_NOT_FOUND = 0X000006AE;

    /**
     * The object UUID) has already been registered.
     */
    public static final int RPC_S_ALREADY_REGISTERED = 0X000006AF;

    /**
     * The type UUID has already been registered.
     */
    public static final int RPC_S_TYPE_ALREADY_REGISTERED = 0X000006B0;

    /**
     * The RPC server is already listening.
     */
    public static final int RPC_S_ALREADY_LISTENING = 0X000006B1;

    /**
     * No protocol sequences have been registered.
     */
    public static final int RPC_S_NO_PROTSEQS_REGISTERED = 0X000006B2;

    /**
     * The RPC server is not listening.
     */
    public static final int RPC_S_NOT_LISTENING = 0X000006B3;

    /**
     * The manager type is unknown.
     */
    public static final int RPC_S_UNKNOWN_MGR_TYPE = 0X000006B4;

    /**
     * The interface is unknown.
     */
    public static final int RPC_S_UNKNOWN_IF = 0X000006B5;

    /**
     * There are no bindings.
     */
    public static final int RPC_S_NO_BINDINGS = 0X000006B6;

    /**
     * There are no protocol sequences.
     */
    public static final int RPC_S_NO_PROTSEQS = 0X000006B7;

    /**
     * The endpoint cannot be created.
     */
    public static final int RPC_S_CANT_CREATE_ENDPOINT = 0X000006B8;

    /**
     * Not enough resources are available to complete this   operation.
     */
    public static final int RPC_S_OUT_OF_RESOURCES = 0X000006B9;

    /**
     * The RPC server is unavailable.
     */
    public static final int RPC_S_SERVER_UNAVAILABLE = 0X000006BA;

    /**
     * The RPC server is too busy to complete this operation.
     */
    public static final int RPC_S_SERVER_TOO_BUSY = 0X000006BB;

    /**
     * The network options are invalid.
     */
    public static final int RPC_S_INVALID_NETWORK_OPTIONS = 0X000006BC;

    /**
     * There are no RPCs active on this thread.
     */
    public static final int RPC_S_NO_CALL_ACTIVE = 0X000006BD;

    /**
     * The RPC failed.
     */
    public static final int RPC_S_CALL_FAILED = 0X000006BE;

    /**
     * The RPC failed and did not execute.
     */
    public static final int RPC_S_CALL_FAILED_DNE = 0X000006BF;

    /**
     * An RPC protocol error occurred.
     */
    public static final int RPC_S_PROTOCOL_ERROR = 0X000006C0;

    /**
     * Access to the HTTP proxy is denied.
     */
    public static final int RPC_S_PROXY_ACCESS_DENIED = 0X000006C1;

    /**
     * The transfer syntax is not supported by the RPC   server.
     */
    public static final int RPC_S_UNSUPPORTED_TRANS_SYN = 0X000006C2;

    /**
     * The UUID type is not supported.
     */
    public static final int RPC_S_UNSUPPORTED_TYPE = 0X000006C4;

    /**
     * The tag is invalid.
     */
    public static final int RPC_S_INVALID_TAG = 0X000006C5;

    /**
     * The array bounds are invalid.
     */
    public static final int RPC_S_INVALID_BOUND = 0X000006C6;

    /**
     * The binding does not contain an entry name.
     */
    public static final int RPC_S_NO_ENTRY_NAME = 0X000006C7;

    /**
     * The name syntax is invalid.
     */
    public static final int RPC_S_INVALID_NAME_SYNTAX = 0X000006C8;

    /**
     * The name syntax is not supported.
     */
    public static final int RPC_S_UNSUPPORTED_NAME_SYNTAX = 0X000006C9;

    /**
     * No network address is available to use to construct a   UUID.
     */
    public static final int RPC_S_UUID_NO_ADDRESS = 0X000006CB;

    /**
     * The endpoint is a duplicate.
     */
    public static final int RPC_S_DUPLICATE_ENDPOINT = 0X000006CC;

    /**
     * The authentication type is unknown.
     */
    public static final int RPC_S_UNKNOWN_AUTHN_TYPE = 0X000006CD;

    /**
     * The maximum number of calls is too small.
     */
    public static final int RPC_S_MAX_CALLS_TOO_SMALL = 0X000006CE;

    /**
     * The string is too long.
     */
    public static final int RPC_S_STRING_TOO_LONG = 0X000006CF;

    /**
     * The RPC protocol sequence was not found.
     */
    public static final int RPC_S_PROTSEQ_NOT_FOUND = 0X000006D0;

    /**
     * The procedure number is out of range.
     */
    public static final int RPC_S_PROCNUM_OUT_OF_RANGE = 0X000006D1;

    /**
     * The binding does not contain any authentication   information.
     */
    public static final int RPC_S_BINDING_HAS_NO_AUTH = 0X000006D2;

    /**
     * The authentication service is unknown.
     */
    public static final int RPC_S_UNKNOWN_AUTHN_SERVICE = 0X000006D3;

    /**
     * The authentication level is unknown.
     */
    public static final int RPC_S_UNKNOWN_AUTHN_LEVEL = 0X000006D4;

    /**
     * The security context is invalid.
     */
    public static final int RPC_S_INVALID_AUTH_IDENTITY = 0X000006D5;

    /**
     * The authorization service is unknown.
     */
    public static final int RPC_S_UNKNOWN_AUTHZ_SERVICE = 0X000006D6;

    /**
     * The entry is invalid.
     */
    public static final int EPT_S_INVALID_ENTRY = 0X000006D7;

    /**
     * The server endpoint cannot perform the operation.
     */
    public static final int EPT_S_CANT_PERFORM_OP = 0X000006D8;

    /**
     * There are no more endpoints available from the endpoint   mapper.
     */
    public static final int EPT_S_NOT_REGISTERED = 0X000006D9;

    /**
     * No interfaces have been exported.
     */
    public static final int RPC_S_NOTHING_TO_EXPORT = 0X000006DA;

    /**
     * The entry name is incomplete.
     */
    public static final int RPC_S_INCOMPLETE_NAME = 0X000006DB;

    /**
     * The version option is invalid.
     */
    public static final int RPC_S_INVALID_VERS_OPTION = 0X000006DC;

    /**
     * There are no more members.
     */
    public static final int RPC_S_NO_MORE_MEMBERS = 0X000006DD;

    /**
     * There is nothing to unexport.
     */
    public static final int RPC_S_NOT_ALL_OBJS_UNEXPORTED = 0X000006DE;

    /**
     * The interface was not found.
     */
    public static final int RPC_S_INTERFACE_NOT_FOUND = 0X000006DF;

    /**
     * The entry already exists.
     */
    public static final int RPC_S_ENTRY_ALREADY_EXISTS = 0X000006E0;

    /**
     * The entry is not found.
     */
    public static final int RPC_S_ENTRY_NOT_FOUND = 0X000006E1;

    /**
     * The name service is unavailable.
     */
    public static final int RPC_S_NAME_SERVICE_UNAVAILABLE = 0X000006E2;

    /**
     * The network address family is invalid.
     */
    public static final int RPC_S_INVALID_NAF_ID = 0X000006E3;

    /**
     * The requested operation is not supported.
     */
    public static final int RPC_S_CANNOT_SUPPORT = 0X000006E4;

    /**
     * No security context is available to allow   impersonation.
     */
    public static final int RPC_S_NO_CONTEXT_AVAILABLE = 0X000006E5;

    /**
     * An internal error occurred in an RPC.
     */
    public static final int RPC_S_INTERNAL_ERROR = 0X000006E6;

    /**
     * The RPC server attempted an integer division by zero.
     */
    public static final int RPC_S_ZERO_DIVIDE = 0X000006E7;

    /**
     * An addressing error occurred in the RPC server.
     */
    public static final int RPC_S_ADDRESS_ERROR = 0X000006E8;

    /**
     * A floating-point operation at the RPC server caused a   division by zero.
     */
    public static final int RPC_S_FP_DIV_ZERO = 0X000006E9;

    /**
     * A floating-point underflow occurred at the RPC server.
     */
    public static final int RPC_S_FP_UNDERFLOW = 0X000006EA;

    /**
     * A floating-point overflow occurred at the RPC server.
     */
    public static final int RPC_S_FP_OVERFLOW = 0X000006EB;

    /**
     * The list of RPC servers available for the binding of   auto handles has been exhausted.
     */
    public static final int RPC_X_NO_MORE_ENTRIES = 0X000006EC;

    /**
     * Unable to open the character translation table file.
     */
    public static final int RPC_X_SS_CHAR_TRANS_OPEN_FAIL = 0X000006ED;

    /**
     * The file containing the character translation table   has fewer than 512 bytes.
     */
    public static final int RPC_X_SS_CHAR_TRANS_SHORT_FILE = 0X000006EE;

    /**
     * A null context handle was passed from the client to   the host during an RPC.
     */
    public static final int RPC_X_SS_IN_NULL_CONTEXT = 0X000006EF;

    /**
     * The context handle changed during an RPC.
     */
    public static final int RPC_X_SS_CONTEXT_DAMAGED = 0X000006F1;

    /**
     * The binding handles passed to an RPC do not match.
     */
    public static final int RPC_X_SS_HANDLES_MISMATCH = 0X000006F2;

    /**
     * The stub is unable to get the RPC handle.
     */
    public static final int RPC_X_SS_CANNOT_GET_CALL_HANDLE = 0X000006F3;

    /**
     * A null reference pointer was passed to the stub.
     */
    public static final int RPC_X_NULL_REF_POINTER = 0X000006F4;

    /**
     * The enumeration value is out of range.
     */
    public static final int RPC_X_ENUM_VALUE_OUT_OF_RANGE = 0X000006F5;

    /**
     * The byte count is too small.
     */
    public static final int RPC_X_BYTE_COUNT_TOO_SMALL = 0X000006F6;

    /**
     * The stub received bad data.
     */
    public static final int RPC_X_BAD_STUB_DATA = 0X000006F7;

    /**
     * The supplied user buffer is not valid for the   requested operation.
     */
    public static final int ERROR_INVALID_USER_BUFFER = 0X000006F8;

    /**
     * The disk media is not recognized. It might not be   formatted.
     */
    public static final int ERROR_UNRECOGNIZED_MEDIA = 0X000006F9;

    /**
     * The workstation does not have a trust secret.
     */
    public static final int ERROR_NO_TRUST_LSA_SECRET = 0X000006FA;

    /**
     * The security database on the server does not have a   computer account for this workstation trust relationship.
     */
    public static final int ERROR_NO_TRUST_SAM_ACCOUNT = 0X000006FB;

    /**
     * The trust relationship between the primary domain and   the trusted domain failed.
     */
    public static final int ERROR_TRUSTED_DOMAIN_FAILURE = 0X000006FC;

    /**
     * The trust relationship between this workstation and the   primary domain failed.
     */
    public static final int ERROR_TRUSTED_RELATIONSHIP_FAILURE = 0X000006FD;

    /**
     * The network logon failed.
     */
    public static final int ERROR_TRUST_FAILURE = 0X000006FE;

    /**
     * An RPC is already in progress for this thread.
     */
    public static final int RPC_S_CALL_IN_PROGRESS = 0X000006FF;

    /**
     * An attempt was made to log on, but the network logon   service was not started.
     */
    public static final int ERROR_NETLOGON_NOT_STARTED = 0X00000700;

    /**
     * The user\'s account has expired.
     */
    public static final int ERROR_ACCOUNT_EXPIRED = 0X00000701;

    /**
     * The redirector is in use and cannot be unloaded.
     */
    public static final int ERROR_REDIRECTOR_HAS_OPEN_HANDLES = 0X00000702;

    /**
     * The specified printer driver is already installed.
     */
    public static final int ERROR_PRINTER_DRIVER_ALREADY_INSTALLED = 0X00000703;

    /**
     * The specified port is unknown.
     */
    public static final int ERROR_UNKNOWN_PORT = 0X00000704;

    /**
     * The printer driver is unknown.
     */
    public static final int ERROR_UNKNOWN_PRINTER_DRIVER = 0X00000705;

    /**
     * The print processor is unknown.
     */
    public static final int ERROR_UNKNOWN_PRINTPROCESSOR = 0X00000706;

    /**
     * The specified separator file is invalid.
     */
    public static final int ERROR_INVALID_SEPARATOR_FILE = 0X00000707;

    /**
     * The specified priority is invalid.
     */
    public static final int ERROR_INVALID_PRIORITY = 0X00000708;

    /**
     * The printer name is invalid.
     */
    public static final int ERROR_INVALID_PRINTER_NAME = 0X00000709;

    /**
     * The printer already exists.
     */
    public static final int ERROR_PRINTER_ALREADY_EXISTS = 0X0000070A;

    /**
     * The printer command is invalid.
     */
    public static final int ERROR_INVALID_PRINTER_COMMAND = 0X0000070B;

    /**
     * The specified data type is invalid.
     */
    public static final int ERROR_INVALID_DATATYPE = 0X0000070C;

    /**
     * The environment specified is invalid.
     */
    public static final int ERROR_INVALID_ENVIRONMENT = 0X0000070D;

    /**
     * There are no more bindings.
     */
    public static final int RPC_S_NO_MORE_BINDINGS = 0X0000070E;

    /**
     * The account used is an interdomain trust account. Use   your global user account or local user account to access this server.
     */
    public static final int ERROR_NOLOGON_INTERDOMAIN_TRUST_ACCOUNT = 0X0000070F;

    /**
     * The account used is a computer account. Use your   global user account or local user account to access this server.
     */
    public static final int ERROR_NOLOGON_WORKSTATION_TRUST_ACCOUNT = 0X00000710;

    /**
     * The account used is a server trust account. Use your   global user account or local user account to access this server.
     */
    public static final int ERROR_NOLOGON_SERVER_TRUST_ACCOUNT = 0X00000711;

    /**
     * The name or SID of the domain specified is   inconsistent with the trust information for that domain.
     */
    public static final int ERROR_DOMAIN_TRUST_INCONSISTENT = 0X00000712;

    /**
     * The server is in use and cannot be unloaded.
     */
    public static final int ERROR_SERVER_HAS_OPEN_HANDLES = 0X00000713;

    /**
     * The specified image file did not contain a resource   section.
     */
    public static final int ERROR_RESOURCE_DATA_NOT_FOUND = 0X00000714;

    /**
     * The specified resource type cannot be found in the   image file.
     */
    public static final int ERROR_RESOURCE_TYPE_NOT_FOUND = 0X00000715;

    /**
     * The specified resource name cannot be found in the   image file.
     */
    public static final int ERROR_RESOURCE_NAME_NOT_FOUND = 0X00000716;

    /**
     * The specified resource language ID cannot be found in   the image file.
     */
    public static final int ERROR_RESOURCE_LANG_NOT_FOUND = 0X00000717;

    /**
     * Not enough quota is available to process this command.
     */
    public static final int ERROR_NOT_ENOUGH_QUOTA = 0X00000718;

    /**
     * No interfaces have been registered.
     */
    public static final int RPC_S_NO_INTERFACES = 0X00000719;

    /**
     * The RPC was canceled.
     */
    public static final int RPC_S_CALL_CANCELLED = 0X0000071A;

    /**
     * The binding handle does not contain all the required   information.
     */
    public static final int RPC_S_BINDING_INCOMPLETE = 0X0000071B;

    /**
     * A communications failure occurred during an RPC.
     */
    public static final int RPC_S_COMM_FAILURE = 0X0000071C;

    /**
     * The requested authentication level is not supported.
     */
    public static final int RPC_S_UNSUPPORTED_AUTHN_LEVEL = 0X0000071D;

    /**
     * No principal name is registered.
     */
    public static final int RPC_S_NO_PRINC_NAME = 0X0000071E;

    /**
     * The error specified is not a valid Windows RPC error   code.
     */
    public static final int RPC_S_NOT_RPC_ERROR = 0X0000071F;

    /**
     * A UUID that is valid only on this computer has been   allocated.
     */
    public static final int RPC_S_UUID_LOCAL_ONLY = 0X00000720;

    /**
     * A security package-specific error occurred.
     */
    public static final int RPC_S_SEC_PKG_ERROR = 0X00000721;

    /**
     * The thread is not canceled.
     */
    public static final int RPC_S_NOT_CANCELLED = 0X00000722;

    /**
     * Invalid operation on the encoding/decoding handle.
     */
    public static final int RPC_X_INVALID_ES_ACTION = 0X00000723;

    /**
     * Incompatible version of the serializing package.
     */
    public static final int RPC_X_WRONG_ES_VERSION = 0X00000724;

    /**
     * Incompatible version of the RPC stub.
     */
    public static final int RPC_X_WRONG_STUB_VERSION = 0X00000725;

    /**
     * The RPC pipe object is invalid or corrupted.
     */
    public static final int RPC_X_INVALID_PIPE_OBJECT = 0X00000726;

    /**
     * An invalid operation was attempted on an RPC pipe   object.
     */
    public static final int RPC_X_WRONG_PIPE_ORDER = 0X00000727;

    /**
     * Unsupported RPC pipe version.
     */
    public static final int RPC_X_WRONG_PIPE_VERSION = 0X00000728;

    /**
     * The group member was not found.
     */
    public static final int RPC_S_GROUP_MEMBER_NOT_FOUND = 0X0000076A;

    /**
     * The endpoint mapper database entry could not be   created.
     */
    public static final int EPT_S_CANT_CREATE = 0X0000076B;

    /**
     * The object UUID is the nil UUID.
     */
    public static final int RPC_S_INVALID_OBJECT = 0X0000076C;

    /**
     * The specified time is invalid.
     */
    public static final int ERROR_INVALID_TIME = 0X0000076D;

    /**
     * The specified form name is invalid.
     */
    public static final int ERROR_INVALID_FORM_NAME = 0X0000076E;

    /**
     * The specified form size is invalid.
     */
    public static final int ERROR_INVALID_FORM_SIZE = 0X0000076F;

    /**
     * The specified printer handle is already being waited   on.
     */
    public static final int ERROR_ALREADY_WAITING = 0X00000770;

    /**
     * The specified printer has been deleted.
     */
    public static final int ERROR_PRINTER_DELETED = 0X00000771;

    /**
     * The state of the printer is invalid.
     */
    public static final int ERROR_INVALID_PRINTER_STATE = 0X00000772;

    /**
     * The user\'s password must be changed before logging on   the first time.
     */
    public static final int ERROR_PASSWORD_MUST_CHANGE = 0X00000773;

    /**
     * Could not find the domain controller for this domain.
     */
    public static final int ERROR_DOMAIN_CONTROLLER_NOT_FOUND = 0X00000774;

    /**
     * The referenced account is currently locked out and   cannot be logged on to.
     */
    public static final int ERROR_ACCOUNT_LOCKED_OUT = 0X00000775;

    /**
     * The object exporter specified was not found.
     */
    public static final int OR_INVALID_OXID = 0X00000776;

    /**
     * The object specified was not found.
     */
    public static final int OR_INVALID_OID = 0X00000777;

    /**
     * The object set specified was not found.
     */
    public static final int OR_INVALID_SET = 0X00000778;

    /**
     * Some data remains to be sent in the request buffer.
     */
    public static final int RPC_S_SEND_INCOMPLETE = 0X00000779;

    /**
     * Invalid asynchronous RPC handle.
     */
    public static final int RPC_S_INVALID_ASYNC_HANDLE = 0X0000077A;

    /**
     * Invalid asynchronous RPC call handle for this   operation.
     */
    public static final int RPC_S_INVALID_ASYNC_CALL = 0X0000077B;

    /**
     * The RPC pipe object has already been closed.
     */
    public static final int RPC_X_PIPE_CLOSED = 0X0000077C;

    /**
     * The RPC call completed before all pipes were   processed.
     */
    public static final int RPC_X_PIPE_DISCIPLINE_ERROR = 0X0000077D;

    /**
     * No more data is available from the RPC pipe.
     */
    public static final int RPC_X_PIPE_EMPTY = 0X0000077E;

    /**
     * No site name is available for this machine.
     */
    public static final int ERROR_NO_SITENAME = 0X0000077F;

    /**
     * The file cannot be accessed by the system.
     */
    public static final int ERROR_CANT_ACCESS_FILE = 0X00000780;

    /**
     * The name of the file cannot be resolved by the system.
     */
    public static final int ERROR_CANT_RESOLVE_FILENAME = 0X00000781;

    /**
     * The entry is not of the expected type.
     */
    public static final int RPC_S_ENTRY_TYPE_MISMATCH = 0X00000782;

    /**
     * Not all object UUIDs could be exported to the   specified entry.
     */
    public static final int RPC_S_NOT_ALL_OBJS_EXPORTED = 0X00000783;

    /**
     * The interface could not be exported to the specified   entry.
     */
    public static final int RPC_S_INTERFACE_NOT_EXPORTED = 0X00000784;

    /**
     * The specified profile entry could not be added.
     */
    public static final int RPC_S_PROFILE_NOT_ADDED = 0X00000785;

    /**
     * The specified profile element could not be added.
     */
    public static final int RPC_S_PRF_ELT_NOT_ADDED = 0X00000786;

    /**
     * The specified profile element could not be removed.
     */
    public static final int RPC_S_PRF_ELT_NOT_REMOVED = 0X00000787;

    /**
     * The group element could not be added.
     */
    public static final int RPC_S_GRP_ELT_NOT_ADDED = 0X00000788;

    /**
     * The group element could not be removed.
     */
    public static final int RPC_S_GRP_ELT_NOT_REMOVED = 0X00000789;

    /**
     * The printer driver is not compatible with a policy   enabled on your computer that blocks Windows NT 4.0 operating system drivers.
     */
    public static final int ERROR_KM_DRIVER_BLOCKED = 0X0000078A;

    /**
     * The context has expired and can no longer be used.
     */
    public static final int ERROR_CONTEXT_EXPIRED = 0X0000078B;

    /**
     * The current user\'s delegated trust creation quota has   been exceeded.
     */
    public static final int ERROR_PER_USER_TRUST_QUOTA_EXCEEDED = 0X0000078C;

    /**
     * The total delegated trust creation quota has been   exceeded.
     */
    public static final int ERROR_ALL_USER_TRUST_QUOTA_EXCEEDED = 0X0000078D;

    /**
     * The current user\'s delegated trust deletion quota has   been exceeded.
     */
    public static final int ERROR_USER_DELETE_TRUST_QUOTA_EXCEEDED = 0X0000078E;

    /**
     * Logon failure: The machine you are logging on to is   protected by an authentication firewall. The specified account is not allowed   to authenticate to the machine.
     */
    public static final int ERROR_AUTHENTICATION_FIREWALL_FAILED = 0X0000078F;

    /**
     * Remote connections to the Print Spooler are blocked by   a policy set on your machine.
     */
    public static final int ERROR_REMOTE_PRINT_CONNECTIONS_BLOCKED = 0X00000790;

    /**
     * The pixel format is invalid.
     */
    public static final int ERROR_INVALID_PIXEL_FORMAT = 0X000007D0;

    /**
     * The specified driver is invalid.
     */
    public static final int ERROR_BAD_DRIVER = 0X000007D1;

    /**
     * The window style or class attribute is invalid for   this operation.
     */
    public static final int ERROR_INVALID_WINDOW_STYLE = 0X000007D2;

    /**
     * The requested metafile operation is not supported.
     */
    public static final int ERROR_METAFILE_NOT_SUPPORTED = 0X000007D3;

    /**
     * The requested transformation operation is not   supported.
     */
    public static final int ERROR_TRANSFORM_NOT_SUPPORTED = 0X000007D4;

    /**
     * The requested clipping operation is not supported.
     */
    public static final int ERROR_CLIPPING_NOT_SUPPORTED = 0X000007D5;

    /**
     * The specified color management module is invalid.
     */
    public static final int ERROR_INVALID_CMM = 0X000007DA;

    /**
     * The specified color profile is invalid.
     */
    public static final int ERROR_INVALID_PROFILE = 0X000007DB;

    /**
     * The specified tag was not found.
     */
    public static final int ERROR_TAG_NOT_FOUND = 0X000007DC;

    /**
     * A required tag is not present.
     */
    public static final int ERROR_TAG_NOT_PRESENT = 0X000007DD;

    /**
     * The specified tag is already present.
     */
    public static final int ERROR_DUPLICATE_TAG = 0X000007DE;

    /**
     * The specified color profile is not associated with any   device.
     */
    public static final int ERROR_PROFILE_NOT_ASSOCIATED_WITH_DEVICE = 0X000007DF;

    /**
     * The specified color profile was not found.
     */
    public static final int ERROR_PROFILE_NOT_FOUND = 0X000007E0;

    /**
     * The specified color space is invalid.
     */
    public static final int ERROR_INVALID_COLORSPACE = 0X000007E1;

    /**
     * Image Color Management is not enabled.
     */
    public static final int ERROR_ICM_NOT_ENABLED = 0X000007E2;

    /**
     * There was an error while deleting the color transform.
     */
    public static final int ERROR_DELETING_ICM_XFORM = 0X000007E3;

    /**
     * The specified color transform is invalid.
     */
    public static final int ERROR_INVALID_TRANSFORM = 0X000007E4;

    /**
     * The specified transform does not match the bitmap\'s   color space.
     */
    public static final int ERROR_COLORSPACE_MISMATCH = 0X000007E5;

    /**
     * The specified named color index is not present in the   profile.
     */
    public static final int ERROR_INVALID_COLORINDEX = 0X000007E6;

    /**
     * The specified profile is intended for a device of a   different type than the specified device.
     */
    public static final int ERROR_PROFILE_DOES_NOT_MATCH_DEVICE = 0X000007E7;

    /**
     * The workstation driver is not installed.
     */
    public static final int NERR_NetNotStarted = 0X00000836;

    /**
     * The server could not be located.
     */
    public static final int NERR_UnknownServer = 0X00000837;

    /**
     * An internal error occurred. The network cannot access   a shared memory segment.
     */
    public static final int NERR_ShareMem = 0X00000838;

    /**
     * A network resource shortage occurred.
     */
    public static final int NERR_NoNetworkResource = 0X00000839;

    /**
     * This operation is not supported on workstations.
     */
    public static final int NERR_RemoteOnly = 0X0000083A;

    /**
     * The device is not connected.
     */
    public static final int NERR_DevNotRedirected = 0X0000083B;

    /**
     * The network connection was made successfully, but the   user had to be prompted for a password other than the one originally   specified.
     */
    public static final int ERROR_CONNECTED_OTHER_PASSWORD = 0X0000083C;

    /**
     * The network connection was made successfully using   default credentials.
     */
    public static final int ERROR_CONNECTED_OTHER_PASSWORD_DEFAULT = 0X0000083D;

    /**
     * The Server service is not started.
     */
    public static final int NERR_ServerNotStarted = 0X00000842;

    /**
     * The queue is empty.
     */
    public static final int NERR_ItemNotFound = 0X00000843;

    /**
     * The device or directory does not exist.
     */
    public static final int NERR_UnknownDevDir = 0X00000844;

    /**
     * The operation is invalid on a redirected resource.
     */
    public static final int NERR_RedirectedPath = 0X00000845;

    /**
     * The name has already been shared.
     */
    public static final int NERR_DuplicateShare = 0X00000846;

    /**
     * The server is currently out of the requested resource.
     */
    public static final int NERR_NoRoom = 0X00000847;

    /**
     * Requested addition of items exceeds the maximum   allowed.
     */
    public static final int NERR_TooManyItems = 0X00000849;

    /**
     * The Peer service supports only two simultaneous users.
     */
    public static final int NERR_InvalidMaxUsers = 0X0000084A;

    /**
     * The API return buffer is too small.
     */
    public static final int NERR_BufTooSmall = 0X0000084B;

    /**
     * A remote API error occurred.
     */
    public static final int NERR_RemoteErr = 0X0000084F;

    /**
     * An error occurred when opening or reading the   configuration file.
     */
    public static final int NERR_LanmanIniError = 0X00000853;

    /**
     * A general network error occurred.
     */
    public static final int NERR_NetworkError = 0X00000858;

    /**
     * The Workstation service is in an inconsistent state.   Restart the computer before restarting the Workstation service.
     */
    public static final int NERR_WkstaInconsistentState = 0X00000859;

    /**
     * The Workstation service has not been started.
     */
    public static final int NERR_WkstaNotStarted = 0X0000085A;

    /**
     * The requested information is not available.
     */
    public static final int NERR_BrowserNotStarted = 0X0000085B;

    /**
     * An internal error occurred.
     */
    public static final int NERR_InternalError = 0X0000085C;

    /**
     * The server is not configured for transactions.
     */
    public static final int NERR_BadTransactConfig = 0X0000085D;

    /**
     * The requested API is not supported on the remote   server.
     */
    public static final int NERR_InvalidAPI = 0X0000085E;

    /**
     * The event name is invalid.
     */
    public static final int NERR_BadEventName = 0X0000085F;

    /**
     * The computer name already exists on the network.   Change it and reboot the computer.
     */
    public static final int NERR_DupNameReboot = 0X00000860;

    /**
     * The specified component could not be found in the   configuration information.
     */
    public static final int NERR_CfgCompNotFound = 0X00000862;

    /**
     * The specified parameter could not be found in the   configuration information.
     */
    public static final int NERR_CfgParamNotFound = 0X00000863;

    /**
     * A line in the configuration file is too long.
     */
    public static final int NERR_LineTooLong = 0X00000865;

    /**
     * The printer does not exist.
     */
    public static final int NERR_QNotFound = 0X00000866;

    /**
     * The print job does not exist.
     */
    public static final int NERR_JobNotFound = 0X00000867;

    /**
     * The printer destination cannot be found.
     */
    public static final int NERR_DestNotFound = 0X00000868;

    /**
     * The printer destination already exists.
     */
    public static final int NERR_DestExists = 0X00000869;

    /**
     * The print queue already exists.
     */
    public static final int NERR_QExists = 0X0000086A;

    /**
     * No more printers can be added.
     */
    public static final int NERR_QNoRoom = 0X0000086B;

    /**
     * No more print jobs can be added.
     */
    public static final int NERR_JobNoRoom = 0X0000086C;

    /**
     * No more printer destinations can be added.
     */
    public static final int NERR_DestNoRoom = 0X0000086D;

    /**
     * This printer destination is idle and cannot accept   control operations.
     */
    public static final int NERR_DestIdle = 0X0000086E;

    /**
     * This printer destination request contains an invalid   control function.
     */
    public static final int NERR_DestInvalidOp = 0X0000086F;

    /**
     * The print processor is not responding.
     */
    public static final int NERR_ProcNoRespond = 0X00000870;

    /**
     * The spooler is not running.
     */
    public static final int NERR_SpoolerNotLoaded = 0X00000871;

    /**
     * This operation cannot be performed on the print   destination in its current state.
     */
    public static final int NERR_DestInvalidState = 0X00000872;

    /**
     * This operation cannot be performed on the print queue   in its current state.
     */
    public static final int NERR_QinvalidState = 0X00000873;

    /**
     * This operation cannot be performed on the print job in   its current state.
     */
    public static final int NERR_JobInvalidState = 0X00000874;

    /**
     * A spooler memory allocation failure occurred.
     */
    public static final int NERR_SpoolNoMemory = 0X00000875;

    /**
     * The device driver does not exist.
     */
    public static final int NERR_DriverNotFound = 0X00000876;

    /**
     * The data type is not supported by the print processor.
     */
    public static final int NERR_DataTypeInvalid = 0X00000877;

    /**
     * The print processor is not installed.
     */
    public static final int NERR_ProcNotFound = 0X00000878;

    /**
     * The service database is locked.
     */
    public static final int NERR_ServiceTableLocked = 0X00000884;

    /**
     * The service table is full.
     */
    public static final int NERR_ServiceTableFull = 0X00000885;

    /**
     * The requested service has already been started.
     */
    public static final int NERR_ServiceInstalled = 0X00000886;

    /**
     * The service does not respond to control actions.
     */
    public static final int NERR_ServiceEntryLocked = 0X00000887;

    /**
     * The service has not been started.
     */
    public static final int NERR_ServiceNotInstalled = 0X00000888;

    /**
     * The service name is invalid.
     */
    public static final int NERR_BadServiceName = 0X00000889;

    /**
     * The service is not responding to the control function.
     */
    public static final int NERR_ServiceCtlTimeout = 0X0000088A;

    /**
     * The service control is busy.
     */
    public static final int NERR_ServiceCtlBusy = 0X0000088B;

    /**
     * The configuration file contains an invalid service   program name.
     */
    public static final int NERR_BadServiceProgName = 0X0000088C;

    /**
     * The service could not be controlled in its present   state.
     */
    public static final int NERR_ServiceNotCtrl = 0X0000088D;

    /**
     * The service ended abnormally.
     */
    public static final int NERR_ServiceKillProc = 0X0000088E;

    /**
     * The requested pause or stop is not valid for this   service.
     */
    public static final int NERR_ServiceCtlNotValid = 0X0000088F;

    /**
     * The service control dispatcher could not find the   service name in the dispatch table.
     */
    public static final int NERR_NotInDispatchTbl = 0X00000890;

    /**
     * The service control dispatcher pipe read failed.
     */
    public static final int NERR_BadControlRecv = 0X00000891;

    /**
     * A thread for the new service could not be created.
     */
    public static final int NERR_ServiceNotStarting = 0X00000892;

    /**
     * This workstation is already logged on to the LAN.
     */
    public static final int NERR_AlreadyLoggedOn = 0X00000898;

    /**
     * The workstation is not logged on to the LAN.
     */
    public static final int NERR_NotLoggedOn = 0X00000899;

    /**
     * The user name or group name parameter is invalid.
     */
    public static final int NERR_BadUsername = 0X0000089A;

    /**
     * The password parameter is invalid.
     */
    public static final int NERR_BadPassword = 0X0000089B;

    /**
     * The logon processor did not add the message alias.
     */
    public static final int NERR_UnableToAddName_W = 0X0000089C;

    /**
     * The logon processor did not add the message alias.
     */
    public static final int NERR_UnableToAddName_F = 0X0000089D;

    /**
     * &#64;W The logoff processor did not delete the message   alias.
     */
    public static final int NERR_UnableToDelName_W = 0X0000089E;

    /**
     * The logoff processor did not delete the message alias.
     */
    public static final int NERR_UnableToDelName_F = 0X0000089F;

    /**
     * Network logons are paused.
     */
    public static final int NERR_LogonsPaused = 0X000008A1;

    /**
     * A centralized logon server conflict occurred.
     */
    public static final int NERR_LogonServerConflict = 0X000008A2;

    /**
     * The server is configured without a valid user path.
     */
    public static final int NERR_LogonNoUserPath = 0X000008A3;

    /**
     * An error occurred while loading or running the logon   script.
     */
    public static final int NERR_LogonScriptError = 0X000008A4;

    /**
     * The logon server was not specified. The computer will   be logged on as STANDALONE.
     */
    public static final int NERR_StandaloneLogon = 0X000008A6;

    /**
     * The logon server could not be found.
     */
    public static final int NERR_LogonServerNotFound = 0X000008A7;

    /**
     * There is already a logon domain for this computer.
     */
    public static final int NERR_LogonDomainExists = 0X000008A8;

    /**
     * The logon server could not validate the logon.
     */
    public static final int NERR_NonValidatedLogon = 0X000008A9;

    /**
     * The security database could not be found.
     */
    public static final int NERR_ACFNotFound = 0X000008AB;

    /**
     * The group name could not be found.
     */
    public static final int NERR_GroupNotFound = 0X000008AC;

    /**
     * The user name could not be found.
     */
    public static final int NERR_UserNotFound = 0X000008AD;

    /**
     * The resource name could not be found.
     */
    public static final int NERR_ResourceNotFound = 0X000008AE;

    /**
     * The group already exists.
     */
    public static final int NERR_GroupExists = 0X000008AF;

    /**
     * The user account already exists.
     */
    public static final int NERR_UserExists = 0X000008B0;

    /**
     * The resource permission list already exists.
     */
    public static final int NERR_ResourceExists = 0X000008B1;

    /**
     * This operation is allowed only on the PDC of the   domain.
     */
    public static final int NERR_NotPrimary = 0X000008B2;

    /**
     * The security database has not been started.
     */
    public static final int NERR_ACFNotLoaded = 0X000008B3;

    /**
     * There are too many names in the user accounts   database.
     */
    public static final int NERR_ACFNoRoom = 0X000008B4;

    /**
     * A disk I/O failure occurred.
     */
    public static final int NERR_ACFFileIOFail = 0X000008B5;

    /**
     * The limit of 64 entries per resource was exceeded.
     */
    public static final int NERR_ACFTooManyLists = 0X000008B6;

    /**
     * Deleting a user with a session is not allowed.
     */
    public static final int NERR_UserLogon = 0X000008B7;

    /**
     * The parent directory could not be located.
     */
    public static final int NERR_ACFNoParent = 0X000008B8;

    /**
     * Unable to add to the security database session cache   segment.
     */
    public static final int NERR_CanNotGrowSegment = 0X000008B9;

    /**
     * This operation is not allowed on this special group.
     */
    public static final int NERR_SpeGroupOp = 0X000008BA;

    /**
     * This user is not cached in the user accounts database   session cache.
     */
    public static final int NERR_NotInCache = 0X000008BB;

    /**
     * The user already belongs to this group.
     */
    public static final int NERR_UserInGroup = 0X000008BC;

    /**
     * The user does not belong to this group.
     */
    public static final int NERR_UserNotInGroup = 0X000008BD;

    /**
     * This user account is undefined.
     */
    public static final int NERR_AccountUndefined = 0X000008BE;

    /**
     * This user account has expired.
     */
    public static final int NERR_AccountExpired = 0X000008BF;

    /**
     * The user is not allowed to log on from this   workstation.
     */
    public static final int NERR_InvalidWorkstation = 0X000008C0;

    /**
     * The user is not allowed to log on at this time.
     */
    public static final int NERR_InvalidLogonHours = 0X000008C1;

    /**
     * The password of this user has expired.
     */
    public static final int NERR_PasswordExpired = 0X000008C2;

    /**
     * The password of this user cannot change.
     */
    public static final int NERR_PasswordCantChange = 0X000008C3;

    /**
     * This password cannot be used now.
     */
    public static final int NERR_PasswordHistConflict = 0X000008C4;

    /**
     * The password does not meet the password policy   requirements. Check the minimum password length, password complexity, and   password history requirements.
     */
    public static final int NERR_PasswordTooShort = 0X000008C5;

    /**
     * The password of this user is too recent to change.
     */
    public static final int NERR_PasswordTooRecent = 0X000008C6;

    /**
     * The security database is corrupted.
     */
    public static final int NERR_InvalidDatabase = 0X000008C7;

    /**
     * No updates are necessary to this replicant network or   local security database.
     */
    public static final int NERR_DatabaseUpToDate = 0X000008C8;

    /**
     * This replicant database is outdated; synchronization   is required.
     */
    public static final int NERR_SyncRequired = 0X000008C9;

    /**
     * The network connection could not be found.
     */
    public static final int NERR_UseNotFound = 0X000008CA;

    /**
     * This asg_type is invalid.
     */
    public static final int NERR_BadAsgType = 0X000008CB;

    /**
     * This device is currently being shared.
     */
    public static final int NERR_DeviceIsShared = 0X000008CC;

    /**
     * The computer name could not be added as a message   alias. The name might already exist on the network.
     */
    public static final int NERR_NoComputerName = 0X000008DE;

    /**
     * The Messenger service is already started.
     */
    public static final int NERR_MsgAlreadyStarted = 0X000008DF;

    /**
     * The Messenger service failed to start.
     */
    public static final int NERR_MsgInitFailed = 0X000008E0;

    /**
     * The message alias could not be found on the network.
     */
    public static final int NERR_NameNotFound = 0X000008E1;

    /**
     * This message alias has already been forwarded.
     */
    public static final int NERR_AlreadyForwarded = 0X000008E2;

    /**
     * This message alias has been added but is still   forwarded.
     */
    public static final int NERR_AddForwarded = 0X000008E3;

    /**
     * This message alias already exists locally.
     */
    public static final int NERR_AlreadyExists = 0X000008E4;

    /**
     * The maximum number of added message aliases has been   exceeded.
     */
    public static final int NERR_TooManyNames = 0X000008E5;

    /**
     * The computer name could not be deleted.
     */
    public static final int NERR_DelComputerName = 0X000008E6;

    /**
     * Messages cannot be forwarded back to the same   workstation.
     */
    public static final int NERR_LocalForward = 0X000008E7;

    /**
     * An error occurred in the domain message processor.
     */
    public static final int NERR_GrpMsgProcessor = 0X000008E8;

    /**
     * The message was sent, but the recipient has paused the   Messenger service.
     */
    public static final int NERR_PausedRemote = 0X000008E9;

    /**
     * The message was sent but not received.
     */
    public static final int NERR_BadReceive = 0X000008EA;

    /**
     * The message alias is currently in use. Try again   later.
     */
    public static final int NERR_NameInUse = 0X000008EB;

    /**
     * The Messenger service has not been started.
     */
    public static final int NERR_MsgNotStarted = 0X000008EC;

    /**
     * The name is not on the local computer.
     */
    public static final int NERR_NotLocalName = 0X000008ED;

    /**
     * The forwarded message alias could not be found on the   network.
     */
    public static final int NERR_NoForwardName = 0X000008EE;

    /**
     * The message alias table on the remote station is full.
     */
    public static final int NERR_RemoteFull = 0X000008EF;

    /**
     * Messages for this alias are not currently being   forwarded.
     */
    public static final int NERR_NameNotForwarded = 0X000008F0;

    /**
     * The broadcast message was truncated.
     */
    public static final int NERR_TruncatedBroadcast = 0X000008F1;

    /**
     * This is an invalid device name.
     */
    public static final int NERR_InvalidDevice = 0X000008F6;

    /**
     * A write fault occurred.
     */
    public static final int NERR_WriteFault = 0X000008F7;

    /**
     * A duplicate message alias exists on the network.
     */
    public static final int NERR_DuplicateName = 0X000008F9;

    /**
     * This message alias will be deleted later.
     */
    public static final int NERR_DeleteLater = 0X000008FA;

    /**
     * The message alias was not successfully deleted from   all networks.
     */
    public static final int NERR_IncompleteDel = 0X000008FB;

    /**
     * This operation is not supported on computers with   multiple networks.
     */
    public static final int NERR_MultipleNets = 0X000008FC;

    /**
     * This shared resource does not exist.
     */
    public static final int NERR_NetNameNotFound = 0X00000906;

    /**
     * This device is not shared.
     */
    public static final int NERR_DeviceNotShared = 0X00000907;

    /**
     * A session does not exist with that computer name.
     */
    public static final int NERR_ClientNameNotFound = 0X00000908;

    /**
     * There is not an open file with that identification   number.
     */
    public static final int NERR_FileIdNotFound = 0X0000090A;

    /**
     * A failure occurred when executing a remote   administration command.
     */
    public static final int NERR_ExecFailure = 0X0000090B;

    /**
     * A failure occurred when opening a remote temporary   file.
     */
    public static final int NERR_TmpFile = 0X0000090C;

    /**
     * The data returned from a remote administration command   has been truncated to 64 KB.
     */
    public static final int NERR_TooMuchData = 0X0000090D;

    /**
     * This device cannot be shared as both a spooled and a   nonspooled resource.
     */
    public static final int NERR_DeviceShareConflict = 0X0000090E;

    /**
     * The information in the list of servers might be   incorrect.
     */
    public static final int NERR_BrowserTableIncomplete = 0X0000090F;

    /**
     * The computer is not active in this domain.
     */
    public static final int NERR_NotLocalDomain = 0X00000910;

    /**
     * The share must be removed from the Distributed File   System (DFS) before it can be deleted.
     */
    public static final int NERR_IsDfsShare = 0X00000911;

    /**
     * The operation is invalid for this device.
     */
    public static final int NERR_DevInvalidOpCode = 0X0000091B;

    /**
     * This device cannot be shared.
     */
    public static final int NERR_DevNotFound = 0X0000091C;

    /**
     * This device was not open.
     */
    public static final int NERR_DevNotOpen = 0X0000091D;

    /**
     * This device name list is invalid.
     */
    public static final int NERR_BadQueueDevString = 0X0000091E;

    /**
     * The queue priority is invalid.
     */
    public static final int NERR_BadQueuePriority = 0X0000091F;

    /**
     * There are no shared communication devices.
     */
    public static final int NERR_NoCommDevs = 0X00000921;

    /**
     * The queue you specified does not exist.
     */
    public static final int NERR_QueueNotFound = 0X00000922;

    /**
     * This list of devices is invalid.
     */
    public static final int NERR_BadDevString = 0X00000924;

    /**
     * The requested device is invalid.
     */
    public static final int NERR_BadDev = 0X00000925;

    /**
     * This device is already in use by the spooler.
     */
    public static final int NERR_InUseBySpooler = 0X00000926;

    /**
     * This device is already in use as a communication   device.
     */
    public static final int NERR_CommDevInUse = 0X00000927;

    /**
     * This computer name is invalid.
     */
    public static final int NERR_InvalidComputer = 0X0000092F;

    /**
     * The string and prefix specified are too long.
     */
    public static final int NERR_MaxLenExceeded = 0X00000932;

    /**
     * This path component is invalid.
     */
    public static final int NERR_BadComponent = 0X00000934;

    /**
     * Could not determine the type of input.
     */
    public static final int NERR_CantType = 0X00000935;

    /**
     * The buffer for types is not big enough.
     */
    public static final int NERR_TooManyEntries = 0X0000093A;

    /**
     * Profile files cannot exceed 64 KB.
     */
    public static final int NERR_ProfileFileTooBig = 0X00000942;

    /**
     * The start offset is out of range.
     */
    public static final int NERR_ProfileOffset = 0X00000943;

    /**
     * The system cannot delete current connections to   network resources.
     */
    public static final int NERR_ProfileCleanup = 0X00000944;

    /**
     * The system was unable to parse the command line in   this file.
     */
    public static final int NERR_ProfileUnknownCmd = 0X00000945;

    /**
     * An error occurred while loading the profile file.
     */
    public static final int NERR_ProfileLoadErr = 0X00000946;

    /**
     * Errors occurred while saving the profile file. The   profile was partially saved.
     */
    public static final int NERR_ProfileSaveErr = 0X00000947;

    /**
     * Log file %1 is full.
     */
    public static final int NERR_LogOverflow = 0X00000949;

    /**
     * This log file has changed between reads.
     */
    public static final int NERR_LogFileChanged = 0X0000094A;

    /**
     * Log file %1 is corrupt.
     */
    public static final int NERR_LogFileCorrupt = 0X0000094B;

    /**
     * The source path cannot be a directory.
     */
    public static final int NERR_SourceIsDir = 0X0000094C;

    /**
     * The source path is illegal.
     */
    public static final int NERR_BadSource = 0X0000094D;

    /**
     * The destination path is illegal.
     */
    public static final int NERR_BadDest = 0X0000094E;

    /**
     * The source and destination paths are on different   servers.
     */
    public static final int NERR_DifferentServers = 0X0000094F;

    /**
     * The Run server you requested is paused.
     */
    public static final int NERR_RunSrvPaused = 0X00000951;

    /**
     * An error occurred when communicating with a Run   server.
     */
    public static final int NERR_ErrCommRunSrv = 0X00000955;

    /**
     * An error occurred when starting a background process.
     */
    public static final int NERR_ErrorExecingGhost = 0X00000957;

    /**
     * The shared resource you are connected to could not be   found.
     */
    public static final int NERR_ShareNotFound = 0X00000958;

    /**
     * The LAN adapter number is invalid.
     */
    public static final int NERR_InvalidLana = 0X00000960;

    /**
     * There are open files on the connection.
     */
    public static final int NERR_OpenFiles = 0X00000961;

    /**
     * Active connections still exist.
     */
    public static final int NERR_ActiveConns = 0X00000962;

    /**
     * This share name or password is invalid.
     */
    public static final int NERR_BadPasswordCore = 0X00000963;

    /**
     * The device is being accessed by an active process.
     */
    public static final int NERR_DevInUse = 0X00000964;

    /**
     * The drive letter is in use locally.
     */
    public static final int NERR_LocalDrive = 0X00000965;

    /**
     * The specified client is already registered for the   specified event.
     */
    public static final int NERR_AlertExists = 0X0000097E;

    /**
     * The alert table is full.
     */
    public static final int NERR_TooManyAlerts = 0X0000097F;

    /**
     * An invalid or nonexistent alert name was raised.
     */
    public static final int NERR_NoSuchAlert = 0X00000980;

    /**
     * The alert recipient is invalid.
     */
    public static final int NERR_BadRecipient = 0X00000981;

    /**
     * A user\'s session with this server has been deleted.
     */
    public static final int NERR_AcctLimitExceeded = 0X00000982;

    /**
     * The log file does not contain the requested record   number.
     */
    public static final int NERR_InvalidLogSeek = 0X00000988;

    /**
     * The user accounts database is not configured   correctly.
     */
    public static final int NERR_BadUasConfig = 0X00000992;

    /**
     * This operation is not permitted when the Net Logon   service is running.
     */
    public static final int NERR_InvalidUASOp = 0X00000993;

    /**
     * This operation is not allowed on the last   administrative account.
     */
    public static final int NERR_LastAdmin = 0X00000994;

    /**
     * Could not find the domain controller for this domain.
     */
    public static final int NERR_DCNotFound = 0X00000995;

    /**
     * Could not set logon information for this user.
     */
    public static final int NERR_LogonTrackingError = 0X00000996;

    /**
     * The Net Logon service has not been started.
     */
    public static final int NERR_NetlogonNotStarted = 0X00000997;

    /**
     * Unable to add to the user accounts database.
     */
    public static final int NERR_CanNotGrowUASFile = 0X00000998;

    /**
     * This server\'s clock is not synchronized with the PDC\'s   clock.
     */
    public static final int NERR_TimeDiffAtDC = 0X00000999;

    /**
     * A password mismatch has been detected.
     */
    public static final int NERR_PasswordMismatch = 0X0000099A;

    /**
     * The server identification does not specify a valid   server.
     */
    public static final int NERR_NoSuchServer = 0X0000099C;

    /**
     * The session identification does not specify a valid   session.
     */
    public static final int NERR_NoSuchSession = 0X0000099D;

    /**
     * The connection identification does not specify a valid   connection.
     */
    public static final int NERR_NoSuchConnection = 0X0000099E;

    /**
     * There is no space for another entry in the table of   available servers.
     */
    public static final int NERR_TooManyServers = 0X0000099F;

    /**
     * The server has reached the maximum number of sessions   it supports.
     */
    public static final int NERR_TooManySessions = 0X000009A0;

    /**
     * The server has reached the maximum number of   connections it supports.
     */
    public static final int NERR_TooManyConnections = 0X000009A1;

    /**
     * The server cannot open more files because it has   reached its maximum number.
     */
    public static final int NERR_TooManyFiles = 0X000009A2;

    /**
     * There are no alternate servers registered on this   server.
     */
    public static final int NERR_NoAlternateServers = 0X000009A3;

    /**
     * Try the down-level (remote admin protocol) version of   API instead.
     */
    public static final int NERR_TryDownLevel = 0X000009A6;

    /**
     * The uninterruptible power supply (UPS) driver could   not be accessed by the UPS service.
     */
    public static final int NERR_UPSDriverNotStarted = 0X000009B0;

    /**
     * The UPS service is not configured correctly.
     */
    public static final int NERR_UPSInvalidConfig = 0X000009B1;

    /**
     * The UPS service could not access the specified Comm   Port.
     */
    public static final int NERR_UPSInvalidCommPort = 0X000009B2;

    /**
     * The UPS indicated a line fail or low battery   situation. Service not started.
     */
    public static final int NERR_UPSSignalAsserted = 0X000009B3;

    /**
     * The UPS service failed to perform a system shut down.
     */
    public static final int NERR_UPSShutdownFailed = 0X000009B4;

    /**
     * The program below returned an MS-DOS error code.
     */
    public static final int NERR_BadDosRetCode = 0X000009C4;

    /**
     * The program below needs more memory.
     */
    public static final int NERR_ProgNeedsExtraMem = 0X000009C5;

    /**
     * The program below called an unsupported MS-DOS   function.
     */
    public static final int NERR_BadDosFunction = 0X000009C6;

    /**
     * The workstation failed to boot.
     */
    public static final int NERR_RemoteBootFailed = 0X000009C7;

    /**
     * The file below is corrupt.
     */
    public static final int NERR_BadFileCheckSum = 0X000009C8;

    /**
     * No loader is specified in the boot-block definition   file.
     */
    public static final int NERR_NoRplBootSystem = 0X000009C9;

    /**
     * NetBIOS returned an error: The network control blocks   (NCBs) and Server Message Block (SMB) are dumped above.
     */
    public static final int NERR_RplLoadrNetBiosErr = 0X000009CA;

    /**
     * A disk I/O error occurred.
     */
    public static final int NERR_RplLoadrDiskErr = 0X000009CB;

    /**
     * Image parameter substitution failed.
     */
    public static final int NERR_ImageParamErr = 0X000009CC;

    /**
     * Too many image parameters cross disk sector   boundaries.
     */
    public static final int NERR_TooManyImageParams = 0X000009CD;

    /**
     * The image was not generated from an MS-DOS disk   formatted with /S.
     */
    public static final int NERR_NonDosFloppyUsed = 0X000009CE;

    /**
     * Remote boot will be restarted later.
     */
    public static final int NERR_RplBootRestart = 0X000009CF;

    /**
     * The call to the Remoteboot server failed.
     */
    public static final int NERR_RplSrvrCallFailed = 0X000009D0;

    /**
     * Cannot connect to the Remoteboot server.
     */
    public static final int NERR_CantConnectRplSrvr = 0X000009D1;

    /**
     * Cannot open image file on the Remoteboot server.
     */
    public static final int NERR_CantOpenImageFile = 0X000009D2;

    /**
     * Connecting to the Remoteboot server.
     */
    public static final int NERR_CallingRplSrvr = 0X000009D3;

    /**
     * Connecting to the Remoteboot server.
     */
    public static final int NERR_StartingRplBoot = 0X000009D4;

    /**
     * Remote boot service was stopped, check the error log   for the cause of the problem.
     */
    public static final int NERR_RplBootServiceTerm = 0X000009D5;

    /**
     * Remote boot startup failed; check the error log for   the cause of the problem.
     */
    public static final int NERR_RplBootStartFailed = 0X000009D6;

    /**
     * A second connection to a Remoteboot resource is not allowed.
     */
    public static final int NERR_RPL_CONNECTED = 0X000009D7;

    /**
     * The browser service was configured with   MaintainServerList=No.
     */
    public static final int NERR_BrowserConfiguredToNotRun = 0X000009F6;

    /**
     * Service failed to start because none of the network   adapters started with this service.
     */
    public static final int NERR_RplNoAdaptersStarted = 0X00000A32;

    /**
     * Service failed to start due to bad startup information   in the registry.
     */
    public static final int NERR_RplBadRegistry = 0X00000A33;

    /**
     * Service failed to start because its database is absent   or corrupt.
     */
    public static final int NERR_RplBadDatabase = 0X00000A34;

    /**
     * Service failed to start because the RPLFILES share is   absent.
     */
    public static final int NERR_RplRplfilesShare = 0X00000A35;

    /**
     * Service failed to start because the RPLUSER group is   absent.
     */
    public static final int NERR_RplNotRplServer = 0X00000A36;

    /**
     * Cannot enumerate service records.
     */
    public static final int NERR_RplCannotEnum = 0X00000A37;

    /**
     * Workstation record information has been corrupted.
     */
    public static final int NERR_RplWkstaInfoCorrupted = 0X00000A38;

    /**
     * Workstation record was not found.
     */
    public static final int NERR_RplWkstaNotFound = 0X00000A39;

    /**
     * Workstation name is in use by some other workstation.
     */
    public static final int NERR_RplWkstaNameUnavailable = 0X00000A3A;

    /**
     * Profile record information has been corrupted.
     */
    public static final int NERR_RplProfileInfoCorrupted = 0X00000A3B;

    /**
     * Profile record was not found.
     */
    public static final int NERR_RplProfileNotFound = 0X00000A3C;

    /**
     * Profile name is in use by some other profile.
     */
    public static final int NERR_RplProfileNameUnavailable = 0X00000A3D;

    /**
     * There are workstations using this profile.
     */
    public static final int NERR_RplProfileNotEmpty = 0X00000A3E;

    /**
     * Configuration record information has been corrupted.
     */
    public static final int NERR_RplConfigInfoCorrupted = 0X00000A3F;

    /**
     * Configuration record was not found.
     */
    public static final int NERR_RplConfigNotFound = 0X00000A40;

    /**
     * Adapter ID record information has been corrupted.
     */
    public static final int NERR_RplAdapterInfoCorrupted = 0X00000A41;

    /**
     * An internal service error has occurred.
     */
    public static final int NERR_RplInternal = 0X00000A42;

    /**
     * Vendor ID record information has been corrupted.
     */
    public static final int NERR_RplVendorInfoCorrupted = 0X00000A43;

    /**
     * Boot block record information has been corrupted.
     */
    public static final int NERR_RplBootInfoCorrupted = 0X00000A44;

    /**
     * The user account for this workstation record is   missing.
     */
    public static final int NERR_RplWkstaNeedsUserAcct = 0X00000A45;

    /**
     * The RPLUSER local group could not be found.
     */
    public static final int NERR_RplNeedsRPLUSERAcct = 0X00000A46;

    /**
     * Boot block record was not found.
     */
    public static final int NERR_RplBootNotFound = 0X00000A47;

    /**
     * Chosen profile is incompatible with this workstation.
     */
    public static final int NERR_RplIncompatibleProfile = 0X00000A48;

    /**
     * Chosen network adapter ID is in use by some other   workstation.
     */
    public static final int NERR_RplAdapterNameUnavailable = 0X00000A49;

    /**
     * There are profiles using this configuration.
     */
    public static final int NERR_RplConfigNotEmpty = 0X00000A4A;

    /**
     * There are workstations, profiles, or configurations   using this boot block.
     */
    public static final int NERR_RplBootInUse = 0X00000A4B;

    /**
     * Service failed to back up the Remoteboot database.
     */
    public static final int NERR_RplBackupDatabase = 0X00000A4C;

    /**
     * Adapter record was not found.
     */
    public static final int NERR_RplAdapterNotFound = 0X00000A4D;

    /**
     * Vendor record was not found.
     */
    public static final int NERR_RplVendorNotFound = 0X00000A4E;

    /**
     * Vendor name is in use by some other vendor record.
     */
    public static final int NERR_RplVendorNameUnavailable = 0X00000A4F;

    /**
     * The boot name or vendor ID is in use by some other   boot block record.
     */
    public static final int NERR_RplBootNameUnavailable = 0X00000A50;

    /**
     * The configuration name is in use by some other   configuration.
     */
    public static final int NERR_RplConfigNameUnavailable = 0X00000A51;

    /**
     * The internal database maintained by the DFS service is   corrupt.
     */
    public static final int NERR_DfsInternalCorruption = 0X00000A64;

    /**
     * One of the records in the internal DFS database is   corrupt.
     */
    public static final int NERR_DfsVolumeDataCorrupt = 0X00000A65;

    /**
     * There is no DFS name whose entry path matches the   input entry path.
     */
    public static final int NERR_DfsNoSuchVolume = 0X00000A66;

    /**
     * A root or link with the given name already exists.
     */
    public static final int NERR_DfsVolumeAlreadyExists = 0X00000A67;

    /**
     * The server share specified is already shared in the   DFS.
     */
    public static final int NERR_DfsAlreadyShared = 0X00000A68;

    /**
     * The indicated server share does not support the   indicated DFS namespace.
     */
    public static final int NERR_DfsNoSuchShare = 0X00000A69;

    /**
     * The operation is not valid in this portion of the   namespace.
     */
    public static final int NERR_DfsNotALeafVolume = 0X00000A6A;

    /**
     * The operation is not valid in this portion of the   namespace.
     */
    public static final int NERR_DfsLeafVolume = 0X00000A6B;

    /**
     * The operation is ambiguous because the link has   multiple servers.
     */
    public static final int NERR_DfsVolumeHasMultipleServers = 0X00000A6C;

    /**
     * Unable to create a link.
     */
    public static final int NERR_DfsCantCreateJunctionPoint = 0X00000A6D;

    /**
     * The server is not DFS-aware.
     */
    public static final int NERR_DfsServerNotDfsAware = 0X00000A6E;

    /**
     * The specified rename target path is invalid.
     */
    public static final int NERR_DfsBadRenamePath = 0X00000A6F;

    /**
     * The specified DFS link is offline.
     */
    public static final int NERR_DfsVolumeIsOffline = 0X00000A70;

    /**
     * The specified server is not a server for this link.
     */
    public static final int NERR_DfsNoSuchServer = 0X00000A71;

    /**
     * A cycle in the DFS name was detected.
     */
    public static final int NERR_DfsCyclicalName = 0X00000A72;

    /**
     * The operation is not supported on a server-based DFS.
     */
    public static final int NERR_DfsNotSupportedInServerDfs = 0X00000A73;

    /**
     * This link is already supported by the specified server   share.
     */
    public static final int NERR_DfsDuplicateService = 0X00000A74;

    /**
     * Cannot remove the last server share supporting this   root or link.
     */
    public static final int NERR_DfsCantRemoveLastServerShare = 0X00000A75;

    /**
     * The operation is not supported for an inter-DFS link.
     */
    public static final int NERR_DfsVolumeIsInterDfs = 0X00000A76;

    /**
     * The internal state of the DFS Service has become   inconsistent.
     */
    public static final int NERR_DfsInconsistent = 0X00000A77;

    /**
     * The DFS Service has been installed on the specified   server.
     */
    public static final int NERR_DfsServerUpgraded = 0X00000A78;

    /**
     * The DFS data being reconciled is identical.
     */
    public static final int NERR_DfsDataIsIdentical = 0X00000A79;

    /**
     * The DFS root cannot be deleted. Uninstall DFS if   required.
     */
    public static final int NERR_DfsCantRemoveDfsRoot = 0X00000A7A;

    /**
     * A child or parent directory of the share is already in   a DFS.
     */
    public static final int NERR_DfsChildOrParentInDfs = 0X00000A7B;

    /**
     * DFS internal error.
     */
    public static final int NERR_DfsInternalError = 0X00000A82;

    /**
     * This machine is already joined to a domain.
     */
    public static final int NERR_SetupAlreadyJoined = 0X00000A83;

    /**
     * This machine is not currently joined to a domain.
     */
    public static final int NERR_SetupNotJoined = 0X00000A84;

    /**
     * This machine is a domain controller and cannot be   unjoined from a domain.
     */
    public static final int NERR_SetupDomainController = 0X00000A85;

    /**
     * The destination domain controller does not support   creating machine accounts in organizational units (OUs).
     */
    public static final int NERR_DefaultJoinRequired = 0X00000A86;

    /**
     * The specified workgroup name is invalid.
     */
    public static final int NERR_InvalidWorkgroupName = 0X00000A87;

    /**
     * The specified computer name is incompatible with the   default language used on the domain controller.
     */
    public static final int NERR_NameUsesIncompatibleCodePage = 0X00000A88;

    /**
     * The specified computer account could not be found.
     */
    public static final int NERR_ComputerAccountNotFound = 0X00000A89;

    /**
     * This version of Windows cannot be joined to a domain.
     */
    public static final int NERR_PersonalSku = 0X00000A8A;

    /**
     * The password must change at the next logon.
     */
    public static final int NERR_PasswordMustChange = 0X00000A8D;

    /**
     * The account is locked out.
     */
    public static final int NERR_AccountLockedOut = 0X00000A8E;

    /**
     * The password is too long.
     */
    public static final int NERR_PasswordTooLong = 0X00000A8F;

    /**
     * The password does not meet the complexity policy.
     */
    public static final int NERR_PasswordNotComplexEnough = 0X00000A90;

    /**
     * The password does not meet the requirements of the   password filter DLLs.
     */
    public static final int NERR_PasswordFilterError = 0X00000A91;

    /**
     * The specified print monitor is unknown.
     */
    public static final int ERROR_UNKNOWN_PRINT_MONITOR = 0X00000BB8;

    /**
     * The specified printer driver is currently in use.
     */
    public static final int ERROR_PRINTER_DRIVER_IN_USE = 0X00000BB9;

    /**
     * The spool file was not found.
     */
    public static final int ERROR_SPOOL_FILE_NOT_FOUND = 0X00000BBA;

    /**
     * A StartDocPrinter call was not issued.
     */
    public static final int ERROR_SPL_NO_STARTDOC = 0X00000BBB;

    /**
     * An AddJob call was not issued.
     */
    public static final int ERROR_SPL_NO_ADDJOB = 0X00000BBC;

    /**
     * The specified print processor has already been   installed.
     */
    public static final int ERROR_PRINT_PROCESSOR_ALREADY_INSTALLED = 0X00000BBD;

    /**
     * The specified print monitor has already been   installed.
     */
    public static final int ERROR_PRINT_MONITOR_ALREADY_INSTALLED = 0X00000BBE;

    /**
     * The specified print monitor does not have the required   functions.
     */
    public static final int ERROR_INVALID_PRINT_MONITOR = 0X00000BBF;

    /**
     * The specified print monitor is currently in use.
     */
    public static final int ERROR_PRINT_MONITOR_IN_USE = 0X00000BC0;

    /**
     * The requested operation is not allowed when there are   jobs queued to the printer.
     */
    public static final int ERROR_PRINTER_HAS_JOBS_QUEUED = 0X00000BC1;

    /**
     * The requested operation is successful. Changes will   not be effective until the system is rebooted.
     */
    public static final int ERROR_SUCCESS_REBOOT_REQUIRED = 0X00000BC2;

    /**
     * The requested operation is successful. Changes will   not be effective until the service is restarted.
     */
    public static final int ERROR_SUCCESS_RESTART_REQUIRED = 0X00000BC3;

    /**
     * No printers were found.
     */
    public static final int ERROR_PRINTER_NOT_FOUND = 0X00000BC4;

    /**
     * The printer driver is known to be unreliable.
     */
    public static final int ERROR_PRINTER_DRIVER_WARNED = 0X00000BC5;

    /**
     * The printer driver is known to harm the system.
     */
    public static final int ERROR_PRINTER_DRIVER_BLOCKED = 0X00000BC6;

    /**
     * The specified printer driver package is currently in   use.
     */
    public static final int ERROR_PRINTER_DRIVER_PACKAGE_IN_USE = 0X00000BC7;

    /**
     * Unable to find a core driver package that is required   by the printer driver package.
     */
    public static final int ERROR_CORE_DRIVER_PACKAGE_NOT_FOUND = 0X00000BC8;

    /**
     * The requested operation failed. A system reboot is   required to roll back changes made.
     */
    public static final int ERROR_FAIL_REBOOT_REQUIRED = 0X00000BC9;

    /**
     * The requested operation failed. A system reboot has   been initiated to roll back changes made.
     */
    public static final int ERROR_FAIL_REBOOT_INITIATED = 0X00000BCA;

    /**
     * The specified printer driver was not found on the system and needs to be downloaded
     */
    public static final int ERROR_PRINTER_DRIVER_DOWNLOAD_NEEDED = 0x00000BCB;

    /**
     * Reissue the given operation as a cached I/O operation.
     */
    public static final int ERROR_IO_REISSUE_AS_CACHED = 0X00000F6E;

    /**
     * Windows Internet Name Service (WINS) encountered an   error while processing the command.
     */
    public static final int ERROR_WINS_INTERNAL = 0X00000FA0;

    /**
     * The local WINS cannot be deleted.
     */
    public static final int ERROR_CAN_NOT_DEL_LOCAL_WINS = 0X00000FA1;

    /**
     * The importation from the file failed.
     */
    public static final int ERROR_STATIC_INIT = 0X00000FA2;

    /**
     * The backup failed. Was a full backup done before?
     */
    public static final int ERROR_INC_BACKUP = 0X00000FA3;

    /**
     * The backup failed. Check the directory to which you   are backing the database.
     */
    public static final int ERROR_FULL_BACKUP = 0X00000FA4;

    /**
     * The name does not exist in the WINS database.
     */
    public static final int ERROR_REC_NON_EXISTENT = 0X00000FA5;

    /**
     * Replication with a nonconfigured partner is not   allowed.
     */
    public static final int ERROR_RPL_NOT_ALLOWED = 0X00000FA6;

    /**
     * The version of the supplied content information is not   supported.
     */
    public static final int PEERDIST_ERROR_CONTENTINFO_VERSION_UNSUPPORTED = 0X00000FD2;

    /**
     * The supplied content information is malformed.
     */
    public static final int PEERDIST_ERROR_CANNOT_PARSE_CONTENTINFO = 0X00000FD3;

    /**
     * The requested data cannot be found in local or peer   caches.
     */
    public static final int PEERDIST_ERROR_MISSING_DATA = 0X00000FD4;

    /**
     * No more data is available or required.
     */
    public static final int PEERDIST_ERROR_NO_MORE = 0X00000FD5;

    /**
     * The supplied object has not been initialized.
     */
    public static final int PEERDIST_ERROR_NOT_INITIALIZED = 0X00000FD6;

    /**
     * The supplied object has already been initialized.
     */
    public static final int PEERDIST_ERROR_ALREADY_INITIALIZED = 0X00000FD7;

    /**
     * A shutdown operation is already in progress.
     */
    public static final int PEERDIST_ERROR_SHUTDOWN_IN_PROGRESS = 0X00000FD8;

    /**
     * The supplied object has already been invalidated.
     */
    public static final int PEERDIST_ERROR_INVALIDATED = 0X00000FD9;

    /**
     * An element already exists and was not replaced.
     */
    public static final int PEERDIST_ERROR_ALREADY_EXISTS = 0X00000FDA;

    /**
     * Cannot cancel the requested operation as it has   already been completed.
     */
    public static final int PEERDIST_ERROR_OPERATION_NOTFOUND = 0X00000FDB;

    /**
     * Cannot perform the requested operation because it has   already been carried out.
     */
    public static final int PEERDIST_ERROR_ALREADY_COMPLETED = 0X00000FDC;

    /**
     * An operation accessed data beyond the bounds of valid   data.
     */
    public static final int PEERDIST_ERROR_OUT_OF_BOUNDS = 0X00000FDD;

    /**
     * The requested version is not supported.
     */
    public static final int PEERDIST_ERROR_VERSION_UNSUPPORTED = 0X00000FDE;

    /**
     * A configuration value is invalid.
     */
    public static final int PEERDIST_ERROR_INVALID_CONFIGURATION = 0X00000FDF;

    /**
     * The SKU is not licensed.
     */
    public static final int PEERDIST_ERROR_NOT_LICENSED = 0X00000FE0;

    /**
     * PeerDist Service is still initializing and will be   available shortly.
     */
    public static final int PEERDIST_ERROR_SERVICE_UNAVAILABLE = 0X00000FE1;

    /**
     * The Dynamic Host Configuration Protocol (DHCP) client   has obtained an IP address that is already in use on the network. The local   interface will be disabled until the DHCP client can obtain a new address.
     */
    public static final int ERROR_DHCP_ADDRESS_CONFLICT = 0X00001004;

    /**
     * The GUID passed was not recognized as valid by a WMI   data provider.
     */
    public static final int ERROR_WMI_GUID_NOT_FOUND = 0X00001068;

    /**
     * The instance name passed was not recognized as valid   by a WMI data provider.
     */
    public static final int ERROR_WMI_INSTANCE_NOT_FOUND = 0X00001069;

    /**
     * The data item ID passed was not recognized as valid by   a WMI data provider.
     */
    public static final int ERROR_WMI_ITEMID_NOT_FOUND = 0X0000106A;

    /**
     * The WMI request could not be completed and should be   retried.
     */
    public static final int ERROR_WMI_TRY_AGAIN = 0X0000106B;

    /**
     * The WMI data provider could not be located.
     */
    public static final int ERROR_WMI_DP_NOT_FOUND = 0X0000106C;

    /**
     * The WMI data provider references an instance set that   has not been registered.
     */
    public static final int ERROR_WMI_UNRESOLVED_INSTANCE_REF = 0X0000106D;

    /**
     * The WMI data block or event notification has already   been enabled.
     */
    public static final int ERROR_WMI_ALREADY_ENABLED = 0X0000106E;

    /**
     * The WMI data block is no longer available.
     */
    public static final int ERROR_WMI_GUID_DISCONNECTED = 0X0000106F;

    /**
     * The WMI data service is not available.
     */
    public static final int ERROR_WMI_SERVER_UNAVAILABLE = 0X00001070;

    /**
     * The WMI data provider failed to carry out the request.
     */
    public static final int ERROR_WMI_DP_FAILED = 0X00001071;

    /**
     * The WMI Managed Object Format (MOF) information is not   valid.
     */
    public static final int ERROR_WMI_INVALID_MOF = 0X00001072;

    /**
     * The WMI registration information is not valid.
     */
    public static final int ERROR_WMI_INVALID_REGINFO = 0X00001073;

    /**
     * The WMI data block or event notification has already   been disabled.
     */
    public static final int ERROR_WMI_ALREADY_DISABLED = 0X00001074;

    /**
     * The WMI data item or data block is read-only.
     */
    public static final int ERROR_WMI_READ_ONLY = 0X00001075;

    /**
     * The WMI data item or data block could not be changed.
     */
    public static final int ERROR_WMI_SET_FAILURE = 0X00001076;

    /**
     * The media identifier does not represent a valid   medium.
     */
    public static final int ERROR_INVALID_MEDIA = 0X000010CC;

    /**
     * The library identifier does not represent a valid   library.
     */
    public static final int ERROR_INVALID_LIBRARY = 0X000010CD;

    /**
     * The media pool identifier does not represent a valid   media pool.
     */
    public static final int ERROR_INVALID_MEDIA_POOL = 0X000010CE;

    /**
     * The drive and medium are not compatible, or they exist   in different libraries.
     */
    public static final int ERROR_DRIVE_MEDIA_MISMATCH = 0X000010CF;

    /**
     * The medium currently exists in an offline library and   must be online to perform this operation.
     */
    public static final int ERROR_MEDIA_OFFLINE = 0X000010D0;

    /**
     * The operation cannot be performed on an offline   library.
     */
    public static final int ERROR_LIBRARY_OFFLINE = 0X000010D1;

    /**
     * The library, drive, or media pool is empty.
     */
    public static final int ERROR_EMPTY = 0X000010D2;

    /**
     * The library, drive, or media pool must be empty to   perform this operation.
     */
    public static final int ERROR_NOT_EMPTY = 0X000010D3;

    /**
     * No media is currently available in this media pool or   library.
     */
    public static final int ERROR_MEDIA_UNAVAILABLE = 0X000010D4;

    /**
     * A resource required for this operation is disabled.
     */
    public static final int ERROR_RESOURCE_DISABLED = 0X000010D5;

    /**
     * The media identifier does not represent a valid   cleaner.
     */
    public static final int ERROR_INVALID_CLEANER = 0X000010D6;

    /**
     * The drive cannot be cleaned or does not support   cleaning.
     */
    public static final int ERROR_UNABLE_TO_CLEAN = 0X000010D7;

    /**
     * The object identifier does not represent a valid   object.
     */
    public static final int ERROR_OBJECT_NOT_FOUND = 0X000010D8;

    /**
     * Unable to read from or write to the database.
     */
    public static final int ERROR_DATABASE_FAILURE = 0X000010D9;

    /**
     * The database is full.
     */
    public static final int ERROR_DATABASE_FULL = 0X000010DA;

    /**
     * The medium is not compatible with the device or media   pool.
     */
    public static final int ERROR_MEDIA_INCOMPATIBLE = 0X000010DB;

    /**
     * The resource required for this operation does not   exist.
     */
    public static final int ERROR_RESOURCE_NOT_PRESENT = 0X000010DC;

    /**
     * The operation identifier is not valid.
     */
    public static final int ERROR_INVALID_OPERATION = 0X000010DD;

    /**
     * The media is not mounted or ready for use.
     */
    public static final int ERROR_MEDIA_NOT_AVAILABLE = 0X000010DE;

    /**
     * The device is not ready for use.
     */
    public static final int ERROR_DEVICE_NOT_AVAILABLE = 0X000010DF;

    /**
     * The operator or administrator has refused the request.
     */
    public static final int ERROR_REQUEST_REFUSED = 0X000010E0;

    /**
     * The drive identifier does not represent a valid drive.
     */
    public static final int ERROR_INVALID_DRIVE_OBJECT = 0X000010E1;

    /**
     * Library is full. No slot is available for use.
     */
    public static final int ERROR_LIBRARY_FULL = 0X000010E2;

    /**
     * The transport cannot access the medium.
     */
    public static final int ERROR_MEDIUM_NOT_ACCESSIBLE = 0X000010E3;

    /**
     * Unable to load the medium into the drive.
     */
    public static final int ERROR_UNABLE_TO_LOAD_MEDIUM = 0X000010E4;

    /**
     * Unable to retrieve the drive status.
     */
    public static final int ERROR_UNABLE_TO_INVENTORY_DRIVE = 0X000010E5;

    /**
     * Unable to retrieve the slot status.
     */
    public static final int ERROR_UNABLE_TO_INVENTORY_SLOT = 0X000010E6;

    /**
     * Unable to retrieve status about the transport.
     */
    public static final int ERROR_UNABLE_TO_INVENTORY_TRANSPORT = 0X000010E7;

    /**
     * Cannot use the transport because it is already in use.
     */
    public static final int ERROR_TRANSPORT_FULL = 0X000010E8;

    /**
     * Unable to open or close the inject/eject port.
     */
    public static final int ERROR_CONTROLLING_IEPORT = 0X000010E9;

    /**
     * Unable to eject the medium because it is in a drive.
     */
    public static final int ERROR_UNABLE_TO_EJECT_MOUNTED_MEDIA = 0X000010EA;

    /**
     * A cleaner slot is already reserved.
     */
    public static final int ERROR_CLEANER_SLOT_SET = 0X000010EB;

    /**
     * A cleaner slot is not reserved.
     */
    public static final int ERROR_CLEANER_SLOT_NOT_SET = 0X000010EC;

    /**
     * The cleaner cartridge has performed the maximum number   of drive cleanings.
     */
    public static final int ERROR_CLEANER_CARTRIDGE_SPENT = 0X000010ED;

    /**
     * Unexpected on-medium identifier.
     */
    public static final int ERROR_UNEXPECTED_OMID = 0X000010EE;

    /**
     * The last remaining item in this group or resource   cannot be deleted.
     */
    public static final int ERROR_CANT_DELETE_LAST_ITEM = 0X000010EF;

    /**
     * The message provided exceeds the maximum size allowed   for this parameter.
     */
    public static final int ERROR_MESSAGE_EXCEEDS_MAX_SIZE = 0X000010F0;

    /**
     * The volume contains system or paging files.
     */
    public static final int ERROR_VOLUME_CONTAINS_SYS_FILES = 0X000010F1;

    /**
     * The media type cannot be removed from this library   because at least one drive in the library reports it can support this media   type.
     */
    public static final int ERROR_INDIGENOUS_TYPE = 0X000010F2;

    /**
     * This offline media cannot be mounted on this system   because no enabled drives are present that can be used.
     */
    public static final int ERROR_NO_SUPPORTING_DRIVES = 0X000010F3;

    /**
     * A cleaner cartridge is present in the tape library.
     */
    public static final int ERROR_CLEANER_CARTRIDGE_INSTALLED = 0X000010F4;

    /**
     * Cannot use the IEport because it is not empty.
     */
    public static final int ERROR_IEPORT_FULL = 0X000010F5;

    /**
     * The remote storage service was not able to recall the   file.
     */
    public static final int ERROR_FILE_OFFLINE = 0X000010FE;

    /**
     * The remote storage service is not operational at this   time.
     */
    public static final int ERROR_REMOTE_STORAGE_NOT_ACTIVE = 0X000010FF;

    /**
     * The remote storage service encountered a media error.
     */
    public static final int ERROR_REMOTE_STORAGE_MEDIA_ERROR = 0X00001100;

    /**
     * The file or directory is not a reparse point.
     */
    public static final int ERROR_NOT_A_REPARSE_POINT = 0X00001126;

    /**
     * The reparse point attribute cannot be set because it   conflicts with an existing attribute.
     */
    public static final int ERROR_REPARSE_ATTRIBUTE_CONFLICT = 0X00001127;

    /**
     * The data present in the reparse point buffer is   invalid.
     */
    public static final int ERROR_INVALID_REPARSE_DATA = 0X00001128;

    /**
     * The tag present in the reparse point buffer is invalid.
     */
    public static final int ERROR_REPARSE_TAG_INVALID = 0X00001129;

    /**
     * There is a mismatch between the tag specified in the   request and the tag present in the reparse point.
     */
    public static final int ERROR_REPARSE_TAG_MISMATCH = 0X0000112A;

    /**
     * Single Instance Storage (SIS) is not available on this   volume.
     */
    public static final int ERROR_VOLUME_NOT_SIS_ENABLED = 0X00001194;

    /**
     * The operation cannot be completed because other   resources depend on this resource.
     */
    public static final int ERROR_DEPENDENT_RESOURCE_EXISTS = 0X00001389;

    /**
     * The cluster resource dependency cannot be found.
     */
    public static final int ERROR_DEPENDENCY_NOT_FOUND = 0X0000138A;

    /**
     * The cluster resource cannot be made dependent on the   specified resource because it is already dependent.
     */
    public static final int ERROR_DEPENDENCY_ALREADY_EXISTS = 0X0000138B;

    /**
     * The cluster resource is not online.
     */
    public static final int ERROR_RESOURCE_NOT_ONLINE = 0X0000138C;

    /**
     * A cluster node is not available for this operation.
     */
    public static final int ERROR_HOST_NODE_NOT_AVAILABLE = 0X0000138D;

    /**
     * The cluster resource is not available.
     */
    public static final int ERROR_RESOURCE_NOT_AVAILABLE = 0X0000138E;

    /**
     * The cluster resource could not be found.
     */
    public static final int ERROR_RESOURCE_NOT_FOUND = 0X0000138F;

    /**
     * The cluster is being shut down.
     */
    public static final int ERROR_SHUTDOWN_CLUSTER = 0X00001390;

    /**
     * A cluster node cannot be evicted from the cluster   unless the node is down or it is the last node.
     */
    public static final int ERROR_CANT_EVICT_ACTIVE_NODE = 0X00001391;

    /**
     * The object already exists.
     */
    public static final int ERROR_OBJECT_ALREADY_EXISTS = 0X00001392;

    /**
     * The object is already in the list.
     */
    public static final int ERROR_OBJECT_IN_LIST = 0X00001393;

    /**
     * The cluster group is not available for any new   requests.
     */
    public static final int ERROR_GROUP_NOT_AVAILABLE = 0X00001394;

    /**
     * The cluster group could not be found.
     */
    public static final int ERROR_GROUP_NOT_FOUND = 0X00001395;

    /**
     * The operation could not be completed because the   cluster group is not online.
     */
    public static final int ERROR_GROUP_NOT_ONLINE = 0X00001396;

    /**
     * The operation failed because either the specified   cluster node is not the owner of the resource, or the node is not a possible   owner of the resource.
     */
    public static final int ERROR_HOST_NODE_NOT_RESOURCE_OWNER = 0X00001397;

    /**
     * The operation failed because either the specified   cluster node is not the owner of the group, or the node is not a possible   owner of the group.
     */
    public static final int ERROR_HOST_NODE_NOT_GROUP_OWNER = 0X00001398;

    /**
     * The cluster resource could not be created in the   specified resource monitor.
     */
    public static final int ERROR_RESMON_CREATE_FAILED = 0X00001399;

    /**
     * The cluster resource could not be brought online by   the resource monitor.
     */
    public static final int ERROR_RESMON_ONLINE_FAILED = 0X0000139A;

    /**
     * The operation could not be completed because the   cluster resource is online.
     */
    public static final int ERROR_RESOURCE_ONLINE = 0X0000139B;

    /**
     * The cluster resource could not be deleted or brought   offline because it is the quorum resource.
     */
    public static final int ERROR_QUORUM_RESOURCE = 0X0000139C;

    /**
     * The cluster could not make the specified resource a   quorum resource because it is not capable of being a quorum resource.
     */
    public static final int ERROR_NOT_QUORUM_CAPABLE = 0X0000139D;

    /**
     * The cluster software is shutting down.
     */
    public static final int ERROR_CLUSTER_SHUTTING_DOWN = 0X0000139E;

    /**
     * The group or resource is not in the correct state to   perform the requested operation.
     */
    public static final int ERROR_INVALID_STATE = 0X0000139F;

    /**
     * The properties were stored but not all changes will   take effect until the next time the resource is brought online.
     */
    public static final int ERROR_RESOURCE_PROPERTIES_STORED = 0X000013A0;

    /**
     * The cluster could not make the specified resource a   quorum resource because it does not belong to a shared storage class.
     */
    public static final int ERROR_NOT_QUORUM_CLASS = 0X000013A1;

    /**
     * The cluster resource could not be deleted because it   is a core resource.
     */
    public static final int ERROR_CORE_RESOURCE = 0X000013A2;

    /**
     * The quorum resource failed to come online.
     */
    public static final int ERROR_QUORUM_RESOURCE_ONLINE_FAILED = 0X000013A3;

    /**
     * The quorum log could not be created or mounted   successfully.
     */
    public static final int ERROR_QUORUMLOG_OPEN_FAILED = 0X000013A4;

    /**
     * The cluster log is corrupt.
     */
    public static final int ERROR_CLUSTERLOG_CORRUPT = 0X000013A5;

    /**
     * The record could not be written to the cluster log   because it exceeds the maximum size.
     */
    public static final int ERROR_CLUSTERLOG_RECORD_EXCEEDS_MAXSIZE = 0X000013A6;

    /**
     * The cluster log exceeds its maximum size.
     */
    public static final int ERROR_CLUSTERLOG_EXCEEDS_MAXSIZE = 0X000013A7;

    /**
     * No checkpoint record was found in the cluster log.
     */
    public static final int ERROR_CLUSTERLOG_CHKPOINT_NOT_FOUND = 0X000013A8;

    /**
     * The minimum required disk space needed for logging is   not available.
     */
    public static final int ERROR_CLUSTERLOG_NOT_ENOUGH_SPACE = 0X000013A9;

    /**
     * The cluster node failed to take control of the quorum   resource because the resource is owned by another active node.
     */
    public static final int ERROR_QUORUM_OWNER_ALIVE = 0X000013AA;

    /**
     * A cluster network is not available for this operation.
     */
    public static final int ERROR_NETWORK_NOT_AVAILABLE = 0X000013AB;

    /**
     * A cluster node is not available for this operation.
     */
    public static final int ERROR_NODE_NOT_AVAILABLE = 0X000013AC;

    /**
     * All cluster nodes must be running to perform this   operation.
     */
    public static final int ERROR_ALL_NODES_NOT_AVAILABLE = 0X000013AD;

    /**
     * A cluster resource failed.
     */
    public static final int ERROR_RESOURCE_FAILED = 0X000013AE;

    /**
     * The cluster node is not valid.
     */
    public static final int ERROR_CLUSTER_INVALID_NODE = 0X000013AF;

    /**
     * The cluster node already exists.
     */
    public static final int ERROR_CLUSTER_NODE_EXISTS = 0X000013B0;

    /**
     * A node is in the process of joining the cluster.
     */
    public static final int ERROR_CLUSTER_JOIN_IN_PROGRESS = 0X000013B1;

    /**
     * The cluster node was not found.
     */
    public static final int ERROR_CLUSTER_NODE_NOT_FOUND = 0X000013B2;

    /**
     * The cluster local node information was not found.
     */
    public static final int ERROR_CLUSTER_LOCAL_NODE_NOT_FOUND = 0X000013B3;

    /**
     * The cluster network already exists.
     */
    public static final int ERROR_CLUSTER_NETWORK_EXISTS = 0X000013B4;

    /**
     * The cluster network was not found.
     */
    public static final int ERROR_CLUSTER_NETWORK_NOT_FOUND = 0X000013B5;

    /**
     * The cluster network interface already exists.
     */
    public static final int ERROR_CLUSTER_NETINTERFACE_EXISTS = 0X000013B6;

    /**
     * The cluster network interface was not found.
     */
    public static final int ERROR_CLUSTER_NETINTERFACE_NOT_FOUND = 0X000013B7;

    /**
     * The cluster request is not valid for this object.
     */
    public static final int ERROR_CLUSTER_INVALID_REQUEST = 0X000013B8;

    /**
     * The cluster network provider is not valid.
     */
    public static final int ERROR_CLUSTER_INVALID_NETWORK_PROVIDER = 0X000013B9;

    /**
     * The cluster node is down.
     */
    public static final int ERROR_CLUSTER_NODE_DOWN = 0X000013BA;

    /**
     * The cluster node is not reachable.
     */
    public static final int ERROR_CLUSTER_NODE_UNREACHABLE = 0X000013BB;

    /**
     * The cluster node is not a member of the cluster.
     */
    public static final int ERROR_CLUSTER_NODE_NOT_MEMBER = 0X000013BC;

    /**
     * A cluster join operation is not in progress.
     */
    public static final int ERROR_CLUSTER_JOIN_NOT_IN_PROGRESS = 0X000013BD;

    /**
     * The cluster network is not valid.
     */
    public static final int ERROR_CLUSTER_INVALID_NETWORK = 0X000013BE;

    /**
     * The cluster node is up.
     */
    public static final int ERROR_CLUSTER_NODE_UP = 0X000013C0;

    /**
     * The cluster IP address is already in use.
     */
    public static final int ERROR_CLUSTER_IPADDR_IN_USE = 0X000013C1;

    /**
     * The cluster node is not paused.
     */
    public static final int ERROR_CLUSTER_NODE_NOT_PAUSED = 0X000013C2;

    /**
     * No cluster security context is available.
     */
    public static final int ERROR_CLUSTER_NO_SECURITY_CONTEXT = 0X000013C3;

    /**
     * The cluster network is not configured for internal   cluster communication.
     */
    public static final int ERROR_CLUSTER_NETWORK_NOT_INTERNAL = 0X000013C4;

    /**
     * The cluster node is already up.
     */
    public static final int ERROR_CLUSTER_NODE_ALREADY_UP = 0X000013C5;

    /**
     * The cluster node is already down.
     */
    public static final int ERROR_CLUSTER_NODE_ALREADY_DOWN = 0X000013C6;

    /**
     * The cluster network is already online.
     */
    public static final int ERROR_CLUSTER_NETWORK_ALREADY_ONLINE = 0X000013C7;

    /**
     * The cluster network is already offline.
     */
    public static final int ERROR_CLUSTER_NETWORK_ALREADY_OFFLINE = 0X000013C8;

    /**
     * The cluster node is already a member of the cluster.
     */
    public static final int ERROR_CLUSTER_NODE_ALREADY_MEMBER = 0X000013C9;

    /**
     * The cluster network is the only one configured for   internal cluster communication between two or more active cluster nodes. The   internal communication capability cannot be removed from the network.
     */
    public static final int ERROR_CLUSTER_LAST_INTERNAL_NETWORK = 0X000013CA;

    /**
     * One or more cluster resources depend on the network to   provide service to clients. The client access capability cannot be removed   from the network.
     */
    public static final int ERROR_CLUSTER_NETWORK_HAS_DEPENDENTS = 0X000013CB;

    /**
     * This operation cannot be performed on the cluster   resource because it is the quorum resource. This quorum resource cannot be   brought offline and its possible owners list cannot be modified.
     */
    public static final int ERROR_INVALID_OPERATION_ON_QUORUM = 0X000013CC;

    /**
     * The cluster quorum resource is not allowed to have any   dependencies.
     */
    public static final int ERROR_DEPENDENCY_NOT_ALLOWED = 0X000013CD;

    /**
     * The cluster node is paused.
     */
    public static final int ERROR_CLUSTER_NODE_PAUSED = 0X000013CE;

    /**
     * The cluster resource cannot be brought online. The   owner node cannot run this resource.
     */
    public static final int ERROR_NODE_CANT_HOST_RESOURCE = 0X000013CF;

    /**
     * The cluster node is not ready to perform the requested   operation.
     */
    public static final int ERROR_CLUSTER_NODE_NOT_READY = 0X000013D0;

    /**
     * The cluster node is shutting down.
     */
    public static final int ERROR_CLUSTER_NODE_SHUTTING_DOWN = 0X000013D1;

    /**
     * The cluster join operation was aborted.
     */
    public static final int ERROR_CLUSTER_JOIN_ABORTED = 0X000013D2;

    /**
     * The cluster join operation failed due to incompatible   software versions between the joining node and its sponsor.
     */
    public static final int ERROR_CLUSTER_INCOMPATIBLE_VERSIONS = 0X000013D3;

    /**
     * This resource cannot be created because the cluster   has reached the limit on the number of resources it can monitor.
     */
    public static final int ERROR_CLUSTER_MAXNUM_OF_RESOURCES_EXCEEDED = 0X000013D4;

    /**
     * The system configuration changed during the cluster   join or form operation. The join or form operation was aborted.
     */
    public static final int ERROR_CLUSTER_SYSTEM_CONFIG_CHANGED = 0X000013D5;

    /**
     * The specified resource type was not found.
     */
    public static final int ERROR_CLUSTER_RESOURCE_TYPE_NOT_FOUND = 0X000013D6;

    /**
     * The specified node does not support a resource of this   type. This might be due to version inconsistencies or due to the absence of   the resource DLL on this node.
     */
    public static final int ERROR_CLUSTER_RESTYPE_NOT_SUPPORTED = 0X000013D7;

    /**
     * The specified resource name is not supported by this   resource DLL. This might be due to a bad (or changed) name supplied to the   resource DLL.
     */
    public static final int ERROR_CLUSTER_RESNAME_NOT_FOUND = 0X000013D8;

    /**
     * No authentication package could be registered with the   RPC server.
     */
    public static final int ERROR_CLUSTER_NO_RPC_PACKAGES_REGISTERED = 0X000013D9;

    /**
     * You cannot bring the group online because the owner of   the group is not in the preferred list for the group. To change the owner node   for the group, move the group.
     */
    public static final int ERROR_CLUSTER_OWNER_NOT_IN_PREFLIST = 0X000013DA;

    /**
     * The join operation failed because the cluster database   sequence number has changed or is incompatible with the locker node. This can   happen during a join operation if the cluster database was changing during
     * the join.
     */
    public static final int ERROR_CLUSTER_DATABASE_SEQMISMATCH = 0X000013DB;

    /**
     * The resource monitor will not allow the fail operation   to be performed while the resource is in its current state. This can happen   if the resource is in a pending state.
     */
    public static final int ERROR_RESMON_INVALID_STATE = 0X000013DC;

    /**
     * A non-locker code received a request to reserve the   lock for making global updates.
     */
    public static final int ERROR_CLUSTER_GUM_NOT_LOCKER = 0X000013DD;

    /**
     * The quorum disk could not be located by the cluster   service.
     */
    public static final int ERROR_QUORUM_DISK_NOT_FOUND = 0X000013DE;

    /**
     * The backed-up cluster database is possibly corrupt.
     */
    public static final int ERROR_DATABASE_BACKUP_CORRUPT = 0X000013DF;

    /**
     * A DFS root already exists in this cluster node.
     */
    public static final int ERROR_CLUSTER_NODE_ALREADY_HAS_DFS_ROOT = 0X000013E0;

    /**
     * An attempt to modify a resource property failed   because it conflicts with another existing property.
     */
    public static final int ERROR_RESOURCE_PROPERTY_UNCHANGEABLE = 0X000013E1;

    /**
     * An operation was attempted that is incompatible with   the current membership state of the node.
     */
    public static final int ERROR_CLUSTER_MEMBERSHIP_INVALID_STATE = 0X00001702;

    /**
     * The quorum resource does not contain the quorum log.
     */
    public static final int ERROR_CLUSTER_QUORUMLOG_NOT_FOUND = 0X00001703;

    /**
     * The membership engine requested shutdown of the   cluster service on this node.
     */
    public static final int ERROR_CLUSTER_MEMBERSHIP_HALT = 0X00001704;

    /**
     * The join operation failed because the cluster instance   ID of the joining node does not match the cluster instance ID of the sponsor   node.
     */
    public static final int ERROR_CLUSTER_INSTANCE_ID_MISMATCH = 0X00001705;

    /**
     * A matching cluster network for the specified IP   address could not be found.
     */
    public static final int ERROR_CLUSTER_NETWORK_NOT_FOUND_FOR_IP = 0X00001706;

    /**
     * The actual data type of the property did not match the   expected data type of the property.
     */
    public static final int ERROR_CLUSTER_PROPERTY_DATA_TYPE_MISMATCH = 0X00001707;

    /**
     * The cluster node was evicted from the cluster   successfully, but the node was not cleaned up. To determine what clean-up   steps failed and how to recover, see the Failover Clustering application   event log
     * using Event Viewer.
     */
    public static final int ERROR_CLUSTER_EVICT_WITHOUT_CLEANUP = 0X00001708;

    /**
     * Two or more parameter values specified for a   resource\'s properties are in conflict.
     */
    public static final int ERROR_CLUSTER_PARAMETER_MISMATCH = 0X00001709;

    /**
     * This computer cannot be made a member of a cluster.
     */
    public static final int ERROR_NODE_CANNOT_BE_CLUSTERED = 0X0000170A;

    /**
     * This computer cannot be made a member of a cluster   because it does not have the correct version of Windows installed.
     */
    public static final int ERROR_CLUSTER_WRONG_OS_VERSION = 0X0000170B;

    /**
     * A cluster cannot be created with the specified cluster   name because that cluster name is already in use. Specify a different name   for the cluster.
     */
    public static final int ERROR_CLUSTER_CANT_CREATE_DUP_CLUSTER_NAME = 0X0000170C;

    /**
     * The cluster configuration action has already been   committed.
     */
    public static final int ERROR_CLUSCFG_ALREADY_COMMITTED = 0X0000170D;

    /**
     * The cluster configuration action could not be rolled   back.
     */
    public static final int ERROR_CLUSCFG_ROLLBACK_FAILED = 0X0000170E;

    /**
     * The drive letter assigned to a system disk on one node   conflicted with the drive letter assigned to a disk on another node.
     */
    public static final int ERROR_CLUSCFG_SYSTEM_DISK_DRIVE_LETTER_CONFLICT = 0X0000170F;

    /**
     * One or more nodes in the cluster are running a version   of Windows that does not support this operation.
     */
    public static final int ERROR_CLUSTER_OLD_VERSION = 0X00001710;

    /**
     * The name of the corresponding computer account does   not match the network name for this resource.
     */
    public static final int ERROR_CLUSTER_MISMATCHED_COMPUTER_ACCT_NAME = 0X00001711;

    /**
     * No network adapters are available.
     */
    public static final int ERROR_CLUSTER_NO_NET_ADAPTERS = 0X00001712;

    /**
     * The cluster node has been poisoned.
     */
    public static final int ERROR_CLUSTER_POISONED = 0X00001713;

    /**
     * The group is unable to accept the request because it   is moving to another node.
     */
    public static final int ERROR_CLUSTER_GROUP_MOVING = 0X00001714;

    /**
     * The resource type cannot accept the request because it   is too busy performing another operation.
     */
    public static final int ERROR_CLUSTER_RESOURCE_TYPE_BUSY = 0X00001715;

    /**
     * The call to the cluster resource DLL timed out.
     */
    public static final int ERROR_RESOURCE_CALL_TIMED_OUT = 0X00001716;

    /**
     * The address is not valid for an IPv6 Address resource.   A global IPv6 address is required, and it must match a cluster network.   Compatibility addresses are not permitted.
     */
    public static final int ERROR_INVALID_CLUSTER_IPV6_ADDRESS = 0X00001717;

    /**
     * An internal cluster error occurred. A call to an   invalid function was attempted.
     */
    public static final int ERROR_CLUSTER_INTERNAL_INVALID_FUNCTION = 0X00001718;

    /**
     * A parameter value is out of acceptable range.
     */
    public static final int ERROR_CLUSTER_PARAMETER_OUT_OF_BOUNDS = 0X00001719;

    /**
     * A network error occurred while sending data to another   node in the cluster. The number of bytes transmitted was less than required.
     */
    public static final int ERROR_CLUSTER_PARTIAL_SEND = 0X0000171A;

    /**
     * An invalid cluster registry operation was attempted.
     */
    public static final int ERROR_CLUSTER_REGISTRY_INVALID_FUNCTION = 0X0000171B;

    /**
     * An input string of characters is not properly   terminated.
     */
    public static final int ERROR_CLUSTER_INVALID_STRING_TERMINATION = 0X0000171C;

    /**
     * An input string of characters is not in a valid format   for the data it represents.
     */
    public static final int ERROR_CLUSTER_INVALID_STRING_FORMAT = 0X0000171D;

    /**
     * An internal cluster error occurred. A cluster database   transaction was attempted while a transaction was already in progress.
     */
    public static final int ERROR_CLUSTER_DATABASE_TRANSACTION_IN_PROGRESS = 0X0000171E;

    /**
     * An internal cluster error occurred. There was an   attempt to commit a cluster database transaction while no transaction was in   progress.
     */
    public static final int ERROR_CLUSTER_DATABASE_TRANSACTION_NOT_IN_PROGRESS = 0X0000171F;

    /**
     * An internal cluster error occurred. Data was not   properly initialized.
     */
    public static final int ERROR_CLUSTER_NULL_DATA = 0X00001720;

    /**
     * An error occurred while reading from a stream of data.   An unexpected number of bytes was returned.
     */
    public static final int ERROR_CLUSTER_PARTIAL_READ = 0X00001721;

    /**
     * An error occurred while writing to a stream of data.   The required number of bytes could not be written.
     */
    public static final int ERROR_CLUSTER_PARTIAL_WRITE = 0X00001722;

    /**
     * An error occurred while deserializing a stream of   cluster data.
     */
    public static final int ERROR_CLUSTER_CANT_DESERIALIZE_DATA = 0X00001723;

    /**
     * One or more property values for this resource are in   conflict with one or more property values associated with its dependent   resources.
     */
    public static final int ERROR_DEPENDENT_RESOURCE_PROPERTY_CONFLICT = 0X00001724;

    /**
     * A quorum of cluster nodes was not present to form a   cluster.
     */
    public static final int ERROR_CLUSTER_NO_QUORUM = 0X00001725;

    /**
     * The cluster network is not valid for an IPv6 address   resource, or it does not match the configured address.
     */
    public static final int ERROR_CLUSTER_INVALID_IPV6_NETWORK = 0X00001726;

    /**
     * The cluster network is not valid for an IPv6 tunnel   resource. Check the configuration of the IP Address resource on which the   IPv6 tunnel resource depends.
     */
    public static final int ERROR_CLUSTER_INVALID_IPV6_TUNNEL_NETWORK = 0X00001727;

    /**
     * Quorum resource cannot reside in the available storage   group.
     */
    public static final int ERROR_QUORUM_NOT_ALLOWED_IN_THIS_GROUP = 0X00001728;

    /**
     * The specified file could not be encrypted.
     */
    public static final int ERROR_ENCRYPTION_FAILED = 0X00001770;

    /**
     * The specified file could not be decrypted.
     */
    public static final int ERROR_DECRYPTION_FAILED = 0X00001771;

    /**
     * The specified file is encrypted and the user does not   have the ability to decrypt it.
     */
    public static final int ERROR_FILE_ENCRYPTED = 0X00001772;

    /**
     * There is no valid encryption recovery policy   configured for this system.
     */
    public static final int ERROR_NO_RECOVERY_POLICY = 0X00001773;

    /**
     * The required encryption driver is not loaded for this   system.
     */
    public static final int ERROR_NO_EFS = 0X00001774;

    /**
     * The file was encrypted with a different encryption   driver than is currently loaded.
     */
    public static final int ERROR_WRONG_EFS = 0X00001775;

    /**
     * There are no Encrypting File System (EFS) keys defined   for the user.
     */
    public static final int ERROR_NO_USER_KEYS = 0X00001776;

    /**
     * The specified file is not encrypted.
     */
    public static final int ERROR_FILE_NOT_ENCRYPTED = 0X00001777;

    /**
     * The specified file is not in the defined EFS export   format.
     */
    public static final int ERROR_NOT_EXPORT_FORMAT = 0X00001778;

    /**
     * The specified file is read-only.
     */
    public static final int ERROR_FILE_READ_ONLY = 0X00001779;

    /**
     * The directory has been disabled for encryption.
     */
    public static final int ERROR_DIR_EFS_DISALLOWED = 0X0000177A;

    /**
     * The server is not trusted for remote encryption   operation.
     */
    public static final int ERROR_EFS_SERVER_NOT_TRUSTED = 0X0000177B;

    /**
     * Recovery policy configured for this system contains   invalid recovery certificate.
     */
    public static final int ERROR_BAD_RECOVERY_POLICY = 0X0000177C;

    /**
     * The encryption algorithm used on the source file needs   a bigger key buffer than the one on the destination file.
     */
    public static final int ERROR_EFS_ALG_BLOB_TOO_BIG = 0X0000177D;

    /**
     * The disk partition does not support file encryption.
     */
    public static final int ERROR_VOLUME_NOT_SUPPORT_EFS = 0X0000177E;

    /**
     * This machine is disabled for file encryption.
     */
    public static final int ERROR_EFS_DISABLED = 0X0000177F;

    /**
     * A newer system is required to decrypt this encrypted   file.
     */
    public static final int ERROR_EFS_VERSION_NOT_SUPPORT = 0X00001780;

    /**
     * The remote server sent an invalid response for a file   being opened with client-side encryption.
     */
    public static final int ERROR_CS_ENCRYPTION_INVALID_SERVER_RESPONSE = 0X00001781;

    /**
     * Client-side encryption is not supported by the remote   server even though it claims to support it.
     */
    public static final int ERROR_CS_ENCRYPTION_UNSUPPORTED_SERVER = 0X00001782;

    /**
     * File is encrypted and should be opened in client-side   encryption mode.
     */
    public static final int ERROR_CS_ENCRYPTION_EXISTING_ENCRYPTED_FILE = 0X00001783;

    /**
     * A new encrypted file is being created and a $EFS needs   to be provided.
     */
    public static final int ERROR_CS_ENCRYPTION_NEW_ENCRYPTED_FILE = 0X00001784;

    /**
     * The SMB client requested a client-side extension (CSE)   file system control (FSCTL) on a non-CSE file.
     */
    public static final int ERROR_CS_ENCRYPTION_FILE_NOT_CSE = 0X00001785;

    /**
     * The list of servers for this workgroup is not   currently available
     */
    public static final int ERROR_NO_BROWSER_SERVERS_FOUND = 0X000017E6;

    /**
     * The Task Scheduler service must be configured to run   in the System account to function properly. Individual tasks can be   configured to run in other accounts.
     */
    public static final int SCHED_E_SERVICE_NOT_LOCALSYSTEM = 0X00001838;

    /**
     * The log service encountered an invalid log sector.
     */
    public static final int ERROR_LOG_SECTOR_INVALID = 0X000019C8;

    /**
     * The log service encountered a log sector with invalid   block parity.
     */
    public static final int ERROR_LOG_SECTOR_PARITY_INVALID = 0X000019C9;

    /**
     * The log service encountered a remapped log sector.
     */
    public static final int ERROR_LOG_SECTOR_REMAPPED = 0X000019CA;

    /**
     * The log service encountered a partial or incomplete   log block.
     */
    public static final int ERROR_LOG_BLOCK_INCOMPLETE = 0X000019CB;

    /**
     * The log service encountered an attempt to access data   outside the active log range.
     */
    public static final int ERROR_LOG_INVALID_RANGE = 0X000019CC;

    /**
     * The log service user marshaling buffers are exhausted.
     */
    public static final int ERROR_LOG_BLOCKS_EXHAUSTED = 0X000019CD;

    /**
     * The log service encountered an attempt to read from a   marshaling area with an invalid read context.
     */
    public static final int ERROR_LOG_READ_CONTEXT_INVALID = 0X000019CE;

    /**
     * The log service encountered an invalid log restart   area.
     */
    public static final int ERROR_LOG_RESTART_INVALID = 0X000019CF;

    /**
     * The log service encountered an invalid log block   version.
     */
    public static final int ERROR_LOG_BLOCK_VERSION = 0X000019D0;

    /**
     * The log service encountered an invalid log block.
     */
    public static final int ERROR_LOG_BLOCK_INVALID = 0X000019D1;

    /**
     * The log service encountered an attempt to read the log   with an invalid read mode.
     */
    public static final int ERROR_LOG_READ_MODE_INVALID = 0X000019D2;

    /**
     * The log service encountered a log stream with no   restart area.
     */
    public static final int ERROR_LOG_NO_RESTART = 0X000019D3;

    /**
     * The log service encountered a corrupted metadata file.
     */
    public static final int ERROR_LOG_METADATA_CORRUPT = 0X000019D4;

    /**
     * The log service encountered a metadata file that could   not be created by the log file system.
     */
    public static final int ERROR_LOG_METADATA_INVALID = 0X000019D5;

    /**
     * The log service encountered a metadata file with   inconsistent data.
     */
    public static final int ERROR_LOG_METADATA_INCONSISTENT = 0X000019D6;

    /**
     * The log service encountered an attempt to erroneous   allocate or dispose reservation space.
     */
    public static final int ERROR_LOG_RESERVATION_INVALID = 0X000019D7;

    /**
     * The log service cannot delete a log file or file   system container.
     */
    public static final int ERROR_LOG_CANT_DELETE = 0X000019D8;

    /**
     * The log service has reached the maximum allowable   containers allocated to a log file.
     */
    public static final int ERROR_LOG_CONTAINER_LIMIT_EXCEEDED = 0X000019D9;

    /**
     * The log service has attempted to read or write   backward past the start of the log.
     */
    public static final int ERROR_LOG_START_OF_LOG = 0X000019DA;

    /**
     * The log policy could not be installed because a policy   of the same type is already present.
     */
    public static final int ERROR_LOG_POLICY_ALREADY_INSTALLED = 0X000019DB;

    /**
     * The log policy in question was not installed at the   time of the request.
     */
    public static final int ERROR_LOG_POLICY_NOT_INSTALLED = 0X000019DC;

    /**
     * The installed set of policies on the log is invalid.
     */
    public static final int ERROR_LOG_POLICY_INVALID = 0X000019DD;

    /**
     * A policy on the log in question prevented the   operation from completing.
     */
    public static final int ERROR_LOG_POLICY_CONFLICT = 0X000019DE;

    /**
     * Log space cannot be reclaimed because the log is   pinned by the archive tail.
     */
    public static final int ERROR_LOG_PINNED_ARCHIVE_TAIL = 0X000019DF;

    /**
     * The log record is not a record in the log file.
     */
    public static final int ERROR_LOG_RECORD_NONEXISTENT = 0X000019E0;

    /**
     * The number of reserved log records or the adjustment   of the number of reserved log records is invalid.
     */
    public static final int ERROR_LOG_RECORDS_RESERVED_INVALID = 0X000019E1;

    /**
     * The reserved log space or the adjustment of the log   space is invalid.
     */
    public static final int ERROR_LOG_SPACE_RESERVED_INVALID = 0X000019E2;

    /**
     * A new or existing archive tail or base of the active   log is invalid.
     */
    public static final int ERROR_LOG_TAIL_INVALID = 0X000019E3;

    /**
     * The log space is exhausted.
     */
    public static final int ERROR_LOG_FULL = 0X000019E4;

    /**
     * The log could not be set to the requested size.
     */
    public static final int ERROR_COULD_NOT_RESIZE_LOG = 0X000019E5;

    /**
     * The log is multiplexed; no direct writes to the   physical log are allowed.
     */
    public static final int ERROR_LOG_MULTIPLEXED = 0X000019E6;

    /**
     * The operation failed because the log is a dedicated   log.
     */
    public static final int ERROR_LOG_DEDICATED = 0X000019E7;

    /**
     * The operation requires an archive context.
     */
    public static final int ERROR_LOG_ARCHIVE_NOT_IN_PROGRESS = 0X000019E8;

    /**
     * Log archival is in progress.
     */
    public static final int ERROR_LOG_ARCHIVE_IN_PROGRESS = 0X000019E9;

    /**
     * The operation requires a non-ephemeral log, but the   log is ephemeral.
     */
    public static final int ERROR_LOG_EPHEMERAL = 0X000019EA;

    /**
     * The log must have at least two containers before it   can be read from or written to.
     */
    public static final int ERROR_LOG_NOT_ENOUGH_CONTAINERS = 0X000019EB;

    /**
     * A log client has already registered on the stream.
     */
    public static final int ERROR_LOG_CLIENT_ALREADY_REGISTERED = 0X000019EC;

    /**
     * A log client has not been registered on the stream.
     */
    public static final int ERROR_LOG_CLIENT_NOT_REGISTERED = 0X000019ED;

    /**
     * A request has already been made to handle the log full   condition.
     */
    public static final int ERROR_LOG_FULL_HANDLER_IN_PROGRESS = 0X000019EE;

    /**
     * The log service encountered an error when attempting   to read from a log container.
     */
    public static final int ERROR_LOG_CONTAINER_READ_FAILED = 0X000019EF;

    /**
     * The log service encountered an error when attempting   to write to a log container.
     */
    public static final int ERROR_LOG_CONTAINER_WRITE_FAILED = 0X000019F0;

    /**
     * The log service encountered an error when attempting   to open a log container.
     */
    public static final int ERROR_LOG_CONTAINER_OPEN_FAILED = 0X000019F1;

    /**
     * The log service encountered an invalid container state   when attempting a requested action.
     */
    public static final int ERROR_LOG_CONTAINER_STATE_INVALID = 0X000019F2;

    /**
     * The log service is not in the correct state to perform   a requested action.
     */
    public static final int ERROR_LOG_STATE_INVALID = 0X000019F3;

    /**
     * The log space cannot be reclaimed because the log is   pinned.
     */
    public static final int ERROR_LOG_PINNED = 0X000019F4;

    /**
     * The log metadata flush failed.
     */
    public static final int ERROR_LOG_METADATA_FLUSH_FAILED = 0X000019F5;

    /**
     * Security on the log and its containers is   inconsistent.
     */
    public static final int ERROR_LOG_INCONSISTENT_SECURITY = 0X000019F6;

    /**
     * Records were appended to the log or reservation   changes were made, but the log could not be flushed.
     */
    public static final int ERROR_LOG_APPENDED_FLUSH_FAILED = 0X000019F7;

    /**
     * The log is pinned due to reservation consuming most of   the log space. Free some reserved records to make space available.
     */
    public static final int ERROR_LOG_PINNED_RESERVATION = 0X000019F8;

    /**
     * The transaction handle associated with this operation   is not valid.
     */
    public static final int ERROR_INVALID_TRANSACTION = 0X00001A2C;

    /**
     * The requested operation was made in the context of a   transaction that is no longer active.
     */
    public static final int ERROR_TRANSACTION_NOT_ACTIVE = 0X00001A2D;

    /**
     * The requested operation is not valid on the   transaction object in its current state.
     */
    public static final int ERROR_TRANSACTION_REQUEST_NOT_VALID = 0X00001A2E;

    /**
     * The caller has called a response API, but the response   is not expected because the transaction manager did not issue the   corresponding request to the caller.
     */
    public static final int ERROR_TRANSACTION_NOT_REQUESTED = 0X00001A2F;

    /**
     * It is too late to perform the requested operation   because the transaction has already been aborted.
     */
    public static final int ERROR_TRANSACTION_ALREADY_ABORTED = 0X00001A30;

    /**
     * It is too late to perform the requested operation   because the transaction has already been committed.
     */
    public static final int ERROR_TRANSACTION_ALREADY_COMMITTED = 0X00001A31;

    /**
     * The transaction manager was unable to be successfully   initialized. Transacted operations are not supported.
     */
    public static final int ERROR_TM_INITIALIZATION_FAILED = 0X00001A32;

    /**
     * The specified resource manager made no changes or   updates to the resource under this transaction.
     */
    public static final int ERROR_RESOURCEMANAGER_READ_ONLY = 0X00001A33;

    /**
     * The resource manager has attempted to prepare a   transaction that it has not successfully joined.
     */
    public static final int ERROR_TRANSACTION_NOT_JOINED = 0X00001A34;

    /**
     * The transaction object already has a superior   enlistment, and the caller attempted an operation that would have created a   new superior. Only a single superior enlistment is allowed.
     */
    public static final int ERROR_TRANSACTION_SUPERIOR_EXISTS = 0X00001A35;

    /**
     * The resource manager tried to register a protocol that   already exists.
     */
    public static final int ERROR_CRM_PROTOCOL_ALREADY_EXISTS = 0X00001A36;

    /**
     * The attempt to propagate the transaction failed.
     */
    public static final int ERROR_TRANSACTION_PROPAGATION_FAILED = 0X00001A37;

    /**
     * The requested propagation protocol was not registered   as a CRM.
     */
    public static final int ERROR_CRM_PROTOCOL_NOT_FOUND = 0X00001A38;

    /**
     * The buffer passed in to PushTransaction or   PullTransaction is not in a valid format.
     */
    public static final int ERROR_TRANSACTION_INVALID_MARSHALL_BUFFER = 0X00001A39;

    /**
     * The current transaction context associated with the   thread is not a valid handle to a transaction object.
     */
    public static final int ERROR_CURRENT_TRANSACTION_NOT_VALID = 0X00001A3A;

    /**
     * The specified transaction object could not be opened   because it was not found.
     */
    public static final int ERROR_TRANSACTION_NOT_FOUND = 0X00001A3B;

    /**
     * The specified resource manager object could not be   opened because it was not found.
     */
    public static final int ERROR_RESOURCEMANAGER_NOT_FOUND = 0X00001A3C;

    /**
     * The specified enlistment object could not be opened   because it was not found.
     */
    public static final int ERROR_ENLISTMENT_NOT_FOUND = 0X00001A3D;

    /**
     * The specified transaction manager object could not be   opened because it was not found.
     */
    public static final int ERROR_TRANSACTIONMANAGER_NOT_FOUND = 0X00001A3E;

    /**
     * The specified resource manager was unable to create an   enlistment because its associated transaction manager is not online.
     */
    public static final int ERROR_TRANSACTIONMANAGER_NOT_ONLINE = 0X00001A3F;

    /**
     * The specified transaction manager was unable to create   the objects contained in its log file in the ObjectB namespace. Therefore,   the transaction manager was unable to recover.
     */
    public static final int ERROR_TRANSACTIONMANAGER_RECOVERY_NAME_COLLISION = 0X00001A40;

    /**
     * The function attempted to use a name that is reserved   for use by another transaction.
     */
    public static final int ERROR_TRANSACTIONAL_CONFLICT = 0X00001A90;

    /**
     * Transaction support within the specified file system   resource manager is not started or was shut down due to an error.
     */
    public static final int ERROR_RM_NOT_ACTIVE = 0X00001A91;

    /**
     * The metadata of the resource manager has been   corrupted. The resource manager will not function.
     */
    public static final int ERROR_RM_METADATA_CORRUPT = 0X00001A92;

    /**
     * The specified directory does not contain a resource   manager.
     */
    public static final int ERROR_DIRECTORY_NOT_RM = 0X00001A93;

    /**
     * The remote server or share does not support transacted   file operations.
     */
    public static final int ERROR_TRANSACTIONS_UNSUPPORTED_REMOTE = 0X00001A95;

    /**
     * The requested log size is invalid.
     */
    public static final int ERROR_LOG_RESIZE_INVALID_SIZE = 0X00001A96;

    /**
     * The object (file, stream, link) corresponding to the   handle has been deleted by a transaction savepoint rollback.
     */
    public static final int ERROR_OBJECT_NO_LONGER_EXISTS = 0X00001A97;

    /**
     * The specified file miniversion was not found for this   transacted file open.
     */
    public static final int ERROR_STREAM_MINIVERSION_NOT_FOUND = 0X00001A98;

    /**
     * The specified file miniversion was found but has been   invalidated. The most likely cause is a transaction savepoint rollback.
     */
    public static final int ERROR_STREAM_MINIVERSION_NOT_VALID = 0X00001A99;

    /**
     * A miniversion can only be opened in the context of the   transaction that created it.
     */
    public static final int ERROR_MINIVERSION_INACCESSIBLE_FROM_SPECIFIED_TRANSACTION = 0X00001A9A;

    /**
     * It is not possible to open a miniversion with modify   access.
     */
    public static final int ERROR_CANT_OPEN_MINIVERSION_WITH_MODIFY_INTENT = 0X00001A9B;

    /**
     * It is not possible to create any more miniversions for   this stream.
     */
    public static final int ERROR_CANT_CREATE_MORE_STREAM_MINIVERSIONS = 0X00001A9C;

    /**
     * The remote server sent mismatching version numbers or   FID for a file opened with transactions.
     */
    public static final int ERROR_REMOTE_FILE_VERSION_MISMATCH = 0X00001A9E;

    /**
     * The handle has been invalidated by a transaction. The   most likely cause is the presence of memory mapping on a file, or an open   handle when the transaction ended or rolled back to savepoint.
     */
    public static final int ERROR_HANDLE_NO_LONGER_VALID = 0X00001A9F;

    /**
     * There is no transaction metadata on the file.
     */
    public static final int ERROR_NO_TXF_METADATA = 0X00001AA0;

    /**
     * The log data is corrupt.
     */
    public static final int ERROR_LOG_CORRUPTION_DETECTED = 0X00001AA1;

    /**
     * The file cannot be recovered because a handle is still   open on it.
     */
    public static final int ERROR_CANT_RECOVER_WITH_HANDLE_OPEN = 0X00001AA2;

    /**
     * The transaction outcome is unavailable because the   resource manager responsible for it is disconnected.
     */
    public static final int ERROR_RM_DISCONNECTED = 0X00001AA3;

    /**
     * The request was rejected because the enlistment in   question is not a superior enlistment.
     */
    public static final int ERROR_ENLISTMENT_NOT_SUPERIOR = 0X00001AA4;

    /**
     * The transactional resource manager is already   consistent. Recovery is not needed.
     */
    public static final int ERROR_RECOVERY_NOT_NEEDED = 0X00001AA5;

    /**
     * The transactional resource manager has already been   started.
     */
    public static final int ERROR_RM_ALREADY_STARTED = 0X00001AA6;

    /**
     * The file cannot be opened in a transaction because its   identity depends on the outcome of an unresolved transaction.
     */
    public static final int ERROR_FILE_IDENTITY_NOT_PERSISTENT = 0X00001AA7;

    /**
     * The operation cannot be performed because another   transaction is depending on the fact that this property will not change.
     */
    public static final int ERROR_CANT_BREAK_TRANSACTIONAL_DEPENDENCY = 0X00001AA8;

    /**
     * The operation would involve a single file with two   transactional resource managers and is therefore not allowed.
     */
    public static final int ERROR_CANT_CROSS_RM_BOUNDARY = 0X00001AA9;

    /**
     * The $Txf directory must be empty for this operation to   succeed.
     */
    public static final int ERROR_TXF_DIR_NOT_EMPTY = 0X00001AAA;

    /**
     * The operation would leave a transactional resource   manager in an inconsistent state and is, therefore, not allowed.
     */
    public static final int ERROR_INDOUBT_TRANSACTIONS_EXIST = 0X00001AAB;

    /**
     * The operation could not be completed because the   transaction manager does not have a log.
     */
    public static final int ERROR_TM_VOLATILE = 0X00001AAC;

    /**
     * A rollback could not be scheduled because a previously   scheduled rollback has already been executed or is queued for execution.
     */
    public static final int ERROR_ROLLBACK_TIMER_EXPIRED = 0X00001AAD;

    /**
     * The transactional metadata attribute on the file or   directory is corrupt and unreadable.
     */
    public static final int ERROR_TXF_ATTRIBUTE_CORRUPT = 0X00001AAE;

    /**
     * The encryption operation could not be completed   because a transaction is active.
     */
    public static final int ERROR_EFS_NOT_ALLOWED_IN_TRANSACTION = 0X00001AAF;

    /**
     * This object is not allowed to be opened in a   transaction.
     */
    public static final int ERROR_TRANSACTIONAL_OPEN_NOT_ALLOWED = 0X00001AB0;

    /**
     * An attempt to create space in the transactional   resource manager\'s log failed. The failure status has been recorded in the event   log.
     */
    public static final int ERROR_LOG_GROWTH_FAILED = 0X00001AB1;

    /**
     * Memory mapping (creating a mapped section) to a remote   file under a transaction is not supported.
     */
    public static final int ERROR_TRANSACTED_MAPPING_UNSUPPORTED_REMOTE = 0X00001AB2;

    /**
     * Transaction metadata is already present on this file   and cannot be superseded.
     */
    public static final int ERROR_TXF_METADATA_ALREADY_PRESENT = 0X00001AB3;

    /**
     * A transaction scope could not be entered because the   scope handler has not been initialized.
     */
    public static final int ERROR_TRANSACTION_SCOPE_CALLBACKS_NOT_SET = 0X00001AB4;

    /**
     * Promotion was required to allow the resource manager   to enlist, but the transaction was set to disallow it.
     */
    public static final int ERROR_TRANSACTION_REQUIRED_PROMOTION = 0X00001AB5;

    /**
     * This file is open for modification in an unresolved   transaction and can be opened for execution only by a transacted reader.
     */
    public static final int ERROR_CANNOT_EXECUTE_FILE_IN_TRANSACTION = 0X00001AB6;

    /**
     * The request to thaw frozen transactions was ignored   because transactions were not previously frozen.
     */
    public static final int ERROR_TRANSACTIONS_NOT_FROZEN = 0X00001AB7;

    /**
     * Transactions cannot be frozen because a freeze is   already in progress.
     */
    public static final int ERROR_TRANSACTION_FREEZE_IN_PROGRESS = 0X00001AB8;

    /**
     * The target volume is not a snapshot volume. This   operation is only valid on a volume mounted as a snapshot.
     */
    public static final int ERROR_NOT_SNAPSHOT_VOLUME = 0X00001AB9;

    /**
     * The savepoint operation failed because files are open   on the transaction. This is not permitted.
     */
    public static final int ERROR_NO_SAVEPOINT_WITH_OPEN_FILES = 0X00001ABA;

    /**
     * Windows has discovered corruption in a file, and that   file has since been repaired. Data loss might have occurred.
     */
    public static final int ERROR_DATA_LOST_REPAIR = 0X00001ABB;

    /**
     * The sparse operation could not be completed because a   transaction is active on the file.
     */
    public static final int ERROR_SPARSE_NOT_ALLOWED_IN_TRANSACTION = 0X00001ABC;

    /**
     * The call to create a transaction manager object failed   because the Tm Identity stored in the logfile does not match the Tm Identity that   was passed in as an argument.
     */
    public static final int ERROR_TM_IDENTITY_MISMATCH = 0X00001ABD;

    /**
     * I/O was attempted on a section object that has been   floated as a result of a transaction ending. There is no valid data.
     */
    public static final int ERROR_FLOATED_SECTION = 0X00001ABE;

    /**
     * The transactional resource manager cannot currently   accept transacted work due to a transient condition, such as low resources.
     */
    public static final int ERROR_CANNOT_ACCEPT_TRANSACTED_WORK = 0X00001ABF;

    /**
     * The transactional resource manager had too many   transactions outstanding that could not be aborted. The transactional   resource manager has been shut down.
     */
    public static final int ERROR_CANNOT_ABORT_TRANSACTIONS = 0X00001AC0;

    /**
     * The specified session name is invalid.
     */
    public static final int ERROR_CTX_WINSTATION_NAME_INVALID = 0X00001B59;

    /**
     * The specified protocol driver is invalid.
     */
    public static final int ERROR_CTX_INVALID_PD = 0X00001B5A;

    /**
     * The specified protocol driver was not found in the   system path.
     */
    public static final int ERROR_CTX_PD_NOT_FOUND = 0X00001B5B;

    /**
     * The specified terminal connection driver was not found   in the system path.
     */
    public static final int ERROR_CTX_WD_NOT_FOUND = 0X00001B5C;

    /**
     * A registry key for event logging could not be created   for this session.
     */
    public static final int ERROR_CTX_CANNOT_MAKE_EVENTLOG_ENTRY = 0X00001B5D;

    /**
     * A service with the same name already exists on the   system.
     */
    public static final int ERROR_CTX_SERVICE_NAME_COLLISION = 0X00001B5E;

    /**
     * A close operation is pending on the session.
     */
    public static final int ERROR_CTX_CLOSE_PENDING = 0X00001B5F;

    /**
     * There are no free output buffers available.
     */
    public static final int ERROR_CTX_NO_OUTBUF = 0X00001B60;

    /**
     * The MODEM.INF file was not found.
     */
    public static final int ERROR_CTX_MODEM_INF_NOT_FOUND = 0X00001B61;

    /**
     * The modem name was not found in the MODEM.INF file.
     */
    public static final int ERROR_CTX_INVALID_MODEMNAME = 0X00001B62;

    /**
     * The modem did not accept the command sent to it.   Verify that the configured modem name matches the attached modem.
     */
    public static final int ERROR_CTX_MODEM_RESPONSE_ERROR = 0X00001B63;

    /**
     * The modem did not respond to the command sent to it.   Verify that the modem is properly cabled and turned on.
     */
    public static final int ERROR_CTX_MODEM_RESPONSE_TIMEOUT = 0X00001B64;

    /**
     * Carrier detect has failed or carrier has been dropped   due to disconnect.
     */
    public static final int ERROR_CTX_MODEM_RESPONSE_NO_CARRIER = 0X00001B65;

    /**
     * Dial tone not detected within the required time.   Verify that the phone cable is properly attached and functional.
     */
    public static final int ERROR_CTX_MODEM_RESPONSE_NO_DIALTONE = 0X00001B66;

    /**
     * Busy signal detected at remote site on callback.
     */
    public static final int ERROR_CTX_MODEM_RESPONSE_BUSY = 0X00001B67;

    /**
     * Voice detected at remote site on callback.
     */
    public static final int ERROR_CTX_MODEM_RESPONSE_VOICE = 0X00001B68;

    /**
     * Transport driver error.
     */
    public static final int ERROR_CTX_TD_ERROR = 0X00001B69;

    /**
     * The specified session cannot be found.
     */
    public static final int ERROR_CTX_WINSTATION_NOT_FOUND = 0X00001B6E;

    /**
     * The specified session name is already in use.
     */
    public static final int ERROR_CTX_WINSTATION_ALREADY_EXISTS = 0X00001B6F;

    /**
     * The requested operation cannot be completed because   the terminal connection is currently busy processing a connect, disconnect,   reset, or delete operation.
     */
    public static final int ERROR_CTX_WINSTATION_BUSY = 0X00001B70;

    /**
     * An attempt has been made to connect to a session whose   video mode is not supported by the current client.
     */
    public static final int ERROR_CTX_BAD_VIDEO_MODE = 0X00001B71;

    /**
     * The application attempted to enable DOS graphics mode.   DOS graphics mode is not supported.
     */
    public static final int ERROR_CTX_GRAPHICS_INVALID = 0X00001B7B;

    /**
     * Your interactive logon privilege has been disabled.   Contact your administrator.
     */
    public static final int ERROR_CTX_LOGON_DISABLED = 0X00001B7D;

    /**
     * The requested operation can be performed only on the   system console. This is most often the result of a driver or system DLL   requiring direct console access.
     */
    public static final int ERROR_CTX_NOT_CONSOLE = 0X00001B7E;

    /**
     * The client failed to respond to the server connect   message.
     */
    public static final int ERROR_CTX_CLIENT_QUERY_TIMEOUT = 0X00001B80;

    /**
     * Disconnecting the console session is not supported.
     */
    public static final int ERROR_CTX_CONSOLE_DISCONNECT = 0X00001B81;

    /**
     * Reconnecting a disconnected session to the console is   not supported.
     */
    public static final int ERROR_CTX_CONSOLE_CONNECT = 0X00001B82;

    /**
     * The request to control another session remotely was   denied.
     */
    public static final int ERROR_CTX_SHADOW_DENIED = 0X00001B84;

    /**
     * The requested session access is denied.
     */
    public static final int ERROR_CTX_WINSTATION_ACCESS_DENIED = 0X00001B85;

    /**
     * The specified terminal connection driver is invalid.
     */
    public static final int ERROR_CTX_INVALID_WD = 0X00001B89;

    /**
     * The requested session cannot be controlled remotely.   This might be because the session is disconnected or does not currently have   a user logged on.
     */
    public static final int ERROR_CTX_SHADOW_INVALID = 0X00001B8A;

    /**
     * The requested session is not configured to allow   remote control.
     */
    public static final int ERROR_CTX_SHADOW_DISABLED = 0X00001B8B;

    /**
     * None
     */
    public static final int ERROR_CTX_CLIENT_LICENSE_IN_USE = 0X00001B8C;

    /**
     * Your request to connect to this terminal server has   been rejected. Your terminal server client license number has not been   entered for this copy of the terminal server client. Contact your system
     * administrator.
     */
    public static final int ERROR_CTX_CLIENT_LICENSE_NOT_SET = 0X00001B8D;

    /**
     * The number of connections to this computer is limited   and all connections are in use right now. Try connecting later or contact   your system administrator.
     */
    public static final int ERROR_CTX_LICENSE_NOT_AVAILABLE = 0X00001B8E;

    /**
     * The client you are using is not licensed to use this   system. Your logon request is denied.
     */
    public static final int ERROR_CTX_LICENSE_CLIENT_INVALID = 0X00001B8F;

    /**
     * The system license has expired. Your logon request is   denied.
     */
    public static final int ERROR_CTX_LICENSE_EXPIRED = 0X00001B90;

    /**
     * Remote control could not be terminated because the   specified session is not currently being remotely controlled.
     */
    public static final int ERROR_CTX_SHADOW_NOT_RUNNING = 0X00001B91;

    /**
     * The remote control of the console was terminated   because the display mode was changed. Changing the display mode in a remote   control session is not supported.
     */
    public static final int ERROR_CTX_SHADOW_ENDED_BY_MODE_CHANGE = 0X00001B92;

    /**
     * Activation has already been reset the maximum number of   times for this installation. Your activation timer will not be cleared.
     */
    public static final int ERROR_ACTIVATION_COUNT_EXCEEDED = 0X00001B93;

    /**
     * Remote logons are currently disabled.
     */
    public static final int ERROR_CTX_WINSTATIONS_DISABLED = 0X00001B94;

    /**
     * You do not have the proper encryption level to access   this session.
     */
    public static final int ERROR_CTX_ENCRYPTION_LEVEL_REQUIRED = 0X00001B95;

    /**
     * The user %s\\%s is currently logged on to this   computer. Only the current user or an administrator can log on to this   computer.
     */
    public static final int ERROR_CTX_SESSION_IN_USE = 0X00001B96;

    /**
     * The user %s\\%s is already logged on to the console of   this computer. You do not have permission to log in at this time. To resolve   this issue, contact %s\\%s and have them log off.
     */
    public static final int ERROR_CTX_NO_FORCE_LOGOFF = 0X00001B97;

    /**
     * Unable to log you on because of an account   restriction.
     */
    public static final int ERROR_CTX_ACCOUNT_RESTRICTION = 0X00001B98;

    /**
     * The RDP component %2 detected an error in the protocol   stream and has disconnected the client.
     */
    public static final int ERROR_RDP_PROTOCOL_ERROR = 0X00001B99;

    /**
     * The Client Drive Mapping Service has connected on   terminal connection.
     */
    public static final int ERROR_CTX_CDM_CONNECT = 0X00001B9A;

    /**
     * The Client Drive Mapping Service has disconnected on   terminal connection.
     */
    public static final int ERROR_CTX_CDM_DISCONNECT = 0X00001B9B;

    /**
     * The terminal server security layer detected an error   in the protocol stream and has disconnected the client.
     */
    public static final int ERROR_CTX_SECURITY_LAYER_ERROR = 0X00001B9C;

    /**
     * The target session is incompatible with the current   session.
     */
    public static final int ERROR_TS_INCOMPATIBLE_SESSIONS = 0X00001B9D;

    /**
     * The file replication service API was called   incorrectly.
     */
    public static final int FRS_ERR_INVALID_API_SEQUENCE = 0X00001F41;

    /**
     * The file replication service cannot be started.
     */
    public static final int FRS_ERR_STARTING_SERVICE = 0X00001F42;

    /**
     * The file replication service cannot be stopped.
     */
    public static final int FRS_ERR_STOPPING_SERVICE = 0X00001F43;

    /**
     * The file replication service API terminated the   request. The event log might contain more information.
     */
    public static final int FRS_ERR_INTERNAL_API = 0X00001F44;

    /**
     * The file replication service terminated the request.   The event log might contain more information.
     */
    public static final int FRS_ERR_INTERNAL = 0X00001F45;

    /**
     * The file replication service cannot be contacted. The   event log might contain more information.
     */
    public static final int FRS_ERR_SERVICE_COMM = 0X00001F46;

    /**
     * The file replication service cannot satisfy the   request because the user has insufficient privileges. The event log might   contain more information.
     */
    public static final int FRS_ERR_INSUFFICIENT_PRIV = 0X00001F47;

    /**
     * The file replication service cannot satisfy the   request because authenticated RPC is not available. The event log might   contain more information.
     */
    public static final int FRS_ERR_AUTHENTICATION = 0X00001F48;

    /**
     * The file replication service cannot satisfy the   request because the user has insufficient privileges on the domain   controller. The event log might contain more information.
     */
    public static final int FRS_ERR_PARENT_INSUFFICIENT_PRIV = 0X00001F49;

    /**
     * The file replication service cannot satisfy the   request because authenticated RPC is not available on the domain controller.   The event log might contain more information.
     */
    public static final int FRS_ERR_PARENT_AUTHENTICATION = 0X00001F4A;

    /**
     * The file replication service cannot communicate with   the file replication service on the domain controller. The event log might   contain more information.
     */
    public static final int FRS_ERR_CHILD_TO_PARENT_COMM = 0X00001F4B;

    /**
     * The file replication service on the domain controller   cannot communicate with the file replication service on this computer. The   event log might contain more information.
     */
    public static final int FRS_ERR_PARENT_TO_CHILD_COMM = 0X00001F4C;

    /**
     * The file replication service cannot populate the   system volume because of an internal error. The event log might contain more   information.
     */
    public static final int FRS_ERR_SYSVOL_POPULATE = 0X00001F4D;

    /**
     * The file replication service cannot populate the   system volume because of an internal time-out. The event log might contain   more information.
     */
    public static final int FRS_ERR_SYSVOL_POPULATE_TIMEOUT = 0X00001F4E;

    /**
     * The file replication service cannot process the   request. The system volume is busy with a previous request.
     */
    public static final int FRS_ERR_SYSVOL_IS_BUSY = 0X00001F4F;

    /**
     * The file replication service cannot stop replicating   the system volume because of an internal error. The event log might contain   more information.
     */
    public static final int FRS_ERR_SYSVOL_DEMOTE = 0X00001F50;

    /**
     * The file replication service detected an invalid   parameter.
     */
    public static final int FRS_ERR_INVALID_SERVICE_PARAMETER = 0X00001F51;

    /**
     * An error occurred while installing the directory   service. For more information, see the event log.
     */
    public static final int ERROR_DS_NOT_INSTALLED = 0X00002008;

    /**
     * The directory service evaluated group memberships   locally.
     */
    public static final int ERROR_DS_MEMBERSHIP_EVALUATED_LOCALLY = 0X00002009;

    /**
     * The specified directory service attribute or value   does not exist.
     */
    public static final int ERROR_DS_NO_ATTRIBUTE_OR_VALUE = 0X0000200A;

    /**
     * The attribute syntax specified to the directory   service is invalid.
     */
    public static final int ERROR_DS_INVALID_ATTRIBUTE_SYNTAX = 0X0000200B;

    /**
     * The attribute type specified to the directory service   is not defined.
     */
    public static final int ERROR_DS_ATTRIBUTE_TYPE_UNDEFINED = 0X0000200C;

    /**
     * The specified directory service attribute or value   already exists.
     */
    public static final int ERROR_DS_ATTRIBUTE_OR_VALUE_EXISTS = 0X0000200D;

    /**
     * The directory service is busy.
     */
    public static final int ERROR_DS_BUSY = 0X0000200E;

    /**
     * The directory service is unavailable.
     */
    public static final int ERROR_DS_UNAVAILABLE = 0X0000200F;

    /**
     * The directory service was unable to allocate a   relative identifier.
     */
    public static final int ERROR_DS_NO_RIDS_ALLOCATED = 0X00002010;

    /**
     * The directory service has exhausted the pool of   relative identifiers.
     */
    public static final int ERROR_DS_NO_MORE_RIDS = 0X00002011;

    /**
     * The requested operation could not be performed because   the directory service is not the master for that type of operation.
     */
    public static final int ERROR_DS_INCORRECT_ROLE_OWNER = 0X00002012;

    /**
     * The directory service was unable to initialize the   subsystem that allocates relative identifiers.
     */
    public static final int ERROR_DS_RIDMGR_INIT_ERROR = 0X00002013;

    /**
     * The requested operation did not satisfy one or more   constraints associated with the class of the object.
     */
    public static final int ERROR_DS_OBJ_CLASS_VIOLATION = 0X00002014;

    /**
     * The directory service can perform the requested   operation only on a leaf object.
     */
    public static final int ERROR_DS_CANT_ON_NON_LEAF = 0X00002015;

    /**
     * The directory service cannot perform the requested   operation on the relative distinguished name (RDN) attribute of an object.
     */
    public static final int ERROR_DS_CANT_ON_RDN = 0X00002016;

    /**
     * The directory service detected an attempt to modify   the object class of an object.
     */
    public static final int ERROR_DS_CANT_MOD_OBJ_CLASS = 0X00002017;

    /**
     * The requested cross-domain move operation could not be   performed.
     */
    public static final int ERROR_DS_CROSS_DOM_MOVE_ERROR = 0X00002018;

    /**
     * Unable to contact the global catalog (GC) server.
     */
    public static final int ERROR_DS_GC_NOT_AVAILABLE = 0X00002019;

    /**
     * The policy object is shared and can only be modified   at the root.
     */
    public static final int ERROR_SHARED_POLICY = 0X0000201A;

    /**
     * The policy object does not exist.
     */
    public static final int ERROR_POLICY_OBJECT_NOT_FOUND = 0X0000201B;

    /**
     * The requested policy information is only in the   directory service.
     */
    public static final int ERROR_POLICY_ONLY_IN_DS = 0X0000201C;

    /**
     * A domain controller promotion is currently active.
     */
    public static final int ERROR_PROMOTION_ACTIVE = 0X0000201D;

    /**
     * A domain controller promotion is not currently active.
     */
    public static final int ERROR_NO_PROMOTION_ACTIVE = 0X0000201E;

    /**
     * An operations error occurred.
     */
    public static final int ERROR_DS_OPERATIONS_ERROR = 0X00002020;

    /**
     * A protocol error occurred.
     */
    public static final int ERROR_DS_PROTOCOL_ERROR = 0X00002021;

    /**
     * The time limit for this request was exceeded.
     */
    public static final int ERROR_DS_TIMELIMIT_EXCEEDED = 0X00002022;

    /**
     * The size limit for this request was exceeded.
     */
    public static final int ERROR_DS_SIZELIMIT_EXCEEDED = 0X00002023;

    /**
     * The administrative limit for this request was   exceeded.
     */
    public static final int ERROR_DS_ADMIN_LIMIT_EXCEEDED = 0X00002024;

    /**
     * The compare response was false.
     */
    public static final int ERROR_DS_COMPARE_FALSE = 0X00002025;

    /**
     * The compare response was true.
     */
    public static final int ERROR_DS_COMPARE_TRUE = 0X00002026;

    /**
     * The requested authentication method is not supported   by the server.
     */
    public static final int ERROR_DS_AUTH_METHOD_NOT_SUPPORTED = 0X00002027;

    /**
     * A more secure authentication method is required for   this server.
     */
    public static final int ERROR_DS_STRONG_AUTH_REQUIRED = 0X00002028;

    /**
     * Inappropriate authentication.
     */
    public static final int ERROR_DS_INAPPROPRIATE_AUTH = 0X00002029;

    /**
     * The authentication mechanism is unknown.
     */
    public static final int ERROR_DS_AUTH_UNKNOWN = 0X0000202A;

    /**
     * A referral was returned from the server.
     */
    public static final int ERROR_DS_REFERRAL = 0X0000202B;

    /**
     * The server does not support the requested critical   extension.
     */
    public static final int ERROR_DS_UNAVAILABLE_CRIT_EXTENSION = 0X0000202C;

    /**
     * This request requires a secure connection.
     */
    public static final int ERROR_DS_CONFIDENTIALITY_REQUIRED = 0X0000202D;

    /**
     * Inappropriate matching.
     */
    public static final int ERROR_DS_INAPPROPRIATE_MATCHING = 0X0000202E;

    /**
     * A constraint violation occurred.
     */
    public static final int ERROR_DS_CONSTRAINT_VIOLATION = 0X0000202F;

    /**
     * There is no such object on the server.
     */
    public static final int ERROR_DS_NO_SUCH_OBJECT = 0X00002030;

    /**
     * There is an alias problem.
     */
    public static final int ERROR_DS_ALIAS_PROBLEM = 0X00002031;

    /**
     * An invalid dn syntax has been specified.
     */
    public static final int ERROR_DS_INVALID_DN_SYNTAX = 0X00002032;

    /**
     * The object is a leaf object.
     */
    public static final int ERROR_DS_IS_LEAF = 0X00002033;

    /**
     * There is an alias dereferencing problem.
     */
    public static final int ERROR_DS_ALIAS_DEREF_PROBLEM = 0X00002034;

    /**
     * The server is unwilling to process the request.
     */
    public static final int ERROR_DS_UNWILLING_TO_PERFORM = 0X00002035;

    /**
     * A loop has been detected.
     */
    public static final int ERROR_DS_LOOP_DETECT = 0X00002036;

    /**
     * There is a naming violation.
     */
    public static final int ERROR_DS_NAMING_VIOLATION = 0X00002037;

    /**
     * The result set is too large.
     */
    public static final int ERROR_DS_OBJECT_RESULTS_TOO_LARGE = 0X00002038;

    /**
     * The operation affects multiple DSAs.
     */
    public static final int ERROR_DS_AFFECTS_MULTIPLE_DSAS = 0X00002039;

    /**
     * The server is not operational.
     */
    public static final int ERROR_DS_SERVER_DOWN = 0X0000203A;

    /**
     * A local error has occurred.
     */
    public static final int ERROR_DS_LOCAL_ERROR = 0X0000203B;

    /**
     * An encoding error has occurred.
     */
    public static final int ERROR_DS_ENCODING_ERROR = 0X0000203C;

    /**
     * A decoding error has occurred.
     */
    public static final int ERROR_DS_DECODING_ERROR = 0X0000203D;

    /**
     * The search filter cannot be recognized.
     */
    public static final int ERROR_DS_FILTER_UNKNOWN = 0X0000203E;

    /**
     * One or more parameters are illegal.
     */
    public static final int ERROR_DS_PARAM_ERROR = 0X0000203F;

    /**
     * The specified method is not supported.
     */
    public static final int ERROR_DS_NOT_SUPPORTED = 0X00002040;

    /**
     * No results were returned.
     */
    public static final int ERROR_DS_NO_RESULTS_RETURNED = 0X00002041;

    /**
     * The specified control is not supported by the server.
     */
    public static final int ERROR_DS_CONTROL_NOT_FOUND = 0X00002042;

    /**
     * A referral loop was detected by the client.
     */
    public static final int ERROR_DS_CLIENT_LOOP = 0X00002043;

    /**
     * The preset referral limit was exceeded.
     */
    public static final int ERROR_DS_REFERRAL_LIMIT_EXCEEDED = 0X00002044;

    /**
     * The search requires a SORT control.
     */
    public static final int ERROR_DS_SORT_CONTROL_MISSING = 0X00002045;

    /**
     * The search results exceed the offset range specified.
     */
    public static final int ERROR_DS_OFFSET_RANGE_ERROR = 0X00002046;

    /**
     * The root object must be the head of a naming context.   The root object cannot have an instantiated parent.
     */
    public static final int ERROR_DS_ROOT_MUST_BE_NC = 0X0000206D;

    /**
     * The add replica operation cannot be performed. The   naming context must be writable to create the replica.
     */
    public static final int ERROR_DS_ADD_REPLICA_INHIBITED = 0X0000206E;

    /**
     * A reference to an attribute that is not defined in the   schema occurred.
     */
    public static final int ERROR_DS_ATT_NOT_DEF_IN_SCHEMA = 0X0000206F;

    /**
     * The maximum size of an object has been exceeded.
     */
    public static final int ERROR_DS_MAX_OBJ_SIZE_EXCEEDED = 0X00002070;

    /**
     * An attempt was made to add an object to the directory   with a name that is already in use.
     */
    public static final int ERROR_DS_OBJ_STRING_NAME_EXISTS = 0X00002071;

    /**
     * An attempt was made to add an object of a class that   does not have an RDN defined in the schema.
     */
    public static final int ERROR_DS_NO_RDN_DEFINED_IN_SCHEMA = 0X00002072;

    /**
     * An attempt was made to add an object using an RDN that   is not the RDN defined in the schema.
     */
    public static final int ERROR_DS_RDN_DOESNT_MATCH_SCHEMA = 0X00002073;

    /**
     * None of the requested attributes were found on the   objects.
     */
    public static final int ERROR_DS_NO_REQUESTED_ATTS_FOUND = 0X00002074;

    /**
     * The user buffer is too small.
     */
    public static final int ERROR_DS_USER_BUFFER_TO_SMALL = 0X00002075;

    /**
     * The attribute specified in the operation is not   present on the object.
     */
    public static final int ERROR_DS_ATT_IS_NOT_ON_OBJ = 0X00002076;

    /**
     * Illegal modify operation. Some aspect of the   modification is not permitted.
     */
    public static final int ERROR_DS_ILLEGAL_MOD_OPERATION = 0X00002077;

    /**
     * The specified object is too large.
     */
    public static final int ERROR_DS_OBJ_TOO_LARGE = 0X00002078;

    /**
     * The specified instance type is not valid.
     */
    public static final int ERROR_DS_BAD_INSTANCE_TYPE = 0X00002079;

    /**
     * The operation must be performed at a master DSA.
     */
    public static final int ERROR_DS_MASTERDSA_REQUIRED = 0X0000207A;

    /**
     * The object class attribute must be specified.
     */
    public static final int ERROR_DS_OBJECT_CLASS_REQUIRED = 0X0000207B;

    /**
     * A required attribute is missing.
     */
    public static final int ERROR_DS_MISSING_REQUIRED_ATT = 0X0000207C;

    /**
     * An attempt was made to modify an object to include an   attribute that is not legal for its class.
     */
    public static final int ERROR_DS_ATT_NOT_DEF_FOR_CLASS = 0X0000207D;

    /**
     * The specified attribute is already present on the   object.
     */
    public static final int ERROR_DS_ATT_ALREADY_EXISTS = 0X0000207E;

    /**
     * The specified attribute is not present, or has no   values.
     */
    public static final int ERROR_DS_CANT_ADD_ATT_VALUES = 0X00002080;

    /**
     * Multiple values were specified for an attribute that   can have only one value.
     */
    public static final int ERROR_DS_SINGLE_VALUE_CONSTRAINT = 0X00002081;

    /**
     * A value for the attribute was not in the acceptable   range of values.
     */
    public static final int ERROR_DS_RANGE_CONSTRAINT = 0X00002082;

    /**
     * The specified value already exists.
     */
    public static final int ERROR_DS_ATT_VAL_ALREADY_EXISTS = 0X00002083;

    /**
     * The attribute cannot be removed because it is not   present on the object.
     */
    public static final int ERROR_DS_CANT_REM_MISSING_ATT = 0X00002084;

    /**
     * The attribute value cannot be removed because it is   not present on the object.
     */
    public static final int ERROR_DS_CANT_REM_MISSING_ATT_VAL = 0X00002085;

    /**
     * The specified root object cannot be a subreference.
     */
    public static final int ERROR_DS_ROOT_CANT_BE_SUBREF = 0X00002086;

    /**
     * Chaining is not permitted.
     */
    public static final int ERROR_DS_NO_CHAINING = 0X00002087;

    /**
     * Chained evaluation is not permitted.
     */
    public static final int ERROR_DS_NO_CHAINED_EVAL = 0X00002088;

    /**
     * The operation could not be performed because the   object\'s parent is either uninstantiated or deleted.
     */
    public static final int ERROR_DS_NO_PARENT_OBJECT = 0X00002089;

    /**
     * Having a parent that is an alias is not permitted.   Aliases are leaf objects.
     */
    public static final int ERROR_DS_PARENT_IS_AN_ALIAS = 0X0000208A;

    /**
     * The object and parent must be of the same type, either   both masters or both replicas.
     */
    public static final int ERROR_DS_CANT_MIX_MASTER_AND_REPS = 0X0000208B;

    /**
     * The operation cannot be performed because child objects   exist. This operation can only be performed on a leaf object.
     */
    public static final int ERROR_DS_CHILDREN_EXIST = 0X0000208C;

    /**
     * Directory object not found.
     */
    public static final int ERROR_DS_OBJ_NOT_FOUND = 0X0000208D;

    /**
     * The aliased object is missing.
     */
    public static final int ERROR_DS_ALIASED_OBJ_MISSING = 0X0000208E;

    /**
     * The object name has bad syntax.
     */
    public static final int ERROR_DS_BAD_NAME_SYNTAX = 0X0000208F;

    /**
     * An alias is not permitted to refer to another alias.
     */
    public static final int ERROR_DS_ALIAS_POINTS_TO_ALIAS = 0X00002090;

    /**
     * The alias cannot be dereferenced.
     */
    public static final int ERROR_DS_CANT_DEREF_ALIAS = 0X00002091;

    /**
     * The operation is out of scope.
     */
    public static final int ERROR_DS_OUT_OF_SCOPE = 0X00002092;

    /**
     * The operation cannot continue because the object is in   the process of being removed.
     */
    public static final int ERROR_DS_OBJECT_BEING_REMOVED = 0X00002093;

    /**
     * The DSA object cannot be deleted.
     */
    public static final int ERROR_DS_CANT_DELETE_DSA_OBJ = 0X00002094;

    /**
     * A directory service error has occurred.
     */
    public static final int ERROR_DS_GENERIC_ERROR = 0X00002095;

    /**
     * The operation can only be performed on an internal   master DSA object.
     */
    public static final int ERROR_DS_DSA_MUST_BE_INT_MASTER = 0X00002096;

    /**
     * The object must be of class DSA.
     */
    public static final int ERROR_DS_CLASS_NOT_DSA = 0X00002097;

    /**
     * Insufficient access rights to perform the operation.
     */
    public static final int ERROR_DS_INSUFF_ACCESS_RIGHTS = 0X00002098;

    /**
     * The object cannot be added because the parent is not   on the list of possible superiors.
     */
    public static final int ERROR_DS_ILLEGAL_SUPERIOR = 0X00002099;

    /**
     * Access to the attribute is not permitted because the   attribute is owned by the SAM.
     */
    public static final int ERROR_DS_ATTRIBUTE_OWNED_BY_SAM = 0X0000209A;

    /**
     * The name has too many parts.
     */
    public static final int ERROR_DS_NAME_TOO_MANY_PARTS = 0X0000209B;

    /**
     * The name is too long.
     */
    public static final int ERROR_DS_NAME_TOO_LONG = 0X0000209C;

    /**
     * The name value is too long.
     */
    public static final int ERROR_DS_NAME_VALUE_TOO_LONG = 0X0000209D;

    /**
     * The directory service encountered an error parsing a   name.
     */
    public static final int ERROR_DS_NAME_UNPARSEABLE = 0X0000209E;

    /**
     * The directory service cannot get the attribute type   for a name.
     */
    public static final int ERROR_DS_NAME_TYPE_UNKNOWN = 0X0000209F;

    /**
     * The name does not identify an object; the name   identifies a phantom.
     */
    public static final int ERROR_DS_NOT_AN_OBJECT = 0X000020A0;

    /**
     * The security descriptor is too short.
     */
    public static final int ERROR_DS_SEC_DESC_TOO_SHORT = 0X000020A1;

    /**
     * The security descriptor is invalid.
     */
    public static final int ERROR_DS_SEC_DESC_INVALID = 0X000020A2;

    /**
     * Failed to create name for deleted object.
     */
    public static final int ERROR_DS_NO_DELETED_NAME = 0X000020A3;

    /**
     * The parent of a new subreference must exist.
     */
    public static final int ERROR_DS_SUBREF_MUST_HAVE_PARENT = 0X000020A4;

    /**
     * The object must be a naming context.
     */
    public static final int ERROR_DS_NCNAME_MUST_BE_NC = 0X000020A5;

    /**
     * It is not permitted to add an attribute that is owned   by the system.
     */
    public static final int ERROR_DS_CANT_ADD_SYSTEM_ONLY = 0X000020A6;

    /**
     * The class of the object must be structural; you cannot   instantiate an abstract class.
     */
    public static final int ERROR_DS_CLASS_MUST_BE_CONCRETE = 0X000020A7;

    /**
     * The schema object could not be found.
     */
    public static final int ERROR_DS_INVALID_DMD = 0X000020A8;

    /**
     * A local object with this GUID (dead or alive) already   exists.
     */
    public static final int ERROR_DS_OBJ_GUID_EXISTS = 0X000020A9;

    /**
     * The operation cannot be performed on a back link.
     */
    public static final int ERROR_DS_NOT_ON_BACKLINK = 0X000020AA;

    /**
     * The cross-reference for the specified naming context   could not be found.
     */
    public static final int ERROR_DS_NO_CROSSREF_FOR_NC = 0X000020AB;

    /**
     * The operation could not be performed because the   directory service is shutting down.
     */
    public static final int ERROR_DS_SHUTTING_DOWN = 0X000020AC;

    /**
     * The directory service request is invalid.
     */
    public static final int ERROR_DS_UNKNOWN_OPERATION = 0X000020AD;

    /**
     * The role owner attribute could not be read.
     */
    public static final int ERROR_DS_INVALID_ROLE_OWNER = 0X000020AE;

    /**
     * The requested Flexible Single Master Operations (FSMO)   operation failed. The current FSMO holder could not be contacted.
     */
    public static final int ERROR_DS_COULDNT_CONTACT_FSMO = 0X000020AF;

    /**
     * Modification of a distinguished name across a naming   context is not permitted.
     */
    public static final int ERROR_DS_CROSS_NC_DN_RENAME = 0X000020B0;

    /**
     * The attribute cannot be modified because it is owned   by the system.
     */
    public static final int ERROR_DS_CANT_MOD_SYSTEM_ONLY = 0X000020B1;

    /**
     * Only the replicator can perform this function.
     */
    public static final int ERROR_DS_REPLICATOR_ONLY = 0X000020B2;

    /**
     * The specified class is not defined.
     */
    public static final int ERROR_DS_OBJ_CLASS_NOT_DEFINED = 0X000020B3;

    /**
     * The specified class is not a subclass.
     */
    public static final int ERROR_DS_OBJ_CLASS_NOT_SUBCLASS = 0X000020B4;

    /**
     * The name reference is invalid.
     */
    public static final int ERROR_DS_NAME_REFERENCE_INVALID = 0X000020B5;

    /**
     * A cross-reference already exists.
     */
    public static final int ERROR_DS_CROSS_REF_EXISTS = 0X000020B6;

    /**
     * It is not permitted to delete a master   cross-reference.
     */
    public static final int ERROR_DS_CANT_DEL_MASTER_CROSSREF = 0X000020B7;

    /**
     * Subtree notifications are only supported on naming   context (NC) heads.
     */
    public static final int ERROR_DS_SUBTREE_NOTIFY_NOT_NC_HEAD = 0X000020B8;

    /**
     * Notification filter is too complex.
     */
    public static final int ERROR_DS_NOTIFY_FILTER_TOO_COMPLEX = 0X000020B9;

    /**
     * Schema update failed: Duplicate RDN.
     */
    public static final int ERROR_DS_DUP_RDN = 0X000020BA;

    /**
     * Schema update failed: Duplicate OID.
     */
    public static final int ERROR_DS_DUP_OID = 0X000020BB;

    /**
     * Schema update failed: Duplicate Message Application   Programming Interface (MAPI) identifier.
     */
    public static final int ERROR_DS_DUP_MAPI_ID = 0X000020BC;

    /**
     * Schema update failed: Duplicate schema ID GUID.
     */
    public static final int ERROR_DS_DUP_SCHEMA_ID_GUID = 0X000020BD;

    /**
     * Schema update failed: Duplicate LDAP display name.
     */
    public static final int ERROR_DS_DUP_LDAP_DISPLAY_NAME = 0X000020BE;

    /**
     * Schema update failed: Range-Lower less than   Range-Upper.
     */
    public static final int ERROR_DS_SEMANTIC_ATT_TEST = 0X000020BF;

    /**
     * Schema update failed: Syntax mismatch.
     */
    public static final int ERROR_DS_SYNTAX_MISMATCH = 0X000020C0;

    /**
     * Schema deletion failed: Attribute is used in the   Must-Contain list.
     */
    public static final int ERROR_DS_EXISTS_IN_MUST_HAVE = 0X000020C1;

    /**
     * Schema deletion failed: Attribute is used in the   May-Contain list.
     */
    public static final int ERROR_DS_EXISTS_IN_MAY_HAVE = 0X000020C2;

    /**
     * Schema update failed: Attribute in May-Contain list   does not exist.
     */
    public static final int ERROR_DS_NONEXISTENT_MAY_HAVE = 0X000020C3;

    /**
     * Schema update failed: Attribute in the Must-Contain   list does not exist.
     */
    public static final int ERROR_DS_NONEXISTENT_MUST_HAVE = 0X000020C4;

    /**
     * Schema update failed: Class in the Aux Class list does   not exist or is not an auxiliary class.
     */
    public static final int ERROR_DS_AUX_CLS_TEST_FAIL = 0X000020C5;

    /**
     * Schema update failed: Class in the Poss-Superiors list   does not exist.
     */
    public static final int ERROR_DS_NONEXISTENT_POSS_SUP = 0X000020C6;

    /**
     * Schema update failed: Class in the subclass of the   list does not exist or does not satisfy hierarchy rules.
     */
    public static final int ERROR_DS_SUB_CLS_TEST_FAIL = 0X000020C7;

    /**
     * Schema update failed: Rdn-Att-Id has wrong syntax.
     */
    public static final int ERROR_DS_BAD_RDN_ATT_ID_SYNTAX = 0X000020C8;

    /**
     * Schema deletion failed: Class is used as an auxiliary   class.
     */
    public static final int ERROR_DS_EXISTS_IN_AUX_CLS = 0X000020C9;

    /**
     * Schema deletion failed: Class is used as a subclass.
     */
    public static final int ERROR_DS_EXISTS_IN_SUB_CLS = 0X000020CA;

    /**
     * Schema deletion failed: Class is used as a   Poss-Superior.
     */
    public static final int ERROR_DS_EXISTS_IN_POSS_SUP = 0X000020CB;

    /**
     * Schema update failed in recalculating validation   cache.
     */
    public static final int ERROR_DS_RECALCSCHEMA_FAILED = 0X000020CC;

    /**
     * The tree deletion is not finished. The request must be   made again to continue deleting the tree.
     */
    public static final int ERROR_DS_TREE_DELETE_NOT_FINISHED = 0X000020CD;

    /**
     * The requested delete operation could not be performed.
     */
    public static final int ERROR_DS_CANT_DELETE = 0X000020CE;

    /**
     * Cannot read the governs class identifier for the   schema record.
     */
    public static final int ERROR_DS_ATT_SCHEMA_REQ_ID = 0X000020CF;

    /**
     * The attribute schema has bad syntax.
     */
    public static final int ERROR_DS_BAD_ATT_SCHEMA_SYNTAX = 0X000020D0;

    /**
     * The attribute could not be cached.
     */
    public static final int ERROR_DS_CANT_CACHE_ATT = 0X000020D1;

    /**
     * The class could not be cached.
     */
    public static final int ERROR_DS_CANT_CACHE_CLASS = 0X000020D2;

    /**
     * The attribute could not be removed from the cache.
     */
    public static final int ERROR_DS_CANT_REMOVE_ATT_CACHE = 0X000020D3;

    /**
     * The class could not be removed from the cache.
     */
    public static final int ERROR_DS_CANT_REMOVE_CLASS_CACHE = 0X000020D4;

    /**
     * The distinguished name attribute could not be read.
     */
    public static final int ERROR_DS_CANT_RETRIEVE_DN = 0X000020D5;

    /**
     * No superior reference has been configured for the   directory service. The directory service is, therefore, unable to issue   referrals to objects outside this forest.
     */
    public static final int ERROR_DS_MISSING_SUPREF = 0X000020D6;

    /**
     * The instance type attribute could not be retrieved.
     */
    public static final int ERROR_DS_CANT_RETRIEVE_INSTANCE = 0X000020D7;

    /**
     * An internal error has occurred.
     */
    public static final int ERROR_DS_CODE_INCONSISTENCY = 0X000020D8;

    /**
     * A database error has occurred.
     */
    public static final int ERROR_DS_DATABASE_ERROR = 0X000020D9;

    /**
     * None
     */
    public static final int ERROR_DS_GOVERNSID_MISSING = 0X000020DA;

    /**
     * An expected attribute is missing.
     */
    public static final int ERROR_DS_MISSING_EXPECTED_ATT = 0X000020DB;

    /**
     * The specified naming context is missing a   cross-reference.
     */
    public static final int ERROR_DS_NCNAME_MISSING_CR_REF = 0X000020DC;

    /**
     * A security checking error has occurred.
     */
    public static final int ERROR_DS_SECURITY_CHECKING_ERROR = 0X000020DD;

    /**
     * The schema is not loaded.
     */
    public static final int ERROR_DS_SCHEMA_NOT_LOADED = 0X000020DE;

    /**
     * Schema allocation failed. Check if the machine is   running low on memory.
     */
    public static final int ERROR_DS_SCHEMA_ALLOC_FAILED = 0X000020DF;

    /**
     * Failed to obtain the required syntax for the attribute   schema.
     */
    public static final int ERROR_DS_ATT_SCHEMA_REQ_SYNTAX = 0X000020E0;

    /**
     * The GC verification failed. The GC is not available or   does not support the operation. Some part of the directory is currently not   available.
     */
    public static final int ERROR_DS_GCVERIFY_ERROR = 0X000020E1;

    /**
     * The replication operation failed because of a schema   mismatch between the servers involved.
     */
    public static final int ERROR_DS_DRA_SCHEMA_MISMATCH = 0X000020E2;

    /**
     * The DSA object could not be found.
     */
    public static final int ERROR_DS_CANT_FIND_DSA_OBJ = 0X000020E3;

    /**
     * The naming context could not be found.
     */
    public static final int ERROR_DS_CANT_FIND_EXPECTED_NC = 0X000020E4;

    /**
     * The naming context could not be found in the cache.
     */
    public static final int ERROR_DS_CANT_FIND_NC_IN_CACHE = 0X000020E5;

    /**
     * The child object could not be retrieved.
     */
    public static final int ERROR_DS_CANT_RETRIEVE_CHILD = 0X000020E6;

    /**
     * The modification was not permitted for security   reasons.
     */
    public static final int ERROR_DS_SECURITY_ILLEGAL_MODIFY = 0X000020E7;

    /**
     * The operation cannot replace the hidden record.
     */
    public static final int ERROR_DS_CANT_REPLACE_HIDDEN_REC = 0X000020E8;

    /**
     * The hierarchy file is invalid.
     */
    public static final int ERROR_DS_BAD_HIERARCHY_FILE = 0X000020E9;

    /**
     * The attempt to build the hierarchy table failed.
     */
    public static final int ERROR_DS_BUILD_HIERARCHY_TABLE_FAILED = 0X000020EA;

    /**
     * The directory configuration parameter is missing from   the registry.
     */
    public static final int ERROR_DS_CONFIG_PARAM_MISSING = 0X000020EB;

    /**
     * The attempt to count the address book indices failed.
     */
    public static final int ERROR_DS_COUNTING_AB_INDICES_FAILED = 0X000020EC;

    /**
     * The allocation of the hierarchy table failed.
     */
    public static final int ERROR_DS_HIERARCHY_TABLE_MALLOC_FAILED = 0X000020ED;

    /**
     * The directory service encountered an internal failure.
     */
    public static final int ERROR_DS_INTERNAL_FAILURE = 0X000020EE;

    /**
     * The directory service encountered an unknown failure.
     */
    public static final int ERROR_DS_UNKNOWN_ERROR = 0X000020EF;

    /**
     * A root object requires a class of \"top\".
     */
    public static final int ERROR_DS_ROOT_REQUIRES_CLASS_TOP = 0X000020F0;

    /**
     * This directory server is shutting down, and cannot   take ownership of new floating single-master operation roles.
     */
    public static final int ERROR_DS_REFUSING_FSMO_ROLES = 0X000020F1;

    /**
     * The directory service is missing mandatory   configuration information and is unable to determine the ownership of floating   single-master operation roles.
     */
    public static final int ERROR_DS_MISSING_FSMO_SETTINGS = 0X000020F2;

    /**
     * The directory service was unable to transfer ownership   of one or more floating single-master operation roles to other servers.
     */
    public static final int ERROR_DS_UNABLE_TO_SURRENDER_ROLES = 0X000020F3;

    /**
     * The replication operation failed.
     */
    public static final int ERROR_DS_DRA_GENERIC = 0X000020F4;

    /**
     * An invalid parameter was specified for this   replication operation.
     */
    public static final int ERROR_DS_DRA_INVALID_PARAMETER = 0X000020F5;

    /**
     * The directory service is too busy to complete the   replication operation at this time.
     */
    public static final int ERROR_DS_DRA_BUSY = 0X000020F6;

    /**
     * The DN specified for this replication operation is   invalid.
     */
    public static final int ERROR_DS_DRA_BAD_DN = 0X000020F7;

    /**
     * The naming context specified for this replication   operation is invalid.
     */
    public static final int ERROR_DS_DRA_BAD_NC = 0X000020F8;

    /**
     * The DN specified for this replication operation   already exists.
     */
    public static final int ERROR_DS_DRA_DN_EXISTS = 0X000020F9;

    /**
     * The replication system encountered an internal error.
     */
    public static final int ERROR_DS_DRA_INTERNAL_ERROR = 0X000020FA;

    /**
     * The replication operation encountered a database   inconsistency.
     */
    public static final int ERROR_DS_DRA_INCONSISTENT_DIT = 0X000020FB;

    /**
     * The server specified for this replication operation   could not be contacted.
     */
    public static final int ERROR_DS_DRA_CONNECTION_FAILED = 0X000020FC;

    /**
     * The replication operation encountered an object with   an invalid instance type.
     */
    public static final int ERROR_DS_DRA_BAD_INSTANCE_TYPE = 0X000020FD;

    /**
     * The replication operation failed to allocate memory.
     */
    public static final int ERROR_DS_DRA_OUT_OF_MEM = 0X000020FE;

    /**
     * The replication operation encountered an error with   the mail system.
     */
    public static final int ERROR_DS_DRA_MAIL_PROBLEM = 0X000020FF;

    /**
     * The replication reference information for the target   server already exists.
     */
    public static final int ERROR_DS_DRA_REF_ALREADY_EXISTS = 0X00002100;

    /**
     * The replication reference information for the target   server does not exist.
     */
    public static final int ERROR_DS_DRA_REF_NOT_FOUND = 0X00002101;

    /**
     * The naming context cannot be removed because it is   replicated to another server.
     */
    public static final int ERROR_DS_DRA_OBJ_IS_REP_SOURCE = 0X00002102;

    /**
     * The replication operation encountered a database   error.
     */
    public static final int ERROR_DS_DRA_DB_ERROR = 0X00002103;

    /**
     * The naming context is in the process of being removed   or is not replicated from the specified server.
     */
    public static final int ERROR_DS_DRA_NO_REPLICA = 0X00002104;

    /**
     * Replication access was denied.
     */
    public static final int ERROR_DS_DRA_ACCESS_DENIED = 0X00002105;

    /**
     * The requested operation is not supported by this   version of the directory service.
     */
    public static final int ERROR_DS_DRA_NOT_SUPPORTED = 0X00002106;

    /**
     * The replication RPC was canceled.
     */
    public static final int ERROR_DS_DRA_RPC_CANCELLED = 0X00002107;

    /**
     * The source server is currently rejecting replication   requests.
     */
    public static final int ERROR_DS_DRA_SOURCE_DISABLED = 0X00002108;

    /**
     * The destination server is currently rejecting   replication requests.
     */
    public static final int ERROR_DS_DRA_SINK_DISABLED = 0X00002109;

    /**
     * The replication operation failed due to a collision of   object names.
     */
    public static final int ERROR_DS_DRA_NAME_COLLISION = 0X0000210A;

    /**
     * The replication source has been reinstalled.
     */
    public static final int ERROR_DS_DRA_SOURCE_REINSTALLED = 0X0000210B;

    /**
     * The replication operation failed because a required   parent object is missing.
     */
    public static final int ERROR_DS_DRA_MISSING_PARENT = 0X0000210C;

    /**
     * The replication operation was preempted.
     */
    public static final int ERROR_DS_DRA_PREEMPTED = 0X0000210D;

    /**
     * The replication synchronization attempt was abandoned   because of a lack of updates.
     */
    public static final int ERROR_DS_DRA_ABANDON_SYNC = 0X0000210E;

    /**
     * The replication operation was terminated because the   system is shutting down.
     */
    public static final int ERROR_DS_DRA_SHUTDOWN = 0X0000210F;

    /**
     * A synchronization attempt failed because the   destination DC is currently waiting to synchronize new partial attributes   from the source. This condition is normal if a recent schema change modified   the partial
     * attribute set. The destination partial attribute set is not a   subset of the source partial attribute set.
     */
    public static final int ERROR_DS_DRA_INCOMPATIBLE_PARTIAL_SET = 0X00002110;

    /**
     * The replication synchronization attempt failed because   a master replica attempted to sync from a partial replica.
     */
    public static final int ERROR_DS_DRA_SOURCE_IS_PARTIAL_REPLICA = 0X00002111;

    /**
     * The server specified for this replication operation   was contacted, but that server was unable to contact an additional server   needed to complete the operation.
     */
    public static final int ERROR_DS_DRA_EXTN_CONNECTION_FAILED = 0X00002112;

    /**
     * The version of the directory service schema of the   source forest is not compatible with the version of the directory service on   this computer.
     */
    public static final int ERROR_DS_INSTALL_SCHEMA_MISMATCH = 0X00002113;

    /**
     * Schema update failed: An attribute with the same link   identifier already exists.
     */
    public static final int ERROR_DS_DUP_LINK_ID = 0X00002114;

    /**
     * Name translation: Generic processing error.
     */
    public static final int ERROR_DS_NAME_ERROR_RESOLVING = 0X00002115;

    /**
     * Name translation: Could not find the name or   insufficient right to see name.
     */
    public static final int ERROR_DS_NAME_ERROR_NOT_FOUND = 0X00002116;

    /**
     * Name translation: Input name mapped to more than one   output name.
     */
    public static final int ERROR_DS_NAME_ERROR_NOT_UNIQUE = 0X00002117;

    /**
     * Name translation: The input name was found but not the   associated output format.
     */
    public static final int ERROR_DS_NAME_ERROR_NO_MAPPING = 0X00002118;

    /**
     * Name translation: Unable to resolve completely, only   the domain was found.
     */
    public static final int ERROR_DS_NAME_ERROR_DOMAIN_ONLY = 0X00002119;

    /**
     * Name translation: Unable to perform purely syntactical   mapping at the client without going out to the wire.
     */
    public static final int ERROR_DS_NAME_ERROR_NO_SYNTACTICAL_MAPPING = 0X0000211A;

    /**
     * Modification of a constructed attribute is not   allowed.
     */
    public static final int ERROR_DS_CONSTRUCTED_ATT_MOD = 0X0000211B;

    /**
     * The OM-Object-Class specified is incorrect for an attribute   with the specified syntax.
     */
    public static final int ERROR_DS_WRONG_OM_OBJ_CLASS = 0X0000211C;

    /**
     * The replication request has been posted; waiting for a   reply.
     */
    public static final int ERROR_DS_DRA_REPL_PENDING = 0X0000211D;

    /**
     * The requested operation requires a directory service,   and none was available.
     */
    public static final int ERROR_DS_DS_REQUIRED = 0X0000211E;

    /**
     * The LDAP display name of the class or attribute   contains non-ASCII characters.
     */
    public static final int ERROR_DS_INVALID_LDAP_DISPLAY_NAME = 0X0000211F;

    /**
     * The requested search operation is only supported for   base searches.
     */
    public static final int ERROR_DS_NON_BASE_SEARCH = 0X00002120;

    /**
     * The search failed to retrieve attributes from the   database.
     */
    public static final int ERROR_DS_CANT_RETRIEVE_ATTS = 0X00002121;

    /**
     * The schema update operation tried to add a backward   link attribute that has no corresponding forward link.
     */
    public static final int ERROR_DS_BACKLINK_WITHOUT_LINK = 0X00002122;

    /**
     * The source and destination of a cross-domain move do   not agree on the object\'s epoch number. Either the source or the destination   does not have the latest version of the object.
     */
    public static final int ERROR_DS_EPOCH_MISMATCH = 0X00002123;

    /**
     * The source and destination of a cross-domain move do   not agree on the object\'s current name. Either the source or the destination   does not have the latest version of the object.
     */
    public static final int ERROR_DS_SRC_NAME_MISMATCH = 0X00002124;

    /**
     * The source and destination for the cross-domain move operation   are identical. The caller should use a local move operation instead of a   cross-domain move operation.
     */
    public static final int ERROR_DS_SRC_AND_DST_NC_IDENTICAL = 0X00002125;

    /**
     * The source and destination for a cross-domain move do   not agree on the naming contexts in the forest. Either the source or the   destination does not have the latest version of the Partitions container.
     */
    public static final int ERROR_DS_DST_NC_MISMATCH = 0X00002126;

    /**
     * The destination of a cross-domain move is not   authoritative for the destination naming context.
     */
    public static final int ERROR_DS_NOT_AUTHORITIVE_FOR_DST_NC = 0X00002127;

    /**
     * The source and destination of a cross-domain move do   not agree on the identity of the source object. Either the source or the   destination does not have the latest version of the source object.
     */
    public static final int ERROR_DS_SRC_GUID_MISMATCH = 0X00002128;

    /**
     * The object being moved across domains is already known   to be deleted by the destination server. The source server does not have the   latest version of the source object.
     */
    public static final int ERROR_DS_CANT_MOVE_DELETED_OBJECT = 0X00002129;

    /**
     * Another operation that requires exclusive access to   the PDC FSMO is already in progress.
     */
    public static final int ERROR_DS_PDC_OPERATION_IN_PROGRESS = 0X0000212A;

    /**
     * A cross-domain move operation failed because two   versions of the moved object exist - one each in the source and destination   domains. The destination object needs to be removed to restore the system to   a
     * consistent state.
     */
    public static final int ERROR_DS_CROSS_DOMAIN_CLEANUP_REQD = 0X0000212B;

    /**
     * This object cannot be moved across domain boundaries   either because cross-domain moves for this class are not allowed, or the   object has some special characteristics, for example, a trust account or a
     * restricted relative identifier (RID), that prevent its move.
     */
    public static final int ERROR_DS_ILLEGAL_XDOM_MOVE_OPERATION = 0X0000212C;

    /**
     * Cannot move objects with memberships across domain   boundaries because, once moved, this violates the membership conditions of   the account group. Remove the object from any account group memberships and retry.
     */
    public static final int ERROR_DS_CANT_WITH_ACCT_GROUP_MEMBERSHPS = 0X0000212D;

    /**
     * A naming context head must be the immediate child of   another naming context head, not of an interior node.
     */
    public static final int ERROR_DS_NC_MUST_HAVE_NC_PARENT = 0X0000212E;

    /**
     * The directory cannot validate the proposed naming   context name because it does not hold a replica of the naming context above   the proposed naming context. Ensure that the domain naming master role is   held by
     * a server that is configured as a GC server, and that the server is   up-to-date with its replication partners. (Applies only to Windows 2000   operating system domain naming masters.)
     */
    public static final int ERROR_DS_CR_IMPOSSIBLE_TO_VALIDATE = 0X0000212F;

    /**
     * Destination domain must be in native mode.
     */
    public static final int ERROR_DS_DST_DOMAIN_NOT_NATIVE = 0X00002130;

    /**
     * The operation cannot be performed because the server   does not have an infrastructure container in the domain of interest.
     */
    public static final int ERROR_DS_MISSING_INFRASTRUCTURE_CONTAINER = 0X00002131;

    /**
     * Cross-domain moves of nonempty account groups is not   allowed.
     */
    public static final int ERROR_DS_CANT_MOVE_ACCOUNT_GROUP = 0X00002132;

    /**
     * Cross-domain moves of nonempty resource groups is not   allowed.
     */
    public static final int ERROR_DS_CANT_MOVE_RESOURCE_GROUP = 0X00002133;

    /**
     * The search flags for the attribute are invalid. The   ambiguous name resolution (ANR) bit is valid only on attributes of Unicode or   Teletex strings.
     */
    public static final int ERROR_DS_INVALID_SEARCH_FLAG = 0X00002134;

    /**
     * Tree deletions starting at an object that has an NC   head as a descendant are not allowed.
     */
    public static final int ERROR_DS_NO_TREE_DELETE_ABOVE_NC = 0X00002135;

    /**
     * The directory service failed to lock a tree in   preparation for a tree deletion because the tree was in use.
     */
    public static final int ERROR_DS_COULDNT_LOCK_TREE_FOR_DELETE = 0X00002136;

    /**
     * The directory service failed to identify the list of   objects to delete while attempting a tree deletion.
     */
    public static final int ERROR_DS_COULDNT_IDENTIFY_OBJECTS_FOR_TREE_DELETE = 0X00002137;

    /**
     * SAM initialization failed because of the following   error: %1. Error Status: 0x%2. Click OK to shut down the system and reboot   into Directory Services Restore Mode. Check the event log for detailed
     * information.
     */
    public static final int ERROR_DS_SAM_INIT_FAILURE = 0X00002138;

    /**
     * Only an administrator can modify the membership list   of an administrative group.
     */
    public static final int ERROR_DS_SENSITIVE_GROUP_VIOLATION = 0X00002139;

    /**
     * Cannot change the primary group ID of a domain   controller account.
     */
    public static final int ERROR_DS_CANT_MOD_PRIMARYGROUPID = 0X0000213A;

    /**
     * An attempt was made to modify the base schema.
     */
    public static final int ERROR_DS_ILLEGAL_BASE_SCHEMA_MOD = 0X0000213B;

    /**
     * Adding a new mandatory attribute to an existing class,   deleting a mandatory attribute from an existing class, or adding an optional   attribute to the special class Top that is not a backlink attribute (directly
     * or through inheritance, for example, by adding or deleting an auxiliary   class) is not allowed.
     */
    public static final int ERROR_DS_NONSAFE_SCHEMA_CHANGE = 0X0000213C;

    /**
     * Schema update is not allowed on this DC because the DC   is not the schema FSMO role owner.
     */
    public static final int ERROR_DS_SCHEMA_UPDATE_DISALLOWED = 0X0000213D;

    /**
     * An object of this class cannot be created under the   schema container. You can only create Attribute-Schema and Class-Schema   objects under the schema container.
     */
    public static final int ERROR_DS_CANT_CREATE_UNDER_SCHEMA = 0X0000213E;

    /**
     * None
     */
    public static final int ERROR_DS_INSTALL_NO_SRC_SCH_VERSION = 0X0000213F;

    /**
     * None
     */
    public static final int ERROR_DS_INSTALL_NO_SCH_VERSION_IN_INIFILE = 0X00002140;

    /**
     * The specified group type is invalid.
     */
    public static final int ERROR_DS_INVALID_GROUP_TYPE = 0X00002141;

    /**
     * You cannot nest global groups in a mixed domain if the   group is security-enabled.
     */
    public static final int ERROR_DS_NO_NEST_GLOBALGROUP_IN_MIXEDDOMAIN = 0X00002142;

    /**
     * You cannot nest local groups in a mixed domain if the   group is security-enabled.
     */
    public static final int ERROR_DS_NO_NEST_LOCALGROUP_IN_MIXEDDOMAIN = 0X00002143;

    /**
     * A global group cannot have a local group as a member.
     */
    public static final int ERROR_DS_GLOBAL_CANT_HAVE_LOCAL_MEMBER = 0X00002144;

    /**
     * A global group cannot have a universal group as a   member.
     */
    public static final int ERROR_DS_GLOBAL_CANT_HAVE_UNIVERSAL_MEMBER = 0X00002145;

    /**
     * A universal group cannot have a local group as a   member.
     */
    public static final int ERROR_DS_UNIVERSAL_CANT_HAVE_LOCAL_MEMBER = 0X00002146;

    /**
     * A global group cannot have a cross-domain member.
     */
    public static final int ERROR_DS_GLOBAL_CANT_HAVE_CROSSDOMAIN_MEMBER = 0X00002147;

    /**
     * A local group cannot have another cross domain local   group as a member.
     */
    public static final int ERROR_DS_LOCAL_CANT_HAVE_CROSSDOMAIN_LOCAL_MEMBER = 0X00002148;

    /**
     * A group with primary members cannot change to a   security-disabled group.
     */
    public static final int ERROR_DS_HAVE_PRIMARY_MEMBERS = 0X00002149;

    /**
     * The schema cache load failed to convert the string   default security descriptor (SD) on a class-schema object.
     */
    public static final int ERROR_DS_STRING_SD_CONVERSION_FAILED = 0X0000214A;

    /**
     * Only DSAs configured to be GC servers should be   allowed to hold the domain naming master FSMO role. (Applies only to Windows   2000 servers.)
     */
    public static final int ERROR_DS_NAMING_MASTER_GC = 0X0000214B;

    /**
     * The DSA operation is unable to proceed because of a   DNS lookup failure.
     */
    public static final int ERROR_DS_DNS_LOOKUP_FAILURE = 0X0000214C;

    /**
     * While processing a change to the DNS host name for an   object, the SPN values could not be kept in sync.
     */
    public static final int ERROR_DS_COULDNT_UPDATE_SPNS = 0X0000214D;

    /**
     * The Security Descriptor attribute could not be read.
     */
    public static final int ERROR_DS_CANT_RETRIEVE_SD = 0X0000214E;

    /**
     * The object requested was not found, but an object with   that key was found.
     */
    public static final int ERROR_DS_KEY_NOT_UNIQUE = 0X0000214F;

    /**
     * The syntax of the linked attribute being added is   incorrect. Forward links can only have syntax 2.5.5.1, 2.5.5.7, and 2.5.5.14,   and backlinks can only have syntax 2.5.5.1.
     */
    public static final int ERROR_DS_WRONG_LINKED_ATT_SYNTAX = 0X00002150;

    /**
     * SAM needs to get the boot password.
     */
    public static final int ERROR_DS_SAM_NEED_BOOTKEY_PASSWORD = 0X00002151;

    /**
     * SAM needs to get the boot key from the floppy disk.
     */
    public static final int ERROR_DS_SAM_NEED_BOOTKEY_FLOPPY = 0X00002152;

    /**
     * Directory Service cannot start.
     */
    public static final int ERROR_DS_CANT_START = 0X00002153;

    /**
     * Directory Services could not start.
     */
    public static final int ERROR_DS_INIT_FAILURE = 0X00002154;

    /**
     * The connection between client and server requires   packet privacy or better.
     */
    public static final int ERROR_DS_NO_PKT_PRIVACY_ON_CONNECTION = 0X00002155;

    /**
     * The source domain cannot be in the same forest as the   destination.
     */
    public static final int ERROR_DS_SOURCE_DOMAIN_IN_FOREST = 0X00002156;

    /**
     * The destination domain MUST be in the forest.
     */
    public static final int ERROR_DS_DESTINATION_DOMAIN_NOT_IN_FOREST = 0X00002157;

    /**
     * The operation requires that destination domain   auditing be enabled.
     */
    public static final int ERROR_DS_DESTINATION_AUDITING_NOT_ENABLED = 0X00002158;

    /**
     * The operation could not locate a DC for the source   domain.
     */
    public static final int ERROR_DS_CANT_FIND_DC_FOR_SRC_DOMAIN = 0X00002159;

    /**
     * The source object must be a group or user.
     */
    public static final int ERROR_DS_SRC_OBJ_NOT_GROUP_OR_USER = 0X0000215A;

    /**
     * The source object\'s SID already exists in the   destination forest.
     */
    public static final int ERROR_DS_SRC_SID_EXISTS_IN_FOREST = 0X0000215B;

    /**
     * The source and destination object must be of the same   type.
     */
    public static final int ERROR_DS_SRC_AND_DST_OBJECT_CLASS_MISMATCH = 0X0000215C;

    /**
     * SAM initialization failed because of the following   error: %1. Error Status: 0x%2. Click OK to shut down the system and reboot   into Safe Mode. Check the event log for detailed information.
     */
    public static final int ERROR_SAM_INIT_FAILURE = 0X0000215D;

    /**
     * Schema information could not be included in the   replication request.
     */
    public static final int ERROR_DS_DRA_SCHEMA_INFO_SHIP = 0X0000215E;

    /**
     * The replication operation could not be completed due   to a schema incompatibility.
     */
    public static final int ERROR_DS_DRA_SCHEMA_CONFLICT = 0X0000215F;

    /**
     * The replication operation could not be completed due   to a previous schema incompatibility.
     */
    public static final int ERROR_DS_DRA_EARLIER_SCHEMA_CONFLICT = 0X00002160;

    /**
     * The replication update could not be applied because   either the source or the destination has not yet received information   regarding a recent cross-domain move operation.
     */
    public static final int ERROR_DS_DRA_OBJ_NC_MISMATCH = 0X00002161;

    /**
     * The requested domain could not be deleted because   there exist domain controllers that still host this domain.
     */
    public static final int ERROR_DS_NC_STILL_HAS_DSAS = 0X00002162;

    /**
     * The requested operation can be performed only on a GC   server.
     */
    public static final int ERROR_DS_GC_REQUIRED = 0X00002163;

    /**
     * A local group can only be a member of other local   groups in the same domain.
     */
    public static final int ERROR_DS_LOCAL_MEMBER_OF_LOCAL_ONLY = 0X00002164;

    /**
     * Foreign security principals cannot be members of   universal groups.
     */
    public static final int ERROR_DS_NO_FPO_IN_UNIVERSAL_GROUPS = 0X00002165;

    /**
     * The attribute is not allowed to be replicated to the   GC because of security reasons.
     */
    public static final int ERROR_DS_CANT_ADD_TO_GC = 0X00002166;

    /**
     * The checkpoint with the PDC could not be taken because   too many modifications are currently being processed.
     */
    public static final int ERROR_DS_NO_CHECKPOINT_WITH_PDC = 0X00002167;

    /**
     * The operation requires that source domain auditing be   enabled.
     */
    public static final int ERROR_DS_SOURCE_AUDITING_NOT_ENABLED = 0X00002168;

    /**
     * Security principal objects can only be created inside   domain naming contexts.
     */
    public static final int ERROR_DS_CANT_CREATE_IN_NONDOMAIN_NC = 0X00002169;

    /**
     * An SPN could not be constructed because the provided   host name is not in the necessary format.
     */
    public static final int ERROR_DS_INVALID_NAME_FOR_SPN = 0X0000216A;

    /**
     * A filter was passed that uses constructed attributes.
     */
    public static final int ERROR_DS_FILTER_USES_CONTRUCTED_ATTRS = 0X0000216B;

    /**
     * None
     */
    public static final int ERROR_DS_UNICODEPWD_NOT_IN_QUOTES = 0X0000216C;

    /**
     * Your computer could not be joined to the domain. You   have exceeded the maximum number of computer accounts you are allowed to   create in this domain. Contact your system administrator to have this limit   reset
     * or increased.
     */
    public static final int ERROR_DS_MACHINE_ACCOUNT_QUOTA_EXCEEDED = 0X0000216D;

    /**
     * For security reasons, the operation must be run on the   destination DC.
     */
    public static final int ERROR_DS_MUST_BE_RUN_ON_DST_DC = 0X0000216E;

    /**
     * For security reasons, the source DC must be NT4SP4 or   greater.
     */
    public static final int ERROR_DS_SRC_DC_MUST_BE_SP4_OR_GREATER = 0X0000216F;

    /**
     * Critical directory service system objects cannot be   deleted during tree deletion operations. The tree deletion might have been   partially performed.
     */
    public static final int ERROR_DS_CANT_TREE_DELETE_CRITICAL_OBJ = 0X00002170;

    /**
     * Directory Services could not start because of the   following error: %1. Error Status: 0x%2. Click OK to shut down the system.   You can use the Recovery Console to further diagnose the system.
     */
    public static final int ERROR_DS_INIT_FAILURE_CONSOLE = 0X00002171;

    /**
     * SAM initialization failed because of the following   error: %1. Error Status: 0x%2. Click OK to shut down the system. You can use   the Recovery Console to further diagnose the system.
     */
    public static final int ERROR_DS_SAM_INIT_FAILURE_CONSOLE = 0X00002172;

    /**
     * The version of the operating system installed is   incompatible with the current forest functional level. You must upgrade to a   new version of the operating system before this server can become a domain
     * controller in this forest.
     */
    public static final int ERROR_DS_FOREST_VERSION_TOO_HIGH = 0X00002173;

    /**
     * The version of the operating system installed is   incompatible with the current domain functional level. You must upgrade to a   new version of the operating system before this server can become a domain
     * controller in this domain.
     */
    public static final int ERROR_DS_DOMAIN_VERSION_TOO_HIGH = 0X00002174;

    /**
     * The version of the operating system installed on this   server no longer supports the current forest functional level. You must raise   the forest functional level before this server can become a domain controller
     * in this forest.
     */
    public static final int ERROR_DS_FOREST_VERSION_TOO_LOW = 0X00002175;

    /**
     * The version of the operating system installed on this   server no longer supports the current domain functional level. You must raise   the domain functional level before this server can become a domain controller
     * in this domain.
     */
    public static final int ERROR_DS_DOMAIN_VERSION_TOO_LOW = 0X00002176;

    /**
     * The version of the operating system installed on this   server is incompatible with the functional level of the domain or forest.
     */
    public static final int ERROR_DS_INCOMPATIBLE_VERSION = 0X00002177;

    /**
     * The functional level of the domain (or forest) cannot   be raised to the requested value because one or more domain controllers in   the domain (or forest) are at a lower, incompatible functional level.
     */
    public static final int ERROR_DS_LOW_DSA_VERSION = 0X00002178;

    /**
     * The forest functional level cannot be raised to the   requested value because one or more domains are still in mixed-domain mode.   All domains in the forest must be in native mode for you to raise the forest
     * functional level.
     */
    public static final int ERROR_DS_NO_BEHAVIOR_VERSION_IN_MIXEDDOMAIN = 0X00002179;

    /**
     * The sort order requested is not supported.
     */
    public static final int ERROR_DS_NOT_SUPPORTED_SORT_ORDER = 0X0000217A;

    /**
     * The requested name already exists as a unique   identifier.
     */
    public static final int ERROR_DS_NAME_NOT_UNIQUE = 0X0000217B;

    /**
     * The machine account was created before Windows NT 4.0.   The account needs to be re-created.
     */
    public static final int ERROR_DS_MACHINE_ACCOUNT_CREATED_PRENT4 = 0X0000217C;

    /**
     * The database is out of version store.
     */
    public static final int ERROR_DS_OUT_OF_VERSION_STORE = 0X0000217D;

    /**
     * Unable to continue operation because multiple   conflicting controls were used.
     */
    public static final int ERROR_DS_INCOMPATIBLE_CONTROLS_USED = 0X0000217E;

    /**
     * Unable to find a valid security descriptor reference   domain for this partition.
     */
    public static final int ERROR_DS_NO_REF_DOMAIN = 0X0000217F;

    /**
     * Schema update failed: The link identifier is reserved.
     */
    public static final int ERROR_DS_RESERVED_LINK_ID = 0X00002180;

    /**
     * Schema update failed: There are no link identifiers   available.
     */
    public static final int ERROR_DS_LINK_ID_NOT_AVAILABLE = 0X00002181;

    /**
     * An account group cannot have a universal group as a   member.
     */
    public static final int ERROR_DS_AG_CANT_HAVE_UNIVERSAL_MEMBER = 0X00002182;

    /**
     * Rename or move operations on naming context heads or   read-only objects are not allowed.
     */
    public static final int ERROR_DS_MODIFYDN_DISALLOWED_BY_INSTANCE_TYPE = 0X00002183;

    /**
     * Move operations on objects in the schema naming   context are not allowed.
     */
    public static final int ERROR_DS_NO_OBJECT_MOVE_IN_SCHEMA_NC = 0X00002184;

    /**
     * A system flag has been set on the object that does not   allow the object to be moved or renamed.
     */
    public static final int ERROR_DS_MODIFYDN_DISALLOWED_BY_FLAG = 0X00002185;

    /**
     * This object is not allowed to change its grandparent   container. Moves are not forbidden on this object, but are restricted to   sibling containers.
     */
    public static final int ERROR_DS_MODIFYDN_WRONG_GRANDPARENT = 0X00002186;

    /**
     * Unable to resolve completely; a referral to another   forest was generated.
     */
    public static final int ERROR_DS_NAME_ERROR_TRUST_REFERRAL = 0X00002187;

    /**
     * The requested action is not supported on a standard   server.
     */
    public static final int ERROR_NOT_SUPPORTED_ON_STANDARD_SERVER = 0X00002188;

    /**
     * Could not access a partition of the directory service   located on a remote server. Make sure at least one server is running for the   partition in question.
     */
    public static final int ERROR_DS_CANT_ACCESS_REMOTE_PART_OF_AD = 0X00002189;

    /**
     * The directory cannot validate the proposed naming   context (or partition) name because it does not hold a replica, nor can it   contact a replica of the naming context above the proposed naming context.   Ensure
     * that the parent naming context is properly registered in the DNS, and   at least one replica of this naming context is reachable by the domain naming   master.
     */
    public static final int ERROR_DS_CR_IMPOSSIBLE_TO_VALIDATE_V2 = 0X0000218A;

    /**
     * The thread limit for this request was exceeded.
     */
    public static final int ERROR_DS_THREAD_LIMIT_EXCEEDED = 0X0000218B;

    /**
     * The GC server is not in the closest site.
     */
    public static final int ERROR_DS_NOT_CLOSEST = 0X0000218C;

    /**
     * None
     */
    public static final int ERROR_DS_CANT_DERIVE_SPN_WITHOUT_SERVER_REF = 0X0000218D;

    /**
     * The directory service failed to enter single-user   mode.
     */
    public static final int ERROR_DS_SINGLE_USER_MODE_FAILED = 0X0000218E;

    /**
     * The directory service cannot parse the script because   of a syntax error.
     */
    public static final int ERROR_DS_NTDSCRIPT_SYNTAX_ERROR = 0X0000218F;

    /**
     * The directory service cannot process the script   because of an error.
     */
    public static final int ERROR_DS_NTDSCRIPT_PROCESS_ERROR = 0X00002190;

    /**
     * The directory service cannot perform the requested   operation because the servers involved are of different replication epochs   (which is usually related to a domain rename that is in progress).
     */
    public static final int ERROR_DS_DIFFERENT_REPL_EPOCHS = 0X00002191;

    /**
     * The directory service binding must be renegotiated due   to a change in the server extensions information.
     */
    public static final int ERROR_DS_DRS_EXTENSIONS_CHANGED = 0X00002192;

    /**
     * The operation is not allowed on a disabled   cross-reference.
     */
    public static final int ERROR_DS_REPLICA_SET_CHANGE_NOT_ALLOWED_ON_DISABLED_CR = 0X00002193;

    /**
     * Schema update failed: No values for msDS-IntId are   available.
     */
    public static final int ERROR_DS_NO_MSDS_INTID = 0X00002194;

    /**
     * Schema update failed: Duplicate msDS-IntId. Retry the   operation.
     */
    public static final int ERROR_DS_DUP_MSDS_INTID = 0X00002195;

    /**
     * Schema deletion failed: Attribute is used in rDNAttID.
     */
    public static final int ERROR_DS_EXISTS_IN_RDNATTID = 0X00002196;

    /**
     * The directory service failed to authorize the request.
     */
    public static final int ERROR_DS_AUTHORIZATION_FAILED = 0X00002197;

    /**
     * The directory service cannot process the script   because it is invalid.
     */
    public static final int ERROR_DS_INVALID_SCRIPT = 0X00002198;

    /**
     * The remote create cross-reference operation failed on   the domain naming master FSMO. The operation\'s error is in the extended data.
     */
    public static final int ERROR_DS_REMOTE_CROSSREF_OP_FAILED = 0X00002199;

    /**
     * A cross-reference is in use locally with the same   name.
     */
    public static final int ERROR_DS_CROSS_REF_BUSY = 0X0000219A;

    /**
     * The directory service cannot derive an SPN with which   to mutually authenticate the target server because the server\'s domain has   been deleted from the forest.
     */
    public static final int ERROR_DS_CANT_DERIVE_SPN_FOR_DELETED_DOMAIN = 0X0000219B;

    /**
     * Writable NCs prevent this DC from demoting.
     */
    public static final int ERROR_DS_CANT_DEMOTE_WITH_WRITEABLE_NC = 0X0000219C;

    /**
     * The requested object has a nonunique identifier and   cannot be retrieved.
     */
    public static final int ERROR_DS_DUPLICATE_ID_FOUND = 0X0000219D;

    /**
     * Insufficient attributes were given to create an   object. This object might not exist because it might have been deleted and   the garbage already collected.
     */
    public static final int ERROR_DS_INSUFFICIENT_ATTR_TO_CREATE_OBJECT = 0X0000219E;

    /**
     * The group cannot be converted due to attribute   restrictions on the requested group type.
     */
    public static final int ERROR_DS_GROUP_CONVERSION_ERROR = 0X0000219F;

    /**
     * Cross-domain moves of nonempty basic application   groups is not allowed.
     */
    public static final int ERROR_DS_CANT_MOVE_APP_BASIC_GROUP = 0X000021A0;

    /**
     * Cross-domain moves of nonempty query-based application   groups is not allowed.
     */
    public static final int ERROR_DS_CANT_MOVE_APP_QUERY_GROUP = 0X000021A1;

    /**
     * The FSMO role ownership could not be verified because   its directory partition did not replicate successfully with at least one   replication partner.
     */
    public static final int ERROR_DS_ROLE_NOT_VERIFIED = 0X000021A2;

    /**
     * The target container for a redirection of a well-known   object container cannot already be a special container.
     */
    public static final int ERROR_DS_WKO_CONTAINER_CANNOT_BE_SPECIAL = 0X000021A3;

    /**
     * The directory service cannot perform the requested   operation because a domain rename operation is in progress.
     */
    public static final int ERROR_DS_DOMAIN_RENAME_IN_PROGRESS = 0X000021A4;

    /**
     * The directory service detected a child partition below   the requested partition name. The partition hierarchy must be created in a   top down method.
     */
    public static final int ERROR_DS_EXISTING_AD_CHILD_NC = 0X000021A5;

    /**
     * The directory service cannot replicate with this   server because the time since the last replication with this server has   exceeded the tombstone lifetime.
     */
    public static final int ERROR_DS_REPL_LIFETIME_EXCEEDED = 0X000021A6;

    /**
     * The requested operation is not allowed on an object   under the system container.
     */
    public static final int ERROR_DS_DISALLOWED_IN_SYSTEM_CONTAINER = 0X000021A7;

    /**
     * The LDAP server\'s network send queue has filled up   because the client is not processing the results of its requests fast enough.   No more requests will be processed until the client catches up. If the client
     * does not catch up then it will be disconnected.
     */
    public static final int ERROR_DS_LDAP_SEND_QUEUE_FULL = 0X000021A8;

    /**
     * The scheduled replication did not take place because   the system was too busy to execute the request within the schedule window.   The replication queue is overloaded. Consider reducing the number of partners or
     * decreasing the scheduled replication frequency.
     */
    public static final int ERROR_DS_DRA_OUT_SCHEDULE_WINDOW = 0X000021A9;

    /**
     * At this time, it cannot be determined if the branch   replication policy is available on the hub domain controller. Retry at a   later time to account for replication latencies.
     */
    public static final int ERROR_DS_POLICY_NOT_KNOWN = 0X000021AA;

    /**
     * The site settings object for the specified site does   not exist.
     */
    public static final int ERROR_NO_SITE_SETTINGS_OBJECT = 0X000021AB;

    /**
     * The local account store does not contain secret   material for the specified account.
     */
    public static final int ERROR_NO_SECRETS = 0X000021AC;

    /**
     * Could not find a writable domain controller in the domain.
     */
    public static final int ERROR_NO_WRITABLE_DC_FOUND = 0X000021AD;

    /**
     * The server object for the domain controller does not   exist.
     */
    public static final int ERROR_DS_NO_SERVER_OBJECT = 0X000021AE;

    /**
     * The NTDS Settings object for the domain controller   does not exist.
     */
    public static final int ERROR_DS_NO_NTDSA_OBJECT = 0X000021AF;

    /**
     * The requested search operation is not supported for   attribute scoped query (ASQ) searches.
     */
    public static final int ERROR_DS_NON_ASQ_SEARCH = 0X000021B0;

    /**
     * A required audit event could not be generated for the   operation.
     */
    public static final int ERROR_DS_AUDIT_FAILURE = 0X000021B1;

    /**
     * The search flags for the attribute are invalid. The   subtree index bit is valid only on single-valued attributes.
     */
    public static final int ERROR_DS_INVALID_SEARCH_FLAG_SUBTREE = 0X000021B2;

    /**
     * The search flags for the attribute are invalid. The   tuple index bit is valid only on attributes of Unicode strings.
     */
    public static final int ERROR_DS_INVALID_SEARCH_FLAG_TUPLE = 0X000021B3;

    /**
     * The replication operation failed because the target   object referenced by a link value is recycled.
     */
    public static final int ERROR_DS_DRA_RECYCLED_TARGET = 0X000021BF;

    /**
     * The functional level of the domain (or forest) cannot   be lowered to the requested value.
     */
    public static final int ERROR_DS_HIGH_DSA_VERSION = 0X000021C2;

    /**
     * The operation failed because the SPN value provided   for addition/modification is not unique forest-wide.
     */
    public static final int ERROR_DS_SPN_VALUE_NOT_UNIQUE_IN_FOREST = 0X000021C7;

    /**
     * The operation failed because the UPN value provided   for addition/modification is not unique forest-wide.
     */
    public static final int ERROR_DS_UPN_VALUE_NOT_UNIQUE_IN_FOREST = 0X000021C8;

    /**
     * DNS server unable to interpret format.
     */
    public static final int DNS_ERROR_RCODE_FORMAT_ERROR = 0X00002329;

    /**
     * DNS server failure.
     */
    public static final int DNS_ERROR_RCODE_SERVER_FAILURE = 0X0000232A;

    /**
     * DNS name does not exist.
     */
    public static final int DNS_ERROR_RCODE_NAME_ERROR = 0X0000232B;

    /**
     * DNS request not supported by name server.
     */
    public static final int DNS_ERROR_RCODE_NOT_IMPLEMENTED = 0X0000232C;

    /**
     * DNS operation refused.
     */
    public static final int DNS_ERROR_RCODE_REFUSED = 0X0000232D;

    /**
     * DNS name that should not exist, does exist.
     */
    public static final int DNS_ERROR_RCODE_YXDOMAIN = 0X0000232E;

    /**
     * DNS resource record (RR) set that should not exist,   does exist.
     */
    public static final int DNS_ERROR_RCODE_YXRRSET = 0X0000232F;

    /**
     * DNS RR set that should to exist, does not exist.
     */
    public static final int DNS_ERROR_RCODE_NXRRSET = 0X00002330;

    /**
     * DNS server not authoritative for zone.
     */
    public static final int DNS_ERROR_RCODE_NOTAUTH = 0X00002331;

    /**
     * DNS name in update or prereq is not in zone.
     */
    public static final int DNS_ERROR_RCODE_NOTZONE = 0X00002332;

    /**
     * DNS signature failed to verify.
     */
    public static final int DNS_ERROR_RCODE_BADSIG = 0X00002338;

    /**
     * DNS bad key.
     */
    public static final int DNS_ERROR_RCODE_BADKEY = 0X00002339;

    /**
     * DNS signature validity expired.
     */
    public static final int DNS_ERROR_RCODE_BADTIME = 0X0000233A;

    /**
     * No records found for given DNS query.
     */
    public static final int DNS_INFO_NO_RECORDS = 0X0000251D;

    /**
     * Bad DNS packet.
     */
    public static final int DNS_ERROR_BAD_PACKET = 0X0000251E;

    /**
     * No DNS packet.
     */
    public static final int DNS_ERROR_NO_PACKET = 0X0000251F;

    /**
     * DNS error, check rcode.
     */
    public static final int DNS_ERROR_RCODE = 0X00002520;

    /**
     * Unsecured DNS packet.
     */
    public static final int DNS_ERROR_UNSECURE_PACKET = 0X00002521;

    /**
     * Invalid DNS type.
     */
    public static final int DNS_ERROR_INVALID_TYPE = 0X0000254F;

    /**
     * Invalid IP address.
     */
    public static final int DNS_ERROR_INVALID_IP_ADDRESS = 0X00002550;

    /**
     * Invalid property.
     */
    public static final int DNS_ERROR_INVALID_PROPERTY = 0X00002551;

    /**
     * Try DNS operation again later.
     */
    public static final int DNS_ERROR_TRY_AGAIN_LATER = 0X00002552;

    /**
     * Record for given name and type is not unique.
     */
    public static final int DNS_ERROR_NOT_UNIQUE = 0X00002553;

    /**
     * DNS name does not comply with RFC specifications.
     */
    public static final int DNS_ERROR_NON_RFC_NAME = 0X00002554;

    /**
     * DNS name is a fully qualified DNS name.
     */
    public static final int DNS_STATUS_FQDN = 0X00002555;

    /**
     * DNS name is dotted (multilabel).
     */
    public static final int DNS_STATUS_DOTTED_NAME = 0X00002556;

    /**
     * DNS name is a single-part name.
     */
    public static final int DNS_STATUS_SINGLE_PART_NAME = 0X00002557;

    /**
     * DNS name contains an invalid character.
     */
    public static final int DNS_ERROR_INVALID_NAME_CHAR = 0X00002558;

    /**
     * DNS name is entirely numeric.
     */
    public static final int DNS_ERROR_NUMERIC_NAME = 0X00002559;

    /**
     * The operation requested is not permitted on a DNS root   server.
     */
    public static final int DNS_ERROR_NOT_ALLOWED_ON_ROOT_SERVER = 0X0000255A;

    /**
     * The record could not be created because this part of   the DNS namespace has been delegated to another server.
     */
    public static final int DNS_ERROR_NOT_ALLOWED_UNDER_DELEGATION = 0X0000255B;

    /**
     * The DNS server could not find a set of root hints.
     */
    public static final int DNS_ERROR_CANNOT_FIND_ROOT_HINTS = 0X0000255C;

    /**
     * The DNS server found root hints but they were not   consistent across all adapters.
     */
    public static final int DNS_ERROR_INCONSISTENT_ROOT_HINTS = 0X0000255D;

    /**
     * The specified value is too small for this parameter.
     */
    public static final int DNS_ERROR_DWORD_VALUE_TOO_SMALL = 0X0000255E;

    /**
     * The specified value is too large for this parameter.
     */
    public static final int DNS_ERROR_DWORD_VALUE_TOO_LARGE = 0X0000255F;

    /**
     * This operation is not allowed while the DNS server is   loading zones in the background. Try again later.
     */
    public static final int DNS_ERROR_BACKGROUND_LOADING = 0X00002560;

    /**
     * The operation requested is not permitted on against a   DNS server running on a read-only DC.
     */
    public static final int DNS_ERROR_NOT_ALLOWED_ON_RODC = 0X00002561;

    /**
     * DNS zone does not exist.
     */
    public static final int DNS_ERROR_ZONE_DOES_NOT_EXIST = 0X00002581;

    /**
     * DNS zone information not available.
     */
    public static final int DNS_ERROR_NO_ZONE_INFO = 0X00002582;

    /**
     * Invalid operation for DNS zone.
     */
    public static final int DNS_ERROR_INVALID_ZONE_OPERATION = 0X00002583;

    /**
     * Invalid DNS zone configuration.
     */
    public static final int DNS_ERROR_ZONE_CONFIGURATION_ERROR = 0X00002584;

    /**
     * DNS zone has no start of authority (SOA) record.
     */
    public static final int DNS_ERROR_ZONE_HAS_NO_SOA_RECORD = 0X00002585;

    /**
     * DNS zone has no Name Server (NS) record.
     */
    public static final int DNS_ERROR_ZONE_HAS_NO_NS_RECORDS = 0X00002586;

    /**
     * DNS zone is locked.
     */
    public static final int DNS_ERROR_ZONE_LOCKED = 0X00002587;

    /**
     * DNS zone creation failed.
     */
    public static final int DNS_ERROR_ZONE_CREATION_FAILED = 0X00002588;

    /**
     * DNS zone already exists.
     */
    public static final int DNS_ERROR_ZONE_ALREADY_EXISTS = 0X00002589;

    /**
     * DNS automatic zone already exists.
     */
    public static final int DNS_ERROR_AUTOZONE_ALREADY_EXISTS = 0X0000258A;

    /**
     * Invalid DNS zone type.
     */
    public static final int DNS_ERROR_INVALID_ZONE_TYPE = 0X0000258B;

    /**
     * Secondary DNS zone requires master IP address.
     */
    public static final int DNS_ERROR_SECONDARY_REQUIRES_MASTER_IP = 0X0000258C;

    /**
     * DNS zone not secondary.
     */
    public static final int DNS_ERROR_ZONE_NOT_SECONDARY = 0X0000258D;

    /**
     * Need secondary IP address.
     */
    public static final int DNS_ERROR_NEED_SECONDARY_ADDRESSES = 0X0000258E;

    /**
     * WINS initialization failed.
     */
    public static final int DNS_ERROR_WINS_INIT_FAILED = 0X0000258F;

    /**
     * Need WINS servers.
     */
    public static final int DNS_ERROR_NEED_WINS_SERVERS = 0X00002590;

    /**
     * NBTSTAT initialization call failed.
     */
    public static final int DNS_ERROR_NBSTAT_INIT_FAILED = 0X00002591;

    /**
     * Invalid delete of SOA.
     */
    public static final int DNS_ERROR_SOA_DELETE_INVALID = 0X00002592;

    /**
     * A conditional forwarding zone already exists for that   name.
     */
    public static final int DNS_ERROR_FORWARDER_ALREADY_EXISTS = 0X00002593;

    /**
     * This zone must be configured with one or more master   DNS server IP addresses.
     */
    public static final int DNS_ERROR_ZONE_REQUIRES_MASTER_IP = 0X00002594;

    /**
     * The operation cannot be performed because this zone is   shut down.
     */
    public static final int DNS_ERROR_ZONE_IS_SHUTDOWN = 0X00002595;

    /**
     * The primary DNS zone requires a data file.
     */
    public static final int DNS_ERROR_PRIMARY_REQUIRES_DATAFILE = 0X000025B3;

    /**
     * Invalid data file name for the DNS zone.
     */
    public static final int DNS_ERROR_INVALID_DATAFILE_NAME = 0X000025B4;

    /**
     * Failed to open the data file for the DNS zone.
     */
    public static final int DNS_ERROR_DATAFILE_OPEN_FAILURE = 0X000025B5;

    /**
     * Failed to write the data file for the DNS zone.
     */
    public static final int DNS_ERROR_FILE_WRITEBACK_FAILED = 0X000025B6;

    /**
     * Failure while reading datafile for DNS zone.
     */
    public static final int DNS_ERROR_DATAFILE_PARSING = 0X000025B7;

    /**
     * DNS record does not exist.
     */
    public static final int DNS_ERROR_RECORD_DOES_NOT_EXIST = 0X000025E5;

    /**
     * DNS record format error.
     */
    public static final int DNS_ERROR_RECORD_FORMAT = 0X000025E6;

    /**
     * Node creation failure in DNS.
     */
    public static final int DNS_ERROR_NODE_CREATION_FAILED = 0X000025E7;

    /**
     * Unknown DNS record type.
     */
    public static final int DNS_ERROR_UNKNOWN_RECORD_TYPE = 0X000025E8;

    /**
     * DNS record timed out.
     */
    public static final int DNS_ERROR_RECORD_TIMED_OUT = 0X000025E9;

    /**
     * Name not in DNS zone.
     */
    public static final int DNS_ERROR_NAME_NOT_IN_ZONE = 0X000025EA;

    /**
     * CNAME loop detected.
     */
    public static final int DNS_ERROR_CNAME_LOOP = 0X000025EB;

    /**
     * Node is a CNAME DNS record.
     */
    public static final int DNS_ERROR_NODE_IS_CNAME = 0X000025EC;

    /**
     * A CNAME record already exists for the given name.
     */
    public static final int DNS_ERROR_CNAME_COLLISION = 0X000025ED;

    /**
     * Record is only at DNS zone root.
     */
    public static final int DNS_ERROR_RECORD_ONLY_AT_ZONE_ROOT = 0X000025EE;

    /**
     * DNS record already exists.
     */
    public static final int DNS_ERROR_RECORD_ALREADY_EXISTS = 0X000025EF;

    /**
     * Secondary DNS zone data error.
     */
    public static final int DNS_ERROR_SECONDARY_DATA = 0X000025F0;

    /**
     * Could not create DNS cache data.
     */
    public static final int DNS_ERROR_NO_CREATE_CACHE_DATA = 0X000025F1;

    /**
     * DNS name does not exist.
     */
    public static final int DNS_ERROR_NAME_DOES_NOT_EXIST = 0X000025F2;

    /**
     * Could not create pointer (PTR) record.
     */
    public static final int DNS_WARNING_PTR_CREATE_FAILED = 0X000025F3;

    /**
     * DNS domain was undeleted.
     */
    public static final int DNS_WARNING_DOMAIN_UNDELETED = 0X000025F4;

    /**
     * The directory service is unavailable.
     */
    public static final int DNS_ERROR_DS_UNAVAILABLE = 0X000025F5;

    /**
     * DNS zone already exists in the directory service.
     */
    public static final int DNS_ERROR_DS_ZONE_ALREADY_EXISTS = 0X000025F6;

    /**
     * DNS server not creating or reading the boot file for   the directory service integrated DNS zone.
     */
    public static final int DNS_ERROR_NO_BOOTFILE_IF_DS_ZONE = 0X000025F7;

    /**
     * DNS AXFR (zone transfer) complete.
     */
    public static final int DNS_INFO_AXFR_COMPLETE = 0X00002617;

    /**
     * DNS zone transfer failed.
     */
    public static final int DNS_ERROR_AXFR = 0X00002618;

    /**
     * Added local WINS server.
     */
    public static final int DNS_INFO_ADDED_LOCAL_WINS = 0X00002619;

    /**
     * Secure update call needs to continue update request.
     */
    public static final int DNS_STATUS_CONTINUE_NEEDED = 0X00002649;

    /**
     * TCP/IP network protocol not installed.
     */
    public static final int DNS_ERROR_NO_TCPIP = 0X0000267B;

    /**
     * No DNS servers configured for local system.
     */
    public static final int DNS_ERROR_NO_DNS_SERVERS = 0X0000267C;

    /**
     * The specified directory partition does not exist.
     */
    public static final int DNS_ERROR_DP_DOES_NOT_EXIST = 0X000026AD;

    /**
     * The specified directory partition already exists.
     */
    public static final int DNS_ERROR_DP_ALREADY_EXISTS = 0X000026AE;

    /**
     * This DNS server is not enlisted in the specified   directory partition.
     */
    public static final int DNS_ERROR_DP_NOT_ENLISTED = 0X000026AF;

    /**
     * This DNS server is already enlisted in the specified   directory partition.
     */
    public static final int DNS_ERROR_DP_ALREADY_ENLISTED = 0X000026B0;

    /**
     * The directory partition is not available at this time.   Wait a few minutes and try again.
     */
    public static final int DNS_ERROR_DP_NOT_AVAILABLE = 0X000026B1;

    /**
     * The application directory partition operation failed.   The domain controller holding the domain naming master role is down or unable   to service the request or is not running Windows Server 2003.
     */
    public static final int DNS_ERROR_DP_FSMO_ERROR = 0X000026B2;

    /**
     * A blocking operation was interrupted by a call to   WSACancelBlockingCall.
     */
    public static final int WSAEINTR = 0X00002714;

    /**
     * The file handle supplied is not valid.
     */
    public static final int WSAEBADF = 0X00002719;

    /**
     * An attempt was made to access a socket in a way   forbidden by its access permissions.
     */
    public static final int WSAEACCES = 0X0000271D;

    /**
     * The system detected an invalid pointer address in   attempting to use a pointer argument in a call.
     */
    public static final int WSAEFAULT = 0X0000271E;

    /**
     * An invalid argument was supplied.
     */
    public static final int WSAEINVAL = 0X00002726;

    /**
     * Too many open sockets.
     */
    public static final int WSAEMFILE = 0X00002728;

    /**
     * A nonblocking socket operation could not be completed   immediately.
     */
    public static final int WSAEWOULDBLOCK = 0X00002733;

    /**
     * A blocking operation is currently executing.
     */
    public static final int WSAEINPROGRESS = 0X00002734;

    /**
     * An operation was attempted on a nonblocking socket   that already had an operation in progress.
     */
    public static final int WSAEALREADY = 0X00002735;

    /**
     * An operation was attempted on something that is not a   socket.
     */
    public static final int WSAENOTSOCK = 0X00002736;

    /**
     * A required address was omitted from an operation on a   socket.
     */
    public static final int WSAEDESTADDRREQ = 0X00002737;

    /**
     * A message sent on a datagram socket was larger than   the internal message buffer or some other network limit, or the buffer used   to receive a datagram into was smaller than the datagram itself.
     */
    public static final int WSAEMSGSIZE = 0X00002738;

    /**
     * A protocol was specified in the socket function call   that does not support the semantics of the socket type requested.
     */
    public static final int WSAEPROTOTYPE = 0X00002739;

    /**
     * An unknown, invalid, or unsupported option or level   was specified in a getsockopt or setsockopt call.
     */
    public static final int WSAENOPROTOOPT = 0X0000273A;

    /**
     * The requested protocol has not been configured into   the system, or no implementation for it exists.
     */
    public static final int WSAEPROTONOSUPPORT = 0X0000273B;

    /**
     * The support for the specified socket type does not   exist in this address family.
     */
    public static final int WSAESOCKTNOSUPPORT = 0X0000273C;

    /**
     * The attempted operation is not supported for the type   of object referenced.
     */
    public static final int WSAEOPNOTSUPP = 0X0000273D;

    /**
     * The protocol family has not been configured into the   system or no implementation for it exists.
     */
    public static final int WSAEPFNOSUPPORT = 0X0000273E;

    /**
     * An address incompatible with the requested protocol   was used.
     */
    public static final int WSAEAFNOSUPPORT = 0X0000273F;

    /**
     * Only one usage of each socket address   (protocol/network address/port) is normally permitted.
     */
    public static final int WSAEADDRINUSE = 0X00002740;

    /**
     * The requested address is not valid in its context.
     */
    public static final int WSAEADDRNOTAVAIL = 0X00002741;

    /**
     * A socket operation encountered a dead network.
     */
    public static final int WSAENETDOWN = 0X00002742;

    /**
     * A socket operation was attempted to an unreachable   network.
     */
    public static final int WSAENETUNREACH = 0X00002743;

    /**
     * The connection has been broken due to keep-alive   activity detecting a failure while the operation was in progress.
     */
    public static final int WSAENETRESET = 0X00002744;

    /**
     * An established connection was aborted by the software   in your host machine.
     */
    public static final int WSAECONNABORTED = 0X00002745;

    /**
     * An existing connection was forcibly closed by the   remote host.
     */
    public static final int WSAECONNRESET = 0X00002746;

    /**
     * An operation on a socket could not be performed   because the system lacked sufficient buffer space or because a queue was   full.
     */
    public static final int WSAENOBUFS = 0X00002747;

    /**
     * A connect request was made on an already connected   socket.
     */
    public static final int WSAEISCONN = 0X00002748;

    /**
     * A request to send or receive data was disallowed   because the socket is not connected and (when sending on a datagram socket   using a sendto call) no address was supplied.
     */
    public static final int WSAENOTCONN = 0X00002749;

    /**
     * A request to send or receive data was disallowed   because the socket had already been shut down in that direction with a   previous shutdown call.
     */
    public static final int WSAESHUTDOWN = 0X0000274A;

    /**
     * Too many references to a kernel object.
     */
    public static final int WSAETOOMANYREFS = 0X0000274B;

    /**
     * A connection attempt failed because the connected party   did not properly respond after a period of time, or the established   connection failed because the connected host failed to respond.
     */
    public static final int WSAETIMEDOUT = 0X0000274C;

    /**
     * No connection could be made because the target machine   actively refused it.
     */
    public static final int WSAECONNREFUSED = 0X0000274D;

    /**
     * Cannot translate name.
     */
    public static final int WSAELOOP = 0X0000274E;

    /**
     * Name or name component was too long.
     */
    public static final int WSAENAMETOOLONG = 0X0000274F;

    /**
     * A socket operation failed because the destination host   was down.
     */
    public static final int WSAEHOSTDOWN = 0X00002750;

    /**
     * A socket operation was attempted to an unreachable   host.
     */
    public static final int WSAEHOSTUNREACH = 0X00002751;

    /**
     * Cannot remove a directory that is not empty.
     */
    public static final int WSAENOTEMPTY = 0X00002752;

    /**
     * A Windows Sockets implementation might have a limit on   the number of applications that can use it simultaneously.
     */
    public static final int WSAEPROCLIM = 0X00002753;

    /**
     * Ran out of quota.
     */
    public static final int WSAEUSERS = 0X00002754;

    /**
     * Ran out of disk quota.
     */
    public static final int WSAEDQUOT = 0X00002755;

    /**
     * File handle reference is no longer available.
     */
    public static final int WSAESTALE = 0X00002756;

    /**
     * Item is not available locally.
     */
    public static final int WSAEREMOTE = 0X00002757;

    /**
     * WSAStartup cannot function at this time because the underlying   system it uses to provide network services is currently unavailable.
     */
    public static final int WSASYSNOTREADY = 0X0000276B;

    /**
     * The Windows Sockets version requested is not   supported.
     */
    public static final int WSAVERNOTSUPPORTED = 0X0000276C;

    /**
     * Either the application has not called WSAStartup, or   WSAStartup failed.
     */
    public static final int WSANOTINITIALISED = 0X0000276D;

    /**
     * Returned by WSARecv or WSARecvFrom to indicate that   the remote party has initiated a graceful shutdown sequence.
     */
    public static final int WSAEDISCON = 0X00002775;

    /**
     * No more results can be returned by   WSALookupServiceNext.
     */
    public static final int WSAENOMORE = 0X00002776;

    /**
     * A call to WSALookupServiceEnd was made while this call   was still processing. The call has been canceled.
     */
    public static final int WSAECANCELLED = 0X00002777;

    /**
     * The procedure call table is invalid.
     */
    public static final int WSAEINVALIDPROCTABLE = 0X00002778;

    /**
     * The requested service provider is invalid.
     */
    public static final int WSAEINVALIDPROVIDER = 0X00002779;

    /**
     * The requested service provider could not be loaded or   initialized.
     */
    public static final int WSAEPROVIDERFAILEDINIT = 0X0000277A;

    /**
     * A system call that should never fail has failed.
     */
    public static final int WSASYSCALLFAILURE = 0X0000277B;

    /**
     * No such service is known. The service cannot be found   in the specified namespace.
     */
    public static final int WSASERVICE_NOT_FOUND = 0X0000277C;

    /**
     * The specified class was not found.
     */
    public static final int WSATYPE_NOT_FOUND = 0X0000277D;

    /**
     * No more results can be returned by   WSALookupServiceNext.
     */
    public static final int WSA_E_NO_MORE = 0X0000277E;

    /**
     * A call to WSALookupServiceEnd was made while this call   was still processing. The call has been canceled.
     */
    public static final int WSA_E_CANCELLED = 0X0000277F;

    /**
     * A database query failed because it was actively   refused.
     */
    public static final int WSAEREFUSED = 0X00002780;

    /**
     * No such host is known.
     */
    public static final int WSAHOST_NOT_FOUND = 0X00002AF9;

    /**
     * This is usually a temporary error during host name   resolution and means that the local server did not receive a response from an   authoritative server.
     */
    public static final int WSATRY_AGAIN = 0X00002AFA;

    /**
     * A nonrecoverable error occurred during a database   lookup.
     */
    public static final int WSANO_RECOVERY = 0X00002AFB;

    /**
     * The requested name is valid, but no data of the   requested type was found.
     */
    public static final int WSANO_DATA = 0X00002AFC;

    /**
     * At least one reserve has arrived.
     */
    public static final int WSA_QOS_RECEIVERS = 0X00002AFD;

    /**
     * At least one path has arrived.
     */
    public static final int WSA_QOS_SENDERS = 0X00002AFE;

    /**
     * There are no senders.
     */
    public static final int WSA_QOS_NO_SENDERS = 0X00002AFF;

    /**
     * There are no receivers.
     */
    public static final int WSA_QOS_NO_RECEIVERS = 0X00002B00;

    /**
     * Reserve has been confirmed.
     */
    public static final int WSA_QOS_REQUEST_CONFIRMED = 0X00002B01;

    /**
     * Error due to lack of resources.
     */
    public static final int WSA_QOS_ADMISSION_FAILURE = 0X00002B02;

    /**
     * Rejected for administrative reasons - bad credentials.
     */
    public static final int WSA_QOS_POLICY_FAILURE = 0X00002B03;

    /**
     * Unknown or conflicting style.
     */
    public static final int WSA_QOS_BAD_STYLE = 0X00002B04;

    /**
     * There is a problem with some part of the filterspec or   provider-specific buffer in general.
     */
    public static final int WSA_QOS_BAD_OBJECT = 0X00002B05;

    /**
     * There is a problem with some part of the flowspec.
     */
    public static final int WSA_QOS_TRAFFIC_CTRL_ERROR = 0X00002B06;

    /**
     * General quality of serve (QOS) error.
     */
    public static final int WSA_QOS_GENERIC_ERROR = 0X00002B07;

    /**
     * An invalid or unrecognized service type was found in   the flowspec.
     */
    public static final int WSA_QOS_ESERVICETYPE = 0X00002B08;

    /**
     * An invalid or inconsistent flowspec was found in the   QOS structure.
     */
    public static final int WSA_QOS_EFLOWSPEC = 0X00002B09;

    /**
     * Invalid QOS provider-specific buffer.
     */
    public static final int WSA_QOS_EPROVSPECBUF = 0X00002B0A;

    /**
     * An invalid QOS filter style was used.
     */
    public static final int WSA_QOS_EFILTERSTYLE = 0X00002B0B;

    /**
     * An invalid QOS filter type was used.
     */
    public static final int WSA_QOS_EFILTERTYPE = 0X00002B0C;

    /**
     * An incorrect number of QOS FILTERSPECs were specified   in the FLOWDESCRIPTOR.
     */
    public static final int WSA_QOS_EFILTERCOUNT = 0X00002B0D;

    /**
     * None
     */
    public static final int WSA_QOS_EOBJLENGTH = 0X00002B0E;

    /**
     * An incorrect number of flow descriptors was specified   in the QOS structure.
     */
    public static final int WSA_QOS_EFLOWCOUNT = 0X00002B0F;

    /**
     * An unrecognized object was found in the QOS   provider-specific buffer.
     */
    public static final int WSA_QOS_EUNKOWNPSOBJ = 0X00002B10;

    /**
     * An invalid policy object was found in the QOS provider-specific   buffer.
     */
    public static final int WSA_QOS_EPOLICYOBJ = 0X00002B11;

    /**
     * An invalid QOS flow descriptor was found in the flow   descriptor list.
     */
    public static final int WSA_QOS_EFLOWDESC = 0X00002B12;

    /**
     * An invalid or inconsistent flowspec was found in the   QOS provider-specific buffer.
     */
    public static final int WSA_QOS_EPSFLOWSPEC = 0X00002B13;

    /**
     * An invalid FILTERSPEC was found in the QOS   provider-specific buffer.
     */
    public static final int WSA_QOS_EPSFILTERSPEC = 0X00002B14;

    /**
     * An invalid shape discard mode object was found in the   QOS provider-specific buffer.
     */
    public static final int WSA_QOS_ESDMODEOBJ = 0X00002B15;

    /**
     * An invalid shaping rate object was found in the QOS   provider-specific buffer.
     */
    public static final int WSA_QOS_ESHAPERATEOBJ = 0X00002B16;

    /**
     * A reserved policy element was found in the QOS   provider-specific buffer.
     */
    public static final int WSA_QOS_RESERVED_PETYPE = 0X00002B17;

    /**
     * The specified quick mode policy already exists.
     */
    public static final int ERROR_IPSEC_QM_POLICY_EXISTS = 0X000032C8;

    /**
     * The specified quick mode policy was not found.
     */
    public static final int ERROR_IPSEC_QM_POLICY_NOT_FOUND = 0X000032C9;

    /**
     * The specified quick mode policy is being used.
     */
    public static final int ERROR_IPSEC_QM_POLICY_IN_USE = 0X000032CA;

    /**
     * The specified main mode policy already exists.
     */
    public static final int ERROR_IPSEC_MM_POLICY_EXISTS = 0X000032CB;

    /**
     * The specified main mode policy was not found.
     */
    public static final int ERROR_IPSEC_MM_POLICY_NOT_FOUND = 0X000032CC;

    /**
     * The specified main mode policy is being used.
     */
    public static final int ERROR_IPSEC_MM_POLICY_IN_USE = 0X000032CD;

    /**
     * The specified main mode filter already exists.
     */
    public static final int ERROR_IPSEC_MM_FILTER_EXISTS = 0X000032CE;

    /**
     * The specified main mode filter was not found.
     */
    public static final int ERROR_IPSEC_MM_FILTER_NOT_FOUND = 0X000032CF;

    /**
     * The specified transport mode filter already exists.
     */
    public static final int ERROR_IPSEC_TRANSPORT_FILTER_EXISTS = 0X000032D0;

    /**
     * The specified transport mode filter does not exist.
     */
    public static final int ERROR_IPSEC_TRANSPORT_FILTER_NOT_FOUND = 0X000032D1;

    /**
     * The specified main mode authentication list exists.
     */
    public static final int ERROR_IPSEC_MM_AUTH_EXISTS = 0X000032D2;

    /**
     * The specified main mode authentication list was not   found.
     */
    public static final int ERROR_IPSEC_MM_AUTH_NOT_FOUND = 0X000032D3;

    /**
     * The specified main mode authentication list is being   used.
     */
    public static final int ERROR_IPSEC_MM_AUTH_IN_USE = 0X000032D4;

    /**
     * The specified default main mode policy was not found.
     */
    public static final int ERROR_IPSEC_DEFAULT_MM_POLICY_NOT_FOUND = 0X000032D5;

    /**
     * The specified default main mode authentication list   was not found.
     */
    public static final int ERROR_IPSEC_DEFAULT_MM_AUTH_NOT_FOUND = 0X000032D6;

    /**
     * The specified default quick mode policy was not found.
     */
    public static final int ERROR_IPSEC_DEFAULT_QM_POLICY_NOT_FOUND = 0X000032D7;

    /**
     * The specified tunnel mode filter exists.
     */
    public static final int ERROR_IPSEC_TUNNEL_FILTER_EXISTS = 0X000032D8;

    /**
     * The specified tunnel mode filter was not found.
     */
    public static final int ERROR_IPSEC_TUNNEL_FILTER_NOT_FOUND = 0X000032D9;

    /**
     * The main mode filter is pending deletion.
     */
    public static final int ERROR_IPSEC_MM_FILTER_PENDING_DELETION = 0X000032DA;

    /**
     * The transport filter is pending deletion.
     */
    public static final int ERROR_IPSEC_TRANSPORT_FILTER_ENDING_DELETION = 0X000032DB;

    /**
     * The tunnel filter is pending deletion.
     */
    public static final int ERROR_IPSEC_TUNNEL_FILTER_PENDING_DELETION = 0X000032DC;

    /**
     * The main mode policy is pending deletion.
     */
    public static final int ERROR_IPSEC_MM_POLICY_PENDING_ELETION = 0X000032DD;

    /**
     * The main mode authentication bundle is pending   deletion.
     */
    public static final int ERROR_IPSEC_MM_AUTH_PENDING_DELETION = 0X000032DE;

    /**
     * The quick mode policy is pending deletion.
     */
    public static final int ERROR_IPSEC_QM_POLICY_PENDING_DELETION = 0X000032DF;

    /**
     * The main mode policy was successfully added, but some   of the requested offers are not supported.
     */
    public static final int WARNING_IPSEC_MM_POLICY_PRUNED = 0X000032E0;

    /**
     * The quick mode policy was successfully added, but some   of the requested offers are not supported.
     */
    public static final int WARNING_IPSEC_QM_POLICY_PRUNED = 0X000032E1;

    /**
     * Starts the list of frequencies of various IKE Win32   error codes encountered during negotiations.
     */
    public static final int ERROR_IPSEC_IKE_NEG_STATUS_BEGIN = 0X000035E8;

    /**
     * The IKE authentication credentials are unacceptable.
     */
    public static final int ERROR_IPSEC_IKE_AUTH_FAIL = 0X000035E9;

    /**
     * The IKE security attributes are unacceptable.
     */
    public static final int ERROR_IPSEC_IKE_ATTRIB_FAIL = 0X000035EA;

    /**
     * The IKE negotiation is in progress.
     */
    public static final int ERROR_IPSEC_IKE_NEGOTIATION_PENDING = 0X000035EB;

    /**
     * General processing error.
     */
    public static final int ERROR_IPSEC_IKE_GENERAL_PROCESSING_ERROR = 0X000035EC;

    /**
     * Negotiation timed out.
     */
    public static final int ERROR_IPSEC_IKE_TIMED_OUT = 0X000035ED;

    /**
     * The IKE failed to find a valid machine certificate.   Contact your network security administrator about installing a valid   certificate in the appropriate certificate store.
     */
    public static final int ERROR_IPSEC_IKE_NO_CERT = 0X000035EE;

    /**
     * The IKE security association (SA) was deleted by a   peer before it was completely established.
     */
    public static final int ERROR_IPSEC_IKE_SA_DELETED = 0X000035EF;

    /**
     * The IKE SA was deleted before it was completely   established.
     */
    public static final int ERROR_IPSEC_IKE_SA_REAPED = 0X000035F0;

    /**
     * The negotiation request sat in the queue too long.
     */
    public static final int ERROR_IPSEC_IKE_MM_ACQUIRE_DROP = 0X000035F1;

    /**
     * The negotiation request sat in the queue too long.
     */
    public static final int ERROR_IPSEC_IKE_QM_ACQUIRE_DROP = 0X000035F2;

    /**
     * The negotiation request sat in the queue too long.
     */
    public static final int ERROR_IPSEC_IKE_QUEUE_DROP_MM = 0X000035F3;

    /**
     * The negotiation request sat in the queue too long.
     */
    public static final int ERROR_IPSEC_IKE_QUEUE_DROP_NO_MM = 0X000035F4;

    /**
     * There was no response from a peer.
     */
    public static final int ERROR_IPSEC_IKE_DROP_NO_RESPONSE = 0X000035F5;

    /**
     * The negotiation took too long.
     */
    public static final int ERROR_IPSEC_IKE_MM_DELAY_DROP = 0X000035F6;

    /**
     * The negotiation took too long.
     */
    public static final int ERROR_IPSEC_IKE_QM_DELAY_DROP = 0X000035F7;

    /**
     * An unknown error occurred.
     */
    public static final int ERROR_IPSEC_IKE_ERROR = 0X000035F8;

    /**
     * The certificate revocation check failed.
     */
    public static final int ERROR_IPSEC_IKE_CRL_FAILED = 0X000035F9;

    /**
     * Invalid certificate key usage.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_KEY_USAGE = 0X000035FA;

    /**
     * Invalid certificate type.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_CERT_TYPE = 0X000035FB;

    /**
     * The IKE negotiation failed because the machine   certificate used does not have a private key. IPsec certificates require a   private key. Contact your network security administrator about a certificate   that has
     * a private key.
     */
    public static final int ERROR_IPSEC_IKE_NO_PRIVATE_KEY = 0X000035FC;

    /**
     * There was a failure in the Diffie-Hellman computation.
     */
    public static final int ERROR_IPSEC_IKE_DH_FAIL = 0X000035FE;

    /**
     * Invalid header.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_HEADER = 0X00003600;

    /**
     * No policy configured.
     */
    public static final int ERROR_IPSEC_IKE_NO_POLICY = 0X00003601;

    /**
     * Failed to verify signature.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_SIGNATURE = 0X00003602;

    /**
     * Failed to authenticate using Kerberos.
     */
    public static final int ERROR_IPSEC_IKE_KERBEROS_ERROR = 0X00003603;

    /**
     * The peer\'s certificate did not have a public key.
     */
    public static final int ERROR_IPSEC_IKE_NO_PUBLIC_KEY = 0X00003604;

    /**
     * Error processing the error payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR = 0X00003605;

    /**
     * Error processing the SA payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_SA = 0X00003606;

    /**
     * Error processing the proposal payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_PROP = 0X00003607;

    /**
     * Error processing the transform payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_TRANS = 0X00003608;

    /**
     * Error processing the key exchange payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_KE = 0X00003609;

    /**
     * Error processing the ID payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_ID = 0X0000360A;

    /**
     * Error processing the certification payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_CERT = 0X0000360B;

    /**
     * Error processing the certificate request payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_CERT_REQ = 0X0000360C;

    /**
     * Error processing the hash payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_HASH = 0X0000360D;

    /**
     * Error processing the signature payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_SIG = 0X0000360E;

    /**
     * Error processing the nonce payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_NONCE = 0X0000360F;

    /**
     * Error processing the notify payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_NOTIFY = 0X00003610;

    /**
     * Error processing the delete payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_DELETE = 0X00003611;

    /**
     * Error processing the VendorId payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_VENDOR = 0X00003612;

    /**
     * Invalid payload received.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_PAYLOAD = 0X00003613;

    /**
     * Soft SA loaded.
     */
    public static final int ERROR_IPSEC_IKE_LOAD_SOFT_SA = 0X00003614;

    /**
     * Soft SA torn down.
     */
    public static final int ERROR_IPSEC_IKE_SOFT_SA_TORN_DOWN = 0X00003615;

    /**
     * Invalid cookie received.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_COOKIE = 0X00003616;

    /**
     * Peer failed to send valid machine certificate.
     */
    public static final int ERROR_IPSEC_IKE_NO_PEER_CERT = 0X00003617;

    /**
     * Certification revocation check of peer\'s certificate   failed.
     */
    public static final int ERROR_IPSEC_IKE_PEER_CRL_FAILED = 0X00003618;

    /**
     * New policy invalidated SAs formed with the old policy.
     */
    public static final int ERROR_IPSEC_IKE_POLICY_CHANGE = 0X00003619;

    /**
     * There is no available main mode IKE policy.
     */
    public static final int ERROR_IPSEC_IKE_NO_MM_POLICY = 0X0000361A;

    /**
     * Failed to enabled trusted computer base (TCB)   privilege.
     */
    public static final int ERROR_IPSEC_IKE_NOTCBPRIV = 0X0000361B;

    /**
     * Failed to load SECURITY.DLL.
     */
    public static final int ERROR_IPSEC_IKE_SECLOADFAIL = 0X0000361C;

    /**
     * Failed to obtain the security function table dispatch   address from the SSPI.
     */
    public static final int ERROR_IPSEC_IKE_FAILSSPINIT = 0X0000361D;

    /**
     * Failed to query the Kerberos package to obtain the max   token size.
     */
    public static final int ERROR_IPSEC_IKE_FAILQUERYSSP = 0X0000361E;

    /**
     * Failed to obtain the Kerberos server credentials for   the Internet Security Association and Key Management Protocol   (ISAKMP)/ERROR_IPSEC_IKE service. Kerberos authentication will not function.   The most likely
     * reason for this is lack of domain membership. This is normal   if your computer is a member of a workgroup.
     */
    public static final int ERROR_IPSEC_IKE_SRVACQFAIL = 0X0000361F;

    /**
     * Failed to determine the SSPI principal name for   ISAKMP/ERROR_IPSEC_IKE service (QueryCredentialsAttributes).
     */
    public static final int ERROR_IPSEC_IKE_SRVQUERYCRED = 0X00003620;

    /**
     * Failed to obtain a new service provider interface   (SPI) for the inbound SA from the IPsec driver. The most common cause for   this is that the driver does not have the correct filter. Check your policy   to
     * verify the filters.
     */
    public static final int ERROR_IPSEC_IKE_GETSPIFAIL = 0X00003621;

    /**
     * Given filter is invalid.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_FILTER = 0X00003622;

    /**
     * Memory allocation failed.
     */
    public static final int ERROR_IPSEC_IKE_OUT_OF_MEMORY = 0X00003623;

    /**
     * Failed to add an SA to the IPSec driver. The most   common cause for this is if the IKE negotiation took too long to complete. If   the problem persists, reduce the load on the faulting machine.
     */
    public static final int ERROR_IPSEC_IKE_ADD_UPDATE_KEY_FAILED = 0X00003624;

    /**
     * Invalid policy.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_POLICY = 0X00003625;

    /**
     * Invalid digital object identifier (DOI).
     */
    public static final int ERROR_IPSEC_IKE_UNKNOWN_DOI = 0X00003626;

    /**
     * Invalid situation.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_SITUATION = 0X00003627;

    /**
     * Diffie-Hellman failure.
     */
    public static final int ERROR_IPSEC_IKE_DH_FAILURE = 0X00003628;

    /**
     * Invalid Diffie-Hellman group.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_GROUP = 0X00003629;

    /**
     * Error encrypting payload.
     */
    public static final int ERROR_IPSEC_IKE_ENCRYPT = 0X0000362A;

    /**
     * Error decrypting payload.
     */
    public static final int ERROR_IPSEC_IKE_DECRYPT = 0X0000362B;

    /**
     * Policy match error.
     */
    public static final int ERROR_IPSEC_IKE_POLICY_MATCH = 0X0000362C;

    /**
     * Unsupported ID.
     */
    public static final int ERROR_IPSEC_IKE_UNSUPPORTED_ID = 0X0000362D;

    /**
     * Hash verification failed.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_HASH = 0X0000362E;

    /**
     * Invalid hash algorithm.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_HASH_ALG = 0X0000362F;

    /**
     * Invalid hash size.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_HASH_SIZE = 0X00003630;

    /**
     * Invalid encryption algorithm.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_ENCRYPT_ALG = 0X00003631;

    /**
     * Invalid authentication algorithm.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_AUTH_ALG = 0X00003632;

    /**
     * Invalid certificate signature.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_SIG = 0X00003633;

    /**
     * Load failed.
     */
    public static final int ERROR_IPSEC_IKE_LOAD_FAILED = 0X00003634;

    /**
     * Deleted by using an RPC call.
     */
    public static final int ERROR_IPSEC_IKE_RPC_DELETE = 0X00003635;

    /**
     * A temporary state was created to perform   reinitialization. This is not a real failure.
     */
    public static final int ERROR_IPSEC_IKE_BENIGN_REINIT = 0X00003636;

    /**
     * The lifetime value received in the Responder Lifetime   Notify is below the Windows 2000 configured minimum value. Fix the policy on   the peer machine.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_RESPONDER_LIFETIME_NOTIFY = 0X00003637;

    /**
     * Key length in the certificate is too small for   configured security requirements.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_CERT_KEYLEN = 0X00003639;

    /**
     * Maximum number of established MM SAs to peer exceeded.
     */
    public static final int ERROR_IPSEC_IKE_MM_LIMIT = 0X0000363A;

    /**
     * The IKE received a policy that disables negotiation.
     */
    public static final int ERROR_IPSEC_IKE_NEGOTIATION_DISABLED = 0X0000363B;

    /**
     * Reached maximum quick mode limit for the main mode.   New main mode will be started.
     */
    public static final int ERROR_IPSEC_IKE_QM_LIMIT = 0X0000363C;

    /**
     * Main mode SA lifetime expired or the peer sent a main   mode delete.
     */
    public static final int ERROR_IPSEC_IKE_MM_EXPIRED = 0X0000363D;

    /**
     * Main mode SA assumed to be invalid because peer   stopped responding.
     */
    public static final int ERROR_IPSEC_IKE_PEER_MM_ASSUMED_INVALID = 0X0000363E;

    /**
     * Certificate does not chain to a trusted root in IPsec   policy.
     */
    public static final int ERROR_IPSEC_IKE_CERT_CHAIN_POLICY_MISMATCH = 0X0000363F;

    /**
     * Received unexpected message ID.
     */
    public static final int ERROR_IPSEC_IKE_UNEXPECTED_MESSAGE_ID = 0X00003640;

    /**
     * Received invalid AuthIP user mode attributes.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_UMATTS = 0X00003641;

    /**
     * Sent DOS cookie notify to initiator.
     */
    public static final int ERROR_IPSEC_IKE_DOS_COOKIE_SENT = 0X00003642;

    /**
     * The IKE service is shutting down.
     */
    public static final int ERROR_IPSEC_IKE_SHUTTING_DOWN = 0X00003643;

    /**
     * Could not verify the binding between the color   graphics adapter (CGA) address and the certificate.
     */
    public static final int ERROR_IPSEC_IKE_CGA_AUTH_FAILED = 0X00003644;

    /**
     * Error processing the NatOA payload.
     */
    public static final int ERROR_IPSEC_IKE_PROCESS_ERR_NATOA = 0X00003645;

    /**
     * The parameters of the main mode are invalid for this   quick mode.
     */
    public static final int ERROR_IPSEC_IKE_INVALID_MM_FOR_QM = 0X00003646;

    /**
     * The quick mode SA was expired by the IPsec driver.
     */
    public static final int ERROR_IPSEC_IKE_QM_EXPIRED = 0X00003647;

    /**
     * Too many dynamically added IKEEXT filters were   detected.
     */
    public static final int ERROR_IPSEC_IKE_TOO_MANY_FILTERS = 0X00003648;

    /**
     * Ends the list of frequencies of various IKE Win32   error codes encountered during negotiations.
     */
    public static final int ERROR_IPSEC_IKE_NEG_STATUS_END = 0X00003649;

    /**
     * The requested section was not present in the   activation context.
     */
    public static final int ERROR_SXS_SECTION_NOT_FOUND = 0X000036B0;

    /**
     * The application has failed to start because its   side-by-side configuration is incorrect. See the application event log for   more detail.
     */
    public static final int ERROR_SXS_CANT_GEN_ACTCTX = 0X000036B1;

    /**
     * The application binding data format is invalid.
     */
    public static final int ERROR_SXS_INVALID_ACTCTXDATA_FORMAT = 0X000036B2;

    /**
     * The referenced assembly is not installed on your   system.
     */
    public static final int ERROR_SXS_ASSEMBLY_NOT_FOUND = 0X000036B3;

    /**
     * The manifest file does not begin with the required tag   and format information.
     */
    public static final int ERROR_SXS_MANIFEST_FORMAT_ERROR = 0X000036B4;

    /**
     * The manifest file contains one or more syntax errors.
     */
    public static final int ERROR_SXS_MANIFEST_PARSE_ERROR = 0X000036B5;

    /**
     * The application attempted to activate a disabled   activation context.
     */
    public static final int ERROR_SXS_ACTIVATION_CONTEXT_DISABLED = 0X000036B6;

    /**
     * The requested lookup key was not found in any active   activation context.
     */
    public static final int ERROR_SXS_KEY_NOT_FOUND = 0X000036B7;

    /**
     * A component version required by the application   conflicts with another active component version.
     */
    public static final int ERROR_SXS_VERSION_CONFLICT = 0X000036B8;

    /**
     * The type requested activation context section does not   match the query API used.
     */
    public static final int ERROR_SXS_WRONG_SECTION_TYPE = 0X000036B9;

    /**
     * Lack of system resources has required isolated   activation to be disabled for the current thread of execution.
     */
    public static final int ERROR_SXS_THREAD_QUERIES_DISABLED = 0X000036BA;

    /**
     * An attempt to set the process default activation   context failed because the process default activation context was already   set.
     */
    public static final int ERROR_SXS_PROCESS_DEFAULT_ALREADY_SET = 0X000036BB;

    /**
     * The encoding group identifier specified is not   recognized.
     */
    public static final int ERROR_SXS_UNKNOWN_ENCODING_GROUP = 0X000036BC;

    /**
     * The encoding requested is not recognized.
     */
    public static final int ERROR_SXS_UNKNOWN_ENCODING = 0X000036BD;

    /**
     * The manifest contains a reference to an invalid URI.
     */
    public static final int ERROR_SXS_INVALID_XML_NAMESPACE_URI = 0X000036BE;

    /**
     * The application manifest contains a reference to a   dependent assembly that is not installed.
     */
    public static final int ERROR_SXS_ROOT_MANIFEST_DEPENDENCY_OT_INSTALLED = 0X000036BF;

    /**
     * The manifest for an assembly used by the application   has a reference to a dependent assembly that is not installed.
     */
    public static final int ERROR_SXS_LEAF_MANIFEST_DEPENDENCY_NOT_INSTALLED = 0X000036C0;

    /**
     * The manifest contains an attribute for the assembly   identity that is not valid.
     */
    public static final int ERROR_SXS_INVALID_ASSEMBLY_IDENTITY_ATTRIBUTE = 0X000036C1;

    /**
     * The manifest is missing the required default namespace   specification on the assembly element.
     */
    public static final int ERROR_SXS_MANIFEST_MISSING_REQUIRED_DEFAULT_NAMESPACE = 0X000036C2;

    /**
     * The manifest has a default namespace specified on the   assembly element but its value is not   urn:schemas-microsoft-com:asm.v1\".\"
     */
    public static final int ERROR_SXS_MANIFEST_INVALID_REQUIRED_DEFAULT_NAMESPACE = 0X000036C3;

    /**
     * The private manifest probed has crossed the   reparse-point-associated path.
     */
    public static final int ERROR_SXS_PRIVATE_MANIFEST_CROSS_PATH_WITH_REPARSE_POINT = 0X000036C4;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest have files by the same name.
     */
    public static final int ERROR_SXS_DUPLICATE_DLL_NAME = 0X000036C5;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest have window classes with the same   name.
     */
    public static final int ERROR_SXS_DUPLICATE_WINDOWCLASS_NAME = 0X000036C6;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest have the same COM server CLSIDs.
     */
    public static final int ERROR_SXS_DUPLICATE_CLSID = 0X000036C7;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest have proxies for the same COM   interface IIDs.
     */
    public static final int ERROR_SXS_DUPLICATE_IID = 0X000036C8;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest have the same COM type library TLBIDs.
     */
    public static final int ERROR_SXS_DUPLICATE_TLBID = 0X000036C9;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest have the same COM ProgIDs.
     */
    public static final int ERROR_SXS_DUPLICATE_PROGID = 0X000036CA;

    /**
     * Two or more components referenced directly or   indirectly by the application manifest are different versions of the same   component, which is not permitted.
     */
    public static final int ERROR_SXS_DUPLICATE_ASSEMBLY_NAME = 0X000036CB;

    /**
     * A component\'s file does not match the verification   information present in the component manifest.
     */
    public static final int ERROR_SXS_FILE_HASH_MISMATCH = 0X000036CC;

    /**
     * The policy manifest contains one or more syntax   errors.
     */
    public static final int ERROR_SXS_POLICY_PARSE_ERROR = 0X000036CD;

    /**
     * Manifest Parse Error: A string literal was expected,   but no opening quotation mark was found.
     */
    public static final int ERROR_SXS_XML_E_MISSINGQUOTE = 0X000036CE;

    /**
     * Manifest Parse Error: Incorrect syntax was used in a   comment.
     */
    public static final int ERROR_SXS_XML_E_COMMENTSYNTAX = 0X000036CF;

    /**
     * Manifest Parse Error: A name started with an invalid   character.
     */
    public static final int ERROR_SXS_XML_E_BADSTARTNAMECHAR = 0X000036D0;

    /**
     * Manifest Parse Error: A name contained an invalid   character.
     */
    public static final int ERROR_SXS_XML_E_BADNAMECHAR = 0X000036D1;

    /**
     * Manifest Parse Error: A string literal contained an   invalid character.
     */
    public static final int ERROR_SXS_XML_E_BADCHARINSTRING = 0X000036D2;

    /**
     * Manifest Parse Error: Invalid syntax for an XML   declaration.
     */
    public static final int ERROR_SXS_XML_E_XMLDECLSYNTAX = 0X000036D3;

    /**
     * Manifest Parse Error: An Invalid character was found   in text content.
     */
    public static final int ERROR_SXS_XML_E_BADCHARDATA = 0X000036D4;

    /**
     * Manifest Parse Error: Required white space was   missing.
     */
    public static final int ERROR_SXS_XML_E_MISSINGWHITESPACE = 0X000036D5;

    /**
     * Manifest Parse Error: The angle bracket (&rsaquo;)   character was expected.
     */
    public static final int ERROR_SXS_XML_E_EXPECTINGTAGEND = 0X000036D6;

    /**
     * Manifest Parse Error: A semicolon (;) was expected.
     */
    public static final int ERROR_SXS_XML_E_MISSINGSEMICOLON = 0X000036D7;

    /**
     * Manifest Parse Error: Unbalanced parentheses.
     */
    public static final int ERROR_SXS_XML_E_UNBALANCEDPAREN = 0X000036D8;

    /**
     * Manifest Parse Error: Internal error.
     */
    public static final int ERROR_SXS_XML_E_INTERNALERROR = 0X000036D9;

    /**
     * Manifest Parse Error: Whitespace is not allowed at   this location.
     */
    public static final int ERROR_SXS_XML_E_UNEXPECTED_WHITESPACE = 0X000036DA;

    /**
     * Manifest Parse Error: End of file reached in invalid   state for current encoding.
     */
    public static final int ERROR_SXS_XML_E_INCOMPLETE_ENCODING = 0X000036DB;

    /**
     * Manifest Parse Error: Missing parenthesis.
     */
    public static final int ERROR_SXS_XML_E_MISSING_PAREN = 0X000036DC;

    /**
     * Manifest Parse Error: A single (\') or double (\")   quotation mark is missing.
     */
    public static final int ERROR_SXS_XML_E_EXPECTINGCLOSEQUOTE = 0X000036DD;

    /**
     * Manifest Parse Error: Multiple colons are not allowed   in a name.
     */
    public static final int ERROR_SXS_XML_E_MULTIPLE_COLONS = 0X000036DE;

    /**
     * Manifest Parse Error: Invalid character for decimal   digit.
     */
    public static final int ERROR_SXS_XML_E_INVALID_DECIMAL = 0X000036DF;

    /**
     * Manifest Parse Error: Invalid character for   hexadecimal digit.
     */
    public static final int ERROR_SXS_XML_E_INVALID_HEXIDECIMAL = 0X000036E0;

    /**
     * Manifest Parse Error: Invalid Unicode character value   for this platform.
     */
    public static final int ERROR_SXS_XML_E_INVALID_UNICODE = 0X000036E1;

    /**
     * Manifest Parse Error: Expecting whitespace or question   mark (?).
     */
    public static final int ERROR_SXS_XML_E_WHITESPACEORQUESTIONMARK = 0X000036E2;

    /**
     * Manifest Parse Error: End tag was not expected at this   location.
     */
    public static final int ERROR_SXS_XML_E_UNEXPECTEDENDTAG = 0X000036E3;

    /**
     * Manifest Parse Error: The following tags were not   closed: %1.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDTAG = 0X000036E4;

    /**
     * Manifest Parse Error: Duplicate attribute.
     */
    public static final int ERROR_SXS_XML_E_DUPLICATEATTRIBUTE = 0X000036E5;

    /**
     * Manifest Parse Error: Only one top-level element is   allowed in an XML document.
     */
    public static final int ERROR_SXS_XML_E_MULTIPLEROOTS = 0X000036E6;

    /**
     * Manifest Parse Error: Invalid at the top level of the   document.
     */
    public static final int ERROR_SXS_XML_E_INVALIDATROOTLEVEL = 0X000036E7;

    /**
     * Manifest Parse Error: Invalid XML declaration.
     */
    public static final int ERROR_SXS_XML_E_BADXMLDECL = 0X000036E8;

    /**
     * Manifest Parse Error: XML document must have a   top-level element.
     */
    public static final int ERROR_SXS_XML_E_MISSINGROOT = 0X000036E9;

    /**
     * Manifest Parse Error: Unexpected end of file.
     */
    public static final int ERROR_SXS_XML_E_UNEXPECTEDEOF = 0X000036EA;

    /**
     * Manifest Parse Error: Parameter entities cannot be   used inside markup declarations in an internal subset.
     */
    public static final int ERROR_SXS_XML_E_BADPEREFINSUBSET = 0X000036EB;

    /**
     * Manifest Parse Error: Element was not closed.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDSTARTTAG = 0X000036EC;

    /**
     * Manifest Parse Error: End element was missing the angle bracket (&rsaquo;) character.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDENDTAG = 0X000036ED;

    /**
     * Manifest Parse Error: A string literal was not closed.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDSTRING = 0X000036EE;

    /**
     * Manifest Parse Error: A comment was not closed.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDCOMMENT = 0X000036EF;

    /**
     * Manifest Parse Error: A declaration was not closed.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDDECL = 0X000036F0;

    /**
     * Manifest Parse Error: A CDATA section was not closed.
     */
    public static final int ERROR_SXS_XML_E_UNCLOSEDCDATA = 0X000036F1;

    /**
     * Manifest Parse Error: The namespace prefix is not   allowed to start with the reserved string xml\".\"
     */
    public static final int ERROR_SXS_XML_E_RESERVEDNAMESPACE = 0X000036F2;

    /**
     * Manifest Parse Error: System does not support the   specified encoding.
     */
    public static final int ERROR_SXS_XML_E_INVALIDENCODING = 0X000036F3;

    /**
     * Manifest Parse Error: Switch from current encoding to   specified encoding not supported.
     */
    public static final int ERROR_SXS_XML_E_INVALIDSWITCH = 0X000036F4;

    /**
     * Manifest Parse Error: The name \"xml\" is   reserved and must be lowercase.
     */
    public static final int ERROR_SXS_XML_E_BADXMLCASE = 0X000036F5;

    /**
     * Manifest Parse Error: The stand-alone attribute must   have the value \"yes\" or \"no\".
     */
    public static final int ERROR_SXS_XML_E_INVALID_STANDALONE = 0X000036F6;

    /**
     * Manifest Parse Error: The stand-alone attribute cannot   be used in external entities.
     */
    public static final int ERROR_SXS_XML_E_UNEXPECTED_STANDALONE = 0X000036F7;

    /**
     * Manifest Parse Error: Invalid version number.
     */
    public static final int ERROR_SXS_XML_E_INVALID_VERSION = 0X000036F8;

    /**
     * Manifest Parse Error: Missing equal sign (=) between   the attribute and the attribute value.
     */
    public static final int ERROR_SXS_XML_E_MISSINGEQUALS = 0X000036F9;

    /**
     * Assembly Protection Error: Unable to recover the   specified assembly.
     */
    public static final int ERROR_SXS_PROTECTION_RECOVERY_FAILED = 0X000036FA;

    /**
     * Assembly Protection Error: The public key for an   assembly was too short to be allowed.
     */
    public static final int ERROR_SXS_PROTECTION_PUBLIC_KEY_OO_SHORT = 0X000036FB;

    /**
     * Assembly Protection Error: The catalog for an assembly   is not valid, or does not match the assembly\'s manifest.
     */
    public static final int ERROR_SXS_PROTECTION_CATALOG_NOT_VALID = 0X000036FC;

    /**
     * An HRESULT could not be translated to a corresponding   Win32 error code.
     */
    public static final int ERROR_SXS_UNTRANSLATABLE_HRESULT = 0X000036FD;

    /**
     * Assembly Protection Error: The catalog for an assembly   is missing.
     */
    public static final int ERROR_SXS_PROTECTION_CATALOG_FILE_MISSING = 0X000036FE;

    /**
     * The supplied assembly identity is missing one or more   attributes that must be present in this context.
     */
    public static final int ERROR_SXS_MISSING_ASSEMBLY_IDENTITY_ATTRIBUTE = 0X000036FF;

    /**
     * The supplied assembly identity has one or more   attribute names that contain characters not permitted in XML names.
     */
    public static final int ERROR_SXS_INVALID_ASSEMBLY_IDENTITY_ATTRIBUTE_NAME = 0X00003700;

    /**
     * The referenced assembly could not be found.
     */
    public static final int ERROR_SXS_ASSEMBLY_MISSING = 0X00003701;

    /**
     * The activation context activation stack for the   running thread of execution is corrupt.
     */
    public static final int ERROR_SXS_CORRUPT_ACTIVATION_STACK = 0X00003702;

    /**
     * The application isolation metadata for this process or   thread has become corrupt.
     */
    public static final int ERROR_SXS_CORRUPTION = 0X00003703;

    /**
     * The activation context being deactivated is not the   most recently activated one.
     */
    public static final int ERROR_SXS_EARLY_DEACTIVATION = 0X00003704;

    /**
     * The activation context being deactivated is not active   for the current thread of execution.
     */
    public static final int ERROR_SXS_INVALID_DEACTIVATION = 0X00003705;

    /**
     * The activation context being deactivated has already   been deactivated.
     */
    public static final int ERROR_SXS_MULTIPLE_DEACTIVATION = 0X00003706;

    /**
     * A component used by the isolation facility has   requested to terminate the process.
     */
    public static final int ERROR_SXS_PROCESS_TERMINATION_REQUESTED = 0X00003707;

    /**
     * A kernel mode component is releasing a reference on an   activation context.
     */
    public static final int ERROR_SXS_RELEASE_ACTIVATION_ONTEXT = 0X00003708;

    /**
     * The activation context of the system default assembly   could not be generated.
     */
    public static final int ERROR_SXS_SYSTEM_DEFAULT_ACTIVATION_CONTEXT_EMPTY = 0X00003709;

    /**
     * The value of an attribute in an identity is not within   the legal range.
     */
    public static final int ERROR_SXS_INVALID_IDENTITY_ATTRIBUTE_VALUE = 0X0000370A;

    /**
     * The name of an attribute in an identity is not within   the legal range.
     */
    public static final int ERROR_SXS_INVALID_IDENTITY_ATTRIBUTE_NAME = 0X0000370B;

    /**
     * An identity contains two definitions for the same   attribute.
     */
    public static final int ERROR_SXS_IDENTITY_DUPLICATE_ATTRIBUTE = 0X0000370C;

    /**
     * The identity string is malformed. This might be due to   a trailing comma, more than two unnamed attributes, a missing attribute name,   or a missing attribute value.
     */
    public static final int ERROR_SXS_IDENTITY_PARSE_ERROR = 0X0000370D;

    /**
     * A string containing localized substitutable content   was malformed. Either a dollar sign ($) was followed by something other than   a left parenthesis or another dollar sign, or a substitution\'s right
     * parenthesis was not found.
     */
    public static final int ERROR_MALFORMED_SUBSTITUTION_STRING = 0X0000370E;

    /**
     * The public key token does not correspond to the public   key specified.
     */
    public static final int ERROR_SXS_INCORRECT_PUBLIC_KEY_OKEN = 0X0000370F;

    /**
     * A substitution string had no mapping.
     */
    public static final int ERROR_UNMAPPED_SUBSTITUTION_STRING = 0X00003710;

    /**
     * The component must be locked before making the   request.
     */
    public static final int ERROR_SXS_ASSEMBLY_NOT_LOCKED = 0X00003711;

    /**
     * The component store has been corrupted.
     */
    public static final int ERROR_SXS_COMPONENT_STORE_CORRUPT = 0X00003712;

    /**
     * An advanced installer failed during setup or   servicing.
     */
    public static final int ERROR_ADVANCED_INSTALLER_FAILED = 0X00003713;

    /**
     * The character encoding in the XML declaration did not   match the encoding used in the document.
     */
    public static final int ERROR_XML_ENCODING_MISMATCH = 0X00003714;

    /**
     * The identities of the manifests are identical, but the   contents are different.
     */
    public static final int ERROR_SXS_MANIFEST_IDENTITY_SAME_BUT_CONTENTS_DIFFERENT = 0X00003715;

    /**
     * The component identities are different.
     */
    public static final int ERROR_SXS_IDENTITIES_DIFFERENT = 0X00003716;

    /**
     * The assembly is not a deployment.
     */
    public static final int ERROR_SXS_ASSEMBLY_IS_NOT_A_DEPLOYMENT = 0X00003717;

    /**
     * The file is not a part of the assembly.
     */
    public static final int ERROR_SXS_FILE_NOT_PART_OF_ASSEMBLY = 0X00003718;

    /**
     * The size of the manifest exceeds the maximum allowed.
     */
    public static final int ERROR_SXS_MANIFEST_TOO_BIG = 0X00003719;

    /**
     * The setting is not registered.
     */
    public static final int ERROR_SXS_SETTING_NOT_REGISTERED = 0X0000371A;

    /**
     * One or more required members of the transaction are   not present.
     */
    public static final int ERROR_SXS_TRANSACTION_CLOSURE_INCOMPLETE = 0X0000371B;

    /**
     * The specified channel path is invalid.
     */
    public static final int ERROR_EVT_INVALID_CHANNEL_PATH = 0X00003A98;

    /**
     * The specified query is invalid.
     */
    public static final int ERROR_EVT_INVALID_QUERY = 0X00003A99;

    /**
     * The publisher metadata cannot be found in the   resource.
     */
    public static final int ERROR_EVT_PUBLISHER_METADATA_NOT_FOUND = 0X00003A9A;

    /**
     * The template for an event definition cannot be found   in the resource (error = %1).
     */
    public static final int ERROR_EVT_EVENT_TEMPLATE_NOT_FOUND = 0X00003A9B;

    /**
     * The specified publisher name is invalid.
     */
    public static final int ERROR_EVT_INVALID_PUBLISHER_NAME = 0X00003A9C;

    /**
     * The event data raised by the publisher is not   compatible with the event template definition in the publisher\'s manifest.
     */
    public static final int ERROR_EVT_INVALID_EVENT_DATA = 0X00003A9D;

    /**
     * The specified channel could not be found. Check   channel configuration.
     */
    public static final int ERROR_EVT_CHANNEL_NOT_FOUND = 0X00003A9F;

    /**
     * The specified XML text was not well-formed. See   extended error for more details.
     */
    public static final int ERROR_EVT_MALFORMED_XML_TEXT = 0X00003AA0;

    /**
     * The caller is trying to subscribe to a direct channel   which is not allowed. The events for a direct channel go directly to a log   file and cannot be subscribed to.
     */
    public static final int ERROR_EVT_SUBSCRIPTION_TO_DIRECT_CHANNEL = 0X00003AA1;

    /**
     * Configuration error.
     */
    public static final int ERROR_EVT_CONFIGURATION_ERROR = 0X00003AA2;

    /**
     * The query result is stale or invalid. This might be   due to the log being cleared or rolling over after the query result was   created. Users should handle this code by releasing the query result object   and
     * reissuing the query.
     */
    public static final int ERROR_EVT_QUERY_RESULT_STALE = 0X00003AA3;

    /**
     * Query result is currently at an invalid position.
     */
    public static final int ERROR_EVT_QUERY_RESULT_INVALID_POSITION = 0X00003AA4;

    /**
     * Registered Microsoft XML (MSXML) does not support   validation.
     */
    public static final int ERROR_EVT_NON_VALIDATING_MSXML = 0X00003AA5;

    /**
     * An expression can only be followed by a   change-of-scope operation if it itself evaluates to a node set and is not   already part of some other change-of-scope operation.
     */
    public static final int ERROR_EVT_FILTER_ALREADYSCOPED = 0X00003AA6;

    /**
     * Cannot perform a step operation from a term that does   not represent an element set.
     */
    public static final int ERROR_EVT_FILTER_NOTELTSET = 0X00003AA7;

    /**
     * Left side arguments to binary operators must be either   attributes, nodes, or variables and right side arguments must be constants.
     */
    public static final int ERROR_EVT_FILTER_INVARG = 0X00003AA8;

    /**
     * A step operation must involve either a node test or,   in the case of a predicate, an algebraic expression against which to test   each node in the node set identified by the preceding node set can be evaluated.
     */
    public static final int ERROR_EVT_FILTER_INVTEST = 0X00003AA9;

    /**
     * This data type is currently unsupported.
     */
    public static final int ERROR_EVT_FILTER_INVTYPE = 0X00003AAA;

    /**
     * A syntax error occurred at position %1!d!
     */
    public static final int ERROR_EVT_FILTER_PARSEERR = 0X00003AAB;

    /**
     * This operator is unsupported by this implementation of   the filter.
     */
    public static final int ERROR_EVT_FILTER_UNSUPPORTEDOP = 0X00003AAC;

    /**
     * The token encountered was unexpected.
     */
    public static final int ERROR_EVT_FILTER_UNEXPECTEDTOKEN = 0X00003AAD;

    /**
     * The requested operation cannot be performed over an   enabled direct channel. The channel must first be disabled before performing   the requested operation.
     */
    public static final int ERROR_EVT_INVALID_OPERATION_OVER_ENABLED_DIRECT_CHANNEL = 0X00003AAE;

    /**
     * Channel property %1!s! contains an invalid value. The   value has an invalid type, is outside the valid range, cannot be updated, or   is not supported by this type of channel.
     */
    public static final int ERROR_EVT_INVALID_CHANNEL_PROPERTY_VALUE = 0X00003AAF;

    /**
     * Publisher property %1!s! contains an invalid value.   The value has an invalid type, is outside the valid range, cannot be updated,   or is not supported by this type of publisher.
     */
    public static final int ERROR_EVT_INVALID_PUBLISHER_PROPERTY_VALUE = 0X00003AB0;

    /**
     * The channel fails to activate.
     */
    public static final int ERROR_EVT_CHANNEL_CANNOT_ACTIVATE = 0X00003AB1;

    /**
     * The xpath expression exceeded supported complexity.   Simplify it or split it into two or more simple expressions.
     */
    public static final int ERROR_EVT_FILTER_TOO_COMPLEX = 0X00003AB2;

    /**
     * The message resource is present but the message is not   found in the string or message table.
     */
    public static final int ERROR_EVT_MESSAGE_NOT_FOUND = 0X00003AB3;

    /**
     * The message ID for the desired message could not be   found.
     */
    public static final int ERROR_EVT_MESSAGE_ID_NOT_FOUND = 0X00003AB4;

    /**
     * The substitution string for the insert index (%1)   could not be found.
     */
    public static final int ERROR_EVT_UNRESOLVED_VALUE_INSERT = 0X00003AB5;

    /**
     * The description string for the parameter reference   (%1) could not be found.
     */
    public static final int ERROR_EVT_UNRESOLVED_PARAMETER_INSERT = 0X00003AB6;

    /**
     * The maximum number of replacements has been reached.
     */
    public static final int ERROR_EVT_MAX_INSERTS_REACHED = 0X00003AB7;

    /**
     * The event definition could not be found for the event   ID (%1).
     */
    public static final int ERROR_EVT_EVENT_DEFINITION_NOT_OUND = 0X00003AB8;

    /**
     * The locale-specific resource for the desired message   is not present.
     */
    public static final int ERROR_EVT_MESSAGE_LOCALE_NOT_FOUND = 0X00003AB9;

    /**
     * The resource is too old to be compatible.
     */
    public static final int ERROR_EVT_VERSION_TOO_OLD = 0X00003ABA;

    /**
     * The resource is too new to be compatible.
     */
    public static final int ERROR_EVT_VERSION_TOO_NEW = 0X00003ABB;

    /**
     * The channel at index %1 of the query cannot be opened.
     */
    public static final int ERROR_EVT_CANNOT_OPEN_CHANNEL_OF_QUERY = 0X00003ABC;

    /**
     * The publisher has been disabled and its resource is   not available. This usually occurs when the publisher is in the process of   being uninstalled or upgraded.
     */
    public static final int ERROR_EVT_PUBLISHER_DISABLED = 0X00003ABD;

    /**
     * The subscription fails to activate.
     */
    public static final int ERROR_EC_SUBSCRIPTION_CANNOT_ACTIVATE = 0X00003AE8;

    /**
     * The log of the subscription is in a disabled state and   events cannot be forwarded to it. The log must first be enabled before the   subscription can be activated.
     */
    public static final int ERROR_EC_LOG_DISABLED = 0X00003AE9;

    /**
     * The resource loader failed to find the Multilingual   User Interface (MUI) file.
     */
    public static final int ERROR_MUI_FILE_NOT_FOUND = 0X00003AFC;

    /**
     * The resource loader failed to load the MUI file   because the file failed to pass validation.
     */
    public static final int ERROR_MUI_INVALID_FILE = 0X00003AFD;

    /**
     * The release candidate (RC) manifest is corrupted with   garbage data, is an unsupported version, or is missing a required item.
     */
    public static final int ERROR_MUI_INVALID_RC_CONFIG = 0X00003AFE;

    /**
     * The RC manifest has an invalid culture name.
     */
    public static final int ERROR_MUI_INVALID_LOCALE_NAME = 0X00003AFF;

    /**
     * The RC Manifest has an invalid ultimate fallback name.
     */
    public static final int ERROR_MUI_INVALID_ULTIMATEFALLBACK_NAME = 0X00003B00;

    /**
     * The resource loader cache does not have a loaded MUI   entry.
     */
    public static final int ERROR_MUI_FILE_NOT_LOADED = 0X00003B01;

    /**
     * The user stopped resource enumeration.
     */
    public static final int ERROR_RESOURCE_ENUM_USER_STOP = 0X00003B02;

    /**
     * User interface language installation failed.
     */
    public static final int ERROR_MUI_INTLSETTINGS_UILANG_NOT_INSTALLED = 0X00003B03;

    /**
     * Locale installation failed.
     */
    public static final int ERROR_MUI_INTLSETTINGS_INVALID_LOCALE_NAME = 0X00003B04;

    /**
     * The monitor returned a DDC/CI capabilities string that   did not comply with the ACCESS.bus 3.0, DDC/CI 1.1, or MCCS 2 Revision 1   specification.
     */
    public static final int ERROR_MCA_INVALID_CAPABILITIES_STRING = 0X00003B60;

    /**
     * The monitor\'s VCP version (0xDF) VCP code returned an   invalid version value.
     */
    public static final int ERROR_MCA_INVALID_VCP_VERSION = 0X00003B61;

    /**
     * The monitor does not comply with the MCCS   specification it claims to support.
     */
    public static final int ERROR_MCA_MONITOR_VIOLATES_MCCS_SPECIFICATION = 0X00003B62;

    /**
     * The MCCS version in a monitor\'s mccs_ver capability   does not match the MCCS version the monitor reports when the VCP version   (0xDF) VCP code is used.
     */
    public static final int ERROR_MCA_MCCS_VERSION_MISMATCH = 0X00003B63;

    /**
     * The monitor configuration API works only with monitors   that support the MCCS 1.0, MCCS 2.0, or MCCS 2.0 Revision 1 specifications.
     */
    public static final int ERROR_MCA_UNSUPPORTED_MCCS_VERSION = 0X00003B64;

    /**
     * An internal monitor configuration API error occurred.
     */
    public static final int ERROR_MCA_INTERNAL_ERROR = 0X00003B65;

    /**
     * The monitor returned an invalid monitor technology   type. CRT, plasma, and LCD (TFT) are examples of monitor technology types.   This error implies that the monitor violated the MCCS 2.0 or MCCS 2.0   Revision 1
     * specification.
     */
    public static final int ERROR_MCA_INVALID_TECHNOLOGY_TYPE_RETURNED = 0X00003B66;

    /**
     * The SetMonitorColorTemperature() caller passed a color   temperature to it that the current monitor did not support. CRT, plasma, and   LCD (TFT) are examples of monitor technology types. This error implies that
     * the monitor violated the MCCS 2.0 or MCCS 2.0 Revision 1 specification.
     */
    public static final int ERROR_MCA_UNSUPPORTED_COLOR_TEMPERATURE = 0X00003B67;

    /**
     * The requested system device cannot be identified due   to multiple indistinguishable devices potentially matching the identification   criteria.
     */
    public static final int ERROR_AMBIGUOUS_SYSTEM_DEVICE = 0X00003B92;

    /**
     * The requested system device cannot be found.
     */
    public static final int ERROR_SYSTEM_DEVICE_NOT_FOUND = 0X00003BC3;

}
			