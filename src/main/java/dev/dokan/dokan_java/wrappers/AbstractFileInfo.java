package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.constants.dokan_java.DefaultFileTimePolicy;
import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.masking.MaskValueSet;

import java.nio.file.attribute.FileTime;
import java.util.concurrent.atomic.AtomicInteger;

public class AbstractFileInfo {

	//See field by same name in com.sun.jna.platform.win32.WinBase.FILETIME;
	private final static long WINDOWS_EPOCH_0 = -11644473600000L;
	private final static long UNIX_EPOCH_0 = 0;

	private final static FileTime YEAR_1601 = FileTime.fromMillis(WINDOWS_EPOCH_0);
	private final static FileTime YEAR_1970 = FileTime.fromMillis(UNIX_EPOCH_0);

	private final AtomicInteger fileAttributes;

	private FileTime creationTime;
	private FileTime lastAccessTime;
	private FileTime lastWriteTime;

	private DefaultFileTimePolicy fileTimePolicy = DefaultFileTimePolicy.INHERIT_ELSE_1970;
	private long fileSize;

	public AbstractFileInfo(MaskValueSet<FileAttribute> attributes) {
		this(attributes.isEmpty() ? FileAttribute.NORMAL.maskingValue() : attributes.intValue());
	}

	public AbstractFileInfo(int attributes) {
		this.fileAttributes = new AtomicInteger(attributes);
	}

	public int getFlags() {
		return this.fileAttributes.get();
	}

	public void setFlags(int flags) {
		this.fileAttributes.set(flags);
	}

	public MaskValueSet<FileAttribute> getFileAttributes() {
		return MaskValueSet.maskValueSet(this.fileAttributes.get(), FileAttribute.values());
	}

	public boolean getFlag(FileAttribute flag) {
		return (this.fileAttributes.get() & flag.maskingValue()) != 0;
	}

	public boolean setFlag(FileAttribute flag) {
		return updateFlag(flag, true);
	}

	public boolean unsetFlag(FileAttribute flag) {
		return updateFlag(flag, false);
	}

	public boolean updateFlag(FileAttribute flag, boolean value) {
		int prev = this.fileAttributes.getAndUpdate(current -> current & (value ? flag.maskingValue() : ~flag.maskingValue()));
		return (prev & flag.maskingValue()) != 0;
	}

	public void setTimes(long creationTime, long lastAccessTime, long lastWriteTime) {
		setCreationTime(creationTime);
		setLastAccessTime(lastAccessTime);
		setLastWriteTime(lastWriteTime);
	}

	public void setTimes(FileTime creationTime, FileTime lastAccessTime, FileTime lastWriteTime) {
		setCreationTime(creationTime);
		setLastAccessTime(lastAccessTime);
		setLastWriteTime(lastWriteTime);
	}

	public FileTime getStaticCreationTime() {
		return this.creationTime;
	}

	public FileTime getCreationTime() {
		if(this.creationTime != null) {
			return this.creationTime;
		}
		return getAlternateTime();
	}

	public void setCreationTime(FileTime creationTime) {
		this.creationTime = creationTime;
	}

	public void setCreationTime(long millis) {
		setCreationTime(FileTime.fromMillis(millis));
	}

	public FileTime getStaticLastWriteTime() {
		return this.lastWriteTime;
	}

	public FileTime getLastWriteTime() {
		if(this.lastWriteTime != null) {
			return this.lastWriteTime;
		}
		return getAlternateTime();
	}

	public void setLastWriteTime(FileTime lastWriteTime) {
		this.lastWriteTime = lastWriteTime;
	}

	public void setLastWriteTime(long millis) {
		setLastWriteTime(FileTime.fromMillis(millis));
	}

	public FileTime getStaticLastAccessTime() {
		return this.lastAccessTime;
	}

	public FileTime getLastAccessTime() {
		if(this.lastAccessTime != null) {
			return this.lastAccessTime;
		}
		return getAlternateTime();
	}

	public void setLastAccessTime(FileTime lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public void setLastAccessTime(long millis) {
		setLastAccessTime(FileTime.fromMillis(millis));
	}

	public long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	private FileTime getAlternateTime() {
		if(this.fileTimePolicy == DefaultFileTimePolicy.STATIC_YEAR_1601) {
			return YEAR_1601;
		}
		if(this.fileTimePolicy == DefaultFileTimePolicy.STATIC_YEAR_1970) {
			return YEAR_1970;
		}

		//--> Inheritance
		//#1 Priority: Last write Time
		if(this.lastWriteTime != null) {
			return this.lastWriteTime;
		}
		//#2 Priority: Creation Time
		if(this.creationTime != null) {
			return this.creationTime;
		}
		//#3 Priority: Access Time
		if(this.lastAccessTime != null) {
			return this.lastAccessTime;
		}
		//#4 Resort to default time by policy
		return this.fileTimePolicy == DefaultFileTimePolicy.INHERIT_ELSE_1601 ? YEAR_1601 : YEAR_1970;
	}
}