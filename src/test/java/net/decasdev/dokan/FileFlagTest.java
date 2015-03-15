/*******************************************************************************
 * Copyright(c) 2011-1012 Ubeeko
 * All rights reserved.
 *
 * Created: 29/05/12
 * Author: Ivan Frain
 ******************************************************************************/

package net.decasdev.dokan;

import java.util.EnumSet;

import net.decasdev.dokan.FileFlag.FileFlags;

import org.junit.Assert;
import org.junit.Test;
public class FileFlagTest {

    @Test
    public void getFlagsTest(){
        int value = 0x02000000;
        EnumSet<FileFlags> flags = FileFlag.getFlags(value);
        Assert.assertTrue(flags.contains(FileFlags.FILE_FLAG_BACKUP_SEMANTICS));
        Assert.assertFalse(flags.contains(FileFlags.FILE_FLAG_DELETE_ON_CLOSE));

        value = 0x02000000 | 0x04000000;
        flags = FileFlag.getFlags(value);
        Assert.assertTrue(flags.contains(FileFlags.FILE_FLAG_BACKUP_SEMANTICS));
        Assert.assertTrue(flags.contains(FileFlags.FILE_FLAG_DELETE_ON_CLOSE));

    }

}
