package com.sinoway.wrn.service;

import java.util.List;

import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

public interface IWarnMonitorService {
	public List findMonitorNameList(WfDatCerditPrsn wdcp,XPeopleInfo cp,String trncod);
	
	public List findWCPrdtrnList();
	
	public String updateMonitorName(WfDatCerditPrsn wdcp);
	
	public String addMonitorName(WfDatCerditPrsn wdcp);
}
