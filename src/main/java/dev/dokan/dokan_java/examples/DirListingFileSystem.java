package dev.dokan.dokan_java.examples;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import dev.dokan.dokan_java.DokanyFileSystemStub;
import dev.dokan.dokan_java.DokanyOperations;
import dev.dokan.dokan_java.DokanyUtils;
import dev.dokan.dokan_java.FileSystemInformation;
import dev.dokan.dokan_java.constants.EnumInteger;
import dev.dokan.dokan_java.constants.microsoft.CreateOptions;
import dev.dokan.dokan_java.constants.microsoft.CreationDisposition;
import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.constants.microsoft.Win32ErrorCodes;
import dev.dokan.dokan_java.structure.ByHandleFileInformation;
import dev.dokan.dokan_java.structure.DokanFileInfo;
import dev.dokan.dokan_java.structure.EnumIntegerSet;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * This filesystem shows the content of a given directory and it sub directories
 */
public class DirListingFileSystem extends DokanyFileSystemStub {

    private final AtomicLong handleHandler;
    private final FileStore fileStore;
    private final Path root;

    public DirListingFileSystem(Path root, FileSystemInformation fileSystemInformation) {
        super(fileSystemInformation, false);
        this.root = root;
        this.handleHandler = new AtomicLong(0);
        FileStore tmp = null;
        try {
            tmp = Files.getFileStore(this.root);
        } catch (IOException e) {
            //Log message
        }
        this.fileStore = tmp;
    }

    @Override
    public int zwCreateFile(WString rawPath, WinBase.SECURITY_ATTRIBUTES securityContext, int rawDesiredAccess, int rawFileAttributes, int rawShareAccess, int rawCreateDisposition, int rawCreateOptions, DokanFileInfo dokanFileInfo) {
        Path p = getrootedPath(rawPath);

        //the files must exist and we are read only here
        CreationDisposition openOption = EnumInteger.enumFromInt(rawCreateDisposition, CreationDisposition.values());
        if (!Files.exists(p)) {
            switch (openOption) {
                case CREATE_NEW:
                case OPEN_ALWAYS:
                case CREATE_ALWAYS:
                    return Win32ErrorCodes.ERROR_ACCESS_DENIED;
                case OPEN_EXISTING:
                case TRUNCATE_EXISTING:
                    return Win32ErrorCodes.ERROR_FILE_NOT_FOUND; //this is maybe a lot, but definitly not atomical
                default:
                    return Win32ErrorCodes.ERROR_GEN_FAILURE;
            }
        } else {
            switch (openOption) {
                case CREATE_NEW:
                    return Win32ErrorCodes.ERROR_ALREADY_EXISTS;
                case TRUNCATE_EXISTING:
                    return Win32ErrorCodes.ERROR_ACCESS_DENIED; //this is maybe a lot, but definitly not atomical
                case OPEN_ALWAYS:
                case CREATE_ALWAYS:
                case OPEN_EXISTING:
                    //NO-OP
                    break;
                default:
                    return Win32ErrorCodes.ERROR_GEN_FAILURE;
            }

        }

        //set attributes
        EnumIntegerSet<FileAttribute> fileAttrs = EnumIntegerSet.enumSetFromInt(rawFileAttributes, FileAttribute.values());
        int status = setFileAttributes(p, fileAttrs);
        if (status != Win32ErrorCodes.ERROR_SUCCESS) {
            return status;
        }

        if (Files.isDirectory(p)) {
            if ((rawCreateOptions & CreateOptions.FILE_NON_DIRECTORY_FILE) == 1) {
                return Win32ErrorCodes.ERROR_DIRECTORY;
            } else {
                dokanFileInfo.IsDirectory = 1;
            }
            //TODO
        } else {

        }

        long val = this.handleHandler.incrementAndGet();
        if (val == 0) {
            val = this.handleHandler.incrementAndGet();
        }

        dokanFileInfo.Context = val;

        return Win32ErrorCodes.ERROR_SUCCESS;
    }

    private int setFileAttributes(Path p, EnumIntegerSet<FileAttribute> fileAttrs) {
        DosFileAttributeView attrView = Files.getFileAttributeView(p, DosFileAttributeView.class);
        try {
            for (FileAttribute attr : fileAttrs) {
                switch (attr) {
                    case HIDDEN:
                        attrView.setHidden(true);
                        break;
                    case READONLY:
                        attrView.setReadOnly(true);
                        break;
                    case ARCHIVE:
                        attrView.setArchive(true);
                        break;
                    case SYSTEM:
                        attrView.setSystem(true);
                        break;
                    default:
                        //not supported
                        break;
                }
            }
            return Win32ErrorCodes.ERROR_SUCCESS;
        } catch (IOException e) {
            return Win32ErrorCodes.ERROR_WRITE_FAULT;

        }
    }

    @Override
    public void cleanup(WString rawPath, DokanFileInfo dokanFileInfo) {
        Path p = getrootedPath(rawPath);
        //nothing to do
    }

    @Override
    public void closeFile(WString rawPath, DokanFileInfo dokanFileInfo) {
        Path p = getrootedPath(rawPath);
        dokanFileInfo.Context = 0;
    }

    @Override
    public int getFileInformation(WString rawPath, ByHandleFileInformation handleFileInfo, DokanFileInfo dokanFileInfo) {
        Path p = getrootedPath(rawPath);
        if (dokanFileInfo.Context == 0) {
            return Win32ErrorCodes.ERROR_INVALID_HANDLE;
        }
        try {
            getFileInformation(p).copyTo(handleFileInfo);
            return Win32ErrorCodes.ERROR_SUCCESS;
        } catch (IOException e) {
            return Win32ErrorCodes.ERROR_READ_FAULT;
        }
    }

    private ByHandleFileInformation getFileInformation(Path p) throws IOException {
        DosFileAttributes attr = Files.readAttributes(p, DosFileAttributes.class);
        long index = 0;
        if (attr.fileKey() != null) {
            index = (long) attr.fileKey();
        }
        int fileAttr = 0;
        fileAttr |= attr.isArchive() ? WinNT.FILE_ATTRIBUTE_ARCHIVE : 0;
        fileAttr |= attr.isSystem() ? WinNT.FILE_ATTRIBUTE_SYSTEM : 0;
        fileAttr |= attr.isHidden() ? WinNT.FILE_ATTRIBUTE_HIDDEN : 0;
        fileAttr |= attr.isReadOnly() ? WinNT.FILE_ATTRIBUTE_READONLY : 0;
        fileAttr |= attr.isDirectory() ? WinNT.FILE_ATTRIBUTE_DIRECTORY : 0;
        fileAttr |= attr.isSymbolicLink() ? WinNT.FILE_ATTRIBUTE_REPARSE_POINT : 0;

        if (fileAttr == 0) {
            fileAttr |= WinNT.FILE_ATTRIBUTE_NORMAL;
        }

        return new ByHandleFileInformation(p.getFileName(), fileAttr, attr.creationTime(), attr.lastAccessTime(), attr.lastModifiedTime(), this.volumeSerialnumber, attr.size(), index);
    }

    @Override
    public int findFiles(WString rawPath, DokanyOperations.FillWin32FindData rawFillFindData, DokanFileInfo dokanFileInfo) {
        Path path = getrootedPath(rawPath);
        if (dokanFileInfo.Context == 0) {
            return Win32ErrorCodes.ERROR_INVALID_HANDLE;
        }
        try (Stream<Path> stream = Files.list(path)) {
            stream.map(p -> {
                try {
                    return getFileInformation(path.resolve(p)).toWin32FindData();
                } catch (IOException e) {
                    return null;
                }
            }).forEach(file -> {
                if (file != null) {
                    rawFillFindData.fillWin32FindData(file, dokanFileInfo);
                }
            });
            return Win32ErrorCodes.ERROR_SUCCESS;
        } catch (IOException e) {
            return Win32ErrorCodes.ERROR_READ_FAULT;
        }
    }

    @Override
    public int getDiskFreeSpace(LongByReference freeBytesAvailable, LongByReference totalNumberOfBytes, LongByReference totalNumberOfFreeBytes, DokanFileInfo dokanFileInfo) {
        if (this.fileStore == null) {
            return Win32ErrorCodes.ERROR_GEN_FAILURE;
        } else {
            try {
                freeBytesAvailable.setValue(fileStore.getUsableSpace());
                totalNumberOfBytes.setValue(fileStore.getTotalSpace());
                totalNumberOfFreeBytes.setValue(fileStore.getUnallocatedSpace());
                return Win32ErrorCodes.ERROR_SUCCESS;
            } catch (IOException e) {
                return Win32ErrorCodes.ERROR_IO_DEVICE;
            }
        }
    }

    @Override
    public int getVolumeInformation(Pointer rawVolumeNameBuffer, int rawVolumeNameSize, IntByReference rawVolumeSerialNumber, IntByReference rawMaximumComponentLength, IntByReference rawFileSystemFlags, Pointer rawFileSystemNameBuffer, int rawFileSystemNameSize, DokanFileInfo dokanFileInfo) {
        rawVolumeNameBuffer.setWideString(0L, DokanyUtils.trimStrToSize(this.volumeName, rawVolumeNameSize));
        rawVolumeSerialNumber.setValue(this.volumeSerialnumber);
        rawMaximumComponentLength.setValue(this.fileSystemInformation.getMaxComponentLength());
        rawFileSystemFlags.setValue(this.fileSystemInformation.getFileSystemFeatures().toInt());
        rawFileSystemNameBuffer.setWideString(0L, DokanyUtils.trimStrToSize(this.fileSystemInformation.getFileSystemName(), rawFileSystemNameSize));
        return Win32ErrorCodes.ERROR_SUCCESS;
    }

    private Path getrootedPath(WString rawPath) {
        String unixPath = rawPath.toString().replace('\\', '/');
        String relativeUnixPath = unixPath.startsWith("/")?
                ("/".equals(unixPath)?"":unixPath.substring(1))
                :unixPath;
        return root.resolve(relativeUnixPath);
    }
}
