package com.dokany.java.structure;

/**
 * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa363788%28v=vs.85%29.aspx?f=255&MSPPError=-2147217396">BY_HANDLE_FILE_INFORMATION structure (MSDN)</a>
 *
 */
public class ByHandleFileInformation extends FileInfo {

	ByHandleFileInformation(final Builder builder, final int numberOfLinks, final int volumeSerialNumber) {
		super(builder);

		dwNumberOfLinks = numberOfLinks;
		dwVolumeSerialNumber = volumeSerialNumber;
	}

	public ByHandleFileInformation() {
	}
}
