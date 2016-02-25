package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefUsrrole extends Entity{

	private static final long serialVersionUID = 374413829271L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefUsrrole() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  rolecod String 字段ROLECOD
	 */
	private String rolecod;
	/**
	 *  usrid String 字段USRID
	 */
	private String usrid;
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
