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

import java.util.Set;

import com.google.common.base.MoreObjects;

public final class DokanOptions {
  private final short version;
  private final short threadCount;
  private final int options;
  private final long globalContext;
  private final String mountPoint;

  public DokanOptions(short version, short threadCount, int options, long globalContext,
      String mountPoint) {
    this.version = version;
    this.threadCount = threadCount;
    this.options = options;
    this.globalContext = globalContext;
    this.mountPoint = checkNotNull(mountPoint);
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
    private int version = 0;
    private int threadCount = 0;
    private int options = 0;
    private long globalContext = 0L;
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

    /**
     * Set supported Dokan version (e.g. '730' represents version 0.7.3).
     * 
     * @throws IllegalArgumentException if {@code version} is not in range [0, 65535]
     */
    public Builder version(int version) {
      if (version < 0 || version > 65535) {
        throw new IllegalArgumentException(version + " is not in range [0, 65535]");
      }
      this.version = version;
      return this;
    }

    /**
     * Number of threads to be used (0 for default value).
     * 
     * @throws IllegalArgumentException if {@code threadCount} is not in range [0, 65535]
     */
    public Builder threadCount(int threadCount) {
      if (version < 0 || version > 65535) {
        throw new IllegalArgumentException(version + " is not in range [0, 65535]");
      }
      this.threadCount = threadCount;
      return this;
    }

    public Builder options(Set<DokanOption> options) {
      this.options = DokanOption.bitFieldFrom(options);
      return this;
    }

    public Builder globalContext(long globalContext) {
      this.globalContext = globalContext;
      return this;
    }

    public DokanOptions build() {
      return new DokanOptions((short) this.version, (short) this.threadCount, this.options,
          this.globalContext, this.mountPoint);
    }
  }
}
