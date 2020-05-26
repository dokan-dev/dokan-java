package dev.dokan.dokan_java;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT;
import dev.dokan.dokan_java.constants.microsoft.CreationDisposition;

import java.nio.file.attribute.FileTime;
import java.util.Date;

import static dev.dokan.dokan_java.constants.microsoft.CreateDispositions.*;
import static dev.dokan.dokan_java.constants.microsoft.CreateOptions.*;
import static dev.dokan.dokan_java.constants.microsoft.CreationDisposition.*;

/**
 * Utilities to do various operations.
 */
public class DokanUtils {

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

    private DokanUtils() {

    }

    public static String trimStrToSize(final String str, final int len) {
        return str.substring(0, Math.min(str.length(), len));
    }

    public static FILETIME toFILETIME(final FileTime time) {
        return getTime(time.toMillis());
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
    public static int ntStatusFromWin32ErrorCode(int win32Errorcode) { //FIXME
        return (int) DokanNativeMethods.DokanNtStatusFromWin32(win32Errorcode);
    }

}
