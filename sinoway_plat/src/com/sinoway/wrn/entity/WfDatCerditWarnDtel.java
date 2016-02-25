package com.sinoway.wrn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 征信预警产品明细表
 * @author wanglongmei
 *
 */
public class WfDatCerditWarnDtel implements java.io.Serializable {

	private static final long serialVersionUID = -6001111303762605114L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 明细编码
	 */
	
	private String warnDtlJrn;
	
	/**
	 * 原交易编码
	 */
	private String trnCod;
	
	/**
	 * 原交易名称
	 */
	private String trnNam;
	
	/**
	 * 姓名
	 */
	private String prsnNam;
	
	/**
	 * 身份证号
	 */
	private String credtNo;
	
	/**
	 * 数据来源
	 */
	private String datOri;
	
	/**
	 * 可信度
	 */
	private String reality;
	
	/**
	 * 监控时间
	 */
	private String warnTim;
	
	/**
	 * 监控日期
	 */
	private String warnDte;
	
	/**
	 * 接收日期
	 */
	private String recvDte;
	
	/**
	 * 接收时间
	 */
	private String recvTim;
	
	/**
	 * 预警明细
	 */
	private String warnDtls;

	public WfDatCerditWarnDtel() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarnDtlJrn() {
		return warnDtlJrn;
	}

	public void setWarnDtlJrn(String warnDtlJrn) {
		this.warnDtlJrn = warnDtlJrn;
	}

	public String getTrnCod() {
		return trnCod;
	}

	public void setTrnCod(String trnCod) {
		this.trnCod = trnCod;
	}

	public String getTrnNam() {
		return trnNam;
	}

	public void setTrnNam(String trnNam) {
		this.trnNam = trnNam;
	}

	public String getPrsnNam() {
		return prsnNam;
	}

	public void setPrsnNam(String prsnNam) {
		this.prsnNam = prsnNam;
	}

	public String getCredtNo() {
		return credtNo;
	}

	public void setCredtNo(String credtNo) {
		this.credtNo = credtNo;
	}

	public String getDatOri() {
		return datOri;
	}

	public void setDatOri(String datOri) {
		this.datOri = datOri;
	}

	public String getReality() {
		return reality;
	}

	public void setReality(String reality) {
		this.reality = reality;
	}

	public String getWarnTim() {
		return warnTim;
	}

	public void setWarnTim(String warnTim) {
		this.warnTim = warnTim;
	}

	public String getWarnDte() {
		return warnDte;
	}

	public void setWarnDte(String warnDte) {
		this.warnDte = warnDte;
	}

	public String getRecvDte() {
		return recvDte;
	}

	public void setRecvDte(String recvDte) {
		this.recvDte = recvDte;
	}

	public String getRecvTim() {
		return recvTim;
	}

	public void setRecvTim(String recvTim) {
		this.recvTim = recvTim;
	}

	public String getWarnDtls() {
		return warnDtls;
	}

	public void setWarnDtls(String warnDtls) {
		this.warnDtls = warnDtls;
	}

	
	
}
