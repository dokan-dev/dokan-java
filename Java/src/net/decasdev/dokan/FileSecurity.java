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

public interface FileSecurity {
	public final static int SECURITY_ANONYMOUS = 0x00000000;
	public final static int SECURITY_CONTEXT_TRACKING = 0x00040000;
	public final static int SECURITY_DELEGATION = 0x00030000;
	public final static int SECURITY_EFFECTIVE_ONLY = 0x00080000;
	public final static int SECURITY_IDENTIFICATION = 0x00010000;
	public final static int SECURITY_IMPERSONATION = 0x00020000;
}
