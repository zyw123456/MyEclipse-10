package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefChnlblack extends Entity{

	private static final long serialVersionUID = 209691334685L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefChnlblack() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  chnlcod String 字段CHNLCOD
	 */
	private String chnlcod;
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
