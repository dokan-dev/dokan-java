package com.dokan.java.constants.microsoft;

public final class CreateOptions {

    public static final int FILE_DIRECTORY_FILE = 0x00000001;
    public static final int FILE_WRITE_THROUGH = 0x00000002;
    public static final int FILE_SEQUENTIAL_ONLY = 0x00000004;
    public static final int FILE_NO_INTERMEDIATE_BUFFERING = 0x00000008;
    public static final int FILE_SYNCHRONOUS_IO_ALERT = 0x00000010;
    public static final int FILE_SYNCHRONOUS_IO_NONALERT = 0x00000020;
    public static final int FILE_NON_DIRECTORY_FILE = 0x00000040;
    public static final int FILE_CREATE_TREE_CONNECTION = 0x00000080;

    public static final int FILE_COMPLETE_IF_OPLOCKED = 0x00000100;
    public static final int FILE_NO_EA_KNOWLEDGE = 0x00000200;
    public static final int FILE_OPEN_REMOTE_INSTANCE = 0x00000400;
    public static final int FILE_RANDOM_ACCESS = 0x00000800;

    public static final int FILE_DELETE_ON_CLOSE = 0x00001000;
    public static final int FILE_OPEN_BY_FILE_ID = 0x00002000;
    public static final int FILE_OPEN_FOR_BACKUP_INTENT = 0x00004000;
    public static final int FILE_NO_COMPRESSION = 0x00008000;

    private CreateOptions() {

    }
}
