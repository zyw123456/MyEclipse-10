package com.sinoway.mcp.entity;

import com.sinoway.common.frame.Entity;

public class FDatTrnbtch extends Entity{

	private static final long serialVersionUID = 406560224735L;
	
	/**
     * 缺省构造函数
     */
 	public FDatTrnbtch() {}
 	
	/**
	 *  trnbtchid String 字段TRNBTCHID
	 */
	private String trnbtchid;
	/**
	 *  prdcod String 字段PRDCOD
	 */
	private String prdcod;
	/**
	 *  prdnam String 字段PRDNAM
	 */
	private String prdnam;
	/**
	 *  trncod String 字段TRNCOD
	 */
	private String trncod;
	/**
	 *  trnnam String 字段TRNNAM
	 */
	private String trnnam;
	/**
	 *  datori String 字段指下游客户发起报告请求的前端：
1、平台提交
2、底层接口
3、微信
4、APP
	 */
	private String datori;
	/**
	 *  btchdte String 字段BTCHDTE
	 */
	private String btchdte;
	/**
	 *  btchtim String 字段BTCHTIM
	 */
	private String btchtim;
	/**
	 *  prsnsum String 字段PRSNSUM
	 */
	private String prsnsum;
	/**
	 *  subusrid String 字段SUBUSRID
	 */
	private String subusrid;
	/**
	 *  orgno String 字段ORGNO
	 */
	private String orgno;
	/**
	 *  mainusrid String 字段MAINUSRID
	 */
	private String mainusrid;
	/**
	 *  btchsta String 字段0-未处理 1-处理中 2-处理完成 3-超时 4-异常
	 */
	private String btchsta;
	/**
	 *  donenum String 字段DONENUM
	 */
	private String donenum;



	
	/**
	* 返回TRNBTCHID属性的值
	* @return String
	*/ 
	public String getTrnbtchid() {
		return this.trnbtchid;
	}
	/**
	 * 设置trnbtchid的值
	 * @param trnbtchid String
	 */
	public void setTrnbtchid(String trnbtchid) {
		this.trnbtchid = trnbtchid == null ? null : trnbtchid.trim();
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
	* 返回指下游客户发起报告请求的前端：
1、平台提交
2、底层接口
3、微信
4、APP属性的值
	* @return String
	*/ 
	public String getDatori() {
		return this.datori;
	}
	/**
	 * 设置datori的值
	 * @param datori String
	 */
	public void setDatori(String datori) {
		this.datori = datori == null ? null : datori.trim();
	}
	/**
	* 返回BTCHDTE属性的值
	* @return String
	*/ 
	public String getBtchdte() {
		return this.btchdte;
	}
	/**
	 * 设置btchdte的值
	 * @param btchdte String
	 */
	public void setBtchdte(String btchdte) {
		this.btchdte = btchdte == null ? null : btchdte.trim();
	}
	/**
	* 返回BTCHTIM属性的值
	* @return String
	*/ 
	public String getBtchtim() {
		return this.btchtim;
	}
	/**
	 * 设置btchtim的值
	 * @param btchtim String
	 */
	public void setBtchtim(String btchtim) {
		this.btchtim = btchtim == null ? null : btchtim.trim();
	}
	/**
	* 返回PRSNSUM属性的值
	* @return String
	*/ 
	public String getPrsnsum() {
		return this.prsnsum;
	}
	/**
	 * 设置prsnsum的值
	 * @param prsnsum String
	 */
	public void setPrsnsum(String prsnsum) {
		this.prsnsum = prsnsum == null ? null : prsnsum.trim();
	}
	/**
	* 返回SUBUSRID属性的值
	* @return String
	*/ 
	public String getSubusrid() {
		return this.subusrid;
	}
	/**
	 * 设置subusrid的值
	 * @param subusrid String
	 */
	public void setSubusrid(String subusrid) {
		this.subusrid = subusrid == null ? null : subusrid.trim();
	}
	/**
	* 返回ORGNO属性的值
	* @return String
	*/ 
	public String getOrgno() {
		return this.orgno;
	}
	/**
	 * 设置orgno的值
	 * @param orgno String
	 */
	public void setOrgno(String orgno) {
		this.orgno = orgno == null ? null : orgno.trim();
	}
	/**
	* 返回MAINUSRID属性的值
	* @return String
	*/ 
	public String getMainusrid() {
		return this.mainusrid;
	}
	/**
	 * 设置mainusrid的值
	 * @param mainusrid String
	 */
	public void setMainusrid(String mainusrid) {
		this.mainusrid = mainusrid == null ? null : mainusrid.trim();
	}
	/**
	* 返回0-未处理 1-处理中 2-处理完成 3-超时 4-异常属性的值
	* @return String
	*/ 
	public String getBtchsta() {
		return this.btchsta;
	}
	/**
	 * 设置btchsta的值
	 * @param btchsta String
	 */
	public void setBtchsta(String btchsta) {
		this.btchsta = btchsta == null ? null : btchsta.trim();
	}
	/**
	* 返回DONENUM属性的值
	* @return String
	*/ 
	public String getDonenum() {
		return this.donenum;
	}
	/**
	 * 设置donenum的值
	 * @param donenum String
	 */
	public void setDonenum(String donenum) {
		this.donenum = donenum == null ? null : donenum.trim();
	}

	


	
}
