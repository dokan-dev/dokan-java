package dev.dokan.dokan_java.conv;


public interface MaskValueEnum extends EnumInteger {

    static <T extends Enum<T> & MaskValueEnum> MaskValueSet<T> getSetFromInt(final int intValue, final T[] allEnumValues) {
        return MaskValueSet.getSetFromInt(intValue, allEnumValues);
    }

    default int getMaskValue() {
        return intValue();
    }
}