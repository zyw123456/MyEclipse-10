package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgrefInoutele extends Entity{

	private static final long serialVersionUID = 263142191291L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgrefInoutele() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  chnlcod String 字段CHNLCOD
	 */
	private String chnlcod;
	/**
	 *  intrncod String 字段包括：原交易编码、子交易编码
	 */
	private String intrncod;
	/**
	 *  outelecod String 字段OUTELECOD
	 */
	private String outelecod;
	/**
	 *  inelecod String 字段INELECOD
	 */
	private String inelecod;
	/**
	 *  dtype String 字段0-数字 1-金额 2-字符 3-日期 4-复合
	 */
	private String dtype;
	/**
	 *  dmaxlen String 字段DMAXLEN
	 */
	private String dmaxlen;
	/**
	 *  dminlen String 字段DMINLEN
	 */
	private String dminlen;
	/**
	 *  isnul String 字段0-可以 1-不可以
	 */
	private String isnul;
	/**
	 *  iotype String 字段0-输出 1-输入 2-输入输出
	 */
	private String iotype;
	/**
	 *  expelecod String 字段EXPELECOD
	 */
	private String expelecod;
	/**
	 *  inpelecod String 字段INPELECOD
	 */
	private String inpelecod;
	/**
	 *  sta String 字段STA
	 */
	private String sta;
	/**
	 *  trntyp String 字段0-原交易 1-子交易
	 */
	private String trntyp;



	
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
	* 返回包括：原交易编码、子交易编码属性的值
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
	* 返回INELECOD属性的值
	* @return String
	*/ 
	public String getInelecod() {
		return this.inelecod;
	}
	/**
	 * 设置inelecod的值
	 * @param inelecod String
	 */
	public void setInelecod(String inelecod) {
		this.inelecod = inelecod == null ? null : inelecod.trim();
	}
	/**
	* 返回0-数字 1-金额 2-字符 3-日期 4-复合属性的值
	* @return String
	*/ 
	public String getDtype() {
		return this.dtype;
	}
	/**
	 * 设置dtype的值
	 * @param dtype String
	 */
	public void setDtype(String dtype) {
		this.dtype = dtype == null ? null : dtype.trim();
	}
	/**
	* 返回DMAXLEN属性的值
	* @return String
	*/ 
	public String getDmaxlen() {
		return this.dmaxlen;
	}
	/**
	 * 设置dmaxlen的值
	 * @param dmaxlen String
	 */
	public void setDmaxlen(String dmaxlen) {
		this.dmaxlen = dmaxlen == null ? null : dmaxlen.trim();
	}
	/**
	* 返回DMINLEN属性的值
	* @return String
	*/ 
	public String getDminlen() {
		return this.dminlen;
	}
	/**
	 * 设置dminlen的值
	 * @param dminlen String
	 */
	public void setDminlen(String dminlen) {
		this.dminlen = dminlen == null ? null : dminlen.trim();
	}
	/**
	* 返回0-可以 1-不可以属性的值
	* @return String
	*/ 
	public String getIsnul() {
		return this.isnul;
	}
	/**
	 * 设置isnul的值
	 * @param isnul String
	 */
	public void setIsnul(String isnul) {
		this.isnul = isnul == null ? null : isnul.trim();
	}
	/**
	* 返回0-输出 1-输入 2-输入输出属性的值
	* @return String
	*/ 
	public String getIotype() {
		return this.iotype;
	}
	/**
	 * 设置iotype的值
	 * @param iotype String
	 */
	public void setIotype(String iotype) {
		this.iotype = iotype == null ? null : iotype.trim();
	}
	/**
	* 返回EXPELECOD属性的值
	* @return String
	*/ 
	public String getExpelecod() {
		return this.expelecod;
	}
	/**
	 * 设置expelecod的值
	 * @param expelecod String
	 */
	public void setExpelecod(String expelecod) {
		this.expelecod = expelecod == null ? null : expelecod.trim();
	}
	/**
	* 返回INPELECOD属性的值
	* @return String
	*/ 
	public String getInpelecod() {
		return this.inpelecod;
	}
	/**
	 * 设置inpelecod的值
	 * @param inpelecod String
	 */
	public void setInpelecod(String inpelecod) {
		this.inpelecod = inpelecod == null ? null : inpelecod.trim();
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

	


	
}
