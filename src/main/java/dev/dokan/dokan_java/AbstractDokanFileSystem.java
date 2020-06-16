package dev.dokan.dokan_java;


import com.sun.jna.CallbackThreadInitializer;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import dev.dokan.dokan_java.constants.dokany.MountError;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.masking.MaskValueSet;
import dev.dokan.dokan_java.structure.DokanControl;
import dev.dokan.dokan_java.structure.DokanOptions;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


/**
 * TODO: Add Description to this class
 */
public abstract class AbstractDokanFileSystem implements DokanFileSystem {

	private static final int TIMEOUT = 3000;
	private static final CallbackThreadInitializer DEFAULT_CALLBACK_THREAD_INITIALIZER = new CallbackThreadInitializer();

	protected final FileSystemInformation fileSystemInformation;
	protected final DokanOperations dokanOperations;
	protected Path mountPoint;
	protected String volumeName;
	protected int volumeSerialnumber;
	protected DokanOptions dokanOptions;

	private final AtomicBoolean isMounted;
	private Set<String> notImplementedMethods;

	public AbstractDokanFileSystem(FileSystemInformation fileSystemInformation) {
		this.fileSystemInformation = fileSystemInformation;
		this.isMounted = new AtomicBoolean(false);
		this.dokanOperations = new DokanOperations();
		init(dokanOperations);
	}

	private void init(DokanOperations dokanOperations) {
		notImplementedMethods = Arrays.stream(getClass().getMethods())
				.filter(method -> method.getAnnotation(NotImplemented.class) != null)
				.map(Method::getName)
				.collect(Collectors.toSet());

		if (isImplemented("zwCreateFile")) {
			dokanOperations.setZwCreateFile(this::zwCreateFile);
			Native.setCallbackThreadInitializer(dokanOperations.ZwCreateFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("cleanup")) {
			dokanOperations.setCleanup(this::cleanup);
			Native.setCallbackThreadInitializer(dokanOperations.Cleanup, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("closeFile")) {
			dokanOperations.setCloseFile(this::closeFile);
			Native.setCallbackThreadInitializer(dokanOperations.CloseFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("readFile")) {
			dokanOperations.setReadFile(this::readFile);
			Native.setCallbackThreadInitializer(dokanOperations.ReadFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("writeFile")) {
			dokanOperations.setWriteFile(this::writeFile);
			Native.setCallbackThreadInitializer(dokanOperations.WriteFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("flushFileBuffer")) {
			dokanOperations.setFlushFileBuffers(this::flushFileBuffers);
			Native.setCallbackThreadInitializer(dokanOperations.FlushFileBuffers, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("getFileInformation")) {
			dokanOperations.setGetFileInformation(this::getFileInformation);
			Native.setCallbackThreadInitializer(dokanOperations.GetFileInformation, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("findFiles")) {
			dokanOperations.setFindFiles(this::findFiles);
			Native.setCallbackThreadInitializer(dokanOperations.FindFiles, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("findFilesWithPattern")) {
			dokanOperations.setFindFilesWithPattern(this::findFilesWithPattern);
			Native.setCallbackThreadInitializer(dokanOperations.FindFilesWithPattern, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("setFileAttributes")) {
			dokanOperations.setSetFileAttributes(this::setFileAttributes);
			Native.setCallbackThreadInitializer(dokanOperations.SetFileAttributes, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("setFileTime")) {
			dokanOperations.setSetFileTime(this::setFileTime);
			Native.setCallbackThreadInitializer(dokanOperations.SetFileTime, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("deleteFile")) {
			dokanOperations.setDeleteFile(this::deleteFile);
			Native.setCallbackThreadInitializer(dokanOperations.DeleteFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("deleteDirectory")) {
			dokanOperations.setDeleteDirectory(this::deleteDirectory);
			Native.setCallbackThreadInitializer(dokanOperations.DeleteDirectory, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("moveFile")) {
			dokanOperations.setMoveFile(this::moveFile);
			Native.setCallbackThreadInitializer(dokanOperations.MoveFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("setEndOfFile")) {
			dokanOperations.setSetEndOfFile(this::setEndOfFile);
			Native.setCallbackThreadInitializer(dokanOperations.SetEndOfFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("setAllocationSize")) {
			dokanOperations.setSetAllocationSize(this::setAllocationSize);
			Native.setCallbackThreadInitializer(dokanOperations.SetAllocationSize, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("lockFile")) {
			dokanOperations.setLockFile(this::lockFile);
			Native.setCallbackThreadInitializer(dokanOperations.LockFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("unlockFile")) {
			dokanOperations.setUnlockFile(this::unlockFile);
			Native.setCallbackThreadInitializer(dokanOperations.UnlockFile, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("getDiskFreeSpace")) {
			dokanOperations.setGetDiskFreeSpace(this::getDiskFreeSpace);
			Native.setCallbackThreadInitializer(dokanOperations.GetDiskFreeSpace, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("getVolumeInformation")) {
			dokanOperations.setGetVolumeInformation(this::getVolumeInformation);
			Native.setCallbackThreadInitializer(dokanOperations.GetVolumeInformation, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("mounted")) {
			dokanOperations.setMounted(this::mounted);
			Native.setCallbackThreadInitializer(dokanOperations.Mounted, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("unmounted")) {
			dokanOperations.setUnmounted(this::unmounted);
			Native.setCallbackThreadInitializer(dokanOperations.Unmounted, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("getFileSecurity")) {
			dokanOperations.setGetFileSecurity(this::getFileSecurity);
			Native.setCallbackThreadInitializer(dokanOperations.GetFileSecurity, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("setFileSecurity")) {
			dokanOperations.setSetFileSecurity(this::setFileSecurity);
			Native.setCallbackThreadInitializer(dokanOperations.SetFileSecurity, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
		if (isImplemented("findStreams")) {
			dokanOperations.setFindStreams(this::findStreams);
			Native.setCallbackThreadInitializer(dokanOperations.FindStreams, DEFAULT_CALLBACK_THREAD_INITIALIZER);
		}
	}

	private boolean isImplemented(String funcName) {
		return !notImplementedMethods.contains(funcName);
	}

	/**
	 * The general mount method. If the underlying system supports shutdown hooks, one is installed in case the JVM is shutting down and the filesystem is still mounted.
	 *
	 * @param mountPoint path pointing to an empty directory or unused drive letter
	 * @param volumeName the displayed name of the volume (only important when a drive letter is used as a mount point)
	 * @param volumeSerialnumber the serial number of the volume (only important when a drive letter is used as a mount point)
	 * @param blocking if true the mount and further file system calls are foreground operations and thus will block this thread. To unmount the device you have to use the dokanctl.exe tool.
	 * @param timeout timeout after which a not processed file system call is canceled and the volume is unmounted
	 * @param allocationUnitSize the size of the smallest allocatable space in bytes
	 * @param sectorSize the sector size
	 * @param UNCName
	 * @param threadCount the number of threads spawned for processing filesystem calls
	 * @param options an {@link MaskValueSet} containing {@link MountOption}s
	 */
	@Override
	public final synchronized void mount(Path mountPoint, String volumeName, int volumeSerialnumber, boolean blocking, long timeout, long allocationUnitSize, long sectorSize, String UNCName, short threadCount, MaskValueSet<MountOption> options) {
		this.dokanOptions = new DokanOptions(mountPoint.toString(), threadCount, options, UNCName, timeout, allocationUnitSize, sectorSize);
		this.mountPoint = mountPoint;
		this.volumeName = volumeName;
		this.volumeSerialnumber = volumeSerialnumber;

		try {
			int mountStatus;

			if (DokanUtils.canHandleShutdownHooks()) {
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
				isMounted.set(true);
			}
			if (mountStatus < 0) {
				throw new RuntimeException("Negative result of mount operation. Code" + mountStatus + " -- " + MountError.fromInt(mountStatus).getDescription());
			}
		} catch (UnsatisfiedLinkError | Exception e) {
			throw new MountFailedException("Unable to mount filesystem.", e);
		}
	}

	/**
	 * Additional method for easy mounting with a lot of default values
	 *
	 * @param mountPoint
	 * @param mountOptions
	 */
	public void mount(Path mountPoint, MaskValueSet<MountOption> mountOptions) {
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
		return DokanNativeMethods.DokanMain(dokanOptions, this.dokanOperations);
	}

	@Override
	public final synchronized void unmount() {
		if (!volumeIsStillMounted()) {
			isMounted.set(false);
		}

		if (isMounted.get()) {
			if (DokanNativeMethods.DokanRemoveMountPoint(new WString(mountPoint.toAbsolutePath().toString()))) {
				isMounted.set(false);
			} else {
				throw new UnmountFailedException("Unmount of " + volumeName + "(" + mountPoint + ") failed. Try again, shut down JVM or use `dokanctl.exe to unmount manually.");
			}
		}
	}

	private boolean volumeIsStillMounted() {
		char[] mntPtCharArray = mountPoint.toAbsolutePath().toString().toCharArray();
		IntByReference lengthPointer = new IntByReference();
		Pointer startOfList = DokanNativeMethods.DokanGetMountPointList(false, lengthPointer);

		@Unsigned int length = lengthPointer.getValue();
		List<DokanControl> list = DokanControl.getDokanControlList(startOfList, length);
		// It is not enough that the entry.MountPoint contains the actual mount point. It also has to ends afterwards.
		boolean mountPointInList = list.stream().anyMatch(entry ->
				Arrays.equals(entry.MountPoint, 12, 12 + mntPtCharArray.length, mntPtCharArray, 0, mntPtCharArray.length)
						&& (entry.MountPoint.length == 12 + mntPtCharArray.length || entry.MountPoint[12 + mntPtCharArray.length] == '\0'));
		DokanNativeMethods.DokanReleaseMountPointList(startOfList);
		return mountPointInList;
	}


	@Override
	public void close() {
		unmount();
	}

}
