package com.sinoway.stm.entity;

/**
 * 返回报文的封装类(对应出口参数)
 * @author xiehao
 *
 */
public class StmResMsg {
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";
	
	private String msgcode; //报文编码
	private String oprator; //操作人
	private String prdcod;  //产品编码
	private String states;  //状态
	private String result;  //原因描述
	
	
	public String getMsgcode() {
		return msgcode;
	}
	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}
	public String getOprator() {
		return oprator;
	}
	public void setOprator(String oprator) {
		this.oprator = oprator;
	}
	public String getPrdcod() {
		return prdcod;
	}
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
