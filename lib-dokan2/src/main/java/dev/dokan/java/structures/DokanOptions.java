package dev.dokan.java.structures;

import dev.dokan.java.constants.MountOptions;
import dev.dokan.java.nativeannotations.Boolean;
import dev.dokan.java.nativeannotations.EnumSet;
import dev.dokan.java.nativeannotations.Unsigned;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinNT;

import java.nio.file.Path;

@Structure.FieldOrder({"Version", "SingleThread", "Options", "GlobalContext", "MountPoint", "UNCName", "Timeout", "AllocationUnitSize", "SectorSize", "VolumeSecurityDescriptorLength", "VolumeSecurityDescriptor"})
public class DokanOptions extends Structure implements Structure.ByReference {

	private static final int VOLUME_SECURITY_DESCRIPTOR_MAX_SIZE = 1024 * 16;
	/**
	 * Version of the Dokan features requested without dots (version "123" is equal to Dokan version 1.2.3).
	 * TODO: What happens if the requested version does not exist?
	 */
	@Unsigned
	public volatile short Version;

	/**
	 * Only use a single thread to process events. This is highly not recommended as can easily create a bottleneck.
	 */
	@Boolean
	public volatile byte SingleThread;

	/**
	 * Features enabled for the mount. See {@link MountOptions}
	 */
	@EnumSet
	public volatile int Options;

	/**
	 * FileSystem can store anything here.
	 *
	 * TODO: maybe make this non-volatile
	 */
	@Unsigned
	public volatile long GlobalContext;

	/**
	 * Mount point. It can be a driver letter like "M:\" or a folder path "C:\mount\dokan" on a NTFS partition.
	 */
	//LPCWSTR
	public volatile WString MountPoint;

	/**
	 * UNC Name for the Network Redirector
	 * \see <a href="https://msdn.microsoft.com/en-us/library/windows/hardware/ff556761(v=vs.85).aspx">Support for UNC Naming</a>
	 */
	//LPCWSTR
	public volatile WString UNCName;

	/**
	 * Max timeout in milliseconds of each request before Dokan gives up to wait events to complete.
	 * A timeout request is a sign that the userland implementation is no longer able to properly manage requests in time.
	 * The driver will therefore unmount the device when a timeout trigger in order to keep the system stable.
	 * The default timeout value is 15 seconds.
	 */
	@Unsigned
	public volatile int Timeout;

	/**
	 * Allocation Unit Size of the volume. This will affect the file size.
	 */
	@Unsigned
	public volatile int AllocationUnitSize;

	/**
	 * Sector Size of the volume. This will affect the file size.
	 */
	@Unsigned
	public volatile int SectorSize;

	/**
	 * Length of the optional VolumeSecurityDescriptor provided. Set 0 will disable the option.
	 */
	@Unsigned
	public volatile int VolumeSecurityDescriptorLength;

	/**
	 * Optional Volume Security descriptor. See <a href="https://docs.microsoft.com/en-us/windows/win32/api/securitybaseapi/nf-securitybaseapi-initializesecuritydescriptor">InitializeSecurityDescriptor</a>
	 */
	public volatile byte[] VolumeSecurityDescriptor = new byte[VOLUME_SECURITY_DESCRIPTOR_MAX_SIZE];

	public int getVersion() {
		return Short.toUnsignedInt(Version);
	}

	public boolean getSingleThread() {
		return SingleThread != 0;
	}

	public long getOptions() {
		return Integer.toUnsignedLong(Options);
	}

	public long getTimeout() {
		return Integer.toUnsignedLong(Timeout);
	}

	public long getAllocationUnitSize() {
		return Integer.toUnsignedLong(AllocationUnitSize);
	}

	public long getSectorSize() {
		return Integer.toUnsignedLong(SectorSize);
	}

	public long getVolumeSecurityDescriptorLength() {
		return Integer.toUnsignedLong(VolumeSecurityDescriptorLength);
	}

	public static DokanOptions.Builder create() {
		return new Builder();
	}

	public static class Builder {

		@Unsigned
		private short version = 200;
		@Boolean
		private byte singleThread = 0x00;
		@EnumSet
		private int options = MountOptions.MOUNT_MANAGER;
		@Unsigned
		private long globalContext = 0;
		private String uncName = "";
		@Unsigned
		private int timeout = 5000;
		@Unsigned
		private int allocationUnitSize = 4096; //default ntfs is 4KiBi
		@Unsigned
		private int sectorSize = 4096;
		@Unsigned
		private int volumeSecurityDescriptorLength = 0;
		private byte[] volumeSecurityDescriptor = new byte[VOLUME_SECURITY_DESCRIPTOR_MAX_SIZE];

		public DokanOptions dokanOptions = new DokanOptions();

		Builder() {
		}

		public Builder withSecurityDescriptor(WinNT.SECURITY_DESCRIPTOR descriptor) {
			if (descriptor.data.length > this.volumeSecurityDescriptor.length) {
				throw new IllegalArgumentException("Given security descriptor is too big");
			}
			System.arraycopy(descriptor.data,0,volumeSecurityDescriptor,0,descriptor.data.length);
			this.volumeSecurityDescriptorLength = descriptor.data.length;
			return this;
		}

		public Builder withMinorAndPatchVersion(int minorVersion, int patchVersion) {
			if( minorVersion >= 10 || minorVersion < 0) {
				throw new IllegalArgumentException("minorVersion must be a number between 0 and 9 (inclusive)");
			}
			if( patchVersion >= 10 || patchVersion < 0) {
				throw new IllegalArgumentException("patchVersion must be a number between 0 and 9 (inclusive)");
			}

			version += minorVersion*10 + patchVersion;
			return this;
		}

		public Builder withSingleThreadEnabled(boolean isSingleThreaded) {
			if (isSingleThreaded) {
				this.singleThread = 0x01;
			}
			return this;
		}

		public Builder withGlobalContext(long context) {
			this.globalContext = context;
			return this;
		}

		public Builder withUNCName(String uncName) {
			this.uncName = uncName;
			return this;
		}

		public Builder withTimeout(@Unsigned int timeout) {
			this.timeout = timeout;
			return this;
		}

		public Builder withAllocationUnitSize(@Unsigned int clustersize) {
			this.allocationUnitSize = clustersize;
			return this;
		}

		public Builder withSectorSize(@Unsigned int sectorSize) {
			this.sectorSize = sectorSize;
			return this;
		}

		public Builder withOptions(@EnumSet int options) {
			this.options = options;
			return this;
		}

		public DokanOptions build(Path mountPoint) {
			dokanOptions.writeField("Version", version);
			dokanOptions.writeField("SingleThread", singleThread);
			dokanOptions.writeField("Options", options);
			dokanOptions.writeField("GlobalContext", globalContext);
			dokanOptions.writeField("MountPoint", new WString(mountPoint.toAbsolutePath().toString()));
			dokanOptions.writeField("UNCName", new WString(uncName));
			dokanOptions.writeField("Timeout", timeout);
			dokanOptions.writeField("AllocationUnitSize", allocationUnitSize);
			dokanOptions.writeField("SectorSize", sectorSize);
			dokanOptions.writeField("VolumeSecurityDescriptorLength", volumeSecurityDescriptorLength);
			dokanOptions.writeField("VolumeSecurityDescriptor", volumeSecurityDescriptor);
			return dokanOptions;
		}
	}
}
