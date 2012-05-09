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

/** From WinError.h */
public interface WinError {
	public static final int ERROR_SUCCESS = 0;
	public static final int ERROR_INVALID_FUNCTION = 1;
	public static final int ERROR_FILE_NOT_FOUND = 2;
	public static final int ERROR_PATH_NOT_FOUND = 3;
	public static final int ERROR_TOO_MANY_OPEN_FILES = 4;
	public static final int ERROR_ACCESS_DENIED = 5;
	public static final int ERROR_INVALID_HANDLE = 6;
	public static final int ERROR_INVALID_ACCESS = 12;
	public static final int ERROR_INVALID_DATA = 13;
	public static final int ERROR_INVALID_DRIVE = 15;
	public static final int ERROR_WRITE_FAULT = 29;
	public static final int ERROR_READ_FAULT = 30;
	public static final int ERROR_GEN_FAILURE = 31;
	public static final int ERROR_SHARING_VIOLATION = 32;
	public static final int ERROR_LOCK_VIOLATION = 33;
	public static final int ERROR_HANDLE_EOF = 38;
	public static final int ERROR_HANDLE_DISK_FULL = 39;
	public static final int ERROR_NOT_SUPPORTED = 50;
	public static final int ERROR_FILE_EXISTS = 80;
	public static final int ERROR_CANNOT_MAKE = 82;
	public static final int ERROR_INVALID_PARAMETER = 87;
	public static final int ERROR_DRIVE_LOCKED = 108;
	public static final int ERROR_OPEN_FAILED = 110;
	public static final int ERROR_BUFFER_OVERFLOW = 111;
	public static final int ERROR_DISK_FULL = 112;
	public static final int ERROR_INVALID_NAME = 123;
	public static final int ERROR_NO_VOLUME_LABEL = 125;
	public static final int ERROR_NEGATIVE_SEEK = 131;
	public static final int ERROR_SEEK_ON_DEVICE = 132;
	public static final int ERROR_DIR_NOT_EMPTY = 145;
	public static final int ERROR_LABEL_TOO_LONG = 154;
	public static final int ERROR_DISCARDED = 157;
	public static final int ERROR_NOT_LOCKED = 158;
	public static final int ERROR_BAD_ARGUMENTS = 160;
	public static final int ERROR_BAD_PATHNAME = 161;
	public static final int ERROR_LOCK_FAILED = 167;
	public static final int ERROR_BUSY = 170;
	public static final int ERROR_ALREADY_EXISTS = 183;
	public static final int ERROR_FILENAME_EXCED_RANGE = 206;
	public static final int ERROR_META_EXPANSION_TOO_LONG = 208;
	public static final int ERROR_FILE_TOO_LARGE = 223;
	public static final int ERROR_VIRUS_INFECTED = 225;
	public static final int ERROR_VIRUS_DELETED = 226;
	public static final int ERROR_DIRECTORY = 267;
}
