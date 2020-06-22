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

    /**
     * Convenience method that stands in for the missing method {@code Byte#divideUnsigned(byte, byte)}.<br>
     * This method was inspired by {@link Integer#divideUnsigned(int, int) the method of the same in Integer}<br>
     * <br>
     * <b>Description copied from {@link Integer#divideUnsigned(int, int)}</b><br>
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     *
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned.  Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument
     * @see #remainderUnsigned
     * @since 1.8
     *
     * @see Integer#divideUnsigned(int, int)
     */
    public static @Unsigned byte divideUnsigned(@Unsigned byte dividend, @Unsigned byte divisor) {
        return (byte) (Byte.toUnsignedInt(dividend) / Byte.toUnsignedInt(divisor));
    }

    /**
     * Convenience method that stands in for the missing method {@code Short#divideUnsigned(short, short)}.<br>
     * This method was inspired by {@link Integer#divideUnsigned(int, int) the method of the same in Integer}<br>
     * <br>
     * <b>Description copied from {@link Integer#divideUnsigned(int, int)}</b><br>
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     *
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned.  Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument
     * @see #remainderUnsigned
     * @since 1.8
     *
     * @see Integer#divideUnsigned(int, int)
     */
    public static @Unsigned short divideUnsigned(@Unsigned short dividend, @Unsigned short divisor) {
        return (short) (Short.toUnsignedInt(dividend) / Short.toUnsignedInt(divisor));
    }

    /**
     * Convenience method that delegates to {@link Integer#divideUnsigned(int, int)}.<br>
     * <br>
     * <b>Description copied from {@link Integer#divideUnsigned(int, int)}</b><br>
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     *
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned.  Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument
     * @see #remainderUnsigned
     * @since 1.8
     *
     * @see Integer#divideUnsigned(int, int)
     */
    public static @Unsigned int divideUnsigned(@Unsigned int dividend, @Unsigned int divisor) {
        return Integer.divideUnsigned(dividend, divisor);
    }

    /**
     * Convenience method that delegates to {@link Long#divideUnsigned(long, long)}.<br>
     * <br>
     * <b>Description copied from {@link Long#divideUnsigned(long, long)}</b><br>
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     *
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned.  Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument
     * @see #remainderUnsigned
     * @since 1.8
     *
     * @see Long#divideUnsigned(long, long)
     */
    public static @Unsigned long divideUnsigned(@Unsigned long dividend, @Unsigned long divisor) {
        return Long.divideUnsigned(dividend, divisor);
    }

    public static @Unsigned byte remainderUnsigned(@Unsigned byte dividend, @Unsigned byte divisor) {
        return (byte) (Byte.toUnsignedInt(dividend) % Byte.toUnsignedInt(divisor));
    }

    public static @Unsigned short remainderUnsigned(@Unsigned short dividend, @Unsigned short divisor) {
        return (short) (Short.toUnsignedInt(dividend) % Short.toUnsignedInt(divisor));
    }

    public static @Unsigned int remainderUnsigned(@Unsigned int dividend, @Unsigned int divisor) {
        return Integer.remainderUnsigned(dividend, divisor);
    }

    public static @Unsigned long remainderUnsigned(@Unsigned long dividend, @Unsigned long divisor) {
        return Long.remainderUnsigned(dividend, divisor);
    }

    public static int compareUnsigned(byte x, byte y) {
        return Byte.compareUnsigned(x, y);
    }

    public static int compareUnsigned(short x, short y) {
        return Short.compareUnsigned(x, y);
    }

    public static int compareUnsigned(int x, int y) {
        return Integer.compareUnsigned(x, y);
    }

    public static int compareUnsigned(long x, long y) {
        return Long.compareUnsigned(x, y);
    }
}