package com.sinoway.fad.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.common.entity.WfCfgrefTrninele;
import com.sinoway.common.util.Constant.RptStatus;
import com.sinoway.rpt.dao.WfDatCreditrptDao;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;

/**
 * 反欺诈云Service
 * 
 * @author user
 *
 */
public interface IFraudService {

	/**
	 * 根据原报告元素和报告编号查询查询 原报告元素
	 * 
	 */
	public List findOldElecod(String rptid);
	/**
	 * 查询报告流转页面元素 +原报告元素
	 * 
	 */
	public List<WfCfgrefTrninele> findTrninele(String prdcod,String elecod);

	/**
	 * 查询报告页面元素
	 * 
	 */
	public List<WfCfgrefTrninele> findPageElecod(String prdcod);
	public List findOldTranByRptid(String rptId);
	/**
	 * 查询已经拥有原交易
	 * 
	*/
	public List findTranByRptid(String rptId);
	
	/**
	 *发起一个报告 向后台发起请求
	 * 
	 * @throws WFException
	 */
	public void createReport(WfDatCreditrpt creditrpt) throws WFException;

	
	
	/**
	 * 删除一个反欺诈报告
	 * @param creditrpt
	 * @throws Exception
	 */
	public void deleteByRptids( String[] rptids) throws Exception;
	
	
	
	
	
	/**
	 * 根据改登录账号以及应用编号查询配置的验证产品、反欺诈策略
	 * @param peopleCode
	 * @return
	 * @throws Exception
	 */
	public List<WfCfgdefPrdinfo> findPrdinfoByPeopleAndAppcod(String appcod,String peopleCode) throws Exception;

	
	
	/**
	 * 根据报告Id查找相应报告信息
	 * @param rptid
	 * @param peoplecode
	 * @throws WFException 
	 */
	public WfDatCreditrpt getFraudByrptid(String rptid) throws WFException;

	
	/**
	 * 更改报告实体类
	 * @param creditrpt
	 * @throws WFException 
	 */
	public void updateCreditrpt(WfDatCreditrpt creditrpt) throws WFException;

}
