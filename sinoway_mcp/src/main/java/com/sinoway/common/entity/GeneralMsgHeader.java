package com.sinoway.common.entity;

import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.SystemConstant.IsOrNot;

/**
 * 通用报文头
 * @author Liuzhen
 * @version 1.0
 * 2015-11-6
 */
public class GeneralMsgHeader {
	// 报文体长度
	private int msglen = 0;
	// 内部交易码
	private String intrncod = "00000000";
	
	// 外部交易码
	private String outtrncod = "00000000";
	
	// 产品码
	private String prdcod = "00000000";
	// 渠道号
	private String chnlcod = "00000000";
	// 是否批量 0-否 1-是
	private String isbatch = IsOrNot.NOT.getValue();
	// 报文类型  0-业务发起请求  1-前置异步即时响应  2-业务响应  3-客户端异步即时响应 4-客户端异步结果获取请求 8-重发交易 9-校验报文（长连接使用）
	private String msgtype = MsgTemplateType.ASYNSRES.getValue();;
	// 报文校验码
	private String checkcod = "00000000000000000000000000000000";
	
	private byte[] bytes = null;//字节数组
	
	/*
	 *   GETTER    AND   SETTER
	 */
	public int getMsglen() {
		return msglen;
	}
	public void setMsglen(int msglen) {
		this.msglen = msglen;
	}
	public String getIntrncod() {
		return intrncod;
	}
	public void setIntrncod(String intrncod) {
		this.intrncod = intrncod;
	}
	public String getPrdcod() {
		return prdcod;
	}
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}
	public String getChnlcod() {
		return chnlcod;
	}
	public void setChnlcod(String chnlcod) {
		this.chnlcod = chnlcod;
	}
	public String getCheckcod() {
		return checkcod;
	}
	public void setCheckcod(String checkcod) {
		this.checkcod = checkcod;
	}
	public String getIsbatch() {
		return isbatch;
	}
	public void setIsbatch(String isbatch) {
		this.isbatch = isbatch;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getOuttrncod() {
		return outtrncod;
	}
	public void setOuttrncod(String outtrncod) {
		this.outtrncod = outtrncod;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	
}
