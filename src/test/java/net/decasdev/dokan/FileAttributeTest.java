/*******************************************************************************
 * Copyright(c) 2011-1012 Ubeeko
 * All rights reserved.
 *
 * Created: 29/05/12
 * Author: Ivan Frain
 ******************************************************************************/

package net.decasdev.dokan;

import org.junit.Assert;
import net.decasdev.dokan.FileAttribute.FileAttributeFlags;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class FileAttributeTest {

    private static Logger logger = LoggerFactory.getLogger(FileAttributeTest.class);

    @Test
    public void getFlagsTest(){
        int value = 0x00000020;
        EnumSet<FileAttributeFlags> flags = FileAttribute.getFlags(value);
        Assert.assertTrue(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_ARCHIVE));
        Assert.assertFalse(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_TEMPORARY));
        logger.debug("value = " + value + ": " +FileAttribute.toString(value));

        value = 0x00000010 | 0x00001000;
        flags = FileAttribute.getFlags(value);
        Assert.assertTrue(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_DIRECTORY));
        Assert.assertTrue(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_OFFLINE));
        logger.debug("value = " + value + ": " +FileAttribute.toString(value));

    }

}
