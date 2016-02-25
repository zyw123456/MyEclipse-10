package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefPrdtrnchnl extends Entity{

	private static final long serialVersionUID = 146677621843L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefPrdtrnchnl() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  prdtrncod String 字段PRDTRNCOD
	 */
	private String prdtrncod;
	/**
	 *  mtatrncod String 字段MTATRNCOD
	 */
	private String mtatrncod;
	/**
	 *  chnlcod String 字段CHNLCOD
	 */
	private String chnlcod;
	
	private String nodecod;
	
	private String usrid;
	/**
	 *  levels String 字段LEVELS
	 */
	private String levels;
	/**
	 *  sta String 字段0、停用    1、启用
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
	* 返回PRDTRNCOD属性的值
	* @return String
	*/ 
	public String getPrdtrncod() {
		return this.prdtrncod;
	}
	/**
	 * 设置prdtrncod的值
	 * @param prdtrncod String
	 */
	public void setPrdtrncod(String prdtrncod) {
		this.prdtrncod = prdtrncod == null ? null : prdtrncod.trim();
	}
	/**
	* 返回MTATRNCOD属性的值
	* @return String
	*/ 
	public String getMtatrncod() {
		return this.mtatrncod;
	}
	/**
	 * 设置mtatrncod的值
	 * @param mtatrncod String
	 */
	public void setMtatrncod(String mtatrncod) {
		this.mtatrncod = mtatrncod == null ? null : mtatrncod.trim();
	}
	/**
	* 返回CHNLCOD属性的值
	* @return String
	*/ 
	public String getChnlcod() {
		return this.chnlcod;
	}
	/**
	 * 设置chnlcod的值
	 * @param chnlcod String
	 */
	public void setChnlcod(String chnlcod) {
		this.chnlcod = chnlcod == null ? null : chnlcod.trim();
	}
	/**
	* 返回LEVELS属性的值
	* @return String
	*/ 
	public String getLevels() {
		return this.levels;
	}
	/**
	 * 设置levels的值
	 * @param levels String
	 */
	public void setLevels(String levels) {
		this.levels = levels == null ? null : levels.trim();
	}
	/**
	* 返回0、停用
1、启用属性的值
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
	public String getNodecod() {
		return nodecod;
	}
	public void setNodecod(String nodecod) {
		this.nodecod = nodecod;
	}
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	
}
