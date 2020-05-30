package dev.dokan.dokan_java.masking;


public interface MaskValueEnum extends EnumInteger {

    default int maskingValue() {
        return intValue();
    }
}