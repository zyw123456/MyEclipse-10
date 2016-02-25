/**   
* @Title: FadBaseAction.java 
* @Package com.sinoway.fad.action 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Leo May the source be with you!   
* @date 2015-12-16 上午9:56:35 
* @version V1.0   
*/
package com.sinoway.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.annotations.JSON;

import com.yzj.wf.base.action.BaseAction;

/** 
 * @ClassName: FadBaseAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Leo May the source be with you! 
 * @date 2015-12-16 上午9:56:35 
 *  
 */
public class HPBaseAction extends BaseAction {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6544945993905865082L;
	
	/**
	 * 获取当前操作人员父操作员的代码
	 * 
	 * @return 父操作人员代码
	 */
	@JSON(serialize = false)
	protected String getParentPeopleCode() {
		return getCurrentPeople().getParntCode();
	}

	/** 
	* @Title: getAccAlarmV 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object getAccAlarmV() {
		// TODO Auto-generated method stub
		return super.getRequest().getParameter("ACCALARMV");
	}

	/** 
	* @Title: getAccRptV 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object getAccRptV() {
		// TODO Auto-generated method stub
		return super.getRequest().getParameter("ACCRPTV");
	}

	/** 
	* @Title: getPlatAlarmDetailV 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object getPlatAlarmDetailV() {
		// TODO Auto-generated method stub
		Object a = super.getRequest().getAttributeNames();
		HttpServletRequest h;
	
		return super.getRequest().getParameter("PLATALARMDETAILV");
	}

}
