package com.sinoway.common.entity;

/**
 * 与核心交互的报文头实体
 * @author Liuzhen
 * @version 1.0
 * 2015-11-6
 */
public class CoreMsgHeader {
	//	渠道号
	private String chnlcod = null;
	//	交易码
	private String intrncod = null;
	//	产品号
	private String prdcod = null;
	//	前置流水
	private String fnttrnjrn = null;
	//	前置日期
	private String fnttrndte = null;
	//	前置时间
	private String fnttrntim = null;
	//	核心日期
	private String masttrndte = null;
	//	核心时间
	private String masttrntim = null;
	//	核心流水
	private String mastjrn = null;
	//	供应商流水
	private String supptrnjrn = null;
	//	供应商日期
	private String supptrndte = null;
	//	供应商时间
	private String supptrntim = null;
	//	用户编码
	private String usrid = null;
	//	子用户编码
	private String subusrid = null;
	//	机构号
	private String orgno = null;
	//	交易批次
	private String trnbtchid = null;
	private String clntjrn = null;
	private String clnttrndte = null; 
	private String clnttrntime = null;
	private String fntprdtrnjrn = null;//子交易中的产品或原交易流水

	// 状态
	private String status = null;
	// 描述
	private String result = null;
	
	// 数据源
	private String datori = null;
	
	/*
	 * 		GETTER    AND   SETTER
	 */
	public String getChnlcod() {
		return chnlcod;
	}
	public void setChnlcod(String chnlcod) {
		this.chnlcod = chnlcod;
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
	public String getFnttrnjrn() {
		return fnttrnjrn;
	}
	public void setFnttrnjrn(String fnttrnjrn) {
		this.fnttrnjrn = fnttrnjrn;
	}
	public String getMasttrndte() {
		return masttrndte;
	}
	public void setMasttrndte(String masttrndte) {
		this.masttrndte = masttrndte;
	}
	public String getMasttrntim() {
		return masttrntim;
	}
	public void setMasttrntim(String masttrntim) {
		this.masttrntim = masttrntim;
	}
	public String getMastjrn() {
		return mastjrn;
	}
	public void setMastjrn(String mastjrn) {
		this.mastjrn = mastjrn;
	}
	public String getSupptrnjrn() {
		return supptrnjrn;
	}
	public void setSupptrnjrn(String supptrnjrn) {
		this.supptrnjrn = supptrnjrn;
	}
	public String getSupptrndte() {
		return supptrndte;
	}
	public void setSupptrndte(String supptrndte) {
		this.supptrndte = supptrndte;
	}
	public String getSupptrntim() {
		return supptrntim;
	}
	public void setSupptrntim(String supptrntim) {
		this.supptrntim = supptrntim;
	}
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getSubusrid() {
		return subusrid;
	}
	public void setSubusrid(String subusrid) {
		this.subusrid = subusrid;
	}
	public String getOrgno() {
		return orgno;
	}
	public void setOrgno(String orgno) {
		this.orgno = orgno;
	}
	public String getTrnbtchid() {
		return trnbtchid;
	}
	public void setTrnbtchid(String trnbtchid) {
		this.trnbtchid = trnbtchid;
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
	public String getDatori() {
		return datori;
	}
	public void setDatori(String datori) {
		this.datori = datori;
	}
	public String getFnttrndte() {
		return fnttrndte;
	}
	public void setFnttrndte(String fnttrndte) {
		this.fnttrndte = fnttrndte;
	}
	public String getFnttrntim() {
		return fnttrntim;
	}
	public void setFnttrntim(String fnttrntim) {
		this.fnttrntim = fnttrntim;
	}
	public String getFntprdtrnjrn() {
		return fntprdtrnjrn;
	}
	public void setFntprdtrnjrn(String fntprdtrnjrn) {
		this.fntprdtrnjrn = fntprdtrnjrn;
	}
}
