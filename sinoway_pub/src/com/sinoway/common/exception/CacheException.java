package com.sinoway.common.exception;

@SuppressWarnings("serial")
public class CacheException extends RuntimeException{
	/**
	 * 创建一个新的 <code>CacheException</code>.
	 */
	public CacheException() {
		super();
	}

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 *
	 * @param message
	 *            抛出异常的原因.
	 */
	public CacheException(String message) {
		super(message);
	}

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 *
	 * @param cause
	 *            抛出异常的底层原因.
	 */
	public CacheException(Throwable cause) {
		super(cause);
	}

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 *
	 * @param message
	 *            抛出异常的原因.
	 * @param cause
	 *            抛出异常的底层原因.
	 */
	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}
}
