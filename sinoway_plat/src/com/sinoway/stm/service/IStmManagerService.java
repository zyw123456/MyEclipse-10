package com.sinoway.stm.service;

import java.util.List;

import com.sinoway.stm.entity.FrotTrnObjInfo;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;
import com.yzj.wf.plat.entity.WfCfgrefCompusrprd;
import com.yzj.wf.plat.entity.WfCfgrefPrddetil;


public interface IStmManagerService {
	/**
	 * 查询用户的产品,当产品为空时查询所有
	 * @param peopleCode
	 * @param prdTyp
	 * @return
	 */
	public List<?> queryUserPrds(String peopleCode,String appcod,String prdcod);
	
	/**
	 * 根据产品编码查询交易码信息
	 * @param prdcod
	 * @return
	 */
	public List<?> queryPrdTrns(String prdcod);
	
	/**
	 * 根据prdcod查询产品信息
	 * @param prdcod
	 * @return
	 */
	public List<?> queryPrdInfo(String prdcod);
	
	/**
	 * 查询产品明细信息
	 * @param prdcod
	 * @param trncod 不为null时查询指定的产品信息
	 * @return
	 */
	public List<?> queryPrddetail(String prdcod,String trncod);

	/**
	 * 查询某产品的机构人员信息
	 * @param prdcod
	 * @return
	 */
	public List<?> queryPoByPrdcod(String prdcod);
	
	public void saveOrUpdatePrd(WfCfgdefPrdinfo prdinfo);

	public void saveOrUpdatePrddetail(WfCfgrefPrddetil prddetail);

	public void saveOrUpdateUsrPrd(WfCfgrefCompusrprd usrprd);
	
	public void updatePrds(List<WfCfgdefPrdinfo> prds);
	
	public void updateUsrPrds(List<WfCfgrefCompusrprd> usrprds);
	
	public void updatePrddetails(List<WfCfgrefPrddetil> prddetails);
	
	public void updateUsrprd(String peoplecode,String prdcod,String sta);
	
	public void updateUsrprd(String prdcod,String sta);
	/**
	 * 编码查询返回产品明细表
	 * @param code
	 * @return
	 */
	public List<String> queryByPrdcod(String prdcod);
	
	public FrotTrnObjInfo saveOrUpdateStm(XPeopleInfo people,FrotTrnObjInfo frotObj);
	public FrotTrnObjInfo delStm(XPeopleInfo people,FrotTrnObjInfo frotObj);
	/**
	 * 根据产品明细编码查产品明细
	 * @param id
	 * @return
	 */
	public WfCfgrefPrddetil queryPrddetilById(String id);
}
