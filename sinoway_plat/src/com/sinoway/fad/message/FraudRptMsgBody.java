package com.sinoway.fad.message;

/**
 * 通用报告报文体(适用于反欺诈报告和验证报告)
 * 
 * @author miles
 *
 */
public class FraudRptMsgBody {

	/**
	 * 报文消息头部 可以通用
	 */
	private String trncod;

	/**
	 * 反欺诈报告报文消息体
	 */
	private WFDatFraudMsg trninfo;

	public FraudRptMsgBody() {
		super();
	}


	public FraudRptMsgBody(String trncod, WFDatFraudMsg trninfo) {
		super();
		this.trncod = trncod;
		this.trninfo = trninfo;
	}


	public String getTrncod() {
		return trncod;
	}


	public void setTrncod(String trncod) {
		this.trncod = trncod;
	}


	public WFDatFraudMsg getTrninfo() {
		return trninfo;
	}

	public void setTrninfo(WFDatFraudMsg trninfo) {
		this.trninfo = trninfo;
	}

	public String toString() {
		return "FraudRptMsgBody [trncod=" + trncod + ", trninfo=" + trninfo + "]";
	}

}
