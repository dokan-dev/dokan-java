package dev.dokan.java.sample;

import java.nio.ByteBuffer;
import java.time.Instant;

import static com.sun.jna.platform.win32.WinNT.FILE_ATTRIBUTE_NORMAL;

public final class File extends Resource {

	private static final int CAPACITY = 1024; //1 kiBi
	private static final byte [] OVERWRITE = new byte[CAPACITY];

	private ByteBuffer content;

	public File (String name) {
		this(name, FILE_ATTRIBUTE_NORMAL);
	}

	public File (String name, int attributes) {
		this(name, attributes, Instant.now(), Instant.now(), Instant.now());
	}

	public File(String name, int attributes, Instant creationTime, Instant lastAccessTime, Instant lastModificationTime) {
		super(name, attributes, CAPACITY, creationTime, lastAccessTime, lastModificationTime);
		this.content = ByteBuffer.allocate(CAPACITY); //1 kiBi
	}

	@Override
	public Type getType() {
		return Type.FILE;
	}

	public void wipe() {
		content.clear();
		content.put(OVERWRITE);
		content.clear();
	}

	@Override
	public long getSize() {
		return content.limit();
	}

}
