package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefChnltrnauth extends Entity{

	private static final long serialVersionUID = 70581634617L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefChnltrnauth() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  cfgcod String 字段CFGCOD
	 */
	private String cfgcod;
	/**
	 *  chnlcod String 字段CHNLCOD
	 */
	private String chnlcod;
	/**
	 *  trnip String 字段多个IP用 | 隔开
	 */
	private String trnip;
	/**
	 *  intrncod String 字段多个交易码用 | 隔开，*表示所有
	 */
	private String intrncod;
	/**
	 *  prdcod String 字段多个产品码用 | 隔开，*表示所有
	 */
	private String prdcod;
	/**
	 *  trntyp String 字段0-原交易 1-子交易
	 */
	private String trntyp;
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
	* 返回CFGCOD属性的值
	* @return String
	*/ 
	public String getCfgcod() {
		return this.cfgcod;
	}
	/**
	 * 设置cfgcod的值
	 * @param cfgcod String
	 */
	public void setCfgcod(String cfgcod) {
		this.cfgcod = cfgcod == null ? null : cfgcod.trim();
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
	* 返回多个IP用 | 隔开属性的值
	* @return String
	*/ 
	public String getTrnip() {
		return this.trnip;
	}
	/**
	 * 设置trnip的值
	 * @param trnip String
	 */
	public void setTrnip(String trnip) {
		this.trnip = trnip == null ? null : trnip.trim();
	}
	/**
	* 返回多个交易码用 | 隔开，*表示所有属性的值
	* @return String
	*/ 
	public String getIntrncod() {
		return this.intrncod;
	}
	/**
	 * 设置intrncod的值
	 * @param intrncod String
	 */
	public void setIntrncod(String intrncod) {
		this.intrncod = intrncod == null ? null : intrncod.trim();
	}
	/**
	* 返回多个产品码用 | 隔开，*表示所有属性的值
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
	* 返回0-原交易 1-子交易属性的值
	* @return String
	*/ 
	public String getTrntyp() {
		return this.trntyp;
	}
	/**
	 * 设置trntyp的值
	 * @param trntyp String
	 */
	public void setTrntyp(String trntyp) {
		this.trntyp = trntyp == null ? null : trntyp.trim();
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
