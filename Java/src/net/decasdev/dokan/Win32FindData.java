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

/** WIN32_FIND_DATA */
public class Win32FindData {
	public int fileAttributes;
	/** FILETIME */
	public long creationTime;
	/** FILETIME */
	public long lastAccessTime;
	/** FILETIME */
	public long lastWriteTime;
	public long fileSize;
	public int reserved0;
	public int reserved1;
	public String fileName;
	public String alternateFileName;

	public Win32FindData() {
	}

	public Win32FindData(int fileAttributes, long creationTime, long lastAccessTime, long lastWriteTime,
			long fileSize, int reserved0, int reserved1, String fileName, String alternateFileName) {
		this.fileAttributes = fileAttributes;
		this.creationTime = creationTime;
		this.lastAccessTime = lastAccessTime;
		this.lastWriteTime = lastWriteTime;
		this.fileSize = fileSize;
		this.reserved0 = reserved0;
		this.reserved1 = reserved1;
		this.fileName = fileName;
		this.alternateFileName = alternateFileName;
	}

	@Override public String toString() {
		return "Win32FindData(" + "fileAttributes=" + fileAttributes + "," + "lastAccessTime="
				+ lastAccessTime + "," + "lastWriteTime=" + lastWriteTime + "," + "fileSize=" + fileSize
				+ "," + "reserved0=" + reserved0 + "," + "reserved1=" + reserved1 + "," + "fileName="
				+ fileName + "," + "alternateFileName=" + alternateFileName + ")";
	}
}
