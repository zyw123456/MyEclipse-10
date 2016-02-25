package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefMutxrole extends Entity{

	private static final long serialVersionUID = 292141347728L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefMutxrole() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  rolecod String 字段ROLECOD
	 */
	private String rolecod;
	/**
	 *  muexrole String 字段MUEXROLE
	 */
	private String muexrole;
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
	* 返回MUEXROLE属性的值
	* @return String
	*/ 
	public String getMuexrole() {
		return this.muexrole;
	}
	/**
	 * 设置muexrole的值
	 * @param muexrole String
	 */
	public void setMuexrole(String muexrole) {
		this.muexrole = muexrole == null ? null : muexrole.trim();
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
