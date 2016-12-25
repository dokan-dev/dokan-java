package com.dokany.java.examples;

import com.dokany.java.DokanyDriver;

public class Mount {
	public static void main(final String[] args) throws Throwable {
		System.out.println("Starting Dokany ExampleFS");

		final DokanyDriver<Node> dokanyDriver = new DokanyDriver<Node>("M:\\", new ExampleFS());

		dokanyDriver.start();
	}
}