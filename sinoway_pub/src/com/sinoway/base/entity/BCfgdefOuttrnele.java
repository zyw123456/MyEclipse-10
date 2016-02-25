package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;


/**
 * 外部要素定义配置信息
 * @author THINK
 */
public class BCfgdefOuttrnele extends Entity{
	
	private static final long serialVersionUID = 3060005899985922435L;

	/**
     * 缺省构造函数
     */
 	public BCfgdefOuttrnele() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  outelecod String 字段OUTELECOD
	 */
	private String outelecod;
	/**
	 *  outelenam String 字段OUTELENAM
	 */
	private String outelenam;
	/**
	 *  sta String 字段0-停用 1-启用
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
	* 返回OUTELECOD属性的值
	* @return String
	*/ 
	public String getOutelecod() {
		return this.outelecod;
	}
	/**
	 * 设置outelecod的值
	 * @param outelecod String
	 */
	public void setOutelecod(String outelecod) {
		this.outelecod = outelecod == null ? null : outelecod.trim();
	}
	/**
	* 返回OUTELENAM属性的值
	* @return String
	*/ 
	public String getOutelenam() {
		return this.outelenam;
	}
	/**
	 * 设置outelenam的值
	 * @param outelenam String
	 */
	public void setOutelenam(String outelenam) {
		this.outelenam = outelenam == null ? null : outelenam.trim();
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

	


	
}
