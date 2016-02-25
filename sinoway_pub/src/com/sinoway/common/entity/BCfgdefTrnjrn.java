package com.sinoway.common.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefTrnjrn extends Entity{

	private static final long serialVersionUID = 863264399849L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefTrnjrn() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  syscod String 字段SYSCOD
	 */
	private String syscod;
	/**
	 *  jrndte String 字段JRNDTE
	 */
	private String jrndte;
	/**
	 *  jrnval String 字段JRNVAL
	 */
	private String jrnval;
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
	* 返回SYSCOD属性的值
	* @return String
	*/ 
	public String getSyscod() {
		return this.syscod;
	}
	/**
	 * 设置syscod的值
	 * @param syscod String
	 */
	public void setSyscod(String syscod) {
		this.syscod = syscod == null ? null : syscod.trim();
	}
	/**
	* 返回JRNDTE属性的值
	* @return String
	*/ 
	public String getJrndte() {
		return this.jrndte;
	}
	/**
	 * 设置jrndte的值
	 * @param jrndte String
	 */
	public void setJrndte(String jrndte) {
		this.jrndte = jrndte == null ? null : jrndte.trim();
	}
	/**
	* 返回JRNVAL属性的值
	* @return String
	*/ 
	public String getJrnval() {
		return this.jrnval;
	}
	/**
	 * 设置jrnval的值
	 * @param jrnval String
	 */
	public void setJrnval(String jrnval) {
		this.jrnval = jrnval == null ? null : jrnval.trim();
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
