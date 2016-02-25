package com.sinoway.acc.service;

import java.util.List;
import java.util.Map;

import com.sinoway.acc.entity.WfCfgdefCompinfo;
import com.sinoway.acc.entity.WfCfgdefPwd;

public interface IAccountService {

	/**
	 * 新增公司基本信息
	 * @param appcod
	 * @return
	 */
	public void createCompanyInfo(WfCfgdefCompinfo wfCfgdefCompinfo,String flag) throws Exception;
	/**
	 * 查询公司基本信息
	 * @param appcod
	 * @param peoplecode
	 * @return
	 */
	public List<Map> searchCompany(String compno);
	/**
	 * 查询当前登录人的公司信息
	 * @param id
	 * @return
	 */
	public List findCurrentCompInfo(String id);
	/**
	 * 修改当前公司信息
	 * @param comp
	 */
	public void updateCurrentCompInfo(WfCfgdefCompinfo comp);
	/**
	 * 通过id查询机构信息
	 * @param id
	 * @return
	 */
	public List findOrganiznfoById(String id);
}
