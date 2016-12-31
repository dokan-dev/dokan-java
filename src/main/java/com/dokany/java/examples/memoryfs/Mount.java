package com.dokany.java.examples.memoryfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyDriver;

/**
 * Mounts MemoryFS at M:\\.
 *
 */
public class Mount {
	private final static Logger LOGGER = LoggerFactory.getLogger(Mount.class);

	public static void main(final String[] args) throws Throwable {
		LOGGER.info("Starting Dokany ExampleFS");
		final DokanyDriver dokanyDriver = new DokanyDriver("M:\\", new MemoryFS());
		dokanyDriver.start();
	}
}