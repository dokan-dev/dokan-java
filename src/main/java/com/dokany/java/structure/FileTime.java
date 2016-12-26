package com.dokany.java.structure;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sun.jna.Structure;

public class FileTime extends Structure {
	private static final long EPOCH_DIFF = 11644473600000L;

	public int dwHighDateTime;
	public int dwLowDateTime;

	public FileTime(final long l) {
		setValue(l);
	}

	public FileTime(final Date date) {
		setDate(date);
	}

	public FileTime() {
		this(new Date());
	}

	public void setValue(final long l) {
		dwHighDateTime = (int) ((l >> 32L) & 0xFFFFFFFFL);
		dwLowDateTime = (int) ((l >> 0L) & 0xFFFFFFFFL);
	}

	public long getValue() {
		return (dwLowDateTime & 0xFFFFFFFFL) | ((long) dwHighDateTime << 32L);
	}

	public void setDate(final Date date) {
		setValue(toFileTime(date));
	}

	public Date getDate() {
		return toDate(dwHighDateTime, dwLowDateTime);
	}

	private Date toDate(final int high, final int low) {
		final long filetime = ((long) high << 32) | (low & 0xffffffffL);
		final long ms_since_16010101 = filetime / (1000 * 10);
		final long ms_since_19700101 = ms_since_16010101 - EPOCH_DIFF;
		return new Date(ms_since_19700101);
	}

	public static long toFileTime(final Date date) {
		final long ms_since_19700101 = date.getTime();
		final long ms_since_16010101 = ms_since_19700101 + EPOCH_DIFF;
		return ms_since_16010101 * 1000 * 10;
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("dwHighDateTime", "dwLowDateTime");
	}

	public static class VAL extends FileTime implements Structure.ByValue {
		public VAL() {
			super();
		}

		public VAL(final long l) {
			super(l);
		}

		public VAL(final Date date) {
			super(date);
		}
	}

	public static class REF extends FileTime implements Structure.ByReference {
		public REF() {
			super();
		}

		public REF(final long l) {
			super(l);
		}

		public REF(final Date date) {
			super(date);
		}
	}
}
