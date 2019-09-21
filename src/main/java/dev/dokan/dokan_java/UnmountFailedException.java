package dev.dokan.dokan_java;

public class UnmountFailedException extends RuntimeException {

    public UnmountFailedException(String msg) {
        super(msg);
    }

    public UnmountFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnmountFailedException(Throwable cause) {
        super(cause);
    }
}
