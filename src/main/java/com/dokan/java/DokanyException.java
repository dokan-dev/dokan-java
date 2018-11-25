package com.dokan.java;

public final class DokanyException extends RuntimeException {

	private final int value;
	private final String message;
	private final Throwable cause;

	public DokanyException(Exception e) {
		this.value = Integer.MIN_VALUE;
		this.message = "";
		this.cause = e;
	}

	public DokanyException(String message, int errorCode) {
	    this.value = errorCode;
	    this.message = message;
	    this.cause = null;
	}

    public DokanyException(String message, Throwable cause) {
        this.value = Integer.MIN_VALUE;
        this.message = message;
        this.cause = cause;
    }

	public int getValue() {
		return value;
	}


	public Throwable getCause(){
	    return this.cause;
    }

    public String getMessage(){
	    return message;
    }
}