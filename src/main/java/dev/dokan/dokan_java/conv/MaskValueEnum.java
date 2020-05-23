package dev.dokan.dokan_java.conv;


public interface MaskValueEnum extends EnumInteger {

    default int getMaskValue() {
        return intValue();
    }
}