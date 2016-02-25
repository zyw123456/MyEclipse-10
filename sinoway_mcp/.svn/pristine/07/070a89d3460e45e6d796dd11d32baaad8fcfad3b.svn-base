package com.sinoway.mcp.exception;

/**
 * 交易报文转换异常
 * @author Liuzhen
 * 2015-10-30
 * 
 */
public class TradeMsgTransfException extends Exception {
	


	private static final long serialVersionUID = -4526426033390257349L;

	private String sysName;
	private int errTypeCode;
	private int errInfoCode;

	public TradeMsgTransfException() {
		super();
	}

	public TradeMsgTransfException(Exception e) {
		super(e);
	}

	public TradeMsgTransfException(String errorMsg) {
		super(errorMsg);
	}

	public TradeMsgTransfException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}

	public TradeMsgTransfException(TradeMsgTransfException e) {
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
	public TradeMsgTransfException(String sysName, int errTypeCode, int errInfoCode) {
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
	public TradeMsgTransfException(String sysName, int errTypeCode, int errInfoCode,
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
	public TradeMsgTransfException(String sysName, int errTypeCode, int errInfoCode,
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
