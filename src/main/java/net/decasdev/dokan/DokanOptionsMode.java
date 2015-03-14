package net.decasdev.dokan;

import java.util.EnumSet;
import java.util.Set;

/**
 * Author : Saine Imad
 * Class : DokanOptionsMode
 * Description :
 */
public class DokanOptionsMode{

    public enum Mode{

        DEBUG(0x00000001),
        STD_ERR(0x00000002),
        ALT_STREAM(0x00000004),
        KEEP_ALIVE(0x00000008),
        NETWORK_DRIVE(0x00000010),
        REMOVABLE_DRIVE(0x00000020);
        
        private int value;

        Mode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Translates a numeric status code into a Set of StatusFlag enums
     * @param value
     * @return EnumSet representing a documents status
     */
    public static EnumSet<Mode> getFlags(int value)
    {
        EnumSet<Mode> flags = EnumSet.noneOf(Mode.class);

        for (Mode flag: Mode.values()) {
            long flagValue = flag.getValue();
            if ((flagValue & value) == flagValue)
                flags.add(flag);
        }

        return flags;
    }


    /**
     * Translates a set of flags enums into a numeric status code
     * @param flags if statusFlags
     * @return numeric representation of the document status
     */
    public static long getStatusValue(Set<Mode> flags)
    {
        long value=0;
        for (Mode flag: flags) {
            value |= flag.getValue();
        }
        return value;
    }

    public static String toString(int value) {
        String result = new String("");
        Set<Mode> flags = getFlags(value);

        for (Mode flag: flags) {
            result += flag.toString()+ " | ";
        }

        return result;
    }





}
