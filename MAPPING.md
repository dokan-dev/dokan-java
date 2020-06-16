# Overview
| C Data Type         | Windows Data Type - Macro | Size (Bytes/bits) | java/JNA Data Type                |
|---------------------|---------------------------|-------------------|-----------------------------------|
| char/byte           | UCHAR                     | 1B/8b             | byte                              |
| unsigned short      | USHORT                    | 2B/16b            | short (@Unsigned)/WinDef.USHORT   |
| unsigned long (int) | ULONG                     | 4B/32b            | int (@Unsigned)/WinDef.ULONG      |
| unsigned long (int) | DWORD/AccessMask          | 4B/32b            | int (@Unsigned)/WinDef.DWORD      |
| unsigned __int64    | ULONG64                   | 8B/64b            | long (@Unsigned)/WinDef.ULONGLONG |
# References
Basic Types/Size: [Web](https://docs.microsoft.com/en-us/cpp/c-language/storage-of-basic-types?view=vs-2019) | [Permalink](https://web.archive.org/web/20200616151823/https://docs.microsoft.com/en-us/cpp/c-language/storage-of-basic-types?view=vs-2019) <br>
Windows Data Types/Mapped C Data Types: [Web](https://docs.microsoft.com/en-us/windows/win32/winprog/windows-data-types) | [Permalink](https://web.archive.org/web/20200616152122/https://docs.microsoft.com/en-us/windows/win32/winprog/windows-data-types) <br>
JNA Data Types: [Web](https://java-native-access.github.io/jna/5.5.0/javadoc/com/sun/jna/platform/win32/WinDef.html) | [Permalink](https://web.archive.org/web/20200616212719/https://java-native-access.github.io/jna/5.5.0/javadoc/com/sun/jna/platform/win32/WinDef.html) <br>