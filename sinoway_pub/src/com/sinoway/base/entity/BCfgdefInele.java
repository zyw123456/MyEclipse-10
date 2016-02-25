package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefInele extends Entity{

	private static final long serialVersionUID = 904154036314L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefInele() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  elecod String 字段ELECOD
	 */
	private String elecod;
	/**
	 *  pelecod String 字段PELECOD
	 */
	private String pelecod;
	/**
	 *  elenam String 字段ELENAM
	 */
	private String elenam;
	/**
	 *  elealinam String 字段ELEALINAM
	 */
	private String elealinam;
	/**
	 *  sta String 字段0:启用，1:停用
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
	* 返回ELECOD属性的值
	* @return String
	*/ 
	public String getElecod() {
		return this.elecod;
	}
	/**
	 * 设置elecod的值
	 * @param elecod String
	 */
	public void setElecod(String elecod) {
		this.elecod = elecod == null ? null : elecod.trim();
	}
	/**
	* 返回PELECOD属性的值
	* @return String
	*/ 
	public String getPelecod() {
		return this.pelecod;
	}
	/**
	 * 设置pelecod的值
	 * @param pelecod String
	 */
	public void setPelecod(String pelecod) {
		this.pelecod = pelecod == null ? null : pelecod.trim();
	}
	/**
	* 返回ELENAM属性的值
	* @return String
	*/ 
	public String getElenam() {
		return this.elenam;
	}
	/**
	 * 设置elenam的值
	 * @param elenam String
	 */
	public void setElenam(String elenam) {
		this.elenam = elenam == null ? null : elenam.trim();
	}
	/**
	* 返回ELEALINAM属性的值
	* @return String
	*/ 
	public String getElealinam() {
		return this.elealinam;
	}
	/**
	 * 设置elealinam的值
	 * @param elealinam String
	 */
	public void setElealinam(String elealinam) {
		this.elealinam = elealinam == null ? null : elealinam.trim();
	}
	/**
	* 返回0:启用，1:停用属性的值
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
