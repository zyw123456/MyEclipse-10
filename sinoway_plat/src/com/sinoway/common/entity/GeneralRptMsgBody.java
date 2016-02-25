package com.sinoway.common.entity;


/**
 * 通用的报文消息体
 * @author miles
 *
 */
public class GeneralRptMsgBody {

	/**
	 * 状态值
	 */
	private String status;
	
	/**
	 * 描述
	 */
	private String result;
	
	public GeneralRptMsgBody() {
		super();
	}
	

	public GeneralRptMsgBody(String status, String result) {
		super();
		this.status = status;
		this.result = result;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	
	
}
