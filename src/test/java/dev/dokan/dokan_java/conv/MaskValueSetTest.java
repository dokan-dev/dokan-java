package dev.dokan.dokan_java.conv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaskValueSetTest {

    @Test
    void fromIntOverEnumIntegerSetToInt() {
        int val = TestEnum.A.intValue() | TestEnum.B.intValue() | TestEnum.C.intValue();
        MaskValueSet<TestEnum> testSet = MaskValueSet.enumSetFromInt(val, TestEnum.values());
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
        public int intValue() {
            return val;
        }
    }

}
