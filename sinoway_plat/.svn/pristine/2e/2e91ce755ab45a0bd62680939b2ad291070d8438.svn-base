package com.sinoway.rpt.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.rpt.dao.RptDao;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.rpt.service.IRptService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;


/**
 * @Title:个人征信报告
 * @author 于辉
 *
 */
public class RptService implements IRptService{
	private RptDao rptDao;
    
	public void setRptDao(RptDao rptDao) {
		this.rptDao = rptDao;
	}
	
	/** 
	* @Title: addCredItRpt 
	* @Description: (新增征信报告) 
	* @author 于辉
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public String addCredItRpt(WfDatCreditrptUtil wfdatUtil) {
		//  Auto-generated method stub
		return rptDao.addCredItRpt(wfdatUtil);
	}
	/** 
	* @Title: findCredItRpt 
	* @Description: (查询产品数据) 
	* @return List     
	* @author 于辉
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List findCreadItRpt(String peoplecode,String appcod) {
		//  Auto-generated method stub
		return rptDao.findCreadItRpt(peoplecode,appcod);
	}
	/** 
	* @Title: findCredItRpt 
	* @Description: (查询产品数据去掉原有的产品) 
	* @return List     
	* @author 于辉
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List findCreadItRptOld(String peoplecode,String appcod,String oldprdcod){
		return rptDao.findCreadItRptOld(peoplecode, appcod, oldprdcod);
	}
	
	

}
