/*
  JDokan : Java library for Dokan
  
  Copyright (C) 2008 Yu Kobayashi http://yukoba.accelart.jp/
  				2009 Caleido AG   http://www.wuala.com/

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

public interface FileFlag {
	public final static int FLAGS_MASK= 0xFFFF0000;
	
	public final static int FILE_FLAG_BACKUP_SEMANTICS = 0x02000000;
	public final static int FILE_FLAG_DELETE_ON_CLOSE = 0x04000000;
	public final static int FILE_FLAG_NO_BUFFERING = 0x20000000;
	public final static int FILE_FLAG_OPEN_NO_RECALL = 0x00100000;
	public final static int FILE_FLAG_OPEN_REPARSE_POINT = 0x00200000;
	public final static int FILE_FLAG_OVERLAPPED = 0x40000000;
	public final static int FILE_FLAG_POSIX_SEMANTICS = 0x01000000;
	public final static int FILE_FLAG_RANDOM_ACCESS = 0x10000000;
	public final static int FILE_FLAG_SEQUENTIAL_SCAN = 0x08000000;
	public final static int FILE_FLAG_WRITE_THROUGH = 0x80000000;
}
