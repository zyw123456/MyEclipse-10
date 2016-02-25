package com.sinoway.common.exception;

/**
 * 对称加解密异常
 * @author Liuzhen
 * @version 1.0
 * 2015-11-3
 * 
 */
public class DesException extends Exception {
	


	private static final long serialVersionUID = -4526426033390257349L;

	private String sysName;
	private int errTypeCode;
	private int errInfoCode;

	public DesException() {
		super();
	}

	public DesException(Exception e) {
		super(e);
	}

	public DesException(String errorMsg) {
		super(errorMsg);
	}

	public DesException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}

	public DesException(DesException e) {
		super(e);
		this.sysName = e.getSysName();
		this.errTypeCode = e.getErrTypeCode();
		this.errInfoCode = e.getErrInfoCode();
	}

	/**
	 * 构造方法
	 * 
	 * @param sysName
	 *            系统名称
	 * @param errTypeCode
	 *            错误类别码
	 * @param errInfoCode
	 *            错误信息码
	 */
	public DesException(String sysName, int errTypeCode, int errInfoCode) {
		super();
		this.sysName = sysName;
		this.errTypeCode = errTypeCode;
		this.errInfoCode = errInfoCode;
	}

	/**
	 * 构造方法
	 * 
	 * @param sysName
	 *            系统名称
	 * @param errTypeCode
	 *            错误类别码
	 * @param errInfoCode
	 *            错误信息码
	 * @param e
	 *            用于构造当前异常信息的异常
	 */
	public DesException(String sysName, int errTypeCode, int errInfoCode,
			Exception e) {
		super(e.getMessage());
		this.sysName = sysName;
		this.errTypeCode = errTypeCode;
		this.errInfoCode = errInfoCode;
	}

	/**
	 * 构造方法
	 * 
	 * @param sysName
	 *            系统名称
	 * @param errTypeCode
	 *            错误类别码
	 * @param errInfoCode
	 *            错误信息码
	 * @param errMsg
	 *            错误信息
	 */
	public DesException(String sysName, int errTypeCode, int errInfoCode,
			String errMsg) {
		super(errMsg);
		this.sysName = sysName;
		this.errTypeCode = errTypeCode;
		this.errInfoCode = errInfoCode;
	}

	public String getSysName() {
		return sysName;
	}

	public int getErrTypeCode() {
		return errTypeCode;
	}

	public int getErrInfoCode() {
		return errInfoCode;
	}

}
