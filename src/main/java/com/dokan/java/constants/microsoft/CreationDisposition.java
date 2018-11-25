package com.dokan.java.constants.microsoft;

import com.dokan.java.constants.EnumInteger;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

/**
 * Enum of actions to take on a not-necessarily existing file or device.
 * <p>These values are the userspace equivalent of {@link CreateDisposition}.
 * For a given CreateDisposition value the corresponding CreationDisposition value can be computed via the {@link com.dokan.java.NativeMethods#DokanMapKernelToUserCreateFileFlags(long, long, long, long,
 * IntByReference, IntByReference, IntByReference)}
 * </p>
 *
 * <p>
 * The following table shows the actions performed on a file for the given flag distinguishing if the file already exists or not:
 * </p>
 *
 * <table class="striped">
 * <caption style="display:none">additional options</caption>
 * <thead>
 * <tr>
 * <th scope="col">Option</th>
 * <th scope="col">Action if file exists</th>
 * <th scope="col">Action if file does not exist</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <th scope="row">CREATE_ALWAYS</th>
 * <td>Overwrite the file and return {@link Win32ErrorCodes#ERROR_ALREADY_EXISTS}.</td>
 * <td>Create the file.</td>
 * </tr>
 * <tr>
 * <th scope="row">CREATE_NEW</th>
 * <td>Fail and return {@link Win32ErrorCodes#ERROR_ALREADY_EXISTS}.</td>
 * <td>Create the file.</td>
 * </tr>
 * <tr>
 * <th scope="row">OPEN_ALWAYS</th>
 * <td>Open the file and return {@link Win32ErrorCodes#ERROR_ALREADY_EXISTS}.</td>
 * <td>Create the file.</td>
 * </tr>
 * <tr>
 * <th scope="row">OPEN_EXISTING</th>
 * <td>Open the file.</td>
 * <td>Fail and return {@link Win32ErrorCodes#ERROR_FILE_NOT_FOUND}.</td>
 * </tr>
 * <tr>
 * <th scope="row">TRUNCATE_EXISTING</th>
 * <td>Truncate the file, and overwrite it.</td>
 * <td>Fail.</td>
 * </tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://docs.microsoft.com/en-us/windows/desktop/api/fileapi/nf-fileapi-createfilea#parameters">The Microsoft Documentation of CreateFile</a>
 * @see <a href="https://stackoverflow.com/questions/14469607/difference-between-open-always-and-create-always-in-createfile-of-windows-api">StackOverFlow Answer summing up the above table</a>.
 */
public enum CreationDisposition implements EnumInteger {
    CREATE_NEW(WinNT.CREATE_NEW),
    CREATE_ALWAYS(WinNT.CREATE_ALWAYS),
    OPEN_EXISTING(WinNT.OPEN_EXISTING),
    OPEN_ALWAYS(WinNT.OPEN_ALWAYS),
    TRUNCATE_EXISTING(WinNT.TRUNCATE_EXISTING);

    private final int mask;

    CreationDisposition(final int i) {
        mask = i;
    }

    public static CreationDisposition fromInt(final int value) {
        return EnumInteger.enumFromInt(value, values());
    }

    public int getMask() {
        return this.mask;
    }

}
