package com.sinoway.acc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.acc.entity.PeopleShowInfo;
import com.sinoway.acc.service.IPoOrganizeinfoService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.cache.CacheEnum;
import com.yzj.wf.common.cache.CacheFactory;
import com.yzj.wf.common.db.dao.BaseDao;
import com.yzj.wf.core.model.am.RoleGroupInfo;
import com.yzj.wf.core.model.po.OrganizeInfo;
import com.yzj.wf.core.model.po.PeopleInfo;
import com.yzj.wf.core.model.po.RoleGroupPeople;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;
import com.yzj.wf.plat.entity.WfCfgrefCompusrprd;

public class PoOrganizeinfoService implements IPoOrganizeinfoService{
	
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 查询登录父账号下的所有子账号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<PeopleShowInfo> queryChildren(String parntcode) {
		List<PeopleShowInfo> list = new ArrayList<PeopleShowInfo>();
		try {
				String[] paramNames={"parntCode","peopleState"};
				Object[] paramValues={parntcode,(short)0};
				List<PeopleInfo> peolist = (List<PeopleInfo>)baseDao.findByNamedQueryAndNamedParamArr("AccDao.queryChildren", paramNames, paramValues);
				for(PeopleInfo p:peolist){
					list.add(this.showPeopleInfo(p));
				}
		} catch (WFException e) {
				e.printStackTrace();
		}
		return list;
		
	}

	/**
	 * 查询登录父账号下的所有团队
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<OrganizeInfo> queryOrganize(XPeopleInfo curPeople) {
		List<OrganizeInfo> list = new ArrayList<OrganizeInfo>();
		StringBuffer hql = new StringBuffer();
			hql.append("with org(corgno,sid,parentorg,orgname) as (select corgno,sid,parentorg,orgname from po_organizeinfo where parentorg='"+curPeople.getOrganizeSid()+"' union all select a.corgno,a.sid,a.parentorg,");
			hql.append("a.orgname from po_organizeinfo a,org b where a.parentorg = b.sid ) select corgno,orgname,sid from org");
		try {
		List<Map<String,String>> maplist = (List<Map<String,String>>)baseDao.execSqlQuery(hql.toString());
			for(Map<String,String> m:maplist){
				OrganizeInfo o = new OrganizeInfo();
				o.setSid(m.get("SID"));
				o.setOrgName(m.get("ORGNAME"));
				list.add(o);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据子账号id查peopleInfo对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PeopleInfo getPeopleById(String peoId){
		PeopleInfo p = (PeopleInfo) CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).getCloneObject(peoId);
		if(p==null){
			try {
				List<PeopleInfo> list = (List<PeopleInfo>) baseDao.findByNamedQueryAndNamedParam("AccDao.getPeople", "id", peoId);
				if(list.size()>0){	
					p = list.get(0);
				}
				if(p!=null){	
					CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).putRealObject(p.getSid(),p);
				}
			} catch (WFException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	/**
	 * 根据团队id查团队下的人员
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List<PeopleShowInfo> getPeopleByOrgId(String orgId){
		List<PeopleShowInfo> list = new ArrayList<PeopleShowInfo>();
		try {
			String[] paramNames={"organizeInfo","peopleState"};
			Object[] paramValues={orgId,(short)0};
			List<PeopleInfo> peolist= (List<PeopleInfo>)baseDao.findByNamedQueryAndNamedParamArr("AccDao.getPeopleByOrgId", paramNames, paramValues);
			for(PeopleInfo p:peolist){
					list.add(this.showPeopleInfo(p));
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return list;		
	}
	/**
	 * 根据团队id查团队
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public OrganizeInfo getOrganize(String orgId){
		OrganizeInfo o = (OrganizeInfo) CacheFactory.getCacheInstance(CacheEnum.OrganizeInfoCache.getValue()).getRealObject(orgId);
		if(o!=null){
			return o;
		}
		try {
			List<OrganizeInfo> list = (List<OrganizeInfo>)baseDao.findByNamedQueryAndNamedParam("AccDao.getOrganize", "id", orgId);
			if(list.size()>0){
				o = list.get(0);
			}
			if(o!=null){	
				CacheFactory.getCacheInstance(CacheEnum.OrganizeInfoCache.getValue()).putRealObject(o.getSid(),o);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return o;
	}
	/**
	 * 查询用户下的所有产品
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<WfCfgdefPrdinfo> queryPrds(String peoplecode) {
		List<WfCfgdefPrdinfo> list = null;
		try { 
			String[] paramNames = {"peoplecode","sta"};
			Object[] paramValues= {peoplecode,"1"};
			list = (List<WfCfgdefPrdinfo>)baseDao.findByNamedQueryAndNamedParamArr("AccDao.queryPrds", paramNames, paramValues);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 给子账户赋权限或修改
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void setPermission(List<WfCfgrefCompusrprd> list) {
		for (int i = 0; i < list.size(); i++) {
			WfCfgrefCompusrprd w = list.get(i);
			try {
				baseDao.saveOrUpdate(w);
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).putRealObject(w.getId(),w);
			} catch (WFException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 新增一条人员信息或修改
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void addPeople(PeopleInfo p) {
		try {
			baseDao.saveOrUpdate(p);
			CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).putRealObject(p.getSid(),p);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询用户对应产品(用户产品关系表)
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<WfCfgrefCompusrprd> getCompusrprd(String peoplecode) {
		List<WfCfgrefCompusrprd> list = new ArrayList<WfCfgrefCompusrprd>();
		/*List<WfCfgrefCompusrprd> cachelist = (List<WfCfgrefCompusrprd>) CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).getRealValues();
		for (WfCfgrefCompusrprd wcc : cachelist) {
			if(peoplecode.equals(wcc.getPeoplecode()) && "1".equals(wcc.getSta())){
				list.add(wcc);
			}
		}
		if(list.size()>0){
			return list;
		}*/
		try {
			String[] paramNames={"peoplecode","sta"};
			Object[] paramValues={peoplecode,"1"};
			list = (List<WfCfgrefCompusrprd>)baseDao.findByNamedQueryAndNamedParamArr("AccDao.getCompusrprd", paramNames, paramValues);
			for (WfCfgrefCompusrprd wcc:list){
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).putRealObject(wcc.getId(),wcc);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据id删除用户
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void deletePeople(String peoId) {
		try {
			PeopleInfo p = this.getPeopleById(peoId);
			p.setPeopleState((short)-1);
			baseDao.saveOrUpdate(p);
			CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).removeRealObject(p);
			
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据用户账号删除用户对应产品(物理删除)
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public void deletePrds(String peoplecode){
		List<WfCfgrefCompusrprd> list = this.getCompusrprd(peoplecode);
		try {
			baseDao.deleteAll(list);
			for (WfCfgrefCompusrprd wcc : list) {
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).removeRealObject(wcc);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增机构
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void addOrganize(OrganizeInfo o) {
		try {
			baseDao.saveOrUpdate(o);
			CacheFactory.getCacheInstance(CacheEnum.OrganizeInfoCache.getValue()).putRealObject(o.getSid(),o);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给新增人员赋角色组
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void addRoleGroupPeople(RoleGroupPeople rgp){
		try {
			baseDao.saveOrUpdate(rgp);
			//CacheFactory.getCacheInstance(CacheEnum.RoleGroupPeopleCache.getValue()).putRealObject(rgp,rgp);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据角色组id查角色组
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public RoleGroupInfo getRoleGroupById(String sid) {
		RoleGroupInfo roleGroup = (RoleGroupInfo) CacheFactory.getCacheInstance(CacheEnum.RoleGroupInfoCache.getValue()).getRealObject(sid);
		if(roleGroup != null){
			return roleGroup;
		}
		try {
			roleGroup = (RoleGroupInfo)baseDao.findByNamedQueryAndNamedParam("AccDao.getRoleGroupById", "id",sid);
			CacheFactory.getCacheInstance(CacheEnum.RoleGroupInfoCache.getValue()).putRealObject(roleGroup.getSid(), roleGroup);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return roleGroup;
	}
	/**
	 * 删除用户时，删除用户角色组关系
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void deleteRoleGroupPeople(RoleGroupPeople rgp) {
		try {
			baseDao.delete(rgp);
			CacheFactory.getCacheInstance(CacheEnum.RoleGroupPeopleCache.getValue()).removeRealObject(rgp);
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据用户账号删除用户对应产品(逻辑删除)
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	public void deletePrdsByUpdate(String peoplecode){
		List<WfCfgrefCompusrprd> list = this.getCompusrprd(peoplecode);
		for (int i = 0; i < list.size(); i++) {
			WfCfgrefCompusrprd w = list.get(i);
			w.setSta("0");
		}
		try {
			baseDao.batchUpdate(list);
			for (WfCfgrefCompusrprd wcc : list) {
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).removeRealObject(wcc);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 对用户列表展示信息处理
	 * @return
	 */
	public PeopleShowInfo showPeopleInfo(PeopleInfo p){
		PeopleShowInfo psi = new PeopleShowInfo();
		psi.setPeopleName(p.getPeopleName());
		psi.setSid(p.getSid());
		psi.setPeopleCode(p.getPeopleCode());
		psi.setPwd(p.getPwd());
		psi.setUsrId(p.getUsrId());
		OrganizeInfo o = this.getOrganize(p.getOrganizeInfo());
		//psi.setOrganizeInfo(o.getOrgName());
		psi.setOrganizeInfo(o.getSid());
		psi.setOrgName(o.getOrgName());
		psi.setOrgNo(o.getOrgNo());
		if(p.getPeopleState() == 0){
			psi.setPeopleState("正常");
		}else{
			psi.setPeopleState("禁用");
		}
		return psi;	
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List querfuzzyQueryChildren(String parntCode, String dataStr) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		//StringBuffer sql = new StringBuffer("select * from PO_PEOPLEINFO where PEOPLESTATE=0");
		StringBuffer sql = new StringBuffer("select SID,PEOPLECODE,PARNTCODE,USRID,PEOPLENAME,PEOPLEGENDER,PWD,PEOPLESTATE,ORGANIZEINFO,DEFAULTDESKTOP,MEMO,TAG,DEFAULTCONFIG,LASTCHANGEPWDTIME,PASSWORDERRCOUNT,RECENTPASSWORDRECORD,PHONE,EMAIL,PEOPLETYPE,PEOPLEIDCARD,PEOPLELEVEL from PO_PEOPLEINFO where PEOPLESTATE=0");
		if(!"".equals(parntCode)){
			sql.append(" and PARNTCODE ='"+parntCode+"'");
		}
		if(!"".equals(dataStr)){
			sql.append(" and UPPER(PEOPLENAME) like UPPER('%"+dataStr+"%')");
		}
		try {
			list = (List) baseDao.execSqlQuery(sql.toString());
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List fuzzyQueryOrganize(XPeopleInfo curPeople, String str) {
		List<OrganizeInfo> list = new ArrayList<OrganizeInfo>();
		StringBuffer hql = new StringBuffer();
			hql.append("with org(corgno,sid,parentorg,orgname) as (select corgno,sid,parentorg,orgname from po_organizeinfo where parentorg='"+curPeople.getOrganizeSid()+"' and UPPER(orgname) like Upper('%"+str+"%"+"') union all select a.corgno,a.sid,a.parentorg,");
			hql.append("a.orgname from po_organizeinfo a,org b where a.parentorg = b.sid ) select corgno,orgname,sid from org");
		try {
		List<Map<String,String>> maplist = (List<Map<String,String>>)baseDao.execSqlQuery(hql.toString());
			for(Map<String,String> m:maplist){
				OrganizeInfo o = new OrganizeInfo();
				o.setSid(m.get("SID"));
				o.setOrgName(m.get("ORGNAME"));
				list.add(o);
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<?> findCompusrByPrdcod(String prdcod) {
		String[] paramNames = {"prdcod","sta"};
		Object[] paramValues= {prdcod,"1"};
		List list = null;
		try {
			list = (List)baseDao.findByNamedQueryAndNamedParamArr("AccDao.queryCompusrByPrdcod", paramNames, paramValues);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List findWfcfgerfPrdById(String prdcod) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
	//	String sql = "select * from WF_CFGREF_PRDDETIL where PRDCOD ='"+prdcod+"'";
		String sql = "select ID,PRDCOD,PRDNAM,PRDTYP,TRNCOD,DAYEXPCNT,MONEXPCNT,MON3EXPCNT,WARNFREQ,STA from WF_CFGREF_PRDDETIL where PRDCOD ='"+prdcod+"'";
		try {
			list = (List) baseDao.execSqlQuery(sql);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List findPeopleInfoById(String sid) {
		// TODO Auto-generated method stub
		List<PeopleShowInfo> list = new ArrayList<PeopleShowInfo>();
		try {
			String[] paramNames={"sid","peopleState"};
			Object[] paramValues={sid,(short)0};
			List<PeopleInfo> peolist = (List<PeopleInfo>)baseDao.findByNamedQueryAndNamedParamArr("AccDao.findPeopleInfoById", paramNames, paramValues);
			for(PeopleInfo p:peolist){
				list.add(this.showPeopleInfo(p));
			}
	} catch (WFException e) {
			e.printStackTrace();
	}
	return list;
	}

	public List findPeopleInfoByPeoplecode(String peoplecode) {
		List peopleList = new ArrayList();
		try{
			peopleList = (List<PeopleInfo>)baseDao.findByNamedQueryAndNamedParam("AccDao.findPeopleInfoByPeoplecode", "peopleCode", peoplecode);
		}catch(WFException e){
		 e.printStackTrace();
		}
		return peopleList;
	}

	public List findPeopleInfoByNewTeam(String newTeam) {
		List peopleList = new ArrayList();
		try{
			peopleList = (List<PeopleInfo>)baseDao.findByNamedQueryAndNamedParam("AccDao.findPeopleInfoByNewTeam", "newTeam", newTeam);
		}catch(WFException e){
		 e.printStackTrace();
		}
		return peopleList;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void addNewRoleGroupPeople(String sid,RoleGroupPeople rgp,List<WfCfgrefCompusrprd> list, PeopleInfo p) throws WFException {
		try {
			RoleGroupInfo roleGroup = (RoleGroupInfo) CacheFactory.getCacheInstance(CacheEnum.RoleGroupInfoCache.getValue()).getRealObject(sid);
			if(roleGroup != null){//根据角色组id获取角色组
				rgp.setRoleGroupInfo(roleGroup);
			}else{				
				roleGroup = (RoleGroupInfo)baseDao.findByNamedQueryAndNamedParam("AccDao.getRoleGroupById", "id",sid);
				CacheFactory.getCacheInstance(CacheEnum.RoleGroupInfoCache.getValue()).putRealObject(roleGroup.getSid(), roleGroup);
				rgp.setRoleGroupInfo(roleGroup);
			}
			baseDao.saveOrUpdate(rgp);//给新增人员赋角色组
			for (int i = 0; i < list.size(); i++) {
				WfCfgrefCompusrprd w = list.get(i);
				try {
					baseDao.saveOrUpdate(w);//给子账户赋权限
					CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).putRealObject(w.getId(),w);
				} catch (WFException e) {
					e.printStackTrace();
				}
			}
			baseDao.saveOrUpdate(p);//新增一条人员信息
			CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).putRealObject(p.getSid(),p);
		} catch (WFException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void deleteRoleGroupPeopleInfo(String peoId,String sid,RoleGroupPeople rgp, String peoplecode) throws WFException {
		try {
			//根据id删除用户(状态删除)
			PeopleInfo p = this.getPeopleById(peoId);
			p.setPeopleState((short)-1);
			baseDao.saveOrUpdate(p);
			CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).removeRealObject(p);
			//根据角色组id获取角色组
			RoleGroupInfo roleGroup = (RoleGroupInfo) CacheFactory.getCacheInstance(CacheEnum.RoleGroupInfoCache.getValue()).getRealObject(sid);
			if(roleGroup != null){
				rgp.setRoleGroupInfo(roleGroup);
			}else{				
				roleGroup = (RoleGroupInfo)baseDao.findByNamedQueryAndNamedParam("AccDao.getRoleGroupById", "id",sid);
				CacheFactory.getCacheInstance(CacheEnum.RoleGroupInfoCache.getValue()).putRealObject(roleGroup.getSid(), roleGroup);
				rgp.setRoleGroupInfo(roleGroup);
			}
			//删除用户时，删除用户角色组关系
			baseDao.delete(rgp);
			CacheFactory.getCacheInstance(CacheEnum.RoleGroupPeopleCache.getValue()).removeRealObject(rgp);
			List<WfCfgrefCompusrprd> list = this.getCompusrprd(peoplecode);
			for (int i = 0; i < list.size(); i++) {
				WfCfgrefCompusrprd w = list.get(i);
				w.setSta("0");
			}
			//根据用户账号删除用户对应产品(逻辑删除)
			baseDao.batchUpdate(list);
			for (WfCfgrefCompusrprd wcc : list) {
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).removeRealObject(wcc);
			}
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void updateRoleGroupPeopleInfo(String peoplecode,List<WfCfgrefCompusrprd> list, PeopleInfo p) throws WFException {
		//根据用户账号删除用户对应产品(逻辑删除)
		List<WfCfgrefCompusrprd> lists = this.getCompusrprd(peoplecode);
		for (int i = 0; i < lists.size(); i++) {
			WfCfgrefCompusrprd w = lists.get(i);
			w.setSta("0");
		}
		try {
			baseDao.batchUpdate(lists);
			for (WfCfgrefCompusrprd wcc : lists) {
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).removeRealObject(wcc);
			}
			// 给子账户赋权限
			for (int i = 0; i < list.size(); i++) {
				WfCfgrefCompusrprd w = list.get(i);
				baseDao.saveOrUpdate(w);
				CacheFactory.getCacheInstance(CacheEnum.CompusrPrdCache.getValue()).putRealObject(w.getId(),w);
			}
			//新增一条人员信息或修改
			baseDao.saveOrUpdate(p);
			CacheFactory.getCacheInstance(CacheEnum.PeopleInfoCache.getValue()).putRealObject(p.getSid(),p);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
}	

