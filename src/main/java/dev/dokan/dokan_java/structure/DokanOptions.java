package dev.dokan.dokan_java.structure;


import com.sun.jna.Structure;
import com.sun.jna.WString;
import dev.dokan.dokan_java.DokanNativeMethods;
import dev.dokan.dokan_java.Unsigned;
import dev.dokan.dokan_java.UnsignedNumbers;
import dev.dokan.dokan_java.constants.dokany.MountOption;
import dev.dokan.dokan_java.masking.MaskValueSet;

import java.util.Arrays;
import java.util.List;


/**
 * Dokan mount options used to describe Dokan device behavior.
 *
 * @see <a href="https://dokan-dev.github.io/dokany-doc/html/struct_d_o_k_a_n___o_p_t_i_o_n_s.html">Dokany Documentation of PDOKAN_OPTIONS</a>
 */
public class DokanOptions extends Structure implements Structure.ByReference {

	/**
	 * Version of the Dokan features requested (version "123" is equal to Dokan version 1.2.3).
	 */
	@Unsigned
	public short Version = DokanNativeMethods.getMinimumRequiredDokanVersion();

	/**
	 * Number of threads to be used internally by Dokan library. More thread will handle more events at the same time.
	 */
	@Unsigned
	public short ThreadCount;

	/**
	 * Features enable for the mount. It is a combination of {@link MountOption} masks.
	 */
	@Unsigned
	public int Options;

	/**
	 * FileSystem can store anything here
	 */
	@Unsigned
	public long GlobalContext = 0L;

	/**
	 * Mount point. It can be a drive letter like \"M:\\\" or a folder path \"C:\\mount\\dokany\" on a NTFS partition.
	 */
	public WString MountPoint;

	/**
	 * UNC name used for the Network Redirector.
	 *
	 * @see <a href="https://docs.microsoft.com/de-de/windows-hardware/drivers/ifs/support-for-unc-naming-and-mup">Support for UNC Naming</a>
	 */
	public WString UNCName;

	/**
	 * Max timeout in milliseconds of each request before Dokan gives up to wait events to complete.
	 */
	@Unsigned
	public int Timeout;

	/**
	 * Allocation Unit Size of the volume. This will affect the file size.
	 */
	@Unsigned
	public int AllocationUnitSize;

	/**
	 * Sector Size of the volume. This will affect then file size.
	 */
	@Unsigned
	public int SectorSize;

	public DokanOptions() {

	}

	public DokanOptions(String mountPoint, @Unsigned short threadCount, MaskValueSet<MountOption> mountOptions, String uncName, @Unsigned int timeout, @Unsigned int allocationUnitSize, @Unsigned int sectorSize) {
		this(mountPoint, threadCount, mountOptions.intValue(), uncName, timeout, allocationUnitSize, sectorSize);
	}

	public DokanOptions(String mountPoint, @Unsigned short threadCount, @Unsigned int mountOptions, String uncName, @Unsigned int timeout, @Unsigned int allocationUnitSize, @Unsigned int sectorSize) {
		MountPoint = new WString(mountPoint);
		ThreadCount = threadCount;
		Options = mountOptions;
		if (uncName != null) {
			UNCName = new WString(uncName);
		} else {
			UNCName = null;
		}
		Timeout = timeout;
		AllocationUnitSize = allocationUnitSize;
		SectorSize = sectorSize;
	}

	public MaskValueSet<MountOption> getMountOptions() {
		return MaskValueSet.maskValueSet(this.Options, MountOption.values());
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("Version", "ThreadCount", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize");
	}

	@Override
	public String toString() {
		return String.format("DeviceOptions(Version=%s, ThreadCount=%s, Options=%s, mountOptions=%s, GlobalContext=%s, MountPoint=%s, UNCName=%s, Timeout=%s, AllocationUnitSize=%s, SectorSize=%s)",
				UnsignedNumbers.toUnsignedString(this.Version),
				UnsignedNumbers.toUnsignedString(this.ThreadCount),
				UnsignedNumbers.toUnsignedString(this.Options),
				this.getMountOptions(),
				UnsignedNumbers.toUnsignedString(this.GlobalContext),
				this.MountPoint,
				this.UNCName,
				UnsignedNumbers.toUnsignedString(this.Timeout),
				UnsignedNumbers.toUnsignedString(this.AllocationUnitSize),
				UnsignedNumbers.toUnsignedString(this.SectorSize));
	}
}
