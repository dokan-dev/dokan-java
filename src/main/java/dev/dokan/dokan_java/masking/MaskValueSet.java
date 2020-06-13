package dev.dokan.dokan_java.masking;

import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

/**
 * Used to store multiple {@link MaskValueEnum} values such as {@link FileSystemFlag} and {@link MountOption}.
 *
 * @param <T> Type of {@link EnumInteger}
 */
public interface MaskValueSet<T extends Enum<T> & MaskValueEnum> extends Set<T>, IntegerConvertible {

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> emptySet(Class<T> clazz) {
        return new MaskValueSetImpl<>(clazz);
    }

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> allOf(Class<T> clazz) {
        return new MaskValueSetImpl<T>(clazz.getEnumConstants());
    }

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> copyOf(Collection<T> collection) {
        if(collection instanceof MaskValueSet) {
            return new MaskValueSetImpl<T>((MaskValueSet<T>) collection);
        }
        return new MaskValueSetImpl<T>(collection);
    }

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> of(T[] values) {
        return new MaskValueSetImpl<T>(values);
    }

    @SafeVarargs
    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> of(T first, T... others) {
        return new MaskValueSetImpl<>(first, others);
    }

    /**
     * Creates a set of MaskValueEnums which corresponds to the bit flag given as an 32bit integer.
     * <p>
     * The type of the set is the enum class of the input array
     *
     * @param intValue the integer value of the combined bitflag
     * @param allEnumValues all possible values of this MaskValueEnum
     * @param <T> enum type of the array implementing the MaskValueEnum interface
     * @return a set of MaskValueEnum values whose mask were set in the intValue
     */
    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> maskValueSet(final int intValue, final T[] allEnumValues) {
        MaskValueSet<T> elements = new MaskValueSetImpl<>(allEnumValues[0].getDeclaringClass());
        int remainingValues = intValue;
        for (T current : allEnumValues) {
            int mask = current.intValue();

            if ((remainingValues & mask) == mask) {
                elements.add(current);
                remainingValues -= mask;
            }
        }
        return elements;
    }

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> maskValueSet(final int intValue, final Class<T> type) {
        return maskValueSet(intValue, type.getEnumConstants());
    }

    void add(T... items);

    EnumSet<T> elements();

    MaskValueSet<T> clone();

}