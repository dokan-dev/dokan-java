/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
 *               2015 Simon Herter <sim.herter@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.dokandev.dokanjava;

import com.github.dokandev.dokanjava.util.FileAttribute;
import com.github.dokandev.dokanjava.util.FileTime;
import com.google.common.base.MoreObjects;

public class ByHandleFileInformation {
  public int fileAttributes;
  public long creationTime;
  public long lastAccessTime;
  public long lastWriteTime;
  public int volumeSerialNumber;
  public long fileSize;
  public int numberOfLinks;
  public long fileIndex;

  public ByHandleFileInformation(int fileAttributes, long creationTime, long lastAccessTime,
      long lastWriteTime, int volumeSerialNumber, long fileSize, int numberOfLinks, long fileIndex) {
    this.fileAttributes = fileAttributes;
    this.creationTime = creationTime;
    this.lastAccessTime = lastAccessTime;
    this.lastWriteTime = lastWriteTime;
    this.volumeSerialNumber = volumeSerialNumber;
    this.fileSize = fileSize;
    this.numberOfLinks = numberOfLinks;
    this.fileIndex = fileIndex;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("fileAttributes", FileAttribute.fromInt(fileAttributes))
        .add("creationTime", FileTime.toDate(creationTime))
        .add("lastAccessTime", FileTime.toDate(lastAccessTime))
        .add("lastWriteTime", FileTime.toDate(lastWriteTime))
        .add("volumeSerialNumber", volumeSerialNumber)
        .add("fileSize", fileSize)
        .add("numberOfLinks", numberOfLinks)
        .add("fileIndex", fileIndex)
        .toString();
  }
}
