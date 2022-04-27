package dev.dokan.java.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Pseduo-Implementation of the JDK nio.Path class.
 * The interface is not actually implemented, but for nearly all methods in the Path class exists a method with the same name.
 * TODO: Tests
 */
public class MemoryPath implements Comparable<MemoryPath>, Iterable<MemoryPath> {

	private static final String CURRENT_DIR = ".";
	private static final String PARENT_DIR = "..";
	private static final char SEPERATOR = '\\';
	private static final char MAGIC_ROOT_CHAR = '*';
	private static final String MAGIC_ROOT_STR = String.valueOf(MAGIC_ROOT_CHAR);

	static final MemoryPath ROOT = new MemoryPath(new String[]{MAGIC_ROOT_STR});

	private final String[] components;
	private final boolean isAbsolute;

	public static MemoryPath of(String path) {
		if (path.indexOf(MAGIC_ROOT_CHAR) != -1) {
			throw new IllegalArgumentException("Illegal character '*' found in path " + path);
		}

		if (path.equals(String.valueOf(SEPERATOR))) {
			return ROOT;
		}

		var components = path.trim().split(String.valueOf(SEPERATOR) + String.valueOf(SEPERATOR));
		if (path.indexOf(SEPERATOR) == 0) { //if path is empty, we must not change the first (and only) component!
			components[0] = MAGIC_ROOT_STR;
		}
		return new MemoryPath(components);
	}

	private MemoryPath(String[] splittedPath) {
		this.components = splittedPath;
		this.isAbsolute = components[0].equals(MAGIC_ROOT_STR);
	}

	public boolean isAbsolute() {
		return isAbsolute;
	}

	public MemoryPath getRoot() {
		return this.isAbsolute() ? ROOT : null;
	}

	public MemoryPath getFileName() {
		return isAbsolute() && this.equals(getRoot()) ? null : getName(getNameCount() - 1);
	}

	public MemoryPath getParent() {
		if (components.length == 1) {
			return null;
		} else {
			return new MemoryPath(Arrays.copyOf(components, components.length - 1));
		}
	}

	public int getNameCount() {
		return components.length - (isAbsolute() ? 1 : 0);
	}

	public MemoryPath getName(int index) {
		if (index < 0 || index >= getNameCount() || this.equals(ROOT)) {
			throw new IllegalArgumentException(new IndexOutOfBoundsException("Index must be between 0 and getNameCount()"));
		}
		return MemoryPath.of(components[index + (isAbsolute() ? 1 : 0)]);
	}

	public MemoryPath subpath(int beginIndex, int endIndex) {
		if (beginIndex < 0 || beginIndex >= endIndex || endIndex > getNameCount()) {
			throw new IllegalArgumentException(new IndexOutOfBoundsException("Indices must be between 0 and getNameCount(), with beginIndex smaller than endIndex."));
		}
		return new MemoryPath(Arrays.copyOfRange(components, beginIndex + (isAbsolute() ? 1 : 0), endIndex + (isAbsolute() ? 1 : 0)));
	}

	public boolean startsWith(MemoryPath other) {
		int length = Math.min(other.components.length, this.components.length);
		for (int i = 0; i < length; i++) {
			if (!this.components[i].equals(other.components[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean startsWith(String other) {
		return startsWith(MemoryPath.of(other));
	}

	public boolean endsWith(MemoryPath other) {
		int length = Math.min(other.components.length, this.components.length);
		for (int i = 0; i < length; i++) {
			if (!this.components[this.components.length - 1 - i].equals(other.components[other.components.length - 1 - i])) {
				return false;
			}
		}
		return true;
	}

	public boolean endsWith(String other) {
		return endsWith(MemoryPath.of(other));
	}

	public MemoryPath normalize() {
		List<Integer> indicesToIgnore = new ArrayList<>();
		List<Integer> indicesToUse = new ArrayList<>();
		for (int ix = 0; ix < components.length; ix++) {
			if (components[ix].equals(CURRENT_DIR)) {
				//ignore all "."
				indicesToIgnore.add(ix);
			} else if (components[ix].equals(PARENT_DIR)) {
				//ignore nearly all "path\\.."
				int ix2 = ix;
				do {
					ix2--;
				} while (indicesToIgnore.contains(ix2));

				if (!(components[ix2].equals(PARENT_DIR) || components[ix2].equals(MAGIC_ROOT_STR))) {
					indicesToIgnore.add(ix);
					indicesToIgnore.add(ix2);
				} else {
					indicesToUse.add((ix));
				}
			} else {
				indicesToUse.add(ix);
			}
		}

		//copy all not-ignored components
		String[] newComponents = new String[indicesToUse.size()];
		for (int j = 0; j < indicesToUse.size(); j++) {
			int jx = indicesToUse.get(j);
			newComponents[jx] = components[jx];
		}

		return new MemoryPath(newComponents);
	}

	public MemoryPath resolve(MemoryPath other) {
		if (other.isAbsolute()) {
			return other;
		}
		if (other.components[0].isEmpty()) {
			return this;
		}

		var joinedComponents = Arrays.copyOf(this.components, this.components.length + other.components.length);
		System.arraycopy(other.components, 0, joinedComponents, this.components.length, other.components.length);
		return new MemoryPath(joinedComponents);
	}

	public MemoryPath resolve(String other) {
		return resolve(MemoryPath.of(other));
	}

	public MemoryPath resolveSibling(MemoryPath other) {
		if (other == null) throw new NullPointerException();
		MemoryPath parent = getParent();
		return (parent == null) ? other : parent.resolve(other);
	}

	public MemoryPath resolveSibling(String other) {
		return resolveSibling(MemoryPath.of(other));
	}

	public MemoryPath relativize(MemoryPath other) {
		if (other.isAbsolute() != isAbsolute()) {
			throw new IllegalArgumentException("Both paths must be either relative or absolute");
		}
		throw new UnsupportedOperationException(); //TODO
	}

	/*
	@Override
	public URI toUri() {
		return null;
	}
	 */

	public MemoryPath toAbsolutePath() {
		if (isAbsolute()) {
			return this;
		} else {
			var absCompontens = new String[components.length + 1];
			System.arraycopy(components, 0, absCompontens, 1, components.length);
			return new MemoryPath(absCompontens);
		}
	}

	//-- comparable --

	@Override
	public int compareTo(MemoryPath other) {
		int length = Math.min(this.components.length, other.components.length);

		for (int i = 0; i < length; i++) {
			int result = (components[i].compareTo(other.components[i]));
			if (result != 0) {
				return result;
			}
		}

		if (components.length < other.components.length) {
			return -1;
		} else if (components.length > other.components.length) {
			return +1;
		} else {
			return 0;
		}
	}

	//-- iterable --
	@Override
	public Iterator<MemoryPath> iterator() {
		return new Iterator<>() {
			private int i = 0;

			@Override
			public boolean hasNext() {
				return (i < getNameCount());
			}

			@Override
			public MemoryPath next() {
				if (i < getNameCount()) {
					MemoryPath result = getName(i);
					i++;
					return result;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	// -- object --
	@Override
	public boolean equals(Object other) {
		if (other instanceof MemoryPath o) {
			if (o.getNameCount() == this.getNameCount()) {
				for (int i = 0; i < getNameCount(); i++) {
					if (!this.components[i].equals(o.components[i])) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash((Object[]) components); //TODO: correct?
	}

	@Override
	public String toString() {
		if (this.equals(ROOT)) {
			return String.valueOf(SEPERATOR);
		} else if (isAbsolute()) {
			return SEPERATOR + Arrays.stream(components).skip(1).collect(Collectors.joining(String.valueOf(SEPERATOR)));
		} else {
			return Arrays.stream(components).collect(Collectors.joining(String.valueOf(SEPERATOR)));
		}
	}
}
