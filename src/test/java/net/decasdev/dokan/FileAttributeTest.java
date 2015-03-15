/*******************************************************************************
 * Copyright(c) 2011-1012 Ubeeko
 * All rights reserved.
 *
 * Created: 29/05/12
 * Author: Ivan Frain
 ******************************************************************************/

package net.decasdev.dokan;

import java.util.EnumSet;

import net.decasdev.dokan.FileAttribute.FileAttributeFlags;

import org.junit.Assert;
import org.junit.Test;

public class FileAttributeTest {

    @Test
    public void getFlagsTest(){
        int value = 0x00000020;
        EnumSet<FileAttributeFlags> flags = FileAttribute.getFlags(value);
        Assert.assertTrue(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_ARCHIVE));
        Assert.assertFalse(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_TEMPORARY));

        value = 0x00000010 | 0x00001000;
        flags = FileAttribute.getFlags(value);
        Assert.assertTrue(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_DIRECTORY));
        Assert.assertTrue(flags.contains(FileAttributeFlags.FILE_ATTRIBUTE_OFFLINE));

    }

}
