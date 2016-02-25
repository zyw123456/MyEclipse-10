package com.sinoway.wrn.service.impl;

import java.util.List;

import com.sinoway.rpt.service.impl.WfDatCreditrptService;
import com.sinoway.wrn.dao.WarnMonitorDao;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.service.IWarnMonitorService;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
/** 
 * @ClassName: WarnMonitorAction 
 * @Description: (天警云监控人员) 
 * @author 于辉
 * @date 2015-12-29
 *  
 */
public class WarnMonitorService implements IWarnMonitorService{
	private WarnMonitorDao warnMDao;
	WfDatCreditrptService wdcpService;
	public void setWdcpService(WfDatCreditrptService wdcpService) {
		this.wdcpService = wdcpService;
	}
	/** 
	* @Title: findMonitorNameList 
	* @Description:(查询监控人员名单) 
	* @return List     
	* @throws 于辉
	*/
	@Override
	public List findMonitorNameList(WfDatCerditPrsn wdcp,XPeopleInfo cp,String trncod) {
		// TODO Auto-generated method stub
		System.out.println(cp.getOrgNo());
		String orgno = wdcpService.getCurOrgAndChildOrg(cp);
		wdcp.setOrgno(orgno);
		return warnMDao.findMonitorNameList(wdcp,trncod);
	}
	/** 
	* @Title: findWCPrdtrnList 
	* @Description:(查询监控模块原交易 ) 
	* @return List     
	* @throws 于辉
	*/
	@Override
	public List findWCPrdtrnList() {
		// TODO Auto-generated method stub
		return warnMDao.findWCPrdtrnList();
	}
	/** 
	* @Title: updateMonitorName 
	* @Description:(修改监控人员 ) 
	* @throws 于辉
	*/
	@Override
	public String updateMonitorName(WfDatCerditPrsn wdcp) {
		// TODO Auto-generated method stub
		return warnMDao.updateMonitorName(wdcp);
	}
	/** 
	* @Title: addMonitorName 
	* @Description:(增加监控人员 ) 
	* @throws 于辉
	*/
	@Override
	public String addMonitorName(WfDatCerditPrsn wdcp) {
		// TODO Auto-generated method stub
		return warnMDao.addMonitorName(wdcp);
	}
	public void setWarnMDao(WarnMonitorDao warnMDao) {
		this.warnMDao = warnMDao;
	}
	
}
