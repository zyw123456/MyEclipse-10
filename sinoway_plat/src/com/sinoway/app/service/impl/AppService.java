package com.sinoway.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.app.entity.WfcgrefApptrn;
import com.sinoway.app.service.IAppService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;

public class AppService implements IAppService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BaseDao baseDao;
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<Map> getPrdTrns(String appcod) {
		List<Map> trns = null;
		try {
			String sql = "select id,trncod,trnnam,startdte,stopdte,trnnature,sta from Wf_Cfgdef_Prdtrn where trncod in (select trncod from Wf_Cfgref_Apptrn where appcod ='"+appcod+"')";
			trns = (List<Map>) baseDao.execSqlQuery(sql);
		} catch (Exception e) {
			logger.error(appcod+"查询交易码失败:",e);
			e.printStackTrace();
		}
		return trns;
	}
	
	@Override
	public List<Map> getPrdTrns(String appcod, String peoplecode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List findTrncodByAppcod(String appcode) {
		// TODO Auto-generated method stub
		//List<WfcgrefApptrn> list = new ArrayList<WfcgrefApptrn>();
		List list = new ArrayList();
		String sql ="select APPCOD,TRNCOD from WF_CFGREF_APPTRN where APPCOD = '"+appcode+"'";
		try {
			list =(List) baseDao.execSqlQuery(sql);
			//list =(List) baseDao.execSqlQuery(sql);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
