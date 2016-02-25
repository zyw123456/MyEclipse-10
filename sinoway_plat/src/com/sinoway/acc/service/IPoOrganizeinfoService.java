package com.sinoway.acc.service;

import java.util.List;

import com.sinoway.acc.entity.PeopleShowInfo;
import com.yzj.wf.common.WFException;
import com.yzj.wf.core.model.am.RoleGroupInfo;
import com.yzj.wf.core.model.po.OrganizeInfo;
import com.yzj.wf.core.model.po.PeopleInfo;
import com.yzj.wf.core.model.po.RoleGroupPeople;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;
import com.yzj.wf.plat.entity.WfCfgrefCompusrprd;

public interface IPoOrganizeinfoService {
	
	/**
	 * 查询登录父账号下的所有子账号
	 * @return
	 */
	public List<PeopleShowInfo> queryChildren(String parntcode);
	/**
	 * 查询登录父账号下的所有团队
	 * @return
	 */
	public List<OrganizeInfo> queryOrganize(XPeopleInfo curPeople);
	/**
	 * 根据子账号id查peopleShowInfo对象
	 * @return
	 */
	//public PeopleShowInfo getPeople(String peoId);
	/**
	 * 根据子账号id查peopleInfo对象
	 * @return
	 */
	public PeopleInfo getPeopleById(String peoId);
	
	/**
	 * 根据团队id查团队下的人员
	 * @return
	 */
	public List<PeopleShowInfo> getPeopleByOrgId(String orgId);
	/**
	 * 根据团队id查团队
	 * @return
	 */
	public OrganizeInfo getOrganize(String orgId);
	/**
	 * 查询用户账号下的所有产品
	 * @return
	 */
	public List<WfCfgdefPrdinfo> queryPrds(String peoplecode);
	/**
	 * 给子账户赋权限或修改
	 * @return
	 */
	public void setPermission(List<WfCfgrefCompusrprd> list);
	
	/**
	 * 新增一条人员信息或修改
	 * @return
	 */
	public void addPeople(PeopleInfo p);
	/**
	 * 查询用户对应产品(用户产品关系表)
	 * @return
	 */
	public List<WfCfgrefCompusrprd> getCompusrprd(String peoplecode);
	/**
	 * 根据id删除用户
	 * @return
	 */
	public void deletePeople(String peoId);
	/**
	 * 根据用户账号删除用户对应产品
	 * @return
	 */
	public void deletePrds(String peoplecode);
	/**
	 * 新增机构
	 * @return
	 */
	public void addOrganize(OrganizeInfo o);
	/**
	 * 给新增人员赋角色组
	 * @return
	 */
	public void addRoleGroupPeople(RoleGroupPeople rgp);
	/**
	 * 根据角色组id查角色组
	 * @return
	 */
	public RoleGroupInfo getRoleGroupById(String sid);
	/**
	 * 删除用户时，删除用户角色组关系
	 * @return
	 */
	public void deleteRoleGroupPeople(RoleGroupPeople rgp);
	/**
	 * 根据用户账号删除用户对应产品(逻辑删除)
	 * @return
	 */
	public void deletePrdsByUpdate(String peoplecode);
	/**
	 * 根据登录账户模糊查询其子账户
	 * @param parntCode 登陆账户
	 * @param dataStr 子账户字符串
	 * @return
	 */
	public List querfuzzyQueryChildren(String parntCode,String dataStr);
	/**
	 * 查询登录父账号下的所有团队的团队进行模糊查询
	 * @param curPeople
	 * @param str
	 * @return
	 */
	public List fuzzyQueryOrganize(XPeopleInfo curPeople,String str);
	/**
	 * 根据产品编码查询当前产品的用户信息
	 * @param prdcod
	 * @return
	 */
	public List<?> findCompusrByPrdcod(String prdcod);
	/**
	 * 通过产品编码查询原交易码
	 */
	public List findWfcfgerfPrdById(String prdcod);
	/**
	 * 根据id查询使用人信息
	 * @param sid
	 * @return
	 */
	public List findPeopleInfoById(String sid);
	/**
	 * 根据peoplecode校验输入的账号是否重复
	 * @param sid
	 * @return
	 */
	public List findPeopleInfoByPeoplecode(String peoplecode);
	public List findPeopleInfoByNewTeam(String newTeam);
	/**
	 * /**
	 * 新增子账户并赋权限（与之前方法不同于，任何一个操作出现问题，则事物全部回滚）
	 * @param sid 角色组id
	 * @param rgp 人员与团队关系
	 * @param list 企业用户与产品对照关系
	 * @param p 人员信息
	 * @author PengGaobo
	 * @throws WFException 
	 *
	 */
	public void addNewRoleGroupPeople(String sid,RoleGroupPeople rgp,List<WfCfgrefCompusrprd> list,PeopleInfo p) throws WFException;
	/**
	 * 子账户中删除子账户并且解除其与团队的关系（与之前方法不同于，任何一个操作出现问题，则事物全部回滚）
	 * @param rgp
	 * @param peoplecode
	 * @author PengGaobo
	 * @throws WFException 
	 */
	public void deleteRoleGroupPeopleInfo(String peoId,String sid,RoleGroupPeople rgp,String peoplecode) throws WFException;
	/**
	 * 子账户信息修改（与之前方法不同于，任何一个操作出现问题，则事物全部回滚）
	 * @param peoplecode 
	 * @param list
	 * @param p
	 * @author PengGaobo
	 * @throws WFException 
	 */
	public void updateRoleGroupPeopleInfo(String peoplecode,List<WfCfgrefCompusrprd> list,PeopleInfo p) throws WFException;
}
