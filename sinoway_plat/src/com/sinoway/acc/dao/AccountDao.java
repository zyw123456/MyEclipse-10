package com.sinoway.acc.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.sinoway.common.util.Constant;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;


/**
 * 公司账户dao
 * @author user
 *
 */
public class AccountDao extends BaseDao {

	
	protected Class<WfDatCreditrpt> getModelClass() {
		return WfDatCreditrpt.class;
	}	
	
	
	/**
	 * 查询公司信息
	 * @param peopleCode
	 * @param rptids
	 * @return List<map>
	 * @throws WFException 
	 */
	public List<Map> searchAcocuntlist(String peopleCode, String[] rptids) throws WFException{
		Query nameQuery = getSession().getNamedQuery("accountDao.searchAcocuntlist");
		return nameQuery.list() ;

	}
	
	
}
