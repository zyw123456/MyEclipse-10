package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefTrnblack extends Entity{

	private static final long serialVersionUID = 981110401946L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefTrnblack() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  trncod String 字段TRNCOD
	 */
	private String trncod;
	/**
	 *  trnnam String 字段TRNNAM
	 */
	private String trnnam;
	/**
	 *  handupdte String 字段HANDUPDTE
	 */
	private String handupdte;
	/**
	 *  handuptim String 字段HANDUPTIM
	 */
	private String handuptim;
	/**
	 *  duration String 字段DURATION
	 */
	private String duration;
	/**
	 *  sta String 字段1表示使用中
0表示未使用
-1表示已销毁
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
	* 返回TRNNAM属性的值
	* @return String
	*/ 
	public String getTrnnam() {
		return this.trnnam;
	}
	/**
	 * 设置trnnam的值
	 * @param trnnam String
	 */
	public void setTrnnam(String trnnam) {
		this.trnnam = trnnam == null ? null : trnnam.trim();
	}
	/**
	* 返回HANDUPDTE属性的值
	* @return String
	*/ 
	public String getHandupdte() {
		return this.handupdte;
	}
	/**
	 * 设置handupdte的值
	 * @param handupdte String
	 */
	public void setHandupdte(String handupdte) {
		this.handupdte = handupdte == null ? null : handupdte.trim();
	}
	/**
	* 返回HANDUPTIM属性的值
	* @return String
	*/ 
	public String getHanduptim() {
		return this.handuptim;
	}
	/**
	 * 设置handuptim的值
	 * @param handuptim String
	 */
	public void setHanduptim(String handuptim) {
		this.handuptim = handuptim == null ? null : handuptim.trim();
	}
	/**
	* 返回DURATION属性的值
	* @return String
	*/ 
	public String getDuration() {
		return this.duration;
	}
	/**
	 * 设置duration的值
	 * @param duration String
	 */
	public void setDuration(String duration) {
		this.duration = duration == null ? null : duration.trim();
	}
	/**
	* 返回1表示使用中
0表示未使用
-1表示已销毁属性的值
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
