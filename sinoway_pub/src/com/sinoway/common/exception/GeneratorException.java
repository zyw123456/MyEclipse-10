package com.sinoway.common.exception;

public class GeneratorException extends Exception {

	private static final long serialVersionUID = -5318780812556638280L;

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(Throwable cause) {
		super(cause);
	}

	public GeneratorException(String message, Throwable cause) {
		super(message, cause);
	}
}
