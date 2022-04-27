package dev.dokan.java.sample;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_DIRECTORY;

public final class Directory extends Resource {

	private static final long SIZE = 4L;

	private final List<Resource> resources;

	public Directory(String name) {
		this(name, FILE_ATTRIBUTE_DIRECTORY);
	}

	public Directory(String name, int attributes) {
		this(name, attributes, Instant.now(), Instant.now(), Instant.now());
	}

	/**
	 * Instantiates a new directory.
	 * <p>
	 * Note: Regardless of the given {@code attributes}, {@link com.sun.jna.platform.win32.WinNT#FILE_ATTRIBUTE_DIRECTORY} is always set.
	 *
	 * @param name the name of the directory
	 * @param attributes the attributes to be set on creation
	 */
	public Directory(String name, int attributes, Instant creationTime, Instant lastAccessTime, Instant lastModificationTime) {
		super(name, attributes, SIZE, creationTime, lastAccessTime, lastModificationTime);
		this.attributes |= FILE_ATTRIBUTE_DIRECTORY;
		this.resources = new ArrayList<>();
	}

	@Override
	public Type getType() {
		return Type.DIR;
	}

	void addResource(Resource r) {
		resources.add(r);
	}

	void removeResource(Resource r) {
		resources.remove(r);
	}

	public Stream<Resource> list() {
		return resources.stream();
	}

}
