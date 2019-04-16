package dev.dokan.dokan_java.examples;

import dev.dokan.dokan_java.FileSystemInformation;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;
import dev.dokan.dokan_java.structure.EnumIntegerSet;
import org.slf4j.impl.SimpleLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirListingFSTest {

    static {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "info");
        System.setProperty(SimpleLogger.LOG_FILE_KEY, "System.out");
        System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
        System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "HH:mm:ss:SSS");
    }

    public static void main(String[] args) {
        System.out.println("Starting Dokany MirrorFS");

        Path mountPoint = Paths.get("M:\\mnt\\");
        EnumIntegerSet mountOptions = new EnumIntegerSet<>(MountOption.class);
        mountOptions.add(MountOption.DEBUG_MODE, MountOption.STD_ERR_OUTPUT, MountOption.WRITE_PROTECTION, MountOption.CURRENT_SESSION);

        EnumIntegerSet<FileSystemFlag> fsFeatures = new EnumIntegerSet<>(FileSystemFlag.class);
        fsFeatures.add(FileSystemFlag.READ_ONLY_VOLUME, FileSystemFlag.CASE_PRESERVED_NAMES);
        FileSystemInformation fsInfo = new FileSystemInformation(fsFeatures);
        DirListingFileSystem fs = new DirListingFileSystem(Paths.get("M:\\test"), fsInfo);

        try {
            fs.mount(mountPoint, mountOptions);
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
