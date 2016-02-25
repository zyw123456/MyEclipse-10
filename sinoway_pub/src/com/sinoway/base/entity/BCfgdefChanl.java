package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

/**
 * 渠道实体
 * @author THINK
 */
public class BCfgdefChanl extends Entity{

	private static final long serialVersionUID = 894862749409L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefChanl() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  chnlcod String 字段CHNLCOD
	 */
	private String chnlcod;
	/**
	 *  chnlnam String 字段CHNLNAM
	 */
	private String chnlnam;
	/**
	 *  chnltype String 字段0-上游渠道 1-下游渠道
	 */
	private String chnltype;
	/**
	 *  sta String 字段0-停用 1-启用
	 */
	private String sta;
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
	* 返回CHNLNAM属性的值
	* @return String
	*/ 
	public String getChnlnam() {
		return this.chnlnam;
	}
	/**
	 * 设置chnlnam的值
	 * @param chnlnam String
	 */
	public void setChnlnam(String chnlnam) {
		this.chnlnam = chnlnam == null ? null : chnlnam.trim();
	}
	/**
	* 返回0-上游渠道 1-下游渠道属性的值
	* @return String
	*/ 
	public String getChnltype() {
		return this.chnltype;
	}
	/**
	 * 设置chnltype的值
	 * @param chnltype String
	 */
	public void setChnltype(String chnltype) {
		this.chnltype = chnltype == null ? null : chnltype.trim();
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
