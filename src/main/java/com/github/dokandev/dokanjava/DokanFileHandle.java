package com.github.dokandev.dokanjava;

public class DokanFileHandle {
    public String fileName;
    public DokanFileInfo info;

    public DokanFileHandle(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "DokanFileHandle{" +
                "fileName='" + fileName + '\'' +
                ", info=" + info +
                '}';
    }
}
