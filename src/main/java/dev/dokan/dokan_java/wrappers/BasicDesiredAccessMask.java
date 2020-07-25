package dev.dokan.dokan_java.wrappers;


import dev.dokan.dokan_java.constants.microsoft.accessmaskflags.BasicAccessMaskFlag;
import dev.dokan.dokan_java.masking.MaskValueSet;

import java.util.concurrent.atomic.AtomicInteger;


public class BasicDesiredAccessMask {

    private final static int ACCESS_MASK_VALUES = MaskValueSet.of(BasicAccessMaskFlag.values()).intValue();

    protected final AtomicInteger accessMask;

    public BasicDesiredAccessMask(MaskValueSet<BasicAccessMaskFlag> accessMask) {
        this(accessMask.intValue());
    }

    public BasicDesiredAccessMask(int accessMask) {
        this.accessMask = new AtomicInteger(accessMask);
    }

    public MaskValueSet<BasicAccessMaskFlag> getBasicRights() {
        return BasicAccessMaskFlag.maskValueSet(this.accessMask.get());
    }

    public int getBasicAccessMask() {
        return getAccessMask() & ACCESS_MASK_VALUES;
    }

    public void setBasicAccessMask(int accessMask) {
        setAccessMask(accessMask & ACCESS_MASK_VALUES);
    }

    public int getAccessMask() {
        return this.accessMask.get();
    }

    public void setAccessMask(int accessMask) {
        this.accessMask.set(accessMask);
    }

    public boolean getFlag(BasicAccessMaskFlag flag) {
        return getFlag(flag.maskingValue());
    }

    public boolean getFlag(int flag) {
        ensureSingleFlag(flag);

        return (this.accessMask.get() & flag) != 0;
    }

    public boolean setFlag(BasicAccessMaskFlag flag) {
        return updateFlag(flag, true);
    }

    public boolean unsetFlag(BasicAccessMaskFlag flag) {
        return updateFlag(flag, false);
    }

    public boolean updateFlag(BasicAccessMaskFlag flag, boolean value) {
        return updateFlag(flag.maskingValue(), value);
    }

    public boolean updateFlag(int flag, boolean value) {
        ensureSingleFlag(flag);

        int prev = this.accessMask.getAndUpdate(current -> current & (value ? flag : ~flag));
        return (prev & flag) != 0;
    }

    protected void ensureSingleFlag(int flag) {
        if (!isSingleFlag(flag)) {
            throw new IllegalArgumentException("Result for more than one flag is undefined!");
        }
    }

    protected boolean isSingleFlag(int flag) {
        /*
         * This may be more performant, but it doesn't really matter
         * Integer.highestOneBit(flag) != Integer.lowestOneBit(flag)
         */
        return Integer.bitCount(flag) == 1;
    }
}