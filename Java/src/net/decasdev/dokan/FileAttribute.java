/*
  JDokan : Java library for Dokan
  
  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
  				2009 Caleido AG   http://www.wuala.com/

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

public interface FileAttribute {
	public final static int	ATTRIBUTE_MASK= 0x0000FFFF;
	
	public final static int FILE_ATTRIBUTE_ARCHIVE = 0x00000020;
	public final static int FILE_ATTRIBUTE_COMPRESSED = 0x00000800;
	public final static int FILE_ATTRIBUTE_DIRECTORY = 0x00000010;
	public final static int FILE_ATTRIBUTE_ENCRYPTED = 0x00000040;
	public final static int FILE_ATTRIBUTE_HIDDEN = 0x00000002;
	public final static int FILE_ATTRIBUTE_NORMAL = 0x00000080;
	public final static int FILE_ATTRIBUTE_OFFLINE = 0x00001000;
	public final static int FILE_ATTRIBUTE_READONLY = 0x00000001;
	public final static int FILE_ATTRIBUTE_REPARSE_POINT = 0x00000400;
	public final static int FILE_ATTRIBUTE_SPARSE_FILE = 0x00000200;
	public final static int FILE_ATTRIBUTE_SYSTEM = 0x00000004;
	public final static int FILE_ATTRIBUTE_TEMPORARY = 0x00000100;
	public final static int FILE_ATTRIBUTE_NOT_CONTENT_INDEXED = 0x00002000;
	public final static int FILE_ATTRIBUTE_VIRTUAL = 0x00010000;
}
