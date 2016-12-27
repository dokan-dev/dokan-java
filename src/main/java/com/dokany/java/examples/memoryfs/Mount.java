package com.dokany.java.examples.memoryfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dokany.java.DokanyDriver;

/**
 * Mounts MemoryFS at M:\\.
 *
 */
public class Mount {
	private final static Logger logger = LoggerFactory.getLogger(Mount.class);

	public static void main(final String[] args) throws Throwable {
		logger.info("Starting Dokany ExampleFS");
		final DokanyDriver<Node> dokanyDriver = new DokanyDriver<Node>("M:\\", new MemoryFS());
		dokanyDriver.start();
	}
}