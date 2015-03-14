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

#include "stdafx.h"

DWORD LongLongToHigh(LONGLONG ll) { return (DWORD)(ll >> 32); }
DWORD LongLongToLow(LONGLONG ll) { return (DWORD)(ll & 0xffffffff); }

LONGLONG FileTime2LongLong(CONST FILETIME* ft)
{
	if (ft == NULL)
		return 0;
	else
		return (((LONGLONG)ft->dwHighDateTime) << 32) | ((LONGLONG)ft->dwLowDateTime);
}

FILETIME LongLong2FileTime(LONGLONG ll) 
{
	FILETIME ft;
	ft.dwHighDateTime = LongLongToHigh(ll);
	ft.dwLowDateTime = LongLongToLow(ll);
	return ft;
}

LONGLONG ToLongLong(DWORD high, DWORD low)
{
	return ((LONGLONG)high) << 32 | ((LONGLONG)low);
}

