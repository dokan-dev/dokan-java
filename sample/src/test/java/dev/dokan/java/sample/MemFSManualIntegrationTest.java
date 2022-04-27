package dev.dokan.java.sample;

import dev.dokan.java.DokanException;
import dev.dokan.java.DokanMount;
import dev.dokan.java.constants.MountOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class MemFSManualIntegrationTest {

	public static void main(String[] args) throws IOException, DokanException {
		try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
			var fs = new MemoryFs();

			var mount = DokanMount.create(fs)
					.withMountOptions(MountOptions.MOUNT_MANAGER | MountOptions.STDERR | MountOptions.DEBUG)
					.withTimeout(3000)
					.withSingleThreaded(true)
					.mount(Path.of("X:\\"));

			waitForUserInput(reader);
			mount.unmount();
		}
		System.out.println("Goodbye!");
	}

	private static void waitForUserInput(BufferedReader reader) throws IOException {
		System.out.println("Please enter ONE Character to continue...");
		char exit = ' ';
		while (!Character.isAlphabetic(exit)) {
			try {
				exit = (char) reader.read();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
}
