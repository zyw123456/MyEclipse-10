package com.sinoway.fad.message;

import com.sinoway.common.entity.GeneralRptMsgHeader;



/**
 * 反欺诈报告消息实体类
 * @author miles
 *
 */
public class FraudRptMsg {

	/**
	 * 反欺诈请求报文头
	 */
	private GeneralRptMsgHeader header;
	
	/**
	 * 反欺诈请求报文体
	 */
	private FraudRptMsgBody body;

	public GeneralRptMsgHeader getHeader() {
		return header;
	}

	public void setHeader(GeneralRptMsgHeader header) {
		this.header = header;
	}

	public FraudRptMsgBody getBody() {
		return body;
	}

	public void setBody(FraudRptMsgBody body) {
		this.body = body;
	}

	public String toString() {
		return "FraudRptMsg [header=" + header + ", body=" + body + "]";
	}
	
	
	
}
