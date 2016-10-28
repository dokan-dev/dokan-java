package com.github.dokandev.dokanjava.util;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"PointlessBitwiseExpression", "WeakerAccess"})
abstract public class FileTime extends Structure {
    private static final long EPOCH_DIFF = 11644473600000L;

    public int dwHighDateTime;
    public int dwLowDateTime;

    public FileTime() {
        setValue(0L);
    }

    public FileTime(long l) {
        setValue(l);
    }

    public static Date toDate(final int high, final int low) {
        final long filetime = (long) high << 32 | low & 0xffffffffL;
        final long ms_since_16010101 = filetime / (1000 * 10);
        final long ms_since_19700101 = ms_since_16010101 - EPOCH_DIFF;
        return new Date(ms_since_19700101);
    }

    public static long toFileTime(final Date date) {
        final long ms_since_19700101 = date.getTime();
        final long ms_since_16010101 = ms_since_19700101 + EPOCH_DIFF;
        return ms_since_16010101 * 1000 * 10;
    }

    public void setValue(long l) {
        dwHighDateTime = (int) ((l >> 32L) & 0xFFFFFFFFL);
        dwLowDateTime = (int) ((l >> 0L) & 0xFFFFFFFFL);
    }

    public void setDate(Date date) {
        setValue(toFileTime(date));
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("dwHighDateTime", "dwLowDateTime");
    }

    static public class VAL extends FileTime implements Structure.ByValue {
        public VAL() {
        }

        public VAL(long l) {
            super(l);
        }
    }

    static public class REF extends FileTime implements Structure.ByReference {
        public REF() {
        }

        public REF(long l) {
            super(l);
        }
    }
}
