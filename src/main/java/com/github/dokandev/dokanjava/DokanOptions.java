/*
 * Dokan-Java : Java library for Dokan
 * 
 * Copyright (C) 
 *   2008 Yu Kobayashi http://yukoba.accelart.jp/
 *   2015 Simon Herter <sim.herter@gmail.com>
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

import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.base.MoreObjects;

public final class DokanOptions {
  public int version;
  public int threadCount;
  public long options;
  public long globalContext;
  public String mountPoint;

  private DokanOptions(int version, int threadCount, long options, long globalContext,
      String mountPoint) {
    this.version = version;
    this.threadCount = threadCount;
    this.options = options;
    this.globalContext = globalContext;
    this.mountPoint = mountPoint;
  }

  public static Builder builder(String mountPoint) {
    return new Builder(mountPoint);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("version", version)
        .add("threadCount", threadCount)
        .add("options", options)
        .add("globalContext", globalContext)
        .add("mountPoint", mountPoint)
        .toString();
  }

  public static class Builder {
    private int version;
    private int threadCount;
    private long options;
    private long globalContext;
    private final String mountPoint;

    Builder(String mountPoint) {
      this.mountPoint = checkNotNull(mountPoint);
    }

    Builder(DokanOptions source) {
      this.version = source.version;
      this.threadCount = source.threadCount;
      this.options = source.options;
      this.globalContext = source.globalContext;
      this.mountPoint = source.mountPoint;
    }

    public Builder version(int version) {
      this.version = version;
      return this;
    }

    public Builder threadCount(int threadCount) {
      this.threadCount = threadCount;
      return this;
    }

    public Builder options(long options) {
      this.options = options;
      return this;
    }

    public Builder globalContext(long globalContext) {
      this.globalContext = globalContext;
      return this;
    }

    public DokanOptions build() {
      return new DokanOptions(this.version, this.threadCount, this.options, this.globalContext,
          this.mountPoint);
    }
  }
}
