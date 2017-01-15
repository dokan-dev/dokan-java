package com.dokany.java.structure;

import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;

import com.dokany.java.DokanyUtils;
import com.dokany.java.constants.EnumInteger;

/**
 * Used to store multiple enum values such as {@link com.dokany.java.constants.FileSystemFeature} and {@link com.dokany.java.constants.MountOption}.
 *
 * @param <T> Type of enum
 */
public class EnumIntegerSet<T extends Enum<T>> extends AbstractSet<T> {

	private final EnumSet<T> values;

	@SafeVarargs
	public EnumIntegerSet(final T... items) {
		if (DokanyUtils.isNull(items) || (items.length < 1) || DokanyUtils.isNull(items[0])) {
			throw new IllegalArgumentException("items array cannot be empty");
		}

		values = EnumSet.noneOf(items[0].getDeclaringClass());

		for (final T item : items) {
			if (DokanyUtils.isNull(item)) {
				throw new IllegalArgumentException("Item cannot be null");
			}
			if (!EnumInteger.class.isInstance(item)) {
				throw new IllegalArgumentException("Items must all implement EnumInteger");
			}
			values.add(item);
		}
	}

	public final int toInt() {
		int toReturn = -1;
		for (final T current : values) {
			// Already checked (in constructor) to ensure only objects which implement EnumInteger are stored in values
			final EnumInteger enumInt = (EnumInteger) current;
			if (toReturn == -1) {
				toReturn = enumInt.getVal();
			} else {
				toReturn = toReturn | enumInt.getVal();
			}
		}
		return toReturn;
	}

	@Override
	public Iterator<T> iterator() {
		return values.iterator();
	}

	@Override
	public int size() {
		return values.size();
	}
}
