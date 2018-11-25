package com.dokan.java.constants;

/**
 * An EnumInteger is an object that is represented by an 32bit integer value. TODO: Maybe make this an abstract class
 */
public interface EnumInteger {

    /**
     * Converts an 32bit integer into an object.
     * <p>
     * The class of the object implements this interface and all whished/possible values to check for a match are given as parameter.
     *
     * @param value The 32bit integer value to be converted
     * @param enumValues Array of possible objects which can be represented as a 32bit integer
     * @param <T> Class which implements the EnumInteger interface.
     * @return Object which has the same bitmask as value and is a subclass of EnumInteger
     *
     * @throws IllegalArgumentException if none of the EnumIntegers equals the given value
     */
    static <T extends EnumInteger> T enumFromInt(final int value, final T[] enumValues) {
        for (final T current : enumValues) {
            if (value == current.getMask()) {
                return current;
            }
        }
        throw new IllegalArgumentException("Invalid int value: " + value);
    }

    /**
     * Returns the 32bit integer value which represents this object.
     *
     * @return the value representing this object.
     */
    int getMask();

}
