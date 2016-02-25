package com.sinoway.acc.service.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.acc.entity.WfCfgdefCompinfo;
import com.sinoway.acc.entity.WfCfgdefPwd;
import com.sinoway.acc.service.IAccountService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.JRNGenerator;
import com.sinoway.common.util.StringUtil;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;
/**
 * App账户管理
 * @author zhangyanwei
 *
 */
public class AccountService implements IAccountService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BaseDao baseDao;

	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/** 
	* @Title: createCompanyInfo 
	* @param  prdcod 用户编号 
	* @return void   
	 * @throws Exception 
	* @throws zhangyanwei
	*/
	public void createCompanyInfo(WfCfgdefCompinfo wfCfgdefCompinfo,String flag) throws Exception {
		try {
			if(flag.equals("0")){
			wfCfgdefCompinfo.setId(JRNGenerator.generateJrn("P", "1", "00000000"));
		//	baseDao.saveOrUpdate(wfCfgdefCompinfo);
			String[] paramName = {"id","compno","compnam","compaddr","prsnnam","phnone","strdte","enddte",
					  "comptyp","sta"};
            Object[] paramValue = {wfCfgdefCompinfo.getId(),wfCfgdefCompinfo.getCompno(),wfCfgdefCompinfo.getCompnam(),
            		wfCfgdefCompinfo.getCompaddr(),wfCfgdefCompinfo.getPrsnnam(),wfCfgdefCompinfo.getPhnone(),
            		wfCfgdefCompinfo.getStrdte(),wfCfgdefCompinfo.getEnddte(),wfCfgdefCompinfo.getComptyp(),wfCfgdefCompinfo.getSta()};
            baseDao.execNameQueryAndNamedParamArr("accountDao.createCompanyInfo", paramName, paramValue);
			}else{
			WfCfgdefCompinfo c =new WfCfgdefCompinfo();
			c.setId(wfCfgdefCompinfo.getId());
			c.setCompno(wfCfgdefCompinfo.getCompno());
			c.setCompnam(wfCfgdefCompinfo.getCompnam());
			c.setComptyp(wfCfgdefCompinfo.getComptyp());
			c.setPhnone(wfCfgdefCompinfo.getPhnone());
			c.setEnddte(wfCfgdefCompinfo.getEnddte());
			c.setStrdte(wfCfgdefCompinfo.getStrdte());
			c.setCompaddr(wfCfgdefCompinfo.getCompaddr());
			baseDao.saveOrUpdate(wfCfgdefCompinfo);	
//			String[] paramName = {"id","compno","compnam","compaddr","prsnnam","phnone","strdte","enddte",
//					  "comptyp","sta"};
//            Object[] paramValue = {wfCfgdefCompinfo.getId(),wfCfgdefCompinfo.getCompno(),wfCfgdefCompinfo.getCompnam(),
//            		wfCfgdefCompinfo.getCompaddr(),wfCfgdefCompinfo.getPrsnnam(),wfCfgdefCompinfo.getPhnone(),
//            		wfCfgdefCompinfo.getStrdte(),wfCfgdefCompinfo.getEnddte(),wfCfgdefCompinfo.getComptyp(),wfCfgdefCompinfo.getSta()};
//           
//            
//            baseDao.execNameQueryAndNamedParamArr("accountDao.updateCompanyInfo", paramName, paramValue);
			}
		} catch (WFException e) {
			logger.debug("新增公司基本信息出现错误");
			e.printStackTrace();
		}
	}

	/** 
	* @Title: searchCompany 
	* @param  compno  公司编号
	* @return List   
	* @throws zhangyanwei
	*/
	public List<Map>  searchCompany(String compno ) {
		
		List<Map> wfCfgdefCompinfo = null;
		try {
			//String sql ="select * from WF_CFGDEF_COMPINFO where id = 'P10000000015122116084000000000'";
			wfCfgdefCompinfo = (List<Map>) baseDao.findByNamedQueryAndNamedParam("accountDao.getAccountInfoBycompno", "compid", compno);
			return wfCfgdefCompinfo;
		
		} catch (WFException e) {
			e.printStackTrace();
		}
		return wfCfgdefCompinfo;
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List findCurrentCompInfo(String id) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String sql= "select ID,COMPNO,COMPNAM,COMPADDR,PRSNNAM,PHNONE,STRDTE,ENDDTE,COMPTYP,STA from WF_CFGDEF_COMPINFO where ID='"+id+"'";
		try {
			list = (List) baseDao.execSqlQuery(sql);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updateCurrentCompInfo(WfCfgdefCompinfo comp) {
		try {
			baseDao.saveOrUpdate(comp);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List findOrganiznfoById(String id) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String sql = "select PARENTORG from PO_ORGANIZEINFO where SID ='"+id+"'";
		try {
			list = (List) baseDao.execSqlQuery(sql);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
