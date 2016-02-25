package com.sinoway.base.entity;

import com.sinoway.common.frame.Entity;

public class BCfgdefRoleinfo extends Entity{

	private static final long serialVersionUID = 519627864075L;
	
	/**
     * 缺省构造函数
     */
 	public BCfgdefRoleinfo() {}
 	
	/**
	 *  id String 字段ID
	 */
	private String id;
	/**
	 *  rolecod String 字段ROLECOD
	 */
	private String rolecod;
	/**
	 *  rolenam String 字段ROLENAM
	 */
	private String rolenam;
	/**
	 *  roledesc String 字段描述角色
	 */
	private String roledesc;
	/**
	 *  prdcod String 字段PRDCOD
	 */
	private String prdcod;
	/**
	 *  prdnam String 字段PRDNAM
	 */
	private String prdnam;
	/**
	 *  roleprdlev String 字段产品交易码不分等级时，值默认为1
	 */
	private String roleprdlev;
	/**
	 *  deforgrole String 字段1.注册时,所有父机构默认拥有该角色
0.不是
	 */
	private String deforgrole;
	/**
	 *  defusrrole String 字段1.机构注册时,是否是机构内默认用户角色
0.不是
	 */
	private String defusrrole;
	/**
	 *  sta String 字段STA
	 */
	private String sta;
	/**
	 *  sort String 字段SORT
	 */
	private String sort;



	
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
	* 返回ROLECOD属性的值
	* @return String
	*/ 
	public String getRolecod() {
		return this.rolecod;
	}
	/**
	 * 设置rolecod的值
	 * @param rolecod String
	 */
	public void setRolecod(String rolecod) {
		this.rolecod = rolecod == null ? null : rolecod.trim();
	}
	/**
	* 返回ROLENAM属性的值
	* @return String
	*/ 
	public String getRolenam() {
		return this.rolenam;
	}
	/**
	 * 设置rolenam的值
	 * @param rolenam String
	 */
	public void setRolenam(String rolenam) {
		this.rolenam = rolenam == null ? null : rolenam.trim();
	}
	/**
	* 返回描述角色属性的值
	* @return String
	*/ 
	public String getRoledesc() {
		return this.roledesc;
	}
	/**
	 * 设置roledesc的值
	 * @param roledesc String
	 */
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc == null ? null : roledesc.trim();
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
	* 返回产品交易码不分等级时，值默认为1属性的值
	* @return String
	*/ 
	public String getRoleprdlev() {
		return this.roleprdlev;
	}
	/**
	 * 设置roleprdlev的值
	 * @param roleprdlev String
	 */
	public void setRoleprdlev(String roleprdlev) {
		this.roleprdlev = roleprdlev == null ? null : roleprdlev.trim();
	}
	/**
	* 返回1.注册时,所有父机构默认拥有该角色
0.不是属性的值
	* @return String
	*/ 
	public String getDeforgrole() {
		return this.deforgrole;
	}
	/**
	 * 设置deforgrole的值
	 * @param deforgrole String
	 */
	public void setDeforgrole(String deforgrole) {
		this.deforgrole = deforgrole == null ? null : deforgrole.trim();
	}
	/**
	* 返回1.机构注册时,是否是机构内默认用户角色
0.不是属性的值
	* @return String
	*/ 
	public String getDefusrrole() {
		return this.defusrrole;
	}
	/**
	 * 设置defusrrole的值
	 * @param defusrrole String
	 */
	public void setDefusrrole(String defusrrole) {
		this.defusrrole = defusrrole == null ? null : defusrrole.trim();
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
	* 返回SORT属性的值
	* @return String
	*/ 
	public String getSort() {
		return this.sort;
	}
	/**
	 * 设置sort的值
	 * @param sort String
	 */
	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}

	


	
}
