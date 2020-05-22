package dev.dokan.dokan_java.wrappers;

import com.sun.jna.platform.win32.WinBase;
import dev.dokan.dokan_java.DokanUtils;
import dev.dokan.dokan_java.constants.EnumInteger;
import dev.dokan.dokan_java.constants.microsoft.FileAttribute;
import dev.dokan.dokan_java.constants.microsoft.MicrosoftReparsePointTag;
import dev.dokan.dokan_java.structure.EnumIntegerSet;

import java.util.Objects;
import java.util.function.Function;

public class FindFileInfo extends AbstractFileInfo {

	private static final int MAX_ALTERNATIVE_NAME_LENGTH = 14;
	private static final FileAttribute REPARSE_POINT_FLAG = FileAttribute.REPARSE_POINT;
	private static final String REPARSE_POINT_NOT_SET_MESSAGE =
		"Reparse point tags are disabled on this File(-Handle); Flag \"FileAttribute.REPARSE_POINT\" not set";
	private final int RESERVED_1_FIELD_DEFAULT = 0;

	private int reparsePointTag;
	//	private int reserved_1; //Not yet specified by Microsoft
	private String fileName;
	private String alternativeName;

	public FindFileInfo(EnumIntegerSet<FileAttribute> attributes) {
		super(attributes);
	}

	public FindFileInfo(int attributes) {
		super(attributes);
	}

	public int getReparsePointTagValueLeniently() {
		return getReparsePointFlag() ? this.reparsePointTag : 0;
	}

	public int getReparsePointTagValue() {
		tryReparsePoint();
		return this.reparsePointTag;
	}

	public void setReparsePointTagValue(int reparsePointTagValue) {
		tryReparsePoint();
		this.reparsePointTag = reparsePointTagValue;
	}

	public MicrosoftReparsePointTag getMSReparsePointTag() {
		return getReparsePointTag(MicrosoftReparsePointTag.values());
	}

	public void setMSReparsePointTag(MicrosoftReparsePointTag tag) {
		setReparsePointTag(tag);
	}

	public <T extends EnumInteger> T getReparsePointTag(T[] possibleValues) {
		return EnumInteger.enumFromInt(getReparsePointTagValue(), possibleValues);
	}

	public <T extends EnumInteger> void setReparsePointTag(T tag) {
		setReparsePointTagValue(tag.getMask());
	}

	public <T extends Enum<?>> T getReparsePointTag(Function<Integer, T> parser) {
		return parser.apply(getReparsePointTagValue());
	}

	public <T extends Enum<?>> void setReparsePointTag(T tag, Function<T, Integer> parser) {
		setReparsePointTagValue(parser.apply(tag));
	}

	public boolean getReparsePointFlag() {
		return getFlag(REPARSE_POINT_FLAG);
	}

	public boolean setReparsePointFlag() {
		return updateReparsePointFlag(true);
	}

	public boolean unsetReparsePointFlag() {
		return updateReparsePointFlag(false);
	}

	public boolean updateReparsePointFlag(boolean value) {
		/*
		 * The previous tag value is not discarded when updating the flag to false.
		 * But: The tag value will only be present in the WIN32_FIND_DATA if the flag is set to true.
		 * The following code could change that behavior.
		 *
		 * if(value && (prevValueFromUpdate != value)) { setReparsePointTagValue(0); }
		 */
		return updateFlag(REPARSE_POINT_FLAG, value);
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = Objects.requireNonNullElse(fileName, "");
	}

	public String getAlternativeName() {
		return this.alternativeName;
	}

	public void setAlternativeName(String alternativeName) {
		alternativeName = Objects.requireNonNullElse(alternativeName, "");
		if(alternativeName.length() > 14) {
			throw new IllegalArgumentException("Alternative name must not be longer than 14 chars");
		}
		this.alternativeName = alternativeName;
	}

	public String setAlternativeNameLeniently(String alternativeName) {
		alternativeName = Objects.requireNonNullElse(alternativeName, "");
		this.alternativeName = alternativeName.substring(0, MAX_ALTERNATIVE_NAME_LENGTH);

		return this.alternativeName;
	}

	public WinBase.WIN32_FIND_DATA toWIN32_FIND_DATA() {
		long fileSize = getFileSize();
		int fileSizeHigh = (int) (fileSize >> 32 & 0xffffffffL);
		int fileSizeLow = (int) (fileSize & 0xffffffffL);

		return new WinBase.WIN32_FIND_DATA(getFlags(),
			DokanUtils.toFILETIME(getCreationTime()),
			DokanUtils.toFILETIME(getLastAccessTime()),
			DokanUtils.toFILETIME(getLastWriteTime()),
			fileSizeHigh,
			fileSizeLow,
			getReparsePointTagValueLeniently(),
			this.RESERVED_1_FIELD_DEFAULT,
			this.fileName.toCharArray(),
			this.alternativeName.toCharArray());
	}

	private void tryReparsePoint() {
		if(!getReparsePointFlag()) {
			throw new IllegalStateException(REPARSE_POINT_NOT_SET_MESSAGE);
		}
	}
}