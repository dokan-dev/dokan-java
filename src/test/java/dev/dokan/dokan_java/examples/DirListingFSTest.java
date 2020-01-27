package dev.dokan.dokan_java.examples;

import dev.dokan.dokan_java.FileSystemInformation;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.constants.microsoft.FileSystemFlag;
import dev.dokan.dokan_java.structure.EnumIntegerSet;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirListingFSTest {


    public static void main(String[] args) {
        System.out.println("Starting Dokany MirrorFS");

        Path mountPoint = Path.of("M:\\mnt\\");
        EnumIntegerSet<MountOption> mountOptions = new EnumIntegerSet<>(MountOption.class);
        mountOptions.add(MountOption.STD_ERR_OUTPUT, MountOption.WRITE_PROTECTION, MountOption.CURRENT_SESSION);

        EnumIntegerSet<FileSystemFlag> fsFeatures = new EnumIntegerSet<>(FileSystemFlag.class);
        fsFeatures.add(FileSystemFlag.READ_ONLY_VOLUME, FileSystemFlag.CASE_PRESERVED_NAMES);
        FileSystemInformation fsInfo = new FileSystemInformation(fsFeatures);
        try (DirListingFileSystem fs = new DirListingFileSystem(Paths.get("M:\\test"), fsInfo)) {
            fs.mount(mountPoint, mountOptions);
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
