package com.sinoway.rpt.exception;


/**
 * 校验异常信息类
 * @author miles
 *
 */
public class VerifiedException extends Exception {

	private static final long serialVersionUID = 1356164483157151306L;

	public VerifiedException(String message) {
		super(message);
	}

	public VerifiedException(Throwable cause) {
		super(cause);
	}

	public VerifiedException(String message, Throwable cause) {
		super(message, cause);
	}
}
