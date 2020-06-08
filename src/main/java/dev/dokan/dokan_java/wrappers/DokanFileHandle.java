package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.constants.dokany.DokanFileInfoFlag;
import dev.dokan.dokan_java.masking.MaskValueSet;
import dev.dokan.dokan_java.structure.DokanFileInfo;
import dev.dokan.dokan_java.wrappers.mountinfo.MountInfo;
import dev.dokan.dokan_java.wrappers.mountinfo.ROMountInfo;

import java.util.concurrent.atomic.AtomicInteger;

public class DokanFileHandle {

	private static final long INVALID_HANDLE = 0L;

	private final AtomicInteger flags;
	private final long dokanContext; //DokanFileInfo tells us to "never modify". Happy to oblige
	private final ROMountInfo mountInfo;

	private long context;
	private int processId;

	public DokanFileHandle(DokanFileInfo nativeInfo) {
		this.context = nativeInfo.Context;
		this.dokanContext = nativeInfo.DokanContext;
		this.processId = nativeInfo.ProcessId;
		/*
		 * FIXME
		 * This copy is as expensive as it is useless. A reference to a stored MountInfo-Object would be far more useful.
		 * Due to bug/issue #46 this would be inherently unsafe, so it's a copy for now...
		 *
		 * See https://github.com/dokan-dev/dokan-java/issues/46
		 */
		this.mountInfo = new MountInfo(nativeInfo.DokanOpts);

		MaskValueSet<DokanFileInfoFlag> flagSet = MaskValueSet.emptySet(DokanFileInfoFlag.class);
		if(nativeInfo.deleteOnClose()) {
			flagSet.add(DokanFileInfoFlag.DELETE_ON_CLOSE);
		}
		if(nativeInfo.isDirectory()) {
			flagSet.add(DokanFileInfoFlag.IS_DIRECTORY);
		}
		if(nativeInfo.noCache()) {
			flagSet.add(DokanFileInfoFlag.NO_CACHE);
		}
		if(nativeInfo.pagingIo()) {
			flagSet.add(DokanFileInfoFlag.PAGING_IO);
		}
		if(nativeInfo.synchronousIo()) {
			flagSet.add(DokanFileInfoFlag.SYNCHRONOUS_IO);
		}
		if(nativeInfo.writeToEndOfFile()) {
			flagSet.add(DokanFileInfoFlag.WRITE_TO_END_OF_FILE);
		}
		this.flags = new AtomicInteger(flagSet.intValue());
	}

	public MaskValueSet<DokanFileInfoFlag> getFileInfo() {
		return MaskValueSet.maskValueSet(this.flags.get(), DokanFileInfoFlag.values());
	}

	public void setFileInfo(MaskValueSet<DokanFileInfoFlag> flagSet) {
		this.flags.set(flagSet.intValue());
	}

	public int getFlags() {
		return this.flags.get();
	}

	public void setFlags(int flags) {
		this.flags.set(flags);
	}

	public boolean getFlag(DokanFileInfoFlag flag) {
		return (this.flags.get() & flag.maskingValue()) != 0;
	}

	public boolean setFlag(DokanFileInfoFlag flag) {
		return updateFlag(flag, true);
	}

	public boolean unsetFlag(DokanFileInfoFlag flag) {
		return updateFlag(flag, false);
	}

	public boolean updateFlag(DokanFileInfoFlag flag, boolean value) {
		int prev = this.flags.getAndUpdate(current -> current & (value ? flag.maskingValue() : ~flag.maskingValue()));
		return (prev & flag.maskingValue()) != 0;
	}

	public long getContext() {
		return this.context;
	}

	public void setContext(long context) {
		this.context = context;
	}

	public boolean isValid() {
		return getContext() != INVALID_HANDLE;
	}

	public void invalidate() {
		setContext(INVALID_HANDLE);
	}

	public long getDokanContext() {
		return this.dokanContext;
	}

	public long getProcessId() {
		return this.processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public ROMountInfo getMountInfo() {
		return mountInfo;
	}
}