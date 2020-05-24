package dev.dokan.dokan_java.conv;


public interface MaskValueEnum extends EnumInteger {

    static <T extends Enum<T> & MaskValueEnum> T enumFromInt(final int value, final T[] enumValues) {
        return EnumInteger.enumFromInt(value, enumValues);
    }

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> getSetFromInt(final int intValue, final T[] allEnumValues) {
        return MaskValueSet.getSetFromInt(intValue, allEnumValues);
    }

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> getSetFromInt(final int intValue, final Class<T> type) {
        return MaskValueSet.getSetFromInt(intValue, type);
    }

    default int getMaskValue() {
        return intValue();
    }
}