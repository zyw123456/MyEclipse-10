package com.sinoway.rpt.dao;

import java.util.Arrays;

import org.hibernate.Query;

import com.sinoway.common.util.Constant;
import com.sinoway.common.util.Constant.RptStatus;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;


/**
 * 报告dao
 * @author user
 *
 */
public class WfDatCreditrptDao extends BaseDao {

	
	protected Class<WfDatCreditrpt> getModelClass() {
		return WfDatCreditrpt.class;
	}	
	
	
	/**
	 * 删除报告(更新报告状态)
	 * @param peopleCode
	 * @param rptids
	 * @return
	 * @throws WFException 
	 */
	public Object deleteByRptids(String[] rptids) throws WFException{
		Query nameQuery = getSession().getNamedQuery("creditrptDao.deleteFraudByRptids");
		nameQuery.setParameterList("rptid", Arrays.asList(rptids));
		nameQuery.setParameter("rptsta", RptStatus.STATIS_DELETE.getCode());
		return nameQuery.executeUpdate();
//		String[] paramNames = {"peoplecode","rptid","rptsta"};
//		Object[] paramValues ={peopleCode,Arrays.asList(rptids),Constant.CreditReportStats.CREDIT_REPORT_STATUS_DELETE.getValue()};
//		this.execNameQueryAndNamedParamArr(, paramNames, paramValues);
	}
	
	
}
