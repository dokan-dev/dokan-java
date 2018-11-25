package com.dokan.java.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumIntegerTest {

    @Test
    void FromIntegerOverEnumToInteger() {
        int val = 4096;
        Assertions.assertEquals(val, EnumInteger.enumFromInt(val, TestEnum.values()).getMask());
    }

    @Test
    void NotExistingIntThrowsException() {
        int val = 2;
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumInteger.enumFromInt(val, TestEnum.values()));
    }

    enum TestEnum implements EnumInteger {
        A(1),
        B(4096);

        private final int val;

        TestEnum(int val) {
            this.val = val;
        }

        @Override
        public int getMask() {
            return val;
        }
    }

}
