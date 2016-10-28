package com.github.dokandev.dokanjava;

@SuppressWarnings("unused")
public class Example {
    static public void main(String[] args) {
        System.out.println("hello");
        System.out.println(Dokan.DokanVersion());
        System.out.println(Dokan.DokanDriverVersion());
        Dokan.DokanUnmount('M');
        Dokan.DokanMain(DokanOptions.DebugMode | DokanOptions.StderrOutput, "M:\\", 10000, new DokanOperations() {

        });
        //System.out.println(NativeMethods.INSTANCE.DokanRemoveMountPoint(new WString("M")));
        //DokanNative.INSTANCE.printf("hello %s", "world");
    }
}
