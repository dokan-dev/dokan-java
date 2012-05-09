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

/** BY_HANDLE_FILE_INFORMATION */
public class ByHandleFileInformation {
	public int fileAttributes;
	/** FILETIME */
	public long creationTime;
	/** FILETIME */
	public long lastAccessTime;
	/** FILETIME */
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

	@Override public String toString() {
		return "ByHandleFileInformation(" + "fileAttributes=" + fileAttributes + "," + "lastAccessTime="
				+ lastAccessTime + "," + "lastWriteTime=" + lastWriteTime + "," + "fileSize=" + fileSize
				+ "," + "volumeSerialNumber=" + volumeSerialNumber + "," + "fileSize=" + fileSize + ","
				+ "numberOfLinks=" + numberOfLinks + "," + "fileIndex=" + fileIndex + ")";
	}
}
