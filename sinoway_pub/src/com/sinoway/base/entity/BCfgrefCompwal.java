package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefCompwal extends Entity{

	private static final long serialVersionUID = 193662593468L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefCompwal() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  usrid String 字段USRID
	 */
	private String usrid;
	/**
	 *  walid String 字段WALID
	 */
	private String walid;
	/**
	 *  sta String 字段STA
	 */
	private String sta;



	
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
	* 返回USRID属性的值
	* @return String
	*/ 
	public String getUsrid() {
		return this.usrid;
	}
	/**
	 * 设置usrid的值
	 * @param usrid String
	 */
	public void setUsrid(String usrid) {
		this.usrid = usrid == null ? null : usrid.trim();
	}
	/**
	* 返回WALID属性的值
	* @return String
	*/ 
	public String getWalid() {
		return this.walid;
	}
	/**
	 * 设置walid的值
	 * @param walid String
	 */
	public void setWalid(String walid) {
		this.walid = walid == null ? null : walid.trim();
	}
	/**
	* 返回STA属性的值
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

	


	
}
