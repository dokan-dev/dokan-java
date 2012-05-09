/*
  JDokan : Java library for Dokan

  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/

  http://decas-dev.net/en

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 3 of the License, or (at your option) any
later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along
with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package net.decasdev.dokan;

public class Dokan {
	static {
		System.loadLibrary("JDokan");
	}
	
	// mount returns error codes
	public static final int DOKAN_SUCCESS = 0;
	/** General Error */
	public static final int DOKAN_ERROR = -1;
	/** Bad Drive letter */
	public static final int DOKAN_DRIVE_LETTER_ERROR = -2;
	/** Can't install driver */
	public static final int DOKAN_DRIVER_INSTALL_ERROR = -3;
	/** Driver something wrong */
	public static final int DOKAN_START_ERROR = -4;
	/** Can't assign a drive letter */
	public static final int DOKAN_MOUNT_ERROR = -5;
	
	private Dokan() {
	}
	
	// DokanMain
	public static native int mount(DokanOptions options,DokanOperations operations);

	public static native boolean unmount(char driveLetter);
	
	public static native boolean removeMountPoint(String mountPoint);

	/**
	 * Check whether Name can match Expression. Expression can contain wildcard characters (? and *)
	 */
	public static native boolean isNameInExpression(String expression, String name, boolean ignoreCase);

	public static native int getVersion();

	public static native int getDriverVersion();
}
