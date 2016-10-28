package com.github.dokandev.dokanjava;

@SuppressWarnings("unused")
public class Example {
    static public void main(String[] args) {
        System.out.println("hello");
        System.out.println(Dokan.version());
        System.out.println(Dokan.driverVersion());
        Dokan.unmount('M');
        Dokan.main(DokanOptions.DebugMode | DokanOptions.StderrOutput, "M:\\", 10000, new DokanOperations() {
            @Override
            public long getUsedBytes() {
                return 256L * 1024 * 1024;
            }
        });
        //System.out.println(NativeMethods.INSTANCE.DokanRemoveMountPoint(new WString("M")));
        //DokanNative.INSTANCE.printf("hello %s", "world");
    }
}
