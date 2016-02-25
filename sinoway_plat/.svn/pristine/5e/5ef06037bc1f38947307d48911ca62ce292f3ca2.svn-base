package com.sinoway.common.service;

import java.util.LinkedList;
import java.util.List;

import com.sinoway.common.exception.HPException;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.wrn.entity.WfDatCerditWarnDtel;
import com.yzj.wf.common.WFException;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

public interface IHPService {
	/** 
	* @Title: getTop
	* @Description: 获取实时更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	public <T> LinkedList<T> getTop(Class<T> T, String dateName, String timeName, int maxResults) throws HPException;
	/** 
	* @Title: getAccAlarmNo 
	* @Description: 获取账号委托监控统计
	* @param @param userID
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	public long getAccAlarmNo(XPeopleInfo curPeople)throws WFException; 

	/** 
	* @Title: getAccAlarmDetailNo 
	* @Description: 获取账号监控预警统计
	* @param @param userID
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	public long getAccAlarmDetailNo(XPeopleInfo curPeople)throws WFException; 

	/** 
	* @Title: getPlatAlarmDetailNo 
	* @Description: 获取平台监控预警统计
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	public long getPlatAlarmDetailNo()throws WFException;

	/** 
	* @Title: getPlatAlarmNo 
	* @Description: 获取平台总监控统计
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	public long getPlatAlarmNo()throws WFException;
	/** 
	* @Title: getTopPlatAlarmDetail
	* @Description: 获取实时预警更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	public List<WfDatCerditWarnDtel> getTopPlatAlarmDetail(int maxResults,XPeopleInfo curPeople) throws HPException,WFException;
	/** 
	* @Title: getTopAccRpt
	* @Description: 获取账号实时报告更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	public List<WfDatCreditrptUtil> getTopAccRpt(int maxResults,XPeopleInfo curPeople) throws HPException,WFException;
	
	/**
	 * 监控预警明细表中前N条
	 * @param maxResults
	 * @return
	 * @throws HPException 
	 */
	public List<WfDatCerditWarnDtel> getTopPlatAlarmDetail(int maxResults) throws HPException;
	
	/**
	 * 报告表中的前N条
	 * @param topNMaxResult
	 * @return
	 * @throws HPException 
	 */
	public List<WfDatCreditrptUtil> getTopPlatRpt(int topNMaxResult) throws HPException;
}
