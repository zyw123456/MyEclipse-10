package com.sinoway.common.exception;

public class HttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 376105983067149270L;

	public HttpException(String message) {
		super(message);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}
}
