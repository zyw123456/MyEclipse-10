package com.sinoway.wrn.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.wrn.dao.WarnPersonDao;
import com.sinoway.wrn.dao.WarnProductDao;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.service.IWarnPersonService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;
/**
 * 
 * @author LBC
 * 时间：2015-12-30下午7:29:10
 * 说明：监控人员的业务层
 */
public class WarnPersonService implements IWarnPersonService{
	private WarnProductDao warnProductDao;
	private WarnPersonDao warnPersonDao;
	private final static WFLogger logger = WFLogger.getLogger(WarnProductDao.class.getName());	

	public void setWarnPersonDao(WarnPersonDao warnPersonDao) {
		this.warnPersonDao = warnPersonDao;
	}
	public void setWarnProductDao(WarnProductDao warnProductDao) {
		this.warnProductDao = warnProductDao;
	}	
	/** 
	 * @Title: findPolicyList 
	 * @Description:(根据peoplecode,appcod找到策略的集合) 
	 * @param peoplecode,appcod
	 * @return JSON    
	 */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true, rollbackFor=Exception.class)
	public List findPolicyList(String appcod,String peopleCode) {
		System.out.println("WarnProductDao.findPrdDetil is invoke!!!");
		List policyList = null;
		String[] paramName = {"peoplecode","appcod"};
		Object[] paramValue = {peopleCode,appcod};
		try {
			//			只要不抛异常，List就不为null
			policyList = warnProductDao.findByNamedQueryAndNamedParamArr("WarnProductDao.findPrdDetil", paramName, paramValue);	
		} catch (WFException e) {
			logger.error(peopleCode+":查询报错:",e);
			e.printStackTrace();
		}	
		return policyList;
	}

	/**
	 * 根据prdcod查找prdnam 
	 * @param prdcod
	 * @author LBC
	 */
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true, rollbackFor=Exception.class)
	@Override
	public List findPrdnamByprdcod(String prdcod,String sta,String appcod){
		//		List pNameList= warnProductDao.findPrdnamByprdcod(prdcod,sta,appcod);
		List pNameList = null;
		String[] paramName = {"prdcod","sta","appcod"};
		Object[] paramValue = {prdcod,sta,appcod};
		try {
			pNameList = warnProductDao.findByNamedQueryAndNamedParamArr("WarnProductDao.findPrdnamByprdcod", paramName, paramValue);	
		} catch (WFException e) {
			e.printStackTrace();
		}
		return pNameList;
	}

	/**
	 * 添加一个预警对象
	 * @param WfDatCerditPrsn
	 * @author LBC
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void addWranPerson(WfDatCerditPrsn cerditPrsn) {
		String[] paramName = {"id","prsnnam","prsncod","loantyp","loanamt",
				"loanlmt","telno","repaydte","repaytyp","repayamt","prdcod",
				"prdnam","loansrtdte","loanenddte","peoplecode","orgno",
				"parntcode","sta","usrid","p_usrid","corgno","cporgno"};
		Object[] paramValue = {GUIDGenerator.generateId(),
				cerditPrsn.getPrsnnam(),
				cerditPrsn.getPrsncod(),
				cerditPrsn.getLoantyp(),
				cerditPrsn.getLoanamt(),
				cerditPrsn.getLoanlmt(),
				cerditPrsn.getTelno(),
				cerditPrsn.getRepaydte(),
				cerditPrsn.getRepaytyp(),
				cerditPrsn.getRepayamt(),
				cerditPrsn.getPrdcod(),
				cerditPrsn.getPrdnam(),
				cerditPrsn.getLoansrtdte(),
				cerditPrsn.getLoanenddte(),
				cerditPrsn.getPeoplecode(),
				cerditPrsn.getOrgno(),
				cerditPrsn.getParntcode(),
				cerditPrsn.getSta(),
				cerditPrsn.getUsrid(),
				cerditPrsn.getP_usrid(),
				cerditPrsn.getCorgno(),
				cerditPrsn.getCporgno()
		};
		try {
			warnPersonDao.execNameQueryAndNamedParamArr("warnPersonDao.addWranPerson", paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}

	}

}

