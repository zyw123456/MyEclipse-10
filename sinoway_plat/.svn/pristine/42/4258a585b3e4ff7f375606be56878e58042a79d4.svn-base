package com.sinoway.wrn.service;

import java.util.List;

import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.entity.WfDatCerditWarnWaiting;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;

public interface IWaitingMonitorService {
	
	/**
	 * 待监控人员名单查询
	 */
    public List<WfDatCerditWarnWaiting> queryWarnWaitingPeople(String order,String loantyp,XPeopleInfo curPeople);
    /**
	 * 待监控人员补充信息
	 */
    public void suppleWarnWaitingPeople(WfDatCerditPrsn wdc);
    /**
     * 开启监控
     */
    public void startMonitor(String id);
    /**
	 * 查询当前人员的天警云模板
	 */
    public List<WfCfgdefPrdinfo> queryWarnPrds(WfDatCerditPrsn wdc);
    /**
	 * 更换预警报告查询人员的天警云模板
	 */
    public void changeWarnPrds(WfDatCerditPrsn wdc);
    /**
	 * 根据id查监控人员
	 */
    public WfDatCerditPrsn getWfDatCerditwarnById(String id);
}
