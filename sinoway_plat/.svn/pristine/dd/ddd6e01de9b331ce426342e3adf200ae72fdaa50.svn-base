package com.sinoway.wrn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.wrn.service.IWfDatCerditWarndtelService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;
/**
 * 征信预警产品明细处理（天警云首页）
 * @author Administrator
 *
 */
public class WfDatCerditWarndtelService implements IWfDatCerditWarndtelService{
private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List findAllWfDatCerditWarndtel(Map queryMap) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select ID,TRNCOD,PRSNNAM,CREDTNO,DATORI,REALITY,WARNTIM,WARNDTE from WF_DAT_CERDITWARNDTEL where 1=1");
		if(queryMap!=null){
			if(!"".equals(queryMap.get("warnDataType"))&&queryMap.get("warnDataType")!=null&&!"null".equals(queryMap.get("warnDataType"))){
				sql.append(" and TRNCOD ='"+queryMap.get("warnDataType")+"'");
				//sql.append(" and TRNCOD like '%"+queryMap.get("warnDataType")+"%'");
			}
			if(!"".equals(queryMap.get("dataUrl"))&&queryMap.get("dataUrl")!=null&&!"null".equals(queryMap.get("dataUrl"))){
				sql.append(" and DATORI ='"+queryMap.get("dataUrl")+"'");
				//sql.append(" and DATORI ='%"+queryMap.get("dataUrl")+"%'");
			}
			if(!"".equals(queryMap.get("controlTime"))&&queryMap.get("controlTime")!=null&&!"null".equals("controlTime")){
				if("2".equals(queryMap.get("controlTime"))){
					sql.append(" order by WARNDTE desc, WARNTIM desc ");
				}else{
					sql.append(" order by WARNDTE asc, WARNTIM asc");
				}
			}
			if(!"".equals(queryMap.get("dataRealty"))&&queryMap.get("dataRealty")!=null&&!"null".equals("dataRealty")){
				if("2".equals(queryMap.get("dataRealty"))){
					sql.append(" order by REALITY desc");
				}else{
					sql.append(" order by REALITY asc");
				}
			}
			/*      以下是监控 时间和可信度同时排序            */
//			if("升序".equals(queryMap.get("controlTime"))){
//				sql.append(" order by WARNDTE asc, WARNTIM asc");
//			}
//			if("降序".equals(queryMap.get("controlTime"))){
//				sql.append(" order by WARNDTE desc, WARNTIM desc ");
//			} 
//			if("高到低".equals(queryMap.get("dataRealty"))){
//				sql.append(",REALITY desc");
//			}
//			if("低到高".equals(queryMap.get("dataRealty"))){
//				sql.append(",REALITY asc");
//			}
		}
		//sql.append("  fetch first 30 rows only");
		sql.append(" fetch first "+queryMap.get("pageSize")+" rows only");
		try {
			list = (List) baseDao.execSqlQuery(sql.toString());
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
