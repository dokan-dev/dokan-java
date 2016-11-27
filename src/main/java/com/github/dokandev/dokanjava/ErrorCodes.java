package com.github.dokandev.dokanjava;

public class ErrorCodes {
    static public final long ERROR_SUCCESS = 0;
    //static public final long ERROR_FILE_NOT_FOUND = 2;
    static public final long ERROR_FILE_NOT_FOUND = 0xc0000034;
    static public final long ERROR_WRITE_FAULT = 29;
    static public final long ERROR_READ_FAULT = 30;
    static public final long ERROR_ALREADY_EXISTS = 183;
    static public final long ERROR_FILE_EXISTS = 80;

    static public final long ObjectNameCollision = 0xc0000035;
}
