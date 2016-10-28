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

import com.github.dokandev.dokanjava.util.WinError;

public class DokanException extends Exception {
  private static final long serialVersionUID = -2759529773077624821L;

  private final int errorCode;

  public DokanException(WinError error) {
    this.errorCode = error.code;
  }
  
  public DokanException(long errorCode) {
    if (errorCode < 0 || errorCode > 4294967295L) {
      throw new IllegalArgumentException("error code is not in range [0, 4294967295]");
    }
    this.errorCode = (int) errorCode;
  }
}
