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

#pragma once

#ifndef WINVER				
#define WINVER 0x0500		
#endif

#ifndef _WIN32_WINNT		
#define _WIN32_WINNT 0x0500	
#endif						

#ifndef _WIN32_WINDOWS		
#define _WIN32_WINDOWS 0x0410 
#endif

#ifndef _WIN32_IE			
#define _WIN32_IE 0x0500	
#endif

#define WIN32_LEAN_AND_MEAN		

#include <windows.h>
#include <winerror.h>
#include <stdlib.h>
#include <wchar.h>
#include <jni.h>

#ifndef MIN
#define MIN(a, b) ( a < b ? a : b)
#endif

#if _DEBUG
	#ifndef LOG
		#define LOG(...) { fwprintf(stdout, __VA_ARGS__); fflush(stdout); }
	#endif
	#ifndef LOGA
		#define LOGA(...) { fprintf(stdout, __VA_ARGS__); fflush(stdout); }
	#endif
#else
	#ifndef LOG
		#define LOG(...) { doNothing(__VA_ARGS__); }
	#endif
	#ifndef LOGA
		#define LOGA(...) { doNothing(__VA_ARGS__); }
	#endif
#endif

void doNothing(...);
