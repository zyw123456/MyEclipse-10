package com.sinoway.wrn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.common.util.Constant;
import com.sinoway.rpt.service.impl.WfDatCreditrptService;
import com.sinoway.wrn.dao.WaitingMonitorDao;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.entity.WfDatCerditWarnWaiting;
import com.sinoway.wrn.service.IWaitingMonitorService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.cache.CacheEnum;
import com.yzj.wf.common.cache.CacheFactory;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;

public class WaitingMonitorService implements IWaitingMonitorService {
	private WaitingMonitorDao waitingMonitorDao;
	private WfDatCreditrptService wfDatCreditrptService;
	
	

	

	public WfDatCreditrptService getWfDatCreditrptService() {
		return wfDatCreditrptService;
	}

	public void setWfDatCreditrptService(WfDatCreditrptService wfDatCreditrptService) {
		this.wfDatCreditrptService = wfDatCreditrptService;
	}

	public WaitingMonitorDao getWaitingMonitorDao() {
		return waitingMonitorDao;
	}

	public void setWaitingMonitorDao(WaitingMonitorDao waitingMonitorDao) {
		this.waitingMonitorDao = waitingMonitorDao;
	}
	
	/**
	 * 待监控人员名单查询
	 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List<WfDatCerditWarnWaiting> queryWarnWaitingPeople(String order,String loantyp,XPeopleInfo curPeople){
    	List<WfDatCerditWarnWaiting> waitinglist = new ArrayList<WfDatCerditWarnWaiting>();
    	StringBuffer hql = new StringBuffer();
    	hql.append("select prsn from com.sinoway.wrn.entity.WfDatCerditPrsn prsn where (prsn.sta ='0' or prsn.sta='2')");
    	hql.append(" and corgno in("+wfDatCreditrptService.getCurOrgAndChildOrg(curPeople)+")");
    	if(!"0".equals(loantyp)){
    		hql.append(" and loantyp='" + loantyp + "'");
    	}
    	if("up".equals(order)){
    		hql.append(" order by loansrtdte,loanenddte");
    	}else if("down".equals(order)){
    		hql.append(" order by loanenddte desc,loansrtdte desc");
    	}
    	try {
			List<WfDatCerditPrsn> list =  (List<WfDatCerditPrsn>) waitingMonitorDao.find(hql.toString());
			for (WfDatCerditPrsn prsn : list) {
				waitinglist.add(this.processWarnWaiting(prsn));
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
    	return waitinglist;
    }
    /**
	 * 处理待监控人员展示信息
	 * 
	 */
    public WfDatCerditWarnWaiting processWarnWaiting(WfDatCerditPrsn prsn){
    	WfDatCerditWarnWaiting warnWaiting = new WfDatCerditWarnWaiting();
    	warnWaiting.setSta(prsn.getSta());
    	String prsncod = prsn.getPrsncod();
		prsncod = prsncod.substring(0, 3)+"***********"+prsncod.substring(prsncod.length()-4);
    	warnWaiting.setPrsncod(prsncod);
    	warnWaiting.setPrsnnam(prsn.getPrsnnam());
    	warnWaiting.setId(prsn.getId());
    	warnWaiting.setLoanamt(prsn.getLoanamt());
    	warnWaiting.setLoanlmt(prsn.getLoanlmt());
    	warnWaiting.setLoansrtdte(prsn.getLoansrtdte());
    	warnWaiting.setLoanenddte(prsn.getLoanenddte());
    	warnWaiting.setPeoplecode(prsn.getPeoplecode());
    	warnWaiting.setParntcode(prsn.getParntcode());
    	warnWaiting.setPrdcod(prsn.getPrdcod());
    	warnWaiting.setPrdnam(prsn.getPrdnam());
    	warnWaiting.setTelno(prsn.getTelno());
    	warnWaiting.setRepayamt(prsn.getRepayamt());
    	warnWaiting.setRepaytyp(prsn.getRepaytyp());
    	warnWaiting.setRepaydte(prsn.getRepaydte());
    	if("".equals(prsn.getLoantyp()) || prsn.getLoantyp() == null){
    		warnWaiting.setLoantyp("----");
    	}else if(Constant.LoanType.LOAN_CONSUMWE.getCode().equals(prsn.getLoantyp())){
    		warnWaiting.setLoantyp("消费类贷款");
    	}else if(Constant.LoanType.LOAN_CAR.getCode().equals(prsn.getLoantyp())){
    		warnWaiting.setLoantyp("汽车贷款");
    	}else if(Constant.LoanType.LOAN_PURCHASE.getCode().equals(prsn.getLoantyp())){
    		warnWaiting.setLoantyp("购房贷款");
    	}
    	if(prsn.getLoansrtdte() == null ||prsn.getLoanenddte() == null){
    		warnWaiting.setSection("----");
    	}else{
    		warnWaiting.setSection(prsn.getLoansrtdte() + "--" +prsn.getLoanenddte());
    	}
    	if("1".equals(this.getPrdByPrdcod(prsn.getPrdcod()).getIsdefult())){
    		warnWaiting.setModule("全模块监控");
    	}else{
    		warnWaiting.setModule(prsn.getPrdnam());
    	}
    	return warnWaiting;
    }
    /**
	 * 待监控人员补充信息
	 */

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public void suppleWarnWaitingPeople(WfDatCerditPrsn prsn) {
		try {
			WfDatCerditPrsn oldWarn = this.getWfDatCerditwarnById(prsn.getId());
			oldWarn.setPrsnnam(prsn.getPrsnnam());
			if(!prsn.getPrsncod().contains("*")){		
				oldWarn.setPrsncod(prsn.getPrsncod());
			}
			oldWarn.setLoanlmt(prsn.getLoanlmt());
			oldWarn.setLoanamt(prsn.getLoanamt());
			oldWarn.setLoantyp(prsn.getLoantyp());
			oldWarn.setLoanenddte(prsn.getLoanenddte().replace("-", ""));
			oldWarn.setLoansrtdte(prsn.getLoansrtdte().replace("-", ""));
			oldWarn.setTelno(prsn.getTelno());
			oldWarn.setRepayamt(prsn.getRepayamt());
			oldWarn.setRepaydte(prsn.getRepaydte());
			oldWarn.setRepaytyp(prsn.getRepaytyp());
			oldWarn.setSta("1");
			waitingMonitorDao.update(oldWarn);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
     * 开启监控
     */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public void startMonitor(String id) {
		WfDatCerditPrsn prsn = this.getWfDatCerditwarnById(id);
		prsn.setSta("1");
		try {
			waitingMonitorDao.update(prsn);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
     * 根据id查一个预警人员对象
     */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public WfDatCerditPrsn getWfDatCerditwarnById(String id){
		WfDatCerditPrsn prsn = null;
		try {
			List<WfDatCerditPrsn> list = (List<WfDatCerditPrsn>) waitingMonitorDao.findByNamedQueryAndNamedParam("waitingMonitorDao.getWfDatCerditwarnById", "id",id);
			if(list.size()>0){
				prsn = list.get(0);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return prsn;
	}
	 /**
	  * 查询预警报告发起人员的天警云模板
	  */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List<WfCfgdefPrdinfo> queryWarnPrds(WfDatCerditPrsn prsn){
		List<WfCfgdefPrdinfo> prdlist = null;
		String[] paramNames = {"id","appname","prdsta","comsta"};
		try {
				Object[] paramValues = {prsn.getPeoplecode(),"天警云","1","1"};
				prdlist = (List<WfCfgdefPrdinfo>) waitingMonitorDao.findByNamedQueryAndNamedParamArr("waitingMonitorDao.queryWarnPrds", paramNames, paramValues);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return prdlist;
	}
	/**
	 * 更换预警报告查询人员的天警云模板
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
    public void changeWarnPrds(WfDatCerditPrsn prsn){
    	WfDatCerditPrsn oldWarn = this.getWfDatCerditwarnById(prsn.getId());
    	oldWarn.setPrdcod(prsn.getPrdcod());
    	oldWarn.setPrdnam(prsn.getPrdnam());
    	try {
			waitingMonitorDao.update(oldWarn);
		} catch (WFException e) {
			e.printStackTrace();
		}
    }
    /**
	 * 根据产品编码查产品
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public WfCfgdefPrdinfo getPrdByPrdcod(String prdcod){
    	WfCfgdefPrdinfo prd = null;
    	prd = (WfCfgdefPrdinfo) CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).getRealObject(prdcod);
    	if(prd != null){
    		return prd;
    	}
    	try {
    		List<WfCfgdefPrdinfo> list = (List<WfCfgdefPrdinfo>) waitingMonitorDao.findByNamedQueryAndNamedParam("waitingMonitorDao.getPrdByPrdcod", "prdcod", prdcod);
    		if(list.size() >0){
    			prd = list.get(0);
    		}
    		CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).putRealObject(prdcod,prd);
		} catch (WFException e) {
			e.printStackTrace();
		}
    	return prd;
    }
}
