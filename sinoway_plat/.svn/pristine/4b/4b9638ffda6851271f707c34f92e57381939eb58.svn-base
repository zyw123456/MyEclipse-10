package com.sinoway.wrn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 征信预警产品与明细对照关系表:WF_DAT_CERDITWARNDTELREF
 * @author wanglongmei
 *
 */
@Entity
@Table(name = "WF_DAT_CERDITWARNDTELREF")
public class WfDatCerditWarnDtelRef implements java.io.Serializable {

	private static final long serialVersionUID = -7225683070938429472L;

	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 报告编号
	 */
	private String warnId;
	
	/**
	 * 明细流水号
	 */
	private String warnDtlJrn;
	
	/**
	 * 状态
	 */
	private String sta;

	@Id 
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="WARNID")
	public String getWarnId() {
		return warnId;
	}

	public void setWarnId(String warnId) {
		this.warnId = warnId;
	}

	@Column(name="WARNDTLJRN")
	public String getWarnDtlJrn() {
		return warnDtlJrn;
	}

	public void setWarnDtlJrn(String warnDtlJrn) {
		this.warnDtlJrn = warnDtlJrn;
	}

	@Column(name="STA")
	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}
	
	
}
