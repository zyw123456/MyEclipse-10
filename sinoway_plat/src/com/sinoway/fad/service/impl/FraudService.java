package com.sinoway.fad.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.common.entity.WfCfgrefTrninele;
import com.sinoway.common.util.Constant.RptStatus;
import com.sinoway.fad.service.IFraudService;
import com.sinoway.rpt.dao.WfDatCreditrptDao;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;

/**
 * 反欺诈云Service
 * 
 * @author user
 *
 */
public class FraudService implements IFraudService{

	private WFLogger logger = WFLogger.getLogger(this.getClass());

	private WfDatCreditrptDao creditrptDao;
	

	public WfDatCreditrptDao getCreditrptDao() {
		return creditrptDao;
	}

	public void setCreditrptDao(WfDatCreditrptDao creditrptDao) {
		this.creditrptDao = creditrptDao;
	}
	/**
	 * 根据原报告元素和报告编号查询查询 原报告元素
	 * 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List findOldElecod(String rptid){
		 List adrrList = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT reqadrr FROM WF_DAT_CREDITRPT WHERE RPTID = '"+rptid+"' ");
			adrrList =(List)creditrptDao.execSqlQuery(buff.toString());
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adrrList;
	}
	/**
	 * 查询报告流转页面元素 +原报告元素
	 * 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List<WfCfgrefTrninele> findTrninele(String prdcod,String elecod){
		List<WfCfgrefTrninele> list = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" select   wft  FROM com.sinoway.common.entity.WfCfgrefTrninele wft WHERE ");
			buff.append(" wft.id  in ( ");
			buff.append(" select max(wcf.id)  FROM com.sinoway.common.entity.WfCfgrefTrninele wcf WHERE wcf.trncod IN ");
			buff.append(" (SELECT wfp.trncod FROM com.yzj.wf.plat.entity.WfCfgrefPrddetil wfp WHERE wfp.prdcod = '"+prdcod+"') ");
			buff.append(" AND wcf.elecod NOT IN ("+elecod+") ");
			buff.append(" group by wcf.elenam,wcf.elecod) ");
			buff.append(" ORDER BY wft.trncod,to_number(wft.sort) asc ");
			list =(List<WfCfgrefTrninele>) creditrptDao.find(buff.toString());
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	   }
	/**
	 * 查询报告页面元素
	 * 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List<WfCfgrefTrninele> findPageElecod(String prdcod){
		List<WfCfgrefTrninele> list = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" select   wft  FROM com.sinoway.common.entity.WfCfgrefTrninele wft WHERE ");
			buff.append(" wft.id  in (select max(wcf.id) from com.sinoway.common.entity.WfCfgrefTrninele wcf ");
			buff.append(" WHERE wcf.trncod IN ");
			buff.append(" (SELECT wfp.trncod FROM com.yzj.wf.plat.entity.WfCfgrefPrddetil  wfp WHERE wfp.prdcod ='"+prdcod+"')	");
			buff.append(" group by wcf.elenam,wcf.elecod) ");
			buff.append(" ORDER BY wft.trncod,to_number(wft.sort) asc ");
			list =(List<WfCfgrefTrninele>) creditrptDao.find(buff.toString());
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List findOldTranByRptid(String rptId){
		List list = null;
		try {
			list = (List) creditrptDao.findByNamedQueryAndNamedParam("creditrptDao.findOldTranByRptid", "rptId", rptId);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询已经拥有原交易
	 * 
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List findTranByRptid(String rptId){
		List list = null;
		try {
			list = (List) creditrptDao.findByNamedQueryAndNamedParam("creditrptDao.findTranByRptid", "rptId", rptId);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 *发起一个报告 向后台发起请求
	 * 
	 * @throws WFException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void createReport(WfDatCreditrpt creditrpt) throws WFException {
		logger.info(" create a Report");
		creditrptDao.saveOrUpdate(creditrpt);
		
	}

	
	
	/**
	 * 删除一个反欺诈报告
	 * @param creditrpt
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public void deleteByRptids( String[] rptids) throws Exception {
		try {
			creditrptDao.deleteByRptids( rptids);
		} catch (Exception e) {
			throw new WFException("WF", 530, 530, e);
		}
	}
	
	
	
	
	
	/**
	 * 根据改登录账号以及应用编号查询配置的验证产品、反欺诈策略
	 * @param peopleCode
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	public List<WfCfgdefPrdinfo> findPrdinfoByPeopleAndAppcod(String appcod,String peopleCode) throws Exception{
		String[] paramName = {"peopleid","sta","appcod"};
		Object[] paramValue = {peopleCode,RptStatus.STATIS_DELETE.getCode(),appcod};
		List<WfCfgdefPrdinfo> list = (List<WfCfgdefPrdinfo>) creditrptDao.findByNamedQueryAndNamedParamArr("creditrptDao.getFraudByPeoplecode", paramName, paramValue);
		return list;
	}

	
	
	/**
	 * 根据报告Id查找相应报告信息
	 * @param rptid
	 * @param peoplecode
	 * @throws WFException 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public WfDatCreditrpt getFraudByrptid(String rptid) throws WFException {
		return (WfDatCreditrpt) creditrptDao.loadByKey(WfDatCreditrpt.class, "rptid", rptid, true);
	}

	
	/**
	 * 更改报告实体类
	 * @param creditrpt
	 * @throws WFException 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public void updateCreditrpt(WfDatCreditrpt creditrpt) throws WFException {
		creditrptDao.update(creditrpt);
	}

}
