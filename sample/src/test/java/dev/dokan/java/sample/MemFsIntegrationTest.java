package dev.dokan.java.sample;

import com.sun.jna.platform.win32.WinNT;
import dev.dokan.java.DokanException;
import dev.dokan.java.DokanMount;
import dev.dokan.java.constants.MountOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class MemFsIntegrationTest {
	@TempDir
	Path tmpDir;
	Path mountPoint;

	ResourceManager resourceManager;
	MemoryFs memfsSpy;
	DokanMount.Mounter mounter;

	@BeforeEach
	public void init() throws IOException {
		this.mountPoint = tmpDir.resolve("mnt");
		Files.createDirectory(mountPoint);

		this.resourceManager = new ResourceManager();
		var memfs = new MemoryFs(resourceManager);
		this.memfsSpy = Mockito.spy(memfs);
		this.mounter = DokanMount.create(memfsSpy) //
				.withMountOptions(MountOptions.MOUNT_MANAGER | MountOptions.STDERR) //
				.withTimeout(3000) //
				.withSingleThreaded(true);
	}

	@AfterEach
	public void cleanup() throws IOException {
		resourceManager.clear();
		Files.deleteIfExists(mountPoint);
	}

	@Test
	@DisplayName("After successful file creation, file exists")
	public void testFileCreation() throws DokanException {
		var file = mountPoint.resolve("test.file");
		var interalFilePath = MemoryPath.of("\\test.file");

		try (var mount = mounter.mount(mountPoint)) {
			Assertions.assertDoesNotThrow(() -> Files.createFile(file));
		}

		//We could actually mock the resourceManager here
		Assertions.assertTrue(resourceManager.exists(interalFilePath), "File creation failed");
	}

	@Test
	@DisplayName("After successful dir creation, dir exists")
	public void testDirCreation() throws DokanException {
		var dir = mountPoint.resolve("testDir");
		var interalDirPath = MemoryPath.of("\\testDir");

		try (var mount = mounter.mount(mountPoint)) {
			Assertions.assertDoesNotThrow(() -> Files.createDirectory(dir));
		}

		Assertions.assertTrue(resourceManager.exists(interalDirPath), "Resource creation failed.");
		Resource r = resourceManager.get(interalDirPath);
		Assertions.assertInstanceOf(Directory.class, r);
	}

	@Test
	@DisplayName("Non existing file is correctly determined")
	public void testNonExistingFileIsCorrectlyIndicated() throws DokanException {
		var resource = mountPoint.resolve("test.resource");

		final boolean result;
		try (var mount = mounter.mount(mountPoint)) {
			result = Files.notExists(resource);
		}
		Assertions.assertTrue(result, "Filesystem reports existence of non-existing resource");
	}

	@Test
	@DisplayName("Existing file is correctly indicated")
	public void testExistenceOfExistingFile() throws DokanException {
		var file = mountPoint.resolve("test.file");
		var internalFilePath = MemoryPath.ROOT.resolve("test.file");
		resourceManager.put(internalFilePath, new File("test.file", 0));

		final boolean result;
		try (var mount = mounter.mount(mountPoint)) {
			result = Files.exists(file);
		}
		Assertions.assertTrue(result, "MemFs indicates existing file as non-existing");
	}

	@Test
	@DisplayName("Existing dir is correctly indicated")
	public void testExistenceOfExistingDir() throws DokanException {
		var dir = mountPoint.resolve("testDir");
		var internalFilePath = MemoryPath.ROOT.resolve("testDir");
		resourceManager.put(internalFilePath, new Directory("testDir", 0));

		final boolean result;
		try (var mount = mounter.mount(mountPoint)) {
			result = Files.exists(dir);
		}
		Assertions.assertTrue(result, "MemFs indicates existing dir as non-existing");
	}

	@Test
	@DisplayName("Empty non-root dir returns empty listing")
	public void testEmptyNonRootDirListing() throws DokanException, IOException {
		resourceManager.put(MemoryPath.of("\\bar"), new Directory("bar", 0x00));
		var path = mountPoint.resolve("bar");
		final List<Path> result;
		try (var mount = mounter.mount(mountPoint)) {
			result = Files.list(path).collect(Collectors.toList());
		}

		Assertions.assertTrue(result.isEmpty(), "Listing of empty non-root dir contains unexpected elements");
	}

	@Test
	@DisplayName("Existing resources appear in dir listing")
	public void testDirListing() throws DokanException, IOException {
		resourceManager.put(MemoryPath.of("\\foo"), new File("foo", 0x00));
		resourceManager.put(MemoryPath.of("\\bar"), new Directory("bar", 0x00));

		final List<Path> result;
		try (var mount = mounter.mount(mountPoint)) {
			result = Files.list(mountPoint).collect(Collectors.toList());
		}

		Assertions.assertTrue(2 <= result.size());
		Assertions.assertTrue(result.stream().anyMatch(p -> p.endsWith("foo")));
		Assertions.assertTrue(result.stream().anyMatch(p -> p.endsWith("bar")));
	}

	@Test
	@DisplayName("Reading correct attribute values from existing, hidden, archived file")
	public void testReadAttributesHiddenArchiveFile() throws DokanException, IOException {
		Instant creationTime = Instant.parse("2007-12-03T10:15:30.00Z");
		Instant lastAccessTime = Instant.parse("2020-01-03T18:05:44.10Z");
		Instant lastModificationTime = Instant.parse("1970-07-20T23:55:01.00Z");
		resourceManager.put(MemoryPath.of("\\foo"), //
				new File("foo", //
						WinNT.FILE_ATTRIBUTE_HIDDEN | WinNT.FILE_ATTRIBUTE_ARCHIVE, //
						creationTime, //
						lastAccessTime, //
						lastModificationTime) //
		);
		var path = mountPoint.resolve("foo");

		final DosFileAttributes attr;
		try (var mount = mounter.mount(mountPoint)) {
			attr = Files.readAttributes(path, DosFileAttributes.class);
		}

		Assertions.assertTrue(attr.isRegularFile());
		Assertions.assertFalse(attr.isDirectory());
		Assertions.assertFalse(attr.isOther());
		Assertions.assertTrue(attr.isHidden());
		Assertions.assertFalse(attr.isSystem());
		Assertions.assertFalse(attr.isReadOnly());
		Assertions.assertTrue(attr.isArchive());
		Assertions.assertEquals(0, attr.creationTime().compareTo(FileTime.from(creationTime)));
		Assertions.assertEquals(0, attr.lastAccessTime().compareTo(FileTime.from(lastAccessTime)));
		Assertions.assertEquals(0, attr.lastModifiedTime().compareTo(FileTime.from(lastModificationTime)));
	}

	@Test
	@DisplayName("Reading correct attribute values from existing directory")
	public void testReadAttributesSystemDirectory() throws DokanException, IOException {
		resourceManager.put(MemoryPath.of("\\baz"), new Directory("baz", WinNT.FILE_SYSTEM_DIR));
		var path = mountPoint.resolve("baz");

		final DosFileAttributes attr;
		try (var mount = mounter.mount(mountPoint)) {
			attr = Files.readAttributes(path, DosFileAttributes.class);
		}

		Assertions.assertTrue(attr.isDirectory());
		Assertions.assertFalse(attr.isRegularFile());
		Assertions.assertFalse(attr.isOther());
		Assertions.assertFalse(attr.isHidden());
		Assertions.assertTrue(attr.isSystem());
		Assertions.assertFalse(attr.isReadOnly());
		Assertions.assertFalse(attr.isArchive());
	}

}
