package dev.dokan.dokan_java;


public class UnsignedNumbers {

    public static String toUnsignedString(@Unsigned byte value) {
        return toUnsignedString(Byte.toUnsignedInt(value));
    }

    public static String toUnsignedString(@Unsigned short value) {
        return toUnsignedString(Short.toUnsignedInt(value));
    }

    public static String toUnsignedString(@Unsigned int value) {
        return Integer.toUnsignedString(value);
    }

    public static String toUnsignedString(@Unsigned long value) {
        return Long.toUnsignedString(value);
    }

}