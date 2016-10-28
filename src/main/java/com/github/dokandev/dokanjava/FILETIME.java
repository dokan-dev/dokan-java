package com.github.dokandev.dokanjava;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"PointlessBitwiseExpression", "WeakerAccess"})
abstract public class FILETIME extends Structure {
    private static final long EPOCH_DIFF = 11644473600000L;

    public int dwHighDateTime;
    public int dwLowDateTime;

    public static Date filetimeToDate(final int high, final int low) {
        final long filetime = (long) high << 32 | low & 0xffffffffL;
        final long ms_since_16010101 = filetime / (1000 * 10);
        final long ms_since_19700101 = ms_since_16010101 - EPOCH_DIFF;
        return new Date(ms_since_19700101);
    }

    public static long dateToFileTime(final Date date) {
        final long ms_since_19700101 = date.getTime();
        final long ms_since_16010101 = ms_since_19700101 + EPOCH_DIFF;
        return ms_since_16010101 * 1000 * 10;
    }

    public void setDate(Date date) {
        long l = dateToFileTime(date);
        dwHighDateTime = (int) ((l >> 32L) & 0xFFFFFFFFL);
        dwLowDateTime = (int) ((l >> 0L) & 0xFFFFFFFFL);
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("dwHighDateTime", "dwLowDateTime");
    }

    static public class VAL extends FILETIME implements Structure.ByValue {
    }

    static public class REF extends FILETIME implements Structure.ByReference {
    }
}
