/*******************************************************************************
 * Copyright(c) 2011-1012 Ubeeko
 * All rights reserved.
 *
 * Created: 29/05/12
 * Author: Ivan Frain
 ******************************************************************************/

package com.github.dokandev.dokanjava;

import java.util.EnumSet;

import org.junit.Assert;
import org.junit.Test;

import com.github.dokandev.dokanjava.FileAttribute;
import com.github.dokandev.dokanjava.FileAttribute.FileAttributeFlags;

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
