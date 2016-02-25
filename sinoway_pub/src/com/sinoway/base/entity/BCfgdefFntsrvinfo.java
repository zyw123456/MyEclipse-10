package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefFntsrvinfo extends Entity{

	private static final long serialVersionUID = 821499549016L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefFntsrvinfo() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  servcod String 字段SERVCOD
	 */
	private String servcod;
	/**
	 *  servnam String 字段SERVNAM
	 */
	private String servnam;
	/**
	 *  servip String 字段SERVIP
	 */
	private String servip;
	/**
	 *  mainport String 字段MAINPORT
	 */
	private String mainport;
	/**
	 *  reqmsgurl String 字段REQMSGURL
	 */
	private String reqmsgurl;
	/**
	 *  resmsgurl String 字段RESMSGURL
	 */
	private String resmsgurl;
	/**
	 *  filemsgurl String 字段FILEMSGURL
	 */
	private String filemsgurl;
	/**
	 *  filestorurl String 字段FILESTORURL
	 */
	private String filestorurl;
	/**
	 *  sta String 字段0-停用 1-启用
	 */
	private String sta;
	/**
	 *  servsta String 字段0-异常 1-正常
	 */
	private String servsta;
	/**
	 *  confileurl String 字段CONFILEURL
	 */
	private String confileurl;



	
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
	* 返回SERVCOD属性的值
	* @return String
	*/ 
	public String getServcod() {
		return this.servcod;
	}
	/**
	 * 设置servcod的值
	 * @param servcod String
	 */
	public void setServcod(String servcod) {
		this.servcod = servcod == null ? null : servcod.trim();
	}
	/**
	* 返回SERVNAM属性的值
	* @return String
	*/ 
	public String getServnam() {
		return this.servnam;
	}
	/**
	 * 设置servnam的值
	 * @param servnam String
	 */
	public void setServnam(String servnam) {
		this.servnam = servnam == null ? null : servnam.trim();
	}
	/**
	* 返回SERVIP属性的值
	* @return String
	*/ 
	public String getServip() {
		return this.servip;
	}
	/**
	 * 设置servip的值
	 * @param servip String
	 */
	public void setServip(String servip) {
		this.servip = servip == null ? null : servip.trim();
	}
	/**
	* 返回MAINPORT属性的值
	* @return String
	*/ 
	public String getMainport() {
		return this.mainport;
	}
	/**
	 * 设置mainport的值
	 * @param mainport String
	 */
	public void setMainport(String mainport) {
		this.mainport = mainport == null ? null : mainport.trim();
	}
	/**
	* 返回REQMSGURL属性的值
	* @return String
	*/ 
	public String getReqmsgurl() {
		return this.reqmsgurl;
	}
	/**
	 * 设置reqmsgurl的值
	 * @param reqmsgurl String
	 */
	public void setReqmsgurl(String reqmsgurl) {
		this.reqmsgurl = reqmsgurl == null ? null : reqmsgurl.trim();
	}
	/**
	* 返回RESMSGURL属性的值
	* @return String
	*/ 
	public String getResmsgurl() {
		return this.resmsgurl;
	}
	/**
	 * 设置resmsgurl的值
	 * @param resmsgurl String
	 */
	public void setResmsgurl(String resmsgurl) {
		this.resmsgurl = resmsgurl == null ? null : resmsgurl.trim();
	}
	/**
	* 返回FILEMSGURL属性的值
	* @return String
	*/ 
	public String getFilemsgurl() {
		return this.filemsgurl;
	}
	/**
	 * 设置filemsgurl的值
	 * @param filemsgurl String
	 */
	public void setFilemsgurl(String filemsgurl) {
		this.filemsgurl = filemsgurl == null ? null : filemsgurl.trim();
	}
	/**
	* 返回FILESTORURL属性的值
	* @return String
	*/ 
	public String getFilestorurl() {
		return this.filestorurl;
	}
	/**
	 * 设置filestorurl的值
	 * @param filestorurl String
	 */
	public void setFilestorurl(String filestorurl) {
		this.filestorurl = filestorurl == null ? null : filestorurl.trim();
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
	* 返回0-异常 1-正常属性的值
	* @return String
	*/ 
	public String getServsta() {
		return this.servsta;
	}
	/**
	 * 设置servsta的值
	 * @param servsta String
	 */
	public void setServsta(String servsta) {
		this.servsta = servsta == null ? null : servsta.trim();
	}
	/**
	* 返回CONFILEURL属性的值
	* @return String
	*/ 
	public String getConfileurl() {
		return this.confileurl;
	}
	/**
	 * 设置confileurl的值
	 * @param confileurl String
	 */
	public void setConfileurl(String confileurl) {
		this.confileurl = confileurl == null ? null : confileurl.trim();
	}

	


	
}
