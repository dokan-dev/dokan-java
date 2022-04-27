package dev.dokan.java;

import dev.dokan.java.constants.MountOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//TODO: Disable, if Dokany not installed
public class DokanMountTest {

	private static final Path TEST_DRIVE_LETTER = Path.of("X:\\");

	private DokanFileSystem fs = new MinimalDokanAdapter();
	private DokanMount mount;

	@Test
	public void testMountToDirSuccessful(@TempDir Path tmp) throws DokanException, IOException {
		var fsSpy = Mockito.spy(fs);
		var mnt = Files.createDirectory(tmp.resolve("mnt"));
		this.mount = DokanMount.create(fsSpy)
				.withMountOptions(MountOptions.STDERR)
				.mount(mnt);
		mount.unmount();

		Mockito.verify(fsSpy, Mockito.times(1)).mounted(Mockito.any(), Mockito.any());
		Mockito.verify(fsSpy, Mockito.times(1)).unmounted(Mockito.any());
	}

	@Test
	@DisabledIf("driveLetterReserved")
	public void testMountToDriveSuccessful() throws DokanException {
		var fsSpy = Mockito.spy(fs);
		this.mount = DokanMount.create(fsSpy)
				.withMountOptions(MountOptions.STDERR)
				.mount(TEST_DRIVE_LETTER);
		mount.unmount();

		Mockito.verify(fsSpy, Mockito.times(1)).mounted(Mockito.any(), Mockito.any());
		Mockito.verify(fsSpy, Mockito.times(1)).unmounted(Mockito.any());
	}

	private static boolean driveLetterReserved() {
		return !Files.notExists(TEST_DRIVE_LETTER);
	}

}
