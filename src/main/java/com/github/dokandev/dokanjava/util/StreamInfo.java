package com.github.dokandev.dokanjava.util;

import com.github.dokandev.dokanjava.DOKAN_OPERATIONS;

public class StreamInfo {
    public String name;
    public long length;

    public StreamInfo(String name, long length) {
        this.name = name;
        this.length = length;
    }

    public DOKAN_OPERATIONS.Win32FindStreamData toStruct() {
        DOKAN_OPERATIONS.Win32FindStreamData out = new DOKAN_OPERATIONS.Win32FindStreamData();
        out.length = length;
        this.name.getChars(0, this.name.length(), out.cFileName, 0);
        return out;
    }
}
