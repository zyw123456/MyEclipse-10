package com.sinoway.wrn.service;

import java.util.List;
import java.util.Map;

import com.sinoway.common.entity.PageUtil;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

public interface IWrnService {

	/**
	 * 查询异常预警列表
	 * @param parameters 
	 *            姓名,身份证号,预警时间,监控模块
	 * @param pageModel
	 *            分页模型
	 * @param XPeopleInfo
	 *            个人信息       
	 * @return 异常预警列表
	 */
	public List queryAbnormalWarns(Map<String,String> parameters,PageUtil pageModel,XPeopleInfo cp);

	/**
	 * 统计异常预警总数
	 * @param parameters 
	 * 			     姓名,身份证号,预警时间,监控模块
	 * @param XPeopleInfo
	 *            个人信息
	 * @return 异常预警记录数
	 */
	public int abnormalWarnsCount(Map<String,String> parameters,XPeopleInfo cp);
	
	/**
	 * 查询天警云原交易名列表
	 * @param peopleCode  人员代码
	 * @return
	 */
	public List queryTrnNameList(String peopleCode);
	
	/**
	 * 查询征信预警产品明细表原交易信息
	 * @param warnid  报告编号
	 * @return
	 */
	public List queryTrnList(String warnid);
	
	/**
	 * 查询征信预警产品明细表每种  原交易  下的信息表头
	 * @param warnid  报告编号
	 * @return
	 */
	public List queryWarnDetailTitle(String warnid);
	
	/**
	 * 查询征信预警产品明细表每种  原交易  下的信息表格内容
	 * @param warnid  报告编号
	 * @return
	 */
	public List queryWarnDetailContent(String warnid,String trnCod);
}
