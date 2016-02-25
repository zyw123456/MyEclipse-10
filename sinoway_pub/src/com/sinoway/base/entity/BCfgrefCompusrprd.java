package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefCompusrprd extends Entity{

	private static final long serialVersionUID = 337334238283L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefCompusrprd() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  usrid String 字段USRID
	 */
	private String usrid;
	/**
	 *  prdcod String 字段PRDCOD
	 */
	private String prdcod;
	/**
	 *  usrtype String 字段企业用户：200
	 */
	private String usrtype;
	/**
	 *  isdisp String 字段0、不显示
1、显示明细
	 */
	private String isdisp;
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
	* 返回PRDCOD属性的值
	* @return String
	*/ 
	public String getPrdcod() {
		return this.prdcod;
	}
	/**
	 * 设置prdcod的值
	 * @param prdcod String
	 */
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod == null ? null : prdcod.trim();
	}
	/**
	* 返回企业用户：200属性的值
	* @return String
	*/ 
	public String getUsrtype() {
		return this.usrtype;
	}
	/**
	 * 设置usrtype的值
	 * @param usrtype String
	 */
	public void setUsrtype(String usrtype) {
		this.usrtype = usrtype == null ? null : usrtype.trim();
	}
	/**
	* 返回0、不显示
1、显示明细属性的值
	* @return String
	*/ 
	public String getIsdisp() {
		return this.isdisp;
	}
	/**
	 * 设置isdisp的值
	 * @param isdisp String
	 */
	public void setIsdisp(String isdisp) {
		this.isdisp = isdisp == null ? null : isdisp.trim();
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
