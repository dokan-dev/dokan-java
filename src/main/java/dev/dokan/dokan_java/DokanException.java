package dev.dokan.dokan_java;

public final class DokanException extends RuntimeException {

    private final int errorCode;

    public DokanException(Exception e) {
        super(e);
        this.errorCode = Integer.MIN_VALUE;
    }

    public DokanException(String message) {
        super(message);
        this.errorCode = Integer.MIN_VALUE;
    }
    public DokanException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DokanException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public DokanException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = Integer.MIN_VALUE;
    }

    public DokanException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Use #getErrorCode() instead.
     * @return error code.
     */
    @Deprecated
    public int getValue() {
        return errorCode;
    }


}