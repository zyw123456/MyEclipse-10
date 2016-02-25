package com.sinoway.stm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.acc.entity.PeopleShowInfo;
import com.sinoway.acc.service.IPoOrganizeinfoService;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.Constant.ResultStatus;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.stm.common.ParseEntity;
import com.sinoway.stm.dao.PrdDao;
import com.sinoway.stm.entity.FrotTrnObjInfo;
import com.sinoway.stm.entity.StmResMsg;
import com.sinoway.stm.service.IStmManagerService;
import com.sinoway.stm.service.IStmMsgtrnService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.cache.CacheEnum;
import com.yzj.wf.common.cache.CacheFactory;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;
import com.yzj.wf.plat.entity.WfCfgrefCompusrprd;
import com.yzj.wf.plat.entity.WfCfgrefPrddetil;

public class StmManagerService implements IStmManagerService{
	private PrdDao prdDao;
	private IStmMsgtrnService stmMsgtrnService;
	private IPoOrganizeinfoService poOrganizeinfoService;
	
	
	public IPoOrganizeinfoService getPoOrganizeinfoService() {
		return poOrganizeinfoService;
	}

	public void setPoOrganizeinfoService(
			IPoOrganizeinfoService poOrganizeinfoService) {
		this.poOrganizeinfoService = poOrganizeinfoService;
	}

	public IStmMsgtrnService getStmMsgtrnService() {
		return stmMsgtrnService;
	}

	public void setStmMsgtrnService(IStmMsgtrnService stmMsgtrnService) {
		this.stmMsgtrnService = stmMsgtrnService;
	}

	public PrdDao getPrdDao() {
		return prdDao;
	}

	public void setPrdDao(PrdDao prdDao) {
		this.prdDao = prdDao;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void saveOrUpdatePrddetail(WfCfgrefPrddetil prddetail) {
		try {
			prdDao.saveOrUpdate(prddetail);
			if("0".equals(prddetail.getSta())){
				CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue()).removeRealObject(prddetail);
			}else{
				CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue()).putRealObject(prddetail.getId(),prddetail);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void saveOrUpdateUsrPrd(WfCfgrefCompusrprd usrprd) {
		try {
			prdDao.saveOrUpdate(usrprd);
			if("0".equals(usrprd.getSta())){
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).removeRealObject(usrprd);
			}else{
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).putRealObject(usrprd.getId(),usrprd);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updatePrds(List<WfCfgdefPrdinfo> prds) {
		try {
			prdDao.batchUpdate(prds);
			for(WfCfgdefPrdinfo prd:prds){
				CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).putRealObject(prd.getPrdcod(),prd);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void saveOrUpdatePrd(WfCfgdefPrdinfo prdinfo) {
		try {
			prdDao.saveOrUpdate(prdinfo);
			if("0".equals(prdinfo.getSta())){
				CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).removeRealObject(prdinfo);
			}else{
				CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).putRealObject(prdinfo.getPrdcod(),prdinfo);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updateUsrPrds(List<WfCfgrefCompusrprd> usrprds) {
		try {
			prdDao.batchUpdate(usrprds);
			for(WfCfgrefCompusrprd usrprd:usrprds){
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).putRealObject(usrprd.getId(),usrprd);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updatePrddetails(List<WfCfgrefPrddetil> prddetails) {
		try {
			prdDao.batchUpdate(prddetails);
			for(WfCfgrefPrddetil prddetail:prddetails){
				CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue()).putRealObject(prddetail.getId(),prddetail);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<?> queryUserPrds(String peopleCode,String appcod,String prdcod) {
		List li = null;
		try {
			String hqlId;
			String[] paramName;
			String[] paramValue;
			if(null == peopleCode){
				hqlId = "PrdDao.queryUserPrdsWithNoPeopleCode";
				String[] tmpParamName = {"appcod","prdcod","sta"};
				String[] tmpParamValue = {appcod,prdcod,"1"};
				paramName = tmpParamName;
				paramValue = tmpParamValue;
			}else if(null == prdcod){
				hqlId = "PrdDao.queryUserPrdsWithNoPrdcod";
				String[] tmpParamName = {"peoplecode","appcod","sta"};
				String[] tmpParamValue = {peopleCode,appcod,"1"};
				paramName = tmpParamName;
				paramValue = tmpParamValue;
			}else{
				hqlId = "PrdDao.queryUserPrds";
				String[] tmpParamName = {"peoplecode","appcod","prdcod","sta"};
				String[] tmpParamValue = {peopleCode,appcod,prdcod,"1"};
				paramName = tmpParamName;
				paramValue = tmpParamValue;
			}
			li = (List<Map>) prdDao.findByNamedQueryAndNamedParamArr(hqlId, paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<?> queryPrdTrns(String prdcod) {
		List li = null;
		try {
			String[] paramName = {"prdcod", "trnsta","prddsta"};
			String[] paramValue = {prdcod,"1","1"};
			li = (List<Map>) prdDao.findByNamedQueryAndNamedParamArr("PrdDao.queryPrdTrns", paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return li;
	}
	

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<?> queryPrdInfo(String prdcod) {
		List<WfCfgdefPrdinfo> li = new ArrayList();
		WfCfgdefPrdinfo prdinfo = (WfCfgdefPrdinfo) CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).getRealObject(prdcod);
		if(null != prdinfo){
			li.add(prdinfo);
			return li;
		}
		try {
			String[] paramName = {"prdcod","prdsta"};
			String[] paramValue = {prdcod,"1"};
			li = (List<WfCfgdefPrdinfo>) prdDao.findByNamedQueryAndNamedParamArr("PrdDao.queryPrdInfo", paramName, paramValue);
			if(li.size()>0){
				CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue()).putRealObject(((WfCfgdefPrdinfo) li.get(0)).getPrdcod(),li.get(0));
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<?> queryPrddetail(String prdcod, String trncod) {
		List<Map> li = new ArrayList();
		List<WfCfgrefPrddetil> tmplst = (List<WfCfgrefPrddetil>) CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue()).getRealValues();
		for(WfCfgrefPrddetil prdd:tmplst){
			if(null == trncod){
				if(prdcod.equals(prdd.getPrdcod())){
					li.add(ParseEntity.parseEntityToMap(new HashMap<String,String>(), prdd));
				}
			}else{
				if(prdcod.equals(prdd.getPrdcod())&&trncod.equals(prdd.getTrncod())){
					li.add(ParseEntity.parseEntityToMap(new HashMap<String,String>(), prdd));
				}
			}
		}
		if(li.size()>0){
			return li;
		}
		try {
			String hqlId;
			String[] paramName;
			String[] paramValue;
			if(null == trncod){
				hqlId = "PrdDao.queryPrddetailWithNoTrncod";
				String[] tmpParamName = {"prddsta", "prdcod"};
				String[] tmpParamValue = {"1",prdcod};
				paramName = tmpParamName;
				paramValue = tmpParamValue;
			}else{
				hqlId = "PrdDao.queryPrddetail";
				String[] tmpParamName = {"prddsta", "prdcod","trncod"};
				String[] tmpParamValue = {"1",prdcod,trncod};
				paramName = tmpParamName;
				paramValue = tmpParamValue;
			}
			//查询出来的是个map
			li = (List<Map>) prdDao.findByNamedQueryAndNamedParamArr(hqlId, paramName, paramValue);
			WfCfgrefPrddetil prddetail;
			for(Map map : li){
				prddetail =  new WfCfgrefPrddetil();
				prddetail = (WfCfgrefPrddetil) ParseEntity.parseMapToEntity(map, prddetail);
				CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue()).putRealObject(prddetail.getId(),prddetail);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return li;
	}


	
	/*
	 * 
	 * (non-Javadoc)
	 * @see com.sinoway.stm.service.IStmManagerService#queryByPrdcod(java.lang.String)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List<String> queryByPrdcod(String prdcod) {
		String[] paramName = {"sta", "prdcod"};
		String[] paramValue = {"1", prdcod};
		try {
			return (List<String>) prdDao.findByNamedQueryAndNamedParamArr("PrdDao.queryByPrdcod", paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 根据产品编码查询当前使用该产品的机构人员信息
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<?> queryPoByPrdcod(String prdcod) {
		List li = null;
		try {
			String[] paramName = {"sta", "prdcod"};
			String[] paramValue = {"1", prdcod};
			li = prdDao.findByNamedQueryAndNamedParamArr("PrdDao.queryPoByPrdcod", paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updateUsrprd(String peoplecode, String prdcod,String sta) {
		try {
			String[] paramName = {"peoplecode", "prdcod","sta"};
			String[] paramValue = {peoplecode, prdcod,sta};
			prdDao.execNameQueryAndNamedParamArr("PrdDao.updateUsrprd", paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updateUsrprd(String prdcod, String sta) {
		try {
			String[] paramName = {"prdcod","sta"};
			String[] paramValue = {prdcod,sta};
			prdDao.execNameQueryAndNamedParamArr("PrdDao.updateUsrprdWithNoPeoplecode", paramName, paramValue);
		} catch (WFException e) {
			e.printStackTrace();
		}
		
	}
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
	@Override
	public FrotTrnObjInfo saveOrUpdateStm(XPeopleInfo people, FrotTrnObjInfo frotObj) {
		String prdcod = frotObj.getPrdcod();
		String prdnam = frotObj.getPrdnam();
		String appcod = frotObj.getAppcod();
		String prdtyp = frotObj.getPrdtyp();
		List<PeopleShowInfo> peoples = frotObj.getPeoples();
		List<Map<String,String>> trns = frotObj.getTrns();
		if(null == prdcod||"".equals(prdcod)){
			WfCfgdefPrdinfo prdinfo = new WfCfgdefPrdinfo();
			//1.1保存产品
			String prdId = GUIDGenerator.generateId();
			if(null == prdId || "".equals(prdId)){
				frotObj.setErrmsg("核心未返回产品编码...");
				
				return frotObj;
			}
			prdinfo.setId(prdId);
			prdinfo.setPrdnam(prdnam);
			prdinfo.setIsdefult("0");
			prdinfo.setSta(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
			prdinfo.setPrdtyp(prdtyp);
			prdinfo.setAppcod(appcod);
			prdinfo.setCretday(DateUtil.getCurrentDate8Len());
			try {
				StmResMsg resmsg = stmMsgtrnService.addOrUpdatePrd(people.getUsrId(), appcod,null, prdnam, trns,peoples);
				prdcod = resmsg.getPrdcod();
				if(Constant.HttpStatus.STATIS_FAIL.getCode().equals(resmsg.getStates()) ||resmsg.getStates() == null ){
					frotObj.setErrmsg("请求核心系统失败...");
					return frotObj;
				}
				if(null == prdcod || "".equals(prdcod)){
					frotObj.setErrmsg("核心未返回产品编码...");
					return frotObj;
				}
			} catch (Exception e) {
				frotObj.setErrcod("2");
				frotObj.setErrmsg("请求核心系统通信异常");
				e.printStackTrace();
				return frotObj;
			}
		 try{
			prdinfo.setPrdcod(prdcod);
			this.saveOrUpdatePrd(prdinfo);
			//2.1保存产品交易码关系
			WfCfgrefPrddetil prdd = null;
			for(Map<String,String> trnMap:trns){
				prdd = new WfCfgrefPrddetil();
				prdd.setId(GUIDGenerator.generateId());
				//返回的trn设置新的prddId值
				trnMap.put("prddid", prdd.getId());
				prdd.setTrncod(trnMap.get("trncod"));
				prdd.setPrdcod(prdcod);
				prdd.setPrdnam(prdnam);
				prdd.setDayexpcnt(trnMap.get("dayexpcnt"));
				prdd.setMonexpcnt(trnMap.get("monexpcnt"));
				prdd.setMon3expcnt(trnMap.get("mon3expcnt"));
				prdd.setSta(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
				this.saveOrUpdatePrddetail(prdd);
			}
			//3.1保存用户产品关系
			WfCfgrefCompusrprd usrprd = new WfCfgrefCompusrprd();
			usrprd.setId(GUIDGenerator.generateId());
			usrprd.setPeoplecode(people.getPeopleCode());
			usrprd.setPrdcod(prdcod);
			usrprd.setSta(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
			usrprd.setUsrtype(Constant.UsrType.USRTYPE_ENTERPRISE.getValue());
			this.saveOrUpdateUsrPrd(usrprd);
			//保存子账户的用户产品信息
			for(PeopleShowInfo p:peoples){
				usrprd = new WfCfgrefCompusrprd();
				usrprd.setId(GUIDGenerator.generateId());
				usrprd.setPeoplecode(p.getPeopleCode());
				usrprd.setPrdcod(prdcod);
				usrprd.setSta( ResultStatus.RESULTSTATUS_SUCCESS.getCode());
				usrprd.setUsrtype(Constant.UsrType.USRTYPE_ENTERPRISE.getValue());
				this.saveOrUpdateUsrPrd(usrprd);
			}
			//封装前台对象并返回
			List<Map> rstList = new ArrayList<Map>();
			//map<产品编码-详细信息>
			Map<String,Object> map = null;
			map = new HashMap<String,Object>();
			map.put("prdcod", prdcod);
			map.put("prdnam",prdnam);
			map.put("isdefult", prdinfo.getIsdefult());
			map.put("cretday", prdinfo.getCretday());
			List<Map<String,String>> po = new ArrayList<Map<String,String>>();
			Map<String,String> poMap = new HashMap<String,String>();
			poMap.put("organizeInfo", people.getOrganizeSid());
			poMap.put("orgName", people.getOrgName());
			poMap.put("peopleCode", people.getPeopleCode());
			poMap.put("peopleName", people.getPeopleName());
			po.add(poMap);
			for (PeopleShowInfo p :peoples) {
				poMap = new HashMap<String,String>();
				poMap.put("organizeInfo", p.getOrganizeInfo());
				poMap.put("orgName", p.getOrgName());
				poMap.put("peopleCode", p.getPeopleCode());
				poMap.put("peopleName", p.getPeopleName());
				po.add(poMap);
			}
			map.put("po", po);
			//获得当前产品编码的对应交易码信息
			map.put("trns",trns);
			rstList.add(map);
			frotObj.setObj(rstList);
			frotObj.setErrcod(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
			return frotObj;
		 }catch(Exception e){
			frotObj.setErrcod(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
			e.printStackTrace();
			return frotObj;
		 }
		}else{

			//1.2 更新产品
			WfCfgdefPrdinfo prdinfo = new WfCfgdefPrdinfo();
			StmResMsg resmsg = stmMsgtrnService.addOrUpdatePrd(people.getUsrId(),appcod, prdcod, prdnam, trns,peoples);
			if(null == resmsg.getStates()||Constant.HttpStatus.STATIS_FAIL.equals(resmsg.getStates())){
				frotObj.setErrcod(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
				frotObj.setErrmsg("请求核心系统通信异常");
				return frotObj;
			}
		 try{
			List<WfCfgdefPrdinfo> prdl = (List<WfCfgdefPrdinfo>) this.queryPrdInfo(prdcod);
			if(prdl.size() > 0){
				prdinfo = prdl.get(0);
				prdinfo.setPrdnam(prdnam);
				prdinfo.setPrdtyp(prdtyp);
				this.saveOrUpdatePrd(prdinfo);
			}
			//2.2更新产品交易码关系
			List<Map> oldPrddl = (List<Map>) this.queryPrddetail(prdcod, null);
			//新旧查询
			Map<String,String> map = new HashMap<String,String>();
			for (Map<String,String> oldMap : oldPrddl){
				map.put(oldMap.get("trncod"), "old");
			}
			for (Map<String,String> trnMap : trns){
				if(map.containsKey(trnMap.get("trncod"))){
					//old 旧包含新的元素
					map.put(trnMap.get("trncod"), "contain");
				}else{
					//new 旧不包含新的元素
					map.put(trnMap.get("trncod"), "new");
				}
			}
			WfCfgrefPrddetil prdd = null;
			for(String key:map.keySet()){
				if("old".equals(map.get(key))){
					for (Map<String,String> oldMap : oldPrddl){
						if(key.equals(oldMap.get("trncod"))){
							/*prdd = new WfCfgrefPrddetil();
							prdd = (WfCfgrefPrddetil) ParseEntity.parseMapToEntity(oldMap, prdd);*/
							prdd = this.queryPrddetilById(oldMap.get("prddid"));
							prdd.setPrdnam(prdnam);
							prdd.setSta( ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
							//不使用this调用,使用
							prdDao.saveOrUpdate(prdd);
							break;
						}
					}
				}else{
					for (Map<String,String> trnMap : trns){
						if(key.equals(trnMap.get("trncod"))){
							if("new".equals(map.get(key))){
								prdd = new WfCfgrefPrddetil();
								prdd.setId(GUIDGenerator.generateId());
								prdd.setTrncod(trnMap.get("trncod"));
								prdd.setPrdcod(prdcod);
								prdd.setPrdnam(prdnam);
								prdd.setDayexpcnt(trnMap.get("dayexpcnt"));
								prdd.setMonexpcnt(trnMap.get("monexpcnt"));
								prdd.setMon3expcnt(trnMap.get("mon3expcnt"));
								prdd.setSta(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
							}else{
								prdd = this.queryPrddetilById(trnMap.get("prddid"));
								prdd.setTrncod(trnMap.get("trncod"));
								prdd.setPrdcod(prdcod);
								prdd.setPrdnam(prdnam);
								prdd.setDayexpcnt(trnMap.get("dayexpcnt"));
								prdd.setMonexpcnt(trnMap.get("monexpcnt"));
								prdd.setMon3expcnt(trnMap.get("mon3expcnt"));
								prdd.setSta(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
							}
							//这里改用prdDao调用
							prdDao.saveOrUpdate(prdd);
							break;
						}
					}
				}
			}
			//2.3 更新用户产品关系    20160108修改产品时,屏蔽维护关系
			List<PeopleShowInfo> oldPeoples = (List<PeopleShowInfo>) poOrganizeinfoService.findCompusrByPrdcod(prdcod);
			Map<String,String> tmpUsrs = new HashMap<String,String>();
			for(PeopleShowInfo p:oldPeoples){
				tmpUsrs.put(p.getPeopleCode(), "old");
			}
			for(PeopleShowInfo p:peoples){
				if(tmpUsrs.containsKey(p.getPeopleCode())){
					tmpUsrs.put(p.getPeopleCode(),"contain");
				}else{
					tmpUsrs.put(p.getPeopleCode(),"new");
				}
			}
			WfCfgrefCompusrprd usrprd = null;
			for(String key:tmpUsrs.keySet()){
				if("new".equals(tmpUsrs.get(key))){
					usrprd = new WfCfgrefCompusrprd();
					usrprd.setId(GUIDGenerator.generateId());
					usrprd.setPeoplecode(key);
					usrprd.setPrdcod(prdcod);
					usrprd.setSta("1");
					usrprd.setUsrtype(Constant.UsrType.USRTYPE_ENTERPRISE.getValue());
					this.saveOrUpdateUsrPrd(usrprd);
				}else if("old".equals(tmpUsrs.get(key))){
					//修改状态
					this.updateUsrprd(key, prdcod, ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
				}
			}
			frotObj.setErrcod(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
		
		 }catch(Exception e ){
		   e.printStackTrace();
		   frotObj.setErrmsg("");
		   frotObj.setErrcod(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode()); 
		   return frotObj;
		 }
		  return frotObj;
		}
	}
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
	@Override
	public FrotTrnObjInfo delStm(XPeopleInfo people, FrotTrnObjInfo frotObj) {
		String prdcod = frotObj.getPrdcod();
		String appcod = frotObj.getAppcod();
		//1.向核心发送删除请求
		//stmMsgtrnService.sendCretPrd("delPrd", null);
		//2.更改用户产品关系表中的状态
//		String peopleCode = people.getPeopleCode();
		StmResMsg resmsg = stmMsgtrnService.delPrd(people.getUsrId(), prdcod,appcod);
		if(null == resmsg.getStates()||Constant.HttpStatus.STATIS_FAIL.getCode().equals(resmsg.getStates())){
			frotObj.setErrcod(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
			frotObj.setErrmsg("请求核心系统失败...");
			return frotObj;
		}
	 try{
		this.updateUsrprd(prdcod, ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
		//3.更改产品表中该产品的状态
		WfCfgdefPrdinfo prdinfo = new WfCfgdefPrdinfo();
		List<WfCfgdefPrdinfo> prdl = (List<WfCfgdefPrdinfo>)this.queryPrdInfo(prdcod);
		if(prdl.size() > 0){
			prdinfo = prdl.get(0);
			prdinfo.setSta(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
			this.saveOrUpdatePrd(prdinfo);
		}
		//4.更改产品交易码关系表中的状态
		WfCfgrefPrddetil prddetail;
		List<Map> prddl = (List<Map>) this.queryPrddetail(prdcod,null);
		List<WfCfgrefPrddetil> prddlst = new ArrayList<WfCfgrefPrddetil>();
		if(prddl.size()>0){
			for(Map map : prddl){
				//prddetail =  new WfCfgrefPrddetil();
				prddetail = this.queryPrddetilById((String)map.get("prddid"));
				prddetail.setSta(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
				prddlst.add(prddetail);
			}
			this.updatePrddetails(prddlst);
		}
		frotObj.setErrcod(ResultStatus.RESULTSTATUS_SUCCESS.getCode());
	 }catch(Exception e){
		 e.printStackTrace();
		 frotObj.setErrmsg(resmsg.getResult());
		 frotObj.setErrcod(ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode());
		 return frotObj;
	 }
		 return frotObj;
	}

	@Override
	public WfCfgrefPrddetil queryPrddetilById(String id) {
		WfCfgrefPrddetil prddetil = null;
		try {
			List<WfCfgrefPrddetil> prddList=  (List<WfCfgrefPrddetil>) prdDao.findByNamedQueryAndNamedParam("PrdDao.queryPrddetailById", "id", id);
			if(prddList.size() >0 ){
				prddetil = prddList.get(0);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return prddetil;
	}
	

	

}
