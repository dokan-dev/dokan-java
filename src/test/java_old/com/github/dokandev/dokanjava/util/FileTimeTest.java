package com.github.dokandev.dokanjava.util;

import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;

import org.junit.Test;

public class FileTimeTest {
  
  @Test
  public void testEpochDifferenceBetweenWindowsAndUnix() throws Exception {
    LocalDateTime windows = LocalDateTime.of(1601, Month.JANUARY, 1, 0, 0);
    LocalDateTime unix = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0);
    long diff = unix.toEpochSecond(ZoneOffset.UTC) - windows.toEpochSecond(ZoneOffset.UTC);
    assertThat(FileTime.EPOCH_DIFF).isEqualTo(diff);
  }
  
  @Test
  public void testSpecificDate() throws Exception {
    // calculated with http://www.silisoftware.com/tools/date.php
    long filetime = 0x01D0965512783A00L;
    ZonedDateTime date = ZonedDateTime.parse("2015-05-24T19:08:52+00:00");
    assertThat(FileTime.toDate(filetime)).isEqualTo(date);
    assertThat(FileTime.toFileTime(date)).isEqualTo(filetime);
  }

  @Test
  public void testFileTimeZeroIsJanuar1601() {
    ZonedDateTime date = FileTime.toDate(0);
    assertThat(date.getYear()).is(1601);
  }

  @Test
  public void testRandomLong() throws Exception {
    long filetime = new Random().nextLong();
    ZonedDateTime date = FileTime.toDate(filetime);
    assertThat(FileTime.toFileTime(date)).isEqualTo(filetime);
  }
  
  @Test
  public void testNegativeFileTime() throws Exception {
    long filetime = -390881366987146965L;
    ZonedDateTime date = FileTime.toDate(filetime);
    assertThat(FileTime.toFileTime(date)).isEqualTo(filetime);
  }

}
