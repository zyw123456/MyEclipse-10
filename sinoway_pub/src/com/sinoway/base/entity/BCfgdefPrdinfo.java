package com.sinoway.base.entity;

import java.util.List;

import com.sinoway.common.frame.Entity;

public class BCfgdefPrdinfo extends Entity{

	private static final long serialVersionUID = 426113685023L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefPrdinfo() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  prdcod String 字段PRDCOD
	 */
	private String prdcod;
	/**
	 *  prdnam String 字段PRDNAM
	 */
	private String prdnam;
	/**
	 *  appcod String 字段APPCOD
	 */
	private String appcod;
	/**
	 *  appnam String 字段APPNAM
	 */
	private String appnam;
	/**
	 *  prdtyp String 字段验证类产品；
个人报告类；
反欺诈报告类；
天警云类
金融类数据监测
电商类数据监测
个人被查询类数据监测
公共信息类数据监测
	 */
	private String prdtyp;
	/**
	 *  isdefult String 字段0、不是默认产品
1、是默认产品
	 */
	private String isdefult;
	/**
	 *  parntcod String 字段PARNTCOD
	 */
	private String parntcod;
	/**
	 *  cretday String 字段CRETDAY
	 */
	private String cretday;
	/**
	 *  sta String 字段0:启用，1:停用
	 */
	private String sta;
	/**
	 *  memo String 字段MEMO
	 */
	private String memo;

	/**
	 * 产品明细列表
	 */
	private List<BCfgrefPrddetil> detilList;


	public List<BCfgrefPrddetil> getDetilList() {
		return detilList;
	}
	public void setDetilList(List<BCfgrefPrddetil> detilList) {
		this.detilList = detilList;
	}
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
	* 返回PRDNAM属性的值
	* @return String
	*/ 
	public String getPrdnam() {
		return this.prdnam;
	}
	/**
	 * 设置prdnam的值
	 * @param prdnam String
	 */
	public void setPrdnam(String prdnam) {
		this.prdnam = prdnam == null ? null : prdnam.trim();
	}
	/**
	* 返回APPCOD属性的值
	* @return String
	*/ 
	public String getAppcod() {
		return this.appcod;
	}
	/**
	 * 设置appcod的值
	 * @param appcod String
	 */
	public void setAppcod(String appcod) {
		this.appcod = appcod == null ? null : appcod.trim();
	}
	/**
	* 返回APPNAM属性的值
	* @return String
	*/ 
	public String getAppnam() {
		return this.appnam;
	}
	/**
	 * 设置appnam的值
	 * @param appnam String
	 */
	public void setAppnam(String appnam) {
		this.appnam = appnam == null ? null : appnam.trim();
	}
	/**
	* 返回验证类产品；
个人报告类；
反欺诈报告类；
天警云类
金融类数据监测
电商类数据监测
个人被查询类数据监测
公共信息类数据监测属性的值
	* @return String
	*/ 
	public String getPrdtyp() {
		return this.prdtyp;
	}
	/**
	 * 设置prdtyp的值
	 * @param prdtyp String
	 */
	public void setPrdtyp(String prdtyp) {
		this.prdtyp = prdtyp == null ? null : prdtyp.trim();
	}
	/**
	* 返回0、不是默认产品
1、是默认产品属性的值
	* @return String
	*/ 
	public String getIsdefult() {
		return this.isdefult;
	}
	/**
	 * 设置isdefult的值
	 * @param isdefult String
	 */
	public void setIsdefult(String isdefult) {
		this.isdefult = isdefult == null ? null : isdefult.trim();
	}
	/**
	* 返回PARNTCOD属性的值
	* @return String
	*/ 
	public String getParntcod() {
		return this.parntcod;
	}
	/**
	 * 设置parntcod的值
	 * @param parntcod String
	 */
	public void setParntcod(String parntcod) {
		this.parntcod = parntcod == null ? null : parntcod.trim();
	}
	/**
	* 返回CRETDAY属性的值
	* @return String
	*/ 
	public String getCretday() {
		return this.cretday;
	}
	/**
	 * 设置cretday的值
	 * @param cretday String
	 */
	public void setCretday(String cretday) {
		this.cretday = cretday == null ? null : cretday.trim();
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
