/*******************************************************************************
 * Copyright(c) 2011-1012 Ubeeko
 * All rights reserved.
 *
 * Created: 25/05/12
 * Author: Ivan Frain
 ******************************************************************************/

package com.github.dokandev.dokanjava;

import java.util.EnumSet;

import org.junit.Assert;
import org.junit.Test;

import com.github.dokandev.dokanjava.FileAccess;

public class FileAccessTest {

	@Test
	public void getFlagsTest() {
		int value = 0x80;
		EnumSet<FileAccess.FileAccessFlags> flags = FileAccess.getFlags(value);
		Assert.assertTrue(flags.contains(FileAccess.FileAccessFlags.GENERIC_READ));
		Assert.assertFalse(flags.contains(FileAccess.FileAccessFlags.GENERIC_WRITE));

		value = 0x80 + 0x40;
		flags = FileAccess.getFlags(value);
		Assert.assertTrue(flags.contains(FileAccess.FileAccessFlags.GENERIC_READ));
		Assert.assertTrue(flags.contains(FileAccess.FileAccessFlags.GENERIC_WRITE));

	}
}
