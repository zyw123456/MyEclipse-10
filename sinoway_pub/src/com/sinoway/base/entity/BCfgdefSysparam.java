package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefSysparam extends Entity{

	private static final long serialVersionUID = 678362389455L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefSysparam() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  paramcod String 字段PARAMCOD
	 */
	private String paramcod;
	/**
	 *  paramnam String 字段PARAMNAM
	 */
	private String paramnam;
	/**
	 *  paramval String 字段PARAMVAL
	 */
	private String paramval;
	/**
	 *  memo String 字段MEMO
	 */
	private String memo;



	
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
	* 返回PARAMCOD属性的值
	* @return String
	*/ 
	public String getParamcod() {
		return this.paramcod;
	}
	/**
	 * 设置paramcod的值
	 * @param paramcod String
	 */
	public void setParamcod(String paramcod) {
		this.paramcod = paramcod == null ? null : paramcod.trim();
	}
	/**
	* 返回PARAMNAM属性的值
	* @return String
	*/ 
	public String getParamnam() {
		return this.paramnam;
	}
	/**
	 * 设置paramnam的值
	 * @param paramnam String
	 */
	public void setParamnam(String paramnam) {
		this.paramnam = paramnam == null ? null : paramnam.trim();
	}
	/**
	* 返回PARAMVAL属性的值
	* @return String
	*/ 
	public String getParamval() {
		return this.paramval;
	}
	/**
	 * 设置paramval的值
	 * @param paramval String
	 */
	public void setParamval(String paramval) {
		this.paramval = paramval == null ? null : paramval.trim();
	}
	/**
	* 返回MEMO属性的值
	* @return String
	*/ 
	public String getMemo() {
		return this.memo;
	}
	/**
	 * 设置memo的值
	 * @param memo String
	 */
	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	


	
}
