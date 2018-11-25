package com.dokan.java.structure;

import com.dokan.java.constants.EnumInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumIntegerSetTest {

    @Test
    void fromIntOverEnumIntegerSetToInt() {
        int val = TestEnum.A.getMask() | TestEnum.B.getMask() | TestEnum.C.getMask();
        EnumIntegerSet<TestEnum> testSet = EnumIntegerSet.enumSetFromInt(val, TestEnum.values());
        Assertions.assertFalse(testSet.contains(TestEnum.D));
        Assertions.assertTrue(testSet.contains(TestEnum.A));
        Assertions.assertTrue(testSet.contains(TestEnum.B));
        Assertions.assertTrue(testSet.contains(TestEnum.C));
        Assertions.assertEquals(val, testSet.toInt());
    }

    enum TestEnum implements EnumInteger {
        A(0x01),
        B(0x08),
        C(0x110),
        D(0x200);

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
