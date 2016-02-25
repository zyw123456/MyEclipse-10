package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefFntinouttrn extends Entity{

	private static final long serialVersionUID = 840489225205L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefFntinouttrn() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  intrncod String 字段包括：原交易编码、子交易编码
	 */
	private String intrncod;
	/**
	 *  outtrncod String 字段OUTTRNCOD
	 */
	private String outtrncod;
	/**
	 *  sta String 字段0-停用 1-启用
	 */
	private String sta;
	/**
	 *  trntyp String 字段0-原交易 1-子交易
	 */
	private String trntyp;



	
	/**
	* 返回ID属性的值
	* @return String
	*/ 
	public String getId() {
		return this.id;
	}
	/**
	 * 设置id的值
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	/**
	* 返回包括：原交易编码、子交易编码属性的值
	* @return String
	*/ 
	public String getIntrncod() {
		return this.intrncod;
	}
	/**
	 * 设置intrncod的值
	 * @param intrncod String
	 */
	public void setIntrncod(String intrncod) {
		this.intrncod = intrncod == null ? null : intrncod.trim();
	}
	/**
	* 返回OUTTRNCOD属性的值
	* @return String
	*/ 
	public String getOuttrncod() {
		return this.outtrncod;
	}
	/**
	 * 设置outtrncod的值
	 * @param outtrncod String
	 */
	public void setOuttrncod(String outtrncod) {
		this.outtrncod = outtrncod == null ? null : outtrncod.trim();
	}
	/**
	* 返回0-停用 1-启用属性的值
	* @return String
	*/ 
	public String getSta() {
		return this.sta;
	}
	/**
	 * 设置sta的值
	 * @param sta String
	 */
	public void setSta(String sta) {
		this.sta = sta == null ? null : sta.trim();
	}
	/**
	* 返回0-原交易 1-子交易属性的值
	* @return String
	*/ 
	public String getTrntyp() {
		return this.trntyp;
	}
	/**
	 * 设置trntyp的值
	 * @param trntyp String
	 */
	public void setTrntyp(String trntyp) {
		this.trntyp = trntyp == null ? null : trntyp.trim();
	}

	


	
}
