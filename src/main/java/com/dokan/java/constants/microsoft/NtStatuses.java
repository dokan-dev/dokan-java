package com.dokan.java.constants.microsoft;


public final class NtStatuses {


    /**
     * The operation completed successfully.
     */
    public static final int STATUS_SUCCESS = 0X00000000;

    /**
     * The caller specified WaitAny for WaitType and one of   the dispatcher objects in the Object array has been set to the signaled   state.
     */
    public static final int STATUS_WAIT_0 = 0X00000000;

    /**
     * The caller specified WaitAny for WaitType and one of   the dispatcher objects in the Object array has been set to the signaled   state.
     */
    public static final int STATUS_WAIT_1 = 0X00000001;

    /**
     * The caller specified WaitAny for WaitType and one of   the dispatcher objects in the Object array has been set to the signaled   state.
     */
    public static final int STATUS_WAIT_2 = 0X00000002;

    /**
     * The caller specified WaitAny for WaitType and one of   the dispatcher objects in the Object array has been set to the signaled state.
     */
    public static final int STATUS_WAIT_3 = 0X00000003;

    /**
     * The caller specified WaitAny for WaitType and one of   the dispatcher objects in the Object array has been set to the signaled   state.
     */
    public static final int STATUS_WAIT_63 = 0X0000003F;

    /**
     * The caller attempted to wait for a mutex that has been   abandoned.
     */
    public static final int STATUS_ABANDONED = 0X00000080;

    /**
     * The caller attempted to wait for a mutex that has been   abandoned.
     */
    public static final int STATUS_ABANDONED_WAIT_0 = 0X00000080;

    /**
     * The caller attempted to wait for a mutex that has been   abandoned.
     */
    public static final int STATUS_ABANDONED_WAIT_63 = 0X000000BF;

    /**
     * A user-mode APC was delivered before the given   Interval expired.
     */
    public static final int STATUS_USER_APC = 0X000000C0;

    /**
     * The delay completed because the thread was alerted.
     */
    public static final int STATUS_ALERTED = 0X00000101;

    /**
     * The given Timeout interval expired.
     */
    public static final int STATUS_TIMEOUT = 0X00000102;

    /**
     * The operation that was requested is pending   completion.
     */
    public static final int STATUS_PENDING = 0X00000103;

    /**
     * A reparse should be performed by the Object Manager   because the name of the file resulted in a symbolic link.
     */
    public static final int STATUS_REPARSE = 0X00000104;

    /**
     * Returned by enumeration APIs to indicate more   information is available to successive calls.
     */
    public static final int STATUS_MORE_ENTRIES = 0X00000105;

    /**
     * Indicates not all privileges or groups that are   referenced are assigned to the caller. This allows, for example, all   privileges to be disabled without having to know exactly which privileges are   assigned.
     */
    public static final int STATUS_NOT_ALL_ASSIGNED = 0X00000106;

    /**
     * Some of the information to be translated has not been   translated.
     */
    public static final int STATUS_SOME_NOT_MAPPED = 0X00000107;

    /**
     * An open/create operation completed while an   opportunistic lock (oplock) break is underway.
     */
    public static final int STATUS_OPLOCK_BREAK_IN_PROGRESS = 0X00000108;

    /**
     * A new volume has been mounted by a file system.
     */
    public static final int STATUS_VOLUME_MOUNTED = 0X00000109;

    /**
     * This success level status indicates that the   transaction state already exists for the registry subtree but that a   transaction commit was previously aborted. The commit has now been completed.
     */
    public static final int STATUS_RXACT_COMMITTED = 0X0000010A;

    /**
     * Indicates that a notify change request has been   completed due to closing the handle that made the notify change request.
     */
    public static final int STATUS_NOTIFY_CLEANUP = 0X0000010B;

    /**
     * Indicates that a notify change request is being   completed and that the information is not being returned in the caller\'s   buffer. The caller now needs to enumerate the files to find the changes.
     */
    public static final int STATUS_NOTIFY_ENUM_DIR = 0X0000010C;

    /**
     * {No Quotas} No system quota limits are specifically   set for this account.
     */
    public static final int STATUS_NO_QUOTAS_FOR_ACCOUNT = 0X0000010D;

    /**
     * {Connect Failure on Primary Transport} An attempt was   made to connect to the remote server %hs on the primary transport, but the   connection failed. The computer WAS able to connect on a secondary transport.
     */
    public static final int STATUS_PRIMARY_TRANSPORT_CONNECT_FAILED = 0X0000010E;

    /**
     * The page fault was a transition fault.
     */
    public static final int STATUS_PAGE_FAULT_TRANSITION = 0X00000110;

    /**
     * The page fault was a demand zero fault.
     */
    public static final int STATUS_PAGE_FAULT_DEMAND_ZERO = 0X00000111;

    /**
     * The page fault was a demand zero fault.
     */
    public static final int STATUS_PAGE_FAULT_COPY_ON_WRITE = 0X00000112;

    /**
     * The page fault was a demand zero fault.
     */
    public static final int STATUS_PAGE_FAULT_GUARD_PAGE = 0X00000113;

    /**
     * The page fault was satisfied by reading from a   secondary storage device.
     */
    public static final int STATUS_PAGE_FAULT_PAGING_FILE = 0X00000114;

    /**
     * The cached page was locked during operation.
     */
    public static final int STATUS_CACHE_PAGE_LOCKED = 0X00000115;

    /**
     * The crash dump exists in a paging file.
     */
    public static final int STATUS_CRASH_DUMP = 0X00000116;

    /**
     * The specified buffer contains all zeros.
     */
    public static final int STATUS_BUFFER_ALL_ZEROS = 0X00000117;

    /**
     * A reparse should be performed by the Object Manager   because the name of the file resulted in a symbolic link.
     */
    public static final int STATUS_REPARSE_OBJECT = 0X00000118;

    /**
     * The device has succeeded a query-stop and its resource   requirements have changed.
     */
    public static final int STATUS_RESOURCE_REQUIREMENTS_CHANGED = 0X00000119;

    /**
     * The translator has translated these resources into the   global space and no additional translations should be performed.
     */
    public static final int STATUS_TRANSLATION_COMPLETE = 0X00000120;

    /**
     * The directory service evaluated group memberships   locally, because it was unable to contact a global catalog server.
     */
    public static final int STATUS_DS_MEMBERSHIP_EVALUATED_LOCALLY = 0X00000121;

    /**
     * A process being terminated has no threads to   terminate.
     */
    public static final int STATUS_NOTHING_TO_TERMINATE = 0X00000122;

    /**
     * The specified process is not part of a job.
     */
    public static final int STATUS_PROCESS_NOT_IN_JOB = 0X00000123;

    /**
     * The specified process is part of a job.
     */
    public static final int STATUS_PROCESS_IN_JOB = 0X00000124;

    /**
     * {Volume Shadow Copy Service} The system is now ready   for hibernation.
     */
    public static final int STATUS_VOLSNAP_HIBERNATE_READY = 0X00000125;

    /**
     * A file system or file system filter driver has successfully   completed an FsFilter operation.
     */
    public static final int STATUS_FSFILTER_OP_COMPLETED_SUCCESSFULLY = 0X00000126;

    /**
     * The specified interrupt vector was already connected.
     */
    public static final int STATUS_INTERRUPT_VECTOR_ALREADY_CONNECTED = 0X00000127;

    /**
     * The specified interrupt vector is still connected.
     */
    public static final int STATUS_INTERRUPT_STILL_CONNECTED = 0X00000128;

    /**
     * The current process is a cloned process.
     */
    public static final int STATUS_PROCESS_CLONED = 0X00000129;

    /**
     * The file was locked and all users of the file can only   read.
     */
    public static final int STATUS_FILE_LOCKED_WITH_ONLY_READERS = 0X0000012A;

    /**
     * The file was locked and at least one user of the file   can write.
     */
    public static final int STATUS_FILE_LOCKED_WITH_WRITERS = 0X0000012B;

    /**
     * The specified ResourceManager made no changes or   updates to the resource under this transaction.
     */
    public static final int STATUS_RESOURCEMANAGER_READ_ONLY = 0X00000202;

    /**
     * An operation is blocked and waiting for an oplock.
     */
    public static final int STATUS_WAIT_FOR_OPLOCK = 0X00000367;

    /**
     * Debugger handled the exception.
     */
    public static final int DBG_EXCEPTION_HANDLED = 0X00010001;

    /**
     * The debugger continued.
     */
    public static final int DBG_CONTINUE = 0X00010002;

    /**
     * The IO was completed by a filter.
     */
    public static final int STATUS_FLT_IO_COMPLETE = 0X001C0001;

    /**
     * The file is temporarily unavailable.
     */
    public static final int STATUS_FILE_NOT_AVAILABLE = 0XC0000467;

    /**
     * The share is temporarily unavailable.
     */
    public static final int STATUS_SHARE_UNAVAILABLE = 0XC0000480;

    /**
     * A threadpool worker thread entered a callback at   thread affinity %p and exited at affinity %p.
     */
    public static final int STATUS_CALLBACK_RETURNED_THREAD_AFFINITY = 0XC0000721;

    /**
     * {Object Exists} An attempt was made to create an   object but the object name already exists.
     */
    public static final int STATUS_OBJECT_NAME_EXISTS = 0X40000000;

    /**
     * {Thread Suspended} A thread termination occurred while   the thread was suspended. The thread resumed, and termination proceeded.
     */
    public static final int STATUS_THREAD_WAS_SUSPENDED = 0X40000001;

    /**
     * {Working Set Range Error} An attempt was made to set   the working set minimum or maximum to values that are outside the allowable   range.
     */
    public static final int STATUS_WORKING_SET_LIMIT_RANGE = 0X40000002;

    /**
     * {Image Relocated} An image file could not be mapped at   the address that is specified in the image file. Local fixes must be   performed on this image.
     */
    public static final int STATUS_IMAGE_NOT_AT_BASE = 0X40000003;

    /**
     * This informational level status indicates that a   specified registry subtree transaction state did not yet exist and had to be   created.
     */
    public static final int STATUS_RXACT_STATE_CREATED = 0X40000004;

    /**
     * {Segment Load} A virtual DOS machine (VDM) is loading,   unloading, or moving an MS-DOS or Win16 program segment image. An exception   is raised so that a debugger can load, unload, or track symbols and
     * breakpoints within these 16-bit segments.
     */
    public static final int STATUS_SEGMENT_NOTIFICATION = 0X40000005;

    /**
     * {Local Session Key} A user session key was requested   for a local remote procedure call (RPC) connection. The session key that is   returned is a constant value and not unique to this connection.
     */
    public static final int STATUS_LOCAL_USER_SESSION_KEY = 0X40000006;

    /**
     * {Invalid Current Directory} The process cannot switch   to the startup current directory %hs. Select OK to set the current directory   to %hs, or select CANCEL to exit.
     */
    public static final int STATUS_BAD_CURRENT_DIRECTORY = 0X40000007;

    /**
     * {Serial IOCTL Complete} A serial I/O operation was   completed by another write to a serial port. (The IOCTL_SERIAL_XOFF_COUNTER   reached zero.)
     */
    public static final int STATUS_SERIAL_MORE_WRITES = 0X40000008;

    /**
     * {Registry Recovery} One of the files that contains the   system registry data had to be recovered by using a log or alternate copy.   The recovery was successful.
     */
    public static final int STATUS_REGISTRY_RECOVERED = 0X40000009;

    /**
     * {Redundant Read} To satisfy a read request, the   Windows NT operating system fault-tolerant file system successfully read the   requested data from a redundant copy. This was done because the file system
     * encountered a failure on a member of the fault-tolerant volume but was unable   to reassign the failing area of the device.
     */
    public static final int STATUS_FT_READ_RECOVERY_FROM_BACKUP = 0X4000000A;

    /**
     * {Redundant Write} To satisfy a write request, the   Windows NT fault-tolerant file system successfully wrote a redundant copy of   the information. This was done because the file system encountered a failure   on
     * a member of the fault-tolerant volume but was unable to reassign the   failing area of the device.
     */
    public static final int STATUS_FT_WRITE_RECOVERY = 0X4000000B;

    /**
     * {Serial IOCTL Timeout} A serial I/O operation   completed because the time-out period expired. (The IOCTL_SERIAL_XOFF_COUNTER   had not reached zero.)
     */
    public static final int STATUS_SERIAL_COUNTER_TIMEOUT = 0X4000000C;

    /**
     * {Password Too Complex} The Windows password is too   complex to be converted to a LAN Manager password. The LAN Manager password   that returned is a NULL string.
     */
    public static final int STATUS_NULL_LM_PASSWORD = 0X4000000D;

    /**
     * {Machine Type Mismatch} The image file %hs is valid   but is for a machine type other than the current machine. Select OK to   continue, or CANCEL to fail the DLL load.
     */
    public static final int STATUS_IMAGE_MACHINE_TYPE_MISMATCH = 0X4000000E;

    /**
     * {Partial Data Received} The network transport returned   partial data to its client. The remaining data will be sent later.
     */
    public static final int STATUS_RECEIVE_PARTIAL = 0X4000000F;

    /**
     * {Expedited Data Received} The network transport   returned data to its client that was marked as expedited by the remote   system.
     */
    public static final int STATUS_RECEIVE_EXPEDITED = 0X40000010;

    /**
     * {Partial Expedited Data Received} The network   transport returned partial data to its client and this data was marked as   expedited by the remote system. The remaining data will be sent later.
     */
    public static final int STATUS_RECEIVE_PARTIAL_EXPEDITED = 0X40000011;

    /**
     * {TDI Event Done} The TDI indication has completed   successfully.
     */
    public static final int STATUS_EVENT_DONE = 0X40000012;

    /**
     * {TDI Event Pending} The TDI indication has entered the   pending state.
     */
    public static final int STATUS_EVENT_PENDING = 0X40000013;

    /**
     * Checking file system on %wZ.
     */
    public static final int STATUS_CHECKING_FILE_SYSTEM = 0X40000014;

    /**
     * {Fatal Application Exit} %hs
     */
    public static final int STATUS_FATAL_APP_EXIT = 0X40000015;

    /**
     * The specified registry key is referenced by a   predefined handle.
     */
    public static final int STATUS_PREDEFINED_HANDLE = 0X40000016;

    /**
     * {Page Unlocked} The page protection of a locked page   was changed to \'No Access\' and the page was unlocked from memory and from the   process.
     */
    public static final int STATUS_WAS_UNLOCKED = 0X40000017;

    /**
     * %hs
     */
    public static final int STATUS_SERVICE_NOTIFICATION = 0X40000018;

    /**
     * {Page Locked} One of the pages to lock was already   locked.
     */
    public static final int STATUS_WAS_LOCKED = 0X40000019;

    /**
     * Application popup: %1 : %2
     */
    public static final int STATUS_LOG_HARD_ERROR = 0X4000001A;

    /**
     * A Win32 process already exists.
     */
    public static final int STATUS_ALREADY_WIN32 = 0X4000001B;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_UNSIMULATE = 0X4000001C;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_CONTINUE = 0X4000001D;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_SINGLE_STEP = 0X4000001E;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_BREAKPOINT = 0X4000001F;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_EXCEPTION_CONTINUE = 0X40000020;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_EXCEPTION_LASTCHANCE = 0X40000021;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_EXCEPTION_CHAIN = 0X40000022;

    /**
     * {Machine Type Mismatch} The image file %hs is valid   but is for a machine type other than the current machine.
     */
    public static final int STATUS_IMAGE_MACHINE_TYPE_MISMATCH_EXE = 0X40000023;

    /**
     * A yield execution was performed and no thread was   available to run.
     */
    public static final int STATUS_NO_YIELD_PERFORMED = 0X40000024;

    /**
     * The resume flag to a timer API was ignored.
     */
    public static final int STATUS_TIMER_RESUME_IGNORED = 0X40000025;

    /**
     * The arbiter has deferred arbitration of these   resources to its parent.
     */
    public static final int STATUS_ARBITRATION_UNHANDLED = 0X40000026;

    /**
     * The device has detected a CardBus card in its slot.
     */
    public static final int STATUS_CARDBUS_NOT_SUPPORTED = 0X40000027;

    /**
     * An exception status code that is used by the Win32 x86   emulation subsystem.
     */
    public static final int STATUS_WX86_CREATEWX86TIB = 0X40000028;

    /**
     * The CPUs in this multiprocessor system are not all the   same revision level. To use all processors, the operating system restricts   itself to the features of the least capable processor in the system. If
     * problems occur with this system, contact the CPU manufacturer to see if this   mix of processors is supported.
     */
    public static final int STATUS_MP_PROCESSOR_MISMATCH = 0X40000029;

    /**
     * The system was put into hibernation.
     */
    public static final int STATUS_HIBERNATED = 0X4000002A;

    /**
     * The system was resumed from hibernation.
     */
    public static final int STATUS_RESUME_HIBERNATION = 0X4000002B;

    /**
     * Windows has detected that the system firmware (BIOS)   was updated [previous firmware date = %2, current firmware date %3].
     */
    public static final int STATUS_FIRMWARE_UPDATED = 0X4000002C;

    /**
     * A device driver is leaking locked I/O pages and is   causing system degradation. The system has automatically enabled the tracking   code to try and catch the culprit.
     */
    public static final int STATUS_DRIVERS_LEAKING_LOCKED_PAGES = 0X4000002D;

    /**
     * The ALPC message being canceled has already been   retrieved from the queue on the other side.
     */
    public static final int STATUS_MESSAGE_RETRIEVED = 0X4000002E;

    /**
     * The system power state is transitioning from %2 to %3.
     */
    public static final int STATUS_SYSTEM_POWERSTATE_TRANSITION = 0X4000002F;

    /**
     * The receive operation was successful. Check the ALPC   completion list for the received message.
     */
    public static final int STATUS_ALPC_CHECK_COMPLETION_LIST = 0X40000030;

    /**
     * The system power state is transitioning from %2 to %3   but could enter %4.
     */
    public static final int STATUS_SYSTEM_POWERSTATE_COMPLEX_TRANSITION = 0X40000031;

    /**
     * Access to %1 is monitored by policy rule %2.
     */
    public static final int STATUS_ACCESS_AUDIT_BY_POLICY = 0X40000032;

    /**
     * A valid hibernation file has been invalidated and   should be abandoned.
     */
    public static final int STATUS_ABANDON_HIBERFILE = 0X40000033;

    /**
     * Business rule scripts are disabled for the calling   application.
     */
    public static final int STATUS_BIZRULES_NOT_ENABLED = 0X40000034;

    /**
     * The system has awoken.
     */
    public static final int STATUS_WAKE_SYSTEM = 0X40000294;

    /**
     * The directory service is shutting down.
     */
    public static final int STATUS_DS_SHUTTING_DOWN = 0X40000370;

    /**
     * Debugger will reply later.
     */
    public static final int DBG_REPLY_LATER = 0X40010001;

    /**
     * Debugger cannot provide a handle.
     */
    public static final int DBG_UNABLE_TO_PROVIDE_HANDLE = 0X40010002;

    /**
     * Debugger terminated the thread.
     */
    public static final int DBG_TERMINATE_THREAD = 0X40010003;

    /**
     * Debugger terminated the process.
     */
    public static final int DBG_TERMINATE_PROCESS = 0X40010004;

    /**
     * Debugger obtained control of C.
     */
    public static final int DBG_CONTROL_C = 0X40010005;

    /**
     * Debugger printed an exception on control C.
     */
    public static final int DBG_PRINTEXCEPTION_C = 0X40010006;

    /**
     * Debugger received a RIP exception.
     */
    public static final int DBG_RIPEXCEPTION = 0X40010007;

    /**
     * Debugger received a control break.
     */
    public static final int DBG_CONTROL_BREAK = 0X40010008;

    /**
     * Debugger command communication exception.
     */
    public static final int DBG_COMMAND_EXCEPTION = 0X40010009;

    /**
     * None
     */
    public static final int RPC_NT_UUID_LOCAL_ONLY = 0X40020056;

    /**
     * Some data remains to be sent in the request buffer.
     */
    public static final int RPC_NT_SEND_INCOMPLETE = 0X400200AF;

    /**
     * The Client Drive Mapping Service has connected on   Terminal Connection.
     */
    public static final int STATUS_CTX_CDM_CONNECT = 0X400A0004;

    /**
     * The Client Drive Mapping Service has disconnected on   Terminal Connection.
     */
    public static final int STATUS_CTX_CDM_DISCONNECT = 0X400A0005;

    /**
     * A kernel mode component is releasing a reference on an   activation context.
     */
    public static final int STATUS_SXS_RELEASE_ACTIVATION_CONTEXT = 0X4015000D;

    /**
     * The transactional resource manager is already   consistent. Recovery is not needed.
     */
    public static final int STATUS_RECOVERY_NOT_NEEDED = 0X40190034;

    /**
     * The transactional resource manager has already been   started.
     */
    public static final int STATUS_RM_ALREADY_STARTED = 0X40190035;

    /**
     * The log service encountered a log stream with no   restart area.
     */
    public static final int STATUS_LOG_NO_RESTART = 0X401A000C;

    /**
     * {Display Driver Recovered From Failure} The %hs   display driver has detected a failure and recovered from it. Some graphical   operations might have failed. The next time you restart the machine, a dialog   box
     * appears, giving you an opportunity to upload data about this failure to   Microsoft.
     */
    public static final int STATUS_VIDEO_DRIVER_DEBUG_REPORT_REQUEST = 0X401B00EC;

    /**
     * The specified buffer is not big enough to contain the   entire requested dataset. Partial data is populated up to the size of the   buffer.
     */
    public static final int STATUS_GRAPHICS_PARTIAL_DATA_POPULATED = 0X401E000A;

    /**
     * The kernel driver detected a version mismatch between   it and the user mode driver.
     */
    public static final int STATUS_GRAPHICS_DRIVER_MISMATCH = 0X401E0117;

    /**
     * No mode is pinned on the specified VidPN   source/target.
     */
    public static final int STATUS_GRAPHICS_MODE_NOT_PINNED = 0X401E0307;

    /**
     * The specified mode set does not specify a preference   for one of its modes.
     */
    public static final int STATUS_GRAPHICS_NO_PREFERRED_MODE = 0X401E031E;

    /**
     * The specified dataset (for example, mode set,   frequency range set, descriptor set, or topology) is empty.
     */
    public static final int STATUS_GRAPHICS_DATASET_IS_EMPTY = 0X401E034B;

    /**
     * The specified dataset (for example, mode set,   frequency range set, descriptor set, or topology) does not contain any more   elements.
     */
    public static final int STATUS_GRAPHICS_NO_MORE_ELEMENTS_IN_DATASET = 0X401E034C;

    /**
     * The specified content transformation is not pinned on   the specified VidPN present path.
     */
    public static final int STATUS_GRAPHICS_PATH_CONTENT_GEOMETRY_TRANSFORMATION_NOT_PINNED = 0X401E0351;

    /**
     * The child device presence was not reliably detected.
     */
    public static final int STATUS_GRAPHICS_UNKNOWN_CHILD_STATUS = 0X401E042F;

    /**
     * Starting the lead adapter in a linked configuration   has been temporarily deferred.
     */
    public static final int STATUS_GRAPHICS_LEADLINK_START_DEFERRED = 0X401E0437;

    /**
     * The display adapter is being polled for children too   frequently at the same polling level.
     */
    public static final int STATUS_GRAPHICS_POLLING_TOO_FREQUENTLY = 0X401E0439;

    /**
     * Starting the adapter has been temporarily deferred.
     */
    public static final int STATUS_GRAPHICS_START_DEFERRED = 0X401E043A;

    /**
     * The request will be completed later by an NDIS status   indication.
     */
    public static final int STATUS_NDIS_INDICATION_REQUIRED = 0X40230001;

    /**
     * {EXCEPTION} Guard Page Exception A page of memory that   marks the end of a data structure, such as a stack or an array, has been   accessed.
     */
    public static final int STATUS_GUARD_PAGE_VIOLATION = 0X80000001;

    /**
     * {EXCEPTION} Alignment Fault A data type misalignment   was detected in a load or store instruction.
     */
    public static final int STATUS_DATATYPE_MISALIGNMENT = 0X80000002;

    /**
     * {EXCEPTION} Breakpoint A breakpoint has been reached.
     */
    public static final int STATUS_BREAKPOINT = 0X80000003;

    /**
     * {EXCEPTION} Single Step A single step or trace   operation has just been completed.
     */
    public static final int STATUS_SINGLE_STEP = 0X80000004;

    /**
     * {Buffer Overflow} The data was too large to fit into   the specified buffer.
     */
    public static final int STATUS_BUFFER_OVERFLOW = 0X80000005;

    /**
     * {No More Files} No more files were found which match   the file specification.
     */
    public static final int STATUS_NO_MORE_FILES = 0X80000006;

    /**
     * {Kernel Debugger Awakened} The system debugger was   awakened by an interrupt.
     */
    public static final int STATUS_WAKE_SYSTEM_DEBUGGER = 0X80000007;

    /**
     * {Handles Closed} Handles to objects have been   automatically closed because of the requested operation.
     */
    public static final int STATUS_HANDLES_CLOSED = 0X8000000A;

    /**
     * {Non-Inheritable ACL} An access control list (ACL)   contains no components that can be inherited.
     */
    public static final int STATUS_NO_INHERITANCE = 0X8000000B;

    /**
     * {GUID Substitution} During the translation of a   globally unique identifier (GUID) to a Windows security ID (SID), no   administratively defined GUID prefix was found. A substitute prefix was used,   which will
     * not compromise system security. However, this might provide a more   restrictive access than intended.
     */
    public static final int STATUS_GUID_SUBSTITUTION_MADE = 0X8000000C;

    /**
     * Because of protection conflicts, not all the requested   bytes could be copied.
     */
    public static final int STATUS_PARTIAL_COPY = 0X8000000D;

    /**
     * {Out of Paper} The printer is out of paper.
     */
    public static final int STATUS_DEVICE_PAPER_EMPTY = 0X8000000E;

    /**
     * {Device Power Is Off} The printer power has been   turned off.
     */
    public static final int STATUS_DEVICE_POWERED_OFF = 0X8000000F;

    /**
     * {Device Offline} The printer has been taken offline.
     */
    public static final int STATUS_DEVICE_OFF_LINE = 0X80000010;

    /**
     * {Device Busy} The device is currently busy.
     */
    public static final int STATUS_DEVICE_BUSY = 0X80000011;

    /**
     * {No More EAs} No more extended attributes (EAs) were   found for the file.
     */
    public static final int STATUS_NO_MORE_EAS = 0X80000012;

    /**
     * {Illegal EA} The specified extended attribute (EA)   name contains at least one illegal character.
     */
    public static final int STATUS_INVALID_EA_NAME = 0X80000013;

    /**
     * {Inconsistent EA List} The extended attribute (EA)   list is inconsistent.
     */
    public static final int STATUS_EA_LIST_INCONSISTENT = 0X80000014;

    /**
     * {Invalid EA Flag} An invalid extended attribute (EA)   flag was set.
     */
    public static final int STATUS_INVALID_EA_FLAG = 0X80000015;

    /**
     * {Verifying Disk} The media has changed and a verify   operation is in progress; therefore, no reads or writes can be performed to   the device, except those that are used in the verify operation.
     */
    public static final int STATUS_VERIFY_REQUIRED = 0X80000016;

    /**
     * {Too Much Information} The specified access control   list (ACL) contained more information than was expected.
     */
    public static final int STATUS_EXTRANEOUS_INFORMATION = 0X80000017;

    /**
     * This warning level status indicates that the   transaction state already exists for the registry subtree, but that a   transaction commit was previously aborted. The commit has NOT been completed   but has not
     * been rolled back either; therefore, it can still be committed, if   needed.
     */
    public static final int STATUS_RXACT_COMMIT_NECESSARY = 0X80000018;

    /**
     * {No More Entries} No more entries are available from   an enumeration operation.
     */
    public static final int STATUS_NO_MORE_ENTRIES = 0X8000001A;

    /**
     * {Filemark Found} A filemark was detected.
     */
    public static final int STATUS_FILEMARK_DETECTED = 0X8000001B;

    /**
     * {Media Changed} The media has changed.
     */
    public static final int STATUS_MEDIA_CHANGED = 0X8000001C;

    /**
     * {I/O Bus Reset} An I/O bus reset was detected.
     */
    public static final int STATUS_BUS_RESET = 0X8000001D;

    /**
     * {End of Media} The end of the media was encountered.
     */
    public static final int STATUS_END_OF_MEDIA = 0X8000001E;

    /**
     * The beginning of a tape or partition has been   detected.
     */
    public static final int STATUS_BEGINNING_OF_MEDIA = 0X8000001F;

    /**
     * {Media Changed} The media might have changed.
     */
    public static final int STATUS_MEDIA_CHECK = 0X80000020;

    /**
     * A tape access reached a set mark.
     */
    public static final int STATUS_SETMARK_DETECTED = 0X80000021;

    /**
     * During a tape access, the end of the data written is   reached.
     */
    public static final int STATUS_NO_DATA_DETECTED = 0X80000022;

    /**
     * The redirector is in use and cannot be unloaded.
     */
    public static final int STATUS_REDIRECTOR_HAS_OPEN_HANDLES = 0X80000023;

    /**
     * The server is in use and cannot be unloaded.
     */
    public static final int STATUS_SERVER_HAS_OPEN_HANDLES = 0X80000024;

    /**
     * The specified connection has already been   disconnected.
     */
    public static final int STATUS_ALREADY_DISCONNECTED = 0X80000025;

    /**
     * A long jump has been executed.
     */
    public static final int STATUS_LONGJUMP = 0X80000026;

    /**
     * A cleaner cartridge is present in the tape library.
     */
    public static final int STATUS_CLEANER_CARTRIDGE_INSTALLED = 0X80000027;

    /**
     * The Plug and Play query operation was not successful.
     */
    public static final int STATUS_PLUGPLAY_QUERY_VETOED = 0X80000028;

    /**
     * A frame consolidation has been executed.
     */
    public static final int STATUS_UNWIND_CONSOLIDATE = 0X80000029;

    /**
     * {Registry Hive Recovered} The registry hive (file):   %hs was corrupted and it has been recovered. Some data might have been lost.
     */
    public static final int STATUS_REGISTRY_HIVE_RECOVERED = 0X8000002A;

    /**
     * The application is attempting to run executable code   from the module %hs. This might be insecure. An alternative, %hs, is   available. Should the application use the secure module %hs?
     */
    public static final int STATUS_DLL_MIGHT_BE_INSECURE = 0X8000002B;

    /**
     * The application is loading executable code from the   module %hs. This is secure but might be incompatible with previous releases   of the operating system. An alternative, %hs, is available. Should the
     * application use the secure module %hs?
     */
    public static final int STATUS_DLL_MIGHT_BE_INCOMPATIBLE = 0X8000002C;

    /**
     * The create operation stopped after reaching a symbolic   link.
     */
    public static final int STATUS_STOPPED_ON_SYMLINK = 0X8000002D;

    /**
     * The device has indicated that cleaning is necessary.
     */
    public static final int STATUS_DEVICE_REQUIRES_CLEANING = 0X80000288;

    /**
     * The device has indicated that its door is open.   Further operations require it closed and secured.
     */
    public static final int STATUS_DEVICE_DOOR_OPEN = 0X80000289;

    /**
     * Windows discovered a corruption in the file %hs. This   file has now been repaired. Check if any data in the file was lost because of   the corruption.
     */
    public static final int STATUS_DATA_LOST_REPAIR = 0X80000803;

    /**
     * Debugger did not handle the exception.
     */
    public static final int DBG_EXCEPTION_NOT_HANDLED = 0X80010001;

    /**
     * The cluster node is already up.
     */
    public static final int STATUS_CLUSTER_NODE_ALREADY_UP = 0X80130001;

    /**
     * The cluster node is already down.
     */
    public static final int STATUS_CLUSTER_NODE_ALREADY_DOWN = 0X80130002;

    /**
     * The cluster network is already online.
     */
    public static final int STATUS_CLUSTER_NETWORK_ALREADY_ONLINE = 0X80130003;

    /**
     * The cluster network is already offline.
     */
    public static final int STATUS_CLUSTER_NETWORK_ALREADY_OFFLINE = 0X80130004;

    /**
     * The cluster node is already a member of the cluster.
     */
    public static final int STATUS_CLUSTER_NODE_ALREADY_MEMBER = 0X80130005;

    /**
     * The log could not be set to the requested size.
     */
    public static final int STATUS_COULD_NOT_RESIZE_LOG = 0X80190009;

    /**
     * There is no transaction metadata on the file.
     */
    public static final int STATUS_NO_TXF_METADATA = 0X80190029;

    /**
     * The file cannot be recovered because there is a handle   still open on it.
     */
    public static final int STATUS_CANT_RECOVER_WITH_HANDLE_OPEN = 0X80190031;

    /**
     * Transaction metadata is already present on this file   and cannot be superseded.
     */
    public static final int STATUS_TXF_METADATA_ALREADY_PRESENT = 0X80190041;

    /**
     * A transaction scope could not be entered because the   scope handler has not been initialized.
     */
    public static final int STATUS_TRANSACTION_SCOPE_CALLBACKS_NOT_SET = 0X80190042;

    /**
     * {Display Driver Stopped Responding and recovered} The   %hs display driver has stopped working normally. The recovery had been   performed.
     */
    public static final int STATUS_VIDEO_HUNG_DISPLAY_DRIVER_THREAD_RECOVERED = 0X801B00EB;

    /**
     * {Buffer too small} The buffer is too small to contain   the entry. No information has been written to the buffer.
     */
    public static final int STATUS_FLT_BUFFER_TOO_SMALL = 0X801C0001;

    /**
     * Volume metadata read or write is incomplete.
     */
    public static final int STATUS_FVE_PARTIAL_METADATA = 0X80210001;

    /**
     * BitLocker encryption keys were ignored because the   volume was in a transient state.
     */
    public static final int STATUS_FVE_TRANSIENT_STATE = 0X80210002;

    /**
     * {Operation Failed} The requested operation was   unsuccessful.
     */
    public static final int STATUS_UNSUCCESSFUL = 0XC0000001;

    /**
     * {Not Implemented} The requested operation is not   implemented.
     */
    public static final int STATUS_NOT_IMPLEMENTED = 0XC0000002;

    /**
     * {Invalid Parameter} The specified information class is   not a valid information class for the specified object.
     */
    public static final int STATUS_INVALID_INFO_CLASS = 0XC0000003;

    /**
     * The specified information record length does not match   the length that is required for the specified information class.
     */
    public static final int STATUS_INFO_LENGTH_MISMATCH = 0XC0000004;

    /**
     * The instruction at 0x%08lx referenced memory at   0x%08lx. The memory could not be %s.
     */
    public static final int STATUS_ACCESS_VIOLATION = 0XC0000005;

    /**
     * The instruction at 0x%08lx referenced memory at   0x%08lx. The required data was not placed into memory because of an I/O error   status of 0x%08lx.
     */
    public static final int STATUS_IN_PAGE_ERROR = 0XC0000006;

    /**
     * The page file quota for the process has been   exhausted.
     */
    public static final int STATUS_PAGEFILE_QUOTA = 0XC0000007;

    /**
     * An invalid HANDLE was specified.
     */
    public static final int STATUS_INVALID_HANDLE = 0XC0000008;

    /**
     * An invalid initial stack was specified in a call to   NtCreateThread.
     */
    public static final int STATUS_BAD_INITIAL_STACK = 0XC0000009;

    /**
     * An invalid initial start address was specified in a   call to NtCreateThread.
     */
    public static final int STATUS_BAD_INITIAL_PC = 0XC000000A;

    /**
     * An invalid client ID was specified.
     */
    public static final int STATUS_INVALID_CID = 0XC000000B;

    /**
     * An attempt was made to cancel or set a timer that has   an associated APC and the specified thread is not the thread that originally   set the timer with an associated APC routine.
     */
    public static final int STATUS_TIMER_NOT_CANCELED = 0XC000000C;

    /**
     * An invalid parameter was passed to a service or   function.
     */
    public static final int STATUS_INVALID_PARAMETER = 0XC000000D;

    /**
     * A device that does not exist was specified.
     */
    public static final int STATUS_NO_SUCH_DEVICE = 0XC000000E;

    /**
     * {File Not Found} The file %hs does not exist.
     */
    public static final int STATUS_NO_SUCH_FILE = 0XC000000F;

    /**
     * The specified request is not a valid operation for the   target device.
     */
    public static final int STATUS_INVALID_DEVICE_REQUEST = 0XC0000010;

    /**
     * The end-of-file marker has been reached. There is no   valid data in the file beyond this marker.
     */
    public static final int STATUS_END_OF_FILE = 0XC0000011;

    /**
     * {Wrong Volume} The wrong volume is in the drive.   Insert volume %hs into drive %hs.
     */
    public static final int STATUS_WRONG_VOLUME = 0XC0000012;

    /**
     * {No Disk} There is no disk in the drive. Insert a disk   into drive %hs.
     */
    public static final int STATUS_NO_MEDIA_IN_DEVICE = 0XC0000013;

    /**
     * {Unknown Disk Format} The disk in drive %hs is not   formatted properly. Check the disk, and reformat it, if needed.
     */
    public static final int STATUS_UNRECOGNIZED_MEDIA = 0XC0000014;

    /**
     * {Sector Not Found} The specified sector does not   exist.
     */
    public static final int STATUS_NONEXISTENT_SECTOR = 0XC0000015;

    /**
     * {Still Busy} The specified I/O request packet (IRP)   cannot be disposed of because the I/O operation is not complete.
     */
    public static final int STATUS_MORE_PROCESSING_REQUIRED = 0XC0000016;

    /**
     * {Not Enough Quota} Not enough virtual memory or paging   file quota is available to complete the specified operation.
     */
    public static final int STATUS_NO_MEMORY = 0XC0000017;

    /**
     * {Conflicting Address Range} The specified address   range conflicts with the address space.
     */
    public static final int STATUS_CONFLICTING_ADDRESSES = 0XC0000018;

    /**
     * The address range to unmap is not a mapped view.
     */
    public static final int STATUS_NOT_MAPPED_VIEW = 0XC0000019;

    /**
     * The virtual memory cannot be freed.
     */
    public static final int STATUS_UNABLE_TO_FREE_VM = 0XC000001A;

    /**
     * The specified section cannot be deleted.
     */
    public static final int STATUS_UNABLE_TO_DELETE_SECTION = 0XC000001B;

    /**
     * An invalid system service was specified in a system   service call.
     */
    public static final int STATUS_INVALID_SYSTEM_SERVICE = 0XC000001C;

    /**
     * {EXCEPTION} Illegal Instruction An attempt was made to   execute an illegal instruction.
     */
    public static final int STATUS_ILLEGAL_INSTRUCTION = 0XC000001D;

    /**
     * {Invalid Lock Sequence} An attempt was made to execute   an invalid lock sequence.
     */
    public static final int STATUS_INVALID_LOCK_SEQUENCE = 0XC000001E;

    /**
     * {Invalid Mapping} An attempt was made to create a view   for a section that is bigger than the section.
     */
    public static final int STATUS_INVALID_VIEW_SIZE = 0XC000001F;

    /**
     * {Bad File} The attributes of the specified mapping   file for a section of memory cannot be read.
     */
    public static final int STATUS_INVALID_FILE_FOR_SECTION = 0XC0000020;

    /**
     * {Already Committed} The specified address range is   already committed.
     */
    public static final int STATUS_ALREADY_COMMITTED = 0XC0000021;

    /**
     * {Access Denied} A process has requested access to an   object but has not been granted those access rights.
     */
    public static final int STATUS_ACCESS_DENIED = 0XC0000022;

    /**
     * {Buffer Too Small} The buffer is too small to contain   the entry. No information has been written to the buffer.
     */
    public static final int STATUS_BUFFER_TOO_SMALL = 0XC0000023;

    /**
     * {Wrong Type} There is a mismatch between the type of   object that is required by the requested operation and the type of object   that is specified in the request.
     */
    public static final int STATUS_OBJECT_TYPE_MISMATCH = 0XC0000024;

    /**
     * {EXCEPTION} Cannot Continue Windows cannot continue   from this exception.
     */
    public static final int STATUS_NONCONTINUABLE_EXCEPTION = 0XC0000025;

    /**
     * An invalid exception disposition was returned by an   exception handler.
     */
    public static final int STATUS_INVALID_DISPOSITION = 0XC0000026;

    /**
     * Unwind exception code.
     */
    public static final int STATUS_UNWIND = 0XC0000027;

    /**
     * An invalid or unaligned stack was encountered during   an unwind operation.
     */
    public static final int STATUS_BAD_STACK = 0XC0000028;

    /**
     * An invalid unwind target was encountered during an   unwind operation.
     */
    public static final int STATUS_INVALID_UNWIND_TARGET = 0XC0000029;

    /**
     * An attempt was made to unlock a page of memory that   was not locked.
     */
    public static final int STATUS_NOT_LOCKED = 0XC000002A;

    /**
     * A device parity error on an I/O operation.
     */
    public static final int STATUS_PARITY_ERROR = 0XC000002B;

    /**
     * An attempt was made to decommit uncommitted virtual   memory.
     */
    public static final int STATUS_UNABLE_TO_DECOMMIT_VM = 0XC000002C;

    /**
     * An attempt was made to change the attributes on memory   that has not been committed.
     */
    public static final int STATUS_NOT_COMMITTED = 0XC000002D;

    /**
     * Invalid object attributes specified to NtCreatePort or   invalid port attributes specified to NtConnectPort.
     */
    public static final int STATUS_INVALID_PORT_ATTRIBUTES = 0XC000002E;

    /**
     * The length of the message that was passed to   NtRequestPort or NtRequestWaitReplyPort is longer than the maximum message   that is allowed by the port.
     */
    public static final int STATUS_PORT_MESSAGE_TOO_LONG = 0XC000002F;

    /**
     * An invalid combination of parameters was specified.
     */
    public static final int STATUS_INVALID_PARAMETER_MIX = 0XC0000030;

    /**
     * An attempt was made to lower a quota limit below the   current usage.
     */
    public static final int STATUS_INVALID_QUOTA_LOWER = 0XC0000031;

    /**
     * {Corrupt Disk} The file system structure on the disk   is corrupt and unusable. Run the Chkdsk utility on the volume %hs.
     */
    public static final int STATUS_DISK_CORRUPT_ERROR = 0XC0000032;

    /**
     * The object name is invalid.
     */
    public static final int STATUS_OBJECT_NAME_INVALID = 0XC0000033;

    /**
     * The object name is not found.
     */
    public static final int STATUS_OBJECT_NAME_NOT_FOUND = 0XC0000034;

    /**
     * The object name already exists.
     */
    public static final int STATUS_OBJECT_NAME_COLLISION = 0XC0000035;

    /**
     * An attempt was made to send a message to a   disconnected communication port.
     */
    public static final int STATUS_PORT_DISCONNECTED = 0XC0000037;

    /**
     * An attempt was made to attach to a device that was   already attached to another device.
     */
    public static final int STATUS_DEVICE_ALREADY_ATTACHED = 0XC0000038;

    /**
     * The object path component was not a directory object.
     */
    public static final int STATUS_OBJECT_PATH_INVALID = 0XC0000039;

    /**
     * {Path Not Found} The path %hs does not exist.
     */
    public static final int STATUS_OBJECT_PATH_NOT_FOUND = 0XC000003A;

    /**
     * The object path component was not a directory object.
     */
    public static final int STATUS_OBJECT_PATH_SYNTAX_BAD = 0XC000003B;

    /**
     * {Data Overrun} A data overrun error occurred.
     */
    public static final int STATUS_DATA_OVERRUN = 0XC000003C;

    /**
     * {Data Late} A data late error occurred.
     */
    public static final int STATUS_DATA_LATE_ERROR = 0XC000003D;

    /**
     * {Data Error} An error occurred in reading or writing   data.
     */
    public static final int STATUS_DATA_ERROR = 0XC000003E;

    /**
     * {Bad CRC} A cyclic redundancy check (CRC) checksum   error occurred.
     */
    public static final int STATUS_CRC_ERROR = 0XC000003F;

    /**
     * {Section Too Large} The specified section is too big   to map the file.
     */
    public static final int STATUS_SECTION_TOO_BIG = 0XC0000040;

    /**
     * The NtConnectPort request is refused.
     */
    public static final int STATUS_PORT_CONNECTION_REFUSED = 0XC0000041;

    /**
     * The type of port handle is invalid for the operation   that is requested.
     */
    public static final int STATUS_INVALID_PORT_HANDLE = 0XC0000042;

    /**
     * A file cannot be opened because the share access flags   are incompatible.
     */
    public static final int STATUS_SHARING_VIOLATION = 0XC0000043;

    /**
     * Insufficient quota exists to complete the operation.
     */
    public static final int STATUS_QUOTA_EXCEEDED = 0XC0000044;

    /**
     * The specified page protection was not valid.
     */
    public static final int STATUS_INVALID_PAGE_PROTECTION = 0XC0000045;

    /**
     * An attempt to release a mutant object was made by a thread   that was not the owner of the mutant object.
     */
    public static final int STATUS_MUTANT_NOT_OWNED = 0XC0000046;

    /**
     * An attempt was made to release a semaphore such that   its maximum count would have been exceeded.
     */
    public static final int STATUS_SEMAPHORE_LIMIT_EXCEEDED = 0XC0000047;

    /**
     * An attempt was made to set the DebugPort or   ExceptionPort of a process, but a port already exists in the process, or an   attempt was made to set the CompletionPort of a file but a port was already   set in the
     * file, or an attempt was made to set the associated completion port   of an ALPC port but it is already set.
     */
    public static final int STATUS_PORT_ALREADY_SET = 0XC0000048;

    /**
     * An attempt was made to query image information on a   section that does not map an image.
     */
    public static final int STATUS_SECTION_NOT_IMAGE = 0XC0000049;

    /**
     * An attempt was made to suspend a thread whose suspend   count was at its maximum.
     */
    public static final int STATUS_SUSPEND_COUNT_EXCEEDED = 0XC000004A;

    /**
     * An attempt was made to suspend a thread that has begun   termination.
     */
    public static final int STATUS_THREAD_IS_TERMINATING = 0XC000004B;

    /**
     * An attempt was made to set the working set limit to an   invalid value (for example, the minimum greater than maximum).
     */
    public static final int STATUS_BAD_WORKING_SET_LIMIT = 0XC000004C;

    /**
     * A section was created to map a file that is not   compatible with an already existing section that maps the same file.
     */
    public static final int STATUS_INCOMPATIBLE_FILE_MAP = 0XC000004D;

    /**
     * A view to a section specifies a protection that is   incompatible with the protection of the initial view.
     */
    public static final int STATUS_SECTION_PROTECTION = 0XC000004E;

    /**
     * An operation involving EAs failed because the file   system does not support EAs.
     */
    public static final int STATUS_EAS_NOT_SUPPORTED = 0XC000004F;

    /**
     * An EA operation failed because the EA set is too   large.
     */
    public static final int STATUS_EA_TOO_LARGE = 0XC0000050;

    /**
     * An EA operation failed because the name or EA index is   invalid.
     */
    public static final int STATUS_NONEXISTENT_EA_ENTRY = 0XC0000051;

    /**
     * The file for which EAs were requested has no EAs.
     */
    public static final int STATUS_NO_EAS_ON_FILE = 0XC0000052;

    /**
     * The EA is corrupt and cannot be read.
     */
    public static final int STATUS_EA_CORRUPT_ERROR = 0XC0000053;

    /**
     * A requested read/write cannot be granted due to a   conflicting file lock.
     */
    public static final int STATUS_FILE_LOCK_CONFLICT = 0XC0000054;

    /**
     * A requested file lock cannot be granted due to other   existing locks.
     */
    public static final int STATUS_LOCK_NOT_GRANTED = 0XC0000055;

    /**
     * A non-close operation has been requested of a file   object that has a delete pending.
     */
    public static final int STATUS_DELETE_PENDING = 0XC0000056;

    /**
     * An attempt was made to set the control attribute on a   file. This attribute is not supported in the destination file system.
     */
    public static final int STATUS_CTL_FILE_NOT_SUPPORTED = 0XC0000057;

    /**
     * Indicates a revision number that was encountered or   specified is not one that is known by the service. It might be a more recent   revision than the service is aware of.
     */
    public static final int STATUS_UNKNOWN_REVISION = 0XC0000058;

    /**
     * Indicates that two revision levels are incompatible.
     */
    public static final int STATUS_REVISION_MISMATCH = 0XC0000059;

    /**
     * Indicates a particular security ID cannot be assigned   as the owner of an object.
     */
    public static final int STATUS_INVALID_OWNER = 0XC000005A;

    /**
     * Indicates a particular security ID cannot be assigned   as the primary group of an object.
     */
    public static final int STATUS_INVALID_PRIMARY_GROUP = 0XC000005B;

    /**
     * An attempt has been made to operate on an   impersonation token by a thread that is not currently impersonating a client.
     */
    public static final int STATUS_NO_IMPERSONATION_TOKEN = 0XC000005C;

    /**
     * A mandatory group cannot be disabled.
     */
    public static final int STATUS_CANT_DISABLE_MANDATORY = 0XC000005D;

    /**
     * No logon servers are currently available to service   the logon request.
     */
    public static final int STATUS_NO_LOGON_SERVERS = 0XC000005E;

    /**
     * A specified logon session does not exist. It might   already have been terminated.
     */
    public static final int STATUS_NO_SUCH_LOGON_SESSION = 0XC000005F;

    /**
     * A specified privilege does not exist.
     */
    public static final int STATUS_NO_SUCH_PRIVILEGE = 0XC0000060;

    /**
     * A required privilege is not held by the client.
     */
    public static final int STATUS_PRIVILEGE_NOT_HELD = 0XC0000061;

    /**
     * The name provided is not a properly formed account   name.
     */
    public static final int STATUS_INVALID_ACCOUNT_NAME = 0XC0000062;

    /**
     * The specified account already exists.
     */
    public static final int STATUS_USER_EXISTS = 0XC0000063;

    /**
     * The specified account does not exist.
     */
    public static final int STATUS_NO_SUCH_USER = 0XC0000064;

    /**
     * The specified group already exists.
     */
    public static final int STATUS_GROUP_EXISTS = 0XC0000065;

    /**
     * The specified group does not exist.
     */
    public static final int STATUS_NO_SUCH_GROUP = 0XC0000066;

    /**
     * The specified user account is already in the specified   group account. Also used to indicate a group cannot be deleted because it   contains a member.
     */
    public static final int STATUS_MEMBER_IN_GROUP = 0XC0000067;

    /**
     * The specified user account is not a member of the   specified group account.
     */
    public static final int STATUS_MEMBER_NOT_IN_GROUP = 0XC0000068;

    /**
     * Indicates the requested operation would disable or   delete the last remaining administration account. This is not allowed to   prevent creating a situation in which the system cannot be administrated.
     */
    public static final int STATUS_LAST_ADMIN = 0XC0000069;

    /**
     * When trying to update a password, this return status   indicates that the value provided as the current password is not correct.
     */
    public static final int STATUS_WRONG_PASSWORD = 0XC000006A;

    /**
     * When trying to update a password, this return status   indicates that the value provided for the new password contains values that   are not allowed in passwords.
     */
    public static final int STATUS_ILL_FORMED_PASSWORD = 0XC000006B;

    /**
     * When trying to update a password, this status   indicates that some password update rule has been violated. For example, the   password might not meet length criteria.
     */
    public static final int STATUS_PASSWORD_RESTRICTION = 0XC000006C;

    /**
     * The attempted logon is invalid. This is either due to   a bad username or authentication information.
     */
    public static final int STATUS_LOGON_FAILURE = 0XC000006D;

    /**
     * Indicates a referenced user name and authentication   information are valid, but some user account restriction has prevented   successful authentication (such as time-of-day restrictions).
     */
    public static final int STATUS_ACCOUNT_RESTRICTION = 0XC000006E;

    /**
     * The user account has time restrictions and cannot be   logged onto at this time.
     */
    public static final int STATUS_INVALID_LOGON_HOURS = 0XC000006F;

    /**
     * The user account is restricted so that it cannot be   used to log on from the source workstation.
     */
    public static final int STATUS_INVALID_WORKSTATION = 0XC0000070;

    /**
     * The user account password has expired.
     */
    public static final int STATUS_PASSWORD_EXPIRED = 0XC0000071;

    /**
     * The referenced account is currently disabled and   cannot be logged on to.
     */
    public static final int STATUS_ACCOUNT_DISABLED = 0XC0000072;

    /**
     * None of the information to be translated has been   translated.
     */
    public static final int STATUS_NONE_MAPPED = 0XC0000073;

    /**
     * The number of LUIDs requested cannot be allocated with   a single allocation.
     */
    public static final int STATUS_TOO_MANY_LUIDS_REQUESTED = 0XC0000074;

    /**
     * Indicates there are no more LUIDs to allocate.
     */
    public static final int STATUS_LUIDS_EXHAUSTED = 0XC0000075;

    /**
     * Indicates the sub-authority value is invalid for the   particular use.
     */
    public static final int STATUS_INVALID_SUB_AUTHORITY = 0XC0000076;

    /**
     * Indicates the ACL structure is not valid.
     */
    public static final int STATUS_INVALID_ACL = 0XC0000077;

    /**
     * Indicates the SID structure is not valid.
     */
    public static final int STATUS_INVALID_SID = 0XC0000078;

    /**
     * Indicates the SECURITY_DESCRIPTOR structure is not   valid.
     */
    public static final int STATUS_INVALID_SECURITY_DESCR = 0XC0000079;

    /**
     * Indicates the specified procedure address cannot be   found in the DLL.
     */
    public static final int STATUS_PROCEDURE_NOT_FOUND = 0XC000007A;

    /**
     * {Bad Image} %hs is either not designed to run on   Windows or it contains an error. Try installing the program again using the   original installation media or contact your system administrator or the   software
     * vendor for support.
     */
    public static final int STATUS_INVALID_IMAGE_FORMAT = 0XC000007B;

    /**
     * An attempt was made to reference a token that does not   exist. This is typically done by referencing the token that is associated   with a thread when the thread is not impersonating a client.
     */
    public static final int STATUS_NO_TOKEN = 0XC000007C;

    /**
     * Indicates that an attempt to build either an inherited   ACL or ACE was not successful. This can be caused by a number of things. One   of the more probable causes is the replacement of a CreatorId with a SID that
     * did not fit into the ACE or ACL.
     */
    public static final int STATUS_BAD_INHERITANCE_ACL = 0XC000007D;

    /**
     * The range specified in NtUnlockFile was not locked.
     */
    public static final int STATUS_RANGE_NOT_LOCKED = 0XC000007E;

    /**
     * An operation failed because the disk was full.
     */
    public static final int STATUS_DISK_FULL = 0XC000007F;

    /**
     * The GUID allocation server is disabled at the moment.
     */
    public static final int STATUS_SERVER_DISABLED = 0XC0000080;

    /**
     * The GUID allocation server is enabled at the moment.
     */
    public static final int STATUS_SERVER_NOT_DISABLED = 0XC0000081;

    /**
     * Too many GUIDs were requested from the allocation   server at once.
     */
    public static final int STATUS_TOO_MANY_GUIDS_REQUESTED = 0XC0000082;

    /**
     * The GUIDs could not be allocated because the Authority   Agent was exhausted.
     */
    public static final int STATUS_GUIDS_EXHAUSTED = 0XC0000083;

    /**
     * The value provided was an invalid value for an   identifier authority.
     */
    public static final int STATUS_INVALID_ID_AUTHORITY = 0XC0000084;

    /**
     * No more authority agent values are available for the   particular identifier authority value.
     */
    public static final int STATUS_AGENTS_EXHAUSTED = 0XC0000085;

    /**
     * An invalid volume label has been specified.
     */
    public static final int STATUS_INVALID_VOLUME_LABEL = 0XC0000086;

    /**
     * A mapped section could not be extended.
     */
    public static final int STATUS_SECTION_NOT_EXTENDED = 0XC0000087;

    /**
     * Specified section to flush does not map a data file.
     */
    public static final int STATUS_NOT_MAPPED_DATA = 0XC0000088;

    /**
     * Indicates the specified image file did not contain a   resource section.
     */
    public static final int STATUS_RESOURCE_DATA_NOT_FOUND = 0XC0000089;

    /**
     * Indicates the specified resource type cannot be found   in the image file.
     */
    public static final int STATUS_RESOURCE_TYPE_NOT_FOUND = 0XC000008A;

    /**
     * Indicates the specified resource name cannot be found   in the image file.
     */
    public static final int STATUS_RESOURCE_NAME_NOT_FOUND = 0XC000008B;

    /**
     * {EXCEPTION} Array bounds exceeded.
     */
    public static final int STATUS_ARRAY_BOUNDS_EXCEEDED = 0XC000008C;

    /**
     * {EXCEPTION} Floating-point denormal operand.
     */
    public static final int STATUS_FLOAT_DENORMAL_OPERAND = 0XC000008D;

    /**
     * {EXCEPTION} Floating-point division by zero.
     */
    public static final int STATUS_FLOAT_DIVIDE_BY_ZERO = 0XC000008E;

    /**
     * {EXCEPTION} Floating-point inexact result.
     */
    public static final int STATUS_FLOAT_INEXACT_RESULT = 0XC000008F;

    /**
     * {EXCEPTION} Floating-point invalid operation.
     */
    public static final int STATUS_FLOAT_INVALID_OPERATION = 0XC0000090;

    /**
     * {EXCEPTION} Floating-point overflow.
     */
    public static final int STATUS_FLOAT_OVERFLOW = 0XC0000091;

    /**
     * {EXCEPTION} Floating-point stack check.
     */
    public static final int STATUS_FLOAT_STACK_CHECK = 0XC0000092;

    /**
     * {EXCEPTION} Floating-point underflow.
     */
    public static final int STATUS_FLOAT_UNDERFLOW = 0XC0000093;

    /**
     * {EXCEPTION} Integer division by zero.
     */
    public static final int STATUS_INTEGER_DIVIDE_BY_ZERO = 0XC0000094;

    /**
     * {EXCEPTION} Integer overflow.
     */
    public static final int STATUS_INTEGER_OVERFLOW = 0XC0000095;

    /**
     * {EXCEPTION} Privileged instruction.
     */
    public static final int STATUS_PRIVILEGED_INSTRUCTION = 0XC0000096;

    /**
     * An attempt was made to install more paging files than   the system supports.
     */
    public static final int STATUS_TOO_MANY_PAGING_FILES = 0XC0000097;

    /**
     * The volume for a file has been externally altered such   that the opened file is no longer valid.
     */
    public static final int STATUS_FILE_INVALID = 0XC0000098;

    /**
     * When a block of memory is allotted for future updates,   such as the memory allocated to hold discretionary access control and primary   group information, successive updates might exceed the amount of memory
     * originally allotted. Because a quota might already have been charged to   several processes that have handles to the object, it is not reasonable to   alter the size of the allocated memory. Instead, a request
     * that requires more   memory than has been allotted must fail and the   STATUS_ALLOTTED_SPACE_EXCEEDED error returned.
     */
    public static final int STATUS_ALLOTTED_SPACE_EXCEEDED = 0XC0000099;

    /**
     * Insufficient system resources exist to complete the   API.
     */
    public static final int STATUS_INSUFFICIENT_RESOURCES = 0XC000009A;

    /**
     * An attempt has been made to open a DFS exit path   control file.
     */
    public static final int STATUS_DFS_EXIT_PATH_FOUND = 0XC000009B;

    /**
     * There are bad blocks (sectors) on the hard disk.
     */
    public static final int STATUS_DEVICE_DATA_ERROR = 0XC000009C;

    /**
     * There is bad cabling, non-termination, or the   controller is not able to obtain access to the hard disk.
     */
    public static final int STATUS_DEVICE_NOT_CONNECTED = 0XC000009D;

    /**
     * Virtual memory cannot be freed because the base   address is not the base of the region and a region size of zero was   specified.
     */
    public static final int STATUS_FREE_VM_NOT_AT_BASE = 0XC000009F;

    /**
     * An attempt was made to free virtual memory that is not   allocated.
     */
    public static final int STATUS_MEMORY_NOT_ALLOCATED = 0XC00000A0;

    /**
     * The working set is not big enough to allow the   requested pages to be locked.
     */
    public static final int STATUS_WORKING_SET_QUOTA = 0XC00000A1;

    /**
     * {Write Protect Error} The disk cannot be written to   because it is write-protected. Remove the write protection from the volume   %hs in drive %hs.
     */
    public static final int STATUS_MEDIA_WRITE_PROTECTED = 0XC00000A2;

    /**
     * {Drive Not Ready} The drive is not ready for use; its   door might be open. Check drive %hs and make sure that a disk is inserted and   that the drive door is closed.
     */
    public static final int STATUS_DEVICE_NOT_READY = 0XC00000A3;

    /**
     * The specified attributes are invalid or are   incompatible with the attributes for the group as a whole.
     */
    public static final int STATUS_INVALID_GROUP_ATTRIBUTES = 0XC00000A4;

    /**
     * A specified impersonation level is invalid. Also used   to indicate that a required impersonation level was not provided.
     */
    public static final int STATUS_BAD_IMPERSONATION_LEVEL = 0XC00000A5;

    /**
     * An attempt was made to open an anonymous-level token.   Anonymous tokens cannot be opened.
     */
    public static final int STATUS_CANT_OPEN_ANONYMOUS = 0XC00000A6;

    /**
     * The validation information class requested was   invalid.
     */
    public static final int STATUS_BAD_VALIDATION_CLASS = 0XC00000A7;

    /**
     * The type of a token object is inappropriate for its   attempted use.
     */
    public static final int STATUS_BAD_TOKEN_TYPE = 0XC00000A8;

    /**
     * The type of a token object is inappropriate for its   attempted use.
     */
    public static final int STATUS_BAD_MASTER_BOOT_RECORD = 0XC00000A9;

    /**
     * An attempt was made to execute an instruction at an   unaligned address and the host system does not support unaligned instruction   references.
     */
    public static final int STATUS_INSTRUCTION_MISALIGNMENT = 0XC00000AA;

    /**
     * The maximum named pipe instance count has been   reached.
     */
    public static final int STATUS_INSTANCE_NOT_AVAILABLE = 0XC00000AB;

    /**
     * An instance of a named pipe cannot be found in the   listening state.
     */
    public static final int STATUS_PIPE_NOT_AVAILABLE = 0XC00000AC;

    /**
     * The named pipe is not in the connected or closing   state.
     */
    public static final int STATUS_INVALID_PIPE_STATE = 0XC00000AD;

    /**
     * The specified pipe is set to complete operations and   there are current I/O operations queued so that it cannot be changed to queue   operations.
     */
    public static final int STATUS_PIPE_BUSY = 0XC00000AE;

    /**
     * The specified handle is not open to the server end of   the named pipe.
     */
    public static final int STATUS_ILLEGAL_FUNCTION = 0XC00000AF;

    /**
     * The specified named pipe is in the disconnected state.
     */
    public static final int STATUS_PIPE_DISCONNECTED = 0XC00000B0;

    /**
     * The specified named pipe is in the closing state.
     */
    public static final int STATUS_PIPE_CLOSING = 0XC00000B1;

    /**
     * The specified named pipe is in the connected state.
     */
    public static final int STATUS_PIPE_CONNECTED = 0XC00000B2;

    /**
     * The specified named pipe is in the listening state.
     */
    public static final int STATUS_PIPE_LISTENING = 0XC00000B3;

    /**
     * The specified named pipe is not in message mode.
     */
    public static final int STATUS_INVALID_READ_MODE = 0XC00000B4;

    /**
     * {Device Timeout} The specified I/O operation on %hs   was not completed before the time-out period expired.
     */
    public static final int STATUS_IO_TIMEOUT = 0XC00000B5;

    /**
     * The specified file has been closed by another process.
     */
    public static final int STATUS_FILE_FORCED_CLOSED = 0XC00000B6;

    /**
     * Profiling is not started.
     */
    public static final int STATUS_PROFILING_NOT_STARTED = 0XC00000B7;

    /**
     * Profiling is not stopped.
     */
    public static final int STATUS_PROFILING_NOT_STOPPED = 0XC00000B8;

    /**
     * The passed ACL did not contain the minimum required   information.
     */
    public static final int STATUS_COULD_NOT_INTERPRET = 0XC00000B9;

    /**
     * The file that was specified as a target is a   directory, and the caller specified that it could be anything but a   directory.
     */
    public static final int STATUS_FILE_IS_A_DIRECTORY = 0XC00000BA;

    /**
     * The request is not supported.
     */
    public static final int STATUS_NOT_SUPPORTED = 0XC00000BB;

    /**
     * This remote computer is not listening.
     */
    public static final int STATUS_REMOTE_NOT_LISTENING = 0XC00000BC;

    /**
     * A duplicate name exists on the network.
     */
    public static final int STATUS_DUPLICATE_NAME = 0XC00000BD;

    /**
     * The network path cannot be located.
     */
    public static final int STATUS_BAD_NETWORK_PATH = 0XC00000BE;

    /**
     * The network is busy.
     */
    public static final int STATUS_NETWORK_BUSY = 0XC00000BF;

    /**
     * This device does not exist.
     */
    public static final int STATUS_DEVICE_DOES_NOT_EXIST = 0XC00000C0;

    /**
     * The network BIOS command limit has been reached.
     */
    public static final int STATUS_TOO_MANY_COMMANDS = 0XC00000C1;

    /**
     * An I/O adapter hardware error has occurred.
     */
    public static final int STATUS_ADAPTER_HARDWARE_ERROR = 0XC00000C2;

    /**
     * The network responded incorrectly.
     */
    public static final int STATUS_INVALID_NETWORK_RESPONSE = 0XC00000C3;

    /**
     * An unexpected network error occurred.
     */
    public static final int STATUS_UNEXPECTED_NETWORK_ERROR = 0XC00000C4;

    /**
     * The remote adapter is not compatible.
     */
    public static final int STATUS_BAD_REMOTE_ADAPTER = 0XC00000C5;

    /**
     * The print queue is full.
     */
    public static final int STATUS_PRINT_QUEUE_FULL = 0XC00000C6;

    /**
     * Space to store the file that is waiting to be printed   is not available on the server.
     */
    public static final int STATUS_NO_SPOOL_SPACE = 0XC00000C7;

    /**
     * The requested print file has been canceled.
     */
    public static final int STATUS_PRINT_CANCELLED = 0XC00000C8;

    /**
     * The network name was deleted.
     */
    public static final int STATUS_NETWORK_NAME_DELETED = 0XC00000C9;

    /**
     * Network access is denied.
     */
    public static final int STATUS_NETWORK_ACCESS_DENIED = 0XC00000CA;

    /**
     * {Incorrect Network Resource Type} The specified device   type (LPT, for example) conflicts with the actual device type on the remote   resource.
     */
    public static final int STATUS_BAD_DEVICE_TYPE = 0XC00000CB;

    /**
     * {Network Name Not Found} The specified share name   cannot be found on the remote server.
     */
    public static final int STATUS_BAD_NETWORK_NAME = 0XC00000CC;

    /**
     * The name limit for the network adapter card of the   local computer was exceeded.
     */
    public static final int STATUS_TOO_MANY_NAMES = 0XC00000CD;

    /**
     * The network BIOS session limit was exceeded.
     */
    public static final int STATUS_TOO_MANY_SESSIONS = 0XC00000CE;

    /**
     * File sharing has been temporarily paused.
     */
    public static final int STATUS_SHARING_PAUSED = 0XC00000CF;

    /**
     * No more connections can be made to this remote   computer at this time because the computer has already accepted the maximum   number of connections.
     */
    public static final int STATUS_REQUEST_NOT_ACCEPTED = 0XC00000D0;

    /**
     * Print or disk redirection is temporarily paused.
     */
    public static final int STATUS_REDIRECTOR_PAUSED = 0XC00000D1;

    /**
     * A network data fault occurred.
     */
    public static final int STATUS_NET_WRITE_FAULT = 0XC00000D2;

    /**
     * The number of active profiling objects is at the   maximum and no more can be started.
     */
    public static final int STATUS_PROFILING_AT_LIMIT = 0XC00000D3;

    /**
     * {Incorrect Volume} The destination file of a rename   request is located on a different device than the source of the rename   request.
     */
    public static final int STATUS_NOT_SAME_DEVICE = 0XC00000D4;

    /**
     * The specified file has been renamed and thus cannot be   modified.
     */
    public static final int STATUS_FILE_RENAMED = 0XC00000D5;

    /**
     * {Network Request Timeout} The session with a remote   server has been disconnected because the time-out interval for a request has   expired.
     */
    public static final int STATUS_VIRTUAL_CIRCUIT_CLOSED = 0XC00000D6;

    /**
     * Indicates an attempt was made to operate on the   security of an object that does not have security associated with it.
     */
    public static final int STATUS_NO_SECURITY_ON_OBJECT = 0XC00000D7;

    /**
     * Used to indicate that an operation cannot continue   without blocking for I/O.
     */
    public static final int STATUS_CANT_WAIT = 0XC00000D8;

    /**
     * Used to indicate that a read operation was done on an   empty pipe.
     */
    public static final int STATUS_PIPE_EMPTY = 0XC00000D9;

    /**
     * Configuration information could not be read from the   domain controller, either because the machine is unavailable or access has   been denied.
     */
    public static final int STATUS_CANT_ACCESS_DOMAIN_INFO = 0XC00000DA;

    /**
     * Indicates that a thread attempted to terminate itself   by default (called NtTerminateThread with NULL) and it was the last thread in   the current process.
     */
    public static final int STATUS_CANT_TERMINATE_SELF = 0XC00000DB;

    /**
     * Indicates the Sam Server was in the wrong state to   perform the desired operation.
     */
    public static final int STATUS_INVALID_SERVER_STATE = 0XC00000DC;

    /**
     * Indicates the domain was in the wrong state to perform   the desired operation.
     */
    public static final int STATUS_INVALID_DOMAIN_STATE = 0XC00000DD;

    /**
     * This operation is only allowed for the primary domain   controller of the domain.
     */
    public static final int STATUS_INVALID_DOMAIN_ROLE = 0XC00000DE;

    /**
     * The specified domain did not exist.
     */
    public static final int STATUS_NO_SUCH_DOMAIN = 0XC00000DF;

    /**
     * The specified domain already exists.
     */
    public static final int STATUS_DOMAIN_EXISTS = 0XC00000E0;

    /**
     * An attempt was made to exceed the limit on the number   of domains per server for this release.
     */
    public static final int STATUS_DOMAIN_LIMIT_EXCEEDED = 0XC00000E1;

    /**
     * An error status returned when the opportunistic lock   (oplock) request is denied.
     */
    public static final int STATUS_OPLOCK_NOT_GRANTED = 0XC00000E2;

    /**
     * An error status returned when an invalid opportunistic   lock (oplock) acknowledgment is received by a file system.
     */
    public static final int STATUS_INVALID_OPLOCK_PROTOCOL = 0XC00000E3;

    /**
     * This error indicates that the requested operation   cannot be completed due to a catastrophic media failure or an on-disk data   structure corruption.
     */
    public static final int STATUS_INTERNAL_DB_CORRUPTION = 0XC00000E4;

    /**
     * An internal error occurred.
     */
    public static final int STATUS_INTERNAL_ERROR = 0XC00000E5;

    /**
     * Indicates generic access types were contained in an   access mask which should already be mapped to non-generic access types.
     */
    public static final int STATUS_GENERIC_NOT_MAPPED = 0XC00000E6;

    /**
     * Indicates a security descriptor is not in the   necessary format (absolute or self-relative).
     */
    public static final int STATUS_BAD_DESCRIPTOR_FORMAT = 0XC00000E7;

    /**
     * An access to a user buffer failed at an expected point   in time. This code is defined because the caller does not want to accept   STATUS_ACCESS_VIOLATION in its filter.
     */
    public static final int STATUS_INVALID_USER_BUFFER = 0XC00000E8;

    /**
     * If an I/O error that is not defined in the standard   FsRtl filter is returned, it is converted to the following error, which is   guaranteed to be in the filter. In this case, information is lost; however,   the
     * filter correctly handles the exception.
     */
    public static final int STATUS_UNEXPECTED_IO_ERROR = 0XC00000E9;

    /**
     * If an MM error that is not defined in the standard   FsRtl filter is returned, it is converted to one of the following errors,   which are guaranteed to be in the filter. In this case, information is lost;
     * however, the filter correctly handles the exception.
     */
    public static final int STATUS_UNEXPECTED_MM_CREATE_ERR = 0XC00000EA;

    /**
     * If an MM error that is not defined in the standard   FsRtl filter is returned, it is converted to one of the following errors,   which are guaranteed to be in the filter. In this case, information is lost;
     * however, the filter correctly handles the exception.
     */
    public static final int STATUS_UNEXPECTED_MM_MAP_ERROR = 0XC00000EB;

    /**
     * If an MM error that is not defined in the standard   FsRtl filter is returned, it is converted to one of the following errors,   which are guaranteed to be in the filter. In this case, information is lost;
     * however, the filter correctly handles the exception.
     */
    public static final int STATUS_UNEXPECTED_MM_EXTEND_ERR = 0XC00000EC;

    /**
     * The requested action is restricted for use by logon   processes only. The calling process has not registered as a logon process.
     */
    public static final int STATUS_NOT_LOGON_PROCESS = 0XC00000ED;

    /**
     * An attempt has been made to start a new session   manager or LSA logon session by using an ID that is already in use.
     */
    public static final int STATUS_LOGON_SESSION_EXISTS = 0XC00000EE;

    /**
     * An invalid parameter was passed to a service or   function as the first argument.
     */
    public static final int STATUS_INVALID_PARAMETER_1 = 0XC00000EF;

    /**
     * An invalid parameter was passed to a service or   function as the second argument.
     */
    public static final int STATUS_INVALID_PARAMETER_2 = 0XC00000F0;

    /**
     * An invalid parameter was passed to a service or   function as the third argument.
     */
    public static final int STATUS_INVALID_PARAMETER_3 = 0XC00000F1;

    /**
     * An invalid parameter was passed to a service or   function as the fourth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_4 = 0XC00000F2;

    /**
     * An invalid parameter was passed to a service or   function as the fifth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_5 = 0XC00000F3;

    /**
     * An invalid parameter was passed to a service or   function as the sixth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_6 = 0XC00000F4;

    /**
     * An invalid parameter was passed to a service or   function as the seventh argument.
     */
    public static final int STATUS_INVALID_PARAMETER_7 = 0XC00000F5;

    /**
     * An invalid parameter was passed to a service or   function as the eighth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_8 = 0XC00000F6;

    /**
     * An invalid parameter was passed to a service or   function as the ninth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_9 = 0XC00000F7;

    /**
     * An invalid parameter was passed to a service or   function as the tenth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_10 = 0XC00000F8;

    /**
     * An invalid parameter was passed to a service or   function as the eleventh argument.
     */
    public static final int STATUS_INVALID_PARAMETER_11 = 0XC00000F9;

    /**
     * An invalid parameter was passed to a service or   function as the twelfth argument.
     */
    public static final int STATUS_INVALID_PARAMETER_12 = 0XC00000FA;

    /**
     * An attempt was made to access a network file, but the   network software was not yet started.
     */
    public static final int STATUS_REDIRECTOR_NOT_STARTED = 0XC00000FB;

    /**
     * An attempt was made to start the redirector, but the   redirector has already been started.
     */
    public static final int STATUS_REDIRECTOR_STARTED = 0XC00000FC;

    /**
     * A new guard page for the stack cannot be created.
     */
    public static final int STATUS_STACK_OVERFLOW = 0XC00000FD;

    /**
     * A specified authentication package is unknown.
     */
    public static final int STATUS_NO_SUCH_PACKAGE = 0XC00000FE;

    /**
     * A malformed function table was encountered during an   unwind operation.
     */
    public static final int STATUS_BAD_FUNCTION_TABLE = 0XC00000FF;

    /**
     * Indicates the specified environment variable name was   not found in the specified environment block.
     */
    public static final int STATUS_VARIABLE_NOT_FOUND = 0XC0000100;

    /**
     * Indicates that the directory trying to be deleted is   not empty.
     */
    public static final int STATUS_DIRECTORY_NOT_EMPTY = 0XC0000101;

    /**
     * {Corrupt File} The file or directory %hs is corrupt   and unreadable. Run the Chkdsk utility.
     */
    public static final int STATUS_FILE_CORRUPT_ERROR = 0XC0000102;

    /**
     * A requested opened file is not a directory.
     */
    public static final int STATUS_NOT_A_DIRECTORY = 0XC0000103;

    /**
     * The logon session is not in a state that is consistent   with the requested operation.
     */
    public static final int STATUS_BAD_LOGON_SESSION_STATE = 0XC0000104;

    /**
     * An internal LSA error has occurred. An authentication   package has requested the creation of a logon session but the ID of an   already existing logon session has been specified.
     */
    public static final int STATUS_LOGON_SESSION_COLLISION = 0XC0000105;

    /**
     * A specified name string is too long for its intended   use.
     */
    public static final int STATUS_NAME_TOO_LONG = 0XC0000106;

    /**
     * The user attempted to force close the files on a   redirected drive, but there were opened files on the drive, and the user did   not specify a sufficient level of force.
     */
    public static final int STATUS_FILES_OPEN = 0XC0000107;

    /**
     * The user attempted to force close the files on a   redirected drive, but there were opened directories on the drive, and the   user did not specify a sufficient level of force.
     */
    public static final int STATUS_CONNECTION_IN_USE = 0XC0000108;

    /**
     * RtlFindMessage could not locate the requested message   ID in the message table resource.
     */
    public static final int STATUS_MESSAGE_NOT_FOUND = 0XC0000109;

    /**
     * An attempt was made to duplicate an object handle into   or out of an exiting process.
     */
    public static final int STATUS_PROCESS_IS_TERMINATING = 0XC000010A;

    /**
     * Indicates an invalid value has been provided for the LogonType   requested.
     */
    public static final int STATUS_INVALID_LOGON_TYPE = 0XC000010B;

    /**
     * Indicates that an attempt was made to assign   protection to a file system file or directory and one of the SIDs in the   security descriptor could not be translated into a GUID that could be stored   by the file
     * system. This causes the protection attempt to fail, which might   cause a file creation attempt to fail.
     */
    public static final int STATUS_NO_GUID_TRANSLATION = 0XC000010C;

    /**
     * Indicates that an attempt has been made to impersonate   via a named pipe that has not yet been read from.
     */
    public static final int STATUS_CANNOT_IMPERSONATE = 0XC000010D;

    /**
     * Indicates that the specified image is already loaded.
     */
    public static final int STATUS_IMAGE_ALREADY_LOADED = 0XC000010E;

    /**
     * Indicates that an attempt was made to change the size   of the LDT for a process that has no LDT.
     */
    public static final int STATUS_NO_LDT = 0XC0000117;

    /**
     * Indicates that an attempt was made to grow an LDT by   setting its size, or that the size was not an even number of selectors.
     */
    public static final int STATUS_INVALID_LDT_SIZE = 0XC0000118;

    /**
     * Indicates that the starting value for the LDT   information was not an integral multiple of the selector size.
     */
    public static final int STATUS_INVALID_LDT_OFFSET = 0XC0000119;

    /**
     * Indicates that the user supplied an invalid descriptor   when trying to set up LDT descriptors.
     */
    public static final int STATUS_INVALID_LDT_DESCRIPTOR = 0XC000011A;

    /**
     * The specified image file did not have the correct   format. It appears to be NE format.
     */
    public static final int STATUS_INVALID_IMAGE_NE_FORMAT = 0XC000011B;

    /**
     * Indicates that the transaction state of a registry   subtree is incompatible with the requested operation. For example, a request   has been made to start a new transaction with one already in progress, or a
     * request has been made to apply a transaction when one is not currently in   progress.
     */
    public static final int STATUS_RXACT_INVALID_STATE = 0XC000011C;

    /**
     * Indicates an error has occurred during a registry   transaction commit. The database has been left in an unknown, but probably   inconsistent, state. The state of the registry transaction is left as   COMMITTING.
     */
    public static final int STATUS_RXACT_COMMIT_FAILURE = 0XC000011D;

    /**
     * An attempt was made to map a file of size zero with   the maximum size specified as zero.
     */
    public static final int STATUS_MAPPED_FILE_SIZE_ZERO = 0XC000011E;

    /**
     * Too many files are opened on a remote server. This   error should only be returned by the Windows redirector on a remote drive.
     */
    public static final int STATUS_TOO_MANY_OPENED_FILES = 0XC000011F;

    /**
     * The I/O request was canceled.
     */
    public static final int STATUS_CANCELLED = 0XC0000120;

    /**
     * An attempt has been made to remove a file or directory   that cannot be deleted.
     */
    public static final int STATUS_CANNOT_DELETE = 0XC0000121;

    /**
     * Indicates a name that was specified as a remote   computer name is syntactically invalid.
     */
    public static final int STATUS_INVALID_COMPUTER_NAME = 0XC0000122;

    /**
     * An I/O request other than close was performed on a file   after it was deleted, which can only happen to a request that did not   complete before the last handle was closed via NtClose.
     */
    public static final int STATUS_FILE_DELETED = 0XC0000123;

    /**
     * Indicates an operation that is incompatible with   built-in accounts has been attempted on a built-in (special) SAM account. For   example, built-in accounts cannot be deleted.
     */
    public static final int STATUS_SPECIAL_ACCOUNT = 0XC0000124;

    /**
     * The operation requested cannot be performed on the   specified group because it is a built-in special group.
     */
    public static final int STATUS_SPECIAL_GROUP = 0XC0000125;

    /**
     * The operation requested cannot be performed on the   specified user because it is a built-in special user.
     */
    public static final int STATUS_SPECIAL_USER = 0XC0000126;

    /**
     * Indicates a member cannot be removed from a group   because the group is currently the member\'s primary group.
     */
    public static final int STATUS_MEMBERS_PRIMARY_GROUP = 0XC0000127;

    /**
     * An I/O request other than close and several other   special case operations was attempted using a file object that had already   been closed.
     */
    public static final int STATUS_FILE_CLOSED = 0XC0000128;

    /**
     * Indicates a process has too many threads to perform   the requested action. For example, assignment of a primary token can be   performed only when a process has zero or one threads.
     */
    public static final int STATUS_TOO_MANY_THREADS = 0XC0000129;

    /**
     * An attempt was made to operate on a thread within a   specific process, but the specified thread is not in the specified process.
     */
    public static final int STATUS_THREAD_NOT_IN_PROCESS = 0XC000012A;

    /**
     * An attempt was made to establish a token for use as a   primary token but the token is already in use. A token can only be the   primary token of one process at a time.
     */
    public static final int STATUS_TOKEN_ALREADY_IN_USE = 0XC000012B;

    /**
     * The page file quota was exceeded.
     */
    public static final int STATUS_PAGEFILE_QUOTA_EXCEEDED = 0XC000012C;

    /**
     * {Out of Virtual Memory} Your system is low on virtual   memory. To ensure that Windows runs correctly, increase the size of your   virtual memory paging file. For more information, see Help.
     */
    public static final int STATUS_COMMITMENT_LIMIT = 0XC000012D;

    /**
     * The specified image file did not have the correct   format: it appears to be LE format.
     */
    public static final int STATUS_INVALID_IMAGE_LE_FORMAT = 0XC000012E;

    /**
     * The specified image file did not have the correct   format: it did not have an initial MZ.
     */
    public static final int STATUS_INVALID_IMAGE_NOT_MZ = 0XC000012F;

    /**
     * The specified image file did not have the correct   format: it did not have a proper e_lfarlc in the MZ header.
     */
    public static final int STATUS_INVALID_IMAGE_PROTECT = 0XC0000130;

    /**
     * The specified image file did not have the correct   format: it appears to be a 16-bit Windows image.
     */
    public static final int STATUS_INVALID_IMAGE_WIN_16 = 0XC0000131;

    /**
     * The Netlogon service cannot start because another   Netlogon service running in the domain conflicts with the specified role.
     */
    public static final int STATUS_LOGON_SERVER_CONFLICT = 0XC0000132;

    /**
     * The time at the primary domain controller is different   from the time at the backup domain controller or member server by too large   an amount.
     */
    public static final int STATUS_TIME_DIFFERENCE_AT_DC = 0XC0000133;

    /**
     * On applicable Windows Server releases, the SAM   database is significantly out of synchronization with the copy on the domain   controller. A complete synchronization is required.
     */
    public static final int STATUS_SYNCHRONIZATION_REQUIRED = 0XC0000134;

    /**
     * {Unable To Locate Component} This application has   failed to start because %hs was not found. Reinstalling the application might   fix this problem.
     */
    public static final int STATUS_DLL_NOT_FOUND = 0XC0000135;

    /**
     * The NtCreateFile API failed. This error should never   be returned to an application; it is a place holder for the Windows LAN   Manager Redirector to use in its internal error-mapping routines.
     */
    public static final int STATUS_OPEN_FAILED = 0XC0000136;

    /**
     * {Privilege Failed} The I/O permissions for the process   could not be changed.
     */
    public static final int STATUS_IO_PRIVILEGE_FAILED = 0XC0000137;

    /**
     * {Ordinal Not Found} The ordinal %ld could not be   located in the dynamic link library %hs.
     */
    public static final int STATUS_ORDINAL_NOT_FOUND = 0XC0000138;

    /**
     * {Entry Point Not Found} The procedure entry point %hs   could not be located in the dynamic link library %hs.
     */
    public static final int STATUS_ENTRYPOINT_NOT_FOUND = 0XC0000139;

    /**
     * {Application Exit by CTRL+C} The application   terminated as a result of a CTRL+C.
     */
    public static final int STATUS_CONTROL_C_EXIT = 0XC000013A;

    /**
     * {Virtual Circuit Closed} The network transport on your   computer has closed a network connection. There might or might not be I/O   requests outstanding.
     */
    public static final int STATUS_LOCAL_DISCONNECT = 0XC000013B;

    /**
     * {Virtual Circuit Closed} The network transport on a   remote computer has closed a network connection. There might or might not be   I/O requests outstanding.
     */
    public static final int STATUS_REMOTE_DISCONNECT = 0XC000013C;

    /**
     * {Insufficient Resources on Remote Computer} The remote   computer has insufficient resources to complete the network request. For   example, the remote computer might not have enough available memory to carry
     * out the request at this time.
     */
    public static final int STATUS_REMOTE_RESOURCES = 0XC000013D;

    /**
     * {Virtual Circuit Closed} An existing connection   (virtual circuit) has been broken at the remote computer. There is probably   something wrong with the network software protocol or the network hardware on   the
     * remote computer.
     */
    public static final int STATUS_LINK_FAILED = 0XC000013E;

    /**
     * {Virtual Circuit Closed} The network transport on your   computer has closed a network connection because it had to wait too long for   a response from the remote computer.
     */
    public static final int STATUS_LINK_TIMEOUT = 0XC000013F;

    /**
     * The connection handle that was given to the transport   was invalid.
     */
    public static final int STATUS_INVALID_CONNECTION = 0XC0000140;

    /**
     * The address handle that was given to the transport was   invalid.
     */
    public static final int STATUS_INVALID_ADDRESS = 0XC0000141;

    /**
     * {DLL Initialization Failed} Initialization of the   dynamic link library %hs failed. The process is terminating abnormally.
     */
    public static final int STATUS_DLL_INIT_FAILED = 0XC0000142;

    /**
     * {Missing System File} The required system file %hs is   bad or missing.
     */
    public static final int STATUS_MISSING_SYSTEMFILE = 0XC0000143;

    /**
     * {Application Error} The exception %s (0x%08lx)   occurred in the application at location 0x%08lx.
     */
    public static final int STATUS_UNHANDLED_EXCEPTION = 0XC0000144;

    /**
     * {Application Error} The application failed to   initialize properly (0x%lx). Click OK to terminate the application.
     */
    public static final int STATUS_APP_INIT_FAILURE = 0XC0000145;

    /**
     * {Unable to Create Paging File} The creation of the   paging file %hs failed (%lx). The requested size was %ld.
     */
    public static final int STATUS_PAGEFILE_CREATE_FAILED = 0XC0000146;

    /**
     * {No Paging File Specified} No paging file was   specified in the system configuration.
     */
    public static final int STATUS_NO_PAGEFILE = 0XC0000147;

    /**
     * {Incorrect System Call Level} An invalid level was   passed into the specified system call.
     */
    public static final int STATUS_INVALID_LEVEL = 0XC0000148;

    /**
     * {Incorrect Password to LAN Manager Server} You   specified an incorrect password to a LAN Manager 2.x or MS-NET server.
     */
    public static final int STATUS_WRONG_PASSWORD_CORE = 0XC0000149;

    /**
     * {EXCEPTION} A real-mode application issued a floating-point   instruction and floating-point hardware is not present.
     */
    public static final int STATUS_ILLEGAL_FLOAT_CONTEXT = 0XC000014A;

    /**
     * The pipe operation has failed because the other end of   the pipe has been closed.
     */
    public static final int STATUS_PIPE_BROKEN = 0XC000014B;

    /**
     * {The Registry Is Corrupt} The structure of one of the   files that contains registry data is corrupt; the image of the file in memory   is corrupt; or the file could not be recovered because the alternate copy or
     * log was absent or corrupt.
     */
    public static final int STATUS_REGISTRY_CORRUPT = 0XC000014C;

    /**
     * An I/O operation initiated by the Registry failed and   cannot be recovered. The registry could not read in, write out, or flush one   of the files that contain the system\'s image of the registry.
     */
    public static final int STATUS_REGISTRY_IO_FAILED = 0XC000014D;

    /**
     * An event pair synchronization operation was performed   using the thread-specific client/server event pair object, but no event pair   object was associated with the thread.
     */
    public static final int STATUS_NO_EVENT_PAIR = 0XC000014E;

    /**
     * The volume does not contain a recognized file system.   Be sure that all required file system drivers are loaded and that the volume   is not corrupt.
     */
    public static final int STATUS_UNRECOGNIZED_VOLUME = 0XC000014F;

    /**
     * No serial device was successfully initialized. The   serial driver will unload.
     */
    public static final int STATUS_SERIAL_NO_DEVICE_INITED = 0XC0000150;

    /**
     * The specified local group does not exist.
     */
    public static final int STATUS_NO_SUCH_ALIAS = 0XC0000151;

    /**
     * The specified account name is not a member of the   group.
     */
    public static final int STATUS_MEMBER_NOT_IN_ALIAS = 0XC0000152;

    /**
     * The specified account name is already a member of the   group.
     */
    public static final int STATUS_MEMBER_IN_ALIAS = 0XC0000153;

    /**
     * The specified local group already exists.
     */
    public static final int STATUS_ALIAS_EXISTS = 0XC0000154;

    /**
     * A requested type of logon (for example, interactive,   network, and service) is not granted by the local security policy of the   target system. Ask the system administrator to grant the necessary form of
     * logon.
     */
    public static final int STATUS_LOGON_NOT_GRANTED = 0XC0000155;

    /**
     * The maximum number of secrets that can be stored in a   single system was exceeded. The length and number of secrets is limited to   satisfy U.S. State Department export restrictions.
     */
    public static final int STATUS_TOO_MANY_SECRETS = 0XC0000156;

    /**
     * The length of a secret exceeds the maximum allowable   length. The length and number of secrets is limited to satisfy U.S. State   Department export restrictions.
     */
    public static final int STATUS_SECRET_TOO_LONG = 0XC0000157;

    /**
     * The local security authority (LSA) database contains   an internal inconsistency.
     */
    public static final int STATUS_INTERNAL_DB_ERROR = 0XC0000158;

    /**
     * The requested operation cannot be performed in   full-screen mode.
     */
    public static final int STATUS_FULLSCREEN_MODE = 0XC0000159;

    /**
     * During a logon attempt, the user\'s security context   accumulated too many security IDs. This is a very unusual situation. Remove   the user from some global or local groups to reduce the number of security   IDs
     * to incorporate into the security context.
     */
    public static final int STATUS_TOO_MANY_CONTEXT_IDS = 0XC000015A;

    /**
     * A user has requested a type of logon (for example,   interactive or network) that has not been granted. An administrator has   control over who can logon interactively and through the network.
     */
    public static final int STATUS_LOGON_TYPE_NOT_GRANTED = 0XC000015B;

    /**
     * The system has attempted to load or restore a file   into the registry, and the specified file is not in the format of a registry   file.
     */
    public static final int STATUS_NOT_REGISTRY_FILE = 0XC000015C;

    /**
     * An attempt was made to change a user password in the   security account manager without providing the necessary Windows   cross-encrypted password.
     */
    public static final int STATUS_NT_CROSS_ENCRYPTION_REQUIRED = 0XC000015D;

    /**
     * A domain server has an incorrect configuration.
     */
    public static final int STATUS_DOMAIN_CTRLR_CONFIG_ERROR = 0XC000015E;

    /**
     * An attempt was made to explicitly access the secondary   copy of information via a device control to the fault tolerance driver and   the secondary copy is not present in the system.
     */
    public static final int STATUS_FT_MISSING_MEMBER = 0XC000015F;

    /**
     * A configuration registry node that represents a driver   service entry was ill-formed and did not contain the required value entries.
     */
    public static final int STATUS_ILL_FORMED_SERVICE_ENTRY = 0XC0000160;

    /**
     * An illegal character was encountered. For a multibyte   character set, this includes a lead byte without a succeeding trail byte. For   the Unicode character set this includes the characters 0xFFFF and 0xFFFE.
     */
    public static final int STATUS_ILLEGAL_CHARACTER = 0XC0000161;

    /**
     * No mapping for the Unicode character exists in the   target multibyte code page.
     */
    public static final int STATUS_UNMAPPABLE_CHARACTER = 0XC0000162;

    /**
     * The Unicode character is not defined in the Unicode   character set that is installed on the system.
     */
    public static final int STATUS_UNDEFINED_CHARACTER = 0XC0000163;

    /**
     * The paging file cannot be created on a floppy disk.
     */
    public static final int STATUS_FLOPPY_VOLUME = 0XC0000164;

    /**
     * {Floppy Disk Error} While accessing a floppy disk, an   ID address mark was not found.
     */
    public static final int STATUS_FLOPPY_ID_MARK_NOT_FOUND = 0XC0000165;

    /**
     * {Floppy Disk Error} While accessing a floppy disk, the   track address from the sector ID field was found to be different from the   track address that is maintained by the controller.
     */
    public static final int STATUS_FLOPPY_WRONG_CYLINDER = 0XC0000166;

    /**
     * {Floppy Disk Error} The floppy disk controller   reported an error that is not recognized by the floppy disk driver.
     */
    public static final int STATUS_FLOPPY_UNKNOWN_ERROR = 0XC0000167;

    /**
     * {Floppy Disk Error} While accessing a floppy-disk, the   controller returned inconsistent results via its registers.
     */
    public static final int STATUS_FLOPPY_BAD_REGISTERS = 0XC0000168;

    /**
     * {Hard Disk Error} While accessing the hard disk, a   recalibrate operation failed, even after retries.
     */
    public static final int STATUS_DISK_RECALIBRATE_FAILED = 0XC0000169;

    /**
     * {Hard Disk Error} While accessing the hard disk, a   disk operation failed even after retries.
     */
    public static final int STATUS_DISK_OPERATION_FAILED = 0XC000016A;

    /**
     * {Hard Disk Error} While accessing the hard disk, a   disk controller reset was needed, but even that failed.
     */
    public static final int STATUS_DISK_RESET_FAILED = 0XC000016B;

    /**
     * An attempt was made to open a device that was sharing   an interrupt request (IRQ) with other devices. At least one other device that   uses that IRQ was already opened. Two concurrent opens of devices that share
     * an IRQ and only work via interrupts is not supported for the particular bus   type that the devices use.
     */
    public static final int STATUS_SHARED_IRQ_BUSY = 0XC000016C;

    /**
     * {FT Orphaning} A disk that is part of a fault-tolerant   volume can no longer be accessed.
     */
    public static final int STATUS_FT_ORPHANING = 0XC000016D;

    /**
     * The basic input/output system (BIOS) failed to connect   a system interrupt to the device or bus for which the device is connected.
     */
    public static final int STATUS_BIOS_FAILED_TO_CONNECT_INTERRUPT = 0XC000016E;

    /**
     * The tape could not be partitioned.
     */
    public static final int STATUS_PARTITION_FAILURE = 0XC0000172;

    /**
     * When accessing a new tape of a multi-volume partition,   the current blocksize is incorrect.
     */
    public static final int STATUS_INVALID_BLOCK_LENGTH = 0XC0000173;

    /**
     * The tape partition information could not be found when   loading a tape.
     */
    public static final int STATUS_DEVICE_NOT_PARTITIONED = 0XC0000174;

    /**
     * An attempt to lock the eject media mechanism failed.
     */
    public static final int STATUS_UNABLE_TO_LOCK_MEDIA = 0XC0000175;

    /**
     * An attempt to unload media failed.
     */
    public static final int STATUS_UNABLE_TO_UNLOAD_MEDIA = 0XC0000176;

    /**
     * The physical end of tape was detected.
     */
    public static final int STATUS_EOM_OVERFLOW = 0XC0000177;

    /**
     * {No Media} There is no media in the drive. Insert   media into drive %hs.
     */
    public static final int STATUS_NO_MEDIA = 0XC0000178;

    /**
     * A member could not be added to or removed from the   local group because the member does not exist.
     */
    public static final int STATUS_NO_SUCH_MEMBER = 0XC000017A;

    /**
     * A new member could not be added to a local group   because the member has the wrong account type.
     */
    public static final int STATUS_INVALID_MEMBER = 0XC000017B;

    /**
     * An illegal operation was attempted on a registry key   that has been marked for deletion.
     */
    public static final int STATUS_KEY_DELETED = 0XC000017C;

    /**
     * The system could not allocate the required space in a   registry log.
     */
    public static final int STATUS_NO_LOG_SPACE = 0XC000017D;

    /**
     * Too many SIDs have been specified.
     */
    public static final int STATUS_TOO_MANY_SIDS = 0XC000017E;

    /**
     * An attempt was made to change a user password in the   security account manager without providing the necessary LM cross-encrypted   password.
     */
    public static final int STATUS_LM_CROSS_ENCRYPTION_REQUIRED = 0XC000017F;

    /**
     * An attempt was made to create a symbolic link in a   registry key that already has subkeys or values.
     */
    public static final int STATUS_KEY_HAS_CHILDREN = 0XC0000180;

    /**
     * An attempt was made to create a stable subkey under a   volatile parent key.
     */
    public static final int STATUS_CHILD_MUST_BE_VOLATILE = 0XC0000181;

    /**
     * The I/O device is configured incorrectly or the   configuration parameters to the driver are incorrect.
     */
    public static final int STATUS_DEVICE_CONFIGURATION_ERROR = 0XC0000182;

    /**
     * An error was detected between two drivers or within an   I/O driver.
     */
    public static final int STATUS_DRIVER_INTERNAL_ERROR = 0XC0000183;

    /**
     * The device is not in a valid state to perform this   request.
     */
    public static final int STATUS_INVALID_DEVICE_STATE = 0XC0000184;

    /**
     * The I/O device reported an I/O error.
     */
    public static final int STATUS_IO_DEVICE_ERROR = 0XC0000185;

    /**
     * A protocol error was detected between the driver and   the device.
     */
    public static final int STATUS_DEVICE_PROTOCOL_ERROR = 0XC0000186;

    /**
     * This operation is only allowed for the primary domain   controller of the domain.
     */
    public static final int STATUS_BACKUP_CONTROLLER = 0XC0000187;

    /**
     * The log file space is insufficient to support this   operation.
     */
    public static final int STATUS_LOG_FILE_FULL = 0XC0000188;

    /**
     * A write operation was attempted to a volume after it   was dismounted.
     */
    public static final int STATUS_TOO_LATE = 0XC0000189;

    /**
     * The workstation does not have a trust secret for the   primary domain in the local LSA database.
     */
    public static final int STATUS_NO_TRUST_LSA_SECRET = 0XC000018A;

    /**
     * On applicable Windows Server releases, the SAM   database does not have a computer account for this workstation trust   relationship.
     */
    public static final int STATUS_NO_TRUST_SAM_ACCOUNT = 0XC000018B;

    /**
     * The logon request failed because the trust   relationship between the primary domain and the trusted domain failed.
     */
    public static final int STATUS_TRUSTED_DOMAIN_FAILURE = 0XC000018C;

    /**
     * The logon request failed because the trust   relationship between this workstation and the primary domain failed.
     */
    public static final int STATUS_TRUSTED_RELATIONSHIP_FAILURE = 0XC000018D;

    /**
     * The Eventlog log file is corrupt.
     */
    public static final int STATUS_EVENTLOG_FILE_CORRUPT = 0XC000018E;

    /**
     * No Eventlog log file could be opened. The Eventlog   service did not start.
     */
    public static final int STATUS_EVENTLOG_CANT_START = 0XC000018F;

    /**
     * The network logon failed. This might be because the   validation authority cannot be reached.
     */
    public static final int STATUS_TRUST_FAILURE = 0XC0000190;

    /**
     * An attempt was made to acquire a mutant such that its   maximum count would have been exceeded.
     */
    public static final int STATUS_MUTANT_LIMIT_EXCEEDED = 0XC0000191;

    /**
     * An attempt was made to logon, but the NetLogon service   was not started.
     */
    public static final int STATUS_NETLOGON_NOT_STARTED = 0XC0000192;

    /**
     * The user account has expired.
     */
    public static final int STATUS_ACCOUNT_EXPIRED = 0XC0000193;

    /**
     * {EXCEPTION} Possible deadlock condition.
     */
    public static final int STATUS_POSSIBLE_DEADLOCK = 0XC0000194;

    /**
     * Multiple connections to a server or shared resource by   the same user, using more than one user name, are not allowed. Disconnect all   previous connections to the server or shared resource and try again.
     */
    public static final int STATUS_NETWORK_CREDENTIAL_CONFLICT = 0XC0000195;

    /**
     * An attempt was made to establish a session to a   network server, but there are already too many sessions established to that   server.
     */
    public static final int STATUS_REMOTE_SESSION_LIMIT = 0XC0000196;

    /**
     * The log file has changed between reads.
     */
    public static final int STATUS_EVENTLOG_FILE_CHANGED = 0XC0000197;

    /**
     * The account used is an interdomain trust account. Use   your global user account or local user account to access this server.
     */
    public static final int STATUS_NOLOGON_INTERDOMAIN_TRUST_ACCOUNT = 0XC0000198;

    /**
     * The account used is a computer account. Use your   global user account or local user account to access this server.
     */
    public static final int STATUS_NOLOGON_WORKSTATION_TRUST_ACCOUNT = 0XC0000199;

    /**
     * The account used is a server trust account. Use your   global user account or local user account to access this server.
     */
    public static final int STATUS_NOLOGON_SERVER_TRUST_ACCOUNT = 0XC000019A;

    /**
     * The name or SID of the specified domain is   inconsistent with the trust information for that domain.
     */
    public static final int STATUS_DOMAIN_TRUST_INCONSISTENT = 0XC000019B;

    /**
     * A volume has been accessed for which a file system   driver is required that has not yet been loaded.
     */
    public static final int STATUS_FS_DRIVER_REQUIRED = 0XC000019C;

    /**
     * Indicates that the specified image is already loaded   as a DLL.
     */
    public static final int STATUS_IMAGE_ALREADY_LOADED_AS_DLL = 0XC000019D;

    /**
     * Short name settings cannot be changed on this volume   due to the global registry setting.
     */
    public static final int STATUS_INCOMPATIBLE_WITH_GLOBAL_SHORT_NAME_REGISTRY_SETTING = 0XC000019E;

    /**
     * Short names are not enabled on this volume.
     */
    public static final int STATUS_SHORT_NAMES_NOT_ENABLED_ON_VOLUME = 0XC000019F;

    /**
     * The security stream for the given volume is in an   inconsistent state. Please run CHKDSK on the volume.
     */
    public static final int STATUS_SECURITY_STREAM_IS_INCONSISTENT = 0XC00001A0;

    /**
     * A requested file lock operation cannot be processed   due to an invalid byte range.
     */
    public static final int STATUS_INVALID_LOCK_RANGE = 0XC00001A1;

    /**
     * The specified access control entry (ACE) contains an   invalid condition.
     */
    public static final int STATUS_INVALID_ACE_CONDITION = 0XC00001A2;

    /**
     * The subsystem needed to support the image type is not   present.
     */
    public static final int STATUS_IMAGE_SUBSYSTEM_NOT_PRESENT = 0XC00001A3;

    /**
     * The specified file already has a notification GUID   associated with it.
     */
    public static final int STATUS_NOTIFICATION_GUID_ALREADY_DEFINED = 0XC00001A4;

    /**
     * A remote open failed because the network open   restrictions were not satisfied.
     */
    public static final int STATUS_NETWORK_OPEN_RESTRICTION = 0XC0000201;

    /**
     * There is no user session key for the specified logon   session.
     */
    public static final int STATUS_NO_USER_SESSION_KEY = 0XC0000202;

    /**
     * The remote user session has been deleted.
     */
    public static final int STATUS_USER_SESSION_DELETED = 0XC0000203;

    /**
     * Indicates the specified resource language ID cannot be   found in the image file.
     */
    public static final int STATUS_RESOURCE_LANG_NOT_FOUND = 0XC0000204;

    /**
     * Insufficient server resources exist to complete the   request.
     */
    public static final int STATUS_INSUFF_SERVER_RESOURCES = 0XC0000205;

    /**
     * The size of the buffer is invalid for the specified   operation.
     */
    public static final int STATUS_INVALID_BUFFER_SIZE = 0XC0000206;

    /**
     * The transport rejected the specified network address   as invalid.
     */
    public static final int STATUS_INVALID_ADDRESS_COMPONENT = 0XC0000207;

    /**
     * The transport rejected the specified network address   due to invalid use of a wildcard.
     */
    public static final int STATUS_INVALID_ADDRESS_WILDCARD = 0XC0000208;

    /**
     * The transport address could not be opened because all   the available addresses are in use.
     */
    public static final int STATUS_TOO_MANY_ADDRESSES = 0XC0000209;

    /**
     * The transport address could not be opened because it   already exists.
     */
    public static final int STATUS_ADDRESS_ALREADY_EXISTS = 0XC000020A;

    /**
     * The transport address is now closed.
     */
    public static final int STATUS_ADDRESS_CLOSED = 0XC000020B;

    /**
     * The transport connection is now disconnected.
     */
    public static final int STATUS_CONNECTION_DISCONNECTED = 0XC000020C;

    /**
     * The transport connection has been reset.
     */
    public static final int STATUS_CONNECTION_RESET = 0XC000020D;

    /**
     * The transport cannot dynamically acquire any more   nodes.
     */
    public static final int STATUS_TOO_MANY_NODES = 0XC000020E;

    /**
     * The transport aborted a pending transaction.
     */
    public static final int STATUS_TRANSACTION_ABORTED = 0XC000020F;

    /**
     * The transport timed out a request that is waiting for   a response.
     */
    public static final int STATUS_TRANSACTION_TIMED_OUT = 0XC0000210;

    /**
     * The transport did not receive a release for a pending   response.
     */
    public static final int STATUS_TRANSACTION_NO_RELEASE = 0XC0000211;

    /**
     * The transport did not find a transaction that matches   the specific token.
     */
    public static final int STATUS_TRANSACTION_NO_MATCH = 0XC0000212;

    /**
     * The transport had previously responded to a   transaction request.
     */
    public static final int STATUS_TRANSACTION_RESPONDED = 0XC0000213;

    /**
     * The transport does not recognize the specified   transaction request ID.
     */
    public static final int STATUS_TRANSACTION_INVALID_ID = 0XC0000214;

    /**
     * The transport does not recognize the specified   transaction request type.
     */
    public static final int STATUS_TRANSACTION_INVALID_TYPE = 0XC0000215;

    /**
     * The transport can only process the specified request on   the server side of a session.
     */
    public static final int STATUS_NOT_SERVER_SESSION = 0XC0000216;

    /**
     * The transport can only process the specified request   on the client side of a session.
     */
    public static final int STATUS_NOT_CLIENT_SESSION = 0XC0000217;

    /**
     * {Registry File Failure} The registry cannot load the hive   (file): %hs or its log or alternate. It is corrupt, absent, or not writable.
     */
    public static final int STATUS_CANNOT_LOAD_REGISTRY_FILE = 0XC0000218;

    /**
     * {Unexpected Failure in DebugActiveProcess} An   unexpected failure occurred while processing a DebugActiveProcess API   request. Choosing OK will terminate the process, and choosing Cancel will   ignore the
     * error.
     */
    public static final int STATUS_DEBUG_ATTACH_FAILED = 0XC0000219;

    /**
     * {Fatal System Error} The %hs system process terminated   unexpectedly with a status of 0x%08x (0x%08x 0x%08x). The system has been   shut down.
     */
    public static final int STATUS_SYSTEM_PROCESS_TERMINATED = 0XC000021A;

    /**
     * {Data Not Accepted} The TDI client could not handle   the data received during an indication.
     */
    public static final int STATUS_DATA_NOT_ACCEPTED = 0XC000021B;

    /**
     * {Unable to Retrieve Browser Server List} The list of   servers for this workgroup is not currently available.
     */
    public static final int STATUS_NO_BROWSER_SERVERS_FOUND = 0XC000021C;

    /**
     * NTVDM encountered a hard error.
     */
    public static final int STATUS_VDM_HARD_ERROR = 0XC000021D;

    /**
     * {Cancel Timeout} The driver %hs failed to complete a   canceled I/O request in the allotted time.
     */
    public static final int STATUS_DRIVER_CANCEL_TIMEOUT = 0XC000021E;

    /**
     * {Reply Message Mismatch} An attempt was made to reply   to an LPC message, but the thread specified by the client ID in the message   was not waiting on that message.
     */
    public static final int STATUS_REPLY_MESSAGE_MISMATCH = 0XC000021F;

    /**
     * {Mapped View Alignment Incorrect} An attempt was made   to map a view of a file, but either the specified base address or the offset   into the file were not aligned on the proper allocation granularity.
     */
    public static final int STATUS_MAPPED_ALIGNMENT = 0XC0000220;

    /**
     * {Bad Image Checksum} The image %hs is possibly   corrupt. The header checksum does not match the computed checksum.
     */
    public static final int STATUS_IMAGE_CHECKSUM_MISMATCH = 0XC0000221;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs. The data has been lost. This error might be caused   by a failure of your computer hardware or network connection. Try to save
     * this file elsewhere.
     */
    public static final int STATUS_LOST_WRITEBEHIND_DATA = 0XC0000222;

    /**
     * The parameters passed to the server in the   client/server shared memory window were invalid. Too much data might have   been put in the shared memory window.
     */
    public static final int STATUS_CLIENT_SERVER_PARAMETERS_INVALID = 0XC0000223;

    /**
     * The user password must be changed before logging on the first time.
     */
    public static final int STATUS_PASSWORD_MUST_CHANGE = 0XC0000224;

    /**
     * The object was not found.
     */
    public static final int STATUS_NOT_FOUND = 0XC0000225;

    /**
     * The stream is not a tiny stream.
     */
    public static final int STATUS_NOT_TINY_STREAM = 0XC0000226;

    /**
     * A transaction recovery failed.
     */
    public static final int STATUS_RECOVERY_FAILURE = 0XC0000227;

    /**
     * The request must be handled by the stack overflow   code.
     */
    public static final int STATUS_STACK_OVERFLOW_READ = 0XC0000228;

    /**
     * A consistency check failed.
     */
    public static final int STATUS_FAIL_CHECK = 0XC0000229;

    /**
     * The attempt to insert the ID in the index failed   because the ID is already in the index.
     */
    public static final int STATUS_DUPLICATE_OBJECTID = 0XC000022A;

    /**
     * The attempt to set the object ID failed because the   object already has an ID.
     */
    public static final int STATUS_OBJECTID_EXISTS = 0XC000022B;

    /**
     * Internal OFS status codes indicating how an allocation   operation is handled. Either it is retried after the containing oNode is   moved or the extent stream is converted to a large stream.
     */
    public static final int STATUS_CONVERT_TO_LARGE = 0XC000022C;

    /**
     * The request needs to be retried.
     */
    public static final int STATUS_RETRY = 0XC000022D;

    /**
     * The attempt to find the object found an object on the   volume that matches by ID; however, it is out of the scope of the handle that   is used for the operation.
     */
    public static final int STATUS_FOUND_OUT_OF_SCOPE = 0XC000022E;

    /**
     * The bucket array must be grown. Retry the transaction   after doing so.
     */
    public static final int STATUS_ALLOCATE_BUCKET = 0XC000022F;

    /**
     * The specified property set does not exist on the   object.
     */
    public static final int STATUS_PROPSET_NOT_FOUND = 0XC0000230;

    /**
     * The user/kernel marshaling buffer has overflowed.
     */
    public static final int STATUS_MARSHALL_OVERFLOW = 0XC0000231;

    /**
     * The supplied variant structure contains invalid data.
     */
    public static final int STATUS_INVALID_VARIANT = 0XC0000232;

    /**
     * A domain controller for this domain was not found.
     */
    public static final int STATUS_DOMAIN_CONTROLLER_NOT_FOUND = 0XC0000233;

    /**
     * The user account has been automatically locked because   too many invalid logon attempts or password change attempts have been   requested.
     */
    public static final int STATUS_ACCOUNT_LOCKED_OUT = 0XC0000234;

    /**
     * NtClose was called on a handle that was protected from   close via NtSetInformationObject.
     */
    public static final int STATUS_HANDLE_NOT_CLOSABLE = 0XC0000235;

    /**
     * The transport-connection attempt was refused by the   remote system.
     */
    public static final int STATUS_CONNECTION_REFUSED = 0XC0000236;

    /**
     * The transport connection was gracefully closed.
     */
    public static final int STATUS_GRACEFUL_DISCONNECT = 0XC0000237;

    /**
     * The transport endpoint already has an address   associated with it.
     */
    public static final int STATUS_ADDRESS_ALREADY_ASSOCIATED = 0XC0000238;

    /**
     * An address has not yet been associated with the   transport endpoint.
     */
    public static final int STATUS_ADDRESS_NOT_ASSOCIATED = 0XC0000239;

    /**
     * An operation was attempted on a nonexistent transport   connection.
     */
    public static final int STATUS_CONNECTION_INVALID = 0XC000023A;

    /**
     * An invalid operation was attempted on an active   transport connection.
     */
    public static final int STATUS_CONNECTION_ACTIVE = 0XC000023B;

    /**
     * The remote network is not reachable by the transport.
     */
    public static final int STATUS_NETWORK_UNREACHABLE = 0XC000023C;

    /**
     * The remote system is not reachable by the transport.
     */
    public static final int STATUS_HOST_UNREACHABLE = 0XC000023D;

    /**
     * The remote system does not support the transport   protocol.
     */
    public static final int STATUS_PROTOCOL_UNREACHABLE = 0XC000023E;

    /**
     * No service is operating at the destination port of the   transport on the remote system.
     */
    public static final int STATUS_PORT_UNREACHABLE = 0XC000023F;

    /**
     * The request was aborted.
     */
    public static final int STATUS_REQUEST_ABORTED = 0XC0000240;

    /**
     * The transport connection was aborted by the local   system.
     */
    public static final int STATUS_CONNECTION_ABORTED = 0XC0000241;

    /**
     * The specified buffer contains ill-formed data.
     */
    public static final int STATUS_BAD_COMPRESSION_BUFFER = 0XC0000242;

    /**
     * The requested operation cannot be performed on a file   with a user mapped section open.
     */
    public static final int STATUS_USER_MAPPED_FILE = 0XC0000243;

    /**
     * {Audit Failed} An attempt to generate a security audit   failed.
     */
    public static final int STATUS_AUDIT_FAILED = 0XC0000244;

    /**
     * The timer resolution was not previously set by the current   process.
     */
    public static final int STATUS_TIMER_RESOLUTION_NOT_SET = 0XC0000245;

    /**
     * A connection to the server could not be made because   the limit on the number of concurrent connections for this account has been   reached.
     */
    public static final int STATUS_CONNECTION_COUNT_LIMIT = 0XC0000246;

    /**
     * Attempting to log on during an unauthorized time of   day for this account.
     */
    public static final int STATUS_LOGIN_TIME_RESTRICTION = 0XC0000247;

    /**
     * The account is not authorized to log on from this   station.
     */
    public static final int STATUS_LOGIN_WKSTA_RESTRICTION = 0XC0000248;

    /**
     * {UP/MP Image Mismatch} The image %hs has been modified   for use on a uniprocessor system, but you are running it on a multiprocessor   machine. Reinstall the image file.
     */
    public static final int STATUS_IMAGE_MP_UP_MISMATCH = 0XC0000249;

    /**
     * There is insufficient account information to log you   on.
     */
    public static final int STATUS_INSUFFICIENT_LOGON_INFO = 0XC0000250;

    /**
     * {Invalid DLL Entrypoint} The dynamic link library %hs   is not written correctly. The stack pointer has been left in an inconsistent   state. The entry point should be declared as WINAPI or STDCALL. Select YES to
     * fail the DLL load. Select NO to continue execution. Selecting NO might cause   the application to operate incorrectly.
     */
    public static final int STATUS_BAD_DLL_ENTRYPOINT = 0XC0000251;

    /**
     * {Invalid Service Callback Entrypoint} The %hs service   is not written correctly. The stack pointer has been left in an inconsistent   state. The callback entry point should be declared as WINAPI or STDCALL.
     * Selecting OK will cause the service to continue operation. However, the   service process might operate incorrectly.
     */
    public static final int STATUS_BAD_SERVICE_ENTRYPOINT = 0XC0000252;

    /**
     * The server received the messages but did not send a   reply.
     */
    public static final int STATUS_LPC_REPLY_LOST = 0XC0000253;

    /**
     * There is an IP address conflict with another system on   the network.
     */
    public static final int STATUS_IP_ADDRESS_CONFLICT1 = 0XC0000254;

    /**
     * There is an IP address conflict with another system on   the network.
     */
    public static final int STATUS_IP_ADDRESS_CONFLICT2 = 0XC0000255;

    /**
     * {Low On Registry Space} The system has reached the   maximum size that is allowed for the system part of the registry. Additional   storage requests will be ignored.
     */
    public static final int STATUS_REGISTRY_QUOTA_LIMIT = 0XC0000256;

    /**
     * The contacted server does not support the indicated   part of the DFS namespace.
     */
    public static final int STATUS_PATH_NOT_COVERED = 0XC0000257;

    /**
     * A callback return system service cannot be executed   when no callback is active.
     */
    public static final int STATUS_NO_CALLBACK_ACTIVE = 0XC0000258;

    /**
     * The service being accessed is licensed for a particular   number of connections. No more connections can be made to the service at this   time because the service has already accepted the maximum number of
     * connections.
     */
    public static final int STATUS_LICENSE_QUOTA_EXCEEDED = 0XC0000259;

    /**
     * The password provided is too short to meet the policy   of your user account. Choose a longer password.
     */
    public static final int STATUS_PWD_TOO_SHORT = 0XC000025A;

    /**
     * The policy of your user account does not allow you to   change passwords too frequently. This is done to prevent users from changing   back to a familiar, but potentially discovered, password. If you feel your
     * password has been compromised, contact your administrator immediately to have   a new one assigned.
     */
    public static final int STATUS_PWD_TOO_RECENT = 0XC000025B;

    /**
     * You have attempted to change your password to one that   you have used in the past. The policy of your user account does not allow   this. Select a password that you have not previously used.
     */
    public static final int STATUS_PWD_HISTORY_CONFLICT = 0XC000025C;

    /**
     * You have attempted to load a legacy device driver   while its device instance had been disabled.
     */
    public static final int STATUS_PLUGPLAY_NO_DEVICE = 0XC000025E;

    /**
     * The specified compression format is unsupported.
     */
    public static final int STATUS_UNSUPPORTED_COMPRESSION = 0XC000025F;

    /**
     * The specified hardware profile configuration is   invalid.
     */
    public static final int STATUS_INVALID_HW_PROFILE = 0XC0000260;

    /**
     * The specified Plug and Play registry device path is   invalid.
     */
    public static final int STATUS_INVALID_PLUGPLAY_DEVICE_PATH = 0XC0000261;

    /**
     * {Driver Entry Point Not Found} The %hs device driver   could not locate the ordinal %ld in driver %hs.
     */
    public static final int STATUS_DRIVER_ORDINAL_NOT_FOUND = 0XC0000262;

    /**
     * {Driver Entry Point Not Found} The %hs device driver   could not locate the entry point %hs in driver %hs.
     */
    public static final int STATUS_DRIVER_ENTRYPOINT_NOT_FOUND = 0XC0000263;

    /**
     * {Application Error} The application attempted to   release a resource it did not own. Click OK to terminate the application.
     */
    public static final int STATUS_RESOURCE_NOT_OWNED = 0XC0000264;

    /**
     * An attempt was made to create more links on a file   than the file system supports.
     */
    public static final int STATUS_TOO_MANY_LINKS = 0XC0000265;

    /**
     * The specified quota list is internally inconsistent   with its descriptor.
     */
    public static final int STATUS_QUOTA_LIST_INCONSISTENT = 0XC0000266;

    /**
     * The specified file has been relocated to offline   storage.
     */
    public static final int STATUS_FILE_IS_OFFLINE = 0XC0000267;

    /**
     * {Windows Evaluation Notification} The evaluation   period for this installation of Windows has expired. This system will   shutdown in 1 hour. To restore access to this installation of Windows,   upgrade this
     * installation by using a licensed distribution of this product.
     */
    public static final int STATUS_EVALUATION_EXPIRATION = 0XC0000268;

    /**
     * {Illegal System DLL Relocation} The system DLL %hs was   relocated in memory. The application will not run properly. The relocation   occurred because the DLL %hs occupied an address range that is reserved for
     * Windows system DLLs. The vendor supplying the DLL should be contacted for a   new DLL.
     */
    public static final int STATUS_ILLEGAL_DLL_RELOCATION = 0XC0000269;

    /**
     * {License Violation} The system has detected tampering   with your registered product type. This is a violation of your software   license. Tampering with the product type is not permitted.
     */
    public static final int STATUS_LICENSE_VIOLATION = 0XC000026A;

    /**
     * {DLL Initialization Failed} The application failed to   initialize because the window station is shutting down.
     */
    public static final int STATUS_DLL_INIT_FAILED_LOGOFF = 0XC000026B;

    /**
     * {Unable to Load Device Driver} %hs device driver could   not be loaded. Error Status was 0x%x.
     */
    public static final int STATUS_DRIVER_UNABLE_TO_LOAD = 0XC000026C;

    /**
     * DFS is unavailable on the contacted server.
     */
    public static final int STATUS_DFS_UNAVAILABLE = 0XC000026D;

    /**
     * An operation was attempted to a volume after it was   dismounted.
     */
    public static final int STATUS_VOLUME_DISMOUNTED = 0XC000026E;

    /**
     * An internal error occurred in the Win32 x86 emulation   subsystem.
     */
    public static final int STATUS_WX86_INTERNAL_ERROR = 0XC000026F;

    /**
     * Win32 x86 emulation subsystem floating-point stack   check.
     */
    public static final int STATUS_WX86_FLOAT_STACK_CHECK = 0XC0000270;

    /**
     * The validation process needs to continue on to the   next step.
     */
    public static final int STATUS_VALIDATE_CONTINUE = 0XC0000271;

    /**
     * There was no match for the specified key in the index.
     */
    public static final int STATUS_NO_MATCH = 0XC0000272;

    /**
     * There are no more matches for the current index   enumeration.
     */
    public static final int STATUS_NO_MORE_MATCHES = 0XC0000273;

    /**
     * The NTFS file or directory is not a reparse point.
     */
    public static final int STATUS_NOT_A_REPARSE_POINT = 0XC0000275;

    /**
     * The Windows I/O reparse tag passed for the NTFS   reparse point is invalid.
     */
    public static final int STATUS_IO_REPARSE_TAG_INVALID = 0XC0000276;

    /**
     * The Windows I/O reparse tag does not match the one   that is in the NTFS reparse point.
     */
    public static final int STATUS_IO_REPARSE_TAG_MISMATCH = 0XC0000277;

    /**
     * The user data passed for the NTFS reparse point is invalid.
     */
    public static final int STATUS_IO_REPARSE_DATA_INVALID = 0XC0000278;

    /**
     * The layered file system driver for this I/O tag did   not handle it when needed.
     */
    public static final int STATUS_IO_REPARSE_TAG_NOT_HANDLED = 0XC0000279;

    /**
     * The NTFS symbolic link could not be resolved even   though the initial file name is valid.
     */
    public static final int STATUS_REPARSE_POINT_NOT_RESOLVED = 0XC0000280;

    /**
     * The NTFS directory is a reparse point.
     */
    public static final int STATUS_DIRECTORY_IS_A_REPARSE_POINT = 0XC0000281;

    /**
     * The range could not be added to the range list because   of a conflict.
     */
    public static final int STATUS_RANGE_LIST_CONFLICT = 0XC0000282;

    /**
     * The specified medium changer source element contains   no media.
     */
    public static final int STATUS_SOURCE_ELEMENT_EMPTY = 0XC0000283;

    /**
     * The specified medium changer destination element   already contains media.
     */
    public static final int STATUS_DESTINATION_ELEMENT_FULL = 0XC0000284;

    /**
     * The specified medium changer element does not exist.
     */
    public static final int STATUS_ILLEGAL_ELEMENT_ADDRESS = 0XC0000285;

    /**
     * The specified element is contained in a magazine that   is no longer present.
     */
    public static final int STATUS_MAGAZINE_NOT_PRESENT = 0XC0000286;

    /**
     * The device requires re-initialization due to hardware   errors.
     */
    public static final int STATUS_REINITIALIZATION_NEEDED = 0XC0000287;

    /**
     * The file encryption attempt failed.
     */
    public static final int STATUS_ENCRYPTION_FAILED = 0XC000028A;

    /**
     * The file decryption attempt failed.
     */
    public static final int STATUS_DECRYPTION_FAILED = 0XC000028B;

    /**
     * The specified range could not be found in the range   list.
     */
    public static final int STATUS_RANGE_NOT_FOUND = 0XC000028C;

    /**
     * There is no encryption recovery policy configured for   this system.
     */
    public static final int STATUS_NO_RECOVERY_POLICY = 0XC000028D;

    /**
     * The required encryption driver is not loaded for this   system.
     */
    public static final int STATUS_NO_EFS = 0XC000028E;

    /**
     * The file was encrypted with a different encryption   driver than is currently loaded.
     */
    public static final int STATUS_WRONG_EFS = 0XC000028F;

    /**
     * There are no EFS keys defined for the user.
     */
    public static final int STATUS_NO_USER_KEYS = 0XC0000290;

    /**
     * The specified file is not encrypted.
     */
    public static final int STATUS_FILE_NOT_ENCRYPTED = 0XC0000291;

    /**
     * The specified file is not in the defined EFS export   format.
     */
    public static final int STATUS_NOT_EXPORT_FORMAT = 0XC0000292;

    /**
     * The specified file is encrypted and the user does not   have the ability to decrypt it.
     */
    public static final int STATUS_FILE_ENCRYPTED = 0XC0000293;

    /**
     * The GUID passed was not recognized as valid by a WMI   data provider.
     */
    public static final int STATUS_WMI_GUID_NOT_FOUND = 0XC0000295;

    /**
     * The instance name passed was not recognized as valid   by a WMI data provider.
     */
    public static final int STATUS_WMI_INSTANCE_NOT_FOUND = 0XC0000296;

    /**
     * The data item ID passed was not recognized as valid by   a WMI data provider.
     */
    public static final int STATUS_WMI_ITEMID_NOT_FOUND = 0XC0000297;

    /**
     * The WMI request could not be completed and should be   retried.
     */
    public static final int STATUS_WMI_TRY_AGAIN = 0XC0000298;

    /**
     * The policy object is shared and can only be modified   at the root.
     */
    public static final int STATUS_SHARED_POLICY = 0XC0000299;

    /**
     * The policy object does not exist when it should.
     */
    public static final int STATUS_POLICY_OBJECT_NOT_FOUND = 0XC000029A;

    /**
     * The requested policy information only lives in the Ds.
     */
    public static final int STATUS_POLICY_ONLY_IN_DS = 0XC000029B;

    /**
     * The volume must be upgraded to enable this feature.
     */
    public static final int STATUS_VOLUME_NOT_UPGRADED = 0XC000029C;

    /**
     * The remote storage service is not operational at this   time.
     */
    public static final int STATUS_REMOTE_STORAGE_NOT_ACTIVE = 0XC000029D;

    /**
     * The remote storage service encountered a media error.
     */
    public static final int STATUS_REMOTE_STORAGE_MEDIA_ERROR = 0XC000029E;

    /**
     * The tracking (workstation) service is not running.
     */
    public static final int STATUS_NO_TRACKING_SERVICE = 0XC000029F;

    /**
     * The server process is running under a SID that is   different from the SID that is required by client.
     */
    public static final int STATUS_SERVER_SID_MISMATCH = 0XC00002A0;

    /**
     * The specified directory service attribute or value   does not exist.
     */
    public static final int STATUS_DS_NO_ATTRIBUTE_OR_VALUE = 0XC00002A1;

    /**
     * The attribute syntax specified to the directory   service is invalid.
     */
    public static final int STATUS_DS_INVALID_ATTRIBUTE_SYNTAX = 0XC00002A2;

    /**
     * The attribute type specified to the directory service   is not defined.
     */
    public static final int STATUS_DS_ATTRIBUTE_TYPE_UNDEFINED = 0XC00002A3;

    /**
     * The specified directory service attribute or value   already exists.
     */
    public static final int STATUS_DS_ATTRIBUTE_OR_VALUE_EXISTS = 0XC00002A4;

    /**
     * The directory service is busy.
     */
    public static final int STATUS_DS_BUSY = 0XC00002A5;

    /**
     * The directory service is unavailable.
     */
    public static final int STATUS_DS_UNAVAILABLE = 0XC00002A6;

    /**
     * The directory service was unable to allocate a   relative identifier.
     */
    public static final int STATUS_DS_NO_RIDS_ALLOCATED = 0XC00002A7;

    /**
     * The directory service has exhausted the pool of   relative identifiers.
     */
    public static final int STATUS_DS_NO_MORE_RIDS = 0XC00002A8;

    /**
     * The requested operation could not be performed because   the directory service is not the master for that type of operation.
     */
    public static final int STATUS_DS_INCORRECT_ROLE_OWNER = 0XC00002A9;

    /**
     * The directory service was unable to initialize the   subsystem that allocates relative identifiers.
     */
    public static final int STATUS_DS_RIDMGR_INIT_ERROR = 0XC00002AA;

    /**
     * The requested operation did not satisfy one or more   constraints that are associated with the class of the object.
     */
    public static final int STATUS_DS_OBJ_CLASS_VIOLATION = 0XC00002AB;

    /**
     * The directory service can perform the requested   operation only on a leaf object.
     */
    public static final int STATUS_DS_CANT_ON_NON_LEAF = 0XC00002AC;

    /**
     * The directory service cannot perform the requested   operation on the Relatively Defined Name (RDN) attribute of an object.
     */
    public static final int STATUS_DS_CANT_ON_RDN = 0XC00002AD;

    /**
     * The directory service detected an attempt to modify   the object class of an object.
     */
    public static final int STATUS_DS_CANT_MOD_OBJ_CLASS = 0XC00002AE;

    /**
     * An error occurred while performing a cross domain move   operation.
     */
    public static final int STATUS_DS_CROSS_DOM_MOVE_FAILED = 0XC00002AF;

    /**
     * Unable to contact the global catalog server.
     */
    public static final int STATUS_DS_GC_NOT_AVAILABLE = 0XC00002B0;

    /**
     * The requested operation requires a directory service,   and none was available.
     */
    public static final int STATUS_DIRECTORY_SERVICE_REQUIRED = 0XC00002B1;

    /**
     * The reparse attribute cannot be set because it is   incompatible with an existing attribute.
     */
    public static final int STATUS_REPARSE_ATTRIBUTE_CONFLICT = 0XC00002B2;

    /**
     * A group marked \"use for deny only\" cannot be   enabled.
     */
    public static final int STATUS_CANT_ENABLE_DENY_ONLY = 0XC00002B3;

    /**
     * {EXCEPTION} Multiple floating-point faults.
     */
    public static final int STATUS_FLOAT_MULTIPLE_FAULTS = 0XC00002B4;

    /**
     * {EXCEPTION} Multiple floating-point traps.
     */
    public static final int STATUS_FLOAT_MULTIPLE_TRAPS = 0XC00002B5;

    /**
     * The device has been removed.
     */
    public static final int STATUS_DEVICE_REMOVED = 0XC00002B6;

    /**
     * The volume change journal is being deleted.
     */
    public static final int STATUS_JOURNAL_DELETE_IN_PROGRESS = 0XC00002B7;

    /**
     * The volume change journal is not active.
     */
    public static final int STATUS_JOURNAL_NOT_ACTIVE = 0XC00002B8;

    /**
     * The requested interface is not supported.
     */
    public static final int STATUS_NOINTERFACE = 0XC00002B9;

    /**
     * A directory service resource limit has been exceeded.
     */
    public static final int STATUS_DS_ADMIN_LIMIT_EXCEEDED = 0XC00002C1;

    /**
     * {System Standby Failed} The driver %hs does not   support standby mode. Updating this driver allows the system to go to standby   mode.
     */
    public static final int STATUS_DRIVER_FAILED_SLEEP = 0XC00002C2;

    /**
     * Mutual Authentication failed. The server password is   out of date at the domain controller.
     */
    public static final int STATUS_MUTUAL_AUTHENTICATION_FAILED = 0XC00002C3;

    /**
     * The system file %1 has become corrupt and has been   replaced.
     */
    public static final int STATUS_CORRUPT_SYSTEM_FILE = 0XC00002C4;

    /**
     * {EXCEPTION} Alignment Error A data type misalignment   error was detected in a load or store instruction.
     */
    public static final int STATUS_DATATYPE_MISALIGNMENT_ERROR = 0XC00002C5;

    /**
     * The WMI data item or data block is read-only.
     */
    public static final int STATUS_WMI_READ_ONLY = 0XC00002C6;

    /**
     * The WMI data item or data block could not be changed.
     */
    public static final int STATUS_WMI_SET_FAILURE = 0XC00002C7;

    /**
     * {Virtual Memory Minimum Too Low} Your system is low on   virtual memory. Windows is increasing the size of your virtual memory paging   file. During this process, memory requests for some applications might be
     * denied. For more information, see Help.
     */
    public static final int STATUS_COMMITMENT_MINIMUM = 0XC00002C8;

    /**
     * {EXCEPTION} Register NaT consumption faults. A NaT   value is consumed on a non-speculative instruction.
     */
    public static final int STATUS_REG_NAT_CONSUMPTION = 0XC00002C9;

    /**
     * The transport element of the medium changer contains   media, which is causing the operation to fail.
     */
    public static final int STATUS_TRANSPORT_FULL = 0XC00002CA;

    /**
     * Security Accounts Manager initialization failed because   of the following error: %hs Error Status: 0x%x. Click OK to shut down this   system and restart in Directory Services Restore Mode. Check the event log
     * for more detailed information.
     */
    public static final int STATUS_DS_SAM_INIT_FAILURE = 0XC00002CB;

    /**
     * This operation is supported only when you are   connected to the server.
     */
    public static final int STATUS_ONLY_IF_CONNECTED = 0XC00002CC;

    /**
     * Only an administrator can modify the membership list   of an administrative group.
     */
    public static final int STATUS_DS_SENSITIVE_GROUP_VIOLATION = 0XC00002CD;

    /**
     * A device was removed so enumeration must be restarted.
     */
    public static final int STATUS_PNP_RESTART_ENUMERATION = 0XC00002CE;

    /**
     * The journal entry has been deleted from the journal.
     */
    public static final int STATUS_JOURNAL_ENTRY_DELETED = 0XC00002CF;

    /**
     * Cannot change the primary group ID of a domain   controller account.
     */
    public static final int STATUS_DS_CANT_MOD_PRIMARYGROUPID = 0XC00002D0;

    /**
     * {Fatal System Error} The system image %s is not   properly signed. The file has been replaced with the signed file. The system   has been shut down.
     */
    public static final int STATUS_SYSTEM_IMAGE_BAD_SIGNATURE = 0XC00002D1;

    /**
     * The device will not start without a reboot.
     */
    public static final int STATUS_PNP_REBOOT_REQUIRED = 0XC00002D2;

    /**
     * The power state of the current device cannot support   this request.
     */
    public static final int STATUS_POWER_STATE_INVALID = 0XC00002D3;

    /**
     * The specified group type is invalid.
     */
    public static final int STATUS_DS_INVALID_GROUP_TYPE = 0XC00002D4;

    /**
     * In a mixed domain, no nesting of a global group if the   group is security enabled.
     */
    public static final int STATUS_DS_NO_NEST_GLOBALGROUP_IN_MIXEDDOMAIN = 0XC00002D5;

    /**
     * In a mixed domain, cannot nest local groups with other   local groups, if the group is security enabled.
     */
    public static final int STATUS_DS_NO_NEST_LOCALGROUP_IN_MIXEDDOMAIN = 0XC00002D6;

    /**
     * A global group cannot have a local group as a member.
     */
    public static final int STATUS_DS_GLOBAL_CANT_HAVE_LOCAL_MEMBER = 0XC00002D7;

    /**
     * A global group cannot have a universal group as a   member.
     */
    public static final int STATUS_DS_GLOBAL_CANT_HAVE_UNIVERSAL_MEMBER = 0XC00002D8;

    /**
     * A universal group cannot have a local group as a   member.
     */
    public static final int STATUS_DS_UNIVERSAL_CANT_HAVE_LOCAL_MEMBER = 0XC00002D9;

    /**
     * A global group cannot have a cross-domain member.
     */
    public static final int STATUS_DS_GLOBAL_CANT_HAVE_CROSSDOMAIN_MEMBER = 0XC00002DA;

    /**
     * A local group cannot have another cross-domain local   group as a member.
     */
    public static final int STATUS_DS_LOCAL_CANT_HAVE_CROSSDOMAIN_LOCAL_MEMBER = 0XC00002DB;

    /**
     * Cannot change to a security-disabled group because   primary members are in this group.
     */
    public static final int STATUS_DS_HAVE_PRIMARY_MEMBERS = 0XC00002DC;

    /**
     * The WMI operation is not supported by the data block   or method.
     */
    public static final int STATUS_WMI_NOT_SUPPORTED = 0XC00002DD;

    /**
     * There is not enough power to complete the requested   operation.
     */
    public static final int STATUS_INSUFFICIENT_POWER = 0XC00002DE;

    /**
     * The Security Accounts Manager needs to get the boot   password.
     */
    public static final int STATUS_SAM_NEED_BOOTKEY_PASSWORD = 0XC00002DF;

    /**
     * The Security Accounts Manager needs to get the boot   key from the floppy disk.
     */
    public static final int STATUS_SAM_NEED_BOOTKEY_FLOPPY = 0XC00002E0;

    /**
     * The directory service cannot start.
     */
    public static final int STATUS_DS_CANT_START = 0XC00002E1;

    /**
     * The directory service could not start because of the following   error: %hs Error Status: 0x%x. Click OK to shut down this system and restart   in Directory Services Restore Mode. Check the event log for more
     * detailed   information.
     */
    public static final int STATUS_DS_INIT_FAILURE = 0XC00002E2;

    /**
     * The Security Accounts Manager initialization failed   because of the following error: %hs Error Status: 0x%x. Click OK to shut down   this system and restart in Safe Mode. Check the event log for more detailed
     * information.
     */
    public static final int STATUS_SAM_INIT_FAILURE = 0XC00002E3;

    /**
     * The requested operation can be performed only on a   global catalog server.
     */
    public static final int STATUS_DS_GC_REQUIRED = 0XC00002E4;

    /**
     * A local group can only be a member of other local   groups in the same domain.
     */
    public static final int STATUS_DS_LOCAL_MEMBER_OF_LOCAL_ONLY = 0XC00002E5;

    /**
     * Foreign security principals cannot be members of   universal groups.
     */
    public static final int STATUS_DS_NO_FPO_IN_UNIVERSAL_GROUPS = 0XC00002E6;

    /**
     * Your computer could not be joined to the domain. You   have exceeded the maximum number of computer accounts you are allowed to   create in this domain. Contact your system administrator to have this limit   reset
     * or increased.
     */
    public static final int STATUS_DS_MACHINE_ACCOUNT_QUOTA_EXCEEDED = 0XC00002E7;

    /**
     * This operation cannot be performed on the current   domain.
     */
    public static final int STATUS_CURRENT_DOMAIN_NOT_ALLOWED = 0XC00002E9;

    /**
     * The directory or file cannot be created.
     */
    public static final int STATUS_CANNOT_MAKE = 0XC00002EA;

    /**
     * The system is in the process of shutting down.
     */
    public static final int STATUS_SYSTEM_SHUTDOWN = 0XC00002EB;

    /**
     * Directory Services could not start because of the   following error: %hs Error Status: 0x%x. Click OK to shut down the system.   You can use the recovery console to diagnose the system further.
     */
    public static final int STATUS_DS_INIT_FAILURE_CONSOLE = 0XC00002EC;

    /**
     * Security Accounts Manager initialization failed   because of the following error: %hs Error Status: 0x%x. Click OK to shut down   the system. You can use the recovery console to diagnose the system further.
     */
    public static final int STATUS_DS_SAM_INIT_FAILURE_CONSOLE = 0XC00002ED;

    /**
     * A security context was deleted before the context was   completed. This is considered a logon failure.
     */
    public static final int STATUS_UNFINISHED_CONTEXT_DELETED = 0XC00002EE;

    /**
     * The client is trying to negotiate a context and the   server requires user-to-user but did not send a TGT reply.
     */
    public static final int STATUS_NO_TGT_REPLY = 0XC00002EF;

    /**
     * An object ID was not found in the file.
     */
    public static final int STATUS_OBJECTID_NOT_FOUND = 0XC00002F0;

    /**
     * Unable to accomplish the requested task because the   local machine does not have any IP addresses.
     */
    public static final int STATUS_NO_IP_ADDRESSES = 0XC00002F1;

    /**
     * The supplied credential handle does not match the   credential that is associated with the security context.
     */
    public static final int STATUS_WRONG_CREDENTIAL_HANDLE = 0XC00002F2;

    /**
     * The crypto system or checksum function is invalid   because a required function is unavailable.
     */
    public static final int STATUS_CRYPTO_SYSTEM_INVALID = 0XC00002F3;

    /**
     * The number of maximum ticket referrals has been   exceeded.
     */
    public static final int STATUS_MAX_REFERRALS_EXCEEDED = 0XC00002F4;

    /**
     * The local machine must be a Kerberos KDC (domain   controller) and it is not.
     */
    public static final int STATUS_MUST_BE_KDC = 0XC00002F5;

    /**
     * The other end of the security negotiation requires   strong crypto but it is not supported on the local machine.
     */
    public static final int STATUS_STRONG_CRYPTO_NOT_SUPPORTED = 0XC00002F6;

    /**
     * The KDC reply contained more than one principal name.
     */
    public static final int STATUS_TOO_MANY_PRINCIPALS = 0XC00002F7;

    /**
     * Expected to find PA data for a hint of what etype to   use, but it was not found.
     */
    public static final int STATUS_NO_PA_DATA = 0XC00002F8;

    /**
     * The client certificate does not contain a valid UPN,   or does not match the client name in the logon request. Contact your administrator.
     */
    public static final int STATUS_PKINIT_NAME_MISMATCH = 0XC00002F9;

    /**
     * Smart card logon is required and was not used.
     */
    public static final int STATUS_SMARTCARD_LOGON_REQUIRED = 0XC00002FA;

    /**
     * An invalid request was sent to the KDC.
     */
    public static final int STATUS_KDC_INVALID_REQUEST = 0XC00002FB;

    /**
     * The KDC was unable to generate a referral for the   service requested.
     */
    public static final int STATUS_KDC_UNABLE_TO_REFER = 0XC00002FC;

    /**
     * The encryption type requested is not supported by the   KDC.
     */
    public static final int STATUS_KDC_UNKNOWN_ETYPE = 0XC00002FD;

    /**
     * A system shutdown is in progress.
     */
    public static final int STATUS_SHUTDOWN_IN_PROGRESS = 0XC00002FE;

    /**
     * The server machine is shutting down.
     */
    public static final int STATUS_SERVER_SHUTDOWN_IN_PROGRESS = 0XC00002FF;

    /**
     * This operation is not supported on a computer running   Windows Server 2003 operating system for Small Business Server.
     */
    public static final int STATUS_NOT_SUPPORTED_ON_SBS = 0XC0000300;

    /**
     * The WMI GUID is no longer available.
     */
    public static final int STATUS_WMI_GUID_DISCONNECTED = 0XC0000301;

    /**
     * Collection or events for the WMI GUID is already   disabled.
     */
    public static final int STATUS_WMI_ALREADY_DISABLED = 0XC0000302;

    /**
     * Collection or events for the WMI GUID is already   enabled.
     */
    public static final int STATUS_WMI_ALREADY_ENABLED = 0XC0000303;

    /**
     * The master file table on the volume is too fragmented   to complete this operation.
     */
    public static final int STATUS_MFT_TOO_FRAGMENTED = 0XC0000304;

    /**
     * Copy protection failure.
     */
    public static final int STATUS_COPY_PROTECTION_FAILURE = 0XC0000305;

    /**
     * Copy protection error - DVD CSS Authentication failed.
     */
    public static final int STATUS_CSS_AUTHENTICATION_FAILURE = 0XC0000306;

    /**
     * Copy protection error - The specified sector does not   contain a valid key.
     */
    public static final int STATUS_CSS_KEY_NOT_PRESENT = 0XC0000307;

    /**
     * Copy protection error - DVD session key not established.
     */
    public static final int STATUS_CSS_KEY_NOT_ESTABLISHED = 0XC0000308;

    /**
     * Copy protection error - The read failed because the   sector is encrypted.
     */
    public static final int STATUS_CSS_SCRAMBLED_SECTOR = 0XC0000309;

    /**
     * Copy protection error - The region of the specified DVD   does not correspond to the region setting of the drive.
     */
    public static final int STATUS_CSS_REGION_MISMATCH = 0XC000030A;

    /**
     * Copy protection error - The region setting of the drive   might be permanent.
     */
    public static final int STATUS_CSS_RESETS_EXHAUSTED = 0XC000030B;

    /**
     * The Kerberos protocol encountered an error while   validating the KDC certificate during smart card logon. There is more   information in the system event log.
     */
    public static final int STATUS_PKINIT_FAILURE = 0XC0000320;

    /**
     * The Kerberos protocol encountered an error while   attempting to use the smart card subsystem.
     */
    public static final int STATUS_SMARTCARD_SUBSYSTEM_FAILURE = 0XC0000321;

    /**
     * The target server does not have acceptable Kerberos   credentials.
     */
    public static final int STATUS_NO_KERB_KEY = 0XC0000322;

    /**
     * The transport determined that the remote system is   down.
     */
    public static final int STATUS_HOST_DOWN = 0XC0000350;

    /**
     * An unsupported pre-authentication mechanism was   presented to the Kerberos package.
     */
    public static final int STATUS_UNSUPPORTED_PREAUTH = 0XC0000351;

    /**
     * The encryption algorithm that is used on the source   file needs a bigger key buffer than the one that is used on the destination   file.
     */
    public static final int STATUS_EFS_ALG_BLOB_TOO_BIG = 0XC0000352;

    /**
     * An attempt to remove a processes DebugPort was made,   but a port was not already associated with the process.
     */
    public static final int STATUS_PORT_NOT_SET = 0XC0000353;

    /**
     * An attempt to do an operation on a debug port failed   because the port is in the process of being deleted.
     */
    public static final int STATUS_DEBUGGER_INACTIVE = 0XC0000354;

    /**
     * This version of Windows is not compatible with the   behavior version of the directory forest, domain, or domain controller.
     */
    public static final int STATUS_DS_VERSION_CHECK_FAILURE = 0XC0000355;

    /**
     * The specified event is currently not being audited.
     */
    public static final int STATUS_AUDITING_DISABLED = 0XC0000356;

    /**
     * The machine account was created prior to Windows NT   4.0 operating system. The account needs to be recreated.
     */
    public static final int STATUS_PRENT4_MACHINE_ACCOUNT = 0XC0000357;

    /**
     * An account group cannot have a universal group as a   member.
     */
    public static final int STATUS_DS_AG_CANT_HAVE_UNIVERSAL_MEMBER = 0XC0000358;

    /**
     * The specified image file did not have the correct   format; it appears to be a 32-bit Windows image.
     */
    public static final int STATUS_INVALID_IMAGE_WIN_32 = 0XC0000359;

    /**
     * The specified image file did not have the correct   format; it appears to be a 64-bit Windows image.
     */
    public static final int STATUS_INVALID_IMAGE_WIN_64 = 0XC000035A;

    /**
     * The client\'s supplied SSPI channel bindings were   incorrect.
     */
    public static final int STATUS_BAD_BINDINGS = 0XC000035B;

    /**
     * The client session has expired; so the client must   re-authenticate to continue accessing the remote resources.
     */
    public static final int STATUS_NETWORK_SESSION_EXPIRED = 0XC000035C;

    /**
     * The AppHelp dialog box canceled; thus preventing the   application from starting.
     */
    public static final int STATUS_APPHELP_BLOCK = 0XC000035D;

    /**
     * The SID filtering operation removed all SIDs.
     */
    public static final int STATUS_ALL_SIDS_FILTERED = 0XC000035E;

    /**
     * The driver was not loaded because the system is starting   in safe mode.
     */
    public static final int STATUS_NOT_SAFE_MODE_DRIVER = 0XC000035F;

    /**
     * Access to %1 has been restricted by your Administrator   by the default software restriction policy level.
     */
    public static final int STATUS_ACCESS_DISABLED_BY_POLICY_DEFAULT = 0XC0000361;

    /**
     * Access to %1 has been restricted by your Administrator   by location with policy rule %2 placed on path %3.
     */
    public static final int STATUS_ACCESS_DISABLED_BY_POLICY_PATH = 0XC0000362;

    /**
     * Access to %1 has been restricted by your Administrator   by software publisher policy.
     */
    public static final int STATUS_ACCESS_DISABLED_BY_POLICY_PUBLISHER = 0XC0000363;

    /**
     * Access to %1 has been restricted by your Administrator   by policy rule %2.
     */
    public static final int STATUS_ACCESS_DISABLED_BY_POLICY_OTHER = 0XC0000364;

    /**
     * The driver was not loaded because it failed its   initialization call.
     */
    public static final int STATUS_FAILED_DRIVER_ENTRY = 0XC0000365;

    /**
     * The device encountered an error while applying power   or reading the device configuration. This might be caused by a failure of   your hardware or by a poor connection.
     */
    public static final int STATUS_DEVICE_ENUMERATION_ERROR = 0XC0000366;

    /**
     * The create operation failed because the name contained   at least one mount point that resolves to a volume to which the specified   device object is not attached.
     */
    public static final int STATUS_MOUNT_POINT_NOT_RESOLVED = 0XC0000368;

    /**
     * The device object parameter is either not a valid   device object or is not attached to the volume that is specified by the file   name.
     */
    public static final int STATUS_INVALID_DEVICE_OBJECT_PARAMETER = 0XC0000369;

    /**
     * A machine check error has occurred. Check the system   event log for additional information.
     */
    public static final int STATUS_MCA_OCCURED = 0XC000036A;

    /**
     * Driver %2 has been blocked from loading.
     */
    public static final int STATUS_DRIVER_BLOCKED_CRITICAL = 0XC000036B;

    /**
     * Driver %2 has been blocked from loading.
     */
    public static final int STATUS_DRIVER_BLOCKED = 0XC000036C;

    /**
     * There was error [%2] processing the driver database.
     */
    public static final int STATUS_DRIVER_DATABASE_ERROR = 0XC000036D;

    /**
     * System hive size has exceeded its limit.
     */
    public static final int STATUS_SYSTEM_HIVE_TOO_LARGE = 0XC000036E;

    /**
     * A dynamic link library (DLL) referenced a module that   was neither a DLL nor the process\'s executable image.
     */
    public static final int STATUS_INVALID_IMPORT_OF_NON_DLL = 0XC000036F;

    /**
     * The local account store does not contain secret   material for the specified account.
     */
    public static final int STATUS_NO_SECRETS = 0XC0000371;

    /**
     * Access to %1 has been restricted by your Administrator   by policy rule %2.
     */
    public static final int STATUS_ACCESS_DISABLED_NO_SAFER_UI_BY_POLICY = 0XC0000372;

    /**
     * The system was not able to allocate enough memory to   perform a stack switch.
     */
    public static final int STATUS_FAILED_STACK_SWITCH = 0XC0000373;

    /**
     * A heap has been corrupted.
     */
    public static final int STATUS_HEAP_CORRUPTION = 0XC0000374;

    /**
     * An incorrect PIN was presented to the smart card.
     */
    public static final int STATUS_SMARTCARD_WRONG_PIN = 0XC0000380;

    /**
     * The smart card is blocked.
     */
    public static final int STATUS_SMARTCARD_CARD_BLOCKED = 0XC0000381;

    /**
     * No PIN was presented to the smart card.
     */
    public static final int STATUS_SMARTCARD_CARD_NOT_AUTHENTICATED = 0XC0000382;

    /**
     * No smart card is available.
     */
    public static final int STATUS_SMARTCARD_NO_CARD = 0XC0000383;

    /**
     * The requested key container does not exist on the   smart card.
     */
    public static final int STATUS_SMARTCARD_NO_KEY_CONTAINER = 0XC0000384;

    /**
     * The requested certificate does not exist on the smart   card.
     */
    public static final int STATUS_SMARTCARD_NO_CERTIFICATE = 0XC0000385;

    /**
     * The requested keyset does not exist.
     */
    public static final int STATUS_SMARTCARD_NO_KEYSET = 0XC0000386;

    /**
     * A communication error with the smart card has been   detected.
     */
    public static final int STATUS_SMARTCARD_IO_ERROR = 0XC0000387;

    /**
     * The system detected a possible attempt to compromise   security. Ensure that you can contact the server that authenticated you.
     */
    public static final int STATUS_DOWNGRADE_DETECTED = 0XC0000388;

    /**
     * The smart card certificate used for authentication has   been revoked. Contact your system administrator. There might be additional   information in the event log.
     */
    public static final int STATUS_SMARTCARD_CERT_REVOKED = 0XC0000389;

    /**
     * An untrusted certificate authority was detected while   processing the smart card certificate that is used for authentication.   Contact your system administrator.
     */
    public static final int STATUS_ISSUING_CA_UNTRUSTED = 0XC000038A;

    /**
     * The revocation status of the smart card certificate   that is used for authentication could not be determined. Contact your system administrator.
     */
    public static final int STATUS_REVOCATION_OFFLINE_C = 0XC000038B;

    /**
     * The smart card certificate used for authentication was   not trusted. Contact your system administrator.
     */
    public static final int STATUS_PKINIT_CLIENT_FAILURE = 0XC000038C;

    /**
     * The smart card certificate used for authentication has   expired. Contact your system administrator.
     */
    public static final int STATUS_SMARTCARD_CERT_EXPIRED = 0XC000038D;

    /**
     * The driver could not be loaded because a previous   version of the driver is still in memory.
     */
    public static final int STATUS_DRIVER_FAILED_PRIOR_UNLOAD = 0XC000038E;

    /**
     * The smart card provider could not perform the action   because the context was acquired as silent.
     */
    public static final int STATUS_SMARTCARD_SILENT_CONTEXT = 0XC000038F;

    /**
     * The delegated trust creation quota of the current user   has been exceeded.
     */
    public static final int STATUS_PER_USER_TRUST_QUOTA_EXCEEDED = 0XC0000401;

    /**
     * The total delegated trust creation quota has been   exceeded.
     */
    public static final int STATUS_ALL_USER_TRUST_QUOTA_EXCEEDED = 0XC0000402;

    /**
     * The delegated trust deletion quota of the current user   has been exceeded.
     */
    public static final int STATUS_USER_DELETE_TRUST_QUOTA_EXCEEDED = 0XC0000403;

    /**
     * The requested name already exists as a unique   identifier.
     */
    public static final int STATUS_DS_NAME_NOT_UNIQUE = 0XC0000404;

    /**
     * The requested object has a non-unique identifier and   cannot be retrieved.
     */
    public static final int STATUS_DS_DUPLICATE_ID_FOUND = 0XC0000405;

    /**
     * The group cannot be converted due to attribute   restrictions on the requested group type.
     */
    public static final int STATUS_DS_GROUP_CONVERSION_ERROR = 0XC0000406;

    /**
     * {Volume Shadow Copy Service} Wait while the Volume   Shadow Copy Service prepares volume %hs for hibernation.
     */
    public static final int STATUS_VOLSNAP_PREPARE_HIBERNATE = 0XC0000407;

    /**
     * Kerberos sub-protocol User2User is required.
     */
    public static final int STATUS_USER2USER_REQUIRED = 0XC0000408;

    /**
     * The system detected an overrun of a stack-based buffer   in this application. This overrun could potentially allow a malicious user to   gain control of this application.
     */
    public static final int STATUS_STACK_BUFFER_OVERRUN = 0XC0000409;

    /**
     * The Kerberos subsystem encountered an error. A service   for user protocol request was made against a domain controller which does not   support service for user.
     */
    public static final int STATUS_NO_S4U_PROT_SUPPORT = 0XC000040A;

    /**
     * An attempt was made by this server to make a Kerberos   constrained delegation request for a target that is outside the server realm.   This action is not supported and the resulting error indicates a
     * misconfiguration on the allowed-to-delegate-to list for this server. Contact   your administrator.
     */
    public static final int STATUS_CROSSREALM_DELEGATION_FAILURE = 0XC000040B;

    /**
     * The revocation status of the domain controller   certificate used for smart card authentication could not be determined. There   is additional information in the system event log. Contact your system
     * administrator.
     */
    public static final int STATUS_REVOCATION_OFFLINE_KDC = 0XC000040C;

    /**
     * An untrusted certificate authority was detected while   processing the domain controller certificate used for authentication. There   is additional information in the system event log. Contact your system
     * administrator.
     */
    public static final int STATUS_ISSUING_CA_UNTRUSTED_KDC = 0XC000040D;

    /**
     * The domain controller certificate used for smart card   logon has expired. Contact your system administrator with the contents of   your system event log.
     */
    public static final int STATUS_KDC_CERT_EXPIRED = 0XC000040E;

    /**
     * The domain controller certificate used for smart card   logon has been revoked. Contact your system administrator with the contents   of your system event log.
     */
    public static final int STATUS_KDC_CERT_REVOKED = 0XC000040F;

    /**
     * Data present in one of the parameters is more than the   function can operate on.
     */
    public static final int STATUS_PARAMETER_QUOTA_EXCEEDED = 0XC0000410;

    /**
     * The system has failed to hibernate (The error code is   %hs). Hibernation will be disabled until the system is restarted.
     */
    public static final int STATUS_HIBERNATION_FAILURE = 0XC0000411;

    /**
     * An attempt to delay-load a .dll or get a function   address in a delay-loaded .dll failed.
     */
    public static final int STATUS_DELAY_LOAD_FAILED = 0XC0000412;

    /**
     * Logon Failure: The machine you are logging onto is   protected by an authentication firewall. The specified account is not allowed   to authenticate to the machine.
     */
    public static final int STATUS_AUTHENTICATION_FIREWALL_FAILED = 0XC0000413;

    /**
     * %hs is a 16-bit application. You do not have   permissions to execute 16-bit applications. Check your permissions with your   system administrator.
     */
    public static final int STATUS_VDM_DISALLOWED = 0XC0000414;

    /**
     * {Display Driver Stopped Responding} The %hs display   driver has stopped working normally. Save your work and reboot the system to   restore full display functionality. The next time you reboot the machine a
     * dialog will be displayed giving you a chance to report this failure to   Microsoft.
     */
    public static final int STATUS_HUNG_DISPLAY_DRIVER_THREAD = 0XC0000415;

    /**
     * The Desktop heap encountered an error while allocating   session memory. There is more information in the system event log.
     */
    public static final int STATUS_INSUFFICIENT_RESOURCE_FOR_SPECIFIED_SHARED_SECTION_SIZE = 0XC0000416;

    /**
     * An invalid parameter was passed to a C runtime   function.
     */
    public static final int STATUS_INVALID_CRUNTIME_PARAMETER = 0XC0000417;

    /**
     * The authentication failed because NTLM was blocked.
     */
    public static final int STATUS_NTLM_BLOCKED = 0XC0000418;

    /**
     * The source object\'s SID already exists in destination   forest.
     */
    public static final int STATUS_DS_SRC_SID_EXISTS_IN_FOREST = 0XC0000419;

    /**
     * The domain name of the trusted domain already exists   in the forest.
     */
    public static final int STATUS_DS_DOMAIN_NAME_EXISTS_IN_FOREST = 0XC000041A;

    /**
     * The flat name of the trusted domain already exists in   the forest.
     */
    public static final int STATUS_DS_FLAT_NAME_EXISTS_IN_FOREST = 0XC000041B;

    /**
     * The User Principal Name (UPN) is invalid.
     */
    public static final int STATUS_INVALID_USER_PRINCIPAL_NAME = 0XC000041C;

    /**
     * There has been an assertion failure.
     */
    public static final int STATUS_ASSERTION_FAILURE = 0XC0000420;

    /**
     * Application verifier has found an error in the current   process.
     */
    public static final int STATUS_VERIFIER_STOP = 0XC0000421;

    /**
     * A user mode unwind is in progress.
     */
    public static final int STATUS_CALLBACK_POP_STACK = 0XC0000423;

    /**
     * %2 has been blocked from loading due to   incompatibility with this system. Contact your software vendor for a   compatible version of the driver.
     */
    public static final int STATUS_INCOMPATIBLE_DRIVER_BLOCKED = 0XC0000424;

    /**
     * Illegal operation attempted on a registry key which   has already been unloaded.
     */
    public static final int STATUS_HIVE_UNLOADED = 0XC0000425;

    /**
     * Compression is disabled for this volume.
     */
    public static final int STATUS_COMPRESSION_DISABLED = 0XC0000426;

    /**
     * The requested operation could not be completed due to   a file system limitation.
     */
    public static final int STATUS_FILE_SYSTEM_LIMITATION = 0XC0000427;

    /**
     * The hash for image %hs cannot be found in the system   catalogs. The image is likely corrupt or the victim of tampering.
     */
    public static final int STATUS_INVALID_IMAGE_HASH = 0XC0000428;

    /**
     * The implementation is not capable of performing the   request.
     */
    public static final int STATUS_NOT_CAPABLE = 0XC0000429;

    /**
     * The requested operation is out of order with respect   to other operations.
     */
    public static final int STATUS_REQUEST_OUT_OF_SEQUENCE = 0XC000042A;

    /**
     * An operation attempted to exceed an   implementation-defined limit.
     */
    public static final int STATUS_IMPLEMENTATION_LIMIT = 0XC000042B;

    /**
     * The requested operation requires elevation.
     */
    public static final int STATUS_ELEVATION_REQUIRED = 0XC000042C;

    /**
     * The required security context does not exist.
     */
    public static final int STATUS_NO_SECURITY_CONTEXT = 0XC000042D;

    /**
     * The PKU2U protocol encountered an error while   attempting to utilize the associated certificates.
     */
    public static final int STATUS_PKU2U_CERT_FAILURE = 0XC000042E;

    /**
     * The operation was attempted beyond the valid data   length of the file.
     */
    public static final int STATUS_BEYOND_VDL = 0XC0000432;

    /**
     * The attempted write operation encountered a write already   in progress for some portion of the range.
     */
    public static final int STATUS_ENCOUNTERED_WRITE_IN_PROGRESS = 0XC0000433;

    /**
     * The page fault mappings changed in the middle of   processing a fault so the operation must be retried.
     */
    public static final int STATUS_PTE_CHANGED = 0XC0000434;

    /**
     * The attempt to purge this file from memory failed to   purge some or all the data from memory.
     */
    public static final int STATUS_PURGE_FAILED = 0XC0000435;

    /**
     * The requested credential requires confirmation.
     */
    public static final int STATUS_CRED_REQUIRES_CONFIRMATION = 0XC0000440;

    /**
     * The remote server sent an invalid response for a file   being opened with Client Side Encryption.
     */
    public static final int STATUS_CS_ENCRYPTION_INVALID_SERVER_RESPONSE = 0XC0000441;

    /**
     * Client Side Encryption is not supported by the remote   server even though it claims to support it.
     */
    public static final int STATUS_CS_ENCRYPTION_UNSUPPORTED_SERVER = 0XC0000442;

    /**
     * File is encrypted and should be opened in Client Side   Encryption mode.
     */
    public static final int STATUS_CS_ENCRYPTION_EXISTING_ENCRYPTED_FILE = 0XC0000443;

    /**
     * A new encrypted file is being created and a $EFS needs   to be provided.
     */
    public static final int STATUS_CS_ENCRYPTION_NEW_ENCRYPTED_FILE = 0XC0000444;

    /**
     * The SMB client requested a CSE FSCTL on a non-CSE   file.
     */
    public static final int STATUS_CS_ENCRYPTION_FILE_NOT_CSE = 0XC0000445;

    /**
     * Indicates a particular Security ID cannot be assigned   as the label of an object.
     */
    public static final int STATUS_INVALID_LABEL = 0XC0000446;

    /**
     * The process hosting the driver for this device has   terminated.
     */
    public static final int STATUS_DRIVER_PROCESS_TERMINATED = 0XC0000450;

    /**
     * The requested system device cannot be identified due   to multiple indistinguishable devices potentially matching the identification   criteria.
     */
    public static final int STATUS_AMBIGUOUS_SYSTEM_DEVICE = 0XC0000451;

    /**
     * The requested system device cannot be found.
     */
    public static final int STATUS_SYSTEM_DEVICE_NOT_FOUND = 0XC0000452;

    /**
     * This boot application must be restarted.
     */
    public static final int STATUS_RESTART_BOOT_APPLICATION = 0XC0000453;

    /**
     * Insufficient NVRAM resources exist to complete the   API.  A reboot might be required.
     */
    public static final int STATUS_INSUFFICIENT_NVRAM_RESOURCES = 0XC0000454;

    /**
     * No ranges for the specified operation were able to be   processed.
     */
    public static final int STATUS_NO_RANGES_PROCESSED = 0XC0000460;

    /**
     * The storage device does not support Offload Write.
     */
    public static final int STATUS_DEVICE_FEATURE_NOT_SUPPORTED = 0XC0000463;

    /**
     * Data cannot be moved because the source device cannot   communicate with the destination device.
     */
    public static final int STATUS_DEVICE_UNREACHABLE = 0XC0000464;

    /**
     * The token representing the data is invalid or expired.
     */
    public static final int STATUS_INVALID_TOKEN = 0XC0000465;

    /**
     * The file server is temporarily unavailable.
     */
    public static final int STATUS_SERVER_UNAVAILABLE = 0XC0000466;

    /**
     * The specified task name is invalid.
     */
    public static final int STATUS_INVALID_TASK_NAME = 0XC0000500;

    /**
     * The specified task index is invalid.
     */
    public static final int STATUS_INVALID_TASK_INDEX = 0XC0000501;

    /**
     * The specified thread is already joining a task.
     */
    public static final int STATUS_THREAD_ALREADY_IN_TASK = 0XC0000502;

    /**
     * A callback has requested to bypass native code.
     */
    public static final int STATUS_CALLBACK_BYPASS = 0XC0000503;

    /**
     * A fail fast exception occurred. Exception handlers   will not be invoked and the process will be terminated immediately.
     */
    public static final int STATUS_FAIL_FAST_EXCEPTION = 0XC0000602;

    /**
     * Windows cannot verify the digital signature for this   file. The signing certificate for this file has been revoked.
     */
    public static final int STATUS_IMAGE_CERT_REVOKED = 0XC0000603;

    /**
     * The ALPC port is closed.
     */
    public static final int STATUS_PORT_CLOSED = 0XC0000700;

    /**
     * The ALPC message requested is no longer available.
     */
    public static final int STATUS_MESSAGE_LOST = 0XC0000701;

    /**
     * The ALPC message supplied is invalid.
     */
    public static final int STATUS_INVALID_MESSAGE = 0XC0000702;

    /**
     * The ALPC message has been canceled.
     */
    public static final int STATUS_REQUEST_CANCELED = 0XC0000703;

    /**
     * Invalid recursive dispatch attempt.
     */
    public static final int STATUS_RECURSIVE_DISPATCH = 0XC0000704;

    /**
     * No receive buffer has been supplied in a synchronous   request.
     */
    public static final int STATUS_LPC_RECEIVE_BUFFER_EXPECTED = 0XC0000705;

    /**
     * The connection port is used in an invalid context.
     */
    public static final int STATUS_LPC_INVALID_CONNECTION_USAGE = 0XC0000706;

    /**
     * The ALPC port does not accept new request messages.
     */
    public static final int STATUS_LPC_REQUESTS_NOT_ALLOWED = 0XC0000707;

    /**
     * The resource requested is already in use.
     */
    public static final int STATUS_RESOURCE_IN_USE = 0XC0000708;

    /**
     * The hardware has reported an uncorrectable memory   error.
     */
    public static final int STATUS_HARDWARE_MEMORY_ERROR = 0XC0000709;

    /**
     * Status 0x%08x was returned, waiting on handle 0x%x for   wait 0x%p, in waiter 0x%p.
     */
    public static final int STATUS_THREADPOOL_HANDLE_EXCEPTION = 0XC000070A;

    /**
     * After a callback to 0x%p(0x%p), a completion call to   Set event(0x%p) failed with status 0x%08x.
     */
    public static final int STATUS_THREADPOOL_SET_EVENT_ON_COMPLETION_FAILED = 0XC000070B;

    /**
     * After a callback to 0x%p(0x%p), a completion call to   ReleaseSemaphore(0x%p, %d) failed with status 0x%08x.
     */
    public static final int STATUS_THREADPOOL_RELEASE_SEMAPHORE_ON_COMPLETION_FAILED = 0XC000070C;

    /**
     * After a callback to 0x%p(0x%p), a completion call to   ReleaseMutex(%p) failed with status 0x%08x.
     */
    public static final int STATUS_THREADPOOL_RELEASE_MUTEX_ON_COMPLETION_FAILED = 0XC000070D;

    /**
     * After a callback to 0x%p(0x%p), a completion call to   FreeLibrary(%p) failed with status 0x%08x.
     */
    public static final int STATUS_THREADPOOL_FREE_LIBRARY_ON_COMPLETION_FAILED = 0XC000070E;

    /**
     * The thread pool 0x%p was released while a thread was   posting a callback to 0x%p(0x%p) to it.
     */
    public static final int STATUS_THREADPOOL_RELEASED_DURING_OPERATION = 0XC000070F;

    /**
     * A thread pool worker thread is impersonating a client,   after a callback to 0x%p(0x%p). This is unexpected, indicating that the   callback is missing a call to revert the impersonation.
     */
    public static final int STATUS_CALLBACK_RETURNED_WHILE_IMPERSONATING = 0XC0000710;

    /**
     * A thread pool worker thread is impersonating a client,   after executing an APC. This is unexpected, indicating that the APC is   missing a call to revert the impersonation.
     */
    public static final int STATUS_APC_RETURNED_WHILE_IMPERSONATING = 0XC0000711;

    /**
     * Either the target process, or the target thread\'s   containing process, is a protected process.
     */
    public static final int STATUS_PROCESS_IS_PROTECTED = 0XC0000712;

    /**
     * A thread is getting dispatched with MCA EXCEPTION   because of MCA.
     */
    public static final int STATUS_MCA_EXCEPTION = 0XC0000713;

    /**
     * The client certificate account mapping is not unique.
     */
    public static final int STATUS_CERTIFICATE_MAPPING_NOT_UNIQUE = 0XC0000714;

    /**
     * The symbolic link cannot be followed because its type   is disabled.
     */
    public static final int STATUS_SYMLINK_CLASS_DISABLED = 0XC0000715;

    /**
     * Indicates that the specified string is not valid for   IDN normalization.
     */
    public static final int STATUS_INVALID_IDN_NORMALIZATION = 0XC0000716;

    /**
     * No mapping for the Unicode character exists in the   target multi-byte code page.
     */
    public static final int STATUS_NO_UNICODE_TRANSLATION = 0XC0000717;

    /**
     * The provided callback is already registered.
     */
    public static final int STATUS_ALREADY_REGISTERED = 0XC0000718;

    /**
     * The provided context did not match the target.
     */
    public static final int STATUS_CONTEXT_MISMATCH = 0XC0000719;

    /**
     * The specified port already has a completion list.
     */
    public static final int STATUS_PORT_ALREADY_HAS_COMPLETION_LIST = 0XC000071A;

    /**
     * A threadpool worker thread entered a callback at   thread base priority 0x%x and exited at priority 0x%x.
     */
    public static final int STATUS_CALLBACK_RETURNED_THREAD_PRIORITY = 0XC000071B;

    /**
     * An invalid thread, handle %p, is specified for this   operation. Possibly, a threadpool worker thread was specified.
     */
    public static final int STATUS_INVALID_THREAD = 0XC000071C;

    /**
     * A threadpool worker thread entered a callback, which   left transaction state.
     */
    public static final int STATUS_CALLBACK_RETURNED_TRANSACTION = 0XC000071D;

    /**
     * A threadpool worker thread entered a callback, which   left the loader lock held.
     */
    public static final int STATUS_CALLBACK_RETURNED_LDR_LOCK = 0XC000071E;

    /**
     * A threadpool worker thread entered a callback, which   left with preferred languages set.
     */
    public static final int STATUS_CALLBACK_RETURNED_LANG = 0XC000071F;

    /**
     * A threadpool worker thread entered a callback, which   left with background priorities set.
     */
    public static final int STATUS_CALLBACK_RETURNED_PRI_BACK = 0XC0000720;

    /**
     * The attempted operation required self healing to be   enabled.
     */
    public static final int STATUS_DISK_REPAIR_DISABLED = 0XC0000800;

    /**
     * The directory service cannot perform the requested   operation because a domain rename operation is in progress.
     */
    public static final int STATUS_DS_DOMAIN_RENAME_IN_PROGRESS = 0XC0000801;

    /**
     * An operation failed because the storage quota was   exceeded.
     */
    public static final int STATUS_DISK_QUOTA_EXCEEDED = 0XC0000802;

    /**
     * An operation failed because the content was blocked.
     */
    public static final int STATUS_CONTENT_BLOCKED = 0XC0000804;

    /**
     * The operation could not be completed due to bad   clusters on disk.
     */
    public static final int STATUS_BAD_CLUSTERS = 0XC0000805;

    /**
     * The operation could not be completed because the   volume is dirty. Please run the Chkdsk utility and try again.
     */
    public static final int STATUS_VOLUME_DIRTY = 0XC0000806;

    /**
     * This file is checked out or locked for editing by   another user.
     */
    public static final int STATUS_FILE_CHECKED_OUT = 0XC0000901;

    /**
     * The file must be checked out before saving changes.
     */
    public static final int STATUS_CHECKOUT_REQUIRED = 0XC0000902;

    /**
     * The file type being saved or retrieved has been   blocked.
     */
    public static final int STATUS_BAD_FILE_TYPE = 0XC0000903;

    /**
     * The file size exceeds the limit allowed and cannot be   saved.
     */
    public static final int STATUS_FILE_TOO_LARGE = 0XC0000904;

    /**
     * Access Denied. Before opening files in this location,   you must first browse to the e.g. site and select the option to log on   automatically.
     */
    public static final int STATUS_FORMS_AUTH_REQUIRED = 0XC0000905;

    /**
     * The operation did not complete successfully because   the file contains a virus.
     */
    public static final int STATUS_VIRUS_INFECTED = 0XC0000906;

    /**
     * This file contains a virus and cannot be opened. Due   to the nature of this virus, the file has been removed from this location.
     */
    public static final int STATUS_VIRUS_DELETED = 0XC0000907;

    /**
     * The resources required for this device conflict with   the MCFG table.
     */
    public static final int STATUS_BAD_MCFG_TABLE = 0XC0000908;

    /**
     * The operation did not complete successfully because it   would cause an oplock to be broken. The caller has requested that existing   oplocks not be broken.
     */
    public static final int STATUS_CANNOT_BREAK_OPLOCK = 0XC0000909;

    /**
     * WOW Assertion Error.
     */
    public static final int STATUS_WOW_ASSERTION = 0XC0009898;

    /**
     * The cryptographic signature is invalid.
     */
    public static final int STATUS_INVALID_SIGNATURE = 0XC000A000;

    /**
     * The cryptographic provider does not support HMAC.
     */
    public static final int STATUS_HMAC_NOT_SUPPORTED = 0XC000A001;

    /**
     * The IPsec queue overflowed.
     */
    public static final int STATUS_IPSEC_QUEUE_OVERFLOW = 0XC000A010;

    /**
     * The neighbor discovery queue overflowed.
     */
    public static final int STATUS_ND_QUEUE_OVERFLOW = 0XC000A011;

    /**
     * An Internet Control Message Protocol (ICMP) hop limit   exceeded error was received.
     */
    public static final int STATUS_HOPLIMIT_EXCEEDED = 0XC000A012;

    /**
     * The protocol is not installed on the local machine.
     */
    public static final int STATUS_PROTOCOL_NOT_SUPPORTED = 0XC000A013;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs; the data has been lost. This error might be caused   by network connectivity issues. Try to save this file elsewhere.
     */
    public static final int STATUS_LOST_WRITEBEHIND_DATA_NETWORK_DISCONNECTED = 0XC000A080;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs; the data has been lost. This error was returned by   the server on which the file exists. Try to save this file elsewhere.
     */
    public static final int STATUS_LOST_WRITEBEHIND_DATA_NETWORK_SERVER_ERROR = 0XC000A081;

    /**
     * {Delayed Write Failed} Windows was unable to save all   the data for the file %hs; the data has been lost. This error might be caused   if the device has been removed or the media is write-protected.
     */
    public static final int STATUS_LOST_WRITEBEHIND_DATA_LOCAL_DISK_ERROR = 0XC000A082;

    /**
     * Windows was unable to parse the requested XML data.
     */
    public static final int STATUS_XML_PARSE_ERROR = 0XC000A083;

    /**
     * An error was encountered while processing an XML   digital signature.
     */
    public static final int STATUS_XMLDSIG_ERROR = 0XC000A084;

    /**
     * This indicates that the caller made the connection   request in the wrong routing compartment.
     */
    public static final int STATUS_WRONG_COMPARTMENT = 0XC000A085;

    /**
     * This indicates that there was an AuthIP failure when   attempting to connect to the remote host.
     */
    public static final int STATUS_AUTHIP_FAILURE = 0XC000A086;

    /**
     * OID mapped groups cannot have members.
     */
    public static final int STATUS_DS_OID_MAPPED_GROUP_CANT_HAVE_MEMBERS = 0XC000A087;

    /**
     * The specified OID cannot be found.
     */
    public static final int STATUS_DS_OID_NOT_FOUND = 0XC000A088;

    /**
     * Hash generation for the specified version and hash   type is not enabled on server.
     */
    public static final int STATUS_HASH_NOT_SUPPORTED = 0XC000A100;

    /**
     * The hash requests is not present or not up to date   with the current file contents.
     */
    public static final int STATUS_HASH_NOT_PRESENT = 0XC000A101;

    /**
     * A file system filter on the server has not opted in   for Offload Read support.
     */
    public static final int STATUS_OFFLOAD_READ_FLT_NOT_SUPPORTED = 0XC000A2A1;

    /**
     * A file system filter on the server has not opted in   for Offload Write support.
     */
    public static final int STATUS_OFFLOAD_WRITE_FLT_NOT_SUPPORTED = 0XC000A2A2;

    /**
     * Offload read operations cannot be performed on:
     */
    public static final int STATUS_OFFLOAD_READ_FILE_NOT_SUPPORTED = 0XC000A2A3;

    /**
     * Offload write operations cannot be performed on:
     */
    public static final int STATUS_OFFLOAD_WRITE_FILE_NOT_SUPPORTED = 0XC000A2A4;

    /**
     * The debugger did not perform a state change.
     */
    public static final int DBG_NO_STATE_CHANGE = 0XC0010001;

    /**
     * The debugger found that the application is not idle.
     */
    public static final int DBG_APP_NOT_IDLE = 0XC0010002;

    /**
     * The string binding is invalid.
     */
    public static final int RPC_NT_INVALID_STRING_BINDING = 0XC0020001;

    /**
     * The binding handle is not the correct type.
     */
    public static final int RPC_NT_WRONG_KIND_OF_BINDING = 0XC0020002;

    /**
     * The binding handle is invalid.
     */
    public static final int RPC_NT_INVALID_BINDING = 0XC0020003;

    /**
     * The RPC protocol sequence is not supported.
     */
    public static final int RPC_NT_PROTSEQ_NOT_SUPPORTED = 0XC0020004;

    /**
     * The RPC protocol sequence is invalid.
     */
    public static final int RPC_NT_INVALID_RPC_PROTSEQ = 0XC0020005;

    /**
     * The string UUID is invalid.
     */
    public static final int RPC_NT_INVALID_STRING_UUID = 0XC0020006;

    /**
     * The endpoint format is invalid.
     */
    public static final int RPC_NT_INVALID_ENDPOINT_FORMAT = 0XC0020007;

    /**
     * The network address is invalid.
     */
    public static final int RPC_NT_INVALID_NET_ADDR = 0XC0020008;

    /**
     * No endpoint was found.
     */
    public static final int RPC_NT_NO_ENDPOINT_FOUND = 0XC0020009;

    /**
     * The time-out value is invalid.
     */
    public static final int RPC_NT_INVALID_TIMEOUT = 0XC002000A;

    /**
     * The object UUID was not found.
     */
    public static final int RPC_NT_OBJECT_NOT_FOUND = 0XC002000B;

    /**
     * The object UUID has already been registered.
     */
    public static final int RPC_NT_ALREADY_REGISTERED = 0XC002000C;

    /**
     * The type UUID has already been registered.
     */
    public static final int RPC_NT_TYPE_ALREADY_REGISTERED = 0XC002000D;

    /**
     * The RPC server is already listening.
     */
    public static final int RPC_NT_ALREADY_LISTENING = 0XC002000E;

    /**
     * No protocol sequences have been registered.
     */
    public static final int RPC_NT_NO_PROTSEQS_REGISTERED = 0XC002000F;

    /**
     * The RPC server is not listening.
     */
    public static final int RPC_NT_NOT_LISTENING = 0XC0020010;

    /**
     * The manager type is unknown.
     */
    public static final int RPC_NT_UNKNOWN_MGR_TYPE = 0XC0020011;

    /**
     * The interface is unknown.
     */
    public static final int RPC_NT_UNKNOWN_IF = 0XC0020012;

    /**
     * There are no bindings.
     */
    public static final int RPC_NT_NO_BINDINGS = 0XC0020013;

    /**
     * There are no protocol sequences.
     */
    public static final int RPC_NT_NO_PROTSEQS = 0XC0020014;

    /**
     * The endpoint cannot be created.
     */
    public static final int RPC_NT_CANT_CREATE_ENDPOINT = 0XC0020015;

    /**
     * Insufficient resources are available to complete this   operation.
     */
    public static final int RPC_NT_OUT_OF_RESOURCES = 0XC0020016;

    /**
     * The RPC server is unavailable.
     */
    public static final int RPC_NT_SERVER_UNAVAILABLE = 0XC0020017;

    /**
     * The RPC server is too busy to complete this operation.
     */
    public static final int RPC_NT_SERVER_TOO_BUSY = 0XC0020018;

    /**
     * The network options are invalid.
     */
    public static final int RPC_NT_INVALID_NETWORK_OPTIONS = 0XC0020019;

    /**
     * No RPCs are active on this thread.
     */
    public static final int RPC_NT_NO_CALL_ACTIVE = 0XC002001A;

    /**
     * The RPC failed.
     */
    public static final int RPC_NT_CALL_FAILED = 0XC002001B;

    /**
     * The RPC failed and did not execute.
     */
    public static final int RPC_NT_CALL_FAILED_DNE = 0XC002001C;

    /**
     * An RPC protocol error occurred.
     */
    public static final int RPC_NT_PROTOCOL_ERROR = 0XC002001D;

    /**
     * The RPC server does not support the transfer syntax.
     */
    public static final int RPC_NT_UNSUPPORTED_TRANS_SYN = 0XC002001F;

    /**
     * The type UUID is not supported.
     */
    public static final int RPC_NT_UNSUPPORTED_TYPE = 0XC0020021;

    /**
     * The tag is invalid.
     */
    public static final int RPC_NT_INVALID_TAG = 0XC0020022;

    /**
     * The array bounds are invalid.
     */
    public static final int RPC_NT_INVALID_BOUND = 0XC0020023;

    /**
     * The binding does not contain an entry name.
     */
    public static final int RPC_NT_NO_ENTRY_NAME = 0XC0020024;

    /**
     * The name syntax is invalid.
     */
    public static final int RPC_NT_INVALID_NAME_SYNTAX = 0XC0020025;

    /**
     * The name syntax is not supported.
     */
    public static final int RPC_NT_UNSUPPORTED_NAME_SYNTAX = 0XC0020026;

    /**
     * No network address is available to construct a UUID.
     */
    public static final int RPC_NT_UUID_NO_ADDRESS = 0XC0020028;

    /**
     * The endpoint is a duplicate.
     */
    public static final int RPC_NT_DUPLICATE_ENDPOINT = 0XC0020029;

    /**
     * The authentication type is unknown.
     */
    public static final int RPC_NT_UNKNOWN_AUTHN_TYPE = 0XC002002A;

    /**
     * The maximum number of calls is too small.
     */
    public static final int RPC_NT_MAX_CALLS_TOO_SMALL = 0XC002002B;

    /**
     * The string is too long.
     */
    public static final int RPC_NT_STRING_TOO_LONG = 0XC002002C;

    /**
     * The RPC protocol sequence was not found.
     */
    public static final int RPC_NT_PROTSEQ_NOT_FOUND = 0XC002002D;

    /**
     * The procedure number is out of range.
     */
    public static final int RPC_NT_PROCNUM_OUT_OF_RANGE = 0XC002002E;

    /**
     * The binding does not contain any authentication   information.
     */
    public static final int RPC_NT_BINDING_HAS_NO_AUTH = 0XC002002F;

    /**
     * The authentication service is unknown.
     */
    public static final int RPC_NT_UNKNOWN_AUTHN_SERVICE = 0XC0020030;

    /**
     * The authentication level is unknown.
     */
    public static final int RPC_NT_UNKNOWN_AUTHN_LEVEL = 0XC0020031;

    /**
     * The security context is invalid.
     */
    public static final int RPC_NT_INVALID_AUTH_IDENTITY = 0XC0020032;

    /**
     * The authorization service is unknown.
     */
    public static final int RPC_NT_UNKNOWN_AUTHZ_SERVICE = 0XC0020033;

    /**
     * The entry is invalid.
     */
    public static final int EPT_NT_INVALID_ENTRY = 0XC0020034;

    /**
     * The operation cannot be performed.
     */
    public static final int EPT_NT_CANT_PERFORM_OP = 0XC0020035;

    /**
     * No more endpoints are available from the endpoint   mapper.
     */
    public static final int EPT_NT_NOT_REGISTERED = 0XC0020036;

    /**
     * No interfaces have been exported.
     */
    public static final int RPC_NT_NOTHING_TO_EXPORT = 0XC0020037;

    /**
     * The entry name is incomplete.
     */
    public static final int RPC_NT_INCOMPLETE_NAME = 0XC0020038;

    /**
     * The version option is invalid.
     */
    public static final int RPC_NT_INVALID_VERS_OPTION = 0XC0020039;

    /**
     * There are no more members.
     */
    public static final int RPC_NT_NO_MORE_MEMBERS = 0XC002003A;

    /**
     * There is nothing to unexport.
     */
    public static final int RPC_NT_NOT_ALL_OBJS_UNEXPORTED = 0XC002003B;

    /**
     * The interface was not found.
     */
    public static final int RPC_NT_INTERFACE_NOT_FOUND = 0XC002003C;

    /**
     * The entry already exists.
     */
    public static final int RPC_NT_ENTRY_ALREADY_EXISTS = 0XC002003D;

    /**
     * The entry was not found.
     */
    public static final int RPC_NT_ENTRY_NOT_FOUND = 0XC002003E;

    /**
     * The name service is unavailable.
     */
    public static final int RPC_NT_NAME_SERVICE_UNAVAILABLE = 0XC002003F;

    /**
     * The network address family is invalid.
     */
    public static final int RPC_NT_INVALID_NAF_ID = 0XC0020040;

    /**
     * The requested operation is not supported.
     */
    public static final int RPC_NT_CANNOT_SUPPORT = 0XC0020041;

    /**
     * No security context is available to allow   impersonation.
     */
    public static final int RPC_NT_NO_CONTEXT_AVAILABLE = 0XC0020042;

    /**
     * An internal error occurred in the RPC.
     */
    public static final int RPC_NT_INTERNAL_ERROR = 0XC0020043;

    /**
     * The RPC server attempted to divide an integer by zero.
     */
    public static final int RPC_NT_ZERO_DIVIDE = 0XC0020044;

    /**
     * An addressing error occurred in the RPC server.
     */
    public static final int RPC_NT_ADDRESS_ERROR = 0XC0020045;

    /**
     * A floating point operation at the RPC server caused a   divide by zero.
     */
    public static final int RPC_NT_FP_DIV_ZERO = 0XC0020046;

    /**
     * A floating point underflow occurred at the RPC server.
     */
    public static final int RPC_NT_FP_UNDERFLOW = 0XC0020047;

    /**
     * A floating point overflow occurred at the RPC server.
     */
    public static final int RPC_NT_FP_OVERFLOW = 0XC0020048;

    /**
     * An RPC is already in progress for this thread.
     */
    public static final int RPC_NT_CALL_IN_PROGRESS = 0XC0020049;

    /**
     * There are no more bindings.
     */
    public static final int RPC_NT_NO_MORE_BINDINGS = 0XC002004A;

    /**
     * The group member was not found.
     */
    public static final int RPC_NT_GROUP_MEMBER_NOT_FOUND = 0XC002004B;

    /**
     * The endpoint mapper database entry could not be   created.
     */
    public static final int EPT_NT_CANT_CREATE = 0XC002004C;

    /**
     * The object UUID is the nil UUID.
     */
    public static final int RPC_NT_INVALID_OBJECT = 0XC002004D;

    /**
     * No interfaces have been registered.
     */
    public static final int RPC_NT_NO_INTERFACES = 0XC002004F;

    /**
     * The RPC was canceled.
     */
    public static final int RPC_NT_CALL_CANCELLED = 0XC0020050;

    /**
     * The binding handle does not contain all the required   information.
     */
    public static final int RPC_NT_BINDING_INCOMPLETE = 0XC0020051;

    /**
     * A communications failure occurred during an RPC.
     */
    public static final int RPC_NT_COMM_FAILURE = 0XC0020052;

    /**
     * The requested authentication level is not supported.
     */
    public static final int RPC_NT_UNSUPPORTED_AUTHN_LEVEL = 0XC0020053;

    /**
     * No principal name was registered.
     */
    public static final int RPC_NT_NO_PRINC_NAME = 0XC0020054;

    /**
     * The error specified is not a valid Windows RPC error   code.
     */
    public static final int RPC_NT_NOT_RPC_ERROR = 0XC0020055;

    /**
     * A security package-specific error occurred.
     */
    public static final int RPC_NT_SEC_PKG_ERROR = 0XC0020057;

    /**
     * The thread was not canceled.
     */
    public static final int RPC_NT_NOT_CANCELLED = 0XC0020058;

    /**
     * Invalid asynchronous RPC handle.
     */
    public static final int RPC_NT_INVALID_ASYNC_HANDLE = 0XC0020062;

    /**
     * Invalid asynchronous RPC call handle for this   operation.
     */
    public static final int RPC_NT_INVALID_ASYNC_CALL = 0XC0020063;

    /**
     * Access to the HTTP proxy is denied.
     */
    public static final int RPC_NT_PROXY_ACCESS_DENIED = 0XC0020064;

    /**
     * The list of RPC servers available for auto-handle   binding has been exhausted.
     */
    public static final int RPC_NT_NO_MORE_ENTRIES = 0XC0030001;

    /**
     * The file designated by DCERPCCHARTRANS cannot be   opened.
     */
    public static final int RPC_NT_SS_CHAR_TRANS_OPEN_FAIL = 0XC0030002;

    /**
     * The file containing the character translation table   has fewer than 512 bytes.
     */
    public static final int RPC_NT_SS_CHAR_TRANS_SHORT_FILE = 0XC0030003;

    /**
     * A null context handle is passed as an [in] parameter.
     */
    public static final int RPC_NT_SS_IN_NULL_CONTEXT = 0XC0030004;

    /**
     * The context handle does not match any known context   handles.
     */
    public static final int RPC_NT_SS_CONTEXT_MISMATCH = 0XC0030005;

    /**
     * The context handle changed during a call.
     */
    public static final int RPC_NT_SS_CONTEXT_DAMAGED = 0XC0030006;

    /**
     * The binding handles passed to an RPC do not match.
     */
    public static final int RPC_NT_SS_HANDLES_MISMATCH = 0XC0030007;

    /**
     * The stub is unable to get the call handle.
     */
    public static final int RPC_NT_SS_CANNOT_GET_CALL_HANDLE = 0XC0030008;

    /**
     * A null reference pointer was passed to the stub.
     */
    public static final int RPC_NT_NULL_REF_POINTER = 0XC0030009;

    /**
     * The enumeration value is out of range.
     */
    public static final int RPC_NT_ENUM_VALUE_OUT_OF_RANGE = 0XC003000A;

    /**
     * The byte count is too small.
     */
    public static final int RPC_NT_BYTE_COUNT_TOO_SMALL = 0XC003000B;

    /**
     * The stub received bad data.
     */
    public static final int RPC_NT_BAD_STUB_DATA = 0XC003000C;

    /**
     * Invalid operation on the encoding/decoding handle.
     */
    public static final int RPC_NT_INVALID_ES_ACTION = 0XC0030059;

    /**
     * Incompatible version of the serializing package.
     */
    public static final int RPC_NT_WRONG_ES_VERSION = 0XC003005A;

    /**
     * Incompatible version of the RPC stub.
     */
    public static final int RPC_NT_WRONG_STUB_VERSION = 0XC003005B;

    /**
     * The RPC pipe object is invalid or corrupt.
     */
    public static final int RPC_NT_INVALID_PIPE_OBJECT = 0XC003005C;

    /**
     * An invalid operation was attempted on an RPC pipe   object.
     */
    public static final int RPC_NT_INVALID_PIPE_OPERATION = 0XC003005D;

    /**
     * Unsupported RPC pipe version.
     */
    public static final int RPC_NT_WRONG_PIPE_VERSION = 0XC003005E;

    /**
     * The RPC pipe object has already been closed.
     */
    public static final int RPC_NT_PIPE_CLOSED = 0XC003005F;

    /**
     * The RPC call completed before all pipes were   processed.
     */
    public static final int RPC_NT_PIPE_DISCIPLINE_ERROR = 0XC0030060;

    /**
     * No more data is available from the RPC pipe.
     */
    public static final int RPC_NT_PIPE_EMPTY = 0XC0030061;

    /**
     * A device is missing in the system BIOS MPS table. This   device will not be used. Contact your system vendor for a system BIOS update.
     */
    public static final int STATUS_PNP_BAD_MPS_TABLE = 0XC0040035;

    /**
     * A translator failed to translate resources.
     */
    public static final int STATUS_PNP_TRANSLATION_FAILED = 0XC0040036;

    /**
     * An IRQ translator failed to translate resources.
     */
    public static final int STATUS_PNP_IRQ_TRANSLATION_FAILED = 0XC0040037;

    /**
     * Driver %2 returned an invalid ID for a child device   (%3).
     */
    public static final int STATUS_PNP_INVALID_ID = 0XC0040038;

    /**
     * Reissue the given operation as a cached I/O operation
     */
    public static final int STATUS_IO_REISSUE_AS_CACHED = 0XC0040039;

    /**
     * Session name %1 is invalid.
     */
    public static final int STATUS_CTX_WINSTATION_NAME_INVALID = 0XC00A0001;

    /**
     * The protocol driver %1 is invalid.
     */
    public static final int STATUS_CTX_INVALID_PD = 0XC00A0002;

    /**
     * The protocol driver %1 was not found in the system   path.
     */
    public static final int STATUS_CTX_PD_NOT_FOUND = 0XC00A0003;

    /**
     * A close operation is pending on the terminal   connection.
     */
    public static final int STATUS_CTX_CLOSE_PENDING = 0XC00A0006;

    /**
     * No free output buffers are available.
     */
    public static final int STATUS_CTX_NO_OUTBUF = 0XC00A0007;

    /**
     * The MODEM.INF file was not found.
     */
    public static final int STATUS_CTX_MODEM_INF_NOT_FOUND = 0XC00A0008;

    /**
     * The modem (%1) was not found in the MODEM.INF file.
     */
    public static final int STATUS_CTX_INVALID_MODEMNAME = 0XC00A0009;

    /**
     * The modem did not accept the command sent to it.   Verify that the configured modem name matches the attached modem.
     */
    public static final int STATUS_CTX_RESPONSE_ERROR = 0XC00A000A;

    /**
     * The modem did not respond to the command sent to it.   Verify that the modem cable is properly attached and the modem is turned on.
     */
    public static final int STATUS_CTX_MODEM_RESPONSE_TIMEOUT = 0XC00A000B;

    /**
     * Carrier detection has failed or the carrier has been   dropped due to disconnection.
     */
    public static final int STATUS_CTX_MODEM_RESPONSE_NO_CARRIER = 0XC00A000C;

    /**
     * A dial tone was not detected within the required time.   Verify that the phone cable is properly attached and functional.
     */
    public static final int STATUS_CTX_MODEM_RESPONSE_NO_DIALTONE = 0XC00A000D;

    /**
     * A busy signal was detected at a remote site on   callback.
     */
    public static final int STATUS_CTX_MODEM_RESPONSE_BUSY = 0XC00A000E;

    /**
     * A voice was detected at a remote site on callback.
     */
    public static final int STATUS_CTX_MODEM_RESPONSE_VOICE = 0XC00A000F;

    /**
     * Transport driver error.
     */
    public static final int STATUS_CTX_TD_ERROR = 0XC00A0010;

    /**
     * The client you are using is not licensed to use this   system. Your logon request is denied.
     */
    public static final int STATUS_CTX_LICENSE_CLIENT_INVALID = 0XC00A0012;

    /**
     * The system has reached its licensed logon limit. Try   again later.
     */
    public static final int STATUS_CTX_LICENSE_NOT_AVAILABLE = 0XC00A0013;

    /**
     * The system license has expired. Your logon request is   denied.
     */
    public static final int STATUS_CTX_LICENSE_EXPIRED = 0XC00A0014;

    /**
     * The specified session cannot be found.
     */
    public static final int STATUS_CTX_WINSTATION_NOT_FOUND = 0XC00A0015;

    /**
     * The specified session name is already in use.
     */
    public static final int STATUS_CTX_WINSTATION_NAME_COLLISION = 0XC00A0016;

    /**
     * The requested operation cannot be completed because   the terminal connection is currently processing a connect, disconnect, reset,   or delete operation.
     */
    public static final int STATUS_CTX_WINSTATION_BUSY = 0XC00A0017;

    /**
     * An attempt has been made to connect to a session whose   video mode is not supported by the current client.
     */
    public static final int STATUS_CTX_BAD_VIDEO_MODE = 0XC00A0018;

    /**
     * The application attempted to enable DOS graphics mode.   DOS graphics mode is not supported.
     */
    public static final int STATUS_CTX_GRAPHICS_INVALID = 0XC00A0022;

    /**
     * The requested operation can be performed only on the   system console. This is most often the result of a driver or system DLL   requiring direct console access.
     */
    public static final int STATUS_CTX_NOT_CONSOLE = 0XC00A0024;

    /**
     * The client failed to respond to the server connect   message.
     */
    public static final int STATUS_CTX_CLIENT_QUERY_TIMEOUT = 0XC00A0026;

    /**
     * Disconnecting the console session is not supported.
     */
    public static final int STATUS_CTX_CONSOLE_DISCONNECT = 0XC00A0027;

    /**
     * Reconnecting a disconnected session to the console is   not supported.
     */
    public static final int STATUS_CTX_CONSOLE_CONNECT = 0XC00A0028;

    /**
     * The request to control another session remotely was denied.
     */
    public static final int STATUS_CTX_SHADOW_DENIED = 0XC00A002A;

    /**
     * A process has requested access to a session, but has   not been granted those access rights.
     */
    public static final int STATUS_CTX_WINSTATION_ACCESS_DENIED = 0XC00A002B;

    /**
     * The terminal connection driver %1 is invalid.
     */
    public static final int STATUS_CTX_INVALID_WD = 0XC00A002E;

    /**
     * The terminal connection driver %1 was not found in the   system path.
     */
    public static final int STATUS_CTX_WD_NOT_FOUND = 0XC00A002F;

    /**
     * The requested session cannot be controlled remotely.   You cannot control your own session, a session that is trying to control your   session, a session that has no user logged on, or other sessions from the
     * console.
     */
    public static final int STATUS_CTX_SHADOW_INVALID = 0XC00A0030;

    /**
     * The requested session is not configured to allow   remote control.
     */
    public static final int STATUS_CTX_SHADOW_DISABLED = 0XC00A0031;

    /**
     * The RDP protocol component %2 detected an error in the   protocol stream and has disconnected the client.
     */
    public static final int STATUS_RDP_PROTOCOL_ERROR = 0XC00A0032;

    /**
     * None
     */
    public static final int STATUS_CTX_CLIENT_LICENSE_NOT_SET = 0XC00A0033;

    /**
     * Your request to connect to this terminal server has   been rejected. Your terminal server client license number is currently being   used by another user. Contact your system administrator to obtain a new copy
     * of the terminal server client with a valid, unique license number. Click OK   to continue.
     */
    public static final int STATUS_CTX_CLIENT_LICENSE_IN_USE = 0XC00A0034;

    /**
     * The remote control of the console was terminated   because the display mode was changed. Changing the display mode in a remote   control session is not supported.
     */
    public static final int STATUS_CTX_SHADOW_ENDED_BY_MODE_CHANGE = 0XC00A0035;

    /**
     * Remote control could not be terminated because the   specified session is not currently being remotely controlled.
     */
    public static final int STATUS_CTX_SHADOW_NOT_RUNNING = 0XC00A0036;

    /**
     * Your interactive logon privilege has been disabled.   Contact your system administrator.
     */
    public static final int STATUS_CTX_LOGON_DISABLED = 0XC00A0037;

    /**
     * The terminal server security layer detected an error   in the protocol stream and has disconnected the client.
     */
    public static final int STATUS_CTX_SECURITY_LAYER_ERROR = 0XC00A0038;

    /**
     * The target session is incompatible with the current   session.
     */
    public static final int STATUS_TS_INCOMPATIBLE_SESSIONS = 0XC00A0039;

    /**
     * The resource loader failed to find an MUI file.
     */
    public static final int STATUS_MUI_FILE_NOT_FOUND = 0XC00B0001;

    /**
     * The resource loader failed to load an MUI file because   the file failed to pass validation.
     */
    public static final int STATUS_MUI_INVALID_FILE = 0XC00B0002;

    /**
     * The RC manifest is corrupted with garbage data, is an   unsupported version, or is missing a required item.
     */
    public static final int STATUS_MUI_INVALID_RC_CONFIG = 0XC00B0003;

    /**
     * The RC manifest has an invalid culture name.
     */
    public static final int STATUS_MUI_INVALID_LOCALE_NAME = 0XC00B0004;

    /**
     * The RC manifest has and invalid ultimate fallback   name.
     */
    public static final int STATUS_MUI_INVALID_ULTIMATEFALLBACK_NAME = 0XC00B0005;

    /**
     * The resource loader cache does not have a loaded MUI   entry.
     */
    public static final int STATUS_MUI_FILE_NOT_LOADED = 0XC00B0006;

    /**
     * The user stopped resource enumeration.
     */
    public static final int STATUS_RESOURCE_ENUM_USER_STOP = 0XC00B0007;

    /**
     * The cluster node is not valid.
     */
    public static final int STATUS_CLUSTER_INVALID_NODE = 0XC0130001;

    /**
     * The cluster node already exists.
     */
    public static final int STATUS_CLUSTER_NODE_EXISTS = 0XC0130002;

    /**
     * A node is in the process of joining the cluster.
     */
    public static final int STATUS_CLUSTER_JOIN_IN_PROGRESS = 0XC0130003;

    /**
     * The cluster node was not found.
     */
    public static final int STATUS_CLUSTER_NODE_NOT_FOUND = 0XC0130004;

    /**
     * The cluster local node information was not found.
     */
    public static final int STATUS_CLUSTER_LOCAL_NODE_NOT_FOUND = 0XC0130005;

    /**
     * The cluster network already exists.
     */
    public static final int STATUS_CLUSTER_NETWORK_EXISTS = 0XC0130006;

    /**
     * The cluster network was not found.
     */
    public static final int STATUS_CLUSTER_NETWORK_NOT_FOUND = 0XC0130007;

    /**
     * The cluster network interface already exists.
     */
    public static final int STATUS_CLUSTER_NETINTERFACE_EXISTS = 0XC0130008;

    /**
     * The cluster network interface was not found.
     */
    public static final int STATUS_CLUSTER_NETINTERFACE_NOT_FOUND = 0XC0130009;

    /**
     * The cluster request is not valid for this object.
     */
    public static final int STATUS_CLUSTER_INVALID_REQUEST = 0XC013000A;

    /**
     * The cluster network provider is not valid.
     */
    public static final int STATUS_CLUSTER_INVALID_NETWORK_PROVIDER = 0XC013000B;

    /**
     * The cluster node is down.
     */
    public static final int STATUS_CLUSTER_NODE_DOWN = 0XC013000C;

    /**
     * The cluster node is not reachable.
     */
    public static final int STATUS_CLUSTER_NODE_UNREACHABLE = 0XC013000D;

    /**
     * The cluster node is not a member of the cluster.
     */
    public static final int STATUS_CLUSTER_NODE_NOT_MEMBER = 0XC013000E;

    /**
     * A cluster join operation is not in progress.
     */
    public static final int STATUS_CLUSTER_JOIN_NOT_IN_PROGRESS = 0XC013000F;

    /**
     * The cluster network is not valid.
     */
    public static final int STATUS_CLUSTER_INVALID_NETWORK = 0XC0130010;

    /**
     * No network adapters are available.
     */
    public static final int STATUS_CLUSTER_NO_NET_ADAPTERS = 0XC0130011;

    /**
     * The cluster node is up.
     */
    public static final int STATUS_CLUSTER_NODE_UP = 0XC0130012;

    /**
     * The cluster node is paused.
     */
    public static final int STATUS_CLUSTER_NODE_PAUSED = 0XC0130013;

    /**
     * The cluster node is not paused.
     */
    public static final int STATUS_CLUSTER_NODE_NOT_PAUSED = 0XC0130014;

    /**
     * No cluster security context is available.
     */
    public static final int STATUS_CLUSTER_NO_SECURITY_CONTEXT = 0XC0130015;

    /**
     * The cluster network is not configured for internal   cluster communication.
     */
    public static final int STATUS_CLUSTER_NETWORK_NOT_INTERNAL = 0XC0130016;

    /**
     * The cluster node has been poisoned.
     */
    public static final int STATUS_CLUSTER_POISONED = 0XC0130017;

    /**
     * An attempt was made to run an invalid AML opcode.
     */
    public static final int STATUS_ACPI_INVALID_OPCODE = 0XC0140001;

    /**
     * The AML interpreter stack has overflowed.
     */
    public static final int STATUS_ACPI_STACK_OVERFLOW = 0XC0140002;

    /**
     * An inconsistent state has occurred.
     */
    public static final int STATUS_ACPI_ASSERT_FAILED = 0XC0140003;

    /**
     * An attempt was made to access an array outside its   bounds.
     */
    public static final int STATUS_ACPI_INVALID_INDEX = 0XC0140004;

    /**
     * A required argument was not specified.
     */
    public static final int STATUS_ACPI_INVALID_ARGUMENT = 0XC0140005;

    /**
     * A fatal error has occurred.
     */
    public static final int STATUS_ACPI_FATAL = 0XC0140006;

    /**
     * An invalid SuperName was specified.
     */
    public static final int STATUS_ACPI_INVALID_SUPERNAME = 0XC0140007;

    /**
     * An argument with an incorrect type was specified.
     */
    public static final int STATUS_ACPI_INVALID_ARGTYPE = 0XC0140008;

    /**
     * An object with an incorrect type was specified.
     */
    public static final int STATUS_ACPI_INVALID_OBJTYPE = 0XC0140009;

    /**
     * A target with an incorrect type was specified.
     */
    public static final int STATUS_ACPI_INVALID_TARGETTYPE = 0XC014000A;

    /**
     * An incorrect number of arguments was specified.
     */
    public static final int STATUS_ACPI_INCORRECT_ARGUMENT_COUNT = 0XC014000B;

    /**
     * An address failed to translate.
     */
    public static final int STATUS_ACPI_ADDRESS_NOT_MAPPED = 0XC014000C;

    /**
     * An incorrect event type was specified.
     */
    public static final int STATUS_ACPI_INVALID_EVENTTYPE = 0XC014000D;

    /**
     * A handler for the target already exists.
     */
    public static final int STATUS_ACPI_HANDLER_COLLISION = 0XC014000E;

    /**
     * Invalid data for the target was specified.
     */
    public static final int STATUS_ACPI_INVALID_DATA = 0XC014000F;

    /**
     * An invalid region for the target was specified.
     */
    public static final int STATUS_ACPI_INVALID_REGION = 0XC0140010;

    /**
     * An attempt was made to access a field outside the   defined range.
     */
    public static final int STATUS_ACPI_INVALID_ACCESS_SIZE = 0XC0140011;

    /**
     * The global system lock could not be acquired.
     */
    public static final int STATUS_ACPI_ACQUIRE_GLOBAL_LOCK = 0XC0140012;

    /**
     * An attempt was made to reinitialize the ACPI   subsystem.
     */
    public static final int STATUS_ACPI_ALREADY_INITIALIZED = 0XC0140013;

    /**
     * The ACPI subsystem has not been initialized.
     */
    public static final int STATUS_ACPI_NOT_INITIALIZED = 0XC0140014;

    /**
     * An incorrect mutex was specified.
     */
    public static final int STATUS_ACPI_INVALID_MUTEX_LEVEL = 0XC0140015;

    /**
     * The mutex is not currently owned.
     */
    public static final int STATUS_ACPI_MUTEX_NOT_OWNED = 0XC0140016;

    /**
     * An attempt was made to access the mutex by a process   that was not the owner.
     */
    public static final int STATUS_ACPI_MUTEX_NOT_OWNER = 0XC0140017;

    /**
     * An error occurred during an access to region space.
     */
    public static final int STATUS_ACPI_RS_ACCESS = 0XC0140018;

    /**
     * An attempt was made to use an incorrect table.
     */
    public static final int STATUS_ACPI_INVALID_TABLE = 0XC0140019;

    /**
     * The registration of an ACPI event failed.
     */
    public static final int STATUS_ACPI_REG_HANDLER_FAILED = 0XC0140020;

    /**
     * An ACPI power object failed to transition state.
     */
    public static final int STATUS_ACPI_POWER_REQUEST_FAILED = 0XC0140021;

    /**
     * The requested section is not present in the activation   context.
     */
    public static final int STATUS_SXS_SECTION_NOT_FOUND = 0XC0150001;

    /**
     * Windows was unble to process the application binding   information. Refer to the system event log for further information.
     */
    public static final int STATUS_SXS_CANT_GEN_ACTCTX = 0XC0150002;

    /**
     * The application binding data format is invalid.
     */
    public static final int STATUS_SXS_INVALID_ACTCTXDATA_FORMAT = 0XC0150003;

    /**
     * The referenced assembly is not installed on the   system.
     */
    public static final int STATUS_SXS_ASSEMBLY_NOT_FOUND = 0XC0150004;

    /**
     * The manifest file does not begin with the required tag   and format information.
     */
    public static final int STATUS_SXS_MANIFEST_FORMAT_ERROR = 0XC0150005;

    /**
     * The manifest file contains one or more syntax errors.
     */
    public static final int STATUS_SXS_MANIFEST_PARSE_ERROR = 0XC0150006;

    /**
     * The application attempted to activate a disabled   activation context.
     */
    public static final int STATUS_SXS_ACTIVATION_CONTEXT_DISABLED = 0XC0150007;

    /**
     * The requested lookup key was not found in any active   activation context.
     */
    public static final int STATUS_SXS_KEY_NOT_FOUND = 0XC0150008;

    /**
     * A component version required by the application   conflicts with another component version that is already active.
     */
    public static final int STATUS_SXS_VERSION_CONFLICT = 0XC0150009;

    /**
     * The type requested activation context section does not   match the query API used.
     */
    public static final int STATUS_SXS_WRONG_SECTION_TYPE = 0XC015000A;

    /**
     * Lack of system resources has required isolated   activation to be disabled for the current thread of execution.
     */
    public static final int STATUS_SXS_THREAD_QUERIES_DISABLED = 0XC015000B;

    /**
     * The referenced assembly could not be found.
     */
    public static final int STATUS_SXS_ASSEMBLY_MISSING = 0XC015000C;

    /**
     * An attempt to set the process default activation   context failed because the process default activation context was already   set.
     */
    public static final int STATUS_SXS_PROCESS_DEFAULT_ALREADY_SET = 0XC015000E;

    /**
     * The activation context being deactivated is not the   most recently activated one.
     */
    public static final int STATUS_SXS_EARLY_DEACTIVATION = 0XC015000F;

    /**
     * The activation context being deactivated is not active   for the current thread of execution.
     */
    public static final int STATUS_SXS_INVALID_DEACTIVATION = 0XC0150010;

    /**
     * The activation context being deactivated has already   been deactivated.
     */
    public static final int STATUS_SXS_MULTIPLE_DEACTIVATION = 0XC0150011;

    /**
     * The activation context of the system default assembly   could not be generated.
     */
    public static final int STATUS_SXS_SYSTEM_DEFAULT_ACTIVATION_CONTEXT_EMPTY = 0XC0150012;

    /**
     * A component used by the isolation facility has   requested that the process be terminated.
     */
    public static final int STATUS_SXS_PROCESS_TERMINATION_REQUESTED = 0XC0150013;

    /**
     * The activation context activation stack for the   running thread of execution is corrupt.
     */
    public static final int STATUS_SXS_CORRUPT_ACTIVATION_STACK = 0XC0150014;

    /**
     * The application isolation metadata for this process or   thread has become corrupt.
     */
    public static final int STATUS_SXS_CORRUPTION = 0XC0150015;

    /**
     * The value of an attribute in an identity is not within   the legal range.
     */
    public static final int STATUS_SXS_INVALID_IDENTITY_ATTRIBUTE_VALUE = 0XC0150016;

    /**
     * The name of an attribute in an identity is not within   the legal range.
     */
    public static final int STATUS_SXS_INVALID_IDENTITY_ATTRIBUTE_NAME = 0XC0150017;

    /**
     * An identity contains two definitions for the same   attribute.
     */
    public static final int STATUS_SXS_IDENTITY_DUPLICATE_ATTRIBUTE = 0XC0150018;

    /**
     * The identity string is malformed. This might be due to   a trailing comma, more than two unnamed attributes, a missing attribute name,   or a missing attribute value.
     */
    public static final int STATUS_SXS_IDENTITY_PARSE_ERROR = 0XC0150019;

    /**
     * The component store has become corrupted.
     */
    public static final int STATUS_SXS_COMPONENT_STORE_CORRUPT = 0XC015001A;

    /**
     * A component\'s file does not match the verification   information present in the component manifest.
     */
    public static final int STATUS_SXS_FILE_HASH_MISMATCH = 0XC015001B;

    /**
     * The identities of the manifests are identical, but   their contents are different.
     */
    public static final int STATUS_SXS_MANIFEST_IDENTITY_SAME_BUT_CONTENTS_DIFFERENT = 0XC015001C;

    /**
     * The component identities are different.
     */
    public static final int STATUS_SXS_IDENTITIES_DIFFERENT = 0XC015001D;

    /**
     * The assembly is not a deployment.
     */
    public static final int STATUS_SXS_ASSEMBLY_IS_NOT_A_DEPLOYMENT = 0XC015001E;

    /**
     * The file is not a part of the assembly.
     */
    public static final int STATUS_SXS_FILE_NOT_PART_OF_ASSEMBLY = 0XC015001F;

    /**
     * An advanced installer failed during setup or   servicing.
     */
    public static final int STATUS_ADVANCED_INSTALLER_FAILED = 0XC0150020;

    /**
     * The character encoding in the XML declaration did not   match the encoding used in the document.
     */
    public static final int STATUS_XML_ENCODING_MISMATCH = 0XC0150021;

    /**
     * The size of the manifest exceeds the maximum allowed.
     */
    public static final int STATUS_SXS_MANIFEST_TOO_BIG = 0XC0150022;

    /**
     * The setting is not registered.
     */
    public static final int STATUS_SXS_SETTING_NOT_REGISTERED = 0XC0150023;

    /**
     * One or more required transaction members are not   present.
     */
    public static final int STATUS_SXS_TRANSACTION_CLOSURE_INCOMPLETE = 0XC0150024;

    /**
     * The SMI primitive installer failed during setup or   servicing.
     */
    public static final int STATUS_SMI_PRIMITIVE_INSTALLER_FAILED = 0XC0150025;

    /**
     * A generic command executable returned a result that   indicates failure.
     */
    public static final int STATUS_GENERIC_COMMAND_FAILED = 0XC0150026;

    /**
     * A component is missing file verification information   in its manifest.
     */
    public static final int STATUS_SXS_FILE_HASH_MISSING = 0XC0150027;

    /**
     * The function attempted to use a name that is reserved   for use by another transaction.
     */
    public static final int STATUS_TRANSACTIONAL_CONFLICT = 0XC0190001;

    /**
     * The transaction handle associated with this operation   is invalid.
     */
    public static final int STATUS_INVALID_TRANSACTION = 0XC0190002;

    /**
     * The requested operation was made in the context of a   transaction that is no longer active.
     */
    public static final int STATUS_TRANSACTION_NOT_ACTIVE = 0XC0190003;

    /**
     * The transaction manager was unable to be successfully   initialized. Transacted operations are not supported.
     */
    public static final int STATUS_TM_INITIALIZATION_FAILED = 0XC0190004;

    /**
     * Transaction support within the specified file system   resource manager was not started or was shut down due to an error.
     */
    public static final int STATUS_RM_NOT_ACTIVE = 0XC0190005;

    /**
     * The metadata of the resource manager has been   corrupted. The resource manager will not function.
     */
    public static final int STATUS_RM_METADATA_CORRUPT = 0XC0190006;

    /**
     * The resource manager attempted to prepare a   transaction that it has not successfully joined.
     */
    public static final int STATUS_TRANSACTION_NOT_JOINED = 0XC0190007;

    /**
     * The specified directory does not contain a file system   resource manager.
     */
    public static final int STATUS_DIRECTORY_NOT_RM = 0XC0190008;

    /**
     * The remote server or share does not support transacted   file operations.
     */
    public static final int STATUS_TRANSACTIONS_UNSUPPORTED_REMOTE = 0XC019000A;

    /**
     * The requested log size for the file system resource   manager is invalid.
     */
    public static final int STATUS_LOG_RESIZE_INVALID_SIZE = 0XC019000B;

    /**
     * The remote server sent mismatching version number or   Fid for a file opened with transactions.
     */
    public static final int STATUS_REMOTE_FILE_VERSION_MISMATCH = 0XC019000C;

    /**
     * The resource manager tried to register a protocol that   already exists.
     */
    public static final int STATUS_CRM_PROTOCOL_ALREADY_EXISTS = 0XC019000F;

    /**
     * The attempt to propagate the transaction failed.
     */
    public static final int STATUS_TRANSACTION_PROPAGATION_FAILED = 0XC0190010;

    /**
     * The requested propagation protocol was not registered   as a CRM.
     */
    public static final int STATUS_CRM_PROTOCOL_NOT_FOUND = 0XC0190011;

    /**
     * The transaction object already has a superior   enlistment, and the caller attempted an operation that would have created a   new superior. Only a single superior enlistment is allowed.
     */
    public static final int STATUS_TRANSACTION_SUPERIOR_EXISTS = 0XC0190012;

    /**
     * The requested operation is not valid on the   transaction object in its current state.
     */
    public static final int STATUS_TRANSACTION_REQUEST_NOT_VALID = 0XC0190013;

    /**
     * The caller has called a response API, but the response   is not expected because the transaction manager did not issue the   corresponding request to the caller.
     */
    public static final int STATUS_TRANSACTION_NOT_REQUESTED = 0XC0190014;

    /**
     * It is too late to perform the requested operation,   because the transaction has already been aborted.
     */
    public static final int STATUS_TRANSACTION_ALREADY_ABORTED = 0XC0190015;

    /**
     * It is too late to perform the requested operation,   because the transaction has already been committed.
     */
    public static final int STATUS_TRANSACTION_ALREADY_COMMITTED = 0XC0190016;

    /**
     * The buffer passed in to NtPushTransaction or   NtPullTransaction is not in a valid format.
     */
    public static final int STATUS_TRANSACTION_INVALID_MARSHALL_BUFFER = 0XC0190017;

    /**
     * The current transaction context associated with the   thread is not a valid handle to a transaction object.
     */
    public static final int STATUS_CURRENT_TRANSACTION_NOT_VALID = 0XC0190018;

    /**
     * An attempt to create space in the transactional   resource manager\'s log failed. The failure status has been recorded in the   event log.
     */
    public static final int STATUS_LOG_GROWTH_FAILED = 0XC0190019;

    /**
     * The object (file, stream, or link) that corresponds to   the handle has been deleted by a transaction savepoint rollback.
     */
    public static final int STATUS_OBJECT_NO_LONGER_EXISTS = 0XC0190021;

    /**
     * The specified file miniversion was not found for this   transacted file open.
     */
    public static final int STATUS_STREAM_MINIVERSION_NOT_FOUND = 0XC0190022;

    /**
     * The specified file miniversion was found but has been   invalidated. The most likely cause is a transaction savepoint rollback.
     */
    public static final int STATUS_STREAM_MINIVERSION_NOT_VALID = 0XC0190023;

    /**
     * A miniversion can be opened only in the context of the   transaction that created it.
     */
    public static final int STATUS_MINIVERSION_INACCESSIBLE_FROM_SPECIFIED_TRANSACTION = 0XC0190024;

    /**
     * It is not possible to open a miniversion with modify   access.
     */
    public static final int STATUS_CANT_OPEN_MINIVERSION_WITH_MODIFY_INTENT = 0XC0190025;

    /**
     * It is not possible to create any more miniversions for   this stream.
     */
    public static final int STATUS_CANT_CREATE_MORE_STREAM_MINIVERSIONS = 0XC0190026;

    /**
     * The handle has been invalidated by a transaction. The   most likely cause is the presence of memory mapping on a file or an open   handle when the transaction ended or rolled back to savepoint.
     */
    public static final int STATUS_HANDLE_NO_LONGER_VALID = 0XC0190028;

    /**
     * The log data is corrupt.
     */
    public static final int STATUS_LOG_CORRUPTION_DETECTED = 0XC0190030;

    /**
     * The transaction outcome is unavailable because the   resource manager responsible for it is disconnected.
     */
    public static final int STATUS_RM_DISCONNECTED = 0XC0190032;

    /**
     * The request was rejected because the enlistment in   question is not a superior enlistment.
     */
    public static final int STATUS_ENLISTMENT_NOT_SUPERIOR = 0XC0190033;

    /**
     * The file cannot be opened in a transaction because its   identity depends on the outcome of an unresolved transaction.
     */
    public static final int STATUS_FILE_IDENTITY_NOT_PERSISTENT = 0XC0190036;

    /**
     * The operation cannot be performed because another   transaction is depending on this property not changing.
     */
    public static final int STATUS_CANT_BREAK_TRANSACTIONAL_DEPENDENCY = 0XC0190037;

    /**
     * The operation would involve a single file with two   transactional resource managers and is, therefore, not allowed.
     */
    public static final int STATUS_CANT_CROSS_RM_BOUNDARY = 0XC0190038;

    /**
     * The $Txf directory must be empty for this operation to   succeed.
     */
    public static final int STATUS_TXF_DIR_NOT_EMPTY = 0XC0190039;

    /**
     * The operation would leave a transactional resource   manager in an inconsistent state and is therefore not allowed.
     */
    public static final int STATUS_INDOUBT_TRANSACTIONS_EXIST = 0XC019003A;

    /**
     * The operation could not be completed because the   transaction manager does not have a log.
     */
    public static final int STATUS_TM_VOLATILE = 0XC019003B;

    /**
     * A rollback could not be scheduled because a previously   scheduled rollback has already executed or been queued for execution.
     */
    public static final int STATUS_ROLLBACK_TIMER_EXPIRED = 0XC019003C;

    /**
     * The transactional metadata attribute on the file or   directory %hs is corrupt and unreadable.
     */
    public static final int STATUS_TXF_ATTRIBUTE_CORRUPT = 0XC019003D;

    /**
     * The encryption operation could not be completed   because a transaction is active.
     */
    public static final int STATUS_EFS_NOT_ALLOWED_IN_TRANSACTION = 0XC019003E;

    /**
     * This object is not allowed to be opened in a   transaction.
     */
    public static final int STATUS_TRANSACTIONAL_OPEN_NOT_ALLOWED = 0XC019003F;

    /**
     * Memory mapping (creating a mapped section) a remote   file under a transaction is not supported.
     */
    public static final int STATUS_TRANSACTED_MAPPING_UNSUPPORTED_REMOTE = 0XC0190040;

    /**
     * Promotion was required to allow the resource manager   to enlist, but the transaction was set to disallow it.
     */
    public static final int STATUS_TRANSACTION_REQUIRED_PROMOTION = 0XC0190043;

    /**
     * This file is open for modification in an unresolved   transaction and can be opened for execute only by a transacted reader.
     */
    public static final int STATUS_CANNOT_EXECUTE_FILE_IN_TRANSACTION = 0XC0190044;

    /**
     * The request to thaw frozen transactions was ignored   because transactions were not previously frozen.
     */
    public static final int STATUS_TRANSACTIONS_NOT_FROZEN = 0XC0190045;

    /**
     * Transactions cannot be frozen because a freeze is   already in progress.
     */
    public static final int STATUS_TRANSACTION_FREEZE_IN_PROGRESS = 0XC0190046;

    /**
     * The target volume is not a snapshot volume. This   operation is valid only on a volume mounted as a snapshot.
     */
    public static final int STATUS_NOT_SNAPSHOT_VOLUME = 0XC0190047;

    /**
     * The savepoint operation failed because files are open   on the transaction, which is not permitted.
     */
    public static final int STATUS_NO_SAVEPOINT_WITH_OPEN_FILES = 0XC0190048;

    /**
     * The sparse operation could not be completed because a   transaction is active on the file.
     */
    public static final int STATUS_SPARSE_NOT_ALLOWED_IN_TRANSACTION = 0XC0190049;

    /**
     * The call to create a transaction manager object failed   because the Tm Identity that is stored in the log file does not match the Tm   Identity that was passed in as an argument.
     */
    public static final int STATUS_TM_IDENTITY_MISMATCH = 0XC019004A;

    /**
     * I/O was attempted on a section object that has been   floated as a result of a transaction ending. There is no valid data.
     */
    public static final int STATUS_FLOATED_SECTION = 0XC019004B;

    /**
     * The transactional resource manager cannot currently   accept transacted work due to a transient condition, such as low resources.
     */
    public static final int STATUS_CANNOT_ACCEPT_TRANSACTED_WORK = 0XC019004C;

    /**
     * The transactional resource manager had too many   transactions outstanding that could not be aborted. The transactional   resource manager has been shut down.
     */
    public static final int STATUS_CANNOT_ABORT_TRANSACTIONS = 0XC019004D;

    /**
     * The specified transaction was unable to be opened   because it was not found.
     */
    public static final int STATUS_TRANSACTION_NOT_FOUND = 0XC019004E;

    /**
     * The specified resource manager was unable to be opened   because it was not found.
     */
    public static final int STATUS_RESOURCEMANAGER_NOT_FOUND = 0XC019004F;

    /**
     * The specified enlistment was unable to be opened   because it was not found.
     */
    public static final int STATUS_ENLISTMENT_NOT_FOUND = 0XC0190050;

    /**
     * The specified transaction manager was unable to be   opened because it was not found.
     */
    public static final int STATUS_TRANSACTIONMANAGER_NOT_FOUND = 0XC0190051;

    /**
     * The specified resource manager was unable to create an   enlistment because its associated transaction manager is not online.
     */
    public static final int STATUS_TRANSACTIONMANAGER_NOT_ONLINE = 0XC0190052;

    /**
     * The specified transaction manager was unable to create   the objects contained in its log file in the Ob namespace. Therefore, the   transaction manager was unable to recover.
     */
    public static final int STATUS_TRANSACTIONMANAGER_RECOVERY_NAME_COLLISION = 0XC0190053;

    /**
     * The call to create a superior enlistment on this   transaction object could not be completed because the transaction object   specified for the enlistment is a subordinate branch of the transaction. Only   the
     * root of the transaction can be enlisted as a superior.
     */
    public static final int STATUS_TRANSACTION_NOT_ROOT = 0XC0190054;

    /**
     * Because the associated transaction manager or resource   manager has been closed, the handle is no longer valid.
     */
    public static final int STATUS_TRANSACTION_OBJECT_EXPIRED = 0XC0190055;

    /**
     * The compression operation could not be completed   because a transaction is active on the file.
     */
    public static final int STATUS_COMPRESSION_NOT_ALLOWED_IN_TRANSACTION = 0XC0190056;

    /**
     * The specified operation could not be performed on this   superior enlistment because the enlistment was not created with the   corresponding completion response in the NotificationMask.
     */
    public static final int STATUS_TRANSACTION_RESPONSE_NOT_ENLISTED = 0XC0190057;

    /**
     * The specified operation could not be performed because   the record to be logged was too long. This can occur because either there are   too many enlistments on this transaction or the combined RecoveryInformation
     * being logged on behalf of those enlistments is too long.
     */
    public static final int STATUS_TRANSACTION_RECORD_TOO_LONG = 0XC0190058;

    /**
     * The link-tracking operation could not be completed   because a transaction is active.
     */
    public static final int STATUS_NO_LINK_TRACKING_IN_TRANSACTION = 0XC0190059;

    /**
     * This operation cannot be performed in a transaction.
     */
    public static final int STATUS_OPERATION_NOT_SUPPORTED_IN_TRANSACTION = 0XC019005A;

    /**
     * The kernel transaction manager had to abort or forget   the transaction because it blocked forward progress.
     */
    public static final int STATUS_TRANSACTION_INTEGRITY_VIOLATED = 0XC019005B;

    /**
     * The handle is no longer properly associated with its   transaction.  It might have been opened in a transactional resource manager   that was subsequently forced to restart.  Please close the handle and open a
     * new one.
     */
    public static final int STATUS_EXPIRED_HANDLE = 0XC0190060;

    /**
     * The specified operation could not be performed because   the resource manager is not enlisted in the transaction.
     */
    public static final int STATUS_TRANSACTION_NOT_ENLISTED = 0XC0190061;

    /**
     * The log service found an invalid log sector.
     */
    public static final int STATUS_LOG_SECTOR_INVALID = 0XC01A0001;

    /**
     * The log service encountered a log sector with invalid   block parity.
     */
    public static final int STATUS_LOG_SECTOR_PARITY_INVALID = 0XC01A0002;

    /**
     * The log service encountered a remapped log sector.
     */
    public static final int STATUS_LOG_SECTOR_REMAPPED = 0XC01A0003;

    /**
     * The log service encountered a partial or incomplete   log block.
     */
    public static final int STATUS_LOG_BLOCK_INCOMPLETE = 0XC01A0004;

    /**
     * The log service encountered an attempt to access data   outside the active log range.
     */
    public static final int STATUS_LOG_INVALID_RANGE = 0XC01A0005;

    /**
     * The log service user-log marshaling buffers are   exhausted.
     */
    public static final int STATUS_LOG_BLOCKS_EXHAUSTED = 0XC01A0006;

    /**
     * The log service encountered an attempt to read from a   marshaling area with an invalid read context.
     */
    public static final int STATUS_LOG_READ_CONTEXT_INVALID = 0XC01A0007;

    /**
     * The log service encountered an invalid log restart   area.
     */
    public static final int STATUS_LOG_RESTART_INVALID = 0XC01A0008;

    /**
     * The log service encountered an invalid log block   version.
     */
    public static final int STATUS_LOG_BLOCK_VERSION = 0XC01A0009;

    /**
     * The log service encountered an invalid log block.
     */
    public static final int STATUS_LOG_BLOCK_INVALID = 0XC01A000A;

    /**
     * The log service encountered an attempt to read the log   with an invalid read mode.
     */
    public static final int STATUS_LOG_READ_MODE_INVALID = 0XC01A000B;

    /**
     * The log service encountered a corrupted metadata file.
     */
    public static final int STATUS_LOG_METADATA_CORRUPT = 0XC01A000D;

    /**
     * The log service encountered a metadata file that could   not be created by the log file system.
     */
    public static final int STATUS_LOG_METADATA_INVALID = 0XC01A000E;

    /**
     * The log service encountered a metadata file with   inconsistent data.
     */
    public static final int STATUS_LOG_METADATA_INCONSISTENT = 0XC01A000F;

    /**
     * The log service encountered an attempt to erroneously   allocate or dispose reservation space.
     */
    public static final int STATUS_LOG_RESERVATION_INVALID = 0XC01A0010;

    /**
     * The log service cannot delete the log file or the file   system container.
     */
    public static final int STATUS_LOG_CANT_DELETE = 0XC01A0011;

    /**
     * The log service has reached the maximum allowable   containers allocated to a log file.
     */
    public static final int STATUS_LOG_CONTAINER_LIMIT_EXCEEDED = 0XC01A0012;

    /**
     * The log service has attempted to read or write   backward past the start of the log.
     */
    public static final int STATUS_LOG_START_OF_LOG = 0XC01A0013;

    /**
     * The log policy could not be installed because a policy   of the same type is already present.
     */
    public static final int STATUS_LOG_POLICY_ALREADY_INSTALLED = 0XC01A0014;

    /**
     * The log policy in question was not installed at the   time of the request.
     */
    public static final int STATUS_LOG_POLICY_NOT_INSTALLED = 0XC01A0015;

    /**
     * The installed set of policies on the log is invalid.
     */
    public static final int STATUS_LOG_POLICY_INVALID = 0XC01A0016;

    /**
     * A policy on the log in question prevented the   operation from completing.
     */
    public static final int STATUS_LOG_POLICY_CONFLICT = 0XC01A0017;

    /**
     * The log space cannot be reclaimed because the log is   pinned by the archive tail.
     */
    public static final int STATUS_LOG_PINNED_ARCHIVE_TAIL = 0XC01A0018;

    /**
     * The log record is not a record in the log file.
     */
    public static final int STATUS_LOG_RECORD_NONEXISTENT = 0XC01A0019;

    /**
     * The number of reserved log records or the adjustment   of the number of reserved log records is invalid.
     */
    public static final int STATUS_LOG_RECORDS_RESERVED_INVALID = 0XC01A001A;

    /**
     * The reserved log space or the adjustment of the log   space is invalid.
     */
    public static final int STATUS_LOG_SPACE_RESERVED_INVALID = 0XC01A001B;

    /**
     * A new or existing archive tail or the base of the   active log is invalid.
     */
    public static final int STATUS_LOG_TAIL_INVALID = 0XC01A001C;

    /**
     * The log space is exhausted.
     */
    public static final int STATUS_LOG_FULL = 0XC01A001D;

    /**
     * The log is multiplexed; no direct writes to the   physical log are allowed.
     */
    public static final int STATUS_LOG_MULTIPLEXED = 0XC01A001E;

    /**
     * The operation failed because the log is dedicated.
     */
    public static final int STATUS_LOG_DEDICATED = 0XC01A001F;

    /**
     * The operation requires an archive context.
     */
    public static final int STATUS_LOG_ARCHIVE_NOT_IN_PROGRESS = 0XC01A0020;

    /**
     * Log archival is in progress.
     */
    public static final int STATUS_LOG_ARCHIVE_IN_PROGRESS = 0XC01A0021;

    /**
     * The operation requires a nonephemeral log, but the log   is ephemeral.
     */
    public static final int STATUS_LOG_EPHEMERAL = 0XC01A0022;

    /**
     * The log must have at least two containers before it   can be read from or written to.
     */
    public static final int STATUS_LOG_NOT_ENOUGH_CONTAINERS = 0XC01A0023;

    /**
     * A log client has already registered on the stream.
     */
    public static final int STATUS_LOG_CLIENT_ALREADY_REGISTERED = 0XC01A0024;

    /**
     * A log client has not been registered on the stream.
     */
    public static final int STATUS_LOG_CLIENT_NOT_REGISTERED = 0XC01A0025;

    /**
     * A request has already been made to handle the log full   condition.
     */
    public static final int STATUS_LOG_FULL_HANDLER_IN_PROGRESS = 0XC01A0026;

    /**
     * The log service encountered an error when attempting   to read from a log container.
     */
    public static final int STATUS_LOG_CONTAINER_READ_FAILED = 0XC01A0027;

    /**
     * The log service encountered an error when attempting   to write to a log container.
     */
    public static final int STATUS_LOG_CONTAINER_WRITE_FAILED = 0XC01A0028;

    /**
     * The log service encountered an error when attempting   to open a log container.
     */
    public static final int STATUS_LOG_CONTAINER_OPEN_FAILED = 0XC01A0029;

    /**
     * The log service encountered an invalid container state   when attempting a requested action.
     */
    public static final int STATUS_LOG_CONTAINER_STATE_INVALID = 0XC01A002A;

    /**
     * The log service is not in the correct state to perform   a requested action.
     */
    public static final int STATUS_LOG_STATE_INVALID = 0XC01A002B;

    /**
     * The log space cannot be reclaimed because the log is   pinned.
     */
    public static final int STATUS_LOG_PINNED = 0XC01A002C;

    /**
     * The log metadata flush failed.
     */
    public static final int STATUS_LOG_METADATA_FLUSH_FAILED = 0XC01A002D;

    /**
     * Security on the log and its containers is   inconsistent.
     */
    public static final int STATUS_LOG_INCONSISTENT_SECURITY = 0XC01A002E;

    /**
     * Records were appended to the log or reservation   changes were made, but the log could not be flushed.
     */
    public static final int STATUS_LOG_APPENDED_FLUSH_FAILED = 0XC01A002F;

    /**
     * The log is pinned due to reservation consuming most of   the log space. Free some reserved records to make space available.
     */
    public static final int STATUS_LOG_PINNED_RESERVATION = 0XC01A0030;

    /**
     * {Display Driver Stopped Responding} The %hs display   driver has stopped working normally. Save your work and reboot the system to   restore full display functionality. The next time you reboot the computer, a
     * dialog box will allow you to upload data about this failure to Microsoft.
     */
    public static final int STATUS_VIDEO_HUNG_DISPLAY_DRIVER_THREAD = 0XC01B00EA;

    /**
     * A handler was not defined by the filter for this   operation.
     */
    public static final int STATUS_FLT_NO_HANDLER_DEFINED = 0XC01C0001;

    /**
     * A context is already defined for this object.
     */
    public static final int STATUS_FLT_CONTEXT_ALREADY_DEFINED = 0XC01C0002;

    /**
     * Asynchronous requests are not valid for this   operation.
     */
    public static final int STATUS_FLT_INVALID_ASYNCHRONOUS_REQUEST = 0XC01C0003;

    /**
     * This is an internal error code used by the filter   manager to determine if a fast I/O operation should be forced down the   input/output request packet (IRP) path. Minifilters should never return this   value.
     */
    public static final int STATUS_FLT_DISALLOW_FAST_IO = 0XC01C0004;

    /**
     * An invalid name request was made. The name requested   cannot be retrieved at this time.
     */
    public static final int STATUS_FLT_INVALID_NAME_REQUEST = 0XC01C0005;

    /**
     * Posting this operation to a worker thread for further   processing is not safe at this time because it could lead to a system   deadlock.
     */
    public static final int STATUS_FLT_NOT_SAFE_TO_POST_OPERATION = 0XC01C0006;

    /**
     * The Filter Manager was not initialized when a filter   tried to register. Make sure that the Filter Manager is loaded as a driver.
     */
    public static final int STATUS_FLT_NOT_INITIALIZED = 0XC01C0007;

    /**
     * The filter is not ready for attachment to volumes   because it has not finished initializing (FltStartFiltering has not been   called).
     */
    public static final int STATUS_FLT_FILTER_NOT_READY = 0XC01C0008;

    /**
     * The filter must clean up any operation-specific   context at this time because it is being removed from the system before the   operation is completed by the lower drivers.
     */
    public static final int STATUS_FLT_POST_OPERATION_CLEANUP = 0XC01C0009;

    /**
     * The Filter Manager had an internal error from which it   cannot recover; therefore, the operation has failed. This is usually the   result of a filter returning an invalid value from a pre-operation callback.
     */
    public static final int STATUS_FLT_INTERNAL_ERROR = 0XC01C000A;

    /**
     * The object specified for this action is in the process   of being deleted; therefore, the action requested cannot be completed at this   time.
     */
    public static final int STATUS_FLT_DELETING_OBJECT = 0XC01C000B;

    /**
     * A nonpaged pool must be used for this type of context.
     */
    public static final int STATUS_FLT_MUST_BE_NONPAGED_POOL = 0XC01C000C;

    /**
     * A duplicate handler definition has been provided for   an operation.
     */
    public static final int STATUS_FLT_DUPLICATE_ENTRY = 0XC01C000D;

    /**
     * The callback data queue has been disabled.
     */
    public static final int STATUS_FLT_CBDQ_DISABLED = 0XC01C000E;

    /**
     * Do not attach the filter to the volume at this time.
     */
    public static final int STATUS_FLT_DO_NOT_ATTACH = 0XC01C000F;

    /**
     * Do not detach the filter from the volume at this time.
     */
    public static final int STATUS_FLT_DO_NOT_DETACH = 0XC01C0010;

    /**
     * An instance already exists at this altitude on the   volume specified.
     */
    public static final int STATUS_FLT_INSTANCE_ALTITUDE_COLLISION = 0XC01C0011;

    /**
     * An instance already exists with this name on the   volume specified.
     */
    public static final int STATUS_FLT_INSTANCE_NAME_COLLISION = 0XC01C0012;

    /**
     * The system could not find the filter specified.
     */
    public static final int STATUS_FLT_FILTER_NOT_FOUND = 0XC01C0013;

    /**
     * The system could not find the volume specified.
     */
    public static final int STATUS_FLT_VOLUME_NOT_FOUND = 0XC01C0014;

    /**
     * The system could not find the instance specified.
     */
    public static final int STATUS_FLT_INSTANCE_NOT_FOUND = 0XC01C0015;

    /**
     * No registered context allocation definition was found   for the given request.
     */
    public static final int STATUS_FLT_CONTEXT_ALLOCATION_NOT_FOUND = 0XC01C0016;

    /**
     * An invalid parameter was specified during context   registration.
     */
    public static final int STATUS_FLT_INVALID_CONTEXT_REGISTRATION = 0XC01C0017;

    /**
     * The name requested was not found in the Filter Manager   name cache and could not be retrieved from the file system.
     */
    public static final int STATUS_FLT_NAME_CACHE_MISS = 0XC01C0018;

    /**
     * The requested device object does not exist for the   given volume.
     */
    public static final int STATUS_FLT_NO_DEVICE_OBJECT = 0XC01C0019;

    /**
     * The specified volume is already mounted.
     */
    public static final int STATUS_FLT_VOLUME_ALREADY_MOUNTED = 0XC01C001A;

    /**
     * The specified transaction context is already enlisted   in a transaction.
     */
    public static final int STATUS_FLT_ALREADY_ENLISTED = 0XC01C001B;

    /**
     * The specified context is already attached to another   object.
     */
    public static final int STATUS_FLT_CONTEXT_ALREADY_LINKED = 0XC01C001C;

    /**
     * No waiter is present for the filter\'s reply to this   message.
     */
    public static final int STATUS_FLT_NO_WAITER_FOR_REPLY = 0XC01C0020;

    /**
     * A monitor descriptor could not be obtained.
     */
    public static final int STATUS_MONITOR_NO_DESCRIPTOR = 0XC01D0001;

    /**
     * This release does not support the format of the   obtained monitor descriptor.
     */
    public static final int STATUS_MONITOR_UNKNOWN_DESCRIPTOR_FORMAT = 0XC01D0002;

    /**
     * The checksum of the obtained monitor descriptor is   invalid.
     */
    public static final int STATUS_MONITOR_INVALID_DESCRIPTOR_CHECKSUM = 0XC01D0003;

    /**
     * The monitor descriptor contains an invalid standard   timing block.
     */
    public static final int STATUS_MONITOR_INVALID_STANDARD_TIMING_BLOCK = 0XC01D0004;

    /**
     * WMI data-block registration failed for one of the   MSMonitorClass WMI subclasses.
     */
    public static final int STATUS_MONITOR_WMI_DATABLOCK_REGISTRATION_FAILED = 0XC01D0005;

    /**
     * The provided monitor descriptor block is either   corrupted or does not contain the monitor\'s detailed serial number.
     */
    public static final int STATUS_MONITOR_INVALID_SERIAL_NUMBER_MONDSC_BLOCK = 0XC01D0006;

    /**
     * The provided monitor descriptor block is either   corrupted or does not contain the monitor\'s user-friendly name.
     */
    public static final int STATUS_MONITOR_INVALID_USER_FRIENDLY_MONDSC_BLOCK = 0XC01D0007;

    /**
     * There is no monitor descriptor data at the specified   (offset or size) region.
     */
    public static final int STATUS_MONITOR_NO_MORE_DESCRIPTOR_DATA = 0XC01D0008;

    /**
     * The monitor descriptor contains an invalid detailed timing   block.
     */
    public static final int STATUS_MONITOR_INVALID_DETAILED_TIMING_BLOCK = 0XC01D0009;

    /**
     * Monitor descriptor contains invalid manufacture date.
     */
    public static final int STATUS_MONITOR_INVALID_MANUFACTURE_DATE = 0XC01D000A;

    /**
     * Exclusive mode ownership is needed to create an   unmanaged primary allocation.
     */
    public static final int STATUS_GRAPHICS_NOT_EXCLUSIVE_MODE_OWNER = 0XC01E0000;

    /**
     * The driver needs more DMA buffer space to complete the   requested operation.
     */
    public static final int STATUS_GRAPHICS_INSUFFICIENT_DMA_BUFFER = 0XC01E0001;

    /**
     * The specified display adapter handle is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_DISPLAY_ADAPTER = 0XC01E0002;

    /**
     * The specified display adapter and all of its state   have been reset.
     */
    public static final int STATUS_GRAPHICS_ADAPTER_WAS_RESET = 0XC01E0003;

    /**
     * The driver stack does not match the expected driver   model.
     */
    public static final int STATUS_GRAPHICS_INVALID_DRIVER_MODEL = 0XC01E0004;

    /**
     * Present happened but ended up into the changed desktop   mode.
     */
    public static final int STATUS_GRAPHICS_PRESENT_MODE_CHANGED = 0XC01E0005;

    /**
     * Nothing to present due to desktop occlusion.
     */
    public static final int STATUS_GRAPHICS_PRESENT_OCCLUDED = 0XC01E0006;

    /**
     * Not able to present due to denial of desktop access.
     */
    public static final int STATUS_GRAPHICS_PRESENT_DENIED = 0XC01E0007;

    /**
     * Not able to present with color conversion.
     */
    public static final int STATUS_GRAPHICS_CANNOTCOLORCONVERT = 0XC01E0008;

    /**
     * Present redirection is disabled (desktop windowing   management subsystem is off).
     */
    public static final int STATUS_GRAPHICS_PRESENT_REDIRECTION_DISABLED = 0XC01E000B;

    /**
     * Previous exclusive VidPn source owner has released its   ownership
     */
    public static final int STATUS_GRAPHICS_PRESENT_UNOCCLUDED = 0XC01E000C;

    /**
     * Not enough video memory is available to complete the   operation.
     */
    public static final int STATUS_GRAPHICS_NO_VIDEO_MEMORY = 0XC01E0100;

    /**
     * Could not probe and lock the underlying memory of an   allocation.
     */
    public static final int STATUS_GRAPHICS_CANT_LOCK_MEMORY = 0XC01E0101;

    /**
     * The allocation is currently busy.
     */
    public static final int STATUS_GRAPHICS_ALLOCATION_BUSY = 0XC01E0102;

    /**
     * An object being referenced has already reached the   maximum reference count and cannot be referenced further.
     */
    public static final int STATUS_GRAPHICS_TOO_MANY_REFERENCES = 0XC01E0103;

    /**
     * A problem could not be solved due to an existing   condition. Try again later.
     */
    public static final int STATUS_GRAPHICS_TRY_AGAIN_LATER = 0XC01E0104;

    /**
     * A problem could not be solved due to an existing   condition. Try again now.
     */
    public static final int STATUS_GRAPHICS_TRY_AGAIN_NOW = 0XC01E0105;

    /**
     * The allocation is invalid.
     */
    public static final int STATUS_GRAPHICS_ALLOCATION_INVALID = 0XC01E0106;

    /**
     * No more unswizzling apertures are currently available.
     */
    public static final int STATUS_GRAPHICS_UNSWIZZLING_APERTURE_UNAVAILABLE = 0XC01E0107;

    /**
     * The current allocation cannot be unswizzled by an   aperture.
     */
    public static final int STATUS_GRAPHICS_UNSWIZZLING_APERTURE_UNSUPPORTED = 0XC01E0108;

    /**
     * The request failed because a pinned allocation cannot   be evicted.
     */
    public static final int STATUS_GRAPHICS_CANT_EVICT_PINNED_ALLOCATION = 0XC01E0109;

    /**
     * The allocation cannot be used from its current segment   location for the specified operation.
     */
    public static final int STATUS_GRAPHICS_INVALID_ALLOCATION_USAGE = 0XC01E0110;

    /**
     * A locked allocation cannot be used in the current   command buffer.
     */
    public static final int STATUS_GRAPHICS_CANT_RENDER_LOCKED_ALLOCATION = 0XC01E0111;

    /**
     * The allocation being referenced has been closed   permanently.
     */
    public static final int STATUS_GRAPHICS_ALLOCATION_CLOSED = 0XC01E0112;

    /**
     * An invalid allocation instance is being referenced.
     */
    public static final int STATUS_GRAPHICS_INVALID_ALLOCATION_INSTANCE = 0XC01E0113;

    /**
     * An invalid allocation handle is being referenced.
     */
    public static final int STATUS_GRAPHICS_INVALID_ALLOCATION_HANDLE = 0XC01E0114;

    /**
     * The allocation being referenced does not belong to the   current device.
     */
    public static final int STATUS_GRAPHICS_WRONG_ALLOCATION_DEVICE = 0XC01E0115;

    /**
     * The specified allocation lost its content.
     */
    public static final int STATUS_GRAPHICS_ALLOCATION_CONTENT_LOST = 0XC01E0116;

    /**
     * A GPU exception was detected on the given device. The   device cannot be scheduled.
     */
    public static final int STATUS_GRAPHICS_GPU_EXCEPTION_ON_DEVICE = 0XC01E0200;

    /**
     * The specified VidPN topology is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN_TOPOLOGY = 0XC01E0300;

    /**
     * The specified VidPN topology is valid but is not   supported by this model of the display adapter.
     */
    public static final int STATUS_GRAPHICS_VIDPN_TOPOLOGY_NOT_SUPPORTED = 0XC01E0301;

    /**
     * The specified VidPN topology is valid but is not   currently supported by the display adapter due to allocation of its   resources.
     */
    public static final int STATUS_GRAPHICS_VIDPN_TOPOLOGY_CURRENTLY_NOT_SUPPORTED = 0XC01E0302;

    /**
     * The specified VidPN handle is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN = 0XC01E0303;

    /**
     * The specified video present source is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDEO_PRESENT_SOURCE = 0XC01E0304;

    /**
     * The specified video present target is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDEO_PRESENT_TARGET = 0XC01E0305;

    /**
     * The specified VidPN modality is not supported (for   example, at least two of the pinned modes are not co-functional).
     */
    public static final int STATUS_GRAPHICS_VIDPN_MODALITY_NOT_SUPPORTED = 0XC01E0306;

    /**
     * The specified VidPN source mode set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN_SOURCEMODESET = 0XC01E0308;

    /**
     * The specified VidPN target mode set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN_TARGETMODESET = 0XC01E0309;

    /**
     * The specified video signal frequency is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_FREQUENCY = 0XC01E030A;

    /**
     * The specified video signal active region is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_ACTIVE_REGION = 0XC01E030B;

    /**
     * The specified video signal total region is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_TOTAL_REGION = 0XC01E030C;

    /**
     * The specified video present source mode is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDEO_PRESENT_SOURCE_MODE = 0XC01E0310;

    /**
     * The specified video present target mode is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDEO_PRESENT_TARGET_MODE = 0XC01E0311;

    /**
     * The pinned mode must remain in the set on the VidPN\'s   co-functional modality enumeration.
     */
    public static final int STATUS_GRAPHICS_PINNED_MODE_MUST_REMAIN_IN_SET = 0XC01E0312;

    /**
     * The specified video present path is already in the   VidPN\'s topology.
     */
    public static final int STATUS_GRAPHICS_PATH_ALREADY_IN_TOPOLOGY = 0XC01E0313;

    /**
     * The specified mode is already in the mode set.
     */
    public static final int STATUS_GRAPHICS_MODE_ALREADY_IN_MODESET = 0XC01E0314;

    /**
     * The specified video present source set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDEOPRESENTSOURCESET = 0XC01E0315;

    /**
     * The specified video present target set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDEOPRESENTTARGETSET = 0XC01E0316;

    /**
     * The specified video present source is already in the   video present source set.
     */
    public static final int STATUS_GRAPHICS_SOURCE_ALREADY_IN_SET = 0XC01E0317;

    /**
     * The specified video present target is already in the   video present target set.
     */
    public static final int STATUS_GRAPHICS_TARGET_ALREADY_IN_SET = 0XC01E0318;

    /**
     * The specified VidPN present path is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN_PRESENT_PATH = 0XC01E0319;

    /**
     * The miniport has no recommendation for augmenting the   specified VidPN\'s topology.
     */
    public static final int STATUS_GRAPHICS_NO_RECOMMENDED_VIDPN_TOPOLOGY = 0XC01E031A;

    /**
     * The specified monitor frequency range set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITOR_FREQUENCYRANGESET = 0XC01E031B;

    /**
     * The specified monitor frequency range is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITOR_FREQUENCYRANGE = 0XC01E031C;

    /**
     * The specified frequency range is not in the specified   monitor frequency range set.
     */
    public static final int STATUS_GRAPHICS_FREQUENCYRANGE_NOT_IN_SET = 0XC01E031D;

    /**
     * The specified frequency range is already in the   specified monitor frequency range set.
     */
    public static final int STATUS_GRAPHICS_FREQUENCYRANGE_ALREADY_IN_SET = 0XC01E031F;

    /**
     * The specified mode set is stale. Reacquire the new   mode set.
     */
    public static final int STATUS_GRAPHICS_STALE_MODESET = 0XC01E0320;

    /**
     * The specified monitor source mode set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITOR_SOURCEMODESET = 0XC01E0321;

    /**
     * The specified monitor source mode is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITOR_SOURCE_MODE = 0XC01E0322;

    /**
     * The miniport does not have a recommendation regarding   the request to provide a functional VidPN given the current display adapter   configuration.
     */
    public static final int STATUS_GRAPHICS_NO_RECOMMENDED_FUNCTIONAL_VIDPN = 0XC01E0323;

    /**
     * The ID of the specified mode is being used by another   mode in the set.
     */
    public static final int STATUS_GRAPHICS_MODE_ID_MUST_BE_UNIQUE = 0XC01E0324;

    /**
     * The system failed to determine a mode that is   supported by both the display adapter and the monitor connected to it.
     */
    public static final int STATUS_GRAPHICS_EMPTY_ADAPTER_MONITOR_MODE_SUPPORT_INTERSECTION = 0XC01E0325;

    /**
     * The number of video present targets must be greater   than or equal to the number of video present sources.
     */
    public static final int STATUS_GRAPHICS_VIDEO_PRESENT_TARGETS_LESS_THAN_SOURCES = 0XC01E0326;

    /**
     * The specified present path is not in the VidPN\'s   topology.
     */
    public static final int STATUS_GRAPHICS_PATH_NOT_IN_TOPOLOGY = 0XC01E0327;

    /**
     * The display adapter must have at least one video   present source.
     */
    public static final int STATUS_GRAPHICS_ADAPTER_MUST_HAVE_AT_LEAST_ONE_SOURCE = 0XC01E0328;

    /**
     * The display adapter must have at least one video   present target.
     */
    public static final int STATUS_GRAPHICS_ADAPTER_MUST_HAVE_AT_LEAST_ONE_TARGET = 0XC01E0329;

    /**
     * The specified monitor descriptor set is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITORDESCRIPTORSET = 0XC01E032A;

    /**
     * The specified monitor descriptor is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITORDESCRIPTOR = 0XC01E032B;

    /**
     * The specified descriptor is not in the specified   monitor descriptor set.
     */
    public static final int STATUS_GRAPHICS_MONITORDESCRIPTOR_NOT_IN_SET = 0XC01E032C;

    /**
     * The specified descriptor is already in the specified   monitor descriptor set.
     */
    public static final int STATUS_GRAPHICS_MONITORDESCRIPTOR_ALREADY_IN_SET = 0XC01E032D;

    /**
     * The ID of the specified monitor descriptor is being   used by another descriptor in the set.
     */
    public static final int STATUS_GRAPHICS_MONITORDESCRIPTOR_ID_MUST_BE_UNIQUE = 0XC01E032E;

    /**
     * The specified video present target subset type is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN_TARGET_SUBSET_TYPE = 0XC01E032F;

    /**
     * Two or more of the specified resources are not related   to each other, as defined by the interface semantics.
     */
    public static final int STATUS_GRAPHICS_RESOURCES_NOT_RELATED = 0XC01E0330;

    /**
     * The ID of the specified video present source is being   used by another source in the set.
     */
    public static final int STATUS_GRAPHICS_SOURCE_ID_MUST_BE_UNIQUE = 0XC01E0331;

    /**
     * The ID of the specified video present target is being   used by another target in the set.
     */
    public static final int STATUS_GRAPHICS_TARGET_ID_MUST_BE_UNIQUE = 0XC01E0332;

    /**
     * The specified VidPN source cannot be used because   there is no available VidPN target to connect it to.
     */
    public static final int STATUS_GRAPHICS_NO_AVAILABLE_VIDPN_TARGET = 0XC01E0333;

    /**
     * The newly arrived monitor could not be associated with   a display adapter.
     */
    public static final int STATUS_GRAPHICS_MONITOR_COULD_NOT_BE_ASSOCIATED_WITH_ADAPTER = 0XC01E0334;

    /**
     * The particular display adapter does not have an   associated VidPN manager.
     */
    public static final int STATUS_GRAPHICS_NO_VIDPNMGR = 0XC01E0335;

    /**
     * The VidPN manager of the particular display adapter   does not have an active VidPN.
     */
    public static final int STATUS_GRAPHICS_NO_ACTIVE_VIDPN = 0XC01E0336;

    /**
     * The specified VidPN topology is stale; obtain the new   topology.
     */
    public static final int STATUS_GRAPHICS_STALE_VIDPN_TOPOLOGY = 0XC01E0337;

    /**
     * No monitor is connected on the specified video present   target.
     */
    public static final int STATUS_GRAPHICS_MONITOR_NOT_CONNECTED = 0XC01E0338;

    /**
     * The specified source is not part of the specified   VidPN\'s topology.
     */
    public static final int STATUS_GRAPHICS_SOURCE_NOT_IN_TOPOLOGY = 0XC01E0339;

    /**
     * The specified primary surface size is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_PRIMARYSURFACE_SIZE = 0XC01E033A;

    /**
     * The specified visible region size is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VISIBLEREGION_SIZE = 0XC01E033B;

    /**
     * The specified stride is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_STRIDE = 0XC01E033C;

    /**
     * The specified pixel format is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_PIXELFORMAT = 0XC01E033D;

    /**
     * The specified color basis is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_COLORBASIS = 0XC01E033E;

    /**
     * The specified pixel value access mode is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_PIXELVALUEACCESSMODE = 0XC01E033F;

    /**
     * The specified target is not part of the specified   VidPN\'s topology.
     */
    public static final int STATUS_GRAPHICS_TARGET_NOT_IN_TOPOLOGY = 0XC01E0340;

    /**
     * Failed to acquire the display mode management   interface.
     */
    public static final int STATUS_GRAPHICS_NO_DISPLAY_MODE_MANAGEMENT_SUPPORT = 0XC01E0341;

    /**
     * The specified VidPN source is already owned by a DMM   client and cannot be used until that client releases it.
     */
    public static final int STATUS_GRAPHICS_VIDPN_SOURCE_IN_USE = 0XC01E0342;

    /**
     * The specified VidPN is active and cannot be accessed.
     */
    public static final int STATUS_GRAPHICS_CANT_ACCESS_ACTIVE_VIDPN = 0XC01E0343;

    /**
     * The specified VidPN\'s present path importance ordinal   is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_PATH_IMPORTANCE_ORDINAL = 0XC01E0344;

    /**
     * The specified VidPN\'s present path content geometry   transformation is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_PATH_CONTENT_GEOMETRY_TRANSFORMATION = 0XC01E0345;

    /**
     * The specified content geometry transformation is not   supported on the respective VidPN present path.
     */
    public static final int STATUS_GRAPHICS_PATH_CONTENT_GEOMETRY_TRANSFORMATION_NOT_SUPPORTED = 0XC01E0346;

    /**
     * The specified gamma ramp is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_GAMMA_RAMP = 0XC01E0347;

    /**
     * The specified gamma ramp is not supported on the respective   VidPN present path.
     */
    public static final int STATUS_GRAPHICS_GAMMA_RAMP_NOT_SUPPORTED = 0XC01E0348;

    /**
     * Multisampling is not supported on the respective VidPN   present path.
     */
    public static final int STATUS_GRAPHICS_MULTISAMPLING_NOT_SUPPORTED = 0XC01E0349;

    /**
     * The specified mode is not in the specified mode set.
     */
    public static final int STATUS_GRAPHICS_MODE_NOT_IN_MODESET = 0XC01E034A;

    /**
     * The specified VidPN topology recommendation reason is   invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_VIDPN_TOPOLOGY_RECOMMENDATION_REASON = 0XC01E034D;

    /**
     * The specified VidPN present path content type is   invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_PATH_CONTENT_TYPE = 0XC01E034E;

    /**
     * The specified VidPN present path copy protection type   is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_COPYPROTECTION_TYPE = 0XC01E034F;

    /**
     * Only one unassigned mode set can exist at any one time   for a particular VidPN source or target.
     */
    public static final int STATUS_GRAPHICS_UNASSIGNED_MODESET_ALREADY_EXISTS = 0XC01E0350;

    /**
     * The specified scan line ordering type is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_SCANLINE_ORDERING = 0XC01E0352;

    /**
     * The topology changes are not allowed for the specified   VidPN.
     */
    public static final int STATUS_GRAPHICS_TOPOLOGY_CHANGES_NOT_ALLOWED = 0XC01E0353;

    /**
     * All available importance ordinals are being used in   the specified topology.
     */
    public static final int STATUS_GRAPHICS_NO_AVAILABLE_IMPORTANCE_ORDINALS = 0XC01E0354;

    /**
     * The specified primary surface has a different   private-format attribute than the current primary surface.
     */
    public static final int STATUS_GRAPHICS_INCOMPATIBLE_PRIVATE_FORMAT = 0XC01E0355;

    /**
     * The specified mode-pruning algorithm is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MODE_PRUNING_ALGORITHM = 0XC01E0356;

    /**
     * The specified monitor-capability origin is invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITOR_CAPABILITY_ORIGIN = 0XC01E0357;

    /**
     * The specified monitor-frequency range constraint is   invalid.
     */
    public static final int STATUS_GRAPHICS_INVALID_MONITOR_FREQUENCYRANGE_CONSTRAINT = 0XC01E0358;

    /**
     * The maximum supported number of present paths has been   reached.
     */
    public static final int STATUS_GRAPHICS_MAX_NUM_PATHS_REACHED = 0XC01E0359;

    /**
     * The miniport requested that augmentation be canceled   for the specified source of the specified VidPN\'s topology.
     */
    public static final int STATUS_GRAPHICS_CANCEL_VIDPN_TOPOLOGY_AUGMENTATION = 0XC01E035A;

    /**
     * The specified client type was not recognized.
     */
    public static final int STATUS_GRAPHICS_INVALID_CLIENT_TYPE = 0XC01E035B;

    /**
     * The client VidPN is not set on this adapter (for   example, no user mode-initiated mode changes have taken place on this   adapter).
     */
    public static final int STATUS_GRAPHICS_CLIENTVIDPN_NOT_SET = 0XC01E035C;

    /**
     * The specified display adapter child device already has   an external device connected to it.
     */
    public static final int STATUS_GRAPHICS_SPECIFIED_CHILD_ALREADY_CONNECTED = 0XC01E0400;

    /**
     * The display adapter child device does not support   reporting a descriptor.
     */
    public static final int STATUS_GRAPHICS_CHILD_DESCRIPTOR_NOT_SUPPORTED = 0XC01E0401;

    /**
     * The display adapter is not linked to any other   adapters.
     */
    public static final int STATUS_GRAPHICS_NOT_A_LINKED_ADAPTER = 0XC01E0430;

    /**
     * The lead adapter in a linked configuration was not   enumerated yet.
     */
    public static final int STATUS_GRAPHICS_LEADLINK_NOT_ENUMERATED = 0XC01E0431;

    /**
     * Some chain adapters in a linked configuration have not   yet been enumerated.
     */
    public static final int STATUS_GRAPHICS_CHAINLINKS_NOT_ENUMERATED = 0XC01E0432;

    /**
     * The chain of linked adapters is not ready to start   because of an unknown failure.
     */
    public static final int STATUS_GRAPHICS_ADAPTER_CHAIN_NOT_READY = 0XC01E0433;

    /**
     * An attempt was made to start a lead link display   adapter when the chain links had not yet started.
     */
    public static final int STATUS_GRAPHICS_CHAINLINKS_NOT_STARTED = 0XC01E0434;

    /**
     * An attempt was made to turn on a lead link display   adapter when the chain links were turned off.
     */
    public static final int STATUS_GRAPHICS_CHAINLINKS_NOT_POWERED_ON = 0XC01E0435;

    /**
     * The adapter link was found in an inconsistent state.   Not all adapters are in an expected PNP/power state.
     */
    public static final int STATUS_GRAPHICS_INCONSISTENT_DEVICE_LINK_STATE = 0XC01E0436;

    /**
     * The driver trying to start is not the same as the   driver for the posted display adapter.
     */
    public static final int STATUS_GRAPHICS_NOT_POST_DEVICE_DRIVER = 0XC01E0438;

    /**
     * An operation is being attempted that requires the   display adapter to be in a quiescent state.
     */
    public static final int STATUS_GRAPHICS_ADAPTER_ACCESS_NOT_EXCLUDED = 0XC01E043B;

    /**
     * The driver does not support OPM.
     */
    public static final int STATUS_GRAPHICS_OPM_NOT_SUPPORTED = 0XC01E0500;

    /**
     * The driver does not support COPP.
     */
    public static final int STATUS_GRAPHICS_COPP_NOT_SUPPORTED = 0XC01E0501;

    /**
     * The driver does not support UAB.
     */
    public static final int STATUS_GRAPHICS_UAB_NOT_SUPPORTED = 0XC01E0502;

    /**
     * The specified encrypted parameters are invalid.
     */
    public static final int STATUS_GRAPHICS_OPM_INVALID_ENCRYPTED_PARAMETERS = 0XC01E0503;

    /**
     * An array passed to a function cannot hold all of the   data that the function wants to put in it.
     */
    public static final int STATUS_GRAPHICS_OPM_PARAMETER_ARRAY_TOO_SMALL = 0XC01E0504;

    /**
     * The GDI display device passed to this function does   not have any active protected outputs.
     */
    public static final int STATUS_GRAPHICS_OPM_NO_PROTECTED_OUTPUTS_EXIST = 0XC01E0505;

    /**
     * The PVP cannot find an actual GDI display device that   corresponds to the passed-in GDI display device name.
     */
    public static final int STATUS_GRAPHICS_PVP_NO_DISPLAY_DEVICE_CORRESPONDS_TO_NAME = 0XC01E0506;

    /**
     * This function failed because the GDI display device   passed to it was not attached to the Windows desktop.
     */
    public static final int STATUS_GRAPHICS_PVP_DISPLAY_DEVICE_NOT_ATTACHED_TO_DESKTOP = 0XC01E0507;

    /**
     * The PVP does not support mirroring display devices   because they do not have any protected outputs.
     */
    public static final int STATUS_GRAPHICS_PVP_MIRRORING_DEVICES_NOT_SUPPORTED = 0XC01E0508;

    /**
     * The function failed because an invalid pointer   parameter was passed to it. A pointer parameter is invalid if it is null, is   not correctly aligned, or it points to an invalid address or a kernel mode
     * address.
     */
    public static final int STATUS_GRAPHICS_OPM_INVALID_POINTER = 0XC01E050A;

    /**
     * An internal error caused an operation to fail.
     */
    public static final int STATUS_GRAPHICS_OPM_INTERNAL_ERROR = 0XC01E050B;

    /**
     * The function failed because the caller passed in an   invalid OPM user-mode handle.
     */
    public static final int STATUS_GRAPHICS_OPM_INVALID_HANDLE = 0XC01E050C;

    /**
     * This function failed because the GDI device passed to   it did not have any monitors associated with it.
     */
    public static final int STATUS_GRAPHICS_PVP_NO_MONITORS_CORRESPOND_TO_DISPLAY_DEVICE = 0XC01E050D;

    /**
     * A certificate could not be returned because the   certificate buffer passed to the function was too small.
     */
    public static final int STATUS_GRAPHICS_PVP_INVALID_CERTIFICATE_LENGTH = 0XC01E050E;

    /**
     * DxgkDdiOpmCreateProtectedOutput() could not create a   protected output because the video present yarget is in spanning mode.
     */
    public static final int STATUS_GRAPHICS_OPM_SPANNING_MODE_ENABLED = 0XC01E050F;

    /**
     * DxgkDdiOpmCreateProtectedOutput() could not create a   protected output because the video present target is in theater mode.
     */
    public static final int STATUS_GRAPHICS_OPM_THEATER_MODE_ENABLED = 0XC01E0510;

    /**
     * The function call failed because the display adapter\'s   hardware functionality scan (HFS) failed to validate the graphics hardware.
     */
    public static final int STATUS_GRAPHICS_PVP_HFS_FAILED = 0XC01E0511;

    /**
     * The HDCP SRM passed to this function did not comply   with section 5 of the HDCP 1.1 specification.
     */
    public static final int STATUS_GRAPHICS_OPM_INVALID_SRM = 0XC01E0512;

    /**
     * The protected output cannot enable the HDCP system   because it does not support it.
     */
    public static final int STATUS_GRAPHICS_OPM_OUTPUT_DOES_NOT_SUPPORT_HDCP = 0XC01E0513;

    /**
     * The protected output cannot enable analog copy   protection because it does not support it.
     */
    public static final int STATUS_GRAPHICS_OPM_OUTPUT_DOES_NOT_SUPPORT_ACP = 0XC01E0514;

    /**
     * The protected output cannot enable the CGMS-A   protection technology because it does not support it.
     */
    public static final int STATUS_GRAPHICS_OPM_OUTPUT_DOES_NOT_SUPPORT_CGMSA = 0XC01E0515;

    /**
     * DxgkDdiOPMGetInformation() cannot return the version   of the SRM being used because the application never successfully passed an   SRM to the protected output.
     */
    public static final int STATUS_GRAPHICS_OPM_HDCP_SRM_NEVER_SET = 0XC01E0516;

    /**
     * DxgkDdiOPMConfigureProtectedOutput() cannot enable the   specified output protection technology because the output\'s screen resolution   is too high.
     */
    public static final int STATUS_GRAPHICS_OPM_RESOLUTION_TOO_HIGH = 0XC01E0517;

    /**
     * DxgkDdiOPMConfigureProtectedOutput() cannot enable   HDCP because other physical outputs are using the display adapter\'s HDCP   hardware.
     */
    public static final int STATUS_GRAPHICS_OPM_ALL_HDCP_HARDWARE_ALREADY_IN_USE = 0XC01E0518;

    /**
     * The operating system asynchronously destroyed this   OPM-protected output because the operating system state changed. This error   typically occurs because the monitor PDO associated with this protected   output
     * was removed or stopped, the protected output\'s session became a   nonconsole session, or the protected output\'s desktop became inactive.
     */
    public static final int STATUS_GRAPHICS_OPM_PROTECTED_OUTPUT_NO_LONGER_EXISTS = 0XC01E051A;

    /**
     * OPM functions cannot be called when a session is   changing its type. Three types of sessions currently exist: console,   disconnected, and remote (RDP or ICA).
     */
    public static final int STATUS_GRAPHICS_OPM_SESSION_TYPE_CHANGE_IN_PROGRESS = 0XC01E051B;

    /**
     * The DxgkDdiOPMGetCOPPCompatibleInformation,   DxgkDdiOPMGetInformation, or DxgkDdiOPMConfigureProtectedOutput function   failed. This error is returned only if a protected output has OPM semantics.
     */
    public static final int STATUS_GRAPHICS_OPM_PROTECTED_OUTPUT_DOES_NOT_HAVE_COPP_SEMANTICS = 0XC01E051C;

    /**
     * The DxgkDdiOPMGetInformation and   DxgkDdiOPMGetCOPPCompatibleInformation functions return this error code if   the passed-in sequence number is not the expected sequence number or the   passed-in OMAC value is
     * invalid.
     */
    public static final int STATUS_GRAPHICS_OPM_INVALID_INFORMATION_REQUEST = 0XC01E051D;

    /**
     * The function failed because an unexpected error   occurred inside a display driver.
     */
    public static final int STATUS_GRAPHICS_OPM_DRIVER_INTERNAL_ERROR = 0XC01E051E;

    /**
     * The DxgkDdiOPMGetCOPPCompatibleInformation,   DxgkDdiOPMGetInformation, or DxgkDdiOPMConfigureProtectedOutput function   failed. This error is returned only if a protected output has COPP semantics.
     */
    public static final int STATUS_GRAPHICS_OPM_PROTECTED_OUTPUT_DOES_NOT_HAVE_OPM_SEMANTICS = 0XC01E051F;

    /**
     * The DxgkDdiOPMGetCOPPCompatibleInformation and   DxgkDdiOPMConfigureProtectedOutput functions return this error if the display   driver does not support the DXGKMDT_OPM_GET_ACP_AND_CGMSA_SIGNALING and
     * DXGKMDT_OPM_SET_ACP_AND_CGMSA_SIGNALING GUIDs.
     */
    public static final int STATUS_GRAPHICS_OPM_SIGNALING_NOT_SUPPORTED = 0XC01E0520;

    /**
     * The DxgkDdiOPMConfigureProtectedOutput function returns   this error code if the passed-in sequence number is not the expected sequence   number or the passed-in OMAC value is invalid.
     */
    public static final int STATUS_GRAPHICS_OPM_INVALID_CONFIGURATION_REQUEST = 0XC01E0521;

    /**
     * The monitor connected to the specified video output   does not have an I2C bus.
     */
    public static final int STATUS_GRAPHICS_I2C_NOT_SUPPORTED = 0XC01E0580;

    /**
     * No device on the I2C bus has the specified address.
     */
    public static final int STATUS_GRAPHICS_I2C_DEVICE_DOES_NOT_EXIST = 0XC01E0581;

    /**
     * An error occurred while transmitting data to the   device on the I2C bus.
     */
    public static final int STATUS_GRAPHICS_I2C_ERROR_TRANSMITTING_DATA = 0XC01E0582;

    /**
     * An error occurred while receiving data from the device   on the I2C bus.
     */
    public static final int STATUS_GRAPHICS_I2C_ERROR_RECEIVING_DATA = 0XC01E0583;

    /**
     * The monitor does not support the specified VCP code.
     */
    public static final int STATUS_GRAPHICS_DDCCI_VCP_NOT_SUPPORTED = 0XC01E0584;

    /**
     * The data received from the monitor is invalid.
     */
    public static final int STATUS_GRAPHICS_DDCCI_INVALID_DATA = 0XC01E0585;

    /**
     * A function call failed because a monitor returned an   invalid timing status byte when the operating system used the DDC/CI get   timing report and timing message command to get a timing report from a   monitor.
     */
    public static final int STATUS_GRAPHICS_DDCCI_MONITOR_RETURNED_INVALID_TIMING_STATUS_BYTE = 0XC01E0586;

    /**
     * A monitor returned a DDC/CI capabilities string that   did not comply with the ACCESS.bus 3.0, DDC/CI 1.1, or MCCS 2 Revision 1   specification.
     */
    public static final int STATUS_GRAPHICS_DDCCI_INVALID_CAPABILITIES_STRING = 0XC01E0587;

    /**
     * An internal error caused an operation to fail.
     */
    public static final int STATUS_GRAPHICS_MCA_INTERNAL_ERROR = 0XC01E0588;

    /**
     * An operation failed because a DDC/CI message had an   invalid value in its command field.
     */
    public static final int STATUS_GRAPHICS_DDCCI_INVALID_MESSAGE_COMMAND = 0XC01E0589;

    /**
     * This error occurred because a DDC/CI message had an   invalid value in its length field.
     */
    public static final int STATUS_GRAPHICS_DDCCI_INVALID_MESSAGE_LENGTH = 0XC01E058A;

    /**
     * This error occurred because the value in a DDC/CI   message\'s checksum field did not match the message\'s computed checksum value.   This error implies that the data was corrupted while it was being transmitted
     * from a monitor to a computer.
     */
    public static final int STATUS_GRAPHICS_DDCCI_INVALID_MESSAGE_CHECKSUM = 0XC01E058B;

    /**
     * This function failed because an invalid monitor handle   was passed to it.
     */
    public static final int STATUS_GRAPHICS_INVALID_PHYSICAL_MONITOR_HANDLE = 0XC01E058C;

    /**
     * The operating system asynchronously destroyed the   monitor that corresponds to this handle because the operating system\'s state   changed. This error typically occurs because the monitor PDO associated with
     * this handle was removed or stopped, or a display mode change occurred. A   display mode change occurs when Windows sends a WM_DISPLAYCHANGE message to   applications.
     */
    public static final int STATUS_GRAPHICS_MONITOR_NO_LONGER_EXISTS = 0XC01E058D;

    /**
     * This function can be used only if a program is running   in the local console session. It cannot be used if a program is running on a   remote desktop session or on a terminal server session.
     */
    public static final int STATUS_GRAPHICS_ONLY_CONSOLE_SESSION_SUPPORTED = 0XC01E05E0;

    /**
     * This function cannot find an actual GDI display device   that corresponds to the specified GDI display device name.
     */
    public static final int STATUS_GRAPHICS_NO_DISPLAY_DEVICE_CORRESPONDS_TO_NAME = 0XC01E05E1;

    /**
     * The function failed because the specified GDI display   device was not attached to the Windows desktop.
     */
    public static final int STATUS_GRAPHICS_DISPLAY_DEVICE_NOT_ATTACHED_TO_DESKTOP = 0XC01E05E2;

    /**
     * This function does not support GDI mirroring display   devices because GDI mirroring display devices do not have any physical monitors   associated with them.
     */
    public static final int STATUS_GRAPHICS_MIRRORING_DEVICES_NOT_SUPPORTED = 0XC01E05E3;

    /**
     * The function failed because an invalid pointer   parameter was passed to it. A pointer parameter is invalid if it is null, is   not correctly aligned, or points to an invalid address or to a kernel mode
     * address.
     */
    public static final int STATUS_GRAPHICS_INVALID_POINTER = 0XC01E05E4;

    /**
     * This function failed because the GDI device passed to   it did not have a monitor associated with it.
     */
    public static final int STATUS_GRAPHICS_NO_MONITORS_CORRESPOND_TO_DISPLAY_DEVICE = 0XC01E05E5;

    /**
     * An array passed to the function cannot hold all of the   data that the function must copy into the array.
     */
    public static final int STATUS_GRAPHICS_PARAMETER_ARRAY_TOO_SMALL = 0XC01E05E6;

    /**
     * An internal error caused an operation to fail.
     */
    public static final int STATUS_GRAPHICS_INTERNAL_ERROR = 0XC01E05E7;

    /**
     * The function failed because the current session is   changing its type. This function cannot be called when the current session is   changing its type. Three types of sessions currently exist: console,
     * disconnected, and remote (RDP or ICA).
     */
    public static final int STATUS_GRAPHICS_SESSION_TYPE_CHANGE_IN_PROGRESS = 0XC01E05E8;

    /**
     * The volume must be unlocked before it can be used.
     */
    public static final int STATUS_FVE_LOCKED_VOLUME = 0XC0210000;

    /**
     * The volume is fully decrypted and no key is available.
     */
    public static final int STATUS_FVE_NOT_ENCRYPTED = 0XC0210001;

    /**
     * The control block for the encrypted volume is not   valid.
     */
    public static final int STATUS_FVE_BAD_INFORMATION = 0XC0210002;

    /**
     * Not enough free space remains on the volume to allow   encryption.
     */
    public static final int STATUS_FVE_TOO_SMALL = 0XC0210003;

    /**
     * The partition cannot be encrypted because the file   system is not supported.
     */
    public static final int STATUS_FVE_FAILED_WRONG_FS = 0XC0210004;

    /**
     * The file system is inconsistent. Run the Check Disk   utility.
     */
    public static final int STATUS_FVE_FAILED_BAD_FS = 0XC0210005;

    /**
     * The file system does not extend to the end of the   volume.
     */
    public static final int STATUS_FVE_FS_NOT_EXTENDED = 0XC0210006;

    /**
     * This operation cannot be performed while a file system   is mounted on the volume.
     */
    public static final int STATUS_FVE_FS_MOUNTED = 0XC0210007;

    /**
     * BitLocker Drive Encryption is not included with this   version of Windows.
     */
    public static final int STATUS_FVE_NO_LICENSE = 0XC0210008;

    /**
     * The requested action was denied by the FVE control   engine.
     */
    public static final int STATUS_FVE_ACTION_NOT_ALLOWED = 0XC0210009;

    /**
     * The data supplied is malformed.
     */
    public static final int STATUS_FVE_BAD_DATA = 0XC021000A;

    /**
     * The volume is not bound to the system.
     */
    public static final int STATUS_FVE_VOLUME_NOT_BOUND = 0XC021000B;

    /**
     * The volume specified is not a data volume.
     */
    public static final int STATUS_FVE_NOT_DATA_VOLUME = 0XC021000C;

    /**
     * A read operation failed while converting the volume.
     */
    public static final int STATUS_FVE_CONV_READ_ERROR = 0XC021000D;

    /**
     * A write operation failed while converting the volume.
     */
    public static final int STATUS_FVE_CONV_WRITE_ERROR = 0XC021000E;

    /**
     * The control block for the encrypted volume was updated   by another thread. Try again.
     */
    public static final int STATUS_FVE_OVERLAPPED_UPDATE = 0XC021000F;

    /**
     * The volume encryption algorithm cannot be used on this   sector size.
     */
    public static final int STATUS_FVE_FAILED_SECTOR_SIZE = 0XC0210010;

    /**
     * BitLocker recovery authentication failed.
     */
    public static final int STATUS_FVE_FAILED_AUTHENTICATION = 0XC0210011;

    /**
     * The volume specified is not the boot operating system   volume.
     */
    public static final int STATUS_FVE_NOT_OS_VOLUME = 0XC0210012;

    /**
     * The BitLocker startup key or recovery password could   not be read from external media.
     */
    public static final int STATUS_FVE_KEYFILE_NOT_FOUND = 0XC0210013;

    /**
     * The BitLocker startup key or recovery password file is   corrupt or invalid.
     */
    public static final int STATUS_FVE_KEYFILE_INVALID = 0XC0210014;

    /**
     * The BitLocker encryption key could not be obtained   from the startup key or the recovery password.
     */
    public static final int STATUS_FVE_KEYFILE_NO_VMK = 0XC0210015;

    /**
     * The TPM is disabled.
     */
    public static final int STATUS_FVE_TPM_DISABLED = 0XC0210016;

    /**
     * The authorization data for the SRK of the TPM is not   zero.
     */
    public static final int STATUS_FVE_TPM_SRK_AUTH_NOT_ZERO = 0XC0210017;

    /**
     * The system boot information changed or the TPM locked   out access to BitLocker encryption keys until the computer is restarted.
     */
    public static final int STATUS_FVE_TPM_INVALID_PCR = 0XC0210018;

    /**
     * The BitLocker encryption key could not be obtained   from the TPM.
     */
    public static final int STATUS_FVE_TPM_NO_VMK = 0XC0210019;

    /**
     * The BitLocker encryption key could not be obtained   from the TPM and PIN.
     */
    public static final int STATUS_FVE_PIN_INVALID = 0XC021001A;

    /**
     * A boot application hash does not match the hash   computed when BitLocker was turned on.
     */
    public static final int STATUS_FVE_AUTH_INVALID_APPLICATION = 0XC021001B;

    /**
     * The Boot Configuration Data (BCD) settings are not   supported or have changed because BitLocker was enabled.
     */
    public static final int STATUS_FVE_AUTH_INVALID_CONFIG = 0XC021001C;

    /**
     * Boot debugging is enabled. Run Windows Boot   Configuration Data Store Editor (bcdedit.exe) to turn it off.
     */
    public static final int STATUS_FVE_DEBUGGER_ENABLED = 0XC021001D;

    /**
     * The BitLocker encryption key could not be obtained.
     */
    public static final int STATUS_FVE_DRY_RUN_FAILED = 0XC021001E;

    /**
     * The metadata disk region pointer is incorrect.
     */
    public static final int STATUS_FVE_BAD_METADATA_POINTER = 0XC021001F;

    /**
     * The backup copy of the metadata is out of date.
     */
    public static final int STATUS_FVE_OLD_METADATA_COPY = 0XC0210020;

    /**
     * No action was taken because a system restart is   required.
     */
    public static final int STATUS_FVE_REBOOT_REQUIRED = 0XC0210021;

    /**
     * No action was taken because BitLocker Drive Encryption   is in RAW access mode.
     */
    public static final int STATUS_FVE_RAW_ACCESS = 0XC0210022;

    /**
     * BitLocker Drive Encryption cannot enter RAW access   mode for this volume.
     */
    public static final int STATUS_FVE_RAW_BLOCKED = 0XC0210023;

    /**
     * This feature of BitLocker Drive Encryption is not   included with this version of Windows.
     */
    public static final int STATUS_FVE_NO_FEATURE_LICENSE = 0XC0210026;

    /**
     * Group policy does not permit turning off BitLocker   Drive Encryption on roaming data volumes.
     */
    public static final int STATUS_FVE_POLICY_USER_DISABLE_RDV_NOT_ALLOWED = 0XC0210027;

    /**
     * Bitlocker Drive Encryption failed to recover from   aborted conversion. This could be due to either all conversion logs being   corrupted or the media being write-protected.
     */
    public static final int STATUS_FVE_CONV_RECOVERY_FAILED = 0XC0210028;

    /**
     * The requested virtualization size is too big.
     */
    public static final int STATUS_FVE_VIRTUALIZED_SPACE_TOO_BIG = 0XC0210029;

    /**
     * The drive is too small to be protected using BitLocker   Drive Encryption.
     */
    public static final int STATUS_FVE_VOLUME_TOO_SMALL = 0XC0210030;

    /**
     * The callout does not exist.
     */
    public static final int STATUS_FWP_CALLOUT_NOT_FOUND = 0XC0220001;

    /**
     * The filter condition does not exist.
     */
    public static final int STATUS_FWP_CONDITION_NOT_FOUND = 0XC0220002;

    /**
     * The filter does not exist.
     */
    public static final int STATUS_FWP_FILTER_NOT_FOUND = 0XC0220003;

    /**
     * The layer does not exist.
     */
    public static final int STATUS_FWP_LAYER_NOT_FOUND = 0XC0220004;

    /**
     * The provider does not exist.
     */
    public static final int STATUS_FWP_PROVIDER_NOT_FOUND = 0XC0220005;

    /**
     * The provider context does not exist.
     */
    public static final int STATUS_FWP_PROVIDER_CONTEXT_NOT_FOUND = 0XC0220006;

    /**
     * The sublayer does not exist.
     */
    public static final int STATUS_FWP_SUBLAYER_NOT_FOUND = 0XC0220007;

    /**
     * The object does not exist.
     */
    public static final int STATUS_FWP_NOT_FOUND = 0XC0220008;

    /**
     * An object with that GUID or LUID already exists.
     */
    public static final int STATUS_FWP_ALREADY_EXISTS = 0XC0220009;

    /**
     * The object is referenced by other objects and cannot   be deleted.
     */
    public static final int STATUS_FWP_IN_USE = 0XC022000A;

    /**
     * The call is not allowed from within a dynamic session.
     */
    public static final int STATUS_FWP_DYNAMIC_SESSION_IN_PROGRESS = 0XC022000B;

    /**
     * The call was made from the wrong session and cannot be   completed.
     */
    public static final int STATUS_FWP_WRONG_SESSION = 0XC022000C;

    /**
     * The call must be made from within an explicit   transaction.
     */
    public static final int STATUS_FWP_NO_TXN_IN_PROGRESS = 0XC022000D;

    /**
     * The call is not allowed from within an explicit   transaction.
     */
    public static final int STATUS_FWP_TXN_IN_PROGRESS = 0XC022000E;

    /**
     * The explicit transaction has been forcibly canceled.
     */
    public static final int STATUS_FWP_TXN_ABORTED = 0XC022000F;

    /**
     * The session has been canceled.
     */
    public static final int STATUS_FWP_SESSION_ABORTED = 0XC0220010;

    /**
     * The call is not allowed from within a read-only   transaction.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_TXN = 0XC0220011;

    /**
     * The call timed out while waiting to acquire the   transaction lock.
     */
    public static final int STATUS_FWP_TIMEOUT = 0XC0220012;

    /**
     * The collection of network diagnostic events is   disabled.
     */
    public static final int STATUS_FWP_NET_EVENTS_DISABLED = 0XC0220013;

    /**
     * The operation is not supported by the specified layer.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_LAYER = 0XC0220014;

    /**
     * The call is allowed for kernel-mode callers only.
     */
    public static final int STATUS_FWP_KM_CLIENTS_ONLY = 0XC0220015;

    /**
     * The call tried to associate two objects with   incompatible lifetimes.
     */
    public static final int STATUS_FWP_LIFETIME_MISMATCH = 0XC0220016;

    /**
     * The object is built-in and cannot be deleted.
     */
    public static final int STATUS_FWP_BUILTIN_OBJECT = 0XC0220017;

    /**
     * The maximum number of boot-time filters has been   reached.
     */
    public static final int STATUS_FWP_TOO_MANY_BOOTTIME_FILTERS = 0XC0220018;

    /**
     * The maximum number of callouts has been reached.
     */
    public static final int STATUS_FWP_TOO_MANY_CALLOUTS = 0XC0220018;

    /**
     * A notification could not be delivered because a   message queue has reached maximum capacity.
     */
    public static final int STATUS_FWP_NOTIFICATION_DROPPED = 0XC0220019;

    /**
     * The traffic parameters do not match those for the   security association context.
     */
    public static final int STATUS_FWP_TRAFFIC_MISMATCH = 0XC022001A;

    /**
     * The call is not allowed for the current security   association state.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_SA_STATE = 0XC022001B;

    /**
     * A required pointer is null.
     */
    public static final int STATUS_FWP_NULL_POINTER = 0XC022001C;

    /**
     * An enumerator is not valid.
     */
    public static final int STATUS_FWP_INVALID_ENUMERATOR = 0XC022001D;

    /**
     * The flags field contains an invalid value.
     */
    public static final int STATUS_FWP_INVALID_FLAGS = 0XC022001E;

    /**
     * A network mask is not valid.
     */
    public static final int STATUS_FWP_INVALID_NET_MASK = 0XC022001F;

    /**
     * An FWP_RANGE is not valid.
     */
    public static final int STATUS_FWP_INVALID_RANGE = 0XC0220020;

    /**
     * The time interval is not valid.
     */
    public static final int STATUS_FWP_INVALID_INTERVAL = 0XC0220021;

    /**
     * An array that must contain at least one element has a   zero length.
     */
    public static final int STATUS_FWP_ZERO_LENGTH_ARRAY = 0XC0220022;

    /**
     * The displayData.name field cannot be null.
     */
    public static final int STATUS_FWP_NULL_DISPLAY_NAME = 0XC0220023;

    /**
     * The action type is not one of the allowed action types   for a filter.
     */
    public static final int STATUS_FWP_INVALID_ACTION_TYPE = 0XC0220024;

    /**
     * The filter weight is not valid.
     */
    public static final int STATUS_FWP_INVALID_WEIGHT = 0XC0220025;

    /**
     * A filter condition contains a match type that is not   compatible with the operands.
     */
    public static final int STATUS_FWP_MATCH_TYPE_MISMATCH = 0XC0220026;

    /**
     * An FWP_VALUE or FWPM_CONDITION_VALUE is of the wrong   type.
     */
    public static final int STATUS_FWP_TYPE_MISMATCH = 0XC0220027;

    /**
     * An integer value is outside the allowed range.
     */
    public static final int STATUS_FWP_OUT_OF_BOUNDS = 0XC0220028;

    /**
     * A reserved field is nonzero.
     */
    public static final int STATUS_FWP_RESERVED = 0XC0220029;

    /**
     * A filter cannot contain multiple conditions operating   on a single field.
     */
    public static final int STATUS_FWP_DUPLICATE_CONDITION = 0XC022002A;

    /**
     * A policy cannot contain the same keying module more   than once.
     */
    public static final int STATUS_FWP_DUPLICATE_KEYMOD = 0XC022002B;

    /**
     * The action type is not compatible with the layer.
     */
    public static final int STATUS_FWP_ACTION_INCOMPATIBLE_WITH_LAYER = 0XC022002C;

    /**
     * The action type is not compatible with the sublayer.
     */
    public static final int STATUS_FWP_ACTION_INCOMPATIBLE_WITH_SUBLAYER = 0XC022002D;

    /**
     * The raw context or the provider context is not   compatible with the layer.
     */
    public static final int STATUS_FWP_CONTEXT_INCOMPATIBLE_WITH_LAYER = 0XC022002E;

    /**
     * The raw context or the provider context is not   compatible with the callout.
     */
    public static final int STATUS_FWP_CONTEXT_INCOMPATIBLE_WITH_CALLOUT = 0XC022002F;

    /**
     * The authentication method is not compatible with the   policy type.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_AUTH_METHOD = 0XC0220030;

    /**
     * The Diffie-Hellman group is not compatible with the   policy type.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_DH_GROUP = 0XC0220031;

    /**
     * An IKE policy cannot contain an Extended Mode policy.
     */
    public static final int STATUS_FWP_EM_NOT_SUPPORTED = 0XC0220032;

    /**
     * The enumeration template or subscription will never   match any objects.
     */
    public static final int STATUS_FWP_NEVER_MATCH = 0XC0220033;

    /**
     * The provider context is of the wrong type.
     */
    public static final int STATUS_FWP_PROVIDER_CONTEXT_MISMATCH = 0XC0220034;

    /**
     * The parameter is incorrect.
     */
    public static final int STATUS_FWP_INVALID_PARAMETER = 0XC0220035;

    /**
     * The maximum number of sublayers has been reached.
     */
    public static final int STATUS_FWP_TOO_MANY_SUBLAYERS = 0XC0220036;

    /**
     * The notification function for a callout returned an   error.
     */
    public static final int STATUS_FWP_CALLOUT_NOTIFICATION_FAILED = 0XC0220037;

    /**
     * The IPsec authentication configuration is not   compatible with the authentication type.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_AUTH_CONFIG = 0XC0220038;

    /**
     * The IPsec cipher configuration is not compatible with   the cipher type.
     */
    public static final int STATUS_FWP_INCOMPATIBLE_CIPHER_CONFIG = 0XC0220039;

    /**
     * A policy cannot contain the same auth method more than   once.
     */
    public static final int STATUS_FWP_DUPLICATE_AUTH_METHOD = 0XC022003C;

    /**
     * The TCP/IP stack is not ready.
     */
    public static final int STATUS_FWP_TCPIP_NOT_READY = 0XC0220100;

    /**
     * The injection handle is being closed by another   thread.
     */
    public static final int STATUS_FWP_INJECT_HANDLE_CLOSING = 0XC0220101;

    /**
     * The injection handle is stale.
     */
    public static final int STATUS_FWP_INJECT_HANDLE_STALE = 0XC0220102;

    /**
     * The classify cannot be pended.
     */
    public static final int STATUS_FWP_CANNOT_PEND = 0XC0220103;

    /**
     * The binding to the network interface is being closed.
     */
    public static final int STATUS_NDIS_CLOSING = 0XC0230002;

    /**
     * An invalid version was specified.
     */
    public static final int STATUS_NDIS_BAD_VERSION = 0XC0230004;

    /**
     * An invalid characteristics table was used.
     */
    public static final int STATUS_NDIS_BAD_CHARACTERISTICS = 0XC0230005;

    /**
     * Failed to find the network interface or the network   interface is not ready.
     */
    public static final int STATUS_NDIS_ADAPTER_NOT_FOUND = 0XC0230006;

    /**
     * Failed to open the network interface.
     */
    public static final int STATUS_NDIS_OPEN_FAILED = 0XC0230007;

    /**
     * The network interface has encountered an internal   unrecoverable failure.
     */
    public static final int STATUS_NDIS_DEVICE_FAILED = 0XC0230008;

    /**
     * The multicast list on the network interface is full.
     */
    public static final int STATUS_NDIS_MULTICAST_FULL = 0XC0230009;

    /**
     * An attempt was made to add a duplicate multicast   address to the list.
     */
    public static final int STATUS_NDIS_MULTICAST_EXISTS = 0XC023000A;

    /**
     * At attempt was made to remove a multicast address that   was never added.
     */
    public static final int STATUS_NDIS_MULTICAST_NOT_FOUND = 0XC023000B;

    /**
     * The network interface aborted the request.
     */
    public static final int STATUS_NDIS_REQUEST_ABORTED = 0XC023000C;

    /**
     * The network interface cannot process the request   because it is being reset.
     */
    public static final int STATUS_NDIS_RESET_IN_PROGRESS = 0XC023000D;

    /**
     * An attempt was made to send an invalid packet on a   network interface.
     */
    public static final int STATUS_NDIS_INVALID_PACKET = 0XC023000F;

    /**
     * The specified request is not a valid operation for the   target device.
     */
    public static final int STATUS_NDIS_INVALID_DEVICE_REQUEST = 0XC0230010;

    /**
     * The network interface is not ready to complete this   operation.
     */
    public static final int STATUS_NDIS_ADAPTER_NOT_READY = 0XC0230011;

    /**
     * The length of the buffer submitted for this operation   is not valid.
     */
    public static final int STATUS_NDIS_INVALID_LENGTH = 0XC0230014;

    /**
     * The data used for this operation is not valid.
     */
    public static final int STATUS_NDIS_INVALID_DATA = 0XC0230015;

    /**
     * The length of the submitted buffer for this operation   is too small.
     */
    public static final int STATUS_NDIS_BUFFER_TOO_SHORT = 0XC0230016;

    /**
     * The network interface does not support this object   identifier.
     */
    public static final int STATUS_NDIS_INVALID_OID = 0XC0230017;

    /**
     * The network interface has been removed.
     */
    public static final int STATUS_NDIS_ADAPTER_REMOVED = 0XC0230018;

    /**
     * The network interface does not support this media   type.
     */
    public static final int STATUS_NDIS_UNSUPPORTED_MEDIA = 0XC0230019;

    /**
     * An attempt was made to remove a token ring group   address that is in use by other components.
     */
    public static final int STATUS_NDIS_GROUP_ADDRESS_IN_USE = 0XC023001A;

    /**
     * An attempt was made to map a file that cannot be   found.
     */
    public static final int STATUS_NDIS_FILE_NOT_FOUND = 0XC023001B;

    /**
     * An error occurred while NDIS tried to map the file.
     */
    public static final int STATUS_NDIS_ERROR_READING_FILE = 0XC023001C;

    /**
     * An attempt was made to map a file that is already   mapped.
     */
    public static final int STATUS_NDIS_ALREADY_MAPPED = 0XC023001D;

    /**
     * An attempt to allocate a hardware resource failed   because the resource is used by another component.
     */
    public static final int STATUS_NDIS_RESOURCE_CONFLICT = 0XC023001E;

    /**
     * The I/O operation failed because the network media is   disconnected or the wireless access point is out of range.
     */
    public static final int STATUS_NDIS_MEDIA_DISCONNECTED = 0XC023001F;

    /**
     * The network address used in the request is invalid.
     */
    public static final int STATUS_NDIS_INVALID_ADDRESS = 0XC0230022;

    /**
     * The offload operation on the network interface has   been paused.
     */
    public static final int STATUS_NDIS_PAUSED = 0XC023002A;

    /**
     * The network interface was not found.
     */
    public static final int STATUS_NDIS_INTERFACE_NOT_FOUND = 0XC023002B;

    /**
     * The revision number specified in the structure is not   supported.
     */
    public static final int STATUS_NDIS_UNSUPPORTED_REVISION = 0XC023002C;

    /**
     * The specified port does not exist on this network   interface.
     */
    public static final int STATUS_NDIS_INVALID_PORT = 0XC023002D;

    /**
     * The current state of the specified port on this   network interface does not support the requested operation.
     */
    public static final int STATUS_NDIS_INVALID_PORT_STATE = 0XC023002E;

    /**
     * The miniport adapter is in a lower power state.
     */
    public static final int STATUS_NDIS_LOW_POWER_STATE = 0XC023002F;

    /**
     * The network interface does not support this request.
     */
    public static final int STATUS_NDIS_NOT_SUPPORTED = 0XC02300BB;

    /**
     * The TCP connection is not offloadable because of a   local policy setting.
     */
    public static final int STATUS_NDIS_OFFLOAD_POLICY = 0XC023100F;

    /**
     * The TCP connection is not offloadable by the Chimney   offload target.
     */
    public static final int STATUS_NDIS_OFFLOAD_CONNECTION_REJECTED = 0XC0231012;

    /**
     * The IP Path object is not in an offloadable state.
     */
    public static final int STATUS_NDIS_OFFLOAD_PATH_REJECTED = 0XC0231013;

    /**
     * The wireless LAN interface is in auto-configuration   mode and does not support the requested parameter change operation.
     */
    public static final int STATUS_NDIS_DOT11_AUTO_CONFIG_ENABLED = 0XC0232000;

    /**
     * The wireless LAN interface is busy and cannot perform   the requested operation.
     */
    public static final int STATUS_NDIS_DOT11_MEDIA_IN_USE = 0XC0232001;

    /**
     * The wireless LAN interface is power down and does not   support the requested operation.
     */
    public static final int STATUS_NDIS_DOT11_POWER_STATE_INVALID = 0XC0232002;

    /**
     * The list of wake on LAN patterns is full.
     */
    public static final int STATUS_NDIS_PM_WOL_PATTERN_LIST_FULL = 0XC0232003;

    /**
     * The list of low power protocol offloads is full.
     */
    public static final int STATUS_NDIS_PM_PROTOCOL_OFFLOAD_LIST_FULL = 0XC0232004;

    /**
     * The SPI in the packet does not match a valid IPsec SA.
     */
    public static final int STATUS_IPSEC_BAD_SPI = 0XC0360001;

    /**
     * The packet was received on an IPsec SA whose lifetime   has expired.
     */
    public static final int STATUS_IPSEC_SA_LIFETIME_EXPIRED = 0XC0360002;

    /**
     * The packet was received on an IPsec SA that does not   match the packet characteristics.
     */
    public static final int STATUS_IPSEC_WRONG_SA = 0XC0360003;

    /**
     * The packet sequence number replay check failed.
     */
    public static final int STATUS_IPSEC_REPLAY_CHECK_FAILED = 0XC0360004;

    /**
     * The IPsec header and/or trailer in the packet is   invalid.
     */
    public static final int STATUS_IPSEC_INVALID_PACKET = 0XC0360005;

    /**
     * The IPsec integrity check failed.
     */
    public static final int STATUS_IPSEC_INTEGRITY_CHECK_FAILED = 0XC0360006;

    /**
     * IPsec dropped a clear text packet.
     */
    public static final int STATUS_IPSEC_CLEAR_TEXT_DROP = 0XC0360007;

    /**
     * IPsec dropped an incoming ESP packet in authenticated   firewall mode.  This drop is benign.
     */
    public static final int STATUS_IPSEC_AUTH_FIREWALL_DROP = 0XC0360008;

    /**
     * IPsec dropped a packet due to DOS throttle.
     */
    public static final int STATUS_IPSEC_THROTTLE_DROP = 0XC0360009;

    /**
     * IPsec Dos Protection matched an explicit block rule.
     */
    public static final int STATUS_IPSEC_DOSP_BLOCK = 0XC0368000;

    /**
     * IPsec Dos Protection received an IPsec specific   multicast packet which is not allowed.
     */
    public static final int STATUS_IPSEC_DOSP_RECEIVED_MULTICAST = 0XC0368001;

    /**
     * IPsec Dos Protection received an incorrectly formatted   packet.
     */
    public static final int STATUS_IPSEC_DOSP_INVALID_PACKET = 0XC0368002;

    /**
     * IPsec Dos Protection failed to lookup state.
     */
    public static final int STATUS_IPSEC_DOSP_STATE_LOOKUP_FAILED = 0XC0368003;

    /**
     * IPsec Dos Protection failed to create state because   there are already maximum number of entries allowed by policy.
     */
    public static final int STATUS_IPSEC_DOSP_MAX_ENTRIES = 0XC0368004;

    /**
     * IPsec Dos Protection received an IPsec negotiation   packet for a keying module which is not allowed by policy.
     */
    public static final int STATUS_IPSEC_DOSP_KEYMOD_NOT_ALLOWED = 0XC0368005;

    /**
     * IPsec Dos Protection failed to create per internal IP   ratelimit queue because there is already maximum number of queues allowed by   policy.
     */
    public static final int STATUS_IPSEC_DOSP_MAX_PER_IP_RATELIMIT_QUEUES = 0XC0368006;

    /**
     * The system does not support mirrored volumes.
     */
    public static final int STATUS_VOLMGR_MIRROR_NOT_SUPPORTED = 0XC038005B;

    /**
     * The system does not support RAID-5 volumes.
     */
    public static final int STATUS_VOLMGR_RAID5_NOT_SUPPORTED = 0XC038005C;

    /**
     * A virtual disk support provider for the specified file   was not found.
     */
    public static final int STATUS_VIRTDISK_PROVIDER_NOT_FOUND = 0XC03A0014;

    /**
     * The specified disk is not a virtual disk.
     */
    public static final int STATUS_VIRTDISK_NOT_VIRTUAL_DISK = 0XC03A0015;

    /**
     * The chain of virtual hard disks is inaccessible. The   process has not been granted access rights to the parent virtual hard disk   for the differencing disk.
     */
    public static final int STATUS_VHD_PARENT_VHD_ACCESS_DENIED = 0XC03A0016;

    /**
     * The chain of virtual hard disks is corrupted. There is   a mismatch in the virtual sizes of the parent virtual hard disk and   differencing disk.
     */
    public static final int STATUS_VHD_CHILD_PARENT_SIZE_MISMATCH = 0XC03A0017;

    /**
     * The chain of virtual hard disks is corrupted. A   differencing disk is indicated in its own parent chain.
     */
    public static final int STATUS_VHD_DIFFERENCING_CHAIN_CYCLE_DETECTED = 0XC03A0018;

    /**
     * The chain of virtual hard disks is inaccessible. There   was an error opening a virtual hard disk further up the chain.
     */
    public static final int STATUS_VHD_DIFFERENCING_CHAIN_ERROR_IN_PARENT = 0XC03A0019;


    private NtStatuses(){

    }

}
			