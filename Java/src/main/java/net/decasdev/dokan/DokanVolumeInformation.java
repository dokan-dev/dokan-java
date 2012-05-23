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

public class DokanVolumeInformation {
	public String volumeName;
	public int volumeSerialNumber;
	public int maximumComponentLength;
	public int fileSystemFlags;
	public String fileSystemName;

	public DokanVolumeInformation() {
	}

	@Override public String toString() {
		return "DokanVolumeInformation(" + "volumeName=" + volumeName + "," + "volumeSerialNumber="
				+ volumeSerialNumber + "," + "maximumComponentLength=" + maximumComponentLength + ","
				+ "fileSystemFlags=" + fileSystemFlags + "," + "fileSystemName=" + fileSystemName + ")";
	}
}
