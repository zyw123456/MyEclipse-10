package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefRoletrn extends Entity{

	private static final long serialVersionUID = 719277554384L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefRoletrn() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  rolecod String 字段ROLECOD
	 */
	private String rolecod;
	/**
	 *  trncod String 字段TRNCOD
	 */
	private String trncod;
	/**
	 *  sta String 字段STA
	 */
	private String sta;
	/**
	 *  sort String 字段SORT
	 */
	private String sort;



	
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
	* 返回ROLECOD属性的值
	* @return String
	*/ 
	public String getRolecod() {
		return this.rolecod;
	}
	/**
	 * 设置rolecod的值
	 * @param rolecod String
	 */
	public void setRolecod(String rolecod) {
		this.rolecod = rolecod == null ? null : rolecod.trim();
	}
	/**
	* 返回TRNCOD属性的值
	* @return String
	*/ 
	public String getTrncod() {
		return this.trncod;
	}
	/**
	 * 设置trncod的值
	 * @param trncod String
	 */
	public void setTrncod(String trncod) {
		this.trncod = trncod == null ? null : trncod.trim();
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
	/**
	* 返回SORT属性的值
	* @return String
	*/ 
	public String getSort() {
		return this.sort;
	}
	/**
	 * 设置sort的值
	 * @param sort String
	 */
	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}

	


	
}
