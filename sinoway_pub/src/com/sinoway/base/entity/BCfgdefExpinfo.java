package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefExpinfo extends Entity{

	private static final long serialVersionUID = 570102423247L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefExpinfo() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  expcod String 字段EXPCOD
	 */
	private String expcod;
	/**
	 *  expdesc String 字段EXPDESC
	 */
	private String expdesc;
	/**
	 *  exptype String 字段0-内部异常 1-外部响应异常
	 */
	private String exptype;



	
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
	* 返回EXPCOD属性的值
	* @return String
	*/ 
	public String getExpcod() {
		return this.expcod;
	}
	/**
	 * 设置expcod的值
	 * @param expcod String
	 */
	public void setExpcod(String expcod) {
		this.expcod = expcod == null ? null : expcod.trim();
	}
	/**
	* 返回EXPDESC属性的值
	* @return String
	*/ 
	public String getExpdesc() {
		return this.expdesc;
	}
	/**
	 * 设置expdesc的值
	 * @param expdesc String
	 */
	public void setExpdesc(String expdesc) {
		this.expdesc = expdesc == null ? null : expdesc.trim();
	}
	/**
	* 返回0-内部异常 1-外部响应异常属性的值
	* @return String
	*/ 
	public String getExptype() {
		return this.exptype;
	}
	/**
	 * 设置exptype的值
	 * @param exptype String
	 */
	public void setExptype(String exptype) {
		this.exptype = exptype == null ? null : exptype.trim();
	}

	


	
}
