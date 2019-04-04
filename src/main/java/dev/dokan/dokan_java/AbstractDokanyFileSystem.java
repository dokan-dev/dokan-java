package dev.dokan.dokan_java;

import dev.dokan.dokan_java.constants.dokany.MountError;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.structure.DokanOptions;
import dev.dokan.dokan_java.structure.EnumIntegerSet;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public abstract class AbstractDokanyFileSystem implements DokanyFileSystem {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDokanyFileSystem.class);
    private static final int TIMEOUT = 3000;

    protected final FileSystemInformation fileSystemInformation;
    protected final DokanyOperations dokanyOperations;
    protected final boolean usesKernelFlagsAndCodes;

    protected Path mountPoint;
    protected String volumeName;
    protected int volumeSerialnumber;
    protected DokanOptions dokanOptions;

    private Set<String> notImplementedMethods;

    public AbstractDokanyFileSystem(FileSystemInformation fileSystemInformation, boolean usesKernelFlagsAndCodes) {
        this.fileSystemInformation = fileSystemInformation;
        this.usesKernelFlagsAndCodes = usesKernelFlagsAndCodes;
        this.dokanyOperations = new DokanyOperations();
        init(dokanyOperations);
    }

    private void init(DokanyOperations dokanyOperations) {
        notImplementedMethods = Arrays.stream(getClass().getMethods())
                .filter(method -> method.getAnnotation(NotImplemented.class) != null)
                .map(Method::getName)
                .collect(Collectors.toSet());

        AbstractDokanyFileSystem dokanyFS = this;
        if (usesKernelFlagsAndCodes) {
            if (isImplemented("zwCreateFile")) {
                dokanyOperations.setZwCreateFile(this::zwCreateFile);
            }
            if (isImplemented("cleanup")) {
                dokanyOperations.setCleanup(this::cleanup);
            }
            if (isImplemented("closeFile")) {
                dokanyOperations.setCloseFile(this::closeFile);
            }
            if (isImplemented("readFile")) {
                dokanyOperations.setReadFile(this::readFile);
            }
            if (isImplemented("writeFile")) {
                dokanyOperations.setWriteFile(this::writeFile);
            }
            if (isImplemented("flushFileBuffer")) {
                dokanyOperations.setFlushFileBuffers(this::flushFileBuffers);
            }
            if (isImplemented("getFileInformation")) {
                dokanyOperations.setGetFileInformation(this::getFileInformation);
            }
            if (isImplemented("findFiles")) {
                dokanyOperations.setFindFiles(this::findFiles);
            }
            if (isImplemented("findFilesWithPattern")) {
                dokanyOperations.setFindFilesWithPattern(this::findFilesWithPattern);
            }
            if (isImplemented("setFileAttributes")) {
                dokanyOperations.setSetFileAttributes(this::setFileAttributes);
            }
            if (isImplemented("setFileTime")) {
                dokanyOperations.setSetFileTime(this::setFileTime);
            }
            if (isImplemented("deleteFile")) {
                dokanyOperations.setDeleteFile(this::deleteFile);
            }
            if (isImplemented("deleteDirectory")) {
                dokanyOperations.setDeleteDirectory(this::deleteDirectory);
            }
            if (isImplemented("moveFile")) {
                dokanyOperations.setMoveFile(this::moveFile);
            }
            if (isImplemented("setEndOfFile")) {
                dokanyOperations.setSetEndOfFile(this::setEndOfFile);
            }
            if (isImplemented("setAllocationSize")) {
                dokanyOperations.setSetAllocationSize(this::setAllocationSize);
            }
            if (isImplemented("lockFile")) {
                dokanyOperations.setLockFile(this::lockFile);
            }
            if (isImplemented("unlockFile")) {
                dokanyOperations.setUnlockFile(this::unlockFile);
            }
            if (isImplemented("getDiskFreeSpace")) {
                dokanyOperations.setGetDiskFreeSpace(this::getDiskFreeSpace);
            }
            if (isImplemented("getVolumeInformation")) {
                dokanyOperations.setGetVolumeInformation(this::getVolumeInformation);
            }
            if (isImplemented("mounted")) {
                dokanyOperations.setMounted(this::mounted);
            }
            if (isImplemented("unmounted")) {
                dokanyOperations.setUnmounted(this::unmounted);
            }
            if (isImplemented("getFileSecurity")) {
                dokanyOperations.setGetFileSecurity(this::getFileSecurity);
            }
            if (isImplemented("setFileSecurity")) {
                dokanyOperations.setSetFileSecurity(this::setFileSecurity);
            }
            if (isImplemented("fillWin32FindData")) {
                //TODO: write meaningful comment why there is no method binding
            }
            if (isImplemented("findStreams")) {
                dokanyOperations.setFindStreams(this::findStreams);
            }
        } else {
            if (isImplemented("zwCreateFile")) {
                dokanyOperations.setZwCreateFile((rawPath, securityContext, rawDesiredAccess, rawFileAttributes, rawShareAccess, rawCreateDisposition, rawCreateOptions, dokanFileInfo) -> {
                    IntByReference createDisposition = new IntByReference();
                    IntByReference desiredAccess = new IntByReference();
                    IntByReference fileAttributeFlags = new IntByReference();
                    NativeMethods.DokanMapKernelToUserCreateFileFlags(rawDesiredAccess, rawFileAttributes, rawCreateOptions, rawCreateDisposition, desiredAccess, fileAttributeFlags, createDisposition);
                    return DokanyUtils.ntStatusFromWin32ErrorCode(this.zwCreateFile(rawPath, securityContext, desiredAccess.getValue(), fileAttributeFlags.getValue(), rawShareAccess, createDisposition.getValue(), rawCreateOptions, dokanFileInfo));
                });
            }
            if (isImplemented("cleanup")) {
                dokanyOperations.setCleanup(this::cleanup); //cleanup returns void, so no further preprocessing is necessary
            }
            if (isImplemented("closeFile")) {
                dokanyOperations.setCloseFile(this::closeFile);
            }
            if (isImplemented("readFile")) {
                dokanyOperations.setReadFile((rawPath, rawBuffer, rawBufferLength, rawReadLength, rawOffset, dokanyFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.readFile(rawPath, rawBuffer, rawBufferLength, rawReadLength, rawOffset, dokanyFileInfo)));
            }
            if (isImplemented("writeFile")) {
                dokanyOperations.setWriteFile((rawPath, rawBuffer, rawNumberOfBytesToWrite, rawNumberOfWritesWritten, rawOffset, dokanyFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.writeFile(rawPath, rawBuffer, rawNumberOfBytesToWrite, rawNumberOfWritesWritten, rawOffset, dokanyFileInfo)));
            }
            if (isImplemented("flushFileBuffer")) {
                dokanyOperations.setFlushFileBuffers((rawPath, dokanyFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.flushFileBuffers(rawPath, dokanyFileInfo)));
            }
            if (isImplemented("getFileInformation")) {
                dokanyOperations.setGetFileInformation((rawPath, handleFileInfo, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.getFileInformation(rawPath, handleFileInfo, dokanFileInfo)));
            }
            if (isImplemented("findFiles")) {
                dokanyOperations.setFindFiles((rawPath, rawFillWin32FindData, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.findFiles(rawPath, rawFillWin32FindData, dokanFileInfo)));
            }
            if (isImplemented("findFilesWithPattern")) {
                dokanyOperations.setFindFilesWithPattern(((rawPath, rawFillWin32FindData, pattern, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.findFilesWithPattern(rawPath, rawFillWin32FindData, pattern, dokanFileInfo))));
            }
            if (isImplemented("setFileAttributes")) {
                dokanyOperations.setSetFileAttributes((rawPath, rawAttributes, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.setFileAttributes(rawPath, rawAttributes, dokanFileInfo)));
            }
            if (isImplemented("setFileTime")) {
                dokanyOperations.setSetFileTime((rawPath, rawCreatonTime, rawLastAccessTime, rawLastWriteTime, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.setFileTime(rawPath, rawCreatonTime, rawLastAccessTime, rawLastWriteTime, dokanFileInfo)));
            }
            if (isImplemented("deleteFile")) {
                dokanyOperations.setDeleteFile((rawPath, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.deleteFile(rawPath, dokanFileInfo)));
            }
            if (isImplemented("deleteDirectory")) {
                dokanyOperations.setDeleteDirectory((rawPath, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.deleteDirectory(rawPath, dokanFileInfo)));
            }
            if (isImplemented("moveFile")) {
                dokanyOperations.setMoveFile((rawPath, rawNewFileName, rawReplaceIfExisting, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.moveFile(rawPath, rawNewFileName, rawReplaceIfExisting, dokanFileInfo)));
            }
            if (isImplemented("setEndOfFile")) {
                dokanyOperations.setSetEndOfFile((rawPath, rawByteOffset, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.setEndOfFile(rawPath, rawByteOffset, dokanFileInfo)));
            }
            if (isImplemented("setAllocationSize")) {
                dokanyOperations.setSetAllocationSize((rawPath, rawLength, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.setAllocationSize(rawPath, rawLength, dokanFileInfo)));
            }
            if (isImplemented("lockFile")) {
                dokanyOperations.setLockFile((rawPath, rawByteOffset, rawLength, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.lockFile(rawPath, rawByteOffset, rawLength, dokanFileInfo)));
            }
            if (isImplemented("unlockFile")) {
                dokanyOperations.setUnlockFile((rawPath, rawByteOffset, rawLength, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.unlockFile(rawPath, rawByteOffset, rawLength, dokanFileInfo)));
            }
            if (isImplemented("getDiskFreeSpace")) {
                dokanyOperations.setGetDiskFreeSpace((freeBytesAvailable, totalNumberOfBytes, totalNumberOfFreeBytes, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.getDiskFreeSpace(freeBytesAvailable, totalNumberOfBytes, totalNumberOfFreeBytes, dokanFileInfo)));
            }
            if (isImplemented("getVolumeInformation")) {
                dokanyOperations.setGetVolumeInformation((rawVolumeNameBuffer, rawVolumeNameSize, rawVolumeSerialNumber, rawMaximumComponentLength, rawFileSystemFlags, rawFileSystemNameBuffer, rawFileSystemNameSize, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.getVolumeInformation(rawVolumeNameBuffer, rawVolumeNameSize, rawVolumeSerialNumber, rawMaximumComponentLength, rawFileSystemFlags, rawFileSystemNameBuffer, rawFileSystemNameSize, dokanFileInfo)));
            }
            if (isImplemented("mounted")) {
                dokanyOperations.setMounted((dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.mounted(dokanFileInfo)));
            }
            if (isImplemented("unmounted")) {
                dokanyOperations.setUnmounted((dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.unmounted(dokanFileInfo)));
            }
            if (isImplemented("getFileSecurity")) {
                dokanyOperations.setGetFileSecurity((rawPath, rawSecurityInformation, rawSecurityDescriptor, rawSecurityDescriptorLength, rawSecurityDescriptorLengthNeeded, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.getFileSecurity(rawPath, rawSecurityInformation, rawSecurityDescriptor, rawSecurityDescriptorLength, rawSecurityDescriptorLengthNeeded, dokanFileInfo)));
            }
            if (isImplemented("setFileSecurity")) {
                dokanyOperations.setSetFileSecurity((rawPath, rawSecurityInformation, rawSecurityDescriptor, rawSecurityDescriptorLength, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.setFileSecurity(rawPath, rawSecurityInformation, rawSecurityDescriptor, rawSecurityDescriptorLength, dokanFileInfo)));
            }
            if (isImplemented("fillWin32FindData")) {
                //TODO: write meaningful comment why there is no method binding
            }
            if (isImplemented("findStreams")) {
                dokanyOperations.setFindStreams((rawPath, fillWin32FindStreamData, dokanFileInfo) -> DokanyUtils.ntStatusFromWin32ErrorCode(this.findStreams(rawPath, fillWin32FindStreamData, dokanFileInfo)));
            }

        }
    }

    private boolean isImplemented(String funcName) {
        return !notImplementedMethods.contains(funcName);
    }

    @Override
    public void mount(Path mountPoint, String volumeName, int volumeSerialnumber, boolean blocking, long timeout, long allocationUnitSize, long sectorSize, String UNCName, short threadCount, EnumIntegerSet<MountOption> options) {
        this.dokanOptions = new DokanOptions(mountPoint.toString(), threadCount, options, UNCName, timeout, allocationUnitSize, sectorSize);
        this.mountPoint = mountPoint;
        this.volumeName = volumeName; //TODO: add checks for mountPoint, volumeName and volumeSerialNumber
        this.volumeSerialnumber = volumeSerialnumber;

        LOG.info("Detected Dokan Kernel Driver Version is {}.", NativeMethods.DokanDriverVersion());
        LOG.info("Detected Dokan Version is {}.", NativeMethods.DokanVersion());
        try {
            int mountStatus;

            if (DokanyUtils.canHandleShutdownHooks()) {
                Runtime.getRuntime().addShutdownHook(new Thread(this::unmount));
            }
            if (blocking) {
                mountStatus = execMount(dokanOptions);
            } else {
                try {
                    mountStatus = CompletableFuture
                            .supplyAsync(() -> execMount(dokanOptions))
                            .get(TIMEOUT, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    // ok
                    mountStatus = 0;
                }
            }
            if (mountStatus < 0) {
                throw new DokanyException("Error while mounting. Error message:" + MountError.fromInt(mountStatus).getDescription(), mountStatus);
            }
        } catch (UnsatisfiedLinkError err) {
            LOG.error("Unable to find dokany driver.", err);
            throw new LibraryNotFoundException(err.getMessage());
        } catch (DokanyException e) {
            LOG.warn("Unable to mount filesystem.", e);
            throw e;
        } catch (Exception e) {
            LOG.warn("Unable to mount Filesystem.", e);
            throw new DokanyException(e);
        }
    }

    /**
     * Helper constructor to easy the mounting with a lot of default values
     *
     * @param mountPoint
     * @param mountOptions
     */
    public void mount(Path mountPoint, EnumIntegerSet<MountOption> mountOptions) {
        String uncName = null;
        short threadCount = 5;
        long timeout = 3000;
        long allocationUnitSize = 4096;
        long sectorsize = 512;
        String volumeName = "DOKAN";
        int volumeSerialnumber = 30975;
        mount(mountPoint, volumeName, volumeSerialnumber, false, timeout, allocationUnitSize, sectorsize, uncName, threadCount, mountOptions);
    }

    private int execMount(DokanOptions dokanOptions) {
        return NativeMethods.DokanMain(dokanOptions, this.dokanyOperations);
    }

    @Override
    public void unmount() {
        //TODO: add check if this object is mounted at all and throw otherwise IllegalState exception
        LOG.info("Start to unmount volume {} at {} and shutdown.", this.volumeName, this.mountPoint);
        if (NativeMethods.DokanRemoveMountPoint(new WString(mountPoint.toAbsolutePath().toString()))) {
            LOG.info("Unmount operation successful.");
        } else {
            LOG.error("Unable to unmount filesystem from {}. Please use `dokanctl.exe` to unmount manually.", mountPoint);
        }
    }

    @Override
    public void close() throws Exception {
        unmount();
    }
}
