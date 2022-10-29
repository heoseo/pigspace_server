package com.pigspace.common.support;

public class PigException extends RuntimeException{

	private static final long serialVersionUID = 7154722228490141559L;

	public PigException(String message) {
		super(message);
	}

	public PigException(String message, Throwable cause) {
		super(message, cause);
	}
}
