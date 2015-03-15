/*******************************************************************************
 * Copyright(c) 2011-1012 Ubeeko
 * All rights reserved.
 *
 * Created: 25/05/12
 * Author: Ivan Frain
 ******************************************************************************/

package net.decasdev.dokan;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class FileAccessTest {

    private static Logger logger = LoggerFactory.getLogger(FileAccessTest.class);

    @Test
    public void getFlagsTest(){
        int value = 0x80;
        EnumSet<FileAccess.FileAccessFlags> flags = FileAccess.getFlags(value);
        Assert.assertTrue(flags.contains(FileAccess.FileAccessFlags.GENERIC_READ));
        Assert.assertFalse(flags.contains(FileAccess.FileAccessFlags.GENERIC_WRITE));
        logger.debug("access = " + FileAccess.toString(value));

        value = 0x80 + 0x40;
        flags = FileAccess.getFlags(value);
        Assert.assertTrue(flags.contains(FileAccess.FileAccessFlags.GENERIC_READ));
        Assert.assertTrue(flags.contains(FileAccess.FileAccessFlags.GENERIC_WRITE));

        logger.debug("access = " + FileAccess.toString(value));
    }
}
