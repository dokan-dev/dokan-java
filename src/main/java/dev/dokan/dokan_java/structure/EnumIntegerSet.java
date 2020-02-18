package dev.dokan.dokan_java.structure;

import dev.dokan.dokan_java.constants.EnumInteger;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;

import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * Used to store multiple {@link EnumInteger} values such as {@link FileSystemFlag} and {@link MountOption}.
 *
 * @param <T> Type of {@link EnumInteger}
 */
public final class EnumIntegerSet<T extends Enum<T> & EnumInteger> extends AbstractSet<T> {

    private final EnumSet<T> elements;

    public EnumIntegerSet(final Class<T> clazz) {
        this.elements = EnumSet.noneOf(clazz);
    }

    @SafeVarargs
    public EnumIntegerSet(T first, T... others) {
        this.elements = EnumSet.of(first, others);
    }

    /**
     * Creates a set of enumIntegers which corresponds to the bit flag given as an 32bit integer.
     * <p>
     * The type of the set is the enum class of the input array
     *
     * @param intValue the integer value of the combined bitflag
     * @param allEnumValues all possible values of this enumInteger
     * @param <T> enum type of the array implementing the EnumInteger interface
     * @return a set of enumInteger values whose mask were active in the intValue
     */
    public static <T extends Enum<T> & EnumInteger> EnumIntegerSet<T> enumSetFromInt(final int intValue, final T[] allEnumValues) {
        EnumIntegerSet<T> elements = new EnumIntegerSet<>(allEnumValues[0].getDeclaringClass());
        int remainingValues = intValue;
        for (T current : allEnumValues) {
            int mask = current.getMask();

            if ((remainingValues & mask) == mask) {
                elements.add(current);
                remainingValues -= mask;
            }
        }
        return elements;
    }

    @SafeVarargs
    public final void add(T item, T... items) {
        if (item == null) {
            throw new IllegalArgumentException("Adding null is not allowed.");
        } else {
            elements.add(item);
            for (final T it : items) {
                if (it != null) {
                    elements.add(it);
                }
            }
        }
    }

    public int toInt() {
        int toReturn = 0;
        for (final T current : elements) {
            toReturn |= current.getMask();
        }
        return toReturn;
    }

    @Override
    public boolean add(final T e) {
        return elements.add(e);
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return "EnumIntegerSet(elements=" + this.elements + ")";
    }

}
