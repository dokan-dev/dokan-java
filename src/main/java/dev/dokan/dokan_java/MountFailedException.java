package dev.dokan.dokan_java;

public class MountFailedException extends Exception {

    public MountFailedException(String msg) {
        super(msg);
    }

    public MountFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MountFailedException(Throwable cause) {
        super(cause);
    }
}
