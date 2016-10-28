package com.github.dokandev.dokanjava;

public class FileSystemFeatures {
    static public final int None = 0;
    static public final int CaseSensitiveSearch = 1;
    static public final int CasePreservedNames = 2;
    static public final int UnicodeOnDisk = 4;
    static public final int PersistentAcls = 8;
    static public final int VolumeQuotas = 0x20;
    static public final int SupportsSparseFiles = 0x40;
    static public final int SupportsReparsePoints = 0x80;
    static public final int SupportsRemoteStorage = 0x100;
    static public final int VolumeIsCompressed = 0x00008000;
    static public final int SupportsObjectIDs = 0x00010000;
    static public final int SupportsEncryption = 0x00020000;
    static public final int NamedStreams = 0x00040000;
    static public final int ReadOnlyVolume = 0x00080000;
    static public final int SequentialWriteOnce = 0x00100000;
    static public final int SupportsTransactions = 0x00200000;
}
