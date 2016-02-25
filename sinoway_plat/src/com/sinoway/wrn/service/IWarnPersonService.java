package com.sinoway.wrn.service;

import java.util.List;

import com.sinoway.wrn.entity.WfDatCerditPrsn;
/**
 * 
 * @author LBC
 * 时间：2015-12-30下午7:17:28
 * 说明：监控人员接口
 */
public interface IWarnPersonService {
//	根据peoplecode,appcod找到策略产品
	public List findPolicyList(String appcod,String peopleCode);
//	添加一个监控人员
	public void addWranPerson(WfDatCerditPrsn cerditPrsn);
//	根据prdcod查找prdname
	public List findPrdnamByprdcod(String prdcod,String sta,String appcod);
}
