package com.sinoway.common.entity;


/**
 * 通用报告报文消息头
 * @author miles
 *
 */
public class GeneralRptMsgHeader {
	
	//云平台报告编号
	private String clntjrn;
	//云平台处理日期
	private String clnttrndte;
	//云平台处理时间
	private String clnttrntime;
	//前置流水
	private String fnttrnjrn;
	//前置交易日期
	private String frnttrndte;
	//前置交易时间
	private String frnttrntim;
	//机构号
	private String orgno;
	//子用户号
	private String subusrid;
	//用户号
	private String usrid;
	
	private String status;
	
	private String result;
	
	public GeneralRptMsgHeader() {
		super();
	}
	
	public GeneralRptMsgHeader(String clntjrn, String clnttrndte, String clnttrntime, String fnttrnjrn,
			String frnttrndte, String frnttrntim, String orgno, String subusrid, String usrid) {
		super();
		this.clntjrn = clntjrn;
		this.clnttrndte = clnttrndte;
		this.clnttrntime = clnttrntime;
		this.fnttrnjrn = fnttrnjrn;
		this.frnttrndte = frnttrndte;
		this.frnttrntim = frnttrntim;
		this.orgno = orgno;
		this.subusrid = subusrid;
		this.usrid = usrid;
	}
	
	public String getClntjrn() {
		return clntjrn;
	}
	public void setClntjrn(String clntjrn) {
		this.clntjrn = clntjrn;
	}
	public String getClnttrndte() {
		return clnttrndte;
	}
	public void setClnttrndte(String clnttrndte) {
		this.clnttrndte = clnttrndte;
	}
	public String getClnttrntime() {
		return clnttrntime;
	}
	public void setClnttrntime(String clnttrntime) {
		this.clnttrntime = clnttrntime;
	}
	public String getFnttrnjrn() {
		return fnttrnjrn;
	}
	public void setFnttrnjrn(String fnttrnjrn) {
		this.fnttrnjrn = fnttrnjrn;
	}
	public String getFrnttrndte() {
		return frnttrndte;
	}
	public void setFrnttrndte(String frnttrndte) {
		this.frnttrndte = frnttrndte;
	}
	public String getFrnttrntim() {
		return frnttrntim;
	}
	public void setFrnttrntim(String frnttrntim) {
		this.frnttrntim = frnttrntim;
	}
	public String getOrgno() {
		return orgno;
	}
	public void setOrgno(String orgno) {
		this.orgno = orgno;
	}
	public String getSubusrid() {
		return subusrid;
	}
	public void setSubusrid(String subusrid) {
		this.subusrid = subusrid;
	}
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
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

	@Override
	public String toString() {
		return "GeneralRptMsgHeader [clntjrn=" + clntjrn + ", clnttrndte=" + clnttrndte + ", clnttrntime=" + clnttrntime
				+ ", fnttrnjrn=" + fnttrnjrn + ", frnttrndte=" + frnttrndte + ", frnttrntim=" + frnttrntim + ", orgno="
				+ orgno + ", subusrid=" + subusrid + ", usrid=" + usrid + ", status=" + status + ", result=" + result
				+ "]";
	}

	
}
