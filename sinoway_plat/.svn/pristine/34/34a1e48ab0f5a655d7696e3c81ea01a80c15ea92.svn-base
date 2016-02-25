package com.sinoway.common.service;

import java.util.List;

import com.sinoway.common.entity.WfCfgdefJrnrpt;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;

public class JrnrptService {
	private BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	public List<WfCfgdefJrnrpt> find(WfCfgdefJrnrpt jrnrpt){
		List<WfCfgdefJrnrpt> li = null;
		try {
			String[] paramNames =  {"syscod","jrndte"};
			String[] paramValues = {jrnrpt.getSyscod(),jrnrpt.getJrndte()};
			li =  (List<WfCfgdefJrnrpt>) baseDao.findByNamedQueryAndNamedParamArr("JrnrptDao.findJrnrptBySyscodAndJrndte", paramNames, paramValues);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public void update(WfCfgdefJrnrpt jrnrpt){
		try {
			baseDao.update(jrnrpt);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	
	public void save(WfCfgdefJrnrpt jrnrpt){
		try {
			baseDao.saveOrUpdate(jrnrpt);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}



	/**
	 * 按条件更新
	 * @param jrnrpt
	 * @param jrnvalTemp
	 * @return
	 * @throws WFException
	 */
	public int updateByConditions(WfCfgdefJrnrpt jrnrpt, String jrnvalTemp) throws WFException {
		String[] paramNames =  {"syscod","jrndte","jrnval","sta","id","jrnvaltemp"};
		String[] paramValues = {jrnrpt.getSyscod(),jrnrpt.getJrndte(),jrnrpt.getJrnval(),jrnrpt.getSta(),jrnrpt.getId(),jrnvalTemp};
		return (Integer) baseDao.execNameQueryAndNamedParamArr("JrnrptDao.updateByConditions", paramNames, paramValues);
	}

	
}
