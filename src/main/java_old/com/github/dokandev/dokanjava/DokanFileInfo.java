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

import com.google.common.base.MoreObjects;

public class DokanFileInfo {
  public long context;
  public final long dokanContext;
  public final long dokanOptionsPointer;
  public final long processId;
  public final boolean isDirectory;
  public final boolean deleteOnClose;
  public final boolean pagingIo;
  public final boolean synchronousIo;
  public final boolean noCache;
  public final boolean writeToEndOfFile;

  private DokanFileInfo(long context, long dokanContext, long dokanOptionsPointer, int processId,
      boolean isDirectory, boolean deleteOnClose, boolean pagingIo, boolean synchronousIo,
      boolean noCache, boolean writeToEndOfFile) {
    this.context = context;
    this.dokanContext = dokanContext;
    this.dokanOptionsPointer = dokanOptionsPointer;
    this.processId = Integer.toUnsignedLong(processId);
    this.isDirectory = isDirectory;
    this.deleteOnClose = deleteOnClose;
    this.pagingIo = pagingIo;
    this.synchronousIo = synchronousIo;
    this.noCache = noCache;
    this.writeToEndOfFile = writeToEndOfFile;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("context", context)
        .add("dokanContext", dokanContext)
        .add("dokanOptionsPointer", dokanOptionsPointer)
        .add("processId", processId)
        .add("isDirectory", isDirectory)
        .add("deleteOnClose", deleteOnClose)
        .add("pagingIo", pagingIo)
        .add("synchronousIo", synchronousIo)
        .add("noCache", noCache)
        .add("writeToEndOfFile", writeToEndOfFile)
        .toString();
  }
}
