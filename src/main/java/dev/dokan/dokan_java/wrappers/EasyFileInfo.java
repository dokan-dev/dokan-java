package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.structure.ByHandleFileInformation;
import dev.dokan.dokan_java.structure.EnumIntegerSet;

import java.nio.file.Path;

public class EasyFileInfo extends AbstractFileInfo {

	private final Path path;

	private int volumeSerialNumber;
	private long fileIndex;
	private int numberOfLinks;

	public EasyFileInfo(Path path) {
		this(path, FileAttribute.NORMAL.getMask());
	}

	public EasyFileInfo(Path path, EnumIntegerSet<FileAttribute> attributes) {
		super(attributes);
		this.path = path;
	}

	public EasyFileInfo(Path path, int attributes) {
		super(attributes);
		this.path = path;
	}

	public Path getPath() {
		return this.path;
	}

	public int getVolumeSerialNumber() {
		return this.volumeSerialNumber;
	}

	public void setVolumeSerialNumber(int volumeSerialNumber) {
		this.volumeSerialNumber = volumeSerialNumber;
	}

	public long getFileIndex() {
		return this.fileIndex;
	}

	public void setFileIndex(long fileIndex) {
		this.fileIndex = fileIndex;
	}

	public int getNumberOfLinks() {
		return this.numberOfLinks;
	}

	public void setNumberOfLinks(int numberOfLinks) {
		this.numberOfLinks = numberOfLinks;
	}

	public ByHandleFileInformation toByHandleFileInformation() {
		return toByHandleFileInformation(this.path);
	}

	public ByHandleFileInformation toByHandleFileInformation(Path pathOverride) {
		ByHandleFileInformation info = new ByHandleFileInformation(pathOverride,
			getFlags(),
			getCreationTime(),
			getLastAccessTime(),
			getLastWriteTime(),
			this.volumeSerialNumber,
			getFileSize(),
			this.fileIndex);
		info.nNumberOfLinks = this.numberOfLinks;

		return info;
	}

	public void copyTo(ByHandleFileInformation byHandleFileInformation) {
		copyTo(byHandleFileInformation, this.path);
	}

	public void copyTo(ByHandleFileInformation byHandleFileInformation, Path pathOverride) {
		toByHandleFileInformation(pathOverride).copyTo(byHandleFileInformation); //That's not the most efficient way to do this, but it's less prone to human error
	}
}